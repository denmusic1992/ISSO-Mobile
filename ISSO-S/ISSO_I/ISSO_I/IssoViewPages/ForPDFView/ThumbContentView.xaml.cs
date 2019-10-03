using CommonClassesLibrary;
using FFImageLoading;
using FFImageLoading.Forms;
using ISSO_I.Additional_Classes;
using ISSO_I.IssoViewPages.ForPhotos;
using Mono.Data.Sqlite;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Threading.Tasks;
using CarouselView.FormsPlugin.Abstractions;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class ThumbContentView : IDisposable
	{
		/// <summary>
		/// 
		/// </summary>
		private List<DataInformation> _dataInformation = new List<DataInformation>();
		/// <summary>
		/// 
		/// </summary>
		private int _cIsso;

		private bool Tapped { get; set; }

		public ThumbContentView()
		{
			InitializeComponent();
			lv_schemes.ItemTapped += Lv_schemes_ItemTapped;
		}

		private void Lv_schemes_ItemTapped(object sender, ItemTappedEventArgs e)
		{
			((ListView)sender).SelectedItem = null;
		}

		public void Initialize(int cIsso)
		{
			_cIsso = cIsso;
			var page = new LoadingPopupPage("Подождите, идет загрузка схем...", true);
			Navigation.PushPopupAsync(page, false);
			AddThumbnailsAsync(cIsso);
		}

		private void AddThumbnailsAsync(int cIsso)
		{
			// Вытаскиваем информацию
			Task.Factory.StartNew(() =>
			{
				var pathToDir = Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/";
				// Чистим предыдущие фотки
				if (Directory.Exists(pathToDir))
				{
					DependencyService.Get<ILocalFileProvider>().DeleteFilesFromDir(pathToDir);
				}
				using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
				{
					try
					{
						_dataInformation = new List<DataInformation>();
						var command = connection.CreateCommand();
						command.CommandText =
							$"select * from I_SXEMA where C_ISSO={cIsso} and SXEMA is not null order by N";
						command.CommandTimeout = 30;
						command.CommandType = System.Data.CommandType.Text;
						connection.Open();
						using (var datareader = command.ExecuteReader())
						{
							if (datareader.HasRows)
							{
								while (datareader.Read())
								{
									// Берем данные по фотографии
									var photo = DependencyService.Get<IMediaService>()
										.ResizeImage(
											Convert.FromBase64String(datareader.GetString(datareader.GetOrdinal("PREVIEW"))),
											CommonStaffUtils.StandardImageWidth, CommonStaffUtils.StandardImageHeight);
									var date = datareader["SXEMA_DATE"] != DBNull.Value
										? datareader.GetInt64(datareader.GetOrdinal("SXEMA_DATE"))
										: 0;
									var info = datareader["TITR"] != DBNull.Value ? datareader.GetString(datareader.GetOrdinal("TITR")) : "";
									var n = datareader["N"] != DBNull.Value ? datareader.GetInt32(datareader.GetOrdinal("N")) : 0;

									var newPath =
										$"{Environment.GetFolderPath(Environment.SpecialFolder.Personal)}/ISSO-I/{CommonStaffUtils.RandomNumber(1, 9999999)}";
									if (!Directory.Exists(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/"))
										Directory.CreateDirectory(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/");

									using (var fileStream = new FileStream(newPath, FileMode.OpenOrCreate, FileAccess.Write))
									{
										fileStream.Write(photo, 0, photo.Length);
									}

									_dataInformation.Add(new DataInformation(info, date, photo, newPath, n));
								}
							}
							datareader.Dispose();
						}
						command.Dispose();
					}
					catch (Exception ex)
					{
						Debug.WriteLine($"Произошла ошибка в БД: \n{ex.Message} \nStackTrace: {ex.StackTrace}");
					}
					finally
					{
						connection.Close();
					}
				}

				Device.BeginInvokeOnMainThread(() =>
				{
					lv_schemes.ItemsSource = _dataInformation;
					if (_dataInformation.Count <= 0)
					{
						Content = new Label
						{
							VerticalOptions = LayoutOptions.Center,
							HorizontalOptions = LayoutOptions.Center,
							Text = "Схемы для данного сооружения отсутствуют."
						};
					}
					Navigation.PopPopupAsync();
				});


				// Добавляем данные в StackLayout
				//foreach (DataInformation information in dataInformation)
				//{
				//    // Создаем ContentView
				//    StackLayout contentViewPhoto = new StackLayout
				//    {
				//        VerticalOptions = LayoutOptions.FillAndExpand,
				//        HorizontalOptions = LayoutOptions.FillAndExpand,
				//        Orientation = StackOrientation.Vertical,
				//    };
				//    Image image = new Image
				//    {
				//        Margin = new Thickness(10, 10, 10, 0),
				//        VerticalOptions = LayoutOptions.FillAndExpand,
				//        HorizontalOptions = LayoutOptions.FillAndExpand,
				//        Source = ImageSource.FromStream(() => new MemoryStream(information.Array)),
				//        ClassId = String.Format("{0}", information.Image_source)
				//    };
				//    var date_str = information.Date != 0 ? new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(information.Date).ToString("dd.MM.yyyy") : "";
				//    Label label = new Label
				//    {
				//        VerticalOptions = LayoutOptions.Start,
				//        HorizontalOptions = LayoutOptions.Fill,
				//        VerticalTextAlignment = TextAlignment.Start,
				//        HorizontalTextAlignment = TextAlignment.Center,
				//        Text = !date_str.Equals("") ? String.Format("{0}\n{1}", date_str, information.Info) : information.Info,
				//        Margin = new Thickness(0, 10)
				//    };

				//    var tap = new TapGestureRecognizer();
				//    tap.Tapped += (sender, e) =>
				//    {
				//        ShowPDF(Convert.ToInt32((sender as View).ClassId));
				//    };
				//    image.GestureRecognizers.Add(tap);

				//    BoxView boxView = new BoxView
				//    {
				//        HeightRequest = 1,
				//        Margin = 1,
				//        Opacity = 0.8,
				//        BackgroundColor = Color.FromHex("Accent")
				//    };
				//    // Добавляем в stacklayout наши фотографии с описанием
				//    contentViewPhoto.Children.Add(image);
				//    contentViewPhoto.Children.Add(label);
				//    //contentViewPhoto.Children.Add(boxView);
				//    Device.BeginInvokeOnMainThread(() => { thumb_container.Children.Add(contentViewPhoto); });
				//}
				//Device.BeginInvokeOnMainThread(() => { OnSizeAllocated(App.Current.MainPage.Width, scroll_thumb.Height); Navigation.PopPopupAsync(); });
			});
		}

		private async void ShowPdf(int index)
		{
			var pdfContentView = new PDFContentView(_cIsso, index);
			await Navigation.PushAsync(pdfContentView);
			Tapped = false;
		}

		//private void PdfContentView_PDF_CLOSED(object sender, EventArgs e)
		//{
		//    //DependencyService.Get<ILocalFileProvider>().DeleteFilesFromDir(DependencyService.Get<ILocalFileProvider>().getRootDir());
		//    // Чистим предыдущие фотки
		//    if (Directory.Exists(ConnectionClass.PATH_TO_SCHEMES))
		//    {
		//        DirectoryInfo di = new DirectoryInfo(ConnectionClass.PATH_TO_SCHEMES);
		//        foreach (FileInfo file in di.GetFiles())
		//        {
		//            file.Delete();
		//        }
		//    }
		//}

		private void Thumbnail_Clicked(object sender, EventArgs eventArgs)
		{
			if (Tapped) return;
			Tapped = true;
			var image = sender as CachedImage;
			var photoInfo = image?.BindingContext as DataInformation;
			var index = _dataInformation.IndexOf(photoInfo);
			ShowPdf(_dataInformation[index].N);
		}

		//protected override void OnSizeAllocated(double width, double height)
		//{
		//    base.OnSizeAllocated(width, height);
		//    if (width > height)
		//    {
		//        scroll_thumb.Orientation = ScrollOrientation.Both;
		//        thumb_container.Orientation = StackOrientation.Horizontal;
		//        foreach (StackLayout view in thumb_container.Children)
		//        {
		//            view.Children[1].WidthRequest = width / 2;
		//            var lbl_height = DependencyService.Get<ITextMeter>().MeasureTextSize(((Label)view.Children[1]).Text, width / 2, ((Label)view.Children[1]).FontSize);
		//            view.Children[0].HeightRequest = height - lbl_height - 40;
		//            //view.Children[0].HeightRequest = height * 2 / 3;
		//            view.Children[0].VerticalOptions = LayoutOptions.Fill;
		//            view.Children[1].VerticalOptions = LayoutOptions.FillAndExpand;
		//            view.HeightRequest = height;
		//        }
		//    }
		//    else
		//    {
		//        scroll_thumb.Orientation = ScrollOrientation.Vertical;
		//        thumb_container.Orientation = StackOrientation.Vertical;
		//        foreach (StackLayout view in thumb_container.Children)
		//        {
		//            view.Children[0].WidthRequest = width;
		//            view.Children[1].WidthRequest = width;

		//            view.Children[1].WidthRequest = -1;
		//            view.Children[0].HeightRequest = -1;
		//            view.HeightRequest = -1;
		//        }
		//    }
		//}

		public void Dispose()
		{
			ImageService.Instance.InvalidateCacheAsync(FFImageLoading.Cache.CacheType.Memory);
			lv_schemes.ItemTapped -= Lv_schemes_ItemTapped;
			_dataInformation.Clear(); _dataInformation = null;
			lv_schemes.ItemsSource = null;
			GC.Collect();
		}
	}
}
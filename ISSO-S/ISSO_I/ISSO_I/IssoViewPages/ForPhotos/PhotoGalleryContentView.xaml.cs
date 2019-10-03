using Mono.Data.Sqlite;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using CommonClassesLibrary;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using Stormlion.PhotoBrowser;
using ISSO_I.IssoViewPages.ForPhotos;
using Rg.Plugins.Popup.Extensions;
using ISSO_I.Additional_Classes;
using FFImageLoading;
using FFImageLoading.Forms;
using CommonClassesLibrary.Interfaces;

// ReSharper disable once CheckNamespace
namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class PhotoGalleryContentView : IDisposable
	{
        private List<DataInformation> _dataInformation = new List<DataInformation>();
        public Command TapPhotoCommand { get; set; }

        private PhotoBrowser PhotoBrowser { get; set; }

        private int CIsso { get; set; }

        public PhotoGalleryContentView()
		{
			InitializeComponent();
            lv_photos.ItemTapped += Lv_photos_ItemTapped;
            //TapPhotoCommand = new Command<DataInformation>((DataInformation data) => ShowPictures(Data_Information.IndexOf(data)));
		}

        private void Lv_photos_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            ((ListView)sender).SelectedItem = null;
        }

        public void Dispose()
        {
            var pathToDir = Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/";
            // Чистим предыдущие фотки
            if (Directory.Exists(pathToDir))
            {
                DependencyService.Get<ILocalFileProvider>().DeleteFilesFromDir(pathToDir);
            }
            ImageService.Instance.InvalidateCacheAsync(FFImageLoading.Cache.CacheType.Memory);
            PhotoBrowser.Close();
            lv_photos.ItemTapped -= Lv_photos_ItemTapped;
            _dataInformation.Clear(); _dataInformation = null;
            lv_photos.ItemsSource = null;
            GC.Collect();
        }

        public void Initialize(int cIsso)
        {
            CIsso = cIsso;
            AddPhotosAsync(cIsso);
        }

        private void AddPhotosAsync(int cIsso)
        {
            _dataInformation.Clear();
            var page = new LoadingPopupPage("Подождите, идет загрузка фотографий...", true);
            Navigation.PushPopupAsync(page, false);
            Task.Factory.StartNew(() =>
            {
                // Вытаскиваем информацию
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    _dataInformation = new List<DataInformation>();
                    var command = connection.CreateCommand();
                    command.CommandText = $"select * from I_FOTO where C_ISSO={cIsso} and FOTO is not null order by N";
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
		                                Convert.FromBase64String(datareader.GetString(datareader.GetOrdinal("FOTO"))),
		                                CommonStaffUtils.StandardImageWidth, CommonStaffUtils.StandardImageHeight);
                                var date = datareader["FOTO_DATE"] != DBNull.Value ? datareader.GetInt64(datareader.GetOrdinal("FOTO_DATE")) : 0;
                                var info = datareader["TITR"] != DBNull.Value ? datareader.GetString(datareader.GetOrdinal("TITR")) : "";
                                var n = datareader["N"] != DBNull.Value ? datareader.GetInt32(datareader.GetOrdinal("N")) : 0;

                                var newPath =
	                                $"{Environment.GetFolderPath(Environment.SpecialFolder.Personal)}/ISSO-I/{CommonStaffUtils.RandomNumber(1, 9999999)}";
                                if (!Directory.Exists($"{Environment.GetFolderPath(Environment.SpecialFolder.Personal)}/ISSO-I"))
                                    Directory.CreateDirectory($"{Environment.GetFolderPath(Environment.SpecialFolder.Personal)}/ISSO-I");

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
					connection.Close();
                }

            Device.BeginInvokeOnMainThread(() =>
            {
	            lv_photos.ItemsSource = _dataInformation;
	            if (_dataInformation.Count <= 0)
	            {
		            Content = new Label
		            {
			            VerticalOptions = LayoutOptions.Center,
			            HorizontalOptions = LayoutOptions.Center,
			            Text = "Фотографии данного сооружения отсутствуют."
		            };
	            }
	            Navigation.PopPopupAsync();
            });

                //// Добавляем контролы в StackLayout
                //foreach (DataInformation information in Data_Information)
                //{
                //    // Создаем ContentView
                //    StackLayout contentViewPhoto = new StackLayout
                //    {
                //        VerticalOptions = LayoutOptions.Fill,
                //        HorizontalOptions = LayoutOptions.Fill,
                //        Orientation = StackOrientation.Vertical
                //    };
                //    Image image = new Image
                //    {
                //        Margin = new Thickness(10, 10, 10, 0),
                //        VerticalOptions = LayoutOptions.FillAndExpand,
                //        HorizontalOptions = LayoutOptions.FillAndExpand,
                //        Source = ImageSource.FromStream(() => new MemoryStream(information.Array)),
                //        ClassId = String.Format("{0}", Data_Information.IndexOf(information))
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
                //        ShowPictures(Convert.ToInt32((sender as View).ClassId));
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
                //    Device.BeginInvokeOnMainThread(() => { image_container.Children.Add(contentViewPhoto); });
                //}

                // Добавляем данные
                //Device.BeginInvokeOnMainThread(() =>
                //{
                //    int index = 0;
                //    foreach(StackLayout layout in image_container.Children)
                //    {
                //        var date_str = Data_Information[index].Date != 0 ? new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(Data_Information[index].Date).ToString("dd.MM.yyyy") : "";

                //            (layout.Children[0] as Image).Source = ImageSource.FromStream(() => new MemoryStream(Data_Information[index].Array));
                //            (layout.Children[1] as Label).Text = !date_str.Equals("") ? String.Format("{0}\n{1}", date_str, Data_Information[index].Info) : Data_Information[index].Info;
                //        index++;
                //    }
                //});

                //Device.BeginInvokeOnMainThread(() => { OnSizeAllocated(App.Current.MainPage.Width, scroll_view_photo.Height); Navigation.PopPopupAsync(); });
            });
        }

        private void ShowPictures(int index)
        {
            var photos = new List<Photo>();
            foreach(var information in _dataInformation)
            {
                photos.Add(new Photo
                {
                    URL = $"file://{information.ImageSource}",
                    Title = information.Info
                });
            }
            PhotoBrowser.Close();
            PhotoBrowser = new PhotoBrowser
            {
                Photos = photos,
                //EnableGrid = false,
                StartIndex = index,
            };
            PhotoBrowser.Show();
            PhotoBrowser.EnableGrid = true;
        }

        private void PhotoTapped(object sender, EventArgs e)
        {
            var image = sender as CachedImage;
            var photoInfo = image?.BindingContext as DataInformation;
            var index = _dataInformation.IndexOf(photoInfo);
            ShowPictures(index);
        }

        private async void AddPhotoButton_Clicked(object sender, EventArgs e)
        {
            var file = await CommonPhotoUtils.TakePhoto();
	        if (file == null) return;
	        var addNewPhotoContentPage = new AddNewPhotoContentPage(CIsso, file, _dataInformation.Max(x => x.N));
	        addNewPhotoContentPage.PhotoAdded += AddNewPhotoContentPage_PhotoAdded;
	        await Navigation.PushModalAsync(addNewPhotoContentPage);
        }

        private void AddNewPhotoContentPage_PhotoAdded(object sender, EventArgs e)
        {
            AddPhotosAsync(CIsso);
        }

        //protected override void OnSizeAllocated(double width, double height)
        //{
        //    base.OnSizeAllocated(width, height);
        //    if (width > height)
        //    {
        //        scroll_view_photo.Orientation = ScrollOrientation.Horizontal;
        //        image_container.Orientation = StackOrientation.Horizontal;
        //        foreach(StackLayout view in image_container.Children)
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
        //        scroll_view_photo.Orientation = ScrollOrientation.Vertical;
        //        image_container.Orientation = StackOrientation.Vertical;
        //        foreach (StackLayout view in image_container.Children)
        //        {
        //            view.Children[0].WidthRequest = width;
        //            view.Children[1].WidthRequest = width;

        //            view.Children[1].WidthRequest = -1;
        //            view.Children[0].HeightRequest = -1;
        //            view.HeightRequest = -1;
        //        }
        //    }
        //}
    }
}

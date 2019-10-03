using CommonClassesLibrary;
using System;
using System.Collections.Generic;
using System.Linq;
using Xamarin.Forms;
using Plugin.Permissions.Abstractions;
using Xamarin.Forms.GoogleMaps;
using Xamarin.Forms.Xaml;
using Plugin.Geolocator;
using Plugin.Geolocator.Abstractions;
using ISSO_I.Additional_Classes;
using Mono.Data.Sqlite;
using CommonClassesLibrary.Interfaces;
using CommonClassesLibrary.PopupPages;
using Rg.Plugins.Popup.Extensions;
using ISSO_I.Drivers;

namespace ISSO_I.IssoViewPages.ForTreeView
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class EditCoordsContentPage
	{
		/// <summary>
		/// Экземпляр карты
		/// </summary>
		private Map MyMap { get; set; }

		/// <summary>
		/// Начало ИССО, геокоординаты
		/// </summary>
		private GeoCoords StartGeo { get; set; }

		/// <summary>
		/// Конец ИССО, геокоординаты
		/// </summary>
		private GeoCoords EndGeo { get; set; }

		/// <summary>
		/// Текущее местоположение
		/// </summary>
		private Plugin.Geolocator.Abstractions.Position MyPosition { get; set; }


		private Ais7DataColumnDriver_V_ISSO_GEO DriverVIssoGeo { get; } = new Ais7DataColumnDriver_V_ISSO_GEO();

		/// <summary>
		/// Точность геопозиционирования
		/// </summary>
		private const int Accuracy = 10;

		private int CIsso { get; }

		public event EventHandler SaveChanges;

		/// <summary>
		/// Признак того, что координаты были поменяны
		/// </summary>
		private bool Edited { get; set; }

		private string[] TypesMap { get; } = { "Улица", "Спутник", "Гибрид" };
		/// <summary>
		/// Иконки для отображения карты (темные)
		/// </summary>
		private FileImageSource[] TypesMapImgDark { get; } =
		{
			new FileImageSource() { File = CommonStaffUtils.GetFilePath("normal_map_light.png") },
			new FileImageSource() { File = CommonStaffUtils.GetFilePath("satellite_light.png") },
			new FileImageSource() { File = CommonStaffUtils.GetFilePath("terrain_light.png") }
		};
		/// <summary>
		/// Иконки для отображения карты (светлые)
		/// </summary>
		private FileImageSource[] TypesMapImgLight { get; } =
		{
			new FileImageSource() { File = CommonStaffUtils.GetFilePath("normal_map_dark.png") },
			new FileImageSource() { File = CommonStaffUtils.GetFilePath("satellite_dark.png") },
			new FileImageSource() { File = CommonStaffUtils.GetFilePath("terrain_dark.png") }
		};

		public EditCoordsContentPage(int cIsso)
		{
			InitializeComponent();
			CIsso = cIsso;
			Title = "АИС ИССО-IX. Изменение геокоординат.";
			MyMap = new Map
			{
				VerticalOptions = LayoutOptions.FillAndExpand,
				HorizontalOptions = LayoutOptions.FillAndExpand
			};
			ForMap.Children.Add(MyMap);

			using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
			{
				using (var command = connection.CreateCommand())
				{
					command.CommandText = $"select * from V_ISSO where C_ISSO={cIsso}";
					command.CommandTimeout = 30;
					command.CommandType = System.Data.CommandType.Text;
					connection.Open();
					using (var datareader = command.ExecuteReader())
					{
						if (datareader.HasRows)
						{
							while (datareader.Read())
							{
								StartGeo = new GeoCoords(Convert.ToDouble(datareader["P1_LATITUDE"]), Convert.ToDouble(datareader["P1_LONGITUDE"]));
								EndGeo = new GeoCoords(Convert.ToDouble(datareader["P2_LATITUDE"]), Convert.ToDouble(datareader["P2_LONGITUDE"]));
							}
						}
						datareader.Close();
					}
				}
				connection.Close();
			}

			CurrentISSOLength.Text =
				$"{(GeoCodeCalc.CalcDistance(StartGeo.Latitude, StartGeo.Longitude, EndGeo.Latitude, EndGeo.Longitude) * 1000):F1} м.";
			if (StartGeo != null && EndGeo != null)
			{
				CenterMap(new List<GeoCoords> { StartGeo, EndGeo });
				SetupPins(StartGeo, EndGeo);
			}
			else if (MyPosition != null)
			{
				CenterMap(new List<GeoCoords> { new GeoCoords(MyPosition.Latitude, MyPosition.Longitude) });
			}
			// Инициализация кнопки центрирования карты
			Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("define_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
			Btn_Follow.Clicked += (s, e) =>
			{
				var listIssos = new List<GeoCoords>();
				if (StartGeo != null && EndGeo != null)
				{
					listIssos.Add(StartGeo);
					listIssos.Add(EndGeo);
				}
				else if (MyPosition != null)
					listIssos.Add(new GeoCoords(MyPosition.Latitude, MyPosition.Longitude));
				CenterMap(listIssos);
			};

			// Инициализация кнопки выбора спутников
			Btn_Satellite.Image = TypesMapImgLight[0];

			EditIssoLocations();

			EditStartPointButton.Image = new FileImageSource { File = CommonStaffUtils.GetFilePath("no_location_light.png") };
			EditEndPointButton.Image = new FileImageSource { File = CommonStaffUtils.GetFilePath("no_location_light.png") };

			EditStartPointButton.Clicked += (s, e) => { EditPointButton_Clicked(s, e, true); };
			EditEndPointButton.Clicked += (s, e) => { EditPointButton_Clicked(s, e, false); };
		}


		private async void EditPointButton_Clicked(object sender, EventArgs eventArgs, bool isStart)
		{
			if (MyPosition.Accuracy < Accuracy)
			{
				// Создаем наше модальное окно
				var alertPage = new AlertPopupPage(false,
					$"Использовать текущее местоположение как новые координаты {(isStart ? "начала ИССО" : "конца ИССО")}?",
					confirmText: "Да");
				// Описываем метод при нажатии на Ок
				alertPage.Confirm += (s, e) =>
				{
					// Записываем новые координаты
					if (isStart)
					{
						StartGeo.Latitude = MyPosition.Latitude;
						StartGeo.Longitude = MyPosition.Longitude;
					}
					else
					{
						EndGeo.Latitude = MyPosition.Latitude;
						EndGeo.Longitude = MyPosition.Longitude;
					}
					SetupPins(StartGeo, EndGeo);
					if (StartGeo != null && EndGeo != null)
					{
						CenterMap(new List<GeoCoords> { StartGeo, EndGeo });
					}
					else if (MyPosition != null)
					{
						CenterMap(new List<GeoCoords> { new GeoCoords(MyPosition.Latitude, MyPosition.Longitude) });
					}
					if (StartGeo != null && EndGeo != null)
						CurrentISSOLength.Text =
							$"{(GeoCodeCalc.CalcDistance(StartGeo.Latitude, StartGeo.Longitude, EndGeo.Latitude, EndGeo.Longitude) * 1000):F1} м.";
					EditIssoLocations();
					Edited = true;
				};
				await Navigation.PushPopupAsync(new CommonPopupPage(alertPage, "Изменение координат"));
			}
			else
			{
				DependencyService.Get<IMessage>().LongAlert("Нельзя установить новые координаты, т.к. погрешность > 10 м.");
			}
		}

		private void CenterMap(IReadOnlyCollection<GeoCoords> coords)
		{
			var lowestLat = coords.Select(l => l.Latitude).Min();
			var highestLat = coords.Select(l => l.Latitude).Max();
			var lowestLng = coords.Select(l => l.Longitude).Min();
			var highestLng = coords.Select(l => l.Longitude).Max();
			var finalLat = (lowestLat + highestLat) / 2;
			var finalLng = (lowestLng + highestLng) / 2;
			var distance = GeoCodeCalc.CalcDistance(lowestLat, lowestLng, highestLat, highestLng, GeoCodeCalcMeasurement.Kilometers);

			MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(new Xamarin.Forms.GoogleMaps.Position(finalLat, finalLng), Distance.FromKilometers(distance * 2 / 3)));
		}

		private void SetupPins(GeoCoords start, GeoCoords end)
		{
			MyMap.Pins.Clear();

			if (start != null)
			{
				var startPin = new Pin
				{
					Position = new Xamarin.Forms.GoogleMaps.Position(start.Latitude, start.Longitude),
					Anchor = new Point(0.5, 1.0),
					Label = "Начало ИССО",
					Icon = Device.RuntimePlatform.Equals(Device.Android)
						? BitmapDescriptorFactory.FromBundle("marker_ahead.png")
						: BitmapDescriptorFactory.FromView(new ContentView
						{
							WidthRequest = 40,
							HeightRequest = 40,
							Scale = 1,
							Content = new Image
							{
								Source = new FileImageSource()
								{
									File =
										CommonStaffUtils.GetFilePath(
											"marker_ahead.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "Assets/", "Assets/Icons/"), "marker_far.png")*/
								},
								Aspect = Aspect.AspectFit,
								WidthRequest = 40,
								HeightRequest = 40
							}
						})
				};
				MyMap.Pins.Add(startPin);
			}

			if (end != null)
			{
				var endPin = new Pin
				{
					Position = new Xamarin.Forms.GoogleMaps.Position(end.Latitude, end.Longitude),
					Anchor = new Point(0.5, 1.0),
					Label = "Конец ИССО",
					Icon = Device.RuntimePlatform.Equals(Device.Android)
						? BitmapDescriptorFactory.FromBundle("marker_behind.png")
						: BitmapDescriptorFactory.FromView(new ContentView
						{
							WidthRequest = 40,
							HeightRequest = 40,
							Scale = 1,
							Content = new Image
							{
								Source = new FileImageSource()
								{
									File =
										CommonStaffUtils.GetFilePath(
											"marker_behind.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "Assets/", "Assets/Icons/"), "marker_far.png")*/
								},
								Aspect = Aspect.AspectFit,
								WidthRequest = 40,
								HeightRequest = 40
							}
						})
				};
				MyMap.Pins.Add(endPin);
			}


			GeoStartPointImage.Source = new FileImageSource { File = CommonStaffUtils.GetFilePath("marker_ahead.png") };
			GeoEndPointImage.Source = new FileImageSource { File = CommonStaffUtils.GetFilePath("marker_behind.png") };

			GeoStartPointImage.GestureRecognizers.Add(new TapGestureRecognizer { Command = new Command(() => { MoveCamera(StartGeo); }) });
			GeoEndPointImage.GestureRecognizers.Add(new TapGestureRecognizer { Command = new Command(() => { MoveCamera(EndGeo); }) });
		}

		/// <summary>
		/// Прописываем геокоординаты
		/// </summary>
		private void EditIssoLocations()
		{
			StartLatitude.Text = DriverVIssoGeo.Convert(StartGeo.Latitude).ToString();
			StartLongitude.Text = DriverVIssoGeo.Convert(StartGeo.Longitude).ToString();
			EndLatitude.Text = DriverVIssoGeo.Convert(EndGeo.Latitude).ToString();
			EndLongitude.Text = DriverVIssoGeo.Convert(EndGeo.Longitude).ToString();
		}

		private void MoveCamera(GeoCoords point)
		{
			MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(new Xamarin.Forms.GoogleMaps.Position(point.Latitude, point.Longitude), Distance.FromMeters(15)));
		}

		protected override void OnAppearing()
		{
			base.OnAppearing();
			RequestLocationUpdates();
		}

		public async void RequestLocationUpdates()
		{
			var hasPermission = await CommonStaffUtils.CheckPermissions(Permission.Location);
			if (!hasPermission)
				return;
			if (CrossGeolocator.Current.IsListening) return;
			MyMap.MyLocationEnabled = true;
			CrossGeolocator.Current.PositionChanged += Current_PositionChanged;
			CrossGeolocator.Current.PositionError += Current_PositionError;
			await CrossGeolocator.Current.StartListeningAsync(TimeSpan.FromSeconds(1), 3);
		}

		private void Current_PositionChanged(object sender, PositionEventArgs e)
		{
			MyPosition = e.Position;
			CurrentErrorLbl.Text = $"{e.Position.Accuracy:F1} м.";
			if (e.Position.Accuracy < Accuracy)
			{
				EditStartPointButton.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("define_location_light.png") };
				EditEndPointButton.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("define_location_light.png") };
			}
			else
			{
				EditStartPointButton.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("no_location_light.png") };
				EditEndPointButton.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("no_location_light.png") };
			}
		}

		protected override void OnDisappearing()
		{
			base.OnDisappearing();
			if (CrossGeolocator.Current.IsListening)
			{
				CrossGeolocator.Current.PositionChanged -= Current_PositionChanged;
				CrossGeolocator.Current.PositionError -= Current_PositionError;
				CrossGeolocator.Current.StopListeningAsync();
			}
		}

		private void Current_PositionError(object sender, PositionErrorEventArgs e)
		{
			Navigation.PushPopupAsync(new CommonPopupPage(
				new AlertPopupPage(true, "По непонятным причинам GPS не работает на вашем устройстве"), "Ошибка модуля GPS"));
			//DisplayAlert("Ошибка", "По непонятным причинам GPS не работает на вашем устройстве", "Ну ок :(");
		}

		public override void Dispose()
		{
			MyMap.Pins.Clear();
			MyMap = null;
			MyPosition = null;
			StartGeo = null;
			EndGeo = null;
			GC.Collect();
			if (CrossGeolocator.Current.IsListening)
			{
				CrossGeolocator.Current.PositionChanged -= Current_PositionChanged;
				CrossGeolocator.Current.PositionError -= Current_PositionError;
				CrossGeolocator.Current.StopListeningAsync();
			}
		}

		private async void CancelBtn_Clicked(object sender, EventArgs eventArgs)
		{
			var alertPage = new AlertPopupPage(false, "Вы хотите выйти без сохранения?", confirmText: "Да");
			alertPage.Confirm += async (s, e) => { await Navigation.PopModalAsync(); };
			await Navigation.PushPopupAsync(new CommonPopupPage(alertPage, "Предупреждение: Координаты не сохранены"));
			//var result = await DisplayAlert("Внимание!", "Вы хотите выйти без сохранения?", "Да", "Отмена");
		}

		private async void ConfirmBtn_Clicked(object sender, EventArgs e)
		{
			if (Edited)
			{
				var alertPage = new AlertPopupPage(false, "Сохранить новые координаты?", confirmText: "Да");
				alertPage.Confirm += async (s, events) =>
				{
					using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
					{
						connection.Open();
						using (var command = connection.CreateCommand())
						{
							command.CommandText =
								$"update V_ISSO set P1_LATITUDE={StartGeo.Latitude}, P1_LONGITUDE={StartGeo.Longitude}, P2_LATITUDE={EndGeo.Latitude}," +
								$" P2_LONGITUDE={EndGeo.Longitude} where C_ISSO={CIsso}";
							command.CommandTimeout = 30;
							command.CommandType = System.Data.CommandType.Text;
							command.ExecuteNonQuery();
						}
						connection.Close();
					}
					await Navigation.PopModalAsync();

					// ReSharper disable once PossibleNullReferenceException
					SaveChanges(new[] { StartGeo.Latitude, StartGeo.Longitude, EndGeo.Latitude, EndGeo.Longitude }, EventArgs.Empty);
				};
				await Navigation.PushPopupAsync(new CommonPopupPage(alertPage, "Сохранение новых коорлинат"));
			}
			else
			{
				var alertPage = new AlertPopupPage(true, "Координаты не были изменены. Измените хотя бы одну координату для сохранения.", "ОК");
				alertPage.Confirm += async (o, args) => { await Navigation.PopPopupAsync(); };
				await Navigation.PushPopupAsync(new CommonPopupPage(alertPage, "Сохранение новых координат"));
			}
		}

		private void Btn_Satellite_Clicked(object sender, EventArgs e)
		{
			var page = new CustomDialogPage(TypesMap.Select((t, i) => new ItemType(t, TypesMapImgDark[i])).ToArray());
			page.Clicked += delegate (object sender1, EventArgs e1) { Map_Clicked(sender1, e1, page.IndexSelected); };
			Navigation.PushPopupAsync(new CommonPopupPage(page, CustomDialogPage.Header));
		}

		/// <summary>
		/// Метод, устанавливающий выбранный тип карты
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
		/// <param name="index"></param>
		public void Map_Clicked(object sender, EventArgs e, int index)
		{
			Navigation.PopPopupAsync();
			switch (index)
			{
				case 0:
					MyMap.MapType = MapType.Street;
					Btn_Satellite.Image = TypesMapImgLight[0];
					break;
				case 1:
					MyMap.MapType = MapType.Satellite;
					Btn_Satellite.Image = TypesMapImgLight[1];
					break;
				case 2:
					MyMap.MapType = MapType.Hybrid;
					Btn_Satellite.Image = TypesMapImgLight[2];
					break;
			}
		}
	}
}
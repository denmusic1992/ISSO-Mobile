using CommonClassesLibrary;
using Plugin.Geolocator;
using Plugin.Geolocator.Abstractions;
using System;
using System.Collections.Generic;
using CommonClassesLibrary.PopupPages;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.GoogleMaps;
using Xamarin.Forms.Xaml;

namespace ISSO_I.PopupTypes
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class EditGeoPage
    {
	    public event EventHandler ApplyChanges;
        /// <summary>
        /// Текущая геопозиция
        /// </summary>
        private Plugin.Geolocator.Abstractions.Position _location;
        /// <summary>
        /// Текущая позиция x
        /// </summary>
        private double _currentLatitude;
        /// <summary>
        /// Текущая позиция y
        /// </summary>
        private double _currentLongitude;

	    /// <summary>
        /// Допустимая погрешность
        /// </summary>
        private readonly double _accuracy = 5.0;


        public EditGeoPage(IReadOnlyList<GeoCoords> geoCoords)
        {
	        InitializeComponent();

            var myMap = new Map()
            {
                MyLocationEnabled = true,
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.FillAndExpand,
            };
            for_map.Children.Add(myMap);

            var issoLatitude = (geoCoords[0].Latitude + geoCoords[1].Latitude) / 2;
            var issoLongitude = (geoCoords[0].Longitude + geoCoords[1].Longitude) / 2;

            myMap.MoveToRegion(MapSpan.FromCenterAndRadius(new Xamarin.Forms.GoogleMaps.Position(issoLatitude, issoLongitude), Distance.FromKilometers(0.5)), false);
            status.Text = "Расстояние до объекта, м:\nПогрешность GPS, м:";
        }

        /// <summary>
        /// Проверка наличия GPS
        /// </summary>
        /// <returns>Возвращает есть/нет GPS</returns>
        public bool IsLocationAvailable()
        {
            if (!CrossGeolocator.IsSupported)
                return false;

            return CrossGeolocator.Current.IsGeolocationAvailable;
        }


        /// <summary>
        /// Метод получения текущих координат
        /// </summary>
        public async void RequestLocationUpdates()
        {
            if (CrossGeolocator.Current.IsListening) return;
            CrossGeolocator.Current.PositionChanged += Current_PositionChanged;
            CrossGeolocator.Current.PositionError += Current_PositionError;
            await CrossGeolocator.Current.StartListeningAsync(TimeSpan.FromSeconds(1), 5);
        }

        private async void Current_PositionError(object sender, PositionErrorEventArgs e)
        {
	        await Navigation.PushPopupAsync(new CommonPopupPage(
		        new AlertPopupPage(true, "По непонятным причинам GPS не работает на вашем устройстве."),
		        "Ошибка работы модуля GPS"));
            //DisplayAlert("Ошибка", "По непонятным причинам GPS не работает на вашем устройстве", "Ну ок :(");
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();
	        if (!CrossGeolocator.Current.IsListening) return;
	        CrossGeolocator.Current.PositionChanged -= Current_PositionChanged;
	        CrossGeolocator.Current.PositionError -= Current_PositionError;
	        CrossGeolocator.Current.StopListeningAsync();
        }


        private void Current_PositionChanged(object sender, PositionEventArgs e)
        {
            _location = e.Position;
            _currentLatitude = e.Position.Latitude;
            _currentLongitude = e.Position.Longitude;
            status.Text = $"Погрешность GPS, м: {e.Position.Accuracy}";
            imgStatus.Source = e.Position.Accuracy < _accuracy
	            ? new FileImageSource() {File = CommonStaffUtils.GetFilePath("marker_noway.png")}
	            : new FileImageSource() {File = CommonStaffUtils.GetFilePath("marker_ahead.png")};
        }

	    protected virtual void OnApplyChanges()
	    {
		    ApplyChanges?.Invoke(this, EventArgs.Empty);
	    }
    }
}
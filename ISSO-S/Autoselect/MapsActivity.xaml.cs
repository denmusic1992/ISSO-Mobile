using Plugin.Geolocator;
using Plugin.Geolocator.Abstractions;
using Plugin.Permissions.Abstractions;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using CommonClassesLibrary;
using CommonClassesLibrary.PopupPages;
using Xamarin.Forms;
using Xamarin.Forms.GoogleMaps;
using Xamarin.Forms.Xaml;

namespace Autoselect
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public abstract partial class MapsActivity
    {
        /// <summary>
        /// Предыдущая геопозиция
        /// </summary>
        private Plugin.Geolocator.Abstractions.Position _prevLocation;
        /// <summary>
        /// Текущая геопозиция
        /// </summary>
        private Plugin.Geolocator.Abstractions.Position _location;
        /// <summary>
        /// Список ИССО
        /// </summary>
        public HttpsIsso[] ListOfIssos;
        /// <summary>
        /// Расстояние м/у ИССО и текущей геопозицией
        /// </summary>
        private Dictionary<int, Vector2D> _dists = new Dictionary<int, Vector2D>();
        /// <summary>
        /// Расстояние м/у ИССО и текущей геопозицией(NEW)
        /// </summary>
        private Dictionary<int, Vector2D> _distsNew = new Dictionary<int, Vector2D>();
        /// <summary>
        /// Набор данных по ближайщим ИССО
        /// </summary>
        public ArrayVector HashMap;
        /// <summary>
        /// Радиус Земли (м)
        /// </summary>
        public static double Rad = 6371200;
        /// <summary>
        /// Следование за пользователем
        /// </summary>
        private bool _isFollowed;
        /// <summary>
        /// Для первоначального отображения геопозиции пользователя
        /// </summary>
        private bool _once = true;
        /// <summary>
        /// Когда координаты найдены
        /// </summary>
        private bool _coordsFounded;
        /// <summary>
        /// Идентификатор перехода к подробной информации по ИССО
        /// </summary>
        public bool ToIssoView;
        /// <summary>
        /// Экземпляр карты
        /// </summary>
        public Map MyMap;
        /// <summary>
        /// Тип отображения (по умолчанию список и карта)
        /// </summary>
        public TypeScreen TypeScreen = TypeScreen.MapAndList;
        /// <summary>
        /// Варианты, возможные для отображения
        /// </summary>
        private readonly string[] _typesView = { "Список", "Список и карта", "Карта" };
        /// <summary>
        /// Иконки для этого дела
        /// </summary>
        private FileImageSource[] _typesViewImg = {
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("list_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "list_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("map_list_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "map_list_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("map_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "map_light.png")*/ }
        };
        private string[] _typesMap = { "Улица", "Спутник", "Гибрид" };
        /// <summary>
        /// Иконки для отображения карты (темные)
        /// </summary>
        private FileImageSource[] TypesMapImgDark { get; set; } =
        {
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("normal_map_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "normal_map_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("satellite_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "satellite_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("terrain_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "terrain_light.png")*/ }
        };
        /// <summary>
        /// Иконки для отображения карты (светлые)
        /// </summary>
        private FileImageSource[] TypesMapImgLight { get; set; } =
        {
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("normal_map_dark.png")  /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "normal_map_dark.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("satellite_dark.png")  /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "satellite_dark.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("terrain_dark.png")  /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "terrain_dark.png")*/ }
        };
        /// <summary>
        /// Кнопка бокового меню
        /// </summary>
        private ToolbarItem _toolBarMapType;

	    protected MapsActivity(HttpsIsso[] listOfIssos)
        {
            ListOfIssos = listOfIssos;
            InitializeComponent();

            _toolBarMapType = new ToolbarItem
            {
                Command = new Command((sender) => ShowMapType()),
                Priority = 0,
                Order = ToolbarItemOrder.Primary
            };
            ToolbarItems.Add(_toolBarMapType);
            MyMap = new Map()
            {
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.FillAndExpand,
            };
            ForMap.Children.Add(MyMap);
            Btn_Follow.Image = new FileImageSource() {  File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
            //GridView.HeightRequest = Content.Height / 2;
            //MapGrid.HeightRequest = Content.Height / 2;
            switch (TypeScreen)
            {
                case TypeScreen.OnlyList:
                    GridView.HeightRequest = Content.Height;
                    MapGrid.HeightRequest = 0;
                    // Если координаты не получены
                    //FindCoords.HeightRequest = Content.Height;
                    //Founded.HeightRequest = 0;
                    _toolBarMapType.Icon = Device.RuntimePlatform.Equals(Device.Android) ? new FileImageSource() { File = "list_dark.png" } : _typesViewImg[0];
                    break;
                case TypeScreen.MapAndList:
                    GridView.HeightRequest = Content.Height / 3;
                    MapGrid.HeightRequest = Content.Height * 2 / 3;
                    // Если координаты не получены
                    //FindCoords.HeightRequest = Content.Height / 3;
                    //Founded.HeightRequest = 0;
                    _toolBarMapType.Icon = Device.RuntimePlatform.Equals(Device.Android) ? new FileImageSource() { File = "map_list_dark.png" } : _typesViewImg[1];
                    break;
                case TypeScreen.OnlyMap:
                    GridView.HeightRequest = 0;
                    MapGrid.HeightRequest = Content.Height;
                    _toolBarMapType.Icon = Device.RuntimePlatform.Equals(Device.Android) ? new FileImageSource() { File = "map_dark.png" } : _typesViewImg[2];
                    break;
            }
            Founded.IsVisible = false;
            FindCoords.IsVisible = true;
            DescrNear.Text = "Получение геопозиции для определения ближайших ИССО...";

            MyMap.PinClicked += MyMap_PinClicked;
            MyMap.SelectedPinChanged += MyMap_SelectedPinChanged;
            MyMap.InfoWindowClicked += MyMap_InfoWindowClicked;

            Btn_Satellite.Image = TypesMapImgLight[0];
        }

        private void MyMap_InfoWindowClicked(object sender, InfoWindowClickedEventArgs e)
        {
            if (HashMap.GetIndex(1) != -1 && Math.Abs(e.Pin.Position.Latitude - ListOfIssos[HashMap.GetIndex(1)].Latitude) < CommonStaffUtils.DoubleTolerance &&
                Math.Abs(e.Pin.Position.Longitude - ListOfIssos[HashMap.GetIndex(1)].Longitude) < CommonStaffUtils.DoubleTolerance)
            {
                FarTapped(null, null);
            }
            else if (HashMap.GetIndex(0) != -1 && Math.Abs(e.Pin.Position.Latitude - ListOfIssos[HashMap.GetIndex(0)].Latitude) < CommonStaffUtils.DoubleTolerance &&
                Math.Abs(e.Pin.Position.Longitude - ListOfIssos[HashMap.GetIndex(0)].Longitude) < CommonStaffUtils.DoubleTolerance)
            {
                NearTapped(null, null);
            }
            else if (HashMap.GetIndex(2) != -1)
            {
                BehindTapped(null, null);
            }
        }

        private void MyMap_SelectedPinChanged(object sender, SelectedPinChangedEventArgs e)
        {
            if(MyMap.SelectedPin == null)
            {
                DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
                DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
                DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
            }
        }

        private void MyMap_PinClicked(object sender, PinClickedEventArgs e)
        {
            _isFollowed = false;
            Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
            MyMap.SelectedPin = e.Pin;
            MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(e.Pin.Position, Distance.FromMeters(500)));
            foreach (var _ in MyMap.Pins)
            {
                if(HashMap.GetIndex(1) != -1 && Math.Abs(e.Pin.Position.Latitude - ListOfIssos[HashMap.GetIndex(1)].Latitude) < CommonStaffUtils.DoubleTolerance &&
                    Math.Abs(e.Pin.Position.Longitude - ListOfIssos[HashMap.GetIndex(1)].Longitude) < CommonStaffUtils.DoubleTolerance)
                {
                    //RedBox.Opacity = 0.5;
                    //GreenBox.Opacity = 0.2;
                    //BlueBox.Opacity = 0.2;
                    DescrFar.FontAttributes = FontAttributes.Bold; DistFar.FontAttributes = FontAttributes.Bold;
                    DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
                    DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
                }
                else if (HashMap.GetIndex(0) != -1 && Math.Abs(e.Pin.Position.Latitude - ListOfIssos[HashMap.GetIndex(0)].Latitude) < CommonStaffUtils.DoubleTolerance &&
                    Math.Abs(e.Pin.Position.Longitude - ListOfIssos[HashMap.GetIndex(0)].Longitude) < CommonStaffUtils.DoubleTolerance)
                {
                    //RedBox.Opacity = 0.2;
                    //GreenBox.Opacity = 0.5;
                    //BlueBox.Opacity = 0.2;
                    DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
                    DescrNear.FontAttributes = FontAttributes.Bold; DistNear.FontAttributes = FontAttributes.Bold;
                    DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
                }
                else if (HashMap.GetIndex(2) != -1)
                {
                    //RedBox.Opacity = 0.2;
                    //GreenBox.Opacity = 0.2;
                    //BlueBox.Opacity = 0.5;
                    DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
                    DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
                    DescrBehind.FontAttributes = FontAttributes.Bold; DistBehind.FontAttributes = FontAttributes.Bold;
                }
            }
        }

        private void ShowMapType()
        {
            //var type = await DisplayActionSheet("Тип отображения:", "Отмена", null, "Список", "Список и карта", "Карта");
            //switch (type)
            //{
            //    case "Список":
            //        typeScreen = TypeScreen.OnlyList;
            //        if (Content.WidthRequest > Content.HeightRequest)
            //        {
            //            GridView.WidthRequest = Content.Width;
            //            MapGrid.WidthRequest = 0;
            //            if(PrevLocation != null && Location != null)
            //            {
            //                // Если координаты не получены
            //                Founded.WidthRequest = Content.Width;
            //                FindCoords.WidthRequest = 0;
            //            }
            //            else
            //            {
            //                FindCoords.WidthRequest = Content.Width;
            //                Founded.HeightRequest = 0;
            //            }
            //        }
            //        else
            //        {
            //            GridView.HeightRequest = Content.Height;
            //            MapGrid.HeightRequest = 0;
            //            if (PrevLocation != null && Location != null)
            //            {
            //                // Если координаты не получены
            //                Founded.HeightRequest = Content.Height;
            //                FindCoords.HeightRequest = 0;
            //            }
            //            else
            //            {
            //                FindCoords.HeightRequest = Content.Height;
            //                Founded.HeightRequest = 0;
            //            }
            //        }
            //        break;
            //    case "Список и карта":
            //        typeScreen = TypeScreen.MapAndList;
            //        if (Content.WidthRequest > Content.HeightRequest)
            //        {
            //            GridView.WidthRequest = Content.Width / 2;
            //            MapGrid.WidthRequest = Content.Width / 2;
            //            if (PrevLocation != null && Location != null)
            //            {
            //                // Если координаты не получены
            //                Founded.WidthRequest = Content.Width / 2;
            //                FindCoords.WidthRequest = 0;
            //            }
            //            else
            //            {
            //                FindCoords.WidthRequest = Content.Width / 2;
            //                Founded.HeightRequest = 0;
            //            }
            //        }
            //        else
            //        {
            //            GridView.HeightRequest = Content.Height * 2 / 5;
            //            MapGrid.HeightRequest = Content.Height * 3 / 5;
            //            if (PrevLocation != null && Location != null)
            //            {
            //                // Если координаты не получены
            //                Founded.HeightRequest = Content.Height * 2 / 5;
            //                FindCoords.HeightRequest = 0;
            //            }
            //            else
            //            {
            //                FindCoords.HeightRequest = Content.Height * 2 / 5;
            //                Founded.HeightRequest = 0;
            //            }
            //        }
            //        break;
            //    case "Карта":
            //        typeScreen = TypeScreen.OnlyMap;
            //        if (Content.WidthRequest > Content.HeightRequest)
            //        {
            //            GridView.WidthRequest = 0;
            //            MapGrid.WidthRequest = Content.Width;
            //        }
            //        else
            //        {
            //            GridView.HeightRequest = 0;
            //            MapGrid.HeightRequest = Content.Height;
            //        }
            //        break;
            //}
            var items = new List<ItemType>();
            for(var i = 0; i < _typesView.Length; i++)
            {
                items.Add(new ItemType(_typesView[i], _typesViewImg[i]));
            }
            var page = new CustomDialogPage(items.ToArray());
            page.Clicked += delegate (object sender, EventArgs e) { Type_Clicked(sender, e, page.IndexSelected); };
            Navigation.PushPopupAsync(new CommonPopupPage(page, CustomDialogPage.Header));
        }

        public void Type_Clicked(object sender, EventArgs e, int index)
        {
            Navigation.PopPopupAsync();
            switch (index)
            {
                case 0:
                    TypeScreen = TypeScreen.OnlyList;
                    if (Content.WidthRequest > Content.HeightRequest)
                    {
                        GridView.WidthRequest = Content.Width;
                        MapGrid.WidthRequest = 0;
                        //if (PrevLocation != null && Location != null)
                        //{
                        //    // Если координаты не получены
                        //    Founded.WidthRequest = Content.Width;
                        //    FindCoords.WidthRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.WidthRequest = Content.Width;
                        //    Founded.HeightRequest = 0;
                        //}
                    }
                    else
                    {
                        GridView.HeightRequest = Content.Height;
                        MapGrid.HeightRequest = 0;
                        //if (PrevLocation != null && Location != null)
                        //{
                        //    // Если координаты не получены
                        //    Founded.HeightRequest = Content.Height;
                        //    FindCoords.HeightRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.HeightRequest = Content.Height;
                        //    Founded.HeightRequest = 0;
                        //}
                    }
                    _toolBarMapType.Icon = Device.RuntimePlatform.Equals(Device.Android) ? new FileImageSource() { File = "list_dark.png" } : _typesViewImg[0];
                    break;
                case 1:
                    TypeScreen = TypeScreen.MapAndList;
                    if (Content.WidthRequest > Content.HeightRequest)
                    {
                        GridView.WidthRequest = Content.Width / 2;
                        MapGrid.WidthRequest = Content.Width / 2;
                        //if (PrevLocation != null && Location != null)
                        //{
                        //    // Если координаты не получены
                        //    Founded.WidthRequest = Content.Width / 2;
                        //    FindCoords.WidthRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.WidthRequest = Content.Width / 2;
                        //    Founded.HeightRequest = 0;
                        //}
                    }
                    else
                    {
                        GridView.HeightRequest = Content.Height * 2 / 5;
                        MapGrid.HeightRequest = Content.Height * 3 / 5;
                        //if (PrevLocation != null && Location != null)
                        //{
                        //    // Если координаты не получены
                        //    Founded.HeightRequest = Content.Height * 2 / 5;
                        //    FindCoords.HeightRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.HeightRequest = Content.Height * 2 / 5;
                        //    Founded.HeightRequest = 0;
                        //}
                    }
                    _toolBarMapType.Icon = Device.RuntimePlatform.Equals(Device.Android) ? new FileImageSource() { File = "map_list_dark.png" } : _typesViewImg[1];
                    break;
                case 2:
                    TypeScreen = TypeScreen.OnlyMap;
                    if (Content.WidthRequest > Content.HeightRequest)
                    {
                        GridView.WidthRequest = 0;
                        MapGrid.WidthRequest = Content.Width;
                    }
                    else
                    {
                        GridView.HeightRequest = 0;
                        MapGrid.HeightRequest = Content.Height;
                    }
                    _toolBarMapType.Icon = Device.RuntimePlatform.Equals(Device.Android) ? new FileImageSource() { File = "map_dark.png" } : _typesViewImg[2];
                    break;
            }
            if (!_coordsFounded)
            {
                Founded.IsVisible = false;
                FindCoords.IsVisible = true;
            }
            else
            {
                Founded.IsVisible = true;
                FindCoords.IsVisible = false;
            }
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);
            WidthRequest = width;
            HeightRequest = height;
            if (width > height)
            {
                MapStack.Orientation = StackOrientation.Horizontal;
                switch(TypeScreen)
                {
                    case TypeScreen.OnlyList:
                        GridView.WidthRequest = Content.Width;
                        MapGrid.WidthRequest = 0;
                        ForMap.Children[0].WidthRequest = 1;
                        //if (!CoordsFounded)
                        //{
                        //    FindCoords.HeightRequest = Content.Width;
                        //    Founded.HeightRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.HeightRequest = 0;
                        //    Founded.HeightRequest = Content.Width;
                        //}
                        break;
                    case TypeScreen.MapAndList:
                        GridView.WidthRequest = Content.Width / 2;
                        MapGrid.WidthRequest = Content.Width / 2;
                        ForMap.Children[0].WidthRequest = Content.Width / 2;
                        //if (!CoordsFounded)
                        //{
                        //    FindCoords.HeightRequest = Content.Width / 2;
                        //    Founded.HeightRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.HeightRequest = 0;
                        //    Founded.HeightRequest = Content.Width / 2;
                        //}
                        break;
                    case TypeScreen.OnlyMap:
                        GridView.WidthRequest = 0;
                        MapGrid.WidthRequest = Content.Width;
                        ForMap.Children[0].WidthRequest = Content.Width;
                        break;
	                default:
		                throw new ArgumentOutOfRangeException();
                }
                if(!_coordsFounded)
                {
                    Founded.IsVisible = false;
                    FindCoords.IsVisible = true;
                }
                else
                {
                    Founded.IsVisible = true;
                    FindCoords.IsVisible = false;
                }
                GridView.HeightRequest = -1;
                MapGrid.HeightRequest = -1;
                ForMap.Children[0].HeightRequest = -1;
            }
            else
            {
                MapStack.Orientation = StackOrientation.Vertical;
                switch (TypeScreen)
                {
                    case TypeScreen.OnlyList:
                        GridView.HeightRequest = Content.Height;
                        MapGrid.HeightRequest = 0;
                        ForMap.Children[0].HeightRequest = 1;
                        //if(!CoordsFounded)
                        //{
                        //    FindCoords.HeightRequest = Content.Height;
                        //    Founded.HeightRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.HeightRequest = 0;
                        //    Founded.HeightRequest = Content.Height;
                        //}
                        break;
                    case TypeScreen.MapAndList:
                        GridView.HeightRequest = Content.Height * 2 / 5;
                        MapGrid.HeightRequest = Content.Height * 3 / 5;
                        ForMap.Children[0].HeightRequest = Content.Height * 3 / 5;
                        //if (!CoordsFounded)
                        //{
                        //    FindCoords.HeightRequest = Content.Height * 2 / 5;
                        //    Founded.HeightRequest = 0;
                        //}
                        //else
                        //{
                        //    FindCoords.HeightRequest = 0;
                        //    Founded.HeightRequest = Content.Height * 2 / 5;
                        //}
                        break;
                    case TypeScreen.OnlyMap:
                        GridView.HeightRequest = 0;
                        MapGrid.HeightRequest = Content.Height;
                        ForMap.Children[0].HeightRequest = Content.Height;
                        break;
                }
                if (!_coordsFounded)
                {
                    Founded.IsVisible = false;
                    FindCoords.IsVisible = true;
                }
                else
                {
                    Founded.IsVisible = true;
                    FindCoords.IsVisible = false;
                }
                GridView.WidthRequest = -1;
                MapGrid.WidthRequest = -1;
                ForMap.Children[0].WidthRequest = -1;
            }
        }

        /// <summary>
        /// Метод получения текущих координат
        /// </summary>
        public async void RequestLocationUpdates()
        {
            var hasPermission = await CommonStaffUtils.CheckPermissions(Permission.Location);
            if (!hasPermission)
                return;
            if (CrossGeolocator.Current.IsListening) return;
            MyMap.MyLocationEnabled = true;
            CrossGeolocator.Current.PositionChanged += Current_PositionChanged;
            CrossGeolocator.Current.PositionError += Current_PositionError;
            await CrossGeolocator.Current.StartListeningAsync(TimeSpan.FromSeconds(1), 5);
        }

        private void Current_PositionError(object sender, PositionErrorEventArgs e)
        {
	        Navigation.PushPopupAsync(new CommonPopupPage(
		        new AlertPopupPage(true, "По непонятным причинам GPS не работает на вашем устройстве."),
		        "Ошибка модуля GPS"));
            //DisplayAlert("Ошибка", "По непонятным причинам GPS не работает на вашем устройстве", "Ну ок :(");
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();
            ToIssoView = false;
            RequestLocationUpdates();
            GC.Collect();
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();
            if(CrossGeolocator.Current.IsListening && !ToIssoView)
            {
                CrossGeolocator.Current.PositionChanged -= Current_PositionChanged;
                CrossGeolocator.Current.PositionError -= Current_PositionError;
                CrossGeolocator.Current.StopListeningAsync();
            }
        }

        private void Current_PositionChanged(object sender, PositionEventArgs e)
        {
            _location = e.Position;
            if(!_isFollowed && _once)
            {
                MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(new Xamarin.Forms.GoogleMaps.Position(_location.Latitude, _location.Longitude), Distance.FromKilometers(2)));
                _once = false;
            }
            if (_prevLocation != null && (Math.Abs(_prevLocation.Latitude - _location.Latitude) > CommonStaffUtils.DoubleTolerance || Math.Abs(_prevLocation.Longitude - _location.Longitude) > CommonStaffUtils.DoubleTolerance))
            {
                if (!_coordsFounded)
                {
                    _coordsFounded = true;
                    OnSizeAllocated(Width, Height);
                }
                if(_location.Accuracy <= 50.0)
                {
                    var issos = RefreshList(_prevLocation, _location);
                    HashMap = GetDistanceToObjects(issos, _location);
                    SetTextForTable();
                    SetupMyGeolocation();
                    if(_isFollowed)
                        FollowByMarkers();
                    _prevLocation = _location;
                }
            }
            else if (_location.Accuracy <= 50.0)
            {
                _prevLocation = _location;
            }
        }

        private int[] RefreshList(Plugin.Geolocator.Abstractions.Position previous, Plugin.Geolocator.Abstractions.Position current)
        {
            if( _dists.Count == 0)
            {
                foreach(var isso in ListOfIssos)
                {
                    var lat = Ext.ToRadians(isso.Latitude);
                    var lng = Ext.ToRadians(isso.Longitude);
                    _dists.Add(isso.CIsso, new Vector2D(lat, lng));

                    //dists.Add(isso.CIsso, new Vector2D(isso.Latitude, isso.Longitude));
                }
                foreach (var isso in ListOfIssos)
                {
                    _distsNew.Add(isso.CIsso, new Vector2D(isso.Latitude, isso.Longitude));
                }
            }

            Vector2D vPos = null;
            Vector2D vPrev = null;

            if (current != null && current.Accuracy <= 50.0 && previous != null && previous.Accuracy <= 50.0)
            {
                var lat = Ext.ToRadians(current.Latitude);
                var lng = Ext.ToRadians(current.Longitude);
                vPos = new Vector2D(lat, lng);
                lat = Ext.ToRadians(previous.Latitude);
                lng = Ext.ToRadians(previous.Longitude);
                vPrev = new Vector2D(lat, lng);
            }

            int cIsso1 = -1, cIsso2 = -1, cIsso3 = -1;
            double d1 = double.MaxValue, d2 = double.MaxValue, d3 = double.MaxValue;
	        if (vPos == null) return new[] {cIsso1, cIsso2, cIsso3};
	        foreach (var listOfIsso in ListOfIssos)
	        {
		        var d = _dists[listOfIsso.CIsso];
		        var ind = vPos.Minus(vPrev).DotProduct(d.Minus(vPrev));

		        d = _distsNew[listOfIsso.CIsso];

		        //double dist = Math.Sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y));
		        var dist = Math.Acos(Math.Cos(Ext.ToRadians(90 - current.Latitude)) * Math.Cos(Ext.ToRadians(90 - d.X)) +
		                             Math.Sin(Ext.ToRadians(90 - current.Latitude)) * Math.Sin(Ext.ToRadians(90 - d.X)) * Math.Cos(Ext.ToRadians(current.Longitude - d.Y))) * Rad;
		        if ((dist < d1) && ind > 0.0)
		        {
			        d2 = d1;
			        d1 = dist;
			        cIsso2 = cIsso1;
			        cIsso1 = listOfIsso.CIsso;
		        }
		        if ((dist < d2 && dist > d1) && ind > 0.0)
		        {
			        d2 = dist;
			        cIsso2 = listOfIsso.CIsso;
		        }

		        if ((!(dist < d3)) || !(ind < 0.0)) continue;
		        d3 = dist;
		        cIsso3 = listOfIsso.CIsso;
	        }
	        return new[] { cIsso1, cIsso2, cIsso3 };
        }

        private void FollowByMarkers()
        {
            var latitudes = new List<double>();
            var longitudes = new List<double>();

            latitudes.Add(_location.Latitude);
            longitudes.Add(_location.Longitude);

            if(MyMap.Pins.Count > 0)
            {
                foreach(var pin in MyMap.Pins)
                {
                    if(HashMap.GetIndex(2) != -1)
                    {
                        if (!pin.Label.Equals(ListOfIssos[HashMap.GetIndex(2)].FullName))
                        {
                            // Если до ближайшего ИССО осталось менее 500 метров, то берем во внимание только его
                            if (HashMap.GetIndex(1) != -1 && pin.Label.Equals(ListOfIssos[HashMap.GetIndex(1)].FullName))
                            {
                                if(HashMap.GetDistance(0) >= 500)
                                {
                                    latitudes.Add(pin.Position.Latitude);
                                    longitudes.Add(pin.Position.Longitude);
                                }
                            }
                            else if(HashMap.GetIndex(0) != -1)
                            {
                                latitudes.Add(pin.Position.Latitude);
                                longitudes.Add(pin.Position.Longitude);
                            }
                        }
                    }
                    else
                    {
                        // Если до ближайшего ИССО осталось менее 500 метров, то берем во внимание только его
                        if (pin.Label.Equals(ListOfIssos[HashMap.GetIndex(0)].FullName) && HashMap.GetDistance(0) >= 500)
                        {
                            latitudes.Add(pin.Position.Latitude);
                            longitudes.Add(pin.Position.Longitude);
                        }
                        else
                        {
                            latitudes.Add(pin.Position.Latitude);
                            longitudes.Add(pin.Position.Longitude);
                        }
                    }
                }
            }

            var lowestLat = latitudes.Min();
            var highestLat = latitudes.Max();
            var lowestLng = longitudes.Min();
            var highestLng = longitudes.Max();
            var finalLat = (lowestLat + highestLat) / 2;
            var finalLng = (lowestLng + highestLng) / 2;
            var distance = GeoCodeCalc.CalcDistance(lowestLat, lowestLng, highestLat, highestLng, GeoCodeCalcMeasurement.Kilometers);

            MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(new Xamarin.Forms.GoogleMaps.Position(finalLat, finalLng), Distance.FromKilometers(distance / 2)));

            MyMap.SelectedPin = null;
            //RedBox.Opacity = 0.2;
            //GreenBox.Opacity = 0.2;
            //BlueBox.Opacity = 0.2;
            DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
            DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
            DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
        }

        private ArrayVector GetDistanceToObjects(int[] issos, Plugin.Geolocator.Abstractions.Position location)
        {
            var distances = new List<double>();
            var indexes = new List<int>();
            for (var i = 0; i < 3; i++)
            {
                distances.Add(0);
                indexes.Add(-1);
            }
            for (var i = 0; i < ListOfIssos.Length; i++)
            {
                if (ListOfIssos[i].CIsso == issos[0])
                {
                    if (location != null)
                    {
                        var dist = GetDistance(ListOfIssos[i], location);
                        distances[0] = dist;
                        indexes[0] = i;
                    }
                }
                else if (ListOfIssos[i].CIsso == issos[1])
                {
                    if (location != null)
                    {
                        var dist = GetDistance(ListOfIssos[i], location);
                        distances[1] = dist;
                        indexes[1] = i;
                    }
                }
                else if (ListOfIssos[i].CIsso == issos[2])
                {
                    if (location != null)
                    {
                        var dist = GetDistance(ListOfIssos[i], location);
                        distances[2] = dist;
                        indexes[2] = i;
                    }
                }
            }
            var hashMap = new ArrayVector
            {
                Distances = distances,
                Indexes = indexes
            };
            return hashMap;
        }

        private void SetTextForTable ()
        {
            if (HashMap.GetIndex(0) != -1)
            {
                // Расстояние до ИССО
                DistNear.Text = $"{HashMap.GetDistance(0) / 1000:F1} км";
                // Информация об ИССО
                var isso = ListOfIssos[HashMap.GetIndex(0)];
                var km = isso.WIsso >> 16;
                var m = isso.WIsso & 0xFFFF;
                var str = km + "+" + m;
                var ss1 = isso.Name + ": " + $"{isso.DorName}, км {str} ({isso.Obstacle})";
                DescrNear.Text = ss1;
                imgNear.Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_ahead.png") /* String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "marker_ahead.png")*/ };
            }
            else
            {
                DistNear.Text = "";
                DescrNear.Text = "";
                imgNear.Source = null;
            }
            if (HashMap.GetIndex(1) != -1)
            {
                // Расстояние до ИССО
                DistFar.Text = $"{HashMap.GetDistance(1) / 1000:F1} км";
                // Информация об ИССО
                var isso = ListOfIssos[HashMap.GetIndex(1)];
                var km = isso.WIsso >> 16;
                var m = isso.WIsso & 0xFFFF;
                var str = km + "+" + m;
                var ss1 = isso.Name + ": " + $"{isso.DorName}, км {str} ({isso.Obstacle})";
                DescrFar.Text = ss1;
                imgFar.Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_far.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "marker_far.png")*/ };
            }
            else
            {
                DistFar.Text = "";
                DescrFar.Text = "";
                imgFar.Source = null;
            }
            if (HashMap.GetIndex(2) != -1)
            {
                // Расстояние до ИССО
                DistBehind.Text = $"{HashMap.GetDistance(2) / 1000:F1} км";
                // Информация об ИССО
                var isso = ListOfIssos[HashMap.GetIndex(2)];
                var km = isso.WIsso >> 16;
                var m = isso.WIsso & 0xFFFF;
                var str = km + "+" + m;
                var ss1 = isso.Name + ": " + $"{isso.DorName}, км {str} ({isso.Obstacle})";
                DescrBehind.Text = ss1;
                imgBehind.Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_behind.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "marker_behind.png")*/ };
            }
            else
            {
                DistBehind.Text = "";
                DescrBehind.Text = "";
                imgBehind.Source = null;
            }
        }

        /** расчет через полярные координаты */
        public double GetDistance(HttpsIsso isso, Plugin.Geolocator.Abstractions.Position current)
        {
            var len = Convert.ToDouble(isso.Length);
            var lat1 = Ext.ToRadians(isso.Latitude);
            var lat2 = Ext.ToRadians(current.Latitude);
            var lng1 = Ext.ToRadians(isso.Longitude);
            var lng2 = Ext.ToRadians(current.Longitude);

            var x = Math.Sin(lat1) * Math.Sin(lat2) + Math.Cos(lat1) * Math.Cos(lat2) * Math.Cos(lng2 - lng1);
            var y = Math.Sqrt(Math.Pow(Math.Cos(lat2) * Math.Sin(lng2 - lng1), 2.0) + Math.Pow(Math.Cos(lat1) * Math.Sin(lat2) - Math.Sin(lat1) * Math.Cos(lat2) * Math.Cos(lng2 - lng1), 2.0));

            var dist = Math.Atan(y / x) * Rad;
            return Math.Max(dist - len / 2.0, 0);
        }

        private void SetupMyGeolocation()
        {
            MyMap.Pins.Clear();
            if(HashMap.GetIndex(0) != -1)
            {
                var lat = ListOfIssos[HashMap.GetIndex(0)].Latitude;
                var lng = ListOfIssos[HashMap.GetIndex(0)].Longitude;
                var pin = new Pin()
                {
                    Label = ListOfIssos[HashMap.GetIndex(0)].FullName,
                    Position = new Xamarin.Forms.GoogleMaps.Position(lat, lng),
                    Anchor = new Point(0.5, 1.0)
                };
                if(Device.RuntimePlatform == Device.Android)
                    pin.Icon = BitmapDescriptorFactory.FromBundle("marker_ahead.png");
                else
                    pin.Icon = BitmapDescriptorFactory.FromView(new ContentView
                    {
                        WidthRequest = 40,
                        HeightRequest = 40,
                        Scale = 1,
                        Content = new Image
                        {
                            Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_ahead.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "Assets/", "Assets/Icons/"), "marker_ahead.png")*/ },
                            Aspect = Aspect.AspectFit,
                            WidthRequest = 40,
                            HeightRequest = 40
                        }
                    });
                //pin.Icon = BitmapDescriptorFactory.DefaultMarker(Color.Pink);
                MyMap.Pins.Add(pin);
            }
            if (HashMap.GetIndex(1) != -1)
            {
                var lat = ListOfIssos[HashMap.GetIndex(1)].Latitude;
                var lng = ListOfIssos[HashMap.GetIndex(1)].Longitude;
                var pin = new Pin()
                {
                    Label = ListOfIssos[HashMap.GetIndex(1)].FullName,
                    Position = new Xamarin.Forms.GoogleMaps.Position(lat, lng),
                    //Icon = BitmapDescriptorFactory.DefaultMarker(Color.Red)
                    Anchor = new Point(0.5, 1.0)
                };
                if (Device.RuntimePlatform == Device.Android)
                    pin.Icon = BitmapDescriptorFactory.FromBundle("marker_far.png");
                else
                    pin.Icon = BitmapDescriptorFactory.FromView(new ContentView
                    {
                        WidthRequest = 40,
                        HeightRequest = 40,
                        Scale = 1,
                        Content = new Image
                        {
                            Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_far.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "Assets/", "Assets/Icons/"), "marker_far.png")*/ },
                            Aspect = Aspect.AspectFit,
                            WidthRequest = 40,
                            HeightRequest = 40
                        }
                    });
                //pin.Icon = BitmapDescriptorFactory.DefaultMarker(Color.Black);
                MyMap.Pins.Add(pin);
            }
            if (HashMap.GetIndex(2) != -1)
            {
                var lat = ListOfIssos[HashMap.GetIndex(2)].Latitude;
                var lng = ListOfIssos[HashMap.GetIndex(2)].Longitude;
                var pin = new Pin()
                {
                    Label = ListOfIssos[HashMap.GetIndex(2)].FullName,
                    Position = new Xamarin.Forms.GoogleMaps.Position(lat, lng),
                    //Icon = BitmapDescriptorFactory.DefaultMarker(Color.Blue)
                    Anchor = new Point(0.5, 1.0)
                };
                if(Device.RuntimePlatform == Device.Android)
                    pin.Icon = BitmapDescriptorFactory.FromBundle("marker_behind.png");
                else
                    pin.Icon = BitmapDescriptorFactory.FromView(new ContentView
                    {
                        WidthRequest = 40,
                        HeightRequest = 40,
                        Scale = 1,
                        Content = new Image
                        {
                            Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_behind.png")/* String.Format("{0}{1}", Device.OnPlatform("Icons/", "Assets/", "Assets/Icons/"), "marker_behind.png")*/ },
                            Aspect = Aspect.AspectFit,
                            WidthRequest = 40,
                            HeightRequest = 40
                        }
                    });
                MyMap.Pins.Add(pin);
            }
        }

        public abstract void FarTapped(object sender, EventArgs e);

        public abstract void NearTapped(object sender, EventArgs e);

        public abstract void ShowAlert(string str);

        public abstract void BehindTapped(object sender, EventArgs e);

        public void FarPinTapped(object sender, EventArgs e)
        {
            foreach (var pin in MyMap.Pins)
            {
	            if (HashMap.GetIndex(1) == -1 ||
	                !(Math.Abs(pin.Position.Latitude - ListOfIssos[HashMap.GetIndex(1)].Latitude) <
	                  CommonStaffUtils.DoubleTolerance) ||
	                !(Math.Abs(pin.Position.Longitude - ListOfIssos[HashMap.GetIndex(1)].Longitude) <
	                  CommonStaffUtils.DoubleTolerance)) continue;
	            _isFollowed = false;
	            Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
	            MyMap.SelectedPin = pin;
	            MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(pin.Position, Distance.FromMeters(500)));
	            //RedBox.Opacity = 0.5;
	            //GreenBox.Opacity = 0.2;
	            //BlueBox.Opacity = 0.2;
	            DescrFar.FontAttributes = FontAttributes.Bold; DistFar.FontAttributes = FontAttributes.Bold;
	            DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
	            DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
            }
        }

        public void NearPinTapped(object sender, EventArgs e)
        {
            foreach (var pin in MyMap.Pins)
            {
	            if (HashMap.GetIndex(0) == -1 ||
	                !(Math.Abs(pin.Position.Latitude - ListOfIssos[HashMap.GetIndex(0)].Latitude) <
	                  CommonStaffUtils.DoubleTolerance) ||
	                !(Math.Abs(pin.Position.Longitude - ListOfIssos[HashMap.GetIndex(0)].Longitude) <
	                  CommonStaffUtils.DoubleTolerance)) continue;
	            _isFollowed = false;
	            Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
	            MyMap.SelectedPin = pin;
	            MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(pin.Position, Distance.FromMeters(500)));
	            //RedBox.Opacity = 0.2;
	            //GreenBox.Opacity = 0.5;
	            //BlueBox.Opacity = 0.2;
	            DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
	            DescrNear.FontAttributes = FontAttributes.Bold; DistNear.FontAttributes = FontAttributes.Bold;
	            DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
            }
        }

        public void BehindPinTapped(object sender, EventArgs e)
        {
            foreach (var pin in MyMap.Pins)
            {
	            if (HashMap.GetIndex(2) == -1 ||
	                !(Math.Abs(pin.Position.Latitude - ListOfIssos[HashMap.GetIndex(2)].Latitude) <
	                  CommonStaffUtils.DoubleTolerance) ||
	                !(Math.Abs(pin.Position.Longitude - ListOfIssos[HashMap.GetIndex(2)].Longitude) <
	                  CommonStaffUtils.DoubleTolerance)) continue;
	            _isFollowed = false;
	            Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
	            MyMap.SelectedPin = pin;
	            MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(pin.Position, Distance.FromMeters(500)));
	            //RedBox.Opacity = 0.2;
	            //GreenBox.Opacity = 0.2;
	            //BlueBox.Opacity = 0.5;
	            DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
	            DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
	            DescrBehind.FontAttributes = FontAttributes.Bold; DistBehind.FontAttributes = FontAttributes.Bold;
            }
        }

        public void DisableGeolocator()
        {
            CrossGeolocator.Current.PositionChanged -= Current_PositionChanged;
            CrossGeolocator.Current.PositionError -= Current_PositionError;
            CrossGeolocator.Current.StopListeningAsync();
        }

        private void Btn_Follow_Clicked(object sender, EventArgs e)
        {
            if(_isFollowed)
            {
                _isFollowed = false;
                Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png")/* String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
            }
            else
            {
                if (_location != null)
                {
                    _isFollowed = true;
                    FollowByMarkers();
                    Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("define_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "define_location_dark.png")*/ };
                }
                else
                {
                    ShowAlert("Данные о геолокаци ещё не получены");
                }
            }
        }

        private void Btn_Satellite_Clicked(object sender, EventArgs e)
        {
            //var type = await DisplayActionSheet("Тип карты:", "Отмена", null, "Улица", "Спутник", "Гибрид");
            //switch (type)
            //{
            //    case "Улица":
            //        MyMap.MapType = MapType.Street;

            //        Btn_Satellite.Image = TypesMapImgLight[0];
            //        break;
            //    case "Спутник":
            //        MyMap.MapType = MapType.Satellite;
            //        Btn_Satellite.Image = TypesMapImgLight[1];
            //        break;
            //    case "Гибрид":
            //        MyMap.MapType = MapType.Hybrid;
            //        Btn_Satellite.Image = TypesMapImgLight[2];
            //        break;
            //}
            var items = new List<ItemType>();
            for (var i = 0; i < _typesMap.Length; i++)
            {
                items.Add(new ItemType(_typesMap[i], TypesMapImgDark[i]));
            }
            var page = new CustomDialogPage(items.ToArray());
            page.Clicked += delegate (object sender1, EventArgs e1) { Map_Clicked(sender1, e1, page.IndexSelected); };
            Navigation.PushPopupAsync(new CommonPopupPage(page, CustomDialogPage.Header));
        }

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

        public override void Dispose()
        {
            _prevLocation = null;
            _location = null;
            ListOfIssos = new HttpsIsso[] { }; ListOfIssos = null;
            _dists.Clear(); _distsNew.Clear(); _dists = null; _distsNew = null;
            HashMap?.Dispose();

            MyMap.PinClicked -= MyMap_PinClicked;
            MyMap.SelectedPinChanged -= MyMap_SelectedPinChanged;
            MyMap.InfoWindowClicked -= MyMap_InfoWindowClicked;
            MyMap = null;

            _typesViewImg = null;
            _typesMap = null;
            TypesMapImgLight = null;
            TypesMapImgDark = null;

            _toolBarMapType = null;

            BindingContext = null;
            Content = null;

            GC.Collect();
        }

        //void PrepareForDispose()
        //{
        //    this.PrevLocation = null;
        //    this.Location = null;

        //    this.dists_new.Clear();
        //    this.dists_new = null;

        //    this.dists.Clear();
        //    this.dists = null;

        //    this.listOfIssos = null;

        //    this.hashMap.Dispose();

        //    this.MyMap = null;

        //    this.TypesViewImg = null;
        //    this.TypesMapImgDark = null;
        //    this.TypesMapImgLight = null;

        //    BindingContext = null;

        //    Content = null;
        //}

    }

    public enum TypeScreen
    {
        OnlyList = 1,
        OnlyMap = 2,
        MapAndList = 3
    }
}
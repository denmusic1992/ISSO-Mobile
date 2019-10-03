using Autoselect_new.CustomRenderers;
using CommonClassesLibrary;
using CommonClassesLibrary.BackHandlers;
using Plugin.Geolocator;
using Plugin.Geolocator.Abstractions;
using Plugin.Permissions.Abstractions;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using Xamarin.Forms;
using Xamarin.Forms.GoogleMaps;
using Xamarin.Forms.Xaml;

namespace Autoselect
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public abstract partial class MapsActivity : MyBackHandleContentPage
    {
        /// <summary>
        /// Предыдущая геопозиция
        /// </summary>
        private Plugin.Geolocator.Abstractions.Position PrevLocation = null;
        /// <summary>
        /// Текущая геопозиция
        /// </summary>
        private Plugin.Geolocator.Abstractions.Position Location = null;
        /// <summary>
        /// Список ИССО
        /// </summary>
        public HttpsIsso[] listOfIssos;
        /// <summary>
        /// Расстояние м/у ИССО и текущей геопозицией
        /// </summary>
        private Dictionary<Int32, Vector2D> dists = new Dictionary<int, Vector2D>();
        /// <summary>
        /// Расстояние м/у ИССО и текущей геопозицией(NEW)
        /// </summary>
        private Dictionary<Int32, Vector2D> dists_new = new Dictionary<int, Vector2D>();
        /// <summary>
        /// Набор данных по ближайщим ИССО
        /// </summary>
        public ArrayVector hashMap;
        /// <summary>
        /// Радиус Земли (м)
        /// </summary>
        public static double rad = 6371200;
        /// <summary>
        /// Следование за пользователем
        /// </summary>
        private bool isFollowed = false;
        /// <summary>
        /// Для первоначального отображения геопозиции пользователя
        /// </summary>
        private bool once = true;
        /// <summary>
        /// Когда координаты найдены
        /// </summary>
        private bool CoordsFounded = false;
        /// <summary>
        /// Идентификатор перехода к подробной информации по ИССО
        /// </summary>
        public bool ToIssoView = false;
        /// <summary>
        /// Экземпляр карты
        /// </summary>
        public MyCustomMap MyMap;
        /// <summary>
        /// Тип отображения (по умолчанию список и карта)
        /// </summary>
        public TypeScreen typeScreen = TypeScreen.MapAndList;
        /// <summary>
        /// Варианты, возможные для отображения
        /// </summary>
        private string[] TypesView = { "Список", "Список и карта", "Карта" };
        /// <summary>
        /// Иконки для этого дела
        /// </summary>
        private FileImageSource[] TypesViewImg = {
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("list_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "list_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("map_list_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "map_list_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("map_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "map_light.png")*/ }
        };
        private string[] TypesMap = { "Улица", "Спутник", "Гибрид" };
        /// <summary>
        /// Иконки для отображения карты (темные)
        /// </summary>
        private FileImageSource[] TypesMapImgDark =
        {
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("normal_map_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "normal_map_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("satellite_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "satellite_light.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("terrain_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "terrain_light.png")*/ }
        };
        /// <summary>
        /// Иконки для отображения карты (светлые)
        /// </summary>
        private FileImageSource[] TypesMapImgLight =
        {
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("normal_map_dark.png")  /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "normal_map_dark.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("satellite_dark.png")  /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "satellite_dark.png")*/ },
            new FileImageSource() { File = CommonStaffUtils.GetFilePath("terrain_dark.png")  /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "terrain_dark.png")*/ }
        };
        /// <summary>
        /// Кнопка бокового меню
        /// </summary>
        private ToolbarItem ToolBarMapType;

        public MapsActivity(HttpsIsso[] ListOfIssos)
        {
            this.listOfIssos = ListOfIssos;
            InitializeComponent();

            ToolBarMapType = new ToolbarItem
            {
                Command = new Command((sender) => ShowMapType()),
                Priority = 0,
                Order = ToolbarItemOrder.Primary
            };
            ToolbarItems.Add(ToolBarMapType);
            MyMap = new MyCustomMap()
            {
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.FillAndExpand,
            };
            ForMap.Children.Add(MyMap);
            Btn_Follow.Image = new FileImageSource() {  File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
            //GridView.HeightRequest = Content.Height / 2;
            //MapGrid.HeightRequest = Content.Height / 2;
            switch (typeScreen)
            {
                case TypeScreen.OnlyList:
                    GridView.HeightRequest = Content.Height;
                    MapGrid.HeightRequest = 0;
                    // Если координаты не получены
                    //FindCoords.HeightRequest = Content.Height;
                    //Founded.HeightRequest = 0;
                    if(Device.RuntimePlatform.Equals(Device.Android))
                    {
                        ToolBarMapType.Icon = new FileImageSource() { File = "list_dark.png" };
                    }
                    else
                    {
                        ToolBarMapType.Icon = TypesViewImg[0];
                    }
                    break;
                case TypeScreen.MapAndList:
                    GridView.HeightRequest = Content.Height / 3;
                    MapGrid.HeightRequest = Content.Height * 2 / 3;
                    // Если координаты не получены
                    //FindCoords.HeightRequest = Content.Height / 3;
                    //Founded.HeightRequest = 0;
                    if (Device.RuntimePlatform.Equals(Device.Android))
                    {
                        ToolBarMapType.Icon = new FileImageSource() { File = "map_list_dark.png" };
                    }
                    else
                    {
                        ToolBarMapType.Icon = TypesViewImg[1];
                    }
                    break;
                case TypeScreen.OnlyMap:
                    GridView.HeightRequest = 0;
                    MapGrid.HeightRequest = Content.Height;
                    if (Device.RuntimePlatform.Equals(Device.Android))
                    {
                        ToolBarMapType.Icon = new FileImageSource() { File = "map_dark.png" };
                    }
                    else
                    {
                        ToolBarMapType.Icon = TypesViewImg[2];
                    }
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
            if (hashMap.GetIndex(1) != -1 && e.Pin.Position.Latitude == listOfIssos[hashMap.GetIndex(1)].Latitude &&
                e.Pin.Position.Longitude == listOfIssos[hashMap.GetIndex(1)].Longitude)
            {
                FarTapped(null, null);
            }
            else if (hashMap.GetIndex(0) != -1 && e.Pin.Position.Latitude == listOfIssos[hashMap.GetIndex(0)].Latitude &&
                e.Pin.Position.Longitude == listOfIssos[hashMap.GetIndex(0)].Longitude)
            {
                NearTapped(null, null);
            }
            else if (hashMap.GetIndex(2) != -1)
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
            isFollowed = false;
            Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
            MyMap.SelectedPin = e.Pin;
            MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(e.Pin.Position, Distance.FromMeters(500)), true);
            foreach (Pin pin in MyMap.Pins)
            {
                if(hashMap.GetIndex(1) != -1 && e.Pin.Position.Latitude == listOfIssos[hashMap.GetIndex(1)].Latitude &&
                    e.Pin.Position.Longitude == listOfIssos[hashMap.GetIndex(1)].Longitude)
                {
                    //RedBox.Opacity = 0.5;
                    //GreenBox.Opacity = 0.2;
                    //BlueBox.Opacity = 0.2;
                    DescrFar.FontAttributes = FontAttributes.Bold; DistFar.FontAttributes = FontAttributes.Bold;
                    DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
                    DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
                }
                else if (hashMap.GetIndex(0) != -1 && e.Pin.Position.Latitude == listOfIssos[hashMap.GetIndex(0)].Latitude &&
                    e.Pin.Position.Longitude == listOfIssos[hashMap.GetIndex(0)].Longitude)
                {
                    //RedBox.Opacity = 0.2;
                    //GreenBox.Opacity = 0.5;
                    //BlueBox.Opacity = 0.2;
                    DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
                    DescrNear.FontAttributes = FontAttributes.Bold; DistNear.FontAttributes = FontAttributes.Bold;
                    DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
                }
                else if (hashMap.GetIndex(2) != -1)
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
            List<ItemType> items = new List<ItemType>();
            for(int i = 0; i < TypesView.Length; i++)
            {
                items.Add(new ItemType(TypesView[i], TypesViewImg[i]));
            }
            CustomDialogPage page = new CustomDialogPage(items.ToArray());
            page.Clicked += delegate (object sender, EventArgs e) { Type_Clicked(sender, e, page.index_selected); };
            (Application.Current.MainPage as MasterDetailPage).Detail.Navigation.PushPopupAsync(page);
        }

        public void Type_Clicked(object sender, EventArgs e, int index)
        {
            (Application.Current.MainPage as MasterDetailPage).Detail.Navigation.PopPopupAsync();
            switch (index)
            {
                case 0:
                    typeScreen = TypeScreen.OnlyList;
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
                    if (Device.RuntimePlatform.Equals(Device.Android))
                    {
                        ToolBarMapType.Icon = new FileImageSource() { File = "list_dark.png" };
                    }
                    else
                    {
                        ToolBarMapType.Icon = TypesViewImg[0];
                    }
                    break;
                case 1:
                    typeScreen = TypeScreen.MapAndList;
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
                    if (Device.RuntimePlatform.Equals(Device.Android))
                    {
                        ToolBarMapType.Icon = new FileImageSource() { File = "map_list_dark.png" };
                    }
                    else
                    {
                        ToolBarMapType.Icon = TypesViewImg[1];
                    }
                    break;
                case 2:
                    typeScreen = TypeScreen.OnlyMap;
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
                    if (Device.RuntimePlatform.Equals(Device.Android))
                    {
                        ToolBarMapType.Icon = new FileImageSource() { File = "map_dark.png" };
                    }
                    else
                    {
                        ToolBarMapType.Icon = TypesViewImg[2];
                    }
                    break;
            }
            if (!CoordsFounded)
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
            this.WidthRequest = width;
            this.HeightRequest = height;
            if (width > height)
            {
                MapStack.Orientation = StackOrientation.Horizontal;
                switch(typeScreen)
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
                }
                if(!CoordsFounded)
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
                switch (typeScreen)
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
                if (!CoordsFounded)
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
            var hasPermission = await Utils.CheckPermissions(Permission.Location);
            if (!hasPermission)
                return;
            if (CrossGeolocator.Current.IsListening) return;
            MyMap.MyLocationEnabled = true;
            CrossGeolocator.Current.PositionChanged += Current_PositionChanged;
            CrossGeolocator.Current.PositionError += Current_PositionError;
            await CrossGeolocator.Current.StartListeningAsync(TimeSpan.FromSeconds(1), 5, false);
        }

        private void Current_PositionError(object sender, PositionErrorEventArgs e)
        {
            DisplayAlert("Ошибка", "По непонятным причинам GPS не работает на вашем устройстве", "Ну ок :(");
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
            Location = e.Position;
            if(!isFollowed && once)
            {
                MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(new Xamarin.Forms.GoogleMaps.Position(Location.Latitude, Location.Longitude), Distance.FromKilometers(2)));
                once = false;
            }
            if (PrevLocation != null && (PrevLocation.Latitude != Location.Latitude || PrevLocation.Longitude != Location.Longitude))
            {
                if (!CoordsFounded)
                {
                    CoordsFounded = true;
                    OnSizeAllocated(this.Width, this.Height);
                }
                if(Location.Accuracy <= 50.0)
                {
                    int[] issos = RefreshList(PrevLocation, Location);
                    hashMap = GetDistanceToObjects(issos, Location);
                    SetTextForTable();
                    SetupMyGeolocation();
                    if(isFollowed)
                        FollowByMarkers();
                    PrevLocation = Location;
                }
            }
            else if (Location.Accuracy <= 50.0)
            {
                PrevLocation = Location;
            }
        }

        private int[] RefreshList(Plugin.Geolocator.Abstractions.Position previous, Plugin.Geolocator.Abstractions.Position current)
        {
            if( dists.Count == 0)
            {
                foreach(HttpsIsso isso in listOfIssos)
                {
                    double lat = Ext.ToRadians(isso.Latitude);
                    double lng = Ext.ToRadians(isso.Longitude);
                    dists.Add(isso.CIsso, new Vector2D(lat, lng));

                    //dists.Add(isso.CIsso, new Vector2D(isso.Latitude, isso.Longitude));
                }
                foreach (HttpsIsso isso in listOfIssos)
                {
                    dists_new.Add(isso.CIsso, new Vector2D(isso.Latitude, isso.Longitude));
                }
            }

            Vector2D vPos = null;
            Vector2D vPrev = null;

            if (current != null && current.Accuracy <= 50.0 && previous != null && previous.Accuracy <= 50.0)
            {
                double lat = Ext.ToRadians(current.Latitude);
                double lng = Ext.ToRadians(current.Longitude);
                vPos = new Vector2D(lat, lng);
                lat = Ext.ToRadians(previous.Latitude);
                lng = Ext.ToRadians(previous.Longitude);
                vPrev = new Vector2D(lat, lng);
            }

            int cIsso1 = -1, cIsso2 = -1, cIsso3 = -1;
            double d1 = Double.MaxValue, d2 = Double.MaxValue, d3 = Double.MaxValue;
            if (vPos != null)
            {
                foreach (HttpsIsso listOfIsso in listOfIssos)
                {
                    Vector2D d = dists[listOfIsso.CIsso];
                    double ind = vPos.Minus(vPrev).DotProduct(d.Minus(vPrev));

                    double x = rad * Math.Cos(vPos.x) * Math.Cos(vPos.y);
                    double y = rad * Math.Sin(vPos.x) * Math.Cos(vPos.y);

                    d = dists_new[listOfIsso.CIsso];
                    double posX = rad * Math.Cos(d.x) * Math.Cos(d.y);
                    double posY = rad * Math.Sin(d.x) * Math.Cos(d.y);

                    //double dist = Math.Sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y));
                    double dist = Math.Acos(Math.Cos(Ext.ToRadians(90 - current.Latitude)) * Math.Cos(Ext.ToRadians(90 - d.x)) +
                        Math.Sin(Ext.ToRadians(90 - current.Latitude)) * Math.Sin(Ext.ToRadians(90 - d.x)) * Math.Cos(Ext.ToRadians(current.Longitude - d.y))) * rad;
                    double len = Convert.ToDouble(listOfIsso.Length);
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
                    if ((dist < d3) && ind < 0.0)
                    {
                        d3 = dist;
                        cIsso3 = listOfIsso.CIsso;
                    }
                }
            }
            return new int[] { cIsso1, cIsso2, cIsso3 };
        }

        private void FollowByMarkers()
        {
            var latitudes = new List<double>();
            var longitudes = new List<double>();

            latitudes.Add(Location.Latitude);
            longitudes.Add(Location.Longitude);

            if(MyMap.Pins.Count > 0)
            {
                foreach(Pin pin in MyMap.Pins)
                {
                    if(hashMap.GetIndex(2) != -1)
                    {
                        if (!pin.Label.Equals(listOfIssos[hashMap.GetIndex(2)].FullName))
                        {
                            // Если до ближайшего ИССО осталось менее 500 метров, то берем во внимание только его
                            if (hashMap.GetIndex(1) != -1 && pin.Label.Equals(listOfIssos[hashMap.GetIndex(1)].FullName))
                            {
                                if(hashMap.GetDistance(0) >= 500)
                                {
                                    latitudes.Add(pin.Position.Latitude);
                                    longitudes.Add(pin.Position.Longitude);
                                }
                            }
                            else if(hashMap.GetIndex(0) != -1)
                            {
                                latitudes.Add(pin.Position.Latitude);
                                longitudes.Add(pin.Position.Longitude);
                            }
                        }
                    }
                    else
                    {
                        // Если до ближайшего ИССО осталось менее 500 метров, то берем во внимание только его
                        if (pin.Label.Equals(listOfIssos[hashMap.GetIndex(0)].FullName) && hashMap.GetDistance(0) >= 500)
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

            double lowestLat = latitudes.Min();
            double highestLat = latitudes.Max();
            double lowestLng = longitudes.Min();
            double highestLng = longitudes.Max();
            double finalLat = (lowestLat + highestLat) / 2;
            double finalLng = (lowestLng + highestLng) / 2;
            double distance = DistanceCalculation.GeoCodeCalc.CalcDistance(lowestLat, lowestLng, highestLat, highestLng, DistanceCalculation.GeoCodeCalcMeasurement.Kilometers);

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
            List<Double> distances = new List<Double>();
            List<Int32> indexes = new List<Int32>();
            for (int i = 0; i < 3; i++)
            {
                distances.Add((double)0);
                indexes.Add(-1);
            }
            for (int i = 0; i < listOfIssos.Length; i++)
            {
                if (listOfIssos[i].CIsso == issos[0])
                {
                    if (location != null)
                    {
                        double dist = GetDistance(listOfIssos[i], location);
                        distances[0] = dist;
                        indexes[0] = i;
                    }
                }
                else if (listOfIssos[i].CIsso == issos[1])
                {
                    if (location != null)
                    {
                        double dist = GetDistance(listOfIssos[i], location);
                        distances[1] = dist;
                        indexes[1] = i;
                    }
                }
                else if (listOfIssos[i].CIsso == issos[2])
                {
                    if (location != null)
                    {
                        double dist = GetDistance(listOfIssos[i], location);
                        distances[2] = dist;
                        indexes[2] = i;
                    }
                }
            }
            ArrayVector hashMap = new ArrayVector
            {
                distances = distances,
                indexes = indexes
            };
            return hashMap;
        }

        private void SetTextForTable ()
        {
            if (hashMap.GetIndex(0) != -1)
            {
                // Расстояние до ИССО
                DistNear.Text = String.Format("{0:F1} км", hashMap.GetDistance(0) / 1000);
                // Информация об ИССО
                var isso = listOfIssos[hashMap.GetIndex(0)];
                int km = isso.WIsso >> 16;
                int m = isso.WIsso & 0xFFFF;
                String str = km + "+" + m;
                String ss1 = isso.Name + ": " + String.Format("{0}, км {1} ({2})", isso.DorName, str, isso.Obstacle);
                DescrNear.Text = ss1;
                imgNear.Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_ahead.png") /* String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "marker_ahead.png")*/ };
            }
            else
            {
                DistNear.Text = "";
                DescrNear.Text = "";
                imgNear.Source = null;
            }
            if (hashMap.GetIndex(1) != -1)
            {
                // Расстояние до ИССО
                DistFar.Text = String.Format("{0:F1} км", hashMap.GetDistance(1) / 1000);
                // Информация об ИССО
                var isso = listOfIssos[hashMap.GetIndex(1)];
                int km = isso.WIsso >> 16;
                int m = isso.WIsso & 0xFFFF;
                String str = km + "+" + m;
                String ss1 = isso.Name + ": " + String.Format("{0}, км {1} ({2})", isso.DorName, str, isso.Obstacle);
                DescrFar.Text = ss1;
                imgFar.Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("marker_far.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "marker_far.png")*/ };
            }
            else
            {
                DistFar.Text = "";
                DescrFar.Text = "";
                imgFar.Source = null;
            }
            if (hashMap.GetIndex(2) != -1)
            {
                // Расстояние до ИССО
                DistBehind.Text = String.Format("{0:F1} км", hashMap.GetDistance(2) / 1000);
                // Информация об ИССО
                var isso = listOfIssos[hashMap.GetIndex(2)];
                int km = isso.WIsso >> 16;
                int m = isso.WIsso & 0xFFFF;
                String str = km + "+" + m;
                String ss1 = isso.Name + ": " + String.Format("{0}, км {1} ({2})", isso.DorName, str, isso.Obstacle);
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
            double len = Convert.ToDouble(isso.Length);
            double lat1 = Ext.ToRadians(isso.Latitude);
            double lat2 = Ext.ToRadians(current.Latitude);
            double lng1 = Ext.ToRadians(isso.Longitude);
            double lng2 = Ext.ToRadians(current.Longitude);

            double x = Math.Sin(lat1) * Math.Sin(lat2) + Math.Cos(lat1) * Math.Cos(lat2) * Math.Cos(lng2 - lng1);
            double y = Math.Sqrt(Math.Pow(Math.Cos(lat2) * Math.Sin(lng2 - lng1), 2.0) + Math.Pow(Math.Cos(lat1) * Math.Sin(lat2) - Math.Sin(lat1) * Math.Cos(lat2) * Math.Cos(lng2 - lng1), 2.0));

            double dist = Math.Atan(y / x) * rad;
            return Math.Max(dist - len / 2.0, 0);
        }

        private void SetupMyGeolocation()
        {
            MyMap.Pins.Clear();
            if(hashMap.GetIndex(0) != -1)
            {
                double lat = listOfIssos[hashMap.GetIndex(0)].Latitude;
                double lng = listOfIssos[hashMap.GetIndex(0)].Longitude;
                Pin pin = new Pin()
                {
                    Label = listOfIssos[hashMap.GetIndex(0)].FullName,
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
            if (hashMap.GetIndex(1) != -1)
            {
                double lat = listOfIssos[hashMap.GetIndex(1)].Latitude;
                double lng = listOfIssos[hashMap.GetIndex(1)].Longitude;
                Pin pin = new Pin()
                {
                    Label = listOfIssos[hashMap.GetIndex(1)].FullName,
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
            if (hashMap.GetIndex(2) != -1)
            {
                double lat = listOfIssos[hashMap.GetIndex(2)].Latitude;
                double lng = listOfIssos[hashMap.GetIndex(2)].Longitude;
                Pin pin = new Pin()
                {
                    Label = listOfIssos[hashMap.GetIndex(2)].FullName,
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
            foreach (Pin pin in MyMap.Pins)
            {
                if(hashMap.GetIndex(1) != -1 && pin.Position.Latitude == listOfIssos[hashMap.GetIndex(1)].Latitude &&
                    pin.Position.Longitude == listOfIssos[hashMap.GetIndex(1)].Longitude)
                {
                    isFollowed = false;
                    Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
                    MyMap.SelectedPin = pin;
                    MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(pin.Position, Distance.FromMeters(500)), true);
                    //RedBox.Opacity = 0.5;
                    //GreenBox.Opacity = 0.2;
                    //BlueBox.Opacity = 0.2;
                    DescrFar.FontAttributes = FontAttributes.Bold; DistFar.FontAttributes = FontAttributes.Bold;
                    DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
                    DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
                }
            }
        }

        public void NearPinTapped(object sender, EventArgs e)
        {
            foreach (Pin pin in MyMap.Pins)
            {
                if (hashMap.GetIndex(0) != -1 && pin.Position.Latitude == listOfIssos[hashMap.GetIndex(0)].Latitude &&
                    pin.Position.Longitude == listOfIssos[hashMap.GetIndex(0)].Longitude)
                {
                    isFollowed = false;
                    Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
                    MyMap.SelectedPin = pin;
                    MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(pin.Position, Distance.FromMeters(500)), true);
                    //RedBox.Opacity = 0.2;
                    //GreenBox.Opacity = 0.5;
                    //BlueBox.Opacity = 0.2;
                    DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
                    DescrNear.FontAttributes = FontAttributes.Bold; DistNear.FontAttributes = FontAttributes.Bold;
                    DescrBehind.FontAttributes = FontAttributes.None; DistBehind.FontAttributes = FontAttributes.None;
                }
            }
        }

        public void BehindPinTapped(object sender, EventArgs e)
        {
            foreach (Pin pin in MyMap.Pins)
            {
                if (hashMap.GetIndex(2) != -1 && pin.Position.Latitude == listOfIssos[hashMap.GetIndex(2)].Latitude &&
                    pin.Position.Longitude == listOfIssos[hashMap.GetIndex(2)].Longitude)
                {
                    isFollowed = false;
                    Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
                    MyMap.SelectedPin = pin;
                    MyMap.MoveToRegion(MapSpan.FromCenterAndRadius(pin.Position, Distance.FromMeters(500)), true);
                    //RedBox.Opacity = 0.2;
                    //GreenBox.Opacity = 0.2;
                    //BlueBox.Opacity = 0.5;
                    DescrFar.FontAttributes = FontAttributes.None; DistFar.FontAttributes = FontAttributes.None;
                    DescrNear.FontAttributes = FontAttributes.None; DistNear.FontAttributes = FontAttributes.None;
                    DescrBehind.FontAttributes = FontAttributes.Bold; DistBehind.FontAttributes = FontAttributes.Bold;
                }
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
            if(isFollowed)
            {
                isFollowed = false;
                Btn_Follow.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("free_location_dark.png")/* String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "free_location_dark.png")*/ };
            }
            else
            {
                if (Location != null)
                {
                    isFollowed = true;
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
            List<ItemType> items = new List<ItemType>();
            for (int i = 0; i < TypesMap.Length; i++)
            {
                items.Add(new ItemType(TypesMap[i], TypesMapImgDark[i]));
            }
            CustomDialogPage page = new CustomDialogPage(items.ToArray());
            page.Clicked += delegate (object sender1, EventArgs e1) { Map_Clicked(sender1, e1, page.index_selected); };
            (Application.Current.MainPage as MasterDetailPage).Detail.Navigation.PushPopupAsync(page);
        }

        public void Map_Clicked(object sender, EventArgs e, int index)
        {
            (Application.Current.MainPage as MasterDetailPage).Detail.Navigation.PopPopupAsync();
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
            try
            {
                PrevLocation = null;
                Location = null;
                listOfIssos = new HttpsIsso[0] { }; listOfIssos = null;
                dists.Clear(); dists_new.Clear(); dists = null; dists_new = null;
                hashMap?.Dispose();

                MyMap.PinClicked -= MyMap_PinClicked;
                MyMap.SelectedPinChanged -= MyMap_SelectedPinChanged;
                MyMap.InfoWindowClicked -= MyMap_InfoWindowClicked;
                MyMap = null;

                TypesViewImg = null;
                TypesMap = null;
                TypesMapImgLight = null;
                TypesMapImgDark = null;

                ToolBarMapType = null;

                this.BindingContext = null;
                this.Content = null;

                GC.Collect();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
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

    public class DistanceCalculation
    {
        public static class GeoCodeCalc
        {
            public const double EarthRadiusInMiles = 3956.0;
            public const double EarthRadiusInKilometers = 6367.0;

            public static double ToRadian(double val) { return val * (Math.PI / 180); }
            public static double DiffRadian(double val1, double val2) { return ToRadian(val2) - ToRadian(val1); }

            public static double CalcDistance(double lat1, double lng1, double lat2, double lng2)
            {
                return CalcDistance(lat1, lng1, lat2, lng2, GeoCodeCalcMeasurement.Miles);
            }

            public static double CalcDistance(double lat1, double lng1, double lat2, double lng2, GeoCodeCalcMeasurement m)
            {
                double radius = GeoCodeCalc.EarthRadiusInMiles;

                if (m == GeoCodeCalcMeasurement.Kilometers) { radius = GeoCodeCalc.EarthRadiusInKilometers; }
                return radius * 2 * Math.Asin(Math.Min(1, Math.Sqrt((Math.Pow(Math.Sin((DiffRadian(lat1, lat2)) / 2.0), 2.0) + Math.Cos(ToRadian(lat1)) * Math.Cos(ToRadian(lat2)) * Math.Pow(Math.Sin((DiffRadian(lng1, lng2)) / 2.0), 2.0)))));
            }
        }

        public enum GeoCodeCalcMeasurement : int
        {
            Miles = 0,
            Kilometers = 1
        }
    }
}
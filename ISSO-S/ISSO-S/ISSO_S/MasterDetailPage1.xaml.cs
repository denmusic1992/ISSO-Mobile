using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using LinearList;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using CommonClassesLibrary;
using Autoselect;
using CommonClassesLibrary.Interfaces;
using Rg.Plugins.Popup.Extensions;

// ReSharper disable once CheckNamespace
namespace ISSO_S
{
    /// <inheritdoc />
    /// <summary>
    /// Класс, отвечающий за отображение бокового меню
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MasterDetailPage1
    {
        /// <summary>
        /// Конструктор
        /// </summary>
        public MasterDetailPage1()
        {
            InitializeComponent();
            MasterPage.ListView.ItemSelected += ListView_ItemSelected;
        }

        /// <summary>
        /// Обработчик нажатия элемента из списка
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ListView_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
	        if (!(e.SelectedItem is MasterDetailPage1MenuItem item))
                return;
            Page page;
            // Если был выбран "Автовыбор"
            if(item.TargetType == typeof(MapsActivity))
            {
                // Передаем список ИССО
                var issos = new List<HttpsIsso>();
                var issosTable = App.CreateDatabase().Query<IssosList>(
	                "SELECT I_ISSO.C_ISSO, NAME, DORNAME, W_ISSO, FULLNAME, coalesce(RATINGS, -1) as RATINGS_1, N_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                "left outer join RATING on (RATING.C_ISSO = I_ISSO.C_ISSO and RATING.RATINGDATE = (select MAX(RATINGDATE) from RATING where C_ISSO = I_ISSO.C_ISSO))").ToList();
                foreach(var isso in issosTable)
                {
                    issos.Add(new HttpsIsso(isso.C_ISSO, isso.DORNAME, isso.N_OTC_EXP, isso.C_OTC_EXP, isso.FULLNAME,
	                    isso.LATITUDE, isso.LONGITUDE, isso.LENGTH.ToString(CultureInfo.CurrentCulture), isso.W_ISSO,
	                    isso.NAME, isso.OBSTACLE));
                }
                page = new MainMapsActivity(issos.ToArray());
                //page = new Testik();
            }
            // Если были выбраны "Настройки"
            else if(item.TargetType == typeof(SettingsActivity))
            {
                page = new MainSettingsActivity();
            }
            // Если была выбрана "Синхронизация"
            else
            {
                page = new MainSyncActivity(App.AppType);
            }
            page.Title = item.Title;

            Detail.Navigation.PushAsync(page);
            IsPresented = false;

            MasterPage.ListView.SelectedItem = null;
        }

        /// <summary>
        /// Pushes a page and disables the master menu.
        /// </summary>
        /// <param name="page"></param>
        /// <returns></returns>
        public async Task PushAsync(Page page)
        {
            if (Detail is NavigationPage navPage)
            {
                await navPage.PushAsync(page);
                IsGestureEnabled = false;
            }
        }

        /// <summary>
        /// Pops a page and enables the master menu if we get to the root page.
        /// </summary>
        /// <returns></returns>
        public async Task PopAsync()
        {
            if (Detail is NavigationPage navPage)
            {
                await navPage.PopAsync();

                if (navPage.Navigation.NavigationStack.Count == 1)  //if count == 1 -> is root page
                    IsGestureEnabled = true;
            }
        }
        protected override void OnAppearing()
        {
            base.OnAppearing();
            IsGestureEnabled = true;
        }
    }

    /// <summary>
    /// Класс отображения синхронизации
    /// </summary>
    public class MainSyncActivity : Syncronization
    {

        /// <summary>
        /// Всплывающее окно
        /// </summary>
        MySyncPage _page;

        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="appType"></param>
        public MainSyncActivity(ApplicationType appType) : base(appType) { }

        /// <summary>
        /// Описание абстрактного метода начала синхронизации
        /// </summary>
        public override void Begin_Sync()
        {
            // Передаем true для получения фотографий в ИССО-S
            _page = new MySyncPage(MyLogin.Text, MyPassword.Text, true, true);
            _page.SyncSucceeded += delegate (object sender, EventArgs e) { Page_Sync_Succeeded(sender, e, _page.ElapsedMs); };
            _page.SyncFailed += delegate (object sender, EventArgs e) { Page_Sync_Failed(sender, e, _page.MessageError); };
            _page.SyncCancelled += Page_Sync_Cancelled;
            Navigation.PushPopupAsync(_page);
        }

        public override void Dispose() { }

        /// <inheritdoc />
        /// <summary>
        /// Создание сохраняемых настроек
        /// </summary>
        public override void InitializeSettings()
        {
            if (Application.Current.Properties.TryGetValue("username", out var username))
            {
                MyLogin.Text = Convert.ToString(username);
            }
            else
            {
                Application.Current.Properties.Add("username", "");
            }
            if (Application.Current.Properties.TryGetValue("password", out var password))
            {
                MyPassword.Text = Convert.ToString(password);
            }
            else
            {
                Application.Current.Properties.Add("password", "");
            }
        }

        public override void InitUserCollections() { }

        /// <summary>
        /// Обработчик отмены синхронизации
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public override void Page_Sync_Cancelled(object sender, EventArgs e)
        {
            _page.Ts.Cancel();
            //Navigation.PopPopupAsync();
        }

        public override void Page_Sync_Failed(object sender, EventArgs e, string message)
        {
            Device.BeginInvokeOnMainThread(async() => 
            {
                await Navigation.PopPopupAsync();
                var result =
	                $"Не удалось подключиться к удаленному серверу по адресу {Application.Current.Properties["address"]}. Обратитесь к заказчику работ.\n";
                if (!message.Equals(""))
                    result += $"Причина: ({message}).";
                await DisplayAlert(null, result, "OK");
            });
        }

        public override void Page_Sync_Succeeded(object sender, EventArgs e, long elapsedMs)
        {
            Device.BeginInvokeOnMainThread(async () => { await Navigation.PopPopupAsync(); await DisplayAlert(null, "Синхронизация прошла успешно", "OK"); });
        }

        public override async void SaveSettings()
        {
            Application.Current.Properties["username"] = MyLogin.Text;
            Application.Current.Properties["password"] = MyPassword.Text;
            await Application.Current.SavePropertiesAsync();
        }

	    public override void UploadUserCollectionViewCell_Tapped(object sender, EventArgs e) { }
    }

    public class MainMapsActivity : MapsActivity
    {
        public MainMapsActivity(HttpsIsso[] listOfIssos) : base(listOfIssos) { }

        public override void FarTapped(object sender, EventArgs e)
        {
	        if (HashMap == null || HashMap.GetIndex(1) == -1) return;
	        DisableGeolocator();
	        ToIssoView = true;
	        var issoViewActivity = new IssoViewActivity(ListOfIssos[HashMap.GetIndex(1)].CIsso);
	        Navigation.PushAsync(issoViewActivity);
        }

        public override void NearTapped(object sender, EventArgs e)
        {
	        if (HashMap == null || HashMap.GetIndex(0) == -1) return;
	        DisableGeolocator();
	        ToIssoView = true;
	        var issoViewActivity = new IssoViewActivity(ListOfIssos[HashMap.GetIndex(0)].CIsso);
	        Navigation.PushAsync(issoViewActivity);
        }

        public override void BehindTapped(object sender, EventArgs e)
        {
	        if (HashMap == null || HashMap.GetIndex(2) == -1) return;
	        DisableGeolocator();
	        ToIssoView = true;
	        var issoViewActivity = new IssoViewActivity(ListOfIssos[HashMap.GetIndex(2)].CIsso);
	        Navigation.PushAsync(issoViewActivity);
        }

        public override void ShowAlert(string str)
        {
            DependencyService.Get<IMessage>().ShortAlert(str);
        }
    }

    public class MainSettingsActivity : SettingsActivity
    {
	    public override void InitSettings()
        {
            if (Application.Current.Properties.TryGetValue("address", out var address))
            {
                MyEntryAddress.Text = Convert.ToString(address);
            }
            if (Application.Current.Properties.TryGetValue("port", out var port))
            {
                MyEntryPort.Text = Convert.ToString(port);
            }
            if (Application.Current.Properties.TryGetValue("support_port", out var support))
            {
                MyEntrySupport.Text = Convert.ToString(support);
            }
        }

        public override async void SaveSettings()
        {
            if (!MyEntryAddress.Text.Equals(""))
                Application.Current.Properties["address"] = MyEntryAddress.Text;
            if (!MyEntryPort.Text.Equals(""))
                Application.Current.Properties["port"] = Convert.ToInt32(MyEntryPort.Text);
            if (!MyEntrySupport.Text.Equals(""))
                Application.Current.Properties["support_port"] = Convert.ToInt32(MyEntrySupport.Text);
            await Application.Current.SavePropertiesAsync();
        }
    }

    public class MainPageLinearList : LinearList.LinearList
    {
	    public MainPageLinearList(ApplicationType app) : base(app) { }

        public override ObservableCollection<Isso> BindIssos()
        {
            var issos = new ObservableCollection<Isso>();
            var issosTable = App.CreateDatabase().Query<IssosList>("SELECT I_ISSO.C_ISSO, NAME, DORNAME, W_ISSO, coalesce(RATINGS, -1) as RATINGS_1, N_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                "left outer join RATING on (RATING.C_ISSO = I_ISSO.C_ISSO and RATING.RATINGDATE = (select MAX(RATINGDATE) from RATING where C_ISSO = I_ISSO.C_ISSO))" +
                (SelectedRoad.Equals("[Все]") ? "" : "where DORNAME = '" + SelectedRoad + "'" + " order by DORNAME, W_ISSO")).ToList();
            foreach (var isso in issosTable)
            {
                var info =
	                $"{isso.NAME} (ОТС: {isso.N_OTC_EXP}) \n{isso.DORNAME} км {isso.W_ISSO>> 16}+{isso.W_ISSO& 0xFFFF} ({isso.OBSTACLE})";
                issos.Add(new Isso(isso.C_ISSO, info, (Otc)isso.RATINGS_1, App.AppType));
            }
            return issos;
        }

        public override void CallIssoView(Isso isso)
        {
            var issoViewActivity = new IssoViewActivity(isso.CIsso);
            Navigation.PushAsync(issoViewActivity);
        }

	    public override void Filter_Clicked(object sender, EventArgs e) { }

	    //public async override void Filter_Clicked(object sender, EventArgs e)
        //{
        //    if (_tapped) return;
        //    _tapped = true;
        //    var result = ConnectionClass.CreateDatabase().Query<ISSOS_LIST>("select DISTINCT DORNAME from I_ISSO").ToList();
        //    ObservableCollection<ModelForSelect> dornames = new ObservableCollection<ModelForSelect>();
        //    foreach (ISSOS_LIST res in result)
        //    {
        //        dornames.Add(new ModelForSelect(res.DORNAME, res.DORNAME == selected_road));
        //    }
        //    dornames.Insert(0, new ModelForSelect("[Все]", "[Все]" == selected_road));
        //    var prev_road = selected_road;
        //    //selected_road = await DisplayActionSheet("Выберите дорогу:", "Отмена", null, dornames.ToArray());
        //    SelectedListViewPopupPage selectedListViewPopupPage = new SelectedListViewPopupPage(null, dornames, "Выберите дорогу:");
        //    selectedListViewPopupPage.SaveChanges += SelectedListViewPopupPage_SaveChanges;
        //    Navigation.PushPopupAsync(selectedListViewPopupPage);
        //    _tapped = false;
        //}

        //public override void OnMore(object sender, EventArgs e)
        //{
        //    var mi = ((MenuItem)sender);
        //    DisplayAlert("Подробная информация", "More Context Action", "OK");
        //}
    }
}
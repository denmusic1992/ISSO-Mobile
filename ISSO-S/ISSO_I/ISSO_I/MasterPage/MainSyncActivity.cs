using ISSO_I.Additional_Classes;
using ISSO_I.GeneratedCode;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ServiceModel;
using System.Threading.Tasks;
using CommonClassesLibrary;
using CommonClassesLibrary.PopupPages;
using Xamarin.Forms;

namespace ISSO_I.MasterPage
{
    public class MainSyncActivity : Syncronization
    {
        /// <summary>
        /// Всплывающее окно
        /// </summary>
        MySyncPage _page;
        /// <summary>
        /// 
        /// </summary>
        private const int SecondsWaitStartSession = 10;

        private DBHelper.ListCollection[] Collections { get; set; }

        private int SelectedCollection { get; set; }

        private GetUserCollectionsContentPage GetUserCollectionsContentPage { get; set; }

        public MainSyncActivity(ApplicationType appType) : base(appType) { }

        public override async void Begin_Sync()
        {
            if (Tapped) return;
            Tapped = true;
            // Здесь надо будет добавить класс по выбору выгрузки
            _page = new MySyncPage(MyLogin.Text, MyPassword.Text, MySwitchPhoto.IsToggled, MySwitchScheme.IsToggled, Collections[SelectedCollection]);
            _page.SyncSucceeded += delegate (object sender, EventArgs e) { Page_Sync_Succeeded(sender, e, _page.ElapsedMs); };
            _page.SyncFailed += delegate (object sender, EventArgs e) { Page_Sync_Failed(sender, e, _page.MessageError); };
            _page.SyncCancelled += Page_Sync_Cancelled;
            await Navigation.PushPopupAsync(_page);
            Tapped = false;
        }

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

        public override void InitUserCollections()
        {
			if (!MySyncTableView.Root.Contains(MyTableGetUserCollections)) MySyncTableView.Root.Insert(3, MyTableGetUserCollections);
			if (MySyncTableView.Root.Contains(MyTableUserCollections)) MySyncTableView.Root.Remove(MyTableUserCollections);
			MyConnectIndicator.IsVisible = true;
			MyConnectIndicator.IsRunning = true;
			MyLabelConnect.IsVisible = false;
			MySyncButton.IsEnabled = false;

			Task.Factory.StartNew(() =>
            {
                try
                {
                    System.Diagnostics.Stopwatch.StartNew();
                    // Адрес с общим интерфейсом
                    System.Net.ServicePointManager.ServerCertificateValidationCallback = delegate { return true; };
                    // Адрес с интерфейсом для ISSO-I
                    var addressIssoI =
	                    $"https://{Convert.ToString(Application.Current.Properties["address"])}:{Convert.ToString(Application.Current.Properties["port"])}/ais7UpdateServerSecureBinding/IssoI";
                    var endpointIssoI = new EndpointAddress(addressIssoI);
                    // Для подключения к сервису Isso-I
                    using (var clientIssoI = Device.RuntimePlatform == Device.Android ? new ISSO_I_ClientBase(MySyncPage.GetBinding(), endpointIssoI) :
                    new ISSO_I_ClientBaseIOS(MySyncPage.GetBinding(), endpointIssoI))
                    {
                        // Выставляем время на подключение
	                    if (clientIssoI.Endpoint.Binding != null)
		                    clientIssoI.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsWaitStartSession);
	                    Collections = clientIssoI.GetIssoIUserCollections(MyLogin.Text, MyPassword.Text);
                        if (Collections == null)
                        {
                            Device.BeginInvokeOnMainThread(() =>
                            {
                                MyLabelConnect.Text = "Не удалось получить список выгрузок ИССО. Проверьте логин/пароль, подключение к интернету или обратитесь к администратору сервера.";
                            });
                        }
                        if (Collections != null && Collections.Length > 0)
                        {
                            SelectedCollection = 0;
                            Device.BeginInvokeOnMainThread(() =>
                            {
                                MySyncTableView.Root.Remove(MyTableGetUserCollections);
                                MySyncTableView.Root.Insert(3, MyTableUserCollections);
                                MyConnectIndicator.IsVisible = false;
                                MyLabelConnect.IsVisible = false;
                                MySyncButton.IsEnabled = true;
                                MyLabelUserCollection.Text =
	                                $"Список: {Collections[SelectedCollection].CollectionName} от {new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(Collections[SelectedCollection].DateModify):dd.MM.yyyy}";
                                MyLabelCollectionAdvancedInfo.Text =
	                                $"Описание: {Collections[SelectedCollection].Description}\n Количество ИССО: {Collections[SelectedCollection].IssoList.Split(',').Length}";
                            });
                        }
                        else
                        {
                            Device.BeginInvokeOnMainThread(() =>
                            {
                                MyConnectIndicator.IsVisible = false;
                                MyLabelConnect.IsVisible = true;
                                MySyncButton.IsEnabled = true;
                                MyLabelConnect.Text = "Список выгрузок для Вашей учетной записи пуст. Настройте списки выгрузок или обратитесь к администратору сервера.";
                            });
                        }
                    }
                }
                catch
                {
                    Device.BeginInvokeOnMainThread(() =>
                    {
                        MyConnectIndicator.IsVisible = false;
                        MyLabelConnect.IsVisible = true;
                        MySyncButton.IsEnabled = true;
                        MyLabelConnect.Text = "Возникла ошибка при получении выгрузок ИССО. Проверьте, есть ли доступные Вам выгрузки и повторите попытку или обратитесь к администратору сервера.";
                    });
                }
            });
        }

        public override void Dispose()
        {
            MyConnectIndicator = null; MyLabelCollectionAdvancedInfo = null; MyLabelConnect = null; MyLabelUserCollection = null;
            MyLogin = null; MyPassword = null; MySwitchPhoto = null; MySwitchScheme = null;
            MySyncButton = null; MySyncTableView = null; MyTableGetUserCollections = null; MyTableUserCollections = null;

            BindingContext = null;
            Content = null;
            if(GetUserCollectionsContentPage != null)
            {
                GetUserCollectionsContentPage.ItemSelectedEvent -= GetUserCollectionsContentPage_Item_Selected_Event;
            }

            GC.Collect();

            //Navigation.RemovePage(Navigation.NavigationStack.LastOrDefault());
        }

        public override void Page_Sync_Cancelled(object sender, EventArgs e)
        {
            _page.Ts.Cancel();
            //Navigation.PopPopupAsync();
        }

        public override void Page_Sync_Failed(object sender, EventArgs e, string message)
        {
            Device.BeginInvokeOnMainThread(async () =>
            {
                try
                {
                    _page.Ts.Cancel();
                    await Navigation.PopPopupAsync();
                    var result = "Произошла внутрення ошибка при синхронизации. Обратитесь к администратору приложения в службу поддержки с полным описанием проблемы и на каком шаге произошла ошибка.\n";
                    if (!message.Equals(""))
                        result += $"Причина: ({message}).";
	                await Navigation.PushPopupAsync(new CommonPopupPage(new AlertPopupPage(true, result), ""));
                    //await DisplayAlert(null, result, "OK");
                }
                catch
                {
                    _page.Ts.Cancel();
                }
            });
        }

        public override void Page_Sync_Succeeded(object sender, EventArgs e, long elapsedMs)
        {
            Device.BeginInvokeOnMainThread(async () =>
            {
                try
                {
                    _page.Ts.Cancel();
                    await Navigation.PopPopupAsync();
	                await Navigation.PushPopupAsync(
		                new CommonPopupPage(new AlertPopupPage(true, "Синхронизация прошла успешно"), ""));
	                //await DisplayAlert(null, "Синхронизация прошла успешно", "OK");
                }
                catch
                {
                    _page.Ts.Cancel();
                }
            });
        }

        public override async void SaveSettings()
        {
            Application.Current.Properties["username"] = MyLogin.Text;
            Application.Current.Properties["password"] = MyPassword.Text;
            await Application.Current.SavePropertiesAsync();
        }

        /// <summary>
        /// При выборе выгрузки
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public override void UploadUserCollectionViewCell_Tapped(object sender, EventArgs e)
        {
            var usercollections = new List<UserCollections>();
            foreach (var usercollection in Collections)
            {
                usercollections.Add(UserCollections.Convert(usercollection));
            }
            GetUserCollectionsContentPage = new GetUserCollectionsContentPage(new ObservableCollection<UserCollections>(usercollections), SelectedCollection);
            GetUserCollectionsContentPage.ItemSelectedEvent += GetUserCollectionsContentPage_Item_Selected_Event;
            Navigation.PushAsync(GetUserCollectionsContentPage);
        }

        private void GetUserCollectionsContentPage_Item_Selected_Event(object sender, EventArgs e)
        {
            SelectedCollection = Convert.ToInt32(sender);
            MyLabelUserCollection.Text =
	            $"Список: {Collections[SelectedCollection].CollectionName} от {new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(Collections[SelectedCollection].DateModify):dd.MM.yyyy}";
            MyLabelCollectionAdvancedInfo.Text =
	            $"Описание: {Collections[SelectedCollection].Description}\n Количество ИССО: {Collections[SelectedCollection].IssoList.Split(',').Length}";
        }
    }
}

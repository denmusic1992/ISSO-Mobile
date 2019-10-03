using CommonClassesLibrary.CustomRenderers;
using System;
using CommonClassesLibrary.PopupPages;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace CommonClassesLibrary
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public abstract partial class Syncronization
    {
        /// <summary>
        /// 
        /// </summary>
        public ActivityIndicator MyConnectIndicator { get; protected set; }

        public Label MyLabelConnect { get; protected set; }
        /// <summary>
        ///  Экземпляр ввода логина
        /// </summary>
        public NoHelperEntry MyLogin { get; protected set; }

        /// <summary>
        ///  Экземпляр ввода пароля
        /// </summary>
        public NoHelperEntry MyPassword { get; protected set; }

        /// <summary>
        /// Отображает выбранную выгрузку
        /// </summary>
        public Label MyLabelUserCollection { get; protected set; }

        /// <summary>
        /// 
        /// </summary>
        public Label MyLabelCollectionAdvancedInfo { get; protected set; }

        /// <summary>
        /// Отображает Cell с выбором выгрузки
        /// </summary>
        public TableSection MyTableUserCollections { get; protected set; }

        /// <summary>
        /// Отображает Cell где отображается процесс получения выгрузок
        /// </summary>
        public TableSection MyTableGetUserCollections { get; protected set; }

        /// <summary>
        /// Переключатель фото
        /// </summary>
        public Switch MySwitchPhoto { get; protected set; }

        /// <summary>
        /// Кнопка синхронизации
        /// </summary>
        public Button MySyncButton { get; protected set; }

        /// <summary>
        /// 
        /// </summary>
        public TableView MySyncTableView {get; protected set;}

        /// <summary>
        /// 
        /// </summary>
        public Switch MySwitchScheme { get; protected set; }

        private ApplicationType AppType { get; }

        public bool Tapped { get; set; } = false;

	    protected Syncronization(ApplicationType appType)
        {
            InitializeComponent();
            MyLogin = Login;
            MyPassword = Password;
            MyConnectIndicator = Connect_Indicator;
            MyLabelConnect = Label_Connect;
            MySwitchPhoto = SwitchPhoto;
            MySwitchScheme = SwitchScheme;
            MyLabelUserCollection = label_userCollection;
            MySyncTableView = SyncTableView;
            MySyncButton = SyncButton;
            MyLabelCollectionAdvancedInfo = label_collection_advancedInfo;
            AppType = appType;
	        // ReSharper disable once VirtualMemberCallInConstructor
            InitializeSettings();
            // Убираем ненужные блоки
            switch (appType)
            {
                // Для ИССО-SX убираем блоки с доп настройками и с подключением для ИССО-IX
                case ApplicationType.IssoS:
                    SyncTableView.Root.Remove(AdvancedSettings);
                    SyncTableView.Root.Remove(TableUserCollections);
                    Connect_Indicator.IsVisible = false;
                    Connect_Indicator.IsRunning = false;
                    Label_Connect.IsVisible = false;
                    break;
                // Для ИССО-RX убираем только блок с подключением для ИССО-IX
                case ApplicationType.IssoR:
                    SyncTableView.Root.Remove(AdvancedSettings);
                    SyncTableView.Root.Remove(TableUserCollections);
                    Connect_Indicator.IsVisible = false;
                    Connect_Indicator.IsRunning = false;
                    Label_Connect.IsVisible = false;
                    break;
                // Для ИССО-IX только запоминаем, что у нас есть такой блок и потом используем его
                case ApplicationType.IssoI:
                    SyncButton.Text = "Получить выгрузки";
                    Connect_Indicator.IsVisible = false;
                    Connect_Indicator.IsRunning = false;
                    Label_Connect.IsVisible = false;
                    MyTableUserCollections = TableUserCollections;
                    MyTableGetUserCollections = TableGetUserCollections;
                    SyncTableView.Root.Remove(TableUserCollections);
                    SyncTableView.Root.Remove(TableGetUserCollections);
                    break;
	            default:
		            throw new ArgumentOutOfRangeException(nameof(appType), appType, null);
            }
        }

        public abstract void InitUserCollections();
        

        /// <summary>
        /// Ввод настроек, если таковые имеются
        /// </summary>
        public abstract void InitializeSettings();

        /// <summary>
        /// Сохранение настроек
        /// </summary>
        public abstract void SaveSettings();

        /// <summary>
        /// Метод инициализации окна с информацией по синхронизации
        /// </summary>
        public abstract void Begin_Sync();

        /// <summary>
        /// Обработчик нажатия кнопки синхронизации
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Sync_Click(object sender, EventArgs e)
        {
            SaveSettings();
            if (string.IsNullOrEmpty(Login.Text) || string.IsNullOrEmpty(Password.Text))
            {
	            var alertPopupPage = new AlertPopupPage(true, "Данные учетной записи не заполнены. Проверьте Логин и Пароль.");
	            Navigation.PushPopupAsync(new CommonPopupPage(alertPopupPage, "Ошибка синхронизации"));
	            //DisplayAlert("Ошибка", "Данные учетной записи не заполнены. Проверьте Логин и Пароль.", "Ок");
            }
            else
            {
                switch(AppType)
                {
                    case ApplicationType.IssoI:
                        InitUserCollections();
                        break;
                    default:
                        Begin_Sync();
                        break;
                }
            }
        }

	    /// <summary>
	    /// Обработчик ошибки при синхронизации
	    /// </summary>
	    /// <param name="sender"></param>
	    /// <param name="e"></param>
	    /// <param name="message"></param>
	    public abstract void Page_Sync_Failed(object sender, EventArgs e, string message);
        //{
        //    Navigation.PopPopupAsync();
        //    //page.Disappearing += (sender2, e2) =>
        //    {
        //        //String result = String.Format("Не удалось подключиться к удаленному серверу по адресу {0}. Обратитесь к заказчику работ", App.Current.Properties["address"]);
        //        //if (!message.Equals(""))
        //        //    result += String.Format(" Причина: {0}", message);
        //        //DisplayAlert(null, result, "OK");
        //    };
        //}

	    /// <summary>
	    /// Обработчик успешной синхронизации
	    /// </summary>
	    /// <param name="sender"></param>
	    /// <param name="e"></param>
	    /// <param name="elapsedMs"></param>
	    public abstract  void Page_Sync_Succeeded(object sender, EventArgs e, long elapsedMs);
        //{
        //    Navigation.PopPopupAsync();
        //    //page.Disappearing += (sender2, e2) =>
        //    //{
        //    //    DisplayAlert(null, "Синхронизация прошла успешно", "OK");
        //    //};
        //}

        public abstract void Page_Sync_Cancelled(object sender, EventArgs e);

        public abstract void UploadUserCollectionViewCell_Tapped(object sender, EventArgs e);

        public void MakeSyncUsingCollection(object sender, EventArgs e) { Begin_Sync(); }
    }
}
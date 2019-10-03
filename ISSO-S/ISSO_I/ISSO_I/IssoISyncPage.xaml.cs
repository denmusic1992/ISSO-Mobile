using Rg.Plugins.Popup.Extensions;
using System;
using System.Threading;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class IssoISyncPage : ContentPage
	{
        /// <summary>
        /// Логин пользователя
        /// </summary>
        private string Login;
        /// <summary>
        /// Пароль пользователя
        /// </summary>
        private string Password;
        /// <summary>
        /// Синхронизация успешная
        /// </summary>
        public event EventHandler Sync_Succeeded;
        /// <summary>
        /// Синхронизация с ошибкой
        /// </summary>
        public event EventHandler Sync_Failed;
        /// <summary>
        /// Успех при подключении
        /// </summary>
        private bool success = false;
        /// <summary>
        /// Переменная для остановки асинхронного процесса
        /// </summary>
        private Semaphore semaphore = new Semaphore(0, 15);
        /// <summary>
        /// Вывод ошибки, если таковая есть
        /// </summary>
        public string messageError = "";
        /// <summary>
        /// Переменная вывода функций
        /// </summary>
        private object output;
        /// <summary>
        /// Время ожидания до ошибки
        /// </summary>
        private const int wait_millis = 30000;
        private ListCollection[] collections;

        public IssoISyncPage (string Login, string Password, ListCollection[] collections)
		{
			InitializeComponent ();

            this.Login = Login;
            this.Password = Password;
            this.collections = collections;

            Btn_MoreInfo.Image = new FileImageSource() { File = String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "information_light.png") };
            foreach (ListCollection collection in collections)
            {
                Picker_Collections.Items.Add(collection.CollectionName);
            }
            Picker_Collections.Title = "Выберите доступную выгрузку:";
            Picker_Collections.SelectedIndexChanged += Picker_Collections_SelectedIndexChanged;
            Picker_Collections.SelectedIndex = 0;
            Advanced_Info_Label.Text = collections[Picker_Collections.SelectedIndex].Description;
        }

        private void Picker_Collections_SelectedIndexChanged(object sender, EventArgs e)
        {
            Advanced_Info_Label.Text = collections[Picker_Collections.SelectedIndex].Description;
        }

        private void Btn_MoreInfo_Clicked(object sender, EventArgs e)
        {

        }

        private void SyncButton_Clicked(object sender, EventArgs e)
        {
            Navigation.PopPopupAsync();
            SyncPopupPage page = new SyncPopupPage(Login, Password, collections[Picker_Collections.SelectedIndex].IssoList, SwitchPhoto.IsToggled, SwitchScheme.IsToggled);
            page.Sync_Succeeded += Sync_Succeeded;
            page.Sync_Failed += Sync_Failed;

            Navigation.PushPopupAsync(page);
        }
    }
}
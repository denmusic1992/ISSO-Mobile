using Rg.Plugins.Popup.Services;
using System;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace CommonClassesLibrary
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public abstract partial class SyncPage
	{
        /// <summary>
        /// Экземпляр для выводимой информации
        /// </summary>
        public Label MyLabelInfoSync;
        /// <summary>
        /// 
        /// </summary>
        public string Login;
        /// <summary>
        /// 
        /// </summary>
        public string Password;
        /// <summary>
        /// 
        /// </summary>
        public bool PhotosEnable;
        /// <summary>
        /// 
        /// </summary>
        public bool SchemesEnable;
        /// <summary>
        /// Синхронизация успешная
        /// </summary>
        public event EventHandler SyncSucceeded;
        /// <summary>
        /// Синхронизация с ошибкой
        /// </summary>
        public event EventHandler SyncFailed;
        /// <summary>
        /// Отмена синхронизации
        /// </summary>
        public event EventHandler SyncCancelled;

        /// <summary>
        /// Инициализация класса
        /// </summary>
        protected SyncPage (string login, string password, bool photosEnable, bool schemesEnable)
		{
			InitializeComponent();
            Login = login;
            Password = password;
            MyLabelInfoSync = LabelInfoSync;
            PhotosEnable = photosEnable;
            SchemesEnable = schemesEnable;
			// ReSharper disable once VirtualMemberCallInConstructor
            Sync();
		}
        

        /// <summary>
        /// Абстрактный метод синхронизации
        /// </summary>
        /// <returns></returns>
        public abstract int Sync();

        /// <summary>
        /// Абстрактный метод закрытия синхронизации
        /// </summary>
        public abstract void CloseSync();

        /// <summary>
        /// Если синхронизация была выполнена
        /// </summary>
        public void OnSyncSucceeded()
        {
            SyncSucceeded?.Invoke(this, EventArgs.Empty);
        }

        /// <summary>
        /// Если произошла ошибка
        /// </summary>
        public void OnSyncFailed()
        {
            SyncFailed?.Invoke(this, EventArgs.Empty);
        }

        public void OnSyncCancelled()
        {
            SyncCancelled?.Invoke(this, EventArgs.Empty);
        }

        /// <summary>
        /// Закрытие
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private async void OnClose(object sender, EventArgs e)
        {
            await PopupNavigation.Instance.PopAsync();
            CloseSync();
        }

        protected override bool OnBackButtonPressed()
        {
            return true;
        }

        protected override Task OnAppearingAnimationEndAsync()
        {
            return Content.FadeTo(1);
        }

        protected override Task OnDisappearingAnimationBeginAsync()
        {
            return Content.FadeTo(1);
        }
    }
}
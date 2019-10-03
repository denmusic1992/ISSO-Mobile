using Rg.Plugins.Popup.Extensions;
using System;
using System.ServiceModel;
using System.Threading;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Syncronization : ContentPage
    {
        /// <summary>
        /// Всплывающее окно
        /// </summary>
        IssoISyncPage page;
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

        public Syncronization()
        {
            InitializeComponent();
            if (App.Current.Properties.TryGetValue("username", out object username))
            {
                Login.Text = Convert.ToString(username);
            }
            else
            {
                App.Current.Properties.Add("username", "");
            }
            if (App.Current.Properties.TryGetValue("password", out object password))
            {
                Password.Text = Convert.ToString(password);
            }
            else
            {
                App.Current.Properties.Add("password", "");
            }
        }

        /// <summary>
        /// Обработчик нажатия кнопки синхронизации
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Sync_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(Login.Text) || string.IsNullOrEmpty(Password.Text))
            {
                DisplayAlert("Ошибка", "Данные учетной записи были введены некорректно", "Ок");
            }
            else
            {
                Begin_Sync();
            }
        }

        /// <summary>
        /// Метод инициализации окна с информацией по синхронизации
        /// </summary>
        public async void Begin_Sync()
        {
            // Сохраняем настройки
            App.Current.Properties["username"] = Login.Text;
            App.Current.Properties["password"] = Password.Text;
            await App.Current.SavePropertiesAsync();
            Connect_Database();
        }

        /// <summary>
        /// Обработчик ошибки при синхронизации
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Page_Sync_Failed(object sender, EventArgs e, string message)
        {
            Navigation.PopPopupAsync();
            //page.Disappearing += (sender2, e2) =>
            //{
            //    String result = String.Format("Не удалось подключиться к удаленному серверу по адресу {0}. Обратитесь к заказчику работ", App.Current.Properties["address"]);
            //    if (!message.Equals(""))
            //        result += String.Format(" Причина: {0}", message);
            //    DisplayAlert(null, result, "OK");
            //};
        }
        #region useless
        public void Connect_Database()
        {
            ListCollection[] collections = new ListCollection[0];
            Task.Run(() =>
            {
                //Device.BeginInvokeOnMainThread(() =>
                //{
                //    Connect_Indicator.IsVisible = true;
                //    Connect_Indicator.IsRunning = true;
                //    Label_Connect.IsVisible = true;
                //});
                //Thread.Sleep(1000);
                //// Адрес с общим интерфейсом
                //System.Net.ServicePointManager.ServerCertificateValidationCallback = new System.Net.Security.RemoteCertificateValidationCallback(delegate { return true; });
                //String address_Isso_I = String.Format("https://{0}:{1}{2}", Convert.ToString(App.Current.Properties["address"]), Convert.ToString(App.Current.Properties["port"]), "/ais7UpdateServerSecureBinding/IssoI");
                //EndpointAddress endpoint_common = new EndpointAddress(address_Isso_I);
                //Ais7MobileIssoIServerCoreXamarinClient client_I = new Ais7MobileIssoIServerCoreXamarinClient(BindingExt.GetBinding(), endpoint_common);

                //client_I.GetIssoIUserCollectionsCompleted += Client_I_GetIssoIUserCollectionsCompleted;

                //client_I.GetIssoIUserCollectionsAsync(Login.Text, Password.Text);
                //success = false;
                //semaphore.WaitOne(wait_millis);
                //Device.BeginInvokeOnMainThread(() =>
                //{
                //    Connect_Indicator.IsVisible = false;
                //    Connect_Indicator.IsRunning = false;
                //    Label_Connect.IsVisible = false;
                //});
                //if (!success)
                //{
                //    Device.BeginInvokeOnMainThread(() => { DisplayAlert(null, messageError, "OK"); });
                //}
                //else
                //{
                //    Device.BeginInvokeOnMainThread(() =>
                //    {
                //        collections = (ListCollection[])output;
                //        // Вызываем метод синхронизации
                //        page = new IssoISyncPage(Login.Text.ToString(), Password.Text.ToString(), collections);
                //        page.Sync_Succeeded += Page_Sync_Succeeded;
                //        page.Sync_Failed += delegate (object sender, EventArgs e) { Page_Sync_Failed(sender, e, page.messageError); };
                //        //Navigation.PushPopupAsync(page);
                //    });
                //}
            });
        }

        //public Task<List<UserCollection>> Connect_To_DB()
        //{

        //    //Task<UserCollection[]> task = new Task<UserCollection[]>();
        //    return Task.Factory.StartNew(() => { return userCollections.ToList(); });
        //}

        //private void Client_I_GetIssoIUserCollectionsCompleted(object sender, GetIssoIUserCollectionsCompletedEventArgs e)
        //{
        //    if (e.Error != null || e.Result == null)
        //    {
        //        messageError = "Не удалось получить список выгрузок для данного пользователя.";
        //        success = false;
        //    }
        //    else if (e.Cancelled)
        //    {
        //        messageError = "Request was cancelled.";
        //        success = false;
        //    }
        //    else
        //    {
        //        output = e.Result;
        //        success = true;
        //    }
        //    semaphore.Release();
        //}

        /// <summary>
        /// Обработчик успеха при синхронизации
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Page_Sync_Succeeded(object sender, EventArgs e)
        {
            Navigation.PopPopupAsync();
            page.Disappearing += (sender2, e2) =>
            {
                DisplayAlert(null, "Синхронизация прошла успешно", "OK");
            };
        }
#endregion
    }

    public class BindingExt
    {
        public static BasicHttpsBinding GetBinding()
        {
            // Инициализация безопасного биндинга
            BasicHttpsBinding basicBinding = new BasicHttpsBinding(BasicHttpsSecurityMode.Transport)
            {
                //basicBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.None;

                //basicBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.None;

                // Снятие ограничений по отправке данных
                MaxReceivedMessageSize = Int32.MaxValue
            };
            basicBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.None;
            basicBinding.ReaderQuotas.MaxStringContentLength = Int32.MaxValue;
            basicBinding.ReceiveTimeout = TimeSpan.FromMinutes(10);
            basicBinding.SendTimeout = TimeSpan.FromMinutes(10);
            return basicBinding;
        }
    }
}
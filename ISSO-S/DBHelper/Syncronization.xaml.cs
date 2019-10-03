using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static DBHelper.CommonClasses;

namespace CommonStaffLibrary
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
    public abstract partial class Syncronization : ContentPage
    {
        /// <summary>
        /// Всплывающее окно
        /// </summary>
        SyncPopupPage page;

        /// <summary>
        /// Блок подключения для ИССО-IX
        /// </summary>
        private TableSection Connect_Table_Section;

        public Syncronization(ApplicationType AppType)
        {
            InitializeComponent();
            // Убираем ненужные блоки
            switch(AppType)
            {
                // Для ИССО-SX убираем блоки с доп настройками и с подключением для ИССО-IX
                case ApplicationType.ISSO_S:
                    SyncTableView.Root.Remove(AdvancedSettings);
                    SyncTableView.Root.Remove(AccessToDBSection);
                    break;
                // Для ИССО-RX убираем только блок с подключением для ИССО-IX
                case ApplicationType.ISSO_R:
                    SyncTableView.Root.Remove(AccessToDBSection);
                    break;
                // Для ИССО-IX только запоминаем, что у нас есть такой блок и потом используем его
                case ApplicationType.ISSO_I:
                    TableSection Connect_Table_Section = SyncTableView.Root.ElementAt<TableSection>(SyncTableView.Root.IndexOf(AccessToDBSection));
                    SyncTableView.Root.Remove(AccessToDBSection);
                    break;
            }
        }

        /// <summary>
        /// Ввод настроек, если таковые имеются
        /// </summary>
        public abstract void InitializeSettings();
        //if(App.Current.Properties.TryGetValue("username", out object username))
        //    {
        //        Login.Text = Convert.ToString(username);
        //    }
        //    else
        //    {
        //        App.Current.Properties.Add("username", "");
        //    }
        //    if (App.Current.Properties.TryGetValue("password", out object password))
        //    {
        //        Password.Text = Convert.ToString(password);
        //    }
        //    else
        //    {
        //        App.Current.Properties.Add("password", "");
        //    }

        /// <summary>
        /// Сохранение настроек
        /// </summary>
        public abstract void SaveSettings();
        //// Сохраняем настройки
        //App.Current.Properties["username"] = Login.Text;
        //    App.Current.Properties["password"] = Password.Text;
        //    App.Current.SavePropertiesAsync();

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
        public void Begin_Sync()
        {
            
            // Вызываем метод синхронизации
            page = new SyncPopupPage(Login.Text.ToString(), Password.Text.ToString(), "", false, false);
            page.Sync_Succeeded += Page_Sync_Succeeded;
            page.Sync_Failed += delegate(object sender, EventArgs e) { Page_Sync_Failed(sender, e, page.messageError); };
            Navigation.PushPopupAsync(page);
        }

        /// <summary>
        /// Обработчик ошибки при синхронизации
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Page_Sync_Failed(object sender, EventArgs e, string message)
        {
            Navigation.PopPopupAsync();
            page.Disappearing += (sender2, e2) =>
            {
                //String result = String.Format("Не удалось подключиться к удаленному серверу по адресу {0}. Обратитесь к заказчику работ", App.Current.Properties["address"]);
                //if (!message.Equals(""))
                //    result += String.Format(" Причина: {0}", message);
                //DisplayAlert(null, result, "OK");
            };
        }

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
    }
}
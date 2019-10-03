using Rg.Plugins.Popup.Services;
using System;
using System.ServiceModel;
using System.Threading;
using System.Threading.Tasks;
using Xamarin.Forms.Xaml;

namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class SyncPopupPage
    {
        /// <summary>
        /// Переменная для остановки асинхронного процесса
        /// </summary>
        private readonly Semaphore _semaphore = new Semaphore(0, 15);

	    /// <summary>
        /// Синхронизация успешная
        /// </summary>
        public event EventHandler SyncSucceeded;
        /// <summary>
        /// Синхронизация с ошибкой
        /// </summary>
        public event EventHandler SyncFailed;
        /// <summary>
        /// Вывод ошибки, если таковая есть
        /// </summary>
        public string MessageError = "";

	    #region useless

	    /// <summary>
	    /// Конструктор всплывающего окна
	    /// </summary>
	    /// <param name="login"></param>
	    /// <param name="password"></param>
	    /// <param name="issoList"></param>
	    /// <param name="downloadPhoto"></param>
	    /// <param name="downloadScheme"></param>
	    public SyncPopupPage(string login, string password, string issoList, bool downloadPhoto, bool downloadScheme)
        {
            InitializeComponent();
            Sync(login, password, issoList, downloadPhoto, downloadScheme);
        }

        /// <summary>
        /// Синхронизация
        /// </summary>
        /// <returns></returns>
        public int Sync(string login, string password, string issoList, bool downloadPhoto, bool downloadScheme)
        {
            Task.Run(() =>
            {
                // Адрес с общим интерфейсом
                ////Thread.Sleep(1000);
                ////System.Net.ServicePointManager.ServerCertificateValidationCallback = new System.Net.Security.RemoteCertificateValidationCallback(delegate { return true; });
                ////String address_common = String.Format("https://{0}:{1}{2}", Convert.ToString(App.Current.Properties["address"]), Convert.ToString(App.Current.Properties["port"]), "/ais7UpdateServerSecureBinding/Common");
                ////EndpointAddress endpoint_common = new EndpointAddress(address_common);
                //////ChannelFactory<Iais7MobileCommonServerCore> factory_common = new ChannelFactory<Iais7MobileCommonServerCore>(GetBinding(), endpoint_common);
                ////// Адрес с интерфейсом для ISSO-I
                ////String address_isso_i = String.Format("https://{0}:{1}{2}", Convert.ToString(App.Current.Properties["address"]), Convert.ToString(App.Current.Properties["port"]), "/ais7UpdateServerSecureBinding/IssoI");
                ////EndpointAddress endpoint_isso_i = new EndpointAddress(address_isso_i);

                ////// Инициализация клиентов для подключения к серверу
                ////Ais7MobileCommonServerCoreXamarinClient client_common = new Ais7MobileCommonServerCoreXamarinClient(GetBinding(), endpoint_common);
                ////Ais7MobileIssoIServerCoreXamarinClient client_isso_i = new Ais7MobileIssoIServerCoreXamarinClient(GetBinding(), endpoint_isso_i);

                ////// Создание event handler'ов
                ////client_isso_i.HttpsGetSessionIdForIssoICompleted += Client_isso_i_HttpsGetSessionIdForIssoICompleted;
                ////client_common.HttpsGetIssoListCompleted += Client_common_HttpsGetIssoListCompleted;
                ////client_isso_i.HttpsGetMeanInfoForIssosCompleted += Client_isso_i_HttpsGetMeanInfoForIssosCompleted;
                ////client_isso_i.HttpsGetAdvancedInfoForIssosCompleted += Client_isso_i_HttpsGetAdvancedInfoForIssosCompleted;
                ////client_isso_i.HttpsReceiveSQLQueryCompleted += Client_isso_i_HttpsReceiveSQLQueryCompleted;
                ////client_isso_i.compareCountOfRowsCompleted += Client_isso_i_compareCountOfRowsCompleted;
                ////client_isso_i.HttpsReceiveAllDatabaseCompleted += Client_isso_i_HttpsReceiveAllDatabaseCompleted;
                ////client_isso_i.getPrimaryKeysValuesCompleted += Client_isso_i_getPrimaryKeysValuesCompleted;
                ////client_isso_i.synchronyzeTablesCompleted += Client_isso_i_synchronyzeTablesCompleted;
                ////client_common.HttpsCloseSessionCompleted += Client_common_HttpsCloseSessionCompleted;

                /////// ******************************
                ////// Количество этапов в синхронизации
                ////var count_of_steps = 5 + (Download_Photo ? 1 : 0) + (Download_Scheme ? 1 : 0);

                ////client_isso_i.HttpsGetSessionIdForIssoIAsync(Login, Password, IssoList);
                ////Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = String.Format("Шаг 1 из {0}. Получение разрешения...", count_of_steps); });
                ////Thread.Sleep(1000);
                ////success = false;
                ////semaphore.WaitOne(wait_millis);
                ////if (!success)
                ////{
                ////    OnSyncFailed();
                ////    return;
                ////}
                ////String[] result = (String[])output;
                ////// Получение разрешения и id со списком ИССО
                ////Guid id = new Guid(result[0].ToString());
                ////String nameCurator = result[1].ToString();
                ////int step = Convert.ToInt32(result[2].ToString());
                ////if (!App.Current.Properties.TryGetValue("user", out object user))
                ////{
                ////    App.Current.Properties.Add("user", "");
                ////}
                ////App.Current.Properties["user"] = nameCurator;
                ////App.Current.SavePropertiesAsync();

                ////// Подключение к БД
                ////var connection = App.CreateDatabase();

                ////Device.BeginInvokeOnMainThread(() => {
                ////    LabelInfoSync.Text = String.Format("Шаг 2 из {0}. Получение ИССО...", count_of_steps);
                ////    ((App.Current.MainPage as MasterDetailPage1).Master as MasterDetailPage1Master).SetTextForUser(nameCurator);
                ////});
                ////Thread.Sleep(500);
                ////// Получение самих ИССО
                ////client_common.HttpsGetIssoListAsync(id);
                ////success = false;
                ////semaphore.WaitOne(wait_millis);
                ////if (!success)
                ////{
                ////    OnSyncFailed();
                ////    return;
                ////}
                ////// Удаление предыдущих записей
                ////connection.Execute("delete from I_ISSO");
                ////// Получение результата по ИССО
                ////HttpsIsso[] issos = (HttpsIsso[])output;
                ////// Список ИССО
                ////var cIssos = new List<int>();
                ////// Список типов ИССО
                ////var cTypeIsso = new List<int>();
                ////int i = 1;
                ////foreach (HttpsIsso isso in issos)
                ////{
                ////    Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = "Получение ИССО...\n[" + i + " из " + issos.Count() + "]"; i++; });
                ////    I_ISSO i_isso = new I_ISSO(isso);
                ////    // Добавление ИССО в БД
                ////    connection.InsertOrReplace(i_isso);
                ////    cIssos.Add(i_isso.C_ISSO);
                ////    cTypeIsso.Add(i_isso.CTYPEISSO);
                ////}

                ////// Получение инфы по таблицам
                /////// Сначала мы получаем данные по тому, какие таблицы отображаются для определенного типа ИССО
                ////connection.Execute("delete from TABLE_NAMES");
                ////client_isso_i.HttpsGetMeanInfoForIssosAsync(id, cIssos.ToArray(), cTypeIsso.ToArray());
                ////success = false;
                ////semaphore.WaitOne(wait_millis);
                ////if (!success)
                ////{
                ////    OnSyncFailed();
                ////    return;
                ////}
                ////Device.BeginInvokeOnMainThread(() =>
                ////{
                ////    LabelInfoSync.Text = String.Format("Шаг 3 из {0}. Получение дополнительных сведений по ИССО... (Имена)", count_of_steps);

                ////});
                ////var meanInfo = (MeanInfo)output;
                ////connection.BeginTransaction();
                ////for (i = 0; i < meanInfo.tableNames.Length; i += 10)
                ////{
                ////    int j = 0;
                ////    var sqlRequest = new StringBuilder();
                ////    while (j < 10 && j + i < meanInfo.tableNames.Length)
                ////    {
                ////        HttpsTableNames names = meanInfo.tableNames[i + j];
                ////        sqlRequest.Append("('").Append(names.C_GR_CONSTR).Append("','").Append(names.TableSysName).Append("','").Append(names.TableName).
                ////        Append("','").Append(names.TableParentID).Append("','").Append(names.TableView).Append("'),");
                ////        j++;
                ////    }
                ////    string sql = sqlRequest.ToString(0, sqlRequest.Length - 1);
                ////    connection.Execute("insert into TABLE_NAMES (C_GR_CONSTR, SYS_NAME, DESCRIPTION, PARENT_ID, PARENT_VIEW) values ?", sql);
                ////}
                ////connection.Commit();

                /////// Теперь мы записываем данные, как будем соотносить таблицы к типам ИССО
                ////connection.Execute("delete from TABLE_DELEGATES");
                ////connection.BeginTransaction();
                ////for (i = 0; i < meanInfo.tableDelegate.Length; i += 10)
                ////{
                ////    int j = 0;
                ////    var sqlRequest = new StringBuilder();
                ////    while (j < 10 && i + j < meanInfo.tableDelegate.Length)
                ////    {
                ////        var Delegate = meanInfo.tableDelegate[i + j];
                ////        sqlRequest.Append("(,").Append(Delegate.C_TYPEISSO).Append("','").Append(Delegate.C_GR_CONSTR).Append("),");
                ////        j++;
                ////    }
                ////    string sql = sqlRequest.ToString(0, sqlRequest.Length - 1);
                ////    connection.Execute("insert into TABLE_DELEGATES (ISSO_TYPE, C_GR_CONSTR) values ?", sql);
                ////}
                ////connection.Commit();

                ////// Получение этих таблиц
                ////List<string> table_names = new List<string>();
                ////foreach (HttpsTableNames names in meanInfo.tableNames)
                ////{
                ////    table_names.Add(names.TableSysName);
                ////}
                ////client_isso_i.HttpsReceiveSQLQueryAsync(id, table_names.ToArray());
                ////success = false;
                ////semaphore.WaitOne(wait_millis);
                ////if (!success)
                ////{
                ////    OnSyncFailed();
                ////    return;
                ////}
                ////var sql_requests = (string[])output;
                ////foreach (string sql in sql_requests)
                ////{
                ////    try
                ////    {
                ////        connection.BeginTransaction();
                ////        connection.Execute(sql);
                ////        connection.Commit();
                ////    }
                ////    catch (SQLite.SQLiteException ex)
                ////    {
                ////        throw ex;
                ////    }
                //}
            });
            return 0;
        }

        //private void Client_common_HttpsCloseSessionCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e)
        //{
        //    throw new NotImplementedException();
        //}

        //private void Client_isso_i_synchronyzeTablesCompleted(object sender, synchronyzeTablesCompletedEventArgs e)
        //{
        //    throw new NotImplementedException();
        //}

        //private void Client_isso_i_getPrimaryKeysValuesCompleted(object sender, getPrimaryKeysValuesCompletedEventArgs e)
        //{
        //    throw new NotImplementedException();
        //}

        //private void Client_isso_i_HttpsReceiveAllDatabaseCompleted(object sender, HttpsReceiveAllDatabaseCompletedEventArgs e)
        //{
        //    throw new NotImplementedException();
        //}

        //private void Client_isso_i_compareCountOfRowsCompleted(object sender, compareCountOfRowsCompletedEventArgs e)
        //{
        //    throw new NotImplementedException();
        //}

        //private void Client_isso_i_HttpsReceiveSQLQueryCompleted(object sender, HttpsReceiveSQLQueryCompletedEventArgs e)
        //{
        //    if (e.Error != null)
        //    {
        //        msg = e.Error.Message;
        //        success = false;
        //    }
        //    else if (e.Cancelled)
        //    {
        //        msg = "Request was cancelled.";
        //        success = false;
        //    }
        //    else
        //    {
        //        output = e.Result;
        //        success = true;
        //    }
        //    semaphore.Release();
        //}

        //private void Client_isso_i_HttpsGetAdvancedInfoForIssosCompleted(object sender, HttpsGetAdvancedInfoForIssosCompletedEventArgs e)
        //{
        //    throw new NotImplementedException();
        //}

        //private void Client_isso_i_HttpsGetMeanInfoForIssosCompleted(object sender, HttpsGetMeanInfoForIssosCompletedEventArgs e)
        //{
        //    if (e.Error != null)
        //    {
        //        msg = e.Error.Message;
        //        success = false;
        //    }
        //    else if (e.Cancelled)
        //    {
        //        msg = "Request was cancelled.";
        //        success = false;
        //    }
        //    else
        //    {
        //        output = e.Result;
        //        success = true;
        //    }
        //    semaphore.Release();
        //}

        //private void Client_common_HttpsGetIssoListCompleted(object sender, HttpsGetIssoListCompletedEventArgs e)
        //{
        //    if (e.Error != null)
        //    {
        //        msg = e.Error.Message;
        //        success = false;
        //    }
        //    else if (e.Cancelled)
        //    {
        //        msg = "Request was cancelled.";
        //        success = false;
        //    }
        //    else
        //    {
        //        output = e.Result;
        //        success = true;
        //    }
        //    semaphore.Release();
        //}

        //private void Client_isso_i_HttpsGetSessionIdForIssoICompleted(object sender, HttpsGetSessionIdForIssoICompletedEventArgs e)
        //{
        //    if (e.Error != null)
        //    {
        //        msg = e.Error.Message;
        //        success = false;
        //    }
        //    else if (e.Cancelled)
        //    {
        //        msg = "Request was cancelled.";
        //        success = false;
        //    }
        //    else
        //    {
        //        output = e.Result;
        //        success = true;
        //    }
        //    semaphore.Release();
        //}

        private void OnSyncSucceeded()
        {
            SyncSucceeded?.Invoke(this, EventArgs.Empty);
        }

        private void OnSyncFailed()
        {
            SyncFailed?.Invoke(this, EventArgs.Empty);
        }

        private void OnClose(object sender, EventArgs e)
        {
            MessageError = "";
            PopupNavigation.Instance.PopAsync();
            _semaphore.Release();
        }

        //protected override Task OnAppearingAnimationEndAsync()
        //{
        //    Content.FadeTo(1);
        //    return base.OnAppearingAnimationEndAsync();
        //}

        //protected override Task OnDisappearingAnimationEndAsync()
        //{
        //    Content.FadeTo(1);
        //    return base.OnDisappearingAnimationEndAsync();
        //}

        //protected override Task OnAppearingAnimationEnd()
        //{
        //    return Content.FadeTo(1);
        //}

        //protected override Task OnDisappearingAnimationBegin()
        //{
        //    return Content.FadeTo(1);
        //}
        #endregion

        public static BasicHttpsBinding GetBinding()
        {
            // Инициализация безопасного биндинга
            var basicBinding = new BasicHttpsBinding(BasicHttpsSecurityMode.Transport)
            {
                //basicBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.None;

                //basicBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.None;

                // Снятие ограничений по отправке данных
                MaxReceivedMessageSize = int.MaxValue
            };
            basicBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.None;
            basicBinding.ReaderQuotas.MaxStringContentLength = int.MaxValue;
            basicBinding.ReceiveTimeout = TimeSpan.FromMinutes(10);
            basicBinding.SendTimeout = TimeSpan.FromMinutes(10);
            return basicBinding;
        }
    }
}
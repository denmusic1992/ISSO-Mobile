using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using ISSO_I.GeneratedCode;
using System;
using System.Collections.Generic;
using System.Data;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.ServiceModel;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using ISSO_I.Generated;
using Xamarin.Forms;

namespace ISSO_I
{
	internal class MySyncPage : SyncPage
    {
	    /// <summary>
	    /// Вывод ошибки, если таковая есть
	    /// </summary>
	    public string MessageError { get; set; } = "";

	    public long ElapsedMs;

	    /// <summary>
        /// Количество шагов в синхронизации
        /// </summary>
        private const int CountOfSteps = 5;

        /// <summary>
        /// Токен, отвечающий за завершение треды
        /// </summary>
        public CancellationTokenSource Ts { get; set; } = new CancellationTokenSource();

        /// <summary>
        /// 
        /// </summary>
        private const int SecondsWaitStartSession = 10;
        /// <summary>
        /// 
        /// </summary>
        private const int SecondsWait = 999;

        /// <summary>
        /// 
        /// </summary>
        private DBHelper.ListCollection Collection { get; }

        /// <summary>
        /// Список типов, встречающихся в приложении
        /// </summary>
        private readonly Dictionary<Type, string> _types = new Dictionary<Type, string>
        {
            {typeof(short), "smallint" },
            {typeof(int), "int" },
            {typeof(long), "bigint" },
            {typeof(string), "string" },
            {typeof(float), "float" },
            {typeof(double), "float" },
            {typeof(DateTime), "datetime" },
            {typeof(byte[]), "byte" },
            {typeof(bool), "boolean" },
            {typeof(Guid), "string" },
            {typeof(TimeSpan), "timespan" }
        };

        /// <summary>
        /// Список справочных таблиц
        /// </summary>
        private List<string> STables { get; } = new List<string>()
        {
            "S_DEFECT",
            "S_CNCT",
            "S_REM",
            "S_UNIT_DIMENSION",
            "S_GR_CONSTR",
            "S_DEFPARAM",
            "S_DEFPARAMVALUE",
            "S_DEFPARAMCATEGORY",
            "S_DEF4CONSTR",
            "S_TYPISSO",
            "SYS_FILTER",
            "S_GR_SORG",
            "S_GRPR",
			"S_REM_CONSTR",
			"S_REM_DB",
			"S_REM_AD_TYPE",
			"S_WHE"
        };

        /// <summary>
        /// Список таблиц для дефектов
        /// </summary>
        private List<string> DefectTables { get; } = new List<string>()
        {
            //"i_defect", уже есть в tableNames
            "I_DEF_MOD",
            "I_DEF_DESCR",
            "I_FOTO_DEF",
            "V_GR_CONSTR_DEF",
            "V_GR_CONSTR_DEF2"
        };

        public MySyncPage(string login, string password, bool photosEnable, bool schemesEnable, DBHelper.ListCollection listCollection) : 
            base(login, password, photosEnable, schemesEnable)
        {
            Collection = listCollection;
        }

        public override void CloseSync()
        {
            MessageError = "";
            OnSyncCancelled();
        }

        public override int Sync()
        {
            var ct = Ts.Token;
            Task.Factory.StartNew(() =>
            {
	            // Подключение к БД
	            var connection = ConnectionClass.CreateDatabase();
	            try
	            {
		            var watch = System.Diagnostics.Stopwatch.StartNew();
		            // Адрес с общим интерфейсом
		            System.Net.ServicePointManager.ServerCertificateValidationCallback = delegate { return true; };
		            var addressCommon =
			            $"https://{Convert.ToString(Application.Current.Properties["address"])}:{Convert.ToString(Application.Current.Properties["port"])}/ais7UpdateServerSecureBinding/Common";
		            var endpointCommon = new EndpointAddress(addressCommon);
		            //ChannelFactory<Iais7MobileCommonServerCore> factory_common = new ChannelFactory<Iais7MobileCommonServerCore>(GetBinding(), endpoint_common);
		            // Адрес с интерфейсом для ISSO-I
		            var addressIssoI =
			            $"https://{Convert.ToString(Application.Current.Properties["address"])}:{Convert.ToString(Application.Current.Properties["port"])}/ais7UpdateServerSecureBinding/IssoI";
		            var endpointIssoI = new EndpointAddress(addressIssoI);

		            // Инициализация клиентов для подключения к серверу
		            //Ais7MobileCommonServerCoreXamarinClient client_common = new Ais7MobileCommonServerCoreXamarinClient(GetBinding(), endpoint_common);
		            //Ais7MobileIssoIServerCoreXamarinClient client_isso_i = new Ais7MobileIssoIServerCoreXamarinClient(GetBinding(), endpoint_isso_i);

		            //MyCommonXamarinClient my_client_common = new MyCommonXamarinClient(GetBinding(), endpoint_common);
		            //MyIssoIXamarinClient my_client_i = new MyIssoIXamarinClient(GetBinding(), endpoint_isso_i);

		            // Создание event handler'ов
		            //client_isso_i.GetIssoIUserCollectionsCompleted += Client_isso_i_GetIssoIUserCollectionsCompleted;
		            //client_isso_i.HttpsGetSessionIdForIssoICompleted += Client_isso_i_HttpsGetSessionIdForIssoICompleted;
		            //client_isso_i.HttpsGetMeanInfoForIssosCompleted += Client_isso_i_HttpsGetMeanInfoForIssosCompleted;
		            //client_isso_i.HttpsReceiveSQLQueryCompleted += Client_isso_i_HttpsReceiveSQLQueryCompleted;
		            //client_isso_i.GetDataTableStreamCompleted += Client_isso_i_GetDataTableStreamCompleted;
		            //client_isso_i.GetDictionaryTablesCompleted += Client_isso_i_GetDictionaryTablesCompleted;


		            //client_common.HttpsCloseSessionCompleted += Client_common_HttpsCloseSessionCompleted;
		            //client_common.HttpsGetIssoListCompleted += Client_common_HttpsGetIssoListCompleted;


		            // ***********************
		            // НОВЫЕ КЛАССЫ!

		            // Для подключения к сервису Isso-I
		            using (var clientIssoI = Device.RuntimePlatform == Device.Android
				            ? new ISSO_I_ClientBase(GetBinding(), endpointIssoI)
				            : new ISSO_I_ClientBaseIOS(GetBinding(), endpointIssoI))
			            // Для подключения к сервису Common
		            using (var clientCommon = Device.RuntimePlatform == Device.Android
			            ? new Common_ClientBase(GetBinding(), endpointCommon)
			            : new Common_ClientBaseIOS(GetBinding(), endpointCommon))
		            {
			            #region Регистрация приложения

			            // ******************************
			            // Количество этапов в синхронизации
			            Device.BeginInvokeOnMainThread(() =>
			            {
				            MyLabelInfoSync.Text =
					            $"Шаг 1 из {CountOfSteps}. Получение разрешения...";
			            });
			            Thread.Sleep(1000);
			            //client_isso_i.GetIssoIUserCollectionsAsync(Login, Password);
			            //success = false;
			            //semaphore.WaitOne(wait_millis);
			            //if (!success)
			            //{
			            //    OnSyncFailed();
			            //    return;
			            //}
			            //ListCollection[] collections = (ListCollection[])output;

			            //try
			            //{
			            //    // Выставляем время на подключение
			            //    clientIssoI.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(secondsWaitStartSession);
			            //    collections = clientIssoI.GetIssoIUserCollections(Login, Password);
			            //    if (collections == null)
			            //    {
			            //        if (ct.IsCancellationRequested) return;
			            //        messageError = "Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
			            //        OnSyncFailed();
			            //        return;
			            //    }
			            //}
			            //catch
			            //{
			            //    if (ct.IsCancellationRequested) return;
			            //    messageError = "Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
			            //    OnSyncFailed();
			            //    return;
			            //}

			            //if (ct.IsCancellationRequested) return;


			            string[] result;
			            try
			            {
				            // ReSharper disable once PossibleNullReferenceException
				            clientIssoI.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsWaitStartSession);
				            result = clientIssoI.HttpsGetSessionIdForIssoI(Login, Password, Collection.IssoList);
				            if (result == null)
				            {
					            if (ct.IsCancellationRequested) return;
					            ShowErrorMessage(null, false);
					            return;
				            }
			            }
			            catch (Exception ex)
			            {
				            if (ct.IsCancellationRequested) return;
				            ShowErrorMessage(ex, true);
				            return;
			            }

			            if (ct.IsCancellationRequested) return;
			            // Получение разрешения и id со списком ИССО
			            var id = new Guid(result[0]);
			            var nameCurator = result[1];

			            if (!Application.Current.Properties.TryGetValue("user", out var _))
			            {
				            Application.Current.Properties.Add("user", "");
			            }

			            Application.Current.Properties["user"] = nameCurator;
#pragma warning disable 4014
			            Application.Current.SavePropertiesAsync();
#pragma warning restore 4014

			            #endregion

			            #region Получение ИССО

			            Device.BeginInvokeOnMainThread(() =>
			            {
				            MyLabelInfoSync.Text = $"Шаг 2 из {CountOfSteps}. Получение ИССО...";
				            ((MasterDetailPage1Master) ((MasterDetailPage1) Application.Current.MainPage).Master).SetTextForUser(
					            nameCurator);
			            });
			            Thread.Sleep(500);


			            DBHelper.HttpsIsso[] issos;
			            try
			            {
				            // ReSharper disable once PossibleNullReferenceException
				            clientCommon.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsWait);
				            issos = clientCommon.HttpsGetIssoList(id);
				            if (issos == null)
				            {
					            if (ct.IsCancellationRequested) return;
					            ShowErrorMessage(null, false);
					            return;
				            }
			            }
			            catch (Exception ex)
			            {
				            if (ct.IsCancellationRequested) return;
				            ShowErrorMessage(ex, true);
				            return;
			            }

			            if (ct.IsCancellationRequested) return;
			            // Удаление предыдущих записей
			            connection.Execute("delete from I_ISSO");
			            // Получение результата по ИССО
			            // Список ИССО
			            var cIssos = new List<int>();
			            // Список типов ИССО
			            var cTypeIsso = new List<int>();
			            int[] i = {1};
			            foreach (var isso in issos)
			            {
				            Device.BeginInvokeOnMainThread(() =>
				            {
					            MyLabelInfoSync.Text =
						            $"Шаг 2 из {CountOfSteps}. Получение ИССО...\n[{i[0]} из {issos.Length}]";
					            i[0]++;
				            });
				            var iIsso = new DBHelper.I_ISSO(isso);
				            // Добавление ИССО в БД
				            connection.InsertOrReplace(iIsso);
				            cIssos.Add(iIsso.C_ISSO);
				            cTypeIsso.Add(iIsso.CTYPEISSO);
			            }

			            #endregion

			            #region Получение списка таблиц для загрузки для таких типов ИССО

			            DBHelper.MeanInfo meanInfo;
			            try
			            {
				            // ReSharper disable once PossibleNullReferenceException
				            clientIssoI.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsWait);
				            meanInfo = clientIssoI.HttpsGetMeanInfoForIssos(id, cIssos.ToArray(), cTypeIsso.ToArray());
				            if (meanInfo == null)
				            {
					            if (ct.IsCancellationRequested) return;
					            ShowErrorMessage(null, false);
					            return;
				            }
			            }
			            catch (Exception ex)
			            {
				            if (ct.IsCancellationRequested) return;
				            ShowErrorMessage(ex, true);
				            return;
			            }

			            if (ct.IsCancellationRequested) return;
			            Device.BeginInvokeOnMainThread(() =>
			            {
				            MyLabelInfoSync.Text =
					            $"Шаг 3 из {CountOfSteps}. Получение дополнительных сведений по ИССО...";
			            });

			            // Удаляем из скачивания заранее определенные таблицы
			            foreach (var names in meanInfo.tableNames)
			            {
				            var res = CommonStaffUtils.TableStates.FirstOrDefault(table =>
					            table.Key.Substring(0, table.Key.LastIndexOf('.')).ToLower().Equals(names.TableSysName.ToLower()));
				            if (res.Key != null && res.Value == TableState.Skip)
				            {
					            var tempInfo = meanInfo.tableNames.ToList();
					            tempInfo.Remove(names);
					            meanInfo.tableNames = tempInfo.ToArray();
				            }
			            }

			            connection.BeginTransaction();
			            connection.Execute("delete from TABLE_NAMES");
			            for (i[0] = 0; i[0] < meanInfo.tableNames.Length; i[0] += 10)
			            {
				            var j = 0;
				            var sqlRequest = new StringBuilder();
				            while (j < 10 && j + i[0] < meanInfo.tableNames.Length)
				            {
					            var names = meanInfo.tableNames[i[0] + j];
					            sqlRequest.Append("('").Append(names.C_GR_CONSTR).Append("','").Append(names.TableSysName)
						            .Append("','").Append(names.TableName).Append("','").Append(names.TableParentID).Append("','")
						            .Append(names.TableView).Append("'),");
					            j++;
				            }

				            var sql =
					            $"insert into TABLE_NAMES (C_GR_CONSTR, SYS_NAME, DESCRIPTION, PARENT_ID, PARENT_VIEW) values {sqlRequest.ToString(0, sqlRequest.Length - 1)}";
				            sqlRequest.Clear();
				            connection.Execute(sql);
			            }

			            connection.Commit();

			            // Теперь мы записываем данные, как будем соотносить таблицы к типам ИССО
			            connection.Execute("delete from TABLE_DELEGATES");
			            connection.BeginTransaction();
			            for (i[0] = 0; i[0] < meanInfo.tableDelegate.Length; i[0] += 10)
			            {
				            var j = 0;
				            var sqlRequest = new StringBuilder();
				            while (j < 10 && i[0] + j < meanInfo.tableDelegate.Length)
				            {
					            var Delegate = meanInfo.tableDelegate[i[0] + j];
					            sqlRequest.Append("('").Append(Delegate.C_TYPEISSO).Append("','").Append(Delegate.C_GR_CONSTR)
						            .Append("'),");
					            j++;
				            }

				            var sql =
					            $"insert into TABLE_DELEGATES (ISSO_TYPE, C_GR_CONSTR) values {sqlRequest.ToString(0, sqlRequest.Length - 1)}";
				            connection.Execute(sql);
				            sqlRequest.Clear();
			            }

			            connection.Commit();

			            #endregion

			            // Получение этих таблиц
			            var tableNames = new List<string>();
			            foreach (var names in meanInfo.tableNames)
			            {
				            tableNames.Add(names.TableSysName);
			            }


			            string[] sqlRequests;
			            try
			            {
				            sqlRequests = clientIssoI.HttpsReceiveSQLQuery(id, tableNames.ToArray());
				            if (sqlRequests == null)
				            {
					            if (ct.IsCancellationRequested) return;
					            ShowErrorMessage(null, false);
					            return;
				            }
			            }
			            catch (Exception ex)
			            {
				            if (ct.IsCancellationRequested) return;
				            ShowErrorMessage(ex, true);
				            return;
			            }

			            if (ct.IsCancellationRequested) return;
			            // Преждевременное удаление таблиц
			            foreach (var name in tableNames)
			            {
				            try
				            {
					            connection.BeginTransaction();
					            var sql = $"drop table if exists {name}";
					            connection.Execute(sql);
					            connection.Commit();
				            }
				            catch (SQLite.SQLiteException ex)
				            {
					            Console.WriteLine(ex.Message);
					            throw;
				            }
			            }

			            foreach (var sql in sqlRequests)
			            {
				            try
				            {
					            connection.BeginTransaction();
					            connection.Execute(sql);
					            connection.Commit();

				            }
				            catch (SQLite.SQLiteException ex)
				            {
					            Console.WriteLine(ex.Message);
					            throw;
				            }
			            }

			            try
			            {
				            sqlRequests = clientIssoI.HttpsReceiveSQLQuery(id, STables.ToArray());
				            if (sqlRequests == null)
				            {
					            if (ct.IsCancellationRequested) return;
					            ShowErrorMessage(null, false);
					            return;
				            }
			            }
			            catch (Exception ex)
			            {
				            if (ct.IsCancellationRequested) return;
				            ShowErrorMessage(ex, true);
				            return;
			            }

			            if (ct.IsCancellationRequested) return;

			            // Удаляем все из таблиц
			            foreach (var name in STables)
			            {
				            try
				            {
					            connection.BeginTransaction();
					            var sql = $"drop table if exists {name}";
					            connection.Execute(sql);
					            connection.Commit();
				            }
				            catch (SQLite.SQLiteException ex)
				            {
					            Console.WriteLine(ex.Message);
					            throw;
				            }
			            }

			            // Теперь получим sql-запросы для создания справочных таблиц
			            foreach (var sql in sqlRequests)
			            {
				            try
				            {
					            connection.BeginTransaction();
					            connection.Execute(sql);
					            connection.Commit();

				            }
				            catch (SQLite.SQLiteException ex)
				            {
					            Console.WriteLine(ex.Message);
					            throw;
				            }
			            }

			            // И засунем в них последние актуальные данные
			            foreach (var sTable in STables)
			            {
				            CreateSQL_Insert(clientIssoI, connection, id, sTable, null, false, true, 3, ct);
			            }

			            // Получим справочные таблицы для отображения информации
			            Device.BeginInvokeOnMainThread(() =>
			            {
				            MyLabelInfoSync.Text =
					            $"Шаг {3} из {CountOfSteps}. Получение доп. справочных материалов...";
			            });

			            byte[][] dictionaryTables;
			            try
			            {
				            dictionaryTables = clientIssoI.GetDictionaryTables(id, tableNames.ToArray());
				            if (dictionaryTables == null)
				            {
					            ShowErrorMessage(null, false);
					            return;
				            }
			            }
			            catch (Exception ex)
			            {
				            if (ct.IsCancellationRequested) return;
				            ShowErrorMessage(ex, true);
				            return;
			            }

			            if (ct.IsCancellationRequested) return;
			            connection.DropTable<DBHelper.ADVANCED_S_TABLES>();
			            connection.DropTable<DBHelper.S_INFO_TABLES>();

			            // Заполняем таблицу ADVANCED_S_TABLES
			            using (var ms = new MemoryStream(dictionaryTables[0]))
			            {
				            var reader = new Ais7BinaryIStream(ms);
				            var table = reader.ReadDataTable();
				            connection.CreateTable<DBHelper.ADVANCED_S_TABLES>();
				            InsertDictionaryTables(table, connection, new[]
				            {
					            "TABLE_NAME", "TABLE_COLUMN", "DESCRIPTION", "S_TABLE", "CATEGORY", "READONLY", "VISIBLE",
					            "FORMAT", "MIN_V", "MAX_V", "COLUMNTYPE", "IS_PRIMARY_KEY"
				            }, "ADVANCED_S_TABLES");
			            }

			            // Заполняем таблицу S_INFO_TABLES
			            using (var ms = new MemoryStream(dictionaryTables[1]))
			            {
				            var reader = new Ais7BinaryIStream(ms);
				            var table = reader.ReadDataTable();
				            connection.CreateTable<DBHelper.S_INFO_TABLES>();
				            InsertDictionaryTables(table, connection, new[] {"TABLE_NAME", "ID", "VALUE"}, "S_INFO_TABLES");
			            }

			            //Освобождаем память
			            // ReSharper disable once RedundantAssignment
			            dictionaryTables = null;

			            // Получение самих таблиц
			            foreach (var name in tableNames)
			            {
				            if (!(!PhotosEnable && name.Equals("i_foto") || !SchemesEnable && name.Equals("i_sxema")))
				            {
					            if (!meanInfo.tableNames.ToList().Find(x => x.TableSysName == name).IsTableExportedWhole)
					            {
						            if (ct.IsCancellationRequested) return;
						            CreateSQL_Insert(clientIssoI, connection, id, name, cIssos.ToArray(), false, false, 4, ct);
					            }
					            else
					            {
						            foreach (var cIsso in cIssos)
						            {
							            if (ct.IsCancellationRequested) return;
							            CreateSQL_Insert(clientIssoI, connection, id, name, new[] {cIsso}, true, false, 4, ct);
						            }
					            }
				            }
			            }

			            // Получение таблиц дефектов

			            try
			            {
				            sqlRequests = clientIssoI.HttpsReceiveSQLQuery(id, DefectTables.ToArray());
				            if (sqlRequests == null)
				            {
					            if (ct.IsCancellationRequested) return;
					            ShowErrorMessage(null, false);
					            return;
				            }
			            }
			            catch (Exception ex)
			            {
				            if (ct.IsCancellationRequested) return;
				            ShowErrorMessage(ex, true);
				            return;
			            }

			            if (ct.IsCancellationRequested) return;
			            // Удаляем все из таблиц
			            i[0] = 0;
			            foreach (var name in DefectTables)
			            {
				            try
				            {
					            connection.BeginTransaction();
					            var sql = !sqlRequests[i[0]].ToLower().Contains("create view")
						            ? $"drop table if exists {name}"
						            : $"drop view if exists {name}";
					            connection.Execute(sql);
					            connection.Commit();
					            i[0]++;
				            }
				            catch (SQLite.SQLiteException ex)
				            {
					            Console.WriteLine(ex.Message);
					            throw;
				            }
			            }

			            // Теперь получим sql-запросы для создания справочных таблиц
			            foreach (var sql in sqlRequests)
			            {
				            try
				            {
					            connection.BeginTransaction();
					            connection.Execute(sql);
					            connection.Commit();

				            }
				            catch (SQLite.SQLiteException ex)
				            {
					            Console.WriteLine(ex.Message);
					            throw;
				            }
			            }

			            i[0] = 0;
			            // И засунем в них последние актуальные данные
			            foreach (var defectTable in DefectTables)
			            {
				            if (!sqlRequests[i[0]].ToLower().Contains("create view"))
				            {
					            if (!defectTable.ToLower().Equals("i_foto_def"))
					            {
						            if (ct.IsCancellationRequested) return;
						            CreateSQL_Insert(clientIssoI, connection, id, defectTable, cIssos.ToArray(), false, false, 5, ct);
					            }
					            else
					            {
						            foreach (var cIsso in cIssos)
						            {
							            if (ct.IsCancellationRequested) return;
							            CreateSQL_Insert(clientIssoI, connection, id, defectTable, new[] {cIsso}, true, false, 5, ct);
						            }
					            }
				            }

				            i[0]++;
			            }

			            //// Добьем таблицу i_defect последними бесполезными данными))
			            //try
			            //{
			            //    Device.BeginInvokeOnMainThread(() => { MyLabelInfoSync.Text = String.Format("Завершение модфикации БД..."); });
			            //    // Добавляем столбец main_constr_id - замена скалярной функции из mssql


			            //    //connection.BeginTransaction();
			            //    //connection.Execute("alter table i_defect add column MAIN_CONSTR_ID integer");
			            //    //connection.Commit();

			            //    // Теперь заполняем её
			            //    //using (SqliteConnection connect = new SqliteConnection(App.newDatabasePath))
			            //    //{
			            //    //    SqliteCommand command = connect.CreateCommand();
			            //    //    command.CommandText = "select C"
			            //    //}
			            //    //var c_gr_constrs = connection.Query<C_GR_CONSTR_ID>("select C_GR_CONSTR from S_GR_CONSTR").ToList();
			            //    //int amount = 0;
			            //    //StringBuilder str_update = new StringBuilder();
			            //    //foreach(var c_gr_constr in c_gr_constrs)
			            //    //{
			            //    //    var main_constr_id = MainConstrId(c_gr_constr.C_GR_CONSTR, connection);
			            //    //    amount++;
			            //    //    str_update.Append(String.Format("update i_defect set MAIN_CONSTR_ID = {0} where C_GR_CONSTR = {1} ; ", main_constr_id, c_gr_constr.C_GR_CONSTR));
			            //    //    if(amount > 50 || c_gr_constrs.IndexOf(c_gr_constr) == c_gr_constrs.Count)
			            //    //    {
			            //    //        connection.BeginTransaction();
			            //    //        var num = connection.Execute(str_update.ToString());
			            //    //        Device.BeginInvokeOnMainThread(() => {
			            //    //            MyLabelInfoSync.Text = String.Format("Завершение модификации БД... ({0} из {1})", c_gr_constrs.IndexOf(c_gr_constr), c_gr_constrs.Count);
			            //    //        });
			            //    //        connection.Commit();
			            //    //        str_update.Clear();
			            //    //        amount = 0;
			            //    //    }
			            //    //}

			            //    //EndSync();

			            //}
			            //catch (SQLite.SQLiteException ex)
			            //{
			            //    throw ex;
			            //}



			            //client_common.HttpsCloseSessionAsync(id);
			            clientCommon.HttpsCloseSession(id);
			            watch.Stop();
			            ElapsedMs = watch.ElapsedMilliseconds;
			            connection.Close();
			            //Navigation.PopPopupAsync();
			            OnSyncSucceeded();
		            }
	            }
	            catch (Exception ex)
	            {
		            Console.WriteLine(ex.ToString());
		            if (ct.IsCancellationRequested) return;
		            MessageError =
			            "Произошла внутрення ошибка в приложении. Обратитетсь к разработчику для выявления проблемы.";
		            OnSyncFailed();
		            connection.Close();
	            }
	            finally
	            {
		            connection?.Close();
	            }

            }, ct);
            return 0;
        }

/*
        private void EndSync()
        {
            Device.BeginInvokeOnMainThread(() => { MyLabelInfoSync.Text = "Завершение модфикации БД..."; });

            try
            {
                var cGrConstrs = new List<int>();
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    // Непосредственно выполнение запроса
                    var command = connection.CreateCommand();
                    command.CommandText = "select C_GR_CONSTR from S_GR_CONSTR";
                    command.CommandTimeout = 30;
                    command.CommandType = CommandType.Text;
                    connection.Open();
                    using (var datareader = command.ExecuteReader())
                    {
                        if (datareader.HasRows)
                        {
                            while (datareader.Read())
                            {
                                cGrConstrs.Add(datareader.GetInt32(0));
                            }
                        }
                    }

                    var amount = 0;
                    var strUpdate = new StringBuilder();
                    foreach (var cGrConstr in cGrConstrs)
                    {
                        var mainConstrId = MainConstrId(cGrConstr, connection);
                        amount++;
                        strUpdate.Append(
	                        $"update i_defect set MAIN_CONSTR_ID = {mainConstrId} where C_GR_CONSTR = {cGrConstr} ; ");
	                    if (amount <= 50 && cGrConstrs.IndexOf(cGrConstr) != cGrConstrs.Count) continue;
	                    command = new SqliteCommand(connection)
	                    {
		                    CommandText = strUpdate.ToString(),
		                    CommandTimeout = 30,
		                    CommandType = CommandType.Text
	                    };
	                    var num = command.ExecuteNonQuery();
	                    Device.BeginInvokeOnMainThread(() => {
		                    MyLabelInfoSync.Text =
			                    $"Завершение модификации БД... ({cGrConstrs.IndexOf(cGrConstr)} из {cGrConstrs.Count})";
	                    });
	                    strUpdate.Clear();
	                    amount = 0;
                    }
                    // Закрываем подключение
                    connection.Close();
                }
            }
            catch (Exception ex)
            {
                var error = ex.Message;
            }
        }
*/

/*
        /// <summary>
        /// Рекурсивная функция получения параметра главной группы конструкций
        /// </summary>
        /// <param name="cGrConstr"></param>
        /// <returns></returns>
        private int MainConstrId(int cGrConstr, SqliteConnection connection)
        {
            if (cGrConstr < 1000) return cGrConstr;
            try
            {
                using (var command = new SqliteCommand(connection))
                {
                    command.CommandText = $"select PARENT_GROUP from S_GR_CONSTR where C_GR_CONSTR = {cGrConstr}";
                    command.CommandTimeout = 30;
                    command.CommandType = CommandType.Text;
                    using(var datareader = command.ExecuteReader())
                    {
                        var mainConstrId = 0;
                        if(datareader.HasRows)
                        {
                            datareader.Read();
                            mainConstrId = datareader.GetInt32(0);
                        }
                        return MainConstrId(mainConstrId, connection);
                    }
                }
            }
            catch(Exception ex)
            {
	            Console.WriteLine(ex.Message);
	            return -1;
            }
            //var main_constr_id = connection.ExecuteScalar<Int32>("select PARENT_GROUP from S_GR_CONSTR where C_GR_CONSTR = ?", new object[] { c_gr_constr });
            //using (SqliteConnection connection = new SqliteConnection(App.newDatabasePath))
            //{
            //    SqliteCommand command = connection.CreateCommand();
            //    command.CommandText = String.Format("select PARENT_GROUP from S_GR_CONSTR where C_GR_CONSTR = {0}", c_gr_constr);
            //    command.CommandTimeout = 30;
            //    command.CommandType = System.Data.CommandType.Text;
            //    connection.Open();
            //    using (SqliteDataReader datareader = command.ExecuteReader())
            //    {
            //        if (datareader.HasRows)
            //        {
            //            datareader.Read();
            //            main_constr_id = Convert.ToInt32(datareader.GetValue(0));
            //        }
            //    }
            //    connection.Close();
            //}
            //return MainConstrId(main_constr_id, connection);
        }
*/

	    /// <summary>
	    /// Метод добавления полей в справочные таблицы для информации
	    /// </summary>
	    /// <param name="table">Таблица, в которую производится запись</param>
	    /// <param name="connection">Экземпляр подключения к БД</param>
	    /// <param name="columns">Список колонок</param>
	    /// <param name="tableName"></param>
	    private void InsertDictionaryTables(DataTable table, SQLite.SQLiteConnection connection, string[] columns, string tableName)
        {
            for (var i = 0; i < table.Rows.Count; i += 50)
            {
                connection.BeginTransaction();
                var values = new StringBuilder();
                try
                {
                    for (var j = 0; j + i < table.Rows.Count && j < 50; j++)
                    {
                        values.Append("(");
                        for (var k = 0; k < table.Columns.Count; k++)
                        {
                            if (table.Rows[j + i][k] != DBNull.Value)
                                switch (_types[table.Columns[k].DataType])
                                {
                                    case "smallint":
                                        values.Append($"{Convert.ToInt16(table.Rows[j + i][k])},");
                                        break;
                                    case "int":
                                        values.Append($"{Convert.ToInt32(table.Rows[j + i][k])},");
                                        break;
                                    case "bigint":
                                        values.Append($"{Convert.ToInt64(table.Rows[j + i][k])},");
                                        break;
                                    case "string":
                                        values.Append($"'{table.Rows[j + i][k]}',");
                                        break;
                                    case "float":
                                        values.Append(
	                                        $"{Convert.ToDouble(table.Rows[j + i][k]).ToString(CultureInfo.CurrentCulture).Replace(',', '.')},");
                                        break;
                                    case "datetime":
                                        values.Append(
	                                        $"{CommonStaffUtils.ConvertToUnixTimestamp(Convert.ToDateTime(table.Rows[j + i][k]))},");
                                        break;
                                    case "byte":
                                        //values.Append(String.Format("{0},", string.Format("0x{0}", ByteArrayToString(table.Rows[j + i][k] as byte[]))));
                                        values.Append($"'{Convert.ToBase64String(table.Rows[j + i][k] as byte[] ?? throw new InvalidOperationException())}',");
                                        break;
                                    case "boolean":
                                        values.Append($"'{(Convert.ToBoolean(table.Rows[j + i][k]) ? 1 : 0)}',");
                                        break;
                                    default:
                                        values.Append($"'{table.Rows[j + i][k]}',");
                                        break;
                                }
                            else
                            {
                                values.Append("null,");
                            }

                        }
                        values = values.Remove(values.Length - 1, 1);
                        values.Append("),");
                    }
                    values = values.Remove(values.Length - 1, 1);
                    var columnStr = new StringBuilder("'");
                    foreach (var str in columns)
                    {
                        columnStr.Append(str).Append("','");
                    }
                    var sqlRequest =
	                    $"insert into {tableName} ({string.Join(",", columns)}) values {values}";
                    var count = connection.CreateCommand(sqlRequest).ExecuteNonQuery();
                    Console.WriteLine(count);
                    connection.Commit();
                    values.Clear();
                    columnStr.Clear();
                }
                catch(Exception ex)
                {
                    Console.WriteLine(ex);
                }
            }
        }


	    /// <summary>
	    /// Метод создания sql insert-запроса
	    /// </summary>
	    /// <param name="myClientIssoI"></param>
	    /// <param name="connection">для БД</param>
	    /// <param name="id">Идентификатор</param>
	    /// <param name="name">Имя таблицы</param>
	    /// <param name="cIssos">Список ИССО</param>
	    /// <param name="isForeachIsso">Большая ли таблица</param>
	    /// <param name="isDirectoryTable">Справочная ли это таблица</param>
	    /// <param name="currentStep"></param>
	    /// <param name="ct"></param>
	    private void CreateSQL_Insert(ISSO_I_ClientBase myClientIssoI, SQLite.SQLiteConnection connection, Guid id, string name, int[] cIssos,
            bool isForeachIsso, bool isDirectoryTable, int currentStep, CancellationToken ct)
        {
            try
            {
                Device.BeginInvokeOnMainThread(() =>
                {
                    switch (currentStep)
                    {
                        case 4:
                            if (name.Equals("i_foto"))
                            {
                                MyLabelInfoSync.Text =
	                                $"Шаг {currentStep} из {CountOfSteps}. Получение фотографий по ИССО...  {(isForeachIsso ? $"\n(ИССО №{cIssos[0]})" : "")}";
                            }
                            else if (name.Equals("i_sxema"))
                            {
                                MyLabelInfoSync.Text =
	                                $"Шаг {currentStep} из {CountOfSteps}. Получение схем по ИССО... {(isForeachIsso ? $"\n(ИССО №{cIssos[0]})" : "")}";
                            }
                            else
                            {
                                MyLabelInfoSync.Text =
	                                $"Шаг {currentStep} из {CountOfSteps}. Получение дополнительных сведений по ИССО... (Таблица: {name}) {(isForeachIsso ? $"\n(ИССО №{cIssos[0]})" : "")}";
                            }
                            break;
                        case 3:
                            Device.BeginInvokeOnMainThread(() =>
                            {
                                MyLabelInfoSync.Text =
	                                $"Шаг {currentStep} из {CountOfSteps}. Получение справочных материалов...  (Таблица: {name})";
                            });
                            break;
                        case 5:
                            Device.BeginInvokeOnMainThread(() =>
                            {
                                MyLabelInfoSync.Text =
	                                $"Шаг {currentStep} из {CountOfSteps}. Получение дефектов...  (Таблица: {name}) {(isForeachIsso ? $"\n(ИССО №{cIssos[0]})" : "")}";
                            });
                            break;
                    }
                });
                byte[] tableRes;
                try
                {
                    tableRes = myClientIssoI.GetDataTableStream(id, name, cIssos, isDirectoryTable);
                    if (tableRes == null)
                    {
                        if (ct.IsCancellationRequested) return;
	                    ShowErrorMessage(null, false);
                        return;
                    }
                }
                catch (Exception ex)
                {
	                if (ct.IsCancellationRequested) return;
	                ShowErrorMessage(ex, true);
	                return;
                }
                if (ct.IsCancellationRequested) return;
	            //***************************************///
                using (var ms = new MemoryStream(tableRes))
                {
                    var reader = new Ais7BinaryIStream(ms);
                    using (var table = reader.ReadDataTable())
                    {
                        // Заполняем таблицу
                        // Для начала получим все солонки для создания запроса
                        var columns = new StringBuilder();
                        for (var col = 0; col < table.Columns.Count; col++)
                        {
                            columns.Append($"{table.Columns[col].ColumnName},");
                        }
                        columns = columns.Remove(columns.Length - 1, 1);
                        for (var i = 0; i < table.Rows.Count; i += 50)
                        {
                            connection.BeginTransaction();
                            var values = new StringBuilder();
                            for (var j = 0; j + i < table.Rows.Count && j < 50; j++)
                            {
                                values.Append("(");
                                for (var k = 0; k < table.Columns.Count; k++)
                                {
                                    if (table.Rows[j + i][k] != DBNull.Value)
                                        switch (_types[table.Columns[k].DataType])
                                        {
                                            case "smallint":
                                                values.Append($"{Convert.ToInt16(table.Rows[j + i][k])},");
                                                break;
                                            case "int":
                                                values.Append($"{Convert.ToInt32(table.Rows[j + i][k])},");
                                                break;
                                            case "bigint":
                                                values.Append($"{Convert.ToInt64(table.Rows[j + i][k])},");
                                                break;
                                            case "string":
                                            case "timespan":
                                                values.Append(
	                                                $"'{table.Rows[j + i][k].ToString().Replace("'", "''")}',");
                                                break;
                                            case "float":
                                                values.Append(
	                                                $"{Convert.ToDouble(table.Rows[j + i][k]).ToString(CultureInfo.CurrentCulture).Replace(',', '.')},");
                                                break;
                                            case "datetime":
                                                values.Append(
	                                                $"{CommonStaffUtils.ConvertToUnixTimestamp(Convert.ToDateTime(table.Rows[j + i][k]))},");
                                                break;
                                            case "byte":
                                                //values.Append(String.Format("{0},", string.Format("0x{0}", ByteArrayToString(table.Rows[j + i][k] as byte[]))));
                                                //values.Append(String.Format("'{0}',", Convert.ToBase64String(ObjectToByteArray(table.Rows[j + i][k]))));
                                                var byteArray = (byte[])table.Rows[j + i][k];
                                                ByteArrayToString(byteArray);
                                                var base64String = Convert.ToBase64String(byteArray);
                                                values.Append($"'{base64String}',");
	                                            // ReSharper disable once RedundantAssignment
	                                            byteArray = null;
                                                break;
                                            case "boolean":
                                                values.Append(
	                                                $"'{(Convert.ToBoolean(table.Rows[j + i][k]) ? 1 : 0)}',");
                                                break;
                                            default:
                                                values.Append($"'{table.Rows[j + i][k]}',");
                                                break;
                                        }
                                    else
                                    {
                                        values.Append("null,");
                                    }

                                }
                                values = values.Remove(values.Length - 1, 1);
                                values.Append("),");
                            }
                            values = values.Remove(values.Length - 1, 1);
                            var sqlRequest = $"insert into {name} ({columns}) values {values}";
                            var count = connection.CreateCommand(sqlRequest).ExecuteNonQuery();
                            values.Clear();
	                        Console.WriteLine(count);
                            connection.Commit();
                        }
                        columns.Clear();
                    }
                }
	            // ReSharper disable once RedundantAssignment
                tableRes = new byte[] { };
            }
            catch (Exception ex)
            {
	            Console.WriteLine(ex.Message);
	            OnSyncFailed();
            }
        }

        /// <summary>
        /// Конвертация массива байт в строку
        /// </summary>
        /// <param name="ba"></param>
        /// <returns></returns>
        public string ByteArrayToString(byte[] ba)
        {
            return BitConverter.ToString(ba).Replace("-", "");
        }

        public byte[] ObjectToByteArray(object obj)
        {
            if (obj == null)
                return null;
            var bf = new BinaryFormatter();
            using (var ms = new MemoryStream())
            {
                bf.Serialize(ms, obj);
                return ms.ToArray();
            }
        }

	    private void ShowErrorMessage(Exception ex, bool isInternal)
	    {
		    MessageError = isInternal ? "Произошла ошибка в приложении при синхронизации. Пожалуйста, сделайте скрин экрана с ошибкой и отправьте администратору приложения.\n" +
		                                $"Error:{ex.Message}" :
			    "Нет подключения к серверу. Проверьте настройки подключения и обратитесь к администратору сервера, если все верно.";
			OnSyncFailed();
	    }

        //private void Client_isso_i_GetDictionaryTablesCompleted(object sender, GetDictionaryTablesCompletedEventArgs e)
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

        //private void Client_isso_i_GetDataTableStreamCompleted(object sender, GetDataTableStreamCompletedEventArgs e)
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

        

        //private void Client_common_HttpsCloseSessionCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e) { }

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

        //private void Client_isso_i_GetIssoIUserCollectionsCompleted(object sender, GetIssoIUserCollectionsCompletedEventArgs e)
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

        public static BasicHttpsBinding GetBinding()
        {
            // Инициализация безопасного биндинга
            var basicBinding = new BasicHttpsBinding(BasicHttpsSecurityMode.Transport)
            {
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

    public class CGrConstrId
    {
        public int CGrConstr { get; set; }
    }
}

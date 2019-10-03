using ISSO_S.Generated;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.ServiceModel;
using System.Threading;
using System.Threading.Tasks;
using Xamarin.Forms;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S
{
	internal class MySyncPage : CommonClassesLibrary.SyncPage
	{
		/// <summary>
		///     Время на синхронизацию
		/// </summary>
		public long ElapsedMs;

		/// <summary>
		///     Вывод ошибки, если таковая есть
		/// </summary>
		public string MessageError = "";

		/// <summary>
		///     Токен, отвечающий за завершение треды
		/// </summary>
		public CancellationTokenSource Ts { get; set; } = new CancellationTokenSource();

		/// <summary>
		///     Ожидание отклика с сервера на старте
		/// </summary>
		private const double SecondsToWaitStart = 10;

		/// <summary>
		///     Ожидание отклика с сервера
		/// </summary>
		private const double SecondsToWait = 60;


		public MySyncPage(string login, string password, bool photosEnable, bool schemesEnable) : base(login, password,
			photosEnable, schemesEnable)
		{
		}

		public override void CloseSync()
		{
			MessageError = "";
			OnSyncCancelled();
		}

		public override int Sync()
		{
			var ct = Ts.Token;
			Task.Factory.StartNew(async () =>
			{
				var watch = Stopwatch.StartNew();
				// Адрес с общим интерфейсом
				Device.BeginInvokeOnMainThread(() => { MyLabelInfoSync.Text = "Получение разрешения..."; });
				Thread.Sleep(1000);
				ServicePointManager.ServerCertificateValidationCallback = delegate { return true; };
				var addressCommon =
					$"https://{Convert.ToString(Application.Current.Properties["address"])}:{Convert.ToString(Application.Current.Properties["port"])}/ais7UpdateServerSecureBinding/Common";
				var endpointCommon = new EndpointAddress(addressCommon);
				//ChannelFactory<Iais7MobileCommonServerCore> factory_common = new ChannelFactory<Iais7MobileCommonServerCore>(GetBinding(), endpoint_common);
				// Адрес с интерфейсом для ISSO-S
				var addressIssoS =
					$"https://{Convert.ToString(Application.Current.Properties["address"])}:{Convert.ToString(Application.Current.Properties["port"])}/ais7UpdateServerSecureBinding/IssoS";
				var endpointIssoS = new EndpointAddress(addressIssoS);

				// Инициализация клиентов для подключения к серверу
				//Ais7MobileCommonServerCoreXamarinClient client_common = new Ais7MobileCommonServerCoreXamarinClient(GetBinding(), endpoint_common);
				//Ais7MobileIssoSServerCoreXamarinClient client_isso_s = new Ais7MobileIssoSServerCoreXamarinClient(GetBinding(), endpoint_isso_s);


				//    //Создание event handler'ов
				//    client_isso_s.HttpsGetSessionIdForIssoSCompleted += Client_isso_s_HttpsGetSessionIdForIssoSCompleted; ;
				//    client_common.HttpsGetIssoListCompleted += Client_common_HttpsGetIssoListCompleted;
				//    client_isso_s.HttpsSetRatingInfoCompleted += Client_isso_s_HttpsSetRatingInfoCompleted;
				//    client_isso_s.HttpsSetPhotoIssoSCompleted += Client_isso_s_HttpsSetPhotoIssoSCompleted;
				//    client_isso_s.HttpsGetRatingInfoCompleted += Client_isso_s_HttpsGetRatingInfoCompleted;
				//    client_isso_s.HttpsReceiveInfoAboutPhotoForIssoSCompleted += Client_isso_s_HttpsReceiveInfoAboutPhotoForIssoSCompleted;
				//    client_isso_s.HttpsGetPhotoIssoSCompleted += Client_isso_s_HttpsGetPhotoIssoSCompleted;
				//    client_common.HttpsCloseSessionCompleted += Client_common_HttpsCloseSessionCompleted;


				//MyCommonXamarinClient my_client_common = new MyCommonXamarinClient(GetBinding(), endpoint_common);
				//MyIssoSXamarinClient my_client_s = new MyIssoSXamarinClient(GetBinding(), endpoint_isso_s);
				//IAis7MobileIssoSServerCoreXamarin interface_connection;
				//        if (Device.RuntimePlatform == Device.Android)
				//        {
				//            NewClassForConnection newClassForConnection = new NewClassForConnection(GetBinding(), endpoint_isso_s);
				//            interface_connection = newClassForConnection.ChannelFactory.CreateChannel();
				//            newClassForConnection.Endpoint.Binding.SendTimeout = TimeSpan.FromMilliseconds(20000);
				//            var res = interface_connection.HttpsGetSessionIdForIssoS(Login, Password);
				//            var res1 = newClassForConnection.HttpsGetSessionIdForIssoS(Login, Password);
				//            Console.WriteLine(res[0]);
				//        }
				//        else
				//        {
				//            NewClassForConnection newClassForConnectionIOS = new NewClassForConnection(GetBinding(), endpoint_isso_s);
				//            //var interface_connection_IOS = newClassForConnectionIOS.CreateConnection();
				//            //interface_connection = new NewClassForConnection(GetBinding(), endpoint_isso_s);
				//            newClassForConnectionIOS.Endpoint.Binding.SendTimeout = TimeSpan.FromMilliseconds(20000);
				//            //var res = Task.Factory.FromAsync<string, string, string[]>(newClassForConnectionIOS.BeginHttpsGetSessionIdForIssoS, newClassForConnectionIOS.EndHttpsGetSessionIdForIssoS, Login, Password, null);
				//            Console.WriteLine(res[0]);
				//        }

				// ПОЛНОЦЕННО РАБОТАЮЩИЙ КЛАСС!!!! УРАААА!

				var clientIssoS = Device.RuntimePlatform == Device.Android
					? new ISSO_S_ClientBase(GetBinding(), endpointIssoS)
					: new ISSO_S_ClientBaseIOS(GetBinding(), endpointIssoS);
				var clientCommon = Device.RuntimePlatform == Device.Android
					? new Common_ClientBase(GetBinding(), endpointCommon)
					: new Common_ClientBaseIOS(GetBinding(), endpointCommon);


				string[] result;
				try
				{
					// Выставляем время на подключение
					clientIssoS.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsToWaitStart);
					result = clientIssoS.HttpsGetSessionIdForIssoS(Login, Password);
					if (result == null)
					{
						if (ct.IsCancellationRequested) return;
						MessageError =
							"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
						OnSyncFailed();
						return;
					}
				}
				catch
				{
					if (ct.IsCancellationRequested) return;
					MessageError =
						"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
					OnSyncFailed();
					return;
				}

				if (ct.IsCancellationRequested) return;
				// Получение разрешения и id со списком ИССО
				// Если ошибка не пустая, то останавливаем и пишем об ошибке
				var error = result[3];
				if (!error.Equals(""))
				{
					if (ct.IsCancellationRequested) return;
					MessageError = error;
					OnSyncFailed();
					return;
				}

				var id = new Guid(result[0]);
				var nameCurator = result[1];
				if (!Application.Current.Properties.TryGetValue("user", out var _)) Application.Current.Properties.Add("user", "");
				Application.Current.Properties["user"] = nameCurator;
				await Application.Current.SavePropertiesAsync();
				// Подключение к БД
				var connection = App.CreateDatabase();
				Device.BeginInvokeOnMainThread(() =>
				{
					MyLabelInfoSync.Text = "Получение ИССО...";
					((MasterDetailPage1Master) ((MasterDetailPage1) Application.Current.MainPage).Master).SetTextForUser(nameCurator);
				});
				Thread.Sleep(500);

				// Получение списка ИССО
				HttpsIsso[] issos;
				try
				{
					// Выставление времени
					clientCommon.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsToWait);
					issos = clientCommon.HttpsGetIssoList(id);
					if (issos == null)
					{
						if (ct.IsCancellationRequested) return;
						MessageError =
							"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
						OnSyncFailed();
						return;
					}
				}
				catch
				{
					if (ct.IsCancellationRequested) return;
					MessageError =
						"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
					OnSyncFailed();
					return;
				}

				if (ct.IsCancellationRequested) return;
				// Удаляем из БД 
				connection.Execute("delete from I_ISSO");
				int[] i = {1};
				foreach (var isso in issos)
				{
					Device.BeginInvokeOnMainThread(() =>
					{
						MyLabelInfoSync.Text = "Получение ИССО...\n[" + i[0] + " из " + issos.Length + "]";
						i[0]++;
					});
					var iIsso = new I_ISSO(isso);
					// Добавление ИССО в БД
					connection.InsertOrReplace(iIsso);
				}

				Device.BeginInvokeOnMainThread(() => { MyLabelInfoSync.Text = "Передача сведений на сервер..."; });
				Thread.Sleep(500);
				// Передача информации на сервер
				var ratingTable = from rating in connection.Table<RATING>() where rating.SYNC.Equals(false) select rating;
				var ratings = new List<RatingHttps>();
				foreach (var rating in ratingTable)
				{
					var rate = new RatingHttps
					{
						CIsso = rating.C_ISSO,
						CurrentRating = rating.CURRENTRATING,
						Latitude = rating.LATITUDE_RATING,
						Longitude = rating.LONGITUDE_RATING,
						RatingIsso = rating.RATINGS,
						RatingDate = rating.RATINGDATE,
						RatingDateEdit = rating.RATINGDATEEDIT,
						RatingExt = rating.COMMENTS,
						Offset = rating.OFFSET,
						CheckOut = rating.CHECKOUTOFPLAN
					};
					ratings.Add(rate);
				}

				//Отправка сведений на сервер
				try
				{
					if (ct.IsCancellationRequested) return;
					// Выставление времени для подключения
					clientIssoS.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsToWait);
					clientIssoS.HttpsSetRatingInfo(id, ratings.ToArray());
				}
				catch
				{
					if (ct.IsCancellationRequested) return;
					MessageError =
						"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
					OnSyncFailed();
					return;
				}


				// Передача имеющихся фотографий на сервер
				Device.BeginInvokeOnMainThread(() => { MyLabelInfoSync.Text = "Передача фотографий на сервер..."; });
				Thread.Sleep(500);
				i[0] = 1;
				foreach (var isso in issos)
					// отправление фотографий происходит по каждой записи
				foreach (var rating in ratings.Where(rating => rating.CIsso == isso.CIsso))
				{
					var photosRating = connection.Query<PHOTOS_RATING>(
						"select * from PHOTOS_RATING where C_ISSO=? and RATINGDATE=? and SYNC=?", rating.CIsso, rating.RatingDate, false);
					var photosToSend = new List<PhotoIssoS>();
					foreach (var photos in photosRating)
					{
						Device.BeginInvokeOnMainThread(() =>
						{
							MyLabelInfoSync.Text =
								$"Передача фотографий на сервер для ИССО №{isso.CIsso}\n[{i[0]} из {photosRating.Count}]";
						});
						var photo = new PhotoIssoS
						{
							CIsso = photos.C_ISSO,
							RatingDate = photos.RATINGDATE,
							PhotoDate = photos.PHOTODATE,
							Comment = photos.COMMENT,
							PhotoPath = photos.PHOTOPATH
						};
						//string path = photos.PHOTOPATH;
						//photo.PhotoPath = path.Substring(path.LastIndexOf('/') + 1);
						if (!photo.PhotoPath.Equals(""))
							try
							{
								var imgArray = File.ReadAllBytes(Path.Combine(App.PathToPhoto, photo.PhotoPath));
								photo.Photo = Convert.ToBase64String(imgArray);
							}
							catch (Exception)
							{
								//ex.ToString();
								photo.PhotoPath = "";
							}
						else
							photo.Photo = "";

						photosToSend.Add(photo);
						i[0]++;
					}

					try
					{
						if (ct.IsCancellationRequested) return;
						// Выставление времени для подключения
						clientIssoS.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsToWait);
						// Передача фотографий
						clientIssoS.HttpsSetPhotoIssoS(id, photosToSend.ToArray());
					}
					catch
					{
						if (ct.IsCancellationRequested) return;
						MessageError =
							"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
						OnSyncFailed();
						return;
					}
				}

				connection.Execute("update PHOTOS_RATING set SYNC=1 where SYNC=0");

				// Получаем значения рейтингов по коду CIsso и записываем в БД телефона
				Device.BeginInvokeOnMainThread(() => { MyLabelInfoSync.Text = "Получение сведений c сервера..."; });
				Thread.Sleep(500);

				i[0] = 1;
				foreach (var isso in issos)
				{
					Device.BeginInvokeOnMainThread(() =>
					{
						MyLabelInfoSync.Text =
							$"Получение сведений c сервера...\n[{i[0]} из {issos.Length}]";
					});
					RatingHttps[] ratingHttps;
					try
					{
						if (ct.IsCancellationRequested) return;
						// Выставление времени для подключения
						clientIssoS.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsToWait);
						ratingHttps = clientIssoS.HttpsGetRatingInfo(id, isso.CIsso);
						if (ratingHttps == null)
						{
							if (ct.IsCancellationRequested) return;
							MessageError =
								"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
							OnSyncFailed();
							return;
						}
					}
					catch
					{
						if (ct.IsCancellationRequested) return;
						MessageError =
							"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
						OnSyncFailed();
						return;
					}

					if (ct.IsCancellationRequested) return;
					// Удаление данных из таблицы RATING
					connection.Execute("delete from RATING where C_ISSO = ?", isso.CIsso);
					foreach (var rate in ratingHttps)
					{
						var rating = new RATING(rate);
						connection.InsertOrReplace(rating);
					}

					// Отправляем инфу о фотографиях на устройстве
					var photos = connection.Query<PHOTOS_RATING>("select * from PHOTOS_RATING where C_ISSO=?", isso.CIsso);
					var photosToSend = new List<PhotoIssoS>();
					foreach (var photo in photos)
					{
						var photoIssoS = new PhotoIssoS
						{
							CIsso = photo.C_ISSO,
							RatingDate = photo.RATINGDATE,
							PhotoDate = photo.PHOTODATE,
							PhotoPath = "",
							Photo = ""
						};
						photosToSend.Add(photoIssoS);
					}

					try
					{
						if (ct.IsCancellationRequested) return;
						// Выставление времени для подключения
						clientIssoS.Endpoint.Binding.SendTimeout = TimeSpan.FromSeconds(SecondsToWait);
						// Получение информации по непереданным фотографиям
						clientIssoS.HttpsReceiveInfoAboutPhotoForIssoS(id, isso.CIsso, photosToSend.ToArray());
					}
					catch
					{
						if (ct.IsCancellationRequested) return;
						MessageError =
							"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
						OnSyncFailed();
						return;
					}

					foreach (var rating in ratingHttps.Where(rating => rating.CIsso == isso.CIsso))
					{
						PhotoIssoS[] receivedPhotos;
						try
						{
							if (ct.IsCancellationRequested) return;
							// Теперь получаем недостающие фотографии
							receivedPhotos = clientIssoS.HttpsGetPhotoIssoS(id, isso.CIsso, rating.RatingDate);
							if (receivedPhotos == null)
							{
								if (ct.IsCancellationRequested) return;
								MessageError = "Нет подключения к серверу";
								OnSyncFailed();
								return;
							}
						}
						catch
						{
							if (ct.IsCancellationRequested) return;
							MessageError =
								"Время сессии истекло или нет подключения к серверу. Проверьте настройки подключения или обратитесь к администратору сервера.";
							OnSyncFailed();
							return;
						}

						if (ct.IsCancellationRequested) return;
						var localIndex = 0;
						foreach (var ph in receivedPhotos)
						{
							if (!ph.PhotoPath.Equals("IsAlreadyOnPhone"))
							{
								var photosRating = new PHOTOS_RATING
								{
									C_ISSO = ph.CIsso,
									RATINGDATE = ph.RatingDate,
									PHOTODATE = ph.PhotoDate,
									COMMENT = ph.Comment
								};
								if (ph.PhotoPath.Equals(""))
								{
									var date1 = DateTime.Now.ToString("yyyyMMdd_HHmmss");
									try
									{
										var name = $"IMG_{date1}_{localIndex}.jpeg";
										var jpgFilename = Path.Combine(App.PathToPhoto, name);
										var img = Convert.FromBase64String(ph.Photo);
										if (!Directory.Exists(App.PathToPhoto))
										{
											Directory.CreateDirectory(App.PathToPhoto);
										}

										File.WriteAllBytes(jpgFilename, img);
										photosRating.PHOTOPATH = name;
									}
									catch (Exception ex)
									{
										Console.WriteLine(ex.ToString());
									}
								}
								else
								{
									photosRating.PHOTOPATH = "";
								}

								photosRating.SYNC = true;
								connection.Insert(photosRating);
							}

							localIndex++;
						}

						i[0]++;
					}
				}

				connection.Execute("Update RATING set SYNC = ? where SYNC = ?", true, false);
				//client_common.HttpsCloseSessionAsync(id);
				clientCommon.HttpsCloseSession(id);
				watch.Stop();
				ElapsedMs = watch.ElapsedMilliseconds;
				OnSyncSucceeded();
			}, ct);
			return 0;
		}

		//private void Client_common_HttpsCloseSessionCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e) { }

		///// <summary>
		///// Handler для метода GetIssoList
		///// </summary>
		///// <param name="sender"></param>
		///// <param name="e"></param>
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

		//// Handler для метода GetSessionIdForIssoS
		//private void Client_isso_s_HttpsGetSessionIdForIssoSCompleted(object sender, HttpsGetSessionIdForIssoSCompletedEventArgs e)
		//{

		//    if (e.Error != null)
		//    {
		//        messageError = "Нет подключения к серверу. Возможно, в настройках был неправильно указан адрес или нет подключения к интернету";
		//        success = false;
		//    }
		//    else if (e.Cancelled)
		//    {
		//        msg = "Request was cancelled.";
		//        success = false;
		//    }
		//    else if (e.Result[0] == null)
		//    {
		//        messageError = e.Result[3];
		//        success = false;
		//    }
		//    else
		//    {
		//        output = e.Result;
		//        success = true;
		//    }
		//    semaphore.Release();
		//}

		//// Handler для метода SetRatingInfo
		//private void Client_isso_s_HttpsSetRatingInfoCompleted(object sender, HttpsSetRatingInfoCompletedEventArgs e)
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

		//// Handler для метода SetRatingPhoto
		//private void Client_isso_s_HttpsSetPhotoIssoSCompleted(object sender, HttpsSetPhotoIssoSCompletedEventArgs e)
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

		//// Handler для метода GetRatingInfo
		//private void Client_isso_s_HttpsGetRatingInfoCompleted(object sender, HttpsGetRatingInfoCompletedEventArgs e)
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

		//private void Client_isso_s_HttpsReceiveInfoAboutPhotoForIssoSCompleted(object sender, HttpsReceiveInfoAboutPhotoForIssoSCompletedEventArgs e)
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

		//private void Client_isso_s_HttpsGetPhotoIssoSCompleted(object sender, HttpsGetPhotoIssoSCompletedEventArgs e)
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

		public static BasicHttpsBinding GetBinding()
		{
			// Инициализация безопасного биндинга
			var basicBinding = new BasicHttpsBinding(BasicHttpsSecurityMode.Transport)
				//BasicHttpBinding basicBinding = new BasicHttpBinding(BasicHttpSecurityMode.None)
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
}

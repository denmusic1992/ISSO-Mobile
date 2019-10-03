using Rg.Plugins.Popup.Pages;
using Rg.Plugins.Popup.Services;
using System;
using System.ServiceModel;
using System.Threading;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_S
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class SyncPage : PopupPage
	{
        /// <summary>
        /// Логин
        /// </summary>
        private string Login = "";
        /// <summary>
        /// Пароль
        /// </summary>
        private string Password = "";
        /// <summary>
        /// Переменная для остановки асинхронного процесса
        /// </summary>
        private Semaphore semaphore = new Semaphore(0, 15);
        /// <summary>
        /// Переменная вывода функций
        /// </summary>
        //private object output;
        /// <summary>
        /// Успех при подключении
        /// </summary>
        //private bool success = false;
        /// <summary>
        /// Синхронизация успешная
        /// </summary>
        public event EventHandler Sync_Succeeded;
        /// <summary>
        /// Синхронизация с ошибкой
        /// </summary>
        public event EventHandler Sync_Failed;
        /// <summary>
        /// Вывод ошибки, если таковая есть
        /// </summary>
        public string messageError = "";
        /// <summary>
        /// Время ожидания до ошибки
        /// </summary>
        private const int wait_millis = 30000;

        //string msg = "";


        /// <summary>
        /// Конструктор всплывающего окна
        /// </summary>
        /// <param name="Login"></param>
        /// <param name="Password"></param>
        public SyncPage(string Login, string Password)
        {
            InitializeComponent();
            this.Login = Login;
            this.Password = Password;
            Sync();
        }

        /// <summary>
        /// Синхронизация
        /// </summary>
        /// <returns></returns>
        public int Sync()
        {
            Task.Run(() =>
            {
                // Адрес с общим интерфейсом
                Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = "Получение разрешения..."; });
                Thread.Sleep(1000);
                System.Net.ServicePointManager.ServerCertificateValidationCallback = new System.Net.Security.RemoteCertificateValidationCallback(delegate { return true; });
                String address_common = String.Format("https://{0}:{1}{2}", Convert.ToString(App.Current.Properties["address"]), Convert.ToString(App.Current.Properties["port"]), "/ais7UpdateServerSecureBinding/Common");
                EndpointAddress endpoint_common = new EndpointAddress(address_common);
                //ChannelFactory<Iais7MobileCommonServerCore> factory_common = new ChannelFactory<Iais7MobileCommonServerCore>(GetBinding(), endpoint_common);
                // Адрес с интерфейсом для ISSO-S
                String address_isso_s = String.Format("https://{0}:{1}{2}", Convert.ToString(App.Current.Properties["address"]), Convert.ToString(App.Current.Properties["port"]), "/ais7UpdateServerSecureBinding/IssoS");
                EndpointAddress endpoint_isso_s = new EndpointAddress(address_isso_s);

                // Инициализация клиентов для подключения к серверу
                //Ais7MobileCommonServerCoreXamarinClient client_common = new Ais7MobileCommonServerCoreXamarinClient(GetBinding(), endpoint_common);
                //Ais7MobileIssoSServerCoreXamarinClient client_isso_s = new Ais7MobileIssoSServerCoreXamarinClient(GetBinding(), endpoint_isso_s);


                // Создание event handler'ов
//                client_isso_s.HttpsGetSessionIdForIssoSCompleted += Client_isso_s_HttpsGetSessionIdForIssoSCompleted;
//                client_common.HttpsGetIssoListCompleted += Client_common_HttpsGetIssoListCompleted;
//                client_isso_s.HttpsSetRatingInfoCompleted += Client_isso_s_HttpsSetRatingInfoCompleted;
//                client_isso_s.HttpsSetPhotoIssoSCompleted += Client_isso_s_HttpsSetPhotoIssoSCompleted;
//                client_isso_s.HttpsGetRatingInfoCompleted += Client_isso_s_HttpsGetRatingInfoCompleted;
//                client_isso_s.HttpsReceiveInfoAboutPhotoForIssoSCompleted += Client_isso_s_HttpsReceiveInfoAboutPhotoForIssoSCompleted;
//                client_isso_s.HttpsGetPhotoIssoSCompleted += Client_isso_s_HttpsGetPhotoIssoSCompleted;

//                //


//                client_isso_s.HttpsGetSessionIdForIssoSAsync(Login, Password);
//                success = false;
//                semaphore.WaitOne(wait_millis);
//                if (!success)
//                {
//                    OnSyncFailed();
//                    return;
//                }
//                String[] result = (String[])output;
//                // Получение разрешения и id со списком ИССО
//                var error = result[3].ToString();
//                Guid id = new Guid(result[0].ToString());
//                String nameCurator = result[1].ToString();
//                int step = Convert.ToInt32(result[2].ToString());
//                if (!App.Current.Properties.TryGetValue("user", out object user))
//                {
//                    App.Current.Properties.Add("user", "");
//                }
//                App.Current.Properties["user"] = nameCurator;
//                App.Current.SavePropertiesAsync();
//                // Подключение к БД
//                var connection = App.CreateDatabase();
//                Device.BeginInvokeOnMainThread(() =>
//                {
//                    LabelInfoSync.Text = "Получение ИССО...";
//                    ((App.Current.MainPage as MasterDetailPage1).Master as MasterDetailPage1Master).SetTextForUser(nameCurator);
//                });
//                Thread.Sleep(500);
//                // Получение самих ИССО
//                client_common.HttpsGetIssoListAsync(id);
//                success = false;
//                semaphore.WaitOne(wait_millis);
//                if (!success)
//                {
//                    OnSyncFailed();
//                    return;
//                }
//                // Удаление предыдущих записей
//                connection.Execute("delete from I_ISSO");
//                HttpsIsso[] issos = (HttpsIsso[])output;
//                var i = 1;
//                foreach (HttpsIsso isso in issos)
//                {
//                    Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = "Получение ИССО...\n[" + i + " из " + issos.Count() + "]"; i++; });
//                    I_ISSO i_isso = new I_ISSO(isso);
//                    // Добавление ИССО в БД
//                    connection.InsertOrReplace(i_isso);
//                }

//                Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = "Передача сведений на сервер..."; });
//                Thread.Sleep(500);
//                // Передача информации на сервер
//                var rating_table = from RATING in connection.Table<RATING>() where RATING.SYNC.Equals(false) select RATING;
//                List<RatingHttps> ratings = new List<RatingHttps>();
//                foreach (RATING rating in rating_table)
//                {
//                    RatingHttps rate = new RatingHttps
//                    {
//                        CIsso = rating.C_ISSO,
//                        CurrentRating = rating.CURRENTRATING,
//                        Latitude = rating.LATITUDE_RATING,
//                        Longitude = rating.LONGITUDE_RATING,
//                        RatingIsso = rating.RATINGS,
//                        RatingDate = rating.RATINGDATE,
//                        RatingDateEdit = rating.RATINGDATEEDIT,
//                        RatingExt = rating.COMMENTS,
//                        Offset = rating.OFFSET,
//                        CheckOut = rating.CHECKOUTOFPLAN
//                    };
//                    ratings.Add(rate);
//                }
//                client_isso_s.HttpsSetRatingInfoAsync(id, ratings.ToArray());
//                success = false;
//                semaphore.WaitOne(wait_millis);
//                if (!success)
//                {
//                    OnSyncFailed();
//                    return;
//                }

//                // Передача имеющихся фотографий на сервер
//                Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = "Передача фотографий на сервер..."; });
//                Thread.Sleep(500);
//                i = 1;
//                foreach (HttpsIsso isso in issos)
//                {
//                    var photos_rating = connection.Query<PHOTOS_RATING>("select * from PHOTOS_RATING where C_ISSO=? and SYNC=?", isso.CIsso, false);
//                    List<PhotoIssoS> photos_to_send = new List<PhotoIssoS>();
//                    foreach (PHOTOS_RATING photos in photos_rating)
//                    {
//                        Device.BeginInvokeOnMainThread(() =>
//                        {
//                            LabelInfoSync.Text = String.Format("Передача фотографий на сервер для ИССО №{0}\n[{1} из {2}]",
//                                isso.CIsso, i, photos_rating.Count());
//                        });
//                        PhotoIssoS photo = new PhotoIssoS
//                        {
//                            CIsso = photos.C_ISSO,
//                            RatingDate = photos.RATINGDATE,
//                            PhotoDate = photos.PHOTODATE,
//                            Comment = photos.COMMENT
//                        };
//                        string path = photos.PHOTOPATH;
//                        photo.PhotoPath = path.Substring(path.LastIndexOf('/') + 1);
//                        if (!path.Equals(""))
//                        {
//                            try
//                            {
//                                byte[] img_array = System.IO.File.ReadAllBytes(path);
//                                photo.Photo = Convert.ToBase64String(img_array);
//                            }
//                            catch (Exception ex)
//                            {
//                                ex.ToString();
//                                photo.PhotoPath = "";
//                            }
//                        }
//                        else
//                            photo.Photo = "";
//                        photos_to_send.Add(photo);
//                        i++;
//                    }
//                    client_isso_s.HttpsSetPhotoIssoSAsync(id, photos_to_send.ToArray());
//                    success = false;
//                    semaphore.WaitOne(wait_millis);
//                    if (!success)
//                    {
//                        OnSyncFailed();
//                        return;
//                    }
//                    connection.Execute("update PHOTOS_RATING set SYNC=1 where SYNC=0");
//                }

//                // Получаем значения рейтингов по коду CIsso и записываем в БД телефона
//                Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = "Получение сведений c сервера..."; });
//                Thread.Sleep(500);

//                i = 1;
//                foreach (HttpsIsso isso in issos)
//                {
//                    Device.BeginInvokeOnMainThread(() => { LabelInfoSync.Text = String.Format("Получение сведений c сервера...\n[{0} из {1}]", i, issos.Count()); });
//                    client_isso_s.HttpsGetRatingInfoAsync(id, isso.CIsso);
//                    success = false;
//                    semaphore.WaitOne(wait_millis);
//                    if (!success)
//                    {
//                        OnSyncFailed();
//                        return;
//                    }
//                    RatingHttps[] ratingHttps = (RatingHttps[])output;
//                    // Удаление данных из таблицы RATING
//                    var res = connection.Execute("delete from RATING where C_ISSO = ?", isso.CIsso);
//                    foreach (RatingHttps rate in ratingHttps)
//                    {
//                        RATING rating = new RATING(rate);
//                        connection.InsertOrReplace(rating);
//                    }

//                    // Отправляем инфу о фотографиях на устройстве
//                    var photos = connection.Query<PHOTOS_RATING>("select * from PHOTOS_RATING where C_ISSO=?", isso.CIsso);
//                    List<PhotoIssoS> photos_to_send = new List<PhotoIssoS>();
//                    foreach (PHOTOS_RATING photo in photos)
//                    {
//                        PhotoIssoS photoIssoS = new PhotoIssoS
//                        {
//                            CIsso = photo.C_ISSO,
//                            RatingDate = photo.RATINGDATE,
//                            PhotoDate = photo.PHOTODATE,
//                            PhotoPath = "",
//                            Photo = ""
//                        };
//                        photos_to_send.Add(photoIssoS);
//                    }

//                    client_isso_s.HttpsReceiveInfoAboutPhotoForIssoSAsync(id, isso.CIsso, photos_to_send.ToArray());
//                    success = false;
//                    semaphore.WaitOne(wait_millis);
//                    if (!success)
//                    {
//                        OnSyncFailed();
//                        return;
//                    }

//                    // Теперь получаем недостающие фотографии
//                    client_isso_s.HttpsGetPhotoIssoSAsync(id, isso.CIsso);
//                    success = false;
//                    semaphore.WaitOne(wait_millis);
//                    if (!success)
//                    {
//                        OnSyncFailed();
//                        return;
//                    }
//                    var received_photos = (PhotoIssoS[])output;
//                    int local_index = 0;
//                    foreach (PhotoIssoS ph in received_photos)
//                    {
//                        if (!ph.PhotoPath.Equals("IsAlreadyOnPhone"))
//                        {
//#pragma warning disable IDE0017 // Упростите инициализацию объекта
//                            PHOTOS_RATING photos_rating = new PHOTOS_RATING();
//#pragma warning restore IDE0017 // Упростите инициализацию объекта
//                            photos_rating.C_ISSO = ph.CIsso;
//                            photos_rating.RATINGDATE = ph.RatingDate;
//                            photos_rating.PHOTODATE = ph.PhotoDate;
//                            photos_rating.COMMENT = ph.Comment;
//                            if (ph.PhotoPath.Equals(""))
//                            {
//                                var documentsDirectory = Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/Pictures/ISSO-S/";
//                                var date1 = DateTime.Now.ToString("yyyyMMdd_HHmmss");
//                                try
//                                {
//                                    string name = String.Format("IMG_{0}_{1}.jpeg", date1, local_index);
//                                    string jpgFilename = Path.Combine(documentsDirectory, name);
//                                    byte[] img = Convert.FromBase64String(ph.Photo);
//                                    if (!Directory.Exists(documentsDirectory))
//                                    {
//                                        var info = Directory.CreateDirectory(documentsDirectory);
//                                        var str = info.Extension;
//                                    }
//                                    File.WriteAllBytes(jpgFilename, img);
//                                    photos_rating.PHOTOPATH = name;
//                                }
//                                catch (Exception ex)
//                                {
//                                    ex.ToString();
//                                }
//                            }
//                            else
//                            {
//                                photos_rating.PHOTOPATH = "";
//                            }
//                            photos_rating.SYNC = true;
//                            int result111 = connection.Insert(photos_rating);
//                        }
//                        local_index++;
//                    }
//                    i++;
//                }
//                var not_synced = connection.Execute("Update RATING set SYNC = ? where SYNC = ?", true, false);
//                client_common.HttpsCloseSessionAsync(id);
//                OnSyncSucceeded();
            });
            return 0;
        }



        private void OnSyncSucceeded()
        {
            Sync_Succeeded?.Invoke(this, EventArgs.Empty);
        }

        private void OnSyncFailed()
        {
            Sync_Failed?.Invoke(this, EventArgs.Empty);
        }

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

        private async void OnClose(object sender, EventArgs e)
        {
            messageError = "";
            await PopupNavigation.Instance.PopAsync();
            semaphore.Release();
        }

        protected override void OnAppearingAnimationEnd()
        {
            base.OnAppearingAnimationEnd();
            Content.FadeTo(1);
        }

        protected override void OnDisappearingAnimationBegin()
        {
            base.OnDisappearingAnimationBegin();
            Content.FadeTo(1);
        }


        public static BasicHttpsBinding GetBinding()
        {
            // Инициализация безопасного биндинга
            BasicHttpsBinding basicBinding = new BasicHttpsBinding(BasicHttpsSecurityMode.Transport)
            {
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
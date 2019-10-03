using ISSO_S.Interfaces;
using System;
using System.ServiceModel;
using System.ServiceModel.Channels;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S.Generated
{
    /// <summary>
    /// Класс для обращения к серверу ISSO-S для Android
    /// </summary>
    public class ISSO_S_ClientBase : ClientBase<IAis7MobileIssoSServerCoreXamarin>, IAis7MobileIssoSServerCoreXamarin
    {
        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="binding">Биндинг, настроенный для соединения</param>
        /// <param name="endpointAddress">Адрес интерфейса для подключения</param>
        public ISSO_S_ClientBase(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }


        public PhotoIssoS[] HttpsGetPhotoIssoS(Guid id, int c_isso, long rating_date)
        {
            return Channel.HttpsGetPhotoIssoS(id, c_isso, rating_date);
        }

        public RatingHttps[] HttpsGetRatingInfo(Guid id, int isso)
        {
            return Channel.HttpsGetRatingInfo(id, isso);
        }

        public string[] HttpsGetSessionIdForIssoS(string user, string pass)
        {
            return Channel.HttpsGetSessionIdForIssoS(user, pass);
        }

        public int HttpsReceiveInfoAboutPhotoForIssoS(Guid id, int c_isso, PhotoIssoS[] photos)
        {
            return Channel.HttpsReceiveInfoAboutPhotoForIssoS(id, c_isso, photos);
        }

        public int HttpsSetPhotoIssoS(Guid id, PhotoIssoS[] photos)
        {
            return Channel.HttpsSetPhotoIssoS(id, photos);
        }

        public int HttpsSetRatingInfo(Guid id, RatingHttps[] ratings)
        {
            return Channel.HttpsSetRatingInfo(id, ratings);
        }
    }
}

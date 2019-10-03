using ISSO_S.Interfaces;
using System;
using System.ServiceModel;
using System.ServiceModel.Channels;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S.Generated
{
    /// <summary>
    /// Метод для обращения к серверу ISSO-S для iOS
    /// </summary>
    public class ISSO_S_ClientBaseIOS : ISSO_S_ClientBase
    {
        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="binding">Биндинг, настроенный для соединения</param>
        /// <param name="endpointAddress">Адрес интерфейса для подключения</param>
        public ISSO_S_ClientBaseIOS(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }

        /// <summary>
        /// Метод получения канала, здесь используется экземпляр созданного специально для iOS класса канала 
        /// </summary>
        /// <returns></returns>
        protected override IAis7MobileIssoSServerCoreXamarin CreateChannel()
        {
            return new ISSO_S_ClientBaseIOSChannel(this);
        }
        
        /// <summary>
        /// Класс канала для iOS
        /// </summary>
        private class ISSO_S_ClientBaseIOSChannel : ChannelBase<IAis7MobileIssoSServerCoreXamarin>, IAis7MobileIssoSServerCoreXamarin
        {
            /// <summary>
            /// Конструктор
            /// </summary>
            /// <param name="client"></param>
            public ISSO_S_ClientBaseIOSChannel(ISSO_S_ClientBaseIOS client) : base(client) { }

            public PhotoIssoS[] HttpsGetPhotoIssoS(Guid id, int c_isso, long rating_date)
            {
                return (PhotoIssoS[])Invoke("HttpsGetPhotoIssoS", new object[] { id, c_isso, rating_date });
            }

            public RatingHttps[] HttpsGetRatingInfo(Guid id, int isso)
            {
                return (RatingHttps[])Invoke("HttpsGetRatingInfo", new object[] { id, isso });
            }

            public string[] HttpsGetSessionIdForIssoS(string user, string pass)
            {
                return (string[])Invoke("HttpsGetSessionIdForIssoS", new object[] { user, pass });
            }

            public int HttpsReceiveInfoAboutPhotoForIssoS(Guid id, int c_isso, PhotoIssoS[] photos)
            {
                return (int)Invoke("HttpsReceiveInfoAboutPhotoForIssoS", new object[] { id, c_isso, photos });
            }

            public int HttpsSetPhotoIssoS(Guid id, PhotoIssoS[] photos)
            {
                return (int)Invoke("HttpsSetPhotoIssoS", new object[] { id, photos });
            }

            public int HttpsSetRatingInfo(Guid id, RatingHttps[] ratings)
            {
                return (int)Invoke("HttpsSetRatingInfo", new object[] { id, ratings });
            }
        }
    }
}

using System;
using System.ServiceModel;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S.Interfaces
{

    [ServiceContract]
    public interface IAis7MobileIssoSServerCoreXamarin
    {
        /// <summary>
        /// Получение сессии для зарегистрированных пользователей в приложении ISSO-S
        /// </summary>
        /// <param name="user"></param>
        /// <param name="pass"></param>
        /// <returns></returns>
        [OperationContract]
        String[] HttpsGetSessionIdForIssoS(string user, string pass);

        /// <summary>
        /// Отправка списка рейтингов для определенного сооружения isso
        /// </summary>
        /// <param name="id"></param>
        /// <param name="isso"></param>
        /// <returns></returns>
        [OperationContract]
        [ServiceKnownType(typeof(RatingHttps))]
        RatingHttps[] HttpsGetRatingInfo(Guid id, int isso);

        /// <summary>
        /// Получение списка рейтингов с телефона
        /// </summary>
        /// <param name="id"></param>
        /// <param name="ratings"></param>
        /// <returns></returns>
        [OperationContract]
        int HttpsSetRatingInfo(Guid id, RatingHttps[] ratings);


        /// <summary>
        /// Получение сервером фотографий с приложения Isso-S
        /// </summary>
        /// <param name="id"></param>
        /// <param name="photos"></param>
        /// <returns></returns>
        [OperationContract]
        int HttpsSetPhotoIssoS(Guid id, PhotoIssoS[] photos);

        /// <summary>
        /// Проверить каких фотографий нет на устройстве
        /// </summary>
        /// <param name="id"></param>
        /// <param name="c_isso"></param>
        /// <param name="photos"></param>
        /// <returns></returns>
        [OperationContract]
        int HttpsReceiveInfoAboutPhotoForIssoS(Guid id, int c_isso, PhotoIssoS[] photos);

        /// <summary>
        /// Получение фотографий для приложения Isso-S
        /// </summary>
        /// <param name="id"></param>
        /// <param name="c_isso"></param>
        /// <param name="download"></param>
        /// <returns></returns>
        [OperationContract]
        [ServiceKnownType(typeof(PhotoIssoS))]
        PhotoIssoS[] HttpsGetPhotoIssoS(Guid id, int c_isso, long rating_date);
    }
}

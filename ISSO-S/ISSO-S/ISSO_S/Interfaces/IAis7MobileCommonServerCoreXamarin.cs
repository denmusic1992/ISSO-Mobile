using System;
using System.ServiceModel;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S.Interfaces
{
    [ServiceContract]
    public interface IAis7MobileCommonServerCoreXamarin
    {
        /// <summary>
        /// Получение сессии для зарегистрированных пользователей
        /// </summary>
        /// <param name="user"></param>
        /// <param name="pass"></param>
        /// <returns></returns>
        [OperationContract]
        String[] HttpsGetSessionId(string user, string pass);

        /// <summary>
        /// Отправка списка сооружений в соответствии с присвенной сессией
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [OperationContract]
        HttpsIsso[] HttpsGetIssoList(Guid id);

        /// <summary>
        /// Закрытие сессии
        /// </summary>
        /// <param name="id"></param>
        [OperationContract]
        void HttpsCloseSession(Guid id);

        /// <summary>
        /// Получение информации по ошибке
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [OperationContract]
        string HttpsGetMessage(Guid id);
    }
}

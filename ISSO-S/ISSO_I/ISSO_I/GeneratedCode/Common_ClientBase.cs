using System;
using System.ServiceModel;
using System.ServiceModel.Channels;
using CommonClassesLibrary;
using ISSO_S.Interfaces;

namespace ISSO_I.Generated
{
    /// <summary>
    /// Класс для подулюения к серверу Common
    /// </summary>
    public class Common_ClientBase : ClientBase<IAis7MobileCommonServerCoreXamarin>, IAis7MobileCommonServerCoreXamarin
    {
        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="binding">Биндинг, настроенный для соединения</param>
        /// <param name="endpointAddress">Адрес интерфейса для подключения</param>
        public Common_ClientBase(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }

        public void HttpsCloseSession(Guid id)
        {
            Channel.HttpsCloseSession(id);
        }

        public DBHelper.HttpsIsso[] HttpsGetIssoList(Guid id)
        {
            return base.Channel.HttpsGetIssoList(id);
        }

        public string HttpsGetMessage(Guid id)
        {
            return Channel.HttpsGetMessage(id);
        }

        public string[] HttpsGetSessionId(string user, string pass)
        {
            return Channel.HttpsGetSessionId(user, pass);
        }
    }
}

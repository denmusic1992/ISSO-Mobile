using CommonClassesLibrary;
using ISSO_S.Interfaces;
using System;
using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Channels;
using System.Text;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S.Generated
{
    public class Common_ClientBaseIOS : Common_ClientBase
    {
        public Common_ClientBaseIOS(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }

        protected override IAis7MobileCommonServerCoreXamarin CreateChannel()
        {
            return new Common_ClientBaseIOSChannel(this);
        }

        private class Common_ClientBaseIOSChannel : ChannelBase<IAis7MobileCommonServerCoreXamarin>, IAis7MobileCommonServerCoreXamarin
        {
            public Common_ClientBaseIOSChannel(Common_ClientBaseIOS client) : base(client) { }

            public void HttpsCloseSession(Guid id)
            {
                Invoke("HttpsCloseSession", new object[] { id });
            }

            public HttpsIsso[] HttpsGetIssoList(Guid id)
            {
                return (HttpsIsso[])Invoke("HttpsGetIssoList", new object[] { id });
            }

            public string HttpsGetMessage(Guid id)
            {
                return (string)Invoke("HttpsGetMessage", new object[] { id });
            }

            public string[] HttpsGetSessionId(string user, string pass)
            {
                return (string[])Invoke("HttpsGetSessionId", new object[] { user, pass });
            }
        }
    }
}

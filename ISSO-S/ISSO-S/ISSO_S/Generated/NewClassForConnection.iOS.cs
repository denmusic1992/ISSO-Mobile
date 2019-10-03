using Ais7IssoSServerCore;
using Ais7MobileCommonServerCore;
using ISSO_S.Interfaces;
using System;
using System.ServiceModel;
using System.ServiceModel.Channels;

namespace ISSO_S.Generated
{

    public class NewClassForConnectionIOS : NewClassForConnection
    {
        

        public NewClassForConnectionIOS() : base() { }

        public NewClassForConnectionIOS(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }

        public NewClassForConnectionIOS(string endpoint) : base(endpoint) { }

        protected override IAis7MobileIssoSServerCoreXamarin CreateChannel()
        {
            return new NewClassForConnectionChannel(this);
        }

        class NewClassForConnectionChannel : ChannelBase<IAis7MobileIssoSServerCoreXamarin>, IAis7MobileIssoSServerCoreXamarin
        {
            public NewClassForConnectionChannel(NewClassForConnectionIOS client) : base(client) { }

            public PhotoIssoS[] HttpsGetPhotoIssoS(Guid id, int c_isso)
            {
                throw new NotImplementedException();
            }

            public RatingHttps[] HttpsGetRatingInfo(Guid id, int isso)
            {
                throw new NotImplementedException();
            }

            public string[] HttpsGetSessionIdForIssoS(string user, string pass)
            {
                return (string[])Invoke("HttpsGetSessionIdForIssoS", new object[] { user, pass });
            }

            public int HttpsReceiveInfoAboutPhotoForIssoS(Guid id, int c_isso, PhotoIssoS[] photos)
            {
                throw new NotImplementedException();
            }

            public int HttpsSetPhotoIssoS(Guid id, PhotoIssoS[] photos)
            {
                throw new NotImplementedException();
            }

            public int HttpsSetRatingInfo(Guid id, RatingHttps[] ratings)
            {
                throw new NotImplementedException();
            }
        }

        //public IAsyncResult BeginHttpsGetSessionIdForIssoS(string user, string pass, AsyncCallback callback, object asyncState)
        //{
        //    return base.Channel.BeginHttpsGetSessionIdForIssoS(user, pass, callback, asyncState);
        //}

        //public string[] EndHttpsGetSessionIdForIssoS(IAsyncResult result)
        //{
        //    return base.Channel.EndHttpsGetSessionIdForIssoS(result);
        //}

        //private class NewClassForConnectionChannel : ChannelBase<IAis7MobileIssoSServerCoreXamarinIOS>, IAis7MobileIssoSServerCoreXamarinIOS
        //{
        //    public NewClassForConnectionChannel(ClientBase<IAis7MobileIssoSServerCoreXamarinIOS> client) : base(client) { }

        //    public string[] HttpsGetSessionIdForIssoS(string user, string pass)
        //    {
        //        return (string[])base.Invoke("HttpsGetSessionIdForIssoS", new object[] { user, pass });
        //    }

        //    public IAsyncResult BeginHttpsGetSessionIdForIssoS(string user, string pass, AsyncCallback callback, object asyncState)
        //    {
        //        object[] _args = new object[2];
        //        _args[0] = user;
        //        _args[1] = pass;
        //        return (IAsyncResult)base.BeginInvoke("HttpsGetSessionIdForIssoS", _args, callback, asyncState);
        //    }

        //    public string[] EndHttpsGetSessionIdForIssoS(IAsyncResult asyncResult)
        //    {
        //        object[] _args = new object[0];
        //        return (string[])base.EndInvoke("HttpsGetSessionIdForIssoS", _args, asyncResult);
        //    }
        //}
    }
}

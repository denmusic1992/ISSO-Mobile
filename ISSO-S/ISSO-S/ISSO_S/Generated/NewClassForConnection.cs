using Ais7IssoSServerCore;
using Ais7MobileCommonServerCore;
using ISSO_S.Interfaces;
using System;
using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Channels;
using System.Text;
using System.Threading.Tasks;

namespace ISSO_S.Generated
{
    public class NewClassForConnection : ClientBase<IAis7MobileIssoSServerCoreXamarin>, IAis7MobileIssoSServerCoreXamarin
    {
        public NewClassForConnection() : base() { }

        public NewClassForConnection(Binding binding, EndpointAddress endpointAddress) : base(binding, endpointAddress) { }

        public NewClassForConnection(string endpoint) : base(endpoint) { }


        public string[] HttpsGetSessionIdForIssoS(string user, string pass)
        {
            return base.Channel.HttpsGetSessionIdForIssoS(user, pass);
        }

        public RatingHttps[] HttpsGetRatingInfo(Guid id, int isso)
        {
            throw new NotImplementedException();
        }

        public int HttpsSetRatingInfo(Guid id, RatingHttps[] ratings)
        {
            throw new NotImplementedException();
        }

        public int HttpsSetPhotoIssoS(Guid id, PhotoIssoS[] photos)
        {
            throw new NotImplementedException();
        }

        public int HttpsReceiveInfoAboutPhotoForIssoS(Guid id, int c_isso, PhotoIssoS[] photos)
        {
            throw new NotImplementedException();
        }

        public PhotoIssoS[] HttpsGetPhotoIssoS(Guid id, int c_isso)
        {
            throw new NotImplementedException();
        }

        //private class NewClassForConnectionChannel : ChannelBase<IAis7MobileIssoSServerCoreXamarin>, IAis7MobileIssoSServerCoreXamarin
        //{
        //    public NewClassForConnectionChannel(ClientBase<IAis7MobileIssoSServerCoreXamarin> client) : base(client) { }

        //    public string[] HttpsGetSessionIdForIssoS(string user, string pass)
        //    {
        //        return (string[])base.Invoke("HttpsGetSessionIdForIssoS", new object[] { user, pass });
        //    }

        //    public IAsyncResult BeginHttpsGetSessionIdForIssoS(string user, string pass, AsyncCallback callback, object asyncState)
        //    {
        //        object[] _args = new object[2];
        //        _args[0] = user;
        //        _args[1] = pass;
        //        return BeginInvoke("HttpsGetSessionIdForIssoS", _args, callback, asyncState);
        //    }

        //    public string[] EndHttpsGetSessionIdForIssoS(IAsyncResult asyncResult)
        //    {
        //        object[] _args = new object[0];
        //        return (string[]) EndInvoke("HttpsGetSessionIdForIssoS", _args, asyncResult);
        //    }

        //    public RatingHttps[] HttpsGetRatingInfo(Guid id, int isso)
        //    {
        //        throw new NotImplementedException();
        //    }

        //    public int HttpsSetRatingInfo(Guid id, RatingHttps[] ratings)
        //    {
        //        throw new NotImplementedException();
        //    }

        //    public int HttpsSetPhotoIssoS(Guid id, PhotoIssoS[] photos)
        //    {
        //        throw new NotImplementedException();
        //    }

        //    public int HttpsReceiveInfoAboutPhotoForIssoS(Guid id, int c_isso, PhotoIssoS[] photos)
        //    {
        //        throw new NotImplementedException();
        //    }

        //    public PhotoIssoS[] HttpsGetPhotoIssoS(Guid id, int c_isso)
        //    {
        //        throw new NotImplementedException();
        //    }
        //}
    }
}
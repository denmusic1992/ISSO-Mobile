using Foundation;
using ISSO_I.Interfaces;
using ISSO_I.iOS.PlatformSpecific;
using Xamarin.Forms;

[assembly: Dependency(typeof(ClearCacheProvider))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class ClearCacheProvider : IClearCacheInterface
    {
        public void Clear()
        {
            var cookieStorage = NSHttpCookieStorage.SharedStorage;
            foreach (var cookie in cookieStorage.Cookies)
                cookieStorage.DeleteCookie(cookie);
        }
    }
}
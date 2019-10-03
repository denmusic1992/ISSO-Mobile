using Android.Webkit;
using ISSO_I.Droid.PlatformSpecific;
using ISSO_I.Interfaces;
using Xamarin.Forms;

[assembly: Dependency(typeof(ClearCacheProvider))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class ClearCacheProvider : IClearCacheInterface
    {
        public void Clear()
        {
            var cookieManager = CookieManager.Instance;
            cookieManager.RemoveAllCookie();
        }
    }
}
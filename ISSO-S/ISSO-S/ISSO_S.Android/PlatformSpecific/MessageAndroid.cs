using Android.Widget;
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using ISSO_S.Droid.PlatformSpecific;

[assembly: Xamarin.Forms.Dependency(typeof(MessageAndroid))]
namespace ISSO_S.Droid.PlatformSpecific
{
    public class MessageAndroid : IMessage
    {
        public void LongAlert(string message)
        {
            Toast.MakeText(Android.App.Application.Context, message, ToastLength.Long).Show();
        }

        public void ShortAlert(string message)
        {
            Toast.MakeText(Android.App.Application.Context, message, ToastLength.Short).Show();
        }
    }
}
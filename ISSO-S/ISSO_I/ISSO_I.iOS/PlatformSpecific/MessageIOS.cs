using CommonClassesLibrary.Interfaces;
using Foundation;
using UIKit;
using ISSO_I.iOS.PlatformSpecific;

[assembly: Xamarin.Forms.Dependency(typeof(MessageIos))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class MessageIos : IMessage
    {
	    private const double LongDelay = 3.5;
	    private const double ShortDelay = 2.0;

	    private NSTimer _alertDelay;
	    private UIAlertController _alert;

        public void LongAlert(string message)
        {
            ShowAlert(message, LongDelay);
        }
        public void ShortAlert(string message)
        {
            ShowAlert(message, ShortDelay);
        }

	    private void ShowAlert(string message, double seconds)
        {
            _alertDelay = NSTimer.CreateScheduledTimer(seconds, (obj) =>
            {
                DismissMessage();
            });
            _alert = UIAlertController.Create(null, message, UIAlertControllerStyle.Alert);
            UIApplication.SharedApplication.KeyWindow.RootViewController.PresentViewController(_alert, true, null);
        }

	    private void DismissMessage()
        {
	        _alert?.DismissViewController(true, null);
	        _alertDelay?.Dispose();
        }
    }
}
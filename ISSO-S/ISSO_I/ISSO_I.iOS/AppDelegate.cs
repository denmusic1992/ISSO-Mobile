using CarouselView.FormsPlugin.iOS;
using Foundation;
using ISSO_I.iOS.PlatformSpecific;
using UIKit;
using Xamarin.Forms;

namespace ISSO_I.iOS
{
    // The UIApplicationDelegate for the application. This class is responsible for launching the 
    // User Interface of the application, as well as listening (and optionally responding) to 
    // application events from iOS.
    [Register("AppDelegate")]
    public class AppDelegate : Xamarin.Forms.Platform.iOS.FormsApplicationDelegate
    {
        //
        // This method is invoked when the application has loaded and is ready to run. In this 
        // method you should instantiate the window, load the UI into it and then make the window
        // visible.
        //
        // You have 17 seconds to return from this method, or iOS will terminate your application.
        //
        public override bool FinishedLaunching(UIApplication app, NSDictionary options)
        {
            Forms.Init();
            Xamarin.FormsGoogleMaps.Init("AIzaSyD-_2756m05sBxksd3jK8ZEkcSBwvBxTm8");
            LoadApplication(new App());

            // INIT
            Rg.Plugins.Popup.Popup.Init();
			Plugin.InputKit.Platforms.iOS.Config.Init();
            Stormlion.PhotoBrowser.iOS.Platform.Init();
            DependencyService.Register<IosDateImageClass>();
            FFImageLoading.Forms.Platform.CachedImageRenderer.Init();
			CarouselViewRenderer.Init();
			XamForms.Controls.iOS.Calendar.Init();
            return base.FinishedLaunching(app, options);
        }
    }
}

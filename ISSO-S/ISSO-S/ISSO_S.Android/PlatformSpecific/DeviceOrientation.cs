using Android.Content;
using Android.Runtime;
using Android.Views;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;

[assembly: Dependency(typeof(DeviceOrientation))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class DeviceOrientation : IDeviceOrientation
    {
        public DeviceOrientation() { }

        public static void Init() { }

        public DeviceOrientations GetOrientation()
        {
            IWindowManager windowManager = Android.App.Application.Context.GetSystemService(Context.WindowService).JavaCast<IWindowManager>();

            var rotation = windowManager.DefaultDisplay.Rotation;
            bool isLandscape = rotation == SurfaceOrientation.Rotation90 || rotation == SurfaceOrientation.Rotation270;
            return isLandscape ? DeviceOrientations.Landscape : DeviceOrientations.Portrait;
        }
    }
}
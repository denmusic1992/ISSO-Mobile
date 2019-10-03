using ISSO_I.iOS.PlatformSpecific;
using CommonClassesLibrary.Interfaces;
using UIKit;
using Xamarin.Forms;

[assembly: Dependency(typeof(DeviceOrientation))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class DeviceOrientation : IDeviceOrientation
    {
	    public DeviceOrientations GetOrientation()
        {
            var currentOrientation = UIApplication.SharedApplication.StatusBarOrientation;
            var isPortrait = currentOrientation == UIInterfaceOrientation.Portrait
                || currentOrientation == UIInterfaceOrientation.PortraitUpsideDown;

            return isPortrait ? DeviceOrientations.Portrait : DeviceOrientations.Landscape;
        }
    }
}
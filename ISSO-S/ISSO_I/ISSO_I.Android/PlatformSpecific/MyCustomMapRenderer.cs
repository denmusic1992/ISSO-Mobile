using Android.Content;
using Android.Gms.Maps;
using Xamarin.Forms.GoogleMaps.Android;

//[assembly: ExportRenderer(typeof(MyCustomMap), typeof(MyCustomMapRenderer))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class MyCustomMapRenderer : MapRenderer
    {
        public MyCustomMapRenderer(Context context) : base(context) { }

        protected override void Dispose(bool disposing)
        {
            //base.Dispose(disposing);
            ((MapView) Control).OnPause();
            ((MapView) Control).OnDestroy();
            ((MapView) Control).Dispose();
        }
    }
}
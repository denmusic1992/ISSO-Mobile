using System.Linq;
using ISSO_I.CustomRenderes;
using ISSO_I.iOS.PlatformSpecific;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(ZoomableScrollView), typeof(MyZoomableScrollView))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class MyZoomableScrollView : ScrollViewRenderer
    {
        protected override void OnElementChanged(VisualElementChangedEventArgs e)
        {
            base.OnElementChanged(e);
            MaximumZoomScale = 3f;
            MinimumZoomScale = 1.0f;

        }
        public override void LayoutSubviews()
        {
            base.LayoutSubviews();

            if (Subviews.Length > 0)
            {
                ViewForZoomingInScrollView += GetViewForZooming;
            }
            else
            {
	            // ReSharper disable once DelegateSubtraction
                ViewForZoomingInScrollView -= GetViewForZooming;
            }

        }
        public UIView GetViewForZooming(UIScrollView sv)
        {
            return Subviews.FirstOrDefault();
        }
    }


}
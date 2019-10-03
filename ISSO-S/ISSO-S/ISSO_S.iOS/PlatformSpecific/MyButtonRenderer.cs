using ISSO_S.Droid.CustomRenderers;
using ISSO_S.iOS.PlatformSpecific;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(MyButton), typeof(MyButtonRenderer))]
namespace ISSO_S.iOS.PlatformSpecific
{
    public class MyButtonRenderer : ButtonRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Button> e)
        {
            base.OnElementChanged(e);
            if (Control != null)
            {
                Control.ImageView.ContentMode = UIViewContentMode.ScaleAspectFit;
            }
        }
    }
}
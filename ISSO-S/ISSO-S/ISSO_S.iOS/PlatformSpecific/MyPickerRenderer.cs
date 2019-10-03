using ISSO_S.iOS.CustomRenderers;
using ISSO_S.iOS.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(MyPicker), typeof(MyPickerRenderer))]
namespace ISSO_S.iOS.PlatformSpecific
{

    public class MyPickerRenderer : PickerRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Picker> e)
        {
            base.OnElementChanged(e);
            if (Control != null)
            {
                Control.Font = Control.Font.WithSize(14);
            }
        }
    }
}
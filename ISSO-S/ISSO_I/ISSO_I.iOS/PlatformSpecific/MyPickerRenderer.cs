using ISSO_I.CustomRenderes;
using ISSO_I.iOS.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(MyPicker), typeof(MyPickerRenderer))]
namespace ISSO_I.iOS.PlatformSpecific
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
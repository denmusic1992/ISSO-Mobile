using Android.Content;
using ISSO_I.CustomRenderes;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(TextLikeDatePicker), typeof(TextLikeDatePickerRenderer))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class TextLikeDatePickerRenderer : DatePickerRenderer
    {
        public TextLikeDatePickerRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<DatePicker> e)
        {
            base.OnElementChanged(e);
            var datePickerControl = Control;
            if (datePickerControl != null)
            {
                datePickerControl.Background = null;
                var layoutParams = new MarginLayoutParams(datePickerControl.LayoutParameters);
                layoutParams.SetMargins(0, 0, 0, 0);
                LayoutParameters = layoutParams;
                datePickerControl.LayoutParameters = layoutParams;
                datePickerControl.SetPadding(0, 0, 0, 0);
                SetPadding(0, 0, 0, 0);

                Control.TextSize = 12;
            }
            if (e.OldElement != null)
            {
                Control.Dispose();
            }
        }
    }
}
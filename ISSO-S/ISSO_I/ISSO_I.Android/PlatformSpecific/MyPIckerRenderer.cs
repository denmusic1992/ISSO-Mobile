using Android.Content;
using ISSO_I.CustomRenderes;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(MyPicker), typeof(MyPickerRenderer))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class MyPickerRenderer : PickerRenderer
    {
        public MyPickerRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Picker> e)
        {
            base.OnElementChanged(e);
            var pickerControl = Control;
            if (pickerControl != null)
            {
                pickerControl.TextSize = 12;

            }
        }
    }
}
using Android.Content;
using Android.Content.Res;
using Android.Graphics.Drawables;
using Android.Text;
using ISSO_S.Droid.PlatformSpecific;
using ISSO_S.iOS.CustomRenderers;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(MyPicker), typeof(MyPickerRenderer))]
namespace ISSO_S.Droid.PlatformSpecific
{

    public class MyPickerRenderer : Xamarin.Forms.Platform.Android.AppCompat.PickerRenderer
    {
        public MyPickerRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Picker> e)
        {
            base.OnElementChanged(e);
            var pickerControl = Control;
	        if (pickerControl == null) return;
	        pickerControl.TextSize = 14;
	        var gd = new GradientDrawable();
	        gd.SetColor(Android.Graphics.Color.Transparent);
#pragma warning disable 618
	        pickerControl.SetBackgroundDrawable(gd);
#pragma warning restore 618
	        pickerControl.SetRawInputType(InputTypes.TextFlagNoSuggestions);
	        pickerControl.SetHintTextColor(ColorStateList.ValueOf(Android.Graphics.Color.White));
        }
    }
}
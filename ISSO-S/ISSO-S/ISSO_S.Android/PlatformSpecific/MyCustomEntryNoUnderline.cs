using Android.Content;
using Android.Graphics.Drawables;
using Android.Text;
using Android.Views;
using ISSO_S.Droid.CustomRenderers;
using ISSO_S.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(CustomEntryNoUnderline), typeof(MyCustomEntryNoUnderline))]
namespace ISSO_S.Droid.PlatformSpecific
{

    public class MyCustomEntryNoUnderline : EntryRenderer
    {
        public MyCustomEntryNoUnderline(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
        {
            base.OnElementChanged(e);

	        if (Control == null) return;
	        var gd = new GradientDrawable();
	        gd.SetColor(Android.Graphics.Color.Transparent);
#pragma warning disable 618
	        Control.SetBackgroundDrawable(gd);
#pragma warning restore 618
	        Control.SetRawInputType(InputTypes.TextFlagNoSuggestions);

	        Control.Gravity = GravityFlags.CenterVertical | GravityFlags.Start;
        }
    }
}
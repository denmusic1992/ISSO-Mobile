using Android.Content;
using ISSO_S.Droid.CustomRenderers;
using ISSO_S.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(AddButton), typeof(AddButtonRenderer))]
namespace ISSO_S.Droid.PlatformSpecific
{


    public class AddButtonRenderer : ButtonRenderer
    {
        public AddButtonRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Button> e)
        {
            base.OnElementChanged(e);

	        //Control.SetBackgroundColor(Android.Graphics.Color.ParseColor("#104e8b"));
	        Control?.SetBackgroundResource(Resource.Drawable.new_rem_light);
        }
    }
}
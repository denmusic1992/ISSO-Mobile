using Android.Content;
using CommonClassesLibrary.CustomRenderers;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(MultiselectPicker), typeof(MultiselectPickerRenderer))]
namespace ISSO_I.Droid.PlatformSpecific
{
	public class MultiselectPickerRenderer : PickerRenderer
	{
		public MultiselectPickerRenderer(Context context) : base(context) { }

		protected override void OnElementChanged(ElementChangedEventArgs<Picker> e)
		{
			base.OnElementChanged(e);
		}
	}
}
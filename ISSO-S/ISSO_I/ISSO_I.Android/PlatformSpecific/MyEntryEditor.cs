using Android.Content;
using ISSO_I.Droid.PlatformSpecific;
using ISSO_I.PopupTypes;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(EditorXf), typeof(MyEntryEditor))]
namespace ISSO_I.Droid.PlatformSpecific
{
	internal class MyEntryEditor : EditorRenderer
    {
        public MyEntryEditor(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Editor> e)
        {
            base.OnElementChanged(e);
            if (Control != null)
            {
                Control.ImeOptions = Android.Views.InputMethods.ImeAction.Done;
            }
        }
    }
}
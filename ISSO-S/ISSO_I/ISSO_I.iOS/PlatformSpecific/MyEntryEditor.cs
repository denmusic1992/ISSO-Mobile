using ISSO_I.iOS.PlatformSpecific;
using ISSO_I.PopupTypes;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(EditorXf), typeof(MyEntryEditor))]
namespace ISSO_I.iOS.PlatformSpecific
{
	internal class MyEntryEditor : EditorRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Editor> e)
        {
            base.OnElementChanged(e);
            if(Control != null)
            {
                Control.ReturnKeyType = UIReturnKeyType.Done;
            }
        }
    }
}
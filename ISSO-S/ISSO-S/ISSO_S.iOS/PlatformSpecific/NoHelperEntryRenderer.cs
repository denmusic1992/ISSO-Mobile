using ISSO_S.Droid.CustomRenderers;
using ISSO_S.iOS.PlatformSpecific;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(MyNoHelperEntry), typeof(NoHelperEntryRenderer))]
namespace ISSO_S.iOS.PlatformSpecific
{
    public class NoHelperEntryRenderer : EntryRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
        {
            base.OnElementChanged(e);
            if (Control != null)
            {
                Control.SpellCheckingType = UITextSpellCheckingType.No;             // No Spellchecking
                Control.AutocorrectionType = UITextAutocorrectionType.No;           // No Autocorrection
                Control.AutocapitalizationType = UITextAutocapitalizationType.None; // No Autocapitalization
                Control.BorderStyle = UITextBorderStyle.None;
            }
        }
    }
}
using System.ComponentModel;
using ISSO_I.CustomRenderes;
using ISSO_I.iOS.PlatformSpecific;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(TextLikeDatePicker), typeof(TextLikeDatePickerRenderer))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class TextLikeDatePickerRenderer : DatePickerRenderer
    {
        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);
            Control.Layer.BorderWidth = 0;
            Control.BorderStyle = UITextBorderStyle.None;
            var font = Control.Font.WithSize(12);
            Control.Font = font;
        }
    }
}
using System;
using System.Drawing;
using CommonClassesLibrary;
using CoreGraphics;
using Foundation;
using ISSO_I.iOS;
using UIKit;

[assembly: Xamarin.Forms.Dependency(typeof(CalculateTextWidthIos))]
namespace ISSO_I.iOS
{
    public class CalculateTextWidthIos : ICalculateTextWidthHeight
    {
	    private UILabel _uiLabel;
	    private CGSize _length;

	    // ReSharper disable once MethodOverloadWithOptionalParameter
	    public double CalculateHeight(string text, double width, float textSize, string fontName = null)
        {
            //uiLabel = new UILabel
            //{
            //    Text = text,
            //};
            //var font = uiLabel.Font;
            //font = font.WithSize(textSize);
            //uiLabel.Font = font;
            //uiLabel.SetNeedsLayout();
            //return uiLabel.Frame.Size.Height;

            var nsText = new NSString(text);
            var boundSize = new SizeF((float)width, float.MaxValue);
            var options = NSStringDrawingOptions.UsesFontLeading |
                NSStringDrawingOptions.UsesLineFragmentOrigin;

            if (fontName == null)
            {
                fontName = "HelveticaNeue";
            }

            var attributes = new UIStringAttributes
            {
                Font = UIFont.FromName(fontName, textSize)
            };

            var sizeF = nsText.GetBoundingRect(boundSize, options, attributes, null).Size;

            return sizeF.Height;

        }

        public double CalculateHeight(string text, double width, float textSize)
        {
            throw new NotImplementedException();
        }

        public double CalculateHeight(string text, float textSize)
        {
            throw new NotImplementedException();
        }

        public double CalculateWidth(string text, float textSize)
        {
            _uiLabel = new UILabel
            {
                Text = text,
            };
            var font = _uiLabel.Font;
            font = font.WithSize(textSize);
            _uiLabel.Font = font;
            _uiLabel.SetNeedsLayout();
            _length = _uiLabel.Text.StringSize(_uiLabel.Font);
            return _length.Width;
        }
    }
}
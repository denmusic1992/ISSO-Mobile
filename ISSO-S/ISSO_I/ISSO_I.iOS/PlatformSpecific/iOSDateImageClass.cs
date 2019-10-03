using System;
using System.Drawing;
using CommonClassesLibrary.Interfaces;
using CoreGraphics;
using Foundation;
using ISSO_I.iOS.PlatformSpecific;
using UIKit;

[assembly: Xamarin.Forms.Dependency(typeof(IosDateImageClass))]
namespace ISSO_I.iOS.PlatformSpecific
{
    /// <summary>
    /// Класс для добавления даты в фотографию
    /// </summary>
    public class IosDateImageClass : IDateImageInterface
    {
	    /// <summary>
        /// Метод добавления даты в фотографию
        /// </summary>
        /// <param name="path">Путь в системе до фотографии</param>
        public void AddDateToImageSpecific(string path)
        {
            var image = UIImage.FromFile(path);
            var fWidth = image.Size.Width;
            var fHeight = image.Size.Height;
            var sText = DateTime.Now.ToString("yyyy-MM-dd");

            UIGraphics.BeginImageContext(new SizeF((float)fWidth, (float)fHeight));
            using (var g = UIGraphics.GetCurrentContext())
            {
                image.Draw(new Rectangle(0, 0, (int)fWidth, (int)fHeight));
                var fontSize = 48f;

                // Вычислить длину текста
                var textWidth = TextMeter.MeasureTextSize(sText, 0, fontSize);

                // Основные настройки по цвету и толщине текста и окантовки
                g.SetLineWidth(1.0f);
                g.SetStrokeColor(UIColor.Black.CGColor);
                g.SetFillColor(UIColor.Orange.CGColor);
                g.SetTextDrawingMode(CGTextDrawingMode.FillStroke);
                g.SelectFont("Helvetica", fontSize, CGTextEncoding.MacRoman);
                // Переворот рисовалки
                g.ScaleCTM(1, -1);
                g.TranslateCTM(0, -fHeight);
                // Рисование текста
                g.ShowTextAtPoint((nfloat)(fWidth - textWidth.Width - 20),(nfloat) textWidth.Height, sText);
                var result = UIGraphics.GetImageFromCurrentImageContext();
                UIGraphics.EndImageContext();
                result.AsJPEG().Save(path, NSDataWritingOptions.FileProtectionMask, out var _);
            }
        }
    }

    /// <summary>
    /// Класс получения размера 
    /// </summary>
    public static class TextMeter
    {
        public static Xamarin.Forms.Size MeasureTextSize(string text, double width,
            double fontSize, string fontName = null)
        {
            var nsText = new NSString(text);
            var boundSize = new SizeF((float)width, float.MaxValue);
            var options = NSStringDrawingOptions.UsesFontLeading |
                NSStringDrawingOptions.UsesLineFragmentOrigin;

            if (fontName == null)
            {
                fontName = "Helvetica";
            }

            var attributes = new UIStringAttributes
            {
                Font = UIFont.FromName(fontName, (float)fontSize)
            };

            var sizeF = nsText.GetBoundingRect(boundSize, options, attributes, null).Size;

            return new Xamarin.Forms.Size(sizeF.Width, sizeF.Height);
        }
    }
}
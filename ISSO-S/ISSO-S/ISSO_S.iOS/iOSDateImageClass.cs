using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using CommonClassesLibrary.Interfaces;
using CoreGraphics;
using Foundation;
using ISSO_S.iOS;
using UIKit;

[assembly: Xamarin.Forms.Dependency(typeof(IOSDateImageClass))]
namespace ISSO_S.iOS
{
    /// <summary>
    /// Класс для добавления даты в фотографию
    /// </summary>
    public class IOSDateImageClass : IDateImageInterface
    {
        /// <summary>
        /// Конструктор по умолчанию
        /// </summary>
        public IOSDateImageClass() { }

        /// <summary>
        /// Метод добавления даты в фотографию
        /// </summary>
        /// <param name="path">Путь в системе до фотографии</param>
        public void AddDateToImageSpecific(string path)
        {
            UIImage image = UIImage.FromFile(path);
            nfloat fWidth = image.Size.Width;
            nfloat fHeight = image.Size.Height;
            string sText = DateTime.Now.ToString("yyyy-MM-dd");

            UIGraphics.BeginImageContext(new SizeF((float)fWidth, (float)fHeight));
            using (CGContext g = UIGraphics.GetCurrentContext())
            {
                image.Draw(new Rectangle(0, 0, (int)fWidth, (int)fHeight));
                float fontSize = 48f;

                // Вычислить длину текста
                var textWidth = TextMeterImplementation.MeasureTextSize(sText, 0, fontSize);

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
                result.AsJPEG().Save(file: path, options: NSDataWritingOptions.FileProtectionMask, error: out NSError error);
            }
            
            
            //image.AsJPEG().Save(file: path, options: NSDataWritingOptions.FileProtectionMask, error: out NSError error);

            //using (CGBitmapContext ctx = new CGBitmapContext(IntPtr.Zero, (nint)fWidth, (nint)fHeight, 8, 4 * (nint)fWidth, CGColorSpace.CreateDeviceRGB(), CGBitmapFlags.PremultipliedFirst))
            //{
            //    ctx.ScaleCTM(1, -1);
            //    //ctx.TranslateCTM(0, -fHeight);
            //    ctx.RotateCTM(-(nfloat) Math.PI / 2);
            //    ctx.TranslateCTM(0, -fHeight);
            //    ctx.DrawImage(new CGRect(0, 0, (double)fWidth, (double)fHeight), image.CGImage);
            //    ctx.SelectFont("Helvetica", (nfloat)48, CGTextEncoding.FontSpecific);

            //    //Measure the text's width - This involves drawing an invisible string to calculate the X position difference
            //    float start, end, textWidth;

            //    //Get the texts current position
            //    start = (float)ctx.TextPosition.X;
            //    //Set the drawing mode to invisible
            //    ctx.SetTextDrawingMode(CGTextDrawingMode.Invisible);
            //    //Draw the text at the current position
            //    ctx.ShowText(sText);
            //    //Get the end position
            //    end = (float)ctx.TextPosition.X;
            //    //Subtract start from end to get the text's width
            //    textWidth = end - start;

            //    //Set the fill color to black. This is the text color.
            //    ctx.SetFillColor(0, 0, 0, 1);

            //    //Set the drawing mode back to something that will actually draw Fill for example
            //    ctx.SetTextDrawingMode(CGTextDrawingMode.FillClip);

            //    //Draw the text at given coords.
            //    ctx.ShowTextAtPoint(fWidth - textWidth - 20, fHeight - 20, sText);
            //    UIImage.FromImage(ctx.ToImage()).AsJPEG().Save(file: path, options: NSDataWritingOptions.FileProtectionMask, error: out NSError error);
            //}
        }
    }

    /// <summary>
    /// Класс получения размера 
    /// </summary>
    public static class TextMeterImplementation
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

            return new Xamarin.Forms.Size((double)sizeF.Width, (double)sizeF.Height);
        }
    }
}
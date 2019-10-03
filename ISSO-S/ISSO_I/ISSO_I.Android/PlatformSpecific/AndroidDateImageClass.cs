using System;
using System.IO;
using Android.Graphics;
using Android.Util;
using Android.Views;
using Android.Widget;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Droid.PlatformSpecific;

[assembly: Xamarin.Forms.Dependency(typeof(AndroidDateImageClass))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class AndroidDateImageClass : IDateImageInterface
    {
	    public void AddDateToImageSpecific(string path)
        {
            using (var bmCard = BitmapFactory.DecodeFile(path))
            {
                var mutableBitmap = bmCard.Copy(Bitmap.Config.Argb8888, true);
                var cCanvas = new Canvas(mutableBitmap);
                cCanvas.Save();
                var sText = DateTime.Now.ToString("yyyy-MM-dd");

                //card number
                var pDateFill = new Paint(PaintFlags.AntiAlias);
                pDateFill.SetStyle(Paint.Style.Fill);
                pDateFill.StrokeWidth = 1;
                pDateFill.Color = Color.Orange;
                pDateFill.TextSize = 48;

                var pDateStroke = new Paint(PaintFlags.AntiAlias);
                pDateStroke.SetStyle(Paint.Style.Stroke);
                pDateStroke.StrokeWidth = 1;
                pDateStroke.Color = Color.Black;
                pDateStroke.TextSize = 48;

                var textSize = TextMeter.MeasureTextSize(sText, mutableBitmap.Width, pDateFill.TextSize);

                cCanvas.DrawText(sText, (float)(mutableBitmap.Width - textSize.Width - 20), (float)(mutableBitmap.Height - textSize.Height), pDateFill);
                cCanvas.DrawText(sText, (float)(mutableBitmap.Width - textSize.Width - 20), (float)(mutableBitmap.Height - textSize.Height), pDateStroke);

                using (var swStreamWriter = File.Create(path))
                {
                    mutableBitmap.Compress(Bitmap.CompressFormat.Jpeg, 100, swStreamWriter);
                    swStreamWriter.Flush();
                }

                mutableBitmap.Recycle();
                bmCard.Recycle();
            }
        }
    }

    public static class TextMeter
    {
        private static Typeface _textTypeface;

        public static Xamarin.Forms.Size MeasureTextSize(string text, double width,
            double fontSize, string fontName = null)
        {
            var textView = new TextView(Android.App.Application.Context)
            {
                Typeface = GetTypeface(fontName)
            };
            textView.SetText(text, TextView.BufferType.Normal);
            textView.SetTextSize(ComplexUnitType.Px, (float)fontSize);

            var widthMeasureSpec = View.MeasureSpec.MakeMeasureSpec((int)width, MeasureSpecMode.AtMost);
            var heightMeasureSpec = View.MeasureSpec.MakeMeasureSpec(0, MeasureSpecMode.Unspecified);

            textView.Measure(widthMeasureSpec, heightMeasureSpec);

            return new Xamarin.Forms.Size(textView.MeasuredWidth, textView.MeasuredHeight);
        }

        private static Typeface GetTypeface(string fontName)
        {
	        if (fontName == null)
            {
                return Typeface.Default;
            }

	        return _textTypeface ?? (_textTypeface = Typeface.Create(fontName, TypefaceStyle.Normal));
        }
    }
}
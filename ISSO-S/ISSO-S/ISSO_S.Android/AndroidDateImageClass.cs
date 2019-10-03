using System;
using System.IO;
using Android.Graphics;
using Android.Util;
using Android.Views;
using Android.Widget;
using CommonClassesLibrary.Interfaces;
using ISSO_S.Droid;

[assembly: Xamarin.Forms.Dependency(typeof(AndroidDateImageClass))]
namespace ISSO_S.Droid
{
    public class AndroidDateImageClass : IDateImageInterface
    {
        public AndroidDateImageClass() { }

        public void AddDateToImageSpecific(string path)
        {
            using (Bitmap bmCard = BitmapFactory.DecodeFile(path))
            {
                Bitmap mutableBitmap = bmCard.Copy(Bitmap.Config.Argb8888, true);
                Canvas cCanvas = new Canvas(mutableBitmap);
                cCanvas.Save();
                string sText = DateTime.Now.ToString("yyyy-MM-dd");

                //card number
                Paint pDateFill = new Paint(PaintFlags.AntiAlias);
                pDateFill.SetStyle(Paint.Style.Fill);
                pDateFill.StrokeWidth = 1;
                pDateFill.Color = Color.Orange;
                pDateFill.TextSize = 48;

                Paint pDateStroke = new Paint(PaintFlags.AntiAlias);
                pDateStroke.SetStyle(Paint.Style.Stroke);
                pDateStroke.StrokeWidth = 1;
                pDateStroke.Color = Color.Black;
                pDateStroke.TextSize = 48;

                var textSize = TextMeterImplementation.MeasureTextSize(sText, mutableBitmap.Width, pDateFill.TextSize);

                cCanvas.DrawText(sText, (float)(mutableBitmap.Width - textSize.Width - 20), (float)(mutableBitmap.Height - textSize.Height), pDateFill);
                cCanvas.DrawText(sText, (float)(mutableBitmap.Width - textSize.Width - 20), (float)(mutableBitmap.Height - textSize.Height), pDateStroke);

                using (FileStream swStreamWriter = File.Create(path))
                {
                    mutableBitmap.Compress(Android.Graphics.Bitmap.CompressFormat.Jpeg, 100, swStreamWriter);
                    swStreamWriter.Flush();
                }

                mutableBitmap.Recycle();
                bmCard.Recycle();
            }
        }
    }

    public static class TextMeterImplementation
    {
        private static Typeface textTypeface;

        public static Xamarin.Forms.Size MeasureTextSize(string text, double width,
            double fontSize, string fontName = null)
        {
            var textView = new TextView(global::Android.App.Application.Context)
            {
                Typeface = GetTypeface(fontName)
            };
            textView.SetText(text, TextView.BufferType.Normal);
            textView.SetTextSize(ComplexUnitType.Px, (float)fontSize);

            int widthMeasureSpec = Android.Views.View.MeasureSpec.MakeMeasureSpec((int)width, MeasureSpecMode.AtMost);
            int heightMeasureSpec = Android.Views.View.MeasureSpec.MakeMeasureSpec(0, MeasureSpecMode.Unspecified);

            textView.Measure(widthMeasureSpec, heightMeasureSpec);

            return new Xamarin.Forms.Size((double)textView.MeasuredWidth, (double)textView.MeasuredHeight);
        }

        private static Typeface GetTypeface(string fontName)
        {
            if (fontName == null)
            {
                return Typeface.Default;
            }

            if (textTypeface == null)
            {
                textTypeface = Typeface.Create(fontName, TypefaceStyle.Normal);
            }

            return textTypeface;
        }
    }
}
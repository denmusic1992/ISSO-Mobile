using Android.Graphics;
using Android.Util;
using Android.Views;
using Android.Widget;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Droid;

[assembly: Xamarin.Forms.Dependency(typeof(TextMeterImplementation))]

namespace ISSO_I.Droid
{
    public class TextMeterImplementation : ITextMeter
    {
        private Typeface _textTypeface;

        //public static Xamarin.Forms.Size MeasureTextSize(string text, double width, double fontSize, string fontName = null)
        public double MeasureTextSize(string text, double width, double fontSize, string fontName = null)
        {
	        var textView = new TextView(Android.App.Application.Context) {Typeface = GetTypeface(fontName)};
	        textView.SetText(text, TextView.BufferType.Normal);
            textView.SetTextSize(ComplexUnitType.Px, (float)fontSize);

            var widthMeasureSpec = View.MeasureSpec.MakeMeasureSpec(
                (int)width, MeasureSpecMode.AtMost);
            var heightMeasureSpec = View.MeasureSpec.MakeMeasureSpec(
                0, MeasureSpecMode.Unspecified);

            textView.Measure(widthMeasureSpec, heightMeasureSpec);

            //return new Xamarin.Forms.Size((double)textView.MeasuredWidth, (double)textView.MeasuredHeight);
            return textView.MeasuredHeight;
        }

        private Typeface GetTypeface(string fontName)
        {
	        if (fontName == null)
            {
                return Typeface.Default;
            }

	        return _textTypeface ?? (_textTypeface = Typeface.Create(fontName, TypefaceStyle.Normal));
        }
    }
}
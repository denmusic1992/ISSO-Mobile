using System;
using Android.Content.Res;
using Android.Graphics;
using Android.Util;
using Android.Views;
using Android.Widget;
using CommonClassesLibrary;
using ISSO_I.Droid;
using Xamarin.Forms;
using Application = Android.App.Application;
using View = Android.Views.View;

[assembly: Dependency(typeof(CalculateTextWidthAndroid))]
namespace ISSO_I.Droid
{
    public class CalculateTextWidthAndroid : ICalculateTextWidthHeight
    {
	    public double CalculateHeight(string text, double width, float textSize)
        {
            //Rect bounds = new Rect();
            //TextView textView = new TextView(context: Android.App.Application.Context);
            //textView.TextSize = textSize;
            //textView.Paint.GetTextBounds(text, 0, text.Length, bounds);
            //var height = bounds.Height();
            //return height / Resources.System.DisplayMetrics.ScaledDensity;

            var textView = new TextView(Application.Context)
            {
                Typeface = Typeface.Default
            };
            textView.SetText(text, TextView.BufferType.Normal);
            textView.SetTextSize(ComplexUnitType.Px, textSize);

            var widthMeasureSpec = View.MeasureSpec.MakeMeasureSpec(
                (int)width, MeasureSpecMode.AtMost);
            var heightMeasureSpec = View.MeasureSpec.MakeMeasureSpec(
                0, MeasureSpecMode.Unspecified);

            textView.Measure(widthMeasureSpec, heightMeasureSpec);

            return textView.Height;
        }

        public double CalculateHeight(string text, float textSize)
        {
            throw new NotImplementedException();
        }

        public double CalculateWidth(string text, float textSize)
        {
            var bounds = new Rect();
	        var textView = new TextView(Application.Context) {TextSize = textSize};
	        textView.Paint.GetTextBounds(text, 0, text.Length, bounds);
            var length = bounds.Width();
            return length / Resources.System.DisplayMetrics.ScaledDensity;
        }
    }
}
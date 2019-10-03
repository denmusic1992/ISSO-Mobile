using System.Net;
using Android.Content;
using ISSO_I.CustomRenderes;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(PdfView), typeof(PdfViewRenderer))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class PdfViewRenderer : WebViewRenderer
    {
        public PdfViewRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<WebView> e)
        {
            base.OnElementChanged(e);
	        if (e.NewElement == null) return;
	        var customWebView = Element as PdfView;
	        Control.Settings.AllowUniversalAccessFromFileURLs = true;
	        Control.LoadUrl(
		        $"file:///android_asset/pdfjs/web/viewer.html?file=file://{WebUtility.UrlEncode(customWebView?.Uri)}");
	        //if(e.OldElement != null)
            //{
            //    Control.Dispose();
            //}
        }

        protected override void Dispose(bool disposing)
        {
            base.Dispose(disposing);
	        Control?.Dispose();
        }
    }
}
using System.ComponentModel;
using System.IO;
using System.Net;
using Foundation;
using ISSO_I.CustomRenderes;
using ISSO_I.iOS.PlatformSpecific;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;


[assembly: ExportRenderer(typeof(PdfView), typeof(PdfViewRenderer))]
namespace ISSO_I.iOS.PlatformSpecific
{
    /// <summary>
    /// Рендерер для PDFView
    /// </summary>
    public class PdfViewRenderer : ViewRenderer<PdfView, UIWebView>
    {
	    protected override void OnElementChanged(ElementChangedEventArgs<PdfView> e)
        {
            base.OnElementChanged(e);

            if (Control == null)
            {
                SetNativeControl(new UIWebView());
            }
            if (e.OldElement != null)
            {
                Control.Dispose();
            }

	        if (e.NewElement == null) return;
	        var customWebView = Element;
	        var fileName = Path.Combine(NSBundle.MainBundle.BundlePath,
		        $"Content/{WebUtility.UrlEncode(customWebView.Uri)}");
	        Control.LoadRequest(new NSUrlRequest(new NSUrl(fileName, false)));
	        Control.ScalesPageToFit = true;
	        //var customWebView = Element;
            //LoadFile(customWebView.Uri);

            //Control.ScalesPageToFit = true;

            //Control.ShouldStartLoad += (view, request, type) =>
            //{
            //    if (string.IsNullOrWhiteSpace(Element?.Uri))
            //    {
            //        return true;
            //    }

            //    if (!request.Url.AbsoluteString.Contains(AppScheme))
            //    {
            //        return true;
            //    }

            //    var printInfo = UIPrintInfo.PrintInfo;
            //    var printer = UIPrintInteractionController.SharedPrintController;
            //    printInfo.Duplex = UIPrintInfoDuplex.LongEdge;
            //    printInfo.OutputType = UIPrintInfoOutputType.General;
            //    printer.PrintInfo = printInfo;
            //    printer.PrintingItem = new NSUrl(Element?.Uri, false);
            //    printer.ShowsPageRange = false;
            //    printer.Present(true, (controller, completed, error) =>
            //    {
            //        Debug.WriteLine(completed ? "Printing completed" : $"Printing did not complete : {controller} {error}");
            //    });

            //    return false;
            //};
        }


        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);
            if (e.PropertyName == PdfView.UriProperty.PropertyName)
            {
                LoadFile(Element?.Uri);
            }
        }

        private void LoadFile(string path)
        {
            if (string.IsNullOrWhiteSpace(path))
            {
                return;
            }

            Control.LoadRequest(new NSUrlRequest(new NSUrl(Path.Combine(NSBundle.MainBundle.BundlePath, "Content/pdfjs/web/viewer.html"), false)));
            Control.LoadFinished += Control_LoadFinished;
        }

        private void Control_LoadFinished(object sender, System.EventArgs e)
        {
            Control.LoadFinished -= Control_LoadFinished;
            Control.EvaluateJavascript(
	            $"DEFAULT_URL='{Element?.Uri}'; window.location.href='{Path.Combine(NSBundle.MainBundle.BundlePath, "Content/pdfjs/web/viewer.html")}?file=file://{Element?.Uri}'; ");
        }
    }
}
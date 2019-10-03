using Xamarin.Forms;

namespace ISSO_I.CustomRenderes
{
    /// <inheritdoc />
    /// <summary>
    /// Рендерер для PDF
    /// </summary>
    public class PdfView : WebView
    {
        public static readonly BindableProperty UriProperty = BindableProperty.Create("Uri",
             typeof(string),
             typeof(PdfView),
             default(string));

        public string Uri
        {
            get => (string)GetValue(UriProperty);
	        set => SetValue(UriProperty, value);
        }

        public static readonly BindableProperty IsPdfProperty = BindableProperty.Create("IsPdf",
        typeof(bool),
        typeof(PdfView),
        default(bool));

        public bool IsPdf
        {
            get => (bool)GetValue(IsPdfProperty);
	        set => SetValue(IsPdfProperty, value);
        }
    }
}

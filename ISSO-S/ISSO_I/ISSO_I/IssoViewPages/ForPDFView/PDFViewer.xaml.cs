using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForPDFView
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class PDFViewer : ContentView
	{
        private readonly PdfDocEntity _pdfDocEntity;

        public PDFViewer (PdfDocEntity pdfDocEntity)
		{
			InitializeComponent ();
            _pdfDocEntity = pdfDocEntity;

            Initialize();
        }

        protected void Initialize()
        {
            SetBusyIndicator(true);

            //if (await FileManager.ExistsAsync(_pdfDocEntity.FileName) == false)
            //{
            //    await FileManager.DownloadDocumentsAsync(_pdfDocEntity);
            //}
            //PdfDocView.Uri = FileManager.GetFilePathFromRoot(_pdfDocEntity.FileName);

            //SetBusyIndicator(false);
        }

        private void SetBusyIndicator(bool isBusyIndicatorIsVisible) => BusyIndicator.IsRunning = BusyIndicator.IsVisible = isBusyIndicatorIsVisible;
    }
}
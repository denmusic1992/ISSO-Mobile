using CommonClassesLibrary.BackHandlers;
using System;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class PdfContentPage
	{
		public PdfContentPage ()
		{
			InitializeComponent ();
		}

        public void Initialize(int cIsso)
        {
            PdfView.Initialize(cIsso);
        }

        public override void Dispose()
        {
            PdfView.Dispose();
        }
    }
}
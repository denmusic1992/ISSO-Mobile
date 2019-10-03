using CarouselView.FormsPlugin.Abstractions;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class PhotoContentPage
	{
		public PhotoContentPage ()
		{
			InitializeComponent ();
		}

        public void Initialize(int cIsso)
        {
            PhotoView.Initialize(cIsso);
        }

        public override void Dispose()
        {
            PhotoView.Dispose();
        }
    }
}
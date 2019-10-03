using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.PopupTypes
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class FullScreenImageContainer
	{
		public FullScreenImageContainer (ImageSource source)
		{
			InitializeComponent ();
            image_to_zoom.Source = source;
		}

        private void btn_close_Clicked(object sender, EventArgs e)
        {

        }
    }
}
using System;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace CommonClassesLibrary
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class CommonPopupPage
	{
		/// <summary>
		/// 
		/// </summary>
		/// <param name="contentView"></param>
		/// <param name="header"></param>
		public CommonPopupPage(ContentView contentView, string header)
		{
			InitializeComponent();
			CommonContent.Content = contentView.Content;
			HeaderName.Text = header;
		}

		public void ButtonCloseClicked(object sender, EventArgs e)
		{
			Navigation.PopPopupAsync();
		}
	}
}
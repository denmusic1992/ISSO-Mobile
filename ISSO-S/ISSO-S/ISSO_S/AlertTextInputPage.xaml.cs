using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_S
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class AlertTextInputPage
	{

        // public event handler to expose 
        // the Ok button's click event
        public EventHandler CloseButtonEventHandler { get; set; }

        // public string to expose the 
        // text Entry input's value
        public string TextInputResult { get; set; }

        public AlertTextInputPage (string text)
		{
			InitializeComponent ();
            TextInputResult = text;
            InputEntry.Text = text;
            CloseButton.Clicked += CloseButton_Clicked;
            InputEntry.TextChanged += InputEntry_TextChanged;
		}

        private void InputEntry_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextInputResult = InputEntry.Text;
        }

        private void CloseButton_Clicked(object sender, EventArgs e)
        {
            CloseButtonEventHandler?.Invoke(this, e);
        }
    }
}
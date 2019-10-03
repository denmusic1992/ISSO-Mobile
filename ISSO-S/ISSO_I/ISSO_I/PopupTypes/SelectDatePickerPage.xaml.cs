using CommonClassesLibrary;
using Rg.Plugins.Popup.Extensions;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.PopupTypes
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class SelectDatePickerPage
    {
        private readonly View _view;
	    public event EventHandler SaveChanges;

		/// <summary>
		/// Заголовок для всплывающего окна
		/// </summary>
	    public const string Header = "Выбранная дата";

        public SelectDatePickerPage(View view, long milliseconds)
        {
            InitializeComponent();

            _view = view;
	        date_picker.Date = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(milliseconds);
            date_picker.Format = "dd.MM.yyyy";
        }

        private void button_confirm_Clicked(object sender, EventArgs e)
        {
            ((Label) ((Grid) _view).Children[0]).Text = date_picker.Date.ToString("dd.MM.yyyy");
            Navigation.PopPopupAsync();
            SaveChanges?.Invoke(_view, EventArgs.Empty);
        }
    }
}
using ISSO_I.PopupTypes.ViewModels;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ISSO_I.PopupTypes.Models;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.PopupTypes
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class MultiselectListView
	{

		public MultiselectListViewModel Vm;

		public event EventHandler ApplyConstrs;

		/// <summary>
		/// Для заголовка окна
		/// </summary>
		public const string Header = "Выбранные номера конструкций";

		public MultiselectListView (ObservableCollection<MultiselectItem> items)
		{
			InitializeComponent();
			HeaderName.Text = Header;
			//MultiListView.ItemsSource = _vm.Items;
			Vm = new MultiselectListViewModel(items);
			BindingContext = Vm;
		}

		protected virtual void OnApplyConstrs()
		{
			ApplyConstrs?.Invoke(Vm.Items, EventArgs.Empty);
		}

		private async void ButtonConfirmClicked(object sender, EventArgs e)
		{
			OnApplyConstrs();
			await Navigation.PopPopupAsync();
		}
		

		public void ButtonCloseClicked(object sender, EventArgs e)
		{
			Navigation.PopPopupAsync();
		}
	}
}
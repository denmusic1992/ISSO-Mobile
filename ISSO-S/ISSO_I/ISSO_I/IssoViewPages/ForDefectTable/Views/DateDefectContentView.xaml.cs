using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using CommonClassesLibrary;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;
using ISSO_I.PopupTypes;
using ISSO_I.PopupTypes.Models;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForDefectTable.Views
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class DateDefectContentView
	{
		private DateDefectContentViewModel _vm;

		public DateDefectContentView(CreateDefectModel defectModel)
		{
			InitializeComponent();
			BindingContext = _vm = new DateDefectContentViewModel(defectModel);
			MyCalendar.SelectedDate = null;
			//_vm.DateNotChanged += delegate(object sender, EventArgs args)
			//{
			//	var date = Convert.ToDateTime(sender);
			//	var date1 = date.AddDays(1);
			//	MyCalendar.SelectedDates.Add(date1);
			//};

		}

		private void ChooseConstrClicked(object sender, EventArgs e)
		{
			var multilistView = new MultiselectListView(_vm.Nconstr);
			multilistView.ApplyConstrs += (o, args) =>
			{
				var result = (IEnumerable<MultiselectItem>) o;
				_vm.ChangeConstrs(new ObservableCollection<MultiselectItem>(result));
			};
			//Navigation.PushPopupAsync(new CommonPopupPage(multilistView, MultiselectListView.Header));
			Navigation.PushPopupAsync(multilistView);
		}
	}
}
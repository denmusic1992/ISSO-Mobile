using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForDefectTable.Views
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class SummaryDefectContentView
	{
		public SummaryDefectContentViewModel Vm { get; set; }

		public SummaryDefectContentView (CreateDefectModel defectModel)
		{
			InitializeComponent ();
			Vm = new SummaryDefectContentViewModel(defectModel);
			BindingContext = Vm;
		}
	}
}
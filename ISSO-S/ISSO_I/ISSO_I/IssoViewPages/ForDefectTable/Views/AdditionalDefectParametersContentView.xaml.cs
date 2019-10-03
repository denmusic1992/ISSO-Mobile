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
	/// <summary>
	/// Контрол для ввода дополнительных параметров дефекта
	/// </summary>
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class AdditionalDefectParametersContentView
	{
		public AdditionalDefectParametersContentView (CreateDefectModel defectModel)
		{
			InitializeComponent ();
			BindingContext = new AdditionalDefectParametersContentViewModel(defectModel);
		}
	}
}
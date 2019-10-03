using CommonClassesLibrary;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;
using Rg.Plugins.Popup.Extensions;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForDefectTable.Views
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class QualityParametersTreeView
	{
		public QualityParametersTreeView (CreateDefectModel defectModel)
		{
			InitializeComponent ();
			var page = new LoadingPopupPage("Пожалуйста, подождите...", true);
			Navigation.PushPopupAsync(page, false);
			Task.Factory.StartNew(() =>
			{
				var stackLayout = new StackLayout
				{
					HorizontalOptions = LayoutOptions.FillAndExpand,
					VerticalOptions = LayoutOptions.FillAndExpand,
					Orientation = StackOrientation.Vertical
				};
				TreeStackLayout.Content = stackLayout;
				var mainDefect = new ParamTreeView(defectModel);
				stackLayout.Children.Add(mainDefect);
				//foreach (var child in defectModel.DefectParameters)
				//	stackLayout.Children.Add(new DefectTreeView(cIsso, child, false));
				Device.BeginInvokeOnMainThread(() =>
				{
					Navigation.PopPopupAsync();
					BindingContext = new QualityParametersTreeViewModel();
				});
			});
		}
		
	}
}
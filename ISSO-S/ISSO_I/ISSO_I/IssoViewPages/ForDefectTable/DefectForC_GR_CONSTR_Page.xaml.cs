using CommonClassesLibrary;
using Rg.Plugins.Popup.Extensions;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class DefectForCGrConstrPage 
	{
		public DefectForCGrConstrPage(int cIsso, Ais7IssoDefectsTreeNode parent, IReadOnlyCollection<Ais7IssoDefectsTreeNode> advancedDefects)
		{
			InitializeComponent();
			Title = $"ИССО №{cIsso}: Добавление дефекта. {parent.NGrConstr}";
			var page = new LoadingPopupPage("Идет подбор дефектов...", true);
			Navigation.PushPopupAsync(page, false);
			Task.Factory.StartNew(() =>
			{
				var scrollView = new ScrollView()
				{
					HorizontalOptions = LayoutOptions.FillAndExpand,
					VerticalOptions = LayoutOptions.FillAndExpand,
					Orientation = ScrollOrientation.Vertical
				};
				var stackLayout = new StackLayout
				{
					HorizontalOptions = LayoutOptions.FillAndExpand,
					VerticalOptions = LayoutOptions.FillAndExpand,
					Orientation = StackOrientation.Vertical
				};
				scrollView.Content = stackLayout;
				var mainDefect = new DefectTreeView(cIsso, parent, true);
				stackLayout.Children.Add(mainDefect);
				foreach (var child in advancedDefects)
					stackLayout.Children.Add(new DefectTreeView(cIsso, child, false));
				Device.BeginInvokeOnMainThread(() =>
					{
						Content = scrollView;
						Navigation.PopPopupAsync();
					});
			});
		}

		public override void Dispose()
		{
			Content = null;
			BindingContext = null;
		}
	}
}
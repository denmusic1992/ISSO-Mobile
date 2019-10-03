using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace HighEnergy.TreeView.Demo.DemoModule
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class ParamDefectItemView : ContentView
	{
		public int IndentWidth { get; set; }
		public Command ToggleIsExpandedCommand { get; set; }
		public string Description { get; set; }

		public ParamDefectItemView()
		{
			InitializeComponent();
		}
	}
}
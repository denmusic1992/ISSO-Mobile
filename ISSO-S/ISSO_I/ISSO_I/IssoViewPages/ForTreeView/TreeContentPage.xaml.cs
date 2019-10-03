using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class TreeContentPage
	{
		public TreeContentPage ()
        {
            InitializeComponent();
        }

        public void Init(int cIsso)
        {
            TreeView.InitTreeView(cIsso);
	        if (TreeView.Children.Count <= 0)
	        {
		        Content = new Label
		        {
			        VerticalOptions = LayoutOptions.Center,
			        HorizontalOptions = LayoutOptions.Center,
			        Text = "Данные на данном сооружении отсутствуют."
		        };
	        }
        }

        public override void Dispose()
        {
	        TreeView?.Dispose();
	        BindingContext = null;
            Content = null;
        }
    }
}
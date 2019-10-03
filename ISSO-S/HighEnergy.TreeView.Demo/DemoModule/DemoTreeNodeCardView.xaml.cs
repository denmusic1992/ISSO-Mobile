using Xamarin.Forms;

namespace HighEnergy.TreeView.Demo.DemoModule
{
    public partial class DemoTreeCardView
    {
        public int IndentWidth { get; set; }
        public Command ToggleIsExpandedCommand { get; set; }
        public string Description { get; set; }

        public DemoTreeCardView()
        {
            InitializeComponent();
            //Initialize();
        }
    }
}
using CommonClassesLibrary.BackHandlers;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class DefectContentPage
	{

        public DefectContentPage ()
        {
            InitializeComponent();
        }

        public void Initialize(int cIsso)
        {
            DefectView.InitDefectTree(cIsso);
        }

		public override void Dispose()
        {
            DefectView.Dispose();
        }
    }
}
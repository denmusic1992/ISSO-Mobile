using Xamarin.Forms.Xaml;

namespace CommonClassesLibrary
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoadingPopupPage
    {
        public LoadingPopupPage(string text, bool isRunning)
        {
            InitializeComponent();
            MyTextLoading.Text = text;
            activity_indicator.IsVisible = isRunning;
        }
    }
}
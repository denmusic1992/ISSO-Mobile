using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MasterDetailPage1
    {
        //private MasterDetailPage1Master masterDetailPage { get; }

        public MasterDetailPage1()
        {
            //InitializeComponent();
            //masterDetailPage = new MasterDetailPage1Master();



            // Добавление данных по умолчанию, если их нет
            if (!Application.Current.Properties.TryGetValue("address", out var _))
            {
                Application.Current.Properties.Add("address", "mobile.aisisso.ru");
            }
            if (!Application.Current.Properties.TryGetValue("port", out var _))
            {
                Application.Current.Properties.Add("port", 8790);
            }
            if (!Application.Current.Properties.TryGetValue("support_port", out var _))
            {
                Application.Current.Properties.Add("support_port", 80);
            }

            //Master = masterDetailPage;
            //Detail = new MyNavigationPage(new MainPageLinearList(appType)) { BarBackgroundColor = Color.FromHex("Accent"), BarTextColor = Color.White };
            if (Device.Idiom == TargetIdiom.Tablet)
                MasterBehavior = MasterBehavior.Popover;
        }

        //protected override void OnSizeAllocated(double width, double height)
        //{
        //    base.OnSizeAllocated(width, height);
        //    if(Device.Idiom == TargetIdiom.Tablet)
        //    {
        //        if (width < height)
        //        {
        //            this.IsGestureEnabled = Navigation.NavigationStack.Count > 1;
        //        }
        //        else
        //        {
        //            IsGestureEnabled = true;
        //        }
        //    }
        //}
    }
}
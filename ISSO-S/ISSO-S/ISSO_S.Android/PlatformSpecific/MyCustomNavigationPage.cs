using Android.Content;
using Android.Support.V4.Widget;
using Android.Views;
using ISSO_S.Droid.CustomRenderers;
using ISSO_S.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;
using Xamarin.Forms.Platform.Android.AppCompat;
using static Android.Views.View;

[assembly: ExportRenderer(typeof(MyNavigationPage), typeof(MyCustomNavigationPage))]
namespace ISSO_S.Droid.PlatformSpecific
{
    public class MyCustomNavigationPage : NavigationPageRenderer
    {
        public MyCustomNavigationPage(Context context) : base(context) { }

        protected override void OnAttachedToWindow()
        {
            base.OnAttachedToWindow();
            var page = Element.Parent;
            MasterDetailPage masterDetailPage = null;
            while (page != null)
            {
                if (page is MasterDetailPage)
                {
                    masterDetailPage = page as MasterDetailPage;
                    break;
                }

                page = page.Parent;
            }

            if (masterDetailPage == null)
            {
                return;
            }

	        if (!(Platform.GetRenderer(masterDetailPage) is MasterDetailPageRenderer renderer))
            {
                return;
            }

            var drawerLayout = (DrawerLayout)renderer;
            Android.Support.V7.Widget.Toolbar toolbar = null;

            for (var i = 0; i < ChildCount; i++)
            {
                var child = GetChildAt(i);
                toolbar = child as Android.Support.V7.Widget.Toolbar;
                if (toolbar != null)
                {
                    break;
                }
            }
            toolbar?.SetNavigationOnClickListener(new MenuClickListener(Element, drawerLayout));
        }
    }

    public class MenuClickListener : Java.Lang.Object, IOnClickListener
    {
	    private readonly NavigationPage _navigationPage;
	    private readonly DrawerLayout _layout;

        public MenuClickListener(NavigationPage navigationPage, DrawerLayout layout)
        {
            _navigationPage = navigationPage;
            _layout = layout;
        }

        public void OnClick(Android.Views.View v)
        {
            var page = _navigationPage.CurrentPage as AddNewRating;
            if (_navigationPage.Navigation.NavigationStack.Count <= 1)
            {
                _layout.OpenDrawer((int)GravityFlags.Left);
            }

            if (page != null)
            {
                //if (page.OnNavigationBackButtonPressed())
                //{
                //    navigationPage?.PopAsync();
                //}
                page.OnNavigationBackButtonPressed();
            }
            else
            {
                _navigationPage?.PopAsync();
            }
        }
    }
}
using Android.Content;
using Android.Support.V4.Widget;
using Android.Views;
using CommonClassesLibrary.BackHandlers;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;
using Xamarin.Forms.Platform.Android.AppCompat;

//[assembly: ExportRenderer(typeof(MyNavigationPage), typeof(MyNavRenderer))]

namespace ISSO_I.Droid.PlatformSpecific
{
	internal class MyNavRenderer : NavigationPageRenderer
    {
        public MyNavRenderer(Context context) : base(context) { }

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

        private class MenuClickListener : Java.Lang.Object, IOnClickListener
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
                var page = _navigationPage.CurrentPage as MyBackHandleContentPage;
                if (_navigationPage.Navigation.NavigationStack.Count <= 1)
                {
                    _layout.OpenDrawer((int)GravityFlags.Left);
                }

                else
                {
                    if (page != null)
                    {
                        page.OnNavigationBackButtonPressed();
                    }
                    else
                    {
                        _navigationPage?.PopAsync();
                    }
                }
            }
        }
    }
}
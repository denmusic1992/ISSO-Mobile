
using Android.App;
using Android.Content.PM;
using Android.OS;
using CarouselView.FormsPlugin.Android;
using ISSO_I.Droid.PlatformSpecific;
using Plugin.CurrentActivity;
using Plugin.Permissions;
using Xamarin.Forms;

namespace ISSO_I.Droid
{
    [Activity(Label = "АБДМ Mobile", Icon = "@drawable/ABDM", Theme = "@style/MainTheme", MainLauncher = false, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public class MainActivity : Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        protected override void OnCreate(Bundle bundle)
        {
            TabLayoutResource = Resource.Layout.Tabbar;
            ToolbarResource = Resource.Layout.Toolbar;

            base.OnCreate(bundle);
            Forms.SetFlags("FastRenderers_Experimental");
            Forms.Init(this, bundle);
            LoadApplication(new App());

            // INIT
            Xamarin.FormsGoogleMaps.Init(this, bundle);
            Stormlion.PhotoBrowser.Droid.Platform.Init(this);
            Rg.Plugins.Popup.Popup.Init(this, bundle);
			Plugin.InputKit.Platforms.Droid.Config.Init(this, bundle);
            CrossCurrentActivity.Current.Init(this, bundle);
            DependencyService.Register<AndroidDateImageClass>();
            FFImageLoading.Forms.Platform.CachedImageRenderer.Init(true);
	        CarouselViewRenderer.Init();
			XamForms.Controls.Droid.Calendar.Init();

	        //Android.Support.V7.Widget.Toolbar toolbar = this.FindViewById<Android.Support.V7.Widget.Toolbar>(Resource.Id.toolbar);
	        //SetSupportActionBar(toolbar);
        }

        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, Permission[] grantResults)
        {
            base.OnRequestPermissionsResult(requestCode, permissions, grantResults);
            PermissionsImplementation.Current.OnRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        /// <summary>
        /// Handle Back press for RG.POPUP
        /// </summary>
        public override void OnBackPressed()
        {
            if (Rg.Plugins.Popup.Popup.SendBackPressed(base.OnBackPressed))
            {
                // Do something if there are some pages in the `PopupStack`
            }
            //else
            //{
            //    var currentPage = (((MasterDetailPage1)App.Current.MainPage).Detail as MyNavigationPage).Navigation.NavigationStack.LastOrDefault();
            //    switch (currentPage)
            //    {
            //        case MyBackHandleContentPage page:
            //            if ((currentPage as MyBackHandleContentPage)?.CustomBackButtonAction != null)
            //            {
            //                (currentPage as MyBackHandleContentPage)?.CustomBackButtonAction.Invoke();
            //            }
            //            else
            //            {
            //                base.OnBackPressed();
            //            }
            //            break;
            //    }
            //    // Do something if there are not any pages in the `PopupStack`
            //}
        }

        //public override bool OnOptionsItemSelected(IMenuItem item)
        //{
        //    // check if the current item id 
        //    // is equals to the back button id
        //    фif (item.ItemId == 16908332)
        //    {
        //        // retrieve the current xamarin forms page instance
        //        var currentpage = Xamarin.Forms.Application.Current.MainPage.Navigation.NavigationStack.LastOrDefault();
        //        switch (currentpage)
        //        {
        //            case MyBackHandleContentPage page:
        //                if ((currentpage as MyBackHandleContentPage)?.CustomBackButtonAction != null)
        //                {
        //                    (currentpage as MyBackHandleContentPage)?.CustomBackButtonAction.Invoke();
        //                    return false;
        //                }
        //                break;
        //        }

        //        // if its not subscribed then go ahead 
        //        // with the default back button action
        //        return base.OnOptionsItemSelected(item);
        //    }
        //    else
        //    {
        //        // since its not the back button 
        //        //click, pass the event to the base
        //        return base.OnOptionsItemSelected(item);
        //    }
        //}
    }   
}
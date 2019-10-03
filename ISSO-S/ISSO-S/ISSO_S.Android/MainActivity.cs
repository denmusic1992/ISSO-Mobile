
using Android.App;
using Android.Content.PM;
using Android.Views;
using Android.Widget;
using Android.OS;
using Xamarin.Forms.Platform.Android;
using Xamarin.Forms;
using ISSO_S.Droid;
using ISSO_S;
using Plugin.Permissions;
using System.Linq;
using Xamarin.Forms.Platform.Android.AppCompat;
using Android.Support.V4.Widget;
using static Android.Views.View;
using System.Collections.Generic;
using Android.Graphics.Drawables;
using Android.Text;
using Android.Content.Res;
using CommonClassesLibrary;
using Android.Content;

namespace ISSO_S.Droid
{
    [Activity (Label = "АИС ИССО-SX", Icon = "@drawable/launcher", Theme="@style/MainTheme", MainLauncher = false, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
	public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
	{
		protected override void OnCreate (Bundle bundle)
		{
			TabLayoutResource = Resource.Layout.Tabbar;
			ToolbarResource = Resource.Layout.Toolbar;
            base.OnCreate (bundle);
			global::Xamarin.Forms.Forms.Init (this, bundle);
            LoadApplication (new ISSO_S.App ());
            var nameForDraw = new Dictionary<string, int>
            {
                { "marker_ahead.png", Resource.Drawable.marker_ahead },
                { "marker_far.png", Resource.Drawable.marker_far },
                { "marker_behind.png", Resource.Drawable.marker_behind }
            };
            Xamarin.FormsGoogleMaps.Init(this, bundle);
            Rg.Plugins.Popup.Popup.Init(this, bundle);
            DependencyService.Register<AndroidDateImageClass>();
            Plugin.CurrentActivity.CrossCurrentActivity.Current.Init(this, bundle);
            Stormlion.PhotoBrowser.Droid.Platform.Init(this);
        }


        protected override void OnPostCreate(Bundle savedInstanceState)
        {
            Android.Support.V7.Widget.Toolbar toolbar = FindViewById<Android.Support.V7.Widget.Toolbar>(Resource.Id.toolbar);
            SetSupportActionBar(toolbar);
            base.OnPostCreate(savedInstanceState);
        }

        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, Permission[] grantResults)
        {
            PermissionsImplementation.Current.OnRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        public override void OnBackPressed()
        {
            if (Rg.Plugins.Popup.Popup.SendBackPressed(base.OnBackPressed))
                return;

            // this is not necessary, but in Android user 
            // has both Nav bar back button and
            // physical back button its safe 
            // to cover the both events

            // retrieve the current xamarin forms page instance
            //var currentpage =
            //Xamarin.Forms.Application.
            //Current.MainPage.Navigation.
            //NavigationStack.LastOrDefault() as MyContentPage;

            //// check if the page has subscribed to 
            //// the custom back button event
            //if (currentpage?.CustomBackButtonAction != null)
            //{
            //    currentpage?.CustomBackButtonAction.Invoke();
            //}
            //else
            //{
            //    if (Rg.Plugins.Popup.Popup.SendBackPressed(base.OnBackPressed))
            //        return;
            //    else
            //        base.OnBackPressed();
            //}
        }

        public override bool OnOptionsItemSelected(IMenuItem item)
        {
            // check if the current item id 
            // is equals to the back button id
            if (item.ItemId == 16908332)
            {
                // retrieve the current xamarin forms page instance
                var currentpage = Xamarin.Forms.Application.Current.MainPage.Navigation.NavigationStack.LastOrDefault();
                switch (currentpage)
                {
                }

                // if its not subscribed then go ahead 
                // with the default back button action
                return base.OnOptionsItemSelected(item);
            }
            else
            {
                // since its not the back button 
                //click, pass the event to the base
                return base.OnOptionsItemSelected(item);
            }
        }
    }

}


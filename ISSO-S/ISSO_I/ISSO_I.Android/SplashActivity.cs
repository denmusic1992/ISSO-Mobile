
using Android.App;
using Android.Content.PM;
using Android.Support.V7.App;

namespace ISSO_I.Droid
{
    [Activity(Label = "АИС ИССО-IX", Icon = "@drawable/ABDM", Theme = "@style/MainTheme.Splash", NoHistory = true, MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    class SplashActivity : AppCompatActivity
    {
        //protected override void OnCreate(Bundle savedInstanceState)
        //{
        //    base.OnCreate(savedInstanceState);
        //    SetContentView(Resource.Layout.splash);
        //}

        protected override void OnResume()
        {
            base.OnResume();
            StartActivity(typeof(MainActivity));
        }

        public override void OnBackPressed() { }
    }
}
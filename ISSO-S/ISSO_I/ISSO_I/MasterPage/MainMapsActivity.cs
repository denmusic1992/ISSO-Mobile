using Autoselect;
using ISSO_I.CustomRenderes;
using System;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;

namespace ISSO_I.MasterPage
{
    public class MainMapsActivity : MapsActivity
    {
        private bool Tapped { get; set; }

        public MainMapsActivity(HttpsIsso[] listOfIssos) : base(listOfIssos) { }

        public override async void FarTapped(object sender, EventArgs e)
        {
            if (Tapped) return;
            Tapped = true;
	        if (HashMap == null || HashMap.GetIndex(1) == -1) return;
	        DisableGeolocator();
	        ToIssoView = true;
	        var issoViewActivity = new IssoViewActivity(ListOfIssos[HashMap.GetIndex(1)].CIsso);
	        await ((MyNavigationPage) ((MasterDetailPage1) Application.Current.MainPage).Detail).Navigation.PushAsync(issoViewActivity);
	        Tapped = false;
        }

        public override async void NearTapped(object sender, EventArgs e)
        {
            if (Tapped) return;
            Tapped = true;

            if (HashMap != null && HashMap.GetIndex(0) != -1)
            {
                DisableGeolocator();
                ToIssoView = true;
                var issoViewActivity = new IssoViewActivity(ListOfIssos[HashMap.GetIndex(0)].CIsso);
                await ((MasterDetailPage1) Application.Current.MainPage).Detail.Navigation.PushAsync(issoViewActivity);
                Tapped = false;
            }
        }

        public override async void BehindTapped(object sender, EventArgs e)
        {
            if (Tapped) return;
            Tapped = true;

            if (HashMap != null && HashMap.GetIndex(2) != -1)
            {
                DisableGeolocator();
                ToIssoView = true;
                var issoViewActivity = new IssoViewActivity(ListOfIssos[HashMap.GetIndex(2)].CIsso);
                await ((MasterDetailPage1) Application.Current.MainPage).Detail.Navigation.PushAsync(issoViewActivity);
                Tapped = false;
            }
        }

        public override void ShowAlert(string str)
        {
            DependencyService.Get<IMessage>().ShortAlert(str);
        }
    }
}

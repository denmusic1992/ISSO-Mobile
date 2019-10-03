using System;
using CommonClassesLibrary;
using ISSO_I.CustomRenderes;
using ISSO_I.MasterPage;
using ISSO_I.Sqlite;

namespace ISSO_I
{
    public partial class App
    {

        //public MyNavigationPage Detail { get; set; }

        /// <summary>
        /// 
        /// </summary>
        //public MasterDetailPage1 detailPage { get; set; }

        /// <summary>
        /// Тип приложения (SX, RX или IX)
        /// </summary>
        public static ApplicationType AppType = ApplicationType.IssoI;

	    //public static DefectContext SqliteRepository { get; set; }

	    public static readonly string DatabaseName = "isso_i.db";

	    public App()
        {
            //MainPage = new MasterDetailPage1(appType);
            //var detailPage = new MasterDetailPage1();
            //var Detail = new MainPageLinearList(appType);
            //detailPage.Detail = Detail;
            //detailPage.Master = new MasterDetailPage1Master();
            //MainPage = new MyNavigationPage(detailPage) { BarBackgroundColor = Color.FromHex("#104e8b"), BarTextColor = Color.White };
	        try
	        {
		        MainPage = new MasterDetailPage1
		        {
			        Master = new MasterDetailPage1Master(),
			        Detail = new MyNavigationPage(new MainPageLinearList(AppType))
		        };
	        }
	        catch (Exception ex)
	        {
		        Console.WriteLine(ex.Message);
				Console.WriteLine(ex.InnerException);
		        Console.WriteLine(ex.HResult);
		        Console.WriteLine(ex.StackTrace);
	        }

            InitializeComponent();
        }

        protected override void OnStart()
        {
            // Handle when your app starts
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        }
    }
}

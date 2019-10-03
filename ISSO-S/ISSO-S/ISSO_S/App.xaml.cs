using SQLite;
using System;
using CommonClassesLibrary;
using ISSO_S.Droid.CustomRenderers;
using Xamarin.Forms;
using static CommonClassesLibrary.DBHelper;

// ReSharper disable once CheckNamespace
namespace ISSO_S
{
    public partial class App
    {
        /// <summary>
        /// 
        /// </summary>
        public MyNavigationPage Detail { get; set; }
        /// <summary>
        /// 
        /// </summary>
        public static MasterDetailPage1 DetailPage { get; set; }
        /// <summary>
        /// Тип приложения (SX, RX или IX)
        /// </summary>
        public static ApplicationType AppType { get; set; } = ApplicationType.IssoS;
        /// <summary>
        /// Путь к папке с фотографиями приложения
        /// </summary>
        public static string PathToPhoto { get; set; } = Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/Pictures/ISSO-S/";

        public App ()
        {
            DetailPage = new MasterDetailPage1();
            Detail = new MyNavigationPage(new MainPageLinearList(AppType)) { BarBackgroundColor = Color.FromHex("#104e8b"), BarTextColor = Color.White };
            DetailPage.Detail = Detail;
            MainPage = DetailPage;
            InitializeComponent();
            // Добавление данных по умолчанию, если их нет
            if (!Current.Properties.TryGetValue("address", out object _))
            {
                Current.Properties.Add("address", "mobile.aisisso.ru");
            }
            if (!Current.Properties.TryGetValue("port", out object _))
            {
                Current.Properties.Add("port", 8790);
            }
            if (!Current.Properties.TryGetValue("support_port", out var _))
            {
                Current.Properties.Add("support_port", 80);
            }
        }

        protected override void OnStart ()
		{
			// Handle when your app starts
		}

		protected override void OnSleep ()
		{
			// Handle when your app sleeps
		}

		protected override void OnResume ()
		{
			// Handle when your app resumes
		}

        /// <summary>
        /// Метод подключения к БД SQLITE
        /// </summary>
        /// <returns></returns>
        public static SQLiteConnection CreateDatabase()
        {
            var databasePath = System.IO.Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Personal), "isso_s.db");
	        var connection = new SQLiteConnection(databasePath);
	        connection.CreateTable<I_ISSO>();
	        var sql = "create table if not exists RATING ("
	                     + "C_ISSO integer,"
	                     + "RATINGDATE DATETIME,"
	                     + "RATINGDATEEDIT DATETIME,"
	                     + "RATINGS integer,"
	                     + "LATITUDE_RATING REAL,"
	                     + "LONGITUDE_RATING REAL,"
	                     + "COMMENTS text,"
	                     + "SYNC boolean,"
	                     + "OFFSET long,"
	                     + "CURRENTRATING integer DEFAULT (0),"
	                     + "CHECKOUTOFPLAN boolean DEFAULT (0),"
	                     + "primary key(C_ISSO, RATINGDATE)"
	                     + ")";
	        connection.Execute(sql);
	        sql = "create table if not exists PHOTOS_RATING ("
	              + "C_ISSO integer,"
	              + "RATINGDATE DATETIME,"
	              + "PHOTOPATH text,"
	              + "PHOTODATE DATETIME,"
	              + "COMMENT text,"
	              + "SYNC boolean,"
	              + "primary key(C_ISSO, RATINGDATE, PHOTODATE)"
	              + ")";
	        connection.Execute(sql);
	        //connection.CreateTable<RATING>();
	        //connection.CreateTable<PHOTOS_RATING>();
	        return connection;
        }
    }

}

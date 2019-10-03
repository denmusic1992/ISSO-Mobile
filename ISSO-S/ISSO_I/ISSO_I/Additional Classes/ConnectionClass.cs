using SQLite;
using System;
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;

namespace ISSO_I.Additional_Classes
{
    public class ConnectionClass
    {


        /// <summary>
        /// Путь к папке с фотографиями приложения
        /// </summary>
        public static string PathToSchemes { get; set; } = Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/pdfjs/";

        /// <summary>
        /// Путь для SQLite.SqliteConnection
        /// </summary>
        public static string DatabasePath = DependencyService.Get<IPath>().GetDatabasePath(App.DatabaseName);
        /// <summary>
        /// Путь для SqliteConnection
        /// </summary>
        public static string NewDatabasePath { get; set; } =
	        $"Data Source = {DependencyService.Get<IPath>().GetDatabasePath(App.DatabaseName)}; Version = 3;";

        /// <summary>
        /// Метод подключения к БД SQLITE
        /// </summary>
        /// <returns></returns>
        public static SQLiteConnection CreateDatabase()
        {
            try
            {
                var connection = new SQLiteConnection(DatabasePath);
                connection.CreateTable<DBHelper.I_ISSO>();

                var sql = "create table if not exists TABLE_NAMES ("
                    + "C_GR_CONSTR integer primary key,"
                    + "SYS_NAME text,"
                    + "DESCRIPTION text,"
                    + "PARENT_ID integer,"
                    + "PARENT_VIEW integer" + ")";
                connection.Execute(sql);

                sql = "create table if not exists TABLE_ATTRIBUTES ("
                    + "ID integer primary key,"
                    + "C_GR_CONSTR integer,"
                    + "SYS_NAME text,"
                    + "DATA_TYPE text,"
                    + "DESCRIPTION text,"
                    + "IS_BLOB boolean,"
                    + "CATEGORY text,"
                    + "VISIBLEINGRID integer" + ")";
                connection.Execute(sql);

                sql = "create table if not exists TABLE_VALUES ("
                    + "ATTRIBUTE_ID integer,"
                    + "ISSO integer,"
                    + "VALUE text" + ")";
                connection.Execute(sql);

                sql = "create table if not exists TABLE_DELEGATES ("
                    + "ISSO_TYPE integer,"
                    + "C_GR_CONSTR integer" + ")";
                connection.Execute(sql);

                sql = "create table if not exists UPLOAD_PHOTOS ("
                    + "C_ISSO integer,"
                    + "N integer,"
                    + "COMMENTS text,"
                    + "PHOTO blob,"
                    + "PHOTO_DATE long" + ")";
                connection.Execute(sql);

                sql = "create table if not exists UPLOAD_SCHEMES ("
                    + "C_ISSO integer,"
                    + "N integer,"
                    + "COMMENTS text,"
                    + "SCHEME blob,"
                    + "THUMBNAIL blob,"
                    + "SCHEME_DATE long" + ")";
                connection.Execute(sql);

                connection.CreateTable<DBHelper.ADVANCED_S_TABLES>();
                connection.CreateTable<DBHelper.S_INFO_TABLES>();
                return connection;
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine(ex.ToString());
                return null;
            }
        }
    }
}

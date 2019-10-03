using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.IO;
using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using ISSO_I.Interfaces;
using Mono.Data.Sqlite;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class PDFContentView
	{

        private List<PdfDocEntity> _pdfDocEntities = new List<PdfDocEntity>();

        public PDFContentView(int cIsso, int n)
        {
            InitializeComponent();
            Title = $"ИССО №{cIsso}. Просмотр схемы";
            

            using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
	            try
	            {
		            var command = connection.CreateCommand();
		            command.CommandText =
			            $"select * from I_SXEMA where C_ISSO={cIsso} and N={n} and SXEMA is not null order by N";
		            command.CommandTimeout = 30;
		            command.CommandType = CommandType.Text;
		            connection.Open();
		            using (var datareader = command.ExecuteReader())
		            {
			            if (datareader.HasRows)
			            {
				            datareader.Read();
				            // Берем данные по фотографии
				            var photo = Convert.FromBase64String(datareader.GetString(datareader.GetOrdinal("SXEMA")));
				            // Записываем файл в папку
				            var newPath =
					            $"{ConnectionClass.PathToSchemes}/{CommonStaffUtils.RandomNumber(1, 9999999)}.pdf";
				            if (!Directory.Exists(ConnectionClass.PathToSchemes))
					            Directory.CreateDirectory(ConnectionClass.PathToSchemes);
				            File.WriteAllBytes(newPath, photo);
				            //if (Device.RuntimePlatform == Device.Android)
				            //    PdfDocView.Source = $"file:///android_asset/pdfjs/web/viewer.html?file={WebUtility.UrlEncode(newPath)}";
				            //else
				            //    PdfDocView.Source = newPath;
				            PdfDocView.Uri = newPath;
			            }
			            datareader.Dispose();
		            }
					command.Dispose();
	            }
	            catch (Exception ex)
	            {
		            Debug.WriteLine($"Произошла ошибка в БД: {ex.Message} \nStackTrace: {ex.StackTrace}");
	            }
	            finally
	            {
		            connection.Close();
	            }
            }
        }

		public override void Dispose()
        {
            // Чистим предыдущие фотки
            if (Directory.Exists(ConnectionClass.PathToSchemes))
            {
                var di = new DirectoryInfo(ConnectionClass.PathToSchemes);
                foreach (var file in di.GetFiles())
                {
                    file.Delete();
                }
            }
            DependencyService.Get<IClearCacheInterface>().Clear();
            _pdfDocEntities.Clear(); _pdfDocEntities = null;
            PdfDocView = null;
            BindingContext = null;
            Content = null;
            GC.Collect();
        }

        //private void SetBusyIndicator(bool isBusyIndicatorIsVisible) => BusyIndicator.IsRunning = BusyIndicator.IsVisible = isBusyIndicatorIsVisible;
    }
}
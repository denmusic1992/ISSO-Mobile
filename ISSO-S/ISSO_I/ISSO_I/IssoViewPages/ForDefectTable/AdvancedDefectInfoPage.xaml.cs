using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using ISSO_I.IssoViewPages.ForDefectTable;
using Mono.Data.Sqlite;
using Stormlion.PhotoBrowser;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Globalization;
using System.IO;
using System.Linq;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class AdvancedDefectInfoPage
	{
        /// <summary>
        /// Список дефектов
        /// </summary>
        private List<Ais7AdvancedDefect> _advancedDefects = new List<Ais7AdvancedDefect>();

        /// <summary>
        /// Выбранное раскрытие дефекта
        /// </summary>
        private int _selectedItem;

        /// <summary>
        /// Список раскрытия дефекта
        /// </summary>
        ObservableCollection<Ais7InfoDefect> _defects;

        private string _newPath;


		/// <summary>
		/// Конструктор
		/// </summary>
		/// <param name="cIsso">Номер ИССО</param>
		/// <param name="nDef">Номер дефекта</param>
		public AdvancedDefectInfoPage (int cIsso, int nDef)
		{
			InitializeComponent();
            Title = $"ИССО № {cIsso}. Дефект №{nDef}. Подробно";
            PrepareInfoDefects(cIsso, nDef);
            SetAdvancedDefectInfo(cIsso, nDef);
		}

        //protected override void OnAppearing()
        //{
        //    base.OnAppearing();
        //    var lv_height = 0;
        //    foreach(var def in advancedDefects)
        //    {
        //        lv_height += 60;
        //    }
        //    lv_def_items.HeightRequest = lv_height;
        //}


        /// <summary>
        /// Взять данные по дефекту и БД
        /// </summary>
        /// <param name="cIsso">Номер ИССО</param>
        /// <param name="nDef">Номер дефекта</param>
        private void PrepareInfoDefects(int cIsso, int nDef)
        {
            try
            {
                // Параметр который будем возвращать
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    var command = connection.CreateCommand();
                    command.CommandText =
	                    "select i_defect.c_isso, i_defect.n_def, I_DEF_MOD.date as date_mod, i_defect.c_defect, i_defect.c_gr_constr, " +
	                    " coalesce(ord, s_gr_constr.MAIN_CONSTR_ID) as ord, i_defect.n_constr, I_DEF_DESCR.date, " +
	                    " I_DEF_DESCR.B, I_DEF_DESCR.B1, I_DEF_DESCR.D, I_DEF_DESCR.D1, I_DEF_DESCR.R, I_DEF_DESCR.R1, I_DEF_DESCR.G, I_DEF_DESCR.G1, " +
	                    " i_defect.DATE as date_defect, i_defect.DATEF as date_end, i_def_mod.l_def, i_def_mod.w_def, s_defect.n_defect, c_defparam, n_rem, " +
	                    " sn_unit_dime, v_rem, count(i_foto_def.n_def) as fotocount, s_gr_constr.MAIN_CONSTR_ID " +
	                    " from i_defect  " + " left outer join i_def_mod on  " +
	                    " i_def_mod.c_isso = i_defect.c_isso and  " + " i_def_mod.n_def = i_defect.n_def " +
	                    " left outer join I_DEF_DESCR on i_defect.C_ISSO = I_DEF_DESCR.C_ISSO and" +
	                    " i_defect.N_DEF = I_DEF_DESCR.N_DEF and I_DEF_MOD.DATE=I_DEF_DESCR.DATE" +
	                    " left outer join s_defect on s_defect.c_defect = i_defect.c_defect " +
	                    " left outer join s_rem on s_rem.c_rem = i_def_mod.c_rem " +
	                    " left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen = s_rem.ind_unit " +
	                    " left outer join i_foto_def on i_foto_def.c_isso = i_defect.c_isso and i_foto_def.n_def = i_defect.n_def and " +
	                    " i_foto_def.date = i_def_mod.date " +
	                    " left outer join s_gr_constr on s_gr_constr.C_GR_CONSTR = i_defect.C_GR_CONSTR " +
	                    $" where i_defect.c_isso = {cIsso} and i_defect.N_DEF = {nDef} " +
	                    " group by i_defect.c_isso, i_defect.n_def, I_DEF_MOD.date, i_defect.c_defect, i_defect.c_gr_constr, i_defect.n_constr, I_DEF_DESCR.date, " +
	                    " I_DEF_DESCR.B, I_DEF_DESCR.B1, I_DEF_DESCR.D, I_DEF_DESCR.D1, I_DEF_DESCR.R, I_DEF_DESCR.R1, I_DEF_DESCR.G, I_DEF_DESCR.G1, " +
	                    " i_defect.DATE, i_defect.DATEF, i_def_mod.l_def, n_rem, sn_unit_dime, v_rem, l_def, i_def_mod.w_def, n_defect, c_defparam, " +
	                    " s_gr_constr.c_gr_constr, ord, s_gr_constr.MAIN_CONSTR_ID order by I_DEF_DESCR.date";
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    using (var datareader = command.ExecuteReader())
                    {
                        if (datareader.HasRows)
                        {
	                        while (datareader.Read())
                            {
                                var issoDefect = new Ais7AdvancedDefect();
                                
                                var b = datareader["B"] != DBNull.Value ? Convert.ToInt16(datareader["B"]) : 0;
                                var b1 = datareader["B1"] != DBNull.Value ? Convert.ToInt16(datareader["B1"]) : 0;
                                var d = datareader["D"] != DBNull.Value ? Convert.ToInt16(datareader["D"]) : 0;
                                var d1 = datareader["D1"] != DBNull.Value ? Convert.ToInt16(datareader["D1"]) : 0;
                                var r = datareader["R"] != DBNull.Value ? Convert.ToInt16(datareader["R"]) : 0;
                                var r1 = datareader["R1"] != DBNull.Value ? Convert.ToInt16(datareader["R1"]) : 0;
                                var g = Convert.ToInt16(datareader["G"]) == 1;
                                var g1 = Convert.ToInt16(datareader["G1"]) == 1;

                                issoDefect.L_DEF = datareader["L_DEF"] != DBNull.Value ? Convert.ToString(datareader["L_DEF"]) : "";
                                issoDefect.W_DEF = datareader["W_DEF"] != DBNull.Value ? Convert.ToString(datareader["W_DEF"]) : "";
                                issoDefect.N_REM = datareader["N_REM"] != DBNull.Value ? Convert.ToString(datareader["N_REM"]) : "";
                                issoDefect.REM_DIMENSION = datareader["SN_UNIT_DIME"] != DBNull.Value ? $", {Convert.ToString(datareader["SN_UNIT_DIME"])}"
	                                : "";
                                issoDefect.REM_SIZE = datareader["V_REM"] != DBNull.Value ? Convert.ToDouble(datareader["V_REM"]).ToString(CultureInfo.CurrentCulture) : "не требуется";
                                issoDefect.Date = datareader["date_mod"] != DBNull.Value ? Convert.ToInt64(datareader["date_mod"]) : 0;
                                issoDefect.DateStr = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(issoDefect.Date).ToString("dd.MM.yyyy");
                                issoDefect.PhotoDef = datareader["fotocount"] != DBNull.Value && Convert.ToInt32(datareader["fotocount"]) > 0;
                                issoDefect.BDRG =
	                                $"Б{(b == b1 ? Convert.ToString(b) : $"{b}/{b1}")}, Д{(d == d1 ? Convert.ToString(d) : $"{d}/{d1}")}, Р{(r == r1 ? Convert.ToString(r) : $"{r}/{r1}")}{(g ? (!g1 ? ", (Г)" : ", Г") : "")}";
                                // определяемся с параметрами
                                var param = "";
                                var defparam = "";
                                var commandParam = connection.CreateCommand();
                                commandParam.CommandTimeout = 30;
                                commandParam.CommandType = System.Data.CommandType.Text;
                                commandParam.CommandText = "select i_defect.n_def, s_defparam.c_defparam, " +
                                                           " n_defparam, category, value, sn_unit_dime, i_def_mod.date, min(s_defparamvalue.c_unit_dimen) c_unit_dimen " +
                                                           " from i_def_descr " +
                                                           " left outer join i_def_mod on i_def_descr.c_isso = i_def_mod.c_isso and i_def_descr.n_def = " +
                                                           " i_def_mod.n_def and i_def_descr.date=i_def_mod.date " +
                                                           " left outer join i_defect on i_def_mod.c_isso = i_defect.c_isso and i_def_mod.n_def = i_defect.n_def " +
                                                           " left outer join s_defparam on s_defparam.c_defparam = i_def_descr.c_defparam " +
                                                           " left outer join s_defparamvalue on s_defparamvalue.c_defect = i_defect.c_defect and " +
                                                           " s_defparamvalue.c_gr_constr = i_defect.c_gr_constr and " +
                                                           " s_defparamvalue.c_defparam = i_def_descr.c_defparam " +
                                                           " left outer join s_unit_dimension on s_defparamvalue.c_unit_dimen = s_unit_dimension.c_unit_dimen " +
                                                           $" where i_def_descr.c_isso = {cIsso} and i_defect.N_DEF = {nDef} and i_def_mod.date = {Convert.ToInt64(datareader["date_mod"])} and i_def_descr.c_defparam = {Convert.ToInt32(datareader["C_DEFPARAM"])}" +
                                                           " group by i_defect.n_def, s_defparam.c_defparam, n_defparam, category, value, sn_unit_dime, i_def_mod.date " +
                                                           " order by I_DEF_MOD.date";
                                using (var datareaderParam = commandParam.ExecuteReader())
                                {
                                    if (datareaderParam.HasRows)
                                    {
                                        while (datareaderParam.Read())
                                        {
                                            if (Convert.ToString(datareaderParam["N_DEF"]).Equals(Convert.ToString(nDef)))
                                            {
                                                var pv = new Ais7DefectParamValue(Convert.ToInt16(datareaderParam["C_DEFPARAM"]));
                                                param =
	                                                $"{param}{pv.GetFullName()}{(datareaderParam["SN_UNIT_DIME"] != DBNull.Value ? $", ({Convert.ToString(datareaderParam["SN_UNIT_DIME"])})" : "")}";

                                                defparam =
	                                                $"{defparam}{(datareaderParam["VALUE"] != DBNull.Value ? $"{Convert.ToString(datareaderParam["VALUE"])}" : "")}";
                                            }
                                        }
                                    }
                                }
                                issoDefect.Param = param;
                                issoDefect.DefParam = defparam;
                                _advancedDefects.Add(issoDefect);
                            }
                        }
                    }
                    connection.Close();
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }

  //      protected override void OnSizeAllocated(double width, double height)
  //      {
  //          base.OnSizeAllocated(width, height);
		//	foreach (RowDefinition row_def in grid_adv_char_def.RowDefinitions)
		//	{
		//		var lbl_height = DependencyService.Get<ITextMeter>().MeasureTextSize(
		//						(grid_adv_char_def.Children.Where(x => Grid.GetRow(x) == grid_adv_char_def.RowDefinitions.IndexOf(row_def)
		//						&& Grid.GetColumn(x) == 1).ToList()[0] as Label).Text,
		//						this.Width / 2,
		//						(grid_adv_char_def.Children.Where(x => Grid.GetRow(x) == grid_adv_char_def.RowDefinitions.IndexOf(row_def)
		//						&& Grid.GetColumn(x) == 1).ToList()[0] as Label).FontSize);
		//		row_def.Height = new GridLength(lbl_height, GridUnitType.Absolute);
		//	}

		//	foreach (RowDefinition row_def in grid_rem_char_def.RowDefinitions)
		//	{
		//		var lbl_height = DependencyService.Get<ITextMeter>().MeasureTextSize(
		//						(grid_adv_char_def.Children.Where(x => Grid.GetRow(x) == grid_rem_char_def.RowDefinitions.IndexOf(row_def)
		//						&& Grid.GetColumn(x) == 1).ToList()[0] as Label).Text,
		//						this.Width / 2,
		//						(grid_adv_char_def.Children.Where(x => Grid.GetRow(x) == grid_rem_char_def.RowDefinitions.IndexOf(row_def)
		//						&& Grid.GetColumn(x) == 1).ToList()[0] as Label).FontSize);
		//		row_def.Height = new GridLength(lbl_height, GridUnitType.Absolute);
		//	}
		//}

        /// <summary>
        /// Установка значений по дефекту
        /// </summary>
        void SetAdvancedDefectInfo(int cIsso, int nDef)
        {
            // Заполняем текстовые значения
            localization_label.Text = _advancedDefects[_selectedItem].L_DEF;
            advanced_info_label.Text = _advancedDefects[_selectedItem].W_DEF;
            rem_work_label.Text = _advancedDefects[_selectedItem].N_REM;
            name_rem_volume_label.Text = $"Объем ремонтной работы {_advancedDefects[_selectedItem].REM_DIMENSION}";
            //rem_volume_label.Text = Convert.ToDouble(advancedDefects[SelectedItem].REM_SIZE).ToString("F3");
            if(_advancedDefects[_selectedItem].REM_SIZE.Equals("не требуется"))
            {
                rem_volume_label.Text = _advancedDefects[_selectedItem].REM_SIZE;
            }
            else
            {
                var volume = Convert.ToDouble(_advancedDefects[_selectedItem].REM_SIZE);
                //if (volume - Math.Truncate(volume) > 0)
                //    rem_volume_label.Text = String.Format("{0:0.000}", volume);
                //else
                rem_volume_label.Text = $"{volume:0.000}";
            }

            // Добавление фотографии
            if (_advancedDefects[_selectedItem].PhotoDef)
            {
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    // Получаем фото из БД в соответствии с датой и номером дефекта
                    var command = connection.CreateCommand();
                    command.CommandType = System.Data.CommandType.Text;
                    command.CommandTimeout = 30;
                    command.CommandText =
	                    $"select FOTO from I_FOTO_DEF where C_ISSO={cIsso} and N_DEF={nDef} and date={_advancedDefects[_selectedItem].Date}";
                    connection.Open();
                    using (var reader = command.ExecuteReader())
                    {
                        if(reader.HasRows)
                        {
                            reader.Read();
                            var photoArray = Convert.FromBase64String(reader.GetString(0));
                            photo_def_img.Source = ImageSource.FromStream(() => new MemoryStream(photoArray));
                            _newPath =
	                            $"{Environment.GetFolderPath(Environment.SpecialFolder.Personal)}/ISSO-I/{CommonStaffUtils.RandomNumber(1, 999999)}";
                            // если нет такой папки на устройстве, то создаем
                            if (!Directory.Exists(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/"))
                                Directory.CreateDirectory(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/");
                            var list = Directory.GetFiles(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/", "*");
                            // Очищаем фотографии в этой папке
                            if (list.Length > 0)
                            {
                                foreach (var l in list)
                                {
                                    File.Delete(l);
                                }
                            }
                            Directory.GetFiles(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/", "*");

                            using (var fileStream = new FileStream(_newPath, FileMode.CreateNew, FileAccess.Write))
                            {
                                fileStream.Write(photoArray, 0, photoArray.Length);
                            }
                        }
                    }
                    connection.Close();
                }
            }
            // Иначе пустой Image
            else
            {
                photo_def_img.Source = new FileImageSource();
            }

            // Добавляем структуру раскрытия дефекта
            _defects = new ObservableCollection<Ais7InfoDefect>();
            foreach(var defect in _advancedDefects)
            {
                _defects.Add(new Ais7InfoDefect(
	                $"{defect.Param}{(!defect.DefParam.Equals("") ? $" - {defect.DefParam}" : "")}", defect.BDRG));
            }

            var index = 0;
            foreach(var defect in _defects)
            {
                grid_def_view.RowDefinitions.Add(new RowDefinition { Height = new GridLength(Application.Current.MainPage.Height / 12, GridUnitType.Absolute) });
                grid_def_view.Children.Add(GetTextLayout(defect.Info, index == 0), 0, index);
                grid_def_view.Children.Add(GetTextLayout(defect.BDRG, index == 0), 1, index);
	            var touchContentView = new ContentView
	            {
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            VerticalOptions = LayoutOptions.FillAndExpand,
		            ClassId = $"{index}",
	            };
	            var tapGestureRecognizer = new TapGestureRecognizer();
                tapGestureRecognizer.Tapped += (s, e) =>
                {
                    _selectedItem = Convert.ToInt32(touchContentView.ClassId);
                    EditInfoDefect(cIsso, nDef);
                };
                touchContentView.GestureRecognizers.Add(tapGestureRecognizer);
                grid_def_view.Children.Add(touchContentView, 0, index);
                Grid.SetColumnSpan(touchContentView, 2);
                index += 1;
            }
        }

        /// <summary>
        ///  Изменение контента в соответствии с выбранным раскрытием дефекта
        /// </summary>
        /// <param name="cIsso"></param>
        /// <param name="nDef"></param>
        void EditInfoDefect(int cIsso, int nDef)
        {
            // Изменяем высоту таблицы
            foreach (var rowDef in grid_adv_char_def.RowDefinitions)
            {
                rowDef.Height = GridLength.Star;
            }

            foreach (var rowDef in grid_rem_char_def.RowDefinitions)
            {
                rowDef.Height = GridLength.Star;
            }

            localization_label.Text = _advancedDefects[_selectedItem].L_DEF;
            advanced_info_label.Text = _advancedDefects[_selectedItem].W_DEF;
            rem_work_label.Text = _advancedDefects[_selectedItem].N_REM;
            name_rem_volume_label.Text = $"Объем ремонтной работы {_advancedDefects[_selectedItem].REM_DIMENSION}";
            //rem_volume_label.Text = advancedDefects[SelectedItem].REM_SIZE;
	        if (!_advancedDefects[_selectedItem].N_REM.Equals(""))
	        {
		        var volume =  Convert.ToDouble(_advancedDefects[_selectedItem].REM_SIZE);
		        //if (volume - Math.Truncate(volume) > 0)
		        //    rem_volume_label.Text = String.Format("{0:0.000}", volume);
		        //else
		        rem_volume_label.Text = $"{volume:0.000}";
	        }
            // Добавление фотографии
            if (_advancedDefects[_selectedItem].PhotoDef)
            {
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    var command = connection.CreateCommand();
                    command.CommandType = System.Data.CommandType.Text;
                    command.CommandTimeout = 30;
                    command.CommandText =
	                    $"select FOTO from I_FOTO_DEF where C_ISSO={cIsso} and N_DEF={nDef} and date={_advancedDefects[_selectedItem].Date}";
                    connection.Open();
                    using (var reader = command.ExecuteReader())
                    {
                        if (reader.HasRows)
                        {
                            reader.Read();
                            var photoArray = Convert.FromBase64String(reader.GetString(0));
                            photo_def_img.Source = ImageSource.FromStream(() => new MemoryStream(photoArray));
                            _newPath =
	                            $"{Environment.GetFolderPath(Environment.SpecialFolder.Personal)}/ISSO-I/{CommonStaffUtils.RandomNumber(1, 999999)}";
                            if (!Directory.Exists(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/"))
                                Directory.CreateDirectory(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/");
                            var list = Directory.GetFiles(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/", "*");
                            if(list.Length > 0)
                            {
                                foreach(var l in list)
                                {
                                    File.Delete(l);
                                }
                            }
                            Directory.GetFiles(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/", "*");

                            using (var fileStream = new FileStream(_newPath, FileMode.CreateNew, FileAccess.Write))
                            {
                                fileStream.Write(photoArray, 0, photoArray.Length);
                            }
                        }
                    }
                    connection.Close();
                }
            }
            // Иначе пустой Image
            else
            {
                photo_def_img.Source = new FileImageSource();
            }

            foreach(var view in grid_def_view.Children.Where(x => Grid.GetRow(x) == _selectedItem))
            {
                if(!(view is ContentView))
                    view.BackgroundColor = Color.FromHex("#55104e8b");
            }

            foreach(var view in grid_def_view.Children.Where(x => Grid.GetRow(x) != _selectedItem))
            {
                if (!(view is ContentView))
                    view.BackgroundColor = Color.Transparent;
            }
        }

        /// <summary>
        /// Создание TextView
        /// </summary>
        /// <param name="info"></param>
        /// <param name="isTouched"></param>
        /// <returns></returns>
        public StackLayout GetTextLayout(string info, bool isTouched)
        {
            var layout = new StackLayout
            {
                Orientation = StackOrientation.Horizontal,
                BackgroundColor = Color.White,
                Padding = new Thickness(10, 5, 10, 5)
            };
            var label = new Label
            {
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.FillAndExpand,
                FontSize = 12,
                HorizontalTextAlignment = TextAlignment.Start,
                VerticalTextAlignment = TextAlignment.Center,
                Text = info
            };
            if (isTouched)
                layout.BackgroundColor = Color.FromHex("#55104e8b");
            layout.Children.Add(label);
            return layout;
        }

        /// <summary>
        /// При нажатии на фотографию
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void TapGestureRecognizer_Tapped(object sender, EventArgs e)
        {
            //FullScreenImageContainer fullScreenImageContainer = new FullScreenImageContainer(photo_def_img.Source);
            //Navigation.PushAsync(fullScreenImageContainer);

            //string newPath = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Personal), "/Defects/1");
            //if (!Directory.Exists(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/Defects/"))
            //    Directory.CreateDirectory(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/Defects/");
            //if (!Directory.Exists(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/"))
            //    Directory.CreateDirectory(Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/");
            //if (File.Exists(newPath))
            //    File.Delete(newPath);

            //using (var fileStream = new FileStream(newPath, FileMode.CreateNew, FileAccess.Write))
            //{
            //    fileStream.Write(photo_array, 0, photo_array.Length);
            //}

            PhotoBrowser.Close();
            new PhotoBrowser
            {
                Photos = new List<Photo>
                {
                    new Photo
                    {
                        URL = $"file://{_newPath}",
                        Title = _defects[_selectedItem].Info
                    }
                },
                EnableGrid = false
            }.Show();
        }

        /// <summary>
        ///  Очистка при закрытии окна
        /// </summary>
        public override void Dispose()
        {
            PhotoBrowser.Close();
            _advancedDefects.Clear(); _advancedDefects = null;
            _defects.Clear(); _defects = null;
            BindingContext = null;
            Content = null;
        }
    }

    [SqliteFunctionAttribute(Name = "distance", Arguments = 4, FuncType = FunctionType.Scalar)]
    internal class SqliteDistance : SqliteFunction
    {
        public override object Invoke(object[] args)
        {
            const double radius = 6367;
            var lat1 = Convert.ToDouble(args[0]);
            var lng1 = Convert.ToDouble(args[1]);
            var lat2 = Convert.ToDouble(args[2]);
            var lng2 = Convert.ToDouble(args[3]);

            return radius * 2 * Math.Asin(Math.Min(1, Math.Sqrt((Math.Pow(Math.Sin((lat2 * (Math.PI / 180) - lat1 * (Math.PI / 180)) / 2.0), 2.0) + Math.Cos(lat1 * (Math.PI / 180)) * Math.Cos(lat2 * (Math.PI / 180)) * Math.Pow(Math.Sin((lng2 * (Math.PI / 180) - lng1 * (Math.PI / 180)) / 2.0), 2.0)))));
        }
    }

    #region Unused
    ///// <summary>
    ///// Поведение для того, чтобы listview был с автоподбором высоты
    ///// </summary>
    //public class AutoSizeBehavior : Behavior<ListView>
    //{
    //    ListView _ListView;
    //    ITemplatedItemsView<Cell> Cells => _ListView;

    //    protected override void OnAttachedTo(ListView bindable)
    //    {
    //        bindable.ItemAppearing += AppearanceChanged;
    //        bindable.ItemDisappearing += AppearanceChanged;
    //        _ListView = bindable;
    //    }

    //    protected override void OnDetachingFrom(ListView bindable)
    //    {
    //        bindable.ItemAppearing -= AppearanceChanged;
    //        bindable.ItemDisappearing -= AppearanceChanged;
    //        _ListView = null;
    //    }

    //    void AppearanceChanged(object sender, ItemVisibilityEventArgs e) =>
    //      UpdateHeight(e.Item);

    //    void UpdateHeight(object item)
    //    {
    //        if (_ListView.HasUnevenRows)
    //        {
    //            double height;
    //            if ((height = _ListView.HeightRequest) ==
    //                (double)VisualElement.HeightRequestProperty.DefaultValue)
    //                height = 0;

    //            height += MeasureRowHeight(item);
    //            SetHeight(height);
    //        }
    //        else if (_ListView.RowHeight == (int)ListView.RowHeightProperty.DefaultValue)
    //        {
    //            var height = MeasureRowHeight(item);
    //            _ListView.RowHeight = height;
    //            SetHeight(height);
    //        }
    //    }

    //    int MeasureRowHeight(object item)
    //    {
    //        var template = _ListView.ItemTemplate;
    //        var cell = (Cell)template.CreateContent();
    //        cell.BindingContext = item;
    //        var height = cell.RenderHeight;
    //        var mod = height % 1;
    //        if (mod > 0)
    //            height = height - mod + 1;
    //        return (int)height;
    //    }

    //    void SetHeight(double height)
    //    {
    //        //TODO if header or footer is string etc.
    //        if (_ListView.Header is VisualElement header)
    //            height += header.Height;
    //        if (_ListView.Footer is VisualElement footer)
    //            height += footer.Height;
    //        _ListView.HeightRequest = height;
    //    }
    //}
    #endregion
}
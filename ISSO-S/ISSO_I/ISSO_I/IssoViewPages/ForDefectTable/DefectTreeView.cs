using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Additional_Classes;
using ISSO_I.IssoViewPages.ForDefectTable;
using ISSO_I.PopupTypes;
using ISSO_I.Singleton;
using Mono.Data.Sqlite;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;

namespace ISSO_I.IssoViewPages
{
    public class DefectTreeView : ScrollView, IDisposable
    {
        /// <summary>
        /// Идентификатор синглтона дерева дефектов
        /// </summary>
        private DefectTreeNode DefectTreeNode { get; set; }
        /// <summary>
        /// Для отрисовки таблицы
        /// </summary>
        private readonly List<double> _columnWeights = new List<double>()
        {
            0.10727590690410238D,
            0.60300954627238568D,
            0.62876194312268607D,
            0.68733104555123647D,
            0.23782614958000556D,
            0.56328804190320581D
        };
        /// <summary>
        /// Список фильтров
        /// </summary>
        private List<DefectFilter> _defFilter = new List<DefectFilter>();
        /// <summary>
        /// Список дефектов
        /// </summary>
        private List<Ais7IssoDefect> _defectsList;
        /// <summary>
        /// Кнопка меню для фильтрации дефектов
        /// </summary>
        public ToolbarItem DefectFilterItem { get; set; }

        /// <summary>
        /// Кнопка добавления дефекта
        /// </summary>
        public ToolbarItem AddDefect { get; set; }
        /// <summary>
        /// Номер ИССО
        /// </summary>
        private int _cIsso;

        private enum RowType
        {
            Header = 10,
            Completed = 20,
            Other = 30
        }

        private bool _tapped;

	    public void InitDefectTree(int cIsso)
        {
            _cIsso = cIsso;
	        RefreshDefectsList();

            DefectFilterItem = new ToolbarItem
            {
                Command = new Command((sender) => ShowDefectFilterPage()),
                Icon = CommonStaffUtils.GetFilePath("filter_dark.png"),
                Priority = 1,
                Order = ToolbarItemOrder.Primary
            };

			// Раскомментить потом

			AddDefect = new ToolbarItem
			{
				Command = new Command((sender) => AddDefectContentPage()),
				Icon = CommonStaffUtils.GetFilePath("add.png"),
				Priority = 0,
				Order = ToolbarItemOrder.Primary
			};

			MessagingCenter.Subscribe<string>(this, "DefectAdded", delegate { RefreshDefectsList(); });
		}

        private void ShowDefectFilterPage()
        {
            var defFilterPopupPage = new DefFilterPopupPage(_defFilter);
            defFilterPopupPage.ApplyFilters += DefFilterPopupPage_ApplyFilters;
			var popupPage = new CommonPopupPage(defFilterPopupPage, DefFilterPopupPage.Header);
            Navigation.PushPopupAsync(popupPage);
        }

	    public async void RefreshDefectsList(bool needToObtainFilter = true)
	    {
		    var page = new LoadingPopupPage("Подождите, идет загрузка...", true);
		    await Navigation.PushPopupAsync(page, false);
		    await Task.Factory.StartNew(() =>
		    {
			    DefectTreeNode = BuildTree.GetDefectTree();
			    _defectsList = CreateDefectList(_cIsso);
			    if (_defectsList.Count <= 0)
			    {
				    Device.BeginInvokeOnMainThread(async () =>
				    {
					    await Navigation.PopPopupAsync();
					    Content = new Label
					    {
						    VerticalOptions = LayoutOptions.Center,
						    HorizontalOptions = LayoutOptions.Center,
						    Text = "Дефекты на данном сооружении отсутствуют."
					    };
				    });
				    return;
			    }
			    if(needToObtainFilter)
				    CreateFilterAttributes(_defectsList);
			    Device.BeginInvokeOnMainThread(async () => { DrawDefectTable(_defectsList); await Navigation.PopPopupAsync(); });
		    });
	    }

        private void AddDefectContentPage()
        {
            //AddIssoDefectContentPageList addDefectContentPage = new AddIssoDefectContentPageList(C_ISSO);
            //Navigation.PushAsync(addDefectContentPage);
            var popupPage = new FindDefectsPopupPage(_cIsso);
            popupPage.UseSearchView += PopupPage_UseSearchView;
            popupPage.UseTraditionalView += PopupPage_UseTraditionalView;
	        var commonPopupPage = new CommonPopupPage(popupPage, FindDefectsPopupPage.Header);
	        Navigation.PushPopupAsync(commonPopupPage);
        }

        /// <summary>
        /// При нажатии на дерево дефектов
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void PopupPage_UseTraditionalView(object sender, EventArgs e)
        {
            var pageList = new AddIssoDefectContentPageList(_cIsso);
            Navigation.PushAsync(pageList);
        }

        /// <summary>
        /// При нажатии на поиск дефекта
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void PopupPage_UseSearchView(object sender, EventArgs e)
        {
            
        }

        /// <summary>
        /// При применении фильтра дефектов для просмотра вызывается перерисовка таблицы
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void DefFilterPopupPage_ApplyFilters(object sender, EventArgs e)
        {
            _defFilter = (sender as List<DefectFilter>);
	        RefreshDefectsList(false);
        }

        private void DrawDefectTable(IEnumerable<Ais7IssoDefect> defectsList)
        {
            // инициализация таблицы дефектов
            var defectGrid = new Grid
            {
                HorizontalOptions = LayoutOptions.Start,
                VerticalOptions = LayoutOptions.Start,
                ColumnSpacing = 1,
                RowSpacing = 1,
                BackgroundColor = Color.Black,
                Padding = 1,
                Margin = new Thickness(0, 10, 0, 10)
            };
            // Добавляем ColumnDefinitions
            //defectGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(Application.Current.MainPage.Width / 4, GridUnitType.Absolute) });
            //defectGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(Application.Current.MainPage.Width, GridUnitType.Absolute) });
            //defectGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(Application.Current.MainPage.Width, GridUnitType.Absolute) });
            //defectGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(Application.Current.MainPage.Width / 3, GridUnitType.Absolute) });
            //defectGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(Application.Current.MainPage.Width / 5, GridUnitType.Absolute) });
            //defectGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(Application.Current.MainPage.Width / 2, GridUnitType.Absolute) });
            for(var i = 0; i < 6; i++)
                defectGrid.ColumnDefinitions.Add(new ColumnDefinition { Width = GridLength.Auto });

            // Заполняем оглавление
            defectGrid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
            defectGrid.Children.Add(GetTextLayout("№", RowType.Header, 0), 0, 0);
            defectGrid.Children.Add(GetTextLayout("Местоположение дефекта", RowType.Header, 1), 1, 0);
            defectGrid.Children.Add(GetTextLayout("Тип и описание дефекта", RowType.Header, 2), 2, 0);
            defectGrid.Children.Add(GetTextLayout("Определяющие параметры степени развития и их значения", RowType.Header, 3), 3, 0);
            defectGrid.Children.Add(GetTextLayout("Категории дефекта", RowType.Header, 4), 4, 0);
            defectGrid.Children.Add(GetTextLayout("Характеристика объема дефекта по ремонтопригодности", RowType.Header, 5), 5, 0);
            var iterator = 1;
            foreach(var defect in defectsList)
            {
                if(_defFilter.Find(filter => filter.Activated) == null)
                {
	                if (defect.IsDefCompleted) continue;
	                InsertRow(defectGrid, defect, iterator);
	                iterator++;
                }
                else
                {
                    if(_defFilter.Find(filter => filter.Description.Equals("All") && filter.Activated) != null)
                    {
                        InsertRow(defectGrid, defect, iterator);
                        iterator++;
                    }
                    else
                    {
	                    if (_defFilter.Find(filter => filter.ConstrId == defect.MainConstrId && filter.Activated) ==
	                        null) continue;
	                    InsertRow(defectGrid, defect, iterator);
	                    iterator++;
                    }
                }
            }

            // Все это запихиваем в ScrollView
            //ScrollView DefectScrollView = new ScrollView
            //{
            //    HorizontalOptions = LayoutOptions.FillAndExpand,
            //    VerticalOptions = LayoutOptions.FillAndExpand,
            //    Orientation = ScrollOrientation.Horizontal
            //};
            var defectStackLayout = new StackLayout
            {
                Padding = new Thickness(10, 0, 10, 0),
                Orientation = StackOrientation.Horizontal,
                HorizontalOptions = LayoutOptions.FillAndExpand,
                VerticalOptions = LayoutOptions.FillAndExpand
            };
            defectStackLayout.Children.Add(defectGrid);
            //DefectScrollView.Content = defectStackLayout;
            //this.BindingContext = DefectScrollView;
            Content = defectStackLayout;
            Orientation = ScrollOrientation.Both;
        }

	    private void InsertRow(Grid defectGrid, Ais7IssoDefect defect, int iterator)
        {
            defectGrid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
            defectGrid.Children.Add(GetTextLayout($"{iterator}/{defect.NDef}{(defect.HasPhoto ? " (ф)" : "")}", 
                defect.IsDefCompleted ? RowType.Completed : RowType.Other, 0), 0, iterator);
            defectGrid.Children.Add(GetTextLayout(defect.Location, defect.IsDefCompleted ? RowType.Completed : RowType.Other, 1), 1, iterator);
            defectGrid.Children.Add(GetTextLayout(defect.NDefect, defect.IsDefCompleted ? RowType.Completed : RowType.Other, 2), 2, iterator);
            defectGrid.Children.Add(GetTextLayout(defect.Params, defect.IsDefCompleted ? RowType.Completed : RowType.Other, 3), 3, iterator);
            defectGrid.Children.Add(GetTextLayout(defect.Category, defect.IsDefCompleted ? RowType.Completed : RowType.Other, 4), 4, iterator);
            defectGrid.Children.Add(GetTextLayout(defect.RemontInfo, defect.IsDefCompleted ? RowType.Completed : RowType.Other, 5), 5, iterator);


            var touchContentView = new ContentView
            {
                HorizontalOptions = LayoutOptions.FillAndExpand,
                VerticalOptions = LayoutOptions.FillAndExpand,
            };
            var tapGestureRecognizer = new TapGestureRecognizer() { NumberOfTapsRequired = 1 };
            tapGestureRecognizer.Tapped += async (s, e) => 
            {
                if (_tapped)
                    return;
                _tapped = true;
                var advancedDefectInfoPage = new AdvancedDefectInfoPage(_cIsso, defect.NDef);
                await Navigation.PushAsync(advancedDefectInfoPage);
                _tapped = false;
            };
            touchContentView.GestureRecognizers.Add(tapGestureRecognizer);
            defectGrid.Children.Add(touchContentView, 0, iterator);
            Grid.SetColumnSpan(touchContentView, 6);
        }


	    /// <summary>
	    ///  Создание текстового контрола
	    /// </summary>
	    /// <param name="info"></param>
	    /// <param name="rowType"></param>
	    /// <param name="columnIndex"></param>
	    /// <returns></returns>
	    private StackLayout GetTextLayout(string info, RowType rowType, int columnIndex)
        {
            var layout = new StackLayout
            {
                Orientation = StackOrientation.Horizontal,
                BackgroundColor = Color.White,
                //Padding = new Thickness(10, 5, 10, 5)
            };
            if(DependencyService.Get<IDeviceOrientation>().GetOrientation() == DeviceOrientations.Portrait)
                layout.WidthRequest = _columnWeights[columnIndex] * Application.Current.MainPage.Width;
            else
                layout.WidthRequest = _columnWeights[columnIndex] * Application.Current.MainPage.Height;
            var label = new Label
            {
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.FillAndExpand,
                FontSize = 12,
                HorizontalTextAlignment = TextAlignment.Start,
                VerticalTextAlignment = TextAlignment.Center,
                Text = info
            };
            switch (rowType)
            {
                case RowType.Header:
                    label.BackgroundColor = Color.Accent;
                    label.TextColor = Color.White;
                    label.Margin = new Thickness(10, 5, 10, 5);
                    layout.BackgroundColor = Color.Accent;
                    break;
                case RowType.Completed:
                    layout.BackgroundColor = Color.FromHex("#9fb8d0");
                    layout.Padding = new Thickness(10, 5, 10, 5);
                    break;
                case RowType.Other:
                    layout.Padding = new Thickness(10, 5, 10, 5);
                    break;
            }
            layout.Children.Add(label);
            return layout;
        }

        private void CreateFilterAttributes(List<Ais7IssoDefect> defects)
        {
            using(var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
				_defFilter = new List<DefectFilter>();
                var args = new StringBuilder();
                foreach (var defect in defects)
                {
                    if (!args.ToString().Contains(defect.MainConstrId.ToString()))
                        args.Append($"'{defect.MainConstrId}',");
                }
                var command = connection.CreateCommand();
                command.CommandText =
	                $"select C_GR_CONSTR, TEXT from S_GR_CONSTR where C_GR_CONSTR in ({args.ToString().Substring(0, args.ToString().Length - 1)})";
                command.CommandTimeout = 30;
                command.CommandType = System.Data.CommandType.Text;
                connection.Open();
                using(var reader = command.ExecuteReader())
                {
                    if(reader.HasRows)
                        while(reader.Read())
                            _defFilter.Add(new DefectFilter(reader.GetInt32(0), reader.GetString(1), false));
                }
                connection.Close();
                //defFilter.Add(new DefectFilter(-1, "All", false));
            }
        }

        private List<Ais7IssoDefect> CreateDefectList(int cIsso)
        {
            try
            {
                // Параметр который будем возвращать
                var defectsList = new List<Ais7IssoDefect>();
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    var command = connection.CreateCommand();
                    command.CommandText =
	                    "select i_defect.c_isso, i_defect.n_def, i_defect.c_defect, i_defect.c_gr_constr, " +
	                    "coalesce(ord, s_gr_constr.MAIN_CONSTR_ID) as ord, i_defect.n_constr, i_def_mod.date, " +
	                    "i_defect.B, i_defect.B1, i_defect.D, i_defect.D1, i_defect.R, i_defect.R1, i_defect.G, i_defect.G1, " +
	                    "i_defect.DATE as date_defect, i_defect.DATEF as date_end, i_def_mod.l_def, i_def_mod.w_def, s_defect.n_defect, n_rem, " +
	                    "sn_unit_dime, v_rem, count(i_foto_def.n_def) as fotocount, s_gr_constr.MAIN_CONSTR_ID " +
	                    "from i_defect " + "left outer join i_def_mod on  " + "i_def_mod.c_isso = i_defect.c_isso and  " +
	                    "i_def_mod.n_def = i_defect.n_def and " +
	                    "i_def_mod.date = (select max(date) from i_def_mod d2 where  " +
	                    "d2.c_isso = i_defect.c_isso and  " + "d2.n_def = i_defect.n_def) " +
	                    "left outer join s_defect on s_defect.c_defect = i_defect.c_defect " +
	                    "left outer join s_rem on s_rem.c_rem = i_def_mod.c_rem " +
	                    "left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen = s_rem.ind_unit " +
	                    "left outer join i_foto_def on i_foto_def.c_isso = i_defect.c_isso and i_foto_def.n_def = i_defect.n_def and " +
	                    "i_foto_def.date = i_def_mod.date " +
	                    "left outer join s_gr_constr on s_gr_constr.C_GR_CONSTR = i_defect.C_GR_CONSTR " +
	                    $"where i_defect.c_isso = {cIsso} " +
	                    "group by i_defect.c_isso, i_defect.n_def, i_defect.c_defect, i_defect.c_gr_constr, i_defect.n_constr, i_def_mod.date, " +
	                    "i_defect.B, i_defect.B1, i_defect.D, i_defect.D1, i_defect.R, i_defect.R1, i_defect.G, i_defect.G1, " +
	                    "i_defect.DATE, i_defect.DATEF, i_def_mod.l_def, n_rem, sn_unit_dime, v_rem, l_def, i_def_mod.w_def, n_defect, " +
	                    "s_gr_constr.c_gr_constr, ord, s_gr_constr.MAIN_CONSTR_ID order by ord, i_defect.n_constr, i_defect.n_def";
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;

                    var commandParam = connection.CreateCommand();
                    commandParam.CommandText = "select i_defect.n_def, s_defparam.c_defparam, " +
                                               "   n_defparam, category, value, sn_unit_dime, min(s_defparamvalue.c_unit_dimen) c_unit_dimen " +
                                               "from i_def_descr left outer join i_def_mod on i_def_descr.c_isso = i_def_mod.c_isso and i_def_descr.n_def = " +
                                               "       i_def_mod.n_def and i_def_descr.date=i_def_mod.date " +
                                               "     left outer join i_defect on i_def_mod.c_isso = i_defect.c_isso and i_def_mod.n_def = i_defect.n_def " +
                                               "     left outer join s_defparam on s_defparam.c_defparam = i_def_descr.c_defparam " +
                                               "     left outer join s_defparamvalue on s_defparamvalue.c_defect=i_defect.c_defect and " +
                                               "       s_defparamvalue.c_gr_constr = i_defect.c_gr_constr and " +
                                               "                                        s_defparamvalue.c_defparam = i_def_descr.c_defparam " +
                                               "     left outer join s_unit_dimension on s_defparamvalue.c_unit_dimen = s_unit_dimension.c_unit_dimen " +
                                               "where i_def_mod.date = (select max(date) from i_def_mod d2 where  " +
                                               "            d2.c_isso = i_defect.c_isso and  " +
                                               $"            d2.n_def = i_defect.n_def) and i_def_descr.c_isso = {cIsso}" +
                                               " group by i_defect.n_def, s_defparam.c_defparam, n_defparam, category, value, sn_unit_dime";
                    commandParam.CommandTimeout = 30;
                    commandParam.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    using (var datareader = command.ExecuteReader())
                    {
                        if (datareader.HasRows)
                        {
	                        var indexDef = 1;
                            while (datareader.Read())
                            {
	                            var issoDefect = new Ais7IssoDefect
	                            {
		                            Ord = indexDef,
		                            NDef = Convert.ToInt16(datareader["N_DEF"]),
		                            HasPhoto = datareader["fotocount"] != DBNull.Value &&
		                                       Convert.ToInt16(datareader["fotocount"]) > 0
	                            };

	                            var cGrConstr = Convert.ToInt32(datareader["C_GR_CONSTR"]);
                                DefectTreeNode.Path.Clear();
                                issoDefect.IsDefCompleted = datareader["date_end"] != DBNull.Value;
                                issoDefect.MainConstrId = Convert.ToInt32(datareader["MAIN_CONSTR_ID"]);
                                var cConstr = datareader["N_CONSTR"] != DBNull.Value ? Convert.ToInt16(datareader["N_CONSTR"]) : -100;
                                var constr = DefectTreeNode.GetConstrFullName(DefectTreeNode, cGrConstr, cConstr);
                                var lDef = datareader["L_DEF"] != DBNull.Value ? Convert.ToString(datareader["L_DEF"]) : "";
                                var mainC = DefectTreeNode.GetConstrMainName(DefectTreeNode, cGrConstr, cConstr);
                                //issoDefect.Location = defTree.getConstrFullName(defTree, CGrConstr, c_constr);
                                issoDefect.Location = mainC + (constr.Equals("") ? "" : ". " + constr) + (lDef.Equals("") ? "" : ". " + lDef);
                                issoDefect.NameConstr = DefectTreeNode.GetConstrMainName(DefectTreeNode, cGrConstr, cConstr);
                                var wDef = datareader["W_DEF"] != DBNull.Value ? Convert.ToString(datareader["W_DEF"]) : "";
                                issoDefect.NDefect = Convert.ToString(datareader["N_DEFECT"]) + (!wDef.Equals("") ? ". " + wDef : "");
                                var b = datareader["B"] != DBNull.Value ? Convert.ToInt16(datareader["B"]) : 0;
                                var b1 = datareader["B1"] != DBNull.Value ? Convert.ToInt16(datareader["B1"]) : 0;
                                var d = datareader["D"] != DBNull.Value ? Convert.ToInt16(datareader["D"]) : 0;
                                var d1 = datareader["D1"] != DBNull.Value ? Convert.ToInt16(datareader["D1"]) : 0;
                                var r = datareader["R"] != DBNull.Value ? Convert.ToInt16(datareader["R"]) : 0;
                                var r1 = datareader["R1"] != DBNull.Value ? Convert.ToInt16(datareader["R1"]) : 0;
                                var g = Convert.ToInt16(datareader["G"]) == 1;
                                var g1 = Convert.ToInt16(datareader["G1"]) == 1;
                                issoDefect.Category =
	                                $"Б{(b == b1 ? Convert.ToString(b) : $"{b}/{b1}")}, Д{(d == d1 ? Convert.ToString(d) : $"{d}/{d1}")}, Р{(r == r1 ? Convert.ToString(r) : $"{r}/{r1}")}{(g ? (!g1 ? ", (Г)" : ", Г") : "")}";
                                // определяемся с параметрами
                                var param = "";
                                using (var datareaderParam = commandParam.ExecuteReader())
                                {
                                    if (datareaderParam.HasRows)
                                    {
                                        while (datareaderParam.Read())
                                        {
	                                        if (!Convert.ToString(datareaderParam["N_DEF"])
		                                        .Equals(Convert.ToString(issoDefect.NDef))) continue;
	                                        var pv = new Ais7DefectParamValue(Convert.ToInt16(datareaderParam["C_DEFPARAM"]));
	                                        param +=
		                                        $"{pv.GetFullName()}{(datareaderParam["SN_UNIT_DIME"] != DBNull.Value ? $" ({Convert.ToString(datareaderParam["SN_UNIT_DIME"])})" : "")}{(datareaderParam["VALUE"] != DBNull.Value ? $" - {Convert.ToString(datareaderParam["VALUE"])}" : "")}";
                                        }
                                    }
                                }
                                issoDefect.Params = param;

                                issoDefect.RemontInfo = datareader["N_REM"] != DBNull.Value ? Convert.ToString(datareader["N_REM"]) : "";
                                if (datareader["SN_UNIT_DIME"] != DBNull.Value)
                                    issoDefect.RemontInfo =
		                                    $"{issoDefect.RemontInfo} ({Convert.ToString(datareader["SN_UNIT_DIME"])})";
                                if (datareader["V_REM"] != DBNull.Value)
                                    issoDefect.RemontInfo =
		                                    $"{issoDefect.RemontInfo} - {Convert.ToString(datareader["V_REM"])}";
                                defectsList.Add(issoDefect);

                                indexDef++;
                            }
                        }
                    }
                    connection.Close();
                }
                return defectsList;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                return null;
            }
        }

        public void Dispose()
        {

            _defectsList?.Clear(); _defectsList = null;
            _defFilter?.Clear(); _defFilter = null;
            DefectFilterItem = null;
            AddDefect = null;

            (Content as StackLayout)?.Children.Clear();
            Content = null;
			MessagingCenter.Unsubscribe<string>(this, "DefectAdded");
        }
    }
}

using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using ISSO_I.CustomRenderes;
using ISSO_I.Drivers;
using ISSO_I.IssoViewPages.ForTreeView;
using ISSO_I.PopupTypes;
using Mono.Data.Sqlite;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

// ReSharper disable once CheckNamespace
namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TableTreeView : ISwipeCallback
    {
        private readonly int _cIsso;
        private readonly string _sysName;
        private readonly int _cGrConstr;

        /// <summary>
        /// данные для отображения таблицы
        /// </summary>
        private List<List<object>> _tableRows;
        /// <summary>
        /// свойства колонок
        /// </summary>
        private List<DBHelper.ADVANCED_S_TABLES> _columnProperties;

	    /// <summary>
        /// Ширина строк, по умолчанию (горизонтально)
        /// </summary>
        private double _cellHeightVertical;
        /// <summary>
        /// Ширина строк, по умолчанию (вериткально)
        /// </summary>
        private double _cellHeightHorizontal;
        /// <summary>
        /// Текущая запись
        /// </summary>
        private int _currentPosition;
        /// <summary>
        /// переменная, отвечающая за время анимации
        /// </summary>
        private uint _millisecondsAnim = 100;
        /// <summary>
        /// Описание 
        /// </summary>
        private readonly string _description;
        /// <summary>
        /// Выбранная запись
        /// </summary>
        private DBHelper.ADVANCED_S_TABLES _selectedProperty;
        /// <summary>
        /// По выбранной колонке
        /// </summary>
        private int _selectedColumnindex;
        /// <summary>
        /// Находится ли приложение в процессе нажатия
        /// </summary>
        private bool Tapped { get; set; }
        /// <summary>
        /// Подключение к БД
        /// </summary>
        private SQLite.SQLiteConnection Connection { get; } = ConnectionClass.CreateDatabase();

        /// <summary>
        /// В процессе редактирования
        /// </summary>
        private bool InProcessOfEditing { get; set; }

        /// <summary>
        /// Длина и ширина родительского контрола
        /// </summary>
        private double _width;
        private double _height;
        /// <summary>
        /// Список таблиц, доступных только для чтения
        /// </summary>
        //static String[] read_only_tables { get; set; } = new String[] { "I_OTC", "V_RATING" };
        /// <summary>
        /// Список столбцов, доступных только для чтения
        /// </summary>
        //static String[] read_only_columns { get; set; } = new string[] { "P1_LATITUDE", "P1_LONGITUDE", "P2_LATITUDE", "P2_LONGITUDE" };

        /// <summary>
        /// Список типов, встречающихся в приложении
        /// </summary>
        //private Dictionary<Type, string> _types = new Dictionary<Type, string>
        //{
        //    {typeof(Label), "label" },
        //    {typeof(Entry), "entry" },
        //    {typeof(Editor), "editor" },
        //    {typeof(string), "string" },
        //    {typeof(float), "float" },
        //    {typeof(double), "float" },
        //    {typeof(DateTime), "datetime" },
        //    {typeof(byte[]), "byte" },
        //    {typeof(bool), "boolean" }
        //};


        public TableTreeView(int cIsso, string sysName, int cGrConstr, string description)
        {
            InitializeComponent();

            _width = Width;
            _height = Height;
            Title = $"ИССО №{cIsso}. {description}";
            // Запишем основные переменные
            _cIsso = cIsso;
            _cGrConstr = cGrConstr;
            _sysName = sysName;
            _description = description;
            //TableInfo.Text = String.Format("C_ISSO = {0}, SYS_NAME = {1}, C_GR_CONSTR = {2}", C_ISSO, SYS_NAME, C_GR_CONSTR);
            GetAllInformation();

            // Если у нас таблица с общими данными, то добавим пункт в меню для редактирования координат
            if (cGrConstr == 10)
            {
                var toolbarItem = new ToolbarItem()
                {
                    Text = "Редактировать координаты",
                    Icon = new FileImageSource { File = CommonStaffUtils.GetFilePath("find_coords.png") },
                    Order = ToolbarItemOrder.Primary,
                    Priority = 1,
                    IsDestructive = true,
                    Command = new Command(ShowGeoDialog)
                };
                ToolbarItems.Add(toolbarItem);
            }
        }

        /// <summary>
        /// Показать диалоговое окно для редактирования геокоординат
        /// </summary>
        private async void ShowGeoDialog()
        {
            var editCoordsContentPage = new EditCoordsContentPage(_cIsso);
            editCoordsContentPage.SaveChanges += GeoPage_CoordsChanged;
            await Navigation.PushModalAsync(editCoordsContentPage);

            //DisplayAlert("Информация:", "Эта функция недоступна, т.к. находится в разработке", "OK");
        }

	    /// <summary>
	    /// Получить информацию по ИССО из БД
	    /// </summary>
	    private void GetAllInformation()
        {
            // Получим количество колонок в таблице
            //var table_column_names = new List<ColumnProperty>();
            _columnProperties = Connection.Table<DBHelper.ADVANCED_S_TABLES>().Where(x => x.TABLE_NAME == _sysName).ToList();
            // Создаем экземпляр работы с БД
            using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
                // Получаем инфо по нашей таблице

                //// Получение primarykeys
                //SqliteCommand command = connection.CreateCommand();
                //command.CommandText = String.Format("PRAGMA table_info({0})", SYS_NAME);
                //command.CommandTimeout = 30;
                //command.CommandType = System.Data.CommandType.Text;
                //connection.Open();
                //using (SqliteDataReader datareader = command.ExecuteReader())
                //{
                //    if(datareader.HasRows)
                //    {
                //        //while (datareader.Read())
                //        //{
                //        //    // Получаем имя столбца
                //        //    string column_name = datareader.GetString(1);
                //        //    // Получаем его признак primarykey
                //        //    if(datareader.GetInt32(5) > 0)
                //        //    {
                //        //        columnProperties.Find(x => x.TABLE_COLUMN == column_name).IS_PRIMARY_KEY = true;
                //        //    }
                //        //    else
                //        //    {
                //        //        columnProperties.Find(x => x.TABLE_COLUMN == column_name).IS_PRIMARY_KEY = false;
                //        //    }
                //        //}
                //    }
                //}
                //connection.Close();

                // Получение информации для таблицы из БД
                _tableRows = new List<List<object>>();

                var command = connection.CreateCommand();
                var columnNames = string.Join(",", _columnProperties.Select(item => item.TABLE_COLUMN).ToArray());
                command.CommandText = $"Select {columnNames} from {_sysName} where C_ISSO={_cIsso}";
                command.CommandTimeout = 30;
                command.CommandType = System.Data.CommandType.Text;
                connection.Open();
                using (var datareader = command.ExecuteReader())
                {
                    if (datareader.HasRows)
                    {
                        while (datareader.Read())
                        {
                            var row = new List<object>();
                            for (var columnId = 0; columnId < _columnProperties.Count; columnId++)
                            {
                                row.Add(datareader.GetValue(columnId));
                            }
                            _tableRows.Add(row);
                        }
                    }
					datareader.Dispose();
                }
				command.Dispose();
                connection.Close();

	            if (_tableRows.Count > 1)
	            {
		            // ReSharper disable once ObjectCreationAsStatement
		            new SwipeListener(stack_test, this);
	            }
                else
                {
                    stack_test.IsVisible = false;
                    picker_entries.SelectedIndex = 0;
                }



                // Если в нашем случае имеет место горизантальная ориентация
                if (Application.Current.MainPage.Width > Application.Current.MainPage.Height)
                {
                    // Задаем ширину колонок и строк в соответствии с параметрами экрана
	                _cellHeightVertical = Application.Current.MainPage.Width / 9;
                    _cellHeightHorizontal = Application.Current.MainPage.Height / 7;
                    // вызываем метод построения таблицы
                    //BuildTableView(false);
                }
                else
                {
                    // Задаем ширину колонок и строк в соответствии с параметрами экрана
	                _cellHeightVertical = Application.Current.MainPage.Height / 9;
                    _cellHeightHorizontal = Application.Current.MainPage.Width / 7;
                    // вызываем метод построения таблицы
                    //BuildTableView(true);
                }
            }
            // Теперь изменяем параметр Readonly в случае если есть в списке исключений
            var resInfo = CommonStaffUtils.TableStates.Where(table => table.Key.Substring(0, table.Key.IndexOf('.')).Equals(_sysName) && table.Value == TableState.Readonly);
	        var keyValuePairs = resInfo.ToList();
	        if(keyValuePairs.Any())
            {
                foreach(var res in keyValuePairs)
                {
                    if (res.Key.Split('.')[1].Equals("*"))
                    {
                        _columnProperties.ForEach((item) => { item.READONLY = true; });
                    }
                    else
                    {
                        var columnName = res.Key.Split('.')[1];
                        _columnProperties.Find(item => item.TABLE_COLUMN.Equals(columnName)).READONLY = true;
                    }
                }
            }

	        NewBuildTableView(Width < Height);
        }


        protected override void OnSizeAllocated(double width, double height)
        {
            var oldWidth = _width;
            const double sizenotallocated = 0;

            base.OnSizeAllocated(width, height);
            if (Equals(_width, width) && Equals(_height, height)) return;

            _width = width;
            _height = height;

            // ignore if the previous height was size unallocated
            if (Equals(oldWidth, sizenotallocated)) return;

            // Has the device been rotated ?
            if (!Equals(width, oldWidth))
            {
	            RotateGrid(!(width > height));

	            // Добавляем информацию в Picker
                var entriesItems = new List<string>();
                if (width < height)
                {
                    for (var i = 1; i <= _tableRows.Count; i++)
                    {
                        entriesItems.Add($"Запись №{i}");
                    }
                }
                else
                {
                    for (var i = 1; i < _tableRows.Count; i++)
                    {
                        entriesItems.Add($"Запись №{i},{i + 1}");
                    }
                    if (_currentPosition == _tableRows.Count - 1 && _tableRows.Count > 1)
                        _currentPosition--;
                }
                picker_entries.ItemsSource = entriesItems;
                picker_entries.SelectedIndex = _currentPosition;

            }
        }

        private void RotateGrid(bool isVertical)
        {
            foreach (var child in Root.Children)
            {
                if (child is Grid)
                {
                    var grid = child as Grid;
                    if (isVertical)
                    {
                        grid.ColumnDefinitions[0].Width = new GridLength(Width / 2, GridUnitType.Absolute);
                        grid.ColumnDefinitions[1].Width = new GridLength(Width / 2, GridUnitType.Absolute);
                        if (_tableRows.Count > 1)
                            grid.ColumnDefinitions[2].Width = new GridLength(Width / 2, GridUnitType.Absolute);
                        //foreach (RowDefinition row in grid.RowDefinitions)
                        //{
                        //    // Пересчитываем высоту строк в Grid
                        //    var lblname_height = DependencyService.Get<ITextMeter>().MeasureTextSize(((Label)view.Children[1]).Text, this.Width / 2, ((Label)view.Children[1]).FontSize);
                        //    var lbl1_height = DependencyService.Get<ITextMeter>().MeasureTextSize(((Label)view.Children[1]).Text, this.Width / 2, ((Label)view.Children[1]).FontSize);
                        //    if(tableRows.Count > 1)
                        //    {
                        //        var lbl2_height = DependencyService.Get<ITextMeter>().MeasureTextSize(((Label)view.Children[1]).Text, this.Width / 2, ((Label)view.Children[1]).FontSize);
                        //    }
                        //    row.Height = GridLength.Star;
                        //}
                        for (var i = 0; i < grid.RowDefinitions.Count; i++)
                        {
                            var lblnameHeight = DependencyService.Get<ITextMeter>().MeasureTextSize(
                                ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text, 
                                Width / 2,
                                ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.FontSize ?? Font.Default.FontSize);
                            var lbl1Height = lblnameHeight;
                            if((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children.ToList()[0] is Label)
                                lbl1Height = DependencyService.Get<ITextMeter>().MeasureTextSize(
                                    ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text,
                                    Width / 2,
                                    ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.FontSize ?? Font.Default.FontSize);
                            // Если больше 1 записи, то сравниваем со второй соседней записью и записываем в одну переменную
                            //if(tableRows.Count > 1)
                            //{
                            //    var lbl2_height = DependencyService.Get<ITextMeter>().MeasureTextSize(
                            //    ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid).Children.ToList()[0] as Label).Text,
                            //    this.Width / 2,
                            //    ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid).Children.ToList()[0] as Label).FontSize);
                            //    if (lbl1_height < lbl2_height)
                            //        lbl1_height = lbl2_height;
                            //}
                            grid.RowDefinitions[i].Height = Math.Max(lblnameHeight, lbl1Height) + 25;
                        }
                    }
                    else
                    {

                        if (_tableRows.Count > 1)
                        {
                            grid.ColumnDefinitions[0].Width = new GridLength(Width / 3, GridUnitType.Absolute);
                            grid.ColumnDefinitions[1].Width = new GridLength(Width / 3, GridUnitType.Absolute);
                            grid.ColumnDefinitions[2].Width = new GridLength(Width / 3, GridUnitType.Absolute);
                        }
                        else
                        {
                            grid.ColumnDefinitions[0].Width = new GridLength(Width / 2, GridUnitType.Absolute);
                            grid.ColumnDefinitions[1].Width = new GridLength(Width / 2, GridUnitType.Absolute);
                        }
                        //foreach (RowDefinition row in grid.RowDefinitions)
                        //{
                        //    //row.Height = CellHeightHorizontal;
                        //    row.Height = GridLength.Star;
                        //}
                        for (var i = 0; i < grid.RowDefinitions.Count; i++)
                        {
                            var lblnameHeight = DependencyService.Get<ITextMeter>().MeasureTextSize(
                                ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text,
                                Width / 2,
                                ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.FontSize ?? Font.Default.FontSize);
                            var lbl1Height = lblnameHeight;
                            if ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children.ToList()[0] is Label)
                                lbl1Height = DependencyService.Get<ITextMeter>().MeasureTextSize(
                                    ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text,
                                    Width / 2,
                                    ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.FontSize ?? Font.Default.FontSize);
                            // Если больше 1 записи, то сравниваем со второй соседней записью и записываем в одну переменную
                            if (_tableRows.Count > 1)
                            {
                                var lbl2Height = lbl1Height;
                                if ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children.ToList()[0] is Label)
                                    lbl2Height = DependencyService.Get<ITextMeter>().MeasureTextSize(
                                        ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text,
                                        Width / 2,
                                        ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.FontSize ?? Font.Default.FontSize);
                                if (lbl1Height < lbl2Height)
                                    lbl1Height = lbl2Height;
                            }
                            grid.RowDefinitions[i].Height = Math.Max(lblnameHeight, lbl1Height) + 25;
                        }
                    }
                }
            }
        }

        private void EditItem_Tapped(object sender, EventArgs e)
        {
            InProcessOfEditing = !InProcessOfEditing;
            EditTableItem.Icon = new FileImageSource() { File = CommonStaffUtils.GetFilePath(InProcessOfEditing ? "stop_edit_icon.png" : "edit_icon.png") };
            EditTableItem.Text = InProcessOfEditing ? "Остановить редактирование" : "Начать редактирование";
            //(sender as ToolbarItem).Text = inProcessOfEditing ? "Остановить редактирование" : "Начать редактирование";
            SetItemsEditable(InProcessOfEditing);
        }

        private void ImageArrowLeft_Tapped(object sender, EventArgs e)
        {
            if (((View) sender).IsVisible && !((ArrowLeft.AnimationIsRunning("ScaleTo") || ArrowRight.AnimationIsRunning("ScaleTo"))))
            {
                OnRightSwipe();
            }
        }

        private void ImageArrowRight_Tapped(object sender, EventArgs e)
        {
            if (((View) sender).IsVisible && !((ArrowLeft.AnimationIsRunning("ScaleTo") || ArrowRight.AnimationIsRunning("ScaleTo"))))
            {
                OnLeftSwipe();
            }
        }

        /// <summary>
        /// Изменение редактируемых элементов на видимые/невидимые
        /// </summary>
        /// <param name="isEditable">Параметр, отвечающий за видимость кнопок редактирования</param>
        private void SetItemsEditable(bool isEditable)
        {
            foreach (var child in Root.Children)
            {
                if (child is Grid)
                {
                    var grid = child as Grid;
                    for (var i = 0; i < grid.RowDefinitions.Count; i++)
                    {
                        var definiton = ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text;
                        var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
                        var view = (grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid);
                        ChangeVisibilityOfContent(view, isEditable, columnProperty);
                        if (Width > Height && _tableRows.Count > 1)
                        {
                            view = (grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid);
                            ChangeVisibilityOfContent(view, isEditable, columnProperty);
                        }
                    }
                }
            }
        }

        /// <summary>
        /// Изменить видимость кнопки редактирования
        /// </summary>
        private void ChangeVisibilityOfContent(Grid parent, bool isEditable, DBHelper.ADVANCED_S_TABLES columnProperty)
        {
            foreach(var view in parent.Children)
            {
                if (view is Button)
                {
                    //(view as Button).IsVisible = true;
                    (view as Button).IsEnabled = isEditable /*&& !read_only_tables.Contains(SYS_NAME)*/;
                    (view as Button).IsVisible = isEditable;
                }
                else if (view is Switch)
                {
	                (view as Switch).IsEnabled = !columnProperty.READONLY && isEditable;
                }
                else if (view is TextLikeDatePicker)
                {
                    (view as TextLikeDatePicker).IsEnabled = isEditable /*&& !read_only_tables.Contains(SYS_NAME)*/;
                }
            }
        }


        /// <summary>
        /// Изменяем инфу в соответствии с выбранной записью
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Picker_entries_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (!(sender is Picker picker))
            {
                return;
            }
            if (picker.SelectedIndex != -1)
                _currentPosition = picker.SelectedIndex;

            foreach (var child in Root.Children)
            {
                if (child is Grid)
                {
                    var grid = child as Grid;
                    for (var i = 0; i < grid.RowDefinitions.Count; i++)
                    {
                        var definiton = ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text;
                        var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
                        var view = (grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)/*.Children.ToList()[0]*/;
                        ChangeInfo(view, _columnProperties.IndexOf(columnProperty), 1, columnProperty);
                        if (Width > Height && _tableRows.Count > 1)
                            ChangeInfo((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid)/*.Children.ToList()[0]*/,
                            _columnProperties.IndexOf(columnProperty), 2, columnProperty);
                    }
                }
            }

            // рассматриваем варианты, когда перевернут дисплей
            // В случае с горизонтальной ориентацией
            if (Width > Height)
            {
                // В случае если у нас всего 2 поля для просмотра, то вырубаем верхний контрол со стрелками
                if (_tableRows.Count == 2)
                {
                    stack_test.IsVisible = false;
                }
                if (_currentPosition == 0)
                {
                    ArrowLeft.FadeTo(0, 1);
                    ArrowRight.FadeTo(1, 1);
                }
                else if (_currentPosition == _tableRows.Count - 2)
                {
                    ArrowLeft.FadeTo(1, 1);
                    ArrowRight.FadeTo(0, 1);
                }
                else
                {
                    ArrowLeft.FadeTo(1, 1);
                    ArrowRight.FadeTo(1, 1);
                }
            }
            // В случае с вертикальной ориентацией
            else
            {
                // Делаем видимым контрол со стрелками
                if (_tableRows.Count > 1)
                    stack_test.IsVisible = true;

                if (_currentPosition == 0)
                {
                    ArrowLeft.FadeTo(0, 1);
                    ArrowRight.FadeTo(1, 1);
                }
                else if (_currentPosition == _tableRows.Count - 1)
                {
                    ArrowLeft.FadeTo(1, 1);
                    ArrowRight.FadeTo(0, 1);
                }
                else
                {
                    ArrowLeft.FadeTo(1, 1);
                    ArrowRight.FadeTo(1, 1);
                }
            }
        }

        /// <summary>
        /// Метод обработки swipe влево
        /// </summary>
        public void OnLeftSwipe()
        {
            if (Width > Height)
            {
                if (_currentPosition < _tableRows.Count - 2)
                {
                    _currentPosition++;
                    DoArrowAnimation(ArrowRight);
                    DoAnimation(picker_entries, SwipeDirections.LeftSwipe, -1, -1, null);

	                foreach (var child in Root.Children)
                    {
	                    if (!(child is Grid)) continue;
	                    var grid = child as Grid;
	                    for (var i = 0; i < grid.RowDefinitions.Count; i++)
	                    {
		                    var definiton = ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text;
		                    var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
		                    DoAnimation((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)/*.Children.ToList()[0]*/, SwipeDirections.LeftSwipe,
			                    _columnProperties.IndexOf(columnProperty), 1, columnProperty);
		                    if (Width > Height)
			                    DoAnimation((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid)/*.Children.ToList()[0]*/, SwipeDirections.LeftSwipe,
				                    _columnProperties.IndexOf(columnProperty), 2, columnProperty);
	                    }
                    }
                }
            }
            else
            if (_currentPosition < _tableRows.Count - 1)
            {
                _currentPosition++;
                DoArrowAnimation(ArrowRight);
                DoAnimation(picker_entries, SwipeDirections.LeftSwipe, -1, -1, null);

	            foreach (var child in Root.Children)
                {
	                if (!(child is Grid)) continue;
	                var grid = child as Grid;
	                for (var i = 0; i < grid.RowDefinitions.Count; i++)
	                {
		                var definiton = ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text;
		                var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
		                DoAnimation((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)/*.Children.ToList()[0]*/, SwipeDirections.LeftSwipe,
			                _columnProperties.IndexOf(columnProperty), 1, columnProperty);
		                if (Width > Height)
			                DoAnimation((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid)/*.Children.ToList()[0]*/, SwipeDirections.LeftSwipe,
				                _columnProperties.IndexOf(columnProperty), 2, columnProperty);
	                }
                }
            }
        }

        /// <summary>
        ///  Метод обработки swipe вправо
        /// </summary>
        public void OnRightSwipe()
        {
            if (_currentPosition > 0)
            {
                _currentPosition--;
                DoArrowAnimation(ArrowLeft);
                DoAnimation(picker_entries, SwipeDirections.RightSwipe, -1, -1, null);

	            foreach (var child in Root.Children)
                {
	                if (!(child is Grid)) continue;
	                var grid = child as Grid;
	                for (var i = 0; i < grid.RowDefinitions.Count; i++)
	                {
		                var definiton = ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text;
		                var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
		                DoAnimation((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)/*.Children.ToList()[0]*/, SwipeDirections.RightSwipe,
			                _columnProperties.IndexOf(columnProperty), 1, columnProperty);
		                if (Width > Height)
			                DoAnimation((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 2).ToList()[0] as Grid)/*.Children.ToList()[0]*/, SwipeDirections.RightSwipe,
				                _columnProperties.IndexOf(columnProperty), 2, columnProperty);
	                }
                }
            }
        }
        /// <summary>
        /// Анимация
        /// </summary>
        /// <param name="view"></param>
        private async void DoArrowAnimation(View view)
        {
            await view.ScaleTo(1.2, 50);
            await view.ScaleTo(1, 50);
        }

        private async void DoAnimation(View view, SwipeDirections direction, int indexRow, int columnindex, DBHelper.ADVANCED_S_TABLES columnProperty)
        {
            View subview = null;
            if (indexRow != -1)
            {
                foreach(var subView in ((Grid) view).Children)
                {
	                if (!(subView is Label) && !(subView is Switch)) continue;
	                subview = subView;
	                break;
                }
            }
            else
                subview = view;

            switch (direction)
            {
                case SwipeDirections.LeftSwipe:
                    // двигаем вьюху влево
                    await Task.WhenAny
                        (
                        subview.TranslateTo(-100, 0, _millisecondsAnim),
                        subview.FadeTo(0, _millisecondsAnim)
                        );
                    // затем переносим её вправо и двигаем на исходное место
                    await subview.TranslateTo(100, 0, 0);
                    if (indexRow != -1)
                        ChangeInfo(view, indexRow, columnindex, columnProperty);
                    else
                    {
                        //Label_number.Text = String.Format("Запись №{0}", current_position + 1);
                        picker_entries.SelectedIndex = _currentPosition;
                    }
                    await Task.WhenAny
                        (
                        subview.TranslateTo(0, 0, _millisecondsAnim),
                        subview.FadeTo(1, _millisecondsAnim)
                        );
                    break;
                case SwipeDirections.RightSwipe:
                    // двигаем вьюху вправо
                    await Task.WhenAny
                        (
                        subview.TranslateTo(100, 0, 100),
                        subview.FadeTo(0, 100)
                        );
                    // затем переносим её влево и двигаем на исходное место
                    await subview.TranslateTo(-100, 0, 0);

                    if (indexRow != -1)
                        ChangeInfo(view, indexRow, columnindex, columnProperty);
                    else
                    {
                        //Label_number.Text = String.Format("Запись №{0}", current_position + 1);
                        picker_entries.SelectedIndex = _currentPosition;
                    }

                    await Task.WhenAny
                        (
                        subview.TranslateTo(0, 0, 100),
                        subview.FadeTo(1, 100)
                        );
                    break;
	            default:
		            throw new ArgumentOutOfRangeException(nameof(direction), direction, null);
            }
        }

        private void ChangeInfo(View view, int indexRow, int columnIndex, DBHelper.ADVANCED_S_TABLES columnProperty)
        {
            view.ClassId = $"{indexRow},{_currentPosition + columnIndex - 1}";
            var subview = ((Grid) view).Children[0];
            // Проверка на то, имеется ли ссылка на справочную таблицу
            if (columnProperty.S_TABLE != null)
            {
                // если имеется, то подставляем справочное значение
                // проверка на null
                if (_tableRows[(_currentPosition + columnIndex - 1)][indexRow] != DBNull.Value)
                {
                    var sTableResult = Connection.Query<DBHelper.S_INFO_TABLES>(
	                    $"select * from S_INFO_TABLES where TABLE_NAME='{columnProperty.S_TABLE}' and ID={Convert.ToInt32(_tableRows[(_currentPosition + columnIndex - 1)][indexRow])}").ToList();
                    SetTextInfo(subview, sTableResult[0].VALUE, columnProperty.TABLE_COLUMN);
                }
                else
                {
	                GetTextLayout("", columnProperty.TABLE_COLUMN, false,
		                !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME))*/);
	                SetTextInfo(subview, "", columnProperty.TABLE_COLUMN);
                }
            }
            else
                // Задаем новое значение в соответствии с типом контрола
                switch (columnProperty.COLUMNTYPE)
                {
                    case "text":
                    case "varchar":
                        SetTextInfo(subview, _tableRows[(_currentPosition + columnIndex - 1)][indexRow] != DBNull.Value ?
                            Convert.ToString(_tableRows[(_currentPosition + columnIndex - 1)][indexRow]) : "", columnProperty.TABLE_COLUMN);
                        break;
                    case "int":
                    case "smallint":
                        SetNumericInfo(subview, _tableRows[(_currentPosition + columnIndex - 1)][indexRow] != DBNull.Value ?
                            Convert.ToInt32(_tableRows[(_currentPosition + columnIndex - 1)][indexRow]) : int.MinValue, columnProperty.TABLE_COLUMN);
                        break;
                    case "float":
                    case "double":
                    case "REAL":
                        SetDoubleInfo(subview, _tableRows[(_currentPosition + columnIndex - 1)][indexRow] != DBNull.Value ?
                            Convert.ToDouble(_tableRows[(_currentPosition + columnIndex - 1)][indexRow])
                            : double.MinValue, columnProperty.FORMAT, columnProperty.TABLE_COLUMN);
                        break;
                    case "long":
                    case "date":
                    case "datetime":
                    case "numeric":
                        SetDateInfo(view, _tableRows[(_currentPosition + columnIndex - 1)][indexRow] != DBNull.Value ?
                            Convert.ToInt64(_tableRows[(_currentPosition + columnIndex - 1)][indexRow]) : long.MinValue, columnProperty.TABLE_COLUMN);
                        break;
                    case "bit":
                    case "bool":
                        SetSwitchInfo(subview, _tableRows[(_currentPosition + columnIndex - 1)][indexRow] != DBNull.Value && Convert.ToInt16(_tableRows[(_currentPosition + columnIndex - 1)][indexRow]) == 1);
                        break;
                    default:
#pragma warning disable 219
                        var err = "ERROR! UNUSED TYPE";
#pragma warning restore 219
                        break;
                }
        }


        private void NewBuildTableView(bool isVertical)
        {
            // Переменная для добавления в таблицу
            var index = 0;
            var previousCategory = "";
            // Очередная таблица
            Grid tableGridLayout = null;
            // номер записи для отображения, ставим по умолчанию 0
            //current_position = 0;
            //Для каждого свойства колонки будем идти и создавать новые строки, пока не дойдем до новой категории группировки
            for (var i = 0; i < _columnProperties.Count; i++)
            {
                // Берем очередное свойство колонки
                var columnProperty = _columnProperties[i];
                // Если отображается в таблице
                if (columnProperty.VISIBLE)
                {
                    // Проверка смены категории
                    if (previousCategory != columnProperty.CATEGORY)
                    {
                        // заново строим таблицу
                        index = 0;
                        // меняем категорию
                        previousCategory = columnProperty.CATEGORY;
                        // если у нас уже была до этого таблица, то добавляем её на страницу и создаем новую + наименование группы
                        if (tableGridLayout != null)
                        {
                            // Добавление
                            Root.Children.Add(tableGridLayout);
                            // Группа
	                        var category = new Label
	                        {
		                        HorizontalTextAlignment = TextAlignment.Center,
		                        VerticalTextAlignment = TextAlignment.Center,
		                        HorizontalOptions = LayoutOptions.Fill,
		                        VerticalOptions = LayoutOptions.Fill,
		                        Text = previousCategory ?? _description,
		                        BackgroundColor = Color.Accent,
		                        TextColor = Color.White,
		                        HeightRequest = isVertical ? _cellHeightVertical : _cellHeightHorizontal,
		                        Margin = new Thickness(5, 0, 5, 0)
	                        };
	                        Root.Children.Add(category);
                            // Новая таблица
                            tableGridLayout = new Grid
                            {
                                HorizontalOptions = LayoutOptions.Fill,
                                VerticalOptions = LayoutOptions.Fill,
                                ColumnSpacing = 1,
                                RowSpacing = 1,
                                BackgroundColor = Color.Black,
                                Padding = 1
                            };
                            // Добавляем 2 колонки равные по ширине
                            var columnDef = new ColumnDefinition { Width = new GridLength(1, GridUnitType.Star) };
                            tableGridLayout.ColumnDefinitions.Add(columnDef);
                            tableGridLayout.ColumnDefinitions.Add(columnDef);

                            // Если у нас больше одной записи и горизонтальная ориентация, то добавляем ещё одну колонку
                            if (_tableRows.Count > 1)
                            {
                                tableGridLayout.ColumnDefinitions.Add(columnDef);
                            }
                        }
                        // Если же это первая таблица, то создаем с нуля
                        else
                        {
                            // Группа
	                        var category = new Label
	                        {
		                        HorizontalTextAlignment = TextAlignment.Center,
		                        VerticalTextAlignment = TextAlignment.Center,
		                        HorizontalOptions = LayoutOptions.Fill,
		                        VerticalOptions = LayoutOptions.Fill,
		                        Text = previousCategory ?? _description,
		                        BackgroundColor = Color.Accent,
		                        TextColor = Color.White,
		                        HeightRequest = isVertical ? _cellHeightVertical : _cellHeightHorizontal,
		                        Margin = new Thickness(5, 0, 5, 0)
	                        };
	                        Root.Children.Add(category);
                            // Новая таблица
                            tableGridLayout = new Grid
                            {
                                HorizontalOptions = LayoutOptions.Fill,
                                VerticalOptions = LayoutOptions.Fill,
                                ColumnSpacing = 1,
                                RowSpacing = 1,
                                BackgroundColor = Color.Black,
                                Padding = 1
                            };
                            // Добавляем 2 колонки равные по ширине
                            var columnDef = new ColumnDefinition { Width = new GridLength(1, GridUnitType.Star) };
                            tableGridLayout.ColumnDefinitions.Add(columnDef);
                            tableGridLayout.ColumnDefinitions.Add(columnDef);

                            // Если у нас больше одной записи и горизонтальная ориентация, то добавляем ещё одну колонку
                            if (_tableRows.Count > 1)
                            {
                                tableGridLayout.ColumnDefinitions.Add(columnDef);
                                //if (IsVertical)
                                //    tableGridLayout.ColumnDefinitions[2].Width = new GridLength(0);
                            }
                        }
                    }
                    // Добавляем 2 колонки равные по ширине
                    tableGridLayout.RowDefinitions.Add(new RowDefinition { Height = /*IsVertical ? CellHeightVertical : CellHeightHorizontal*/ GridLength.Star });
                    var categoryRow = GetTextLayout(columnProperty.DESCRIPTION, columnProperty.TABLE_COLUMN, true,
                        !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                    tableGridLayout.Children.Add(categoryRow, 0, index);

                    // По ориентации строим количество колонок
                    var countOfVisibleColumns = _tableRows.Count > 1 ? 2 : 1;

                    // Инициализация нажатия на вьюху, если она не READONLY
                    var tapGestureRecognizer = new TapGestureRecognizer();
                    tapGestureRecognizer.Tapped += (s, e) => {
                        // получим свойства для ячейки, чтобы понять, какое всплыващее окно вызвать
                        var property = _columnProperties[Convert.ToInt32(((View) s).ClassId.Split(',')[0])];
                        GetPopupWindow(property, (View) s, Convert.ToInt32(((View) s)?.ClassId.Split(',')[1]));
                    };

                    for (var columnindex = 1; columnindex <= countOfVisibleColumns; columnindex++)
                    {
                        // Проверка на то, имеется ли ссылка на справочную таблицу
                        if (columnProperty.S_TABLE != null)
                        {
                            // если имеется, то подставляем справочное значение
                            // проверка на null
                            if (_tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value)
                            {
                                var sql =
	                                $"select * from S_INFO_TABLES where TABLE_NAME='{columnProperty.S_TABLE}' and ID={_tableRows[(_currentPosition + columnindex - 1)][i]}";
                                Connection.ExecuteScalar<int>("Select COUNT(*) from S_INFO_TABLES");
                                var sTableResult = Connection.Query<DBHelper.S_INFO_TABLES>(sql)?.ToList();
                                var textview = GetTextLayout(sTableResult[0].VALUE, columnProperty.TABLE_COLUMN, false,
                                    !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                textview.ClassId = $"{i},{_currentPosition + columnindex - 1}";
                                //if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY && !read_only_tables.Contains(columnProperty.TABLE_NAME))
                                //{
                                //    //textview.GestureRecognizers.Add(tapGestureRecognizer);
                                //    StackLayout stackLayout = new StackLayout { Orientation = StackOrientation.Horizontal };
                                //    Button btnedit = new Button
                                //    {
                                //        VerticalOptions = LayoutOptions.Center,
                                //        HorizontalOptions = LayoutOptions.Center,
                                //        Text = "...",
                                //    };
                                //    btnedit.Command = new Command<object>(view =>
                                //    {
                                //        var property = columnProperties[Convert.ToInt32((view as View).ClassId.Split(',')[0])];
                                //        var edited_view = ((view as View).Parent as StackLayout).Children[0] as 
                                //        GetPopupWindow(property, view as View, Convert.ToInt32((view as View).ClassId.Split(',')[1]));
                                //    });
                                //    stackLayout.Children.Add(textview);
                                //    stackLayout.Children.Add(btnedit);
                                //    //stackLayout.Children.Add();
                                //}
                                //else
                                tableGridLayout.Children.Add(textview, columnindex, index);
                                //tableGridLayout.RowDefinitions[tableGridLayout.RowDefinitions.Count - 1].Height = textview.Height;
                            }
                            else
                            {
                                var textview = GetTextLayout("", columnProperty.TABLE_COLUMN, false,
                                    !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                tableGridLayout.Children.Add(textview, columnindex, index);
                            }
                        }
                        else
                        {
                            switch (columnProperty.COLUMNTYPE)
                            {
                                case "text":
                                case "varchar":
                                    var textview = GetTextLayout(_tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value ?
                                        Convert.ToString(_tableRows[(_currentPosition + columnindex - 1)][i]) : "", columnProperty.TABLE_COLUMN, false,
                                        !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                    textview.ClassId = $"{i},{_currentPosition + columnindex - 1}";
                                    //if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY && !read_only_tables.Contains(columnProperty.TABLE_NAME))
                                    //    textview.GestureRecognizers.Add(tapGestureRecognizer);
                                    tableGridLayout.Children.Add(textview, columnindex, index);
                                    break;
                                case "int":
                                    var numview32 = GetNumericLayout(_tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value ?
                                        Convert.ToInt32(_tableRows[(_currentPosition + columnindex - 1)][i]) : int.MinValue, columnProperty.TABLE_COLUMN,
                                        !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                    numview32.ClassId = $"{i},{_currentPosition + columnindex - 1}";
                                    //if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY && !read_only_tables.Contains(columnProperty.TABLE_NAME))
                                    //    numview32.GestureRecognizers.Add(tapGestureRecognizer);
                                    tableGridLayout.Children.Add(numview32, columnindex, index);
                                    break;
                                case "smallint":
                                    var numview16 = GetNumericLayout(_tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value ?
                                        Convert.ToInt16(_tableRows[(_currentPosition + columnindex - 1)][i]) : short.MinValue, columnProperty.TABLE_COLUMN,
                                        !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                    numview16.ClassId = $"{i},{_currentPosition + columnindex - 1}";
                                    //if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY && !read_only_tables.Contains(columnProperty.TABLE_NAME))
                                    //    numview16.GestureRecognizers.Add(tapGestureRecognizer);
                                    tableGridLayout.Children.Add(numview16, columnindex, index);
                                    break;
                                case "bigint":
                                    var numview64 = GetNumericLayout(_tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value ?
                                        Convert.ToInt64(_tableRows[(_currentPosition + columnindex - 1)][i]) : long.MinValue, columnProperty.TABLE_COLUMN,
                                        !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                    numview64.ClassId = $"{i},{_currentPosition + columnindex - 1}";
                                    //if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY && !read_only_tables.Contains(columnProperty.TABLE_NAME))
                                    //    numview64.GestureRecognizers.Add(tapGestureRecognizer);
                                    tableGridLayout.Children.Add(numview64, columnindex, index);
                                    break;
                                case "float":
                                case "double":
                                case "REAL":
                                    var doubleview = GetDoubleLayout(_tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value ?
                                        Convert.ToDouble(_tableRows[(_currentPosition + columnindex - 1)][i]) : double.MinValue, columnProperty.FORMAT,
                                        columnProperty.TABLE_COLUMN, !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                    doubleview.ClassId = $"{i},{_currentPosition + columnindex - 1}";
                                    //if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY && !read_only_tables.Contains(columnProperty.TABLE_NAME))
                                    //{
                                        
                                    //}
                                    tableGridLayout.Children.Add(doubleview, columnindex, index);
                                    break;
                                case "long":
                                case "date":
                                case "datetime":
                                case "numeric":
                                    var dateview = GetDateLayout(_tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value ?
                                        Convert.ToInt64(_tableRows[(_currentPosition + columnindex - 1)][i]) : long.MinValue, columnProperty.TABLE_COLUMN,
                                        !columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/);
                                    (dateview.Children[0] as DatePicker).ClassId =
	                                    $"{i},{_currentPosition + columnindex - 1}";
                                    if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/)
                                    //    //dateview.GestureRecognizers.Add(tapGestureRecognizer);
                                        (dateview.Children[0] as DatePicker).DateSelected += TableTreeView_DateSelected;
                                    tableGridLayout.Children.Add(dateview, columnindex, index);
                                    break;
                                case "bit":
                                case "bool":
                                    var switchview = GetSwitchLayout(columnProperty.READONLY, _tableRows[(_currentPosition + columnindex - 1)][i] != DBNull.Value && Convert.ToInt16(_tableRows[(_currentPosition + columnindex - 1)][i]) == 1);
                                    (switchview.Children[0] as Switch).ClassId =
	                                    $"{i},{_currentPosition + columnindex - 1}";
                                    //if (!columnProperty.READONLY && !columnProperty.IS_PRIMARY_KEY /*&& !read_only_tables.Contains(columnProperty.TABLE_NAME)*/)
                                    //    (switchview.Children[0] as Switch).Toggled += (s, e) => 
                                    //    {
                                    //        selected_columnindex = Convert.ToInt32((s as Switch).ClassId.Split(',')[1]);
                                    //        TableTreeView_Toggled(s, e);
                                    //    };
                                    tableGridLayout.Children.Add(switchview, columnindex, index);
                                    break;
                                default:
#pragma warning disable 219
                                    var err = "ERROR! UNUSED TYPE";
#pragma warning restore 219
                                    break;
                            }
                        }
                    }
                    index++;
                }
            }
            Root.Children.Add(tableGridLayout);

            // Добавляем информацию в Picker
            var entriesItems = new List<string>();
            if (isVertical)
            {
                for (var i = 1; i <= _tableRows.Count; i++)
                {
                    entriesItems.Add($"Запись №{i}");
                }
            }
            else
            {
                for (var i = 1; i < _tableRows.Count; i++)
                {
                    entriesItems.Add($"Запись №{i},{i + 1}");
                }
            }
            picker_entries.ItemsSource = entriesItems;
            picker_entries.SelectedIndex = _currentPosition;
        }

        private void TableTreeView_DateSelected(object sender, DateChangedEventArgs e)
        {
            // Переключенный switcher - столбец
            var selectedColumn = Convert.ToInt32(((View) sender).ClassId.Split(',')[0]);
            // Переключенный switcher - строка
	        //var int32 = Convert.ToInt32(((View) sender).ClassId.Split(',')[1]);

	        // Изменяем значение даты в Label
            foreach(var view in ((Grid) ((View) sender).Parent).Children)
            {
	            if (!(view is Label)) continue;
	            (view as Label).Text = ((DatePicker) sender).Date.ToString("dd.MM.yyyy");
	            break;
            }

            // Получаем дату
            var dateInMillis = ((DatePicker) sender).Date.ToLocalTime().Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Local)).TotalMilliseconds;
            // переменная для построения запроса where
            var sqlUpdateWhere = new StringBuilder("where ");
            // Значение, которое будем вставлять в запрос
            var insertedValue = "";
            var i = 0;
            // Добавляем обязательное значение C_ISSO
            sqlUpdateWhere.Append($"C_ISSO = {_cIsso} and ");
            // Проходимся по каждой колонке
            foreach (var property in _columnProperties)
            {
                // Если это не та колонка, которая редактировалась, то заносим её в запрос where 
                if (i != selectedColumn)
                {
                    if (property.IS_PRIMARY_KEY)
                        // Смотрим, какой тип данных у нас
                        switch (property.COLUMNTYPE)
                        {
                            case "text":
                            case "varchar":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Текст вставляем с '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                            case "int":
                            case "smallint":
                            case "bigint":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Целые числа вставляем в запрос без '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i]} and ");
                                }
                                break;
                            case "float":
                            case "double":
                            case "REAL":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Заменяем запятые на точки для получения правильного запроса
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i].ToString().Replace(",", ".")} and ");
                                }
                                break;
                            default:
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Иначе вставляем как текст
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                        }
                }
                else
                {
                    // Сохраняем индекс измененной колонки
                    selectedColumn = i;
                    _tableRows[_selectedColumnindex][i] = dateInMillis;
                    insertedValue = $"{dateInMillis}";
                }
                i++;
            }
            // Составление запроса
            var sqlUpdateMain =
	            $"update {_columnProperties[selectedColumn].TABLE_NAME} set {_columnProperties[selectedColumn].TABLE_COLUMN}={insertedValue} {sqlUpdateWhere.ToString().Substring(0, sqlUpdateWhere.Length - 4)}";
            using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
                // Непосредственно выполнение запроса
                var command = connection.CreateCommand();
                command.CommandText = sqlUpdateMain;
                command.CommandTimeout = 30;
                command.CommandType = System.Data.CommandType.Text;
                connection.Open();
                command.ExecuteNonQuery();
				command.Dispose();
                connection.Close();
            }
        }

        private void TableTreeView_Toggled(object sender, ToggledEventArgs e)
        {
            // Переключенный switcher - столбец
            var selectedColumn = Convert.ToInt32(((View) sender).ClassId.Split(',')[0]);
            // Переключенный switcher - строка
            //var selectedRow = Convert.ToInt32(((View) sender).ClassId.Split(',')[1]);

            // переменная для построения запроса where
            var sqlUpdateWhere = new StringBuilder("where ");
            // Значение, которое будем вставлять в запрос
            var insertedValue = "";
            var i = 0;
            // Добавляем обязательное значение C_ISSO
            sqlUpdateWhere.Append($"C_ISSO = {_cIsso} and ");
            // Проходимся по каждой колонке
            foreach (var property in _columnProperties)
            {
                // Если это не та колонка, которая редактировалась, то заносим её в запрос where 
                if (i != selectedColumn)
                {
                    if (property.IS_PRIMARY_KEY)
                        // Смотрим, какой тип данных у нас
                        switch (property.COLUMNTYPE)
                        {
                            case "text":
                            case "varchar":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Текст вставляем с '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                            case "int":
                            case "smallint":
                            case "bigint":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Целые числа вставляем в запрос без '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i]} and ");
                                }
                                break;
                            case "float":
                            case "double":
                            case "REAL":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Заменяем запятые на точки для получения правильного запроса
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i].ToString().Replace(",", ".")} and ");
                                }
                                break;
                            default:
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Иначе вставляем как текст
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                        }
                }
                else
                {
                    selectedColumn = i;
                    // Сохраняем индекс измененной колонки
                    _tableRows[_selectedColumnindex][i] = ((Switch) sender).IsToggled ? 1 : 0;
                    insertedValue = $"{_tableRows[_selectedColumnindex][i]}";
                }
                i++;
            }
            // Составление запроса
            var sqlUpdateMain =
	            $"update {_columnProperties[selectedColumn].TABLE_NAME} set {_columnProperties[selectedColumn].TABLE_COLUMN}={insertedValue} {sqlUpdateWhere.ToString().Substring(0, sqlUpdateWhere.Length - 4)}";
            using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
                // Непосредственно выполнение запроса
                var command = connection.CreateCommand();
                command.CommandText = sqlUpdateMain;
                command.CommandTimeout = 30;
                command.CommandType = System.Data.CommandType.Text;
                connection.Open();
                command.ExecuteNonQuery();
				command.Dispose();
                connection.Close();
            }
        }


	    /// <summary>
	    /// Метод для нажатия на редактируемый элемент таблицы
	    /// </summary>
	    /// <param name="property"></param>
	    /// <param name="view"></param>
	    /// <param name="columnIndex"></param>
	    private async void GetPopupWindow(DBHelper.ADVANCED_S_TABLES property, View view, int columnIndex)
        {
            if (Tapped) return;
            Tapped = true;
            _selectedColumnindex = columnIndex;
            _selectedProperty = property;
            // Здесь мы начинаем смотреть, к какому типу данных относится нажатая ячейка;
            // Если это ячейка со справочными материалами, то вызываем окно со списком
            if (property.S_TABLE != null)
            {
                var filter = "";
                //Получим доп фильтр
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    // ToDO: 
	                var command = connection.CreateCommand();
                    int? getConstr = null;
                    command.CommandText = $"select C_GR_CONSTR from S_TYPISSO where C_TYPISSO={_cGrConstr}";
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();

                    using (var datareader = command.ExecuteReader())
                    {
                        if (datareader.HasRows)
                        {
                            datareader.Read();
                            getConstr = Convert.ToInt32(datareader.GetValue(0));
                        }
						datareader.Dispose();
                    }
					command.Dispose();
                    connection.Close();

                    // 2 часть - получим фильтр, если таковой имеется
                    command = connection.CreateCommand();
                    command.CommandText =
	                    $"Select FILTER from SYS_FILTER where REF_TABLE='{property.S_TABLE.Split('.')[0]}' and TABLE_ID={_cGrConstr} and " +
	                    $"(COLUMN_NAME='{property.S_TABLE.Split('.')[1]}' or COLUMN_NAME is null) and (C_GR_CONSTR={getConstr ?? -1} or C_GR_CONSTR is null)";
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    using (var datareader = command.ExecuteReader())
                    {
                        if (datareader.HasRows)
                        {
                            datareader.Read();
                            filter = Convert.ToString(datareader.GetValue(0));
                        }
						datareader.Dispose();
                    }
                    connection.Close();
                }
                if (!filter.Equals(""))
                {
                    ////алгоритм обработки строки фильтра, в соответствии с ID
                    //// Уровень открытых скобок
                    //int level = 0;
                    //int char_index = 0;
                    //// индексы начала и конца подзапроса
                    //int start = -1, end = -1;
                    //foreach(char c in filter)
                    //{
                    //    // В случае с первой открытой строкой получаем начало подзапроса
                    //    if (c.Equals('(') && level == 0)
                    //    {
                    //        level++;
                    //        start = char_index;
                    //    }
                    //    // Если ещё открывается скобка, то увеличиваем уровень открытых скобок
                    //    else if (c.Equals('(') && level > 0)
                    //    {
                    //        level++;
                    //    }
                    //    // Если закрытая скобка и уровень открытых скобок > 1, то просто понижаем этот уровень
                    //    else if (c.Equals(')') && level > 1)
                    //    {
                    //        level--;
                    //    }
                    //    // Если закрытая скобка и она последняя в для подзапроса, то получаем конечный индекс подзапроса
                    //    else if (c.Equals(')') && level == 1)
                    //    {
                    //        level--;
                    //        end = char_index;
                    //    }
                    //    char_index++;
                    //}
                    //// если у нас были скобки, то начинаем тарабанить строку на 3 части
                    //if(start != -1)
                    //{
                    //    string first_part = filter.Substring(0, start - 1);
                    //    string last_part = "";
                    //    if(end != filter.Length - 1)
                    //        last_part = filter.Substring(end + 1, filter.Length - 1);
                    //    string sub_query = filter.Substring(start, end - start);
                    //    first_part = first_part.ToLower().Replace(property.S_TABLE.Split('.')[1].ToLower(), "ID");
                    //    last_part = last_part.ToLower().Replace(property.S_TABLE.Split('.')[1].ToLower(), "ID");
                    //    filter = String.Format("{0}{1}{2}", first_part, sub_query, last_part);
                    //}

                    var replacement = filter.Substring(0, filter.IndexOf(" ", StringComparison.Ordinal)).ToLower();

                    // Используя регулярные выражения
                    var pattern = $@"{replacement}(\s+in|\s+not\s+in|\s*<|\s*=|\s*>|\s+when)";
                    var regex = new Regex(pattern);
                    var match = regex.Match(filter);
                    if (match.Success)
                    {
                        var result = match.Value;
                        result = result.ToLower().Replace(replacement, "ID");
                        filter = regex.Replace(filter, result, 1);
                    }
                    pattern = $@"or\s+{replacement}";
                    regex = new Regex(pattern);
                    match = regex.Match(filter);
                    if (match.Success)
                    {
                        var result = match.Value;
                        result = result.ToLower().Replace(replacement, "ID");
                        filter = regex.Replace(filter, result, 1);
                    }
                    filter = filter.Replace("@cisso", Convert.ToString(_cGrConstr));
                }

                var sTableResult = Connection.Query<DBHelper.S_INFO_TABLES>(
	                $"select * from S_INFO_TABLES where TABLE_NAME='{property.S_TABLE}' {(filter.Equals("") ? "" : $"and {filter}")}").ToList();
                var value = ((Label) ((Grid) view).Children[0]).Text;
                var collection = new ObservableCollection<ModelForSelect>();
                foreach (var info in sTableResult)
                {
                    collection.Add(new ModelForSelect(info.VALUE, info.VALUE.Equals(value)));
                }
                var listpopup = new SelectedListViewPopupPage(view, collection, property.DESCRIPTION);
                listpopup.SaveChanges += Listpopup_SaveChanges;
				var popupPage = new CommonPopupPage(listpopup, listpopup.Header);
                await Navigation.PushPopupAsync(popupPage);
                Tapped = false;
            }
            // иначе смотрим на тип данных
            else
            {
	            CommonPopupPage popupPage;
                switch (property.COLUMNTYPE)
                {
                    // В случае с текстом вызываем popup с текстом
                    case "text":
                    case "varchar":
                        var textPopupPage = new TextPopupPage(view, property, CellType.IsText, _sysName);
                        textPopupPage.SaveChanges += TextPopupPage_SaveChanges;
                        await Navigation.PushPopupAsync(new CommonPopupPage(textPopupPage, textPopupPage.Header));
                        Tapped = false;
                        break;
                    // В случае с числами вызываем popup с текстом и числовым редактором
                    case "int":
                    case "smallint":
                    case "bigint":
                        var numPopupPage = new TextPopupPage(view, property, CellType.IsNumeric, _sysName);
                        numPopupPage.SaveChanges += TextPopupPage_SaveChanges;
                        await Navigation.PushPopupAsync(new CommonPopupPage(numPopupPage, numPopupPage.Header));
                        Tapped = false;
                        break;
                    // В случае с числами с плавающей точкой вызываем popup c текстом и числовым редактором, в котором число будет обрубаться в соответствии с форматом
                    case "float":
                    case "double":
                    case "REAL":
                        var doublePopupPage = new TextPopupPage(view, property, CellType.IsDouble, _sysName);
                        doublePopupPage.SaveChanges += TextPopupPage_SaveChanges;
                        await Navigation.PushPopupAsync(new CommonPopupPage(doublePopupPage, doublePopupPage.Header));
                        Tapped = false;
                        break;
                    // Если это дата, то будем вызывать диалог с выбором даты
                    case "long":
                    case "date":
                    case "datetime":
                    case "numeric":
                        var selectDatePickerPage = new SelectDatePickerPage(view, Convert.ToInt64(_tableRows[_selectedColumnindex][_columnProperties.IndexOf(_selectedProperty)]));
                        selectDatePickerPage.SaveChanges += SelectDatePickerPage_SaveChanges;
						popupPage = new CommonPopupPage(selectDatePickerPage, SelectDatePickerPage.Header);
                        await Navigation.PushPopupAsync(popupPage);
                        Tapped = false;
                        break;
                    default:
#pragma warning disable 219
                        var err = "ERROR! UNUSED TYPE";
#pragma warning restore 219
                        break;
                }
            }
        }

        private void SelectDatePickerPage_SaveChanges(object sender, EventArgs e)
        {
            // Получаем дату
            var dateEdit = Convert.ToDateTime(((Label) ((Grid) sender).Children[0]).Text);
            var dateInMillis = dateEdit.ToLocalTime().Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Local)).TotalMilliseconds;
	        // переменная для построения запроса where
            var sqlUpdateWhere = new StringBuilder("where ");
            // Значение, которое будем вставлять в запрос
            var insertedValue = "";
            var i = 0;
            // Добавляем обязательное значение C_ISSO
            sqlUpdateWhere.Append($"C_ISSO = {_cIsso} and ");
            // Проходимся по каждой колонке
            foreach (var property in _columnProperties)
            {
                // Если это не та колонка, которая редактировалась, то заносим её в запрос where 
                if (property != _selectedProperty)
                {
                    if (property.IS_PRIMARY_KEY)
                        // Смотрим, какой тип данных у нас
                        switch (property.COLUMNTYPE)
                        {
                            case "text":
                            case "varchar":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Текст вставляем с '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                            case "int":
                            case "smallint":
                            case "bigint":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Целые числа вставляем в запрос без '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i]} and ");
                                }
                                break;
                            case "float":
                            case "double":
                            case "REAL":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Заменяем запятые на точки для получения правильного запроса
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i].ToString().Replace(",", ".")} and ");
                                }
                                break;
                            default:
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Иначе вставляем как текст
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                        }
                }
                else
                {
                    // Сохраняем индекс измененной колонки
	                _tableRows[_selectedColumnindex][i] = dateInMillis;
                    insertedValue = $"{dateInMillis}";
                }
                i++;
            }
            // Составление запроса
            var sqlUpdateMain =
	            $"update {_selectedProperty.TABLE_NAME} set {_selectedProperty.TABLE_COLUMN}={insertedValue} {sqlUpdateWhere.ToString().Substring(0, sqlUpdateWhere.Length - 4)}";
            using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
                // Непосредственно выполнение запроса
                var command = connection.CreateCommand();
                command.CommandText = sqlUpdateMain;
                command.CommandTimeout = 30;
                command.CommandType = System.Data.CommandType.Text;
                connection.Open();
                command.ExecuteNonQuery();
				command.Dispose();
                connection.Close();
            }
        }

        private void Listpopup_SaveChanges(object sender, EventArgs e)
        {
            // Получаем значение, введенное в поле редактирования
            var value = ((Label) ((Grid) sender).Children[0]).Text;
            // Получим идентификатор выбранного элемента из справочной таблицы
            var sTableResult = Connection.Query<DBHelper.S_INFO_TABLES>(
	            $"select * from S_INFO_TABLES where TABLE_NAME='{_selectedProperty.S_TABLE}' and VALUE='{value}'").ToList()[0];
            // переменная для построения запроса where
            var sqlUpdateWhere = new StringBuilder("where ");
            // Значение, которое будем вставлять в запрос
            var insertedValue = "";
            var i = 0;
            // Добавляем обязательное значение C_ISSO
            sqlUpdateWhere.Append($"C_ISSO = {_cIsso} and ");
            // Проходимся по каждой колонке
            foreach (var property in _columnProperties)
            {
                // Если это не та колонка, которая редактировалась, то заносим её в запрос where 
                if (property != _selectedProperty)
                {
                    if (property.IS_PRIMARY_KEY)
                        // Смотрим, какой тип данных у нас
                        switch (property.COLUMNTYPE)
                        {
                            case "text":
                            case "varchar":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Текст вставляем с '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                            case "int":
                            case "smallint":
                            case "bigint":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Целые числа вставляем в запрос без '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i]} and ");
                                }
                                break;
                            case "float":
                            case "double":
                            case "REAL":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Заменяем запятые на точки для получения правильного запроса
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i].ToString().Replace(",", ".")} and ");
                                }
                                break;
                            default:
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Иначе вставляем как текст
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                        }
                }
                else
                {
	                // В случае с текстом записываем как есть
                    _tableRows[_selectedColumnindex][i] = sTableResult.ID;
                    insertedValue = $"{sTableResult.ID}";
                }
                i++;
            }
            // Составление запроса
            var sqlUpdateMain =
	            $"update {_selectedProperty.TABLE_NAME} set {_selectedProperty.TABLE_COLUMN}={insertedValue} {sqlUpdateWhere.ToString().Substring(0, sqlUpdateWhere.Length - 4)}";
            using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
                // Непосредственно выполнение запроса
                using (var command = connection.CreateCommand())
                {
                    command.CommandText = sqlUpdateMain;
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    command.ExecuteNonQuery();
					command.Dispose();
                    connection.Close();
                }
            }
        }

        private void TextPopupPage_SaveChanges(object sender, EventArgs e)
        {
            // Получаем значение, введенное в поле редактирования
            var value = ((Label) ((Grid) sender).Children[0]).Text;
            // переменная для построения запроса where
            var sqlUpdateWhere = new StringBuilder("where ");
            // Значение, которое будем вставлять в запрос
            var insertedValue = "";
            var i = 0;
            // Добавляем обязательное значение C_ISSO
            sqlUpdateWhere.Append($"C_ISSO = {_cIsso} and ");
            // Проходимся по каждой колонке
            foreach (var property in _columnProperties)
            {
                // Если это не та колонка, которая редактировалась, то заносим её в запрос where 
                if (property != _selectedProperty)
                {
                    if (property.IS_PRIMARY_KEY)
                        // Смотрим, какой тип данных у нас
                        switch (property.COLUMNTYPE)
                        {
                            case "text":
                            case "varchar":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Текст вставляем с '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                            case "int":
                            case "smallint":
                            case "bigint":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Целые числа вставляем в запрос без '' 
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i]} and ");
                                }
                                break;
                            case "float":
                            case "double":
                            case "REAL":
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Заменяем запятые на точки для получения правильного запроса
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}={_tableRows[_selectedColumnindex][i].ToString().Replace(",", ".")} and ");
                                }
                                break;
                            default:
                                if (_tableRows[_selectedColumnindex][i] != DBNull.Value)
                                {
                                    // Иначе вставляем как текст
                                    sqlUpdateWhere.Append(
	                                    $"{property.TABLE_COLUMN}='{_tableRows[_selectedColumnindex][i]}' and ");
                                }
                                break;
                        }
                }
                else
                {
                    switch (property.COLUMNTYPE)
                    {
                        case "text":
                        case "varchar":
                            // В случае с текстом записываем как есть
                            _tableRows[_selectedColumnindex][i] = value;
                            insertedValue = $"'{value}'";
                            break;
                        case "int":
                        case "smallint":
                        case "bigint":
                            var driver = Ais7DataColumnDriver.Create(_sysName, property.TABLE_COLUMN);
                            if (driver is Ais7DataColumnDriver_V_ISSO_W_ISSO)
                            {
                                try
                                {
                                    var km = Convert.ToInt32(value.Split('+')[0]);
                                    var m = Convert.ToInt32(value.Split('+')[1]);
                                    var modifiedValue = (km << 16) + m;
                                    _tableRows[_selectedColumnindex][i] = driver.Convert(modifiedValue);
                                    insertedValue = $"{modifiedValue}";
                                    ((Label) ((Grid) sender).Children[0]).Text = Convert.ToString(_tableRows[_selectedColumnindex][i]);
                                }
                                catch (Exception)
                                {
                                    return;
                                }
                            }
                            else
                            {
                                // Если целое, то конвертим
                                _tableRows[_selectedColumnindex][i] = Convert.ToInt32(value);
                                insertedValue = $"{Convert.ToInt32(value)}";
                            }
                            break;
                        case "float":
                        case "double":
                        case "REAL":
                            // Здесь также конвертим
                            _tableRows[_selectedColumnindex][i] = Convert.ToDouble(value);
                            insertedValue = $"{Convert.ToDouble(value)}".Replace(",", ".");
                            break;
                        default:
                            // В ином случае записываем как текст
                            _tableRows[_selectedColumnindex][i] = value;
                            insertedValue = $"'{value}'";
                            break;
                    }
                }
                i++;
            }
            // Составление запроса
            var sqlUpdateMain =
	            $"update {_selectedProperty.TABLE_NAME} set {_selectedProperty.TABLE_COLUMN}={insertedValue} {sqlUpdateWhere.ToString().Substring(0, sqlUpdateWhere.Length - 4)}";
            using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
            {
                // Непосредственно выполнение запроса
                using (var command = connection.CreateCommand())
                {
                    command.CommandText = sqlUpdateMain;
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    command.ExecuteNonQuery();
                    connection.Close();
                }
            }
        }

        private void GeoPage_CoordsChanged(object sender, EventArgs args)
        {
            var geoPos = (double[])sender;
            var ais7DataColumnDriverVIssoGeo = new Ais7DataColumnDriver_V_ISSO_GEO();
            foreach (var child in Root.Children)
            {
                if (child is Grid)
                {
                    var grid = child as Grid;
                    for (var i = 0; i < grid.RowDefinitions.Count; i++)
                    {
                        var definiton = ((grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0] as Grid)?.Children.ToList()[0] as Label)?.Text;
                        var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
                        var view = (grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0] as Grid)?.Children[0] as Label;
                        switch (columnProperty.TABLE_COLUMN)
                        {
                            case "P1_LATITUDE":
	                            if (view != null) view.Text = ais7DataColumnDriverVIssoGeo.Convert(geoPos[0]).ToString();
	                            break;
                            case "P1_LONGITUDE":
	                            if (view != null) view.Text = ais7DataColumnDriverVIssoGeo.Convert(geoPos[1]).ToString();
                                break;
                            case "P2_LATITUDE":
	                            if (view != null) view.Text = ais7DataColumnDriverVIssoGeo.Convert(geoPos[2]).ToString();
                                break;
                            case "P2_LONGITUDE":
	                            if (view != null) view.Text = ais7DataColumnDriverVIssoGeo.Convert(geoPos[3]).ToString();
                                break;
                        }
                    }
                }
            }
        }


	    /// <summary>
	    /// Метод построения таблицы
	    /// </summary>
	    /// <summary>
	    ///  Создание текстового контрола
	    /// </summary>
	    /// <param name="info"></param>
	    /// <param name="columnName"></param>
	    /// <param name="isNameColumn"></param>
	    /// <param name="isEditable"></param>
	    /// <returns></returns>
	    public Grid GetTextLayout(string info, string columnName, bool isNameColumn, bool isEditable)
        {
            //StackLayout layout = new StackLayout
            //{
            //    Orientation = StackOrientation.Horizontal,
            //    BackgroundColor = Color.White,
            //    Padding = new Thickness(10, 5, 10, 5)
            //};
            var grid = new Grid
            {
                BackgroundColor = Color.White,
                Padding = new Thickness(10, 5, 10, 5),
                HorizontalOptions = LayoutOptions.FillAndExpand,
                VerticalOptions = LayoutOptions.FillAndExpand,
            };
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(4, GridUnitType.Star) });
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(1, GridUnitType.Star) });

            var label = new Label
            {
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.FillAndExpand,
                FontSize = 12,
                HorizontalTextAlignment = TextAlignment.Start,
                VerticalTextAlignment = TextAlignment.Center, 
                TextColor = Color.Black
            };
            //label.Margin = new Thickness(5, 2, 2, 5);
            var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
            if (driver != null && !isNameColumn)
                label.Text = Convert.ToString(driver.Convert(info));
            else
                label.Text = info;
            //if (isEditable)
            //    label.TextColor = Color.Black;
            grid.Children.Add(label, 0, 0);
            //layout.Children.Add(label);
            if(isEditable && !isNameColumn)
            {
	            var btnedit = new Button
	            {
		            VerticalOptions = LayoutOptions.Center,
		            HorizontalOptions = LayoutOptions.Center,
		            WidthRequest = 30,
		            HeightRequest = 30,
		            Text = "...",
		            IsEnabled = InProcessOfEditing,
		            IsVisible = InProcessOfEditing,
		            Command = new Command<object>(view =>
		            {
			            var property = _columnProperties[Convert.ToInt32(grid.ClassId.Split(',')[0])];
			            GetPopupWindow(property, grid, Convert.ToInt32(grid.ClassId.Split(',')[1]));
		            })
	            };
	            grid.Children.Add(btnedit, 1, 0);
            }

            return grid;
        }

	    /// <summary>
	    /// Класс для изменения информации в текстовом контроле
	    /// </summary>
	    /// <param name="view"></param>
	    /// <param name="info"></param>
	    /// <param name="columnName"></param>
	    public void SetTextInfo(View view, string info, string columnName)
	    {
		    switch (view)
		    {
			    case Label _:
				    var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
				    ((Label) view).Text = driver != null ? Convert.ToString(driver.Convert(info)) : info;
				    break;
			    case Editor _:
				    ((Editor) view).Text = info;
				    break;
		    }
	    }

	    /// <summary>
	    /// Метод для создания контрола с числовой инфой
	    /// </summary>
	    /// <param name="info"></param>
	    /// <param name="columnName"></param>
	    /// <param name="isEditable"></param>
	    /// <returns></returns>
	    public Grid GetNumericLayout(object info, string columnName, bool isEditable)
        {
            //StackLayout layout = new StackLayout
            //{
            //    Orientation = StackOrientation.Horizontal,
            //    BackgroundColor = Color.White,
            //    Padding = new Thickness(10, 5, 10, 5)
            //};
            var grid = new Grid
            {
                BackgroundColor = Color.White,
                Padding = new Thickness(10, 5, 10, 5),
                HorizontalOptions = LayoutOptions.FillAndExpand,
                VerticalOptions = LayoutOptions.FillAndExpand,
            };
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(4, GridUnitType.Star) });
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(1, GridUnitType.Star) });

	        var label = new Label
	        {
		        VerticalOptions = LayoutOptions.FillAndExpand,
		        HorizontalOptions = LayoutOptions.FillAndExpand,
		        FontSize = 12,
		        HorizontalTextAlignment = TextAlignment.Start,
		        VerticalTextAlignment = TextAlignment.Center,
		        TextColor = Color.Black,
		        Margin = new Thickness(5, 2, 2, 5)
	        };
	        switch(info)
            {
                case short _:
                    if (((short)info) != short.MinValue)
                    {
                        var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
	                    label.Text = Convert.ToString(driver != null ? driver.Convert(info) : info);
                    }
                    break;
                case int _:
                    if (((int)info) != int.MinValue)
                    {
                        var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
	                    label.Text = Convert.ToString(driver != null ? driver.Convert(info) : info);
                    }
                    break;
                case long _:
                    if (((long)info) != long.MinValue)
                    {
                        var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
	                    label.Text = Convert.ToString(driver != null ? driver.Convert(info) : info);
                    }
                    break;
            }
            //if (((Int32) info) != Int32.MinValue)
            //    {
            //        var driver = ais7DataColumnDriver.Create(SYS_NAME, columnName);
            //        if (driver != null)
            //            label.Text = Convert.ToString(driver.Convert(info));
            //        else
            //            label.Text = Convert.ToString(info);
            //    }
            //if (info != Int32.MinValue)
            //{
            //    var driver = ais7DataColumnDriver.Create(SYS_NAME, columnName);
            //    if (driver != null)
            //        label.Text = Convert.ToString(driver.Convert(info));
            //    else
            //        label.Text = Convert.ToString(info);
            //}
            //if (isEditable)
            //    label.TextColor = Color.Black;

            grid.Children.Add(label, 0, 0);

	        if (!isEditable) return grid;
	        var btnedit = new Button
	        {
		        VerticalOptions = LayoutOptions.Center,
		        HorizontalOptions = LayoutOptions.Center,
		        WidthRequest = 30,
		        HeightRequest = 30,
		        Text = "...",
		        IsEnabled = InProcessOfEditing,
		        IsVisible = InProcessOfEditing,
		        Command = new Command<object>(view =>
		        {
			        var property = _columnProperties[Convert.ToInt32(grid.ClassId.Split(',')[0])];
			        GetPopupWindow(property, grid, Convert.ToInt32(grid.ClassId.Split(',')[1]));
		        })
	        };
	        grid.Children.Add(btnedit, 1, 0);
	        return grid;
        }

	    /// <summary>
	    /// изменение инфы в числовом контроле
	    /// </summary>
	    /// <param name="view"></param>
	    /// <param name="info"></param>
	    /// <param name="columnName"></param>
	    public void SetNumericInfo(View view, int info, string columnName)
	    {
		    switch (view)
		    {
			    case Label _:
				    if (info != int.MinValue)
				    {
					    var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
					    ((Label) view).Text = driver != null ? Convert.ToString(driver.Convert(info)) : Convert.ToString(info);
				    }
				    else
					    ((Label) view).Text = "";

				    break;
			    case Entry _:
				    ((Entry) view).Text = info != int.MinValue ? Convert.ToString(info) : "";
				    break;
		    }
	    }

	    /// <summary>
	    /// Метод создания контрола с данными формата double
	    /// </summary>
	    /// <param name="info"></param>
	    /// <param name="format">Формат данных (количество цифр после запятой)</param>
	    /// <param name="columnName"></param>
	    /// <param name="isEditable"></param>
	    /// <returns></returns>
	    public Grid GetDoubleLayout(double info, string format, string columnName, bool isEditable)
        {
            //StackLayout layout = new StackLayout
            //{
            //    Orientation = StackOrientation.Horizontal,
            //    BackgroundColor = Color.White,
            //    Padding = new Thickness(10, 5, 10, 5)
            //};
            var grid = new Grid
            {
                BackgroundColor = Color.White,
                Padding = new Thickness(10, 5, 10, 5),
                HorizontalOptions = LayoutOptions.FillAndExpand,
                VerticalOptions = LayoutOptions.FillAndExpand,
            };
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(4, GridUnitType.Star) });
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(1, GridUnitType.Star) });

	        var label = new Label
	        {
		        VerticalOptions = LayoutOptions.FillAndExpand,
		        HorizontalOptions = LayoutOptions.FillAndExpand,
		        FontSize = 12,
		        HorizontalTextAlignment = TextAlignment.Start,
		        VerticalTextAlignment = TextAlignment.Center,
		        TextColor = Color.Black,
		        Margin = new Thickness(5, 2, 2, 5)
	        };
	        if (Math.Abs(info - double.MinValue) > CommonStaffUtils.DoubleTolerance)
            {
	            var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
	            label.Text = driver != null ? Convert.ToString(driver.Convert(info)) : info.ToString(format ?? "F2");
            }
            //if (isEditable)
            //    label.TextColor = Color.Black;
            grid.Children.Add(label, 0, 0);

            if (isEditable /*&& (!read_only_columns.Contains(columnName))*/)
            {
	            var btnedit = new Button
	            {
		            VerticalOptions = LayoutOptions.Center,
		            HorizontalOptions = LayoutOptions.Center,
		            WidthRequest = 30,
		            HeightRequest = 30,
		            Text = "...",
		            IsEnabled = InProcessOfEditing,
		            IsVisible = InProcessOfEditing,
		            Command = new Command<object>(view =>
		            {
			            var property = _columnProperties[Convert.ToInt32(grid.ClassId.Split(',')[0])];
			            GetPopupWindow(property, grid, Convert.ToInt32(grid.ClassId.Split(',')[1]));
		            })
	            };
	            grid.Children.Add(btnedit, 1, 0);
            }
            return grid;
        }


	    /// <summary>
	    /// Изменение инфы в контроле с данными типа double
	    /// </summary>
	    /// <param name="view"></param>
	    /// <param name="info"></param>
	    /// <param name="format"></param>
	    /// <param name="columnName"></param>
	    public void SetDoubleInfo(View view, double info, string format, string columnName)
	    {
		    switch (view)
		    {
			    case Label _:
				    if (Math.Abs(info - double.MinValue) > CommonStaffUtils.DoubleTolerance)
				    {
					    var driver = Ais7DataColumnDriver.Create(_sysName, columnName);
					    ((Label) view).Text = driver != null ? Convert.ToString(driver.Convert(info)) : info.ToString(format ?? "F2");
				    }
				    else
					    ((Label) view).Text = "";

				    break;
			    case Entry _:
				    ((Entry) view).Text = Math.Abs(info - double.MinValue) > CommonStaffUtils.DoubleTolerance ? info.ToString(format ?? "F2") : "";
				    break;
		    }
	    }

        public Grid GetDateLayout(long info, string columnName, bool isEditable)
        {
            //StackLayout layout = new StackLayout
            //{
            //    Orientation = StackOrientation.Horizontal,
            //    BackgroundColor = Color.White,
            //    Padding = new Thickness(10, 5, 10, 5)
            //};
            //Label label = new Label
            //{
            //    VerticalOptions = LayoutOptions.FillAndExpand,
            //    HorizontalOptions = LayoutOptions.FillAndExpand,
            //    FontSize = 12,
            //    HorizontalTextAlignment = TextAlignment.Start,
            //    VerticalTextAlignment = TextAlignment.Center
            //};

            var grid = new Grid
            {
                BackgroundColor = Color.White,
                Padding = new Thickness(10, 5, 10, 5),
                HorizontalOptions = LayoutOptions.FillAndExpand,
                VerticalOptions = LayoutOptions.FillAndExpand,
            };
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(4, GridUnitType.Star) });
            grid.ColumnDefinitions.Add(new ColumnDefinition() { Width = new GridLength(1, GridUnitType.Star) });

            var datePicker = new DatePicker
            {
                //FontSize = 12,
                Format = "dd.MM.yyyy",
            };
            var label = new Label
            {
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.FillAndExpand,
                FontSize = 12,
                HorizontalTextAlignment = TextAlignment.Start,
                VerticalTextAlignment = TextAlignment.Center,
                TextColor = Color.Black
            };
            if (info != long.MinValue)
                datePicker.Date = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(info);
            label.Text = datePicker.Date.ToString("dd.MM.yyyy");

            //datePicker.Margin = new Thickness(5, 2, 2, 2);
            //datePicker.Focused += DatePicker_Focused;
            datePicker.IsVisible = false;
            grid.Children.Add(datePicker);
            grid.Children.Add(label, 0, 0);
            if (isEditable)
            {
	            var btnedit = new Button
	            {
		            VerticalOptions = LayoutOptions.Center,
		            HorizontalOptions = LayoutOptions.Center,
		            WidthRequest = 30,
		            HeightRequest = 30,
		            Text = "...",
		            IsEnabled = InProcessOfEditing,
		            IsVisible = InProcessOfEditing,
		            Command = new Command<object>(view =>
		            {
			            _selectedColumnindex = Convert.ToInt32(grid.ClassId.Split(',')[1]);
			            datePicker.Focus();
		            })
	            };
	            grid.Children.Add(btnedit, 1, 0);
            }
            return grid;
        }

        // Пустой Event Handler для поля ReadOnly
        //private void DatePicker_Focused(object sender, FocusEventArgs e) { return; }

        public void SetDateInfo(View view, long info, string columnName)
        {
            foreach(var subview in ((Grid) view).Children)
            {
                if (subview is DatePicker)
                {
                    if (info != long.MinValue)
                    {
                        //(view as DatePicker).IsVisible = true;
                        //(view as DatePicker).Unfocus();
                        (subview as DatePicker).DateSelected -= TableTreeView_DateSelected;
                        (subview as DatePicker).Date = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(info);
                        (subview as DatePicker).DateSelected += TableTreeView_DateSelected;
                    }
                    //else
                    //{
                    //    (view as DatePicker).IsVisible = false;
                    //}
                }
                else if (subview is Label)
                {
                    if (info != long.MinValue)
                    {
                        //(view as DatePicker).IsVisible = true;
                        //(view as DatePicker).Unfocus();
                        (subview as Label).Text = (((Grid) subview.Parent).Children[0] as DatePicker)?.Date.ToString("dd.MM.yyyy");
                    }
                }
            }
        }

        public Grid GetSwitchLayout(bool isReadOnly, bool switched)
        {
            //StackLayout layout = new StackLayout
            //{
            //    Orientation = StackOrientation.Vertical,
            //    BackgroundColor = Color.White,
            //    Padding = new Thickness(10, 5, 10, 5)
            //};
            var grid = new Grid
            {
                BackgroundColor = Color.White,
                Padding = new Thickness(10, 5, 10, 5),
            };

            var switcher = new Switch
            {
                VerticalOptions = LayoutOptions.FillAndExpand,
                HorizontalOptions = LayoutOptions.Center,
                IsToggled = switched
            };
            //if (isReadOnly)
            //    switcher.IsEnabled = false;
            //else
            //    switcher.IsEnabled = true;
            switcher.Toggled += (s, e) =>
            {
                _selectedColumnindex = Convert.ToInt32(grid.ClassId.Split(',')[1]);
                TableTreeView_Toggled(s, e);
            };
            switcher.IsEnabled = InProcessOfEditing;
            grid.Children.Add(switcher);
            return grid;
        }

        public void SetSwitchInfo(View view, bool info)
        {
            ((Switch) view).Toggled -= TableTreeView_Toggled;
            ((Switch) view).IsToggled = info;
	        ((Switch) view).Toggled += TableTreeView_Toggled;
        }

        public override void Dispose()
        {
            try
            {
                Connection.Close();

                _columnProperties.Clear(); _columnProperties = null;
                _selectedProperty = null;
                foreach(var val in _tableRows)
                {
                    val.Clear();
                }
                _tableRows.Clear(); _tableRows = null;
                Root.Children.Clear();
                BindingContext = null;
                Content = null;

                GC.Collect();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

        }
    }
    /// <summary>
    /// Класс свойств колонки
    /// </summary>
    public class ColumnProperty
    {
        /// <summary>
        /// Номер колонки
        /// </summary>
        public int ColumnId { get; set; }
        /// <summary>
        /// Имя колонки
        /// </summary>
        public string ColumnName { get; set; }
        /// <summary>
        /// Тип колонки
        /// </summary>
        public Type Type { get; set; }
        /// <summary>
        /// Тип в строковом формате
        /// </summary>
        public string TypeStr { get; set; }

        public ColumnProperty(int columnId, string columnName, Type type, string typeStr)
        {
            ColumnId = columnId;
            ColumnName = columnName;
            Type = type;
            TypeStr = typeStr;
        }
    }

    /// <summary>
    /// Для понимания, с чем мы имеем дело в плане редактирования ячейки
    /// </summary>
    public enum CellType
    {
        IsText = 10,
        IsNumeric = 20,
        IsDouble = 30,
        IsDate = 40,
        IsLocation
    }
}
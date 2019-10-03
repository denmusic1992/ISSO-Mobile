using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TableTreeContentView : ISwipeCallback
    {
        /// <summary>
        /// данные для отображения таблицы
        /// </summary>
        private readonly List<List<object>> _tableRows;
        /// <summary>
        /// свойства колонок
        /// </summary>
        private readonly List<DBHelper.ADVANCED_S_TABLES> _columnProperties;

	    /// <summary>
        /// Ширина строк, по умолчанию (горизонтально)
        /// </summary>
        private readonly double _cellHeightVertical;
        /// <summary>
        /// Ширина строк, по умолчанию (вериткально)
        /// </summary>
        private readonly double _cellHeightHorizontal;
        /// <summary>
        /// Текущая запись
        /// </summary>
        private int _currentPosition;
        /// <summary>
        /// переменная, отвечающая за время анимации
        /// </summary>
        private readonly uint _millisecondsAnim = 100;
        /// <summary>
        /// Описание 
        /// </summary>
        private readonly string _description;

        private double _width;
        private double _height;

/*
        /// <summary>
        /// Список типов, встречающихся в приложении
        /// </summary>
        private Dictionary<Type, string> _types = new Dictionary<Type, string>
        {
            {typeof(Label), "label" },
            {typeof(Entry), "entry" },
            {typeof(Editor), "editor" },
            {typeof(String), "string" },
            {typeof(float), "float" },
            {typeof(double), "float" },
            {typeof(DateTime), "datetime" },
            {typeof(byte[]), "byte" },
            {typeof(bool), "boolean" }
        };
*/

        public TableTreeContentView(List<DBHelper.ADVANCED_S_TABLES> columnProperties, List<List<object>> tableRows, string description)
        {
            InitializeComponent();
            // Получаем данные
            _tableRows = tableRows;
            _columnProperties = columnProperties;
            _description = description;
            _width = Width;
            _height = Height;

            if (_tableRows.Count > 1)
            {
	            //ArrowLeft.FadeTo(0, 0);
                //if (tableRows.Count == 1)
                //    ArrowRight.FadeTo(0, 0);
                //Label_number.Text = String.Format("Запись №{0}", current_position + 1);


	            //var swipeListener = new SwipeListener(stack_test, this);


	            //Vapolia.Lib.Ui.Gesture.SetSwipeLeftCommand(scrollviewgrid, new Command(() => { OnLeftSwipe(); }));
                //Vapolia.Lib.Ui.Gesture.SetSwipeRightCommand(scrollviewgrid, new Command(() => { OnRightSwipe(); }));
                //Vapolia.Lib.Ui.Gesture.SetSwipeBottomCommand(scrollviewgrid, new Command(() => { scrollviewgrid.ScrollToAsync(0, 0, true); }));
                //Vapolia.Lib.Ui.Gesture.SetSwipeTopCommand(scrollviewgrid, new Command(() => { scrollviewgrid.ScrollToAsync(0, scrollviewgrid.Content.Height, true); }));
                //SwipeListener swipeListener2 = new SwipeListener(pan_container, this);
            }
            else
            {
                stack_test.IsVisible = false;
            }
            // Добавляем информацию в Picker
            var entriesItems = new List<string>();
            for (var i = 1; i <= tableRows.Count; i++)
            {
                entriesItems.Add($"Запись №{i}");
            }
            picker_entries.ItemsSource = entriesItems;
            picker_entries.SelectedIndex = 0;

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
                _cellHeightHorizontal = Application.Current.MainPage.Height / 7;
                // вызываем метод построения таблицы
                //BuildTableView(true);
            }

            //BuildDatTreeeeeeee();
        }


        //protected override void OnSizeAllocated(double width, double height)
        //{
        //    base.OnSizeAllocated(width, height);
        //    this.WidthRequest = width;
        //    this.HeightRequest = height;
        //    // Горизонтальное положение
        //    if (width > height)
        //    {
        //        // Задаем ширину колонок и строк в соответствии с параметрами экрана
        //        CellWidth = Application.Current.MainPage.Height / 3;
        //        CellHeightVertical = Application.Current.MainPage.Width / 9;
        //        CellHeightHorizontal = Application.Current.MainPage.Height / 7;
        //        // вызываем метод построения таблицы
        //        BuildTableView(false);
        //    }
        //    // Вертикальное положение
        //    else
        //    {
        //        // Задаем ширину колонок и строк в соответствии с параметрами экрана
        //        CellWidth = Application.Current.MainPage.Width / 3;
        //        CellHeightVertical = Application.Current.MainPage.Height / 9;
        //        CellHeightHorizontal = Application.Current.MainPage.Width / 7;
        //        // вызываем метод построения таблицы
        //        BuildTableView(true);
        //    }
        //}

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
	            BuildTableView(!(width > height));
            }
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


        // Изменяем инфу в соответствии с выбранной записью
        private void picker_entries_SelectedIndexChanged(object sender, EventArgs e)
        {
	        if (!(sender is Picker picker))
            {
                return;
            }
            _currentPosition = picker.SelectedIndex;

	        foreach (var child in Root.Children)
            {
	            if (!(child is Grid)) continue;
	            var grid = child as Grid;
	            for (var i = 0; i < grid.RowDefinitions.Count; i++)
	            {
		            var definiton = ((Label) ((StackLayout) grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0]).Children.ToList()[0]).Text;
		            var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
		            ChangeInfo(((StackLayout) grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0]).Children.ToList()[0],
			            _columnProperties.IndexOf(columnProperty), columnProperty);
	            }
            }
            if (_currentPosition == 0)
            {
                ArrowRight.FadeTo(1, 0);
                ArrowLeft.FadeTo(0, 0);
            }
            else if (_currentPosition == _tableRows.Count - 1)
            {
                ArrowRight.FadeTo(0, 0);
                ArrowLeft.FadeTo(1, 0);
            }
            else
            {
                ArrowRight.FadeTo(1, 0);
                ArrowLeft.FadeTo(1, 0);
            }
        }

        public void OnLeftSwipe()
        {
            if (_currentPosition < _tableRows.Count - 1)
            {
                _currentPosition++;
                DoArrowAnimation(ArrowRight);
                //await Task.WhenAny<bool>
                //    (
                //    Label_number.TranslateTo(-100, 0, milliseconds_anim),
                //    Label_number.FadeTo(0, milliseconds_anim)
                //    );
                //Label_number.Text = String.Format("Запись №{0}", current_position + 1);
                //await Label_number.TranslateTo(100, 0, 0);
                //await Task.WhenAny<bool>
                //    (
                //    Label_number.TranslateTo(0, 0, milliseconds_anim),
                //    Label_number.FadeTo(1, milliseconds_anim)
                //    );
                DoAnimation(picker_entries, SwipeDirections.LeftSwipe, -1, null);

	            foreach (var child in Root.Children)
                {
	                if (!(child is Grid)) continue;
	                var grid = child as Grid;
	                for (var i = 0; i < grid.RowDefinitions.Count; i++)
	                {
		                var definiton = ((Label) ((StackLayout) grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0]).Children.ToList()[0]).Text;
		                var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
		                DoAnimation(((StackLayout) grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0]).Children.ToList()[0], SwipeDirections.LeftSwipe,
			                _columnProperties.IndexOf(columnProperty), columnProperty);
	                }
                }
                //if (current_position == tableRows.Count - 1)
                //    ArrowRight.FadeTo(0, 0);
            }
        }

        public void OnRightSwipe()
        {
	        if (_currentPosition <= 0) return;
	        _currentPosition--;
	        DoArrowAnimation(ArrowLeft);
	        //await Task.WhenAny<bool>
	        //    (
	        //    Label_number.TranslateTo(100, 0, 100),
	        //    Label_number.FadeTo(0, 100)
	        //    );
	        //Label_number.Text = String.Format("Запись №{0}", current_position + 1);
	        //await Label_number.TranslateTo(-100, 0, 0);
	        //await Task.WhenAny<bool>
	        //    (
	        //    Label_number.TranslateTo(0, 0, 100),
	        //    Label_number.FadeTo(1, 100)
	        //    );
	        DoAnimation(picker_entries, SwipeDirections.RightSwipe, -1, null);

	        foreach (var child in Root.Children)
	        {
		        if (child is Grid)
		        {
			        var grid = child as Grid;
			        for (var i = 0; i < grid.RowDefinitions.Count; i++)
			        {
				        var definiton = ((Label) ((StackLayout) grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 0).ToList()[0]).Children.ToList()[0]).Text;
				        var columnProperty = _columnProperties.Find(x => x.DESCRIPTION == definiton);
				        DoAnimation(((StackLayout) grid.Children.Where(x => Grid.GetRow(x) == i && Grid.GetColumn(x) == 1).ToList()[0]).Children.ToList()[0], SwipeDirections.RightSwipe,
					        _columnProperties.IndexOf(columnProperty), columnProperty);
			        }
		        }
	        }
	        //if (current_position == 0)
	        //    ArrowLeft.FadeTo(0, 0); ;
        }

        public async void DoArrowAnimation(View view)
        {
            await view.ScaleTo(1.2, 50);
            await view.ScaleTo(1, 50);
        }

        public async void DoAnimation(View view, SwipeDirections direction, int indexRow, DBHelper.ADVANCED_S_TABLES columnProperty)
        {
            switch (direction)
            {
                case SwipeDirections.LeftSwipe:
                    // двигаем вьюху влево
                    await Task.WhenAny
                        (
                        view.TranslateTo(-100, 0, _millisecondsAnim),
                        view.FadeTo(0, _millisecondsAnim)
                        );
                    // затем переносим её вправо и двигаем на исходное место
                    await view.TranslateTo(100, 0, 0);
                    if (indexRow != -1)
                        ChangeInfo(view, indexRow, columnProperty);
                    else
                    {
                        //Label_number.Text = String.Format("Запись №{0}", current_position + 1);
                        picker_entries.SelectedIndex = _currentPosition;
                    }
                    await Task.WhenAny
                        (
                        view.TranslateTo(0, 0, _millisecondsAnim),
                        view.FadeTo(1, _millisecondsAnim)
                        );
                    break;
                case SwipeDirections.RightSwipe:
                    // двигаем вьюху вправо
                    await Task.WhenAny
                        (
                        view.TranslateTo(100, 0, 100),
                        view.FadeTo(0, 100)
                        );
                    // затем переносим её влево и двигаем на исходное место
                    await view.TranslateTo(-100, 0, 0);

                    if (indexRow != -1)
                        ChangeInfo(view, indexRow, columnProperty);
                    else
                    {
                        //Label_number.Text = String.Format("Запись №{0}", current_position + 1);
                        picker_entries.SelectedIndex = _currentPosition;
                    }

                    await Task.WhenAny
                        (
                        view.TranslateTo(0, 0, 100),
                        view.FadeTo(1, 100)
                        );
                    break;
            }
        }

        public void ChangeInfo(View view, int indexRow, DBHelper.ADVANCED_S_TABLES columnProperty)
        {
            // Проверка на то, имеется ли ссылка на справочную таблицу
            if (columnProperty.S_TABLE != null)
            {
                // если имеется, то подставляем справочное значение
                // проверка на null
                if (_tableRows[_currentPosition][indexRow] != DBNull.Value)
                {
	                var connection = ConnectionClass.CreateDatabase();
                    var sTableResult = connection.Query<DBHelper.S_INFO_TABLES>(
	                    $"select * from S_INFO_TABLES where TABLE_NAME='{columnProperty.S_TABLE}' and ID={Convert.ToInt32(_tableRows[_currentPosition][indexRow])}").ToList();
                    SetTextInfo(view, sTableResult[0].VALUE);
					connection.Close();
                }
                else
                {
	                GetTextLayout(columnProperty.READONLY, "");
	                SetTextInfo(view, "");
                }
            }
            else
                // Задаем новое значение в соответствии с типом контрола
                switch (columnProperty.COLUMNTYPE)
                {
                    case "text":
                    case "varchar":
                        SetTextInfo(view, _tableRows[_currentPosition][indexRow] != DBNull.Value ? Convert.ToString(_tableRows[_currentPosition][indexRow]) : "");
                        break;
                    case "int":
                    case "smallint":
                        SetNumericInfo(view, _tableRows[_currentPosition][indexRow] != DBNull.Value ? Convert.ToInt32(_tableRows[_currentPosition][indexRow]) : int.MinValue);
                        break;
                    case "float":
                    case "double":
                        SetDoubleInfo(view, _tableRows[_currentPosition][indexRow] != DBNull.Value ? Convert.ToDouble(_tableRows[_currentPosition][indexRow])
                            : double.MinValue, columnProperty.FORMAT);
                        break;
                    case "long":
                    case "date":
                    case "datetime":
                        SetDateInfo(view, _tableRows[_currentPosition][indexRow] != DBNull.Value ? Convert.ToInt64(_tableRows[_currentPosition][indexRow]) : long.MinValue);
                        break;
                    case "bit":
                    case "bool":
                        SetSwitchInfo(view, _tableRows[_currentPosition][indexRow] != DBNull.Value && Convert.ToInt16(_tableRows[_currentPosition][indexRow]) == 1);
                        break;
                }
        }

	    /// <summary>
	    /// Метод построения таблицы
	    /// </summary>
	    public void BuildTableView(bool isVertical)
        {
            // Переменная для добавления в таблицу
            var index = 0;
            var previousCategory = "";
            // Очередная таблица
            Grid tableGridLayout = null;
            // номер записи для отображения, ставим по умолчанию 0
            _currentPosition = 0;
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
		                        VerticalOptions = LayoutOptions.FillAndExpand,
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
                            var columnDef = new ColumnDefinition { Width = GridLength.Star };
                            tableGridLayout.ColumnDefinitions.Add(columnDef);
                            tableGridLayout.ColumnDefinitions.Add(columnDef);

                            // Если у нас больше одной записи и горизонтальная ориентация, то добавляем ещё одну колонку
                            if (!isVertical && _tableRows.Count > 1)
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
		                        VerticalOptions = LayoutOptions.FillAndExpand,
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
                            var columnDef = new ColumnDefinition { Width = GridLength.Star };
                            tableGridLayout.ColumnDefinitions.Add(columnDef);
                            tableGridLayout.ColumnDefinitions.Add(columnDef);

                            // Если у нас больше одной записи и горизонтальная ориентация, то добавляем ещё одну колонку
                            if (!isVertical && _tableRows.Count > 1)
                            {
                                tableGridLayout.ColumnDefinitions.Add(columnDef);
                            }
                        }
                    }
                    // Добавляем 2 колонки равные по ширине
                    tableGridLayout?.RowDefinitions.Add(new RowDefinition { Height = isVertical ? _cellHeightVertical : _cellHeightHorizontal });
                    var categoryRow = GetTextLayout(true, columnProperty.DESCRIPTION);
                    tableGridLayout?.Children.Add(categoryRow, 0, index);

                    // По ориентации строим количество колонок
                    var countOfVisibleColumns = isVertical ? 1 : 2;
                    for (var columnindex = 1; columnindex <= countOfVisibleColumns; columnindex++)
                    {
                        // Проверка на то, имеется ли ссылка на справочную таблицу
                        if (columnProperty.S_TABLE != null)
                        {
                            // если имеется, то подставляем справочное значение
                            // проверка на null
                            if (_tableRows[_currentPosition][i] != DBNull.Value)
                            {
	                            var connection = ConnectionClass.CreateDatabase();
                                var sTableResult = connection.Query<DBHelper.S_INFO_TABLES>(
	                                $"select * from S_INFO_TABLES where TABLE_NAME='{columnProperty.S_TABLE}' and ID={Convert.ToInt32(_tableRows[_currentPosition][i])}").ToList();
                                var textview = GetTextLayout(columnProperty.READONLY, sTableResult[0].VALUE);
                                tableGridLayout?.Children.Add(textview, columnindex, index);
								connection.Close();
                            }
                            else
                            {
                                var textview = GetTextLayout(columnProperty.READONLY, "");
                                tableGridLayout?.Children.Add(textview, columnindex, index);
                            }
                        }
                        else
                        {
                            switch (columnProperty.COLUMNTYPE)
                            {
                                case "text":
                                case "varchar":
                                    var textview = GetTextLayout(columnProperty.READONLY,
	                                    _tableRows[_currentPosition][i] != DBNull.Value
		                                    ? Convert.ToString(_tableRows[_currentPosition][i])
		                                    : "");
                                    tableGridLayout?.Children.Add(textview, columnindex, index);
                                    break;
                                case "int":
                                case "smallint":
                                case "bigint":
                                    var numview = GetNumericLayout(columnProperty.READONLY,
	                                    _tableRows[_currentPosition][i] != DBNull.Value
		                                    ? Convert.ToInt32(_tableRows[_currentPosition][i])
		                                    : int.MinValue);
                                    tableGridLayout?.Children.Add(numview, columnindex, index);
                                    break;
                                case "float":
                                case "double":
                                    var doubleview = GetDoubleLayout(columnProperty.READONLY, _tableRows[_currentPosition][i] != DBNull.Value ?
                                        Convert.ToDouble(_tableRows[_currentPosition][i]) : double.MinValue, columnProperty.FORMAT);
                                    tableGridLayout?.Children.Add(doubleview, columnindex, index);
                                    break;
                                case "long":
                                case "date":
                                case "datetime":
                                    var dateview = GetDateLayout(columnProperty.READONLY, _tableRows[_currentPosition][i] != DBNull.Value ?
                                        Convert.ToInt64(_tableRows[_currentPosition][i]) : long.MinValue);
                                    tableGridLayout?.Children.Add(dateview, columnindex, index);
                                    break;
                                case "bit":
                                case "bool":
                                    var switchview = GetSwitchLayout(columnProperty.READONLY,
	                                    _tableRows[_currentPosition][i] != DBNull.Value &&
	                                    Convert.ToInt16(_tableRows[_currentPosition][i]) == 1);
                                    tableGridLayout?.Children.Add(switchview, columnindex, index);
                                    break;
                            }
                        }
                    }
                    index++;
                }
            }
            Root.Children.Add(tableGridLayout);
        }

        //public void BuildDatTreeeeeeee()
        //{
        //    Grid tableGridLayout = new Grid
        //    {
        //        HorizontalOptions = LayoutOptions.Fill,
        //        VerticalOptions = LayoutOptions.Fill,
        //        ColumnSpacing = 2,
        //        RowSpacing = 2,
        //        BackgroundColor = Color.Black
        //    };
        //    for (int i = 0; i <= tableRows.Count; i++)
        //    {
        //        var column_def = new ColumnDefinition
        //        {
        //            Width = tableRows.Count > 1 ? CellWidth : Application.Current.MainPage.Width / 2
        //        };
        //        tableGridLayout.ColumnDefinitions.Add(column_def);
        //    }
        //    // Переменная для добавления в таблицу
        //    int index = 0;
        //    // Для понимания, изменилась ли у нас категория
        //    string prev_category = "";
        //    for(int i = 0; i < columnProperties.Count; i++)
        //    {
        //        var columnProperty = columnProperties[i];
        //        if(columnProperty.VISIBLE)
        //        {
        //            if (prev_category != columnProperty.CATEGORY)
        //            {
        //                prev_category = columnProperty.CATEGORY;
        //                tableGridLayout.RowDefinitions.Add(new RowDefinition { Height = CellHeight });
        //                var categoryRow = GetTextLayout(true, prev_category);
        //                tableGridLayout.Children.Add(categoryRow, 0, index);
        //                index++;
        //            }
        //            tableGridLayout.RowDefinitions.Add(new RowDefinition { Height = CellHeight });
        //            // Добавление описания
        //            var descr = GetTextLayout(true, columnProperty.DESCRIPTION);
        //            tableGridLayout.Children.Add(descr, 0, index);
        //            for (int j = 0; j < tableRows.Count; j++)
        //            {
        //                if(columnProperty.S_TABLE != null)
        //                {
        //                    if(tableRows[j][i] != DBNull.Value)
        //                    {
        //                        var s_table_result = App.CreateDatabase().Query<S_INFO_TABLES>(String.Format("select * from S_INFO_TABLES where TABLE_NAME='{0}' and ID={1}", 
        //                            columnProperty.S_TABLE, Convert.ToInt32(tableRows[j][i]))).ToList();
        //                        var textview = GetTextLayout(columnProperty.READONLY, s_table_result[0].VALUE);
        //                        tableGridLayout.Children.Add(textview, j + 1, index);
        //                    }
        //                    else
        //                    {
        //                        var textview = GetTextLayout(columnProperty.READONLY, "");
        //                        tableGridLayout.Children.Add(textview, j + 1, index);
        //                    }
        //                }
        //                else
        //                {
        //                    switch (type_properties.Find(x => x.Column_Name == columnProperty.TABLE_COLUMN).Type_str)
        //                    {
        //                        case "text":
        //                            var textview = GetTextLayout(columnProperty.READONLY, tableRows[j][i] != DBNull.Value ? Convert.ToString(tableRows[j][i]) : "");
        //                            tableGridLayout.Children.Add(textview, j + 1, index);
        //                            break;
        //                        case "int":
        //                        case "smallint":
        //                            var numview = GetNumericLayout(columnProperty.READONLY, tableRows[j][i] != DBNull.Value ? Convert.ToInt32(tableRows[j][i]) : Int32.MinValue);
        //                            tableGridLayout.Children.Add(numview, j + 1, index);
        //                            break;
        //                        case "double":
        //                            var doubleview = GetDoubleLayout(columnProperty.READONLY, tableRows[j][i] != DBNull.Value ? Convert.ToDouble(tableRows[j][i]) : double.MinValue, columnProperty.FORMAT);
        //                            tableGridLayout.Children.Add(doubleview, j + 1, index);
        //                            break;
        //                        case "long":
        //                            var dateview = GetDateLayout(columnProperty.READONLY, tableRows[j][i] != DBNull.Value ? Convert.ToInt64(tableRows[j][i]) : long.MinValue);
        //                            tableGridLayout.Children.Add(dateview, j + 1, index);
        //                            break;
        //                    }
        //                }
        //            }
        //            index++;
        //        }
        //    }
        //    Root.Children.Add(tableGridLayout);
        //}


        /// <summary>
        ///  Создание текстового контрола
        /// </summary>
        /// <param name="isReadOnly"></param>
        /// <param name="info"></param>
        /// <returns></returns>
        public StackLayout GetTextLayout(bool isReadOnly, string info)
        {
            var layout = new StackLayout
            {
                Orientation = StackOrientation.Horizontal,
                BackgroundColor = Color.White
            };
            if (isReadOnly)
            {
	            var label = new Label
	            {
		            VerticalOptions = LayoutOptions.FillAndExpand,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            FontSize = 12,
		            HorizontalTextAlignment = TextAlignment.Start,
		            VerticalTextAlignment = TextAlignment.Center,
		            Margin = new Thickness(5, 2, 2, 5),
		            Text = info
	            };
	            layout.Children.Add(label);
            }
            else
            {
	            var editor = new Editor
	            {
		            VerticalOptions = LayoutOptions.FillAndExpand,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            FontSize = 12,
		            Margin = new Thickness(5, 2, 2, 5),
		            Text = info
	            };
	            layout.Children.Add(editor);
                //Entry entry = new Entry
                //{
                //    VerticalOptions = LayoutOptions.Center,
                //    HorizontalOptions = LayoutOptions.FillAndExpand,
                //    FontSize = 12,
                //    Margin = 10
                //};
                //entry.Text = info;
                //layout.Children.Add(entry);
            }
            return layout;
        }

        /// <summary>
        /// Класс для изменения информации в текстовом контроле
        /// </summary>
        /// <param name="view"></param>
        /// <param name="info"></param>
        public void SetTextInfo(View view, string info)
        {
	        switch (view)
	        {
		        case Label _:
			        ((Label) view).Text = info;
			        break;
		        case Editor _:
			        ((Editor) view).Text = info;
			        break;
	        }
        }

        /// <summary>
        /// Метод для создания контрола с числовой инфой
        /// </summary>
        /// <param name="isReadOnly"></param>
        /// <param name="info"></param>
        /// <returns></returns>
        public StackLayout GetNumericLayout(bool isReadOnly, int info)
        {
            var layout = new StackLayout
            {
                Orientation = StackOrientation.Horizontal,
                BackgroundColor = Color.White
            };
            if (isReadOnly)
            {
	            var label = new Label
	            {
		            VerticalOptions = LayoutOptions.FillAndExpand,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            FontSize = 12,
		            Margin = new Thickness(5, 2, 2, 5)
	            };
	            if (info != int.MinValue)
                    label.Text = Convert.ToString(info);
                layout.Children.Add(label);
            }
            else
            {
	            var entry = new Entry
	            {
		            VerticalOptions = LayoutOptions.Center,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            Keyboard = Keyboard.Numeric,
		            FontSize = 12,
		            Margin = new Thickness(5, 2, 2, 5),
	            };
	            if (info != int.MinValue)
                    entry.Text = Convert.ToString(info);
                layout.Children.Add(entry);
            }
            return layout;
        }

        /// <summary>
        /// изменение инфы в числовом контроле
        /// </summary>
        /// <param name="view"></param>
        /// <param name="info"></param>
        public void SetNumericInfo(View view, int info)
        {
	        switch (view)
	        {
		        case Label label:
			        label.Text = info != int.MinValue ? Convert.ToString(info) : "";
			        break;
		        case Entry label:
			        label.Text = info != int.MinValue ? Convert.ToString(info) : "";
			        break;
	        }
        }

        /// <summary>
        /// Метод создания контрола с данными формата double
        /// </summary>
        /// <param name="isReadOnly"></param>
        /// <param name="info"></param>
        /// <param name="format">Формат данных (количество цифр после запятой)</param>
        /// <returns></returns>
        public StackLayout GetDoubleLayout(bool isReadOnly, double info, string format)
        {
            var layout = new StackLayout
            {
                Orientation = StackOrientation.Horizontal,
                BackgroundColor = Color.White
            };
            if (isReadOnly)
            {
	            var label = new Label
	            {
		            VerticalOptions = LayoutOptions.FillAndExpand,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            FontSize = 12,
		            VerticalTextAlignment = TextAlignment.Start,
		            HorizontalTextAlignment = TextAlignment.Center,
		            Margin = new Thickness(5, 2, 2, 5)
	            };
	            if (Math.Abs(info - double.MinValue) > CommonStaffUtils.DoubleTolerance)
                    label.Text = info.ToString(format ?? "F2");
                layout.Children.Add(label);
            }
            else
            {
	            var entry = new Entry
	            {
		            VerticalOptions = LayoutOptions.Center,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            Keyboard = Keyboard.Numeric,
		            FontSize = 12,
		            HorizontalTextAlignment = TextAlignment.Start,
		            Margin = new Thickness(5, 2, 2, 5)
	            };
	            if (Math.Abs(info - double.MinValue) > CommonStaffUtils.DoubleTolerance)
                    entry.Text = info.ToString(format ?? "F2");
                layout.Children.Add(entry);
            }
            return layout;
        }

        /// <summary>
        /// Изменение инфы в контроле с данными типа double
        /// </summary>
        /// <param name="view"></param>
        /// <param name="info"></param>
        /// <param name="format"></param>
        public void SetDoubleInfo(View view, double info, string format)
        {
	        switch (view)
	        {
		        case Label label:
			        label.Text = Math.Abs(info - double.MinValue) > CommonStaffUtils.DoubleTolerance ? info.ToString(format ?? "F2") : "";
			        break;
		        case Entry label:
			        label.Text = Math.Abs(info - double.MinValue) > CommonStaffUtils.DoubleTolerance ? info.ToString(format ?? "F2") : "";
			        break;
	        }
        }

        public StackLayout GetDateLayout(bool isReadOnly, long info)
        {
            var layout = new StackLayout
            {
                Orientation = StackOrientation.Horizontal,
                BackgroundColor = Color.White
            };
            if (isReadOnly)
            {
	            var label = new Label
	            {
		            VerticalOptions = LayoutOptions.FillAndExpand,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            FontSize = 12,
		            Margin = new Thickness(5, 2, 2, 2)
	            };
	            if (info != long.MinValue)
                    label.Text = Convert.ToString(new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(info).ToString("dd/MM/yyyy"));
                layout.Children.Add(label);
            }
            else
            {
	            var entry = new Entry
	            {
		            VerticalOptions = LayoutOptions.Center,
		            HorizontalOptions = LayoutOptions.FillAndExpand,
		            Keyboard = Keyboard.Numeric,
		            FontSize = 12,
		            Margin = new Thickness(5, 2, 2, 2)
	            };
	            if (info != long.MinValue)
                    entry.Text = Convert.ToString(new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(info).ToString("dd/MM/yyyy"));
                layout.Children.Add(entry);
            }
            return layout;
        }

        public void SetDateInfo(View view, long info)
        {
	        switch (view)
	        {
		        case Label label:
			        label.Text = info != long.MinValue
				        ? Convert.ToString(new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(info)
					        .ToString("dd/MM/yyyy"))
				        : "";
			        break;
		        case Entry label:
			        label.Text = info != long.MinValue
				        ? Convert.ToString(new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(info)
					        .ToString("dd/MM/yyyy"))
				        : "";
			        break;
	        }
        }

        public StackLayout GetSwitchLayout(bool isReadOnly, bool switched)
        {
            var layout = new StackLayout
            {
                Orientation = StackOrientation.Vertical,
                BackgroundColor = Color.White,
            };
	        var switcher = new Switch
	        {
		        VerticalOptions = LayoutOptions.FillAndExpand,
		        HorizontalOptions = LayoutOptions.Center,
		        IsToggled = switched,
		        IsEnabled = !isReadOnly,
	        };
	        layout.Children.Add(switcher);
            return layout;
        }

        public void SetSwitchInfo(View view, bool info)
        {
            ((Switch) view).IsToggled = info;
        }
    }
}
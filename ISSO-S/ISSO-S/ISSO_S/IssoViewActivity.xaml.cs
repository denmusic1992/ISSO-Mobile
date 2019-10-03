using CommonClassesLibrary;
using Plugin.Geolocator;
using Plugin.Geolocator.Abstractions;
using Plugin.Permissions.Abstractions;
using SQLite;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class IssoViewActivity
    {
        /// <summary>
        /// Номер ИССО
        /// </summary>
        private readonly int _cIsso;
        /// <summary>
        /// Координата x для ИССО
        /// </summary>
        private readonly double _issoLatitude;
        /// <summary>
        /// Координата y для ИССО
        /// </summary>
        private readonly double _issoLongitude;
        /// <summary>
        /// Текущая позиция x
        /// </summary>
        private double _currentLatitude;
        /// <summary>
        /// Текущая позиция y
        /// </summary>
        private double _currentLongitude;
        /// <summary>
        /// Длина ИССО
        /// </summary>
        private readonly double _length;
        /// <summary>
        /// Экспертная оценка ОТС ИССО
        /// </summary>
        private readonly string _expertRating;
        /// <summary>
        /// Дата последнего рейтинга
        /// </summary>
        private string _dateRating;
        /// <summary>
        /// Текущий рейтинг ИССО
        /// </summary>
        private int _currentRating;
        /// <summary>
        /// Переменная для подключения к БД
        /// </summary>
        private readonly SQLiteConnection _connection = App.CreateDatabase();
        /// <summary>
        /// Имеющиеся ОТС
        /// </summary>
        private ObservableCollection<TableRow> _rows;
        /// <summary>
        /// Текущее местоположение
        /// </summary>
        private Position _currentPosition;
        /// <summary>
        /// Максимальная погрешность для добавления записи
        /// </summary>
        private static readonly int MaximumAccuracy = 30;
        

        public IssoViewActivity(int cIsso)
        {
            _cIsso = cIsso;
            InitializeComponent();
            BindingContext = this;
            // Инициализация заголовка
            Title = $"ИССО №{cIsso}";
            // Заполняем общую информацию по ИССО
            var info = _connection.Query<I_ISSO>("select * from I_ISSO where C_ISSO=?", cIsso).ToList()[0];

            var addNewOtc = new ToolbarItem
            {
                Command = new Command((sender) => AddNewRating()),
                Icon = CommonStaffUtils.GetFilePath("new_rem_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "new_rem_light.png")*/,
                Priority = 0,
                Order = ToolbarItemOrder.Primary
            };
            ToolbarItems.Add(addNewOtc);
            _issoLatitude = info.LATITUDE;
            _issoLongitude = info.LONGITUDE;
            textViewDescription.Text = info.FULLNAME;
            _length = info.LENGTH;
            if (info.N_OTC_EXP != null)
                _expertRating = $"Экспертная оценка технического состояния: {info.N_OTC_EXP}\n";
            status.Text = "Расстояние до объекта, м: \nПогрешность GPS, м:";
            _currentLatitude = 0; _currentLongitude = 0;
            RequestLocationUpdates();
            //NewRefreshRatings();

            // Убираем сепаратор в iOS
            issoLVHistory.SeparatorVisibility = Device.RuntimePlatform == Device.Android ? SeparatorVisibility.Default : SeparatorVisibility.None;
        }

        /// <summary>
        /// Проверка наличия GPS
        /// </summary>
        /// <returns>Возвращает есть/нет GPS</returns>
        public bool IsLocationAvailable()
        {
            if (!CrossGeolocator.IsSupported)
                return false;

            return CrossGeolocator.Current.IsGeolocationAvailable;
        }

        /// <summary>
        /// Метод получения текущих координат
        /// </summary>
        public async void RequestLocationUpdates()
        {
            var hasPermission = await CommonStaffUtils.CheckPermissions(Permission.Location);
            if (!hasPermission)
                return;
            CrossGeolocator.Current.PositionChanged += Current_PositionChanged;
            CrossGeolocator.Current.PositionError += Current_PositionError;
            await CrossGeolocator.Current.StartListeningAsync(TimeSpan.FromSeconds(1), 5);
        }

        /// <summary>
        /// Handler для ошибки при получении координат
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Current_PositionError(object sender, PositionErrorEventArgs e)
        {
            DisplayAlert("Ошибка", "По непонятным причинам GPS не работает на вашем устройстве", "Ну ок :(");
        }

        /// <summary>
        /// Handler при изменении текущих координат
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Current_PositionChanged(object sender, PositionEventArgs e)
        {
            _currentPosition = e.Position;
            _currentLatitude = e.Position.Latitude;
            _currentLongitude = e.Position.Longitude;
            status.Text =
	            $"Расстояние до объекта, м: {(Math.Abs(_currentLatitude) < CommonStaffUtils.DoubleTolerance ? "[недоступно]" : $"{CommonStaffUtils.GetDistance(_issoLatitude, _issoLongitude, _currentLatitude, _currentLongitude, _length):F1}")}\nПогрешность GPS, м: {(Math.Abs(_currentLatitude) < CommonStaffUtils.DoubleTolerance ? "[недоступно]" : $"{e.Position.Accuracy:F1}")}";
            var dist = CommonStaffUtils.GetDistance(_issoLatitude, _issoLongitude, _currentLatitude, _currentLongitude, _length);
            imgStatus.Source = dist > (100 + e.Position.Accuracy)
	            ? new FileImageSource
	            {
		            File =
			            CommonStaffUtils.GetFilePath(
				            "marker_noway.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "marker_noway.png")*/
	            }
	            : new FileImageSource()
	            {
		            File =
			            CommonStaffUtils.GetFilePath(
				            "marker_ahead.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "marker_ahead.png")*/
	            };
        }

        public async void OnDelete(object sender, EventArgs e)
        {
	        if (!(((MenuItem)sender).CommandParameter is TableRow selectedItem)) return;
	        var selectedRow = _connection.Query<RATING>("select * from RATING where C_ISSO=? and RATINGDATE=?", _cIsso, selectedItem.DateMonth).ToList()[0];
	        if(selectedRow.SYNC)
	        {
		        await DisplayAlert("Удаление невозможно!", "Вы не можете удалить эту оценку, так как она уже была синхронизирована с сервером", "OK");
	        }
	        else
	        {
		        var answer = await DisplayAlert("Подтверждение удаления",
			        $"Оценка, созданная {selectedItem.Date} будет удалена. Продолжить?", "Да", "Нет");
		        if (answer)
		        {
			        DeleteFromDatabase(selectedItem);
		        }
	        }
        }

        /// <summary>
        /// Метод удаления 
        /// </summary>
        private void DeleteFromDatabase(TableRow row)
        {
            // Удаление записи из таблицы RATING
            _connection.Execute("delete from RATING where C_ISSO=? and RATINGDATE=?", _cIsso, row.DateMonth);
            // Удаление фотографий изтаблицы PHOTOS_RATING
            var photos = _connection.Query<PHOTOS_RATING>("select * from PHOTOS_RATING where C_ISSO=? and RATINGDATE=?", _cIsso, row.DateMonth).ToList();
            foreach(PHOTOS_RATING photo in photos)
            {
                if (System.IO.File.Exists(photo.PHOTOPATH))
                    System.IO.File.Delete(photo.PHOTOPATH);
            }
            // Удаление записи из таблицы PHOTOS_RATING
            _connection.Execute("delete from PHOTOS_RATING where C_ISSO=? and RATINGDATE=?", _cIsso, row.DateMonth);
            DependencyService.Get<IMessage>().ShortAlert("Данные удалены");
            NewRefreshRatings();
        }

        /// <summary>
        /// Метод для получения предыдущих ОТС
        /// </summary>
        public void GetRows()
        {
            _currentRating = 0;
            _rows = new ObservableCollection<TableRow>();
            var ratings = _connection.Query<RATING>("Select * from RATING where C_ISSO = ? order by RATINGDATE DESC", _cIsso).ToList();
            foreach (RATING rating in ratings)
            {
                _rows.Add(new TableRow(rating.RATINGS, new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(rating.RATINGDATE + rating.OFFSET).ToString("dd/MM/yyyy"),
                    rating.RATINGDATE, rating.RATINGDATEEDIT, !rating.COMMENTS.Equals("") && !rating.COMMENTS.Equals(" "), rating.CHECKOUTOFPLAN));
                _currentRating += rating.CURRENTRATING;
            }
            if (_rows.Count > 0)
            {
                tvAllRating.Text =
	                $"{_expertRating}Суммарное ухудшение с момента последнего освидетельствования в баллах: {-_currentRating}";
                tvNoRemWorks.IsVisible = false;
            }
            else
            {
                tvAllRating.Text = "Для данного сооружения пока нет рейтинга";
                tvNoRemWorks.Text = "Для данного ИССО не было создано ни одного рейтинга";
                tvNoRemWorks.IsVisible = true;
            }
        }

        public void NewRefreshRatings()
        {
            GetRows();
            var cursor = _connection.Query<RATING>("Select RATINGDATE, OFFSET from RATING where C_ISSO=? order by RATINGDATE DESC limit 1", _cIsso).ToList();
            if (cursor.Count > 0)
            {
                _dateRating = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(cursor[0].RATINGDATE + cursor[0].OFFSET).ToString("dd/MM/yyyy");
                issoLVHistory.ItemsSource = _rows;
            }
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();
            NewRefreshRatings();
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();
            CrossGeolocator.Current.PositionChanged -= Current_PositionChanged;
            CrossGeolocator.Current.PositionError -= Current_PositionError;
            CrossGeolocator.Current.StopListeningAsync();
        }

        private bool CheckGps()
        {
            if (_currentPosition == null)
            {
                DisplayAlert("", "Нет данных о Вашем местоположении", "OK");
                return false;
            }
            if (_currentPosition.Accuracy > MaximumAccuracy)
            {
                DisplayAlert("",
	                $"Недопустимая точность положения. Текущая погрешность : {_currentPosition.Accuracy:F1} м.", "OK");
                return false;
            }

            double dist = CommonStaffUtils.GetDistance(_issoLatitude, _issoLongitude, _currentPosition.Latitude, _currentPosition.Longitude, _length);
            if( dist > (100 + _currentPosition.Accuracy))
            {
                DisplayAlert("", "Вы должны находиться на этом сооружении, чтобы иметь возможность вносить сведения", "OK");
                return false;
            }
            return true;
        }

        private void AddNewRating()
        {
            //if (!CheckGPS()) return;
            //if(!CheckTimeOfLastRating())
            if(true)
            {
                var dateMonth = _currentPosition.Timestamp.ToUnixTimeMilliseconds();
                var page = new AddNewRating(_cIsso, _currentRating, 0, TypeNewRating.IsNew, _currentPosition, dateMonth, true);
                Navigation.PushAsync(page);
            }
            else
            {
                DisplayAlert("", "Вы не можете вносить более одной оценки ситуации в день для текущего сооружения", "OK");
            }

        }

        private bool CheckTimeOfLastRating()
        {
            var currentDate = DateTime.Now.ToString("dd/MM/yyyy");
            return currentDate.Equals(_dateRating);
        }

        private void IssoLVHistory_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            var index = ((ObservableCollection<TableRow>) issoLVHistory.ItemsSource).IndexOf(e.Item as TableRow);
            var table = _connection.Query<RATING>("select * from RATING where C_ISSO=? and RATINGDATE=?", _cIsso, _rows[index].DateMonth).ToList()[0];
            AddNewRating page;
            if (table.SYNC)
                page = new AddNewRating(_cIsso, _currentRating, table.CURRENTRATING, TypeNewRating.IsPreviewed, _currentPosition, table.RATINGDATE, false);
            else
            {
                bool canAddPhotos = false;
                if(_currentPosition != null)
                {
                    double distance = CommonStaffUtils.GetDistance(_issoLatitude, _issoLongitude, _currentPosition.Latitude, _currentPosition.Longitude, _length);
                    if(distance < 100 + _currentPosition.Accuracy)
                    {
                        canAddPhotos = true;
                    }
                }
                page = new AddNewRating(_cIsso, _currentRating, table.CURRENTRATING, TypeNewRating.IsEditable, _currentPosition, table.RATINGDATE, canAddPhotos);
            }
            Navigation.PushAsync(page);
            ((ListView)sender).SelectedItem = null;
        }
    }

    /// <summary>
    /// Класс для определения ОТС
    /// </summary>
    public class TableRow
    {
        /// <summary>
        /// Последний рейтинг
        /// </summary>
        public int LastRating { get; set; }
        /// <summary>
        /// Дата отформатированная
        /// </summary>
        public string Date { get; set; }
        /// <summary>
        /// Дата создания
        /// </summary>
        public long DateMonth { get; set; }
        /// <summary>
        /// Дата редактирования
        /// </summary>
        public long DateEdit { get; set; }
        /// <summary>
        /// Есть ли комментарии
        /// </summary>
        public bool HasComments { get; set; }
        /// <summary>
        /// Необходимость внеплановой проверки
        /// </summary>
        public string CheckOut { get; set; } = "";

        public FileImageSource ImageForRating { get; set; }
        public FileImageSource ImageForComment { get; set; }

        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="lastRating">Последний рейтинг</param>
        /// <param name="date">Отформатированная дата</param>
        /// <param name="dateMonth">Дата создания</param>
        /// <param name="dateEdit">Дата редактирования</param>
        /// <param name="hasComments">Есть ли комментарии</param>
        /// <param name="checkOut">Необходимость внеплановой оценки</param>
        public TableRow(int lastRating, string date, long dateMonth, long dateEdit, bool hasComments, bool checkOut)
        {
            LastRating = lastRating;
            Date = date;
            DateMonth = dateMonth;
            DateEdit = dateEdit;
            HasComments = hasComments;
            if (checkOut)
                CheckOut = "Необходима ОТС";
            switch(lastRating)
            {
                case 20:
                    ImageForRating = new FileImageSource { File = "draw_1.png" };
                    break;
                case 30:
                    ImageForRating = new FileImageSource { File = "draw_2.png" };
                    break;
                case 40:
                    ImageForRating = new FileImageSource { File = "draw_3.png" };
                    break;
                case 50:
                    ImageForRating = new FileImageSource { File = "draw_4.png" };
                    break;
                case 60:
                    ImageForRating = new FileImageSource { File = "draw_5.png" };
                    break;
                case 70:
                    ImageForRating = new FileImageSource { File = "draw_6.png" };
                    break;
                default:
                    ImageForRating = new FileImageSource { File = "draw_0.png" };
                    break;
            }

            ImageForComment = new FileImageSource { File = hasComments ? "comments_light.png" : "no_comments_light.png" };

            }
    }
}
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using Plugin.Geolocator.Abstractions;
using Plugin.Media;
using Plugin.Permissions;
using Plugin.Permissions.Abstractions;
using SQLite;
using Stormlion.PhotoBrowser;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using ISSO_S.iOS.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static CommonClassesLibrary.DBHelper;

namespace ISSO_S
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AddNewRating
    {
        /// <summary>
        /// Номер ИССО
        /// </summary>
        private readonly int _cIsso;
        /// <summary>
        /// Весь рейтинг ОТС по данному ИССО
        /// </summary>
        private readonly int _allRating;
        /// <summary>
        /// Последний рейтинг ОТС по данному ИССО
        /// </summary>
        private int _lastRating;
        /// <summary>
        /// Тип записи
        /// </summary>
        private readonly TypeNewRating _typeOfRating;
        /// <summary>
        /// Текущее положение пользователя
        /// </summary>
        private readonly Position _geoPosition;

	    /// <summary>
        /// Дата при редактирования или просмотра
        /// </summary>
        private readonly long _dateMonth;
        /// <summary>
        /// Переменная для подключения к БД
        /// </summary>
        private readonly SQLiteConnection _connection = App.CreateDatabase();
        /// <summary>
        /// Массив фотографий
        /// </summary>
        private readonly ObservableCollection<PhotoInfo> _photos = new ObservableCollection<PhotoInfo>();

	    /// <summary>
        /// Все ли загрузилось
        /// </summary>
        private readonly bool _isLaunched;
        /// <summary>
        /// Словарь соответствия ОТС и названий
        /// </summary>
        public Dictionary<Otc, string> OtcVariables = new Dictionary<Otc, string>
        {
            { Otc.NotChanged, Otc.NotChanged.GetDescription() },
            { Otc.SlightlyWorse, Otc.SlightlyWorse.GetDescription() },
            { Otc.Worse, Otc.Worse.GetDescription() },
            { Otc.SignificantlyWorse, Otc.SignificantlyWorse.GetDescription() },
            { Otc.Crash, Otc.Crash.GetDescription() },
            { Otc.Improved, Otc.Improved.GetDescription() }
        };

        /// <summary>
        /// Словарь Соответствующих картинок для ОТС
        /// </summary>
        public Dictionary<Otc, FileImageSource> Drawables = new Dictionary<Otc, FileImageSource>
        {
            { Otc.NotChanged, new FileImageSource() { File = "draw_1.png" } },
            { Otc.SlightlyWorse, new FileImageSource() { File = "draw_2.png" } },
            { Otc.Worse, new FileImageSource() { File = "draw_3.png" } },
            { Otc.SignificantlyWorse, new FileImageSource() { File = "draw_4.png" } },
            { Otc.Crash, new FileImageSource() { File = "draw_6.png" } },
            { Otc.Improved, new FileImageSource() { File = "draw_5.png" } }
        };


	    /// <summary>
	    /// Конструктор создания новой записи текущего состояния
	    /// </summary>
	    /// <param name="cIsso">Номер ИССО</param>
	    /// <param name="allRating">Итоговый рейтинг</param>
	    /// <param name="lastRating">Последний рейтинг</param>
	    /// <param name="typeOfRating">Тип рейтинга</param>
	    /// <param name="geoPosition">Последние координаты</param>
	    /// <param name="dateMonth">Дата</param>
	    /// <param name="canAddPhotos"></param>
	    public AddNewRating (int cIsso, int allRating, int lastRating, TypeNewRating typeOfRating, Position geoPosition, long dateMonth, bool canAddPhotos)
        {
            _cIsso = cIsso;
            _lastRating = lastRating;
            _geoPosition = geoPosition;
            _typeOfRating = typeOfRating;
            _dateMonth = dateMonth;
	        // Добавление кнопок меню
            //if(TypeOfRating != TypeNewRating.IsPreviewed)
            //{
            //    ToolbarItem ItemSave = new ToolbarItem()
            //    {
            //        Command = new Command(() => SaveToDatabase()),
            //        Icon = String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "save_light.png"),
            //        Priority = 1,
            //        Order = ToolbarItemOrder.Primary
            //    };
            //    ToolbarItems.Add(ItemSave);
            //}
            //ItemCamera.Command = new Command(() => TakePicture());
            

            // Настройка выпадающего списка
            // Если суммарный рейтинг меньше 1, то убираем степень улучшения

            InitializeComponent();
            // Обработка нажатия "назад"
            CustomBackButtonAction += SaveToDatabase;
            if (!canAddPhotos)
            {
                AddPhotoButton.IsEnabled = false;
                AddPhotoButton.IsVisible = false;
            }
                
            foreach (var otc in OtcVariables.Values)
            {
                pick_otc.Items.Add(otc);
            }
            pick_otc.Title = "Выберите степень ухудшения/улучшения:";
            pick_otc.SelectedIndexChanged += Pick_otc_SelectedIndexChanged;
            img_otc.Source = Drawables[Otc.NotChanged];
            // если не можем редактировать, то получаем сведения по геолокации, и соответственно последнее значение рейтинга
            // иначе получаем предыдущий рейтинг путем вычитания последнего значения из общего рейтинга
            switch (typeOfRating)
            {
                case TypeNewRating.IsNew:
                    _allRating = allRating;
                    if (_allRating > -1)
                    {
                        OtcVariables.Remove(Otc.Improved);
                        Drawables.Remove(Otc.Improved);
                        pick_otc.Items.Remove("Улучшение");
                    }
                    Title = "ИССО №" + cIsso + ". Добавление рейтинга";
                    //Comments.Text = "";
                    pick_otc.SelectedIndex = 0;
                    break;
                case TypeNewRating.IsPreviewed:
                    _allRating = allRating - lastRating;
                    if (_allRating > -1)
                    {
                        OtcVariables.Remove(Otc.Improved);
                        Drawables.Remove(Otc.Improved);
                        pick_otc.Items.Remove("Улучшение");
                    }
                    Title = "ИССО №" + cIsso + ". Просмотр оценки";
                    BindContent();
                    // отключение всех контролов
                    switchOTC.IsEnabled = false;
                    SeekBar.IsEnabled = false;
                    pick_otc.IsEnabled = false;
                    Comments.IsEnabled = false;
                    break;
                case TypeNewRating.IsEditable:
                    _allRating = allRating - lastRating;
                    if (_allRating > -1)
                    {
                        OtcVariables.Remove(Otc.Improved);
                        Drawables.Remove(Otc.Improved);
                        pick_otc.Items.Remove("Улучшение");
                    }
                    Title = "ИССО №" + cIsso + ". Редактирование оценки";
                    BindContent();
                    break;
            }

	        _isLaunched = true;
            ListViewPhotos.IsVisible = _photos.Count > 0;
            // Тестовый набор для фотографий
            //ObservableCollection<PhotoInfo> photos = new ObservableCollection<PhotoInfo>();
            //photos.Add(new PhotoInfo(new FileImageSource() { File = "launcher.png" }, "Здесь есть коммент"));
            //ListViewPhotos.ItemsSource = photos;
            if(typeOfRating == TypeNewRating.IsPreviewed)
            {
                AddPhotoButton.IsEnabled = false;
                AddPhotoButton.IsVisible = false;
            }
            AddPhotoButton.Image = new FileImageSource() { File = CommonStaffUtils.GetFilePath("camera_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "camera_light.png")*/ };

            ListViewPhotos.SeparatorVisibility = Device.RuntimePlatform == Device.Android ? SeparatorVisibility.Default : SeparatorVisibility.None;
        }

        /// <summary>
        /// Обработка нажатия системной кнопки "назад"
        /// </summary>
        public void OnNavigationBackButtonPressed()
        {
            SaveToDatabase();
        }

        /// <summary>
        /// Заполнение контента
        /// </summary>
        public void BindContent()
        {
            var table = _connection.Query<RATING>("select * from RATING where C_ISSO=? and RATINGDATE=?", _cIsso, _dateMonth).ToList()[0];
            Comments.Text = table.COMMENTS;
            switchOTC.IsToggled = table.CHECKOUTOFPLAN;
            switch (_lastRating)
            {
                case 0:
                    pick_otc.SelectedIndex = 0;
                    break;
                case -1:
                    pick_otc.SelectedIndex = 1;
                    break;
                case -2:
                    pick_otc.SelectedIndex = 2;
                    break;
                case -3:
                    pick_otc.SelectedIndex = 3;
                    break;
                case -10:
                    pick_otc.SelectedIndex = 4;
                    break;
                default:
                    pick_otc.SelectedIndex = 5;
                    break;
            }
            // Получаем имеющиеся фотографии
            var photoTable = _connection.Query<PHOTOS_RATING>("Select * from PHOTOS_RATING where C_ISSO=? and RATINGDATE=?", _cIsso, _dateMonth).ToList();
            foreach (var photo in photoTable)
            {
                _photos.Add(new PhotoInfo(photo.C_ISSO, photo.RATINGDATE, photo.PHOTOPATH, photo.PHOTODATE, new FileImageSource { File = Path.Combine(App.PathToPhoto, photo.PHOTOPATH) }, 
                    photo.COMMENT, _typeOfRating != TypeNewRating.IsPreviewed));
            }
            ListViewPhotos.ItemsSource = _photos;
        }

        /// <summary>
        /// Выбор типа оценки
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Pick_otc_SelectedIndexChanged(object sender, EventArgs e)
        {
	        var picker = (Picker)sender;
            var selectedIndex = picker.SelectedIndex;
            if (selectedIndex != -1)
            {
                //var otc = 
                var otc = EnumsHelper.GetValueFromDescription<Otc>(pick_otc.Items[selectedIndex]);
                img_otc.Source = Drawables[otc];
                switch (selectedIndex)
                {
                    case 0:
                        _lastRating = 0;
                        HideSeekBar();
                        break;
                    case 1:
                        _lastRating = -1;
                        HideSeekBar();
                        break;
                    case 2:
                        _lastRating = -2;
                        HideSeekBar();
                        break;
                    case 3:
                        _lastRating = -3;
                        HideSeekBar();
                        break;
                    case 4:
                        _lastRating = -10;
                        HideSeekBar();
                        break;
                    case 5:
                        if (_allRating < -1)
                        {
                            tvBetter.IsVisible = true;
                            SeekBar.IsVisible = true;
                            SeekBar.Maximum = -_allRating - 1;
                            SeekBar.Minimum = 0;
                            if (_typeOfRating != TypeNewRating.IsNew && _lastRating > 0)
                            {
                                SeekBar.Value = _lastRating - 1;
                            }
                            else
                            {
                                _lastRating = Convert.ToInt32(SeekBar.Value + 1);
                            }
                            if(_typeOfRating != TypeNewRating.IsPreviewed)
                                tvBetter.Text = $"Степень улучшения: (1-{-_allRating}):{_lastRating}";
                            else
                            {
                                tvBetter.Text = $"Степень улучшения: {_lastRating}";
                                SeekBar.IsVisible = false;
                            }
                        }
                        else if (_allRating < 0)
                        {
                            _lastRating = 1;
                        }
                        break;
                }
            }
        }

        /// <summary>
        /// Нажатие на кнопку добавления фотографии
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ImageButton_Clicked(object sender, EventArgs e)
        {
            TakePicture();
        }

        //private void TabbedPage_CurrentPageChanged(object sender, EventArgs e)
        //{
        //    if (this.Children.IndexOf(this.CurrentPage) == 0)
        //    {
        //        ToolbarItems.Remove(ItemCamera);
        //    }
        //    else
        //    {
        //        switch (TypeOfRating)
        //        {
        //            case TypeNewRating.IsNew:
        //                ToolbarItems.Add(ItemCamera);
        //                break;
        //            case TypeNewRating.IsPreviewed:
        //                break;
        //            case TypeNewRating.IsEditable:
        //                ToolbarItems.Add(ItemCamera);
        //                break;
        //        }
        //    }
        //}

/*
        /// <summary>
        /// Показать скролл при "улучшении"
        /// </summary>
        private void InitSeekBar()
        {
            tvBetter.IsVisible = true;
            SeekBar.IsVisible = true;
            SeekBar.Maximum = -_allRating - 1;
            SeekBar.Minimum = 0;
            SeekBar.Value = _typeOfRating == TypeNewRating.IsNew ? SeekBar.Minimum : _lastRating;
            tvBetter.Text = $"Степень улучшения: (1-{-_allRating}):{_lastRating}";
        }
*/

        /// <summary>
        /// Скрыть скролл
        /// </summary>
        private void HideSeekBar()
        {
            SeekBar.IsVisible = false;
            tvBetter.IsVisible = false;
        }

        /// <summary>
        /// Метод для получения фотографии
        /// </summary>
        public async void TakePicture()
        {
	        await CrossMedia.Current.Initialize();
            /** Просмотр разрешений на использование камеры */
            if (!CrossMedia.Current.IsCameraAvailable || !CrossMedia.Current.IsTakePhotoSupported)
            {
                await DisplayAlert(null, "Нет камеры", "OK");
                return;
            }

            var cameraStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Camera);
            var storageStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Storage);

            if (cameraStatus != PermissionStatus.Granted || storageStatus != PermissionStatus.Granted)
            {
                var results = await CrossPermissions.Current.RequestPermissionsAsync(Permission.Camera, Permission.Storage);
                cameraStatus = results[Permission.Camera];
                storageStatus = results[Permission.Storage];
            }
            /** Если все доступно, то используем камеру */
            if (cameraStatus == PermissionStatus.Granted && storageStatus == PermissionStatus.Granted)
            {
                var file = await CrossMedia.Current.TakePhotoAsync(new Plugin.Media.Abstractions.StoreCameraMediaOptions
                {
                    Directory = "ISSO-S",
                    DefaultCamera = Plugin.Media.Abstractions.CameraDevice.Rear,
                    MaxWidthHeight = 1280,
                    PhotoSize = Plugin.Media.Abstractions.PhotoSize.MaxWidthHeight,
                    Name = $"IMG_{DateTime.Now:yyyyMMdd_HHmmss}.jpg",
                });

                if (file == null)
                    return;
                // Перемещаем файл в удобное для нас место
                var newPath = Path.Combine(App.PathToPhoto, file.Path.Substring(file.Path.LastIndexOf('/') + 1));
                if (!Directory.Exists(App.PathToPhoto))
                    Directory.CreateDirectory(App.PathToPhoto);
                File.Move(file.Path, newPath);

                // Добавление даты в фотографию
                DependencyService.Get<IDateImageInterface>().AddDateToImageSpecific(newPath);

                // Добавляем фотографию в наш список
                var info = new PhotoInfo(_cIsso, _dateMonth, newPath.Substring(newPath.LastIndexOf('/') + 1), 
                    ((long) (DateTime.Now.ToLocalTime() - new DateTime(1970, 1, 1)).TotalMilliseconds / 1000) * 1000, 
                    new FileImageSource() { File = newPath }, "", _typeOfRating != TypeNewRating.IsPreviewed);
                var photosRating = new PHOTOS_RATING()
                {
                    C_ISSO = info.CIsso,
                    COMMENT = info.Comment,
                    PHOTODATE = info.PhotoDate,
                    PHOTOPATH = newPath.Substring(newPath.LastIndexOf('/') + 1),
                    //PHOTOPATH = newPath,
                    RATINGDATE = info.DateRating,
                    SYNC = false
                };
                _connection.InsertOrReplace(photosRating);
                _photos.Add(info);
                ListViewPhotos.ItemsSource = _photos;
                ListViewPhotos.IsVisible = _photos.Count > 0;
            }
            /** Иначе нет разрешений на камеру */
            else
            {
                await DisplayAlert("Нет разрешений", "Невозможно сделать фотографию, т.к. нет разрешения на это действие", "OK");
                //On iOS you may want to send your user to the settings screen.
                //CrossPermissions.Current.OpenAppSettings();
            }
        }

        /// <summary>
        /// Обраотчик нажатий на список
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ListViewPhotos_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            ((ListView)sender).SelectedItem = null;
        }


        /// <summary>
        /// Обработчик нажатий на кнопку FullScreen
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Btn_fullscr_Clicked(object sender, EventArgs e)
        {
            var btn = sender as Image;
            var photoInfo = btn?.BindingContext as PhotoInfo;
            var index = _photos.IndexOf(photoInfo);
            new PhotoBrowser
            {
                Photos = new List<Photo>
                {
                    new Photo
                    {
                        URL = $"file://{App.PathToPhoto}{_photos[index].PhotoPath}",
                        Title = _photos[index].Comment
                    }
                },
                EnableGrid = false
            }.Show();
            //var page = new FullScreenImage(photos[index].PhotoPath, CIsso);
            //image_clicked = true;
            //Navigation.PushAsync(page);
        }

        /// <summary>
        /// Обработчик нажатия на аппаратную кнопку "Назад"
        /// </summary>
        /// <returns></returns>
        protected override bool OnBackButtonPressed()
        {
            SaveToDatabase();
            return true;
        }

        /// <summary>
        /// Метод сохранения данных в БД
        /// </summary>
        private void SaveToDatabase()
        {
            // Проверка, заполнен ли комментарий ОТС
            if (Comments.Text.Equals("") && pick_otc.SelectedIndex != 0)
            {
                DependencyService.Get<IMessage>().ShortAlert("Комментарий к оценке не заполнен");
                return;
            }
            var i = 1;
            foreach(var info in _photos)
            {
                if (info.Comment.Equals(""))
                {
                    DependencyService.Get<IMessage>().ShortAlert("Отсутствует комментарий для фотографии " + i);
                    return;
                }
                i++;
            }
            if (_typeOfRating == TypeNewRating.IsNew)
            {
                var rating = new RATING()
                {
                    C_ISSO = _cIsso,
                    CURRENTRATING = _lastRating,
                    RATINGDATE = _dateMonth,
                    RATINGDATEEDIT = DateTimeOffset.Now.ToUnixTimeMilliseconds(),
                    RATINGS = (int)EnumsHelper.GetValueFromDescription<Otc>(pick_otc.Items[pick_otc.SelectedIndex]),
                    COMMENTS = Comments.Text,
                    LATITUDE_RATING = _geoPosition.Latitude,
                    LONGITUDE_RATING = _geoPosition.Longitude,
                    CHECKOUTOFPLAN = switchOTC.IsToggled,
                    OFFSET = (long)_geoPosition.Timestamp.ToLocalTime().Offset.TotalMilliseconds,
                    SYNC = false
                };
                _connection.InsertOrReplace(rating);
            }
            else if(_typeOfRating == TypeNewRating.IsEditable)
            {
                var update = new RATING()
                {
                    C_ISSO = _cIsso,
                    CURRENTRATING = _lastRating,
                    RATINGDATE = _dateMonth,
                    RATINGDATEEDIT = DateTimeOffset.Now.ToUnixTimeMilliseconds(),
                    RATINGS = (int)EnumsHelper.GetValueFromDescription<Otc>(pick_otc.Items[pick_otc.SelectedIndex]),
                    COMMENTS = Comments.Text,
                    CHECKOUTOFPLAN = switchOTC.IsToggled,
                    OFFSET = (long)_geoPosition.Timestamp.ToLocalTime().Offset.TotalMilliseconds,
                    SYNC = false
                };
                var sqlUpdate =
	                $"update RATING set CURRENTRATING={update.CURRENTRATING}, RATINGDATEEDIT={update.RATINGDATEEDIT}, RATINGS={update.RATINGS}, COMMENTS='{update.COMMENTS}', CHECKOUTOFPLAN={(update.CHECKOUTOFPLAN ? 1 : 0)}, SYNC={(update.SYNC ? 1 : 0)}" +
	                $" where C_ISSO={update.C_ISSO} and RATINGDATE={update.RATINGDATE}";
                _connection.Execute(sqlUpdate);
                //connection.Update(update);
            }
            if(_typeOfRating != TypeNewRating.IsPreviewed)
            {
                foreach (var info in _photos)
                {
                    var sqlPhotoUpdate =
	                    $"update PHOTOS_RATING set COMMENT='{info.Comment}' where C_ISSO={info.CIsso} and RATINGDATE={info.DateRating} and PHOTODATE={info.PhotoDate}";
                    _connection.Execute(sqlPhotoUpdate);
                }
            }
            // Закрываем это окно
            Navigation.PopAsync();
        }

/*
        /// <summary>
        /// Выход из создания или редактрования без сохранения
        /// </summary>
        private async void QuitWithoutSaving()
        {
            if(_typeOfRating != TypeNewRating.IsPreviewed && _changesApplied)
            {
                var answer = await DisplayAlert("Внимание!", "Вы не сохранили текущую оценку. Вы уверены, что хотите выйти без сохранения?", "Да", "Нет");
                if (answer && _typeOfRating != TypeNewRating.IsEditable)
                {
                    var tablePhotos = _connection.Query<PHOTOS_RATING>("select * from PHOTOS_RATING where C_ISSO=? and RATINGDATE=?", _cIsso, _dateMonth).ToList();
                    if (tablePhotos.Count > 0)
                    {
                        foreach (var photo in tablePhotos)
                        {
                            if (File.Exists(photo.PHOTOPATH))
                                File.Delete(photo.PHOTOPATH);
                        }
                        var result = _connection.Execute("delete from PHOTOS_RATING where C_ISSO=? and RATINGDATE=?", _cIsso, _dateMonth);
                    }
                    await Navigation.PopAsync();
                }
                else if (answer)
                {
                    await Navigation.PopAsync();
                }
            }
            else
            {
                await Navigation.PopAsync();
            }
        }
*/

        /// <summary>
        /// Обработчик удаления фотографии из списка
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Btn_delete_Clicked(object sender, EventArgs e)
        {
            var selectedItem = ((MenuItem)sender).CommandParameter as PhotoInfo;
            ShowCustomDialog(selectedItem);
        }

        private async void ShowCustomDialog(PhotoInfo selectedItem)
        {
            if (_typeOfRating != TypeNewRating.IsPreviewed)
            {
                var index = _photos.IndexOf(selectedItem);
                var photoToDelete = _photos[index];
                var answer = await DisplayAlert("Удаление",
	                $"Вы действительно хотите удалить фотографию оценки ситуации{(photoToDelete.Comment.Equals("") ? "" : $" с комментарием '{photoToDelete.Comment}'")}?", "Да", "Нет");
                if (answer)
                {
                    File.Delete(photoToDelete.PhotoPath);
                    _connection.Execute("delete from PHOTOS_RATING where C_ISSO=? and RATINGDATE=? and PHOTODATE=?", photoToDelete.CIsso, photoToDelete.DateRating, photoToDelete.PhotoDate);
                    _photos.Remove(photoToDelete);
                }
            }
            else
            {
                await DisplayAlert(null, "Данная оценка синхронизирована. Вы не можете удалять фотографии", "OK");
            }
        }

        /// <summary>
        /// ОБработчик переключения внеплановой оценки
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void SwitchOTC_Toggled(object sender, ToggledEventArgs e)
        {
        }
        /// <summary>
        /// Обработчик слайдера по улучшению
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Slider_valueChanged(object sender, ValueChangedEventArgs e)
        {
            if(_isLaunched)
            {
	            var newStep = Math.Round(e.NewValue / 1.0);
                SeekBar.Value = newStep * 1.0;
                _lastRating = Convert.ToInt32(SeekBar.Value + 1);
                tvBetter.Text = $"Степень улучшения: (1-{-_allRating}):{_lastRating}";
            }
        }
        /// <summary>
        /// Обработчик изменения текста комментариев
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Comments_TextChanged(object sender, TextChangedEventArgs e)
        {
        }

/*
        private async void TapGestureRecognizer_Tapped(object sender, EventArgs e)
        {
            var label = sender as Label;
            var photoInfo = label.BindingContext as PhotoInfo;
            var index = _photos.IndexOf(photoInfo);
            var result = await OpenTextInputAlertDialog(_photos[index]);
            _photos[index].Comment = result;
        }
*/

/*
        private async Task<string> OpenTextInputAlertDialog(PhotoInfo info)
        {
            // create the TextInputView
            var inputView = new AlertTextInputPage(info.Comment);

            // create the Transparent Popup Page
            // of type string since we need a string return
            var popup = new InputAlertDialogBase<string>(inputView);
            inputView.CloseButtonEventHandler += (sender, obj) =>
            {
                popup.PageClosedTaskCompletionSource.SetResult(((AlertTextInputPage)sender).TextInputResult);
            };

            // Push the page to Navigation Stack
            await Navigation.PushPopupAsync(popup);

            // await for the user to enter the text input
            var result = await popup.PageClosedTask;

            // Pop the page from Navigation Stack
            await Navigation.PopPopupAsync();

            // return user inserted text value
            return result;
        }
*/

        //private void ImageGrid_Tapped(object sender, EventArgs e)
        //{
        //    image_clicked = true;
        //}
    }

    public enum TypeNewRating
    {
        IsNew = 1,
        IsPreviewed = 2,
        IsEditable = 3
    }

    /// <summary>
    /// Класс фотографий
    /// </summary>
    public class PhotoInfo
    {
        /// <summary>
        /// Номер ИССО
        /// </summary>
        public int CIsso;
        /// <summary>
        /// Дата ОТС
        /// </summary>
        public long DateRating;
        /// <summary>
        /// Путь до фотографии
        /// </summary>
        public string PhotoPath;
        /// <summary>
        /// Дата фотографии
        /// </summary>
        public long PhotoDate;
        /// <summary>
        /// Фотография по ОТС
        /// </summary>
        public FileImageSource Image { get; set; }
        /// <summary>
        /// Комментарий для фотографии
        /// </summary>
        public string Comment { get; set; }
        /// <summary>
        /// Признак того, является ли запись редактируемой
        /// </summary>
        public bool IsPreview { get; set; }

	    /// <summary>
	    /// Конструктор
	    /// </summary>
	    /// <param name="photoDate"></param>
	    /// <param name="image">Фотография по ОТС</param>
	    /// <param name="comment">Комментарий по ОТС</param>
	    /// <param name="cIsso"></param>
	    /// <param name="dateRating"></param>
	    /// <param name="photoPath"></param>
	    /// <param name="isPreview"></param>
	    public PhotoInfo(int cIsso, long dateRating, string photoPath, long photoDate, FileImageSource image, string comment, bool isPreview)
        {
            CIsso = cIsso;
            DateRating = dateRating;
            PhotoPath = photoPath;
            PhotoDate = photoDate;
            Image = image;
            Comment = comment;
            IsPreview = isPreview;
        }
    }
}
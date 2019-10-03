using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Additional_Classes;
using Mono.Data.Sqlite;
using Plugin.Media;
using Plugin.Media.Abstractions;
using Plugin.Permissions;
using Plugin.Permissions.Abstractions;
using Plugin.SpeechRecognition;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Diagnostics;
using System.IO;
using CommonClassesLibrary.PopupPages;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForPhotos
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class AddNewPhotoContentPage
	{
        /// <summary>
        /// Номер ИССО
        /// </summary>
        private int CIsso { get; }

        /// <summary>
        /// Handler на добавление фото
        /// </summary>
        public event EventHandler PhotoAdded;

        /// <summary>
        /// Ссылка на фотографию на устройстве
        /// </summary>
        private MediaFile MediaFile { get; set; }

		/// <summary>
        /// Последний индекс фотографии
        /// </summary>
        private int MaxN { get; }


		public AddNewPhotoContentPage(int cIsso, MediaFile file, int maxN)
		{
			InitializeComponent ();
            CIsso = cIsso;
            MaxN = maxN;
            MediaFile = file;
            UploadingImage.Source = new FileImageSource { File = MediaFile.Path };
            
        }

        private void CancelButton_Clicked(object sender, EventArgs e)
        {
            Navigation.PopModalAsync();
        }

        private async void NewPhotoButton_Clicked(object sender, EventArgs e)
        {
	        MediaFile = await CommonPhotoUtils.TakePhoto();
	        if (MediaFile == null) return;
	        UploadingImage.Source = new FileImageSource {File = MediaFile.Path};
        }

        private async void MicrophonePressed(object sender, EventArgs e)
        {
            var speechStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Speech);
            var microStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Microphone);
            if (speechStatus != PermissionStatus.Granted || microStatus != PermissionStatus.Granted)
            {
                var results = await CrossPermissions.Current.RequestPermissionsAsync(Permission.Speech, Permission.Microphone);
                microStatus = results[Permission.Microphone];
                speechStatus = results[Permission.Speech];
            }

            if (speechStatus == PermissionStatus.Granted && microStatus == PermissionStatus.Granted)
            {
                //listener = CrossSpeechRecognition.Current.ContinuousDictation().Subscribe(
                //    phrase =>
                //    {
                //        EntryComment.Text += phrase;
                //    }, 
                //    completed => 
                //    {
                //        listener.Dispose();
                //    });
                //CrossSpeechRecognition.Current.ContinuousDictation().Subscribe(phrase => { EntryComment.Text += phrase; }, 
                //    () => { CrossSpeechRecognition.Current.ContinuousDictation().Subscribe().Dispose(); });
                CrossSpeechRecognition.Current.ListenUntilPause().Subscribe(phrase => { EntryComment.Text += phrase; }, async() => { Animate_Button(false); await Navigation.PopPopupAsync(); });
                var loadingPopupPage = new LoadingPopupPage("Пожалуйста, говорите!", true);
                Animate_Button(true);
                await Navigation.PushPopupAsync(loadingPopupPage);
            }
            else
            {
				var alertPage = new AlertPopupPage(true, "Распознавание речи невозможно, т.к. недостаточно разрешений для этого.");
	            await Navigation.PushPopupAsync(new CommonPopupPage(alertPage, "Недостаточно разрешений"));
                //await DisplayAlert("Недостаточно разрешений", "Распознавание речи невозможно, т.к. недостаточно разрешений для этого.", "OK");
                //On iOS you may want to send your user to the settings screen.
                //CrossPermissions.Current.OpenAppSettings();
            }
        }

        private async void Animate_Button(bool start)
        {
            await btn_micro.ScaleTo(start ? 1.3 : 1, 100);
            await btn_micro.ScaleTo(start ? 1.2 : 1.1, 100);
            await btn_micro.ScaleTo(start ? 1.3 : 1.0, 100);
        }

        private void MicrophoneReleased(object sender, EventArgs e)
        {
            //if(listener != null)
            //{
            //    listener.Dispose();
            //}
        }

        private async void ApplyPhotoButton_Clicked(object sender, EventArgs e)
        {
            if(EntryComment.Text != null && !EntryComment.Text.Equals(""))
            {
                using (var memoryStream = new MemoryStream())
                {
                    MediaFile.GetStream().CopyTo(memoryStream);
                    MediaFile.Dispose();
                    using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                    {
	                    try
	                    {
		                    connection.Open();
		                    var command = connection.CreateCommand();
		                    command.CommandText =
			                    $"insert into I_FOTO (C_ISSO,N,TITR,FOTO,STATE,FOTO_DATE,ORD, PREVIEW) values ({CIsso}, {(MaxN + 1)}, '{EntryComment.Text}', '{Convert.ToBase64String(memoryStream.ToArray())}', 0, {DateTimeOffset.Now.ToUnixTimeMilliseconds()}, null, null)";
		                    command.CommandTimeout = 30;
		                    command.CommandType = System.Data.CommandType.Text;
		                    command.ExecuteNonQuery();
							command.Dispose();
	                    }
	                    catch (Exception ex)
	                    {
		                    Debug.WriteLine($"Произошла ошибка в БД:\n {ex.Message} \nStackTrace: {ex.StackTrace}");
	                    }
	                    finally
	                    {
		                    connection.Close();
	                    }
                    }
                }
                var pathToDir = Environment.GetFolderPath(Environment.SpecialFolder.Personal) + "/ISSO-I/";
                if (Directory.Exists(pathToDir))
                {
                    DependencyService.Get<ILocalFileProvider>().DeleteFilesFromDir(pathToDir);
                }
                PhotoAdded?.Invoke(this, EventArgs.Empty);
                await Navigation.PopModalAsync();
            }
            else
            {
                DependencyService.Get<IMessage>().ShortAlert("Поле с комментарием не должно быть пустым!");
            }
        }

        public override void Dispose()
        {
            MediaFile.Dispose();
            BindingContext = null;
            Content = null;
        }
    }
}
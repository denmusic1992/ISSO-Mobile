using CommonClassesLibrary;
using Plugin.Permissions;
using Plugin.Permissions.Abstractions;
using Plugin.SpeechRecognition;
using Rg.Plugins.Popup.Extensions;
using System;
using CommonClassesLibrary.PopupPages;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.PopupTypes
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class FindDefectsPopupPage
	{
        private int CIsso { get; }


		public event EventHandler UseTraditionalView;

        public event EventHandler UseSearchView;

		/// <summary>
		/// Для заголовка всплывающего окна
		/// </summary>
		public const string Header = "Ввод нового дефекта";

        /// <summary>
        /// Конструктор
        /// </summary>
		public FindDefectsPopupPage (int cIsso)
		{
            CIsso = cIsso;
			InitializeComponent();
		}

        /// <summary>
        /// Показывать список дефектов
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public void ShowTraditionalPage(object sender, EventArgs e)
        {
            UseTraditionalView?.Invoke(this, EventArgs.Empty);
            Navigation.PopPopupAsync();
        }

        /// <summary>
        /// Показать дефекты в соответствии с фильтром
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="eventArgs"></param>
        public void ShowSearchPage(object sender, EventArgs eventArgs)
        {
            SearchDefect(filter_entry.Text);
            UseSearchView?.Invoke(this, EventArgs.Empty);
            Navigation.PopPopupAsync();
        }

        /// <summary>
        /// Обработчик нажатия кнопки микрофона
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private async void Microphone_Clicked(object sender, EventArgs e)
        {
            // Проверка доступности разрешений на применение действий
            var speechStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Speech);
            var microStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Microphone);
            if (speechStatus != PermissionStatus.Granted || microStatus != PermissionStatus.Granted)
            {
                // В случае с отсутствием таковых идем запрашивать такие разрешения у пользователя
                var results = await CrossPermissions.Current.RequestPermissionsAsync(Permission.Speech, Permission.Microphone);
                microStatus = results[Permission.Microphone];
                speechStatus = results[Permission.Speech];
            }

            // После чего ещё раз проверяем доступность разрешений
            if (speechStatus == PermissionStatus.Granted && microStatus == PermissionStatus.Granted)
            {
                // Инициализация API преобразования речи в текст
                CrossSpeechRecognition.Current.ListenUntilPause().Subscribe(phrase => { filter_entry.Text += phrase; }, async () => { Animate_Button(microphone_button, false); await Navigation.PopPopupAsync(); });
                var loadingPopupPage = new LoadingPopupPage("Пожалуйста, говорите!", true);
                Animate_Button(microphone_button, true);
                await Navigation.PushPopupAsync(loadingPopupPage);
            }
            else
            {
                // Иначе говорим, что разерешений таких нет, увы и ах
	            await Navigation.PushPopupAsync(new CommonPopupPage(
		            new AlertPopupPage(true, "Распознавание речи невозможно, т.к. недостаточно разрешений для этого."),
		            "Недостаточно разрешений"));
                //await Application.Current.MainPage.DisplayAlert("Недостаточно разрешений", "Распознавание речи невозможно, т.к. недостаточно разрешений для этого.", "OK");
                //On iOS you may want to send your user to the settings screen.
                //CrossPermissions.Current.OpenAppSettings();
            }
        }

        /// <summary>
        /// Поиск дефекта в дереве дефектов 
        /// </summary>
        /// <param name="filter"></param>
        private void SearchDefect(string filter)
        {

        }

        /// <summary>
        /// Анимация кнопки распознавания речи
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="start"></param>
        private async void Animate_Button(object sender, bool start)
        {
            await (sender as View).ScaleTo(start ? 1.3 : 1, 100);
            await (sender as View).ScaleTo(start ? 1.2 : 1.1, 100);
            await (sender as View).ScaleTo(start ? 1.3 : 1.0, 100);
        }
    }
}
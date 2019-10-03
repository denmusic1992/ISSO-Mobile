using System;
using Rg.Plugins.Popup.Extensions;
using Rg.Plugins.Popup.Pages;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace CommonClassesLibrary.PopupPages
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class AlertPopupPage : ContentView
	{
		/// <summary>
		/// Обработчик нажатия на отмену
		/// </summary>
		public event EventHandler Cancel;
		/// <summary>
		/// обработчик нажатия на подтверждение
		/// </summary>
		public event EventHandler Confirm;

		/// <summary>
		/// Информационное ли это окно
		/// </summary>
		public bool IsInformative { get; set; }

		public AlertPopupPage(bool isInformative, string textInfo, string cancelText = "Отмена", string confirmText = "Понятно")
		{
			// Это для определения стиля кнопки Ок
			IsInformative = isInformative;

			InitializeComponent();
			//Инициализируем все остальное
			InfoLabel.Text = textInfo;
			ButtonCancel.Text = cancelText;
			ButtonConfirm.Text = confirmText;
			ButtonCancel.Clicked += (s, e) => { Cancel?.Invoke(s, e); };
			ButtonConfirm.Clicked += (s, e) => { Confirm?.Invoke(s, e); };
			Confirm += (s, e) => { Navigation.PopPopupAsync(); };
			Cancel += (s, e) => { Navigation.PopPopupAsync(); };
			// Присваиваем переменные для биндинга
			BindingContext = this;
			// Костыльно в плане паттерна, но лучше, чем писать конвертер True в False :)
			ButtonCancel.IsVisible = !isInformative;
			ButtonConfirm.Style = (Style) (isInformative
				? Application.Current.Resources["ButtonStandardWhite"]
				: Application.Current.Resources["ButtonStandard"]);
		}
	}
}
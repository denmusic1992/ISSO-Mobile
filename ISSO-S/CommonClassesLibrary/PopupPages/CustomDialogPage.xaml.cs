using Rg.Plugins.Popup.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace CommonClassesLibrary
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class CustomDialogPage
	{
        /// <summary>
        /// Обработчик нажатия в списке
        /// </summary>
        public event EventHandler Clicked;
        /// <summary>
        /// Выбранный тип
        /// </summary>
        public int IndexSelected;

		public const string Header = "Тип отображения данных";

        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="types"></param>
		public CustomDialogPage(IEnumerable<ItemType> types)
		{
			InitializeComponent ();
            ListType.ItemsSource = types;
            ListType.ItemTapped += ListType_ItemTapped;
		}

        private void ListType_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            IndexSelected = ((ListType.ItemsSource as ItemType[]) ?? throw new InvalidOperationException()).ToList().IndexOf(((ListView)sender).SelectedItem as ItemType);
            ((ListView)sender).SelectedItem = null;
	        Clicked?.Invoke(this, EventArgs.Empty);
        }

		//protected override void OnSizeAllocated(double width, double height)
		//{
		//	base.OnSizeAllocated(width, height);
		//}

		/// <summary>
		/// Обработчик закрытия всплывающего окна
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="eventArgs"></param>
		private void Button_Close_Clicked(object sender, EventArgs eventArgs)
		{
			Navigation.PopPopupAsync();
		}

        private void OnClose(object sender, EventArgs e)
        {
            PopupNavigation.Instance.PopAsync();
        }
    }

    /// <summary>
    /// Класс инициализации типа
    /// </summary>
    public class ItemType
    {
        /// <summary>
        /// Тип
        /// </summary>
        public string Type { get; set; }
        /// <summary>
        /// Картинка
        /// </summary>
        public FileImageSource Source { get; set; }

        /// <summary>
        /// Конструктор по умолчанию
        /// </summary>
        public ItemType() { }

        /// <summary>
        /// Стандартный конструктор
        /// </summary>
        public ItemType(string type, FileImageSource source)
        {
            Type = type;
            Source = source;
        }
    }

}
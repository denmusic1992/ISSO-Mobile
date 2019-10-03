using Rg.Plugins.Popup.Extensions;
using Rg.Plugins.Popup.Pages;
using Rg.Plugins.Popup.Services;
using System;
using System.Linq;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Autoselect
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class CustomDialogPage : PopupPage
    {
        /// <summary>
        /// Обработчик нажатия в списке
        /// </summary>
        public event EventHandler Clicked;
        /// <summary>
        /// Выбранный тип
        /// </summary>
        public int index_selected;

        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="types"></param>
		public CustomDialogPage(ItemType[] types)
		{
			InitializeComponent ();
            ListType.ItemsSource = types;
            ListType.ItemTapped += ListType_ItemTapped;
		}

        private void ListType_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            index_selected = (ListType.ItemsSource as ItemType[]).ToList().IndexOf(((ListView)sender).SelectedItem as ItemType);
            ((ListView)sender).SelectedItem = null;
            Clicked.Invoke(this, EventArgs.Empty);
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);
            ListType.HeightRequest = Content.Height / 2;
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
        public ItemType(string Type, FileImageSource Source)
        {
            this.Type = Type;
            this.Source = Source;
        }
    }

}
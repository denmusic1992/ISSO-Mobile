using ISSO_I.IssoViewPages;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.PopupTypes
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class DefFilterPopupPage
	{
		/// <summary>
		/// Список выбранных дефектов
		/// </summary>
        private readonly List<DefectFilter> _ais7IssoDefectFilters;

		/// <summary>
		/// Event на применение фильтра по дефектам
		/// </summary>
        public event EventHandler ApplyFilters;

		/// <summary>
		/// 
		/// </summary>
        private bool Tapped { get; set; }

		/// <summary>
		/// Для заголовка окна
		/// </summary>
		public const string Header = "Выбранные фильтры";

		/// <summary>
		/// Конструктор
		/// </summary>
		/// <param name="ais7IssoDefectFilters"></param>
		public DefFilterPopupPage (List<DefectFilter> ais7IssoDefectFilters)
		{
			InitializeComponent ();
            _ais7IssoDefectFilters = ais7IssoDefectFilters.Clone();
            switcher_all.Toggled += Switcher_all_Toggled;
            // Если у нас активированы все дефекты, то включаем свитчер на все дефекты
            if (ais7IssoDefectFilters.Find(x => x.ConstrId == -1 && x.Activated) != null)
            {
                switcher_all.IsToggled = true;
            }
            // Удаляем дефект All, оставляем его только для глобальной переменной
            _ais7IssoDefectFilters.Remove(_ais7IssoDefectFilters.Find(x => x.ConstrId == -1));
            list_view_filter.ItemsSource = new ObservableCollection<DefectFilter>(_ais7IssoDefectFilters);
            list_view_filter.ItemTapped += List_view_filter_ItemTapped;

        }

		/// <summary>
		/// Когда включены все дефекты
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
        private void Switcher_all_Toggled(object sender, ToggledEventArgs e)
        {
            if(switcher_all.IsToggled)
            {
                list_view_filter.IsEnabled = false;
                list_view_filter.BackgroundColor = Color.LightGray;
            }
            else
            {
                list_view_filter.IsEnabled = true;
                list_view_filter.BackgroundColor = Color.Transparent;
            }
        }

        private static void List_view_filter_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            ((ListView)sender).SelectedItem = null;
        }

        private async void button_confirm_Clicked(object sender, EventArgs e)
        {
            if (Tapped) return;
            Tapped = true;
            _ais7IssoDefectFilters.Add(new DefectFilter(-1, "All", switcher_all.IsToggled));
            ApplyFilters?.Invoke(_ais7IssoDefectFilters, EventArgs.Empty);
            await Navigation.PopPopupAsync();
            Tapped = false;
        }
    }

	internal static class ListExtensions
    {
        public static List<T> Clone<T>(this List<T> list) where T: ICloneable
        {
            return list.Select(item => (T)item.Clone()).ToList();
        }
    }
}
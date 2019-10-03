using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using CommonClassesLibrary.ViewModels;
using ISSO_I.PopupTypes.Models;
using Xamarin.Forms;

namespace ISSO_I.PopupTypes.ViewModels
{
    public class MultiselectListViewModel : BaseViewModel, IDisposable
    {
		/// <summary>
		/// Номера конструкций
		/// </summary>
	    private ObservableCollection<MultiselectItem> _items;
	    public ObservableCollection<MultiselectItem> Items
	    {
		    get => _items;
		    set => SetProperty(ref _items, value);
	    }

		/// <summary>
		/// заглушка для выбора всей конструкции
		/// </summary>
	    private bool _flagForAllConstrs;
        

	    /// <summary>
	    /// Идентификатор выбора всей конструкции
	    /// </summary>
	    private bool _allChecked;
	    public bool AllChecked
	    {
		    get => _allChecked;
		    set
		    {
		        if (_allChecked == value) return;
		        _allChecked = value;
                OnPropertyChanged(nameof(AllChecked));
				// Меняем все галочки
			    if (_flagForAllConstrs) return;
			    foreach (var item in Items)
			    {
				    item.IsChecked = value;
			    }
		    }
	    }

	    public MultiselectListViewModel(ObservableCollection<MultiselectItem> items)
	    {
		    Items = items;
			MessagingCenter.Subscribe<string>(this, "CheckingConstrsChanged", interact =>
			{
				if (_flagForAllConstrs) return;
				_flagForAllConstrs = true;
			    AllChecked = Items.Count(item => item.IsChecked) == Items.Count;
				_flagForAllConstrs = false;
			});

			// Здесь назначаем начальное значение, если уже до этого все выбиралось, чтобы и галочка стояла на AllChecked
	        _flagForAllConstrs = true;
		    AllChecked = Items != null && Items.Count(item => item.IsChecked) == Items.Count;
	        _flagForAllConstrs = false;
	    }

	    #region Interface Staff
		

		public void Dispose()
		{
			MessagingCenter.Unsubscribe<string>(this, "CheckingConstrsChanged");
		}

		#endregion

	}
}

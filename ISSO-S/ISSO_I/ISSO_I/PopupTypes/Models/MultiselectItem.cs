using System.ComponentModel;
using System.Runtime.CompilerServices;
using CommonClassesLibrary.ViewModels;
using Xamarin.Forms;

namespace ISSO_I.PopupTypes.Models
{
    public class MultiselectItem : BaseViewModel
    {

	    /// <summary>
	    /// Признак выбранного элемента
	    /// </summary>
	    private bool _isChecked;

	    public bool IsChecked
	    {
		    get => _isChecked;
		    set
		    {
		        SetProperty(ref _isChecked, value);
				MessagingCenter.Send("", "CheckingConstrsChanged");
		    }
	    }

	    /// <summary>
	    /// Текст элемента
	    /// </summary>
	    public string Body { get; set; }

		/// <summary>
		/// Идентификатор конструкции
		/// </summary>
		public short Id { get; set; }
    }
}

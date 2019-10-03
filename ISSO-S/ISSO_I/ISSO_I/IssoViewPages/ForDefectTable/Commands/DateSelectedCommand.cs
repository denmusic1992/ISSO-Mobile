using System;
using System.Windows.Input;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;

namespace ISSO_I.IssoViewPages.ForDefectTable.Commands
{
    public class DateSelectedCommand : ICommand
    {
	    private readonly DateDefectContentViewModel _vm;

	    public event EventHandler CanExecuteChanged;

	    private DateTime _previousDate { get; set; }

	    public DateSelectedCommand(DateDefectContentViewModel vm)
	    {
		    _vm = vm;
	    }

	    public bool CanExecute(object parameter)
	    {
		    return parameter != null;
	    }
	    public void Execute(object parameter)
	    {
		    _vm.SelectedDateChanged(parameter);
	    }
    }
}

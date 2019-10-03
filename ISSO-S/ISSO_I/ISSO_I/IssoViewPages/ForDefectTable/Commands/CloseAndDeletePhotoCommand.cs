using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;

namespace ISSO_I.IssoViewPages.ForDefectTable.Commands
{
    public class CloseAndDeletePhotoCommand : ICommand
    {
	    private readonly AdditionalDefectParametersContentViewModel _vm;

	    public CloseAndDeletePhotoCommand(AdditionalDefectParametersContentViewModel vm)
	    {
		    _vm = vm;
	    }

	    public bool CanExecute(object parameter)
	    {
		    return parameter != null;
	    }

	    public void Execute(object parameter)
	    {
		    _vm.DeletePhotoDefect();
	    }

	    public event EventHandler CanExecuteChanged;
    }
}

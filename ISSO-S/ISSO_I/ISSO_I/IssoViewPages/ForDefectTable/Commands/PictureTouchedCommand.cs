using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;
using FFImageLoading.Forms;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;

namespace ISSO_I.IssoViewPages.ForDefectTable.Commands
{
    public class PictureTouchedCommand : ICommand
    {
	    private readonly AdditionalDefectParametersContentViewModel _vm;
	    public PictureTouchedCommand(AdditionalDefectParametersContentViewModel vm)
	    {
		    _vm = vm;
	    }
	    public bool CanExecute(object parameter)
	    {
		    return parameter != null;
	    }

	    public void Execute(object parameter)
	    {
		    _vm.ShowDefectPicture();
	    }

	    public event EventHandler CanExecuteChanged;
    }
}

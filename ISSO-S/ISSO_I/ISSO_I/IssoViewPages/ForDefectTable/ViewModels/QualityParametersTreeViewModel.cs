using System.ComponentModel;
using System.Runtime.CompilerServices;
using CommonClassesLibrary.ViewModels;
using ISSO_I.IssoViewPages.ForDefectTable.Models;

namespace ISSO_I.IssoViewPages.ForDefectTable.ViewModels
{
    public sealed class QualityParametersTreeViewModel : BaseViewModel
    {

	    //public readonly CreateDefectModel DefectModel;

	    private string _chosenQualityParameter;
	    public string ChosenQualityParameter
	    {
		    get => _chosenQualityParameter;
		    set => SetProperty(ref _chosenQualityParameter, value);
	    }

	    private string _chosenBdrg;
	    public string ChosenBdrg
	    {
		    get => _chosenBdrg;
		    set => SetProperty(ref _chosenBdrg, value);
	    }

	    private bool _missingValue = true;
	    public bool MissingValue
	    {
		    get => _missingValue;
		    set => SetProperty(ref _missingValue, value);
	    }

	   // private Ais7DefectParamValue _selectedQualDefectParameter;

	   // public Ais7DefectParamValue SelectedQualDefectParameter
	   // {
		  //  get => _selectedQualDefectParameter;
		  //  set
		  //  {
			 //   if (_selectedQualDefectParameter == value) return;
			 //   _selectedQualDefectParameter = value;
			 //   DefectModel.SelectedQualDefectParameter = value;
				//OnPropertyChanged(nameof(SelectedQualDefectParameter));
		  //  }
	   // }
	    public QualityParametersTreeViewModel() { }


	    public QualityParametersTreeViewModel(CreateDefectModel defectModel)
	    {
		    #region Часть закоментированная, может понадобиться в будущем

			// Здесь подписываемся на изменение ноды на которую нажали
			//MessagingCenter.Subscribe<string>(this, "ItemChanged", sender => { MissingValue = false; });
			#endregion
		}
    }
}

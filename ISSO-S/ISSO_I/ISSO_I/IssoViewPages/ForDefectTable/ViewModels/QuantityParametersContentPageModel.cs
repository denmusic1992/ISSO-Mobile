using System.Linq;
using System.Windows.Input;
using CommonClassesLibrary.ViewModels;
using ISSO_I.IssoViewPages.ForDefectTable.Commands;
using ISSO_I.IssoViewPages.ForDefectTable.Models;

namespace ISSO_I.IssoViewPages.ForDefectTable.ViewModels
{
    public class QuantityParametersContentPageModel : BaseViewModel
    {
	    private readonly CreateDefectModel _defectModel;

	    private string _parameterName;

	    public string ParameterName
	    {
		    get
		    {
			    var obj = _defectModel.DefectParameters.FirstOrDefault(item => !item.IsQual);
			    _parameterName = $"{obj?.Name}({obj?.SnUnit})";
			    return _parameterName;
		    }
		    set => SetProperty(ref _parameterName, value);
	    }

	    private string _bdrg;

	    public string Bdrg
	    {
		    get => _bdrg;
		    set => SetProperty(ref _bdrg, value);
	    }

	    /// <summary>
	    /// Метод изменения данных БДРГ
	    /// </summary>
	    protected internal void DefineParameterBdrg(double parameter)
	    {
		    //DependencyService.Get<IMessage>().ShortAlert("Edited");
		    foreach (var defectParam in _defectModel.DefectParameters.Where(item => !item.IsQual))
		    {
			    var start = !defectParam.ValueStart.Equals(double.NaN) ? defectParam.ValueStart : double.MinValue;
			    var end = !defectParam.ValueEnd.Equals(double.NaN) ? defectParam.ValueEnd : double.MaxValue;
			    if (start <= parameter && parameter < end)
			    {
				    Bdrg = $"Б{defectParam.B}, Д{defectParam.D},Р{defectParam.R}{(defectParam.G ? ", Г" : "")}";
				    defectParam.Value = parameter;
				    _defectModel.SelectedQuanDefectParameter = defectParam;
				    return;
			    }
		    }
		    //Bdrg = "Вне диапазона значений";
		    Bdrg = "";
		    _defectModel.SelectedQuanDefectParameter = null;
	    }

	    /// <summary>
	    /// Команда изменения параметра
	    /// </summary>
	    public ICommand ParameterEdited { protected set; get; }


	    public QuantityParametersContentPageModel(CreateDefectModel defectModel)
	    {
		    _defectModel = defectModel;
			
		    ParameterEdited = new ParameterEditedCommand(this);
	    }

    }
}

using System;
using System.Linq;
using CommonClassesLibrary.ViewModels;
using ISSO_I.IssoViewPages.ForDefectTable.Models;

namespace ISSO_I.IssoViewPages.ForDefectTable.ViewModels
{
	public class SummaryDefectContentViewModel : BaseViewModel
	{

		/// <summary>
		/// Есть ли качественные параметры
		/// </summary>
		public bool HasQualParams => _defectModel.DefectParameters.Any(item => item.IsQual);

		/// <summary>
		/// Есть ли количественные параметры
		/// </summary>
		public bool HasQuanParams => _defectModel.DefectParameters.Any(item => !item.IsQual);

		/// <summary>
		/// Если есть и количественный, и качественный параметр
		/// </summary>
		public bool HasBothParams => HasQualParams && HasQuanParams;

		/// <summary>
		/// Модель вводимого дефекта
		/// </summary>
		private readonly CreateDefectModel _defectModel;

		/// <summary>
		/// Строка, которая будет выводиться, если дефект выбран на всем сооружении
		/// </summary>
		private const string AllConstrs = "В целом на сооружение";

		private const string NoInfo = "[Информация отсутствует]";

		private const string NoPhoto = "[Фотография дефекта отсутствует]";

		private const string HasPhoto = "Есть фотография дефекта";

		/// <summary>
		/// Выбранный качественный параметр
		/// </summary>
		private string _chosenQualityParam;
		public string ChosenQualityParam
		{
			get => _chosenQualityParam;
			set => SetProperty(ref _chosenQualityParam, value);
		}

		/// <summary>
		/// Выбранный количественный параметр
		/// </summary>
		private string _chosenQuantityParam;
		public string ChosenQuantityParam
		{
			get => _chosenQuantityParam;
			set => SetProperty(ref _chosenQuantityParam, value);
		}

		/// <summary>
		/// Выбранная дата ввода дефекта
		/// </summary>
		private string _chosenDate;
		public string ChosenDate
		{
			get => _chosenDate;
			set => SetProperty(ref _chosenDate, value);
		}

		/// <summary>
		/// Итоговое БДРГ
		/// </summary>
		private string _summaryDefectParam;
		public string SummaryDefectParam
		{
			get => _summaryDefectParam;
			set => SetProperty(ref _summaryDefectParam, value);
		}

		/// <summary>
		/// Введенная локализация для дефекта
		/// </summary>
		private string _chosenLocalization;
		public string ChosenLocalization
		{
			get => _chosenLocalization ?? NoInfo;
			set => SetProperty(ref _chosenLocalization, value);
		}

		/// <summary>
		/// Введенная доп информация по дефекту
		/// </summary>
		private string _chosenAdvancedInfo;
		public string ChosenAdvancedInfo
		{
			get => _chosenAdvancedInfo ?? NoInfo;
			set => SetProperty(ref _chosenAdvancedInfo, value);
		}

		/// <summary>
		/// Идентификатор добавленной фотографии
		/// </summary>
		private string _photoAdded;
		public string PhotoAdded
		{
			get => _photoAdded ?? NoPhoto;
			set => SetProperty(ref _photoAdded, value);
		}

		/// <summary>
		/// Выбранные номера конструкций
		/// </summary>
		private string _chosenConstr;
		public string ChosenConstr
		{
			get => _chosenConstr;
			set => SetProperty(ref _chosenConstr, value);
		}


		/// <summary>
		/// Признак наличия конструкций
		/// </summary>
		public bool IsForConstrs => _defectModel.ParentTreeNode.ConstrList(_defectModel.CIsso).Length > 0;

		/// <summary>
		/// Метод для установление качественного параметра
		/// </summary>
		public void SetQualityParameter()
		{
			ChosenQualityParam = _defectModel.SelectedQualDefectParameter.GetBdrg;
			if (HasBothParams)
				SetSummaryParameter();
		}

		/// <summary>
		/// Метод для установления количественного параметра
		/// </summary>
		public void SetQuantityParameter()
		{

			ChosenQuantityParam = _defectModel.SelectedQuanDefectParameter?.GetBdrg ?? "";
			if (HasBothParams)
				SetSummaryParameter();
		}

		/// <summary>
		/// Метод для установления качественного параметра
		/// </summary>
		public void SetAdvancedInfo()
		{
			ChosenLocalization = _defectModel.Localization ?? NoInfo;
			ChosenAdvancedInfo = _defectModel.AdvancedInfo ?? NoInfo;
		}

		/// <summary>
		/// Метод для установления выбранной даты
		/// </summary>
		public void SetChosenDate()
		{
			ChosenDate = _defectModel.CreationDate?.ToString("MM/dd/yyyy");
		}

		/// <summary>
		/// Метод для установления выбранных конструкций
		/// </summary>
		public void SetSelectedConstr()
		{
			//Если для всего сооружения
			if (_defectModel.AllChecked)
			{
				ChosenConstr = AllConstrs;
			}
			// Если у нас вообще для конструкций, то пишем выбранные конструкции
			else if (IsForConstrs && _defectModel.NConstr != null)
			{
				ChosenConstr = string.Join(", ",
					_defectModel.NConstr.Where(item => item.IsChecked).Select(item => item.Body).ToList());
			}
			else
			{
				ChosenConstr = "";
			}
		}

		public void SetPhotoAdded(bool added)
		{
			PhotoAdded = added ? HasPhoto : NoPhoto;
		}

		/// <summary>
		/// Итоговый уровень БДРГ
		/// </summary>
		private void SetSummaryParameter()
		{
			var bRes = Math.Max(_defectModel.SelectedQualDefectParameter.B, _defectModel.SelectedQuanDefectParameter?.B ?? 0);
			var dRes = Math.Max(_defectModel.SelectedQualDefectParameter.D, _defectModel.SelectedQuanDefectParameter?.D ?? 0);
			var rRes = Math.Max(_defectModel.SelectedQualDefectParameter.R, _defectModel.SelectedQuanDefectParameter?.R ?? 0);
			var gRes = _defectModel.SelectedQualDefectParameter.G || (_defectModel.SelectedQuanDefectParameter?.G ?? false);
			SummaryDefectParam = $"Б{bRes}, Д{dRes}, Р{rRes}, {(gRes ? "Г" : "")}";
		}

		/// <summary>
		/// Конструктор
		/// </summary>
		/// <param name="defectModel"></param>
		public SummaryDefectContentViewModel(CreateDefectModel defectModel)
		{
			_defectModel = defectModel;
		}
		
	}
}

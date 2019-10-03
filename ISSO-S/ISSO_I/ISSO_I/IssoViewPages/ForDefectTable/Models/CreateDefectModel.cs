using ISSO_I.PopupTypes.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using CommonClassesLibrary.ViewModels;
using Xamarin.Forms;

namespace ISSO_I.IssoViewPages.ForDefectTable.Models
{
    public class CreateDefectModel : BaseViewModel
    {
		/// <summary>
		/// Номер ИССО
		/// </summary>
		public int CIsso { get; set; }

		/// <summary>
		///  Идентификатор группы конструкций
		/// </summary>
		public int CGrConstr { get; set; }
		
		/// <summary>
		/// Порядковый номер дефекта
		/// </summary>
		public int CDefect { get; set; }

		/// <summary>
		/// Наименование дефекта
		/// </summary>
		public string NDefect { get; set; }

		/// <summary>
		/// Описание дефекта
		/// </summary>
		public string Description { get; set; }

		/// <summary>
		/// Качественные параметры
		/// </summary>
		public List<Ais7DefectParamValue> DefectParameters { get; set; }

		/// <summary>
		/// К какому конструктиву относится вводимый дефект
		/// </summary>
		public Ais7IssoDefectsTreeNode ParentTreeNode { get; set; }

		///// <summary>
		///// Количественные параметры
		///// </summary>
		//public List<DefectParameter> QuantityParameters { get; set; }

		private Ais7DefectParamValue _selectedQualDefectParameter;

		/// <summary>
		/// Выбранный качественный параметр дефекта
		/// </summary>
		public Ais7DefectParamValue SelectedQualDefectParameter
		{
			get => _selectedQualDefectParameter;
			set
			{
				SetProperty(ref _selectedQualDefectParameter, value);
				MessagingCenter.Send(new Tuple<DefectType, bool>(DefectType.Quality, true), "InteractionChanged");
			}
		}

		/// <summary>
		/// Выбранный количественный параметр дефекта
		/// </summary>
		private Ais7DefectParamValue _selectedQuanDefectParameter;

		public Ais7DefectParamValue SelectedQuanDefectParameter
		{
			get => _selectedQuanDefectParameter;
			set
			{
				SetProperty(ref _selectedQuanDefectParameter, value);
				MessagingCenter.Send(new Tuple<DefectType, bool>(DefectType.Quantity, _selectedQuanDefectParameter != null), "InteractionChanged");
			}
		}

		/// <summary>
		/// Функция инициализации экземпляра
		/// </summary>
	 //   public virtual void Init(CreateDefectModel model)
		//{
		//	DefectParameters = model.DefectParameters;
		//	CDefect = model.CDefect;
		//	CGrConstr = model.CGrConstr;
		//	CIsso = model.CIsso;
		//	Description = model.Description;
		//	NDefect = model.NDefect;
		//	SelectedQualDefectParameter = model.SelectedQualDefectParameter;
		//	SelectedQuanDefectParameter = model.SelectedQuanDefectParameter;
		//}

	    /// <summary>
	    /// Доп. параметры дефекта
	    /// </summary>
	    private readonly Ais7AdvancedDefect _additionalDefectParams = new Ais7AdvancedDefect();

		/// <summary>
		/// Локализация
		/// </summary>
	    public string Localization
	    {
		    get => _additionalDefectParams.L_DEF;
		    set
		    {
			    if (_additionalDefectParams.L_DEF == value) return;
			    _additionalDefectParams.L_DEF = value;
				OnPropertyChanged(nameof(Localization));
		    }
	    }

		/// <summary>
		/// Доп инфа
		/// </summary>
	    public string AdvancedInfo
	    {
		    get => _additionalDefectParams.N_REM;
		    set
		    {
			    if (_additionalDefectParams.N_REM == value) return;
			    _additionalDefectParams.N_REM = value;
				OnPropertyChanged(nameof(AdvancedInfo));
		    }
	    }


	    /// <summary>
	    /// Дата обнаружения
	    /// </summary>
	    private DateTime? _creationDate;

	    public DateTime? CreationDate
	    {
		    get => _creationDate;
		    set => SetProperty(ref _creationDate, value);
	    }


		/// <summary>
		/// Выбранные номера конструкции
		/// </summary>
	    private ObservableCollection<MultiselectItem> _nconstr;

	    public ObservableCollection<MultiselectItem> NConstr
	    {
		    get => _nconstr;
		    set => SetProperty(ref _nconstr, value);
	    }

	    /// <summary>
	    /// Если на всем сооружении
	    /// </summary>
	    private bool _allChecked;
	    public bool AllChecked
	    {
		    get => _allChecked;
		    set => SetProperty(ref _allChecked, value);
	    }


	    private string _defectPhotoPath;
	    /// <summary>
	    /// Фото дефекта (путь до фотографии)
	    /// </summary>
	    public string DefectPhotoPath
	    {
		    get => _defectPhotoPath;
		    set => SetProperty(ref _defectPhotoPath, value);
	    }

		/// <summary>
		/// Итоговая безопасность
		/// </summary>
		public short B => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.B :
		    SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.B :
		    Math.Max(SelectedQuanDefectParameter.B, SelectedQualDefectParameter.B);
	    /// <summary>
	    /// Итоговая безопасность (экспертная)
	    /// </summary>
		public short B1 => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.B1 :
			SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.B1 :
			Math.Max(SelectedQuanDefectParameter.B1, SelectedQualDefectParameter.B1);
		/// <summary>
		/// Итоговая долговечность
		/// </summary>
		public short D => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.D :
			SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.D :
			Math.Max(SelectedQuanDefectParameter.D, SelectedQualDefectParameter.D);
		/// <summary>
		/// Итоговая долговечность (экспертная)
		/// </summary>
	    public short D1 => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.D1 :
		    SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.D1 :
		    Math.Max(SelectedQuanDefectParameter.D1, SelectedQualDefectParameter.D1);
		/// <summary>
		/// Итоговая ремонтопригодность
		/// </summary>
	    public short R => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.R :
		    SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.R :
		    Math.Max(SelectedQuanDefectParameter.R, SelectedQualDefectParameter.R);
		/// <summary>
		/// Итоговая ремонтопригодность (экспертная)
		/// </summary>
	    public short R1 => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.R1 :
		    SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.R1 :
		    Math.Max(SelectedQuanDefectParameter.R1, SelectedQualDefectParameter.R1);
		/// <summary>
		/// Итоговая грузоподьемность
		/// </summary>
		public bool G => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.G :
			SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.G :
			SelectedQuanDefectParameter.G || SelectedQualDefectParameter.G;
		/// <summary>
		/// Итоговая грузоподьемность (экспертная)
		/// </summary>
	    public bool G1 => SelectedQuanDefectParameter == null ? SelectedQualDefectParameter.G1 :
		    SelectedQualDefectParameter == null ? SelectedQuanDefectParameter.G1 :
		    SelectedQuanDefectParameter.G1 || SelectedQualDefectParameter.G1;
    }

	public enum DefectType
	{
		Quality = 1,
		Quantity = 2,
		Advanced = 3,
		Date = 4
	}
}

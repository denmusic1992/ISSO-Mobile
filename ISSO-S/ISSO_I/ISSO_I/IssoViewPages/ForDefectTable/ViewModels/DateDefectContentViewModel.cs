using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows.Input;
using CommonClassesLibrary.ViewModels;
using ISSO_I.IssoViewPages.ForDefectTable.Commands;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.PopupTypes.Models;
using Xamarin.Forms;

namespace ISSO_I.IssoViewPages.ForDefectTable.ViewModels
{
	public class DateDefectContentViewModel : BaseViewModel
	{

		private readonly CreateDefectModel _defectModel;

		public ICommand DateChanged { get; protected set; }

		private DateTime? _selectedDate;

		public DateTime? SelectedDate
		{
			get => _selectedDate;
			set => SetProperty(ref _selectedDate, value);
		}

		/// <summary>
		/// Перечень конструкций
		/// </summary>
		private ObservableCollection<MultiselectItem> _nConstr;
		public ObservableCollection<MultiselectItem> Nconstr
		{
			get
			{
				if (_nConstr != null) return _nConstr;
				var selectedConstrs = _defectModel.ParentTreeNode.ConstrList(_defectModel.CIsso)?.ToList();
				// Наименование и идентификаторы конструкций
				var constrs = selectedConstrs?.Select(item => new Tuple<string, short>(item.ItemName, item.ItemId)).ToList() ?? new List<Tuple<string, short>>();
				if (constrs.Count <= 0) return new ObservableCollection<MultiselectItem>();
				var nConstrs = new ObservableCollection<MultiselectItem>();
				foreach (var constr in constrs)
				{
					nConstrs.Add(new MultiselectItem() { Body = constr.Item1, Id  = constr.Item2, IsChecked = false });
				}

				return nConstrs;
			}
			set => SetProperty(ref _nConstr, value);
		}

		public bool IsForConstrs => Nconstr.Count > 0;

		/// <summary>
		/// Индикатор по всему сооружению
		/// </summary>
		private bool _allConstrs;
		public bool AllConstrs
		{
			get => _allConstrs;
			set
			{
				if (_allConstrs == value) return;
				_allConstrs = value;
				if(_defectModel.CreationDate != null)
					SetInteractionHasChanged(AllConstrs && _defectModel.CreationDate != null);
				OnPropertyChanged(nameof(AllConstrs));
				_defectModel.AllChecked = value;
			}
		}


		/// <summary>
		/// Переменная для отключения галочки "на все сооружение"
		/// </summary>
		private bool _constrsesNotChosen = true;
		public bool ConstrsNotChosen
		{
			get => _constrsesNotChosen;
			set => SetProperty(ref _constrsesNotChosen, value);
		}

		private string _nconstrsChosen = "[Выбранные конструкции отсутсвуют]";
		public string NconstrsChosen
		{
			get => _nconstrsChosen;
			set => SetProperty(ref _nconstrsChosen, value);
		}


		protected internal void ChangeConstrs(ObservableCollection<MultiselectItem> newConstrs)
		{
			// Присваиваем новые значения по выбранным конструкциям
			Nconstr = newConstrs;
			var constlist = string.Join(", ", Nconstr.Where(item => item.IsChecked).Select(item => item.Body));

			NconstrsChosen = !constlist.Equals("") ? constlist : "[Выбранные конструкции отсутсвуют]";
			ConstrsNotChosen = constlist.Equals("");
			SetInteractionHasChanged(!constlist.Equals("") && _defectModel.CreationDate != null);
			_defectModel.NConstr = new ObservableCollection<MultiselectItem>(newConstrs.Where(item => item.IsChecked));
		}

		public DateDefectContentViewModel(CreateDefectModel defectModel)
		{
			_defectModel = defectModel;
			SelectedDate = null;
			DateChanged = new DateSelectedCommand(this);
		}

		protected internal void SelectedDateChanged(object date)
		{
			if (_defectModel.CreationDate == Convert.ToDateTime(date))
			{
				_defectModel.CreationDate = null;
				SelectedDate = null;
				SetInteractionHasChanged(false);
				return;
			}
			_defectModel.CreationDate = Convert.ToDateTime(date);
			SelectedDate = _defectModel.CreationDate;
			if (IsForConstrs)
			{
				if (AllConstrs)
				{
					SetInteractionHasChanged(true);
					return;
				}

				if (Nconstr.Count > 0)
				{
					if (Nconstr.Any(i => i.IsChecked))
					{
						SetInteractionHasChanged(true);
						return;
					}
					SetInteractionHasChanged(false);
				}

				SetInteractionHasChanged(false);
			}
			else SetInteractionHasChanged(true);
		}

		public void SetInteractionHasChanged(bool success)
		{
			MessagingCenter.Send(new Tuple<DefectType, bool>(DefectType.Date, success), "InteractionChanged");
		}
	}
}

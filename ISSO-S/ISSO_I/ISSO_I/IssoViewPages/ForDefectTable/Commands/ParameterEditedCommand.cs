using System;
using System.Windows.Input;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;
using Xamarin.Forms;

namespace ISSO_I.IssoViewPages.ForDefectTable.Commands
{
	public class ParameterEditedCommand : ICommand
	{
		/// <inheritdoc />
		/// <summary>
		/// </summary>
		public event EventHandler CanExecuteChanged;

		private readonly QuantityParametersContentPageModel _viewModel;

		public ParameterEditedCommand(QuantityParametersContentPageModel viewModel)
		{
			_viewModel = viewModel;
		}

		/// <inheritdoc />
		/// <summary>
		/// </summary>
		/// <param name="parameter"></param>
		/// <returns></returns>
		public bool CanExecute(object parameter)
		{
			//var newValue = ((TextChangedEventArgs) parameter).NewTextValue
			//return !Convert.ToString(parameter).Equals(string.Empty);
			return true;
		}

		/// <inheritdoc />
		/// <summary>
		/// </summary>
		/// <param name="parameter"></param>
		public void Execute(object parameter)
		{
			//Проверяем, нулевой ли параметр. Если нулевой, то ставим кнопку недоступной
			if(!Convert.ToString(parameter).Equals(string.Empty))
				_viewModel.DefineParameterBdrg(Convert.ToDouble(parameter));
			else
				MessagingCenter.Send(new Tuple<DefectType, bool>(DefectType.Quantity, false), "InteractionChanged");
		}

	}
}

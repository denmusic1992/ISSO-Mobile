using System;
using System.Linq;
using CommonClassesLibrary;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForDefectTable.Views
{
	/// <inheritdoc />
	/// <summary>
	/// Контрол для ввода главных параметров дефекта
	/// </summary>
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class QuantityParametersContentPage
	{


		public QuantityParametersContentPage(CreateDefectModel defectModel)
		{
			InitializeComponent ();
			BindingContext = new QuantityParametersContentPageModel(defectModel);
			CreateFrameInfo(defectModel);
		}


		private void CreateFrameInfo(CreateDefectModel defectModel)
		{
			var quantityParams = defectModel.DefectParameters.Where(item => !item.IsQual).ToList();
			//Добавляем количественные параметры
			foreach (var parameter in quantityParams)
			{
				GridQuantityInfo.RowDefinitions.Add(new RowDefinition { Height = GridLength.Star });
				GridQuantityInfo.Children.Add(GetTextLayout(parameter.GetValueRange), 0,quantityParams.IndexOf(parameter));
				GridQuantityInfo.Children.Add(GetTextLayout(parameter.GetBdrg), 1, quantityParams.IndexOf(parameter));
			}
		}

		/// <summary>
		/// Создание TextView
		/// </summary>
		/// <param name="info"></param>
		/// <returns></returns>
		public StackLayout GetTextLayout(string info)
		{
			var layout = new StackLayout
			{
				Orientation = StackOrientation.Horizontal,
				Padding = new Thickness(10, 5, 10, 5)
			};
			var label = new Label
			{
				VerticalOptions = LayoutOptions.FillAndExpand,
				HorizontalOptions = LayoutOptions.FillAndExpand,
				HorizontalTextAlignment = TextAlignment.Center,
				VerticalTextAlignment = TextAlignment.Center,
				Text = info,
				Style = (Style)Application.Current.Resources["LabelInTable"]
			};
			layout.Children.Add(label);
			return layout;
		}

		#region Unused old code
		//private void CreateDefectParameters()
		//{

		//	//Добавляем качественные параметры
		//	foreach (var parameter in Vm.QualityDefectParameters)
		//	{
		//		GridQualityParameters.RowDefinitions.Add(new RowDefinition { Height = GridLength.Star });
		//		// 0 индекс - шапка таблицы
		//		GridQualityParameters.Children.Add(GetTextLayout(parameter.Name), 0, Vm.QualityDefectParameters.IndexOf(parameter) + 1);
		//		GridQualityParameters.Children.Add(GetTextLayout(""), 1, Vm.QualityDefectParameters.IndexOf(parameter) + 1);
		//		GridQualityParameters.Children.Add(GetTextLayout(parameter.GetBdrg), 2, Vm.QualityDefectParameters.IndexOf(parameter) + 1);
		//	}

		//	//Добавляем количественные параметры
		//	foreach (var parameter in Vm.QuantityDefectParameters)
		//	{
		//		GridQuantityParameters.RowDefinitions.Add(new RowDefinition { Height = GridLength.Star });
		//		// 0 индекс - шапка таблицы
		//		GridQuantityParameters.Children.Add(GetTextLayout($"{parameter.Name}({parameter.SnUnit})"), 0, Vm.QuantityDefectParameters.IndexOf(parameter) + 1);
		//		GridQuantityParameters.Children.Add(GetTextLayout(parameter.GetValueRange), 1, Vm.QuantityDefectParameters.IndexOf(parameter) + 1);
		//		GridQuantityParameters.Children.Add(GetTextLayout(parameter.GetBdrg), 2, Vm.QuantityDefectParameters.IndexOf(parameter) + 1);
		//	}
		//}
		#endregion
	}
}
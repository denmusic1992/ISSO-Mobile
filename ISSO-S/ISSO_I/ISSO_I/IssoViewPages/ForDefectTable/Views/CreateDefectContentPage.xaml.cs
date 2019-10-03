using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using CommonClassesLibrary.PopupPages;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.ViewModels;
using ISSO_I.IssoViewPages.ForDefectTable.Views;
using Plugin.DeviceOrientation;
using Rg.Plugins.Popup.Extensions;
using Xamarin.Forms;
using Xamarin.Forms.Internals;
using Xamarin.Forms.Xaml;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class CreateDefectContentPage
	{
		//CreateDefectModel DefectModel { get; set; }
		private readonly CreateDefectViewModel _vm;

		private readonly int _indexAdvanced;
		private readonly int _indexSummary;
		private readonly int _indexQuality;
		private readonly int _indexQuantity;

		private readonly List<bool> _interactors;

		public CreateDefectContentPage(int cIsso, int cGrConstr, string description, IReadOnlyList<object> args)
		{
			InitializeComponent();
			Title = $"{description}. Выбор параметров дефекта.";
			var defectModel = new CreateDefectModel
			{
				CIsso = cIsso,
				CGrConstr = cGrConstr,
				Description = description,
				CDefect = (int)args[0],
				NDefect = (string)args[1],
				ParentTreeNode = ((Ais7DefectItem) args[2]).Parent
			};
			defectModel.DefectParameters = Ais7DefectParamValue.ReturnDefectParameters(defectModel.CDefect, defectModel.CGrConstr);
			BindingContext = _vm = new CreateDefectViewModel(defectModel);

			// Прописываем, какие индексы у страниц для удобства
			_indexAdvanced = CarouselView.ItemsSource.Cast<ContentView>().IndexOf(page => page is AdditionalDefectParametersContentView);
			_indexSummary = CarouselView.ItemsSource.Cast<ContentView>().IndexOf(page => page is SummaryDefectContentView);
			_indexQuality = CarouselView.ItemsSource.Cast<ContentView>().IndexOf(page => page is QualityParametersTreeView);
			_indexQuantity = CarouselView.ItemsSource.Cast<ContentView>().IndexOf(page => page is QuantityParametersContentPage);

			// Последнему разрешаем работу с кнопкой с самого начала
			_interactors = new List<bool>(new bool[_indexSummary + 1]) {[_indexSummary] = true};
			MessagingCenter.Subscribe<Tuple<DefectType, bool>>(this, "InteractionChanged", (interact) => InteractionChanged(interact, EventArgs.Empty));
			ButtonForward.IsEnabled = false;
		}

		protected override void OnAppearing()
		{
			base.OnAppearing();
			// Зафиксируем ориентацию экрана, чтобы не происходила всякая дичь с интерфейсом
			CrossDeviceOrientation.Current.LockOrientation(CrossDeviceOrientation.Current.CurrentOrientation);
		}

		protected override void OnDisappearing()
		{
			base.OnDisappearing();
			// Отпустим это дело
			CrossDeviceOrientation.Current.UnlockOrientation();
		}

		private void InteractionChanged(object sender, EventArgs args)
		{
			var receiver = (Tuple<DefectType, bool>)sender;
			switch (receiver.Item1)
			{
				case DefectType.Quality:
					_interactors[_indexQuality] = receiver.Item2;
					break;
				case DefectType.Quantity:
					_interactors[_indexQuantity] = receiver.Item2;
					break;
				case DefectType.Advanced:
					_interactors[_indexAdvanced] = receiver.Item2;
					break;
				case DefectType.Date:
					_interactors[_indexAdvanced + 1] = receiver.Item2;
					break;
			}
			//_interactors[receiver.Item1] = receiver.Item2;
			ChangeButtonInteraction();
		}

		public override void Dispose()
		{
			MessagingCenter.Unsubscribe<Tuple<DefectType, bool>>(this, "InteractionChanged");
		}

		private void ButtonBack_OnClicked(object sender, EventArgs e)
		{
			// Навигация по мастеру назад
			CarouselView.Position = CarouselView.Position == 0 ? CarouselView.Position : CarouselView.Position - 1;
			ButtonBack.IsVisible = CarouselView.Position != 0;
			ButtonForward.Text = CarouselView.Position == _indexAdvanced ? !_interactors[CarouselView.Position] ? "Пропустить" : "Далее" : "Далее";
			ButtonForward.Style = _interactors[CarouselView.Position] ? (Style)Application.Current.Resources["ButtonStandard"]
				: (Style)Application.Current.Resources["ButtonStandardCancel"];
			ButtonForward.IsEnabled = CarouselView.Position == _indexAdvanced || _interactors[CarouselView.Position];
		}


		private void ButtonForward_OnClicked(object sender, EventArgs e)
		{
			// Если дошли до конца и нажали на Добавить дефект, то вызываем окно предупередительное и добавляем собсна
			if (CarouselView.Position == _indexSummary)
			{
				var addPopupPage = new AlertPopupPage(false, "Вы уверены, что хотите добавить новый дефект?", confirmText: "Добавить");
				// При нажатии на Да добавляем и вырубаем окно
				addPopupPage.Confirm += (o, args) => 
				{
					var popupPage = new LoadingPopupPage("Добавление дефекта в БД...", true);
					Navigation.PushPopupAsync(popupPage);
					Task.Factory.StartNew(() =>
					{
						if(_vm.AddDefectToDatabase())
							Device.BeginInvokeOnMainThread(() =>
							{
								DoneWithDefect();
								Navigation.PopPopupAsync();
							});
						else
						Device.BeginInvokeOnMainThread(() =>
						{
							DependencyService.Get<IMessage>().ShortAlert("Невозможно создать дефект. Ошибка при добавлении.");
							Navigation.PopPopupAsync();
						});
					});
				};
				Navigation.PushPopupAsync(new CommonPopupPage(addPopupPage, "Добавление дефекта"));
			}
			// иначе идем дальше
			else
			{
				if (!_interactors[CarouselView.Position] && CarouselView.Position != _indexAdvanced) return;
				CarouselView.Position += 1;
				ButtonBack.IsVisible = CarouselView.Position != 0;
				ButtonForward.IsEnabled = _interactors[CarouselView.Position];
				// Если дошли до доп инфы, прописываем поведение кнопок
				if (CarouselView.Position == _indexAdvanced)
				{
					ButtonForward.Text = _interactors[_indexAdvanced] ? "Далее" : "Пропустить";
					ButtonForward.Style = _interactors[_indexAdvanced] ? (Style)Application.Current.Resources["ButtonStandard"]
						: (Style)Application.Current.Resources["ButtonStandardCancel"];
					// для доп инфы всегда можно нажимать
					ButtonForward.IsEnabled = true;
				}
				// Если же дошли до даты, то прописываем там поведение
				else if (CarouselView.Position == _indexAdvanced + 1)
				{
					ButtonForward.Text = "Далее";
					ButtonForward.Style = _interactors[_indexAdvanced + 1] ? (Style)Application.Current.Resources["ButtonStandard"]
						: (Style)Application.Current.Resources["ButtonStandardCancel"];
				}
				// Дошли до конца - прописываем соответствующее поведение
				else if (CarouselView.Position == _indexSummary)
				{
					ButtonForward.Text = "Добавить дефект";
					ButtonForward.Style = (Style) Application.Current.Resources["ButtonStandard"];
				}
				else
				{
					ButtonForward.Text = "Далее";
					ButtonForward.Style = _interactors[CarouselView.Position] ? (Style)Application.Current.Resources["ButtonStandard"]
						: (Style)Application.Current.Resources["ButtonStandardCancel"];
				}
				
			}
			// Навигация по мастеру вперед
			
			//if (CarouselView.Position == _indexSummary)
			//	ButtonForward.Text = "Добавить дефект";
			//else
			//	ButtonForward.Text = CarouselView.Position == _indexAdvanced ? "Пропустить" : "Далее";
			//ButtonForward.Style = CarouselView.Position == _indexSummary
			//	? (Style)Application.Current.Resources["ButtonStandard"]
			//	: (Style)Application.Current.Resources["ButtonStandardCancel"];
		}

		private void ChangeButtonInteraction()
		{
			if (_interactors[CarouselView.Position])
			{
				ButtonForward.IsEnabled = true;
				ButtonForward.Text = CarouselView.Position == _indexSummary ? "Добавить дефект" : "Далее";
				ButtonForward.Style = (Style)Application.Current.Resources["ButtonStandard"];
			}
			else
			{
				ButtonForward.IsEnabled = false;
				ButtonForward.Text = CarouselView.Position == _indexAdvanced ? "Пропустить" :
						CarouselView.Position == _indexSummary ? "Добавить дефект" : "Далее";
				ButtonForward.Style = (Style)Application.Current.Resources["ButtonStandardCancel"];
			}
		}

		/// <summary>
		/// Заканчиваем работу с дефектом
		/// </summary>
		private async void DoneWithDefect()
		{
			while (Navigation.NavigationStack[Navigation.NavigationStack.Count - 2].GetType() != typeof(IssoViewActivity))
			{
				Navigation.RemovePage(Navigation.NavigationStack[Navigation.NavigationStack.Count - 2]);
			}
			await Navigation.PopAsync();
			MessagingCenter.Send("", "DefectAdded");
		}
	}
}

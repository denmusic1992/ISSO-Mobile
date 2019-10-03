using CommonClassesLibrary;
using ISSO_I.Drivers;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Text.RegularExpressions;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

// ReSharper disable once CheckNamespace
namespace ISSO_I.PopupTypes
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class TextPopupPage
	{
		private readonly View _view;
		private readonly DBHelper.ADVANCED_S_TABLES _property;
		private readonly CellType _cellType;
		public event EventHandler SaveChanges;
		/// <summary>
		/// Заголовок для всплывающего окна
		/// </summary>
		public string Header { get; set; }

		public TextPopupPage(View view, DBHelper.ADVANCED_S_TABLES property, CellType cellType, string sysName)
		{
			InitializeComponent();
			_view = view;
			_property = property;
			_cellType = cellType;
			//// Получим строку для определения верхней и нижней границы
			//StringBuilder minmax = new StringBuilder("(");
			//if (property.MIN_V != 0)
			//    minmax.Append(String.Format("от {0} ", property.MIN_V));
			//if(property.MAX_V != 0)
			//    minmax.Append(String.Format("до {0}", property.MAX_V));
			//minmax.Append(")");
			// Добавляем описание нашего popup окна
			Header = $"{property.DESCRIPTION}";

			// Смотрим, есть ли у нас колонка с CultureInfo
			var driver = Ais7DataColumnDriver.Create(sysName, property.TABLE_COLUMN);
			if (driver is Ais7DataColumnDriver_V_ISSO_W_ISSO)
			{
				editorText.Behaviors.Add(IntColonValidatorBehavior.Instance);
				_cellType = CellType.IsLocation;
			}

			switch (_cellType)
			{
				case CellType.IsNumeric:
				case CellType.IsDouble:
					editorText.Keyboard = Keyboard.Numeric;
					break;
				case CellType.IsLocation:
					editorText.Keyboard = Keyboard.Telephone;
					break;
			}

			editorText.Text = ((Label)((Grid)view).Children[0]).Text;
		}


		//protected override void OnSizeAllocated(double width, double height)
		//{
		//    base.OnSizeAllocated(width, height);
		//    stackLayout.WidthRequest = width * 2 / 3;
		//    stackLayout.HeightRequest = height / 2;
		//}

		private void button_confirm_Clicked(object sender, EventArgs e)
		{
			if (editorText.Text.Equals("")) return;
			// Если у нас тип ячейки double, то отформатируем в соответствии с форматом
			switch (_cellType)
			{
				case CellType.IsDouble:
					var resultDouble = Convert.ToDouble(editorText.Text);
					// Необходимо проверить, попадает ли в диапазон значений полученное число
					if (_property.MIN_V != 0)
					{
						if (resultDouble < _property.MIN_V)
						{
							resultDouble = _property.MIN_V;
							DependencyService.Get<IMessage>().ShortAlert($"Значение было изменено на {resultDouble}");
						}
					}
					if (_property.MAX_V != 0)
					{
						if (resultDouble > _property.MAX_V)
						{
							resultDouble = _property.MAX_V;
							DependencyService.Get<IMessage>().ShortAlert($"Значение было изменено на {resultDouble}");
						}
					}
					((Label)((Grid)_view).Children[0]).Text = resultDouble.ToString(_property.FORMAT ?? "F2");
					break;
				case CellType.IsNumeric:
					// Обрубаем часть, если есть запятая
					if (editorText.Text.IndexOf('.') > 0)
					{
						editorText.Text = editorText.Text.Substring(0, editorText.Text.IndexOf('.'));
					}
					else if (editorText.Text.IndexOf(',') > 0)
					{
						editorText.Text = editorText.Text.Substring(0, editorText.Text.IndexOf(','));
					}
					((Label)((Grid)_view).Children[0]).Text = editorText.Text;
					switch (_property.COLUMNTYPE)
					{
						case "smallint":
							var result = Convert.ToInt32(editorText.Text);
							// В случае с результатом, выходящим за рамки int16, обрубаем по верхнему или нижнему краю int16
							if (result < short.MinValue || result > short.MaxValue)
							{
								if (result < short.MinValue)
									result = short.MinValue;
								else if (result > short.MaxValue)
									result = short.MaxValue;
							}
							if (_property.MIN_V != 0)
							{
								if (result < _property.MIN_V)
								{
									result = _property.MIN_V;
									DependencyService.Get<IMessage>().ShortAlert($"Значение было изменено на {result}");
								}
							}
							if (_property.MAX_V != 0)
							{
								if (result > _property.MAX_V)
								{
									result = _property.MAX_V;
									DependencyService.Get<IMessage>().ShortAlert($"Значение было изменено на {result}");
								}
							}
							((Label)((Grid)_view).Children[0]).Text = result.ToString();
							break;
						default:
							var result32 = Convert.ToInt64(editorText.Text);
							if (result32 == long.MinValue || result32 == long.MaxValue)
							{
								if (result32 < int.MinValue)
									result32 = int.MinValue;
								else if (result32 > int.MaxValue)
									result32 = int.MaxValue;
							}
							if (_property.MIN_V != 0)
							{
								if (result32 < _property.MIN_V)
								{
									result32 = _property.MIN_V;
									DependencyService.Get<IMessage>().ShortAlert($"Значение было изменено на {result32}");
								}
							}
							if (_property.MAX_V != 0)
							{
								if (result32 > _property.MAX_V)
								{
									result32 = _property.MAX_V;
									DependencyService.Get<IMessage>().ShortAlert($"Значение было изменено на {result32}");
								}
							}
							((Label)((Grid)_view).Children[0]).Text = result32.ToString();
							break;
					}

					break;
				case CellType.IsText:
					((Label)((Grid)_view).Children[0]).Text = editorText.Text;
					break;
				case CellType.IsLocation:
					var valueResult = editorText.Text;
					if (valueResult.IndexOf("+", StringComparison.Ordinal) != 0)
					{
						var vals = valueResult.Split('+');
						if (vals.Length > 1)
						{
							((Label)((Grid)_view).Children[0]).Text = !vals[1].Equals("") ? valueResult : $"{vals[0]}+000";
						}
						else
						{
							((Label)((Grid)_view).Children[0]).Text = $"{valueResult}+000";
						}
					}
					else
					{
						((Label)((Grid)_view).Children[0]).Text = $"0+{valueResult.Split('+')[1]}";
					}

					break;
			}
			Navigation.PopPopupAsync();
			SaveChanges?.Invoke(_view, EventArgs.Empty);
		}
	}


	public class IntColonValidatorBehavior : Behavior<Editor>
	{
		public static IntColonValidatorBehavior Instance = new IntColonValidatorBehavior();

		protected override void OnAttachedTo(Editor bindable)
		{
			bindable.TextChanged += Bindable_TextChanged;
			base.OnAttachedTo(bindable);
		}

		protected override void OnDetachingFrom(Editor bindable)
		{
			bindable.TextChanged -= Bindable_TextChanged;
			base.OnDetachingFrom(bindable);
		}

		private void Bindable_TextChanged(object sender, TextChangedEventArgs e)
		{
			if (string.IsNullOrWhiteSpace(e.NewTextValue)) return;
			// сначала удаляем все ненужные нам символы
			const string pattern = @"[^\+0123456789\b]";
			var newVal = Regex.Replace(e.NewTextValue, pattern, "");

			// разделяем строку на 2
			var vals = newVal.Split('+');

			if (vals.Length > 1)
			{
				if (vals[1].Length > 4)
				{
					var oldVal = e.OldTextValue.Split('+')[1];
					vals[1] = oldVal;
				}
				((Editor)sender).Text = $"{vals[0]}+{vals[1]}";
			}
			else
				((Editor)sender).Text = newVal;
		}
	}
}
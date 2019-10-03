using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using CommonClassesLibrary.Interfaces;
using CommonClassesLibrary.ViewModels;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.IssoViewPages.ForDefectTable.Views;
using ISSO_I.Sqlite;
using Xamarin.Forms;

namespace ISSO_I.IssoViewPages.ForDefectTable.ViewModels
{
	public class CreateDefectViewModel : BaseViewModel
	{
		private readonly SummaryDefectContentView _summaryDefectContentView;

		private readonly CreateDefectModel _defectModel;

		public bool HasQualParameters { get; set; }

		public bool HasQuanParameters { get; set; }


		private string _buttonTextBack;
		/// <summary>
		/// Кнопка нажатия назад
		/// </summary>
		public string ButtonTextBack
		{
			get => _buttonTextBack;
			set => SetProperty(ref _buttonTextBack, value);
		}


		private string _textButton;
		/// <summary>
		/// Текст отображения кнопки
		/// </summary>
		public string TextButton
		{
			get => _textButton;
			set => SetProperty(ref _textButton, value);
		}

		private string _buttonTextForward;
		/// <summary>
		/// Кнопка нажатия вперед
		/// </summary>
		public string ButtonTextForward
		{
			get => _buttonTextForward;
			set => SetProperty(ref _buttonTextForward, value);
		}


		private ObservableCollection<View> _myItemsSource;
		/// <summary>
		/// Вьюшки для CarouselView
		/// </summary>
		public ObservableCollection<View> MyItemsSource
		{
			set => SetProperty(ref _myItemsSource, value);
			get => _myItemsSource;
		}

		/// <summary>
		/// Добавление дефекта в БД
		/// </summary>
		public bool AddDefectToDatabase()
		{
			// Инициализация БД для дефектов
			using (var sqliteRepository = new DefectContext())
			{
				// Идентифицируем номер дефекта
				//var nDef = SqliteReader.SelectScalar<int>($"select max(n_def) from i_defect where c_isso={_defectModel.CIsso}") + 1;
				var res = sqliteRepository.i_defect.FirstOrDefault(item => item.c_isso == _defectModel.CIsso);
				var nDef = res != null
					? sqliteRepository.i_defect.Where(i => i.c_isso == _defectModel.CIsso).Max(i => i.n_def)
					: 0;
				// Добавление новой строки в I_DEFECT
				var defectList = new List<defects>();
				// Добавление новых развитий дефектов
				var defectMods = new List<defMods>();
				// Добавление списка дефектов
				var defectsDescrs = new List<defDescr>();
				// Если у нас на всем сооружении, то добавляем только один раз с -1 в nConstr
				if (_defectModel.AllChecked || !_defectModel.ParentTreeNode.IsForConstr)
				{
					// добавляем в i_defect
					var defect = new defects
					{
						c_isso = _defectModel.CIsso,
						n_def = (short)(nDef + 1),
						c_defect = _defectModel.CDefect,
						c_gr_constr = _defectModel.CGrConstr,
						b = _defectModel.B,
						b1 = _defectModel.B1,
						d = _defectModel.D,
						d1 = _defectModel.D1,
						r = _defectModel.R,
						r1 = _defectModel.R1,
						g = _defectModel.G,
						g1 = _defectModel.G1,
						date = _defectModel.CreationDate,
					};
					if (_defectModel.AllChecked)
						defect.n_constr = -1;
					defectList.Add(defect);

					// Добавляем запись в i_def_mod
					var defMod = new defMods
					{
						c_isso = _defectModel.CIsso,
						n_def = (short)(nDef + 1),
						date = _defectModel.CreationDate ?? DateTime.Now,
						l_def = _defectModel.Localization,
						w_def = _defectModel.AdvancedInfo
					};
					defectMods.Add(defMod);

					// Добавляем запись в i_def_descr
					// Если есть качественный, то добавляем
					if (_defectModel.SelectedQualDefectParameter != null)
					{
						defectsDescrs.Add(new defDescr
						{
							c_isso = _defectModel.CIsso,
							n_def = defMod.n_def,
							date = defMod.date,
							c_defparam = _defectModel.SelectedQualDefectParameter.CDefParam,
							b = _defectModel.SelectedQualDefectParameter.B,
							b1 = _defectModel.SelectedQualDefectParameter.B1,
							d = _defectModel.SelectedQualDefectParameter.D,
							d1 = _defectModel.SelectedQualDefectParameter.D1,
							r = _defectModel.SelectedQualDefectParameter.R,
							r1 = _defectModel.SelectedQualDefectParameter.R1,
							g = _defectModel.SelectedQualDefectParameter.G,
							g1 = _defectModel.SelectedQualDefectParameter.G1
						});
					}

					// Если есть количественный, то добавляем
					if (_defectModel.SelectedQuanDefectParameter != null)
					{
						defectsDescrs.Add(new defDescr
						{
							c_isso = _defectModel.CIsso,
							n_def = defMod.n_def,
							date = defMod.date,
							c_defparam = _defectModel.SelectedQuanDefectParameter.CDefParam,
							value = _defectModel.SelectedQuanDefectParameter.Value,
							b = _defectModel.SelectedQuanDefectParameter.B,
							b1 = _defectModel.SelectedQuanDefectParameter.B1,
							d = _defectModel.SelectedQuanDefectParameter.D,
							d1 = _defectModel.SelectedQuanDefectParameter.D1,
							r = _defectModel.SelectedQuanDefectParameter.R,
							r1 = _defectModel.SelectedQuanDefectParameter.R1,
							g = _defectModel.SelectedQuanDefectParameter.G,
							g1 = _defectModel.SelectedQuanDefectParameter.G1
						});
					}
				}
				else
				{
					// Иначе мы добавляем по каждой конструкции новый дефект и присваиваем ему новый уникальный номер
					foreach (var constr in _defectModel.NConstr)
					{
						var defect = new defects
						{
							c_isso = _defectModel.CIsso,
							n_def = (short)(nDef + 1),
							c_defect = _defectModel.CDefect,
							c_gr_constr = _defectModel.CGrConstr,
							n_constr = constr.Id,
							b = _defectModel.B,
							b1 = _defectModel.B1,
							d = _defectModel.D,
							d1 = _defectModel.D1,
							r = _defectModel.R,
							r1 = _defectModel.R1,
							g = _defectModel.G,
							g1 = _defectModel.G1,
							date = _defectModel.CreationDate,
							//datef = CommonStaffUtils.ConvertToUnixTimestamp(_defectModel.CreationDate)
						};
						defectList.Add(defect);

						// Добавление новой строки в I_DEF_MOD
						var defMod = new defMods
						{
							c_isso = _defectModel.CIsso,
							n_def = (short)(nDef + 1),
							date = _defectModel.CreationDate ?? DateTime.Now,
							l_def = _defectModel.Localization,
							w_def = _defectModel.AdvancedInfo
						};
						defectMods.Add(defMod);

						// Если есть качественный, то добавляем
						if (_defectModel.SelectedQualDefectParameter != null)
						{
							defectsDescrs.Add(new defDescr
							{
								c_isso = _defectModel.CIsso,
								n_def = defMod.n_def,
								date = defMod.date,
								c_defparam = _defectModel.SelectedQualDefectParameter.CDefParam,
								b = _defectModel.SelectedQualDefectParameter.B,
								b1 = _defectModel.SelectedQualDefectParameter.B1,
								d = _defectModel.SelectedQualDefectParameter.D,
								d1 = _defectModel.SelectedQualDefectParameter.D1,
								r = _defectModel.SelectedQualDefectParameter.R,
								r1 = _defectModel.SelectedQualDefectParameter.R1,
								g = _defectModel.SelectedQualDefectParameter.G,
								g1 = _defectModel.SelectedQualDefectParameter.G1
							});
						}

						// Если есть количественный, то добавляем
						if (_defectModel.SelectedQuanDefectParameter != null)
						{
							defectsDescrs.Add(new defDescr
							{
								c_isso = _defectModel.CIsso,
								n_def = defMod.n_def,
								date = defMod.date,
								c_defparam = _defectModel.SelectedQuanDefectParameter.CDefParam,
								value = _defectModel.SelectedQuanDefectParameter.Value,
								b = _defectModel.SelectedQuanDefectParameter.B,
								b1 = _defectModel.SelectedQuanDefectParameter.B1,
								d = _defectModel.SelectedQuanDefectParameter.D,
								d1 = _defectModel.SelectedQuanDefectParameter.D1,
								r = _defectModel.SelectedQuanDefectParameter.R,
								r1 = _defectModel.SelectedQuanDefectParameter.R1,
								g = _defectModel.SelectedQuanDefectParameter.G,
								g1 = _defectModel.SelectedQuanDefectParameter.G1
							});
						}

						nDef += 1;
					}
				}

				using (var dbTransaction = sqliteRepository.Database.BeginTransaction())
				{
					// Сохраняем изменения в таблицу
					try
					{
						//Записываем в I_DEFECT
						foreach (var defect in defectList)
						{
							sqliteRepository.i_defect.Add(defect);
						}

						//App.SqliteRepository.i_defect.AddRange(defectList);
						//Записываем в I_DEF_MOD
						foreach (var defectMod in defectMods)
						{
							sqliteRepository.i_def_mod.Add(defectMod);
						}

						//App.SqliteRepository.i_def_mod.AddRange(defectMods);
						//Записываем в I_DEF_DESCR
						foreach (var defectDescr in defectsDescrs)
						{
							sqliteRepository.i_def_descr.Add(defectDescr);
						}
						//App.SqliteRepository.i_def_descr.AddRange(defectsDescrs);

						//Если есть фотография для дефекта, то давайте запишем её в БД
						if (!_defectModel.DefectPhotoPath.Equals(""))
						{
							using (var photo = new FileStream(_defectModel.DefectPhotoPath, FileMode.Open, FileAccess.Read))
							{
								var photoBytes = new byte[photo.Length];
								photo.Read(photoBytes, 0, (int)photo.Length);

								var photoDefect = new FotoDefects
								{
									c_isso = _defectModel.CIsso,
									n_def = (short)(nDef + 1),
									date = _defectModel.CreationDate,
									foto = photoBytes
								};
								sqliteRepository.i_foto_def.Add(photoDefect);
							}

							var pathToDir =
								_defectModel.DefectPhotoPath.Substring(0, _defectModel.DefectPhotoPath.LastIndexOf(Path.DirectorySeparatorChar));
							if (Directory.Exists(pathToDir))
							{
								DependencyService.Get<ILocalFileProvider>().DeleteFilesFromDir(pathToDir);
							}
						}

						// Подтверждаем успешную запись
						sqliteRepository.SaveChanges();
						dbTransaction.Commit();
						return true;
					}
					catch (Exception ex)
					{
						Debug.WriteLine($"Ошибка при добавлении дефекта:\n\tError: {ex.Message}" +
										$"\n\tStackTrace: {ex.StackTrace}");
						dbTransaction.Rollback();
						return false;
					}
				}
			}
		}


		public CreateDefectViewModel(CreateDefectModel defectModel)
		{
			_defectModel = defectModel;
			_summaryDefectContentView = new SummaryDefectContentView(defectModel);
			MyItemsSource = new ObservableCollection<View>
			{
				//new QualityParametersTreeView(defectModel) { VerticalOptions = LayoutOptions.FillAndExpand, HorizontalOptions = LayoutOptions.FillAndExpand, Margin = 0},
				//new MainDefectParametersContentView(defectModel) { VerticalOptions =  LayoutOptions.FillAndExpand, HorizontalOptions = LayoutOptions.FillAndExpand, Margin = 0},
			    //new BoxView {VerticalOptions = LayoutOptions.FillAndExpand, HorizontalOptions = LayoutOptions.FillAndExpand, Margin = 0, BackgroundColor = Color.Red},
			    //new BoxView {VerticalOptions = LayoutOptions.FillAndExpand, HorizontalOptions = LayoutOptions.FillAndExpand, Margin = 0, BackgroundColor = Color.Aqua}
				new AdditionalDefectParametersContentView(defectModel),
				new DateDefectContentView(defectModel),
				_summaryDefectContentView
			};
			// Если есть количественные, то добавляем их
			if (defectModel.DefectParameters.Any(item => !item.IsQual))
			{
				MyItemsSource.Insert(0,
					new QuantityParametersContentPage(defectModel)
					{
						VerticalOptions = LayoutOptions.FillAndExpand,
						HorizontalOptions = LayoutOptions.FillAndExpand,
						Margin = 0
					});
				HasQuanParameters = true;
			}

			// Если есть у нас качественные параметры, то добавляем страницу с кач параметрами
			if (defectModel.DefectParameters.Any(item => item.IsQual))
			{
				MyItemsSource.Insert(0,
					new QualityParametersTreeView(defectModel)
					{
						VerticalOptions = LayoutOptions.FillAndExpand,
						HorizontalOptions = LayoutOptions.FillAndExpand,
						Margin = 0
					});
				HasQualParameters = true;
			}

			// Добавим Event'ы на изменение данных в summary
			defectModel.PropertyChanged += DefectModel_PropertyChanged;
		}

		private void DefectModel_PropertyChanged(object sender, PropertyChangedEventArgs e)
		{
			switch (e.PropertyName)
			{
				case "SelectedQualDefectParameter":
					_summaryDefectContentView.Vm.SetQualityParameter();

					//((CreateDefectModel)summaryDefectContentView).SelectedQualDefectParameter =
					break;
				case "SelectedQuanDefectParameter":
					_summaryDefectContentView.Vm.SetQuantityParameter();
					break;
				case "Localization":
				case "AdvancedInfo":
					_summaryDefectContentView.Vm.SetAdvancedInfo();
					break;
				case "CreationDate":
					_summaryDefectContentView.Vm.SetChosenDate();
					break;
				case "NConstr":
				case "AllChecked":
					_summaryDefectContentView.Vm.SetSelectedConstr();
					break;
				case "DefectPhotoPath":
					_summaryDefectContentView.Vm.SetPhotoAdded(!_defectModel.DefectPhotoPath.Equals(""));
					break;
			}
		}
	}
}

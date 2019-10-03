using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Windows.Input;
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using CommonClassesLibrary.ViewModels;
using ISSO_I.IssoViewPages.ForDefectTable.Commands;
using ISSO_I.IssoViewPages.ForDefectTable.Models;
using ISSO_I.Sqlite;
using Stormlion.PhotoBrowser;
using Xamarin.Forms;

namespace ISSO_I.IssoViewPages.ForDefectTable.ViewModels
{
	public class AdditionalDefectParametersContentViewModel : BaseViewModel
	{
		private readonly CreateDefectModel _defectModel;


		/// <summary>
		/// Локализация
		/// </summary>
		private string _localization;
		public string Localization
		{
			get => _localization;
			set
			{
				SetProperty(ref _localization, value);
				_defectModel.Localization = value;
				MessagingCenter.Send(new Tuple<DefectType, bool>(DefectType.Advanced, true), "InteractionChanged");
			}
		}

		/// <summary>
		/// Дополнительная информация
		/// </summary>
		private string _additionalInformation;
		public string AdditionalInformation
		{
			get => _additionalInformation;
			set
			{
				SetProperty(ref _additionalInformation, value);
				_defectModel.AdvancedInfo = value;
				MessagingCenter.Send(new Tuple<DefectType, bool>(DefectType.Advanced, true), "InteractionChanged");
			}
		}

		private string _volumeRemWorks;

		public string VolumeRemWorks
		{
			get => _volumeRemWorks;
			set => SetProperty(ref _volumeRemWorks, value);
		}


		/// <summary>
		/// Команда добавления фотографии дефекта
		/// </summary>
		public ICommand AddDefectPhoto { get; protected set; }

		/// <summary>
		/// Команда нажатия на само изображение дефекта
		/// </summary>
		public ICommand PictureTouched { get; protected set; }

		/// <summary>
		/// Команда удаления фотографии дефекта
		/// </summary>
		public ICommand CloseAndDelete { get; protected set; }

		private string _defectImage;

		/// <summary>
		/// Фотография дефекта (путь до нее)
		/// </summary>
		public string DefectImage
		{
			get => _defectImage;
			set
			{
				SetProperty(ref _defectImage, value);
				_defectModel.DefectPhotoPath = value;
				HasPhoto = !_defectModel.DefectPhotoPath.Equals("");
			}
		}

		/// <summary>
		/// Ремонтные работы
		/// </summary>
		public ObservableCollection<string> RemWorks => GetRemWorks();

		private bool _hasPhoto;
		public bool HasPhoto
		{
			get => _hasPhoto;
			set => SetProperty(ref _hasPhoto, value);
		}


		public AdditionalDefectParametersContentViewModel()
		{
		}

		/// <summary>
		/// Конструктор
		/// </summary>
		/// <param name="defectModel"></param>
		public AdditionalDefectParametersContentViewModel(CreateDefectModel defectModel)
		{
			_defectModel = defectModel;
			AddDefectPhoto = new Command(AddNewDefectPhoto);
			PictureTouched = new PictureTouchedCommand(this);
			CloseAndDelete = new Command(DeletePhotoDefect);
		}

		/// <summary>
		/// Добавление новой фотографии
		/// </summary>
		protected internal async void AddNewDefectPhoto()
		{
			var mediaFile = await CommonPhotoUtils.TakePhoto();
			DefectImage = mediaFile.Path;
			mediaFile.Dispose();
		}

		/// <summary>
		/// Открытие фотографии в полный размер
		/// </summary>
		protected internal void ShowDefectPicture()
		{
			PhotoBrowser.Close();
			new PhotoBrowser
			{
				Photos = new List<Photo>
				{
					new Photo
					{
						URL = $"file://{DefectImage}",
					}
				},
				EnableGrid = false
			}.Show();
		}

		/// <summary>
		/// Метод удаления фотографии
		/// </summary>
		protected internal void DeletePhotoDefect()
		{
			var pathToDir =
				DefectImage.Substring(0, DefectImage.LastIndexOf(Path.DirectorySeparatorChar));
			if (Directory.Exists(pathToDir))
			{
				DependencyService.Get<ILocalFileProvider>().DeleteFilesFromDir(pathToDir);
			}

			DefectImage = "";
		}

		private ObservableCollection<string> GetRemWorks()
		{
			var constrQuery = "select min(c_gr_constr) from i_isso " +
							  "left outer join s_typisso on s_typisso.c_typisso=i_isso.ctypeisso " +
							  $"where c_isso={_defectModel.CIsso}";
			var mainGrConstr = SqliteReader.SelectScalar<int>(constrQuery);
			// Запрос на получение ремонтных работ
			var sql = "select c_rem, n_rem, ind_value, sn_unit_dime, ind_comment, c_typrem from " +
					  "(select s_rem.C_REM, " +
					  "coalesce(s_rem_db.C_TYPREM, s_rem.C_TYPREM) as C_TYPREM, " +
					  "coalesce(s_rem_db.SHIFR, s_rem.SHIFR) as SHIFR, " +
					  "coalesce(s_rem_db.N_REM, s_rem.N_REM) as N_REM, " +
					  "s_rem.IND_UNIT, s_rem.IND_VALUE, s_rem.IND_COMMENT, coalesce(s_rem_db.ord, s_rem.c_rem) as ord " +
					  "from s_rem " +
					  "left outer join (select * from s_rem_db where c_database = 0) s_rem_db on s_rem_db.c_rem = s_rem.C_REM " +
					  "where s_rem.c_rem in (select s_rem_db.c_rem from s_rem_db where c_database = 0) " +
					  "union all " +
					  "select s_rem.C_REM, " +
					  "s_rem_ad_type.C_TYPREM, " +
					  "s_rem.SHIFR, s_rem.N_REM, s_rem.IND_UNIT, s_rem.IND_VALUE, s_rem.IND_COMMENT, s_rem.c_rem as ord " +
					  "from s_rem_ad_type " +
					  "left outer join s_rem on s_rem_ad_type.c_rem = s_rem.c_rem " +
					  "where s_rem_ad_type.c_database = 0 " +
					  "order by c_typrem, ord) " +
					  "s_rem left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen=s_rem.ind_unit " +
					  "where c_rem in " +
					  $"(select c_rem from s_rem_constr where c_gr_constr={mainGrConstr} and main_f=1) " +
					  "order by ord";
			var dataReader = SqliteReader.SelectQueryReader(sql, out var conn);
			var remWorks = new ObservableCollection<string>();
			if (dataReader != null && dataReader.HasRows)
				while (dataReader.Read())
				{
					remWorks.Add(dataReader.GetString(dataReader.GetOrdinal("n_rem")));
				}
			dataReader?.Close();
			conn.Close();
			return remWorks;
		}
	}
}

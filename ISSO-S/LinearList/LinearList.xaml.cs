using CommonClassesLibrary;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace LinearList
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public abstract partial class LinearList
    {
	    /// <summary>
        /// Список ИССО
        /// </summary>
        public ObservableCollection<Isso> Issos { get; set; } = new ObservableCollection<Isso>();
        /// <summary>
        /// Выбранная дорога
        /// </summary>
        public string SelectedRoad = "[Все]";
        /// <summary>
        /// Список линейных ИССО
        /// </summary>
        public ListView IssoList;

	    protected LinearList(ApplicationType app)
        {
	        Icon = "launcher.png";
            InitializeComponent();
	        IssoList = issoList;
	        IssoList.ItemsSource = Issos;
	        IssoList.ItemTapped += IssoList_ItemTapped;
            switch (app)
            {
                case ApplicationType.IssoS:
                    Title = "АИС ИССО-SX";
                    break;
                case ApplicationType.IssoI:
                    Title = "АИС ИССО-IX";
                    break;
	            case ApplicationType.IssoR:
		            break;
	            default:
		            throw new ArgumentOutOfRangeException(nameof(app), app, null);
            }

            // Убираем сепаратор в iOS
            if (Device.RuntimePlatform == Device.iOS)
                IssoList.SeparatorVisibility = SeparatorVisibility.None;
        }
        /// <summary>
        /// Обработчик нажатия на строку
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void IssoList_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            ((ListView)sender).SelectedItem = null;
            CallIssoView(((Isso)e.Item));
        }

        /// <summary>
        /// Вызов метода при открытии ИССО из списка
        /// </summary>
        /// <param name="isso"></param>
        public abstract void CallIssoView(Isso isso);

        public override void Dispose()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Применить фильтр по дороге
        /// </summary>
        public abstract void Filter_Clicked(object sender, EventArgs e);

        /// <summary>
        /// Загрузка списка ИССО
        /// </summary>
        /// <returns></returns>
        public abstract ObservableCollection<Isso> BindIssos();

        ///// <summary>
        ///// Больше информации об ИССО
        ///// </summary>
        ///// <param name="sender"></param>
        ///// <param name="e"></param>
        //public abstract void OnMore(object sender, EventArgs e);

        private void ContentPage_Appearing(object sender, EventArgs e)
        {
            Issos = BindIssos();
            NoIssos.IsVisible = Issos.Count == 0;
            searchIssoFilter.IsVisible = IssoList.IsVisible = Issos.Count > 0;
            
            //OnSizeAllocated(Content.Width, Content.Height);
	        IssoList.ItemsSource = Issos;
        }

        private void searchIssoFilter_TextChanged(object sender, TextChangedEventArgs e)
        {
	        IssoList.BeginRefresh();
            IssoList.ItemsSource = string.IsNullOrWhiteSpace(e.NewTextValue) ? Issos : Issos.Where(isso => isso.Description.ToLower().Contains(e.NewTextValue.ToLower()));
	        IssoList.EndRefresh();
        }

        //protected override void OnSizeAllocated(double width, double height)
        //{
        //    base.OnSizeAllocated(width, height);
        //    if (issos.Count > 0)
        //    {
        //        NoIssos.HeightRequest = 0;
        //        issoList.HeightRequest = Content.Height;
        //    }
        //    else
        //    {
        //        NoIssos.HeightRequest = Content.Height;
        //        issoList.HeightRequest = 0;
        //    }
        //}

    }


    /// <summary>
    /// Класс, описывающий ИССО в списке сооружений
    /// </summary>
    public class Isso
    {
        /// <summary>
        /// Номер ИССО
        /// </summary>
        public int CIsso { get; set; }
        /// <summary>
        /// Описание
        /// </summary>
        public string Description { get; set; }
        /// <summary>
        /// Последний рейтинг (изображение)
        /// </summary>
        public FileImageSource Img { get; set; }

        public Isso(int cIsso, string description, Otc otc, ApplicationType typeApp)
        {
            CIsso = cIsso;
            Description = description;
            switch(typeApp)
            {
                case ApplicationType.IssoS:
                    switch (otc)
                    {
                        case Otc.NotChanged:
                            Img = new FileImageSource { File = "draw_1.png" };
                            break;
                        case Otc.SlightlyWorse:
                            Img = new FileImageSource { File = "draw_2.png" };
                            break;
                        case Otc.Worse:
                            Img = new FileImageSource { File = "draw_3.png" };
                            break;
                        case Otc.SignificantlyWorse:
                            Img = new FileImageSource { File = "draw_4.png" };
                            break;
                        case Otc.Improved:
                            Img = new FileImageSource { File = "draw_5.png" };
                            break;
                        case Otc.Crash:
                            Img = new FileImageSource { File = "draw_6.png" };
                            break;
                        default:
                            Img = new FileImageSource { File = "draw_0.png" };
                            break;
                    }
                    break;
                case ApplicationType.IssoI:
                    switch(otc)
                    {
                        case Otc.Emergency:
                            Img = new FileImageSource { File = "emergency.png" };
                            break;
                        case Otc.PreEmergency:
                            Img = new FileImageSource { File = "pre_emergency.png" };
                            break;
                        case Otc.NonSatisfied:
                            Img = new FileImageSource { File = "non_satisfied.png" };
                            break;
                        case Otc.Satisfied:
                            Img = new FileImageSource { File = "satisfied.png" };
                            break;
                        case Otc.WellDone:
                            Img = new FileImageSource { File = "well_done.png" };
                            break;
                        case Otc.Excellent:
                            Img = new FileImageSource { File = "well_done.png" };
                            break;
                        case Otc.NotActual:
                            Img = new FileImageSource { File = "not_actual.png" };
                            break;
                        case Otc.EmergencyNotActual:
                            Img = new FileImageSource { File = "emergency_not_actual.png" };
                            break;
                        case Otc.PreEmergencyNotActual:
                            Img = new FileImageSource { File = "pre_emergency_not_actual.png" };
                            break;
                        case Otc.NonSatisfiedNotActual:
                            Img = new FileImageSource { File = "non_satisfied_not_actual.png" };
                            break;
                        case Otc.SatisfiedNotActual:
                            Img = new FileImageSource { File = "satisfied_not_actual.png" };
                            break;
                        case Otc.WellDoneNotActual:
                            Img = new FileImageSource { File = "well_done_not_actual.png" };
                            break;
                    }
                    break;
            }
        }
    }

    public class IssosList
    {
	    /// <summary>
	    /// номер ИССО
	    /// </summary>
	    public int C_ISSO { get; set; }
	    /// <summary>
	    /// Наименование ИССО
	    /// </summary>
	    public string NAME { get; set; }
	    /// <summary>
	    /// Наименование ИССО
	    /// </summary>
	    public string NAME_ISSO { get; set; }
	    /// <summary>
	    /// Экспертная ОТС (численная)
	    /// </summary>
	    public int C_OTC_EXP { get; set; }
	    /// <summary>
	    /// Экспертная ОТС
	    /// </summary>
	    public string N_OTC_EXP { get; set; }
	    /// <summary>
	    /// Последний рейтинг
	    /// </summary>
	    public int RATINGS_1 { get; set; }
	    /// <summary>
	    /// Полное наименование ИССО
	    /// </summary>
	    public string FULLNAME { get; set; }
	    /// <summary>
	    /// Дорога, на которой находится ИССО
	    /// </summary>
	    public string DORNAME { get; set; }
	    /// <summary>
	    /// Номер дороги
	    /// </summary>
	    public int W_ISSO { get; set; }
	    /// <summary>
	    /// Препятствие
	    /// </summary>
	    public string OBSTACLE { get; set; }
	    /// <summary>
	    /// Длина
	    /// </summary>
	    public double LENGTH { get; set; }
	    /// <summary>
	    /// Широта
	    /// </summary>
	    public double LATITUDE { get; set; }
	    /// <summary>
	    /// Долгота
	    /// </summary>
	    public double LONGITUDE { get; set; }
	    /// <summary>
	    /// Тип ИССО
	    /// </summary>
	    public short CTYPEISSO { get; set; } = 0;
    }
}
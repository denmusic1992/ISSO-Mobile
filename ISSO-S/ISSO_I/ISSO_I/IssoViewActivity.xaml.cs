using CommonClassesLibrary.BackHandlers;
using System;
using System.Collections.Generic;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class IssoViewActivity : IDisposable
    {
        /// <summary>
        /// Номер конструкции
        /// </summary>
        private readonly int _cIsso;
        /// <summary>
        /// идикатор первого запуска
        /// </summary>
        private bool _firstLaunch = true;
        /// <summary>
        /// Список проинициализированных страниц
        /// </summary>
        private readonly List<bool> _initializedPages = new List<bool>() { false, false, false, false };
        /// <summary>
        /// Последняя открытая вкладка
        /// </summary>
        private int _currentPage;


        public IssoViewActivity(int cIsso)
        {
            InitializeComponent();
            Title = $"ИССО №{cIsso}";
            _cIsso = cIsso;
            //TreeView.InitTreeView(C_ISSO);
            if (!Application.Current.Properties.TryGetValue("current_page", out var page))
            {
                Application.Current.Properties.Add("current_page", _currentPage);
            }
            else
            {
                _currentPage = Convert.ToInt32(page);
            }
        }

        public void Dispose()
        {
            foreach (var child in Children)
            {
                ((MyBackHandleContentPage) child).Dispose();
            }
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();
            CurrentPage = Children[_currentPage];
            OnCurrentPageChanged();
        }

        /// <inheritdoc />
        /// <summary>
        /// Метод определения нажатой вкладки
        /// </summary>
        protected override async void OnCurrentPageChanged()
        {
            base.OnCurrentPageChanged();
            if (_firstLaunch)
            {
                _firstLaunch = false;
                return;
            }
            switch (Children.IndexOf(CurrentPage))
            {
                // Если мы открываем вкладку c деревом
                case 0:
                    if(!_initializedPages[0])
                    {
                        tree_content.Init(_cIsso);
                        _initializedPages[0] = true;
                    }
                    ToolbarItems.Remove(defect_content.DefectView.DefectFilterItem);
                    ToolbarItems.Remove(defect_content.DefectView.AddDefect);
                    _currentPage = 0;
                    break;
                // Если открываем вкладку с ведомостью дефектов
                case 1:
                    if(!_initializedPages[1])
                    {
                        defect_content.Initialize(_cIsso);
                        _initializedPages[1] = true;
                    }
                    if(!ToolbarItems.Contains(defect_content.DefectView.DefectFilterItem))
                        ToolbarItems.Add(defect_content.DefectView.DefectFilterItem);
                    if (!ToolbarItems.Contains(defect_content.DefectView.AddDefect))
                        ToolbarItems.Add(defect_content.DefectView.AddDefect);
                    _currentPage = 1;
                    break;
                // Если открываем вкладку с фотографиями
                case 2:
                    if(!_initializedPages[2])
                    {
                        photo_content.Initialize(_cIsso);
                        _initializedPages[2] = true;
                    }
                    ToolbarItems.Remove(defect_content.DefectView.DefectFilterItem);
                    ToolbarItems.Remove(defect_content.DefectView.AddDefect);
                    _currentPage = 2;
                    break;
                // Если открываем вкладку со схемами
                case 3:
                    if (!_initializedPages[3])
                    {
                        pdf_content.Initialize(_cIsso);
                        _initializedPages[3] = true;
                    }
                    ToolbarItems.Remove(defect_content.DefectView.DefectFilterItem);
                    ToolbarItems.Remove(defect_content.DefectView.AddDefect);
                    _currentPage = 3;
                    break;
            }
            Application.Current.Properties["current_page"] = _currentPage;
            await Application.Current.SavePropertiesAsync();
        }
    }

    // Статус вкладки
    public interface IActivatePage
    {
        void Active(bool active);
    }
}
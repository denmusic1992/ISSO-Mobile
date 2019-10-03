using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using Autoselect;
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_S
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MasterDetailPage1Master : ContentPage
    {
        /// <summary>
        /// Список
        /// </summary>
        public ListView ListView { get; set; }

        public MasterDetailPage1Master()
        {
            InitializeComponent();

            BindingContext = new MasterDetailPage1MasterViewModel();
            ListView = MenuItemsListView;
            if (App.Current.Properties.TryGetValue("user", out object user))
            {
                LabelUser.Text = "Пользователь: " + user;
            }

            SupportImg.Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("support_light.png") /*String.Format("{0}{1}", Device.OnPlatform("Icons/", "", "Assets/Icons/"), "support_light.png")*/ };
            SupportBtn.GestureRecognizers.Add(new TapGestureRecognizer() { Command = new Command(() => OpenSupportSite()) });

            OrientationChanged();
        }

        private void OpenSupportSite()
        {
            if (App.Current.Properties.TryGetValue("address", out object address) && App.Current.Properties.TryGetValue("support_port", out object port))
                Device.OpenUri(new Uri(String.Format("https://{0}:{1}", address, port)));
            else
                DependencyService.Get<IMessage>().LongAlert("невозможно перейти по данному адресу. Проверьте настройки приложения (адрес и порт службы поддержки)");
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);
            OrientationChanged();
        }

        private class MasterDetailPage1MasterViewModel : INotifyPropertyChanged
        {
            public ObservableCollection<MasterDetailPage1MenuItem> MenuItems { get; set; }

            public MasterDetailPage1MasterViewModel()
            {
                MenuItems = new ObservableCollection<MasterDetailPage1MenuItem>(new[]
                {
                    new MasterDetailPage1MenuItem { Id = 0, Title = "Автовыбор", Img = new FileImageSource{File = CommonStaffUtils.GetFilePath("autoselect_light.png")  }, TargetType = typeof(MapsActivity) },
                    new MasterDetailPage1MenuItem { Id = 1, Title = "Синхронизация", Img = new FileImageSource {File = CommonStaffUtils.GetFilePath("synchronize_light.png") }, TargetType = typeof(CommonClassesLibrary.Syncronization) },
                    new MasterDetailPage1MenuItem { Id = 2, Title = "Настройки", Img = new FileImageSource {File =  CommonStaffUtils.GetFilePath("settings_light.png") }, TargetType = typeof(SettingsActivity) }
                });
            }

            #region INotifyPropertyChanged Implementation
            public event PropertyChangedEventHandler PropertyChanged;
            void OnPropertyChanged([CallerMemberName] string propertyName = "")
            {
                if (PropertyChanged == null)
                    return;

                PropertyChanged.Invoke(this, new PropertyChangedEventArgs(propertyName));
            }
            #endregion
        }

        //protected override void OnAppearing()
        //{
        //    base.OnAppearing();
        //}
        public void SetTextForUser(string user)
        {
            LabelUser.Text = "Пользователь: " + user;
        }

        private void OrientationChanged()
        {
            var device_orientation = DependencyService.Get<IDeviceOrientation>().GetOrientation();
            if(device_orientation == DeviceOrientations.Landscape)
            {
                text_advance.IsVisible = false;
                LabelUser.IsVisible = false;
            }
            else
            {
                text_advance.IsVisible = true;
                LabelUser.IsVisible = true;
            }
        }
    }
}
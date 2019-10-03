using Autoselect;
using CommonClassesLibrary;
using CommonClassesLibrary.Interfaces;
using ISSO_I.Additional_Classes;
using ISSO_I.CustomRenderes;
using ISSO_I.MasterPage;
using LinearList;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Globalization;
using System.Linq;
using System.Runtime.CompilerServices;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MasterDetailPage1Master
    {
        public ListView ListView;

        private bool Tapped { get; set; }

        public MasterDetailPage1Master()
        {
            InitializeComponent();

            BindingContext = new MasterDetailPage1MasterViewModel();
            ListView = MenuItemsListView;
            if (Application.Current.Properties.TryGetValue("user", out var user))
            {
                LabelUser.Text = "Пользователь: " + user;
            }

            SupportImg.Source = new FileImageSource() { File = CommonStaffUtils.GetFilePath("support_light.png") };
            SupportBtn.GestureRecognizers.Add(new TapGestureRecognizer() { Command = new Command(OpenSupportSite) });
            OrientationChanged();

            ListView.ItemSelected += ListView_ItemSelected;
        }

        public void OpenSupportSite()
        {
            if (Application.Current.Properties.TryGetValue("address", out var address) && Application.Current.Properties.TryGetValue("support_port", out var port))
                Device.OpenUri(new Uri($"https://{address}:{port}"));
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
	        // ReSharper disable once MemberCanBePrivate.Local
	        public ObservableCollection<MasterDetailPage1MenuItem> MenuItems { get; }

	        public MasterDetailPage1MasterViewModel()
	        {
		        // ReSharper disable once ObjectCreationAsStatement
		        MenuItems = new ObservableCollection<MasterDetailPage1MenuItem>(new[]
		        {
			        new MasterDetailPage1MenuItem { Id = 0, Title = "Автовыбор", Img = new FileImageSource{File = CommonStaffUtils.GetFilePath("autoselect_light.png")}, TargetType = typeof(MapsActivity) },
			        new MasterDetailPage1MenuItem { Id = 1, Title = "Синхронизация", Img = new FileImageSource {File = CommonStaffUtils.GetFilePath("synchronize_light.png")}, TargetType = typeof(Syncronization) },
			        new MasterDetailPage1MenuItem { Id = 2, Title = "Настройки", Img = new FileImageSource {File = CommonStaffUtils.GetFilePath("settings_light.png")}, TargetType = typeof(SettingsActivity) }
		        });
	        }

            #region INotifyPropertyChanged Implementation
            public event PropertyChangedEventHandler PropertyChanged;

			void OnPropertyChanged([CallerMemberName] string propertyName = "")
			{
				PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
			}

			#endregion
		}

		private async void ListView_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            ListView.SelectedItem = null;
            if (Tapped) return;
            Tapped = true;
	        if (!(e.SelectedItem is MasterDetailPage1MenuItem item))
                return;
            Page page;
            if (item.TargetType == typeof(MapsActivity))
            {
                var issos = new List<HttpsIsso>();
	            var connection = ConnectionClass.CreateDatabase();
                var issosTable = connection.Query<IssosList>("SELECT I_ISSO.C_ISSO, NAME, DORNAME, W_ISSO, FULLNAME, CTYPEISSO, N_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO ").ToList();
                foreach (var isso in issosTable)
                {
                    issos.Add(new HttpsIsso(isso.C_ISSO, isso.DORNAME, isso.N_OTC_EXP, isso.C_OTC_EXP, isso.FULLNAME, isso.LATITUDE, isso.LONGITUDE, isso.LENGTH.ToString(CultureInfo.CurrentCulture), isso.W_ISSO, isso.NAME, isso.OBSTACLE));
                }
                page = new MainMapsActivity(issos.ToArray());
	            connection.Close();
            }
            else if (item.TargetType == typeof(SettingsActivity))
            {
                page = new MainSettingsActivity();
            }
            else
            {
                page = new MainSyncActivity(App.AppType);
            }
            page.Title = item.Title;
	        ((MasterDetailPage1) Application.Current.MainPage).IsPresented = false;
			await ((MyNavigationPage) ((MasterDetailPage1) Application.Current.MainPage).Detail).PushAsync(page);
            Tapped = false;
            //Navigation.PushAsync(page);

            //
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
            if(Device.Idiom == TargetIdiom.Phone)
            {
                var deviceOrientation = DependencyService.Get<IDeviceOrientation>().GetOrientation();
                if (deviceOrientation == DeviceOrientations.Landscape)
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
}
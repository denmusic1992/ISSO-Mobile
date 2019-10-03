using CommonClassesLibrary;
using ISSO_I.Additional_Classes;
using ISSO_I.CustomRenderes;
using ISSO_I.PopupTypes;
using LinearList;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using Xamarin.Forms;

namespace ISSO_I.MasterPage
{
    public class MainPageLinearList : LinearList.LinearList
    {
        private bool Tapped { get; set; }

        public MainPageLinearList(ApplicationType app) : base(app) { }

        public override ObservableCollection<Isso> BindIssos()
        {
            var issos = new ObservableCollection<Isso>();
	        var connection = ConnectionClass.CreateDatabase();
            var issosTable = connection.Query<IssosList>("SELECT I_ISSO.C_ISSO, NAME, CTYPEISSO, DORNAME, W_ISSO, N_OTC_EXP, C_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE, NAME_ISSO from I_ISSO " +
                (SelectedRoad.Equals("[Все]") ? "" : "where DORNAME = '" + SelectedRoad + "'" + " order by DORNAME, W_ISSO")).ToList();
            foreach (var isso in issosTable)
            {
                var info =
	                $"{isso.NAME} (ОТС: {isso.N_OTC_EXP}) \n{isso.DORNAME} км {isso.W_ISSO >> 16}+{isso.W_ISSO & 0xFFFF} ({isso.OBSTACLE})" +
	                $"\n(Код ИССО: {isso.C_ISSO}) {(isso.NAME_ISSO != null && !isso.NAME_ISSO.Equals("") ? $"({isso.NAME_ISSO})" : "")}";
                issos.Add(new Isso(isso.C_ISSO, info, (Otc)isso.C_OTC_EXP, App.AppType));
            }
			connection.Close();
            return issos;
        }

        public override async void CallIssoView(Isso isso)
        {
            if (Tapped) return;
            Tapped = true;
            var issoViewActivity = new IssoViewActivity(isso.CIsso);
            await ((MyNavigationPage) ((MasterDetailPage1) Application.Current.MainPage).Detail).PushAsync(issoViewActivity);
            Tapped = false;
        }

        public override void Filter_Clicked(object sender, EventArgs e)
        {
            if (Tapped) return;
            Tapped = true;
	        var connection = ConnectionClass.CreateDatabase();
            var result = connection.Query<IssosList>("select DISTINCT DORNAME from I_ISSO").ToList();
            var dornames = new ObservableCollection<ModelForSelect>();
            foreach (var res in result)
            {
                dornames.Add(new ModelForSelect(res.DORNAME, res.DORNAME == SelectedRoad));
            }
            dornames.Insert(0, new ModelForSelect("[Все]", "[Все]" == SelectedRoad));
	        //selected_road = await DisplayActionSheet("Выберите дорогу:", "Отмена", null, dornames.ToArray());
            var selectedListViewPopupPage = new SelectedListViewPopupPage(null, dornames, "Выбранная дорога");
            selectedListViewPopupPage.SaveChanges += SelectedListViewPopupPage_SaveChanges;
			var popupPage = new CommonPopupPage(selectedListViewPopupPage, selectedListViewPopupPage.Header);
            Navigation.PushPopupAsync(popupPage);
            Tapped = false;
			connection.Close();
        }

        private void SelectedListViewPopupPage_SaveChanges(object sender, EventArgs e)
        {
            var newRoad = (sender as string);
            switch (newRoad)
            {
	            case "[Все]":
		            FilterBarItem.Icon = new FileImageSource() { File = CommonStaffUtils.GetFilePath("filter_dark.png") };
		            FilterBarItem.Text = "Фильтр не выбран";
		            break;
	            case "Отмена":
	            case null:
		            return;
	            default:
		            FilterBarItem.Icon = new FileImageSource() { File = CommonStaffUtils.GetFilePath("filter_enable_light.png") };
		            FilterBarItem.Text = $"Фильтр: {newRoad}";
		            break;
            }
            SelectedRoad = newRoad;
            Issos = BindIssos();
            IssoList.ItemsSource = Issos;
            //DependencyService.Get<IMessage>().ShortAlert(String.Format("Выбран фильтр по дороге: {0}", new_road));
        }
    }
}

using CommonClassesLibrary;
using Rg.Plugins.Popup.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I.PopupTypes
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class SelectedListViewPopupPage
    {
	    private readonly View _view;
        public event EventHandler SaveChanges;

	    public string Header;

        public SelectedListViewPopupPage(View view, IReadOnlyCollection<ModelForSelect> list, string description)
        {
	        InitializeComponent();
	        _view = view;

            lvitems.ItemsSource = list;
            Header = description;
            var selected = view != null ? list.ToList().Find(x => x.Content == ((Label) ((Grid) view).Children[0]).Text) : list.ToList().Find(x => x.Selected);
            lvitems.SelectedItem = selected;
            lvitems.ScrollTo(lvitems.SelectedItem, ScrollToPosition.Center, false);
        }

        private async void button_confirm_Clicked(object sender, EventArgs e)
        {
            // Здесь выберем элемент, довлетворяющий выбранному элементу списка
            var res = lvitems.SelectedItem as ModelForSelect;
            if(_view != null)
            {
                ((Label) ((Grid) _view).Children[0]).Text = res?.Content;
                await Navigation.PopPopupAsync();
                SaveChanges?.Invoke(_view, EventArgs.Empty);
            }
            //Передаем в другом случае (для LinearList)
            else
            {
                await Navigation.PopPopupAsync();
                SaveChanges?.Invoke(res?.Content, EventArgs.Empty);
            }
        }
    }
}
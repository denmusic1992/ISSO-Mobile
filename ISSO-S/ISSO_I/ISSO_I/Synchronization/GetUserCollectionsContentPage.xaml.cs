using ISSO_I.Additional_Classes;
using System;
using System.Collections.ObjectModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ISSO_I
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class GetUserCollectionsContentPage
	{
        public event EventHandler ItemSelectedEvent;

        private int _selectedCollection;

        private ObservableCollection<UserCollections> UserCollection { get; set; }

        public GetUserCollectionsContentPage (ObservableCollection<UserCollections> userCollection, int selectedCollection)
		{
            Title = "Синхронизация. Выбор выгрузки";
			InitializeComponent();
            UserCollection = userCollection;
            _selectedCollection = selectedCollection;
            userCollection[selectedCollection].IsChecked = true;
            lvGetCollections.ItemsSource = UserCollection;
        }

        /// <summary>
        /// обработчик нажатия на cell
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lvGetCollections_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            var index = ((ObservableCollection<UserCollections>) lvGetCollections.ItemsSource).IndexOf(e.Item as UserCollections);
            foreach (var collection in UserCollection)
                collection.IsChecked = false;
            UserCollection[index].IsChecked = true;
            _selectedCollection = index;
            ((ListView)sender).SelectedItem = null;
            ((ListView)sender).ItemsSource = null;
            ((ListView)sender).ItemsSource = UserCollection;
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();
            ItemSelectedEvent?.Invoke(_selectedCollection, EventArgs.Empty);
            //UserCollection.Clear();
            //BindingContext = null;
            //Content = null;
        }

        public override void Dispose()
        {
            lvGetCollections.ItemsSource = null;
            UserCollection.Clear(); UserCollection = null;


            BindingContext = null;
            Content = null;

            GC.Collect();
        }
    }

}
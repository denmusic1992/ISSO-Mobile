using CommonClassesLibrary.BackHandlers;
using Xamarin.Forms;

namespace ISSO_I.CustomRenderes
{
    public class MyNavigationPage : NavigationPage
    {
        public MyNavigationPage()
        { }
        public MyNavigationPage(Page root) : base(root) { Init(); }

        /// <summary>
        /// Метод инициализации очистки памяти в приложении при закрытии контролов
        /// </summary>
        private void Init()
        {
            // Очистка памяти для закрытых контролов
            Popped += (sender, e) =>
            {
                switch(e.Page)
                {
                    case MyBackHandleContentPage handler:
                        handler.Dispose();
                        break;
                    case MyBackHandleTabbedPage handler:
                        handler.Dispose();
                        break;
                    case IssoViewActivity handler:
                        handler.Dispose();
                        break;
                }
                //((App.Current.MainPage as MasterDetailPage1).Detail as MyNavigationPage).PopAsync();
                //if(e.Page.BindingContext != null) e.Page.BindingContext = null;
            };
        }

        /// <inheritdoc />
        /// <summary>
        /// Отключение бокового меню при добавлении на главный экран новых страниц
        /// </summary>
        /// <param name="child"></param>
        protected override void OnChildAdded(Element child)
        {
            base.OnChildAdded(child);
            if (Navigation.NavigationStack.Count > 1)
                ((MasterDetailPage1)Application.Current.MainPage).IsGestureEnabled = false;
                //App.detailPage.IsGestureEnabled = false;

        }

        /// <inheritdoc />
        /// <summary>
        /// Включение бокового меню при наличии только одной страницы на главном экране
        /// </summary>
        /// <param name="child"></param>
        protected override void OnChildRemoved(Element child)
        {
            base.OnChildRemoved(child);
            if (Navigation.NavigationStack.Count < 2)
                ((MasterDetailPage1)Application.Current.MainPage).IsGestureEnabled = true;
            //App.detailPage.IsGestureEnabled = true;
        }
    }
}

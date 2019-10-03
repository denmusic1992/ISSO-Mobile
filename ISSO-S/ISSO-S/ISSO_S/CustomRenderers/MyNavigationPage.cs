using System;
using Xamarin.Forms;

namespace ISSO_S.Droid.CustomRenderers
{
    public class MyNavigationPage : NavigationPage
    {
        public MyNavigationPage() { }
        public MyNavigationPage(Page root) : base(root) { }


        public Action CustomBackButtonAction { get; set; }

        public static readonly BindableProperty EnableBackButtonOverrideProperty =
               BindableProperty.Create(
               nameof(EnableBackButtonOverride),
               typeof(bool),
               typeof(MyContentPage),
               false);

        /// <inheritdoc />
        /// <summary>
        /// Отключение бокового меню при добавлении на главный экран новых страниц
        /// </summary>
        /// <param name="child"></param>
        protected override void OnChildAdded(Element child)
        {
            base.OnChildAdded(child);
            if (Navigation.NavigationStack.Count > 1)
                App.DetailPage.IsGestureEnabled = false;
        }

        /// <summary>
        /// Включение бокового меню при наличии только одной страницы на главном экране
        /// </summary>
        /// <param name="child"></param>
        protected override void OnChildRemoved(Element child)
        {
            base.OnChildRemoved(child);
            if (Navigation.NavigationStack.Count < 2)
                App.DetailPage.IsGestureEnabled = true;
        }

        /// <summary>
        /// Gets or Sets Custom Back button overriding state
        /// </summary>
        public bool EnableBackButtonOverride
        {
            get => (bool)GetValue(EnableBackButtonOverrideProperty);
	        set => SetValue(EnableBackButtonOverrideProperty, value);
        }
    }
}

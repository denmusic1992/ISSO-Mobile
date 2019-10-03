using System;
using Xamarin.Forms;

namespace CommonClassesLibrary.BackHandlers
{
    public abstract class MyBackHandleTabbedPage : TabbedPage, IDisposable
    {
        public Action CustomBackButtonAction { get; set; }


	    protected MyBackHandleTabbedPage()
        {
            this.CustomBackButtonAction = Dispose;
        }

        public static readonly BindableProperty EnableBackButtonOverrideProperty =
            BindableProperty.Create(nameof(EnableBackButtonOverride), typeof(bool), typeof(MyBackHandleTabbedPage), false);

        /// <summary>
        /// 
        /// </summary>
        public bool EnableBackButtonOverride
        {
            get => (bool)GetValue(EnableBackButtonOverrideProperty);
	        set => SetValue(EnableBackButtonOverrideProperty, value);
        }

        public abstract void Dispose();
    }
}

using Rg.Plugins.Popup.Pages;
using System;
using Xamarin.Forms;

namespace CommonClassesLibrary.BackHandlers
{
    public abstract class MyBackHandlePopupPage : PopupPage, IDisposable
    {
        public Action CustomBackButtonAction { get; set; }


	    protected MyBackHandlePopupPage()
        {
            CustomBackButtonAction = Dispose;
        }

        public static readonly BindableProperty EnableBackButtonOverrideProperty =
            BindableProperty.Create(nameof(EnableBackButtonOverride), typeof(bool), typeof(MyBackHandleContentPage), false);

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

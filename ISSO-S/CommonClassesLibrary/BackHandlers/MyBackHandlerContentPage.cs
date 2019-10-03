using System;
using Xamarin.Forms;

namespace CommonClassesLibrary.BackHandlers
{
	public abstract class MyBackHandleContentPage : ContentPage, IDisposable
	{

        public Action CustomBackButtonAction { get; set; }


		protected MyBackHandleContentPage(bool disposable = true)
		{
			if(disposable)
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

        /// <summary>
        /// 
        /// </summary>
        public abstract void Dispose();

        public void OnNavigationBackButtonPressed()
        {
            Dispose();
        }
    }
}
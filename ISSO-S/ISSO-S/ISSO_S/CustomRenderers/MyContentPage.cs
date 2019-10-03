using System;
using Xamarin.Forms;

namespace ISSO_S.Droid.CustomRenderers
{
	public class MyContentPage : TabbedPage
	{
        public Action CustomBackButtonAction { get; set; }

        public static readonly BindableProperty EnableBackButtonOverrideProperty =
               BindableProperty.Create(
               nameof(EnableBackButtonOverride),
               typeof(bool),
               typeof(MyContentPage),
               false);

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
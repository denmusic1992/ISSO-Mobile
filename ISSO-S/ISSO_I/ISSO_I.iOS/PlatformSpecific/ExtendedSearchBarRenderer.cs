using System;
using System.ComponentModel;
using CommonClassesLibrary.CustomRenderers;
using ISSO_I.iOS.Extensions;
using ISSO_I.iOS.PlatformSpecific;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(ExtendedSearchBar), typeof(ExtendedSearchBarRenderer))]
namespace ISSO_I.iOS.PlatformSpecific
{
    public class ExtendedSearchBarRenderer : SearchBarRenderer
    {
        public new ExtendedSearchBar Element => (ExtendedSearchBar)base.Element;

	    protected override void OnElementChanged(ElementChangedEventArgs<SearchBar> e)
        {
            base.OnElementChanged(e);

            if (e.NewElement != null)
            {
                UpdateKeyboard();
            }
        }

        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);

            if (e.PropertyName == ExtendedSearchBar.KeyboardProperty.PropertyName)
            {
                UpdateKeyboard();
            }
        }

        private void OnToolbarSearchButtonClicked(object sender, EventArgs e)
        {
            // use reflection to call their SearchButtonClicked event handler, because it is private
            var searchPressedMethod = GetType().BaseType?.GetMethod("OnSearchButtonClicked", System.Reflection.BindingFlags.Instance | System.Reflection.BindingFlags.NonPublic);
            searchPressedMethod?.Invoke(this, new[] { sender, e });
        }

        private void UpdateKeyboard()
        {
            Control.ApplyKeyboard(Element.Keyboard);
            if (Device.Idiom == TargetIdiom.Phone && (Element.Keyboard == Keyboard.Telephone || Element.Keyboard == Keyboard.Numeric))
            {
                Control.InputAccessoryView = new UIToolbar(new CoreGraphics.CGRect(0.0f, 0.0f, Frame.Size.Width, 44.0f))
                {
                    Items = new[]
                    {
                    new UIBarButtonItem(UIBarButtonSystemItem.FlexibleSpace),
                    new UIBarButtonItem(UIBarButtonSystemItem.Search, OnToolbarSearchButtonClicked)
                    }
                };
            }
            else
            {
                Control.InputAccessoryView = null;
            }
        }
    }
}
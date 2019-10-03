using System.ComponentModel;
using Android.Content;
using CommonClassesLibrary.CustomRenderers;
using ISSO_I.Droid.PlatformSpecific;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(ExtendedSearchBar), typeof(ExtendedSearchBarRenderer))]
namespace ISSO_I.Droid.PlatformSpecific
{
    public class ExtendedSearchBarRenderer : SearchBarRenderer
    {
        public ExtendedSearchBarRenderer(Context context) : base(context) { }

        public new ExtendedSearchBar Element => (ExtendedSearchBar)base.Element;

	    protected override void OnElementChanged(ElementChangedEventArgs<SearchBar> e)
        {
            base.OnElementChanged(e);

	        Control?.SetInputType(Element.Keyboard.ToInputType());
        }

        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);

            if (e.PropertyName == ExtendedSearchBar.KeyboardProperty.PropertyName)
            {
                Control.SetInputType(Element.Keyboard.ToInputType());
            }
        }
    }
}
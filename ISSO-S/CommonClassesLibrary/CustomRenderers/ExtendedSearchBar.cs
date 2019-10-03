using Xamarin.Forms;

namespace CommonClassesLibrary.CustomRenderers
{
    public class ExtendedSearchBar : SearchBar
    {
        public static readonly BindableProperty KeyboardProperty = BindableProperty.Create(nameof(Keyboard), typeof(Keyboard), typeof(ExtendedSearchBar), Keyboard.Default);
        public Keyboard Keyboard
        {
            get => (Keyboard)GetValue(KeyboardProperty);
	        set => SetValue(KeyboardProperty, value);
        }
    }
}

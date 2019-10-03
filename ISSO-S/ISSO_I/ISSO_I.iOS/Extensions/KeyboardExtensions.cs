using System.Reflection;
using UIKit;
using Xamarin.Forms;

namespace ISSO_I.iOS.Extensions
{
    public static class KeyboardExtensions
    {
        public static void ApplyKeyboard(this IUITextInputTraits textInput, Keyboard keyboard)
        {
            textInput.AutocapitalizationType = UITextAutocapitalizationType.None;
            textInput.AutocorrectionType = UITextAutocorrectionType.No;
            textInput.SpellCheckingType = UITextSpellCheckingType.No;

            if (keyboard == Keyboard.Default)
            {
                textInput.AutocapitalizationType = UITextAutocapitalizationType.Sentences;
                textInput.AutocorrectionType = UITextAutocorrectionType.Default;
                textInput.SpellCheckingType = UITextSpellCheckingType.Default;
            }
            else if (keyboard == Keyboard.Chat)
            {
                textInput.AutocapitalizationType = UITextAutocapitalizationType.Sentences;
                textInput.AutocorrectionType = UITextAutocorrectionType.Yes;
            }
            else if (keyboard == Keyboard.Email)
            {
                textInput.KeyboardType = UIKeyboardType.EmailAddress;
            }
            else if (keyboard == Keyboard.Numeric)
            {
                textInput.KeyboardType = UIKeyboardType.DecimalPad;
            }
            else if (keyboard == Keyboard.Telephone)
            {
                textInput.KeyboardType = UIKeyboardType.PhonePad;
            }
            else if (keyboard == Keyboard.Text)
            {
                textInput.AutocapitalizationType = UITextAutocapitalizationType.Sentences;
                textInput.AutocorrectionType = UITextAutocorrectionType.Yes;
                textInput.SpellCheckingType = UITextSpellCheckingType.Yes;
            }
            else if (keyboard == Keyboard.Url)
            {
                textInput.KeyboardType = UIKeyboardType.Url;
            }
            else
            {
                try
                {
	                // ReSharper disable once PossibleNullReferenceException
                    var flags = (KeyboardFlags) keyboard.GetType()
	                    .GetProperty("Flags", BindingFlags.Instance | BindingFlags.NonPublic)?.GetValue(keyboard);

                    textInput.AutocapitalizationType = flags.HasFlag(KeyboardFlags.CapitalizeSentence) ? UITextAutocapitalizationType.Sentences : UITextAutocapitalizationType.None;
                    textInput.AutocorrectionType = flags.HasFlag(KeyboardFlags.Suggestions) ? UITextAutocorrectionType.Yes : UITextAutocorrectionType.No;
                    textInput.SpellCheckingType = flags.HasFlag(KeyboardFlags.Spellcheck) ? UITextSpellCheckingType.Yes : UITextSpellCheckingType.No;
                }
	            catch
	            {
		            // ignored
	            }
            }
        }
    }
}
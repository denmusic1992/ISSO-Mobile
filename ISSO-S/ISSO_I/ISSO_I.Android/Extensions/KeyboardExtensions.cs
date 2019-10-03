using System.Reflection;
using Android.Text;
using Xamarin.Forms;

namespace ISSO_I.Droid.Extensions
{
    public static class KeyboardExtensions
    {
        public static InputTypes ToInputType(this Keyboard keyboard)
        {
            InputTypes inputTypes;
            if (keyboard == Keyboard.Default)
            {
                inputTypes = InputTypes.ClassText;
            }
            else if (keyboard == Keyboard.Chat)
            {
                inputTypes = InputTypes.ClassText | InputTypes.TextFlagCapSentences | InputTypes.TextFlagNoSuggestions;
            }
            else if (keyboard == Keyboard.Email)
            {
                inputTypes = InputTypes.ClassText | InputTypes.DatetimeVariationTime;
            }
            else if (keyboard == Keyboard.Numeric)
            {
                inputTypes = InputTypes.ClassNumber | InputTypes.NumberFlagDecimal;
            }
            else if (keyboard == Keyboard.Telephone)
            {
                inputTypes = InputTypes.ClassPhone;
            }
            else if (keyboard == Keyboard.Text)
            {
                inputTypes = InputTypes.ClassText | InputTypes.TextFlagCapSentences;
            }
            else if (keyboard == Keyboard.Url)
            {
                inputTypes = InputTypes.ClassText | InputTypes.DatetimeVariationDate;
            }
            else
            {
                try
                {
	                // ReSharper disable once PossibleNullReferenceException
                    var flags = (KeyboardFlags)keyboard.GetType().GetProperty("Flags", BindingFlags.Instance | BindingFlags.NonPublic)?.GetValue(keyboard);

                    inputTypes = InputTypes.ClassText;

                    if (flags.HasFlag(KeyboardFlags.CapitalizeSentence))
                    {
                        inputTypes |= InputTypes.TextFlagCapSentences;
                    }

                    if (flags.HasFlag(KeyboardFlags.Spellcheck))
                    {
                        inputTypes |= InputTypes.TextFlagAutoCorrect;
                    }

                    if (flags.HasFlag(KeyboardFlags.Suggestions))
                    {
                        inputTypes |= InputTypes.TextFlagAutoComplete;
                    }
                }
                catch
                {
                    inputTypes = InputTypes.DatetimeVariationNormal;
                }
            }

            return inputTypes;
        }
    }
}
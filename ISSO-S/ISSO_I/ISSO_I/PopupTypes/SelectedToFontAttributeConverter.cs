using System;
using System.Globalization;
using Xamarin.Forms;

namespace ISSO_I.PopupTypes
{
    public class SelectedToFontAttributeConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
	        if (!(value is bool b)) return FontAttributes.None;
	        return b ? FontAttributes.Bold : FontAttributes.None;
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}

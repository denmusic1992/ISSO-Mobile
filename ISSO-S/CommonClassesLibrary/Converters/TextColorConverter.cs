using System;
using System.Globalization;
using Xamarin.Forms;

namespace CommonClassesLibrary.Converters
{
    public class TextColorConverter : IValueConverter
    {
	    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
	    {
		    if ((bool) value)
		    {
			    return Color.LightGray;
		    }

		    return Color.Black;
	    }

	    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
	    {
		    throw new NotImplementedException();
	    }
    }
}

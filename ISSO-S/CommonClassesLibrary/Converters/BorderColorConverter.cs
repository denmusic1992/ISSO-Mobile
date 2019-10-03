using System;
using System.Globalization;
using Xamarin.Forms;

namespace CommonClassesLibrary.Converters
{
	public class BorderColorConverter : IValueConverter
	{
		public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
		{
			if ((bool) value)
			{
				return Color.Transparent;
			}

			return Color.Accent;
		}

		public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
		{
			throw new NotImplementedException();
		}
	}
}

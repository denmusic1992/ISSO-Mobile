using Plugin.DeviceInfo;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Plugin.DeviceInfo.Abstractions;
using Plugin.Permissions;
using Plugin.Permissions.Abstractions;
using Xamarin.Forms;

namespace CommonClassesLibrary
{
	public class CommonStaffUtils
	{

		/// <summary>
		/// Радиус Земли (м)
		/// </summary>
		public const double Rad = 6371200;

		public const float StandardImageWidth = 720;

		public const float StandardImageHeight = 480;

		/// <summary>
		/// Получить расстояние до объекта
		/// </summary>
		/// <param name="xIsso">координата х ИССО</param>
		/// <param name="yIsso">координата у ИССО</param>
		/// <param name="xCur">координата х текущая</param>
		/// <param name="yCur">координата у текущая</param>
		/// <param name="lengthIsso"></param>
		/// <returns>расстояние до объекта</returns>
		public static double GetDistance(double xIsso, double yIsso, double xCur, double yCur, double lengthIsso)
		{
			var lat1 = ToRadians(xIsso);
			var lng1 = ToRadians(yIsso);

			var lat2 = ToRadians(xCur);
			var lng2 = ToRadians(yCur);

			var x = Math.Sin(lat1) * Math.Sin(lat2) + Math.Cos(lat1) * Math.Cos(lat2) * Math.Cos(lng2 - lng1);
			var y = Math.Sqrt(Math.Pow(Math.Cos(lat2) * Math.Sin(lng2 - lng1), 2) + Math.Pow(Math.Cos(lat1) * Math.Sin(lat2) - Math.Sin(lat1) * Math.Cos(lat2) * Math.Cos(lng2 - lng1), 2));
			var dist = Math.Atan(y / x) * Rad;
			return Math.Max(dist - lengthIsso / 2.0, 0);
		}

		/// <summary>
		/// Перевод градусов в радианы
		/// </summary>
		/// <param name="degrees"></param>
		/// <returns></returns>
		public static double ToRadians(double degrees)
		{
			return degrees / (180.0 / Math.PI);
		}

		/// <summary>
		/// Перевод радианов в градусы
		/// </summary>
		/// <param name="radians"></param>
		/// <returns></returns>
		public static double ToDegrees(double radians)
		{
			return radians / (180.0 / Math.PI);
		}

		/// <summary>
		/// Конвертация из DateTime в Milliseconds since epoch
		/// </summary>
		/// <param name="date"></param>
		/// <returns></returns>
		public static long ConvertToUnixTimestamp(DateTime date)
		{
			var origin = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc);
			var diff = date - origin;
			return Convert.ToInt64(diff.TotalMilliseconds);
		}

		public static int RandomNumber(int min, int max)
		{
			var random = new Random();
			return random.Next(min, max);
		}

		public static string GetFilePath(string fileName)
		{
			switch (CrossDeviceInfo.Current.Platform)
			{
				case Platform.iOS:
					return $"Icons/{fileName}";
				case Platform.WindowsPhone:
					return $"Assets/Icons/{fileName}";
				default:
					return fileName;
			}
		}

		/// <summary>
		/// Погрешность для сравнения чисел типа double
		/// </summary>
		public const double DoubleTolerance = 1e-16;

		/// <summary>
		/// Погрешность для сравнения чисел типа float
		/// </summary>
		public const float FloatTolerance = 1e-8f;

		public static async Task<bool> CheckPermissions(Permission permission)
		{
			var permissionStatus = await CrossPermissions.Current.CheckPermissionStatusAsync(permission);
			var request = false;
			if (permissionStatus == PermissionStatus.Denied)
			{
				if (Device.RuntimePlatform == Device.iOS)
				{

					var title = $"{permission} Permission";
					var question = $"To use this plugin the {permission} permission is required. Please go into Settings and turn on {permission} for the app.";
					const string positive = "Settings";
					const string negative = "Maybe Later";
					var task = Application.Current?.MainPage?.DisplayAlert(title, question, positive, negative);
					if (task == null)
						return false;

					var result = await task;
					if (result)
					{
						CrossPermissions.Current.OpenAppSettings();
					}

					return false;
				}

				request = true;

			}

			if (!request && permissionStatus == PermissionStatus.Granted) return true;
			{
				var newStatus = await CrossPermissions.Current.RequestPermissionsAsync(permission);
				if (!newStatus.ContainsKey(permission) || newStatus[permission] == PermissionStatus.Granted) return true;
				var title = $"{permission} Permission";
				var question = $"To use the plugin the {permission} permission is required.";
				const string positive = "Settings";
				const string negative = "Maybe Later";
				var task = Application.Current?.MainPage?.DisplayAlert(title, question, positive, negative);
				if (task == null)
					return false;

				var result = await task;
				if (result)
				{
					CrossPermissions.Current.OpenAppSettings();
				}
				return false;
			}
		}

		//public static string GetDevicePath(string file_name)
		//{
		//    switch(Device.RuntimePlatform)
		//    {
		//        case Device.Android:
		//            return String.Format("{0}{1}", "Icons/", file_name);
		//        case Device.iOS:
		//            return String.Format("{0}{1}", "Assets/Icons/", file_name);
		//        default:
		//            return file_name;
		//    }
		//}

		/// <summary>
		/// Таблицы, на которые накладываются некоторые ограничения
		/// </summary>
		public static Dictionary<string, TableState> TableStates { get; set; } = new Dictionary<string, TableState>()
		{
			{ "v_isso.p1_latitude", TableState.Readonly },
			{ "v_isso.p2_latitude", TableState.Readonly },
			{ "v_isso.p1_longitude", TableState.Readonly },
			{ "v_isso.p2_longitude", TableState.Readonly },
			{ "i_kniga.*", TableState.Skip },
			{ "i_checkpoint.*", TableState.Skip },
			{ "i_plan_kam.*", TableState.Skip },
			{ "i_defect.*", TableState.Invisible },
			{ "i_otc.*", TableState.Readonly },
			{ "v_rating.*", TableState.Readonly }
		};
	}

	public static class GeoCodeCalc
	{
		public const double EarthRadiusInMiles = 3956.0;
		public const double EarthRadiusInKilometers = 6367.0;

		public static double ToRadian(double val) { return val * (Math.PI / 180); }
		public static double DiffRadian(double val1, double val2) { return ToRadian(val2) - ToRadian(val1); }

		public static double CalcDistance(double lat1, double lng1, double lat2, double lng2)
		{
			return CalcDistance(lat1, lng1, lat2, lng2, GeoCodeCalcMeasurement.Kilometers);
		}

		public static double CalcDistance(double lat1, double lng1, double lat2, double lng2, GeoCodeCalcMeasurement m)
		{
			var radius = EarthRadiusInMiles;

			if (m == GeoCodeCalcMeasurement.Kilometers) { radius = EarthRadiusInKilometers; }
			return radius * 2 * Math.Asin(Math.Min(1, Math.Sqrt((Math.Pow(Math.Sin((DiffRadian(lat1, lat2)) / 2.0), 2.0) + Math.Cos(ToRadian(lat1)) * Math.Cos(ToRadian(lat2)) * Math.Pow(Math.Sin((DiffRadian(lng1, lng2)) / 2.0), 2.0)))));
		}
	}


	/// <summary>
	/// Простой класс, описывающий Геокоординату
	/// </summary>
	public class GeoCoords
	{
		public double Latitude { get; set; }
		public double Longitude { get; set; }

		public GeoCoords() { }

		public GeoCoords(double latitude, double longitude)
		{
			Latitude = latitude;
			Longitude = longitude;
		}
	}

	public enum TableState
	{
		Readonly,
		Skip,
		Invisible
	}

	public enum GeoCodeCalcMeasurement
	{
		Miles = 0,
		Kilometers = 1
	}

}

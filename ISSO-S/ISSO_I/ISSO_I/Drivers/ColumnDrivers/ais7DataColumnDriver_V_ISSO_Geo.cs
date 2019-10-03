using System;
using System.Threading;
// ReSharper disable InconsistentNaming

namespace ISSO_I.Drivers
{
	// ReSharper disable once InconsistentNaming
    public class Ais7DataColumnDriver_V_ISSO_GEO : Ais7DataColumnDriver
    {
        private static double GetAPoint(double value) { return value - Math.Truncate(value); }
        private static string GetText(double value)
        {
            return
	            $"{Math.Truncate(value):f0}˚{System.Convert.ToInt32(Math.Truncate(GetAPoint(value) * 60)).ToString00()}ʹ{(GetAPoint(GetAPoint(value) * 60) * 60).ToString00("F1")}ʺ";
        }



        public override object Convert(object source)
        {
            return source is double d ?
                GetText(d) :
                base.Convert(source);
        }
    }

    public static class DoubleExtension
    {
        public static string ToString00(this double value, string format)
        {
            var v = value.ToString(format);
            while (v.Split(Thread.CurrentThread.CurrentCulture.NumberFormat.NumberDecimalSeparator[0])[0].Length < 2) v = "0" + v;
            return v;
        }

        public static string ToString00(this int value) { return value.ToStringN(2); }
    }

    public class Ais7DataColumnDriver_V_ISSO_P1_LATITUDE : Ais7DataColumnDriver_V_ISSO_GEO { }
    public class Ais7DataColumnDriver_V_ISSO_P2_LATITUDE: Ais7DataColumnDriver_V_ISSO_GEO { }
    public class Ais7DataColumnDriver_V_ISSO_P1_LONGITUDE : Ais7DataColumnDriver_V_ISSO_GEO { }
    public class Ais7DataColumnDriver_V_ISSO_P2_LONGITUDE: Ais7DataColumnDriver_V_ISSO_GEO { }
	public class Ais7DataColumnDriver_I_ISSO_P1_LATITUDE : Ais7DataColumnDriver_V_ISSO_GEO { }
	public class Ais7DataColumnDriver_I_ISSO_P2_LATITUDE: Ais7DataColumnDriver_V_ISSO_GEO { }
	public class Ais7DataColumnDriver_I_ISSO_P1_LONGITUDE : Ais7DataColumnDriver_V_ISSO_GEO { }
	public class Ais7DataColumnDriver_I_ISSO_P2_LONGITUDE: Ais7DataColumnDriver_V_ISSO_GEO { }
}

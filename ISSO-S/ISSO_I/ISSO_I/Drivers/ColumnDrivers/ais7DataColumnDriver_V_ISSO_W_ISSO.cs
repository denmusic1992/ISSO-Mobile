using System;

namespace ISSO_I.Drivers
{
	// ReSharper disable once InconsistentNaming
    public class Ais7DataColumnDriver_V_ISSO_W_ISSO: Ais7DataColumnDriver
    {
        public override object Convert(object source)
        {
            return /*source is Int32 ?*/
	            $"{(int) source >> 16}+{((int) source & 65535).ToStringN(3)}"; /*:
                base.Convert(source);*/
        }

        public override Type ResultValueType => typeof(string);
    }

    public static class IntExtension
    {
        public static string ToStringN(this int value, int n)
        {
            var v = value.ToString();
            while (v.Length < n) v = "0" + v;
            return v;
        }
    }
}

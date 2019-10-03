using System;

namespace ISSO_S.iOS.Extensions
{
    public static class DoubleExtensions
    {
        public static double Clamp (this double self, double min, double max)
        {
            return Math.Min(max, Math.Max(self, min));
        }
    }
}

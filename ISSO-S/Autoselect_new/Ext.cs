using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Autoselect
{
    public class HttpsIsso
    {
        /// <summary>
        /// Номер ИССО
        /// </summary>
        public int CIsso { get; set; }
        /// <summary>
        /// Длина
        /// </summary>
        public String Length { get; set; }
        /// <summary>
        /// Наименование дороги
        /// </summary>
        public String DorName { get; set; }
        /// <summary>
        /// Представление
        /// </summary>
        public int WIsso { get; set; }
        /// <summary>
        /// Препятствие
        /// </summary>
        public String Obstacle { get; set; }
        /// <summary>
        /// Наименование ИССО
        /// </summary>
        public String Name { get; set; }
        /// <summary>
        /// Полное имя ИССО
        /// </summary>
        public String FullName { get; set; }
        /// <summary>
        /// Координата x ИССО
        /// </summary>
        public Double Latitude { get; set; }
        /// <summary>
        /// Координата y ИССО
        /// </summary>
        public Double Longitude { get; set; }
        /// <summary>
        /// ОТС экспертная (числовая)
        /// </summary>
        public int ExpertRatingNumeric { get; set; }
        /// <summary>
        /// ОТС экспертная
        /// </summary>
        public String ExpertRating { get; set; }

        
        public HttpsIsso() { }

        public HttpsIsso(int CIsso, String DorName, String ExpertRating, int ExpertRatingNumeric, String FullName, Double Latitude, Double Longitude,
                         String Length, int WIsso, String Name, String Obstacle)
        {
            this.CIsso = CIsso;
            this.DorName = DorName;
            this.ExpertRating = ExpertRating;
            this.ExpertRatingNumeric = ExpertRatingNumeric;
            this.FullName = FullName;
            this.Latitude = Latitude;
            this.Longitude = Longitude;
            this.Length = Length;
            this.WIsso = WIsso;
            this.Name = Name;
            this.Obstacle = Obstacle;
        }
    }

    /// <summary>
    /// Доп. функции
    /// </summary>
    public class Ext
    {
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
    }

    /// <summary>
    /// Вспомогательный класс
    /// </summary>
    class Vector2D
    {
        public double x = 0.0, y = 0.0;

        public Vector2D(double _x, double _y)
        {
            x = _x;
            y = _y;
        }

        public Vector2D Minus(Vector2D right) { return new Vector2D(x - right.x, y - right.y); }

        public double DotProduct(Vector2D right)
        {
            return x * right.x + y * right.y;
        }
        public double LengthVector(Vector2D second) { return Math.Sqrt(Math.Pow(x - second.x, 2) + Math.Pow(y - second.y, 2)); }
    }

    public class ArrayVector : IDisposable
    {
        public List<Double> distances;
        public List<Int32> indexes;

        public ArrayVector()
        {
            distances = new List<double>();
            indexes = new List<int>();
        }

        public void Dispose()
        {
            this.distances = null;
            this.indexes = null;
        }

        public double GetDistance(int index)
        {
            return distances[index];
        }
        public int GetIndex(int index)
        {
            return indexes[index];
        }
    }
}

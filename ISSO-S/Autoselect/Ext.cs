using System;
using System.Collections.Generic;

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
        public string Length { get; set; }
        /// <summary>
        /// Наименование дороги
        /// </summary>
        public string DorName { get; set; }
        /// <summary>
        /// Представление
        /// </summary>
        public int WIsso { get; set; }
        /// <summary>
        /// Препятствие
        /// </summary>
        public string Obstacle { get; set; }
        /// <summary>
        /// Наименование ИССО
        /// </summary>
        public string Name { get; set; }
        /// <summary>
        /// Полное имя ИССО
        /// </summary>
        public string FullName { get; set; }
        /// <summary>
        /// Координата x ИССО
        /// </summary>
        public double Latitude { get; set; }
        /// <summary>
        /// Координата y ИССО
        /// </summary>
        public double Longitude { get; set; }
        /// <summary>
        /// ОТС экспертная (числовая)
        /// </summary>
        public int ExpertRatingNumeric { get; set; }
        /// <summary>
        /// ОТС экспертная
        /// </summary>
        public string ExpertRating { get; set; }

        
        public HttpsIsso() { }

        public HttpsIsso(int cIsso, string dorName, string expertRating, int expertRatingNumeric, string fullName, double latitude, double longitude,
                         string length, int wIsso, string name, string obstacle)
        {
            CIsso = cIsso;
            DorName = dorName;
            ExpertRating = expertRating;
            ExpertRatingNumeric = expertRatingNumeric;
            FullName = fullName;
            Latitude = latitude;
            Longitude = longitude;
            Length = length;
            WIsso = wIsso;
            Name = name;
            Obstacle = obstacle;
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
        public double X, Y;

        public Vector2D(double x, double y)
        {
            X = x;
            Y = y;
        }

        public Vector2D Minus(Vector2D right) { return new Vector2D(X - right.X, Y - right.Y); }

        public double DotProduct(Vector2D right)
        {
            return X * right.X + Y * right.Y;
        }
        public double LengthVector(Vector2D second) { return Math.Sqrt(Math.Pow(X - second.X, 2) + Math.Pow(Y - second.Y, 2)); }
    }

    public class ArrayVector : IDisposable
    {
        public List<double> Distances;
        public List<int> Indexes;

        public ArrayVector()
        {
            Distances = new List<double>();
            Indexes = new List<int>();
        }

        public void Dispose()
        {
            Distances = null;
            Indexes = null;
        }

        public double GetDistance(int index)
        {
            return Distances[index];
        }
        public int GetIndex(int index)
        {
            return Indexes[index];
        }
    }
}

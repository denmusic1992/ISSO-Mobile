using System;
using System.Reflection;

namespace ISSO_I.Drivers
{
    public abstract class Ais7DataColumnDriver
    {
        /// <summary>
        /// Тип значения, возвращаемого при преобразовании
        /// </summary>
        public virtual Type ResultValueType => null;

	    /// <summary>
        /// Преобразование значения
        /// </summary>
        /// <param name="source"></param>
        /// <returns></returns>
        public virtual object Convert(object source)
        {
            return source;
        }
        

        /// <summary>
        /// Создание драйвера
        /// </summary>
        /// <param name="tableName"></param>
        /// <param name="columnName"></param>
        /// <returns></returns>
        public static Ais7DataColumnDriver Create(string tableName, string columnName)
        {
            var tpS = $"{typeof(Ais7DataColumnDriver)}_{tableName.ToUpper()}_{columnName.ToUpper()}";
            if (Type.GetType(tpS) != null && Type.GetType(tpS).IsSubclassOf(typeof(Ais7DataColumnDriver)))
                return (Ais7DataColumnDriver)Activator.CreateInstance(Type.GetType(tpS) ?? throw new InvalidOperationException());
            return null;
        }
    }
}

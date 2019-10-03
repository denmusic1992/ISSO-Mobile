using System;
using System.Linq;

namespace ISSO_I.Drivers
{
	public class Ais7DataTableDriver
	{
		/// <summary>
		///     порядковый номер колонки с идентификатором
		/// </summary>
		public short IdColumn { get; set; }

		/// <summary>
		///     Порядковый номер колонки со значением
		/// </summary>
		public short ValueColumn { get; protected set; }

		/// <summary>
		///     Внутренняя переменная - имя таблицы для которой создан драйвер
		/// </summary>
		protected string LocalTableName;

		/// <summary>
		///     Имя таблицы для которой создан драйвер
		/// </summary>
		public virtual string TableName
		{
			get
			{
				// отрезаем ISSO_I.
				var tName = GetType().ToString().Substring(5);
				var ind = tName.IndexOf('_');
				return ind == -1 ? LocalTableName : tName.Substring(ind + 1, tName.Length - ind - 1);
			}
		}

		/// <summary>
		///     Получить драйвер для колонки
		/// </summary>
		/// <param name="columnName"></param>
		public Ais7DataColumnDriver ColumnDriver(string columnName)
		{
			return Ais7DataColumnDriver.Create(TableName.ToUpper(), columnName.ToUpper());
		}

		/// <summary>
		///     Проверка на наличие драйвера для колонки
		/// </summary>
		/// <param name="columnName"></param>
		public bool ColumnHasDriver(string columnName)
		{
			var tpS = $"{typeof(Ais7DataColumnDriver)}_{TableName.ToUpper()}_{columnName.ToUpper()}";
			return Type.GetType(tpS) != null;
		}

		/// <summary>
		///     Признак того, что драйвер перегружен
		/// </summary>
		public bool IsSpecial => GetType().ToString() != typeof(Ais7DataTableDriver).ToString();

		/// <summary>
		///     Получить SQL запрос для вытаскивания значений из БД
		/// </summary>
		public virtual string GetSql()
		{
			return $"select * from {TableName}";
		}

		/// <summary>
		///     Получить SQL запрос для вытаскивания значений из БД с фильтром
		/// </summary>
		/// <param name="filter"></param>
		public virtual string GetSql(string filter)
		{
			var sql = GetSql();
			if (string.IsNullOrEmpty(filter)) return sql;
			return sql.ToLower().Contains("where") ? $"{sql} and {filter}" : $"{sql} where {filter}";
		}

		/// <summary>
		///     Создание драйвера для указанной таблицы
		/// </summary>
		public static Ais7DataTableDriver Create(string tableName)
		{
			var tName = tableName.Split('.').Last();
			var driverType = $"{typeof(Ais7DataTableDriver)}_{tName.ToUpper()}";
			var driver = Type.GetType(driverType);
			if (driver != null) // есть специфичный драйвер
				return (Ais7DataTableDriver) Activator.CreateInstance(driver);
			return new Ais7DataTableDriver(tName);
		}

		protected Ais7DataTableDriver(string locaTableName)
		{
			IdColumn = 0;
			ValueColumn = 1;
			LocalTableName = locaTableName;
		}

		protected Ais7DataTableDriver()
		{
			IdColumn = 0;
			ValueColumn = 1;
		}
	}
}

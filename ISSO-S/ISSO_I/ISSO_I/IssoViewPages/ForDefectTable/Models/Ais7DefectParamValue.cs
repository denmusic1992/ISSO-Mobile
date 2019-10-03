using ISSO_I.Additional_Classes;
using Mono.Data.Sqlite;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using ISSO_I.Sqlite;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
	public class Ais7DefectParamValue
	{
		/// <summary>
		/// Признак того, что это качественный параметр
		/// </summary>
		public bool IsQual { get; set; }

		/// <summary>
		/// Единицы измерения
		/// </summary>
		public string SnUnit { get; set; }


		/// <summary>
		/// Наименование параметра
		/// </summary>
		public string Name { get; set; }

		/// <summary>
		/// Категория параметра
		/// </summary>
		public short Category { get; set; }

		/// <summary>
		/// Начальное значение диапазона (для количественных)
		/// </summary>
		public double ValueStart { get; set; }
		/// <summary>
		/// Конеченое значение диапазона (для количественных)
		/// </summary>
		public double ValueEnd { get; set; }

		/// <summary>
		/// Минимальное значение параметра
		/// </summary>
		public double MinParamValue { get; set; }

		/// <summary>
		/// Максимальное значение параметра
		/// </summary>
		public double MaxParamValue { get; set; }

		/// <summary>
		/// Значение количественного признака
		/// </summary>
		public double? Value { get; set; }

		/// <summary>
		/// Параметр дефекта
		/// </summary>
		public short CDefParam { get; set; }
		/// <summary>
		/// Категория по безопасности
		/// </summary>
		public short B { get; set; }

		/// <summary>
		/// Категория по долговечности
		/// </summary>
		public short D { get; set; }

		/// <summary>
		/// Категория по ремонтопригодности
		/// </summary>
		public short R { get; set; }

		/// <summary>
		/// Влияние на грузоподьемность
		/// </summary>
		public bool G { get; set; }

		/// <summary>
		/// Категория по безопасности (экспертная)
		/// </summary>
		public short B1 { get; set; }

		/// <summary>
		/// Категория по долговечности (экспертная)
		/// </summary>
		public short D1 { get; set; }

		/// <summary>
		/// Категория по ремонтопригодности (экспертная)
		/// </summary>
		public short R1 { get; set; }

		/// <summary>
		/// Влияние на грузоподъемность (экспертная)
		/// </summary>
		public bool G1 { get; set; }


		public string GetValueRange => $"{(double.IsNaN(ValueStart) ? "" : $"От {ValueStart:F2} включительно")}{(double.IsNaN(ValueEnd) ? "" : $" до {ValueEnd:F2}")}";

		public string GetBdrg => $"Б{B},Д{D},Р{R}{(G ? ",Г" : "")}";

		public Ais7DefectParamValue() { }

		public Ais7DefectParamValue(int cDefParam)
		{
			var query = "select p.c_defparam, N_DEFPARAM, CATEGORY, case when pv.c_unit_dimen='' then 1 else 0 end as IsQual, SN_UNIT_DIME " +
						"from S_DEFPARAM p left outer join S_DEFPARAMVALUE pv on pv.C_DEFPARAM = p.C_DEFPARAM " +
						$"left outer join S_UNIT_DIMENSION u on u.C_UNIT_DIMEN = pv.c_unit_dimen where p.C_DEFPARAM={cDefParam} limit 1";
			var reader = SqliteReader.SelectQueryReader(query, out var conn);

			if (reader != null && reader.HasRows)
			{
				reader.Read();
				Name = reader.GetString(reader.GetOrdinal("N_DEFPARAM"));
				Category = reader["CATEGORY"] == DBNull.Value ? (short) -1 : Convert.ToInt16(reader["CATEGORY"]);
				IsQual = Convert.ToInt32(reader["IsQual"]) == 1;
				SnUnit = IsQual ? "" : Convert.ToString(reader["SN_UNIT_DIME"]);
			}
			reader?.Close();
			conn.Close();
		}

		public string GetFullName()
		{
			var categoryItem = new CategoryItem();
			var cl = categoryItem.CatItems(Category);
			cl.Insert(0, Name);
			return string.Join(". ", cl.ToArray());
		}

		public class CategoryItem
		{
			public int Id { get; set; }
			public int Parent { get; set; }
			public string Name { get; set; }

			/// <summary>
			/// Полный список возможных категорий
			/// </summary>
			public List<CategoryItem> GetCatList()
			{
				var catList = new List<CategoryItem>();

				using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
				{
					try
					{
						var command = connection.CreateCommand();
						command.CommandText = "select * from s_defparamcategory";
						command.CommandTimeout = 30;
						command.CommandType = System.Data.CommandType.Text;
						connection.Open();
						using (var datareader = command.ExecuteReader())
						{
							if (datareader.HasRows)
							{
								while (datareader.Read())
								{
									var item = new CategoryItem
									{
										Id = Convert.ToInt32(datareader["ID"]),
										Name = Convert.ToString(datareader["NAME"]),
										Parent = datareader["PARENT"] == DBNull.Value
											? -1
											: Convert.ToInt16(datareader["PARENT"])
									};
									catList.Add(item);
								}
							}
							datareader.Dispose();
						}
						command.Dispose();
					}
					catch (Exception ex)
					{
						Debug.WriteLine($"Произошла ошибка в БД: {ex.Message} \nStackTrace: {ex.StackTrace}");
					}
					finally
					{
						connection.Close();
					}
				}
				return catList;
			}

			public List<string> CatItems(int catId)
			{
				var items = new List<string>();
				var catList = GetCatList();
				var ci = catList.FirstOrDefault(i => i.Id == catId);
				while (ci != null)
				{
					items.Add(ci.Name);
					if (ci.Parent == -1) break;
					catId = ci.Parent;
					ci = catList.FirstOrDefault(i => i.Id == catId);
				}
				//Collections.reverse(items);
				items.Reverse();
				return items;
			}

		}

		/// <summary>
		/// Получить экземпляры этого класса для определенного типа дефекта
		/// </summary>
		/// <param name="cDefect"></param>
		/// <param name="cGrConstr"></param>
		/// <returns></returns>
		internal static List<Ais7DefectParamValue> ReturnDefectParameters(int cDefect, int cGrConstr)
		{
			// Запрос получения списка параметров дефекта
			var query =
				"select * from s_defparamvalue " +
				"left outer join s_defparam on s_defparam.c_defparam=s_defparamvalue.c_defparam " +
				"left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen=s_defparamvalue.c_unit_dimen " +
				$"where c_defect={cDefect} and c_gr_constr={cGrConstr} " +
				"order by c_unit_dimen, n";
			// Получения таблицы
			var reader = SqliteReader.SelectQueryReader(query, out var conn);
			var defectParameters = new List<Ais7DefectParamValue>();
			// Пока есть строки - читаем
			if(reader != null && reader.HasRows)
			while (reader.Read())
			{
				var parameter = new Ais7DefectParamValue
				{
					Name = reader.GetString(reader.GetOrdinal("N_DEFPARAM")),
					Category = !reader.IsDBNull(reader.GetOrdinal("CATEGORY"))
						? reader.GetInt16(reader.GetOrdinal("CATEGORY"))
						: (short) (-1),
					CDefParam = reader.GetInt16(reader.GetOrdinal("C_DEFPARAM")),
					IsQual = reader.IsDBNull(reader.GetOrdinal("C_UNIT_DIMEN")),
					B = !reader.IsDBNull(reader.GetOrdinal("B")) ? reader.GetInt16(reader.GetOrdinal("B")) : (short) -1,
					D = !reader.IsDBNull(reader.GetOrdinal("D")) ? reader.GetInt16(reader.GetOrdinal("D")) : (short) -1,
					R = !reader.IsDBNull(reader.GetOrdinal("R")) ? reader.GetInt16(reader.GetOrdinal("R")) : (short) -1,
					G = !reader.IsDBNull(reader.GetOrdinal("B")) && reader.GetBoolean(reader.GetOrdinal("B")),
					ValueStart = !reader.IsDBNull(reader.GetOrdinal("MIN_VALUE"))
						? reader.GetDouble(reader.GetOrdinal("MIN_VALUE"))
						: double.NaN,
					ValueEnd = !reader.IsDBNull(reader.GetOrdinal("MAX_VALUE"))
						? reader.GetDouble(reader.GetOrdinal("MAX_VALUE"))
						: double.NaN,
					MinParamValue = !reader.IsDBNull(reader.GetOrdinal("MIN_V"))
						? reader.GetDouble(reader.GetOrdinal("MIN_V"))
						: 0,
					MaxParamValue = !reader.IsDBNull(reader.GetOrdinal("MAX_V"))
						? reader.GetDouble(reader.GetOrdinal("MAX_V"))
						: double.MaxValue,
					SnUnit = !reader.IsDBNull(reader.GetOrdinal("SN_UNIT_DIME"))
						? reader.GetString(reader.GetOrdinal("SN_UNIT_DIME"))
						: null
				};
				parameter.B1 = parameter.B;
				parameter.D1 = parameter.D;
				parameter.R1 = parameter.R;
				parameter.G1 = parameter.G;


				defectParameters.Add(parameter);
			}
			// Закрываем подключение к БД
			reader?.Close();
			conn.Close();
			return defectParameters;
		}
	}
}

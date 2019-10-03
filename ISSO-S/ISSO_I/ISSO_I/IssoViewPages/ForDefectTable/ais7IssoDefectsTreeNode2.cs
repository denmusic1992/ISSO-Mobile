using System;
using ISSO_I.Additional_Classes;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using HighEnergy.TreeView;
using ISSO_I.Drivers;
using ISSO_I.Sqlite;
using Mono.Data.Sqlite;
using SQLite;

namespace ISSO_I.IssoViewPages.ForDefectTable
{
	/// <inheritdoc />
	/// <summary>
	/// Элемент дерева, только такой, который связан с таблицей в БД
	/// </summary>
	public class Ais7IssoDefectsTreeNode2 : Ais7IssoDefectsTreeNode
	{
		/// <summary>
		/// Имя таблицы
		/// </summary>
		public string TableName { get; protected set; }

		/// <summary>
		/// 
		/// </summary>
		/// <param name="tableName"></param>
		/// <param name="avTables">перечень таблиц, которые нам нужно для дерева</param>
		/// <param name="parentNode"></param>
		/// <param name="infoRows"></param>
		/// <param name="alterTables"></param>
		public Ais7IssoDefectsTreeNode2(string tableName,
			IList<string> avTables, Ais7IssoDefectsTreeNode parentNode, List<InfoRow> infoRows, List<CustomDefectAlterTable> alterTables)
			: base(false)
		{
			TableName = tableName;
			ParentNode = parentNode;
			ItemType = Ais7IssoDefectsTreeNodeType.Type1; // по умолчанию верхний уровень
			NGrConstr = infoRows.Find(x => x.SysName == tableName).Description;
			CGrConstr = infoRows.Find(x => x.SysName == tableName).CGrConstr;

			var subAlterTables = alterTables.Where(x => x.c_gr_constr == CGrConstr).ToList();
			if (subAlterTables.Count > 0)
			{
				isForConstr = subAlterTables[0].is_constr;
				Life = subAlterTables[0].life;
				ItemType = (Ais7IssoDefectsTreeNodeType)subAlterTables[0].ITEM_TYPE;
				OrderInTree = subAlterTables[0].ord;
			}
			else isForConstr = false;

			foreach (var subtable in infoRows.Where(x => x.CGrConstr != 10 && x.ParentId == infoRows.Find(y => y.SysName == tableName).CGrConstr).ToList())
			{
				if (avTables.IndexOf(subtable.SysName) != -1)
					ChildrenNodes.Add(new Ais7IssoDefectsTreeNode2(subtable.SysName, avTables, this, infoRows, alterTables));
			}
			IsNotLeaf = ChildrenNodes.Count > 0;
			LoadDefectList(CGrConstr, alterTables);

		}

		/// <summary>
		/// Таблицы для которых можно создавать дефекты на "Все сооружение"
		/// </summary>
		private static IEnumerable<string> AllAvailableTables => new[] { "I_MP", "I_PS_PEM" };

		/// <inheritdoc />
		/// <summary>
		/// Получение признака 
		/// </summary>
		/// <returns></returns>
		protected override bool GetForAllIssoAvailable()
		{
			return AllAvailableTables.ToList().IndexOf(TableName) != -1;
		}

		/// <inheritdoc />
		/// <summary>
		/// Перегрузим потому, что у нас для мостового полотна должно быть немного по другому
		/// </summary>
		/// <returns></returns>
		public override string GetConstrMainName(int constrId)
		{
			if (AllAvailableTables.ToList().IndexOf(TableName) != -1 && constrId == -1) return NGrConstr;
			return base.GetConstrMainName(constrId);
		}

		/// <summary>
		/// Таблицы для которых можно создавать дефекты на "Все сооружение"
		/// </summary>
		private IEnumerable<string> allAvailableTables => new[] { "i_mp", "i_ps_pem" };


		/// <inheritdoc />
		/// <summary>
		/// Перечень конструкций для данной ноды
		/// </summary>
		/// <param name="cIsso"></param>
		/// <returns></returns>
		public override Ais7ConstrItem[] ConstrList(int cIsso)
		{
			// Если у нас нет номеров конструкций
			if(!IsForConstr) return new Ais7ConstrItem[] { };
			var ttName = TableName;
			var cc = new List<Ais7ConstrItem>();
			var forceToMake = false;
			var constrName = NGrConstr;

			if (allAvailableTables.ToList().IndexOf(TableName) != -1)
			{
				ttName = "I_PS";
				constrName = "Пролетное строение";
				forceToMake = true;
			}

			if (!forceToMake && (ParentNode == null || ParentNode.ParentNode != null))
				return ParentNode != null ? ParentNode.ConstrList(cIsso) : new Ais7ConstrItem[] { };
			var driver = Ais7DataTableDriver.Create(ttName);
			SqliteConnection conn = null;
			SqliteDataReader dataReader = null;
			try
			{
				dataReader = SqliteReader.SelectQueryReader(driver.GetSql($"c_isso={cIsso}"), out conn);
				if (!dataReader.HasRows) return cc.ToArray();
				while (dataReader.Read())
				{
					cc.Add(Ais7ConstrItem.Create(constrName, dataReader.GetInt16(0)));
				}
			}
			catch (SQLiteException ex)
			{
				Debug.WriteLine($"Ошибка при получении списка конструктивов: {ex.Message}, StackTrace: {ex.StackTrace}");
			}
			finally
			{
				dataReader?.Close();
				dataReader?.Dispose();

				conn?.Close();
				conn?.Dispose();
			}

			return cc.ToArray();
		}

		/// <inheritdoc />
		/// <summary>
		/// Перечень конструкций для данной ноды
		/// </summary>
		/// <param name="cIsso"></param>
		/// <param name="cGrConstr"></param>
		/// <param name="mode"></param>
		/// <returns></returns>
		public override bool Visible(int cIsso, int cGrConstr, VisibleMode mode)
		{
			var retV = ChildrenNodes.Any(x => x.Visible(cIsso, cGrConstr, mode)) || DefectList.Count > 0;
			var connectionClass = ConnectionClass.CreateDatabase();
			switch (mode)
			{
				case VisibleMode.ExistConstructions:
					if (CGrConstr < 1000) // если эта нода связана с реальным сооружением и есть такая таблица
					{
						var cnt = connectionClass.ExecuteScalar<int>($"select count(*) from {TableName} where c_isso={cIsso}");
						retV = cnt > 0;
					}
					break;
				case VisibleMode.AllContructions:
					// Текущий тип ИССО
					var issoType = connectionClass.ExecuteScalar<int>(
						$"select C_GR_CONSTR from S_TYPISSO where C_TYPISSO=(select CTYPEISSO from I_ISSO where C_ISSO={cIsso})");

					retV = connectionClass.ExecuteScalar<int>($"select count(*) from s_cnct where c_nmd1={issoType} and c_nmd2={CGrConstr}") > 0;
					break;
				case VisibleMode.All:
					break;
				default:
					throw new ArgumentOutOfRangeException(nameof(mode), mode, null);
			}
			connectionClass.Close();
			return retV;
		}

	}
}

using ISSO_I.Additional_Classes;
using ISSO_I.IssoViewPages.ForDefectTable;
using Mono.Data.Sqlite;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using HighEnergy.TreeView;

namespace ISSO_I.Singleton
{
	internal class BuildTree
    {

        private static DefectTreeNode _defectTreeNode;

        public static DefectTreeNode GetDefectTree()
        {
	        return _defectTreeNode ?? (_defectTreeNode = BuildDefectTree());
        }

	    private static DefectTreeNode BuildDefectTree()
        {
            try
            {
                var defList = new List<InfoRow>();
                using (var connection = new SqliteConnection(ConnectionClass.NewDatabasePath))
                {
                    /** 1) Построение дерева по типам конструкций*/
                    var command = connection.CreateCommand();
                    command.CommandText = string.Format("select TABLE_NAMES.C_GR_CONSTR, TABLE_NAMES.SYS_NAME, TABLE_NAMES.DESCRIPTION, " +
                        " TABLE_NAMES.PARENT_VIEW, PARENT_ID, S_GR_CONSTR.ITEM_TYPE from TABLE_NAMES\n" +
                        " left outer join S_GR_CONSTR on S_GR_CONSTR.C_GR_CONSTR=TABLE_NAMES.C_GR_CONSTR\n" +
                        " where TABLE_NAMES.PARENT_VIEW != -1 and TABLE_NAMES.C_GR_CONSTR in (select distinct C_GR_CONSTR from S_DEF4CONSTR where C_GR_CONSTR < 100000 " +
                        " and C_GR_CONSTR > 0\n" +
                        " union all\n" +
                        " select distinct PARENT_GROUP from S_GR_CONSTR where PARENT_GROUP < 100000 and PARENT_GROUP > 0)\n" +
                        " group by TABLE_NAMES.C_GR_CONSTR, TABLE_NAMES.SYS_NAME, TABLE_NAMES.DESCRIPTION, TABLE_NAMES.PARENT_VIEW, PARENT_ID, ITEM_TYPE");
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    using (var datareader = command.ExecuteReader())
                    {
                        if (datareader.HasRows)
                        {
                            while (datareader.Read())
                            {
                                defList.Add(new InfoRow("", Convert.ToString(datareader["DESCRIPTION"]), Convert.ToInt32(datareader["PARENT_ID"]),
                                    Convert.ToInt32(datareader["C_GR_CONSTR"]), datareader["ITEM_TYPE"] != DBNull.Value ? Convert.ToInt32(datareader["ITEM_TYPE"]) : -100));
                            }
                        }
                    }
                    connection.Close();

                    /** 2) Добавление дефектов к конструкциям */
                    command = connection.CreateCommand();
                    command.CommandText = "select C_GR_CONSTR, TEXT, PARENT_GROUP, ITEM_TYPE from S_GR_CONSTR where C_GR_CONSTR > 100000";
                    command.CommandTimeout = 30;
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    using (var datareader = command.ExecuteReader())
                    {
                        if (datareader.HasRows)
                        {
                            while (datareader.Read())
                            {
                                defList.Add(new InfoRow("", Convert.ToString(datareader["TEXT"]), Convert.ToInt32(datareader["PARENT_GROUP"]),
                                    Convert.ToInt32(datareader["C_GR_CONSTR"]), Convert.ToInt32(datareader["ITEM_TYPE"])));
                            }
                        }
                    }
                    connection.Close();
                }
                DefectTreeNode root = null;
                var parentId = -1;
                foreach (var row in defList)
                {
	                if (row.CGrConstr != 10) continue;
	                root = new DefectTreeNode(new InfoRow("", row.Description, row.ParentId, row.CGrConstr, row.CountOfColumns));
	                parentId = row.CGrConstr;
	                break;
                }
                foreach (var t in defList)
                {
	                if (t.ParentId != parentId) continue;
	                if (root == null) continue;
	                var child = root.AddChild(new InfoRow("", t.Description, t.ParentId,
		                t.CGrConstr, t.CountOfColumns));
	                AddDefNodeToTree(defList, t.CGrConstr, child);
                }
                return root;
            }
            catch (Exception exception)
            {
				Debug.WriteLine(exception.Message,
					exception.InnerException,
					exception.StackTrace);
	            return null;
            }
        }

	    private static void AddDefNodeToTree(List<InfoRow> defList, int parentId, DefectTreeNode parent)
        {
	        foreach (var t in defList)
	        {
		        if (t.ParentId != parentId) continue;
		        var child = parent.AddChild(new InfoRow("", t.Description, t.ParentId,
			        t.CGrConstr, t.CountOfColumns));
		        AddDefNodeToTree(defList, t.CGrConstr, child);
	        }
        }
    }
}

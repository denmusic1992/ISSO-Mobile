using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;

namespace ISSO_I.IssoViewPages.ForDefectTable
{

    /// <summary>
    /// Тип элемента дерева дефектов
    /// </summary>
    public enum Ais7IssoDefectsTreeNodeType
    {
        [Description("Элемент оглавления фильтрации дефектов. Отображается на форме списка дефектов. В представлении локализации не используется")]
        Type0 = 0,
        [Description("Главный уровень, имеет дефекты собственного уровня, для дефектов с нижних подуровней как локализация не включается")]
        Type1 = 1,
        [Description("Включает в окончательном представлении локализации только собственный уровень. Используется в окончательном представлении локализации при выборе дефектов с нижних подуровней")]
        Type2 = 2,
        [Description("Включает в окончательном представлении все верхние родительские подуровни до родительского подуровня 2-го типа включительно. Может не иметь собственных дефектов")]
        Type3 = 3,
        [Description("Элемент оглавления блока конструкций в дереве конструкций. Собственных дефектов не имеет. В представлении локализации не используется")]
        Type4 = 4,
        [Description("Идентификация элемента, являющегося главной конструкцией")]
        Type100 = 100
    }

    /// <summary>
    /// Элемент дерева дефектов для сооружения
    /// </summary>
    public class Ais7IssoDefectsTreeNode
    {
        /// <summary>
        /// Идентификатор группы элементов
        /// </summary>
        public int CGrConstr { get; protected set; }


		/// <summary>
		/// Идентификатор группы конструкций, которая считается родительской для текущей группы 
		/// при определении влияния дефектов текущей группы на долговечность на сооружение
		/// </summary>
		public int ParentGrConstr4D { get; protected set; } = -1;


        /// <summary>
        /// Признак того, что нужно указывать номер конструкции
        /// </summary>
        protected bool isForConstr { get; set; } = false;

        /// <summary>
        /// Признак того, что эта нода требует указания номера конструкции
        /// </summary>
        public bool IsForConstr => isForConstr || (ParentNode != null && ParentNode.IsForConstr);

	    /// <summary>
        /// Влияние конструкции на основную долговечность
        /// </summary>
        public bool Life { get; protected set; }

        /// <summary>
        /// Уровень вложения элементов дерева
        /// </summary>
        public byte Level => ParentNode == null ? (byte)0 : (byte)(ParentNode.Level + 1);

	    /// <summary>
        /// Название группы элементов
        /// </summary>
        public string NGrConstr { get; set; }

		/// <summary>
		/// Тип элемента дерева
		/// </summary>
		public Ais7IssoDefectsTreeNodeType ItemType { get; protected set; } = Ais7IssoDefectsTreeNodeType.Type1;

        /// <summary>
        /// Перечень дефектов для этой группы конструкций
        /// </summary>
        public List<Ais7DefectItem> DefectList { get; protected set; }

        /// <summary>
        /// Порядок в дереве
        /// </summary>
        public short OrderInTree { get; protected set; }

        /// <summary>
        /// Родительская нода в дереве
        /// </summary>
        public Ais7IssoDefectsTreeNode ParentNode { get; protected set; }

        public List<Ais7IssoDefectsTreeNode> ChildrenNodes { get; protected set; }

		public bool IsNotLeaf { get; set; }

		public bool IsDefectLeaf => ReturnDefectsCount();

	    /// <summary>
		/// загрузка перечня дефектов для этой группы конструкций
		/// </summary>
		protected void LoadDefectList(int cgrConstr, List<CustomDefectAlterTable> alterTables)
        {
			//using (SqliteConnection connection = new SqliteConnection(ConnectionClass.newDatabasePath))
			//{
			//    connection.Open();
			//    using (SqliteCommand command = connection.CreateCommand())
			//    {
			//        command.CommandTimeout = 30;
			//        command.CommandText = String.Format("select s_gr_constr.c_gr_constr, is_constr, s_defect.*, item_type, lock_expert, life, ord from s_gr_constr " +
			//            "left outer join s_def4constr on s_def4constr.c_gr_constr = s_gr_constr.c_gr_constr " +
			//            "left outer join s_defect on s_defect.c_defect = s_def4constr.c_defect " +
			//            "where s_defect.c_defect is not null and s_gr_constr.c_gr_constr={0}", CGrConstr);
			//        command.CommandType = System.Data.CommandType.Text;
			//        using (SqliteDataReader dataReader = command.ExecuteReader())
			//        {
			//            if (dataReader.HasRows)
			//            {
			//                while(dataReader.Read())
			//                {
			//                    Boolean lockExpert = false;
			//                    if (dataReader["lock_expert"] != DBNull.Value) lockExpert = dataReader.GetBoolean(dataReader.GetOrdinal("lock_expert"));
			//                    DefectList.Add(new ais7DefectItem(this,
			//                        dataReader.GetInt32(dataReader.GetOrdinal("c_defect")),
			//                        dataReader.GetString(dataReader.GetOrdinal("n_defect")),
			//                        dataReader["W_DEF"] != DBNull.Value ? dataReader.GetString(dataReader.GetOrdinal("W_DEF")) : "",
			//                        lockExpert));
			//                }
			//            }
			//        }
			//    }
			//}
			var subTable = alterTables.FindAll(x => x.c_gr_constr == CGrConstr);
	        foreach (var subItem in subTable)
	        {
		        DefectList.Add(new Ais7DefectItem(this, subItem.C_DEFECT, subItem.N_DEFECT, subItem.W_DEF, subItem.LOCK_EXPERT));
		    }
        }

	    private bool ReturnDefectsCount()
	    {
		    return ChildrenNodes.Count(item => item.ItemType == Ais7IssoDefectsTreeNodeType.Type0) == 0 &&
		           DefectList.Count == 0;
	    }

        public Ais7IssoDefectsTreeNode() { }


        public Ais7IssoDefectsTreeNode(bool life)
        {
            Life = life;
            DefectList = new List<Ais7DefectItem>();
            ChildrenNodes = new List<Ais7IssoDefectsTreeNode>();
            OrderInTree = -1;
            ParentGrConstr4D = -1;
        }


        public Ais7IssoDefectsTreeNode(VGrConstrDef2 sourceRow, Ais7IssoDefectsTreeNode parent, List<CustomDefectAlterTable> alterTables)
        {
            //Life = sourceRow["life"] != DBNull.Value ? (Boolean)sourceRow["life"] : false;
            //DefectList = new List<ais7DefectItem>();
            //ChildrenNodes = new List<ais7IssoDefectsTreeNode>();
            //this.CGrConstr = (Int32)sourceRow["c_gr_constr"];
            //this.NGrConstr = sourceRow["text"].ToString();
            //this.ItemType = sourceRow["item_type"] != DBNull.Value ?
            //    (ais7IssoDefectsTreeNodeType)Convert.ToInt16(sourceRow["item_type"]) :
            //    ais7IssoDefectsTreeNodeType.Type1;

            //this.ParentNode = parent;
            //OrderInTree = sourceRow["ord"] != DBNull.Value ? (Int16)sourceRow["ord"] : (Int16)(-1);
            //ParentGrConstr4D = sourceRow.Table.Columns["ID4D"] != null && sourceRow["ID4D"] != DBNull.Value ?
            //    (Int32)sourceRow["ID4D"] : -1;

            Life = sourceRow.LIFE;
            DefectList = new List<Ais7DefectItem>();
            ChildrenNodes = new List<Ais7IssoDefectsTreeNode>();
            CGrConstr = sourceRow.C_GR_CONSTR;
            NGrConstr = sourceRow.TEXT;
            ItemType = (Ais7IssoDefectsTreeNodeType)sourceRow.ITEM_TYPE;

            ParentNode = parent;
            OrderInTree = sourceRow.ORD;
            ParentGrConstr4D = sourceRow.ID4D;

            LoadDefectList(CGrConstr, alterTables);
        } 

        /// <summary>
        /// Признак что возможно создание дефекта на все сооружение целиком
        /// </summary>
        public bool ForAllIssoAvailable => GetForAllIssoAvailable();

	    /// <summary>
        /// Получение признака что возможно создание дефекта на все сооружение целиком
        /// </summary>
        /// <returns></returns>
        protected virtual bool GetForAllIssoAvailable()
        {
            return ParentNode.GetForAllIssoAvailable();
        }


        public override string ToString()
        {
            return NGrConstr;
        }


        /// <summary>
        /// Поиск ноды с указанным идентификатором
        /// </summary>
        /// <param name="idToLocate"></param>
        /// <returns></returns>
        internal Ais7IssoDefectsTreeNode LocateNode(int idToLocate)
        {
            if (CGrConstr == idToLocate) return this;
            foreach (var node in ChildrenNodes)
            {
                var nd = node.LocateNode(idToLocate);
	            if (nd == null) continue;
	            return nd.ItemType == Ais7IssoDefectsTreeNodeType.Type0 ? nd.ParentNode : nd;
            }
            return null;
        }

        /// <summary>
        /// Режим видимости ноды
        /// </summary>
        public enum VisibleMode
        {
            [Description("Все объекты")]
            All,
            [Description("Все конструкции ИССО")]
            AllContructions,
            [Description("Имеющиеся конструкции ИССО")]
            ExistConstructions
        }


	    /// <summary>
	    /// Проверка видимости этой ноды
	    /// </summary>
	    /// <param name="cIsso"></param>
	    /// <param name="cGrConstr"></param>
	    /// <param name="mode">режим видимости</param>
	    /// <returns></returns>
	    public virtual bool Visible(int cIsso, int cGrConstr, VisibleMode mode)
        {
            switch (mode)
            {
                case VisibleMode.All: return true;
                case VisibleMode.AllContructions:
                case VisibleMode.ExistConstructions:
                    return DefectList.Count > 0 ||
                        ChildrenNodes.Any(x => x.Visible(cIsso, cGrConstr, mode));
	            default:
		            throw new ArgumentOutOfRangeException(nameof(mode), mode, null);
            }
        }


        /// <summary>
        /// Перечень конструкций для данной ноды
        /// </summary>
        /// <param name="cIsso"></param>
        /// <returns></returns>
        public virtual Ais7ConstrItem[] ConstrList(int cIsso)
        {
            return ParentNode?.ConstrList(cIsso);
        }

        /// <summary>
        /// Название конструкции
        /// </summary>
        /// <param name="constrId"></param>
        /// <returns></returns>
        public virtual string GetConstrMainName(int constrId)
        {
	        if (ParentNode.ParentNode == null || ItemType == Ais7IssoDefectsTreeNodeType.Type100)
	            return constrId != short.MinValue ? $"{NGrConstr} №{constrId}" : NGrConstr;
	        return ParentNode.GetConstrMainName(constrId);
        }

	    /// <summary>
	    /// Идентификатор главной конструкции (знечение меньше 1000)
	    /// </summary>
	    /// <returns></returns>
	    public int GetConstrMainId
        {
            get
            {
	            if (ParentGrConstr4D != -1) return ParentGrConstr4D;
	            if (CGrConstr < 1000 || ItemType == Ais7IssoDefectsTreeNodeType.Type100 || ParentNode.ParentNode == null) return CGrConstr;
	            return ParentNode.GetConstrMainId;
            }
        }

        /// <summary>
        /// Значение главной конструкции (ее ID меньше 1000)
        /// </summary>
        /// <returns></returns>
        public Ais7IssoDefectsTreeNode GetConstrMainValue => LocateNode(GetConstrMainId);


	    protected virtual string GetConstrFullName2()
        {
            if (ParentNode.ParentNode == null) return "";

            if (ItemType == Ais7IssoDefectsTreeNodeType.Type2 ||
                ItemType == Ais7IssoDefectsTreeNodeType.Type3) return ParentNode.GetConstrFullName2() + NGrConstr + ". ";
	        return ParentNode.GetConstrFullName2();
        }


        public virtual string GetConstrFullName()
        {
	        var ss = GetConstrFullName2();
	        return ss.Length > 0 ? ss.Substring(0, ss.Length - 2) : "";
        }


        /// <summary>
        /// Получить ноду, которая является нодой - конструкцией
        /// </summary>
        /// <returns></returns>
        public Ais7IssoDefectsTreeNode GetConstrNode()
        {
	        return this is Ais7IssoDefectsTreeNode2 ? this : ParentNode.GetConstrNode();
        }

    }
}

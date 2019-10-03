package com.development.aisisso.isso_i;

/// <summary>
    /// Элемент дерева дефектов для сооружения
    /// </summary>
    public class ais7IssoDefectsTreeNode
    {
        /// <summary>
        /// Идентификатор группы элементов
        /// </summary>
        /*public int CGrConstr;


        /// <summary>
        /// Идентификатор граппы конструкций, которая считается родительской для текущей группы
        /// при определении влияния дефектов текущей группы на долговечность на сооружение
        /// </summary>
        public int ParentGrConstr4D;


        /// <summary>
        /// Признак того, что нужно указывать номер конструкции
        /// </summary>
        protected Boolean isForConstr = false;

        /// <summary>
        /// Признак того, что эта нода требует указания номера конструкции
        /// </summary>
        public Boolean IsForConstr() { return isForConstr || (ParentNode != null && ParentNode.IsForConstr); }

        /// <summary>
        /// Влияние конструкции на основную долговечность
        /// </summary>
        public Boolean Life;

        /// <summary>
        /// Уровень вложения элементов дерева
        /// </summary>
        public Byte Level() { return ParentNode == null ? Byte.valueOf("0") : (byte)(ParentNode.Level + 1); }

        /// <summary>
        /// Название группы элементов
        /// </summary>
        public String NGrConstr;

        /// <summary>
        /// Тип элемента дерева
        /// </summary>
        public int ItemType;

        /// <summary>
        /// Перечень дефектов для этой группы конструкций
        /// </summary>
        public List<ais7DefectItem> DefectList;

        /// <summary>
        /// Порядок в дереве
        /// </summary>
        public int OrderInTree;

        /// <summary>
        /// Родительская нода в дереве
        /// </summary>
        public ais7IssoDefectsTreeNode ParentNode;

        public ArrayList<ais7IssoDefectsTreeNode> ChildrenNodes;

        /// <summary>
        /// загрузка перечня дефектов для этой группы конструкций
        /// </summary>
        protected void LoadDefectList()
        {
            SQLiteDatabase db = new DBHelper(this).get
            Cursor cr =
            foreach (DataRow row in defInfoTable.Select("c_defect not is null and c_gr_constr=" + CGrConstr.ToString()))
            {
                Boolean lockExpert = false;
                if (row["lock_expert"] != DBNull.Value) lockExpert = Convert.ToBoolean(row["lock_expert"]);
                DefectList.Add(new ais7DefectItem(this,
                        Convert.ToInt32(row["c_defect"]),
                        row["n_defect"].ToString(),
                        row.Table.Columns.Contains("W_DEF") ? row["w_def"].ToString() : "",
                        lockExpert));
            }
        }


        public ais7IssoDefectsTreeNode(Boolean life)
        {
            Life = life;
            DefectList = new ArrayList<>();
            ChildrenNodes = new ArrayList<>();
            OrderInTree = -1;
            ParentGrConstr4D = -1;
        }


        public ais7IssoDefectsTreeNode(DataRow sourceRow, ais7IssoDefectsTreeNode parent, DataTable defInfoTable)
        {
            Life = sourceRow["life"] != DBNull.Value ? (Boolean)sourceRow["life"] : false;
            DefectList = new ArrayList<>();
            ChildrenNodes = new ArrayList<>();
            this.CGrConstr = (int)sourceRow["c_gr_constr"];
            this.NGrConstr = sourceRow["text"].ToString();
            this.ItemType = sourceRow["item_type"] != DBNull.Value ?
                    (ais7IssoDefectsTreeNodeType)Convert.ToInt16(sourceRow["item_type"]) :
                    ais7IssoDefectsTreeNodeType.Type1;

            this.ParentNode = parent;
            OrderInTree = sourceRow["ord"] != DBNull.Value ? (Int16)sourceRow["ord"] : (Int16)(-1);
            ParentGrConstr4D = sourceRow.Table.Columns["ID4D"]!=null && sourceRow["ID4D"] != DBNull.Value ?
                    (Int32)sourceRow["ID4D"] : -1;
            LoadDefectList(defInfoTable);
        }

        /// <summary>
        /// Признак что возможно создание дефекта на все сооружение целиком
        /// </summary>
        public Boolean ForAllIssoAvailable() { return getForAllIssoAvailable(); }

        /// <summary>
        /// Получение признака что возможно создание дефекта на все сооружение целиком
        /// </summary>
        /// <returns></returns>
        protected Boolean getForAllIssoAvailable()
    {
        return ParentNode.getForAllIssoAvailable();
    }


        public String ToString()
    {
        return NGrConstr;
    }


        /// <summary>
        /// Поиск ноды с указанным идентификатором
        /// </summary>
        /// <param name="idToLocate"></param>
        /// <returns></returns>
        ais7IssoDefectsTreeNode LocateNode(int idToLocate)
        {
            if (CGrConstr == idToLocate) return this;
            for (ais7IssoDefectsTreeNode node : ChildrenNodes)
            {
                ais7IssoDefectsTreeNode nd = node.LocateNode(idToLocate);
                if (nd != null)
                    if (nd.ItemType == 0) return nd.ParentNode; // это элемент фильтра - вернем родительскую ноду
                    else return nd;
            }
            return null;
        }


        /// <summary>
        /// Проверка видимости этой ноды
        /// </summary>
        /// <param name="cIsso"></param>
        /// <param name="mode">режим видимости</param>
        /// <returns></returns>
        public Boolean Visible(int cIsso, int mode)
    {
        switch (mode)
        {
            case 0: return true;
            case 1:
            case 2:
                return DefectList.size() > 0 ||
                        ChildrenNodes.Any<ais7IssoDefectsTreeNode>(x => x.Visible(cIsso, mode));
        }

        return true;
    }

        /// <summary>
        /// Элемент в дереве
        /// </summary>
        public TreeListNode NodeInTree;

        /// <summary>
        /// Построение дерева
        /// </summary>
        /// <param name="treeList"></param>
        public void FillTree(TreeList treeList)
        {
            FillTree(treeList, null);
        }

        /// <summary>
        /// Построение дерева
        /// </summary>
        /// <param name="treeList"></param>
        public void FillTree(TreeList treeList, TreeListNode parentNode)
        {
            if (ItemType == ais7IssoDefectsTreeNodeType.Type0) return; // это элемент фильтра - выходим

            NodeInTree = treeList.AppendNode(new object[] { this }, parentNode);
            if (OrderInTree != -1) treeList.SetNodeIndex(NodeInTree, OrderInTree);

            foreach (ais7IssoDefectsTreeNode node in ChildrenNodes)
            node.FillTree(treeList, NodeInTree);
        }

        /// <summary>
        /// Перечень конструкций для данной ноды
        /// </summary>
        /// <param name="cIsso"></param>
        /// <returns></returns>
        public ais7ConstrItem[] ConstrList(int cIsso)
    {
        return ParentNode.ConstrList(cIsso);
    }

        /// <summary>
        /// Название конструкции
        /// </summary>
        /// <param name="ConstrId"></param>
        /// <returns></returns>
        public String GetConstrMainName(int ConstrId)
    {
        if (ParentNode.ParentNode == null || ItemType == ais7IssoDefectsTreeNodeType.Type100)
            if (ConstrId != Int16.MinValue) return String.Format("{0} №{1}", NGrConstr, ConstrId);
            else return NGrConstr;
        else return ParentNode.GetConstrMainName(ConstrId);
    }

        /// <summary>
        /// Идентификатор главной конструкции (знечение меньше 1000)
        /// </summary>
        /// <param name="ConstrId"></param>
        /// <returns></returns>
        public int GetConstrMainId
        {
            get
            {
                if (ParentGrConstr4D != -1) return ParentGrConstr4D;
                else
                {
                    if (CGrConstr < 1000 || ItemType == ais7IssoDefectsTreeNodeType.Type100 || ParentNode.ParentNode == null) return CGrConstr;
                    else return ParentNode.GetConstrMainId;
                }
            }
        }

        /// <summary>
        /// Значение главной конструкции (ее ID меньше 1000)
        /// </summary>
        /// <returns></returns>
        public ais7IssoDefectsTreeNode GetConstrMainValue { get { return LocateNode(GetConstrMainId); } }


        protected String GetConstrFullName2()
    {
        if (ParentNode.ParentNode == null) return "";

        if (ItemType == ais7IssoDefectsTreeNodeType.Type2 ||
                ItemType == ais7IssoDefectsTreeNodeType.Type3) return ParentNode.GetConstrFullName2() + NGrConstr + ". ";
        else return ParentNode.GetConstrFullName2();
    }


        public virtual string GetConstrFullName()
    {
        String ss = GetConstrFullName2();
        if (ss.Length > 0) return ss.Substring(0, ss.Length - 2);
        else return "";
    }


        /// <summary>
        /// Получить ноду, которая является нодой - конструкцией
        /// </summary>
        /// <returns></returns>
        public ais7IssoDefectsTreeNode GetConstrNode()
        {
            if (this is ais7IssoDefectsTreeNode2) return this; // это нода конструкций
            return ParentNode.GetConstrNode();
        }

    }

    /// <summary>
    /// Элемент дерева, только такой, который связан с таблицей в БД
    /// </summary>
    public class ais7IssoDefectsTreeNode2 extends ais7IssoDefectsTreeNode
    {
        /// <summary>
        /// Имя таблицы
        /// </summary>
        public String TableName;


        /// <summary>
        ///
        /// </summary>
        /// <param name="tableName"></param>
        /// <param name="avTables">перечень таблиц, которые нам нужно для дерева</param>
        /// <param name="parentNode"></param>
        public ais7IssoDefectsTreeNode2(String tableName,
            List<String> avTables, ais7IssoDefectsTreeNode parentNode, Cursor defInfoTable, Context context)
        {
            TableName = tableName;
            this.ParentNode = parentNode;
            this.ItemType = 1;
            Cursor cr = new DBHelper(context).getReadableDatabase().rawQuery("select C_GR_CONSTR, TEXT " +
                    "from S_GR_CONSTR where N_GR_CONSTR=" + tableName, null );
            cr.moveToFirst();
            NGrConstr = cr.getString(cr.getColumnIndex("TEXT"));
            CGrConstr = cr.getInt(cr.getColumnIndex("C_GR_CONSTR"));
            cr.close();

            defInfoTable.moveToFirst();
            isForConstr = false;
            for(int i = 0; i < defInfoTable.getCount(); i++ ) {
                if(defInfoTable.getString(defInfoTable.getColumnIndex("C_GR_CONSTR")).equals(String.valueOf(CGrConstr))) {
                    if(!defInfoTable.isNull(defInfoTable.getColumnIndex("is_constr")))
                        isForConstr =  Boolean.valueOf(defInfoTable.getString(defInfoTable.getColumnIndex("is_constr")));
                    Life = !defInfoTable.isNull(defInfoTable.getColumnIndex("life")) ?
                            Boolean.valueOf(defInfoTable.getString(defInfoTable.getColumnIndex("life"))) : false;
                    if(!defInfoTable.isNull(defInfoTable.getColumnIndex("item_type")))
                        ItemType = defInfoTable.getInt(defInfoTable.getColumnIndex("item_type"));
                    this.OrderInTree = !defInfoTable.isNull(defInfoTable.getColumnIndex("ord")) ?
                            defInfoTable.getInt(defInfoTable.getColumnIndex("ord")) : -1;
                }
                defInfoTable.moveToNext();
            }

            tblInfo.InitChildren();
            foreach (String subTable in tblInfo.DependsOn) // теперь для всех дочерних раблиц
            if (avTables.IndexOf(subTable) != -1)
                ChildrenNodes.Add(new ais7IssoDefectsTreeNode2(subTable, avTables, this, defInfoTable));
            LoadDefectList(defInfoTable);
        }*/

        /// <summary>
        /// Таблицы для которых можно создавать дефекты на "Все сооружение"
        /// </summary>
        /*private String[] allAvailableTables { get { return new String[] { "I_MP", "I_PS_PEM" }; } }

        /// <summary>
        /// Получение признака
        /// </summary>
        /// <returns></returns>
    protected Boolean getForAllIssoAvailable()
    {
        return allAvailableTables.ToList<string>().IndexOf(TableName)!=-1;
    }

    /// <summary>
    /// Перегрузим потому, что у нас для мостового полотна должно быть немного по другому
    /// </summary>
    /// <returns></returns>
    public String GetConstrMainName(int ConstrId)
    {
        if (allAvailableTables.ToList<string>().IndexOf(TableName) != -1 && ConstrId == -1) return NGrConstr;
        return this.GetConstrMainName(ConstrId);
    }

    /// <summary>
    /// Перечень конструкций для данной ноды
    /// </summary>
    /// <param name="cIsso"></param>
    /// <returns></returns>
    public ais7ConstrItem[] ConstrList(int cIsso)
    {
        String ttName = TableName;
        List<ais7ConstrItem> cc = new ArrayList<>();
        Boolean forceToMake = false;
        String constrName = NGrConstr;

        if (allAvailableTables.ToList<string>().IndexOf(TableName) != -1)
        {
            ttName = "I_PS";
            constrName = "Пролетное строение";
            forceToMake = true;
        }

        if (forceToMake || (ParentNode != null && ParentNode.ParentNode == null)) // это нода первого уровня - надо получиь список
        {
            ais7DataTableDriver driver = ais7DataTableDriver.Create(ttName);
            Iais7Database dbClient = ais7AppCoordinator.GetModule<Iais7Database>();
            foreach (DataRow rr in dbClient.ExecSQLReader(driver.GetSql("c_isso=" + cIsso.ToString())).Rows)
            cc.Add(ais7ConstrItem.Create(constrName, Convert.ToInt16(rr[0])));
            return cc.ToArray();
        }
        if (ParentNode != null) return ParentNode.ConstrList(cIsso);
        else return new ais7ConstrItem[] { };
    }

    public Boolean Visible(int cIsso, VisibleMode mode)
    {
        Boolean retV = ChildrenNodes.Any<ais7IssoDefectsTreeNode>(x => x.Visible(cIsso, mode)) || DefectList.Count > 0;
        switch (mode)
        {
            case VisibleMode.ExistConstructions:
                if (CGrConstr < 1000) // если эта нода связана с реальным сооружением и есть такая таблица
                {
                    Iais7Database dbClient = ais7AppCoordinator.GetModule<Iais7Database>();
                    Int32 cnt = (Int32)dbClient.ExecSQLScalar(String.Format("select count(*) from {0} where c_isso={1}", TableName, cIsso));
                    retV &= cnt > 0;
                }
                break;
            case VisibleMode.AllContructions:
                DataTable sCnct = ais7AppCoordinator.GetModule<Iais7Database>().GetTable("S_CNCT");
                Int32 IssoType = ais7AppCoordinator.GetModule<ais7ModuleIssoManagement>().GetCGrConstr(cIsso);
                retV &= sCnct.Select(String.format("c_nmd1={0} and c_nmd2={1}", IssoType, CGrConstr)).Length > 0;

                break;
        }
        return retV;
    }*/

}

class ais7DefectItem {
    /// <summary>
    /// Идентификатор дефекта
    /// </summary>
    public int cDefect;

    /// <summary>
    /// Название дефекта
    /// </summary>
    public String nDefect;

    public String wDef;

    /// <summary>
    /// Родительский элемент
    /// </summary>
    public ais7IssoDefectsTreeNode Parent;

    /// <summary>
    /// Признак блокирования возможности установки экспертных категорий дефектов
    /// </summary>
    public Boolean LockExpert;

    public ais7DefectItem(ais7IssoDefectsTreeNode Parent, int id, String name, String wDef, Boolean lockExpert) {
        this.Parent = Parent;
        cDefect = id;
        nDefect = name;
        this.wDef = wDef;
        LockExpert = lockExpert;
    }

    public String ToString() {
        return nDefect;
    }
}

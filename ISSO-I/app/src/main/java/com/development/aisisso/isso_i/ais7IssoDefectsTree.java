package com.development.aisisso.isso_i;

public class ais7IssoDefectsTree {

    //Сведения о дефектах
    /*public Cursor getSDefParams(Context context) {
        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();
        Cursor cr = db.rawQuery("select * from s_defparamvalue left outer join s_defparam on s_defparam.c_defparam=s_defparamvalue.c_defparam " +
                "left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen=s_defparamvalue.c_unit_dimen order by c_defect, c_gr_constr, n", null);
        cr.moveToFirst();
        return cr;
    }

    public ais7IssoDefectsTree(Context context) {
        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();
        Cursor tblDefInfo = db.rawQuery("select s_gr_constr.c_gr_constr, is_constr, s_defect.*, item_type, lock_expert, life, ord " +
                "from s_gr_constr " +
                "left outer join s_def4constr on s_def4constr.c_gr_constr=s_gr_constr.c_gr_constr " +
                "left outer join s_defect on s_defect.c_defect=s_def4constr.c_defect", null);

        // сначала строим дерево на основании структуры БД
        List<String> tlList = new ArrayList<>();
        Cursor cr = db.rawQuery("select * from v_gr_constr_def", null);
        cr.moveToFirst();
        for(int i = 0; i < cr.getCount(); i++) {
            tlList.add(cr.getString(0));
            cr.moveToNext();
        }
        cr.close();
        cr = db.rawQuery("select n_gr_constr from s_gr_constr where c_gr_constr=10", null);
        cr.moveToFirst();
        String rootTable = cr.getString(0);
        ais7IssoDefectsTreeNode RootNode = new ais7IssoDefectsTreeNode2(rootTable, tlList, null, tblDefInfo);
        cr.close();

        // а теперь стоим дерево на основании данных в таблице s_gr_constr, только тех, которые не связаны с таблицами БД
        cr = db.rawQuery("select * from v_gr_constr_def2", null);
        cr.moveToFirst();
        for(int i = 0; i < cr.getCount(); i++) {
            try {
                ais7IssoDefectsTreeNode prNode = RootNode.LocateNode(cr.getInt(cr.getColumnIndex("PARENT_GROUP")));
                if(prNode != null) {
                    prNode.ChildrenNodes.add(new ais7IssoDefectsTreeNode(cr, prNode, tblDefInfo));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                e.toString();
            }
        }
    }*/
}

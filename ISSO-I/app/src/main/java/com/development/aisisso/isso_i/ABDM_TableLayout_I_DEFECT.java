package com.development.aisisso.isso_i;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.development.aisisso.isso_i.model.TreeNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ABDM_TableLayout_I_DEFECT extends AppCompatActivity implements View.OnClickListener{

    public int C_ISSO;
    public TableLayout tableInfoDefects;
    public ArrayList<ais7IssoDefect> defects;
    public FloatingActionButton btnDefects;
    public static ArrayList<Integer> filterConstrId = new ArrayList<>();
    public static ArrayList<String> filterText = new ArrayList<>();
    public static ArrayList<String> defFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0"))
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);
        setContentView(R.layout.abdm_tablelayout_i_defect);
        C_ISSO = getIntent().getIntExtra("C_ISSO", -1);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.isso_tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.action_bar_title_layout);
        ((TextView) findViewById(R.id.action_bar_title)).setText("АИС ИССО-I." + " Сооружение №" + C_ISSO);
        ((TextView) findViewById(R.id.action_bar_subtitle)).setText("Дефекты");
        getSupportActionBar().setTitle("");
        if(MainActivity.theme.equals("0")) {
            ((TextView) findViewById(R.id.action_bar_title)).setTextColor(getResources().getColor(R.color.bright_foreground_material_dark));
            ((TextView) findViewById(R.id.action_bar_subtitle)).setTextColor(getResources().getColor(R.color.bright_foreground_material_dark));
        }
        ab.setDisplayHomeAsUpEnabled(true);
        btnDefects = (FloatingActionButton) findViewById(R.id.btn_filter_def);
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.LayoutTableInfo);
        if (!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            assert ab != null;
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_dark);
            ab.setHomeAsUpIndicator(R.drawable.back_light);
        } else {
            assert ab != null;
            ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_light);
            ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }
        tableInfoDefects = (TableLayout) findViewById(R.id.DefTable);
        if(MainActivity.theme.equals("0"))
            tableInfoDefects.setBackground(getDrawable(R.drawable.cell_borders_dark));
        else
            tableInfoDefects.setBackground(getDrawable(R.drawable.cell_borders_light));

        SyncModule sm = new SyncModule();
        sm.execute();
    }



    /**  Построение списка дефектов для определенного ИССО */
    public ArrayList<ais7IssoDefect> CreateDefectList(int C_ISSO) {
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        /*Cursor cursor = db.rawQuery("select I_DEFECT.c_isso, I_DEFECT.n_def, I_DEFECT.c_defect, I_DEFECT.c_gr_constr, " +
                "       coalesce(ord, s_gr_constr.c_gr_constr) as ord, I_DEFECT.n_constr, i_def_mod.date, " +
                "       I_DEFECT.B, I_DEFECT.B1, I_DEFECT.D, I_DEFECT.D1, I_DEFECT.R, I_DEFECT.R1, I_DEFECT.G, I_DEFECT.G1, " +
                "       I_DEFECT.date as date_defect, I_DEFECT.datef as date_end, i_def_mod.l_def, i_def_mod.w_def, s_defect.n_defect, n_rem, " +
                "       sn_unit_dime, v_rem, count(i_foto_def.n_def) as fotocount " +
                "from I_DEFECT  " +
                "left outer join i_def_mod on  " +
                "    i_def_mod.c_isso = I_DEFECT.c_isso and  " +
                "    i_def_mod.n_def = I_DEFECT.n_def and " +
                "    i_def_mod.date = (select max(date) from i_def_mod d2 where  " +
                "            d2.c_isso = I_DEFECT.c_isso and  " +
                "            d2.n_def = I_DEFECT.n_def) " +
                " left outer join s_defect on s_defect.c_defect = I_DEFECT.c_defect " +
                " left outer join s_rem on s_rem.c_rem = i_def_mod.c_rem " +
                " left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen = s_rem.ind_unit " +
                " left outer join i_foto_def on i_foto_def.c_isso = I_DEFECT.c_isso and i_foto_def.n_def = I_DEFECT.n_def and " +
                "       i_foto_def.date = i_def_mod.date " +
                " left outer join s_gr_constr on s_gr_constr.c_gr_constr = (case when (I_DEFECT.C_GR_CONSTR < 1000) then I_DEFECT.C_GR_CONSTR " +
                "else (select parent_group from s_gr_constr where c_gr_constr = I_DEFECT.C_GR_CONSTR) end) " +
                "where I_DEFECT.c_isso = " + C_ISSO +
                " group by I_DEFECT.c_isso, I_DEFECT.n_def, I_DEFECT.c_defect, I_DEFECT.c_gr_constr, I_DEFECT.n_constr, i_def_mod.date, " +
                "       I_DEFECT.B, I_DEFECT.B1, I_DEFECT.D, I_DEFECT.D1, I_DEFECT.R, I_DEFECT.R1, I_DEFECT.G, I_DEFECT.G1, " +
                "       I_DEFECT.date, I_DEFECT.datef, i_def_mod.l_def, n_rem, sn_unit_dime, v_rem, l_def, i_def_mod.w_def, n_defect, " +
                "       s_gr_constr.c_gr_constr, ord order by ord, n_constr, I_DEFECT.n_def", null);*/
        Cursor cursor = db.rawQuery("select I_DEFECT.c_isso, I_DEFECT.n_def, I_DEFECT.c_defect, I_DEFECT.c_gr_constr, " +
                "       coalesce(ord, I_DEFECT.MAIN_CONSTR_ID) as ord, I_DEFECT.n_constr, i_def_mod.date, " +
                "       I_DEFECT.B, I_DEFECT.B1, I_DEFECT.D, I_DEFECT.D1, I_DEFECT.R, I_DEFECT.R1, I_DEFECT.G, I_DEFECT.G1, " +
                "       I_DEFECT.DATE as date_defect, I_DEFECT.DATEF as date_end, i_def_mod.l_def, i_def_mod.w_def, s_defect.n_defect, n_rem, " +
                "       sn_unit_dime, v_rem, count(i_foto_def.n_def) as fotocount, I_DEFECT.MAIN_CONSTR_ID " +
                "from I_DEFECT  " +
                "left outer join i_def_mod on  " +
                "    i_def_mod.c_isso = I_DEFECT.c_isso and  " +
                "    i_def_mod.n_def = I_DEFECT.n_def and " +
                "    i_def_mod.date = (select max(date) from i_def_mod d2 where  " +
                "            d2.c_isso = I_DEFECT.c_isso and  " +
                "            d2.n_def = I_DEFECT.n_def) " +
                " left outer join s_defect on s_defect.c_defect = I_DEFECT.c_defect " +
                " left outer join s_rem on s_rem.c_rem = i_def_mod.c_rem " +
                " left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen = s_rem.ind_unit " +
                " left outer join i_foto_def on i_foto_def.c_isso = I_DEFECT.c_isso and i_foto_def.n_def = I_DEFECT.n_def and " +
                "       i_foto_def.date = i_def_mod.date " +
                " left outer join s_gr_constr on s_gr_constr.C_GR_CONSTR = I_DEFECT.MAIN_CONSTR_ID " +
                "where I_DEFECT.c_isso = " + C_ISSO +
                " group by I_DEFECT.c_isso, I_DEFECT.n_def, I_DEFECT.c_defect, I_DEFECT.c_gr_constr, I_DEFECT.n_constr, i_def_mod.date, " +
                "       I_DEFECT.B, I_DEFECT.B1, I_DEFECT.D, I_DEFECT.D1, I_DEFECT.R, I_DEFECT.R1, I_DEFECT.G, I_DEFECT.G1, " +
                "       I_DEFECT.DATE, I_DEFECT.DATEF, i_def_mod.l_def, n_rem, sn_unit_dime, v_rem, l_def, i_def_mod.w_def, n_defect, " +
                "       s_gr_constr.c_gr_constr, ord, I_DEFECT.MAIN_CONSTR_ID order by ord, I_DEFECT.n_constr, I_DEFECT.n_def", null);
        Cursor paramCursor = db.rawQuery("select I_DEFECT.n_def, s_defparam.c_defparam, " +
                "   n_defparam, category, value, sn_unit_dime, min(s_defparamvalue.c_unit_dimen) c_unit_dimen " +
                "from i_def_descr  " +
                "     left outer join i_def_mod on i_def_descr.c_isso = i_def_mod.c_isso and i_def_descr.n_def = " +
                "       i_def_mod.n_def and i_def_descr.date=i_def_mod.date " +
                "     left outer join I_DEFECT on i_def_mod.c_isso = I_DEFECT.c_isso and i_def_mod.n_def = I_DEFECT.n_def " +
                "     left outer join s_defparam on s_defparam.c_defparam = i_def_descr.c_defparam " +
                "     left outer join s_defparamvalue on s_defparamvalue.c_defect=I_DEFECT.c_defect and " +
                "       s_defparamvalue.c_gr_constr = I_DEFECT.c_gr_constr and " +
                "                                        s_defparamvalue.c_defparam = i_def_descr.c_defparam " +
                "     left outer join s_unit_dimension on s_defparamvalue.c_unit_dimen = s_unit_dimension.c_unit_dimen " +
                "where i_def_mod.date = (select max(date) from i_def_mod d2 where  " +
                "            d2.c_isso = I_DEFECT.c_isso and  " +
                "            d2.n_def = I_DEFECT.n_def) and i_def_descr.c_isso = " + C_ISSO +
                " group by I_DEFECT.n_def, s_defparam.c_defparam, n_defparam, category, value, sn_unit_dime", null);
        cursor.moveToFirst();
        paramCursor.moveToFirst();
        //buildTree bt = new buildTree();
        //DefectTreeNode<InfoRow> defTree = bt.buildDefectTree(getApplicationContext());
        //String str = defTree.searchNodePath(defTree, 751510);
        int indexDef = 1;
        ArrayList<ais7IssoDefect> defectsList = new ArrayList<>();
        for(int i = 0; i < cursor.getCount(); i++) {

            ais7IssoDefect issoDefect = new ais7IssoDefect();
            issoDefect.Ord = i+1;
            issoDefect.NDef = cursor.getInt(cursor.getColumnIndex("N_DEF"));
            issoDefect.HasPhoto = !cursor.isNull(cursor.getColumnIndex("fotocount")) && cursor.getInt(cursor.getColumnIndex("fotocount")) > 0;
            int CGrConstr = cursor.getInt(cursor.getColumnIndex("C_GR_CONSTR"));
            MainActivity.defTree.path.clear();
            issoDefect.isDefCompleted = !cursor.isNull(cursor.getColumnIndex("date_end"));
            issoDefect.MainConstrId = cursor.getInt(cursor.getColumnIndex("MAIN_CONSTR_ID"));
            int c_constr = !cursor.isNull(cursor.getColumnIndex("N_CONSTR")) ? cursor.getInt(cursor.getColumnIndex("N_CONSTR")) : -100;
            String constr = MainActivity.defTree.getConstrFullName(MainActivity.defTree, CGrConstr, c_constr);
            String lDef = !cursor.isNull(cursor.getColumnIndex("L_DEF")) ? cursor.getString(cursor.getColumnIndex("L_DEF")) : "";
            String mainC = MainActivity.defTree.getConstrMainName(MainActivity.defTree, CGrConstr, c_constr);
            //issoDefect.Location = defTree.getConstrFullName(defTree, CGrConstr, c_constr);
            issoDefect.Location = mainC + (constr.equals("") ?  "" : ". " + constr) + (lDef.equals("") ? "" : ". " + lDef);
            issoDefect.NameConstr = MainActivity.defTree.getConstrMainName(MainActivity.defTree, CGrConstr, c_constr);
            String wDef = !cursor.isNull(cursor.getColumnIndex("W_DEF")) ? cursor.getString(cursor.getColumnIndex("W_DEF")) : "";
            issoDefect.NDefect = cursor.getString(cursor.getColumnIndex("N_DEFECT")) + (!wDef.equals("") ? ". " + wDef : "");
            int b = !cursor.isNull(cursor.getColumnIndex("B")) ? cursor.getInt(cursor.getColumnIndex("B")) : 0;
            int b1 = !cursor.isNull(cursor.getColumnIndex("B1")) ? cursor.getInt(cursor.getColumnIndex("B1")) : 0;
            int d = !cursor.isNull(cursor.getColumnIndex("D")) ? cursor.getInt(cursor.getColumnIndex("D")) : 0;
            int d1 = !cursor.isNull(cursor.getColumnIndex("D1")) ? cursor.getInt(cursor.getColumnIndex("D1")) : 0;
            int r = !cursor.isNull(cursor.getColumnIndex("R")) ? cursor.getInt(cursor.getColumnIndex("R")) : 0;
            int r1 = !cursor.isNull(cursor.getColumnIndex("R1")) ? cursor.getInt(cursor.getColumnIndex("R1")) : 0;
            boolean g = cursor.getString(cursor.getColumnIndex("G")).equals("True");
            boolean g1 = cursor.getString(cursor.getColumnIndex("G1")).equals("True");
            issoDefect.Category = "Б" + (b == b1 ? String.valueOf(b) : b + "/" + b1) +
                    ", Д" + (d == d1 ? String.valueOf(d) : d + "/" + d1) +
                    ", Р" + (r == r1 ? String.valueOf(r) : r + "/" + r1) +
                    (g ? (g != g1 ? ", (Г)" : ", Г") : "");
            /// определяемся с параметрами
            String param = "";
            paramCursor.moveToFirst();
            for(int j = 0; j < paramCursor.getCount(); j++ ) {
                if(paramCursor.getString(paramCursor.getColumnIndex("N_DEF")).equals(String.valueOf(issoDefect.NDef))) {
                    ais7DefectParamValue pv = new ais7DefectParamValue(paramCursor.getInt(paramCursor.getColumnIndex("C_DEFPARAM")), getApplicationContext());
                    param += pv.getFullName() + (!paramCursor.isNull(paramCursor.getColumnIndex("SN_UNIT_DIME")) ?
                            " (" + paramCursor.getString(paramCursor.getColumnIndex("SN_UNIT_DIME")) + ")" : "") +
                            (!paramCursor.isNull(paramCursor.getColumnIndex("VALUE")) ? String.format(" - %s",
                                    paramCursor.getString(paramCursor.getColumnIndex("VALUE"))) : "");
                }
                paramCursor.moveToNext();
            }
            issoDefect.Params = param;

            // теперь с ремонтной работой
            /*issoDefect.RemontInfo = !cursor.isNull(cursor.getColumnIndex("N_REM")) ? cursor.getString(cursor.getColumnIndex("N_REM")) : "" +
                    (cursor.isNull(cursor.getColumnIndex("SN_UNIT_DIME")) ?
                            " (" + cursor.getString(cursor.getColumnIndex("SN_UNIT_DIME")) + ")" : "") +
                    (cursor.isNull(cursor.getColumnIndex("V_REM")) ? String.format(" - %.2f", cursor.getDouble(cursor.getColumnIndex("V_REM"))) : "");*/
            issoDefect.RemontInfo = !cursor.isNull(cursor.getColumnIndex("N_REM")) ? cursor.getString(cursor.getColumnIndex("N_REM")) : "";
            if(!cursor.isNull(cursor.getColumnIndex("SN_UNIT_DIME")))
                issoDefect.RemontInfo += " (" + cursor.getString(cursor.getColumnIndex("SN_UNIT_DIME")) + ")";
            if(!cursor.isNull(cursor.getColumnIndex("V_REM")))
                issoDefect.RemontInfo += String.format(" - %s", cursor.getString(cursor.getColumnIndex("V_REM")));
            defectsList.add(issoDefect);
            cursor.moveToNext();
        }
        return defectsList;
    }

    public void addNodeToTree(ArrayList<InfoRow> defList, int parentId, TreeNode parent, Context context) {
        for(int i = 0; i < defList.size(); i++) {
            if(defList.get(i).PARENT_ID == parentId) {
                TreeNode node = new TreeNode(new IconTreeItemHolder.IconTreeItem(defList.get(i).DESCRIPTION,
                        defList.get(i).C_GR_CONSTR, 0, defList.get(i).SYS_NAME)).setViewHolder(new IconTreeItemHolder(context));
                addNodeToTree(defList, defList.get(i).C_GR_CONSTR, node, context);
                parent.addChild(node);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag", "I_DEFECT onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Tag", "I_DEFECT onStop");
    }

    public void onPause() {
        super.onPause();
        Log.d("Tag", "I_DEFECT onPause");
    }

    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            FillTable(defects);
        }
    };


    AlertDialog alert = null;
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_filter_def:
                /*AlertDialog.Builder builder = new AlertDialog.Builder(ABDM_TableLayout_I_DEFECT.this);
                builder.setTitle("Выберите фильтр по дефектам:");
                builder.setMultiChoiceItems(filterText.toArray(new String[filterText.size()]), null,
                        new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (which == filterText.size() - 1) {
                            for(int i = 0; i < filterText.size() - 1; i++ ) {
                                alert.getListView().setItemChecked(i, false);
                            }
                        }
                        else if(alert.getListView().isItemChecked(filterText.size() - 1)) {
                            alert.getListView().setItemChecked(filterText.size() - 1, false);
                        }
                    }
                });
                builder.setPositiveButton("Применить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        defFilter.clear();
                        for(int i = 0; i < filterText.size() - 1; i++) {
                            if(alert.getListView().isItemChecked(i))
                                defFilter.add(String.valueOf(filterConstrId.get(i)));
                        }
                        if(alert.getListView().isItemChecked(filterText.size() - 1)) {
                            defFilter.add("ALL");
                        }
                        FillTable(defects);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert = builder.create();
                for(String filter : defFilter) {
                    switch(filter) {
                        case "ALL":
                            alert.getListView().setItemChecked(filterText.size() - 1, true);
                            break;
                        case "":
                            break;
                        default:
                            alert.getListView().setItemChecked(filterConstrId.indexOf(Integer.parseInt(filter)), true);
                            break;
                    }
                }
                alert.show();*/
                CustomDefectDialog defectDialog = new CustomDefectDialog(ABDM_TableLayout_I_DEFECT.this, handler);
                defectDialog.show();
                Window window = defectDialog.getWindow();
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                break;
        }
    }

    class SyncModule extends AsyncTask<Void, String, Void> {

        public ProgressDialog dialog;

        SyncModule() {}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = new ProgressDialog(ABDM_TableLayout_I_DEFECT.this);
            this.dialog.setIndeterminate(true);
            this.dialog.setCancelable(false);
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            publishProgress("Пожалуйста, подождите...");
            defects = CreateDefectList(C_ISSO);
            CreateFilterAttributes(defects);
            handlerName.sendEmptyMessage(0);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (dialog.isShowing())
                this.dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(values[0]);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)	{
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Tag", "I_DEFECT onDestroy");
        defFilter.clear();
        filterConstrId.clear();
        filterText.clear();
    }

    public void FillTable(final ArrayList<ais7IssoDefect> defects) {
        tableInfoDefects.removeAllViews();
        if(MainActivity.theme.equals("0"))
            tableInfoDefects.setBackground(getDrawable(R.drawable.cell_borders_dark));
        else
            tableInfoDefects.setBackground(getDrawable(R.drawable.cell_borders_light));
        int i = 0;
        boolean once = true;
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        TableRow.LayoutParams l = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        for(final ais7IssoDefect defect : defects) {
            if(i == 0 && once) {
                TableRow trMain = new TableRow(this);
                if(MainActivity.theme.equals("0"))
                    trMain.setBackground(getDrawable(R.drawable.cell_group_shape_dark));
                else
                    trMain.setBackground(getDrawable(R.drawable.cell_group_shape_light));
                trMain.addView(setTextViewDefect("№", metrics.widthPixels, false), l);
                trMain.addView(setTextViewDefect("Местоположение дефекта", metrics.widthPixels, false), l);
                trMain.addView(setTextViewDefect("Тип и описание дефекта", metrics.widthPixels, false), l);
                trMain.addView(setTextViewDefect("Определяющие параметры степени развития и их значения", metrics.widthPixels, false), l);
                trMain.addView(setTextViewDefect("Категории дефекта", metrics.widthPixels, false), l);
                trMain.addView(setTextViewDefect("Характеристика объема дефекта по ремонтопригодности", metrics.widthPixels, false), l);
                tableInfoDefects.addView(trMain);
                once = false;
            }
            TableRow tr = new TableRow(this);
            switch (defFilter.get(0)) {
                case "":
                    if(!defect.isDefCompleted) {
                        if(MainActivity.theme.equals("0"))
                            tr.setBackground(getDrawable(R.drawable.cell_shape_dark));
                        else
                            tr.setBackground(getDrawable(R.drawable.cell_shape_light));
                        tr.addView(setTextViewDefect(String.format("%s/%s%s", i + 1, defect.NDef, defect.HasPhoto ? " (ф)" : ""), metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.Location, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.NDefect, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.Params, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.Category, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.RemontInfo, metrics.widthPixels, false), l);
                        tableInfoDefects.addView(tr);
                        i++;
                        if(MainActivity.theme.equals("0"))
                            ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_light);
                        else
                            ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_dark);
                    }
                    break;
                case "ALL":
                    if(MainActivity.theme.equals("0"))
                        tr.setBackground(getDrawable(R.drawable.cell_shape_dark));
                    else
                        tr.setBackground(getDrawable(R.drawable.cell_shape_light));
                    if(defect.isDefCompleted) {
                        tr.addView(setTextViewDefect(String.format("%s/%s%s", i + 1, defect.NDef, defect.HasPhoto ? " (ф)" : ""), metrics.widthPixels, true), l);
                    }
                    else {
                        tr.addView(setTextViewDefect(String.format("%s/%s%s", i + 1, defect.NDef, defect.HasPhoto ? " (ф)" : ""), metrics.widthPixels, false), l);
                    }
                    tr.addView(setTextViewDefect(defect.Location, metrics.widthPixels, false), l);
                    tr.addView(setTextViewDefect(defect.NDefect, metrics.widthPixels, false), l);
                    tr.addView(setTextViewDefect(defect.Params, metrics.widthPixels, false), l);
                    tr.addView(setTextViewDefect(defect.Category, metrics.widthPixels, false), l);
                    tr.addView(setTextViewDefect(defect.RemontInfo, metrics.widthPixels, false), l);
                    tableInfoDefects.addView(tr);
                    i++;
                    if(MainActivity.theme.equals("0"))
                        ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_enable_dark);
                    else
                        ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_enable_light);
                    break;
                default:
                    if(defFilter.contains(String.valueOf(defect.MainConstrId))) {
                        if(MainActivity.theme.equals("0"))
                            tr.setBackground(getDrawable(R.drawable.cell_shape_dark));
                        else
                            tr.setBackground(getDrawable(R.drawable.cell_shape_light));
                        tr.addView(setTextViewDefect(String.format("%s/%s%s", i + 1, defect.NDef, defect.HasPhoto ? " (ф)" : ""), metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.Location, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.NDefect, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.Params, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.Category, metrics.widthPixels, false), l);
                        tr.addView(setTextViewDefect(defect.RemontInfo, metrics.widthPixels, false), l);
                        tableInfoDefects.addView(tr);
                        i++;
                        if(MainActivity.theme.equals("0"))
                            ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_enable_dark);
                        else
                            ((FloatingActionButton)findViewById(R.id.btn_filter_def)).setImageResource(R.drawable.filter_enable_light);
                    }
                    break;
            }
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AdvancedDefectActivity.class);
                    intent.putExtra("C_ISSO", C_ISSO);
                    intent.putExtra("N_DEF", defect.NDef);
                    //intent.putExtra("defNode", defects.get(finalI));
                    startActivityForResult(intent, 0);
                }
            });
        }
        Toast.makeText(getApplicationContext(), "Всего дефектов показано: " + i + " из " + defects.size(), Toast.LENGTH_SHORT).show();
        btnDefects.setOnClickListener(this);
    }

    public void CreateFilterAttributes(ArrayList<ais7IssoDefect> defects) {
        String sqlArg = "";
        for(ais7IssoDefect defect : defects) {
            if(!sqlArg.contains(String.valueOf(defect.MainConstrId)))
            sqlArg += "'" + defect.MainConstrId + "',";
        }
        sqlArg = sqlArg.substring(0, sqlArg.length() - 1);
        Cursor cursor = new DBHelper(this).getReadableDatabase().rawQuery("select C_GR_CONSTR, TEXT from " +
                "S_GR_CONSTR where C_GR_CONSTR in (" + sqlArg + ")", null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            filterConstrId.add(cursor.getInt(0));
            filterText.add(cursor.getString(1));
            cursor.moveToNext();
        }
    }

    public LinearLayout setTextViewDefect(Object obj, int widthPixels, boolean defCompleted) {
        //TableRow.LayoutParams l = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        TextView tv = new TextView(this);
        LinearLayout layout = new LinearLayout(this);
        if(MainActivity.theme.equals("0"))
            layout.setBackground(getDrawable(R.drawable.cell_shape_dark));
        else
            layout.setBackground(getDrawable(R.drawable.cell_shape_light));
        final String str = "ЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖ";
        //tv.setMaxWidth((widthPixels * 2) / 3);
        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linParams.setMargins(10, 20, 10, 20);
        linParams.gravity = Gravity.CENTER;
        Paint paint = new Paint();
        tv.setMaxWidth((int) paint.measureText(str));
        if(defCompleted) {
            if(MainActivity.theme.equals("0"))
                tv.setTextColor(Color.rgb(122, 122, 255));
            else
                tv.setTextColor(Color.rgb(122, 122, 255));
        }

        tv.setText(obj.toString());
        layout.addView(tv, linParams);
        return layout;
    }

    android.os.Handler handlerName = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            defFilter = new ArrayList<>();
            defFilter.add("");
            FillTable(defects);
        }
    };
}

class ais7IssoDefect implements Serializable {
    /** Номер дефекта */
    public int NDef;
    /** Порядковый номер в ведомости */
    public int Ord;
    /** Наличие фотографий для дефекта */
    public boolean HasPhoto;
    /** Наличие устранения дефекта*/
    public boolean isDefCompleted;
    /** Местоположение дефекта */
    public String Location;
    /** Местоположение дефекта */
    public String NameConstr;
    /** Тип и описание дефекта */
    public String NDefect;
    /** Определяющие параметры степени развития и их значение */
    public String Params;
    /** Категории дефекта */
    public String Category;
    /** Характеристика объема дефекта по ремонтоспособности */
    public String RemontInfo;
    /** Номер главной группы конструкций*/
    public int MainConstrId;



    public String ToString() {
        return String.format("{0}/{1}{2}", Ord, NDef, HasPhoto ? " (ф)" : "");
    }
}

class buildTree {
    public static DefectTreeNode<InfoRow> buildDefectTree(Context context) {
        try {
            ArrayList<InfoRow> defList = new ArrayList<>();
            /** 1) Построение дерева по типам конструкций*/
            Cursor cr = new DBHelper(context).getReadableDatabase().rawQuery("select TABLE_NAMES.C_GR_CONSTR, TABLE_NAMES.SYS_NAME, TABLE_NAMES.DESCRIPTION, " +
                    "TABLE_NAMES.PARENT_VIEW, PARENT_ID, S_GR_CONSTR.ITEM_TYPE from TABLE_NAMES\n" +
                    " left outer join (select * from TABLE_ATTRIBUTES where TABLE_ATTRIBUTES.SYS_NAME = 'C_ISSO') " +
                    "TABLE_ATTRIBUTES on TABLE_ATTRIBUTES.C_GR_CONSTR = TABLE_NAMES.C_GR_CONSTR\n" +
                    "left outer join S_GR_CONSTR on S_GR_CONSTR.C_GR_CONSTR=TABLE_NAMES.C_GR_CONSTR\n" +
                    " left outer join TABLE_VALUES on TABLE_VALUES.ATTRIBUTE_ID = TABLE_ATTRIBUTES.ID\n" +
                    "where TABLE_NAMES.PARENT_VIEW != -1 and TABLE_NAMES.C_GR_CONSTR in (select distinct C_GR_CONSTR from S_DEF4CONSTR where C_GR_CONSTR < 100000 " +
                    "and C_GR_CONSTR > 0\n" +
                    "union all\n" +
                    "select distinct PARENT_GROUP from S_GR_CONSTR where PARENT_GROUP < 100000 and PARENT_GROUP > 0)\n" +
                    "group by TABLE_NAMES.C_GR_CONSTR, TABLE_NAMES.SYS_NAME, TABLE_NAMES.DESCRIPTION, TABLE_NAMES.PARENT_VIEW, PARENT_ID, ITEM_TYPE", null);
            cr.moveToFirst();
            for (int i = 0; i < cr.getCount(); i++) {
                defList.add(new InfoRow("", cr.getString(cr.getColumnIndex("DESCRIPTION")),
                        cr.getInt(cr.getColumnIndex("PARENT_ID")), cr.getInt(cr.getColumnIndex("C_GR_CONSTR")),
                        cr.getInt(cr.getColumnIndex("ITEM_TYPE"))));
                cr.moveToNext();
            }
            cr.close();
            /** 2) Добавление дефектов к конструкциям */
            cr = new DBHelper(context).getReadableDatabase().rawQuery("select C_GR_CONSTR, TEXT, PARENT_GROUP, ITEM_TYPE from S_GR_CONSTR where C_GR_CONSTR > 100000", null);
            cr.moveToFirst();
            for (int i = 0; i < cr.getCount(); i++) {
                defList.add(new InfoRow("", cr.getString(cr.getColumnIndex("TEXT")),
                        cr.getInt(cr.getColumnIndex("PARENT_GROUP")), cr.getInt(cr.getColumnIndex("C_GR_CONSTR")),
                        cr.getInt(cr.getColumnIndex("ITEM_TYPE"))));
                cr.moveToNext();
            }
            cr.close();
        /*TreeNode root = null, node = null;
        int parentId = -1;
        for(InfoRow row : defList) {
            if(row.C_GR_CONSTR == 10) {
                root = TreeNode.root();
                node = new TreeNode(new IconTreeItemHolder.IconTreeItem(row.DESCRIPTION, row.C_GR_CONSTR, 0, row.SYS_NAME)).
                        setViewHolder(new IconTreeItemHolder(context));
                parentId = row.C_GR_CONSTR;
                break;
            }
        }
        for(int i = 0; i < defList.size(); i++) {
            if(defList.get(i).PARENT_ID == parentId) {
                TreeNode treenode = new TreeNode(new IconTreeItemHolder.IconTreeItem(defList.get(i).DESCRIPTION,
                        defList.get(i).C_GR_CONSTR, 0, defList.get(i).SYS_NAME)).setViewHolder(new IconTreeItemHolder(context));
                assert node != null;
                addNodeToTree(defList, defList.get(i).C_GR_CONSTR, treenode, context);
                node.addChild(treenode);
            }
        }
        root.addChild(node);*/
            DefectTreeNode<InfoRow> root = null;
            int parentId = -1;
            for (InfoRow row : defList) {
                if (row.C_GR_CONSTR == 10) {
                    root = new DefectTreeNode<>(new InfoRow("", row.DESCRIPTION, row.PARENT_ID, row.C_GR_CONSTR, row.countOfColumns));
                    parentId = row.C_GR_CONSTR;
                    break;
                }
            }
            for (int i = 0; i < defList.size(); i++) {
                if (defList.get(i).PARENT_ID == parentId) {
                    DefectTreeNode<InfoRow> child = root.addChild(new InfoRow("", defList.get(i).DESCRIPTION, defList.get(i).PARENT_ID,
                            defList.get(i).C_GR_CONSTR, defList.get(i).countOfColumns));
                    assert root != null;
                    addDefNodeToTree(defList, defList.get(i).C_GR_CONSTR, child, context);
                }
            }
            return root;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void addDefNodeToTree(ArrayList<InfoRow> defList, int parentId, DefectTreeNode<InfoRow> parent, Context context) {
        for(int i = 0; i < defList.size(); i++) {
            if(defList.get(i).PARENT_ID == parentId) {
                DefectTreeNode<InfoRow> child = parent.addChild(new InfoRow("", defList.get(i).DESCRIPTION, defList.get(i).PARENT_ID,
                        defList.get(i).C_GR_CONSTR, defList.get(i).countOfColumns));
                addDefNodeToTree(defList, defList.get(i).C_GR_CONSTR, child, context);
            }
        }
    }
}

class ais7DefectParamValue {

    public String Name;
    /// <summary>
    /// Признак того, что это качественный параметр
    /// </summary>
    public Boolean IsQual;
    /// <summary>
    /// Категория параметра
    /// </summary>
    public int Category;
    /// <summary>
    /// Единицы измерения
    /// </summary>
    public String snUnit;

    public Context context;

    public ais7DefectParamValue(int cDefParam, Context context) {
        this.context = context;
        Cursor cr = new DBHelper(context).getReadableDatabase().rawQuery(
                "select p.c_defparam, N_DEFPARAM, CATEGORY, case when pv.c_unit_dimen='' then 1 else 0 end as IsQual, SN_UNIT_DIME " +
                        "from S_DEFPARAM p " +
                        "left outer join S_DEFPARAMVALUE pv on pv.C_DEFPARAM = p.C_DEFPARAM " +
                        "left outer join S_UNIT_DIMENSION u on u.C_UNIT_DIMEN = pv.c_unit_dimen " +
                        "where p.C_DEFPARAM=" + cDefParam + " limit 1", null);
        cr.moveToFirst();
        if(cr.getInt(0) != 0) {
            Name = cr.getString(cr.getColumnIndex("N_DEFPARAM"));
            Category = cr.getInt(cr.getColumnIndex("CATEGORY")) == 0 ? -1 : cr.getInt(cr.getColumnIndex("CATEGORY"));
            IsQual = cr.getInt(cr.getColumnIndex("IsQual")) == 1;
            snUnit = IsQual ? "" : cr.getString(cr.getColumnIndex("SN_UNIT_DIME"));
        }
    }

    public String getFullName() {
        CategoryItem categoryItem = new CategoryItem();
        List<String> cl = categoryItem.CatItems(Category);
        cl.add(0, Name);
        return  TextUtils.join(". ", cl.toArray());
    }

    public class CategoryItem {
        public int Id;
        public int Parent;
        public String Name;

        /// <summary>
        /// Полный список возможных категорий
        /// </summary>
        public List<CategoryItem> getCatList() {
            List<CategoryItem> catList = new ArrayList<>();
            Cursor cr = new DBHelper(context).getReadableDatabase().rawQuery("select * from s_defparamcategory", null);
            cr.moveToFirst();
            for(int i = 0; i < cr.getCount(); i++ ){
                CategoryItem item = new CategoryItem();
                item.Id = cr.getInt(cr.getColumnIndex("ID"));
                item.Name = cr.getString(cr.getColumnIndex("NAME"));
                item.Parent = cr.isNull(cr.getColumnIndex("PARENT")) ? -1 : cr.getInt(cr.getColumnIndex("PARENT"));
                catList.add(item);
                cr.moveToNext();
            }
                /*catList = ais7AppCoordinator.GetModule<Iais7Database>().ExecSQLReader("select * from s_defparamcategory").Select().ToList().
                        ConvertAll(i => new CategoryItem()
                {
                    Id = Convert.ToInt16(i["id"]),
                    Name = i["name"].ToString(),
                    Parent = i["parent"] == DBNull.Value ? (short)(-1) : Convert.ToInt16(i["parent"])
                }).ToList();*/
            return catList;
        }

        public List<String> CatItems(int catId) {
            List<String> items = new ArrayList<>();
            List<CategoryItem> catList = getCatList();
            CategoryItem ci = FirstOrDefault(catList, catId);
            while (ci != null) {
                items.add(ci.Name);
                if(ci.Parent == -1) break;
                catId = ci.Parent;
                ci = FirstOrDefault(catList, catId);
            }
            Collections.reverse(items);
            return items;
        }

        public CategoryItem FirstOrDefault(List<CategoryItem> catList, int catId) {
            for(CategoryItem item : catList) {
                if(item.Id == catId) {
                    return item;
                }
            }
            return null;
        }
    }



}

class DefectTreeNode<InfoRow> implements Iterable<DefectTreeNode<InfoRow>> {

    com.development.aisisso.isso_i.InfoRow data;
    DefectTreeNode<InfoRow> parent;
    List<DefectTreeNode<InfoRow>> children;
    List<String> path = new ArrayList<>();
    DefectTreeNode<InfoRow> result;

    public DefectTreeNode(com.development.aisisso.isso_i.InfoRow data) {
        this.data = data;
        this.children = new LinkedList<>();
    }

    public DefectTreeNode<InfoRow> addChild(com.development.aisisso.isso_i.InfoRow child) {
        DefectTreeNode<InfoRow> childNode = new DefectTreeNode<>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    public DefectTreeNode<InfoRow> getParent(DefectTreeNode<InfoRow> child) {
        if(child != null && child.parent != null)
            return child.parent;
        else return null;
    }

    @Override
    public Iterator<DefectTreeNode<InfoRow>> iterator() {
        return null;
    }

    public String getConstrFullName(DefectTreeNode<InfoRow> def, int C_GR_CONSTR, int N_CONSTR) {
        result = null;
        String fullName = searchFullPath(def, C_GR_CONSTR);
        String mainName = searchMainPath(result, C_GR_CONSTR, N_CONSTR);
        if(fullName.indexOf(mainName.substring(0, mainName.length() - 3)) == 0) return !fullName.equals(mainName) ?
                (fullName.length() > mainName.length() ? (mainName.substring(mainName.length() - 2, mainName.length() - 1).equals("№")
                        ? fullName.substring(mainName.length() - 1) :
                        fullName.substring(mainName.length() + 1)) : "") : "";
        else return fullName;
    }

    public String getConstrMainName(DefectTreeNode<InfoRow> def, int C_GR_CONSTR, int N_CONSTR) {
        result = null;
        searchFullPath(def, C_GR_CONSTR);
        return searchMainPath(result, C_GR_CONSTR, N_CONSTR);
    }

    public String searchFullPath(DefectTreeNode<InfoRow> root, int C_GR_CONSTR) {
        if(getParent(root) == null) {
            //path.add(root.data.DESCRIPTION);
            result = root;
        }

        if(root.data.C_GR_CONSTR == C_GR_CONSTR) {
            String[] arr = new String[path.size()];
            path.toArray(arr);
            result = root;
            return TextUtils.join(". ", arr);
        }

        if(!root.children.isEmpty()) {
            for(DefectTreeNode<InfoRow> child : root.children) {
                if(child.data.countOfColumns == 2 || child.data.countOfColumns == 3) {
                    DefectTreeNode<InfoRow> def = getParent(child);
                    if(def != null && getParent(def) != null) {
                        path.add(child.data.DESCRIPTION);
                    }
                    String addPath = searchFullPath(child, C_GR_CONSTR);
                    if (!addPath.equals(""))
                        return addPath;
                    else {
                        if(def != null && getParent(def) != null) {
                            path.remove(path.size() - 1);
                        }
                    }
                }
                else {
                    String addPath = searchFullPath(child, C_GR_CONSTR);
                    if (!addPath.equals(""))
                        return addPath;
                }
            }
        }
        return "";
    }

    public String searchMainPath(DefectTreeNode<InfoRow> root, int C_GR_CONSTR, int N_CONSTR) {
        if((getParent(getParent(root)) == null || root.data.countOfColumns == 100)) {
            if(N_CONSTR > 0) return root.data.DESCRIPTION + " №" + N_CONSTR;
            else return root.data.DESCRIPTION;
        }
        else if(getParent(root).data.C_GR_CONSTR > 100000)
            return searchMainPath(getParent(root), C_GR_CONSTR, N_CONSTR);
        else
            return searchMainPathLower100000(getParent(root), C_GR_CONSTR, N_CONSTR);
    }

    public String searchMainPathLower100000(DefectTreeNode<InfoRow> root, int C_GR_CONSTR, int N_CONSTR) {
        if(N_CONSTR == -1) return  root.data.DESCRIPTION;
        else return searchMainPath(root, C_GR_CONSTR, N_CONSTR);
    }
}

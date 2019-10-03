package com.development.aisisso.isso_i;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.development.aisisso.isso_i.model.TreeNode;
import com.development.aisisso.isso_i.view.AndroidTreeView;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;


public class TreeViewActivity extends android.support.v4.app.Fragment {

    public int C_ISSO;
    public ArrayList<InfoRow> InfoRows;
    private View view;

    public TreeViewActivity() {}
    public TreeViewActivity(int C_ISSO) {
        this.C_ISSO = C_ISSO;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tree_view_layout, container, false);
        if (!PreferenceManager.getDefaultSharedPreferences(view.getContext()).getString("Theme", "0").equals("0")) {
            view.findViewById(R.id.LayoutViewInfo).setBackgroundColor(getResources().getColor(R.color.background_material_light));
        }
        SQLiteDatabase db = new DBHelper(view.getContext()).getReadableDatabase();
        Cursor cr = db.query("I_ISSO", new String[]{"FULLNAME", "LATITUDE", "LONGITUDE", "LENGTH", "N_OTC_EXP"}, "C_ISSO = " + C_ISSO, null, null, null, null);
        cr.moveToFirst();
        ((TextView)view.findViewById(R.id.textViewDescription)).setText(cr.getString(cr.getColumnIndex("FULLNAME")));
        FloatingActionButton GeoBtn;
        GeoBtn = (FloatingActionButton) view.findViewById(R.id.fabGeo);
        if(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Theme", "0").equals("0")) {
            GeoBtn.setColorNormal(getResources().getColor(R.color.fabMenuDark));
            GeoBtn.setColorPressed(getResources().getColor(R.color.fabMenuDarkPressed));
            GeoBtn.setImageResource(R.drawable.define_location_light);
            GeoBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            GeoBtn.setButtonSize(1);
            GeoBtn.setLabelText("Геопозиция");
        }
        else {
            GeoBtn.setColorNormal(getResources().getColor(R.color.fabMenuLight));
            GeoBtn.setColorPressed(getResources().getColor(R.color.fabMenuLightPressed));
            GeoBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            GeoBtn.setImageResource(R.drawable.define_location_dark);
            GeoBtn.setButtonSize(1);
            GeoBtn.setLabelText("Геопозиция");
        }

        GeoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ViewIssoGeolocation.class);
                startActivity(intent);
            }
        });
        cr.close();

        SyncModule sm = new SyncModule(view);
        sm.execute();

        return view;
    }

    class SyncModule extends AsyncTask<Void, String, Void> {

        public ProgressDialog dialog;
        private View view;

        SyncModule(View view) {
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = new ProgressDialog(view.getContext());
            this.dialog.setIndeterminate(true);
            this.dialog.setCancelable(false);
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            publishProgress("Пожалуйста, подождите...");
            InfoRows = doSQLCount(C_ISSO, view);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (dialog.isShowing())
                this.dialog.dismiss();
            if(InfoRows.size() > 0)
                doTheStuff(view);
            else {
                TextView tv = new TextView(view.getContext());
                tv.setTextSize(16);
                tv.setText("Нет информации по данному сооружению");
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                ((LinearLayout) view.findViewById(R.id.LayoutViewInfo)).addView(tv);
                (view.findViewById(R.id.LayoutViewInfo)).setLayoutParams(new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(values[0]);
        }
    }

    public ArrayList<InfoRow> doSQLCount(int cIsso, View view) {
        ArrayList<InfoRow> copy = new ArrayList<>();
        Cursor cr = new DBHelper(view.getContext()).getReadableDatabase().rawQuery("select TABLE_NAMES.C_GR_CONSTR, TABLE_NAMES.SYS_NAME, TABLE_NAMES.DESCRIPTION, " +
                "PARENT_VIEW, PARENT_ID, count(value) as t_value from TABLE_NAMES\n" +
                " left outer join (select * from TABLE_ATTRIBUTES where TABLE_ATTRIBUTES.SYS_NAME = 'C_ISSO') " +
                "TABLE_ATTRIBUTES on TABLE_ATTRIBUTES.C_GR_CONSTR = TABLE_NAMES.C_GR_CONSTR\n" +
                " left outer join (select * from TABLE_VALUES where ISSO='" + cIsso + "') TABLE_VALUES on TABLE_VALUES.ATTRIBUTE_ID = TABLE_ATTRIBUTES.ID\n" +
                "where TABLE_NAMES.PARENT_VIEW != -1\n" +
                "group by TABLE_NAMES.C_GR_CONSTR, TABLE_NAMES.SYS_NAME, TABLE_NAMES.DESCRIPTION, PARENT_VIEW, PARENT_ID", null);
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++) {
                if (cr.getInt(cr.getColumnIndex("t_value")) > 0) {
                    copy.add(new InfoRow(cr.getString(cr.getColumnIndex("SYS_NAME")), cr.getString(cr.getColumnIndex("DESCRIPTION")),
                            cr.getInt(cr.getColumnIndex("PARENT_ID")), cr.getInt(cr.getColumnIndex("C_GR_CONSTR")), cr.getInt(cr.getColumnIndex("t_value"))));
                }
                cr.moveToNext();
            }
        }
        cr.close();
        return copy;
    }

    public void addNodeToTree(int parentId, TreeNode parent, View view) {
        for(int i = 0; i < InfoRows.size(); i++) {
            if(InfoRows.get(i).PARENT_ID == parentId) {
                TreeNode node = new TreeNode(new IconTreeItemHolder.IconTreeItem(InfoRows.get(i).DESCRIPTION + " [" + InfoRows.get(i).countOfColumns + "]",
                        InfoRows.get(i).C_GR_CONSTR, C_ISSO, InfoRows.get(i).SYS_NAME)).setViewHolder(new IconTreeItemHolder(view.getContext()));
                addNodeToTree(InfoRows.get(i).C_GR_CONSTR, node, view);
                parent.addChild(node);
            }
        }
    }

    public void doTheStuff(View view) {
        TreeNode root = null, treeNode = null;
        int parentId = -1;
        for(InfoRow row : InfoRows) {
            if(row.PARENT_ID == -100) {
                root = TreeNode.root();
                treeNode = new TreeNode(new IconTreeItemHolder.IconTreeItem(row.DESCRIPTION, row.C_GR_CONSTR, C_ISSO, row.SYS_NAME)).
                        setViewHolder(new IconTreeItemHolder(view.getContext()));
                parentId = row.C_GR_CONSTR;
                break;
            }
        }
        for(int i = 0; i < InfoRows.size(); i++) {
            if(InfoRows.get(i).PARENT_ID == parentId) {
                TreeNode node = new TreeNode(new IconTreeItemHolder.IconTreeItem(InfoRows.get(i).DESCRIPTION +  " [" + InfoRows.get(i).countOfColumns + "]",
                        InfoRows.get(i).C_GR_CONSTR, C_ISSO, InfoRows.get(i).SYS_NAME)).setViewHolder(new IconTreeItemHolder(view.getContext()));
                assert treeNode != null;
                addNodeToTree(InfoRows.get(i).C_GR_CONSTR, node, view);
                treeNode.addChild(node);
            }
        }
        root.addChild(treeNode);
        AndroidTreeView treeView = new AndroidTreeView(view.getContext(), root);
        treeView.setUse2dScroll(true);
        treeView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        ViewGroup layout = (ViewGroup) view.findViewById(R.id.LayoutViewInfo);
        layout.addView(treeView.getView());
        treeView.expandLevel(1);
    }

    public void CreateTreeViewInList() {
        DefectTreeNode<InfoRow> listOfItems = doTheTree();
    }


    /** Построение дерева, здесь дерево дефектов используется для того,
     * чтобы не дублировать практически одинаковые классы */
    public DefectTreeNode<InfoRow> doTheTree() {
        DefectTreeNode<InfoRow> root = null;
        int parentId = -1;
        for (InfoRow row : InfoRows) {
            if (row.C_GR_CONSTR == 10) {
                root = new DefectTreeNode<>(new InfoRow(row.SYS_NAME, row.DESCRIPTION, row.PARENT_ID, row.C_GR_CONSTR, row.countOfColumns));
                parentId = row.C_GR_CONSTR;
                break;
            }
        }
        for (int i = 0; i < InfoRows.size(); i++) {
            if (InfoRows.get(i).PARENT_ID == parentId) {
                DefectTreeNode<InfoRow> child = root.addChild(new InfoRow(InfoRows.get(i).SYS_NAME, InfoRows.get(i).DESCRIPTION, InfoRows.get(i).PARENT_ID,
                        InfoRows.get(i).C_GR_CONSTR, InfoRows.get(i).countOfColumns));
                assert root != null;
                addDefNodeToTree(InfoRows, InfoRows.get(i).C_GR_CONSTR, child);
            }
        }
        return root;
    }

    public void addDefNodeToTree(ArrayList<InfoRow> infoRows, int parentId, DefectTreeNode<InfoRow> parent) {
        for(int i = 0; i < infoRows.size(); i++) {
            if(infoRows.get(i).PARENT_ID == parentId) {
                DefectTreeNode<InfoRow> child = parent.addChild(new InfoRow(infoRows.get(i).SYS_NAME, infoRows.get(i).DESCRIPTION, infoRows.get(i).PARENT_ID,
                        infoRows.get(i).C_GR_CONSTR, infoRows.get(i).countOfColumns));
                addDefNodeToTree(infoRows, infoRows.get(i).C_GR_CONSTR, child);
            }
        }
    }

}

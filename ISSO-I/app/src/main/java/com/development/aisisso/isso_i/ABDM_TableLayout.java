package com.development.aisisso.isso_i;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ABDM_TableLayout extends AppCompatActivity {

    public int cIsso;
    public int TableID;
    public int C_GR_CONSTR;
    public String DESCRIPTION;
    public TableLayout tableNameColumns;
    public TableLayout tableInfoColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0"))
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);

        setContentView(R.layout.abdm_table_layout);
        /** */
        TableID = getIntent().getIntExtra("TABLE_ID", -1);
        cIsso = getIntent().getIntExtra("C_ISSO", -1);
        C_GR_CONSTR = getIntent().getIntExtra("C_GR_CONSTR", -1);
        DESCRIPTION = getIntent().getStringExtra("DESCRIPTION");
        /** */
        final Toolbar toolbar = (Toolbar) findViewById(R.id.isso_tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        LinearLayout coordinatorLayout = (LinearLayout) findViewById(R.id.LayoutTableInfo);
        if (!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            assert ab != null;
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ab.setHomeAsUpIndicator(R.drawable.back_light);
        } else {
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }

        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.action_bar_title_layout);
        ((TextView) findViewById(R.id.action_bar_title)).setText("АИС ИССО-I." + " Сооружение №" + cIsso );
        ((TextView) findViewById(R.id.action_bar_subtitle)).setText(DESCRIPTION);
        getSupportActionBar().setTitle("");
        if(MainActivity.theme.equals("0")) {
            ((TextView) findViewById(R.id.action_bar_title)).setTextColor(getResources().getColor(R.color.bright_foreground_material_dark));
            ((TextView) findViewById(R.id.action_bar_subtitle)).setTextColor(getResources().getColor(R.color.bright_foreground_material_dark));
        }
        tableNameColumns = (TableLayout) findViewById(R.id.LayoutNameColumns);
        tableInfoColumns = (TableLayout) findViewById(R.id.LayoutInfoColumns);
        final CustomHorizontalScrollView horizontalScrollView = (CustomHorizontalScrollView) findViewById(R.id.horizontalScrollView);

        if(MainActivity.theme.equals("0")) {
            tableNameColumns.setBackground(getDrawable(R.drawable.cell_borders_dark));
            tableInfoColumns.setBackground(getDrawable(R.drawable.cell_borders_dark));
        }
        else {
            tableNameColumns.setBackground(getDrawable(R.drawable.cell_borders_light));
            tableInfoColumns.setBackground(getDrawable(R.drawable.cell_borders_light));
        }


        SyncModule sm = new SyncModule();
        sm.execute();

        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final CustomScrollView myScrollView = (CustomScrollView) findViewById(R.id.myScrollView);
        /*myScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getX() > tableNameColumns.getWidth() - 50 || event.getX() < tableNameColumns.getWidth() + 50)
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            myScrollView.setEnableScrolling(false);
                            horizontalScrollView.setHorizontalScrollBarEnabled(false);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float x = event.getRawX();
                            tableNameColumns.getLayoutParams().width = (int) (x);
                            horizontalScrollView.getLayoutParams().width = (int) (metrics.widthPixels - x);
                            tableNameColumns.requestLayout();
                            horizontalScrollView.requestLayout();
                            Log.d("Tag", x + ": " + tableNameColumns.getLayoutParams().width + " : " + horizontalScrollView.getLayoutParams().width);
                            break;
                        case MotionEvent.ACTION_UP:
                            myScrollView.setEnableScrolling(true);
                            horizontalScrollView.setHorizontalScrollBarEnabled(true);
                            break;
                    }
                return true;
            }
        });*/
        tableNameColumns.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getX() > tableNameColumns.getWidth() - 50)
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            myScrollView.setEnableScrolling(false);
                            horizontalScrollView.setEnableScrolling(false);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float x = event.getRawX();
                            tableNameColumns.getLayoutParams().width = (int) (x);
                            horizontalScrollView.getLayoutParams().width = (int) (metrics.widthPixels - x);
                            tableNameColumns.requestLayout();
                            horizontalScrollView.requestLayout();
                            Log.d("Tag", x + ": " + tableNameColumns.getLayoutParams().width + " : " + horizontalScrollView.getLayoutParams().width);
                            break;
                        case MotionEvent.ACTION_UP:
                            myScrollView.setEnableScrolling(true);
                            horizontalScrollView.setEnableScrolling(true);
                            break;
                    }
                return true;
            }
        });

        tableInfoColumns.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getRawX() < tableNameColumns.getWidth() + 50)
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            myScrollView.setEnableScrolling(false);
                            horizontalScrollView.setEnableScrolling(false);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float x = event.getRawX();
                            tableNameColumns.getLayoutParams().width = (int) (x);
                            horizontalScrollView.getLayoutParams().width = (int) (metrics.widthPixels - x);
                            tableNameColumns.requestLayout();
                            horizontalScrollView.requestLayout();
                            Log.d("Tag", x + ": " + tableNameColumns.getLayoutParams().width + " : " + horizontalScrollView.getLayoutParams().width);
                            break;
                        case MotionEvent.ACTION_UP:
                            myScrollView.setEnableScrolling(true);
                            horizontalScrollView.setEnableScrolling(true);
                            break;
                    }
                return true;
            }
        });
    }

    class SyncModule extends AsyncTask<Void, String, Void> {

        public ProgressDialog dialog;
        public Cursor names, info;
        public int columnsCount = 0;

        SyncModule() {}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = new ProgressDialog(ABDM_TableLayout.this);
            this.dialog.setIndeterminate(true);
            this.dialog.setCancelable(false);
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            publishProgress("Пожалуйста, подождите...");
            names = getNamesTable();
            Message msg = new Message();
            msg.obj = names;
            handlerName.sendMessage(msg);
            info = getInfoTable();
            msg = new Message();
            msg.obj = info;
            handlerInfo.sendMessage(msg);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (dialog.isShowing())
                this.dialog.dismiss();
            onResume();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(values[0]);
        }

        public Cursor getNamesTable() {
            SQLiteDatabase database = new DBHelper(ABDM_TableLayout.this).getReadableDatabase();
            return database.rawQuery("select DESCRIPTION, SYS_NAME, CATEGORY from TABLE_ATTRIBUTES where C_GR_CONSTR=" + C_GR_CONSTR +
                    " and VISIBLEINGRID=1 and SYS_NAME !=\"C_ISSO\"", null);
        }

        public Cursor getInfoTable() {
            SQLiteDatabase database = new DBHelper(ABDM_TableLayout.this).getReadableDatabase();
            Cursor cr = database.rawQuery("select COUNT(*) as value from TABLE_ATTRIBUTES\n" +
                    "left outer join (select * from TABLE_VALUES where ISSO='" + cIsso + "') TABLE_VALUES on TABLE_ATTRIBUTES.ID=TABLE_VALUES.ATTRIBUTE_ID\n" +
                    "where C_GR_CONSTR=" + C_GR_CONSTR +"\n and TABLE_ATTRIBUTES.SYS_NAME !=\"C_ISSO\"" +
                    "group by CATEGORY, SYS_NAME, DESCRIPTION", null);
            if(cr.moveToFirst()) {
                columnsCount = cr.getInt(cr.getColumnIndex("value"));
                return database.rawQuery("select SYS_NAME, CATEGORY, VALUE from TABLE_ATTRIBUTES\n" +
                        "left outer join (select * from TABLE_VALUES where ISSO='" + cIsso + "') TABLE_VALUES on TABLE_ATTRIBUTES.ID=TABLE_VALUES.ATTRIBUTE_ID\n" +
                        "where C_GR_CONSTR="+ C_GR_CONSTR + " and VISIBLEINGRID=1 and TABLE_ATTRIBUTES.SYS_NAME !=\"C_ISSO\" and ISSO=" + cIsso, null);
            }
            else return null;
        }
        android.os.Handler handlerName = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Cursor name = (Cursor) msg.obj;
                doTheStuffForNames(name);
            }
        };

        android.os.Handler handlerInfo = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Cursor info = (Cursor) msg.obj;
                doTheStuffForInfo(info, columnsCount);
            }
        };
    }

    public void doTheStuffForNames(Cursor cr) {
        String categoryName = "-1";
        if(cr.moveToFirst()) {
            for(int i = 0; i < cr.getCount(); i++) {
                if(!categoryName.equals(cr.getString(cr.getColumnIndex("CATEGORY")))) {
                    categoryName = cr.getString(cr.getColumnIndex("CATEGORY"));
                    TableRow tr = new TableRow(this);
                    TextView tv = new TextView(this);
                    LinearLayout layout = new LinearLayout(this);
                    if(MainActivity.theme.equals("0")) {
                        tr.setBackground(getDrawable(R.drawable.cell_group_shape_dark));
                    }
                    else {
                        tr.setBackground(getDrawable(R.drawable.cell_group_shape_light));
                    }
                    if(!cr.getString(cr.getColumnIndex("CATEGORY")).equals(""))
                        tv.setText(cr.getString(cr.getColumnIndex("CATEGORY")));
                    else
                        tv.setText(DESCRIPTION);
                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    params.setMargins(20, 10, 20, 10);
                    params.gravity = Gravity.CENTER_VERTICAL;
                    layout.addView(tv, params);
                    tr.addView(layout);
                    tableNameColumns.addView(tr);
                }
                TableRow tr = new TableRow(this);
                TextView tv = new TextView(this);
                LinearLayout layout = new LinearLayout(this);
                if(MainActivity.theme.equals("0"))
                    tr.setBackground(getDrawable(R.drawable.cell_shape_dark));
                else
                    tr.setBackground(getDrawable(R.drawable.cell_shape_light));
                tv.setText(cr.getString(cr.getColumnIndex("DESCRIPTION")));
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                params.setMargins(20, 10, 20, 10);
                params.gravity = Gravity.CENTER_VERTICAL;
                layout.addView(tv, params);
                tr.addView(layout);
                tableNameColumns.addView(tr);
                cr.moveToNext();
            }
        }
        cr.close();
    }

    public void doTheStuffForInfo(Cursor c, int columnsCount) {
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String categoryName = "-1";
        final String str = "ЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖ";
        Paint paint = new Paint();

        if(c.moveToFirst()) {
            TableRow.LayoutParams l = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            l.setMargins(20, 10, 20, 10);
            for(int i = 0; i < c.getCount(); i += columnsCount) {
                if(!categoryName.equals(c.getString(c.getColumnIndex("CATEGORY")))) {
                    categoryName = c.getString(c.getColumnIndex("CATEGORY"));
                    TableRow tr = new TableRow(this);
                    TextView tv = new TextView(this);
                    LinearLayout layout = new LinearLayout(this);
                    if(MainActivity.theme.equals("0"))
                        tr.setBackground(getDrawable(R.drawable.cell_group_shape_dark));
                    else
                        tr.setBackground(getDrawable(R.drawable.cell_group_shape_light));
                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                    params.setMargins(20, 10, 20, 10);
                    params.gravity = Gravity.CENTER_VERTICAL;
                    tv.setMaxWidth((int) paint.measureText(str));
                    layout.addView(tv, params);
                    tr.addView(layout);
                    tableInfoColumns.addView(tr);
                }
                TableRow tr = new TableRow(this);
                for(int j = 0; j < columnsCount; j++) {
                    TextView tv = new TextView(this);
                    LinearLayout layout = new LinearLayout(this);
                    if(MainActivity.theme.equals("0"))
                        tr.setBackground(getDrawable(R.drawable.cell_shape_dark));
                    else
                        tr.setBackground(getDrawable(R.drawable.cell_shape_light));
                    tv.setMaxWidth((metrics.widthPixels * 2) / 3);
                    //tv.setHorizontallyScrolling(false);
                    tv.setText(c.getString(c.getColumnIndex("VALUE")));
                    tv.setMaxWidth((int) paint.measureText(str));
                    if(MainActivity.theme.equals("0"))
                        layout.setBackground(getDrawable(R.drawable.cell_shape_dark));
                    else
                        layout.setBackground(getDrawable(R.drawable.cell_shape_light));
                    layout.addView(tv, l);
                    tr.addView(layout);
                    c.moveToNext();
                }
                tableInfoColumns.addView(tr);
            }
        }
        c.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < tableInfoColumns.getChildCount(); i++ ) {
                    int height = 0;
                    int index = 0;
                    for(int j = 0; j < ((LinearLayout)tableInfoColumns.getChildAt(i)).getChildCount() ; j++) {
                        final TextView tv = (TextView) ((LinearLayout) ((TableRow) tableInfoColumns.getChildAt(i)).getChildAt(j)).getChildAt(0);
                        if(height < tv.getHeight()) {
                            index = j;
                            height = tv.getHeight();
                        }
                    }
                    for(int j = 0; j < ((LinearLayout)tableInfoColumns.getChildAt(i)).getChildCount() ; j++) {
                        final TextView tv = (TextView) ((LinearLayout) ((TableRow) tableInfoColumns.getChildAt(i)).getChildAt(j)).getChildAt(0);
                        final int finalIndex = index;
                        final int finalI1 = i;
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setHeight((((LinearLayout) ((TableRow) tableInfoColumns.getChildAt(finalI1)).getChildAt(finalIndex)).getChildAt(0)).getHeight());
                            }
                        });
                    }
                    final TextView tv = (TextView) ((LinearLayout) ((TableRow) tableNameColumns.getChildAt(i)).getChildAt(0)).getChildAt(0);
                    final int finalI = i;
                    final int finalIndex1 = index;
                    tv.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setHeight((((LinearLayout) ((TableRow) tableInfoColumns.getChildAt(finalI)).getChildAt(finalIndex1)).getChildAt(0)).getHeight());
                        }
                    });
                }
            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                tableNameColumns.getLayoutParams().width = metrics.widthPixels / 3;
                //tableInfoColumns.getLayoutParams().width = metrics.widthPixels / 2;
            }
        });
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

}

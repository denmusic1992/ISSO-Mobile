package com.development.aisisso.isso_i;


import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdvancedDefectActivity extends AppCompatActivity {

    private int C_ISSO;
    private int N_DEF;
    private Toolbar defToolBar;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<ais7AdvancedDef> advancedDefList = new ArrayList<>();
    private int itemTouched = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0"))
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);
        setContentView(R.layout.advanced_defect_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.isso_tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        LinearLayout coordinatorLayout = (LinearLayout) findViewById(R.id.linLayoutAdvancedDefect);
        if (!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            assert ab != null;
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ab.setHomeAsUpIndicator(R.drawable.back_light);
        } else {
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }

        C_ISSO = getIntent().getIntExtra("C_ISSO", -1);
        N_DEF = getIntent().getIntExtra("N_DEF", -1);
        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.action_bar_title_layout);
        ((TextView) findViewById(R.id.action_bar_title)).setText("АИС ИССО-I." + " Сооружение №" + C_ISSO);
        ((TextView) findViewById(R.id.action_bar_subtitle)).setText("Дефекты. № " + N_DEF);
        getSupportActionBar().setTitle("");
        if(MainActivity.theme.equals("0")) {
            ((TextView) findViewById(R.id.action_bar_title)).setTextColor(getResources().getColor(R.color.bright_foreground_material_dark));
            ((TextView) findViewById(R.id.action_bar_subtitle)).setTextColor(getResources().getColor(R.color.bright_foreground_material_dark));
        }
        final TableLayout advancedDefectsLayout = (TableLayout) findViewById(R.id.AdvancedDefTable);
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("select I_DEFECT.c_isso, I_DEFECT.n_def, I_DEFECT.c_defect, I_DEFECT.c_gr_constr, " +
                " coalesce(ord, I_DEFECT.MAIN_CONSTR_ID) as ord, I_DEFECT.n_constr, I_DEF_DESCR.date, " +
                " I_DEF_DESCR.B, I_DEF_DESCR.B1, I_DEF_DESCR.D, I_DEF_DESCR.D1, I_DEF_DESCR.R, I_DEF_DESCR.R1, I_DEF_DESCR.G, I_DEF_DESCR.G1, " +
                " I_DEFECT.DATE as date_defect, I_DEFECT.DATEF as date_end, i_def_mod.l_def, i_def_mod.w_def, s_defect.n_defect, n_rem, " +
                " sn_unit_dime, v_rem, count(i_foto_def.n_def) as fotocount, I_DEFECT.MAIN_CONSTR_ID " +
                " from I_DEFECT  " +
                " left outer join i_def_mod on  " +
                " i_def_mod.c_isso = I_DEFECT.c_isso and  " +
                " i_def_mod.n_def = I_DEFECT.n_def " +
                " left outer join I_DEF_DESCR on I_DEFECT.C_ISSO = I_DEF_DESCR.C_ISSO and" +
                " I_DEFECT.N_DEF = I_DEF_DESCR.N_DEF and I_DEF_MOD.DATE=I_DEF_DESCR.DATE" +
                " left outer join s_defect on s_defect.c_defect = I_DEFECT.c_defect " +
                " left outer join s_rem on s_rem.c_rem = i_def_mod.c_rem " +
                " left outer join s_unit_dimension on s_unit_dimension.c_unit_dimen = s_rem.ind_unit " +
                " left outer join i_foto_def on i_foto_def.c_isso = I_DEFECT.c_isso and i_foto_def.n_def = I_DEFECT.n_def and " +
                " i_foto_def.date = i_def_mod.date " +
                " left outer join s_gr_constr on s_gr_constr.C_GR_CONSTR = I_DEFECT.MAIN_CONSTR_ID " +
                " where I_DEFECT.c_isso = " + C_ISSO + " and I_DEFECT.N_DEF = " + N_DEF +
                " group by I_DEFECT.c_isso, I_DEFECT.n_def, I_DEFECT.c_defect, I_DEFECT.c_gr_constr, I_DEFECT.n_constr, I_DEF_DESCR.date, " +
                " I_DEF_DESCR.B, I_DEF_DESCR.B1, I_DEF_DESCR.D, I_DEF_DESCR.D1, I_DEF_DESCR.R, I_DEF_DESCR.R1, I_DEF_DESCR.G, I_DEF_DESCR.G1, " +
                " I_DEFECT.DATE, I_DEFECT.DATEF, i_def_mod.l_def, n_rem, sn_unit_dime, v_rem, l_def, i_def_mod.w_def, n_defect, " +
                " s_gr_constr.c_gr_constr, ord, I_DEFECT.MAIN_CONSTR_ID order by I_DEF_DESCR.date", null);
        cursor.moveToFirst();
        ((TextView) findViewById(R.id.tv_name_defect)).setText(cursor.getString(cursor.getColumnIndex("N_DEFECT")));
        TableRow.LayoutParams l = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        for(int i = 0; i < cursor.getCount(); i++) {
            final ais7AdvancedDef advancedDef = new ais7AdvancedDef();
            if(i == 0) {
                TableRow trMain = new TableRow(this);
                if(MainActivity.theme.equals("0"))
                    trMain.setBackground(getDrawable(R.drawable.cell_group_shape_dark));
                else
                    trMain.setBackground(getDrawable(R.drawable.cell_group_shape_light));
                trMain.addView(setTextViewDefect("Степень развития. Определяющие факторы", false), l);
                trMain.addView(setTextViewDefect("Значение", false), l);
                trMain.addView(setTextViewDefect("Б, Д, Р, Г", false), l);
                /*trMain.addView(setTextViewDefect("Д"), l);
                trMain.addView(setTextViewDefect("Р"), l);
                trMain.addView(setTextViewDefect("Г"), l);*/
                advancedDefectsLayout.addView(trMain);
            }

            // Подготовка к заполнению таблицы
            int b = !cursor.isNull(cursor.getColumnIndex("B")) ? cursor.getInt(cursor.getColumnIndex("B")) : 0;
            int b1 = !cursor.isNull(cursor.getColumnIndex("B1")) ? cursor.getInt(cursor.getColumnIndex("B1")) : 0;
            int d = !cursor.isNull(cursor.getColumnIndex("D")) ? cursor.getInt(cursor.getColumnIndex("D")) : 0;
            int d1 = !cursor.isNull(cursor.getColumnIndex("D1")) ? cursor.getInt(cursor.getColumnIndex("D1")) : 0;
            int r = !cursor.isNull(cursor.getColumnIndex("R")) ? cursor.getInt(cursor.getColumnIndex("R")) : 0;
            int r1 = !cursor.isNull(cursor.getColumnIndex("R1")) ? cursor.getInt(cursor.getColumnIndex("R1")) : 0;
            boolean g = cursor.getString(cursor.getColumnIndex("G")).equals("True");
            boolean g1 = cursor.getString(cursor.getColumnIndex("G1")).equals("True");
            advancedDef.L_DEF = !cursor.isNull(cursor.getColumnIndex("L_DEF")) ? cursor.getString(cursor.getColumnIndex("L_DEF")) : "";
            advancedDef.W_DEF = !cursor.isNull(cursor.getColumnIndex("W_DEF")) ? cursor.getString(cursor.getColumnIndex("W_DEF")) : "";
            advancedDef.N_REM = !cursor.isNull(cursor.getColumnIndex("N_REM")) ? cursor.getString(cursor.getColumnIndex("N_REM")) : "";
            if(!cursor.isNull(cursor.getColumnIndex("SN_UNIT_DIME"))) {
                advancedDef.REM_DIMENSION = ", " + cursor.getString(cursor.getColumnIndex("SN_UNIT_DIME"));
                advancedDef.REM_SIZE = String.format("%s", cursor.getString(cursor.getColumnIndex("V_REM")));
            }
            else {
                advancedDef.REM_DIMENSION = "";
                advancedDef.REM_SIZE = "не требуется";
            }
            advancedDef.date = cursor.getLong(cursor.getColumnIndex("DATE"));
            advancedDefList.add(advancedDef);
            String param = "";
            String defparam = "";
            Cursor paramCursor = db.rawQuery("select I_DEFECT.n_def, s_defparam.c_defparam, " +
                    " n_defparam, category, value, sn_unit_dime, i_def_mod.date, min(s_defparamvalue.c_unit_dimen) c_unit_dimen " +
                    " from i_def_descr  " +
                    " left outer join i_def_mod on i_def_descr.c_isso = i_def_mod.c_isso and i_def_descr.n_def = " +
                    " i_def_mod.n_def and i_def_descr.date=i_def_mod.date " +
                    " left outer join I_DEFECT on i_def_mod.c_isso = I_DEFECT.c_isso and i_def_mod.n_def = I_DEFECT.n_def " +
                    " left outer join s_defparam on s_defparam.c_defparam = i_def_descr.c_defparam " +
                    " left outer join s_defparamvalue on s_defparamvalue.c_defect = I_DEFECT.c_defect and " +
                    " s_defparamvalue.c_gr_constr = I_DEFECT.c_gr_constr and " +
                    " s_defparamvalue.c_defparam = i_def_descr.c_defparam " +
                    " left outer join s_unit_dimension on s_defparamvalue.c_unit_dimen = s_unit_dimension.c_unit_dimen " +
                    " where i_def_descr.c_isso = " + C_ISSO + " and I_DEFECT.N_DEF = " + N_DEF + " and i_def_mod.date = " +
                    cursor.getString(cursor.getColumnIndex("I_DEF_MOD.DATE")) +
                    " group by I_DEFECT.n_def, s_defparam.c_defparam, n_defparam, category, value, sn_unit_dime, i_def_mod.date " +
                    " order by I_DEF_MOD.date", null);
            paramCursor.moveToFirst();
            for(int j = 0; j < paramCursor.getCount(); j++ ) {
                if(paramCursor.getString(paramCursor.getColumnIndex("N_DEF")).equals(String.valueOf(N_DEF))) {
                    ais7DefectParamValue pv = new ais7DefectParamValue(paramCursor.getInt(paramCursor.getColumnIndex("C_DEFPARAM")), getApplicationContext());
                    param += pv.getFullName() + (!paramCursor.isNull(paramCursor.getColumnIndex("SN_UNIT_DIME")) ?
                            ", " + paramCursor.getString(paramCursor.getColumnIndex("SN_UNIT_DIME")) : "");
                    defparam += (!paramCursor.isNull(paramCursor.getColumnIndex("VALUE")) ? String.format("%s",
                                    paramCursor.getString(paramCursor.getColumnIndex("VALUE"))) : "");
                }
                paramCursor.moveToNext();
            }

            // Подготовка закончена

            TableRow trMain = new TableRow(this);
            if(MainActivity.theme.equals("0"))
                trMain.setBackground(getDrawable(R.drawable.cell_shape_dark));
            else
                trMain.setBackground(getDrawable(R.drawable.cell_shape_light));
            trMain.addView(setTextViewDefect("Состояние дефекта от " + getDate(cursor.getLong(cursor.getColumnIndex("I_DEF_MOD.DATE"))) +
                    ((!cursor.isNull(cursor.getColumnIndex("fotocount")) && cursor.getInt(cursor.getColumnIndex("fotocount")) > 0)
                            ? " (ф)" : ""), false), l);
            trMain.addView(setTextViewDefect("", false), l);
            trMain.addView(setTextViewDefect((b == b1 ? String.valueOf(b) : b + "/" + b1) + ", " + (d == d1 ? String.valueOf(d) : d + "/" + d1)
                    + ", " + (r == r1 ? String.valueOf(r) : r + "/" + r1) + ", " + (g!=g1 ? String.format("%s/%s", g ? "да" : "нет", g1 ? "да" : "нет") : g ? "да" : "нет"), false), l);
            /*trMain.addView(setTextViewDefect((d == d1 ? String.valueOf(d) : d + "/" + d1)), l);
            trMain.addView(setTextViewDefect((r == r1 ? String.valueOf(r) : r + "/" + r1)), l);
            trMain.addView(setTextViewDefect(g!=g1 ? String.format("{0}/{1}", g ? "да" : "нет", g1 ? "да" : "нет") : g ? "да" : "нет"), l);*/
            advancedDefectsLayout.addView(trMain);
            final TableRow finalTrMain = trMain;
            final int finalI = i;
            trMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((DefInfoFragment) fragmentArrayList.get(0)).setInfoForDef(advancedDef);
                    ((DefPhotoFragment) fragmentArrayList.get(1)).setPhotoDef(C_ISSO, N_DEF, advancedDef.date);
                    for (int i = 0; i < advancedDefectsLayout.getChildCount(); i++) {
                        TableRow row = (TableRow) advancedDefectsLayout.getChildAt(i);
                        if (row == finalTrMain) {
                            if(MainActivity.theme.equals("0")) {
                                (advancedDefectsLayout.getChildAt(i + 1)).setBackgroundColor(Color.rgb(84, 71, 81));
                                row.setBackgroundColor(Color.rgb(84, 71, 81));
                            }
                            else {
                                (advancedDefectsLayout.getChildAt(i + 1)).setBackgroundColor(Color.rgb(211, 221, 231));
                                row.setBackgroundColor(Color.rgb(211, 221, 231));
                            }
                            i++;
                        } else if (i > 0) {
                            row.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                }
            });

            TableRow tr = new TableRow(this);
            if(MainActivity.theme.equals("0"))
                tr.setBackground(getDrawable(R.drawable.cell_shape_dark));
            else
                tr.setBackground(getDrawable(R.drawable.cell_shape_light));
            tr.addView(setTextViewDefect(param, true), l);
            tr.addView(setTextViewDefect(defparam, false), l);
            tr.addView(setTextViewDefect((b == b1 ? String.valueOf(b) : b + "/" + b1) + ", " + (d == d1 ? String.valueOf(d) : d + "/" + d1)
                    + ", " + (r == r1 ? String.valueOf(r) : r + "/" + r1) + ", " + (g!=g1 ? String.format("%s/%s", g ? "да" : "нет", g1 ? "да" : "нет") : g ? "да" : "нет"), false), l);
            /*trMain.addView(setTextViewDefect((b == b1 ? String.valueOf(b) : b + "/" + b1)), l);
            trMain.addView(setTextViewDefect((d == d1 ? String.valueOf(d) : d + "/" + d1)), l);
            trMain.addView(setTextViewDefect((r == r1 ? String.valueOf(r) : r + "/" + r1)), l);
            trMain.addView(setTextViewDefect(g != g1 ? String.format("{0}/{1}", g ? "да" : "нет", g1 ? "да" : "нет") : g ? "да" : "нет"), l);*/
            advancedDefectsLayout.addView(tr);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((DefInfoFragment) fragmentArrayList.get(0)).setInfoForDef(advancedDef);
                    ((DefPhotoFragment) fragmentArrayList.get(1)).setPhotoDef(C_ISSO, N_DEF, advancedDef.date);
                    itemTouched = finalI;
                    for(int i = 0; i < advancedDefectsLayout.getChildCount(); i++ ) {
                        TableRow row = (TableRow) advancedDefectsLayout.getChildAt(i);
                        if(row == finalTrMain ) {
                            if(MainActivity.theme.equals("0")) {
                                (advancedDefectsLayout.getChildAt(i + 1)).setBackgroundColor(Color.rgb(84, 71, 81));
                                row.setBackgroundColor(Color.rgb(84, 71, 81));
                            }
                            else {
                                (advancedDefectsLayout.getChildAt(i + 1)).setBackgroundColor(Color.rgb(211, 221, 231));
                                row.setBackgroundColor(Color.rgb(211, 221, 231));
                            }
                            i++;
                        }
                        else if(i > 0) {
                            row.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                }
            });
            cursor.moveToNext();
        }

        NonSwipeableViewPager viewPager = (NonSwipeableViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        if(MainActivity.theme.equals("0")) {
            (advancedDefectsLayout.getChildAt(advancedDefectsLayout.getChildCount() - 2)).setBackgroundColor(Color.rgb(84, 71, 81));
            (advancedDefectsLayout.getChildAt(advancedDefectsLayout.getChildCount() - 1)).setBackgroundColor(Color.rgb(84, 71, 81));
        }
        else {
            (advancedDefectsLayout.getChildAt(advancedDefectsLayout.getChildCount() - 2)).setBackgroundColor(Color.rgb(211, 221, 231));
            (advancedDefectsLayout.getChildAt(advancedDefectsLayout.getChildCount() - 1)).setBackgroundColor(Color.rgb(211, 221, 231));
        }
        itemTouched = advancedDefList.size() - 1;

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.advancedLinLayout);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams mainInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayoutMainInfo)).getLayoutParams();
            LinearLayout.LayoutParams secondaryInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayoutSecondaryInfo)).getLayoutParams();
            mainInfo.width = 0;
            mainInfo.height = LinearLayout.LayoutParams.MATCH_PARENT;
            mainInfo.weight = 1;
            secondaryInfo.width = 0;
            secondaryInfo.height = LinearLayout.LayoutParams.MATCH_PARENT;
            secondaryInfo.weight = 1;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.advancedLinLayout);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams mainInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayoutMainInfo)).getLayoutParams();
            LinearLayout.LayoutParams secondaryInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayoutSecondaryInfo)).getLayoutParams();
            mainInfo.width = 0;
            mainInfo.height = LinearLayout.LayoutParams.MATCH_PARENT;
            mainInfo.weight = 1;
            secondaryInfo.width = 0;
            secondaryInfo.height = LinearLayout.LayoutParams.MATCH_PARENT;
            secondaryInfo.weight = 1;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    ((DefPhotoFragment) fragmentArrayList.get(1)).setPhotoDef(C_ISSO, N_DEF, advancedDefList.get(itemTouched).date);
                }
            });
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.advancedLinLayout);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams mainInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayoutMainInfo)).getLayoutParams();
            LinearLayout.LayoutParams secondaryInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayoutSecondaryInfo)).getLayoutParams();
            mainInfo.width = LinearLayout.LayoutParams.MATCH_PARENT;
            mainInfo.height = 0;
            mainInfo.weight = 1;
            secondaryInfo.width = LinearLayout.LayoutParams.MATCH_PARENT;
            secondaryInfo.height = 0;
            secondaryInfo.weight = 2;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    ((DefPhotoFragment) fragmentArrayList.get(1)).setPhotoDef(C_ISSO, N_DEF, advancedDefList.get(itemTouched).date);
                }
            });
        }
    }

    private void setupTabIcons() {
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            tabLayout.getTabAt(0).setIcon(R.drawable.info_dark);
            tabLayout.getTabAt(1).setIcon(R.drawable.info_dark);
        }
        else {
            tabLayout.getTabAt(0).setIcon(R.drawable.info_light);
            tabLayout.getTabAt(1).setIcon(R.drawable.info_light);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ais7AdvancedDef def = advancedDefList.get(advancedDefList.size() - 1);
        fragmentArrayList.add(new DefInfoFragment(def));
        fragmentArrayList.add(new DefPhotoFragment(C_ISSO, N_DEF, def.date));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentArrayList.get(0), "Характеристики");
        adapter.addFragment(fragmentArrayList.get(1), "Фотографии");
        viewPager.setAdapter(adapter);
    }

    public String getDate(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }

    public boolean onOptionsItemSelected(MenuItem item)	{
        switch(item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public LinearLayout setTextViewDefect(Object obj, boolean flag) {
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
        if(flag)
            linParams.setMargins(60, 5, 20, 5);
        else
            linParams.setMargins(20, 5, 20, 5);
        linParams.gravity = Gravity.CENTER;
        Paint paint = new Paint();
        tv.setMaxWidth((int) paint.measureText(str));
        tv.setText(obj.toString());
        if(obj.toString().length() > 2 && flag)
            tv.setTypeface(null, Typeface.ITALIC);
        layout.addView(tv, linParams);
        return layout;
    }
}

class ais7AdvancedDef {

    public String L_DEF;

    public String W_DEF;

    public String N_REM;

    public String REM_DIMENSION;

    public String REM_SIZE;

    public long date;
}

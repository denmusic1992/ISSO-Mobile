package com.development.aisisso.isso_i;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

/** Активити для работы с данными по сооружению, его фотографиями и схемами */
public class ViewInfoActivity extends AppCompatActivity {

    public static int cIsso;                                // Код ИССО
    private FloatingActionMenu Menu;
    public static double longitude;
    public static double latitude;
    public static double length;
    public static String FullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Применение темы */
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0"))
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);

        setContentView(R.layout.view_info_layout);
        // Инициаоизация Верхнего TOOLBAR
        final Toolbar toolbar = (Toolbar) findViewById(R.id.isso_tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        LinearLayout coordinatorLayout = (LinearLayout) findViewById(R.id.linLayoutInfo);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            assert ab != null;
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ab.setHomeAsUpIndicator(R.drawable.back_light);
        } else {
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }


        // Подключение к frameLayout наших фрагментов с данными
        List<String> TitlesList = new ArrayList<>();

        cIsso = Integer.parseInt(getIntent().getStringExtra("C_ISSO"));
        ab.setDisplayShowCustomEnabled(true);
        /*ab.setCustomView(R.layout.action_bar_title_layout);
        ((TextView) findViewById(R.id.action_bar_title)).setText("АИС ИССО-I.");
        ((TextView) findViewById(R.id.action_bar_subtitle)).setText("Сооружение №" + cIsso);
        if(MainActivity.theme.equals("0")) {
            ((TextView) findViewById(R.id.action_bar_title)).setTextColor(getResources().getColor(R.color.fabMenuDark));
            ((TextView) findViewById(R.id.action_bar_subtitle)).setTextColor(getResources().getColor(R.color.fabMenuDark));
        }*/
        getSupportActionBar().setTitle("АИС ИССО-I. Сооружение №" + cIsso);

        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, FragmentsList.get(0)).commit();
        EnableNewFragment(0);

        // Спиннер
        /*Spinner spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, TitlesList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                EnableNewFragment(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        /** Инициализация кнопок меню */
        FloatingActionButton TreeBtn, PhotoBtn, SchemeBtn;
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            (findViewById(R.id.menu_light)).setVisibility(View.GONE);
            Menu = (FloatingActionMenu) findViewById(R.id.menu_dark);
            Menu.setMenuButtonColorNormal(getResources().getColor(R.color.fabMenuDark));
            Menu.setMenuButtonColorPressed(getResources().getColor(R.color.fabMenuDarkPressed));
            Menu.setMenuButtonColorRipple(Color.parseColor("#99FFFFFF"));
            Menu.getMenuIconView().setImageResource(R.drawable.tree_light);
            TreeBtn = (FloatingActionButton) findViewById(R.id.fabTreeDark);
            PhotoBtn = (FloatingActionButton) findViewById(R.id.fabPhotoDark);
            SchemeBtn = (FloatingActionButton) findViewById(R.id.fabSchemeDark);
            TreeBtn.setColorNormal(getResources().getColor(R.color.fabMenuDark));
            TreeBtn.setColorPressed(getResources().getColor(R.color.fabMenuDarkPressed));
            TreeBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            TreeBtn.setButtonSize(1);
            TreeBtn.setLabelText("Список");
            PhotoBtn.setColorNormal(getResources().getColor(R.color.fabMenuDark));
            PhotoBtn.setColorPressed(getResources().getColor(R.color.fabMenuDarkPressed));
            PhotoBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            PhotoBtn.setButtonSize(1);
            PhotoBtn.setLabelText("Фотографии");
            SchemeBtn.setColorNormal(getResources().getColor(R.color.fabMenuDark));
            SchemeBtn.setColorPressed(getResources().getColor(R.color.fabMenuDarkPressed));
            SchemeBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            SchemeBtn.setButtonSize(1);
            SchemeBtn.setLabelText("Чертежи");
        }
        else {
            (findViewById(R.id.menu_dark )).setVisibility(View.GONE);
            Menu = (FloatingActionMenu) findViewById(R.id.menu_light);
            Menu.setMenuButtonColorNormal(getResources().getColor(R.color.fabMenuLight));
            Menu.setMenuButtonColorPressed(getResources().getColor(R.color.fabMenuLightPressed));
            Menu.setMenuButtonColorRipple(Color.parseColor("#99FFFFFF"));
            Menu.getMenuIconView().setImageResource(R.drawable.tree_dark);
            TreeBtn = (FloatingActionButton) findViewById(R.id.fabTreeLight);
            PhotoBtn = (FloatingActionButton) findViewById(R.id.fabPhotoLight);
            SchemeBtn = (FloatingActionButton) findViewById(R.id.fabSchemeLight);
            TreeBtn.setColorNormal(getResources().getColor(R.color.fabMenuLight));
            TreeBtn.setColorPressed(getResources().getColor(R.color.fabMenuLightPressed));
            TreeBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            TreeBtn.setButtonSize(1);
            TreeBtn.setLabelText("Список");
            PhotoBtn.setColorNormal(getResources().getColor(R.color.fabMenuLight));
            PhotoBtn.setColorPressed(getResources().getColor(R.color.fabMenuLightPressed));
            PhotoBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            PhotoBtn.setButtonSize(1);
            PhotoBtn.setLabelText("Фотографии");
            SchemeBtn.setColorNormal(getResources().getColor(R.color.fabMenuLight));
            SchemeBtn.setColorPressed(getResources().getColor(R.color.fabMenuLightPressed));
            SchemeBtn.setColorRipple(Color.parseColor("#99FFFFFF"));
            SchemeBtn.setButtonSize(1);
            SchemeBtn.setLabelText("Чертежи");
        }
        Menu.setClosedOnTouchOutside(true);
        TreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableNewFragment(0);
                Menu.close(true);
                if (PreferenceManager.getDefaultSharedPreferences(ViewInfoActivity.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.tree_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.tree_dark);
            }
        });
        PhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableNewFragment(1);
                Menu.close(true);
                if (PreferenceManager.getDefaultSharedPreferences(ViewInfoActivity.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.photo_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.photo_dark);
            }
        });
        SchemeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableNewFragment(2);
                Menu.close(true);
                if (PreferenceManager.getDefaultSharedPreferences(ViewInfoActivity.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.scheme_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.scheme_dark);
            }
        });

        SQLiteDatabase db = new DBHelper(getApplicationContext()).getReadableDatabase();
        Cursor cr = db.query("I_ISSO", new String[]{"FULLNAME", "LATITUDE", "LONGITUDE", "LENGTH", "N_OTC_EXP"}, "C_ISSO = " + cIsso, null, null, null, null);
        cr.moveToFirst();
        latitude = cr.getDouble(cr.getColumnIndex("LATITUDE"));
        longitude = cr.getDouble(cr.getColumnIndex("LONGITUDE"));
        length = cr.getDouble(cr.getColumnIndex("LENGTH"));
        FullName = cr.getString(cr.getColumnIndex("FULLNAME"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*FragmentManager fragmentManager = ViewInfoActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            int i = 0;
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible()) {
                    switch(i) {
                        case 0:
                            if (PreferenceManager.getDefaultSharedPreferences(ViewInfoActivity.this).getString("Theme", "0").equals("0"))
                                Menu.getMenuIconView().setImageResource(R.drawable.tree_light);
                            else
                                Menu.getMenuIconView().setImageResource(R.drawable.tree_dark);
                            break;
                        case 1:
                            if (PreferenceManager.getDefaultSharedPreferences(ViewInfoActivity.this).getString("Theme", "0").equals("0"))
                                Menu.getMenuIconView().setImageResource(R.drawable.photo_light);
                            else
                                Menu.getMenuIconView().setImageResource(R.drawable.photo_dark);
                            break;
                        case 2:
                            if (PreferenceManager.getDefaultSharedPreferences(ViewInfoActivity.this).getString("Theme", "0").equals("0"))
                                Menu.getMenuIconView().setImageResource(R.drawable.scheme_light);
                            else
                                Menu.getMenuIconView().setImageResource(R.drawable.scheme_dark);
                            break;
                    }
                }
                i++;
            }
        }*/
        finish();
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

    /** Переключение на новый вид */
    private void EnableNewFragment(int position) {
        /*android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0:
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("one")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.fragment_layout, new TreeViewActivity(cIsso), "one").commit();
                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("two")).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("three")).commit();
                }
                break;
            case 1:
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("two")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.fragment_layout, new PhotoViewActivity(cIsso), "two").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("one")).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("three")).commit();
                }
                break;
            case 2:
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("three")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.fragment_layout, new SchemeViewActivity(cIsso), "three").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("one")).commit();
                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("two")).commit();
                }
                break;
        }
    }
}

class InfoRow {

    public String SYS_NAME;
    public String DESCRIPTION;
    public int PARENT_ID;
    public int C_GR_CONSTR;
    public int countOfColumns;

    public InfoRow( String SYS_NAME, String DESCRIPTION, int PARENT_ID, int C_GR_CONSTR, int countOfColumns) {
        this.SYS_NAME = SYS_NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.PARENT_ID = PARENT_ID;
        this.C_GR_CONSTR = C_GR_CONSTR;
        this.countOfColumns = countOfColumns;
    }
}

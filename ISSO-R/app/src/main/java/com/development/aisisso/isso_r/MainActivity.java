package com.development.aisisso.isso_r;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static DBHelper dbHelper;                                       // Для работы с БД
    static LocationManager locationManager;                         // Слушатель GPS
    public static final String MY_SETTINGS = "my_settings";         // Для настроек
    private String selectedRoad = null;                             // фильтр выбранной дороги
    private boolean wasCalled = false;                              // Переменные для
    private boolean wasEdited = false;                              // отображения всплывающих окон
    static final double rad = 6371200;                              // Радиус Земли
    static String theme;                                            // Тип темы
    private CoordinatorLayout coordinatorLayout;                    // Для вывода Snackbar'ов
    private DrawerLayout drawerLayout;
    private RecyclerView lvMain;
    private ArrayList<mainRow> mainRows = new ArrayList<>();


    // Location Listener
    public static LocationListener listen = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
            locationManager.getLastKnownLocation(provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Получение темы из preferences */
        String str = PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", null);
        if(str == null) {
            PreferenceManager.setDefaultValues(this, R.xml.pref_advanced, false);
        }
        theme = PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0");
        if(theme.equals("0"))
            setTheme(R.style.Advanced);
        else {
            setTheme(R.style.AdvancedLight);
        }
        setContentView(R.layout.activity_main);
        /** Добавление кастомной кнопки floating button из нового material design.
         * Из-за поддержки двух тем одновременно и из-за использования Coordinator layout для snackbar'ов
         * пришлось поизвращаться с этим самым coordinator layout..
         * Также при смене темы на светлую цвет фона оставался темным по непонятным причинам, поэтому
         * меняется цвет фона программно.*/
        findViewById(R.id.button_road).setOnClickListener(roadSelector);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        String text = "Привет";
        /**  Подулючение toolbar со связанным заголовком*/
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.RelLayoutMain);
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") ) {
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_dark);
            ((ImageView) findViewById(R.id.imgSupport)).setImageDrawable(getDrawable(R.drawable.support_light));
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.menu_light);
        }
        else {
            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_light);
            ((ImageView) findViewById(R.id.imgSupport)).setImageDrawable(getDrawable(R.drawable.support_dark));
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.menu);
        }
        ab.setDisplayHomeAsUpEnabled(true);

        /** Подключение панели навигации, теперь кнопки меню не будет, будет только вот эта панель.*/
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView view = (NavigationView) findViewById(R.id.nav_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.drawer_autoselect:
                        //Автовыбор
                        startAutoSelect();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_synchronize:
                        //Синхронизация
                        startSynchronize();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_settings:
                        //Настройки
                        startSettings();
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        findViewById(R.id.lSupport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String host = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Address", "aisisso.ru").toLowerCase();
                String port = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("SupPort", "8080");

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://" + host + ":" + port));
                startActivity(i);
            }
        });

        //=================================	GPS =================================================
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showGPSAlert();
        }
        lvMain = (RecyclerView) findViewById(R.id.lvMain);
        lvMain.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        lvMain.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lvMain.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        lvMain.setItemAnimator(itemAnimator);


        selectedRoad = getString(R.string.all_roads);
        //((FloatingActionButton) findViewById(R.id.button_road)).setText("Дорога: " + selectedRoad);

        /** Проверка наличия каких либо значений в БД, если ничего нету, то отправляет в синхронизацию
         * , иначе до свиданья...*/
        dbHelper = new DBHelper(this);
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cr = db.rawQuery("select C_ISSO from I_ISSO", null);
            int cnt = cr.getCount();
            cr.close();
            if (cnt == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Важное сообщение!")
                        .setMessage("Перед использованием приложения необходимо выполнить синхронизацию")
                        .setCancelable(false)
                        .setNegativeButton("Не сейчас", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                System.exit(0);
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        startSynchronize();
                                        dialog.cancel();
                                        wasEdited = true;
                                    }
                                })
                        .setNeutralButton("Настройки", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startSettings();
                                dialog.cancel();
                                wasEdited = true;
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        } catch (Exception e) {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setTitle(e.getMessage());
            AlertDialog alert = bld.create();
            alert.show();
        }
        NewRefreshIssoList();
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            insertWrapper();
        }
        startActivityForResult(checkIntent, 111);
    }

    /**  Фильтр по дороге, выбранной с помощью float button*/
    View.OnClickListener roadSelector = new View.OnClickListener() {
        public void onClick(View v)
        {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cr = db.query(true, "I_ISSO", new String[]{"DORNAME"}, null, null, null, null, null, null);
            final String[] roads = new String[cr.getCount()+1];
            cr.moveToFirst();
            roads[0] = getString(R.string.all_roads);
            for(int i = 1; i <= cr.getCount(); i++)
            {
                roads[i] = cr.getString(cr.getColumnIndex("DORNAME"));
                cr.moveToNext();
            }
            cr.close();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Выберите дорогу:");
            builder.setItems(roads, new DialogInterface.OnClickListener() {
                public String[] _roads = roads;

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedRoad = _roads[which];
                    if(selectedRoad.equals(getString(R.string.all_roads))) {
                        if(theme.equals("0"))
                            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_light);
                        else
                            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_dark);
                    }
                    else {
                        if(theme.equals("0"))
                            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_enable_dark);
                        else
                            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_enable_light);
                    }

                    /*Button btn = (Button) findViewById(R.id.button_road);
                    btn.setText("Дорога: " + selectedRoad);
                    btn.setTextSize(14);*/
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Выбран фильтр по дороге: " + selectedRoad, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    NewRefreshIssoList();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

    };


    @Override
    public void onBackPressed () {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setMessage("Выйти из приложения?")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }

                });
        builder3.show();
    }

    // Здесь убиваем GPS
    public static void killGPS(LocationListener listen) {
        //if(ActivityMonitor.active == 0)
        if(listen != null) {
            locationManager.removeUpdates(listen);
        }
    }

    // Здесь возрождаем GPS
    public static void resumeGPS(LocationListener listen) {
        //if(ActivityMonitor.active != 1) return;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.0f, listen);
    }

    private void insertWrapper() {
        List<String> permissionsNeeded = new ArrayList<>();

        final List<String> permissionsList = new ArrayList<>();
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");
        if (!addPermission(permissionsList, Manifest.permission.READ_PHONE_STATE))
            permissionsNeeded.add("Read Phone State");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Write External Storage");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "Для работы с приложением Вам необходим доступ к разрешениям " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                        123);
                            }
                        });
                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    123);
            return;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Отмена", null)
                .create()
                .show();
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        }
        return true;
    }

    // Alert Dialog для включения GPS и перехода в меню для включения
    public void showGPSAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Включить GPS");

        alertDialog.setMessage("Невозможно определить местоположение. Перейдите в меню 'Настройки'" +
                " и включите GPS.");

        alertDialog.setPositiveButton("Включить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                wasCalled = true;
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Продолжить без GPS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        killGPS(listen);
    }

    @Override
    public void onPause() {
        super.onPause();
        killGPS(listen);
    }


    AlertDialog alert;

    @Override
    public void onResume() {
        super.onResume();
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
            resumeGPS(listen);
        }
        else {
            int hasWriteContactsPermission = 0;
            hasWriteContactsPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED)
                resumeGPS(listen);
        }
        /** wasCalled -  флаг, показывющий, что мы пришли из стороннего uri по вопросу с GPS. Если
         * да, то пытаемся найти местоположение, иначе опять диалоговое окно с требованием включить GPS*/
        if (wasCalled) {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                showGPSAlert();
            }
        }

        // При смене темы в настройках нужно поменять тему и здесь
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals(theme)) {
            if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
                Utils.changeToTheme(MainActivity.this/*, Utils.THEME_DARK*/);
            }
            else {
                Utils.changeToTheme(MainActivity.this/*, Utils.THEME_WHITE*/);
            }
        }

        NewRefreshIssoList();
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        /** Здесь происходит проверка новой версии БД, если так и есть и при этом диаолговое окно
         * не вызывалось, то идем в синхронизацию*/
        String nameCurator = sp.getString("nameCurator", "");
        if(!nameCurator.equals("")) {
            View view = ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0);
            ((TextView) view.findViewById(R.id.tvCurator)).setText("Куратор: " + nameCurator);
        }
        boolean newVersion = sp.getBoolean("newVersion", false);
        if(newVersion && (alert == null || !alert.isShowing())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Важное сообщение!")
                    .setMessage("Структура данных приложения была изменена. Для корректной работы приложения необходимо выполнить синхронизацию.")
                    .setCancelable(false)
                    .setNegativeButton("Не сейчас", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            System.exit(0);
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startSynchronize();
                                    dialog.cancel();
                                }
                            })
                    .setNeutralButton("Настройки", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startSettings();
                            dialog.cancel();
                        }
                    });
            alert = builder.create();
            alert.show();
        }

        /** Опять же проверка того, что у нас до этого момента были показаны какие то диалоговые окна,
         * если да, то погнали показывать*/
        if(wasEdited && (alert == null || !alert.isShowing())) {
            try {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cr = db.rawQuery("select C_ISSO from I_ISSO", null);
                int cnt = cr.getCount();
                cr.close();
                if (cnt == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Важное сообщение!")
                            .setMessage("Перед использованием приложения необходимо выполнить синхронизацию")
                            .setCancelable(false)
                            .setNegativeButton("Не сейчас", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                    System.exit(0);
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            startSynchronize();
                                            dialog.cancel();
                                            wasEdited = true;
                                        }
                                    })
                            .setNeutralButton("Настройки", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startSettings();
                                    dialog.cancel();
                                    wasEdited = true;
                                }
                            });
                    alert = builder.create();
                    alert.show();
                }
            } catch (Exception e) {
                AlertDialog.Builder bld = new AlertDialog.Builder(this);
                bld.setTitle(e.getMessage());
                AlertDialog alert = bld.create();
                alert.show();
            }
        }
    }


    //Создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)	{
        switch(item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Проверка наличия установленного приложения
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    private void startSettings()	{
        Intent intent = new Intent(getBaseContext(), AdvancedSettings.class);
        startActivity(intent);
    }

    public void startSynchronize()	{
        Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
        startActivityForResult(intent, 0);
    }

    private void startAutoSelect() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT C_ISSO, NAME, FULLNAME, DORNAME, W_ISSO,  N_OTC_EXP, C_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO "/* +
                "left outer join I_REM_PLAN_CUR on (I_REM_PLAN_CUR.C_ISSO = I_ISSO.C_ISSO and I_REM_PLAN_CUR.REM_MONTH = (select MAX(REM_MONTH) from I_REM_PLAN_CUR where C_ISSO=I_ISSO.C_ISSO)) "*/, null);
        if(cr.moveToFirst()) {
            HttpsIsso [] listOfIssos = new HttpsIsso [cr.getCount()];
            int[] rating = new int[cr.getCount()];
            for (int i = 0; i < listOfIssos.length; i++) {
                HttpsIsso isso = new HttpsIsso();
                isso.CIsso = cr.getInt(cr.getColumnIndex("C_ISSO"));
                isso.Name = cr.getString(cr.getColumnIndex("NAME"));
                isso.FullName = cr.getString(cr.getColumnIndex("FULLNAME"));
                isso.DorName = cr.getString(cr.getColumnIndex("DORNAME"));
                isso.WIsso = cr.getInt(cr.getColumnIndex("W_ISSO"));
                isso.ExpertRating = cr.getString(cr.getColumnIndex("N_OTC_EXP"));
                isso.ExpertRatingNumeric = cr.getInt(cr.getColumnIndex("C_OTC_EXP"));
                isso.Latitude = cr.getDouble(cr.getColumnIndex("LATITUDE"));
                isso.Longitude = cr.getDouble(cr.getColumnIndex("LONGITUDE"));
                isso.Length = cr.getString(cr.getColumnIndex("LENGTH"));
                isso.Obstacle = cr.getString(cr.getColumnIndex("OBSTACLE"));
                listOfIssos[i] = isso;
                Cursor c = db.rawQuery("select MAX(PERIOD) from I_REM_PLAN_ITEMS where C_ISSO=" + isso.CIsso, null);
                long period;
                if(c.moveToFirst() && c.getLong(0) > 0) {
                    period = c.getLong(0);
                    c = db.rawQuery("select AVG(RATING) from I_REM_PLAN_ITEMS where C_ISSO=" + isso.CIsso + " and " +
                            "PERIOD =" + period, null);
                    c.moveToFirst();
                    rating[i] = c.getInt(0);
                }
                else {
                    rating[i] = -1;
                }
                c.close();
                cr.moveToNext();
            }
            cr.close();
            Drawable[] DRAWABLES = {getResources().getDrawable(R.drawable.draw_0), getResources().getDrawable(R.drawable.draw_1),
                    getResources().getDrawable(R.drawable.draw_2), getResources().getDrawable(R.drawable.draw_3),
                    getResources().getDrawable(R.drawable.draw_4), getResources().getDrawable(R.drawable.draw_5),
                    getResources().getDrawable(R.drawable.draw_6)};

            IssoViewActivity issoViewActivity = new IssoViewActivity();
            //Autoselect activity = new Autoselect(listOfIssos, locationManager, rating, DRAWABLES, issoViewActivity);

            //MapsActivity activity = new MapsActivity();
            MapsActivity activity = new MapsActivity(listOfIssos, locationManager, rating, DRAWABLES);
            Intent intent = new Intent(this, activity.getClass());
            startActivity(intent);
        }
    }

    /*private void startAutoSelectWithoutMaps() {
        Cursor cr = new DBHelper(this).getReadableDatabase().rawQuery("SELECT I_ISSO.C_ISSO, NAME, FULLNAME, DORNAME, W_ISSO, coalesce(AVERAGE_REM_RATING, -1) as RATING, N_OTC_EXP, C_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                "left outer join I_REM_PLAN_CUR on (I_REM_PLAN_CUR.C_ISSO = I_ISSO.C_ISSO and I_REM_PLAN_CUR.REM_MONTH = (select MAX(REM_MONTH) from I_REM_PLAN_CUR where C_ISSO=I_ISSO.C_ISSO)) ", null);
        if (cr.moveToFirst()) {
            HttpsIsso[] listOfIssos = new HttpsIsso[cr.getCount()];
            int[] rating = new int[cr.getCount()];
            for (int i = 0; i < listOfIssos.length; i++) {
                HttpsIsso isso = new HttpsIsso();
                isso.CIsso = cr.getInt(cr.getColumnIndex("C_ISSO"));
                isso.Name = cr.getString(cr.getColumnIndex("NAME"));
                isso.FullName = cr.getString(cr.getColumnIndex("FULLNAME"));
                isso.DorName = cr.getString(cr.getColumnIndex("DORNAME"));
                isso.WIsso = cr.getDouble(cr.getColumnIndex("W_ISSO"));
                isso.ExpertRating = cr.getString(cr.getColumnIndex("N_OTC_EXP"));
                isso.ExpertRatingNumeric = cr.getInt(cr.getColumnIndex("C_OTC_EXP"));
                isso.Latitude = cr.getDouble(cr.getColumnIndex("LATITUDE"));
                isso.Longitude = cr.getDouble(cr.getColumnIndex("LONGITUDE"));
                isso.Length = cr.getString(cr.getColumnIndex("LENGTH"));
                isso.Obstacle = cr.getString(cr.getColumnIndex("OBSTACLE"));
                listOfIssos[i] = isso;
                rating[i] = (cr.getInt(cr.getColumnIndex("RATING")) + 2) * 10;
                cr.moveToNext();
            }
            Drawable[] DRAWABLES = {getResources().getDrawable(R.drawable.draw_0), getResources().getDrawable(R.drawable.draw_1),
                    getResources().getDrawable(R.drawable.draw_2), getResources().getDrawable(R.drawable.draw_3),
                    getResources().getDrawable(R.drawable.draw_4), getResources().getDrawable(R.drawable.draw_5),
                    getResources().getDrawable(R.drawable.draw_6)};

            IssoViewActivity issoViewActivity = new IssoViewActivity();
            //Autoselect activity = new Autoselect(listOfIssos, locationManager, rating, DRAWABLES, issoViewActivity);

            //MapsActivity activity = new MapsActivity();
            MapsActivity activity = new MapsActivity(listOfIssos, locationManager, rating, DRAWABLES);
            Intent intent = new Intent(this, activity.getClass());
            startActivity(intent);
        }
    }*/

    private void NewRefreshIssoList()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr;

        cr = db.rawQuery("SELECT I_ISSO.C_ISSO, NAME, DORNAME, W_ISSO, N_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                /*"left outer join I_REM_PLAN_CUR on (I_REM_PLAN_CUR.C_ISSO = I_ISSO.C_ISSO and I_REM_PLAN_CUR.REM_MONTH = (select MAX(REM_MONTH) from I_REM_PLAN_CUR where C_ISSO = I_ISSO.C_ISSO)) " +*/
                (selectedRoad.equals(getString(R.string.all_roads)) ? "" : " where DORNAME = '" + selectedRoad + "'" + " order by DORNAME, W_ISSO"), null);

        cr.moveToFirst();
        mainRows.clear();

        for ( int i = 0; i < cr.getCount(); i++ ) {
            String info;
            //if ( selectedRoad.equals(getString(R.string.all_roads)) ) {
            /*String str = String.format("%.3f", cr.getDouble(cr.getColumnIndex("W_ISSO")));
            str = str.replaceAll(",", "+");
            str = str.replaceAll("\\.", "+");*/
            int w_isso = cr.getInt(cr.getColumnIndex("W_ISSO"));
            int km = w_isso >> 16;
            int m = w_isso & 0xFFFF;
            String str = km + "+" + m;
            info = cr.getString(cr.getColumnIndex("NAME")) + " (ОТС: " + cr.getString(cr.getColumnIndex("N_OTC_EXP"))+ ")" + "\n"
                    + String.format("%s, км %s (%s)", cr.getString(cr.getColumnIndex("DORNAME")), str, cr.getString(cr.getColumnIndex("OBSTACLE")));
            /*}
            else {
                String str = String.format("%.3f", cr.getDouble(cr.getColumnIndex("W_ISSO")));
                str = str.replaceAll(",", "+");
                str = str.replaceAll("\\.", "+");
                info = cr.getString(cr.getColumnIndex("NAME")) + " (ОТС: " + cr.getString(cr.getColumnIndex("N_OTC_EXP"))+ ")" +
                        "\n" + String.format("км %s (%s)", str, cr.getString(cr.getColumnIndex("OBSTACLE")));
            }*/
            int cIsso = cr.getInt(cr.getColumnIndex("C_ISSO"));
            long period;
            int rating;
            Cursor c = db.rawQuery("select MAX(PERIOD) from I_REM_PLAN_ITEMS where C_ISSO=" + cIsso, null);
            if(c.moveToFirst() && c.getLong(0) > 0) {
                period = c.getLong(0);
                c = db.rawQuery("select AVG(RATING) from I_REM_PLAN_ITEMS where C_ISSO=" + cIsso + " and " +
                        "PERIOD =" + period, null);
                c.moveToFirst();
                rating = c.getInt(0);
            }
            else {
                rating = -1;
            }
            c.close();
            mainRows.add(new mainRow(rating, info, cIsso));
            cr.moveToNext();
        }
        cr.close();

        MyAdapter adapter = new MyAdapter(mainRows);
        lvMain.setAdapter(adapter);
        lvMain.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, IssoViewActivity.class);
                intent.putExtra("C_ISSO", String.valueOf(mainRows.get(position).C_ISSO));
                startActivity(intent);
            }
        }));
        /*lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, IssoViewActivity.class);
                intent.putExtra("C_ISSO", mainRows.get(position).C_ISSO);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 123:
            {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    resumeGPS(listen);
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "Это приложение не будет работать без разрешений.", Toast.LENGTH_LONG)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /*private void RefreshIssoList()
    {
        LinearLayout view = ((LinearLayout) findViewById(R.id.IssoList));
        view.removeAllViews();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr;

        cr = db.rawQuery("SELECT I_ISSO.C_ISSO, NAME, DORNAME, W_ISSO, coalesce(AVERAGE_REM_RATING, 0) as RATING, N_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                "left outer join I_REM_PLAN_CUR on (I_REM_PLAN_CUR.C_ISSO = I_ISSO.C_ISSO and I_REM_PLAN_CUR.REM_DATE = (select MAX(REM_DATE) from I_REM_PLAN_CUR where C_ISSO = I_ISSO.C_ISSO)) " +
                (selectedRoad.equals(getString(R.string.all_roads)) ? "" : " where DORNAME = '" + selectedRoad + "'") + " order by DORNAME, W_ISSO", null);

        cr.moveToFirst();

        for ( int i = 0; i < cr.getCount(); i++ )
        {
            final TextView tv = new TextView(this);
            SpannableString ss1 = new SpannableString("");
            int start = 0;
            int end = 0;

            if ( selectedRoad.equals(getString(R.string.all_roads)) ) {
                String str = String.format("%.3f", cr.getDouble(cr.getColumnIndex("W_ISSO")));
                str = str.replaceAll(",", "+");
                str = str.replaceAll("\\.", "+");
                ss1 = new SpannableString(ss1.toString() + "   " + cr.getString(cr.getColumnIndex("NAME")) + " (ОТС: " + cr.getString(cr.getColumnIndex("N_OTC_EXP"))+ ")" + "\n"
                        + String.format("%s, км %s (%s)", cr.getString(cr.getColumnIndex("DORNAME")), str, cr.getString(cr.getColumnIndex("OBSTACLE"))));
            }
            else {
                String str = String.format("%.3f", cr.getDouble(cr.getColumnIndex("W_ISSO")));
                str = str.replaceAll(",", "+");
                str = str.replaceAll("\\.", "+");
                ss1 = new SpannableString(ss1.toString() + "   " + cr.getString(cr.getColumnIndex("NAME")) + " (ОТС: " + cr.getString(cr.getColumnIndex("N_OTC_EXP"))+ ")" +
                        "\n" + String.format("км %s (%s)", str, cr.getString(cr.getColumnIndex("OBSTACLE"))));
            }
            ss1.setSpan(new RelativeSizeSpan(1.5f), start, end, 0);

            Drawable d;
            switch ( cr.getInt(cr.getColumnIndex("RATING")) )
            {
                case 10:
                    d = getResources().getDrawable(R.drawable.draw_1);
                    break;
                case 20:
                    d = getResources().getDrawable(R.drawable.draw_2);
                    break;
                case 30:
                    d = getResources().getDrawable(R.drawable.draw_3);
                    break;
                case 40:
                    d = getResources().getDrawable(R.drawable.draw_4);
                    break;
                case 50:
                    d = getResources().getDrawable(R.drawable.draw_5);
                    break;
                case 60:
                    d = getResources().getDrawable(R.drawable.draw_6);
                    break;
                default:
                    d = getResources().getDrawable(R.drawable.draw_0);
                    break;
            }

            assert d != null;
            d.setBounds(0, 0, 20, 20);
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            ss1.setSpan(span, end, end + 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            tv.setText(ss1);
            tv.setTag(cr.getInt(cr.getColumnIndex("C_ISSO")));

            view.addView(tv);
            tv.setOnClickListener(new View.OnClickListener()	{
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, IssoViewActivity.class);
                    intent.putExtra("C_ISSO", Integer.parseInt(tv.getTag().toString()));
                    startActivity(intent);
                }
            });
            cr.moveToNext();
        }

        cr.close();
    }

    static Boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }*/
}

class mainRow {
    int AverageRating;
    String Comment;
    int C_ISSO;

    mainRow(int AverageRating, String Comment, int C_ISSO) {
        this.Comment = Comment;
        this.AverageRating = AverageRating;
        this.C_ISSO = C_ISSO;
    }
}


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<mainRow> mainRows;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView rating;
        private TextView comment;

        public ViewHolder(View itemView) {
            super(itemView);
            if(MainActivity.theme.equals("0"))
                (itemView.findViewById(R.id.linLayoutMainRow)).setBackgroundResource(R.drawable.custom_bg_dark);
            else
                (itemView.findViewById(R.id.linLayoutMainRow)).setBackgroundResource(R.drawable.custom_bg);
            rating = (ImageView) itemView.findViewById(R.id.imgMainRating);
            comment = (TextView) itemView.findViewById(R.id.tvMainInfo);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<mainRow> mainRows) {
        this.mainRows = mainRows;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_row_layout, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.comment.setText(mainRows.get(position).Comment);
        int rate = mainRows.get(position).AverageRating;
        if (rate >=0 && rate < 20) {
            holder.rating.setImageResource(R.drawable.draw_1);
        }
        else if (rate >= 20 && rate < 40) {
            holder.rating.setImageResource(R.drawable.draw_2);
        }
        else if (rate >= 40 && rate < 60){
            holder.rating.setImageResource(R.drawable.draw_3);
        }
        else if (rate >= 60 && rate < 80) {
            holder.rating.setImageResource(R.drawable.draw_4);
        }
        else if (rate >= 80 && rate < 100) {
            holder.rating.setImageResource(R.drawable.draw_5);
        }
        else if (rate >= 100) {
            holder.rating.setImageResource(R.drawable.draw_6);
        }
        else {
            holder.rating.setImageResource(R.drawable.draw_0);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mainRows.size();
    }
}

/*class MainAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<mainRow> objects;

    MainAdapter(Context context, ArrayList<mainRow> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.main_row_layout, parent, false);
        }

        mainRow row = getTableRow(position);
        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.tvMainInfo)).setText(row.Comment);
        switch (row.AverageRating) {
            case 20:
                ((ImageView) view.findViewById(R.id.imgMainRating)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_1));
                break;
            case 30:
                ((ImageView) view.findViewById(R.id.imgMainRating)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_2));
                break;
            case 40:
                ((ImageView) view.findViewById(R.id.imgMainRating)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_3));
                break;
            case 50:
                ((ImageView) view.findViewById(R.id.imgMainRating)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_4));
                break;
            case 60:
                ((ImageView) view.findViewById(R.id.imgMainRating)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_5));
                break;
            case 70:
                ((ImageView) view.findViewById(R.id.imgMainRating)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_6));
                break;
            default:
                ((ImageView) view.findViewById(R.id.imgMainRating)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_0));
                break;
        }
        return view;
    }

    // товар по позиции
    mainRow getTableRow(int position) {
        return ((mainRow) getItem(position));
    }
}*/
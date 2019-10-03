package com.ais.admin.isso_s;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import android.util.Log;
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

    public DBHelper dbHelper;                                       // Для работы с БД
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


    public static LocationListener listen = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @SuppressLint("MissingPermission")
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
        //* Получение темы из preferences */
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
        //* Добавление кастомной кнопки floating button из нового material design.
        // Из-за поддержки двух тем одновременно и из-за использования Coordinator layout для snackbar'ов
        // пришлось поизвращаться с этим самым coordinator layout..
        // Также при смене темы на светлую цвет фона оставался темным по непонятным причинам, поэтому
        // меняется цвет фона программно.*/
        findViewById(R.id.button_road).setOnClickListener(roadSelector);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        //*  Подулючение toolbar со связанным заголовком*/
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.RelLayoutMain);
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") ) {
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_dark);
            ((ImageView) findViewById(R.id.imgSupport)).setImageDrawable(getResources().getDrawable(R.drawable.support_light));
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.menu_light);
        }
        else {
            ((FloatingActionButton)findViewById(R.id.button_road)).setImageResource(R.drawable.filter_light);
            ((ImageView) findViewById(R.id.imgSupport)).setImageDrawable(getResources().getDrawable(R.drawable.support_dark));
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.menu);
        }
        ab.setDisplayHomeAsUpEnabled(true);

        //* Подключение панели навигации, теперь кнопки меню не будет, будет только вот эта панель.*/
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
        //((Button) findViewById(R.id.button_road)).setText("Дорога: " + selectedRoad);

        //* Проверка наличия каких либо значений в БД, если ничего нету, то отправляет в синхронизацию, иначе до свиданья...*/
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

    public static void killGPS(LocationListener listen)
    {
        //if(ActivityMonitor.active == 0)
        if(listen != null) {
            locationManager.removeUpdates(listen);
        }
    }

    @SuppressLint("MissingPermission")
    public static void resumeGPS(LocationListener listen)
    {
        //if(ActivityMonitor.active != 1) return;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.0f, listen);
    }

    public void showGPSAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Включить GPS, Интернет");

        alertDialog.setMessage("Невозможно определить местоположение. Перейдите в меню 'Настройки'" +
                " и включите GPS, Интернет.");

        alertDialog.setPositiveButton("Настройки", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                wasCalled = true;
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Выход", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void insertWrapper() {
        List<String> permissionsNeeded = new ArrayList<>();

        final List<String> permissionsList = new ArrayList<>();
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");
        //if (!addPermission(permissionsList, Manifest.permission.READ_PHONE_STATE))
        //    permissionsNeeded.add("Read Phone State");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Write External Storage");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                StringBuilder message = new StringBuilder("Для работы с приложением Вам необходим доступ к разрешениям " + permissionsNeeded.get(0));
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message.append(", ").append(permissionsNeeded.get(i));
                showMessageOKCancel(message.toString(),
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

    @Override
    public void onStop() {
        super.onStop();
        //ActivityMonitor.active--;
        killGPS(listen);
    }

    @Override
    public void onPause() {
        super.onPause();
        //ActivityMonitor.active--;
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
            int hasWriteContactsPermission;
            hasWriteContactsPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED)
                resumeGPS(listen);
        }
        //* wasCalled -  флаг, показывющий, что мы пришли из стороннего uri по вопросу с GPS. Если
        // да, то пытаемся найти местоположение, иначе опять диалоговое окно с требованием включить GPS*/
        if (wasCalled) {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                showGPSAlert();
            }
        }

        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals(theme)) {
            if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
                Utils.changeToTheme(MainActivity.this, Utils.THEME_DARK);
            }
            else {
                Utils.changeToTheme(MainActivity.this, Utils.THEME_WHITE);
            }
        }

        NewRefreshIssoList();

        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        //* Здесь происходит проверка новой версии БД, если так и есть и при этом диаолговое окно не вызывалось, то идем в синхронизацию*/
        String nameCurator = sp.getString("nameCurator", "");
        if(!nameCurator.equals("")) {
            View view = ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0);
            ((TextView) view.findViewById(R.id.tvCurator)).setText("Пользователь: " + nameCurator);
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
        //* Опять же проверка того, что у нас до этого момента были показаны какие то диалоговые окна, если да, то погнали показывать*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)	{
        switch(item.getItemId())
        {
			/*case R.id.autoSelect:
				final String appPackageName = "ru.yandex.yandexmaps";
				boolean installed = appInstalledOrNot(appPackageName);
				if(installed) {
					startAutoSelect();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("Внимание:");
					builder.setMessage("На данном устройстве не установлены 'Яндекс карты'. Перейдите в Google Play Market и установите карты " +
							"или продолжайте использовать автовыбор без карт.");
					builder.setNegativeButton("Перейти в Play Market", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							try {
								startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
							} catch (android.content.ActivityNotFoundException anfe) {
								startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
							}
						}
					});
					builder.setPositiveButton("Продолжить без карт", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							startAutoSelectWithoutMaps();
						}
					});
					AlertDialog alert = builder.create();
					alert.show();
				}
				break;
			case R.id.synchronize:
				startSynchronize();
				break;
			case R.id.settings:
				startSettings();
				break;
				*/
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 123:
            {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                //perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        //&& perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
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
        startActivity(intent);
    }

    private void startAutoSelect() {
        Cursor cr = dbHelper.getReadableDatabase().rawQuery("SELECT I_ISSO.C_ISSO, NAME, FULLNAME, DORNAME, W_ISSO, coalesce(RATING, -4) as RATING, N_OTC_EXP, C_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                "left outer join RATING on (RATING.C_ISSO = I_ISSO.C_ISSO and RATING.RATINGDATE = (select MAX(RATINGDATE) from RATING where C_ISSO = I_ISSO.C_ISSO)) ", null);
        if(cr.moveToFirst()) {
            HttpsIsso[] listOfIssos = new HttpsIsso[cr.getCount()];
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
                //isso.NameIsso = cr.getString(cr.getColumnIndex("NAME_ISSO"));
                isso.Longitude = cr.getDouble(cr.getColumnIndex("LONGITUDE"));
                isso.Length = cr.getString(cr.getColumnIndex("LENGTH"));
                isso.Obstacle = cr.getString(cr.getColumnIndex("OBSTACLE"));
                listOfIssos[i] = isso;
                rating[i] = cr.getInt(cr.getColumnIndex("RATING"));
                cr.moveToNext();
            }
            cr.close();
            Drawable[] DRAWABLES = {getResources().getDrawable(R.drawable.draw_0),
                    getResources().getDrawable(R.drawable.draw_1), getResources().getDrawable(R.drawable.draw_2),
                    getResources().getDrawable(R.drawable.draw_3), getResources().getDrawable(R.drawable.draw_4),
                    getResources().getDrawable(R.drawable.draw_6), getResources().getDrawable(R.drawable.draw_5)};
            IssoViewActivity issoViewActivity = new IssoViewActivity();
            MapsActivity activity = new MapsActivity(listOfIssos, locationManager, rating, DRAWABLES);
            Intent intent = new Intent(this, activity.getClass());
            startActivity(intent);
        }
    }

	/*private void startAutoSelectWithoutMaps() {
        Cursor cr = new DBHelper(this).getReadableDatabase().rawQuery("SELECT I_ISSO.C_ISSO, NAME, FULLNAME, DORNAME, W_ISSO, coalesce(RATING, 0) as RATING, N_OTC_EXP, C_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                "left outer join RATING on (RATING.C_ISSO = I_ISSO.C_ISSO and RATING.RATINGDATE = (select MAX(RATINGDATE) from RATING where C_ISSO = I_ISSO.C_ISSO)) ", null);
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
                rating[i] = cr.getInt(cr.getColumnIndex("RATING"));
                cr.moveToNext();
            }
            Drawable[] DRAWABLES = {getResources().getDrawable(R.drawable.draw_0),
                    getResources().getDrawable(R.drawable.draw_1), getResources().getDrawable(R.drawable.draw_2),
                    getResources().getDrawable(R.drawable.draw_3), getResources().getDrawable(R.drawable.draw_4),
                    getResources().getDrawable(R.drawable.draw_5)};
            IssoViewActivity issoViewActivity = new IssoViewActivity();
            Autoselect activity = new Autoselect(listOfIssos, locationManager, rating, DRAWABLES, issoViewActivity);
            Intent intent = new Intent(this, activity.getClass());
            intent.putExtra("withoutMaps", true);
            startActivity(intent);
        }
    }*/

    private void NewRefreshIssoList()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cr;

        cr = db.rawQuery("SELECT I_ISSO.C_ISSO, NAME, DORNAME, W_ISSO, NAME_ISSO, coalesce(RATING, -1) as RATING, N_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                "left outer join RATING on (RATING.C_ISSO = I_ISSO.C_ISSO and RATING.RATINGDATE = (select MAX(RATINGDATE) from RATING where C_ISSO = I_ISSO.C_ISSO)) " +
                (selectedRoad.equals(getString(R.string.all_roads)) ? "" : " where DORNAME = '" + selectedRoad + "'" + " order by DORNAME, W_ISSO"), null);

        cr.moveToFirst();
        mainRows.clear();

        for ( int i = 0; i < cr.getCount(); i++ ) {
            String info;
            //if ( selectedRoad.equals(getString(R.string.all_roads)) ) {
            int w_isso = cr.getInt(cr.getColumnIndex("W_ISSO"));
            int km = w_isso >> 16;
            int m = w_isso & 0xFFFF;

            //String str = String.format("%.3f", cr.getInt(cr.getColumnIndex("W_ISSO")));
            //str = str.replaceAll(",", "+");
            String str = km + "+" + m;
            String dop = (!cr.isNull(cr.getColumnIndex("NAME_ISSO")) && !cr.getString(cr.getColumnIndex("NAME_ISSO")).equals("")  ?
                    String.format(" (%s)", cr.getString(cr.getColumnIndex("NAME_ISSO"))) : "");
            info = cr.getString(cr.getColumnIndex("NAME")) + " (ОТС: " + cr.getString(cr.getColumnIndex("N_OTC_EXP"))+ ")" + "\n"
                    + String.format("%s, км %s%s (%s)", cr.getString(cr.getColumnIndex("DORNAME")), str, dop, cr.getString(cr.getColumnIndex("OBSTACLE")));
            /*}
            else {
                String str = String.format("%.3f", cr.getDouble(cr.getColumnIndex("W_ISSO")));
                str = str.replaceAll(",", "+");
                str = str.replaceAll("\\.", "+");
                info = cr.getString(cr.getColumnIndex("NAME")) + " (ОТС: " + cr.getString(cr.getColumnIndex("N_OTC_EXP"))+ ")" +
                        "\n" + String.format("км %s (%s)", str, cr.getString(cr.getColumnIndex("OBSTACLE")));
            }*/

            int rating = cr.getInt(cr.getColumnIndex("RATING"));
            int cIsso = cr.getInt(cr.getColumnIndex("C_ISSO"));
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
    }

	/*private void RefreshIssoList() {
		LinearLayout view = ((LinearLayout) findViewById(R.id.IssoList));
		view.removeAllViews();

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cr;

		cr = db.rawQuery("SELECT I_ISSO.C_ISSO, NAME, DORNAME, W_ISSO, coalesce(RATING, 0) as RATING, N_OTC_EXP, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
				"left outer join RATING on (RATING.C_ISSO = I_ISSO.C_ISSO and RATING.RATINGDATE = (select MAX(RATINGDATE) from RATING where C_ISSO = I_ISSO.C_ISSO)) " +
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
				case 20:
					d = getResources().getDrawable(R.drawable.draw_1);
					break;
				case 30:
					d = getResources().getDrawable(R.drawable.draw_2);
					break;
				case 40:
					d = getResources().getDrawable(R.drawable.draw_3);
					break;
				case 50:
					d = getResources().getDrawable(R.drawable.draw_4);
					break;
				case 60:
					d = getResources().getDrawable(R.drawable.draw_5);
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
			if ( i % 2 == 0 )
				if ( PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
					tv.setBackgroundColor(obtainStyledAttributes(R.style.StyleColorDark, new int[]{ android.R.attr.background }).getColor(0, Color.BLACK));
				else
					tv.setBackgroundColor(obtainStyledAttributes(R.style.StyleColorLight, new int[]{ android.R.attr.background }).getColor(0, Color.BLACK));

			view.addView(tv);
			tv.setOnClickListener(new OnClickListener()	{
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, IssoViewActivity.class);
					intent.putExtra("C_ISSO", tv.getTag().toString());
					startActivity(intent);
				}
			});
			cr.moveToNext();
		}

		cr.close();
	}*/
}



class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "ISSO_DB", null, 16);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table I_ISSO ("
                + "C_ISSO integer primary key,"
                + "NAME text,"
                + "NAME_ISSO text,"
                + "C_OTC_EXP integer,"
                + "N_OTC_EXP text,"
                + "FULLNAME text,"
                + "DORNAME text,"
                + "W_ISSO integer,"
                + "OBSTACLE text, "
                + "LENGTH REAL,"
                + "LATITUDE REAL,"
                + "LONGITUDE REAL" + ");");
        db.execSQL("create table RATING ("
                + "C_ISSO integer,"
                + "RATINGDATE DATETIME,"
                + "RATINGDATEEDIT DATETIME,"
                + "RATING integer,"
                + "LATITUDE_RATING REAL,"
                + "LONGITUDE_RATING REAL,"
                + "COMMENTS text,"
                + "SYNC boolean,"
                + "OFFSET long,"
                + "CURRENTRATING integer DEFAULT (0),"
                + "CHECKOUTOFPLAN boolean DEFAULT (0),"
                + " primary key(C_ISSO, RATINGDATE), "
                + " foreign key(C_ISSO) references I_ISSO(C_ISSO) on delete cascade"
                + ");");
        db.execSQL("create table PHOTOS_RATING ("
                + "C_ISSO integer,"
                + "RATINGDATE DATETIME,"
                + "PHOTOPATH text,"
                + "PHOTODATE DATETIME,"
                + "COMMENT text,"
                + "SYNC boolean,"
                + "primary key(C_ISSO, RATINGDATE, PHOTODATE),"
                + "foreign key(C_ISSO, RATINGDATE) references RATING(C_ISSO, RATINGDATE) on delete cascade"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Tag", " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");
        db.beginTransaction();
        try {
            if(newVersion == 9) {
                Cursor cr = db.rawQuery("select * from I_ISSO", null);
                cr.moveToFirst();
                if (cr.getColumnIndex("C_OTC_EXP") == -1) {
                    db.execSQL("alter table I_ISSO ADD C_OTC_EXP integer");
                    db.execSQL("alter table I_ISSO ADD N_OTC_EXP text");
                }
                db.execSQL("create temporary table I_ISSO_tmp ("
                        + "C_ISSO integer primary key,"
                        + "NAME text,"
                        + "C_OTC_EXP integer,"
                        + "N_OTC_EXP text,"
                        + "FULLNAME text,"
                        + "DORNAME text,"
                        + "W_ISSO REAL,"
                        + "OBSTACLE text, "
                        + "LENGTH REAL,"
                        + "LATITUDE REAL,"
                        + "LONGITUDE REAL);");
                db.execSQL("insert into I_ISSO_tmp select * from I_ISSO;");
                db.execSQL("drop table I_ISSO;");
                db.execSQL("create table I_ISSO ("
                        + "C_ISSO integer primary key,"
                        + "NAME text,"
                        + "C_OTC_EXP integer,"
                        + "N_OTC_EXP text,"
                        + "FULLNAME text,"
                        + "DORNAME text,"
                        + "W_ISSO REAL,"
                        + "OBSTACLE text, "
                        + "LENGTH REAL,"
                        + "LATITUDE REAL,"
                        + "LONGITUDE REAL" + ");");
                db.execSQL("insert into I_ISSO select * from I_ISSO_tmp;");
                db.execSQL("drop table I_ISSO_tmp;");


                cr = db.rawQuery("select * from RATING", null);
                cr.moveToFirst();
                if (cr.getColumnIndex("LATITUDE_RATING") == -1) {
                    db.execSQL("alter table RATING ADD LATITUDE_RATING REAL");
                    db.execSQL("alter table RATING ADD LONGITUDE_RATING REAL");
                }
                if (cr.getColumnIndex("RATINGDATEEDIT") == -1) {
                    db.execSQL("alter table RATING ADD RATINGDATEEDIT DATETIME");
                }
                db.execSQL("create temporary table RATING_tmp ("
                        + "C_ISSO integer,"
                        + "RATINGDATE DATETIME,"
                        + "RATINGDATEEDIT DATETIME,"
                        + "RATING integer,"
                        + "LATITUDE_RATING REAL,"
                        + "LONGITUDE_RATING REAL,"
                        + "COMMENTS text,"
                        + "SYNC boolean,"
                        + "OFFSET long,"
                        + "CURRENTRATING integer DEFAULT (0),"
                        + " primary key(C_ISSO, RATINGDATE), "
                        + " foreign key(C_ISSO) references I_ISSO(C_ISSO) on delete cascade"
                        + ");");
                db.execSQL("insert into RATING_tmp select * from RATING;");
                db.execSQL("drop table RATING;");
                db.execSQL("create table RATING ("
                        + "C_ISSO integer,"
                        + "RATINGDATE DATETIME,"
                        + "RATINGDATEEDIT DATETIME,"
                        + "RATING integer,"
                        + "LATITUDE_RATING REAL,"
                        + "LONGITUDE_RATING REAL,"
                        + "COMMENTS text,"
                        + "SYNC boolean,"
                        + "OFFSET long,"
                        + "CURRENTRATING integer DEFAULT (0),"
                        + " primary key(C_ISSO, RATINGDATE), "
                        + " foreign key(C_ISSO) references I_ISSO(C_ISSO) on delete cascade"
                        + ");");
                db.execSQL("insert into RATING select * from RATING_tmp;");
                db.execSQL("drop table RATING_tmp;");
            }
            if(newVersion == 10) {
                db.execSQL("create temporary table I_ISSO_tmp ("
                        + "C_ISSO integer primary key,"
                        + "NAME text,"
                        + "C_OTC_EXP integer,"
                        + "N_OTC_EXP text,"
                        + "FULLNAME text,"
                        + "DORNAME text,"
                        + "W_ISSO integer,"
                        + "OBSTACLE text, "
                        + "LENGTH REAL,"
                        + "LATITUDE REAL,"
                        + "LONGITUDE REAL);");
                db.execSQL("insert into I_ISSO_tmp select * from I_ISSO;");
                db.execSQL("drop table I_ISSO;");
                db.execSQL("create table I_ISSO ("
                        + "C_ISSO integer primary key,"
                        + "NAME text,"
                        + "C_OTC_EXP integer,"
                        + "N_OTC_EXP text,"
                        + "FULLNAME text,"
                        + "DORNAME text,"
                        + "W_ISSO integer,"
                        + "OBSTACLE text, "
                        + "LENGTH REAL,"
                        + "LATITUDE REAL,"
                        + "LONGITUDE REAL" + ");");
                db.execSQL("insert into I_ISSO select * from I_ISSO_tmp;");
                db.execSQL("drop table I_ISSO_tmp;");
            }
            // If you need to add a new column
            if (newVersion > oldVersion) {
                db.execSQL("create table if not exists PHOTOS_RATING ("
                        + "C_ISSO integer,"
                        + "RATINGDATE DATETIME,"
                        + "PHOTOPATH text,"
                        + "PHOTODATE DATETIME,"
                        + "COMMENT text,"
                        + "primary key(C_ISSO, RATINGDATE, PHOTODATE),"
                        + "foreign key(C_ISSO, RATINGDATE) references RATING(C_ISSO, RATINGDATE) on delete cascade"
                        + ");");
                if (!isColumnExists("RATING", "CHECKOUTOFPLAN", db))
                    db.execSQL("ALTER TABLE RATING ADD COLUMN CHECKOUTOFPLAN boolean DEFAULT 0");
                if (!isColumnExists("PHOTOS_RATING", "SYNC", db))
                    db.execSQL("ALTER TABLE PHOTOS_RATING ADD COLUMN SYNC boolean DEFAULT 0");
                if(!isColumnExists("I_ISSO","NAME_ISSO", db))
                    db.execSQL("ALTER TABLE I_ISSO ADD COLUMN NAME_ISSO text");

            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
        SharedPreferences sp = context.getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("newVersion", true);
        e.apply();
    }

    public boolean isColumnExists (String table, String column, SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("PRAGMA table_info("+ table +")", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                if (column.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }

        return false;
    }
}


enum Rating
{   ;public static String getDescription(int _id)
{
    switch(_id)
    {
        case 20: return "Без изменений";
        case 30: return "Незначительное ухудшение";
        case 40: return "Ухудшение";
        case 50: return "Значительное ухудшение";
        case 60: return "Улучшение";
        default: return null;
    }
}
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
            if (MainActivity.theme.equals("0"))
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
        switch (mainRows.get(position).AverageRating) {
            case 20:
                holder.rating.setImageResource(R.drawable.draw_1);
                break;
            case 30:
                holder.rating.setImageResource(R.drawable.draw_2);
                break;
            case 40:
                holder.rating.setImageResource(R.drawable.draw_3);
                break;
            case 50:
                holder.rating.setImageResource(R.drawable.draw_4);
                break;
            case 60:
                holder.rating.setImageResource(R.drawable.draw_5);
                break;
            case 70:
                holder.rating.setImageResource(R.drawable.draw_6);
                break;
            default:
                holder.rating.setImageResource(R.drawable.draw_0);
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mainRows.size();
    }
}
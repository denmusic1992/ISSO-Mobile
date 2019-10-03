package com.ais.admin.isso_s;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private boolean wasCalled = false;                              //переменная для перехода в настройки
    private Location prevLocation = null;                           //предыдущая локация
    private static HttpsIsso[] listOfIssos;                         //весь список сооружений
    private static Drawable[] draw;                                 //список картинок
    // public static IssoViewActivity issoActivity;                       //класс для вызова списка рейтингов/работ/...
    private HashMap<Integer, Vector2D> dists = new HashMap<>();     //расстояния до рассматриваемых объектов
    private boolean isGPSEnabled = false;                           //наличие gps
    private boolean wasMarked = false;                              //
    private ArrayVector hashMap;                                    //
    static final double rad = 6371200;                              //Радиус Земли
    private FloatingActionButton btnFollow;                         //Кнопка слежения по карте
    public static boolean isFollowed = false;                       //Идет ли слежение
    private boolean is500Left = false;                              //наличие расстояния в 500 м
    private boolean isArrived = false;                              // Приехали или нет
    private Location location;                                      //Текущее местоположение
    private TextView tvFar;                                         //...........
    private TextView tvFarDistance;                                 //
    private TextView tvNear;                                        //
    private TextView tvNearDistance;                                // Это
    private TextView tvBehind;                                      // для
    private TextView tvBehindDistance;                              // инициализации
    private ImageView imgGeoFar;                                    // списка
    private ImageView imgGeoNear;                                   //
    private ImageView imgGeoBehind;                                 //
    private ImageView imgFarRating;                                 //
    private ImageView imgNearRating;                                //
    private ImageView imgBehindRating;                              //...........
    private boolean onlyOnce = true;                                //При первом получении координат показать таблицу
    private GoogleMap mMap;                                         //Инициализатор карты
    private Marker pointAhead = null;                               //Маркер впереди
    private Runnable r;
    private FloatingActionMenu Menu;
    private FloatingActionMenu map_menu;
    private TextToSpeech t1;
    private DisplayMetrics displaymetrics;


    /** конструктор */
    public MapsActivity() {
        super();
    }

    /** конструктор */
    public MapsActivity(HttpsIsso[] listOfIssos, LocationManager locationManager, int[] rating, Drawable[] draw) {
        super();
        MapsActivity.listOfIssos = listOfIssos;
        MapsActivity.draw = draw;
    }

    /** Возобновление получения gps через каждые 1000 мс;*/
    @SuppressLint("MissingPermission")
    public static void resumeGPS(LocationListener listen) {
        //if(ActivityMonitor.active != 1) return;
        MainActivity.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.0f, listen);
    }

    /** Отколючение слежения за gps`*/
    public static void killGPS(LocationListener listen) {
        //if(ActivityMonitor.active == 0)
        if(listen != null) {
            MainActivity.locationManager.removeUpdates(listen);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);
        setContentView(R.layout.activity_maps);
        //* При горизонтальной ориентации пределать параметры лайаутов*/
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.linLayoutAutoselect);
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
            linearLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
        //* Постоянно горит экран */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Получение информации о наличии gps
        isGPSEnabled = MainActivity.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSEnabled) {
            MainActivity.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
                    myListener);
        }
        else {
            showGPSAlert();
        }
        //* Инициализация компонент таблицы в соответствии с выбранной темой*/
        if( PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Theme", "0").equals("0")) {
            tvFar = (TextView) findViewById(R.id.textViewFar);
            tvFar.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvFarDistance = (TextView) findViewById(R.id.textViewFarDistance);
            tvFarDistance.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvNear = (TextView) findViewById(R.id.textViewNear);
            tvNear.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvNearDistance = (TextView) findViewById(R.id.textViewNearDistance);
            tvNearDistance.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvBehind = (TextView) findViewById(R.id.textViewBehind);
            tvBehind.setTextColor(getResources().getColor(R.color.textBehindDark));
            tvBehindDistance = (TextView) findViewById(R.id.textViewBehindDistance);
            tvBehindDistance.setTextColor(getResources().getColor(R.color.textBehindDark));
        }
        else {
            tvFar = (TextView) findViewById(R.id.textViewFar);
            tvFar.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvFarDistance = (TextView) findViewById(R.id.textViewFarDistance);
            tvFarDistance.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvNear = (TextView) findViewById(R.id.textViewNear);
            tvNear.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvNearDistance = (TextView) findViewById(R.id.textViewNearDistance);
            tvNearDistance.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvBehind = (TextView) findViewById(R.id.textViewBehind);
            tvBehind.setTextColor(getResources().getColor(R.color.textBehindLight));
            tvBehindDistance = (TextView) findViewById(R.id.textViewBehindDistance);
            tvBehindDistance.setTextColor(getResources().getColor(R.color.textBehindLight));
        }
        imgNearRating = (ImageView) findViewById(R.id.imgNearRating);
        imgFarRating = (ImageView) findViewById(R.id.imgFarRating);
        imgBehindRating = (ImageView) findViewById(R.id.imgBehindRating);
        imgGeoNear = (ImageView) findViewById(R.id.imgGeoNear);
        imgGeoFar = (ImageView) findViewById(R.id.imgGeoFar);
        imgGeoBehind = (ImageView) findViewById(R.id.imgGeoBehind);
        //findViewById(R.id.map).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("ru"));
                }
            }
        });
        //* Инициализация кнопок меню */
        FloatingActionButton ListOnly, ListNMap, MapOnly;
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            (findViewById(R.id.fabMenu_light)).setVisibility(View.GONE);
            (findViewById(R.id.fabFollowLight)).setVisibility(View.GONE);
            Menu = (FloatingActionMenu) findViewById(R.id.fabMenu_dark);
            ListOnly = (FloatingActionButton) findViewById(R.id.fabListDark);
            ListNMap = (FloatingActionButton) findViewById(R.id.fabListNMapDark);
            MapOnly = (FloatingActionButton) findViewById(R.id.fabMapDark);
            btnFollow = (FloatingActionButton) findViewById(R.id.fabFollowDark);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.no_location_light));
        }
        else {
            (findViewById(R.id.fabMenu_dark)).setVisibility(View.GONE);
            (findViewById(R.id.fabFollowDark)).setVisibility(View.GONE);
            Menu = (FloatingActionMenu) findViewById(R.id.fabMenu_light);
            ListOnly = (FloatingActionButton) findViewById(R.id.fabListLight);
            ListNMap = (FloatingActionButton) findViewById(R.id.fabListNMapLight);
            MapOnly = (FloatingActionButton) findViewById(R.id.fabMapLight);
            btnFollow = (FloatingActionButton) findViewById(R.id.fabFollowLight);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.no_location_dark));
        }

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFollowed) {
                    isFollowed = true;
                    if (FollowByObjects(location)) {
                        if (PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
                            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.define_location_light));
                        } else {
                            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.define_location_dark));
                        }
                        Toast.makeText(v.getContext(), "Слежение включено.", Toast.LENGTH_SHORT).show();
                    } else {
                        isFollowed = false;
                        Toast.makeText(v.getContext(), "Слежение невозможно. Нет объектов впереди.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    isFollowed = false;
                    if (PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
                        btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_light));
                    } else {
                        btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_dark));
                    }
                    Toast.makeText(v.getContext(), "Слежение приостановлено.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFollow.setClickable(false);
        displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        Menu.setClosedOnTouchOutside(true);
        //* Слушатели на переключение типов меню */
        ListOnly.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams paramsTable, paramsMap;
                ResizeAnimation a = new ResizeAnimation(findViewById(R.id.layoutAutoselect));
                a.setDuration(300);
                ResizeAnimation b = new ResizeAnimation(findViewById(R.id.linMap));
                b.setDuration(300);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    a.setParamsHeight(findViewById(R.id.layoutAutoselect).getLayoutParams().height, displaymetrics.heightPixels);
                    a.setParamsWidth(findViewById(R.id.layoutAutoselect).getLayoutParams().width, findViewById(R.id.layoutAutoselect).getLayoutParams().width);
                    b.setParamsHeight(findViewById(R.id.linMap).getLayoutParams().height, 0);
                    b.setParamsWidth(findViewById(R.id.linMap).getLayoutParams().width, findViewById(R.id.linMap).getLayoutParams().width);
                }
                else {
                    a.setParamsWidth(findViewById(R.id.layoutAutoselect).getLayoutParams().width, displaymetrics.widthPixels);
                    a.setParamsHeight(findViewById(R.id.layoutAutoselect).getLayoutParams().height, findViewById(R.id.layoutAutoselect).getLayoutParams().height);
                    b.setParamsWidth(findViewById(R.id.linMap).getLayoutParams().width, 0);
                    b.setParamsHeight(findViewById(R.id.linMap).getLayoutParams().height, findViewById(R.id.linMap).getLayoutParams().height);
                }
                findViewById(R.id.layoutAutoselect).startAnimation(a);
                findViewById(R.id.linMap).startAnimation(b);
                /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    paramsTable = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.0f);
                }
                else {
                    paramsTable = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 0.0f);
                }
                (findViewById(R.id.layoutAutoselect)).setLayoutParams(paramsTable);
                (findViewById(R.id.map)).setLayoutParams(paramsMap);*/
                Menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.list_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.list_dark);
            }
        });
        ListNMap.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams paramsTable, paramsMap;
                ResizeAnimation a = new ResizeAnimation(findViewById(R.id.layoutAutoselect));
                a.setDuration(300);
                ResizeAnimation b = new ResizeAnimation(findViewById(R.id.linMap));
                b.setDuration(300);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    a.setParamsHeight(findViewById(R.id.layoutAutoselect).getLayoutParams().height, 1);
                    a.setParamsWidth(findViewById(R.id.layoutAutoselect).getLayoutParams().width, findViewById(R.id.layoutAutoselect).getLayoutParams().width);
                    b.setParamsHeight(findViewById(R.id.linMap).getLayoutParams().height, displaymetrics.heightPixels);
                    b.setParamsWidth(findViewById(R.id.linMap).getLayoutParams().width, findViewById(R.id.linMap).getLayoutParams().width);
                }
                else {
                    a.setParamsWidth(findViewById(R.id.layoutAutoselect).getLayoutParams().width, 1);
                    a.setParamsHeight(findViewById(R.id.layoutAutoselect).getLayoutParams().height, findViewById(R.id.layoutAutoselect).getLayoutParams().height);
                    b.setParamsWidth(findViewById(R.id.linMap).getLayoutParams().width, displaymetrics.widthPixels);
                    b.setParamsHeight(findViewById(R.id.linMap).getLayoutParams().height, findViewById(R.id.linMap).getLayoutParams().height);
                }
                findViewById(R.id.layoutAutoselect).startAnimation(a);
                findViewById(R.id.linMap).startAnimation(b);
                /*LinearLayout.LayoutParams paramsTable, paramsMap;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    paramsTable = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.0f);
                    paramsMap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                }
                else {

                    paramsTable = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 0.0f);
                    paramsMap = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                }
                (findViewById(R.id.layoutAutoselect)).setLayoutParams(paramsTable);
                (findViewById(R.id.map)).setLayoutParams(paramsMap);*/
                Menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.map_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.map_dark);
                if(location != null)
                    drawMarkersNStaff();
            }
        });
        MapOnly.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams paramsTable, paramsMap;
                ResizeAnimation a = new ResizeAnimation(findViewById(R.id.layoutAutoselect));
                a.setDuration(300);
                ResizeAnimation b = new ResizeAnimation(findViewById(R.id.linMap));
                b.setDuration(300);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    a.setParamsHeight(findViewById(R.id.layoutAutoselect).getLayoutParams().height, displaymetrics.heightPixels / 2);
                    a.setParamsWidth(findViewById(R.id.layoutAutoselect).getLayoutParams().width, findViewById(R.id.layoutAutoselect).getLayoutParams().width);
                    b.setParamsHeight(findViewById(R.id.linMap).getLayoutParams().height, displaymetrics.heightPixels / 2);
                    b.setParamsWidth(findViewById(R.id.linMap).getLayoutParams().width, findViewById(R.id.linMap).getLayoutParams().width);
                }
                else {
                    a.setParamsWidth(findViewById(R.id.layoutAutoselect).getLayoutParams().width, displaymetrics.widthPixels / 2);
                    a.setParamsHeight(findViewById(R.id.layoutAutoselect).getLayoutParams().height, findViewById(R.id.layoutAutoselect).getLayoutParams().height);
                    b.setParamsWidth(findViewById(R.id.linMap).getLayoutParams().width, displaymetrics.widthPixels / 2);
                    b.setParamsHeight(findViewById(R.id.linMap).getLayoutParams().height, findViewById(R.id.linMap).getLayoutParams().height);
                }
                findViewById(R.id.layoutAutoselect).startAnimation(a);
                findViewById(R.id.linMap).startAnimation(b);
                /*LinearLayout.LayoutParams paramsTable, paramsMap;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    paramsTable = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                }
                else {
                    paramsTable = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                }
                (findViewById(R.id.layoutAutoselect)).setLayoutParams(paramsTable);
                (findViewById(R.id.map)).setLayoutParams(paramsMap);*/
                Menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.map_list_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.map_list_dark);
                if(location != null)
                    drawMarkersNStaff();
            }
        });
        FloatingActionButton normal_btn, satellite_btn, terrain_btn;
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            (findViewById(R.id.fabMenuSatellite_light)).setVisibility(View.GONE);
            map_menu = (FloatingActionMenu) findViewById(R.id.fabMenuSatellite_dark);
            normal_btn = (FloatingActionButton) findViewById(R.id.fabNormalDark);
            satellite_btn = (FloatingActionButton) findViewById(R.id.fabSatelliteDark);
            terrain_btn = (FloatingActionButton) findViewById(R.id.fabTerrainDark);
        }
        else {
            (findViewById(R.id.fabMenuSatellite_dark)).setVisibility(View.GONE);
            map_menu = (FloatingActionMenu) findViewById(R.id.fabMenuSatellite_light);
            normal_btn = (FloatingActionButton) findViewById(R.id.fabNormalLight);
            satellite_btn = (FloatingActionButton) findViewById(R.id.fabSatelliteLight);
            terrain_btn = (FloatingActionButton) findViewById(R.id.fabTerrainLight);
        }
        map_menu.setClosedOnTouchOutside(true);
        //* Слушатели на переключение типов меню */
        normal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map_menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getString("Theme", "0").equals("0"))
                    map_menu.getMenuIconView().setImageResource(R.drawable.normal_map_light);
                else
                    map_menu.getMenuIconView().setImageResource(R.drawable.normal_map_dark);
            }
        });
        satellite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                map_menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getString("Theme", "0").equals("0"))
                    map_menu.getMenuIconView().setImageResource(R.drawable.satellite_light);
                else
                    map_menu.getMenuIconView().setImageResource(R.drawable.satellite_dark);
            }
        });
        terrain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                map_menu.close(true);
                if (PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getString("Theme", "0").equals("0"))
                    map_menu.getMenuIconView().setImageResource(R.drawable.terrain_light);
                else
                    map_menu.getMenuIconView().setImageResource(R.drawable.terrain_dark);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Бездействие пользователя
        r = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //Убираем кнопки
                if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getBoolean("HideButtons", true)) {
                    Menu.hideMenuButton(true);
                    map_menu.hideMenuButton(true);
                    btnFollow.hideButtonInMenu(true);
                }
            }
        };
        startHandler();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout l = (LinearLayout) findViewById(R.id.mainLayoutAuto);
            LinearLayout.LayoutParams paramsAutoselect = (LinearLayout.LayoutParams) (findViewById(R.id.layoutAutoselect)).getLayoutParams();
            LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) (findViewById(R.id.linMap)).getLayoutParams();
            int width = paramsAutoselect.width;
            int height = paramsAutoselect.height;
            paramsAutoselect.width = height;
            paramsAutoselect.height = width;
            width = paramsMap.width;
            height = paramsMap.height;
            paramsMap.width = height;
            paramsMap.height = width;
            l.setOrientation(LinearLayout.HORIZONTAL);
        }
    }

    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getBoolean("HideButtons", true)) {
            stopHandler();//stop first and then start
            startHandler();
            Menu.showMenuButton(true);
            map_menu.showMenuButton(true);
            btnFollow.showButtonInMenu(true);
        }
    }
    public void stopHandler() {
        (findViewById(R.id.linLayoutAutoselect)).removeCallbacks(r);
    }

    public void startHandler() {
        (findViewById(R.id.linLayoutAutoselect)).postDelayed(r, 10000);
    }

    public boolean onOptionsItemSelected(MenuItem item)	{
        switch(item.getItemId())
        {
            case R.id.normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Ловим переворот экрана */
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        LinearLayout l = (LinearLayout) findViewById(R.id.mainLayoutAuto);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout.LayoutParams paramsAutoselect = (LinearLayout.LayoutParams) (findViewById(R.id.layoutAutoselect)).getLayoutParams();
            LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) (findViewById(R.id.linMap)).getLayoutParams();
            int width = paramsAutoselect.width;
            int height = paramsAutoselect.height;
            paramsAutoselect.width = height;
            paramsAutoselect.height = width;
            width = paramsMap.width;
            height = paramsMap.height;
            paramsMap.width = height;
            paramsMap.height = width;
            l.setOrientation(LinearLayout.HORIZONTAL);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayout.LayoutParams paramsAutoselect = (LinearLayout.LayoutParams) (findViewById(R.id.layoutAutoselect)).getLayoutParams();
            LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) (findViewById(R.id.linMap)).getLayoutParams();
            int width = paramsAutoselect.width;
            int height = paramsAutoselect.height;
            paramsAutoselect.width = height;
            paramsAutoselect.height = width;
            width = paramsMap.width;
            height = paramsMap.height;
            paramsMap.width = height;
            paramsMap.height = width;
            l.setOrientation(LinearLayout.VERTICAL);
        }
    }

    /** Запиливаем инфу в табл */
    public void setTheRest (final HttpsIsso isso, TextView tv, ImageView rate, ImageView geo, int rating, Drawable[] draw, Drawable marker) {
        try {
           /* String str = String.format("%.3f", isso.WIsso);
            str = str.replaceAll(",", "+");
            str = str.replaceAll("\\.", "+");*/
            int km = isso.WIsso >> 16;
            int m = isso.WIsso & 0xFFFF;
            String str = km + "+" + m;
            String ss1 = isso.Name + ": " + String.format("%s, км %s (%s)\n", isso.DorName, str, isso.Obstacle);
            //* здесь выбираем инконку рисуемую */
            /*if (rating >=0 && rating < 20) {
                rate.setImageDrawable(draw[1]);
            }
            else if (rating >= 20 && rating < 40) {
                rate.setImageDrawable(draw[2]);
            }
            else if (rating >= 40 && rating < 60){
                rate.setImageDrawable(draw[3]);
            }
            else if (rating >= 60 && rating < 80) {
                rate.setImageDrawable(draw[4]);
            }
            else if (rating >= 80 && rating < 100) {
                rate.setImageDrawable(draw[5]);
            }
            else if (rating >= 100) {
                rate.setImageDrawable(draw[6]);
            }
            else {
                rate.setImageDrawable(draw[0]);
            }*/
            if(rating == 20)
                rate.setImageDrawable(draw[1]);
            else if(rating == 30)
                rate.setImageDrawable(draw[2]);
            else if(rating == 40)
                rate.setImageDrawable(draw[3]);
            else if(rating == 50)
                rate.setImageDrawable(draw[4]);
            else if(rating == 60)
                rate.setImageDrawable(draw[6]);
            else if(rating == 70)
                rate.setImageDrawable(draw[5]);
            else
                rate.setImageDrawable(draw[0]);

            geo.setImageDrawable(marker);

            tv.setText(ss1);
            tv.setTag(isso.CIsso);

            //* Переходим на добавление рейтинга */
            final TextView finalTv = tv;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), IssoViewActivity.class);
                    intent.putExtra("C_ISSO", finalTv.getTag().toString());
                    startActivity(intent);
                }
            });
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    /** Рисуем маркеры на карте */
    public void setupMyGeolocation (Location location, HttpsIsso[] listOfIssos, ArrayVector hashMap) {
        this.location = location;
        if(mMap != null) {
            mMap.clear();
        }
        LatLng itemFarAhead, itemNearAhead, itemBehind;
        float density = getResources().getDisplayMetrics().density;
        if( PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Theme", "0").equals("0")) {
            if(hashMap.getIndex(0) != -1) {
                double lat = listOfIssos[hashMap.getIndex(0)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(0)].Longitude;
                itemFarAhead = new LatLng(lat, lng);
                pointAhead = mMap.addMarker(new MarkerOptions().position(itemFarAhead)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_ahead_dark))
                        .title(listOfIssos[hashMap.getIndex(0)].FullName));
            }
            if(hashMap.getIndex(1) != -1) {
                double lat = listOfIssos[hashMap.getIndex(1)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(1)].Longitude;
                itemNearAhead = new LatLng(lat, lng);
                Marker pointAfter = mMap.addMarker(new MarkerOptions().position(itemNearAhead)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_after_dark))
                        .title(listOfIssos[hashMap.getIndex(1)].FullName));
            }
            if(hashMap.getIndex(2) != -1) {
                double lat = listOfIssos[hashMap.getIndex(2)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(2)].Longitude;
                itemBehind = new LatLng(lat, lng);
                Marker pointBehind = mMap.addMarker(new MarkerOptions().position(itemBehind)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_behind_dark))
                        .title(listOfIssos[hashMap.getIndex(2)].FullName));
            }
        }
        else {
            if(hashMap.getIndex(0) != -1) {
                double lat = listOfIssos[hashMap.getIndex(0)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(0)].Longitude;
                itemFarAhead = new LatLng(lat, lng);
                pointAhead = mMap.addMarker(new MarkerOptions().position(itemFarAhead)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_ahead_light))
                        .title(listOfIssos[hashMap.getIndex(0)].FullName));
            }
            if(hashMap.getIndex(1) != -1) {
                double lat = listOfIssos[hashMap.getIndex(1)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(1)].Longitude;
                itemNearAhead = new LatLng(lat, lng);
                Marker pointAfter = mMap.addMarker(new MarkerOptions().position(itemNearAhead)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_after_light))
                        .title(listOfIssos[hashMap.getIndex(1)].FullName));
            }
            if(hashMap.getIndex(2) != -1) {
                double lat = listOfIssos[hashMap.getIndex(2)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(2)].Longitude;
                itemBehind = new LatLng(lat, lng);
                Marker pointBehind = mMap.addMarker(new MarkerOptions().position(itemBehind)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_behind_light))
                        .title(listOfIssos[hashMap.getIndex(2)].FullName));
            }
        }
        if(isFollowed)
            FollowByObjects(location);
    }

    /** Слежение  */
    public boolean FollowByObjects(Location location) {
        if(pointAhead != null) {
            if(!is500Left) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(pointAhead.getPosition());
                Marker myPosition = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .visible(false));
                builder.include(myPosition.getPosition());
                LatLngBounds bounds = builder.build();
                int padding = 80;
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                mMap.animateCamera(cu);
            }
            else {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                // Show the current location in Google Map
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
            return true;
        }
        else {
            return false;
        }
    }

    public TableRow getNearObject() {
        return (TableRow) findViewById(R.id.trNear);
    }

    //public void setTVText(String x) { ((TextView) findViewById(R.id.tvFindLocation)).setText(x);}

    //public String getTVText() { return ((TextView) findViewById(R.id.tvFindLocation)).getText().toString();}

    public void setTextForTables (ArrayVector hashMap, HttpsIsso[] listOfIssos, Drawable[] draw) {
        if(hashMap.getIndex(0) != -1)
            tvNearDistance.setText(String.format("%.0f м", hashMap.getDistance(0)));
        else
            tvNearDistance.setText("");
        if(hashMap.getIndex(1) != -1)
            tvFarDistance.setText(String.format("%.0f м", hashMap.getDistance(1)));
        else
            tvFarDistance.setText("");
        if(hashMap.getIndex(2) != -1)
            tvBehindDistance.setText(String.format("%.0f м", hashMap.getDistance(2)));
        else
            tvBehindDistance.setText("");

        if(hashMap.getIndex(0) != -1) {
            if(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Theme", "0").equals("0"))
                setTheRest(listOfIssos[hashMap.getIndex(0)], tvNear, imgNearRating, imgGeoNear, listOfIssos[hashMap.getIndex(0)].ExpertRatingNumeric, draw,
                        !is500Left ? getResources().getDrawable(R.drawable.marker_ahead_dark) :
                                getResources().getDrawable(R.drawable.marker_near_dark));
            else
                setTheRest(listOfIssos[hashMap.getIndex(0)], tvNear, imgNearRating, imgGeoNear, listOfIssos[hashMap.getIndex(0)].ExpertRatingNumeric, draw,
                        !is500Left ? getResources().getDrawable(R.drawable.marker_ahead_light) :
                                getResources().getDrawable(R.drawable.marker_near_light));
        }
        else {
            tvNear.setText("Впереди нет ближайшего объекта.");
            imgNearRating.setImageResource(android.R.color.transparent);
            imgGeoNear.setImageResource(android.R.color.transparent);
            tvNear.setOnClickListener(null);
        }
        if(hashMap.getIndex(1) != -1) {
            if(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Theme", "0").equals("0"))
                setTheRest(listOfIssos[hashMap.getIndex(1)], tvFar, imgFarRating, imgGeoFar, listOfIssos[hashMap.getIndex(1)].ExpertRatingNumeric, draw,
                        getResources().getDrawable(R.drawable.marker_after_dark));
            else
                setTheRest(listOfIssos[hashMap.getIndex(1)], tvFar, imgFarRating, imgGeoFar, listOfIssos[hashMap.getIndex(1)].ExpertRatingNumeric, draw,
                        getResources().getDrawable(R.drawable.marker_after_light));
        }
        else {
            tvFar.setText("Впереди нет следующего за ближайшим объекта.");
            imgFarRating.setImageResource(android.R.color.transparent);
            imgGeoFar.setImageResource(android.R.color.transparent);
            tvFar.setOnClickListener(null);
        }
        if(hashMap.getIndex(2) != -1) {
            if(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Theme", "0").equals("0"))
                setTheRest(listOfIssos[hashMap.getIndex(2)], tvBehind, imgBehindRating, imgGeoBehind, listOfIssos[hashMap.getIndex(2)].ExpertRatingNumeric, draw,
                        getResources().getDrawable(R.drawable.marker_behind_dark));
            else
                setTheRest(listOfIssos[hashMap.getIndex(2)], tvBehind, imgBehindRating, imgGeoBehind, listOfIssos[hashMap.getIndex(2)].ExpertRatingNumeric, draw,
                        getResources().getDrawable(R.drawable.marker_behind_light));
        }
        else {
            tvBehind.setText("Позади нет ближайшего объекта.");
            imgBehindRating.setImageResource(android.R.color.transparent);
            imgGeoBehind.setImageResource(android.R.color.transparent);
            tvBehind.setOnClickListener(null);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tag", "Resumed");
        //Переход в сост-ие onResume, слежение за gps
        //stopped = false;
        MapsActivity.resumeGPS(myListener);

        if(wasCalled) {
            // Включение слежения за gps
            isGPSEnabled = MainActivity.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled) {
                MainActivity.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
                        myListener);
            } else {
                showGPSAlert();
            }
            wasCalled = false;
        }
    }

    @Override
    public void onBackPressed () {
        finish();
    }

    @Override
    public void onPause() {
        //на паузе убираем слежение за gps, выставляем соотв. флаги
        super.onPause();
        Log.d("Tag", "Paused");
        wasCalled = true;
        if(myListener != null) {
            killGPS(myListener);
        }
    }

    @Override
    public void onStop () {
        //на паузе убираем слежение за gps, выставляем соотв. флаги
        super.onStop();
        Log.d("Tag", "Stopped");
        wasCalled = true;
        if(myListener != null) {
            killGPS(myListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    int[] prevIssos = new int[3];
    private LocationListener myListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location mylocation) {
            location = mylocation;
            drawMarkersNStaff();
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onProviderEnabled(String provider) {
            MainActivity.locationManager.getLastKnownLocation(provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    public void drawMarkersNStaff() {
        //при получении новых координат и наличии предыдущих координат проделываем всякие действия
        if(prevLocation != null && prevLocation.getLatitude() != location.getLatitude() &&
                prevLocation.getLongitude() != location.getLongitude()) {
            // при точность менее 50м и скорости <5 км/ч
            if(location.getAccuracy() <= 50.0 && (!wasMarked || (location.getSpeed() * 3.6) > 2.9)) {
                if(onlyOnce) {
                    onlyOnce = false;
                    TableLayout.LayoutParams paramsTable = new TableLayout.LayoutParams(0, TableLayout.LayoutParams.MATCH_PARENT, 1.0f);
                    LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.0f);
                    findViewById(R.id.tableLayout1).setLayoutParams(paramsTable);
                    findViewById(R.id.relLayoutFindLoc).setLayoutParams(paramsImage);
                }
                // получаем списко ИССО поблизости
                int[] issos = refreshList(prevLocation, location);
                // дистанция до выбранных объектов
                hashMap = getDistanceToObjects(issos, location, listOfIssos);
                // Анимация для приближения к объекту
                if(hashMap.getDistance(0) < 500 && !is500Left) {
                    is500Left = true;
                    if(isArrived)
                        isArrived = false;
                    // Здесь синтезатор речи говорит о приближении к объекту
                    if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getBoolean("Voice", true)) {
                            /*TextToSpeech t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                }
                            });
                            t1.setLanguage(new Locale("ru"));*/
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            String utteranceId = this.hashCode() + "";
                            t1.speak("Через 500 метров вы достигните ближайшего объекта", TextToSpeech.QUEUE_FLUSH, null, utteranceId);
                        } else {
                            HashMap<String, String> map = new HashMap<>();
                            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
                            t1.speak("Через 500 метров вы достигните ближайшего объекта", TextToSpeech.QUEUE_FLUSH, map);
                        }
                    }
                    final int[] count = {0};
                    AnimationSet c1 =(AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flash);
                    c1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if(count[0] < 2) {
                                AnimationSet c = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flash);
                                c.setAnimationListener(this);
                                getNearObject().startAnimation(c);
                                count[0]++;
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    getNearObject().startAnimation(c1);
                }
                else if(hashMap.getDistance(0) > 500 && is500Left)
                    is500Left = false;
                if(hashMap.getDistance(0) <= 20.f && !isArrived) {
                    isArrived = true;
                    if(PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getBoolean("Voice", true)) {
                            /*TextToSpeech t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                    t1.setLanguage(new Locale("ru"));
                                }
                            });*/
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            String utteranceId = this.hashCode() + "";
                            t1.speak("Вы достигли ближайшего объекта!", TextToSpeech.QUEUE_FLUSH, null, utteranceId);
                        } else {
                            HashMap<String, String> map = new HashMap<>();
                            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
                            t1.speak("Вы достигли ближайшего объекта!", TextToSpeech.QUEUE_FLUSH, map);
                        }
                    }
                }
                //заполнение таблиц
                setTextForTables(hashMap, listOfIssos, draw);
                // Create a LatLng object for the current location
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            // Show the current location in Google Map
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new
                                    LatLng(location.getLatitude() - 0.015, location.getLongitude() - 0.015),
                                    new LatLng(location.getLatitude() + 0.015, location.getLongitude() + 0.015)), 0);
                            mMap.animateCamera(cameraUpdate);
                            setupMyGeolocation(location, listOfIssos, hashMap);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (!btnFollow.isClickable()) {
                    btnFollow.setClickable(true);
                    if (PreferenceManager.getDefaultSharedPreferences(MapsActivity.this).getString("Theme", "0").equals("0")) {
                        btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_light));
                    } else {
                        btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_dark));
                    }
                }
                //}
                prevLocation = location;
                wasMarked = true;
            }
            else if(hashMap != null) {
                setTextForTables(hashMap, listOfIssos, draw);
                setTextForTables(hashMap, listOfIssos, draw);
            }
        }
        else if(location.getAccuracy() <= 50.0){
            prevLocation = location;
        }
    }

    /** Получение значения расстояния до объектов */
    public ArrayVector getDistanceToObjects(int[] issos, Location location, HttpsIsso[] listOfIssos) {
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            distances.add((double) 0);
            indexes.add(-1);
        }
        for( int i = 0; i < listOfIssos.length; i++ ) {
            if(listOfIssos[i].CIsso == issos[0]) {
                if(location != null) {
                    double dist = getDistance(listOfIssos[i], location);
                    distances.set(0, dist);
                    indexes.set(0, i);
                }
            }
            else if (listOfIssos[i].CIsso == issos[1]) {
                if(location != null) {
                    double dist = getDistance(listOfIssos[i], location);
                    distances.set(1, dist);
                    indexes.set(1, i);
                }
            }
            else if (listOfIssos[i].CIsso == issos[2]) {
                if(location != null) {
                    double dist = getDistance(listOfIssos[i], location);
                    distances.set(2, dist);
                    indexes.set(2, i);
                }
            }
        }
        ArrayVector hashMap = new ArrayVector();
        hashMap.distances = distances;
        hashMap.indexes = indexes;
        return hashMap;
    }

    /** расчет через полярные координаты */
    public double getDistance(HttpsIsso isso, Location current) {
        double len = Double.parseDouble(isso.Length);
        double lat1 = Math.toRadians(isso.Latitude);
        double lat2 = Math.toRadians(current.getLatitude());
        double lng1 = Math.toRadians(isso.Longitude);
        double lng2 = Math.toRadians(current.getLongitude());

        double x = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1);
        double y = Math.sqrt(Math.pow(Math.cos(lat2) * Math.sin(lng2 - lng1), 2.0) + Math.pow(Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1), 2.0));

        double dist = Math.atan(y / x) * rad;
        return Math.max(dist - len / 2.0, 0);
    }




    public void showGPSAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Включить GPS");

        alertDialog.setMessage("Невозможно определить местоположение. Перейдите в меню 'Настройки'" +
                " и включите GPS.");

        alertDialog.setPositiveButton("Включить GPS", new DialogInterface.OnClickListener() {
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
                finish();
            }
        });

        alertDialog.show();
    }

    // Список ИССО поблизости
    private int[] refreshList(Location previous, Location current) {

        if ( dists.isEmpty() ) {
            for (HttpsIsso listOfIsso : listOfIssos) {
                double lat = Math.toRadians(listOfIsso.Latitude);
                double lng = Math.toRadians(listOfIsso.Longitude);
                dists.put(listOfIsso.CIsso, new Vector2D(lat, lng));
            }
        }

        Vector2D vPos = null;
        Vector2D vPrev = null;

        if ( current != null && current.getAccuracy() <= 50.0f && previous != null && previous.getAccuracy() <= 50.0f ) {
            double lat = Math.toRadians(current.getLatitude());
            double lng = Math.toRadians(current.getLongitude());
            vPos = new Vector2D(lat, lng);
            lat = Math.toRadians(previous.getLatitude());
            lng = Math.toRadians(previous.getLongitude());
            vPrev = new Vector2D(lat, lng);
        }

        int cIsso1 = -1, cIsso2 = -1, cIsso3 = -1;
        double d1 = Double.MAX_VALUE, d2 = Double.MAX_VALUE, d3 = Double.MAX_VALUE;
        if ( vPos != null ) {
            for (HttpsIsso listOfIsso : listOfIssos) {
                Vector2D d = dists.get(listOfIsso.CIsso);
                double ind = vPos.minus(vPrev).dotProduct(d.minus(vPrev));
                Log.d("Tag", "result angle = " + ind);

                double x = rad * Math.cos(vPos.x) * Math.cos(vPos.y);
                double y = rad * Math.sin(vPos.x) * Math.cos(vPos.y);

                d = dists.get(listOfIsso.CIsso);
                double posX = rad * Math.cos(d.x) * Math.cos(d.y);
                double posY = rad * Math.sin(d.x) * Math.cos(d.y);

                double dist = Math.sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y));
                double len = Double.parseDouble(listOfIsso.Length);
                if ((dist < d1) && ind > 0.0) {
                    d2 = d1;
                    d1 = dist;
                    cIsso2 = cIsso1;
                    cIsso1 = listOfIsso.CIsso;
                }
                if ((dist < d2 && dist > d1) && ind > 0.0) {
                    d2 = dist;
                    cIsso2 = listOfIsso.CIsso;
                }
                if ((dist < d3) && ind < 0.0) {
                    d3 = dist;
                    cIsso3 = listOfIsso.CIsso;
                }
            }
        }
        return new int[] {cIsso1, cIsso2, cIsso3};
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }
}


package com.development.aisisso.autoselect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Autoselect extends AppCompatActivity implements OnMapReadyCallback{

    private boolean wasCalled = false;                              //переменная для перехода в настройки
    private Location prevLocation = null;                           //предыдущая локация
    private static HttpsIsso[] listOfIssos;                         //весь список сооружений
    private static int[] rating;                                    //список рейтингов
    private static Drawable[] draw;                                 //список картинок
    public static IssoActivity issoActivity;                       //класс для вызова списка рейтингов/работ/...
    private static LocationManager locationManager;                 //менеджер для локации
    //private TablesFragment tablesFragment;                          //класс для списка
    //private MapsFragment mapsFragment;                              //класс для карт
    //private Maps_Tables_Fragment mapsTablesFragment;                //класс для карт и списка
    private HashMap<Integer, Vector2D> dists = new HashMap<>();     //расстояния до рассматриваемых объектов
    //private List<Fragment> FragmentsList;                           //список фрагментов
    //private ViewPagerAdapter adapter;                               //адаптер для хранения фрагментов
    //private boolean stopped = false;                                //флаг для перехода в onPause
    private boolean firstTime = true;                               //флаг для остановки обновления в textview
    private boolean isGPSEnabled = false;                           //наличие gps
    //private boolean withoutMaps;                                    //наличие карт
    private boolean wasMarked = false;                              //
    private ArrayVector hashMap;                                    //
    static final double rad = 6371200;                              //Радиус Земли
    private FloatingActionButton btnFollow;                         //Кнопка слежения по карте
    public static boolean isFollowed = false;
    private boolean is500Left = false;
    private TextView tvFar;
    private TextView tvFarDistance;
    private TextView tvNear;
    private TextView tvNearDistance;
    private TextView tvBehind;
    private TextView tvBehindDistance;
    private ImageView imgGeoFar;
    private ImageView imgGeoNear;
    private ImageView imgGeoBehind;
    private ImageView imgFarRating;
    private ImageView imgNearRating;
    private ImageView imgBehindRating;
    //private MapController mapController;
    //private Overlay overlay = null;
    private Location location;
    //private GeoPoint pointBehind;
    //private GeoPoint pointAhead;
    private boolean onlyOnce = true;


    public Autoselect() {
        super();
    }

    /** конструктор */
    public Autoselect(HttpsIsso[] listOfIssos, LocationManager locationManager, int[] rating, Drawable[] draw, IssoActivity issoActivity) {
        super();
        Autoselect.listOfIssos = listOfIssos;
        Autoselect.rating = rating;
        Autoselect.draw = draw;
        Autoselect.issoActivity = issoActivity;
        Autoselect.locationManager = locationManager;
    }

    /** Возобновление получения gps через каждые 1000 мс;*/
    public static void resumeGPS(LocationListener listen) {
        //if(ActivityMonitor.active != 1) return;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.0f, listen);
    }

    /** Отколючение слежения за gps`*/
    public static void killGPS(LocationListener listen) {
        //if(ActivityMonitor.active == 0)
        if(listen != null) {
            locationManager.removeUpdates(listen);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Выбор темы в соответствии с preference
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);
        setContentView(R.layout.autoselect_layout);
        //Выключение отключения экрана
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout l = (LinearLayout) findViewById(R.id.mainLayoutAuto);
            LinearLayout.LayoutParams paramsAutoselect = (LinearLayout.LayoutParams) (findViewById(R.id.layoutAutoselect)).getLayoutParams();
            LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) (findViewById(R.id.mapView)).getLayoutParams();
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
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Spinner spinner_nav = (Spinner) findViewById(R.id.spinner_nav);
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.linLayoutAutoselect);
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
            linearLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
        //Подключение action bar
        //setSupportActionBar(toolbar);
        //withoutMaps = getIntent().getBooleanExtra("withoutMaps", false);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Получение информации о наличии gps
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSEnabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
                    myListener);
        }
        else {
            showGPSAlert();
        }
        //MapView map = (MapView) findViewById(R.id.mapView);
        //mapsFragment = () getFragmentManager().findFragmentById(R.id.mapView);
        //mapController = map.getMapController();
        //mapController.getOverlayManager().getMyLocation().setEnabled(false);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

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
            //map.getMapController().setNightMode(true);
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
            //map.getMapController().setNightMode(false);
        }
        tvFar.setText("Получение координат .");
        tvFarDistance.setText(".");
        tvNear.setText("Получение координат .");
        tvNearDistance.setText(".");
        tvBehind.setText("Получение координат .");
        tvBehindDistance.setText(".");
        imgNearRating = (ImageView) findViewById(R.id.imgNearRating);
        imgFarRating = (ImageView) findViewById(R.id.imgFarRating);
        imgBehindRating = (ImageView) findViewById(R.id.imgBehindRating);
        imgGeoNear = (ImageView) findViewById(R.id.imgGeoNear);
        imgGeoFar = (ImageView) findViewById(R.id.imgGeoFar);
        imgGeoBehind = (ImageView) findViewById(R.id.imgGeoBehind);


        //Инициализация фрагментов
        //FragmentsList = new ArrayList<>();
        //final NonSwipeableViewPager viewPager = (NonSwipeableViewPager) findViewById(R.id.tabanim_viewpager);
        //ArrayList<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.auto_values)));
        // При отсутствии yandex maps не инициализируются 2 фрагмента
        /*if(withoutMaps) {
            list.remove(1);list.remove(1);
        }*/

        // Если телефон менял ориенитацию, то фрагментам присваиваются сериализуемые классы
        /*if (savedInstanceState != null) {
            FragmentsList = (List<Fragment>) savedInstanceState.getSerializable("FragmentsList");
            tablesFragment = (TablesFragment) savedInstanceState.getSerializable("TablesClass");
            if(!withoutMaps) {
                mapsTablesFragment = (Maps_Tables_Fragment) savedInstanceState.getSerializable("MapsTablesClass");
                mapsFragment = (MapsFragment) savedInstanceState.getSerializable("MapsClass");
            }
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentsList, list);
            if (FragmentsList.size() > 0) {
                adapter.importList(FragmentsList);
            }
        } else {
            tablesFragment = new TablesFragment();
            mapsFragment = new MapsFragment();
            //FragmentsList.add(tablesFragment);
            //if(!withoutMaps) {
                //mapsTablesFragment = new Maps_Tables_Fragment();
                //FragmentsList.add(mapsTablesFragment);
                //FragmentsList.add(mapsFragment);
            //}
            //adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentsList, list);
        }

        //Подключение адаптера фрагментов
        //viewPager.setAdapter(adapter);
        //viewPager.setOffscreenPageLimit(3);
        //viewPager.setCurrentItem(0);viewPager.setCurrentItem(1);viewPager.setCurrentItem(2);viewPager.setCurrentItem(0);*/

        FloatingActionButton ListOnly, ListNMap, MapOnly;
        final FloatingActionMenu Menu;
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            (findViewById(R.id.fabMenu_light)).setVisibility(View.GONE);
            Menu = (FloatingActionMenu) findViewById(R.id.fabMenu_dark);
            ListOnly = (FloatingActionButton) findViewById(R.id.fabListDark);
            ListNMap = (FloatingActionButton) findViewById(R.id.fabListNMapDark);
            MapOnly = (FloatingActionButton) findViewById(R.id.fabMapDark);
            btnFollow = (FloatingActionButton) findViewById(R.id.fabFollowDark);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.no_location_light));
        }
        else {
            (findViewById(R.id.fabMenu_dark)).setVisibility(View.GONE);
            Menu = (FloatingActionMenu) findViewById(R.id.fabMenu_light);
            ListOnly = (FloatingActionButton) findViewById(R.id.fabListLight);
            ListNMap = (FloatingActionButton) findViewById(R.id.fabListNMapLight);
            MapOnly = (FloatingActionButton) findViewById(R.id.fabMapLight);
            btnFollow = (FloatingActionButton) findViewById(R.id.fabFollowLight);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.no_location_dark));
        }
        /*if(withoutMaps) {
            MapOnly.setVisibility(View.GONE);
            ListNMap.setVisibility(View.GONE);
            btnFollow.setVisibility(View.GONE);
        }
        else */
        ((TextView)findViewById(R.id.tvFindLocation)).setText(".");
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFollowed) {
                    isFollowed = true;
                    if (/*FollowByObjects()*/ true) {
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
        Menu.setClosedOnTouchOutside(true);
        ListOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams paramsTable, paramsMap;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    paramsTable = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.0f);
                }
                else {
                    paramsTable = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 0.0f);
                }
                (findViewById(R.id.layoutAutoselect)).setLayoutParams(paramsTable);
                (findViewById(R.id.mapView)).setLayoutParams(paramsMap);
                Menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(Autoselect.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.list_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.list_dark);
            }
        });
        ListNMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams paramsTable, paramsMap;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    paramsTable = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.0f);
                    paramsMap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                }
                else {

                    paramsTable = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 0.0f);
                    paramsMap = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                }
                (findViewById(R.id.layoutAutoselect)).setLayoutParams(paramsTable);
                (findViewById(R.id.mapView)).setLayoutParams(paramsMap);
                Menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(Autoselect.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.map_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.map_dark);
            }
        });
        MapOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams paramsTable, paramsMap;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    paramsTable = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
                }
                else {
                    paramsTable = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                    paramsMap = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                }
                (findViewById(R.id.layoutAutoselect)).setLayoutParams(paramsTable);
                (findViewById(R.id.mapView)).setLayoutParams(paramsMap);
                Menu.close(true);
                if(PreferenceManager.getDefaultSharedPreferences(Autoselect.this).getString("Theme", "0").equals("0"))
                    Menu.getMenuIconView().setImageResource(R.drawable.map_list_light);
                else
                    Menu.getMenuIconView().setImageResource(R.drawable.map_list_dark);
            }
        });
    }

    //Вызывается при повороте экрана
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        if( !stopped) {
            FragmentsList = adapter.exportList();
            outState.putSerializable("FragmentsList", (Serializable) FragmentsList);
            outState.putSerializable("TablesClass", tablesFragment);
            if(!withoutMaps) {
                outState.putSerializable("MapsTablesClass", mapsTablesFragment);
                outState.putSerializable("MapsClass", mapsFragment);
            }
            super.onSaveInstanceState(outState);
        }
    }*/

    /*public void setupMyGeolocation (Location location, HttpsIsso[] listOfIssos, ArrayVector hashMap) {
        this.location = location;
        if(overlay != null) {
            mapController.getOverlayManager().removeOverlay(overlay);
        }
        OverlayManager overlayManager = mapController.getOverlayManager();
        Log.d("Tag", mapController.getOverlayManager().getOverlays().size() + "");
        Overlay overlay = new Overlay(mapController);
        OverlayItem itemFarAhead, itemNearAhead, itemBehind;
        float density = getResources().getDisplayMetrics().density;
        if( PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Theme", "0").equals("0")) {
            if(hashMap.getIndex(0) != -1) {
                double lat = listOfIssos[hashMap.getIndex(0)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(0)].Longitude;
                itemFarAhead = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_ahead_dark));
                BalloonItem balloonFar = new BalloonItem(getApplicationContext(), itemFarAhead.getGeoPoint());
                balloonFar.setText(listOfIssos[hashMap.getIndex(0)].FullName);
                pointAhead = new GeoPoint(lat, lng);
                itemFarAhead.setBalloonItem(balloonFar);
                itemFarAhead.setOffsetX((int) (-7 * density));
                itemFarAhead.setOffsetY((int) (20 * density));
                overlay.addOverlayItem(itemFarAhead);
            }
            if(hashMap.getIndex(1) != -1) {
                double lat = listOfIssos[hashMap.getIndex(1)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(1)].Longitude;
                itemNearAhead = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_after_dark));
                BalloonItem balloonNear = new BalloonItem(getApplicationContext(), itemNearAhead.getGeoPoint());
                balloonNear.setText(listOfIssos[hashMap.getIndex(1)].FullName);
                itemNearAhead.setBalloonItem(balloonNear);
                itemNearAhead.setOffsetX((int) (-7 * density));
                itemNearAhead.setOffsetY((int) (20 * density));
                overlay.addOverlayItem(itemNearAhead);
            }
            if(hashMap.getIndex(2) != -1) {
                double lat = listOfIssos[hashMap.getIndex(2)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(2)].Longitude;
                itemBehind = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_behind_dark));
                BalloonItem balloonBehind = new BalloonItem(getApplicationContext(), itemBehind.getGeoPoint());
                balloonBehind.setText(listOfIssos[hashMap.getIndex(2)].FullName);
                pointBehind = new GeoPoint(lat, lng);
                itemBehind.setBalloonItem(balloonBehind);
                itemBehind.setOffsetX((int) (-7 * density));
                itemBehind.setOffsetY((int) (20 * density));
                overlay.addOverlayItem(itemBehind);
            }
        }
        else {
            if(hashMap.getIndex(0) != -1) {
                double lat = listOfIssos[hashMap.getIndex(0)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(0)].Longitude;
                itemFarAhead = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_ahead_light));
                BalloonItem balloonFar = new BalloonItem(getApplicationContext(), itemFarAhead.getGeoPoint());
                balloonFar.setText(listOfIssos[hashMap.getIndex(0)].FullName);
                pointAhead = new GeoPoint(lat, lng);
                itemFarAhead.setBalloonItem(balloonFar);
                itemFarAhead.setOffsetX((int) (-7 * density));
                itemFarAhead.setOffsetY((int) (20 * density));
                overlay.addOverlayItem(itemFarAhead);
            }
            if(hashMap.getIndex(1) != -1) {
                double lat = listOfIssos[hashMap.getIndex(1)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(1)].Longitude;
                itemNearAhead = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_after_light));
                BalloonItem balloonNear = new BalloonItem(getApplicationContext(), itemNearAhead.getGeoPoint());
                balloonNear.setText(listOfIssos[hashMap.getIndex(1)].FullName);
                itemNearAhead.setBalloonItem(balloonNear);
                itemNearAhead.setOffsetX((int) (-7 * density));
                itemNearAhead.setOffsetY((int) (20 * density));
                overlay.addOverlayItem(itemNearAhead);
            }
            if(hashMap.getIndex(2) != -1) {
                double lat = listOfIssos[hashMap.getIndex(2)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(2)].Longitude;
                itemBehind = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_behind_light));
                BalloonItem balloonBehind = new BalloonItem(getApplicationContext(), itemBehind.getGeoPoint());
                balloonBehind.setText(listOfIssos[hashMap.getIndex(2)].FullName);
                pointBehind = new GeoPoint(lat, lng);
                itemBehind.setBalloonItem(balloonBehind);
                itemBehind.setOffsetX((int) (-7 * density));
                itemBehind.setOffsetY((int) (20 * density));
                overlay.addOverlayItem(itemBehind);
            }
        }
        overlayManager.addOverlay(overlay);
        //mapController.setPositionAnimationTo(new GeoPoint(location.getLatitude(), location.getLongitude()));
        if(Autoselect.isFollowed)
            FollowByObjects();
        this.overlay = overlay;
    }

    public boolean FollowByObjects() {
        List<OverlayItem> list = overlay.getOverlayItems();
        float zoom = mapController.getZoomCurrent();
        if(pointAhead != null ) {
            double lat1 = Math.toRadians(pointAhead.getLat());
            double lat2 = Math.toRadians(location.getLatitude());
            double lng1 = Math.toRadians(pointAhead.getLon());
            double lng2 = Math.toRadians(location.getLongitude());
            double x = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1);
            double y = Math.sqrt(Math.pow(Math.cos(lat2) * Math.sin(lng2 - lng1), 2.0) + Math.pow(Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1), 2.0));
            double dist = Math.atan(y / x) * Autoselect.rad;
            if (dist > 500) {
                double maxLat, minLat, maxLon, minLon;
                maxLat = maxLon = Double.MIN_VALUE;
                minLat = minLon = Double.MAX_VALUE;
                int dosvidos = 0;
                for (int i = 0; i < list.size(); i++) {
                    GeoPoint geoPoint = list.get(i).getGeoPoint();
                    if (pointBehind != null && !geoPoint.equals(pointBehind)) {
                        double lat = geoPoint.getLat();
                        double lon = geoPoint.getLon();
                        maxLat = Math.max(lat, maxLat);
                        minLat = Math.min(lat, minLat);
                        maxLon = Math.max(lon, maxLon);
                        minLon = Math.min(lon, minLon);
                        dosvidos++;
                    }
                }
                GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                maxLat = Math.max(geoPoint.getLat(), maxLat);
                minLat = Math.min(geoPoint.getLat(), minLat);
                maxLon = Math.max(geoPoint.getLon(), maxLon);
                minLon = Math.min(geoPoint.getLon(), minLon);

                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                if(dosvidos > 0) {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        mapController.setZoomToSpan((maxLat - minLat) + ((maxLat - minLat) * 2 / 10), maxLon - minLon + ((maxLon - minLon) * 2 / 10));
                    } else {
                        Rect rect = mapController.getViewport();
                        if (rect.height() > rect.width())
                            mapController.setViewport(0, 0, rect.height(), rect.width());
                        mapController.setZoomToSpan(maxLat - minLat + (maxLat - minLat) * 2 / 10, maxLon - minLon + (maxLon - minLon) * 2 / 10);
                        mapController.setZoomCurrent(mapController.getZoomCurrent() + 2);
                    }
                    mapController.setPositionAnimationTo(new GeoPoint((maxLat + minLat) / 2, (maxLon + minLon) / 2));
                }
            } else {
                mapController.setZoomCurrent(zoom);
                mapController.setPositionAnimationTo(new GeoPoint(location.getLatitude(), location.getLongitude()));
            }
            return true;
        }
        else {
            return false;
        }
    }*/

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        LinearLayout l = (LinearLayout) findViewById(R.id.mainLayoutAuto);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout.LayoutParams paramsAutoselect = (LinearLayout.LayoutParams) (findViewById(R.id.layoutAutoselect)).getLayoutParams();
            LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) (findViewById(R.id.mapView)).getLayoutParams();
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
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            LinearLayout.LayoutParams paramsAutoselect = (LinearLayout.LayoutParams) (findViewById(R.id.layoutAutoselect)).getLayoutParams();
            LinearLayout.LayoutParams paramsMap = (LinearLayout.LayoutParams) (findViewById(R.id.mapView)).getLayoutParams();
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

    public void setTheRest (HttpsIsso isso, TextView tv, ImageView rate, ImageView geo, int rating, Drawable[] draw, Drawable marker) {
        try {
            String str = String.format("%.3f", isso.WIsso);
            str = str.replaceAll(",", "+");
            str = str.replaceAll("\\.", "+");
            String ss1 = isso.Name + ": " + String.format("%s, км %s (%s)\n", isso.DorName, str, isso.Obstacle);
            switch (rating) {
                case 20:
                    rate.setImageDrawable(draw[1]);
                    break;
                case 30:
                    rate.setImageDrawable(draw[2]);
                    break;
                case 40:
                    rate.setImageDrawable(draw[3]);
                    break;
                case 50:
                    rate.setImageDrawable(draw[4]);
                    break;
                case 60:
                    rate.setImageDrawable(draw[5]);
                    break;
                case 70:
                    rate.setImageDrawable(draw[6]);
                    break;
                default:
                    rate.setImageDrawable(draw[0]);
                    break;
            }
            geo.setImageDrawable(marker);

            tv.setText(ss1);
            tv.setTag(isso.CIsso);

            final TextView finalTv = tv;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Autoselect.issoActivity.getClass());
                    intent.putExtra("C_ISSO", finalTv.getTag().toString());
                    startActivity(intent);
                }
            });
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    public TableRow getNearObject() {
        return (TableRow) findViewById(R.id.trNear);
    }

    /*public void setTVText(String x, String y) {
        this.tvFar.setText(x);
        this.tvFarDistance.setText(y);
        this.tvNear.setText(x);
        this.tvNearDistance.setText(y);
        this.tvBehind.setText(x);
        this.tvBehindDistance.setText(y);
    }*/

    public void setTVText(String x) { ((TextView) findViewById(R.id.tvFindLocation)).setText(x);}

    public String getTVText() { return ((TextView) findViewById(R.id.tvFindLocation)).getText().toString();}

    public void setTextForTables (ArrayVector hashMap, HttpsIsso[] listOfIssos, int[] rating, Drawable[] draw) {
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
                setTheRest(listOfIssos[hashMap.getIndex(0)], tvNear, imgNearRating, imgGeoNear, rating[hashMap.getIndex(0)], draw,
                        hashMap.getDistance(0) > 500 ? getResources().getDrawable(R.drawable.marker_ahead_dark) :
                                getResources().getDrawable(R.drawable.marker_near_dark));
            else
                setTheRest(listOfIssos[hashMap.getIndex(0)], tvNear, imgNearRating, imgGeoNear, rating[hashMap.getIndex(0)], draw,
                        hashMap.getDistance(0) > 500 ? getResources().getDrawable(R.drawable.marker_ahead_light) :
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
                setTheRest(listOfIssos[hashMap.getIndex(1)], tvFar, imgFarRating, imgGeoFar, rating[hashMap.getIndex(1)], draw,
                        getResources().getDrawable(R.drawable.marker_after_dark));
            else
                setTheRest(listOfIssos[hashMap.getIndex(1)], tvFar, imgFarRating, imgGeoFar, rating[hashMap.getIndex(1)], draw,
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
                setTheRest(listOfIssos[hashMap.getIndex(2)], tvBehind, imgBehindRating, imgGeoBehind, rating[hashMap.getIndex(2)], draw,
                        getResources().getDrawable(R.drawable.marker_behind_dark));
            else
                setTheRest(listOfIssos[hashMap.getIndex(2)], tvBehind, imgBehindRating, imgGeoBehind, rating[hashMap.getIndex(2)], draw,
                        getResources().getDrawable(R.drawable.marker_behind_light));
        }
        else {
            tvBehind.setText("Позади нет ближайшего объекта.");
            imgBehindRating.setImageResource(android.R.color.transparent);
            imgGeoBehind.setImageResource(android.R.color.transparent);
            tvBehind.setOnClickListener(null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tag", "Resumed");
        //Переход в сост-ие onResume, слежение за gps
        //stopped = false;
        Autoselect.resumeGPS(myListener);

        if(wasCalled) {
            // Включение слежения за gps
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
                        myListener);
            } else {
                showGPSAlert();
            }
            wasCalled = false;
        }
        //Получение координат, ока нет обновления - добавлять точки
        if(isGPSEnabled && firstTime) {
            try {
                final Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String str = getTVText();
                                switch (str) {
                                    case ".":
                                        setTVText("..");
                                        break;
                                    case "..":
                                        setTVText("...");
                                        break;
                                    case "...":
                                        setTVText(".");
                                        break;
                                    default:
                                        timer.cancel();
                                }
                            }
                        });
                    }
                };
                timer.schedule(timerTask, 1000, 1000);
                (findViewById(R.id.progressFindLocation)).animate();
            } catch (Exception e) {
                Toast.makeText(Autoselect.this, e.toString(), Toast.LENGTH_LONG).show();
            }
            firstTime = false;
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
        public void onLocationChanged(Location location) {
            //при получении новых координат и наличии предыдущих координат проделываем всякие действия
            if(prevLocation != null && prevLocation.getLatitude() != location.getLatitude() &&
                    prevLocation.getLongitude() != location.getLongitude()) {
                // при точность менее 50м и скорости <5 км/ч
                if(location.getAccuracy() <= 50.0 && (!wasMarked || (location.getSpeed() * 3.6) > 5)) {
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
                        final TextToSpeech mTTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int status) {
                            }
                        });
                        final int[] count = {0};
                        AnimationSet c1 =(AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.animator.flash);
                        c1.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if(count[0] < 2) {
                                    AnimationSet c = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.animator.flash);
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
                    //заполнение таблиц
                    setTextForTables(hashMap, listOfIssos, rating, draw);
                    //if(!withoutMaps) {
                    // заполнение таблиц и рисование маркеров
                    //mapsTablesFragment.setTextForTables(hashMap, listOfIssos, rating, draw);

                    //mapsTablesFragment.setupMyGeolocation(location, listOfIssos, hashMap);
                    //setupMyGeolocation(location, listOfIssos, hashMap);
                    if(!btnFollow.isClickable()) {
                        btnFollow.setClickable(true);
                        if(PreferenceManager.getDefaultSharedPreferences(Autoselect.this).getString("Theme", "0").equals("0")) {
                            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_light));
                        }
                        else {
                            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_dark));
                        }
                    }
                    //}
                    prevLocation = location;
                    wasMarked = true;
                }
                else if(hashMap != null) {
                    setTextForTables(hashMap, listOfIssos, rating, draw);
                    setTextForTables(hashMap, listOfIssos, rating, draw);
                }
            }
            else if(location.getAccuracy() <= 50.0){
                prevLocation = location;
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
            locationManager.getLastKnownLocation(provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };


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

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    /*public class CustomSpinnerAdapter extends ArrayAdapter<String> {

        private Context context1;
        private ArrayList<String> data;
        public Resources res;
        LayoutInflater inflater;

        public CustomSpinnerAdapter(Context context, ArrayList<String> objects) {
            super(context, R.layout.spinner_row, objects);

            context1 = context;
            data = objects;

            inflater = (LayoutInflater) context1
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        // This funtion called for each row ( Called data.size() times )
        public View getCustomView(int position, ViewGroup parent) {

            View row = inflater.inflate(R.layout.spinner_row, parent, false);

            TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);

            tvCategory.setText(data.get(position));
            if(!PreferenceManager.getDefaultSharedPreferences(Autoselect.this).getString("Theme", "0").equals("0") )
                tvCategory.setTextColor(getResources().getColor(R.color.abc_primary_text_material_light));

            return row;
        }
    }*/

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
        alertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private  List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager, List<Fragment> fragments, List<String> strings) {
        super(manager);
        this.mFragmentList = fragments;
        this.mFragmentTitleList = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public List<Fragment> exportList() {
        return mFragmentList;
    }
    public void importList(List<Fragment> savedPages) {
        mFragmentList = savedPages;
    }
}

class NonSwipeableViewPager extends ViewPager {

    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
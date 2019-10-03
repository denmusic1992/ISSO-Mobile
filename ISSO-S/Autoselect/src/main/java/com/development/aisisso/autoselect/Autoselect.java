package com.development.aisisso.autoselect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Autoselect extends AppCompatActivity {

    private boolean wasCalled = false;                              //переменная для перехода в настройки
    private Location prevLocation = null;                           //предыдущая локация
    private static HttpsIsso[] listOfIssos;                         //весь список сооружений
    private static int[] rating;                                    //список рейтингов
    private static Drawable[] draw;                                 //список картинок
    public static IssoActivity issoActivity;                       //класс для вызова списка рейтингов/работ/...
    private static LocationManager locationManager;                 //менеджер для локации
    private TablesFragment tablesFragment;                          //класс для списка
    private MapsFragment mapsFragment;                              //класс для карт
    private Maps_Tables_Fragment mapsTablesFragment;                //класс для карт и списка
    private HashMap<Integer, Vector2D> dists = new HashMap<>();     //расстояния до рассматриваемых объектов
    private NonSwipeableViewPager viewPager;                        //переменная отображения списков и карт
    private List<Fragment> FragmentsList;                           //список фрагментов
    private ViewPagerAdapter adapter;                               //адаптер для хранения фрагментов
    private boolean stopped = false;                                //флаг для перехода в onPause
    private boolean firstTime = true;                               //флаг для остановки обновления в textview
    private boolean isGPSEnabled = false;                           //наличие gps
    private boolean withoutMaps;                                    //наличие карт
    private boolean wasMarked = false;                              //
    private ArrayVector hashMap;                                    //
    static final double rad = 6371200;                              //Радиус Земли


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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Spinner spinner_nav = (Spinner) findViewById(R.id.spinner_nav);
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.linLayoutAutoselect);
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
            linearLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
        //Подключение action bar
        setSupportActionBar(toolbar);
        withoutMaps = getIntent().getBooleanExtra("withoutMaps", false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

        //Инициализация фрагментов
        FragmentsList = new ArrayList<>();
        viewPager = (NonSwipeableViewPager) findViewById(R.id.tabanim_viewpager);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.auto_values)));
        // При отсутствии yandex maps не инициализируются 2 фрагмента
        if(withoutMaps) {
            list.remove(1);list.remove(1);
        }

        // Если телефон менял ориенитацию, то фрагментам присваиваются сериализуемые классы
        if (savedInstanceState != null) {
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
            FragmentsList.add(tablesFragment);
            if(!withoutMaps) {
                mapsFragment = new MapsFragment();
                mapsTablesFragment = new Maps_Tables_Fragment();
                FragmentsList.add(mapsTablesFragment);
                FragmentsList.add(mapsFragment);
            }
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentsList, list);
        }

        //Подключение адаптера фрагментов
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(
                getApplicationContext(), list);

        //инициализация спиннера
        spinner_nav.setAdapter(spinAdapter);
        spinner_nav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    //Вызывается при повороте экрана
    @Override
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tag", "Resumed");
        stopped = false;
        Autoselect.resumeGPS(myListener);

        if(wasCalled) {
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
                        myListener);
            } else {
                showGPSAlert();
            }
            wasCalled = false;
        }
        if(isGPSEnabled && firstTime) {
            try {
                final Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String str = tablesFragment.getTVText();
                                switch (str) {
                                    case "Получение координат .":
                                        tablesFragment.setTVText("Получение координат ..", "..");
                                        break;
                                    case "Получение координат ..":
                                        tablesFragment.setTVText("Получение координат ...", "...");
                                        break;
                                    case "Получение координат ...":
                                        tablesFragment.setTVText("Получение координат .", ".");
                                        break;
                                    default:
                                        timer.cancel();
                                }
                            }
                        });
                    }
                };
                if(!withoutMaps) {
                    timer.schedule(timerTask, 1000, 1000);
                    final Timer timerMain = new Timer();
                    TimerTask timerTaskMain = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String str = mapsTablesFragment.getTVText();
                                    switch (str) {
                                        case "Получение координат .":
                                            mapsTablesFragment.setTVText("Получение координат ..", "..");
                                            break;
                                        case "Получение координат ..":
                                            mapsTablesFragment.setTVText("Получение координат ...", "...");
                                            break;
                                        case "Получение координат ...":
                                            mapsTablesFragment.setTVText("Получение координат .", ".");
                                            break;
                                        default:
                                            timer.cancel();
                                    }
                                }
                            });
                        }
                    };
                    timerMain.schedule(timerTaskMain, 1000, 1000);
                }
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
        super.onPause();
        Log.d("Tag", "Paused");
        stopped = true;
        wasCalled = true;
        if(myListener != null) {
            killGPS(myListener);
        }
    }

    @Override
    public void onStop () {
        super.onStop();
        Log.d("Tag", "Stopped");
        stopped = false;
        wasCalled = true;
        if(myListener != null) {
            killGPS(myListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private LocationListener myListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if(prevLocation != null) {
                if(location.getAccuracy() <= 50.0 && (!wasMarked || (location.getSpeed() * 3.6) > 5)) {
                    int[] issos = refreshList(prevLocation, location);
                    /*cr = new DBHelper(Autoselect.this).getReadableDatabase().rawQuery("SELECT I_ISSO.C_ISSO, NAME, FULLNAME, DORNAME, W_ISSO, coalesce(RATING, 0) as C_RATING, LATITUDE, LONGITUDE, LENGTH, OBSTACLE from I_ISSO " +
                            "left outer join RATING on (RATING.C_ISSO = I_ISSO.C_ISSO and RATING.RATINGDATE = (select MAX(RATINGDATE) from RATING where C_ISSO = I_ISSO.C_ISSO)) " +
                            String.format(" where I_ISSO.C_ISSO in(%d, %d, %d)", issos[0], issos[1], issos[2]), null);*/
                    hashMap = getDistanceToObjects(issos, location, listOfIssos);
                    tablesFragment.setTextForTables(hashMap, listOfIssos, rating, draw);
                    if(!withoutMaps) {
                        mapsTablesFragment.setTextForTables(hashMap, listOfIssos, rating, draw);
                        mapsTablesFragment.setupMyGeolocation(location, listOfIssos, hashMap);
                        mapsFragment.setupMyGeolocation(location, listOfIssos, hashMap);
                    }
                    prevLocation = location;
                    wasMarked = true;
                }
                else if(hashMap != null) {
                    tablesFragment.setTextForTables(hashMap, listOfIssos, rating, draw);
                    if(!withoutMaps) {
                        mapsTablesFragment.setTextForTables(hashMap, listOfIssos, rating, draw);
                    }
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

    public class CustomSpinnerAdapter extends ArrayAdapter<String> {

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
        alertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

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

package com.development.aisisso.isso_i;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;


public class ViewIssoGeolocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean once = false;
    private Marker pointAhead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
            setTheme(R.style.DialogTheme);
        else
            setTheme(R.style.DialogThemeLight);
        setContentView(R.layout.view_isso_geolocation);
        if (!MainActivity.theme.equals("0")) {
            findViewById(R.id.linLayoutGeoMain).setBackgroundColor(getResources().getColor(R.color.background_material_light));
            findViewById(R.id.btn_closeGeolocation).setBackground(getDrawable(R.drawable.cancel_light));
        }
        else {
            findViewById(R.id.btn_closeGeolocation).setBackground(getDrawable(R.drawable.cancel_dark));
        }

        findViewById(R.id.btn_closeGeolocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                hand.sendEmptyMessage(0);
            }
        };

        Timer tm = new Timer();
        tm.schedule(task, 0, 1000);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_layout);
        mapFragment.getMapAsync(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        findViewById(R.id.fragment_layout).getLayoutParams().height = displaymetrics.heightPixels * 2 / 3;
        findViewById(R.id.fragment_layout).getLayoutParams().width = displaymetrics.widthPixels * 2 / 3;
    }

    public LocationListener listen = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if(!once) {
                setLocation(location);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
            MainActivity.locationManager.getLastKnownLocation(provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    public void setLocation(Location location) {
        if(location != null) {
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
            once = true;
        }
    }

    void refreshStatus() {
        Location currentLocation = MainActivity.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double dist = 0;
        if(currentLocation != null) {
            dist = getDistance(currentLocation, ViewInfoActivity.latitude, ViewInfoActivity.longitude);
        }
        ((TextView) findViewById(R.id.tvMainInfoGeolocation)).setText(String.format("Расстояние до объекта, м:\b %s\nПогрешность GPS, м:\b %s",
                currentLocation == null ? "[недоступно]" : String.format("%.1f", dist),
                currentLocation == null ? "[недоступно]" : String.format("%.1f", currentLocation.getAccuracy())));
    }

    public double getDistance (Location location, double latitude, double longitude) {
        double lat1 = Math.toRadians(location.getLatitude());
        double lng1 = Math.toRadians(location.getLongitude());


        double lat2 = Math.toRadians(latitude);
        double lng2 = Math.toRadians(longitude);

        double x = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1);
        double y = Math.sqrt(Math.pow(Math.cos(lat2) * Math.sin(lng2 - lng1), 2.0) + Math.pow(Math.cos(lat1) *
                Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1), 2.0));

        double dist = Math.atan(y / x) * MainActivity.rad;
        return Math.max(dist - ViewInfoActivity.length / 2.0, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.resumeGPS(listen);
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity.killGPS(listen);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.killGPS(listen);
    }

    android.os.Handler hand = new android.os.Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            refreshStatus();
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(ViewInfoActivity.latitude, ViewInfoActivity.longitude);
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new
                        LatLng(ViewInfoActivity.latitude - 0.05, ViewInfoActivity.longitude - 0.05),
                        new LatLng(ViewInfoActivity.latitude + 0.05, ViewInfoActivity.longitude + 0.05)), 0);
                mMap.animateCamera(cameraUpdate);
            }
        });
        LatLng itemAhead = new LatLng(ViewInfoActivity.latitude, ViewInfoActivity.longitude);
        pointAhead = mMap.addMarker(new MarkerOptions().position(itemAhead)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title(ViewInfoActivity.FullName));
        setLocation(MainActivity.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
    }
}

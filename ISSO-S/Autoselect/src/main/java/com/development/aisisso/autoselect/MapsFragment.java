package com.development.aisisso.autoselect;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;


public class MapsFragment extends Fragment implements Serializable{

    private View v;
    private MapController mapController;
    private Overlay overlay = null;
    private ImageButton btnFollow;
    private boolean isFollowed = false;
    private Location location;
    private GeoPoint pointBehind;
    private GeoPoint pointAhead;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setupMyGeolocation (Location location, HttpsIsso[] listOfIssos, ArrayVector hashMap) {
        this.location = location;
        //mapController.setPositionAnimationTo(new GeoPoint(location.getLatitude(), location.getLongitude()));
        if(btnFollow.getVisibility() != View.VISIBLE) {
            btnFollow.setVisibility(View.VISIBLE);
            btnFollow.setClickable(true);
        }
        if(overlay != null) {
            mapController.getOverlayManager().removeOverlay(overlay);
        }
        OverlayManager overlayManager = mapController.getOverlayManager();
        Log.d("Tag", mapController.getOverlayManager().getOverlays().size() + "");
        Overlay overlay = new Overlay(mapController);
        OverlayItem itemFarAhead, itemNearAhead, itemBehind;
        float density = getResources().getDisplayMetrics().density;
        if( PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
            if(hashMap.getIndex(0) != -1) {
                double lat = listOfIssos[hashMap.getIndex(0)].Latitude;
                double lng = listOfIssos[hashMap.getIndex(0)].Longitude;
                itemFarAhead = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_ahead_dark));
                BalloonItem balloonFar = new BalloonItem(v.getContext(), itemFarAhead.getGeoPoint());
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
                itemNearAhead = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_ahead_dark));
                BalloonItem balloonNear = new BalloonItem(v.getContext(), itemNearAhead.getGeoPoint());
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
                BalloonItem balloonBehind = new BalloonItem(v.getContext(), itemBehind.getGeoPoint());
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
                BalloonItem balloonFar = new BalloonItem(v.getContext(), itemFarAhead.getGeoPoint());
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
                itemNearAhead = new OverlayItem(new GeoPoint(lat, lng), getResources().getDrawable(R.drawable.marker_ahead_light));
                BalloonItem balloonNear = new BalloonItem(v.getContext(), itemNearAhead.getGeoPoint());
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
                BalloonItem balloonBehind = new BalloonItem(v.getContext(), itemBehind.getGeoPoint());
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
        if(isFollowed)
            FollowByObjects();
        this.overlay = overlay;
    }


    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        /** Обьявляем layout с картой */
        v = inflater.inflate(R.layout.maps_layout, container, false);
        MapView map = (MapView) v.findViewById(R.id.mapView);
        mapController = map.getMapController();
        btnFollow = (ImageButton) v.findViewById(R.id.btnFollow);
        btnFollow.setVisibility(View.INVISIBLE);
        btnFollow.setClickable(false);

        if( PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
            mapController.setNightMode(true);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_dark));
            //btnFollow.setBackgroundColor(getResources().getColor(R.color.TransparentDark));
        }
        else {
            mapController.setNightMode(false);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_light));
            //btnFollow.setBackgroundColor(getResources().getColor(R.color.TransparentLight));
        }

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFollowed) {
                    isFollowed = true;
                    if(FollowByObjects()) {
                        if (PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
                            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.define_location_dark));
                        } else {
                            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.define_location_light));
                        }
                        Toast.makeText(v.getContext(), "Слежение включено.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        isFollowed = false;
                        Toast.makeText(v.getContext(), "Слежение невозможно. Нет объектов впереди.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    isFollowed = false;
                    if (PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
                        btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_dark));
                    } else {
                        btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_light));
                    }
                }
            }
        });

        /*mapController.getMapRotator().a(true);
        final float[] superAngle = {0};
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (geoNear != null) {
                    double lat, lon, northLat, x_cur, y_cur, x_north, y_north, x_obj, y_obj, x_east, y_east, eastLon;
                    lat = Math.toRadians(location.getLatitude());
                    lon = Math.toRadians(location.getLongitude());
                    northLat = Math.toRadians(location.getLatitude() + 1);
                    eastLon = Math.toRadians(location.getLongitude() + 1);
                    x_cur = MainActivity.rad * Math.cos(lat) * Math.cos(lon);
                    y_cur = MainActivity.rad * Math.sin(lat) * Math.cos(lon);
                    x_north = x_cur;
                    y_north = MainActivity.rad * Math.sin(northLat) * Math.cos(lon);
                    x_east = MainActivity.rad * Math.cos(lat) * Math.cos(eastLon);
                    y_east = y_cur;
                    x_obj = MainActivity.rad * Math.cos(Math.toRadians(geoNear.getLat())) * Math.cos(Math.toRadians(geoNear.getLon()));
                    y_obj = MainActivity.rad * Math.sin(Math.toRadians(geoNear.getLat())) * Math.cos(Math.toRadians(geoNear.getLon()));
                    double angle = (x_north - x_cur) * (x_obj - x_cur) + (y_north - y_cur) * (y_obj - y_cur);
                    angle /= (Math.sqrt(Math.pow(x_north - x_cur, 2) + Math.pow(y_north - y_cur, 2)) * Math.sqrt(Math.pow(x_obj - x_cur, 2) + Math.pow(y_obj - y_cur, 2)));
                    angle = Math.acos(angle);
                    angle = Math.toDegrees(angle);
                    Log.d("Tag", "" + angle);
                    double rightAngle= (x_east - x_cur) * (x_obj - x_cur) + (y_east - y_cur) * (y_obj - y_cur);
                    rightAngle /= (Math.sqrt(Math.pow(x_east - x_cur, 2) + Math.pow(y_east - y_cur, 2)) * Math.sqrt(Math.pow(x_obj - x_cur, 2) + Math.pow(y_obj - y_cur, 2)));
                    rightAngle = Math.acos(rightAngle);
                    rightAngle = Math.toDegrees(rightAngle);
                    GeoPoint geopoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                    OverlayItem overlayItem = new OverlayItem(geopoint, null);
                    if(rightAngle < 90) {
                        mapController.getMapRotator().a((float) angle);
                    }
                    else {
                        mapController.getMapRotator().a((float) - angle);
                    }
                    mapController.getMapRotator().a(overlayItem.getPoint());
                } else {
                    mapController.getMapRotator().a(true);
                    GeoPoint geoPoint = mapController.getMapCenter();
                    OverlayItem overlayItem = new OverlayItem(geoPoint, null);
                    mapController.getMapRotator().a(overlayItem.getPoint());
                    mapController.getMapRotator().a(120 + superAngle[0]);
                    superAngle[0] += 120;
                }

            }
        });*/
        return v;
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

        /*double x_cur = MainActivity.rad * Math.cos(Math.toRadians(location.getLatitude())) * Math.cos(Math.toRadians(location.getLongitude()));
        double y_cur = MainActivity.rad * Math.sin(Math.toRadians(location.getLatitude())) * Math.cos(Math.toRadians(location.getLongitude()));
        double x_near = MainActivity.rad * Math.cos(Math.toRadians(pointAhead.getLat())) * Math.cos(Math.toRadians(pointAhead.getLon()));
        double y_near = MainActivity.rad * Math.sin(Math.toRadians(pointAhead.getLat())) * Math.cos(Math.toRadians(pointAhead.getLon()));
        double distanceToNear = Math.sqrt(Math.pow(x_near - x_cur, 2) + Math.pow(y_near - y_cur, 2));*/
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

                WindowManager wm = (WindowManager) v.getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                if(dosvidos > 0) {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    /*Rect rect = mapController.getViewport();
                    if (rect.height() < rect.width())
                        mapController.setViewport(0, 0, rect.height(), rect.width());*/
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
    }
}


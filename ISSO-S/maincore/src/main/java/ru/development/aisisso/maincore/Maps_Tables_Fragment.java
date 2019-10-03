package ru.development.aisisso.maincore;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class Maps_Tables_Fragment extends Fragment implements Serializable{

    private View v;
    private TextView tvFar;
    private TextView tvFarDistance;
    private TextView tvNear;
    private TextView tvNearDistance;
    private TextView tvBehind;
    private TextView tvBehindDistance;
    private MapController mapController;
    private Overlay overlay = null;
    private ImageButton btnFollow;
    private boolean isFollowed = false;
    private Location location;
    private GeoPoint pointBehind;
    private GeoPoint pointAhead;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public String getTVText() {
        return this.tvFar.getText().toString();
    }

    public void setTVText(String x, String y) {
        this.tvFar.setText(x);
        this.tvFarDistance.setText(y);
        this.tvNear.setText(x);
        this.tvNearDistance.setText(y);
        this.tvBehind.setText(x);
        this.tvBehindDistance.setText(y);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        LinearLayout l = (LinearLayout) v.findViewById(R.id.linLayoutMapsTables);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            l.setOrientation(LinearLayout.HORIZONTAL);
            l.setBaselineAligned(false);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            l.setOrientation(LinearLayout.VERTICAL);
            l.setBaselineAligned(true);
        }
    }

    public void setTheRest (HttpsIsso isso, TextView tv, int rating, Drawable[] draw) {
        try {
            String str = String.format("%.3f", isso.WIsso);
            str = str.replaceAll(",", "+");
            str = str.replaceAll("\\.", "+");
            SpannableString ss1 = new SpannableString("   " + isso.Name + "\n" + String.format("%s, км %s (%s)\n", isso.DorName, str, isso.Obstacle));
            Drawable d;
            switch (rating) {
                case 20:
                    d = draw[1];
                    break;
                case 30:
                    d = draw[2];
                    break;
                case 40:
                    d = draw[3];
                    break;
                case 50:
                    d = draw[4];
                    break;
                case 60:
                    d = draw[5];
                    break;
                case 70:
                    d = draw[6];
                    break;
                default:
                    d = draw[0];
                    break;
            }

            assert d != null;
            d.setBounds(0, 0, 20, 20);
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            ss1.setSpan(span, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

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
            if(!isAdded()) {
                return;
            }
        }
    }

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
            setTheRest(listOfIssos[hashMap.getIndex(0)], tvNear, rating[hashMap.getIndex(0)], draw);
        }
        else
            tvNear.setText("Впереди нет ближайшего объекта.");
        if(hashMap.getIndex(1) != -1) {
            setTheRest(listOfIssos[hashMap.getIndex(1)], tvFar, rating[hashMap.getIndex(0)], draw);
        }
        else
            tvFar.setText("Впереди нет следующего за ближайшим объекта.");
        if(hashMap.getIndex(2) != -1) {
            setTheRest(listOfIssos[hashMap.getIndex(2)], tvBehind, rating[hashMap.getIndex(0)], draw);
        }
        else {
            tvBehind.setText("Позади нет ближайшего объекта.");
            tvBehind.setClickable(false);
        }
    }

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        /** Обьявляем layout с картами и таблицей */
        v = inflater.inflate(R.layout.maps_tables_layout, container, false);
        MapView map = (MapView) v.findViewById(R.id.mapViewAdvanced);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout l = (LinearLayout) v.findViewById(R.id.linLayoutMapsTables);
            l.setOrientation(LinearLayout.HORIZONTAL);
            l.setBaselineAligned(false);
        }
        //map.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mapController = map.getMapController();
        btnFollow = (ImageButton) v.findViewById(R.id.btnAdvancedFollow);
        btnFollow.setVisibility(View.INVISIBLE);
        btnFollow.setClickable(false);
        if( PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
            mapController.setNightMode(true);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_dark));
            //btnFollow.setBackgroundColor(getResources().getColor(R.color.TransparentDark));
            tvFar = (TextView) v.findViewById(R.id.textViewFar);
            tvFar.setTextColor(getResources().getColor(R.color.textAheadDark));
            //tvFar.setBackgroundResource(R.drawable.row_border_dark);
            tvFarDistance = (TextView) v.findViewById(R.id.textViewFarDistance);
            tvFarDistance.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvNear = (TextView) v.findViewById(R.id.textViewNear);
            tvNear.setTextColor(getResources().getColor(R.color.textAheadDark));
            //tvNear.setBackgroundResource(R.drawable.row_border_dark);
            tvNearDistance = (TextView) v.findViewById(R.id.textViewNearDistance);
            tvNearDistance.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvBehind = (TextView) v.findViewById(R.id.textViewBehind);
            tvBehind.setTextColor(getResources().getColor(R.color.textBehindDark));
            //tvBehind.setBackgroundResource(R.drawable.row_border_dark);
            tvBehindDistance = (TextView) v.findViewById(R.id.textViewBehindDistance);
            tvBehindDistance.setTextColor(getResources().getColor(R.color.textBehindDark));
            /*((TextView) v.findViewById(R.id.textViewFar)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewFarDistance)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewNear)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewNearDistance)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewBehind)).setTextColor(getResources().getColor(R.color.textBehindDark));
            ((TextView) v.findViewById(R.id.textViewBehindDistance)).setTextColor(getResources().getColor(R.color.textBehindDark));
            (v.findViewById(R.id.tableLayout1)).setBackgroundResource(R.drawable.row_border_dark);
            (v.findViewById(R.id.textViewFar)).setBackgroundResource(R.drawable.row_border_dark);
            (v.findViewById(R.id.textViewNear)).setBackgroundResource(R.drawable.row_border_dark);
            (v.findViewById(R.id.textViewBehind)).setBackgroundResource(R.drawable.row_border_dark);*/
        }
        else {
            mapController.setNightMode(false);
            btnFollow.setImageDrawable(getResources().getDrawable(R.drawable.free_location_light));
            //btnFollow.setBackgroundColor(getResources().getColor(R.color.TransparentLight));
            tvFar = (TextView) v.findViewById(R.id.textViewFar);
            tvFar.setTextColor(getResources().getColor(R.color.textAheadLight));
            //tvFar.setBackgroundResource(R.drawable.row_border_light);
            tvFarDistance = (TextView) v.findViewById(R.id.textViewFarDistance);
            tvFarDistance.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvNear = (TextView) v.findViewById(R.id.textViewNear);
            tvNear.setTextColor(getResources().getColor(R.color.textAheadLight));
            //tvNear.setBackgroundResource(R.drawable.row_border_light);
            tvNearDistance = (TextView) v.findViewById(R.id.textViewNearDistance);
            tvNearDistance.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvBehind = (TextView) v.findViewById(R.id.textViewBehind);
            tvBehind.setTextColor(getResources().getColor(R.color.textBehindLight));
            //tvBehind.setBackgroundResource(R.drawable.row_border_light);
            tvBehindDistance = (TextView) v.findViewById(R.id.textViewBehindDistance);
            tvBehindDistance.setTextColor(getResources().getColor(R.color.textBehindLight));
            /*((TextView) v.findViewById(R.id.textViewFar)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewFarDistance)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewNear)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewNearDistance)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewBehind)).setTextColor(getResources().getColor(R.color.textBehindLight));
            ((TextView) v.findViewById(R.id.textViewBehindDistance)).setTextColor(getResources().getColor(R.color.textBehindLight));
            (v.findViewById(R.id.tableLayout1)).setBackgroundResource(R.drawable.row_border_light);
            (v.findViewById(R.id.textViewFar)).setBackgroundResource(R.drawable.row_border_light);
            (v.findViewById(R.id.textViewNear)).setBackgroundResource(R.drawable.row_border_light);
            (v.findViewById(R.id.textViewBehind)).setBackgroundResource(R.drawable.row_border_light);*/
        }
        tvFar.setText("Получение координат .");
        tvFarDistance.setText(".");
        tvNear.setText("Получение координат .");
        tvNearDistance.setText(".");
        tvBehind.setText("Получение координат .");
        tvBehindDistance.setText(".");
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
                    Toast.makeText(v.getContext(), "Слежение приостановлено.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    public boolean FollowByObjects() {
        List<OverlayItem> list = overlay.getOverlayItems();
        float zoom = mapController.getZoomCurrent();
        if(pointAhead != null) {
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
                        Rect rect = mapController.getViewport();
                        if (rect.height() < rect.width())
                            mapController.setViewport(0, 0, rect.height(), rect.width());
                        mapController.setZoomToSpan((maxLat - minLat) * (double) size.y / (double) rect.height() + ((maxLat - minLat) * 2 / 10), maxLon - minLon + ((maxLon - minLon) * 2 / 10));
                    } else {
                        Rect rect = mapController.getViewport();
                        if (rect.height() > rect.width())
                            mapController.setViewport(0, 0, rect.height(), rect.width());
                        mapController.setZoomToSpan(maxLat - minLat + (maxLat - minLat) * 2 / 10, maxLon - minLon + (maxLon - minLon) * 2 / 10);
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

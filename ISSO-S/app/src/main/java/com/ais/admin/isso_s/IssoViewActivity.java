package com.ais.admin.isso_s;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;



public class IssoViewActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private String cIsso;
    private double latitude;
    private double longitude;
    private double length;
    private TextView tvAllRating;

    private ArrayList<tableRow> rows = new ArrayList<>();
    private int currentRating;
    private String expertString = "";
    private String dateRating;
    private RecyclerView listView;
    private Location currentLocation;
    boolean noRemWorks = true;

    public LocationListener listen = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onProviderEnabled(String provider) {
            MainActivity.locationManager.getLastKnownLocation(provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    android.os.Handler hand = new android.os.Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            refreshStatus();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") )
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);

        setContentView(R.layout.activity_isso_view);


        /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = 2;
            findViewById(R.id.linearLayout).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = (float) 0.5;
            findViewById(R.id.textViewDescription).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = (float) 0.5;
            findViewById(R.id.tvAllRating).setLayoutParams(params);
        }*/

        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.IssoCoordinator);
        /*  Подулючение toolbar со связанным заголовком*/
        final Toolbar toolbar = (Toolbar) findViewById(R.id.isso_tabanim_toolbar);
        setSupportActionBar(toolbar);

        cIsso = getIntent().getStringExtra("C_ISSO");
        final ActionBar ab = getSupportActionBar();
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") ) {
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((FloatingActionButton)findViewById(R.id.btnCreateRating)).setImageResource(R.drawable.new_rem_dark);
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_light);
        }
        else {
            ((FloatingActionButton)findViewById(R.id.btnCreateRating)).setImageResource(R.drawable.new_rem_light);
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }
        ab.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("АИС ИССО-S. ИССО №" + cIsso);
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        DBHelper helper = new DBHelper(this);
        db = helper.getReadableDatabase();
        Cursor cr = db.query("RATING", new String[]{"RATINGDATE", "OFFSET"}, "C_ISSO = " + cIsso, null, null, null, "RATINGDATE DESC");
        if (cr.moveToFirst()) {
            fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
            fmt.getTimeZone().setRawOffset((int) cr.getLong(cr.getColumnIndex("OFFSET")));
            dateRating = fmt.format(cr.getLong(cr.getColumnIndex("RATINGDATE")));
            dateRating = dateRating.substring(0, 10);
        }
        cr.close();

        tvAllRating = (TextView) findViewById(R.id.tvAllRating);

        cr = db.query("I_ISSO", new String[] { "FULLNAME", "LATITUDE", "LONGITUDE", "LENGTH", "N_OTC_EXP", "NAME_ISSO"},
                "C_ISSO = " + cIsso, null, null, null, null);
        cr.moveToFirst();
        ((TextView)findViewById(R.id.textViewDescription)).setText(cr.getString(cr.getColumnIndex("FULLNAME")) +
                (!cr.isNull(cr.getColumnIndex("NAME_ISSO")) && !cr.getString(cr.getColumnIndex("NAME_ISSO")).equals("")
                        ? String.format(" (%s)", cr.getString(cr.getColumnIndex("NAME_ISSO"))) : ""));
        latitude = cr.getDouble(cr.getColumnIndex("LATITUDE"));
        longitude = cr.getDouble(cr.getColumnIndex("LONGITUDE"));
        length = cr.getDouble(cr.getColumnIndex("LENGTH"));
        if(cr.getString(cr.getColumnIndex("N_OTC_EXP")) != null)
            expertString = "Экспертная оценка технического состояния: " + cr.getString(cr.getColumnIndex("N_OTC_EXP")) + "\n";
        cr.close();
        listView = (RecyclerView) findViewById(R.id.issoLVHistory);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        listView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        listView.setItemAnimator(itemAnimator);
        newRefreshRatings();

        findViewById(R.id.btnCreateRating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLocation != null) {
                    if(!CheckGPS()) return;
                    AddNewRating();
                }
                else {
                    AlertDialog.Builder bld = new AlertDialog.Builder(IssoViewActivity.this);
                    bld.setMessage("Данные по Вашей геопозиции пока что не получены. Повторите попытку позднее.");
                    bld.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = bld.create();
                    alert.show();
                }
            }
        });

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                hand.sendEmptyMessage(0);
            }
        };

        Timer tm = new Timer();
        tm.schedule(task, 0, 2000);

    }

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LinearLayout l = (LinearLayout) findViewById(R.id.linLayoutMapsTables);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = 2;
            findViewById(R.id.linearLayout).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = (float) 0.5;
            findViewById(R.id.textViewDescription).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = (float) 0.5;
            findViewById(R.id.tvAllRating).setLayoutParams(params);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = 4;
            findViewById(R.id.linearLayout).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = 1;
            findViewById(R.id.textViewDescription).setLayoutParams(params);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = 1;
            findViewById(R.id.tvAllRating).setLayoutParams(params);
        }
    }*/


    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                Log.d("Tag", "select" + ". " + c.getCount() + " rows");
                StringBuilder sb = new StringBuilder();
                do {
                    sb.setLength(0);
                    for (String cn : c.getColumnNames()) {
                        sb.append(cn).append(" = ").append(c.getString(c.getColumnIndex(cn))).append("; ");
                    }
                    Log.d("Tag", sb.toString());
                } while (c.moveToNext());
            }
        } else
            Log.d("Tag", "select" + ". Cursor is null");
    }


    @Override
    public void onResume() {
        super.onResume();
        //ActivityMonitor.active++;
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

    @SuppressLint("MissingPermission")
    void refreshStatus() {
        currentLocation = MainActivity.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double dist = 0;
        if(currentLocation != null) {
            dist = getDistance(currentLocation, latitude, longitude);
        }
        ((TextView) findViewById(R.id.status)).setText(Html.fromHtml(String.format("<b>Расстояние до объекта, м</b> %s <br><b>Погрешность GPS, м</b> %s",
                currentLocation == null ? "[недоступно]" : String.format("%.1f", dist),
                currentLocation == null ? "[недоступно]" : String.format("%.1f", currentLocation.getAccuracy()))));
    }

    private void getRows() {
        currentRating = 0;
        final Cursor cr;
        rows.clear();
        cr = db.query("RATING", new String[]{"RATING", "RATINGDATE", "RATINGDATEEDIT", "COMMENTS", "OFFSET",
                "CURRENTRATING", "LATITUDE_RATING", "LONGITUDE_RATING", "CHECKOUTOFPLAN"}, "C_ISSO = " + cIsso, null, null, null, "RATINGDATE DESC");
        logCursor(cr);
        noRemWorks = true;
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++) {
                noRemWorks = false;
                rows.add(new tableRow(cr.getInt(cr.getColumnIndex("RATING")),
                        new SimpleDateFormat("dd/MM/yyyy").format(cr.getLong(cr.getColumnIndex("RATINGDATE"))),
                        cr.getLong(cr.getColumnIndex("RATINGDATE")), cr.getLong(cr.getColumnIndex("RATINGDATEEDIT")),
                        cr.getString(cr.getColumnIndex("COMMENTS")), cr.getInt(cr.getColumnIndex("CHECKOUTOFPLAN")) == 1));
                currentRating += cr.getInt(cr.getColumnIndex("CURRENTRATING"));
                cr.moveToNext();
            }
        }
        if(cr.moveToPrevious()) {
            tvAllRating.setText( expertString + "Суммарное ухудшение с момента последнего освидетельствования в баллах: " + String.valueOf(-currentRating));
        }
        else {
            tvAllRating.setText(expertString + "Для данного сооружения пока нет оценки ситуации.");
        }
        cr.close();

        if(noRemWorks) {
            TextView tvNoWorks = (TextView) findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setText("Для данного ИССО не было создано ни одной оценки ситуации.");
            tvNoWorks.setVisibility(View.VISIBLE);
        }
        else {
            TextView tvNoWorks = (TextView) findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setVisibility(View.GONE);
        }
    }

    private void newRefreshRatings() {
        getRows();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Cursor c = db.query("RATING", new String[]{"RATINGDATE", "OFFSET"}, "C_ISSO = " + cIsso, null, null, null, "RATINGDATE DESC");
        if (c.moveToFirst()) {
            fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
            fmt.getTimeZone().setRawOffset((int) c.getLong(c.getColumnIndex("OFFSET")));
            dateRating = fmt.format(c.getLong(c.getColumnIndex("RATINGDATE")));
            dateRating = dateRating.substring(0, 10);
        }
        c.close();
        final IssoAdapter adapter = new IssoAdapter(rows);
        listView.setAdapter(adapter);

        /*          Здесь прописано удаление с помощью смахивания элементов списка, но данная
          функция не понадобилась в этом приложении, значит оставим её до лучших времен.....*/
        final ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                //final int position = viewHolder.getAdapterPosition();
                final Cursor c = db.rawQuery("select SYNC from RATING where C_ISSO=" + cIsso
                        + " and RATINGDATE=" + rows.get(viewHolder.getAdapterPosition()).dateMonth, null);
                c.moveToFirst();
                final tableRow row = rows.get(viewHolder.getAdapterPosition());
                //adapter.onItemRemove(viewHolder.getAdapterPosition(), listView);
                if(c.getInt(c.getColumnIndex("SYNC")) == 0) {
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(IssoViewActivity.this);
                    builder3.setTitle("Подтверждение удаления:")
                            .setMessage("Данная оценка, созданная " + new SimpleDateFormat("dd.MM.yyyy").format(new Date(row.dateMonth)) +
                                    " будет удалена. Продолжить?")
                            .setCancelable(false)
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (c.getInt(c.getColumnIndex("SYNC")) == 0) {
                                        // Удаление записей из тапблицы RATING
                                        db.delete("RATING", "C_ISSO=" + cIsso
                                                + " and RATINGDATE=" + row.dateMonth, null);
                                        // Удапление записей из таблицы PHOTOS_RATING
                                        Cursor deleteCursor = db.rawQuery("select PHOTOPATH from PHOTOS_RATING where C_ISSO="
                                                + cIsso + " and RATINGDATE=" + row.dateMonth, null);
                                        if(deleteCursor.moveToFirst()) {
                                            for (int index = 0; index < deleteCursor.getCount(); index++) {
                                                File f = new File(deleteCursor.getString(deleteCursor.getColumnIndex("PHOTOPATH")));
                                                if(f.exists())
                                                    f.delete();
                                            }
                                        }
                                        db.delete("PHOTOS_RATING", "C_ISSO=" + cIsso + " and RATINGDATE="
                                                + row.dateMonth, null);
                                        Snackbar.make(listView, "Данные удалены", Snackbar.LENGTH_LONG).show();

                                        Cursor cr = db.query("RATING", new String[]{"RATING", "RATINGDATE",
                                                "RATINGDATEEDIT", "COMMENTS", "OFFSET", "CURRENTRATING", "LATITUDE_RATING", "LONGITUDE_RATING",
                                                "CHECKOUTOFPLAN"}, "C_ISSO = " + cIsso, null, null, null, "RATINGDATE DESC");
                                        logCursor(cr);
                                        boolean noRemWorks = true;
                                        if(cr.moveToFirst()) {
                                            for (int i = 0; i < cr.getCount(); i++) {
                                                rows.add(new tableRow(cr.getInt(cr.getColumnIndex("RATING")),
                                                        new SimpleDateFormat("dd/MM/yyyy").format(cr.getLong(cr.getColumnIndex("RATINGDATE"))),
                                                        cr.getLong(cr.getColumnIndex("RATINGDATE")), cr.getLong(cr.getColumnIndex("RATINGDATEEDIT")),
                                                        cr.getString(cr.getColumnIndex("COMMENTS")), cr.getInt(cr.getColumnIndex("CHECKOUTOFPLAN")) == 1));
                                                currentRating += cr.getInt(cr.getColumnIndex("CURRENTRATING"));
                                                cr.moveToNext();
                                            }
                                        }

                                        getRows();

                                        final IssoAdapter adapter = new IssoAdapter(rows);
                                        listView.setAdapter(adapter);

                                    } else {
                                        AlertDialog.Builder bld = new AlertDialog.Builder(IssoViewActivity.this);
                                        bld.setMessage("Вы не можете удалить эти данные, так как они уже были синхронизированы с сервером.");
                                        bld.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                        AlertDialog alert = bld.create();
                                        alert.show();
                                        //adapter.onItemReestablished(viewHolder.getAdapterPosition(), listView, row);

                                        getRows();

                                        final IssoAdapter adapter = new IssoAdapter(rows);
                                        listView.setAdapter(adapter);
                                    }
                                }
                            })
                            .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    //adapter.onItemReestablished(viewHolder.getAdapterPosition(), listView, row);

                                    getRows();

                                    final IssoAdapter adapter = new IssoAdapter(rows);
                                    listView.setAdapter(adapter);
                                }
                            });
                    builder3.show();
                }
                else {
                    AlertDialog.Builder bld = new AlertDialog.Builder(IssoViewActivity.this);
                    bld.setMessage("Вы не можете удалить эту оценку, так как она уже была синхронизирована с сервером.");
                    bld.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = bld.create();
                    alert.show();
                    //adapter.onItemReestablished(viewHolder.getAdapterPosition(), listView, row);

                    getRows();

                    final IssoAdapter adapter = new IssoAdapter(rows);
                    listView.setAdapter(adapter);
                }
            }
        };
        new ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(listView);



        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Cursor c = db.query("RATING", new String[]{"RATING", "RATINGDATE", "RATINGDATEEDIT", "COMMENTS", "OFFSET",
                        "CURRENTRATING", "LATITUDE_RATING", "LONGITUDE_RATING", "CHECKOUTOFPLAN", "SYNC"}, "C_ISSO = " + cIsso +
                        " and RATINGDATE=" + rows.get(position).dateMonth, null, null, null, "RATINGDATE DESC");
                c.moveToFirst();
                Intent intent = new Intent(IssoViewActivity.this, AddNewRating.class);
                if(c.getInt(c.getColumnIndex("SYNC")) == 0)
                    intent.putExtra("Editable", true);
                else
                    intent.putExtra("Preview", true);
                @SuppressLint("MissingPermission") Location loc = ((LocationManager) getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
                intent.putExtra("Location", loc);
                intent.putExtra("Date", c.getLong(c.getColumnIndex("RATINGDATE")));
                intent.putExtra("Latitude", c.getDouble(c.getColumnIndex("LATITUDE_RATING")));
                intent.putExtra("Longitude", c.getDouble(c.getColumnIndex("LONGITUDE_RATING")));
                intent.putExtra("Offset", c.getLong(c.getColumnIndex("OFFSET")));
                intent.putExtra("C_ISSO", getIntent().getStringExtra("C_ISSO"));
                intent.putExtra("CurrentRating", c.getInt(c.getColumnIndex("CURRENTRATING")));
                intent.putExtra("isNeedInChecking", c.getInt(c.getColumnIndex("CHECKOUTOFPLAN")));
                intent.putExtra("Comments", c.getString(c.getColumnIndex("COMMENTS")));
                if(currentLocation != null) {
                    double distance = getDistance(currentLocation, latitude, longitude);
                    if (distance < 100.0 + currentLocation.getAccuracy()) {
                        intent.putExtra("CanAddImages", true);
                    }
                }
                Cursor cursor = db.rawQuery("select SUM(CURRENTRATING) from RATING where C_ISSO=" + cIsso, null);
                if(cursor.moveToFirst())
                    intent.putExtra("AllRating", cursor.getInt(0));
                startActivityForResult(intent, 0);
                c.close();
                cursor.close();
            }
        }));

        if(noRemWorks) {
            TextView tvNoWorks = (TextView) findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setText("Для данного ИССО не было создано ни одной оценки ситуации.");
            tvNoWorks.setVisibility(View.VISIBLE);
        }
        else {
            TextView tvNoWorks = (TextView) findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setVisibility(View.GONE);
        }
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

    private void refreshRatings() {
        currentRating = 0;
        final Cursor cr;
        cr = db.query("RATING", new String[]{"RATING", "RATINGDATE", "COMMENTS", "OFFSET", "CURRENTRATING", "LATITUDE_RATING", "LONGITUDE_RATING"}, "C_ISSO = " + cIsso, null, null, null, "RATINGDATE DESC");
        logCursor(cr);
        ScrollView v = (ScrollView)findViewById(R.id.scrollView);
        v.removeAllViews();
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        v.addView(l);
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));

        for(int i = 0; i < cr.getCount(); i++)
        {
            fmt.getTimeZone().setRawOffset((int) cr.getLong(3));
            TextView tv = new TextView(this);
            tv.setText(Html.fromHtml(String.format("<b> Оценка ситуации : </b>%s (%s) <br><b> Дата внесения оценки : </b>%s <br><b>Комментарий : </b>%s<br>",
                    Rating.getDescription(cr.getInt(cr.getColumnIndex("RATING"))),
                    String.valueOf(cr.getInt(cr.getColumnIndex("CURRENTRATING"))),
                    fmt.format(cr.getLong(cr.getColumnIndex("RATINGDATE"))), cr.getString(cr.getColumnIndex("COMMENTS")))));
            View line = new View(this);
            line.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 2));
            line.setBackgroundColor(Color.rgb(200, 200, 200));
            l.addView(line);
            if(i == 0) {
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!IfICanEdit()) return;
                        cr.moveToFirst();
                        Intent intent = new Intent(IssoViewActivity.this, AddNewRating.class);
                        intent.putExtra("Editable", true);
                        intent.putExtra("Date", cr.getLong(cr.getColumnIndex("RATINGDATE")));
                        intent.putExtra("Latitude", cr.getDouble(cr.getColumnIndex("LATITUDE_RATING")));
                        intent.putExtra("Longitude", cr.getDouble(cr.getColumnIndex("LONGITUDE_RATING")));
                        intent.putExtra("Offset", cr.getLong(cr.getColumnIndex("OFFSET")));
                        intent.putExtra("C_ISSO", getIntent().getStringExtra("C_ISSO"));
                        intent.putExtra("CurrentRating", cr.getInt(cr.getColumnIndex("CURRENTRATING")));
                        intent.putExtra("Comments", cr.getString(cr.getColumnIndex("COMMENTS")));
                        Cursor c = db.rawQuery("select SUM(CURRENTRATING) from RATING where C_ISSO=" + cIsso, null);
                        if(c.moveToFirst())
                            intent.putExtra("AllRating", c.getInt(0));
                        startActivityForResult(intent, 0);
                    }
                });
            }
            else
                l.addView(tv);
            if(i == 0) {
                LinearLayout mainLayout = new LinearLayout(this);
                mainLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout lLayout = new LinearLayout(this);
                lLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                l.addView(mainLayout);
                mainLayout.addView(tv);
                mainLayout.addView(lLayout);
                ImageView img = new ImageView(this);
                img.setLayoutParams(params);
                TextView tvEdit = new TextView(this);
                tvEdit.setLayoutParams(params);
                lLayout.addView(img);
                lLayout.addView(tvEdit);
            }
            currentRating += cr.getInt(cr.getColumnIndex("CURRENTRATING"));
            cr.moveToNext();
        }
        if(cr.moveToPrevious()) {
            tvAllRating.setText( expertString + "Суммарное ухудшение с момента последнего освидетельствования в баллах: " + String.valueOf(-currentRating));
        }
        else {
            tvAllRating.append(expertString + "Для данного сооружения пока нет оценки ситуации.");
        }
        cr.close();
        fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Cursor c = db.query("RATING", new String[]{"RATINGDATE", "OFFSET"}, "C_ISSO = " + cIsso, null, null, null, "RATINGDATE DESC");
        if (c.moveToFirst()) {
            fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
            fmt.getTimeZone().setRawOffset((int) c.getLong(c.getColumnIndex("OFFSET")));
            dateRating = fmt.format(c.getLong(c.getColumnIndex("RATINGDATE")));
            dateRating = dateRating.substring(0, 10);
        }
        c.close();
    }


    private void AddNewRating () {
        //if(!CheckGPS()) return;
        if(rows.size() > 0) {
            if (!CheckTimeOfLastRating(rows.get(0).dateMonth)) {
                @SuppressLint("MissingPermission") Location loc = ((LocationManager) getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Intent intent = new Intent(IssoViewActivity.this, AddNewRating.class);
                intent.putExtra("Location", loc);
                intent.putExtra("C_ISSO", getIntent().getStringExtra("C_ISSO"));
                intent.putExtra("AllRating", currentRating);
                startActivityForResult(intent, 0);
            }
            else {
                AlertDialog.Builder bld = new AlertDialog.Builder(this);
                bld.setMessage("Вы не можете вносить более одной оценки ситуации в день для текущего " +
                        "сооружения.");
                bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = bld.create();
                alert.show();
            }
        }
        else {
            @SuppressLint("MissingPermission") Location loc = ((LocationManager) getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Intent intent = new Intent(IssoViewActivity.this, AddNewRating.class);
            intent.putExtra("Location", loc);
            intent.putExtra("C_ISSO", getIntent().getStringExtra("C_ISSO"));
            intent.putExtra("AllRating", currentRating);
            startActivityForResult(intent, 0);
        }
    }


    private boolean CheckGPS() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location loc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc == null) return  false;

        if(loc.getAccuracy() > 20.0f)
        {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage(String.format("Недопустимая точность положения. Текущая погрешность : %s м.", loc.getAccuracy()));
            bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = bld.create();
            alert.show();
            return false;
        }

        double dist = getDistance(loc, latitude, longitude);

        if(dist > (100 + loc.getAccuracy()))
        {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage("Вы должны находиться на этом сооружении, чтобы иметь возможность вносить сведения.");
            bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = bld.create();
            alert.show();
            return false;
        }

        return true;
    }

    private boolean CheckTimeOfLastRating(long dateEdit) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = fmt.format(cal.getTime());
        String dateRating = fmt.format(dateEdit);
        return dateNow.equals(dateRating);
		/*if (currentLocation != null) {
			TimeZoneMapper timeZoneMapper = new TimeZoneMapper(IssoViewActivity.this);
			String timezone = timeZoneMapper.latLngToTimezoneString(currentLocation.getLatitude(), currentLocation.getLongitude());
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeZone(TimeZone.getTimeZone(timezone));
			calendar.setTimeInMillis(currentLocation.getTime());

			SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String dateNow = fmt.format(calendar.getTime());
			dateNow = dateNow.substring(0, 10);
			dateRating = dateRating.substring(0, 10);
			if (dateNow.equals(dateRating)) {
				AlertDialog.Builder bld = new AlertDialog.Builder(this);
				bld.setMessage("Вы не можете вносить более одного рейтинга в день для текущего " +
						"сооружения. \nДля редактирования последнего рейтинга нажмите на него в представленном списке.");
				AlertDialog alert = bld.create();
				alert.show();
				return false;
			}
		}
        else {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage("Данные о вашей геопозиции пока что не получены. Попробуйте позднее.");
            AlertDialog alert = bld.create();
            alert.show();
            return false;
        }
		return true;*/
    }

    private boolean IfICanEdit() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String dateNow = fmt.format(cal.getTime());
        dateNow = dateNow.substring(0, 10);
        dateRating = dateRating.substring(0, 10);
        return dateNow.equals(dateRating);
		/*if (!dateNow.equals(dateRating)) {
            if(i == 1) {
                AlertDialog.Builder bld = new AlertDialog.Builder(this);
                bld.setMessage("Так как прошел день с момента добавления рейтинга, вы не можете более вносить изменения.");
                bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = bld.create();
                alert.show();
                return false;
            }
            else return false;
		}
		else return true;*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            newRefreshRatings();
        }
    }

    public double getDistance (Location location, double latitude, double longitude) {
        double lat1 = Math.toRadians(location.getLatitude());
        double lng1 = Math.toRadians(location.getLongitude());


        double lat2 = Math.toRadians(latitude);
        double lng2 = Math.toRadians(longitude);


        double x = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1);
        double y = Math.sqrt(Math.pow(Math.cos(lat2) * Math.sin(lng2 - lng1), 2.0) + Math.pow(Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1), 2.0));

        double dist = Math.atan(y / x) * MainActivity.rad;
        return Math.max(dist - length / 2.0, 0);
    }
}


class tableRow {

    int lastRating;
    String date;
    long dateMonth;
    long dateEdit;
    String hasComments;
    boolean checkOut;

    tableRow(int lastRating, String date, long dateMonth, long dateEdit, String hasComments, boolean checkOut) {
        this.lastRating = lastRating;
        this.date = date;
        this.dateMonth = dateMonth;
        this.dateEdit = dateEdit;
        this.hasComments = hasComments;
        this.checkOut = checkOut;
    }
}

class IssoAdapter extends RecyclerView.Adapter<IssoAdapter.IssoViewHolder> {
    private List<tableRow> mainRows;

    public static class IssoViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView rating;
        private TextView date;
        private ImageView imgComments;
        private TextView tvCheckIt;

        public IssoViewHolder(View itemView) {
            super(itemView);
            if(MainActivity.theme.equals("0"))
                (itemView.findViewById(R.id.linLayoutIssoRow)).setBackgroundResource(R.drawable.custom_bg_dark);
            else
                (itemView.findViewById(R.id.linLayoutIssoRow)).setBackgroundResource(R.drawable.custom_bg);
            rating = (ImageView) itemView.findViewById(R.id.imgAverageRating);
            date = (TextView) itemView.findViewById(R.id.tvIssoDate);
            imgComments = (ImageView) itemView.findViewById(R.id.imgComments);
            tvCheckIt = (TextView) itemView.findViewById(R.id.tvcheckOutOfPlan);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public IssoAdapter(List<tableRow> mainRows) {
        this.mainRows = mainRows;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IssoAdapter.IssoViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.isso_row_layout, parent, false);
        return new IssoViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(IssoViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        String dateRating = fmt.format(mainRows.get(position).dateEdit);
        String date = mainRows.get(position).date.replaceAll("/", ".");
        holder.date.setText(date);
        if(mainRows.get(position).checkOut) {
            holder.tvCheckIt.setText("Необходима ОТС");
        }
        else {
            holder.tvCheckIt.setVisibility(View.GONE);
        }
        if(!mainRows.get(position).hasComments.equals("")) {
            if (MainActivity.theme.equals("0"))
                holder.imgComments.setImageResource(R.drawable.comments_dark);
            else
                holder.imgComments.setImageResource(R.drawable.comments_light);
        }
        else {
            if(MainActivity.theme.equals("0"))
                holder.imgComments.setImageResource(R.drawable.no_comments_dark);
            else
                holder.imgComments.setImageResource(R.drawable.no_comments_light);
        }
        switch (mainRows.get(position).lastRating) {
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

    public void onItemRemove(int position, final RecyclerView recyclerView) {
        final tableRow row = mainRows.get(position);
        mainRows.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mainRows.size());
        //notifyDataSetChanged();
    }

    public void onItemReestablished(int position, final RecyclerView recyclerView, tableRow row) {
        mainRows.add(position, row);
        //notifyDataSetChanged();
        //recyclerView.scrollToPosition(position);
        notifyItemInserted(position);
        recyclerView.scrollToPosition(position);
        notifyItemRangeChanged(position, mainRows.size());
    }
}
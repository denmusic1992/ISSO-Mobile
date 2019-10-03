package com.development.aisisso.isso_r;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;


public class IssoViewActivity extends AppCompatActivity {

    public static int cIsso;
    private double latitude;
    private double longitude;
    private double length;
    private TextView tvEdit;
    private ImageView img;
    private boolean isEmpty = false;
    final ArrayList<tableRow> rows = new ArrayList<>();
    private int noOfTimesCalled = 0;
    private RecyclerView listView;
    private Location currentLocation;

    public LocationListener listen = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
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

        setContentView(R.layout.issoview_layout);


        LinearLayout coordinatorLayout = (LinearLayout) findViewById(R.id.IssoCoordinator);
        //  Подулючение toolbar со связанным заголовком*/
        final Toolbar toolbar = (Toolbar) findViewById(R.id.isso_tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") ) {
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((FloatingActionButton)findViewById(R.id.btnCreateRemWork)).setImageResource(R.drawable.new_rem_dark);
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_light);
        }
        else {
            ((FloatingActionButton)findViewById(R.id.btnCreateRemWork)).setImageResource(R.drawable.new_rem_light);
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }
        ab.setDisplayHomeAsUpEnabled(true);
        (findViewById(R.id.btnCreateRemWork)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLocation != null) {
                    if (!CheckGPS()) return;
                    ChooseDateAndSoOn();
                } else {
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
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        cIsso = Integer.parseInt(getIntent().getStringExtra("C_ISSO"));
        getSupportActionBar().setTitle("АИС ИССО-R. ИССО №" + cIsso);
        Cursor cr = db.query("I_ISSO", new String[]{"FULLNAME", "LATITUDE", "LONGITUDE", "LENGTH", "N_OTC_EXP"}, "C_ISSO = " + cIsso, null, null, null, null);
        cr.moveToFirst();
        latitude = cr.getDouble(cr.getColumnIndex("LATITUDE"));
        longitude = cr.getDouble(cr.getColumnIndex("LONGITUDE"));
        length = cr.getDouble(cr.getColumnIndex("LENGTH"));
        ((TextView)findViewById(R.id.textViewDescription)).setText(cr.getString(cr.getColumnIndex("FULLNAME")));
        cr.close();
        listView = (RecyclerView) findViewById(R.id.issoLVHistory);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        listView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        listView.setItemAnimator(itemAnimator);
        setRatings();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                hand.sendEmptyMessage(0);
            }
        };

        Timer tm = new Timer();
        tm.schedule(task, 0, 2000);
    }


    private void setRatings() {
        final SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        rows.clear();
        final Cursor cr = db.rawQuery("select PERIOD, PERFORMER, OFFSET, EDIT_DATE from " +
                "I_REM_PLAN_EXEC where C_ISSO=" + IssoViewActivity.cIsso + " order by PERIOD desc", null);
        boolean noRemWorks = true;
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++) {
                noRemWorks = false;
                int rating;
                Cursor c = db.rawQuery("select AVG(RATING) from I_REM_PLAN_ITEMS where C_ISSO=" + cIsso + " and " +
                        "PERIOD =" + cr.getLong(cr.getColumnIndex("PERIOD")), null);
                c.moveToFirst();
                if(c.getInt(0) > 0)
                    rating = c.getInt(0);
                else
                    rating = -1;
                rows.add(new tableRow(cr.getString(cr.getColumnIndex("PERFORMER")), rating,
                        new SimpleDateFormat("dd/MM/yyyy").format(cr.getLong(cr.getColumnIndex("PERIOD"))),
                        cr.getLong(cr.getColumnIndex("PERIOD")), cr.getLong(cr.getColumnIndex("EDIT_DATE"))));
                c.close();
                cr.moveToNext();
            }
        }
        cr.close();
        final IssoAdapter adapter = new IssoAdapter(rows);
        listView.setAdapter(adapter);

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
                final int position = viewHolder.getAdapterPosition();
                final Cursor c = db.rawQuery("select PERFORMER, SYNC from I_REM_PLAN_EXEC where C_ISSO=" + IssoViewActivity.cIsso
                        + " and PERIOD=" + rows.get(position).dateMonth, null);
                c.moveToFirst();
                final tableRow row = rows.get(position);
                adapter.onItemRemove(position, listView);
                if (c.getString(c.getColumnIndex("PERFORMER")).equals(getSharedPreferences(
                        MainActivity.MY_SETTINGS, Context.MODE_PRIVATE).getString("nameCurator", "")) && c.getInt(c.getColumnIndex("SYNC")) == 0) {
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(IssoViewActivity.this);
                    final boolean isSynced = c.getInt(c.getColumnIndex("SYNC")) != 0;
                    builder3.setTitle("Подтверждение удаления:")
                            .setMessage("Данный отчет, созданный " + new SimpleDateFormat("dd.MM.yyyy").format(new Date(row.dateMonth)) +
                                    " будет удален. Продолжить?")
                            .setCancelable(false)
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (!isSynced) {
                                        db.delete("I_REM_PLAN_ITEMS", "C_ISSO=" + IssoViewActivity.cIsso
                                                + " and PERIOD=" + row.dateMonth, null);
                                        db.delete("I_REM_PLAN_EXEC", "C_ISSO=" + IssoViewActivity.cIsso
                                                + " and PERIOD=" + row.dateMonth, null);
                                        Snackbar.make(listView, "Данные удалены", Snackbar.LENGTH_LONG).show();
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
                                        adapter.onItemReestablished(position, listView, row);
                                    }
                                }
                            })
                            .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    adapter.onItemReestablished(position, listView, row);
                                }
                            });
                    builder3.show();
                }
                else if(c.getInt(c.getColumnIndex("SYNC")) == 0){
                    AlertDialog.Builder bld = new AlertDialog.Builder(IssoViewActivity.this);
                    bld.setMessage("Вы не можете удалить эти данные, так как они были созданы другим куратором.");
                    bld.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = bld.create();
                    alert.show();
                    adapter.onItemReestablished(position, listView, rows.get(position));
                }
                else {
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
                    adapter.onItemReestablished(position, listView, row);
                }
                c.close();
            }
        };
        new ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(listView);

        listView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(IssoViewActivity.this, NewRemWorks.class);
                Date d = new Date();
                d.setTime(rows.get(position).dateMonth);
                String formattedDate = new SimpleDateFormat("MMM d, yyyy").format(d);
                Calendar c = Calendar.getInstance();
                c.setTimeZone(TimeZone.getTimeZone("UTC"));
                c.setTimeInMillis(rows.get(position).dateMonth);
                intent.putExtra("PERIOD", c.getTime().getTime());
                if (!CheckTimeOfLastRating(rows.get(position).dateEdit)) {
                    intent.putExtra("PreviewOnly", true);
                    String dateRating = new SimpleDateFormat("dd.MM.yyyy").format(rows.get(position).dateEdit);
                    String date = rows.get(position).date.replaceAll("/", ".");
                    if (date.equals(dateRating))
                        intent.putExtra("dateRow", date);
                    else
                        intent.putExtra("dateRow", date + "/\n" + dateRating);
                } else {
                    intent.putExtra("CanEdit", true);
                    intent.putExtra("PreviewOnly", true);
                    intent.putExtra("Calendar", rows.get(position).dateMonth);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(rows.get(position).dateMonth);
                    int month = calendar.MONTH;
                    intent.putExtra("Month", month);
                    String dateRating = new SimpleDateFormat("dd.MM.yyyy").format(rows.get(position).dateEdit);
                    String date = rows.get(position).date.replaceAll("/", ".");
                    if (date.equals(dateRating))
                        intent.putExtra("dateRow", date);
                    else
                        intent.putExtra("dateRow", date + "/\n" + dateRating);
                }
                startActivityForResult(intent, 111);
            }

            public void onItemLongClick(View view, final int itemPosition) {

            }
        }));
        /*ItemClickSupport.addTo(listView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, final int position, View v) {
                final Cursor c = db.rawQuery("select N_PERFORMER, SYNC from I_REM_PLAN_CUR where C_ISSO=" + IssoViewActivity.cIsso
                        + " and REM_MONTH=" + rows.get(position).dateMonth, null);
                c.moveToFirst();
                if (c.getString(c.getColumnIndex("N_PERFORMER")).equals(getSharedPreferences(
                        MainActivity.MY_SETTINGS, Context.MODE_PRIVATE).getString("nameCurator", ""))) {
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(IssoViewActivity.this);
                    builder3.setMessage("Вы хотите удалить данный отчет?")
                            .setCancelable(false)
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (c.getInt(c.getColumnIndex("SYNC")) == 0) {
                                        db.delete("I_REM_PLAN_EXEC", "C_ISSO=" + IssoViewActivity.cIsso
                                                + " and REM_MONTH=" + rows.get(position).dateMonth, null);
                                        db.delete("I_REM_PLAN_CUR", "C_ISSO=" + IssoViewActivity.cIsso
                                                + " and REM_MONTH=" + rows.get(position).dateMonth, null);
                                        Toast.makeText(IssoViewActivity.this, "Удаление произведено успешно!", Toast.LENGTH_SHORT).show();
                                        rows.remove(position);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        AlertDialog.Builder bld = new AlertDialog.Builder(IssoViewActivity.this);
                                        bld.setMessage("Вы не можете удалить эти данные, так как они уже были синхронизированы с сервером.");
                                        AlertDialog alert = bld.create();
                                        alert.show();
                                    }
                                }
                            })
                            .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder3.show();
                }
                return false;
            }
        });*/
        if(noRemWorks) {
            TextView tvNoWorks = (TextView) findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setText("В этом месяце не зарегистрированы оценки работ по данному ИССО.");
            tvNoWorks.setVisibility(View.VISIBLE);
        }
        else {
            TextView tvNoWorks = (TextView) findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setVisibility(View.GONE);
        }
    }

    private void ChooseDateAndSoOn() {
        final Calendar cal = Calendar.getInstance();
        int mYear = cal.get(Calendar.YEAR);
        final int mMonth = cal.get(Calendar.MONTH);
        int mDay = cal.get(Calendar.DAY_OF_MONTH);
        noOfTimesCalled = 0;

        // Launch Date Picker Dialog
        /*DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if(noOfTimesCalled % 2 == 0) {
                            cal.set(Calendar.YEAR, year);
                            cal.set(Calendar.MONTH, monthOfYear);
                            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            cal.set(Calendar.HOUR_OF_DAY, 0);
                            cal.set(Calendar.MINUTE, 0);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MILLISECOND, 0);
                            //SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                            //String date = "Дата: " + fmt.format(cal.getTime());
                            ChooseRating(cal, monthOfYear);
                        }
                        noOfTimesCalled++;
                    }
                }, mYear, mMonth, mDay);*/

        MonthYearPickerDialog dpd = new MonthYearPickerDialog();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                if (checkIfRemWorkExists(year, monthOfYear)) return;
                if(noOfTimesCalled % 2 == 0) {
                    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, monthOfYear - 1);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    //SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                    //String date = "Дата: " + fmt.format(cal.getTime());
                    Date date = new Date();
                    date.setTime(cal.getTime().getTime());
                    String formattedDate = new SimpleDateFormat("MMM d, yyyy").format(date);
                    ChooseRating(cal, monthOfYear);
                }
                noOfTimesCalled++;
            }
        };
        dpd.setListener(listener);
        dpd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

    // Проверка на то, что запись за такой месяц и год уже есть
    private boolean checkIfRemWorkExists(int year, int month) {
        final SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        boolean exists = false;
        final Cursor cr = db.rawQuery("select PERIOD from I_REM_PLAN_EXEC where C_ISSO="
                + IssoViewActivity.cIsso + " order by PERIOD desc", null);
        if(cr.moveToFirst()) {
            // Вытаскиваем всевозможные записи по этому иссо
            for (int i = 0; i < cr.getCount() && !exists; i++) {
                long period = cr.getLong(cr.getColumnIndex("PERIOD"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(period);
                if (calendar.get(Calendar.YEAR) == year && (calendar.get(Calendar.MONTH) + 1) == month)
                    exists = true;
                cr.moveToNext();
            }
        }
        cr.close();
        if(exists) {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage("Для выбранного Вами месяца (" + getMonth(month)
                    + " " + year + " г.) уже существует оценка работ. Пожалуйста, выберите другую дату.");
            bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = bld.create();
            alert.show();
        }
        return exists;
    }

    public String getMonth(int month) {
        String monthstr = "";
        switch (month) {
            case 1:
                monthstr = "январь";
                break;
            case 2:
                monthstr = "февраль";
                break;
            case 3:
                monthstr = "март";
                break;
            case 4:
                monthstr = "апрель";
                break;
            case 5:
                monthstr = "май";
                break;
            case 6:
                monthstr = "июнь";
                break;
            case 7:
                monthstr = "июль";
                break;
            case 8:
                monthstr = "август";
                break;
            case 9:
                monthstr = "сентябрь";
                break;
            case 10:
                monthstr = "октябрь";
                break;
            case 11:
                monthstr = "ноябрь";
                break;
            case 12:
                monthstr = "декабрь";
                break;
        }
        return monthstr;
    }

    private void ChooseRating( final Calendar cal, final int monthOfYear) {
        final Drawable[] DRAWABLES = new Drawable[] { getResources().getDrawable(R.drawable.draw_1),
                getResources().getDrawable(R.drawable.draw_2), getResources().getDrawable(R.drawable.draw_3),
                getResources().getDrawable(R.drawable.draw_4), getResources().getDrawable(R.drawable.draw_5),
                getResources().getDrawable(R.drawable.draw_6)};
        final String[] items = new String[] { "Работы не проведены", "Очень плохо", "Плохо", "Удовлетворительно", "Хорошо", "Работы проведены полностью"};
        final Dialog dialog = new Dialog(this);
        final int[] CurrentRating = new int[1];
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_all_rating);
        ((TextView) dialog.findViewById(R.id.tvChosenDate)).setText(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
        final ImageView imgChooseAverageRating = (ImageView) dialog.findViewById(R.id.imgChooseAverageRating);
        final TextView tvAverageRating = (TextView) dialog.findViewById(R.id.tvAverageRating);
        SeekBar AverageSeekBar = (SeekBar) dialog.findViewById(R.id.AverageSeekBar);
        Button btnConfirmRating = new Button(getApplicationContext());
        btnConfirmRating.setPadding(0, 10, 0, 10);
        btnConfirmRating.setText(getResources().getString(R.string.confirm));
        btnConfirmRating.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
        ((LinearLayout) dialog.findViewById(R.id.linLayoutAverage)).addView(btnConfirmRating);
        SharedPreferences sp = getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE);
        final int step = sp.getInt("stepSpinner", -1);
        AverageSeekBar.setMax(100 / step);
        //AverageSeekBar.setMax(5);
        imgChooseAverageRating.setImageDrawable(DRAWABLES[0]);
        if(step < 100)
            tvAverageRating.setText(items[0] + " (0%)");
        else
            tvAverageRating.setText(items[0]);
        AverageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int percentage = progress * step;
                if (percentage >=0 && percentage < 20) {
                    imgChooseAverageRating.setImageDrawable(DRAWABLES[0]);
                    if(step < 100)
                        tvAverageRating.setText(items[0] + " (" + percentage + "%)");
                    else
                        tvAverageRating.setText(items[0]);
                }
                else if (percentage >= 20 && percentage < 40) {
                    imgChooseAverageRating.setImageDrawable(DRAWABLES[1]);
                    if(step < 100)
                        tvAverageRating.setText(items[1] + " (" + percentage + "%)");
                    else
                        tvAverageRating.setText(items[1]);
                }
                else if (percentage >= 40 && percentage < 60){
                    imgChooseAverageRating.setImageDrawable(DRAWABLES[2]);
                    if(step < 100)
                        tvAverageRating.setText(items[2] + " (" + percentage + "%)");
                    else
                        tvAverageRating.setText(items[2]);
                }
                else if (percentage >= 60 && percentage < 80) {
                    imgChooseAverageRating.setImageDrawable(DRAWABLES[3]);
                    if(step < 100)
                        tvAverageRating.setText(items[3] + " (" + percentage + "%)");
                    else
                        tvAverageRating.setText(items[3]);
                }
                else if (percentage >= 80 && percentage < 100) {
                    imgChooseAverageRating.setImageDrawable(DRAWABLES[4]);
                    if(step < 100)
                        tvAverageRating.setText(items[4] + " (" + percentage + "%)");
                    else
                        tvAverageRating.setText(items[4]);
                }
                else if (percentage >= 100) {
                    imgChooseAverageRating.setImageDrawable(DRAWABLES[5]);
                    if(step < 100)
                        tvAverageRating.setText(items[5] + " (" + percentage + "%)");
                    else
                        tvAverageRating.setText(items[5]);
                }
                CurrentRating[0] = percentage;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnConfirmRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Location loc = ((LocationManager) getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Intent intent = new Intent(IssoViewActivity.this, NewRemWorks.class);
                intent.putExtra("Calendar", cal.getTime().getTime());
                intent.putExtra("Month", monthOfYear);
                intent.putExtra("location", loc);
                intent.putExtra("Rating", CurrentRating[0]);
                startActivityForResult(intent, 0);
            }
        });
        dialog.show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            finish();
            startActivity(new Intent(IssoViewActivity.this, IssoViewActivity.class).putExtra("C_ISSO", String.valueOf(IssoViewActivity.cIsso)));
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

    private boolean CheckTimeOfLastRating(long dateEdit) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = fmt.format(cal.getTime());
        String dateRating = fmt.format(dateEdit);
        return dateNow.equals(dateRating);
    }

    void logCursor(Cursor c, String title) {
        if (c != null) {
            if (c.moveToFirst()) {
                Log.d("Tag", title + ". " + c.getCount() + " rows");
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
            Log.d("Tag", title + ". Cursor is null");
    }

    private boolean CheckGPS() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location loc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc == null) return  false;

        if(loc.getAccuracy() > 10.0f)
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
}

class tableRow {

    int averageRating;
    String date;
    String Curator;
    long dateMonth;
    long dateEdit;

    tableRow(String Curator, int averageRating, String date, long dateMonth, long dateEdit) {
        this.averageRating = averageRating;
        this.date = date;
        this.Curator = Curator;
        this.dateMonth = dateMonth;
        this.dateEdit = dateEdit;
    }
}

class IssoAdapter extends RecyclerView.Adapter<IssoAdapter.IssoViewHolder> {
    private List<tableRow> mainRows;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class IssoViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView rating;
        private TextView Curator;
        private TextView date;

        IssoViewHolder(View itemView) {
            super(itemView);
            if(MainActivity.theme.equals("0"))
                (itemView.findViewById(R.id.linLayoutIssoRow)).setBackgroundResource(R.drawable.custom_bg_dark);
            else
                (itemView.findViewById(R.id.linLayoutIssoRow)).setBackgroundResource(R.drawable.custom_bg);
            rating = (ImageView) itemView.findViewById(R.id.imgAverageRating);
            Curator = (TextView) itemView.findViewById(R.id.tvIssoCurator);
            date = (TextView) itemView.findViewById(R.id.tvIssoDate);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    IssoAdapter(List<tableRow> mainRows) {
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
        holder.Curator.setText(mainRows.get(position).Curator);
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        String dateRating = fmt.format(mainRows.get(position).dateEdit);
        String date = mainRows.get(position).date.replaceAll("/", ".");
        if(date.equals(dateRating))
            holder.date.setText(date);
        else
            holder.date.setText(date + "/\n" + dateRating);
        int rate = mainRows.get(position).averageRating;
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
        /*switch (mainRows.get(position).averageRating) {
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
        }*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mainRows.size();
    }

    void onItemRemove(int position, final RecyclerView recyclerView) {
        final tableRow row = mainRows.get(position);
        mainRows.remove(position);
        notifyItemRemoved(position);
    }

    void onItemReestablished(int position, final RecyclerView recyclerView, tableRow row) {
        mainRows.add(position, row);
        notifyItemInserted(position);
        recyclerView.scrollToPosition(position);
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
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
}
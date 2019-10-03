package com.development.aisisso.isso_r;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryRemWorks extends Fragment {

    private View v;
    private List<String> N_PERFORMER = new ArrayList<>();
    private List<String> REM_DATE = new ArrayList<>();
    private List<Integer> AVERAGE_REM_RATING = new ArrayList<>();
    private boolean noRemWorks = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        /** Обьявляем layout с таблицей */
        setRetainInstance(true);
        v = inflater.inflate(R.layout.history_rem_layout, container, false);
        findLastRatings(v);
        return v;
    }

    private void showRating(int i) {
        Location loc = ((LocationManager) v.getContext().getSystemService(Context.LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Intent intent = new Intent(v.getContext(), EnterRating.class);
        intent.putExtra("location", loc);
        intent.putExtra("C_ISSO", IssoViewActivity.cIsso);
        startActivityForResult(intent, 0);
    }

    private void findLastRatings(View v) {
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_history);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, v.getResources().getStringArray(R.array.months));
        spinner.setAdapter(adapter);
        // Текущая дата
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        String currentMonth = null;
        switch (month) {
            case 0:
                currentMonth = "01";
                spinner.setSelection(0);
                break;
            case 1:
                currentMonth = "02";
                spinner.setSelection(1);
                break;
            case 2:
                currentMonth = "03";
                spinner.setSelection(2);
                break;
            case 3:
                currentMonth = "04";
                spinner.setSelection(3);
                break;
            case 4:
                currentMonth = "05";
                spinner.setSelection(4);
                break;
            case 5:
                currentMonth = "06";
                spinner.setSelection(5);
                break;
            case 6:
                currentMonth = "07";
                spinner.setSelection(6);
                break;
            case 7:
                currentMonth = "08";
                spinner.setSelection(7);
                break;
            case 8:
                currentMonth = "09";
                spinner.setSelection(8);
                break;
            case 9:
                currentMonth = "10";
                spinner.setSelection(9);
                break;
            case 10:
                currentMonth = "11";
                spinner.setSelection(10);
                break;
            case 11:
                currentMonth = "12";
                spinner.setSelection(11);
                break;
        }
        setRatings(currentMonth);
        //setNewRating(currentMonth);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentMonth = "";
                switch (position) {
                    case 0:
                        currentMonth = "01";
                        break;
                    case 1:
                        currentMonth = "02";
                        break;
                    case 2:
                        currentMonth = "03";
                        break;
                    case 3:
                        currentMonth = "04";
                        break;
                    case 4:
                        currentMonth = "05";
                        break;
                    case 5:
                        currentMonth = "06";
                        break;
                    case 6:
                        currentMonth = "07";
                        break;
                    case 7:
                        currentMonth = "08";
                        break;
                    case 8:
                        currentMonth = "09";
                        break;
                    case 9:
                        currentMonth = "10";
                        break;
                    case 10:
                        currentMonth = "11";
                        break;
                    case 11:
                        currentMonth = "12";
                        break;
                }
                //setNewRating(currentMonth);
                setRatings(currentMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /*private void setNewRating(String currentMonth) {
        SQLiteDatabase db = new DBHelper(v.getContext()).getReadableDatabase();
        final Cursor crInfo;
        N_REM.clear();
        C_REM.clear();
        crInfo = db.query("I_REM_PLAN", new String[] {"N_REM", "C_REM"}, currentMonth + " > 0 and C_ISSO=" + IssoViewActivity.cIsso, null, null, null, null);
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        if(crInfo.moveToFirst()) {
            ArrayList<tableRow> rows = new ArrayList<>();
            for (int i = 0; i < crInfo.getCount(); i++) {
                N_REM.add(crInfo.getString(crInfo.getColumnIndex("N_REM")));
                C_REM.add(crInfo.getInt(crInfo.getColumnIndex("C_REM")));
                final Cursor cr = db.rawQuery("select REM_RATING, COMMENTS, OFFSET, REM_DATE, REM_DATE_EDIT from " +
                        "I_REM_PLAN_EXEC where C_REM=" + crInfo.getInt(crInfo.getColumnIndex("C_REM")) +
                        " and C_ISSO=" + IssoViewActivity.cIsso + " and REM_MONTH > date('now','-2 month') and REM_MONTH < date('now','+1 month')", null);
                cr.moveToFirst();
                int rating;
                if (cr.getCount() > 0) {
                    rating = cr.getInt(cr.getColumnIndex("REM_RATING"));
                } else
                    rating = -1;
                boolean comments = cr.getCount() > 0 && !cr.getString(cr.getColumnIndex("COMMENTS")).equals("");
                String dates = "10.02.2016";
                rows.add(new tableRow(N_REM.get(N_REM.size()-1), rating, comments, dates));
                crInfo.moveToNext();
            }
            ListView listView = (ListView) v.findViewById(R.id.lvMainHistory);
            BoxAdapter adapter = new BoxAdapter(v.getContext(), rows);
            listView.setAdapter(adapter);
        }

    }*/

    private void setRatings(String currentMonth) {
        SQLiteDatabase db = new DBHelper(v.getContext()).getReadableDatabase();
        ArrayList<tableRow> rows = new ArrayList<>();
        N_PERFORMER.clear();
        REM_DATE.clear();
        AVERAGE_REM_RATING.clear();
        final Cursor cr = db.rawQuery("select AVERAGE_REM_RATING, REM_MONTH, N_PERFORMER from " +
                "I_REM_PLAN_CUR where C_ISSO=" + IssoViewActivity.cIsso + " and strftime('%m', REM_MONTH)=" +
                currentMonth + " order by REM_MONTH desc", null);
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++) {
                noRemWorks = false;
                N_PERFORMER.add(cr.getString(cr.getColumnIndex("N_PERFORMER")));
                REM_DATE.add(cr.getString(cr.getColumnIndex("REM_MONTH")));
                AVERAGE_REM_RATING.add(cr.getInt(cr.getColumnIndex("AVERAGE_REM_RATING")));
                //rows.add(new tableRow(N_PERFORMER.get(N_PERFORMER.size() - 1), AVERAGE_REM_RATING.get(AVERAGE_REM_RATING.size() - 1), REM_DATE.get(REM_DATE.size() - 1)));
                cr.moveToNext();
            }
        }
        //ListView listView = (ListView) v.findViewById(R.id.lvMainHistory);
        //BoxAdapter adapter = new BoxAdapter(v.getContext(), rows);
        //listView.setAdapter(adapter);
        if(noRemWorks) {
            TextView tvNoWorks = (TextView) v.findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setText("В этом месяце не зарегистрированы оценки работ по данному ИССО.");
        }
        else {
            TextView tvNoWorks = (TextView) v.findViewById(R.id.tvNoRemWorks);
            tvNoWorks.setText("В данном месяце работы по ИССО не проводятся.");
        }
    }
}



/*class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<tableRow> objects;

    BoxAdapter(Context context, ArrayList<tableRow> products) {
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
            view = lInflater.inflate(R.layout.icon_layout, parent, false);
        }

        tableRow row = getTableRow(position);
        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.tvNPerformer)).setText(row.nameWorks);
        switch (row.rating) {
            case 20:
                ((ImageView) view.findViewById(R.id.imgRatingPicture)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_1));
                break;
            case 30:
                ((ImageView) view.findViewById(R.id.imgRatingPicture)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_2));
                break;
            case 40:
                ((ImageView) view.findViewById(R.id.imgRatingPicture)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_3));
                break;
            case 50:
                ((ImageView) view.findViewById(R.id.imgRatingPicture)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_4));
                break;
            case 60:
                ((ImageView) view.findViewById(R.id.imgRatingPicture)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_5));
                break;
            case 70:
                ((ImageView) view.findViewById(R.id.imgRatingPicture)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_6));
                break;
            default:
                ((ImageView) view.findViewById(R.id.imgRatingPicture)).setImageDrawable(view.getResources().getDrawable(R.drawable.draw_0));
                break;
        }
        ((TextView) view.findViewById(R.id.tvDate)).setText(row.date);

        return view;
    }

    // товар по позиции
    tableRow getTableRow(int position) {
        return ((tableRow) getItem(position));
    }
}*/
package com.development.aisisso.isso_r;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NewRemWorks extends AppCompatActivity {

    private boolean noRemWorks = true;
    private TextView tvDate;
    private Calendar cal;
    private Location loc;
    private ArrayList<createRow> rows = new ArrayList<>();
    private ArrayList<createRow> startRows = new ArrayList<>();
    private CreateAdapter adapter;
    private boolean canEdit = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MainActivity.theme.equals("0"))
            setTheme(R.style.Advanced);
        else {
            setTheme(R.style.AdvancedLight);
        }
        setContentView(R.layout.new_rating_layout);
        cal = Calendar.getInstance();
        boolean PreviewOnly = getIntent().getBooleanExtra("PreviewOnly", false);
        canEdit = getIntent().getBooleanExtra("CanEdit", false);
        if(!PreviewOnly) {
            cal.setTimeInMillis(getIntent().getLongExtra("Calendar", 0));
            int Month = getIntent().getIntExtra("Month", -1);
            loc = getIntent().getParcelableExtra("location");
        }
        final Toolbar toolbar = (Toolbar) findViewById(R.id.new_tabanim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") ) {
            assert ab != null;
            (findViewById(R.id.linLayoutNewRem)).setBackgroundColor(getResources().getColor(R.color.background_material_light));
            if(!PreviewOnly || canEdit)
                ab.setHomeAsUpIndicator(R.drawable.cancel_light);
            else
                ab.setHomeAsUpIndicator(R.drawable.back_light);
        }
        else {
            assert ab != null;
            if(!PreviewOnly || canEdit)
                ab.setHomeAsUpIndicator(R.drawable.cancel_dark);
            else
                ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        if(PreviewOnly)
            getSupportActionBar().setTitle(R.string.title_activity_preview);
        if(canEdit)
            getSupportActionBar().setTitle(R.string.title_activity_edit);

        ab.setDisplayHomeAsUpEnabled(true);
        if(!PreviewOnly && !canEdit)
            ((TextView) findViewById(R.id.tvNewDate)).setText(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
        else
            ((TextView) findViewById(R.id.tvNewDate)).setText(getIntent().getStringExtra("dateRow"));
        ((TextView) findViewById(R.id.tvNewNameCurator)).setText(getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE).getString("nameCurator", ""));
        RecyclerView lvNew = (RecyclerView) findViewById(R.id.lvCreate);
        lvNew.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        lvNew.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lvNew.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        lvNew.setItemAnimator(itemAnimator);
        if(!PreviewOnly)
            setListView(getIntent().getIntExtra("Month", -1), cal.getTime());
        else
            setListPreview();
    }

    private void setListPreview() {
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        final Cursor crInfo;
        crInfo = db.query("I_REM_PLAN_ITEMS", new String[] {"C_ISSO", "C_REM", "PERIOD",
                "RATING", "COMMENTS", "PHOTO_NAME"}, "C_ISSO=" + IssoViewActivity.cIsso +
                " and PERIOD=" + getIntent().getLongExtra("PERIOD", 0), null, null, null, null);
        if(crInfo.moveToFirst()) {
            for (int i = 0; i < crInfo.getCount(); i++) {
                Cursor c = db.query("I_REM_PLAN", new String[] {"N_REM"}, "C_ISSO=" + IssoViewActivity.cIsso +
                        " and C_REM=" + crInfo.getInt(crInfo.getColumnIndex("C_REM")), null, null, null, null);
                if(c.moveToFirst()) {
                    rows.add(new createRow(c.getString(c.getColumnIndex("N_REM")), crInfo.getInt(crInfo.getColumnIndex("C_REM")),
                            crInfo.getInt(crInfo.getColumnIndex("RATING")), crInfo.getString(crInfo.getColumnIndex("COMMENTS")),
                            crInfo.getString(crInfo.getColumnIndex("PHOTO_NAME"))));
                    startRows.add(new createRow(c.getString(c.getColumnIndex("N_REM")), crInfo.getInt(crInfo.getColumnIndex("C_REM")),
                            crInfo.getInt(crInfo.getColumnIndex("RATING")), crInfo.getString(crInfo.getColumnIndex("COMMENTS")),
                            crInfo.getString(crInfo.getColumnIndex("PHOTO_NAME"))));
                }
                crInfo.moveToNext();
            }
            RecyclerView listView = (RecyclerView) findViewById(R.id.lvCreate);
            adapter = new CreateAdapter(rows);
            listView.setAdapter(adapter);
            listView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    showRating(position, true);
                }
            }));
        }
    }

    private void setListView(int month, Date date) {
        String currentMonth = "";
        switch (month) {
            case 1:
                currentMonth = "N1";
                break;
            case 2:
                currentMonth = "N2";
                break;
            case 3:
                currentMonth = "N3";
                break;
            case 4:
                currentMonth = "N4";
                break;
            case 5:
                currentMonth = "N5";
                break;
            case 6:
                currentMonth = "N6";
                break;
            case 7:
                currentMonth = "N7";
                break;
            case 8:
                currentMonth = "N8";
                break;
            case 9:
                currentMonth = "N9";
                break;
            case 10:
                currentMonth = "N10";
                break;
            case 11:
                currentMonth = "N11";
                break;
            case 12:
                currentMonth = "N12";
                break;
        }

        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        final Cursor crInfo;
        crInfo = db.query("I_REM_PLAN", new String[] {"N_REM", "C_REM"}, currentMonth + " > 0 and C_ISSO=" + IssoViewActivity.cIsso, null, null, null, null);
        if(crInfo.moveToFirst()) {
            for (int i = 0; i < crInfo.getCount(); i++) {
                rows.add(new createRow(crInfo.getString(crInfo.getColumnIndex("N_REM")), crInfo.getInt(crInfo.getColumnIndex("C_REM")), getIntent().getIntExtra("Rating", -1), "", ""));
                startRows.add(new createRow(crInfo.getString(crInfo.getColumnIndex("N_REM")), crInfo.getInt(crInfo.getColumnIndex("C_REM")), getIntent().getIntExtra("Rating", -1), "", ""));
                crInfo.moveToNext();
            }
            RecyclerView listView = (RecyclerView) findViewById(R.id.lvCreate);
            adapter = new CreateAdapter(rows);
            listView.setAdapter(adapter);
            listView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    showRating(position, false);
                }
            }));
        }
    }

    private void showRating(int i, boolean isPreview) {
        Location loc = ((LocationManager) getSystemService(Context.LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Intent intent = new Intent(this, EnterRating.class);
        intent.putExtra("PreviewOnly", isPreview);
        intent.putExtra("CanEdit", canEdit);
        intent.putExtra("location", loc);
        intent.putExtra("PERIOD", cal.getTime().getTime());
        intent.putExtra("C_ISSO", IssoViewActivity.cIsso);
        intent.putExtra("Row", rows.get(i));
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        intent.putExtra("timeStamp", timeStamp);
        startActivityForResult(intent, 111);
    }

    //Создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(!getIntent().getBooleanExtra("PreviewOnly", false))
            getMenuInflater().inflate(R.menu.create_menu, menu);
        else if (canEdit) {
            getMenuInflater().inflate(R.menu.edit_menu, menu);
        }
        return true;
    }

    @Override
    public void onBackPressed () {
        if(!getIntent().getBooleanExtra("PreviewOnly", false) || canEdit) {
            if(!startRows.equals(rows)) {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setMessage("Выйти без сохранения?")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder3.show();
            }
            else {
                finish();
            }
        } else {
            finish();
        }
    }


    public boolean onOptionsItemSelected(MenuItem item)	{
        switch(item.getItemId()) {
            case android.R.id.home:
                if(!getIntent().getBooleanExtra("PreviewOnly", false) || canEdit) {
                    if(!startRows.equals(rows)) {
                        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                        builder3.setMessage("Выйти без сохранения?")
                                .setCancelable(false)
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder3.show();
                    }
                    else {
                        finish();
                    }
                } else {
                    finish();
                }
                break;
            case R.id.saveRow:
            case R.id.editRow:
                SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
                if(canEdit) {
                    for(createRow row : rows) {
                        ContentValues cv = new ContentValues();
                        cv.put("C_ISSO", IssoViewActivity.cIsso);
                        cv.put("RATING", row.rating);
                        cv.put("COMMENTS", row.Comment);
                        cv.put("SYNC", 0);
                        db.update("I_REM_PLAN_ITEMS", cv, "C_ISSO=" + IssoViewActivity.cIsso +
                                " and C_REM=" + row.cRem + " and PERIOD=" + getIntent().getLongExtra("PERIOD", 0), null);
                    }
                }
                else for(createRow row : rows) {
                    ContentValues cv = new ContentValues();
                    cv.put("C_ISSO", IssoViewActivity.cIsso);
                    cv.put("C_REM", row.cRem);
                    cv.put("PERIOD", cal.getTime().getTime());
                    cv.put("RATING", row.rating);
                    cv.put("COMMENTS", row.Comment);
                    cv.put("PHOTO_NAME", row.PhotoPath);
                    cv.put("SYNC", 0);
                    db.insert("I_REM_PLAN_ITEMS", null, cv);
                }
                Calendar now = Calendar.getInstance();

                if(canEdit) {
                    ContentValues cv = new ContentValues();
                    //cv.put("C_ISSO", IssoViewActivity.cIsso);
                    //cv.put("PERIOD", getIntent().getLongExtra("PERIOD", 0));
                    /*int rating = 0;
                    for (createRow row : rows)
                        rating += row.rating;
                    int divide = rating % rows.size();
                    rating /= rows.size();
                    if (divide >= 5)
                        rating++;
                    cv.put("AVERAGE_REM_RATING", rating);*/
                    //cv.put("PERFORMER", getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE).getString("nameCurator", ""));
                    cv.put("EDIT_DATE", now.getTime().getTime());
                    cv.put("SYNC", 0);
                    db.update("I_REM_PLAN_EXEC",cv, "C_ISSO=" + IssoViewActivity.cIsso + " and PERIOD="
                            + getIntent().getLongExtra("PERIOD", 0), null);
                }
                else {
                    TimeZoneMapper timeZoneMapper = new TimeZoneMapper(NewRemWorks.this);
                    String timezone = timeZoneMapper.latLngToTimezoneString(loc.getLatitude(), loc.getLongitude());
                    Calendar c = Calendar.getInstance();
                    c.setTimeZone(TimeZone.getTimeZone(timezone));
                    c.setTimeInMillis(loc.getTime());
                    ContentValues cv = new ContentValues();
                    cv.put("C_ISSO", IssoViewActivity.cIsso);
                    cv.put("PERIOD", cal.getTime().getTime());
                    cv.put("PERFORMER", getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE).getString("nameCurator", ""));
                    cv.put("ACTUAL_DATE", c.getTime().getTime());
                    cv.put("EDIT_DATE", now.getTime().getTime());
                    cv.put("OFFSET", c.getTimeZone().getRawOffset());
                    cv.put("LATITUDE_REM", loc.getLatitude());
                    cv.put("LONGITUDE_REM", loc.getLongitude());
                    cv.put("SYNC", 0);
                    db.insert("I_REM_PLAN_EXEC", null, cv);
                }
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            createRow remWork = (createRow) data.getSerializableExtra("REM_WORKS");
            if(remWork != null) {
                int index = 0;
                for (createRow row : rows) {
                    if (row.cRem == remWork.cRem) {
                        if(canEdit) {
                            rows.get(index).Comment = remWork.Comment;
                            rows.get(index).rating = remWork.rating;
                        }
                        else
                            rows.set(index, remWork);
                    } else {
                        index++;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}

class createRow implements Serializable{
    String nameWorks;
    int rating;
    String Comment;
    String PhotoPath;
    int cRem;

    createRow(String nameWorks, int cRem, int rating, String Comment, String PhotoPath) {
        this.nameWorks = nameWorks;
        this.rating = rating;
        this.Comment = Comment;
        this.PhotoPath = PhotoPath;
        this.cRem = cRem;
    }

    @Override
    public boolean equals(Object o) {
        return this.nameWorks.equals(((createRow) o).nameWorks) && this.rating == ((createRow) o).rating &&
                this.Comment.equals(((createRow) o).Comment) && this.PhotoPath.equals(((createRow) o).PhotoPath)
                && cRem == ((createRow) o).cRem;
    }
}

class CreateAdapter extends RecyclerView.Adapter<CreateAdapter.CreateViewHolder> {
    ArrayList<createRow> objects;

    public static class CreateViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView rating;
        private TextView nameWorks;
        private ImageView comments;
        private ImageView photos;

        public CreateViewHolder(View itemView) {
            super(itemView);
            if(MainActivity.theme.equals("0"))
                (itemView.findViewById(R.id.linLayoutCreateRow)).setBackgroundResource(R.drawable.custom_bg_dark);
            else
                (itemView.findViewById(R.id.linLayoutCreateRow)).setBackgroundResource(R.drawable.custom_bg);
            rating = (ImageView) itemView.findViewById(R.id.imgRating);
            comments = (ImageView) itemView.findViewById(R.id.imgComment);
            photos = (ImageView) itemView.findViewById(R.id.imgPhoto);
            nameWorks = (TextView) itemView.findViewById(R.id.tvCreateNRem);
        }
    }

    CreateAdapter(ArrayList<createRow> products) {
        this.objects = products;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return objects.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CreateAdapter.CreateViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_row_layout, parent, false);
        return new CreateViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CreateViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nameWorks.setText(objects.get(position).nameWorks);
        int rate = objects.get(position).rating;
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
        /*switch (rating) {
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
        if(!objects.get(position).Comment.equals("")) {
            if (MainActivity.theme.equals("0"))
                holder.comments.setImageResource(R.drawable.comments_dark);
            else
                holder.comments.setImageResource(R.drawable.comments_light);
        }
        else {
            if(MainActivity.theme.equals("0"))
                holder.comments.setImageResource(R.drawable.no_comments_dark);
            else
                holder.comments.setImageResource(R.drawable.no_comments_light);
        }
        if(objects.get(position).PhotoPath.equals("")) {
            if(MainActivity.theme.equals("0"))
                holder.photos.setImageResource(R.drawable.no_photo_dark);
            else
                holder.photos.setImageResource(R.drawable.no_photo_light);
        }
        else {
            if(MainActivity.theme.equals("0"))
                holder.photos.setImageResource(R.drawable.photo_dark);
            else
                holder.photos.setImageResource(R.drawable.photo_light);
        }
    }
}
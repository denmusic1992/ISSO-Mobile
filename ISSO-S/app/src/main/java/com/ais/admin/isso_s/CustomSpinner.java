package com.ais.admin.isso_s;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class CustomSpinner extends Activity implements View.OnClickListener{

    /**************  Intialize Variables *************/


    private final static String[] items = new String[] { "Без изменений", "Незначительное ухудшение", "Ухудшение", "Значительное ухудшение", "Авария", "Улучшение" };
    private final static Integer[] icons = new Integer[] { R.drawable.draw_1, R.drawable.draw_2, R.drawable.draw_3,
            R.drawable.draw_4, R.drawable.draw_6, R.drawable.draw_5};
    private Location location;
    private int CurrentRating = 0;
    private SeekBar seekBar;
    private TextView tvAdvancedChooseRating;
    private boolean editable;
    private boolean previewed;
    private Spinner spinnerExample;

    public CustomSpinner() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        this.setFinishOnTouchOutside(false);
        editable = getIntent().getBooleanExtra("Editable", false);
        previewed = getIntent().getBooleanExtra("Preview", false);
        final int AllRating;
        if(!editable) {
            location = getIntent().getParcelableExtra("location");
            AllRating = getIntent().getIntExtra("AllRating", 0);
            CurrentRating = getIntent().getIntExtra("CurrentRating", 0) - 1;
        }
        else {
            AllRating = getIntent().getIntExtra("AllRating", 0) - getIntent().getIntExtra("CurrentRating", 0);
            CurrentRating = getIntent().getIntExtra("CurrentRating", 0) - 1;
        }

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tvAdvancedChooseRating = (TextView) findViewById(R.id.tvAdvancedChooseRating);

        seekBar.setVisibility(View.GONE);
        tvAdvancedChooseRating.setVisibility(View.GONE);

        spinnerExample = (Spinner) findViewById(R.id.ratingSpinner);
        final Button btnConfirmRating = new Button(getApplicationContext());
        btnConfirmRating.setPadding(10, 10, 10, 10);
        btnConfirmRating.setText(getResources().getString(R.string.confirm));
        btnConfirmRating.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ((LinearLayout) findViewById(R.id.linLayoutSetRating)).addView(btnConfirmRating);
        if(editable) {
            btnConfirmRating.setText("Изменить");
        }
        else if (previewed) {
            btnConfirmRating.setText("OK");
        }
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            setTheme(R.style.DialogTheme);
        }
        else {
            setTheme(R.style.DialogThemeLight);
            (findViewById(R.id.linLayoutSetRating)).setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((TextView) findViewById(R.id.tvChooseRating)).setTextColor(getResources().getColor(R.color.background_material_dark));
            ((TextView) findViewById(R.id.tvCommentRating)).setTextColor(getResources().getColor(R.color.background_material_dark));
            tvAdvancedChooseRating.setTextColor(getResources().getColor(R.color.background_material_dark));
            ((EditText) findViewById(R.id.editTextCommentRating)).setTextColor(getResources().getColor(R.color.background_material_dark));
            (findViewById(R.id.editTextCommentRating)).setBackgroundColor(getResources().getColor(R.color.almostTransparent));
            ((EditText) findViewById(R.id.editTextCommentRating)).setText("");
            btnConfirmRating.setHighlightColor(getResources().getColor(R.color.background_material_dark));
            //btnEdit.setHighlightColor(getResources().getColor(R.color.background_material_dark));
        }

        ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<>();
        if(AllRating < 0)
            for (int i = 0; i < 6; i++ ) {
                SpinnerModel spinnerModel = new SpinnerModel();
                spinnerModel.setRating(items[i]);
                spinnerModel.setImage(icons[i]);
                CustomListViewValuesArr.add(spinnerModel);
            }
        else {
            for (int i = 0; i < 5; i++ ) {
                SpinnerModel spinnerModel = new SpinnerModel();
                spinnerModel.setRating(items[i]);
                spinnerModel.setImage(icons[i]);
                CustomListViewValuesArr.add(spinnerModel);
            }
        }
        if(previewed) {
            findViewById(R.id.editTextCommentRating).setEnabled(false);
            spinnerExample.setEnabled(false);
            spinnerExample.setClickable(false);
            seekBar.setEnabled(false);
        }

        // Create custom adapter object ( see below CustomAdapter.java )
        CustomAdapter adapter = new CustomAdapter(CustomSpinner.this, R.layout.rating_spinner_row, CustomListViewValuesArr);

        // Set adapter to spinner
        spinnerExample.setAdapter(adapter);

        // Listener called when spinner item selected
        spinnerExample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                switch (position) {
                    case 0:
                        CurrentRating = 0;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        break;
                    case 1:
                        CurrentRating = -1;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        break;
                    case 2:
                        CurrentRating = -2;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        break;
                    case 3:
                        CurrentRating = -3;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        break;
                    case 4:
                        CurrentRating = -10;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        break;
                    case 5:
                        if(AllRating < -1) {
                            seekBar.setVisibility(View.VISIBLE);
                            tvAdvancedChooseRating.setVisibility(View.VISIBLE);
                            CurrentRating = seekBar.getProgress() + 1;
                            tvAdvancedChooseRating.setText(getResources().getString(R.string.advancedChooseRating) + " (1-" + -AllRating + "): " + String.valueOf(CurrentRating));
                            seekBar.setMax(-AllRating - 1);
                            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                    CurrentRating = progress + 1;
                                    tvAdvancedChooseRating.setText(getResources().getString(R.string.advancedChooseRating) + " (1-" + -AllRating + "): " + String.valueOf(CurrentRating));
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                }
                            });
                        }
                        else if (AllRating < 0) {
                            CurrentRating = 1;
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });




        //((EditText) findViewById(R.id.editTextCommentRating)).setSingleLine();
        btnConfirmRating.setOnClickListener(this);

        if(editable || previewed) {
            ((EditText) findViewById(R.id.editTextCommentRating)).setText(getIntent().getStringExtra("Comments"));
            switch(getIntent().getIntExtra("CurrentRating", 0)) {
                case 0:
                    spinnerExample.setSelection(0);
                    break;
                case -1:
                    spinnerExample.setSelection(1);
                    break;
                case -2:
                    spinnerExample.setSelection(2);
                    break;
                case -3:
                    spinnerExample.setSelection(3);
                    break;
                case -10:
                    spinnerExample.setSelection(4);
                    break;
                default:
                    if(AllRating < -1) {
                        spinnerExample.setSelection(5);
                        seekBar.setVisibility(View.VISIBLE);
                        tvAdvancedChooseRating.setVisibility(View.VISIBLE);
                        tvAdvancedChooseRating.setText(getResources().getString(R.string.advancedChooseRating) + " (1-" + -AllRating + "): " + String.valueOf(CurrentRating + 1));
                        seekBar.setMax(-AllRating - 1);
                        seekBar.setProgress(CurrentRating);
                    }
                    else if (AllRating < 0) {
                        spinnerExample.setSelection(5);
                    }
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        ContentValues vals = new ContentValues();
        SQLiteDatabase db = new DBHelper(CustomSpinner.this).getWritableDatabase();
        Calendar now = Calendar.getInstance();
        if(!editable && !previewed){
            TimeZoneMapper timeZoneMapper = new TimeZoneMapper(CustomSpinner.this);
            String timezone = timeZoneMapper.latLngToTimezoneString(location.getLatitude(), location.getLongitude());
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone(timezone));
            cal.setTimeInMillis(location.getTime());

            vals.put("C_ISSO", getIntent().getStringExtra("C_ISSO"));
            vals.put("CURRENTRATING", CurrentRating);
            vals.put("RATING", (spinnerExample.getSelectedItemPosition() + 2) * 10);
            vals.put("RATINGDATE", cal.getTime().getTime());
            vals.put("COMMENTS", ((EditText) findViewById(R.id.editTextCommentRating)).getText().toString());
            vals.put("LATITUDE_RATING", location.getLatitude());
            vals.put("LONGITUDE_RATING", location.getLongitude());
            vals.put("OFFSET", cal.getTimeZone().getRawOffset());
            vals.put("RATINGDATEEDIT", now.getTime().getTime());
            vals.put("SYNC", 0);
            db.insert("RATING", null, vals);
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
        else if(!previewed){
            vals.put("C_ISSO", getIntent().getStringExtra("C_ISSO"));
            vals.put("CURRENTRATING", CurrentRating);
            vals.put("RATING", (spinnerExample.getSelectedItemPosition() + 2) * 10);
            vals.put("RATINGDATE", getIntent().getLongExtra("Date", 0));
            vals.put("COMMENTS", ((EditText) findViewById(R.id.editTextCommentRating)).getText().toString());
            vals.put("LATITUDE_RATING", getIntent().getDoubleExtra("Latitude", 0));
            vals.put("LONGITUDE_RATING", getIntent().getDoubleExtra("Longitude", 0));
            vals.put("OFFSET", getIntent().getLongExtra("Offset", 0));
            vals.put("RATINGDATEEDIT", now.getTime().getTime());
            vals.put("SYNC", 0);
            db.update("RATING", vals, "C_ISSO=" + getIntent().getStringExtra("C_ISSO") + " and RATINGDATE=" +  getIntent().getLongExtra("Date", 0), null);
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
        else {
            finish();
        }
    }
}

class SpinnerModel {

    private String Rating;
    private Integer Image;

    /*********** Set Methods ******************/
    public void setRating(String Rating) {
        this.Rating = Rating;
    }

    public void setImage(Integer Image) {
        this.Image = Image;
    }


    /*********** Get Methods ****************/
    public String getRating() {
        return this.Rating;
    }

    public Integer getImage() {
        return this.Image;
    }
}


/***** Adapter class extends with ArrayAdapter ******/
class CustomAdapter extends ArrayAdapter<String> {

    private ArrayList data;
    LayoutInflater inflater;
    CustomSpinner customSpinner;

    /*************
     * CustomAdapter Constructor
     *****************/
    public CustomAdapter( CustomSpinner activitySpinner, int textViewResourceId, ArrayList objects) {
        super(activitySpinner, textViewResourceId, objects);

        //********* Take passed values **********/
        data = objects;
        this.customSpinner = activitySpinner;

        //**********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater) activitySpinner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        //********* Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.rating_spinner_row, parent, false);
        if (!PreferenceManager.getDefaultSharedPreferences(customSpinner.getBaseContext()).getString("Theme", "0").equals("0")) {
            (row.findViewById(R.id.linLayoutRow)).setBackgroundColor(customSpinner.getResources().getColor(R.color.background_material_light));
            ((TextView) row.findViewById(R.id.tvRating)).setTextColor(customSpinner.getResources().getColor(R.color.background_material_dark));
        }

        //**** Get each Model object from Arraylist ********/
        SpinnerModel tempValues = (SpinnerModel) data.get(position);

        TextView label = (TextView) row.findViewById(R.id.tvRating);
        ImageView companyLogo = (ImageView) row.findViewById(R.id.image);

        // Set values for spinner each row
        label.setText(tempValues.getRating());
        companyLogo.setImageResource(tempValues.getImage());

        return row;
    }

}
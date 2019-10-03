package com.development.aisisso.isso_i;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListPreference extends ListPreference {

    CustomListPreferenceAdapter customListPreferenceAdapter = null;
    Context mContext;
    private LayoutInflater mInflater;
    CharSequence[] entries;
    CharSequence[] entryValues;
    ArrayList<RadioButton> rButtonList = null;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public CustomListPreference(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        rButtonList = new ArrayList<>();
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = prefs.edit();
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder)
    {
        entries = getEntries();
        entryValues = getEntryValues();

        if (entries == null || entryValues == null || entries.length != entryValues.length )
        {
            throw new IllegalStateException(
                    "ListPreference requires an entries array and an entryValues array which are both the same length");
        }

        customListPreferenceAdapter = new CustomListPreferenceAdapter(mContext);

        builder.setAdapter(customListPreferenceAdapter, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

    }

    private class CustomListPreferenceAdapter extends BaseAdapter
    {
        public CustomListPreferenceAdapter(Context context)
        {

        }

        public int getCount()
        {
            return entries.length;
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            CustomHolder holder;

            if(row == null)
            {
                row = mInflater.inflate(R.layout.list_pref_row, parent, false);
                holder = new CustomHolder(row, position);
                row.setTag(holder);
                row.setClickable(true);
                row.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        for(RadioButton rb : rButtonList)
                        {
                            if(rb.getId() != position)
                                rb.setChecked(false);
                            else
                                rb.setChecked(true);
                        }
                        int index = position;
                        int value = Integer.valueOf((String) entryValues[index]);
                        editor.putInt("index", value);
                    }
                });
            }
            if(PreferenceManager.getDefaultSharedPreferences(getContext()).getInt("index", -1) == position)
                rButtonList.get(position).setChecked(true);
            return row;
        }

        class CustomHolder
        {
            private TextView text = null;
            private TextView textAdvanced = null;
            private RadioButton rButton = null;

            CustomHolder(View row, int position)
            {
                text = (TextView)row.findViewById(R.id.tv_mainInfoAboutIsso);
                text.setText(entries[position]);
                textAdvanced = (TextView) row.findViewById(R.id.custom_list_view_row_text_view);
                textAdvanced.setText(entries[position]);
                rButton = (RadioButton)row.findViewById(R.id.custom_list_view_row_radio_button);
                rButton.setId(position);

                // also need to do something to check your preference and set the right button as checked

                boolean has = false;
                for(RadioButton rb : rButtonList) {
                    if(rb.getId() == rButton.getId())
                        has = true;
                }
                if(!has)
                    rButtonList.add(rButton);
                rButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        if(isChecked)
                        {
                            for(RadioButton rb : rButtonList)
                            {
                                if(rb != buttonView)
                                    rb.setChecked(false);
                                else
                                    rb.setChecked(true);
                            }

                            int index = buttonView.getId();
                            int value = Integer.valueOf((String) entryValues[index]);
                            editor.putInt("yourPref", value);
                        }
                    }
                });
            }
        }
    }

    /*Context mContext;
    private LayoutInflater mInflater;
    CharSequence[] entries;
    CharSequence[] entryValues;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    TextView txtInfo;
    TextView txtAdvanced;
    View convertView;
    int index;


    public CustomListPreference(Context context, AttributeSet set) {
        super(context, set);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public void setInformation(UserCollection collection, int index) {
        ((TextView) convertView.findViewById(R.id.tv_mainInfoAboutIsso)).setText(collection.CollectionName + " (" + collection.IssoList.split(",").length + ") от " +
                SettingsActivity.getDate(collection.DateCreate));
        ((TextView) convertView.findViewById(R.id.custom_list_view_row_text_view)).setText("Наименовение: \t" + collection.CollectionName + "\nДата создания: \t" +
                SettingsActivity.getDate(collection.DateCreate) + "\nОписание: \t" +
                collection.Description);
        this.index = index;
    }

    public View getView(View convertView, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.list_pref_row, null, false);

            txtInfo = (TextView) convertView.findViewById(R.id.tv_mainInfoAboutIsso);
            txtAdvanced = (TextView) convertView.findViewById(R.id.custom_list_view_row_text_view);
            txtInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        this.convertView = convertView;
        return convertView;
    }*/
}

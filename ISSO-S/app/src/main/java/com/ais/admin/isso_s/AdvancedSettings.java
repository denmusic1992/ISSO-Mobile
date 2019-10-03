package com.ais.admin.isso_s;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.util.Map;

public class AdvancedSettings extends AppCompatActivity {


    public AdvancedSettings() {}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            setTheme(R.style.Advanced);
        }
        else {
            setTheme(R.style.AdvancedLight);
        }
        setContentView(R.layout.advanced_settings);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.advanced_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.relLayoutAdvanced);
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_light);
        }
        else {
            assert ab != null;
            ab.setHomeAsUpIndicator(R.drawable.back_dark);
        }
        ab.setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new AdvancedSettingsFragment())
                .commit();

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

    public static class AdvancedSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_advanced);
            setRetainInstance(true);

            Map<String, ?> keys = PreferenceManager.getDefaultSharedPreferences(getActivity()).getAll();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            prefs.registerOnSharedPreferenceChangeListener(this);
            for(Map.Entry<String,?> entry : keys.entrySet()) {
                Preference pref = findPreference(entry.getKey());
                if (pref instanceof ListPreference) {
                    ListPreference preference = (ListPreference) pref;
                    preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            /*if (newValue.equals(Utils.THEME_DARK))
                                Utils.changeToTheme(getActivity(), Utils.THEME_DARK);
                            else
                                Utils.changeToTheme(getActivity(), Utils.THEME_WHITE);*/
                            getActivity().finish();
                            getActivity().startActivity(new Intent(getActivity(), getActivity().getClass()));
                            return true;
                        }
                    });
                    pref.setSummary(((ListPreference) findPreference(entry.getKey())).getEntry());
                } else if (entry.getKey().equals("Address")) {
                    if(entry.getValue().toString().equals("")) {
                        pref.setSummary("aisisso.ru");
                    }
                    else {
                        pref.setSummary(entry.getValue().toString());
                    }
                }
                else if (entry.getKey().equals("Port")) {
                    if(entry.getValue().toString().equals("")) {
                        pref.setSummary("8789");
                    }
                    else {
                        pref.setSummary(entry.getValue().toString());
                    }
                }
                else if (entry.getKey().equals("SupPort")) {
                    if(entry.getValue().toString().equals("")) {
                        pref.setSummary("8080");
                    }
                    else {
                        pref.setSummary(entry.getValue().toString());
                    }
                }
                else if (entry.getKey().equals("Voice")) {
                    CheckBoxPreference checkBox = (CheckBoxPreference) findPreference("Voice");
                    ((CheckBoxPreference) pref).setChecked(checkBox.isChecked());
                }
                else if (entry.getKey().equals("HideButtons")) {
                    CheckBoxPreference checkBox = (CheckBoxPreference) findPreference("HideButtons");
                    ((CheckBoxPreference) pref).setChecked(checkBox.isChecked());
                }
            }

            EditTextPreference prefLogin = (EditTextPreference)findPreference("Address");
            prefLogin.setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            EditTextPreference editPref = (EditTextPreference) preference;
                            if (editPref.getText() != null)
                                editPref.getEditText().setSelection(editPref.getText().length());
                            return true;
                        }
                    });
            EditTextPreference prefPort = (EditTextPreference)findPreference("Port");
            prefLogin.setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            EditTextPreference editPref = (EditTextPreference) preference;
                            if (editPref.getText() != null)
                                editPref.getEditText().setSelection(editPref.getText().length());
                            return true;
                        }
                    });
            EditTextPreference prefsupPort = (EditTextPreference)findPreference("SupPort");
            prefLogin.setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            EditTextPreference editPref = (EditTextPreference) preference;
                            if (editPref.getText() != null)
                                editPref.getEditText().setSelection(editPref.getText().length());
                            return true;
                        }
                    });
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
        {
            // Set summary to be the user-description for the selected value
            Preference pref = findPreference(key);
            if(pref instanceof ListPreference)
                pref.setSummary(((ListPreference) findPreference(key)).getEntry());
            else if(pref instanceof EditTextPreference)
                pref.setSummary(sharedPreferences.getString(key, ""));
        }
    }
}


class Utils {
    public final static int THEME_DARK = 0;
    public final static int THEME_WHITE = 1;

    /** * Set the theme of the Activity, and restart it by creating a new Activity of the same type. */

    public static void changeToTheme(Activity activity, int theme) {
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
}

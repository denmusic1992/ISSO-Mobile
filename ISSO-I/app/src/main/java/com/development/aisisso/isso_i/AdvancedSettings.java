package com.development.aisisso.isso_i;

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
import android.widget.EditText;
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

    public class AdvancedSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_advanced);
            setRetainInstance(true);

            Map<String, ?> keys = PreferenceManager.getDefaultSharedPreferences(AdvancedSettings.this).getAll();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AdvancedSettings.this);
            prefs.registerOnSharedPreferenceChangeListener(this);
            for(Map.Entry<String,?> entry : keys.entrySet()) {
                Preference pref = findPreference(entry.getKey());
                if (pref instanceof ListPreference) {
                    ListPreference preference = (ListPreference) pref;
                    preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            if (newValue.equals(Utils.THEME_DARK))
                                Utils.changeToTheme(AdvancedSettings.this, Utils.THEME_DARK);
                            else
                                Utils.changeToTheme(AdvancedSettings.this, Utils.THEME_WHITE);
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
            for (Map.Entry<String, ?> entry : keys.entrySet()) {
                if (!entry.getKey().equals("Pass")) {
                    Preference pref = findPreference(entry.getKey());
                    if (pref instanceof CheckBoxPreference) {
                        CheckBoxPreference check = (CheckBoxPreference) pref;
                        check.setChecked(true);
                    } else {
                        if (pref instanceof EditTextPreference)
                            pref.setSummary(entry.getValue().toString());
                    }
                } else {
                    Preference prePreference = findPreference(entry.getKey());
                    EditText edit = ((EditTextPreference) prePreference).getEditText();
                    String pref = edit.getTransformationMethod().getTransformation(entry.getValue().toString(), edit).toString();
                    prePreference.setSummary(pref);
                }
            }
            if (keys.size() == 0) {
                Preference prePreference = findPreference("Pass");
                EditText edit = ((EditTextPreference) prePreference).getEditText();
                String pref = "";
                prePreference.setSummary(pref);

                // Set summary to be the user-description for the selected value
                Preference preference = findPreference("Login");
                if (preference instanceof EditTextPreference)
                    preference.setSummary("");
            }

            prefLogin = (EditTextPreference) findPreference("Login");
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

            EditTextPreference prefPass = (EditTextPreference) findPreference("Pass");
            prefPass.setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            EditTextPreference editPref = (EditTextPreference) preference;
                            if (editPref.getText() != null)
                                editPref.getEditText().setSelection(editPref.getText().length());
                            return true;                        }
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
            if (key.equals("Pass")) {
                Preference prePreference = findPreference(key);
                EditText edit = ((EditTextPreference) prePreference).getEditText();
                String preference = edit.getTransformationMethod().getTransformation(sharedPreferences.getString(key, ""), edit).toString();
                prePreference.setSummary(preference);
            } else {

                // Set summary to be the user-description for the selected value
                Preference preference = findPreference(key);
                if (preference instanceof ListPreference)
                    preference.setSummary(((ListPreference) findPreference(key)).getEntry());
                else if (preference instanceof EditTextPreference)
                    preference.setSummary(sharedPreferences.getString(key, ""));
            }
        }
    }
}


class Utils {
    public final static int THEME_DARK = 0;
    public final static int THEME_WHITE = 1;

    /** * Set the theme of the Activity, and restart it by creating a new Activity of the same type. */

    public static void changeToTheme(AppCompatActivity activity, int theme) {
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
}

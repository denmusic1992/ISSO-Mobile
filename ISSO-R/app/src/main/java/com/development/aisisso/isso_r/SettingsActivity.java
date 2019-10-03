package com.development.aisisso.isso_r;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;


/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity {

    public SettingsActivity() {}
    private boolean isLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Присвоение стиля
        if (MainActivity.theme.equals("0")) {
            setTheme(R.style.DialogTheme);
            isLight = false;
        }
        else {
            setTheme(R.style.DialogThemeLight);
            isLight = true;
        }
        // Подключение xml во фрагмент
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();
        }
        PreferenceManager.setDefaultValues(SettingsActivity.this, R.xml.pref_general, false);
    }

    @SuppressLint("ValidFragment")
    public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setRetainInstance(true);
            Map<String, ?> keys = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this).getAll();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
            prefs.registerOnSharedPreferenceChangeListener(this);
            for(Map.Entry<String,?> entry : keys.entrySet())
            {
                if(!entry.getKey().equals("Pass"))
                {
                    Preference pref = findPreference(entry.getKey());
                    if(pref instanceof CheckBoxPreference) {
                        CheckBoxPreference check = (CheckBoxPreference) pref;
                        check.setChecked(true);
                    }
                    else {
                        if (pref instanceof EditTextPreference)
                            pref.setSummary(entry.getValue().toString());
                    }
                }
                else {
                    Preference prePreference = findPreference(entry.getKey());
                    EditText edit = ((EditTextPreference) prePreference).getEditText();
                    String pref = edit.getTransformationMethod().getTransformation(entry.getValue().toString(), edit).toString();
                    prePreference.setSummary(pref);
                }
            }
            if(keys.size() == 0) {
                Preference prePreference = findPreference("Pass");
                String pref = "";
                prePreference.setSummary(pref);

                // Set summary to be the user-description for the selected value
                Preference preference = findPreference("Login");
                if (preference instanceof EditTextPreference)
                    preference.setSummary("");
            }

            EditTextPreference prefLogin = (EditTextPreference)findPreference("Login");
            prefLogin.setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            EditTextPreference editPref = (EditTextPreference) preference;
                            if(editPref.getText() != null)
                                editPref.getEditText().setSelection(editPref.getText().length());
                            return true;
                        }
                    });

            EditTextPreference prefPass = (EditTextPreference)findPreference("Pass");
            prefPass.setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            EditTextPreference editPref = (EditTextPreference) preference;
                            if(editPref.getText() != null)
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
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)		{
            if(key.equals("Pass")) {
                Preference prePreference = findPreference(key);
                EditText edit = ((EditTextPreference) prePreference).getEditText();
                String pref = edit.getTransformationMethod().getTransformation(sharedPreferences.getString(key, ""), edit).toString();
                prePreference.setSummary(pref);
            }
            else {

                // Set summary to be the user-description for the selected value
                Preference pref = findPreference(key);
                if (pref instanceof ListPreference)
                    pref.setSummary(((ListPreference) findPreference(key)).getEntry());
                else if (pref instanceof EditTextPreference)
                    pref.setSummary(sharedPreferences.getString(key, ""));
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            //LinearLayout v = new LinearLayout(inflater.getContext());
            LinearLayout v = (LinearLayout) super.onCreateView(inflater, container, savedInstanceState);
            assert v != null;
            if(isLight) {
                v.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            }
            else {
                v.setBackgroundColor(getResources().getColor(R.color.background_material_dark));
            }
            Button btn = new Button(getApplicationContext());
            btn.setText("Синхронизация");

            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWifi.isConnected()) {
                CheckBoxPreference mCheckBoxPref = (CheckBoxPreference) findPreference("AllowToDownloadPhotos");
                PreferenceCategory mCategory = (PreferenceCategory) findPreference("category");
                if(mCheckBoxPref != null)
                    mCategory.removePreference(mCheckBoxPref);
            }

            v.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBoxPreference check = (CheckBoxPreference) findPreference("AllowToDownloadPhotos");
                    if(check != null && check.isChecked()) {
                        AlertDialog.Builder builder3 = new AlertDialog.Builder(SettingsActivity.this);
                        builder3.setMessage("Вы не подключены к сети wi-fi. Загрузка фотографий будет осуществляться через мобильный интернет. Продолжить?")
                                .setCancelable(false)
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        startSync(true);
                                    }
                                })
                                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }

                                });
                        builder3.show();
                    }
                    else if (check != null){
                        startSync(false);
                    }
                    else {
                        startSync(true);
                    }
                }
            });
            if(sm != null) {
                CheckBoxPreference check = (CheckBoxPreference) findPreference("AllowToDownloadPhotos");
                if (check != null && !check.isChecked()){
                    startSync(false);
                }
                else {
                    startSync(true);
                }
            }

            return v;
        }
    }

    SyncModule sm;

    public void startSync(boolean download)
    {
        String login = PreferenceManager.getDefaultSharedPreferences(this).getString("Login", null);
        String pass = PreferenceManager.getDefaultSharedPreferences(this).getString("Pass", null);
        //String host = PreferenceManager.getDefaultSharedPreferences(this).getString("Host", null);
        //String port = PreferenceManager.getDefaultSharedPreferences(this).getString("Port", "8789");
        assert pass != null;
        assert login != null;
        if (login.equals("") || pass.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Важное сообщение!")
                    .setMessage("Необходимо настроить параметры синхронизации.")
                    .setCancelable(true)
                    .setPositiveButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) { dialog.cancel(); }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }


        //sm = (SyncModule) getLastNonConfigurationInstance();
        if(sm == null) {
            sm = new SyncModule(SettingsActivity.this, myHandler);
            sm.user = login;//PreferenceManager.getDefaultSharedPreferences(this).getString("Login", null);
            sm.pass = pass;//PreferenceManager.getDefaultSharedPreferences(this).getString("Pass", null);
            sm.host = PreferenceManager.getDefaultSharedPreferences(this).getString("Address", "aisisso.ru").toLowerCase().replaceAll(" ", "");//PreferenceManager.getDefaultSharedPreferences(this).getString("Host", null);
            sm.port = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString("Port", "8789")); //Integer.parseInt(getResources().getString(R.string.port));//PreferenceManager.getDefaultSharedPreferences(this).getString("Port", null);
            sm.download = download;
            sm.dbHelper = MainActivity.dbHelper;
            sm.execute();
        }
        else {
            sm.attach(this);
            if(sm.dialog.isShowing()) {
                sm.dialog = new ProgressDialog(SettingsActivity.this);
                sm.dialog.setIndeterminate(true);
                sm.dialog.setCancelable(false);
                sm.dialog.show();
                updateProgress(sm, sm.getProgress());
            }
        }
        //RefreshIssoList();
    }

    void updateProgress(SyncModule sm, String progress) {
        sm.dialog.setMessage(progress);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        if(sm != null)
            sm.detach();

        return(sm);
    }


    private static class SyncModule extends AsyncTask<Void, String, Void> {
        String user;
        String pass;
        String host;
        public int port;
        public ProgressDialog dialog;
        private SettingsActivity context;
        private Handler myHandler;
        private boolean download;

        // Для SOAP
        private static final String subFile = "ais7UpdateServerSecureBinding?wsdl";
        private static final String[] SOAP_ACTION = {"http://tempuri.org/Iais7ISSOSecureService/HttpsGetSessionId",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsGetIssoList", "http://tempuri.org/Iais7ISSOSecureService/HttpsGetRemInfo",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsSetWorkRemRating", "http://tempuri.org/Iais7ISSOSecureService/HttpsGetWorkRemRating",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsGetWorkRemCurator", "http://tempuri.org/Iais7ISSOSecureService/HttpsCloseSession",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsGetMessage", "http://tempuri.org/Iais7ISSOSecureService/HttpsSendInfoAboutPhoto",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsReceiveInfoAboutPhoto",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsReceiveInfoAboutRemWorks"};
        private static final String[] METHOD_NAME = {"HttpsGetSessionId", "HttpsGetIssoList", "HttpsGetRemInfo",
                "HttpsSetWorkRemRating", "HttpsGetWorkRemRating", "HttpsGetWorkRemCurator", "HttpsCloseSession", "HttpsGetMessage",
                "HttpsSendInfoAboutPhoto", "HttpsReceiveInfoAboutPhoto" , "HttpsReceiveInfoAboutRemWorks"};
        private static final String NAMESPACE = "http://tempuri.org/";

        // Для JSON
        private static final String[] JSONMETHODS = {"HttpsGetSessionId", "HttpsGetIssoList", "HttpsGetRemInfo",
                "HttpsSetWorkRemRating", "HttpsGetWorkRemRating", "HttpsGetWorkRemCurator", "HttpsCloseSession",
                "HttpsGetMessage", "HttpsSendInfoAboutPhoto", "HttpsReceiveInfoAboutPhoto" ,
                "HttpsReceiveInfoAboutRemWorks"};
        private static final String[] JSONMETHODRESULTS = {"HttpsGetSessionIdResult", "HttpsGetIssoListResult",
                "HttpsGetRemInfoResult", "HttpsSetWorkRemRatingResult", "HttpsGetWorkRemRatingResult",
                "HttpsGetWorkRemCuratorResult", "HttpsCloseSessionResult", "HttpsGetMessageResult",
                "HttpsSendInfoAboutPhotoResult", "HttpsReceiveInfoAboutPhotoResult" , "HttpsReceiveInfoAboutRemWorksResult"};

        String result = "";

        DBHelper dbHelper;

        Boolean isNullOrEmpty(String s) {
            return s == null || s.equals("");
        }

        SyncModule (SettingsActivity context, Handler myHandler) {
            attach(context);
            this.context = context;
            this.myHandler = myHandler;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = new ProgressDialog(this.context);
            this.dialog.setIndeterminate(true);
            this.dialog.setCancelable(false);
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //doSecureSync();
            doJSONSync();
            return null;
        }

        // Соединение с помощью JSON
        private void doJSONSync() {
            if (isNullOrEmpty(user) || isNullOrEmpty(pass) || isNullOrEmpty(host)) return;
            try {
                // Вытаскиваем сертификат из хранилища и регистрируем его
                publishProgress("Получение разрешения...");
                TimeUnit.MILLISECONDS.sleep(1000);
                AllowAccessToServer();

                Map<String, Object> params = new HashMap<>();
                params.put("user", user);
                params.put("pass", pass);
                JSONObject object = ConnectToServer(JSONMETHODS[0], params, true);
                assert object != null;
                JSONArray arr = object.getJSONArray(JSONMETHODRESULTS[0]);
                String id = arr.getString(0);
                String nameCurator = arr.getString(1);
                int step = arr.getInt(2);

                SharedPreferences sp = context.getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putString("nameCurator", nameCurator);
                e.putInt("stepSpinner", step);
                e.apply();

                // Получение ИССО для записи в БД
                publishProgress("Получение ИССО...");
                TimeUnit.MILLISECONDS.sleep(500);

                params = new HashMap<>();
                params.put("id", id);
                object = ConnectToServer(JSONMETHODS[1], params, true);
                Gson gson = new Gson();
                assert object != null;
                HttpsIsso[] issos = gson.fromJson(object.getString(JSONMETHODRESULTS[1]), HttpsIsso[].class);

                publishProgress("Получение ИССО... [" + issos.length + "]");
                TimeUnit.MILLISECONDS.sleep(500);

                // Запись полученных ИССО в БД
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String issoList = "";
                for (HttpsIsso i : issos)
                    issoList += "," + i.CIsso;
                //db.execSQL("delete from I_ISSO where C_ISSO not in (" + issoList.substring(1) + ")");
                // Теперь мы удаляем все записи ИССО
                db.delete("I_ISSO", null, null);
                for (HttpsIsso i : issos) {
                    ContentValues vals = new ContentValues();
                    vals.put("C_ISSO", i.CIsso);
                    vals.put("NAME", i.Name);
                    vals.put("FULLNAME", i.FullName);
                    vals.put("DORNAME", i.DorName);
                    vals.put("W_ISSO", i.WIsso);
                    vals.put("OBSTACLE", i.Obstacle);
                    vals.put("LENGTH", i.Length);
                    vals.put("LATITUDE", i.Latitude);
                    vals.put("LONGITUDE", i.Longitude);
                    vals.put("C_OTC_EXP", i.ExpertRatingNumeric);
                    vals.put("N_OTC_EXP", i.ExpertRating);
                    db.insertWithOnConflict("I_ISSO", null, vals, SQLiteDatabase.CONFLICT_REPLACE);
                }
                publishProgress("Получение информации по ремонтным работам...");
                TimeUnit.MILLISECONDS.sleep(500);
                int i = 0;
                for (HttpsIsso isso : issos) {
                    publishProgress("Получение информации по ремонтным работам... [" + (i + 1) + " из " + issos.length + "]");
                    Cursor cursor = db.query("I_REM_PLAN", new String[]{"C_REM"}, "C_ISSO=" + isso.CIsso, null, null, null, null);
                    JSONArray c_rems = new JSONArray();
                    if (cursor.moveToFirst()) {
                        do {
                            c_rems.put(cursor.getInt(cursor.getColumnIndex("C_REM")));
                        } while (cursor.moveToNext());
                    }
                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("isso", isso.CIsso);
                    params.put("ArrayCRem", c_rems);
                    object = ConnectToServer(JSONMETHODS[10], params, false);
                    assert object != null;
                    if(object.getInt(JSONMETHODRESULTS[10]) > 0) {
                        params = new HashMap<>();
                        params.put("id", id);
                        params.put("isso", isso.CIsso);
                        object = ConnectToServer(JSONMETHODS[2], params, false);
                        gson = new Gson();
                        assert object != null;
                        HttpsRemInfo[] remInfo = gson.fromJson(object.getString(JSONMETHODRESULTS[2]), HttpsRemInfo[].class);
                        db = dbHelper.getWritableDatabase();
                        try {
                            db.beginTransaction();
                            String sql1 = "insert into I_REM_PLAN values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            SQLiteStatement insert = db.compileStatement(sql1);
                            for (HttpsRemInfo info : remInfo) {
                                //db.execSQL("insert into I_REM_PLAN values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                //        new String[] {""+info.CIsso,""+info.CRem,""+info.nRem,""+info.n1,
                                //                ""+info.n2,""+info.n3,""+info.n4,""+info.n5,""+info.n6,
                                //                ""+info.n7,""+info.n8,""+info.n9,""+info.n10,""+info.n11,
                                //                ""+info.n12});
                                insert.bindAllArgsAsStrings(new String[]{"" + info.CIsso, "" + info.CRem, "" + info.nRem, "" + info.n1,
                                        "" + info.n2, "" + info.n3, "" + info.n4, "" + info.n5, "" + info.n6,
                                        "" + info.n7, "" + info.n8, "" + info.n9, "" + info.n10, "" + info.n11,
                                        "" + info.n12});
                                insert.execute();
                            }
                            db.setTransactionSuccessful();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Log.d("TAG", "SQL ERROR: " + ex.toString());
                        } finally {
                            db.endTransaction();
                        }
                    }
                    i++;
                }

                //Передача имеющегося рейтинга на сервер
                publishProgress("Передача данных на сервер...");
                TimeUnit.MILLISECONDS.sleep(500);

                Cursor cr = db.query("I_REM_PLAN_ITEMS", new String[]{"C_ISSO", "C_REM", "PERIOD", "RATING", "COMMENTS", "PHOTO_NAME"}, "SYNC = 0", null, null, null, null);
                cr.moveToFirst();
                JSONArray rating = new JSONArray();
                for(i = 0; i < cr.getCount(); i++) {
                    publishProgress("Передача данных на сервер... [" + (i + 1) + " из " + cr.getCount() + "]");
                    JSONObject ratings = new JSONObject();
                    ratings.put("CIsso", cr.getInt(cr.getColumnIndex("C_ISSO")));
                    ratings.put("CRem", cr.getInt(cr.getColumnIndex("C_REM")));
                    ratings.put("RatingRem", cr.getInt(cr.getColumnIndex("RATING")));
                    ratings.put("RatingRemExt", cr.getString(cr.getColumnIndex("COMMENTS")));
                    ratings.put("RemMonth", cr.getLong(cr.getColumnIndex("PERIOD")));
                    String path = cr.getString(cr.getColumnIndex("PHOTO_NAME"));
                    ratings.put("PhotoPath", path.substring(path.lastIndexOf("/") + 1));
                    if(!path.equals("")) {
                        try {
                            ByteArrayOutputStream out = new ByteArrayOutputStream();
                            Bitmap bmp = BitmapFactory.decodeFile(path);
                            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            byte[] imagebyte = out.toByteArray();
                            ratings.put("Photo", android.util.Base64.encode(imagebyte, 0));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            ratings.put("PhotoPath", "");
                        }
                    }
                    else
                        ratings.put("Photo","");
                    rating.put(ratings);
                    cr.moveToNext();
                }
                cr = db.query("I_REM_PLAN_EXEC", new String[]{"C_ISSO", "PERIOD", "PERFORMER", "ACTUAL_DATE", "EDIT_DATE",
                        "OFFSET", "LATITUDE_REM", "LONGITUDE_REM"}, "SYNC = 0", null, null, null, null);
                cr.moveToFirst();
                JSONArray ratingCur = new JSONArray();
                for(i = 0; i < cr.getCount(); i++) {
                    publishProgress("Передача данных на сервер... [" + (i + 1) + " из " + cr.getCount() + "]");
                    JSONObject ratings = new JSONObject();
                    ratings.put("CIsso",cr.getInt(cr.getColumnIndex("C_ISSO")));
                    ratings.put("Latitude", cr.getDouble(cr.getColumnIndex("LATITUDE_REM")));
                    ratings.put("Longitude", cr.getDouble(cr.getColumnIndex("LONGITUDE_REM")));
                    ratings.put("NPerformer", cr.getString(cr.getColumnIndex("PERFORMER")));
                    ratings.put("Offset", cr.getLong(cr.getColumnIndex("OFFSET")));
                    ratings.put("RatingDateRem", cr.getLong(cr.getColumnIndex("ACTUAL_DATE")));
                    ratings.put("RatingDateRemEdit", cr.getLong(cr.getColumnIndex("EDIT_DATE")));
                    ratings.put("RemMonth", cr.getLong(cr.getColumnIndex("PERIOD")));
                    ratingCur.put(ratings);
                    cr.moveToNext();
                }

                params = new HashMap<>();
                params.put("id", id);
                params.put("remWorks", rating);
                params.put("remCur", ratingCur);
                object = ConnectToServer(JSONMETHODS[3], params, false);
                assert object != null;
                int count = object.getInt(JSONMETHODRESULTS[3]);
                if(count == -2) {
                    result = "Не удалось отправить данные по ИССО. Обратитесь к заказчику работ." /*+ resultError.toString()*/;
                }
                publishProgress("Получение данных c сервера...");
                TimeUnit.MILLISECONDS.sleep(500);
                i = 0;
                for (HttpsIsso isso : issos) {
                    publishProgress("Получение данных c сервера... [" + (i + 1) + " из " + issos.length + "]");

                    Cursor c = db.rawQuery("select PHOTO_NAME from I_REM_PLAN_ITEMS where C_ISSO=" + isso.CIsso + " and PHOTO_NAME !=null", null);
                    c.moveToFirst();
                    JSONArray ArrayPhotos = new JSONArray();
                    for(int ind = 0; ind < c.getCount(); ind++) {
                        if(!c.getString(0).equals("")) {
                            String path = c.getString(0);
                            ArrayPhotos.put(path.substring(path.lastIndexOf("/") + 1));
                        }
                        c.moveToNext();
                    }
                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("c_isso", isso.CIsso);
                    params.put("photos", ArrayPhotos);
                    object = ConnectToServer(JSONMETHODS[9], params, false);

                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("c_isso", isso.CIsso);
                    params.put("download", download);

                    object = ConnectToServer(JSONMETHODS[4], params, false);
                    gson = new Gson();
                    assert object != null;
                    RemWork[] ratingHttps = gson.fromJson(object.getString(JSONMETHODRESULTS[4]), new RemWork[0].getClass());
                    db.delete("I_REM_PLAN_ITEMS", "C_ISSO=" + isso.CIsso, null);
                    for(RemWork rate : ratingHttps) {
                        ContentValues cv = new ContentValues();
                        cv.put("C_ISSO", rate.CIsso);
                        cv.put("C_REM", rate.CRem);
                        cv.put("PERIOD", rate.RemMonth);
                        cv.put("RATING", rate.RatingRem);
                        if(rate.RatingRemExt.equals(""))
                            rate.RatingRemExt = "";
                        cv.put("COMMENTS", rate.RatingRemExt);
                        if(!rate.PhotoPath.equals("")) {
                            File photo = null;
                            if(!rate.PhotoPath.equals("IsAlreadyOnPhone")) {
                                try {
                                    photo = createImageFile(rate.PhotoPath);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                if (photo != null) {
                                    try {
                                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(photo));
                                        bos.write(android.util.Base64.decode(rate.Photo, 0));
                                        bos.flush();
                                        bos.close();
                                        galleryAddPic(photo.getPath());
                                        cv.put("PHOTO_NAME", photo.getAbsolutePath());
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    String appDirectoryName = "ISSO-R";
                                    File imageRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
                                    if (!imageRoot.exists()) imageRoot.mkdirs();
                                    File f = new File(imageRoot, rate.PhotoPath);
                                    cv.put("PHOTO_NAME", f.getAbsolutePath());
                                }
                            }
                            else if(rate.PhotoPath.equals("notDownloaded")) {
                                cv.put("PHOTO_NAME", "notDowloaded");
                            }
                            else {
                                String appDirectoryName = "ISSO-R";
                                File imageRoot = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
                                File f = new File(imageRoot, rate.Photo);
                                cv.put("PHOTO_NAME", f.getAbsolutePath());
                            }
                        }
                        else {
                            cv.put("PHOTO_NAME", "");
                        }
                        cv.put("SYNC", 1);
                        db.insert("I_REM_PLAN_ITEMS", null, cv);
                    }

                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("c_isso", isso.CIsso);
                    object = ConnectToServer(JSONMETHODS[5], params, false);
                    gson = new Gson();
                    assert object != null;
                    HttpsRemCur[] curatorHttps = gson.fromJson(object.getString(JSONMETHODRESULTS[5]), HttpsRemCur[].class);
                    db.delete("I_REM_PLAN_EXEC", "C_ISSO=" + isso.CIsso, null);
                    for(HttpsRemCur rate : curatorHttps) {
                        ContentValues cv = new ContentValues();
                        cv.put("C_ISSO", rate.CIsso);
                        cv.put("LATITUDE_REM", rate.Latitude);
                        cv.put("LONGITUDE_REM", rate.Longitude);
                        cv.put("PERFORMER", rate.NPerformer);
                        cv.put("OFFSET", rate.Offset);
                        cv.put("ACTUAL_DATE", rate.RatingDateRem);
                        cv.put("EDIT_DATE", rate.RatingDateRemEdit);
                        cv.put("PERIOD", rate.RemMonth);
                        cv.put("SYNC", 1);
                        db.insert("I_REM_PLAN_EXEC", null, cv);
                    }
                    i++;
                }

                ContentValues cv = new ContentValues();
                cv.put("SYNC", 1);
                db.update("I_REM_PLAN_ITEMS", cv, "SYNC=0", null);
                db.update("I_REM_PLAN_EXEC", cv, "SYNC=0", null);

                params = new HashMap<>();
                params.put("id", id);
                ConnectToServer(JSONMETHODS[6], params, true);
                result = "Синхронизация выполнена успешно";
                sp = context.getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE);
                e = sp.edit();
                e.putBoolean("newVersion", false);
                e.apply();

            } catch (Exception e) {
                if(result.equals(""))
                    result = "Не удалось подключиться к удаленному серверу по адресу " + host + " Обратитесь к заказчику работ.";
                Log.d("Tag", "Гуляй Вася");
            }
        }

        // Подключение с помощью Https к сервису
        private JSONObject ConnectToServer(String address, Map<String, Object> params, boolean common) {
            try {

                URL url;
                HttpsURLConnection urlConn;
                DataOutputStream printout;
                if (common)
                    url = new URL("https://" + host + ":" + port + "/ais7UpdateServerSecureBinding/Common/" + address);
                else
                    url = new URL("https://" + host + ":" + port + "/ais7UpdateServerSecureBinding/IssoR/" + address);
                urlConn = (HttpsURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setRequestMethod("POST");
                urlConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                urlConn.setRequestProperty("Accept", "application/json");
                urlConn.setRequestProperty("User-Agent", "Pigeon");
                urlConn.connect();
                JSONObject jsonParam = new JSONObject();
                for(String key : params.keySet())
                    jsonParam.put(key, params.get(key));
                printout = new DataOutputStream(urlConn.getOutputStream());
                printout.write(jsonParam.toString().getBytes());
                printout.flush();
                printout.close();

                InputStream in;
                in = urlConn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();
                JSONObject jsonObject = new JSONObject(line);
                urlConn.disconnect();
                return jsonObject;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }



        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing())
                this.dialog.dismiss();
            Message msg = Message.obtain();
            msg.obj = this.result;
            msg.setTarget(myHandler);
            msg.sendToTarget();
        }

        String value = "";

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(values[0]);
            value = values[0];
        }

        private void galleryAddPic(String mCurrentPhotoPath) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(mCurrentPhotoPath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            context.sendBroadcast(mediaScanIntent);
        }

        private File createImageFile(String Format) throws IOException {
            // Create an image file name
            String appDirectoryName = "ISSO-R";
            File imageRoot = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
            if(!imageRoot.exists()) imageRoot.mkdirs();
            return new File(imageRoot, Format);
        }


        void detach() {
            context=null;
        }

        void attach(SettingsActivity activity) {
            this.context = activity;
        }

        String getProgress() {
            return(value);
        }

        // Получение разрешения на подключение
        private void AllowAccessToServer() {
            try {
                //Открытие хранилища сертификатов
                KeyStore keyStore = KeyStore.getInstance("BKS");
                InputStream caInput = context.getResources().openRawResource(R.raw.caroot);
                try {
                    keyStore.load(caInput, "LECfRBlack1992".toCharArray());
                } catch (Exception e) {
                    Log.d("Tag", e.toString());
                    caInput.close();
                }
                //Добавление TrustManager, который доверяет сертификатам из хранилища
                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                tmf.init(keyStore);


                //Создание SSLContext, использующий TrustManager
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, tmf.getTrustManagers(), null);

                javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

                javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return (hostname.equals(context.getResources().getString(R.string.LocalAddress)) || hostname.equals(context.getResources().getString(R.string.GlobalAddress)));
                    }
                });
            } catch (Exception e) {
                Log.d("Tag", e.toString());
                e.toString();
            }
        }


    }

    public class RemWork {

        public int CIsso;

        public int CRem;

        public String PhotoPath;

        public String RatingRemExt;

        public int RatingRem;

        public long RemMonth;

        public String Photo;

    }


    public Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            AlertDialog.Builder bld = new AlertDialog.Builder(SettingsActivity.this);
            bld.setMessage(msg.obj.toString());
            bld.setPositiveButton("ОК", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) { finish();}
            });
            AlertDialog alert = bld.create();
            alert.show();
            SettingsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
    };
}


package com.ais.admin.isso_s;

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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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

        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            setTheme(R.style.DialogTheme);
            isLight = false;
        }
        else {
            setTheme(R.style.DialogThemeLight);
            isLight = true;
        }
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
                    if (pref instanceof EditTextPreference)
                        pref.setSummary(entry.getValue().toString());
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
                EditText edit = ((EditTextPreference) prePreference).getEditText();
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

            v.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startSync();
                }
            });
            if(sm != null) {
                startSync();
            }
            return v;
        }
    }

    SyncModule sm;

    public void startSync()
    {
        String login = PreferenceManager.getDefaultSharedPreferences(this).getString("Login", null);
        String pass = PreferenceManager.getDefaultSharedPreferences(this).getString("Pass", null);
        //String host = PreferenceManager.getDefaultSharedPreferences(this).getString("Host", null);
        //String port = PreferenceManager.getDefaultSharedPreferences(this).getString("Port", "8789");
        if (login == null || pass == null) {
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
            sm.host = PreferenceManager.getDefaultSharedPreferences(this).getString("Address", "aisisso.ru").toLowerCase();//PreferenceManager.getDefaultSharedPreferences(this).getString("Host", null);
            sm.port = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString("Port", "8789")); //Integer.parseInt(getResources().getString(R.string.port));//PreferenceManager.getDefaultSharedPreferences(this).getString("Port", null);
            sm.dbHelper = new DBHelper(getApplicationContext());
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


    static class SyncModule extends AsyncTask<Void, String, Void> {
        public String user;
        public String pass;
        public String host;
        public int port;
        public ProgressDialog dialog;
        @SuppressLint("StaticFieldLeak")
        private SettingsActivity context;
        private Handler myHandler;
        String err = "";

        private static final String subFile = "ais7UpdateServerSecureBinding?wsdl";
        /*private static final String[] SOAP_ACTION = {"http://tempuri.org/Iais7ISSOSecureService/HttpsGetSessionId", "http://tempuri.org/Iais7ISSOSecureService/HttpsGetIssoList",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsSetRatingInfo", "http://tempuri.org/Iais7ISSOSecureService/HttpsGetRatingInfo",
                "http://tempuri.org/Iais7ISSOSecureService/HttpsCloseSession", "http://tempuri.org/Iais7ISSOSecureService/HttpsGetMessage"};
        private static final String[] METHOD_NAME = {"HttpsGetSessionId", "HttpsGetIssoList", "HttpsSetRatingInfo",
                "HttpsGetRatingInfo", "HttpsCloseSession", "HttpsGetMessage"};
        private static final String NAMESPACE = "http://tempuri.org/";*/

        // Для JSON
        private static final String[] JSONMETHODS = {"HttpsGetSessionIdForIssoS", "HttpsGetIssoList", "HttpsSetRatingInfo",
                "HttpsGetRatingInfo", "HttpsCloseSession", "HttpsGetMessage", "HttpsSetPhotoIssoS",
                "HttpsReceiveInfoAboutPhotoForIssoS", "HttpsGetPhotoIssoS"};
        private static final String[] JSONMETHODRESULTS = {"HttpsGetSessionIdForIssoSResult", "HttpsGetIssoListResult",
                "HttpsSetRatingInfoResult", "HttpsGetRatingInfoResult", "HttpsCloseSessionResult",
                "HttpsGetMessageResult", "HttpsSetPhotoIssoSResult", "HttpsReceiveInfoAboutPhotoForIssoSResult",
                "HttpsGetPhotoIssoSResult"};


        //public boolean isOperating = true;
        public String result = "";

        public DBHelper dbHelper;

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
            doJSONSync();
            //doSecureSync();
            return null;
        }

        // Соединение с помощью JSON
        private void doJSONSync() {
            if (isNullOrEmpty(user) || isNullOrEmpty(pass) || isNullOrEmpty(host)) return;
            err = "";
            try {
                // Вытаскиваем сертификат из хранилища и регистрируем его
                publishProgress("Получение разрешения...");
                TimeUnit.MILLISECONDS.sleep(1000);
                AllowAccessToServer();

                Map<String, Object> params = new HashMap<>();
                params.put("user", user);
                params.put("pass", pass);
                JSONObject object = ConnectToServer(JSONMETHODS[0], params, false);
                JSONArray arr = object.getJSONArray(JSONMETHODRESULTS[0]);
                err = arr.getString(3);
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
                HttpsIsso[] issos = gson.fromJson(object.getString(JSONMETHODRESULTS[1]), HttpsIsso[].class);

                publishProgress("Получение ИССО... [" + issos.length + "]");
                TimeUnit.MILLISECONDS.sleep(500);

                // Запись полученных ИССО в БД
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                StringBuilder issoList = new StringBuilder();
                for (HttpsIsso i : issos)
                    issoList.append(",").append(i.CIsso);
                //db.execSQL("delete from I_ISSO where C_ISSO not in (" + issoList.substring(1) + ")");
                // Теперь мы удаляем все записи ИССО
                db.delete("I_ISSO", null, null);
                for (HttpsIsso i : issos) {
                    ContentValues vals = new ContentValues();
                    vals.put("C_ISSO", i.CIsso);
                    vals.put("NAME", i.Name);
                    vals.put("NAME_ISSO", i.NameIsso);
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

                //Передача имеющегося рейтинга на сервер
                publishProgress("Передача информации по рейтингу на сервер...");
                TimeUnit.MILLISECONDS.sleep(500);

                Cursor cr = db.query("RATING", new String[]{"C_ISSO", "CURRENTRATING", "RATINGDATEEDIT", "LATITUDE_RATING", "LONGITUDE_RATING", "RATING", "RATINGDATE", "COMMENTS", "OFFSET"}, "SYNC = 0", null, null, null, null);
                cr.moveToFirst();
                JSONArray rating = new JSONArray();
                for(int i = 0; i < cr.getCount(); i++)
                {
                    publishProgress("Передача информации по рейтингу на сервер... [" + (i + 1) + " из " + cr.getCount() + "]");
                    JSONObject ratings = new JSONObject();
                    ratings.put("CIsso", cr.getInt(cr.getColumnIndex("C_ISSO")));
                    ratings.put("CurrentRating", cr.getInt(cr.getColumnIndex("CURRENTRATING")));
                    ratings.put("Latitude", cr.getDouble(cr.getColumnIndex("LATITUDE_RATING")));
                    ratings.put("Longitude", cr.getDouble(cr.getColumnIndex("LONGITUDE_RATING")));
                    ratings.put("RatingIsso", cr.getInt(cr.getColumnIndex("RATING")));
                    ratings.put("RatingDate", cr.getLong(cr.getColumnIndex("RATINGDATE")));
                    ratings.put("RatingDateEdit", cr.getLong(cr.getColumnIndex("RATINGDATEEDIT")));
                    ratings.put("RatingExt" , cr.getString(cr.getColumnIndex("COMMENTS")));
                    ratings.put("Offset", cr.getLong(cr.getColumnIndex("OFFSET")));
                    rating.put(ratings);
                    cr.moveToNext();
                }
                cr.close();

                params = new HashMap<>();
                params.put("id", id);
                params.put("ratings", rating);
                object = ConnectToServer(JSONMETHODS[2], params, false);
                int count = object.getInt(JSONMETHODRESULTS[2]);
                if(count == -2) {
                    result = "Не удалось отправить рейтинги ИССО. Обратитесь к заказчику работ." /*+ resultError.toString()*/;
                    return;
                }

                //Передача имеющихся фотографий на сервер
                publishProgress("Передача фотографий на сервер...");
                TimeUnit.MILLISECONDS.sleep(500);

                for (HttpsIsso isso : issos) {
                    cr = db.query("PHOTOS_RATING", new String[]{"C_ISSO", "RATINGDATE", "PHOTOPATH", "PHOTODATE", "COMMENT"}, "C_ISSO = " + isso.CIsso + " and SYNC = 0", null, null, null, null);
                    cr.moveToFirst();
                    rating = new JSONArray();
                    for(int i = 0; i < cr.getCount(); i++) {
                        publishProgress("Передача фотографий на сервер для ИССО №" + isso.CIsso + " [" + (i + 1) + " из " + cr.getCount() + "]");
                        JSONObject ratings = new JSONObject();
                        ratings.put("CIsso", cr.getInt(cr.getColumnIndex("C_ISSO")));
                        ratings.put("RatingDate", cr.getLong(cr.getColumnIndex("RATINGDATE")));
                        ratings.put("PhotoDate", cr.getLong(cr.getColumnIndex("PHOTODATE")));
                        ratings.put("Comment", cr.getString(cr.getColumnIndex("COMMENT")));
                        String path = cr.getString(cr.getColumnIndex("PHOTOPATH"));
                        ratings.put("PhotoPath", path.substring(path.lastIndexOf("/") + 1));
                        if(!path.equals("")) {
                            try {
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                Bitmap bmp = BitmapFactory.decodeFile(path);
                                bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
                                byte[] imagebyte = out.toByteArray();
                                ratings.put("Photo", android.util.Base64.encode(imagebyte,0));
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
                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("photos", rating);
                    object = ConnectToServer(JSONMETHODS[6], params, false);
                    assert object != null;
                    count = object.getInt(JSONMETHODRESULTS[6]);
                    if(count == -2) {
                        result = "Не удалось отправить данные по ИССО. Обратитесь к заказчику работ.";
                    }

                    ContentValues cv = new ContentValues();
                    cv.put("SYNC", 1);
                    db.update("PHOTOS_RATING",cv, "C_ISSO = " + isso.CIsso, null);
                }

                // Получаем значения рейтингов по коду CIsso и записываем в БД телефона
                publishProgress("Получение информации по рейтингу c сервера...");
                TimeUnit.MILLISECONDS.sleep(500);
                int i = 0;
                for (HttpsIsso isso : issos) {
                    publishProgress("Получение информации по рейтингу c сервера... [" + (i + 1) + " из " + issos.length + "]");
                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("isso", isso.CIsso);

                    object = ConnectToServer(JSONMETHODS[3], params, false);
                    gson = new Gson();
                    RatingHttps[] ratingHttps = gson.fromJson(object.getString(JSONMETHODRESULTS[3]), RatingHttps[].class);
                    db.delete("RATING", "C_ISSO=" + isso.CIsso, null);
                    for(RatingHttps rate : ratingHttps) {
                        ContentValues cv = new ContentValues();
                        cv.put("C_ISSO", rate.CIsso);
                        cv.put("CURRENTRATING", rate.CurrentRating);
                        cv.put("RATINGDATE", rate.RatingDate);
                        cv.put("RATING", rate.RatingIsso);
                        if(rate.RatingExt.equals("anyType{}"))
                            rate.RatingExt = "";
                        cv.put("COMMENTS", rate.RatingExt);
                        cv.put("OFFSET", rate.Offset);
                        cv.put("CHECKOUTOFPLAN", rate.CheckOut);
                        cv.put("RATINGDATEEDIT", rate.RatingDateEdit);
                        cv.put("LATITUDE_RATING", rate.Latitude);
                        cv.put("LONGITUDE_RATING", rate.Longitude);
                        cv.put("SYNC", 1);
                        db.insertWithOnConflict("RATING", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                    }

                    // Отправляем инфу о фотографиях на устройстве
                    Cursor c = db.rawQuery("select * from PHOTOS_RATING where C_ISSO=" + isso.CIsso, null);
                    JSONArray ArrayPhotos = new JSONArray();
                    c.moveToFirst();
                    for (int ind = 0; ind < c.getCount(); ind++) {
                        JSONObject photo = new JSONObject();
                        photo.put("CIsso", c.getInt(c.getColumnIndex("C_ISSO")));
                        photo.put("RatingDate", c.getLong(c.getColumnIndex("RATINGDATE")));
                        photo.put("PhotoDate", c.getLong(c.getColumnIndex("PHOTODATE")));
                        photo.put("PhotoPath", "");
                        photo.put("Photo", "");
                        ArrayPhotos.put(photo);
                        c.moveToNext();
                    }
                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("c_isso", isso.CIsso);
                    params.put("photos", ArrayPhotos);
                    ConnectToServer(JSONMETHODS[7], params, false);

                    params = new HashMap<>();
                    params.put("id", id);
                    params.put("c_isso", isso.CIsso);

                    object = ConnectToServer(JSONMETHODS[8], params, false);
                    gson = new Gson();
                    assert object != null;
                    PhotosRating[] photos = gson.fromJson(object.getString(JSONMETHODRESULTS[8]), PhotosRating[].class);
                    //db.delete("PHOTOS_RATING", "C_ISSO=" + isso.CIsso, null);
                    int local_index = 0;
                    for(PhotosRating photo : photos) {
                        ContentValues cv = new ContentValues();
                        cv.put("C_ISSO", photo.CIsso);
                        cv.put("RATINGDATE", photo.RatingDate);
                        cv.put("PHOTODATE", photo.PhotoDate);
                        if(photo.Comment.equals(""))
                            photo.Comment = "";
                        cv.put("COMMENT", photo.Comment);
                        if(photo.PhotoPath.equals("")) {
                            File photoFile = null;
                            try {
                                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                String imageFileName = "IMG_" + timeStamp + "_" + local_index +".jpeg";
                                photoFile = createImageFile(imageFileName);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            if (photoFile != null) {
                                try {
                                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(photoFile));
                                    bos.write(android.util.Base64.decode(photo.Photo, 0));
                                    bos.flush();
                                    bos.close();
                                    galleryAddPic(photoFile.getPath());
                                    cv.put("PHOTOPATH", photoFile.getAbsolutePath());
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        else {
                            cv.put("PHOTOPATH", "");
                        }
                        cv.put("SYNC", 1);
                        db.insert("PHOTOS_RATING", null, cv);
                        local_index++;
                    }

                    i++;
                }

                ContentValues cv = new ContentValues();
                cv.put("SYNC", 1);
                db.update("RATING",cv, "SYNC=?", new String[] {"0"});

                params = new HashMap<>();
                params.put("id", id);
                ConnectToServer(JSONMETHODS[4], params, true);
                result = "Синхронизация выполнена успешно";
                sp = context.getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE);
                e = sp.edit();
                e.putBoolean("newVersion", false);
                e.commit();

            } catch (Exception e) {
                if(result.equals(""))
                    result = "Не удалось подключиться к удаленному серверу по адресу " + host + " Обратитесь к заказчику работ.";
                if (!err.equals("")) {
                    result += " Причина: " + err;
                }
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
                    url = new URL("https://" + host + ":" + port + "/ais7UpdateServerSecureBinding/IssoS/" + address);
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


                int status = urlConn.getResponseCode();
                InputStream in;
                if (status < HttpsURLConnection.HTTP_BAD_REQUEST)
                    in = urlConn.getInputStream();
                else
                    in = urlConn.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();
                JSONObject jsonObject = new JSONObject(line);
                urlConn.disconnect();
                return jsonObject;
            }
            catch (Exception e) {
                String ex = e.toString();
                e.printStackTrace();
                if(e.getLocalizedMessage().equals("Connection refused"))
                    err = "Нет подключения к серверу. Возможно, в настройках был неправильно указан адрес или нет подключения к интернету.";
                return null;
            }
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
            String appDirectoryName = "ISSO-S";
            File imageRoot = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
            if(!imageRoot.exists()) imageRoot.mkdirs();
            return new File(imageRoot, Format);
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


        void detach() {
            context=null;
        }

        void attach(SettingsActivity activity) {
            this.context = activity;
        }

        String getProgress() {
            return(value);
        }


        /**********             Для передачи массива сложных объектов        *******//*
        *//*public SoapObject GetSoapObject(String MethodName) {
            return new SoapObject(NAMESPACE, MethodName);
        }*//*

        public SoapSerializationEnvelope GetEnvelope(SoapObject Soap) {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(Soap);
            return envelope;
        }
        *//************                                                   *************//*

        private HttpsIsso[] RetrieveIssoFromSoap(SoapObject soap) {
            HttpsIsso[] issos = new HttpsIsso[soap.getPropertyCount()];
            for (int i = 0; i < issos.length; i++) {
                SoapObject pii = (SoapObject)soap.getProperty(i);
                HttpsIsso category = new HttpsIsso();
                category.CIsso = Integer.parseInt(pii.getProperty("CIsso").toString());
                category.DorName= pii.getProperty("DorName").toString();
                category.ExpertRating = pii.getProperty("ExpertRating").toString();
                category.ExpertRatingNumeric = Integer.parseInt(pii.getProperty("ExpertRatingNumeric").toString());
                category.FullName= pii.getProperty("FullName").toString();
                category.Latitude = Double.parseDouble(pii.getProperty("Latitude").toString());
                category.Longitude = Double.parseDouble(pii.getProperty("Longitude").toString());
                category.Length = pii.getProperty("Length").toString();
                category.WIsso = Integer.parseInt(pii.getProperty("WIsso").toString());
                category.Name = pii.getProperty("Name").toString();
                category.Obstacle = pii.getProperty("Obstacle").toString();
                issos[i] = category;
            }
            return issos;
        }

        private RatingHttps[] RetrieveRatingFromSoap(SoapObject soap) {
            RatingHttps[] ratings = new RatingHttps[soap.getPropertyCount()];
            for (int i = 0; i < ratings.length; i++ ) {
                SoapObject so = (SoapObject) soap.getProperty(i);
                RatingHttps rating = new RatingHttps();
                rating.CIsso = Integer.parseInt(so.getProperty("CIsso").toString());
                rating.CurrentRating = Integer.parseInt(so.getProperty("CurrentRating").toString());
                rating.Latitude = Double.parseDouble(so.getProperty("Latitude").toString());
                rating.Longitude = Double.parseDouble(so.getProperty("Longitude").toString());
                rating.RatingDate = Long.parseLong(so.getProperty("RatingDate").toString());
                rating.RatingExt = so.getProperty("RatingExt").toString();
                rating.RatingIsso = Integer.parseInt(so.getProperty("RatingIsso").toString());
                rating.Offset = Long.parseLong(so.getProperty("Offset").toString());
                ratings[i] = rating;
            }
            return ratings;
        }*/

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
                        //return (hostname.equals(context.getResources().getString(R.string.LocalAddress)) || hostname.equals(context.getResources().getString(R.string.GlobalAddress)) ||
                        //        hostname.equals(context.getResources().getString(R.string.ExternalAddress)));
                        return true;
                    }
                });
            } catch (Exception e) {
                Log.d("Tag", e.toString());
                e.toString();
            }
        }


        /*private Object SecureConnect(String soapAction, SoapObject request, SoapSerializationEnvelope envelope) {
            try {
                if(request != null) {
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = true;
                }
                HttpsTransportSE androidHttpsTransport = new HttpsTransportSE(host, port, subFile, 15000);
                //HttpTransportSE androidHttpsTransport = new HttpTransportSE("http://10.242.52.146:8789/ais7UpdateServerSecureBinding");
                androidHttpsTransport.call(soapAction, envelope);
                return envelope.getResponse();
            } catch (SoapFault e) {
                result = e.getMessage();
                return null;
            }
            catch (Exception e) {
                //result = e.toString();
                Log.d("Tag", "Ошибка: " + e.toString());
                return null;
            }
        }

        private PropertyInfo getPropInfo(String name, Object rating, Object type) {
            PropertyInfo pi = new PropertyInfo();
            pi.setName(name);
            pi.setValue(rating);
            pi.setType(type);
            pi.setNamespace(RatingHttps.NAMESPACE);
            return pi;
        }*/


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


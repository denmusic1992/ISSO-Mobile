package com.development.aisisso.isso_i;


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

import org.json.JSONObject;
import org.kobjects.base64.Base64;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
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

public class UploadSchemeActivity extends PreferenceActivity {
    public static final String MY_SETTINGS = "my_settings";         // Для настроек

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new UploadSchemeFragment())
                    .commit();
        }
        PreferenceManager.setDefaultValues(UploadSchemeActivity.this, R.xml.pref_general, false);
    }

    public class UploadSchemeFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setRetainInstance(true);
            Map<String, ?> keys = PreferenceManager.getDefaultSharedPreferences(UploadSchemeActivity.this).getAll();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UploadSchemeActivity.this);
            prefs.registerOnSharedPreferenceChangeListener(this);
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

            EditTextPreference prefLogin = (EditTextPreference) findPreference("Login");
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
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("Pass")) {
                Preference prePreference = findPreference(key);
                EditText edit = ((EditTextPreference) prePreference).getEditText();
                String pref = edit.getTransformationMethod().getTransformation(sharedPreferences.getString(key, ""), edit).toString();
                prePreference.setSummary(pref);
            } else {

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
            Button btn = new Button(getApplicationContext());
            btn.setText("Синхронизация");

            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWifi.isConnected()) {
                CheckBoxPreference mCheckBoxPref = (CheckBoxPreference) findPreference("AllowToDownloadPhotos");
                PreferenceCategory mCategory = (PreferenceCategory) findPreference("category");
                if (mCheckBoxPref != null)
                    mCategory.removePreference(mCheckBoxPref);
            }

            v.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startSync(true);
                }
            });

            return v;
        }

        public void startSync(boolean download) {
            String login = PreferenceManager.getDefaultSharedPreferences(UploadSchemeActivity.this).getString("Login", null);
            String pass = PreferenceManager.getDefaultSharedPreferences(UploadSchemeActivity.this).getString("Pass", null);
            assert login != null;
            assert pass != null;
            if (login.equals("") || pass.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UploadSchemeActivity.this);
                builder.setTitle("Важное сообщение!")
                        .setMessage("Необходимо настроить параметры синхронизации.")
                        .setCancelable(true)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                return;
            }


            //sm = (SyncModule) getLastNonConfigurationInstance();
            SyncModule sm = new SyncModule(UploadSchemeActivity.this, myHandler);
            sm.user = login;//PreferenceManager.getDefaultSharedPreferences(this).getString("Login", null);
            sm.pass = pass;//PreferenceManager.getDefaultSharedPreferences(this).getString("Pass", null);
            sm.host = "10.242.52.146";//PreferenceManager.getDefaultSharedPreferences(this).getString("Host", null);
            sm.port = 8789;//PreferenceManager.getDefaultSharedPreferences(this).getString("Port", null);
            sm.download = download;
            sm.dbHelper = new DBHelper(UploadSchemeActivity.this);
            sm.execute();
            //RefreshIssoList();
        }

        public Handler myHandler = new Handler() {
            public void handleMessage(Message msg) {
                AlertDialog.Builder bld = new AlertDialog.Builder(UploadSchemeActivity.this);
                bld.setMessage(msg.obj.toString());
                bld.setPositiveButton("ОК", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { finish();}
                });
                AlertDialog alert = bld.create();
                alert.show();
                UploadSchemeActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
        };

        class SyncModule extends AsyncTask<Void, String, Void> {
            public String user;
            public String pass;
            public String host;
            public int port;
            public ProgressDialog dialog;
            private UploadSchemeActivity context;
            private Handler myHandler;
            private boolean download;

            // Для SOAP
            private static final String subFile = "ais7UpdateServerSecureBinding?wsdl";

            // Для JSON
            private final String[] JSONMETHODS = {"UploadSchemeForIssos"};
            private final String[] JSONMETHODRESULTS = {"UploadSchemeForIssosResult"};

            public String result = "";

            public DBHelper dbHelper;

            Boolean isNullOrEmpty(String s) {
                return s == null || s.equals("");
            }

            SyncModule(UploadSchemeActivity context, Handler myHandler) {
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
                    long t = System.currentTimeMillis();
                    AllowAccessToServer();
                    Cursor cr = dbHelper.getReadableDatabase().query("I_ISSO", new String[]{"C_ISSO"}, null, null, null, null, null);
                    if(cr.moveToFirst()) {
                        dbHelper.getWritableDatabase().execSQL("delete from UPLOAD_SCHEMES");
                        for(int i = 0; i < cr.getCount(); i++) {
                            publishProgress("Загрузка схем для ИССО: " + cr.getInt(cr.getColumnIndex("C_ISSO")));
                            Map<String, Object> params = new HashMap<>();
                            params.put("c_isso", cr.getInt(cr.getColumnIndex("C_ISSO")));
                            JSONObject object = ConnectToServer(JSONMETHODS[0], params);
                            Gson gson = new Gson();
                            assert object != null;
                            UploadSchemeForIsso[] schemes = gson.fromJson(object.getString(JSONMETHODRESULTS[0]), UploadSchemeForIsso[].class);
                            for(UploadSchemeForIsso scheme : schemes) {
                                ContentValues cv = new ContentValues();
                                cv.put("C_ISSO", scheme.C_ISSO);
                                cv.put("N", scheme.N);
                                cv.put("COMMENTS", scheme.Comments);
                                byte[] photoArray = Base64.decode(scheme.Scheme);
                                cv.put("SCHEME", photoArray);
                                photoArray = Base64.decode(scheme.Thumbnail);
                                cv.put("THUMBNAIL", photoArray);
                                cv.put("SCHEME_DATE", scheme.SchemeDate);
                                dbHelper.getWritableDatabase().insertWithOnConflict("UPLOAD_SCHEMES", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                            }
                            cr.moveToNext();
                        }
                    }
                    t = System.currentTimeMillis() - t;
                    result = "время отправки фото: " + (t / 1000);
                } catch (Exception e) {
                    if (result.equals(""))
                        result = "Не удалось подключиться к удаленному серверу по адресу " + host + " Обратитесь к заказчику работ.";
                    Log.d("Tag", "Гуляй Вася");
                }
            }

            // Подключение с помощью Https к сервису
            private JSONObject ConnectToServer(String address, Map<String, Object> params) {
                try {

                    URL url;
                    HttpsURLConnection urlConn;
                    DataOutputStream printout;
                    url = new URL("https://" + host + ":" + port + "/ais7UpdateServerSecureBinding/" + address);
                    urlConn = (HttpsURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setRequestMethod("POST");
                    urlConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.setRequestProperty("User-Agent", "Pigeon");
                    urlConn.connect();
                    JSONObject jsonParam = new JSONObject();
                    for (String key : params.keySet())
                        jsonParam.put(key, params.get(key));
                    printout = new DataOutputStream(urlConn.getOutputStream());
                    printout.write(jsonParam.toString().getBytes());
                    printout.flush();
                    printout.close();

                    int status = urlConn.getResponseCode();
                    InputStream in;
                    in = urlConn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line = reader.readLine();
                    JSONObject jsonObject = new JSONObject(line);
                    urlConn.disconnect();
                    return jsonObject;
                } catch (Exception e) {
                    String ex = e.toString();
                    e.printStackTrace();
                    return null;
                }
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

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                if (dialog.isShowing())
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
                File imageRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
                if (!imageRoot.exists()) imageRoot.mkdirs();
                return new File(imageRoot, Format);
            }

            void attach(UploadSchemeActivity activity) {
                this.context = activity;
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

    }
}


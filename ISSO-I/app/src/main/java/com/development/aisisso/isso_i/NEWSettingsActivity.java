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
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;


public class NEWSettingsActivity extends AppCompatActivity {

    public static final String MY_SETTINGS = "my_settings";         // Для настроек
    public UserCollection[] collections;
    public int selectedPositioin = 0;
    public CheckBox chkAllowPhoto;
    public CheckBox chkAllowScheme;
    public SyncModule sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isLight;
        if (MainActivity.theme.equals("0")) {
            setTheme(R.style.DialogTheme);
            isLight = false;
        } else {
            setTheme(R.style.DialogThemeLight);
            isLight = true;
        }
        setContentView(R.layout.new_settingsactivity_layout);
        collections = (UserCollection[]) getIntent().getSerializableExtra("collections");

        final TextView tvInfo = (TextView) findViewById(R.id.tv_mainInfoAboutIsso);
        final TextView tvAdvanced = (TextView) findViewById(R.id.tv_advancedInfoAboutIsso);
        Button btnMoreInfo = (Button) findViewById(R.id.btnMoreInfo);
        chkAllowPhoto = (CheckBox) findViewById(R.id.chkAllowPhoto);
        chkAllowScheme = (CheckBox) findViewById(R.id.chkAllowScheme);
        Button btn = (Button) findViewById(R.id.btn_sync);
        //btn.setText("Синхронизация");
        //((LinearLayout) findViewById(R.id.linLayoutSynchronize)).addView(btn);

        if(isLight) {
            (findViewById(R.id.linLayoutSynchronize)).setBackgroundColor(getResources().getColor(R.color.background_material_light));
            btnMoreInfo.setBackground(getDrawable(R.drawable.information_light));
        }
        else {
            (findViewById(R.id.linLayoutSynchronize)).setBackgroundColor(getResources().getColor(R.color.background_material_dark));
            btnMoreInfo.setBackground(getDrawable(R.drawable.information_dark));
        }

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected()) {
            chkAllowPhoto.setChecked(true);
            chkAllowScheme.setChecked(true);
        }
        else {
            chkAllowPhoto.setChecked(false);
            chkAllowScheme.setChecked(false);
        }
        findViewById(R.id.layoutPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    chkAllowPhoto.setChecked(!chkAllowPhoto.isChecked());
            }
        });
        findViewById(R.id.layoutScheme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkAllowScheme.setChecked(!chkAllowScheme.isChecked());
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onDownload(collections[selectedPositioin]);
            }
        });
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NEWSettingsActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.downloadinfo_layout, null);
                builder.setView(view);
                // Заполнение*/
                if(!MainActivity.theme.equals("0")) {
                    ((TextView) view.findViewById(R.id.tvMainHeaderInfo)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tvNaimenovanie)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tv_name)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tvData)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tv_date)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tvOpisanie)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tv_description)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tvColichestvo)).setTextColor(Color.BLACK);
                    ((TextView) view.findViewById(R.id.tv_numofisso)).setTextColor(Color.BLACK);
                }

                UserCollection collection = collections[selectedPositioin];

                String dateCreate = getDate(collection.DateCreate);
                String dateModify = getDate(collection.DateModify);
                String strDate = dateCreate + "/" + (dateCreate.equals(dateModify) ? "без зименений" : dateModify);

                ((TextView) view.findViewById(R.id.tvMainHeaderInfo)).setText("'" + collection.CollectionName
                        + "' (" + collection.IssoList.split(",").length + ") от " + getDate(collection.DateCreate));
                ((TextView) view.findViewById(R.id.tv_name)).setText(collection.CollectionName);
                ((TextView) view.findViewById(R.id.tv_date)).setText(strDate);
                ((TextView) view.findViewById(R.id.tv_description)).setText(collection.Description);
                ((TextView) view.findViewById(R.id.tv_numofisso)).setText("" + collection.IssoList.split(",").length);
                (view.findViewById(R.id.radiobutton_download)).setVisibility(View.GONE);
                //              */
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                        .show();
            }
        });


        tvInfo.setText(collections[selectedPositioin].CollectionName + " (" + collections[selectedPositioin].IssoList.split(",").length + " ИССО)");
        tvAdvanced.setText("Выгрузка от " + getDate(collections[selectedPositioin].DateCreate) + "\nОписание: " + collections[selectedPositioin].Description);

        findViewById(R.id.clickableLayoutForList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] local_index = {selectedPositioin};
                final AlertDialog.Builder builder = new AlertDialog.Builder(NEWSettingsActivity.this);
                final ListView listView = new ListView(getApplicationContext());
                listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                listView.setAdapter(new CustomDownloadListAdapter(getApplicationContext(), collections, local_index[0]));
                listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        for (int i = 0; i < listView.getCount(); i++) {
                            if (i != position) {
                                RadioButton rb = (RadioButton) listView.getChildAt(i).findViewById(R.id.radiobutton_download);
                                rb.setChecked(false);
                            } else {
                                RadioButton rb = (RadioButton) listView.getChildAt(i).findViewById(R.id.radiobutton_download);
                                rb.setChecked(true);
                            }
                        }
                        local_index[0] = position;
                    }
                });
                builder.setPositiveButton("Выбрать", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedPositioin = local_index[0];
                        tvInfo.setText(collections[selectedPositioin].CollectionName + " (" + collections[selectedPositioin].IssoList.split(",").length + " ИССО)");
                        tvAdvanced.setText("Выгрузка от " + getDate(collections[selectedPositioin].DateCreate) + "\nОписание: " + collections[selectedPositioin].Description);
                        dialog.dismiss();
                    }
                })
                        .setNegativeButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Выбор набора сведений ИССО");
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        /*listView.performItemClick(listView.getAdapter().getView(selectedPositioin, null, null),
                                selectedPositioin,
                                listView.getAdapter().getItemId(selectedPositioin));*/
                        try {
                            synchronized (this) {
                                wait(100);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RadioButton rb = (RadioButton) listView.getChildAt(selectedPositioin).findViewById(R.id.radiobutton_download);
                                        rb.setChecked(true);
                                    }
                                });
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                listView.setSelection(selectedPositioin);
                builder.setView(listView);
                builder.show();
            }
        });

        if (sm != null) {
            startSync(chkAllowPhoto.isChecked(), chkAllowScheme.isChecked(), collections[selectedPositioin]);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onDownload(final UserCollection collection) {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(!mWifi.isConnected()) {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(NEWSettingsActivity.this);
            builder3.setMessage("Вы не подключены к сети wi-fi. Загрузка будет осуществляться через мобильный интернет. Продолжить?")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startSync(chkAllowPhoto.isChecked(), chkAllowScheme.isChecked(), collection);
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
            startSync(chkAllowPhoto.isChecked(), chkAllowScheme.isChecked(), collection);
        }
    }

    public void startSync(boolean downloadPhoto, boolean downloadScheme, UserCollection collection) {
        String login = PreferenceManager.getDefaultSharedPreferences(NEWSettingsActivity.this).getString("Login", null);
        String pass = PreferenceManager.getDefaultSharedPreferences(NEWSettingsActivity.this).getString("Pass", null);
        //String host = PreferenceManager.getDefaultSharedPreferences(this).getString("Host", null);
        //String port = PreferenceManager.getDefaultSharedPreferences(this).getString("Port", "8789");
        assert login != null;
        assert pass != null;
        if (login.equals("") || pass.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(NEWSettingsActivity.this);
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
        if(sm == null) {
            sm = new SyncModule(NEWSettingsActivity.this, myHandler);
            sm.user = login;//PreferenceManager.getDefaultSharedPreferences(this).getString("Login", null);
            sm.pass = pass;//PreferenceManager.getDefaultSharedPreferences(this).getString("Pass", null);
            sm.host = /*"10.242.52.146";*/PreferenceManager.getDefaultSharedPreferences(this).getString("Address", "aisisso.ru").toLowerCase().replaceAll(" ", "");
            sm.port = 8789;//PreferenceManager.getDefaultSharedPreferences(this).getString("Port", null);
            sm.collection = collection;
            sm.downloadPhoto = downloadPhoto;
            sm.downloadScheme = downloadScheme;
            sm.dbHelper = new DBHelper(NEWSettingsActivity.this);
            sm.dialog = new ProgressDialog(NEWSettingsActivity.this);
            sm.dialog.setTitle("");
            sm.dialog.setMessage("");
            sm.dialog.setCancelable(false);
            sm.dialog.setIndeterminate(false);
            sm.dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            sm.dialog.setProgressNumberFormat(null);
            sm.dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //publishProgress("Остановка загрузки, подождите...");
                    AlertDialog.Builder builder = new AlertDialog.Builder(NEWSettingsActivity.this);
                    builder.setMessage("Синхронизация не была завершена, некоторые элементы приложения не будут доступны, " +
                            "пока синхронизация не выполнится полностью.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                    sm.cancel(true);
                }
            });
            sm.execute();
        }
        else {
            sm.attach(this);
            if(sm.dialog.isShowing()) {
                sm.dialog = new ProgressDialog(NEWSettingsActivity.this);
                sm.dialog.setTitle("");
                sm.dialog.setMessage("");
                sm.dialog.setCancelable(false);
                sm.dialog.setIndeterminate(false);
                sm.dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                sm.dialog.setProgressNumberFormat(null);
                sm.dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //publishProgress("Остановка загрузки, подождите...");
                        AlertDialog.Builder builder = new AlertDialog.Builder(NEWSettingsActivity.this);
                        builder.setMessage("Синхронизация не была завершена, некоторые элементы приложения не будут доступны, " +
                                "пока синхронизация не выполнится полностью.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.show();
                        sm.cancel(true);
                    }
                });
                sm.dialog.show();
                updateProgress(sm, sm.getProgress());
            }
            else {
                sm = new SyncModule(NEWSettingsActivity.this, myHandler);
                sm.user = login;//PreferenceManager.getDefaultSharedPreferences(this).getString("Login", null);
                sm.pass = pass;//PreferenceManager.getDefaultSharedPreferences(this).getString("Pass", null);
                sm.host = /*"10.242.52.146";*/PreferenceManager.getDefaultSharedPreferences(this).getString("Address", "aisisso.ru").toLowerCase().replaceAll(" ", "");
                sm.port = 8789;//PreferenceManager.getDefaultSharedPreferences(this).getString("Port", null);
                sm.collection = collection;
                sm.downloadPhoto = downloadPhoto;
                sm.downloadScheme = downloadScheme;
                sm.dbHelper = new DBHelper(NEWSettingsActivity.this);
                sm.dialog = new ProgressDialog(NEWSettingsActivity.this);
                sm.dialog.setTitle("");
                sm.dialog.setMessage("");
                sm.dialog.setCancelable(false);
                sm.dialog.setIndeterminate(false);
                sm.dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                sm.dialog.setProgressNumberFormat(null);
                sm.dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //publishProgress("Остановка загрузки, подождите...");
                        AlertDialog.Builder builder = new AlertDialog.Builder(NEWSettingsActivity.this);
                        builder.setMessage("Синхронизация не была завершена, некоторые элементы приложения не будут доступны, " +
                                "пока синхронизация не выполнится полностью.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.show();
                        sm.cancel(true);
                    }
                });
                sm.execute();
            }
        }
        //RefreshIssoList();
    }

    void updateProgress(SyncModule sm, String[] progress) {
        sm.dialog.setProgress(Integer.parseInt(progress[0]));
        sm.dialog.setMessage(progress[1]);
    }

    /*void ConnectUpdateProgress(ConnectModule sm, String progress) {
        sm.dialog.setMessage(progress);
    }*/

    private static void doKeepDialog(AlertDialog dialog){
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
    }

    // Получение разрешения на подключение
    private void AllowAccessToServer() {
        try {
            //Открытие хранилища сертификатов
            KeyStore keyStore = KeyStore.getInstance("BKS");
            InputStream caInput = getApplicationContext().getResources().openRawResource(R.raw.caroot);
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
                    return (hostname.equals(getApplicationContext().getResources().getString(R.string.LocalAddress)) ||
                            hostname.equals(getApplicationContext().getResources().getString(R.string.GlobalAddress)));
                }
            });
        } catch (Exception e) {
            Log.d("Tag", e.toString());
            e.toString();
        }
    }

    // Подключение с помощью Https к сервису
    private JSONObject ConnectToServer(String address, Map<String, Object> params, boolean common) {
        try {

            URL url;
            HttpsURLConnection urlConn;
            DataOutputStream printout;
            String host = PreferenceManager.getDefaultSharedPreferences(this).getString("Address", "aisisso.ru").toLowerCase().replaceAll(" ", "");
            int port = 8789;
            if (common)
                url = new URL("https://" + host + ":" + port + "/ais7UpdateServerSecureBinding/Common/" + address);
            else
                url = new URL("https://" + host + ":" + port + "/ais7UpdateServerSecureBinding/IssoI/" + address);
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

    public Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            AlertDialog.Builder bld = new AlertDialog.Builder(NEWSettingsActivity.this);
            bld.setMessage(msg.obj.toString());
            bld.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alert = bld.create();
            alert.show();
            NEWSettingsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
    };

    public void FillDownloadTable(UserCollection[] collections, TableLayout table) {
        boolean once = true;
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        TableRow.LayoutParams l = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        for(final UserCollection collection : collections) {
            if (once) {
                TableRow trMain = new TableRow(this);
                if (MainActivity.theme.equals("0"))
                    trMain.setBackground(getDrawable(R.drawable.cell_group_shape_dark));
                else
                    trMain.setBackground(getDrawable(R.drawable.cell_group_shape_light));
                trMain.addView(setTextViewDefect("Название (кол-во ИССО)", metrics.widthPixels), l);
                trMain.addView(setTextViewDefect("Дата создания/изменения", metrics.widthPixels), l);
                //trMain.addView(setTextViewDefect("Описание", metrics.widthPixels), l);
                table.addView(trMain);
                once = false;
            }
            TableRow tr = new TableRow(this);
            if (MainActivity.theme.equals("0"))
                tr.setBackground(getDrawable(R.drawable.cell_shape_dark));
            else
                tr.setBackground(getDrawable(R.drawable.cell_shape_light));

            tr.addView(setTextViewDefect(collection.CollectionName + "(" + (collection.IssoList.split(",")).length + ")", metrics.widthPixels), l);
            tr.addView(setTextViewDefect(getDate(collection.DateCreate) + "/" + getDate(collection.DateModify), metrics.widthPixels), l);
            //tr.addView(setTextViewDefect(collection.Description, metrics.widthPixels), l);
            table.addView(tr);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NEWSettingsActivity.this, AdvancedDownloadInfo.class);
                    intent.putExtra("collection", collection);
                    startActivityForResult(intent, 1);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    onDownload((UserCollection) data.getSerializableExtra("collection"));
                    break;
                case 2:
                    break;
            }
        }
    }

    public static String getDate(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }

    public LinearLayout setTextViewDefect(Object obj, int widthPixels) {
        //TableRow.LayoutParams l = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        TextView tv = new TextView(this);
        LinearLayout layout = new LinearLayout(this);
        if(MainActivity.theme.equals("0"))
            layout.setBackground(getDrawable(R.drawable.cell_shape_dark));
        else
            layout.setBackground(getDrawable(R.drawable.cell_shape_light));
        final String str = "ЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖЖ";
        //tv.setMaxWidth((widthPixels * 2) / 3);
        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linParams.setMargins(10, 20, 10, 20);
        linParams.gravity = Gravity.CENTER;
        Paint paint = new Paint();
        tv.setMaxWidth((int) paint.measureText(str));
        tv.setText(obj.toString());
        layout.addView(tv, linParams);
        return layout;
    }

    private class SyncModule extends AsyncTask<Void, String, Void> {
        String user;
        String pass;
        String host;
        int port;
        public ProgressDialog dialog;
        private NEWSettingsActivity context;
        private Handler myHandler;
        private boolean downloadPhoto;
        private boolean downloadScheme;
        UserCollection collection;
        private String id = "";

        // Для SOAP
        private static final String subFile = "ais7UpdateServerSecureBinding?wsdl";

        // Для JSON
        private final String[] JSONMETHODS = {"HttpsGetSessionIdForIssoI", "HttpsGetIssoList", "HttpsGetAllTablesIssoVersion2",
                "HttpsReceiveSQLQuery", "HttpsReceiveAllDatabase", "returnTableNames", "returnTableAttributes",
                "returnTableDelegate", "returnTableValues", "returnTableValuesForEachIsso", "HttpsCloseSession",
                "HttpsGetMeanInfoForIssos", "HttpsGetAdvancedInfoForIssos", "getTimerFromServer",
                "compareCountOfRows", "getPrimaryKeysValues", "synchronyzeTables"};

        private final String[] JSONMETHODRESULTS = {"HttpsGetSessionIdForIssoIResult", "HttpsGetIssoListResult",
                "HttpsGetAllTablesIssoVersion2Result", "HttpsReceiveSQLQueryResult", "HttpsReceiveAllDatabaseResult",
                "returnTableNamesResult", "returnTableAttributesResult", "returnTableDelegateResult",
                "returnTableValuesResult", "returnTableValuesForEachIssoResult", "HttpsCloseSessionResult",
                "HttpsGetMeanInfoForIssosResult", "HttpsGetAdvancedInfoForIssosResult", "getTimerFromServerResult",
                "compareCountOfRowsResult", "getPrimaryKeysValuesResult", "synchronyzeTablesResult"};
        private final String[] DEFECTS = {"I_DEFECT", "I_DEF_MOD", "I_DEF_DESCR", "S_DEFECT", "S_REM",
                "S_UNIT_DIMENSION", "S_GR_CONSTR", "S_DEFPARAM", "S_DEFPARAMVALUE",
                "S_DEFPARAMCATEGORY", "V_GR_CONSTR_DEF", "V_GR_CONSTR_DEF2", "S_DEF4CONSTR", "I_FOTO_DEF"};


        //private final String[] JSONPHOTOMETHODS = {"UploadPhotoInfoForIssos"};
        //private final String[] JSONPHOTOMETHODRESULTS = {"UploadPhotoInfoForIssosResult"};
        //private final String[] JSONSCHEMEMETHODS = {"UploadSchemeForIssos"};
        //private final String[] JSONSCHEMEMETHODRESULTS = {"UploadSchemeForIssosResult"};

        public String result = "";

        DBHelper dbHelper;

        Boolean isNullOrEmpty(String s) {
            return s == null || s.equals("");
        }

        SyncModule(NEWSettingsActivity context, Handler myHandler) {
            attach(context);
            this.context = context;
            this.myHandler = myHandler;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            publishProgress("Подождите, идет остановка синхронизации...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            //doSecureSync();
            doJSONSync();
            return null;
        }
        long time1 = 0, time2 = 0, time3 = 0;


        // Соединение с помощью JSON
        private void doJSONSync() {
            if (isNullOrEmpty(user) || isNullOrEmpty(pass) || isNullOrEmpty(host)) return;
            try {
                int countOfSteps = 5 + (downloadPhoto ? 1 : 0) + (downloadScheme ? 1 : 0);
                // Вытаскиваем сертификат из хранилища и регистрируем его
                dialog.setProgress(0);
                publishProgress(String.valueOf(0), "Шаг 1 из " + countOfSteps + ". Получение разрешения...");
                TimeUnit.MILLISECONDS.sleep(1000);
                AllowAccessToServer();

                Map<String, Object> params = new HashMap<>();
                params.put("user", user);
                params.put("pass", pass);
                params.put("IssoList", collection.IssoList);
                JSONObject object = ConnectToServer(JSONMETHODS[0], params, false);
                if(isCancelled())
                    return;
                assert object != null;
                JSONArray arr = object.getJSONArray(JSONMETHODRESULTS[0]);
                id = arr.getString(0);
                String nameCurator = arr.getString(1);
                int step = arr.getInt(2);
                dialog.setProgress(100);
                publishProgress(String.valueOf(100), "Шаг 1 из " + countOfSteps + ". Получение разрешения...");
                if(isCancelled())
                    return;

                SharedPreferences sp = context.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putString("nameCurator", nameCurator);
                e.putInt("stepSpinner", step);
                e.apply();

                // Получение ИССО для записи в БД
                params = new HashMap<>();
                params.put("id", id);
                object = ConnectToServer(JSONMETHODS[1], params, true);
                dialog.setProgress(0);
                publishProgress(String.valueOf(0), "Шаг 2 из "+ countOfSteps + ". Получение ИССО...");
                TimeUnit.MILLISECONDS.sleep(500);
                if(isCancelled())
                    return;
                Gson gson = new Gson();
                assert object != null;
                HttpsIsso[] issos = gson.fromJson(object.getString(JSONMETHODRESULTS[1]), HttpsIsso[].class);
                if(isCancelled())
                    return;

                // Запись полученных ИССО в БД
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String issoList = "";
                for (HttpsIsso i : issos)
                    issoList += "," + i.CIsso;
                //db.execSQL("delete from I_ISSO where C_ISSO not in (" + issoList.substring(1) + ")");
                // Теперь мы удаляем все записи ИССО
                db.delete("I_ISSO", null, null);
                JSONArray cIsso = new JSONArray();
                JSONArray cTypeIsso = new JSONArray();
                int bibs = 0;
                for (HttpsIsso i : issos) {
                    ContentValues vals = new ContentValues();
                    vals.put("C_ISSO", i.CIsso);
                    vals.put("NAME", i.Name);
                    vals.put("C_TYPEISSO", i.CTypeIsso);
                    vals.put("FULLNAME", i.FullName);
                    vals.put("DORNAME", i.DorName);
                    vals.put("W_ISSO", i.WIsso);
                    vals.put("OBSTACLE", i.Obstacle);
                    vals.put("LENGTH", i.Length);
                    vals.put("LATITUDE", i.Latitude);
                    vals.put("LONGITUDE", i.Longitude);
                    vals.put("C_OTC_EXP", i.ExpertRatingNumeric);
                    vals.put("N_OTC_EXP", i.ExpertRating);
                    db.insertWithOnConflict("I_ISSO", null, vals, SQLiteDatabase.CONFLICT_IGNORE);
                    dialog.setProgress(((bibs / 266) * 100));
                    publishProgress(String.valueOf(((bibs / 266) * 100)), "Шаг 2 из " + countOfSteps + ". Получение ИССО...");
                    cIsso.put(i.CIsso);
                    cTypeIsso.put(i.CTypeIsso);
                    if(isCancelled())
                        return;
                    bibs++;
                }
                dialog.setProgress(100);
                publishProgress(String.valueOf(((bibs / 266) * 100)), "Шаг 2 из " + countOfSteps + ". Получение ИССО...");
                TimeUnit.MILLISECONDS.sleep(500);
                db.delete("TABLE_NAMES", null, null);
                long t = System.currentTimeMillis();
                params = new HashMap<>();
                params.put("id", id);
                params.put("issos", cIsso);
                params.put("cTypeIssos", cTypeIsso);
                object = ConnectToServer(JSONMETHODS[11], params, false);
                dialog.setProgress(0);
                publishProgress(String.valueOf(0), "Шаг 3 из " + countOfSteps + ". Получение дополнительных сведений по ИССО... (Имена)");
                TimeUnit.MILLISECONDS.sleep(500);
                if(isCancelled())
                    return;
                t = System.currentTimeMillis() - t;
                time1 += t;
                gson = new Gson();
                MeanInfo meanInfo = gson.fromJson(object.getString(JSONMETHODRESULTS[11]),
                        MeanInfo.class);
                t = System.currentTimeMillis();
                db.beginTransaction();
                for (int i = 0; i < meanInfo.tableNames.length; i += 10) {
                    int j = 0;
                    String sqlRequest = "";
                    while (j < 10 && i + j < meanInfo.tableNames.length) {
                        HttpsTableNames names = meanInfo.tableNames[i + j];
                        sqlRequest += "('" + names.C_GR_CONSTR + "','" + names.TableSysName + "','" +
                                names.TableName + "','" + names.TableParentID + "','" + names.TableView + "'),";
                        j++;
                    }
                    dialog.setProgress((int) ((double) i / meanInfo.tableNames.length * 100));
                    publishProgress(String.valueOf((int) ((double) i / meanInfo.tableNames.length * 100)), "Шаг 3 из " + countOfSteps + ". Получение дополнительных сведений по ИССО... (Имена)");
                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                    db.execSQL("insert into TABLE_NAMES (C_GR_CONSTR,SYS_NAME,DESCRIPTION,PARENT_ID,PARENT_VIEW) values " + sqlRequest);
                }
                t = System.currentTimeMillis() - t;
                time3 += t;
                db.setTransactionSuccessful();
                db.endTransaction();
                if(isCancelled())
                    return;
                dialog.setProgress(0);
                publishProgress(String.valueOf(0), "Шаг 3 из " + countOfSteps + ". Получение дополнительных сведений по ИССО... (Отношения)");
                TimeUnit.MILLISECONDS.sleep(500);


                db.delete("TABLE_DELEGATES", null, null);
                t = System.currentTimeMillis();
                db.beginTransaction();
                for (int i = 0; i < meanInfo.tableDelegate.length; i += 100) {
                    int j = 0;
                    String sqlRequest = "";
                    while (j < 100 && i + j < meanInfo.tableDelegate.length) {
                        HttpsTableDelegate delegate = meanInfo.tableDelegate[i + j];
                        sqlRequest += "('" + delegate.C_TYPEISSO + "','" + delegate.C_GR_CONSTR + "'),";
                        j++;
                    }
                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                    db.execSQL("insert into TABLE_DELEGATES (ISSO_TYPE,C_GR_CONSTR) values " + sqlRequest);
                    dialog.setProgress((i / meanInfo.tableNames.length * 100));
                    publishProgress(String.valueOf((i / meanInfo.tableNames.length * 100)), "Шаг 3 из " + countOfSteps + ". Получение дополнительных сведений по ИССО... (Отношения)");
                }
                t = System.currentTimeMillis() - t;
                time3 += t;
                db.setTransactionSuccessful();
                db.endTransaction();
                dialog.setProgress(100);
                publishProgress(String.valueOf(100), "Шаг 3 из " + countOfSteps + ". Получение дополнительных сведений по ИССО... (Отношения)");
                if(isCancelled())
                    return;
                db.delete("TABLE_VALUES", null, null);
                db.delete("TABLE_ATTRIBUTES", null, null);
                bibs = 0;
                for (HttpsTableNames names : meanInfo.tableNames) {
                    dialog.setProgress((int) ((double) bibs / meanInfo.tableNames.length * 100));
                    publishProgress(String.valueOf((int) ((double) bibs / meanInfo.tableNames.length * 100)), "Шаг 3 из " + countOfSteps + ". Получение дополнительных сведений по ИССО... (Значения)");
                    dialog.setProgress((int) ((double) bibs / meanInfo.tableNames.length * 100));
                    if(!names.IsTableExportedWhole) {
                        t = System.currentTimeMillis();
                        params = new HashMap<>();
                        params.put("id", id);
                        params.put("issos", cIsso);
                        params.put("sysTableName", names.TableSysName);
                        params.put("C_GR_CONSTR", names.C_GR_CONSTR);
                        params.put("isTheLast", true);
                        object = ConnectToServer(JSONMETHODS[12], params, false);
                        if(isCancelled())
                            return;
                        gson = new Gson();
                        t = System.currentTimeMillis() - t;
                        time1 += t;
                        AdvancedInfo advancedInfo = gson.fromJson(object.getString(JSONMETHODRESULTS[12]),
                                AdvancedInfo.class);
                        t = System.currentTimeMillis();
                        db.beginTransaction();
                        for (int i = 0; i < advancedInfo.tableAttributes.length; i += 100) {
                            int j = 0;
                            String sqlRequest = "";
                            while (j < 100 && i + j < advancedInfo.tableAttributes.length) {
                                HttpsTableAttributes attributes = advancedInfo.tableAttributes[i + j];
                                sqlRequest += "('" + attributes.ID + "','" + attributes.TableSysName + "','" +
                                        attributes.DataType + "','" + attributes.AttributeName + "','" + attributes.isBlob +
                                        "','" + attributes.C_GR_CONSTR + "','" + attributes.Category + "','" + attributes.VisibleInGrid + "'),";
                                j++;
                            }
                            sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                            db.execSQL("insert into TABLE_ATTRIBUTES (ID,SYS_NAME,DATA_TYPE,DESCRIPTION,IS_BLOB,C_GR_CONSTR,CATEGORY,VISIBLEINGRID) values " + sqlRequest);
                        }
                        t = System.currentTimeMillis() - t;
                        time3 += t;
                        t = System.currentTimeMillis();
                        int j = 0;
                        String sqlRequest = "";
                        for (int i = 0; i < advancedInfo.tableValues.length; i++) {
                            for(int k = 0; k < advancedInfo.tableValues[i].length; k++) {
                                if(j < 80) {
                                    sqlRequest += "('" + advancedInfo.tableAttributes[k].ID + "','" + advancedInfo.tableValues[i][0] + "','" +
                                            advancedInfo.tableValues[i][k] + "'),";
                                    j++;
                                }
                                else {
                                    j = 0;
                                    sqlRequest += "('" + advancedInfo.tableAttributes[k].ID + "','" + advancedInfo.tableValues[i][0] + "','" +
                                            advancedInfo.tableValues[i][k] + "'),";
                                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                    db.execSQL("insert into TABLE_VALUES (ATTRIBUTE_ID,ISSO,VALUE) values " + sqlRequest);
                                    sqlRequest = "";
                                }
                            }
                        }
                        if(!sqlRequest.equals("")) {
                            sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                            db.execSQL("insert into TABLE_VALUES (ATTRIBUTE_ID,ISSO,VALUE) values " + sqlRequest);
                        }
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        if(isCancelled())
                            return;
                        t = System.currentTimeMillis() - t;
                        time3 += t;
                    }
                    else {
                        for(int index = 0; index < issos.length; index += 30) {
                            t = System.currentTimeMillis();
                            params = new HashMap<>();
                            params.put("id", id);
                            arr = new JSONArray();
                            for(int k = index; k < index + 20 && k < issos.length; k++) {
                                arr.put(issos[k].CIsso);
                            }
                            params.put("issos", arr);
                            params.put("sysTableName", names.TableSysName);
                            params.put("C_GR_CONSTR", names.C_GR_CONSTR);
                            if(index + 30 < issos.length)
                                params.put("isTheLast", false);
                            else
                                params.put("isTheLast", true);
                            object = ConnectToServer(JSONMETHODS[12], params, false);
                            if(isCancelled())
                                return;
                            t = System.currentTimeMillis() - t;
                            time1 += t;
                            gson = new Gson();
                            AdvancedInfo advancedInfo = gson.fromJson(object.getString(JSONMETHODRESULTS[12]),
                                    AdvancedInfo.class);
                            t = System.currentTimeMillis();
                            int j = 0;
                            String sqlRequest = "";
                            db.beginTransaction();
                            for (int i = 0; i < advancedInfo.tableValues.length; i++) {
                                for(int k = 0; k < advancedInfo.tableValues[i].length; k++) {
                                    if(j < 80) {
                                        sqlRequest += "('" + advancedInfo.tableAttributes[k].ID + "','" + advancedInfo.tableValues[i][0] + "','" +
                                                advancedInfo.tableValues[i][k] + "'),";
                                        j++;
                                    }
                                    else {
                                        j = 0;
                                        sqlRequest += "('" + advancedInfo.tableAttributes[k].ID + "','" + advancedInfo.tableValues[i][0] + "','" +
                                                advancedInfo.tableValues[i][k] + "'),";
                                        sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                        db.execSQL("insert into TABLE_VALUES (ATTRIBUTE_ID,ISSO,VALUE) values " + sqlRequest);
                                        sqlRequest = "";
                                    }
                                }
                            }
                            if(!sqlRequest.equals("")) {
                                sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                db.execSQL("insert into TABLE_VALUES (ATTRIBUTE_ID,ISSO,VALUE) values " + sqlRequest);
                            }
                            t = System.currentTimeMillis() - t;
                            time3 += t;
                            if(index + 30 >= issos.length) {
                                t = System.currentTimeMillis();
                                for (int i = 0; i < advancedInfo.tableAttributes.length; i += 100) {
                                    j = 0;
                                    sqlRequest = "";
                                    while (j < 100 && i + j < advancedInfo.tableAttributes.length) {
                                        HttpsTableAttributes attributes = advancedInfo.tableAttributes[i + j];
                                        sqlRequest += "('" + attributes.ID + "','" + attributes.TableSysName + "','" +
                                                attributes.DataType + "','" + attributes.AttributeName + "','" + attributes.isBlob +
                                                "','" + attributes.C_GR_CONSTR + "','" + attributes.Category + "','" + attributes.VisibleInGrid + "'),";
                                        j++;
                                    }
                                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                    db.execSQL("insert into TABLE_ATTRIBUTES (ID,SYS_NAME,DATA_TYPE,DESCRIPTION,IS_BLOB,C_GR_CONSTR,CATEGORY,VISIBLEINGRID) values " + sqlRequest);
                                }
                                t = System.currentTimeMillis() - t;
                                time3 += t;
                            }
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            if(isCancelled())
                                return;
                        }
                    }
                    bibs++;
                }
                /*for (int i = 0; i < allInfo.tableValues.length; i += 100) {
                    int j = 0;
                    String sqlRequest = "";
                    while (j < 100 && i + j < allInfo.tableValues.length) {
                        HttpsTableValues values = allInfo.tableValues[i + j];
                        sqlRequest += "('" + values.AttributeID + "','" + values.C_ISSO + "','" +
                                values.TableValue + "'),";
                        j++;
                    }
                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                    db.execSQL("insert into TABLE_VALUES (ATTRIBUTE_ID,ISSO,VALUE) values " + sqlRequest);
                }*/
                dialog.setProgress(0);
                publishProgress(String.valueOf(0), "Шаг 4 из " + countOfSteps + ". Получение дефектов...");
                params = new HashMap<>();
                params.put("id", id);
                object = ConnectToServer(JSONMETHODS[13], params, false);
                if(isCancelled())
                    return;
                time2 = object.getLong(JSONMETHODRESULTS[13]);


                //Выполнение sql create запросов для таблиц дефектов*/
                JSONArray tableNames = new JSONArray();
                for(String table : DEFECTS) {
                    if(!isTableExists(table, -1)/* || table.substring(0, 1).equals("I")*/) {
                        tableNames.put(table);
                    }
                }
                params = new HashMap<>();
                params.put("id", id);
                params.put("names", tableNames);
                object = ConnectToServer(JSONMETHODS[3], params, false);
                if(isCancelled())
                    return;
                JSONArray res = object.getJSONArray(JSONMETHODRESULTS[3]);
                db = new DBHelper(NEWSettingsActivity.this).getWritableDatabase();
                boolean newVersion = false;
                for(int i = 0; i < res.length(); i++) {
                    try {
                        db.execSQL(res.getString(i));
                        if(tableNames.get(i).equals("I_DEFECT")) {
                            db.beginTransaction();
                            try {
                                db.execSQL("ALTER TABLE I_DEFECT ADD COLUMN MAIN_CONSTR_ID integer");
                                db.setTransactionSuccessful();
                            }
                            catch (Exception ex1) {
                                ex1.printStackTrace();
                            }
                            db.endTransaction();
                        }
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                if(isCancelled())
                    return;
                bibs = 0;
                for (String table : DEFECTS) {
                    dialog.setProgress((int) ((double)bibs / DEFECTS.length * 100));
                    publishProgress(String.valueOf((int) ((double)bibs / DEFECTS.length * 100)), "Шаг 4 из " + countOfSteps + ". Получение дефектов...");
                    if(!table.substring(0, 1).equals("I") && !table.substring(0, 1).toLowerCase().equals("v")) {
                        t = System.currentTimeMillis();
                        Cursor cr = db.rawQuery("select * from " + table, null);
                        cr.moveToFirst();
                        params = new HashMap<>();
                        params.put("id", id);
                        params.put("clientCount", cr.getCount());
                        params.put("tableName", table);
                        cr.close();
                        object = ConnectToServer(JSONMETHODS[14], params, false);
                        if(isCancelled())
                            return;
                        int isActual = object.getInt(JSONMETHODRESULTS[14]);
                        t = System.currentTimeMillis() - t;
                        time1 += t;
                        if(isActual == 0) {
                            db.beginTransaction();
                            db.delete(table, null, null);
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            t = System.currentTimeMillis();
                            params = new HashMap<>();
                            params.put("id", id);
                            params.put("tableName", table);
                            arr = new JSONArray();
                            arr.put(-1);
                            params.put("c_issos", arr);
                            object = ConnectToServer(JSONMETHODS[4], params, false);
                            if(isCancelled())
                                return;
                            t = System.currentTimeMillis() - t;
                            time1 += t;
                            JSONArray innerResult = object.getJSONArray(JSONMETHODRESULTS[4]);
                            Cursor dbCursor = db.query(table, null, null, null, null, null, null);
                            String[] columnNames = dbCursor.getColumnNames();
                            String[] questionMarks = new String[columnNames.length];
                            Arrays.fill(questionMarks, "?");
                            dbCursor.close();
                            String request = "insert into " + table + "(" + TextUtils.join(",", columnNames) +
                                    ") values (" + TextUtils.join(",", questionMarks) + ")";
                            SQLiteStatement insertStmt = db.compileStatement(request);
                            t = System.currentTimeMillis();
                            db.beginTransaction();
                            /*for(int j = 0; j < innerResult.length(); j += 50) {
                                int ind = 0;
                                String sqlRequest = "";
                                while (ind < 50 && ind + j < innerResult.length()) {
                                    JSONArray array = innerResult.getJSONArray(j + ind);
                                    sqlRequest += "(";
                                    for (int k = 0; k < array.length(); k++) {
                                        Object obj = array.get(k);
                                        sqlRequest += obj + ",";
                                    }
                                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                    sqlRequest += "),";
                                    ind++;
                                }
                                sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                db.execSQL("insert into " + table + " values " + sqlRequest);
                            }*/
                            for (int j = 0; j < innerResult.length(); j++) {
                                JSONArray array = innerResult.getJSONArray(j);
                                insertStmt.clearBindings();
                                for (int k = 0; k < array.length(); k++) {
                                    Object obj = array.get(k);
                                    if(!obj.toString().equals("null")) {
                                        insertStmt.bindString(k + 1, obj.toString());
                                    }
                                    else
                                        insertStmt.bindNull(k + 1);
                                }
                                insertStmt.executeInsert();
                            }
                            t = System.currentTimeMillis() - t;
                            time3 += t;
                            db.setTransactionSuccessful();
                            int index = innerResult.length();
                            db.endTransaction();
                        }
                        if(isCancelled())
                            return;
                    }
                    else if(table.substring(0, 1).equals("I")) {
                        db.beginTransaction();
                        db.execSQL("delete from " + table + " where C_ISSO not in (" + issoList.substring(1) + ")");
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        for(int index = 0; index < issos.length; index += 20) {
                            t = System.currentTimeMillis();
                            params = new HashMap<>();
                            params.put("id", id);
                            params.put("table", table);
                            JSONArray array = new JSONArray();
                            int n = 0;
                            while( n < 20 && n + index < issos.length) {
                                array.put(issos[n + index].CIsso);
                                n++;
                            }
                            Log.d("Tag", "doing for isso = " + array.getString(0));
                            object = ConnectToServer(JSONMETHODS[15], params, false);
                            if(isCancelled())
                                return;
                            t = System.currentTimeMillis() - t;
                            time1 += t;
                            gson = new Gson();
                            PrimaryInfo info = gson.fromJson(object.getString(JSONMETHODRESULTS[15]), PrimaryInfo.class);

                            String pks = "";
                            for (int i = 0; i < info.pks.length; i++) {
                                pks += info.pks[i] + ",";
                            }
                            pks = pks.substring(0, pks.length() - 1);

                            boolean isByte = false;
                            for (int i = 0; i < info.types.length; i++) {
                                if(info.types[i].equals("System.Byte[]"))
                                    isByte = true;
                            }
                            if(!isByte) {
                                List<String> list = new ArrayList<>();
                                for(int i = 0; i < array.length(); i++){
                                    list.add(array.get(i).toString());
                                }

                                JSONArray result = GetInformationDefects(table, table, pks, list, array);
                                if(isCancelled())
                                    return;
                                Cursor dbCursor = db.query(table, null, null, null, null, null, null);
                                String[] columnNames = dbCursor.getColumnNames();
                                String[] questionMarks = new String[columnNames.length];
                                Arrays.fill(questionMarks, "?");
                                dbCursor.close();
                                String request = "insert into " + table + "(" + TextUtils.join(",", columnNames) +
                                        ") values (" + TextUtils.join(",", questionMarks) + ")";
                                t = System.currentTimeMillis();
                                db.beginTransaction();
                                SQLiteStatement insertStmt = db.compileStatement(request);
                                for (int j = 0; j < result.length(); j++) {
                                    array = result.getJSONArray(j);
                                    insertStmt.clearBindings();
                                    for(int loc_i = 0; loc_i < info.types.length; loc_i++) {
                                        switch(info.types[loc_i]) {
                                            case "System.DateTime":
                                            case "System.Date":
                                                Object obj1 = array.get(loc_i);
                                                if(!obj1.toString().equals("null")) {
                                                    /*String obj2 = obj1.toString().substring(6, 10) + "-" + obj1.toString().substring(3, 5)
                                                            + "-" + obj1.toString().substring(0, 2);*/
                                                    //Long date = Long.parseLong(obj1.toString());
                                                    insertStmt.bindString(loc_i + 1, obj1.toString());
                                                }
                                                else
                                                    insertStmt.bindNull(loc_i + 1);
                                                break;
                                            default:
                                                Object obj = array.get(loc_i);
                                                if(!obj.toString().equals("null"))
                                                    insertStmt.bindString(loc_i + 1, obj.toString());
                                                else
                                                    insertStmt.bindNull(loc_i + 1);
                                                break;
                                        }
                                    }
                                    /*if(table.equals("I_DEFECT"))
                                        insertStmt.bindNull(array.length() - 1);
                                    else {
                                        switch(info.types[array.length() - 1]) {
                                            case "System.DateTime":
                                            case "System.Date":
                                                Object obj1 = array.get(array.length() - 1);
                                                if(!obj1.toString().equals("null")) {
                                                   *//*String obj2 = obj1.toString().substring(6, 10) + "-" + obj1.toString().substring(3, 5)
                                                            + "-" + obj1.toString().substring(0, 2);*//*
                                                    //Long date = Long.parseLong(obj1.toString());
                                                    insertStmt.bindString(array.length(), obj1.toString());
                                                }
                                                else
                                                    insertStmt.bindNull(array.length());
                                                break;
                                            default:
                                                Object obj = array.get(array.length() - 1);
                                                if(!obj.toString().equals("null"))
                                                    insertStmt.bindString(array.length(), obj.toString());
                                                else
                                                    insertStmt.bindNull(array.length());
                                                break;
                                        }
                                    }*/
                                    insertStmt.executeInsert();
                                }
                                db.setTransactionSuccessful();
                                db.endTransaction();
                                if(isCancelled())
                                    return;
                                t = System.currentTimeMillis() - t;
                                time3 += t;
                                /*for (int j = 0; j < result.length(); j += 50) {
                                    int ind = 0;
                                    String sqlRequest = "";
                                    t = System.currentTimeMillis();
                                    db.beginTransaction();
                                    while (ind < 50 && ind + j < result.length()) {
                                        array = result.getJSONArray(j + ind);
                                        SQLiteStatement insertStmt = db.compileStatement(request);
                                        sqlRequest += "(";
                                        for (int k = 0; k < array.length(); k++) {
                                            switch(info.types[k]) {
                                                case "System.DateTime":
                                                case "System.Date":
                                                    Object obj1 = array.get(k);
                                                    if(!obj1.toString().equals("null") )
                                                        sqlRequest += obj1.toString().substring(0, 5) + "-" + obj1.toString().substring(5, 7)
                                                                + "-" + obj1.toString().substring(7, 9) + "',";
                                                    else
                                                        sqlRequest += null + ",";
                                                    break;
                                                default:
                                                    Object obj = array.get(k);
                                                    if(!obj.toString().equals("null"))
                                                        sqlRequest += obj + ",";
                                                    else
                                                        sqlRequest += null + ",";
                                                    break;
                                            }
                                        }
                                        if(table.equals("I_DEFECT")) {
                                            sqlRequest += null + ",";
                                        }
                                        sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                        sqlRequest += "),";
                                        ind++;
                                    }
                                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                    Log.d("Tag", "completed for isso = " + array.getString(0));
                                    db.execSQL("insert into " + table + " values " + sqlRequest);
                                    t = System.currentTimeMillis() - t;
                                    time3 += t;
                                    db.setTransactionSuccessful();
                                    db.endTransaction();
                                }*/
                            }
                            else {
                                for(n = 0; n < 20 && n + index < issos.length; n++ ) {
                                    dialog.setProgress((int) ((double) (n + index) / issos.length * 100));
                                    publishProgress(String.valueOf((int) ((double) (n + index) / issos.length * 100)),
                                            "Шаг 5 из " + countOfSteps + ". Получение фотографий дефектов...");
                                    List<String> list = new ArrayList<>();
                                    list.add(String.valueOf(issos[n + index].CIsso));
                                    JSONArray locArr = new JSONArray();
                                    locArr.put(issos[n + index].CIsso);

                                    JSONArray result = GetInformationDefects(table, table, pks, list, locArr);
                                    if(isCancelled())
                                        return;
                                    Cursor dbCursor = db.query(table, null, null, null, null, null, null);
                                    String[] columnNames = dbCursor.getColumnNames();
                                    String[] questionMarks = new String[columnNames.length];
                                    Arrays.fill(questionMarks, "?");
                                    dbCursor.close();
                                    String request = "insert into " + table + "(" + TextUtils.join(",", columnNames) +
                                            ") values (" + TextUtils.join(",", questionMarks) + ")";
                                    t = System.currentTimeMillis();
                                    db.beginTransaction();
                                    SQLiteStatement insertStmt = db.compileStatement(request);
                                    for (int j = 0; j < result.length(); j++) {
                                        array = result.getJSONArray(j);
                                        insertStmt.clearBindings();
                                        for(int loc_i = 0; loc_i < array.length(); loc_i++) {
                                            switch(info.types[loc_i]) {
                                                /*case "System.DateTime":
                                                case "System.Date":
                                                    Object obj1 = array.get(loc_i);
                                                    if(!obj1.toString().equals("null")) {
                                                    *//*String obj2 = obj1.toString().substring(6, 10) + "-" + obj1.toString().substring(3, 5)
                                                            + "-" + obj1.toString().substring(0, 2);*//*
                                                        //Long date = Long.parseLong(obj1.toString());
                                                        insertStmt.bindString(array.length(), obj1.toString());
                                                    }
                                                    else
                                                        insertStmt.bindNull(loc_i + 1);
                                                    break;*/
                                                case "System.Byte[]":
                                                    Object byteObj = array.get(loc_i);
                                                    if(!byteObj.toString().equals("null")) {
                                                        byte[] obj2 = org.kobjects.base64.Base64.decode(byteObj.toString());
                                                        insertStmt.bindBlob(loc_i + 1, obj2);
                                                    }
                                                    else {
                                                        insertStmt.bindNull(loc_i + 1);
                                                    }
                                                    break;
                                                default:
                                                    Object obj = array.get(loc_i);
                                                    if(!obj.toString().equals("null"))
                                                        insertStmt.bindString(loc_i + 1, obj.toString());
                                                    else
                                                        insertStmt.bindNull(loc_i + 1);
                                                    break;
                                            }
                                        }
                                        insertStmt.executeInsert();
                                        if(isCancelled()) {
                                            db.setTransactionSuccessful();
                                            db.endTransaction();
                                            return;
                                        }
                                    }
                                    db.setTransactionSuccessful();
                                    db.endTransaction();
                                    t = System.currentTimeMillis() - t;
                                    time3 += t;
                                }
                            }

                            /*Cursor cr;
                            List<String> list = new ArrayList<>();
                            for(int i = 0; i < array.length(); i++){
                                list.add(array.get(i).toString());
                            }
                            if (table.substring(0, 1).equals("I") && (table.equals("I_DEF_MOD") || table.equals("I_DEF_DESCR"))) {
                                String strftime = "strftime('%d.%m.%Y', DATE)||' 0:00:00' as DATE";
                                String str = pks.substring(0, pks.indexOf("DATE")) + strftime + pks.substring(pks.indexOf("DATE") + 4, pks.length());
                                cr = db.rawQuery("select " + str + " from (select " + pks + " from " + table + " where C_ISSO in ("
                                        + TextUtils.join(",", list) + ") order by " + pks + ")", null);
                            } else {
                                cr = db.rawQuery("select " + pks + " from " + table + " where C_ISSO in ("
                                        + TextUtils.join(",", list) + ") order by " + pks, null);
                            }
                            JSONArray result = new JSONArray();
                            if (cr.moveToFirst()) {
                                for (int i = 0; i < cr.getCount(); i++) {
                                    int columnCount = cr.getColumnCount();
                                    String str = "";
                                    for (int j = 0; j < columnCount; j++) {
                                        str += cr.getString(j) + "|";
                                    }
                                    result.put(str.substring(0, str.length() - 1));
                                    cr.moveToNext();
                                }
                            }
                            cr.close();
                            t = System.currentTimeMillis();
                            params = new HashMap<>();
                            params.put("id", id);
                            params.put("table", table);
                            params.put("clientPks", result);
                            params.put("cIssos", array);
                            object = ConnectToServer(JSONMETHODS[16], params);
                            t = System.currentTimeMillis() - t;
                            time1 += t;
                            result = object.getJSONArray(JSONMETHODRESULTS[16]);

                            String[] dataType = null;
                            if(result.length() > 0) {
                                dataType = new String[result.getJSONArray(0).length()];
                                for (int i = 0; i < result.getJSONArray(0).length(); i++) {
                                    dataType[i] = result.getJSONArray(0).getString(i);
                                    if(dataType[i].equals("System.Byte[]"))
                                        isByte = true;
                                }
                            }
                            if(!isByte)
                                for (int j = 1; j < result.length(); j += 50) {
                                    int ind = 0;
                                    String sqlRequest = "";
                                    t = System.currentTimeMillis();
                                    db.beginTransaction();
                                    while (ind < 50 && ind + j < result.length()) {
                                        array = result.getJSONArray(j + ind);
                                        sqlRequest += "(";
                                        for (int k = 0; k < array.length(); k++) {
                                            switch(dataType[k]) {
                                                case "System.DateTime":
                                                case "System.Date":
                                                    String obj1 = array.getString(k);
                                                    if(!obj1.equals(""))
                                                        sqlRequest += "'" + obj1.substring(6, 10) + "-" + obj1.substring(3, 5)
                                                                + "-" + obj1.substring(0, 2) + "',";
                                                    else
                                                        sqlRequest += "'',";
                                                    break;
                                                case "System.Byte[]":
                                                    byte[] obj2 = org.kobjects.base64.Base64.decode(array.getString(k));
                                                    sqlRequest += "'" + bytesToHex(obj2)+ "',";
                                                    break;
                                                default:
                                                    Object obj = array.get(k);
                                                    sqlRequest += "'" + obj + "',";
                                                    break;
                                            }

                                            *//*if (table.substring(0, 1).equals("I") && (table.equals("I_DEF_MOD") || table.equals("I_DEF_DESCR"))) {
                                                String obj = array.getString(k);
                                                sqlRequest += "'" + obj.substring(6, 10) + "-" + obj.substring(3, 5)
                                                        + "-" + obj.substring(0, 2) + "',";
                                            } else {
                                                Object obj = array.get(k);
                                                sqlRequest += "'" + obj + "',";
                                            }*//*
                                        }
                                        sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                        sqlRequest += "),";
                                        ind++;
                                    }
                                    sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
                                    Log.d("Tag", "completed for isso = " + array.getString(0));
                                    db.execSQL("insert into " + table + " values " + sqlRequest);
                                    t = System.currentTimeMillis() - t;
                                    time3 += t;
                                    db.setTransactionSuccessful();
                                    db.endTransaction();
                                }
                            else {
                                Cursor dbCursor = db.query(table, null, null, null, null, null, null);
                                String[] columnNames = dbCursor.getColumnNames();
                                String[] questionMarks = new String[columnNames.length];
                                Arrays.fill(questionMarks, "?");
                                String request = "insert into " + table + "(" + TextUtils.join(",", columnNames) +
                                        ") values (" + TextUtils.join(",", questionMarks) + ")";
                                t = System.currentTimeMillis();
                                db.beginTransaction();
                                SQLiteStatement insertStmt = db.compileStatement(request);
                                for (int j = 1; j < result.length(); j++) {
                                    array = result.getJSONArray(j);
                                    insertStmt.clearBindings();
                                    for(int loc_i = 0; loc_i < array.length(); loc_i++) {
                                        switch(dataType[loc_i]) {
                                            case "System.DateTime":
                                            case "System.Date":
                                                String obj1 = array.getString(loc_i);
                                                if(!obj1.equals(""))
                                                    obj1 = obj1.substring(6, 10) + "-" + obj1.substring(3, 5)
                                                            + "-" + obj1.substring(0, 2);
                                                insertStmt.bindString(loc_i + 1, obj1);
                                                break;
                                            case "System.Byte[]":
                                                byte[] obj2 = org.kobjects.base64.Base64.decode(array.getString(loc_i));
                                                insertStmt.bindBlob(loc_i + 1, obj2);
                                                break;
                                            default:
                                                String obj = array.getString(loc_i);
                                                insertStmt.bindString(loc_i + 1, obj);
                                                break;
                                        }
                                    }
                                    insertStmt.executeInsert();
                                }
                                db.setTransactionSuccessful();
                                db.endTransaction();
                                t = System.currentTimeMillis() - t;
                                time3 += t;
                            }*/
                        }
                    }
                    bibs++;
                }
                Cursor addcursor = db.rawQuery("select C_GR_CONSTR from S_GR_CONSTR", null);
                addcursor.moveToFirst();
                for(int i = 0; i < addcursor.getCount(); i++) {
                    int c_gr_constr = MainConstrId(addcursor.getInt(0));
                    db.execSQL("update I_DEFECT set MAIN_CONSTR_ID = " +
                            c_gr_constr + " where C_GR_CONSTR = " + addcursor.getInt(0));
                    addcursor.moveToNext();
                }
                addcursor.close();


                if(downloadPhoto)
                    doPhotoSync(countOfSteps, issos, issoList);
                if(downloadScheme)
                    doSchemeSync(countOfSteps, issos, issoList);
                ConnectToServer(JSONMETHODS[10], params, true);
                result = "Синхронизация завешена. \nВремя работы на клиенте: " + time1/1000 + " c.\nВремя работы на сервере: " + time2 / 1000 + " c.\nВремя отправки: " + (time1 - time2)/1000 +
                        " c.\nВремя записи в БД: " + time3 / 1000 + " c.";

            } catch (Exception e) {
                if (result.equals(""))
                    result = "Не удалось подключиться к удаленному серверу по адресу " + host + " Обратитесь к заказчику работ.";
                Log.d("Tag", "Гуляй Вася");
                if(!id.equals("")) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", id);
                    ConnectToServer(JSONMETHODS[10], params, true);
                }
            }
        }

        int MainConstrId(int c_gr_constr) {
            if(c_gr_constr < 1000) return c_gr_constr;
            Cursor cr = dbHelper.getReadableDatabase().rawQuery("select PARENT_GROUP from S_GR_CONSTR where C_GR_CONSTR = "
                    + c_gr_constr, null);
            cr.moveToFirst();
            int h = cr.getInt(0);
            cr.close();
            return MainConstrId(h);
        }

        void doPhotoSync(int countOfSteps, HttpsIsso[] issos, String issoList) {
            try {
                SQLiteDatabase db = new DBHelper(NEWSettingsActivity.this).getReadableDatabase();
                db.beginTransaction();
                db.execSQL("delete from UPLOAD_PHOTOS where C_ISSO not in (" + issoList.substring(1) + ")");
                db.setTransactionSuccessful();
                db.endTransaction();
                for(int index = 0; index < issos.length; index += 20) {
                    long t = System.currentTimeMillis();
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", id);
                    params.put("table", "I_FOTO");
                    JSONArray array = new JSONArray();
                    int n = 0;
                    while (n < 20 && n + index < issos.length) {
                        array.put(issos[n + index].CIsso);
                        n++;
                    }
                    Log.d("Tag", "doing for isso = " + array.getString(0));
                    JSONObject object = ConnectToServer(JSONMETHODS[15], params, false);
                    if (isCancelled())
                        return;
                    t = System.currentTimeMillis() - t;
                    time1 += t;
                    Gson gson = new Gson();
                    PrimaryInfo info = gson.fromJson(object.getString(JSONMETHODRESULTS[15]), PrimaryInfo.class);

                    String pks = "";
                    for (int i = 0; i < info.pks.length; i++) {
                        pks += info.pks[i] + ",";
                    }
                    pks = pks.substring(0, pks.length() - 1);
                    for(n = 0; n < 20 && n + index < issos.length; n++ ) {
                        dialog.setProgress((int) ((double) (index + n) / issos.length * 100));
                        publishProgress(String.valueOf((int) ((double) (index + n)/ issos.length * 100 )), "Шаг 6 из " + countOfSteps + ". Получение фотографий сооружений...");
                        List<String> list = new ArrayList<>();
                        list.add(String.valueOf(issos[n + index].CIsso));
                        JSONArray locArr = new JSONArray();
                        locArr.put(issos[n + index].CIsso);

                        JSONArray result = GetInformationDefects("UPLOAD_PHOTOS", "I_FOTO", pks, list, locArr);
                        if (isCancelled())
                            return;
                        Cursor dbCursor = new DBHelper(NEWSettingsActivity.this).getReadableDatabase().query("UPLOAD_PHOTOS", null, null, null, null, null, null);
                        String[] columnNames = dbCursor.getColumnNames();
                        String[] questionMarks = new String[columnNames.length];
                        Arrays.fill(questionMarks, "?");
                        dbCursor.close();
                        String request = "insert into " + "UPLOAD_PHOTOS" + "(" + TextUtils.join(",", columnNames) +
                                ") values (" + TextUtils.join(",", questionMarks) + ")";
                        t = System.currentTimeMillis();
                        db.beginTransaction();
                        SQLiteStatement insertStmt = db.compileStatement(request);
                        for (int j = 0; j < result.length(); j++) {
                            array = result.getJSONArray(j);
                            insertStmt.clearBindings();
                            //C_ISSO
                            Object obj = array.get(0);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(1, obj.toString());
                            else
                                insertStmt.bindNull(1);
                            //N
                            obj = array.get(1);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(2, obj.toString());
                            else
                                insertStmt.bindNull(2);
                            //COMMENTS
                            obj = array.get(2);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(3, obj.toString());
                            else
                                insertStmt.bindNull(3);
                            //PHOTO
                            Object byteObj = array.get(3);
                            if (!byteObj.toString().equals("null")) {
                                byte[] obj2 = org.kobjects.base64.Base64.decode(byteObj.toString());
                                insertStmt.bindBlob(4, obj2);
                            } else {
                                insertStmt.bindNull(4);
                            }
                            //PHOTO_DATE
                            obj = array.get(5);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(5, obj.toString());
                            else
                                insertStmt.bindNull(5);
                            insertStmt.executeInsert();
                            if (isCancelled()) {
                                db.setTransactionSuccessful();
                                db.endTransaction();
                                return;
                            }
                        }
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        t = System.currentTimeMillis() - t;
                        time3 += t;
                    }


                    /*Cursor cr = dbHelper.getReadableDatabase().query("I_ISSO", new String[]{"C_ISSO"}, null, null, null, null, null);
                    if (cr.moveToFirst()) {
                        //dbHelper.getWritableDatabase().execSQL("delete from UPLOAD_PHOTOS");
                        for (int i = 0; i < cr.getCount(); i++) {
                            dialog.setProgress((int) ((double) i / cr.getCount() * 100));
                            publishProgress(String.valueOf((int) ((double) i / cr.getCount() * 100)), "Шаг 5 из " + countOfSteps + ". Получение фотографий сооружений...");
                            params = new HashMap<>();
                            params.put("c_isso", cr.getInt(cr.getColumnIndex("C_ISSO")));
                            object = ConnectToServer(JSONPHOTOMETHODS[0], params);
                            if (isCancelled())
                                return;
                            gson = new Gson();
                            assert object != null;
                            UploadPhotoForIsso[] photos = gson.fromJson(object.getString(JSONPHOTOMETHODRESULTS[0]), UploadPhotoForIsso[].class);
                            for (UploadPhotoForIsso photo : photos) {
                                ContentValues cv = new ContentValues();
                                cv.put("C_ISSO", photo.C_ISSO);
                                cv.put("N", photo.N);
                                cv.put("COMMENTS", photo.Comments);
                                byte[] photoArray = null;
                                if (!photo.Photo.equals(""))
                                    photoArray = Base64.decode(photo.Photo);
                                cv.put("PHOTO", photoArray);
                                cv.put("PHOTO_DATE", photo.PhotoDate);
                                dbHelper.getWritableDatabase().insertWithOnConflict("UPLOAD_PHOTOS", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                            }
                            cr.moveToNext();
                            if (isCancelled())
                                return;
                        }
                    }
                    t = System.currentTimeMillis() - t;*/
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                if (result.equals(""))
                    result = "Не удалось подключиться к удаленному серверу по адресу " + host + " Обратитесь к заказчику работ.";
            }
        }

        void doSchemeSync(int countOfSteps, HttpsIsso[] issos, String issoList) {
            try {
                SQLiteDatabase db = new DBHelper(NEWSettingsActivity.this).getReadableDatabase();
                db.beginTransaction();
                db.execSQL("delete from UPLOAD_SCHEMES where C_ISSO not in (" + issoList.substring(1) + ")");
                db.setTransactionSuccessful();
                db.endTransaction();
                for(int index = 0; index < issos.length; index += 20) {
                    long t = System.currentTimeMillis();
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", id);
                    params.put("table", "I_SXEMA");
                    JSONArray array = new JSONArray();
                    int n = 0;
                    while (n < 20 && n + index < issos.length) {
                        array.put(issos[n + index].CIsso);
                        n++;
                    }
                    Log.d("Tag", "doing for isso = " + array.getString(0));
                    JSONObject object = ConnectToServer(JSONMETHODS[15], params, false);
                    if (isCancelled())
                        return;
                    t = System.currentTimeMillis() - t;
                    time1 += t;
                    Gson gson = new Gson();
                    PrimaryInfo info = gson.fromJson(object.getString(JSONMETHODRESULTS[15]), PrimaryInfo.class);

                    String pks = "";
                    for (int i = 0; i < info.pks.length; i++) {
                        pks += info.pks[i] + ",";
                    }
                    pks = pks.substring(0, pks.length() - 1);
                    for(n = 0; n < 20 && n + index < issos.length; n++ ) {
                        dialog.setProgress((int) ((double) (index + n)  / issos.length * 100));
                        publishProgress(String.valueOf((int) ((double) (index + n) / issos.length * 100)),
                                "Шаг "+ (downloadPhoto ? "7" : "6") + " из " + countOfSteps + ". Получение чертежей по сооружениям...");
                        List<String> list = new ArrayList<>();
                        list.add(String.valueOf(issos[n + index].CIsso));
                        JSONArray locArr = new JSONArray();
                        locArr.put(issos[n + index].CIsso);

                        JSONArray result = GetInformationDefects("UPLOAD_SCHEMES", "I_SXEMA", pks, list, locArr);
                        if (isCancelled())
                            return;
                        Cursor dbCursor = new DBHelper(NEWSettingsActivity.this).getReadableDatabase().query("UPLOAD_SCHEMES", null, null, null, null, null, null);
                        String[] columnNames = dbCursor.getColumnNames();
                        String[] questionMarks = new String[columnNames.length];
                        Arrays.fill(questionMarks, "?");
                        dbCursor.close();
                        String request = "insert into " + "UPLOAD_SCHEMES" + "(" + TextUtils.join(",", columnNames) +
                                ") values (" + TextUtils.join(",", questionMarks) + ")";
                        t = System.currentTimeMillis();
                        db.beginTransaction();
                        SQLiteStatement insertStmt = db.compileStatement(request);
                        for (int j = 0; j < result.length(); j++) {
                            array = result.getJSONArray(j);
                            insertStmt.clearBindings();
                            //C_ISSO
                            Object obj = array.get(0);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(1, obj.toString());
                            else
                                insertStmt.bindNull(1);
                            //N
                            obj = array.get(1);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(2, obj.toString());
                            else
                                insertStmt.bindNull(2);
                            //COMMENTS
                            obj = array.get(2);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(3, obj.toString());
                            else
                                insertStmt.bindNull(3);
                            //SCHEME
                            Object byteObj = array.get(3);
                            if (!byteObj.toString().equals("null")) {
                                byte[] obj2 = org.kobjects.base64.Base64.decode(byteObj.toString());
                                insertStmt.bindBlob(4, obj2);
                            } else {
                                insertStmt.bindNull(4);
                            }
                            //THUMBNAIL
                            byteObj = array.get(4);
                            if (!byteObj.toString().equals("null")) {
                                byte[] obj2 = org.kobjects.base64.Base64.decode(byteObj.toString());
                                insertStmt.bindBlob(5, obj2);
                            } else {
                                insertStmt.bindNull(5);
                            }
                            //PHOTO_DATE
                            obj = array.get(5);
                            if (!obj.toString().equals("null"))
                                insertStmt.bindString(6, obj.toString());
                            else
                                insertStmt.bindNull(6);
                            insertStmt.executeInsert();
                            if (isCancelled()) {
                                db.setTransactionSuccessful();
                                db.endTransaction();
                                return;
                            }
                        }
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        t = System.currentTimeMillis() - t;
                        time3 += t;
                    }
                }
                /*long t = System.currentTimeMillis();
                Cursor cr = dbHelper.getReadableDatabase().query("I_ISSO", new String[]{"C_ISSO"}, null, null, null, null, null);
                if(cr.moveToFirst()) {
                    dbHelper.getWritableDatabase().execSQL("delete from UPLOAD_SCHEMES");
                    for(int i = 0; i < cr.getCount(); i++) {
                        dialog.setProgress((int) ((double)i / cr.getCount() * 100));
                        publishProgress(String.valueOf((int) ((double)i / cr.getCount() * 100)), "Шаг "+ (downloadPhoto ? "6" : "5") + " из " + countOfSteps + ". Получение чертежей по сооружениям...");
                        Map<String, Object> params = new HashMap<>();
                        params.put("c_isso", cr.getInt(cr.getColumnIndex("C_ISSO")));
                        JSONObject object = ConnectToServer(JSONSCHEMEMETHODS[0], params);
                        if(isCancelled())
                            return;
                        Gson gson = new Gson();
                        assert object != null;
                        UploadSchemeForIsso[] schemes = gson.fromJson(object.getString(JSONSCHEMEMETHODRESULTS[0]), UploadSchemeForIsso[].class);
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
                        if(isCancelled())
                            return;
                    }
                }*/
            } catch (Exception e) {
                if (result.equals(""))
                    result = "Не удалось подключиться к удаленному серверу по адресу " + host + " Обратитесь к заказчику работ.";
                Log.d("Tag", "Гуляй Вася");
            }
        }

        JSONArray GetInformationDefects(String tableLoc, String tableServ, String pks, List<String> list, JSONArray array) {
            try {
                Cursor cr;
                SQLiteDatabase db = new DBHelper(NEWSettingsActivity.this).getReadableDatabase();
                if (tableLoc.substring(0, 1).equals("I") && !tableLoc.equals("I_DEFECT")) {
                    //String strftime = "strftime('%d.%m.%Y', DATE/1000, 'unixepoch')||' 0:00:00' as DATE";
                    //String str = pks.substring(0, pks.indexOf("DATE")) + strftime + pks.substring(pks.indexOf("DATE") + 4, pks.length());
                    cr = db.rawQuery("select " + pks + " from (select " + pks + " from " + tableLoc + " where C_ISSO in ("
                            + TextUtils.join(",", list) + ") order by " + pks + ")", null);
                } else {
                    cr = db.rawQuery("select " + pks + " from " + tableLoc + " where C_ISSO in ("
                            + TextUtils.join(",", list) + ") order by " + pks, null);
                }
                JSONArray result = new JSONArray();
                if (cr.moveToFirst()) {
                    for (int i = 0; i < cr.getCount(); i++) {
                        int columnCount = cr.getColumnCount();
                        String str = "";
                        for (int j = 0; j < columnCount; j++) {
                            str += cr.getString(j) + "|";
                        }
                        result.put(str.substring(0, str.length() - 1));
                        cr.moveToNext();
                    }
                }
                cr.close();
                long t = System.currentTimeMillis();
                Map<String, Object> params = new HashMap<>();
                params.put("id", id);
                params.put("table", tableServ);
                params.put("clientPks", result);
                params.put("cIssos", array);
                JSONObject object = ConnectToServer(JSONMETHODS[16], params, false);
                t = System.currentTimeMillis() - t;
                time1 += t;
                return object.getJSONArray(JSONMETHODRESULTS[16]);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        //Проверка наличия таблицы в БД
        private boolean isTableExists(String tableName, int c_isso) {
            Cursor c = new DBHelper(NEWSettingsActivity.this).getReadableDatabase().rawQuery("SELECT DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
            if(c != null) {
                if(c.getCount() > 0 ) {
                    if(c_isso < 0) {
                        c.close();
                        return true;
                    }
                    else {
                        Cursor cr = new DBHelper(NEWSettingsActivity.this).getReadableDatabase().rawQuery("select * from " + tableName + " where C_ISSO = " + c_isso, null);
                        if(cr.getCount() > 0) {
                            cr.close();
                            return true;
                        }
                    }
                }
                c.close();
            }
            return false;
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

        String[] value = new String[] {"",""};

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d("Tag", values[0]);
            super.onProgressUpdate(values);
            this.dialog.setProgress(Integer.parseInt(values[0]));
            this.dialog.setMessage(values[1]);
            value = new String[] {values[0],values[1]};
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

        void attach(NEWSettingsActivity activity) {
            this.context = activity;
        }

        String[] getProgress() {
            return(value);
        }

        void detach() {
            context=null;
        }
    }


}

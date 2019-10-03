package com.development.aisisso.isso_i;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdvancedDownloadInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0"))
            setTheme(R.style.DialogTheme);
        else
            setTheme(R.style.DialogThemeLight);
        setContentView(R.layout.advanced_download_layout);
        final UserCollection collection = (UserCollection) getIntent().getSerializableExtra("collection");
        TextView tvName = (TextView) findViewById(R.id.tvCollectionName);
        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvDates = (TextView) findViewById(R.id.tvDates);
        TextView tvCountIsso = (TextView) findViewById(R.id.tvCountISSO);
        tvName.setText(collection.CollectionName);
        tvDescription.setText(collection.Description);
        tvDates.setText(SettingsActivity.getDate(collection.DateCreate) + " / " + SettingsActivity.getDate(collection.DateModify));
        tvCountIsso.setText("" + collection.IssoList.split(",").length);

        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            ((LinearLayout) findViewById(R.id.linLayoutDownload)).setBackgroundColor(getResources().getColor(R.color.background_material_light));
            btnDelete.setBackground(getResources().getDrawable(R.drawable.btn_delete_light));
        }
        else {
            ((LinearLayout) findViewById(R.id.linLayoutDownload)).setBackgroundColor(getResources().getColor(R.color.background_material_dark));
            btnDelete.setBackground(getResources().getDrawable(R.drawable.btn_delete_dark));
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Будет удалено..", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnDownload = (Button) findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("collection", collection);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

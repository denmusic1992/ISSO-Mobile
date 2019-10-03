package com.development.aisisso.isso_i;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CustomDefectDialog extends Dialog {
    private Context context;
    public ListView lvFilterDefects;
    public ListView lvFilterAll;
    public Button btnOK;
    public Button btnCancel;
    public Handler handler;

    public CustomDefectDialog(Context context, Handler handler) {
        super(context);
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_defect);
        lvFilterDefects = (ListView) findViewById(R.id.listViewFilterDefects);
        lvFilterAll = (ListView) findViewById(R.id.listViewAllDefects);
        btnOK = (Button) findViewById(R.id.btnDefOK);
        btnCancel = (Button) findViewById(R.id.btnDefCancel);
        /*if (PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Theme", "0").equals("0"))
            findViewById(R.id.line).setBackgroundColor(Color.WHITE);
        else
            findViewById(R.id.line).setBackgroundColor(Color.BLACK);*/

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, ABDM_TableLayout_I_DEFECT.filterText);
        lvFilterDefects.setAdapter(adapter);
        lvFilterDefects.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvFilterDefects.setDividerHeight(2);
        lvFilterAll.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, new String[]{"Все дефекты"}));
        lvFilterAll.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvFilterAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvFilterDefects.clearChoices();
                adapter.notifyDataSetChanged();

            }
        });
        if (MainActivity.theme.equals("0")) {
            findViewById(R.id.linLayoutBibs).setBackground(getContext().getDrawable(R.drawable.cell_borders_dark));
            lvFilterDefects.setDivider(getContext().getDrawable(R.drawable.cell_borders_dark));
        }
        else {
            findViewById(R.id.linLayoutBibs).setBackground(getContext().getDrawable(R.drawable.cell_borders_light));
            lvFilterDefects.setDivider(getContext().getDrawable(R.drawable.cell_borders_light));
        }
        if(ABDM_TableLayout_I_DEFECT.defFilter.contains("ALL")) {
            lvFilterAll.setItemChecked(0, true);
        }
        else {
            for(int i = 0; i < lvFilterDefects.getCount(); i++) {
                if(ABDM_TableLayout_I_DEFECT.defFilter.contains(String.valueOf(ABDM_TableLayout_I_DEFECT.filterConstrId.get(i))))
                    lvFilterDefects.setItemChecked(i, true);
            }
        }
        if(adapter.getCount() > 2){
            View item = adapter.getView(0, null, lvFilterDefects);
            item.measure(0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    (int) (2.8 * item.getMeasuredHeight()));
            lvFilterDefects.setLayoutParams(params);
        }
        lvFilterDefects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvFilterAll.setItemChecked(0, false);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ABDM_TableLayout_I_DEFECT.defFilter.clear();
                if (!lvFilterAll.isItemChecked(0)) {
                    SparseBooleanArray sbArray = lvFilterDefects.getCheckedItemPositions();
                    for (int i = 0; i < sbArray.size(); i++) {
                        int key = sbArray.keyAt(i);
                        if (sbArray.get(key))
                            ABDM_TableLayout_I_DEFECT.defFilter.add(String.valueOf(ABDM_TableLayout_I_DEFECT.filterConstrId.get(key)));
                    }
                    if (ABDM_TableLayout_I_DEFECT.defFilter.size() == 0) {
                        ABDM_TableLayout_I_DEFECT.defFilter.add("");
                    }
                } else {
                    ABDM_TableLayout_I_DEFECT.defFilter.add("ALL");
                }
                handler.sendEmptyMessage(0);
                dismiss();
            }
        });
    }
}

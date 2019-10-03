package com.development.aisisso.isso_i;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomDownloadListAdapter extends BaseAdapter {

    private UserCollection[] collections;
    private Context context;
    private LayoutInflater lInflater;
    private int selectedPosition;

    public CustomDownloadListAdapter(Context context, UserCollection[] collections, int selectedPosition) {
        this.context = context;
        this.collections = collections;
        this.selectedPosition = selectedPosition;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return collections.length;
    }

    @Override
    public Object getItem(int position) {
        return collections[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getDate(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.downloadinfo_layout, parent, false);
        }
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

        UserCollection collection = collections[position];

        String dateCreate = getDate(collection.DateCreate);
        String dateModify = getDate(collection.DateModify);
        String strDate = dateCreate + "/" + (dateCreate.equals(dateModify) ? "без зименений" : dateModify);

        ((TextView) view.findViewById(R.id.tvMainHeaderInfo)).setText("'" + collection.CollectionName + "' (" + collection.IssoList.split(",").length + ") от " +
                getDate(collection.DateCreate));
        ((TextView) view.findViewById(R.id.tv_name)).setText(collection.CollectionName);
        ((TextView) view.findViewById(R.id.tv_date)).setText(strDate);
        ((TextView) view.findViewById(R.id.tv_description)).setText(collection.Description);
        ((TextView) view.findViewById(R.id.tv_numofisso)).setText("" + collection.IssoList.split(",").length);


        final RadioButton rb = (RadioButton) view.findViewById(R.id.radiobutton_download);
        rb.setClickable(false);
        return view;
    }
}

package com.development.aisisso.isso_i;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomListAdapter extends BaseAdapter {

    public UserCollection[] collections;
    public Context context;
    LayoutInflater lInflater;


    public CustomListAdapter (Context context, UserCollection[] collections) {
        this.context = context;
        this.collections = collections;
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.expandable_listview_item, parent, false);
        }
        UserCollection collection = collections[position];

        ((TextView) view.findViewById(R.id.tvIssoIUserCollection)).setText(collection.CollectionName);
        ((TextView) view.findViewById(R.id.tvDateCreate)).setText(getDate(collection.DateCreate));
        ((TextView) view.findViewById(R.id.tvCollectionDescription)).setText(getDate(collection.DateCreate));
        ((TextView) view.findViewById(R.id.tvDateModify)).setText(getDate(collection.DateCreate));

        return view;
    }
}

package com.development.aisisso.isso_i;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CustomExpandListViewAdapter extends BaseExpandableListAdapter{
    private Context context;
    private UserCollection[] collections;
    customDownloadListener downloadListener;
    customDeleteListener deleteListener;

    public interface customDownloadListener {
        void onDownload(UserCollection collection);
    }

    public interface customDeleteListener {
        void onDelete(UserCollection collection);
    }

    public void setCustomDownloadListener(customDownloadListener listener) {
        this.downloadListener = listener;
    }

    public void setCustomDeleteListener(customDeleteListener listener) {
        this.deleteListener = listener;
    }

    public CustomExpandListViewAdapter(Context context, UserCollection[] collections) {
        this.context = context;
        this.collections = collections;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return collections[childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public String getDate(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        UserCollection collection = collections[groupPosition];
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        TextView tvDateCreate = (TextView) convertView.findViewById(R.id.tvDateCreate);
        TextView tvCollectionDescription = (TextView) convertView.findViewById(R.id.tvCollectionDescription);
        TextView tvDateModify = (TextView) convertView.findViewById(R.id.tvDateModify);
        tvDateCreate.setText(getDate(collection.DateCreate));
        tvCollectionDescription.setText(collection.Description);
        tvDateModify.setText(getDate(collection.DateCreate));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return collections[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return collections.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded,
                             View convertView, final ViewGroup parent) {
        final UserCollection collection = collections[groupPosition];
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvIssoIUserCollection);
        tv.setText(collection.CollectionName);
        Button btn = (Button) convertView.findViewById(R.id.expandable_toggle_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpanded)
                    ((ExpandableListView) parent).collapseGroup(groupPosition);
                else
                    ((ExpandableListView) parent).expandGroup(groupPosition, true);
            }
        });
        Button download = (Button) convertView.findViewById(R.id.btnDownload);
        Button delete = (Button) convertView.findViewById(R.id.btnDelete);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(downloadListener != null) {
                    downloadListener.onDownload(collection);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteListener != null) {
                    deleteListener.onDelete(collection);
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

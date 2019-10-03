package com.development.aisisso.isso_i;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GalleryImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Photo> bmp = new ArrayList<>();
    int width;


    public GalleryImageAdapter(Context mContext, ArrayList<Photo> bmp, int width) {
        this.mContext = mContext;
        this.bmp = bmp;
        this.width = width;
    }

    public int getCount() {
        return bmp.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView tv;
        public ImageView image;
    }

    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        // TODO Auto-generated method stub
        ViewHolder holder = null;

        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = li.inflate(R.layout.image_gallery_item, null);
            holder.tv = (TextView) view.findViewById(R.id.tvItem);
            holder.image = (ImageView) view.findViewById(R.id.imageItem);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray( bmp.get(index).photo, 0 ,bmp.get(index).photo.length);
        holder.image.setImageBitmap(bitmap);
        if(bitmap.getWidth() > bitmap.getHeight())
            holder.image.setLayoutParams(new LinearLayout.LayoutParams(5 * width / 11, 3 * width / 11));
        else
            holder.image.setLayoutParams(new LinearLayout.LayoutParams(3 * width / 11, 5 * width / 11));

        holder.image.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.tv.setTextSize(10);
        holder.tv.setMaxLines(1);
        holder.tv.setMaxWidth(holder.image.getLayoutParams().width);
        holder.tv.setText(bmp.get(index).photoName + "\n" + bmp.get(index).photoDate);
        return view;
    }
}

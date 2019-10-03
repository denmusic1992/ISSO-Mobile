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

public class ListAdapter extends BaseAdapter {

    private ArrayList<Photo> photos;
    private Context context;
    private int width;

    public ListAdapter(Context context, ArrayList<Photo> photos, int width) {
        this.photos = photos;
        this.context = context;
        this.width = width;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.row_layout, parent, false);
        }

        Photo p = getPhoto(position);

        if (p != null) {
            TextView name = (TextView) v.findViewById(R.id.tv_name);
            ImageView imageView = (ImageView) v.findViewById(R.id.photo_isso);

            if (name != null) {
                name.setText(p.photoName);
            }
            name.setMaxLines(1);
            name.setTextSize(10);
            Bitmap bitmap = BitmapFactory.decodeByteArray(p.photo, 0, p.photo.length);
            imageView.setImageBitmap(bitmap);
            if(bitmap.getWidth() > bitmap.getHeight())
                imageView.setLayoutParams(new LinearLayout.LayoutParams(5 * width / 11, 3 * width / 11));
            else
                imageView.setLayoutParams(new LinearLayout.LayoutParams(3 * width / 11, 5 * width / 11));

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return v;
    }

    Photo getPhoto(int position) {
        return ((Photo) getItem(position));
    }

}

class Photo {
    public byte[] photo;
    public String photoDate;
    public String photoName;

    public Photo(byte[] photo, String photoDate, String photoName) {
        this.photo = photo;
        this.photoDate = photoDate;
        this.photoName = photoName;
    }
}

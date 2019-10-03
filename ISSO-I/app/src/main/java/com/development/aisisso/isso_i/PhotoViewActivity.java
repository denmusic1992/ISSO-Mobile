package com.development.aisisso.isso_i;


import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.TextView;

import com.polites.android.GestureImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PhotoViewActivity extends Fragment {

    public int C_ISSO;
    public ArrayList<Photo> listPhotos;
    public View view;
    public Gallery gallery;
    public ListView listView;
    public int item_position = 0;
    public GestureImageView imagePreview;

    public PhotoViewActivity() {}
    public PhotoViewActivity(int C_ISSO) {
        this.C_ISSO = C_ISSO;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.photo_view_layout, container, false);
        Cursor cr = new DBHelper(view.getContext()).getReadableDatabase().rawQuery("select * from UPLOAD_PHOTOS " +
                "where C_ISSO=" + C_ISSO + " and PHOTO is not null order by N", null);
        listPhotos = new ArrayList<>();
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++ ) {
                listPhotos.add(new Photo(cr.getBlob(cr.getColumnIndex("PHOTO")), getDate(cr.getLong(
                        cr.getColumnIndex("PHOTO_DATE"))), cr.getString(cr.getColumnIndex("COMMENTS"))));
                cr.moveToNext();
            }
        }
        cr.close();

        final TextView galleryPhotoName = (TextView) view.findViewById(R.id.galleryPhotoName);
        if(listPhotos.size() > 0) {
            imagePreview = (GestureImageView) view.findViewById(R.id.imageView1);
            gallery = (Gallery) view.findViewById(R.id.gallery1);

            gallery.setSpacing(10);
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            if (listPhotos.size() > 1) {
                gallery.setAdapter(new GalleryImageAdapter(getContext(), listPhotos, metrics.widthPixels));
                gallery.setSelection(item_position, false);
                galleryPhotoName.setText(listPhotos.get(item_position).photoName + "\n" + listPhotos.get(item_position).photoDate);
                gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(listPhotos.get(position).photo, 0, listPhotos.get(position).photo.length);
                        imagePreview.reset();
                        imagePreview.setImageBitmap(bmp);
                        imagePreview.redraw();
                        galleryPhotoName.setText(listPhotos.get(position).photoName + "\n" + listPhotos.get(position).photoDate);
                        item_position = position;
                        listView.setSelection(item_position);
                    }
                });
                listView = (ListView) view.findViewById(R.id.list_view_photos);
                ListAdapter adapter = new ListAdapter(getContext(), listPhotos, metrics.heightPixels);
                listView.setAdapter(adapter);
                listView.setSelection(item_position);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(listPhotos.get(position).photo, 0, listPhotos.get(position).photo.length);
                        imagePreview.reset();
                        imagePreview.setImageBitmap(bmp);
                        imagePreview.redraw();
                        galleryPhotoName.setText(listPhotos.get(position).photoName + "\n" + listPhotos.get(position).photoDate);
                        item_position = position;
                        gallery.setSelection(item_position, false);
                    }
                });

            }
            if (view.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (listPhotos.size() > 1) {
                    listView.setVisibility(View.GONE);
                    gallery.setVisibility(View.VISIBLE);
                } else {
                    listView.setVisibility(View.GONE);
                    gallery.setVisibility(View.GONE);
                }
            } else if (view.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (listPhotos.size() > 1) {
                    listView.setVisibility(View.VISIBLE);
                    gallery.setVisibility(View.GONE);
                } else {
                    listView.setVisibility(View.GONE);
                    gallery.setVisibility(View.GONE);
                }
            }

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Bitmap bmp = BitmapFactory.decodeByteArray(listPhotos.get(item_position).photo, 0, listPhotos.get(item_position).photo.length);
                    imagePreview.reset();
                    imagePreview.setImageBitmap(bmp);
                    imagePreview.redraw();
                }
            });
        }
        else {
            galleryPhotoName.setText("Для данного ИССО фотографии отсутствуют");
            (view.findViewById(R.id.imageView1)).setVisibility(View.GONE);
            (view.findViewById(R.id.list_view_photos)).setVisibility(View.GONE);
            (view.findViewById(R.id.gallery1)).setVisibility(View.GONE);
        }
        return view;
    }

    public String getDate(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return milliseconds != 0 ? formatter.format(calendar.getTime()) : "";
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewGroup viewGroup = (ViewGroup) getView();
        if(listPhotos.size() > 1) {
            item_position = gallery.getSelectedItemPosition();
        }
        else {
            item_position = 0;
        }
        viewGroup.removeAllViewsInLayout();
        view = onCreateView(getActivity().getLayoutInflater(), viewGroup, null);
        viewGroup.addView(view);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = BitmapFactory.decodeByteArray(listPhotos.get(item_position).photo, 0, listPhotos.get(item_position).photo.length);
                imagePreview.reset();
                imagePreview.setImageBitmap(bmp);
                imagePreview.redraw();
            }
        });
        onResume();
    }

}

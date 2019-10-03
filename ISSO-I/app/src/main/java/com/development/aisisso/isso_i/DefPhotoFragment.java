package com.development.aisisso.isso_i;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.polites.android.GestureImageView;

public class DefPhotoFragment extends Fragment {
    private View view;
    private int C_ISSO;
    private int N_DEF;
    private long date;
    private GestureImageView imageView;

    public DefPhotoFragment(int C_ISSO, int N_DEF, long date) {
        this.C_ISSO = C_ISSO;
        this.N_DEF = N_DEF;
        this.date = date;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    public void setPhotoDef(int C_ISSO, int N_DEF, long date) {
        this.C_ISSO = C_ISSO;
        this.N_DEF = N_DEF;
        this.date = date;
        final Cursor cursor = new DBHelper(getContext()).getReadableDatabase().rawQuery("select FOTO from I_FOTO_DEF where C_ISSO = " + C_ISSO +
                " and N_DEF = " + N_DEF + " and DATE = " + date, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && !cursor.isNull(0)) {
            (view.findViewById(R.id.defPhotoName)).setVisibility(View.GONE);
            (view.findViewById(R.id.photoDefLinLayout)).setVisibility(View.VISIBLE);
            imageView = new GestureImageView(view.getContext());
            imageView.setMaxScale(10);
            imageView.setMinScale((float) 0.1);
            ((LinearLayout) view.findViewById(R.id.photoDefLinLayout)).removeAllViews();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((LinearLayout) view.findViewById(R.id.photoDefLinLayout)).addView(imageView, params);
            /*GestureImageView imageDef = (GestureImageView) view.findViewById(R.id.defImageView);
            Bitmap bmp = BitmapFactory.decodeByteArray(cursor.getBlob(0), 0, cursor.getBlob(0).length);
            imageDef.reset();
            imageDef.setImageBitmap(bmp);
            imageDef.redraw();*/
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Bitmap bmp = BitmapFactory.decodeByteArray(cursor.getBlob(0), 0, cursor.getBlob(0).length);
                    imageView.reset();
                    imageView.setImageBitmap(bmp);
                    imageView.redraw();
                }
            });
        }
        else {
            ((TextView) view.findViewById(R.id.defPhotoName)).setText("Фотографии отсутствуют");
            (view.findViewById(R.id.defPhotoName)).setVisibility(View.VISIBLE);
            (view.findViewById(R.id.photoDefLinLayout)).setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.def_photo_layout, container, false);
        setPhotoDef(C_ISSO, N_DEF, date);
        return view;
    }

}

package com.ais.admin.isso_s;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Black on 15.09.2017.
 */

public class GalleryImageAdapter extends BaseAdapter {

    private PhotoFragment mContext;
    private AddNewRating parent;
    public ArrayList<GalleryPhotos> bmp = new ArrayList<>();
    private int width;
    private int lastFocussedPosition = -1;
    private Handler handler = new Handler();

    public GalleryImageAdapter(PhotoFragment context, ArrayList<GalleryPhotos> bmp, int width, AddNewRating parent) {
        this.mContext = context;
        this.bmp = bmp;
        this.width = width;
        this.parent = parent;
    }

    @Override
    public int getCount() {
        return bmp.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder {
        public TextView tv;
        public ImageView image;
        public ImageButton closebtn;
        public ImageButton full_screen;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        final ViewHolder holder;

        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater li = (LayoutInflater) mContext.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = li.inflate(R.layout.photo_info_layout, null);
            holder.tv = (TextView) view.findViewById(R.id.editTextCommentPhoto);
            holder.image = (ImageView) view.findViewById(R.id.imgPhoto);
            holder.closebtn = (ImageButton) view.findViewById(R.id.close);
            holder.full_screen = (ImageButton) view.findViewById(R.id.fullScreen);
            view.setTag(holder);
            if (parent.previewed) {
                holder.closebtn.setVisibility(View.GONE);
                holder.tv.setClickable(false);
                holder.tv.setFocusable(false);
            }
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        //final Bitmap bitmap = BitmapFactory.decodeByteArray( bmp.get(i).PHOTO, 0 ,bmp.get(i).PHOTO.length);
        Bitmap bitmap = getThumbnail(new File(bmp.get(i).PHOTOPATH));
        holder.image.setImageBitmap(bitmap);
        if(bitmap.getWidth() > bitmap.getHeight())
            holder.image.setLayoutParams(new LinearLayout.LayoutParams(5 * width / 11, 3 * width / 11));
        else
            holder.image.setLayoutParams(new LinearLayout.LayoutParams(3 * width / 11, 5 * width / 11));

        holder.image.setScaleType(ImageView.ScaleType.FIT_XY);

        //holder.tv.setWidth(holder.image.getLayoutParams().width);
        holder.tv.setText(bmp.get(i).COMMENT);

        holder.closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext.getContext());
                alertDialog.setTitle("Внимание!");
                alertDialog.setMessage("Вы уверены, что хотите удалить эту фотографию" + (!bmp.get(i).COMMENT.equals("") ? " '" + bmp.get(i).COMMENT + "'" : "") + "?");

                alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.deleteViewFromGallery(i);
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        holder.full_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.fullScreenImage(bmp.get(i).PHOTOPATH);
            }
        });
        /*holder.tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int iii, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int iii, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mContext.photos.get(i).COMMENT = editable.toString();
                if(!parent.once)
                    parent.changed = true;
            }
        });*/
        if(!parent.previewed)
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext.getContext())
                        .setTitle( "Комментарий:" );
                final EditText input = new EditText(mContext.getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                lp.setMargins(20, 0, 20, 0);
                input.setLayoutParams(lp);
                input.setText(mContext.photos.get(i).COMMENT);
                input.setMinLines(5);
                input.setGravity(Gravity.TOP | Gravity.LEFT);
                alert.setView(input);
                alert.setPositiveButton( "ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        holder.tv.setText(input.getText().toString());
                        mContext.photos.get(i).COMMENT = input.getText().toString();
                    }
                })
                        .setNegativeButton( "Отмена", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alert.show();
            }
        });
        view.setMinimumHeight(5 * width / 11);

        return view;
    }

    public Bitmap getThumbnail(File image) {

        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(image.getAbsolutePath(), bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
            return null;

        int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
                : bounds.outWidth;

        // Ищем необходимый размер фото
        int scale = 1;
        while(bounds.outWidth / scale / 2 >= 300 &&
                bounds.outHeight / scale / 2 >= 300) {
            scale *= 2;
        }

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = scale;
        return BitmapFactory.decodeFile(image.getAbsolutePath(), opts);
    }
}

class Photo {
    public byte[] photo;
    public String photoName;

    public Photo(byte[] photo, String photoName) {
        this.photo = photo;
        this.photoName = photoName;
    }
}

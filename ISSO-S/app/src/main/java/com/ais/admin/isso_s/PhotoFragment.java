package com.ais.admin.isso_s;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Black on 22.09.2017.
 */

@SuppressLint("ValidFragment")
public class PhotoFragment extends Fragment {

    private AddNewRating context;
    public ListView lvPhotos;
    public ArrayList<GalleryPhotos> photos = new ArrayList<>();                     // Список фото
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public PhotoFragment(AddNewRating context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.photo_fragment_layout, container, false);

        if(!PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Theme", "0").equals("0")) {
            view.findViewById(R.id.relLayoutGallery).setBackgroundColor(getResources().getColor(R.color.background_material_light));
        }

        // Инициализация галереи
        lvPhotos = (ListView) view.findViewById(R.id.lvPhotos);
        lvPhotos.setItemsCanFocus(true);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);

        // Добавляем фотографии в галерею
        if (context.editable || context.previewed) {
            photos = getPhotos();
            if (photos.size() == 0) {
                (view.findViewById(R.id.tvNoInfo)).setVisibility(View.VISIBLE);
                ((TextView) view.findViewById(R.id.tvNoInfo)).setText("[изображения отсутствуют]");
                lvPhotos.setVisibility(View.INVISIBLE);
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                lvPhotos.setAdapter(new GalleryImageAdapter(this, photos, metrics.heightPixels, context));
            }
            else {
                lvPhotos.setAdapter(new GalleryImageAdapter(this, photos, metrics.widthPixels, context));
            }
        }
        else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                lvPhotos.setAdapter(new GalleryImageAdapter(this, new ArrayList<GalleryPhotos>(), metrics.heightPixels, context));
            }
            else {
                lvPhotos.setAdapter(new GalleryImageAdapter(this, new ArrayList<GalleryPhotos>(), metrics.widthPixels, context));
            }
            (view.findViewById(R.id.tvNoInfo)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tvNoInfo)).setText("[изображения отсутствуют]");
            lvPhotos.setVisibility(View.INVISIBLE);
        }


        return view;
    }

    private Bitmap decodeFile(File f, int REQUIRED_SIZE) {
        try {
            // Декодируем размер фото
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Ищем необходимый размер фото
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Скалируем фото
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Получаем массив байтов из фото
    private byte[] getBitmapAsByteArray() {
        try {
            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(context.photoFile), null, null);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            return stream.toByteArray();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Добавление даты в фотографию
    private void addDateToPhoto(Bitmap bitmap) {
        Bitmap newJPEG = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(Calendar.getInstance().getTime());
        Canvas cs = new Canvas(newJPEG);
        Paint tPaint = new Paint();
        tPaint.setTextSize(24);
        tPaint.setStyle(Paint.Style.STROKE);
        tPaint.setStrokeJoin(Paint.Join.ROUND);
        tPaint.setStrokeMiter(10);
        tPaint.setColor(Color.BLACK);
        tPaint.setStrokeWidth(2);
        cs.drawBitmap(bitmap, 0f, 0f, null);
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        cs.drawText(date, width - tPaint.measureText(date, 0, date.length()) - 20f, height - 30f, tPaint);
        tPaint.setStyle(Paint.Style.FILL);
        tPaint.setColor(Color.rgb(255, 102, 0));
        cs.drawText(date, width - tPaint.measureText(date, 0, date.length()) - 20f, height - 30f, tPaint);
        try {
            FileOutputStream fOut = new FileOutputStream(context.photoFile);
            assert bitmap != null;
            newJPEG.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            galleryAddPic(context.photoFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void galleryAddPic(String mCurrentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    // Создание фотографии и пути до нее
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpeg";
        String appDirectoryName = "ISSO-S";
        File imageRoot = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
        if(!imageRoot.exists()) imageRoot.mkdirs();
        return new File(imageRoot, imageFileName);
    }

    // Функция получения фотографий из БД
    public ArrayList<GalleryPhotos> getPhotos () {
        Cursor cr = new DBHelper(context.getApplicationContext()).getReadableDatabase().rawQuery("select * from PHOTOS_RATING " +
                "where C_ISSO = " + context.C_ISSO + " and RATINGDATE = " + context.getIntent().getLongExtra("Date", 0), null);
        ArrayList<GalleryPhotos> listPhotos = new ArrayList<>();
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++ ) {
                listPhotos.add(new GalleryPhotos(cr.getInt(cr.getColumnIndex("C_ISSO")), cr.getLong(cr.getColumnIndex("RATINGDATE")),
                        cr.getString(cr.getColumnIndex("PHOTOPATH")), cr.getLong(cr.getColumnIndex("PHOTODATE")),
                        cr.getString(cr.getColumnIndex("COMMENT"))));
                cr.moveToNext();
            }
        }
        cr.close();
        return listPhotos;
    }

    // Открыть фотографию на полный экран
    public void fullScreenImage(String imagePath) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(imagePath)), "image/*");
        startActivity(intent);
    }

    // метод удаления фотографии из галереи и из БД
    public void deleteViewFromGallery(int position) {
        new DBHelper(context.getApplicationContext()).getWritableDatabase().delete("PHOTOS_RATING", "C_ISSO=" + photos.get(position).C_ISSO + " and RATINGDATE="
                + photos.get(position).RATING_DATE + " and PHOTODATE=" + photos.get(position).PHOTODATE, null);
        photos.remove(position);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        if (photos.size() == 0) { // если больше фото нет, то убираем галерею
            (view.findViewById(R.id.tvNoInfo)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tvNoInfo)).setText("[изображения отсутствуют]");
            lvPhotos.setVisibility(View.INVISIBLE);
        }
        else {
            (view.findViewById(R.id.tvNoInfo)).setVisibility(View.INVISIBLE);
            lvPhotos.setVisibility(View.VISIBLE);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lvPhotos.setAdapter(new GalleryImageAdapter(this, photos, metrics.heightPixels, context));
        }
        else {
            lvPhotos.setAdapter(new GalleryImageAdapter(this, photos, metrics.widthPixels, context));
        }
        //lvPhotos.deferNotifyDataSetChanged();
    }
}

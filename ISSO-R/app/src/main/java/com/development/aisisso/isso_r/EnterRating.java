package com.development.aisisso.isso_r;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EnterRating extends Activity {
    private final static String[] items = new String[] { "Работы не проведены", "Очень плохо", "Плохо", "Удовлетворительно", "Хорошо", "Работы проведены полностью"};
    private TextView tvCurrentRating;
    private ImageView imgRating;
    private ImageView btnCamera;
    private File photo;
    private createRow row;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        this.setFinishOnTouchOutside(false);
        Display display = getWindowManager().getDefaultDisplay();
        final boolean isPreviewOnly = getIntent().getBooleanExtra("PreviewOnly", false);
        boolean canEdit = getIntent().getBooleanExtra("CanEdit", false);
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        getWindow().setLayout((int) (width * 0.8), LinearLayout.LayoutParams.WRAP_CONTENT);
        final Drawable[] DRAWABLES = new Drawable[] { getResources().getDrawable(R.drawable.draw_1),
                getResources().getDrawable(R.drawable.draw_2), getResources().getDrawable(R.drawable.draw_3),
                getResources().getDrawable(R.drawable.draw_4), getResources().getDrawable(R.drawable.draw_5),
                getResources().getDrawable(R.drawable.draw_6)};

        Location location = getIntent().getParcelableExtra("location");
        final Button btnConfirmRating = new Button(getApplicationContext());
        btnConfirmRating.setPadding(0, 10, 0, 10);
        btnConfirmRating.setText(getResources().getString(R.string.confirm));
        btnConfirmRating.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
        ((LinearLayout) findViewById(R.id.linLayoutDialog)).addView(btnConfirmRating);
        btnCamera = (ImageView) findViewById(R.id.btnDialogPhoto);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0")) {
            setTheme(R.style.DialogTheme);
        } else {
            setTheme(R.style.DialogThemeLight);
            ((TextView) findViewById(R.id.tvComment)).setTextColor(getResources().getColor(R.color.background_material_dark));
            (findViewById(R.id.linLayoutDialog)).setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((TextView) findViewById(R.id.tvCurrentRating)).setTextColor(getResources().getColor(R.color.background_material_dark));
            ((TextView) findViewById(R.id.tvNameWork)).setTextColor(getResources().getColor(R.color.background_material_dark));
            ((EditText) findViewById(R.id.editTextCommentRating)).setTextColor(getResources().getColor(R.color.background_material_dark));
            (findViewById(R.id.editTextCommentRating)).setBackgroundColor(getResources().getColor(R.color.almostTransparent));
            ((EditText) findViewById(R.id.editTextCommentRating)).setText("");
        }

        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        SharedPreferences sp = getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE);
        final int step = sp.getInt("stepSpinner", -1);
        seekBar.setMax(100 / step);
        if(isPreviewOnly && !canEdit) {
            seekBar.setVisibility(View.GONE);
            ((EditText) findViewById(R.id.editTextCommentRating)).setKeyListener(null);
            btnConfirmRating.setText("Ок");
        }
        tvCurrentRating = (TextView) findViewById(R.id.tvCurrentRating);
        imgRating = (ImageView) findViewById(R.id.imgViewRating);

        row = (createRow) getIntent().getSerializableExtra("Row");

        if (row.rating >=0 && row.rating < 20) {
            imgRating.setImageDrawable(DRAWABLES[0]);
            if(step < 100)
                tvCurrentRating.setText(items[0] + " (" + row.rating + "%)");
            else
                tvCurrentRating.setText(items[0]);
        }
        else if (row.rating >= 20 && row.rating < 40) {
            imgRating.setImageDrawable(DRAWABLES[1]);
            if(step < 100)
                tvCurrentRating.setText(items[1] + " (" + row.rating + "%)");
            else
                tvCurrentRating.setText(items[1]);
        }
        else if (row.rating >= 40 && row.rating < 60){
            imgRating.setImageDrawable(DRAWABLES[2]);
            if(step < 100)
                tvCurrentRating.setText(items[2] + " (" + row.rating + "%)");
            else
                tvCurrentRating.setText(items[2]);
        }
        else if (row.rating >= 60 && row.rating < 80) {
            imgRating.setImageDrawable(DRAWABLES[3]);
            if(step < 100)
                tvCurrentRating.setText(items[3] + " (" + row.rating + "%)");
            else
                tvCurrentRating.setText(items[3]);
        }
        else if (row.rating >= 80 && row.rating < 100) {
            imgRating.setImageDrawable(DRAWABLES[4]);
            if(step < 100)
                tvCurrentRating.setText(items[4] + " (" + row.rating + "%)");
            else
                tvCurrentRating.setText(items[4]);
        }
        else if (row.rating >= 100) {
            imgRating.setImageDrawable(DRAWABLES[5]);
            if(step < 100)
                tvCurrentRating.setText(items[5] + " (" + row.rating + "%)");
            else
                tvCurrentRating.setText(items[5]);
        }
        seekBar.setProgress(row.rating / step);
        //imgRating.setImageDrawable(DRAWABLES[row.rating]);
        //tvCurrentRating.setText(items[row.rating]);
        //seekBar.setProgress(row.rating);
        ((TextView) findViewById(R.id.tvNameWork)).setText(row.nameWorks);
        ((EditText) findViewById(R.id.editTextCommentRating)).setText(row.Comment);
        if (MainActivity.theme.equals("0")) {
            if(row.PhotoPath.equals(""))
                btnCamera.setImageDrawable(getResources().getDrawable(R.drawable.no_photo_dark));
            else {
                //Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(row.PhotoPath), 256, 256);
                Uri uri = Uri.fromFile(new File(row.PhotoPath));
                Bitmap bmp = getThumbnail(uri);
                btnCamera.setImageBitmap(bmp);
                //btnCamera.setImageDrawable(getResources().getDrawable(R.drawable.photo_dark));
            }
        } else {
            if(row.PhotoPath.equals(""))
                btnCamera.setImageDrawable(getResources().getDrawable(R.drawable.no_photo_light));
            else {
                Uri uri = Uri.fromFile(new File(row.PhotoPath));
                Bitmap bmp = getThumbnail(uri);
                //btnCamera.setImageDrawable(getResources().getDrawable(R.drawable.photo_light));
                //Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(row.PhotoPath), 256, 256);
                btnCamera.setImageBitmap(bmp);
            }
        }

        if(canEdit)
            btnCamera.setClickable(false);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPreviewOnly) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        photo = null;
                        try {
                            photo = createImageFile(".jpg");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (photo != null) {
                            Uri uri = FileProvider.getUriForFile(getApplicationContext(), "com.development.aisisso.isso_r.provider", photo.getAbsoluteFile());
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            setResult(RESULT_OK, takePictureIntent);
                            startActivityForResult(takePictureIntent, 1);
                        }
                    }
                }
                else if(!row.PhotoPath.equals("")){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(row.PhotoPath)), "image/*");
                    startActivity(intent);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int percentage = progress * step;
                if (percentage >=0 && percentage < 20) {
                    imgRating.setImageDrawable(DRAWABLES[0]);
                    if(step < 100)
                        tvCurrentRating.setText(items[0] + " (" + percentage + "%)");
                    else
                        tvCurrentRating.setText(items[0]);
                }
                else if (percentage >= 20 && percentage < 40) {
                    imgRating.setImageDrawable(DRAWABLES[1]);
                    if(step < 100)
                        tvCurrentRating.setText(items[1] + " (" + percentage + "%)");
                    else
                        tvCurrentRating.setText(items[1]);
                }
                else if (percentage >= 40 && percentage < 60){
                    imgRating.setImageDrawable(DRAWABLES[2]);
                    if(step < 100)
                        tvCurrentRating.setText(items[2] + " (" + percentage + "%)");
                    else
                        tvCurrentRating.setText(items[2]);
                }
                else if (percentage >= 60 && percentage < 80) {
                    imgRating.setImageDrawable(DRAWABLES[3]);
                    if(step < 100)
                        tvCurrentRating.setText(items[3] + " (" + percentage + "%)");
                    else
                        tvCurrentRating.setText(items[3]);
                }
                else if (percentage >= 80 && percentage < 100) {
                    imgRating.setImageDrawable(DRAWABLES[4]);
                    if(step < 100)
                        tvCurrentRating.setText(items[4] + " (" + percentage + "%)");
                    else
                        tvCurrentRating.setText(items[4]);
                }
                else if (percentage >= 100) {
                    imgRating.setImageDrawable(DRAWABLES[5]);
                    if(step < 100)
                        tvCurrentRating.setText(items[5] + " (" + percentage + "%)");
                    else
                        tvCurrentRating.setText(items[5]);
                }
                row.rating = percentage;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        if(isPreviewOnly && !canEdit) {
            btnConfirmRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        else {
            btnConfirmRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createRow remWork = new createRow(row.nameWorks, row.cRem, row.rating,
                            ((EditText) findViewById(R.id.editTextCommentRating)).getText().toString(),
                            photo != null ? photo.getAbsolutePath() : "");
                    Intent intent = new Intent();
                    intent.putExtra("REM_WORKS", remWork);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    public Bitmap getThumbnail(Uri uri) {
        File image = new File(uri.getPath());

        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(image.getPath(), bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
            return null;

        int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
                : bounds.outWidth;

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / 256;
        return BitmapFactory.decodeFile(image.getPath(), opts);
    }


    public File createImageFile(String Format) throws IOException {
        // Create an image file name
        String appDirectoryName = "ISSO-R";
        File imageRoot = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
        if(!imageRoot.exists()) imageRoot.mkdirs();
        return new File(imageRoot, getIntent().getStringExtra("timeStamp") + Format);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = decodeFile(photo, 300);
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
                FileOutputStream fOut = new FileOutputStream(photo);
                assert bitmap != null;
                newJPEG.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.flush();
                fOut.close();
                galleryAddPic(photo.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Animation fadeIn = new AlphaAnimation(0, 1);
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeIn.setDuration(500);
            fadeOut.setDuration(500);
            fadeIn.setStartOffset(500);
            btnCamera.setAnimation(fadeOut);
            Uri uri = Uri.fromFile(new File(photo.getAbsolutePath()));
            Bitmap bmp = getThumbnail(uri);
            btnCamera.setImageBitmap(bmp);

            /*if (MainActivity.theme.equals("0")) {
                //btnCamera.setImageDrawable(getResources().getDrawable(R.drawable.photo_dark));
                Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(photo.getPath()), 256, 256);
                btnCamera.setImageBitmap(ThumbImage);
            } else {
                //btnCamera.setImageDrawable(getResources().getDrawable(R.drawable.photo_light));
                Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(photo.getPath()), 256, 256);
                btnCamera.setImageBitmap(ThumbImage);
            }*/
            btnCamera.setAnimation(fadeIn);
        }
        else{
            photo = null;
        }
    }

    private void galleryAddPic(String mCurrentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeFile(File f, int REQUIRED_SIZE) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

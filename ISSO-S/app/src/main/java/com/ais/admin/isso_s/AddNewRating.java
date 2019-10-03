package com.ais.admin.isso_s;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.TimeZone;

/**
 * Created by Black on 14.09.2017.
 */

public class AddNewRating extends AppCompatActivity {

    private Location location;                                                      // Геопозиция
    //private SeekBar seekBar;                                                        // Ползунок
    //private TextView tvAdvancedChooseRating;                                        // Текст при улучшении
    public boolean editable;                                                       // Можно ли редактировать запись
    public boolean previewed;                                                       // Можно ли просматривать запись
    //private Spinner spinnerExample;                                                 // Выпадающий список
    public String C_ISSO;                                                           // Номер ИССО
    public File photoFile;                                                          // Путь до файла фото
    //private Gallery gallery;                                                        // Галерея
    //public ArrayList<GalleryPhotos> photos = new ArrayList<>();                     // Список фото
    public boolean changed = false;                                                 // Проверка изменения записи
    public boolean once = true;                                                     // параметр только для того, чтобы не изменялось состояние при выборе степени ухудшения
    //private ListView lvPhotos;
    private InfoFragment infoFragment;
    private PhotoFragment photoFragment;

    static final int REQUEST_TAKE_PHOTO = 1;

    // Конструктор
    public AddNewRating() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0"))
            setTheme(R.style.Advanced);
        else
            setTheme(R.style.AdvancedLight);

        setContentView(R.layout.add_new_rating);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // Получаем сведения по наличию редактирования и просмотра
        editable = getIntent().getBooleanExtra("Editable", false);
        previewed = getIntent().getBooleanExtra("Preview", false);
        C_ISSO = getIntent().getStringExtra("C_ISSO");
        location = getIntent().getParcelableExtra("Location");

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        final int AllRating;
        int currentRating;
        if(!editable) { // если не можем редактировать, то получаем сведения по геолокации, и соответственно последнее значение рейтинга
            AllRating = getIntent().getIntExtra("AllRating", 0);
            currentRating = getIntent().getIntExtra("CurrentRating", 0) - 1;
        }
        else { // иначе получаем предыдущий рейтинг путем вычитания последнего значения из общего рейтинга
            AllRating = getIntent().getIntExtra("AllRating", 0) - getIntent().getIntExtra("CurrentRating", 0);
            currentRating = getIntent().getIntExtra("CurrentRating", 0) - 1;
        }

        // инициализация всех элементов UI
        /*seekBar = (SeekBar) findViewById(R.id.seekBarRatingUp);
        tvAdvancedChooseRating = (TextView) findViewById(R.id.tvAdvancedChooseRating);
        seekBar.setVisibility(View.GONE);
        tvAdvancedChooseRating.setVisibility(View.GONE);
        spinnerExample = (Spinner) findViewById(R.id.addRatingSpinner);
        ((EditText) findViewById(R.id.editTextCommentRating)).setImeOptions(EditorInfo.IME_ACTION_DONE);*/

        //  Подключение toolbar со связанным заголовком
        final Toolbar toolbar = (Toolbar) findViewById(R.id.isso_tabanim_toolbar);
        setSupportActionBar(toolbar);

        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") ) {
            findViewById(R.id.MainIssoCoordinator).setBackgroundColor(getResources().getColor(R.color.background_material_light));
            ((FloatingActionButton) findViewById(R.id.floatingActionButton)).setImageDrawable(getResources().getDrawable(R.drawable.camera_light));
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_light);
        }
        else {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_dark);
            ((FloatingActionButton) findViewById(R.id.floatingActionButton)).setImageDrawable(getResources().getDrawable(R.drawable.camera_dark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoFragment = new InfoFragment(this);
        photoFragment = new PhotoFragment(this);
        infoFragment.AllRating = AllRating;
        infoFragment.CurrentRating = currentRating;


        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
        // Спрячем плавающую кнопку добавления фотографий
        hideFloatingActionButton();

        // Здесь мы будем отображать или скрывать нашу кнопку
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0) {
                    hideFloatingActionButton();
                }
                else {
                    showFloatingActionButton();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Обработка нажатия плавающей кнопки
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changed = true;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Проверка наличия камеры
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Создание файла, в который пойдет запись фотографии
                    photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // При ошибке показываем сообщение
                        Toast.makeText(getApplicationContext(), "Возникла ошибка при создании фотографии.", Toast.LENGTH_SHORT).show();
                    }
                    // Если все норм, то создаем внешний интент и фотаем
                    if (photoFile != null) {
                        //Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),"com.example.android.fileprovider",photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        setResult(RESULT_OK, takePictureIntent);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                }
            }
        });

        // В случае с просмотром выключаем все редактирования
        if (previewed) {
            /*infoFragment.spinnerExample.setClickable(false);
            infoFragment.spinnerExample.setEnabled(false);
            findViewById(R.id.editTextCommentRating).setFocusable(false);*/
            findViewById(R.id.floatingActionButton).setVisibility(View.GONE);
            //findViewById(R.id.linLayoutWithCheck).setFocusable(false);
            //findViewById(R.id.linLayoutWithCheck).setClickable(false);

            getSupportActionBar().setTitle("АИС ИССО-S. Просмотр оценки");
        }
        if (editable) {
            getSupportActionBar().setTitle("АИС ИССО-S. Редактирование");
        }

        /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout l = (LinearLayout) findViewById(R.id.linLayMain);
            LinearLayout.LayoutParams paramsInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayForInfo)).getLayoutParams();
            LinearLayout.LayoutParams paramsPhoto = (LinearLayout.LayoutParams) (findViewById(R.id.linLayForPhotos)).getLayoutParams();
            int width = paramsInfo.width;
            int height = paramsInfo.height;
            paramsInfo.width = height;
            paramsInfo.height = width;
            width = paramsPhoto.width;
            height = paramsPhoto.height;
            paramsPhoto.width = height;
            paramsPhoto.height = width;
            l.setOrientation(LinearLayout.HORIZONTAL);
        }*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(infoFragment, "Параметры оценки");
        adapter.addFragment(photoFragment, "Фотографии");
        viewPager.setAdapter(adapter);
    }

    // Открыть фотографию на полный экран
    public void fullScreenImage(String imagePath) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(imagePath)), "image/*");
        startActivity(intent);
    }

    public void showFloatingActionButton() {
        boolean canAddImages = getIntent().getBooleanExtra("CanAddImages", false);
        if(!previewed && !editable)
            ((FloatingActionButton) findViewById(R.id.floatingActionButton)).show();
        if(editable) {
            if(canAddImages)
                ((FloatingActionButton) findViewById(R.id.floatingActionButton)).show();
        }
    }

    public void hideFloatingActionButton() {
        ((FloatingActionButton) findViewById(R.id.floatingActionButton)).hide();
    }

    /** Ловим переворот экрана */
    /* @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        LinearLayout l = (LinearLayout) findViewById(R.id.linLayMain);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout.LayoutParams paramsInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayForInfo)).getLayoutParams();
            LinearLayout.LayoutParams paramsPhoto = (LinearLayout.LayoutParams) (findViewById(R.id.linLayForPhotos)).getLayoutParams();
            int width = paramsInfo.width;
            int height = paramsInfo.height;
            paramsInfo.width = height;
            paramsInfo.height = width;
            width = paramsPhoto.width;
            height = paramsPhoto.height;
            paramsPhoto.width = height;
            paramsPhoto.height = width;
            l.setOrientation(LinearLayout.HORIZONTAL);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayout.LayoutParams paramsInfo = (LinearLayout.LayoutParams) (findViewById(R.id.linLayForInfo)).getLayoutParams();
            LinearLayout.LayoutParams paramsPhoto = (LinearLayout.LayoutParams) (findViewById(R.id.linLayForPhotos)).getLayoutParams();
            int width = paramsInfo.width;
            int height = paramsInfo.height;
            paramsInfo.width = height;
            paramsInfo.height = width;
            width = paramsPhoto.width;
            height = paramsPhoto.height;
            paramsPhoto.width = height;
            paramsPhoto.height = width;
            l.setOrientation(LinearLayout.VERTICAL);
        }
    }*/

    // Инициализация меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_rating, menu);
        if (previewed) {
            menu.getItem(0).setVisible(false);
        }
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Theme", "0").equals("0") ) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.save_light));
        }
        else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.save_dark));
        }
        return true;
    }

    // после фотографии добавляем её в БД
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bmp = decodeFile(photoFile);
            addDateToPhoto(bmp);
            //byte[] photoArr = getBitmapAsByteArray();
            ContentValues cv = new ContentValues();
            // Получаем время по GPS
            TimeZoneMapper timeZoneMapper = new TimeZoneMapper(AddNewRating.this);
            String timezone = timeZoneMapper.latLngToTimezoneString(location.getLatitude(), location.getLongitude());
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone(timezone));
            cal.setTimeInMillis(location.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MILLISECOND, 0);
            long currentTime = calendar.getTimeInMillis();
            // Вставляем данные в DB
            cv.put("C_ISSO", C_ISSO);
            if(editable)
                cv.put("RATINGDATE", getIntent().getLongExtra("Date", 0));
            else
                cv.put("RATINGDATE", cal.getTimeInMillis());
            cv.put("PHOTOPATH", photoFile.getAbsolutePath());
            cv.put("PHOTODATE", currentTime);
            cv.put("COMMENT", "");
            cv.put("SYNC", 0);
            new DBHelper(AddNewRating.this).getWritableDatabase().insert("PHOTOS_RATING", null, cv);

            // Изменяем содержимое галереи
            if (editable)
                photoFragment.photos.add(new GalleryPhotos(Integer.parseInt(C_ISSO), getIntent().getLongExtra("Date",0), photoFile.getAbsolutePath(), currentTime, ""));
            else
                photoFragment.photos.add(new GalleryPhotos(Integer.parseInt(C_ISSO), cal.getTime().getTime(), photoFile.getAbsolutePath(), currentTime, ""));
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            if (photoFragment.photos.size() == 0) {
                (findViewById(R.id.tvNoInfo)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tvNoInfo)).setText("[изображения отсутствуют]");
                photoFragment.lvPhotos.setVisibility(View.INVISIBLE);
            }
            else {
                (findViewById(R.id.tvNoInfo)).setVisibility(View.INVISIBLE);
                photoFragment.lvPhotos.setVisibility(View.VISIBLE);
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                photoFragment.lvPhotos.setAdapter(new GalleryImageAdapter(photoFragment, photoFragment.photos, metrics.heightPixels, this));
            }
            else {
                photoFragment.lvPhotos.setAdapter(new GalleryImageAdapter(photoFragment, photoFragment.photos, metrics.widthPixels, this));
            }
            //lvPhotos.deferNotifyDataSetChanged();
        }
    }

    private Bitmap decodeFile(File f) {
        try {
            // Декодируем размер фото
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Ищем необходимый размер фото
            int scale = 1;
            while(o.outWidth / scale / 2 >= 300 &&
                    o.outHeight / scale / 2 >= 300) {
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
            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(photoFile), null, null);
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
            FileOutputStream fOut = new FileOutputStream(photoFile);
            assert bitmap != null;
            newJPEG.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            galleryAddPic(photoFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void galleryAddPic(String mCurrentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
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
        Cursor cr = new DBHelper(this.getApplicationContext()).getReadableDatabase().rawQuery("select * from PHOTOS_RATING " +
                "where C_ISSO = " + C_ISSO + " and RATINGDATE = " + getIntent().getLongExtra("Date", 0), null);
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

    public boolean onOptionsItemSelected(MenuItem item)	{
        switch(item.getItemId())
        {
            case android.R.id.home:
                if(!previewed && changed)
                    quitWithoutSaving();
                else
                    finish();
                break;
            case R.id.save_rating:
                saveToDatabase();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    // Обработка нажатия "Назад"
    @Override
    public void onBackPressed() {
        if(!previewed && changed)
            quitWithoutSaving();
        else
            finish();
    }

    // Сохранение записи в БД
    private void saveToDatabase() {
        // Проверяем, все ли заполнено
        if (((EditText) findViewById(R.id.editTextCommentRating)).getText().toString().equals("") &&
                infoFragment.spinnerExample.getSelectedItemPosition() != 0) {
            Toast.makeText(getApplicationContext(), "Комментарий к оценке не заполнен", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean allFilled = true;
        for (int i = 0; i < photoFragment.photos.size() && allFilled; i++) {
            if (photoFragment.photos.get(i).COMMENT.equals(""))
                allFilled = false;
        }
        if (!allFilled) {
            Toast.makeText(getApplicationContext(), "Отсутствуют комментарии для некоторых фотографий", Toast.LENGTH_SHORT).show();
            return;
        }
        // Если все заполнено, добавляем запись в БД
        if (!editable) {
            ContentValues cv = new ContentValues();
            // Получаем время по GPS
            TimeZoneMapper timeZoneMapper = new TimeZoneMapper(AddNewRating.this);
            String timezone = timeZoneMapper.latLngToTimezoneString(location.getLatitude(), location.getLongitude());
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone(timezone));
            cal.setTimeInMillis(location.getTime());
            long currentTime = Calendar.getInstance().getTime().getTime();
            // Вставляем данные в DB
            cv.put("C_ISSO", C_ISSO);
            cv.put("CURRENTRATING", infoFragment.CurrentRating);
            cv.put("RATINGDATE", cal.getTime().getTime());
            cv.put("RATINGDATEEDIT", currentTime);

            // выбор рейтинга
            int rating;
            switch (infoFragment.spinnerExample.getSelectedItemPosition()) {
                case 0:
                    rating = 20;
                    break;
                case 1:
                    rating = 30;
                    break;
                case 2:
                    rating = 40;
                    break;
                case 3:
                    rating = 50;
                    break;
                case 4:
                    rating = 70;
                    break;
                case 5:
                    rating = 60;
                    break;
                default:
                    rating = 0;
                    break;
            }
            cv.put("RATING", rating);
            cv.put("COMMENTS", infoFragment.editRatingComments.getText().toString());
            cv.put("LATITUDE_RATING", location.getLatitude());
            cv.put("LONGITUDE_RATING", location.getLongitude());
            cv.put("CHECKOUTOFPLAN", infoFragment.checkBox.isChecked());
            cv.put("OFFSET", cal.getTimeZone().getRawOffset());
            cv.put("SYNC", 0);
            new DBHelper(AddNewRating.this).getWritableDatabase().insert("RATING", null, cv);

            // Для фото добавляем комментарии
            for (int i = 0; i < photoFragment.photos.size(); i++) {
                new DBHelper(AddNewRating.this).getWritableDatabase().execSQL("update PHOTOS_RATING set COMMENT='"
                        + photoFragment.photos.get(i).COMMENT + "' where C_ISSO=" + C_ISSO + " and RATINGDATE=" + cal.getTime().getTime()
                        + " and PHOTODATE=" + photoFragment.photos.get(i).PHOTODATE);
            }
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
        else {
            ContentValues cv = new ContentValues();
            cv.put("C_ISSO", getIntent().getStringExtra("C_ISSO"));
            cv.put("CURRENTRATING", infoFragment.CurrentRating);

            // выбор рейтинга
            int rating;
            switch (infoFragment.spinnerExample.getSelectedItemPosition()) {
                case 0:
                    rating = 20;
                    break;
                case 1:
                    rating = 30;
                    break;
                case 2:
                    rating = 40;
                    break;
                case 3:
                    rating = 50;
                    break;
                case 4:
                    rating = 70;
                    break;
                case 5:
                    rating = 60;
                    break;
                default:
                    rating = 0;
                    break;
            }
            cv.put("RATING", rating);
            cv.put("RATINGDATE", getIntent().getLongExtra("Date", 0));
            cv.put("COMMENTS", infoFragment.editRatingComments.getText().toString());
            cv.put("LATITUDE_RATING", getIntent().getDoubleExtra("Latitude", 0));
            cv.put("LONGITUDE_RATING", getIntent().getDoubleExtra("Longitude", 0));
            cv.put("CHECKOUTOFPLAN", infoFragment.checkBox.isChecked());
            cv.put("OFFSET", getIntent().getLongExtra("Offset", 0));
            cv.put("RATINGDATEEDIT", Calendar.getInstance().getTime().getTime());
            cv.put("SYNC", 0);
            new DBHelper(AddNewRating.this).getWritableDatabase().update("RATING", cv, "C_ISSO=" +
                    getIntent().getStringExtra("C_ISSO") + " and RATINGDATE=" +  getIntent().getLongExtra("Date", 0), null);
            // Для фото добавляем комментарии
            for (int i = 0; i < photoFragment.photos.size(); i++) {
                new DBHelper(AddNewRating.this).getWritableDatabase().execSQL("update PHOTOS_RATING set COMMENT='"
                        + photoFragment.photos.get(i).COMMENT + "', SYNC=0 where C_ISSO=" + C_ISSO + " and RATINGDATE=" + getIntent().getLongExtra("Date", 0)
                        + " and PHOTODATE=" + photoFragment.photos.get(i).PHOTODATE);
            }
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    // Метод выхода без сохранения
    private void quitWithoutSaving() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Внимание!");

        alertDialog.setMessage("Вы не сохранили текущую оценку. Вы уверены, что хотите выйти без сохранения?");

        alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(!editable) {
                    // Если выходим, то удаляем все записи фотографий из БД
                    // Получаем время по GPS
                    TimeZoneMapper timeZoneMapper = new TimeZoneMapper(AddNewRating.this);
                    String timezone = timeZoneMapper.latLngToTimezoneString(location.getLatitude(), location.getLongitude());
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeZone(TimeZone.getTimeZone(timezone));
                    cal.setTimeInMillis(location.getTime());

                    // Удапление записей из таблицы PHOTOS_RATING
                    Cursor deleteCursor = new DBHelper(getApplicationContext()).getWritableDatabase().rawQuery("select PHOTOPATH from PHOTOS_RATING where C_ISSO="
                            + C_ISSO + " and RATINGDATE=" + cal.getTime().getTime(), null);
                    if(deleteCursor.moveToFirst()) {
                        for (int index = 0; index < deleteCursor.getCount(); index++) {
                            File f = new File(deleteCursor.getString(deleteCursor.getColumnIndex("PHOTOPATH")));
                            if(f.exists())
                                f.delete();
                        }
                    }
                    new DBHelper(getApplicationContext()).getWritableDatabase().delete("PHOTOS_RATING", "C_ISSO=" + C_ISSO + " and RATINGDATE="
                            + cal.getTime().getTime(), null);
                }
                finish();
            }
        });
        alertDialog.show();
    }
}

/***** Adapter class extends with ArrayAdapter ******/
class AddRatingAdapter extends ArrayAdapter<String> {

    private ArrayList data;
    private LayoutInflater inflater;
    private InfoFragment customSpinner;

    /*************
     * CustomAdapter Constructor
     *****************/
    AddRatingAdapter(InfoFragment activitySpinner, int textViewResourceId, ArrayList objects) {
        super(activitySpinner.getContext(), textViewResourceId, objects);

        ///********* Take passed values **********/
        data = objects;
        this.customSpinner = activitySpinner;

        ///***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater) activitySpinner.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    private View getCustomView(int position, ViewGroup parent) {

        ///********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.rating_spinner_row, parent, false);
        if (!PreferenceManager.getDefaultSharedPreferences(customSpinner.getActivity().getBaseContext()).getString("Theme", "0").equals("0")) {
            (row.findViewById(R.id.linLayoutRow)).setBackgroundColor(customSpinner.getResources().getColor(R.color.background_material_light));
            ((TextView) row.findViewById(R.id.tvRating)).setTextColor(customSpinner.getResources().getColor(R.color.background_material_dark));
        }

        ///***** Get each Model object from Arraylist ********/
        SpinnerModel tempValues = (SpinnerModel) data.get(position);

        TextView label = (TextView) row.findViewById(R.id.tvRating);
        ImageView companyLogo = (ImageView) row.findViewById(R.id.image);

        // Set values for spinner each row
        label.setText(tempValues.getRating());
        companyLogo.setImageResource(tempValues.getImage());

        return row;
    }

}

class GalleryPhotos {
    public int C_ISSO;
    public long RATING_DATE;
    public String PHOTOPATH;
    public long PHOTODATE;
    public String COMMENT;

    public GalleryPhotos(int C_ISSO, long RATING_DATE, String PHOTOPATH, long PHOTODATE, String COMMENT) {
        this.C_ISSO = C_ISSO;
        this.RATING_DATE = RATING_DATE;
        this.PHOTOPATH = PHOTOPATH;
        this.PHOTODATE = PHOTODATE;
        this.COMMENT = COMMENT;
    }
}



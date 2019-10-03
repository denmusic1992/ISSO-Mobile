package com.development.aisisso.isso_i;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.artifex.mupdfdemo.AsyncTask;
import com.artifex.mupdfdemo.ChoosePDFActivity;
import com.artifex.mupdfdemo.FilePicker;
import com.artifex.mupdfdemo.MuPDFAlert;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;
import com.artifex.mupdfdemo.OutlineActivityData;
import com.artifex.mupdfdemo.SearchTaskResult;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executor;

public class SchemeViewActivity extends Fragment implements FilePicker.FilePickerSupport{

    public int C_ISSO;
    private MuPDFCore core;
    private String mFileName;
    private AlertDialog.Builder mAlertBuilder;
    private boolean mAlertsActive = false;
    private AsyncTask<Void,Void,MuPDFAlert> mAlertTask;
    private AlertDialog mAlertDialog;
    private Bundle savedInstanceState;
    private Gallery gallery;
    private int item_position = 0;
    private View view;
    private ArrayList<Photo> listPhotos = new ArrayList<>();

    public SchemeViewActivity() {}
    public SchemeViewActivity(int C_ISSO) {
        this.C_ISSO = C_ISSO;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.savedInstanceState = savedInstanceState;
    }

    public String getDate(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return milliseconds != 0 ? formatter.format(calendar.getTime()) : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.scheme_view_layout, container, false);

        Cursor cr = new DBHelper(view.getContext()).getReadableDatabase().rawQuery("select * from UPLOAD_SCHEMES " +
                "where C_ISSO=" + C_ISSO + " order by N", null);

        final ArrayList<Scheme> listSchemes = new ArrayList<>();
        if(cr.moveToFirst()) {
            for (int i = 0; i < cr.getCount(); i++ ) {
                listSchemes.add(new Scheme(cr.getBlob(cr.getColumnIndex("SCHEME")), getDate(cr.getLong(
                        cr.getColumnIndex("SCHEME_DATE"))), cr.getString(cr.getColumnIndex("COMMENTS"))));
                cr.moveToNext();
            }
        }
        cr.close();

        final TextView galleryPhotoName = (TextView) view.findViewById(R.id.gallerySchemeName);
        if(listSchemes.size() > 0) {
            setPDFToView(listSchemes.get(item_position).scheme);
            listPhotos = new ArrayList<>();

            cr = new DBHelper(view.getContext()).getReadableDatabase().rawQuery("select * from UPLOAD_SCHEMES " +
                    "where C_ISSO=" + C_ISSO + " order by N", null);
            if (cr.moveToFirst()) {
                for (int i = 0; i < cr.getCount(); i++) {
                    listPhotos.add(new Photo(cr.getBlob(cr.getColumnIndex("THUMBNAIL")), getDate(cr.getLong(
                            cr.getColumnIndex("SCHEME_DATE"))), cr.getString(cr.getColumnIndex("COMMENTS"))));
                    cr.moveToNext();
                }
            }
            cr.close();

            gallery = (Gallery) view.findViewById(R.id.galleryScheme);

            gallery.setSpacing(10);
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            ListView listView = (ListView) view.findViewById(R.id.list_view_schemes);
            galleryPhotoName.setText(listPhotos.get(item_position).photoName + "\n" + listPhotos.get(item_position).photoDate);
            if (listPhotos.size() > 1) {
                gallery.setAdapter(new GalleryImageAdapter(getContext(), listPhotos, metrics.widthPixels));
                gallery.setSelection(item_position, false);
                gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View dview, int position, long id) {
                        core = null;
                        item_position = position;
                        gallery.setSelection(item_position, false);
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
                        setPDFToView(listSchemes.get(position).scheme);
                    }
                });
                ListAdapter adapter = new ListAdapter(getContext(), listPhotos, metrics.heightPixels);
                listView.setAdapter(adapter);
                listView.setSelection(item_position);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View dview, int position, long id) {
                        core = null;
                        item_position = position;
                        gallery.setSelection(item_position, false);
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
                        setPDFToView(listSchemes.get(position).scheme);
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
        }
        else {
            galleryPhotoName.setText("Для данного ИССО чертежи отсутствуют");
            (view.findViewById(R.id.muPDFReader)).setVisibility(View.GONE);
            (view.findViewById(R.id.list_view_schemes)).setVisibility(View.GONE);
            (view.findViewById(R.id.galleryScheme)).setVisibility(View.GONE);
        }
        return view;
    }

    public void setPDFToView(byte[] scheme) {
        File f = new File(getContext().getFilesDir().getAbsolutePath(), "sxema.pdf");
        //File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "sxema.pdf");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(f.getPath());
            fileOutputStream.write(scheme);
            fileOutputStream.flush();
            fileOutputStream.close();

            Uri uri = Uri.parse(f.getPath());
            downloadContent(uri);
            // Now create the UI.
            // First create the document view
            MuPDFReaderView mDocView = (MuPDFReaderView) view.findViewById(R.id.muPDFReader);
            mDocView.setAdapter(new MuPDFPageAdapter(view.getContext(), this, core));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
        onResume();
    }

    public void downloadContent(Uri uri) {
        mAlertBuilder = new AlertDialog.Builder(getContext());
        core = (MuPDFCore)getActivity().getLastNonConfigurationInstance();
        if (savedInstanceState != null && savedInstanceState.containsKey("FileName")) {
            mFileName = savedInstanceState.getString("FileName");
        }
        /*if (core == null) {
            byte buffer[];
            System.out.println("URI to open is: " + uri);
            if (uri.toString().startsWith("content://")) {
                String reason = null;
                try {
                    InputStream is = getActivity().getContentResolver().openInputStream(uri);
                    int len = is.available();
                    buffer = new byte[len];
                    is.read(buffer, 0, len);
                    is.close();
                }
                catch (OutOfMemoryError e) {
                    System.out.println("Out of memory during buffer reading");
                    reason = e.toString();
                }
                catch (Exception e) {
                    System.out.println("Exception reading from stream: " + e);

                    // Handle view requests from the Transformer Prime's file manager
                    // Hopefully other file managers will use this same scheme, if not
                    // using explicit paths.
                    // I'm hoping that this case below is no longer needed...but it's
                    // hard to test as the file manager seems to have changed in 4.x.
                    try {
                        Cursor cursor = getActivity().getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
                        if (cursor.moveToFirst()) {
                            String str = cursor.getString(0);
                            if (str == null) {
                                reason = "Couldn't parse data in intent";
                            }
                            else {
                                uri = Uri.parse(str);
                            }
                        }
                    }
                    catch (Exception e2) {
                        System.out.println("Exception in Transformer Prime file manager code: " + e2);
                        reason = e2.toString();
                    }
                }
                if (reason != null) {
                    Resources res = getResources();
                    AlertDialog alert = mAlertBuilder.create();
                    alert.setButton(AlertDialog.BUTTON_POSITIVE, getString(com.artifex.mupdfdemo.R.string.dismiss),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                }
                            });
                    alert.show();
                    return;
                }
            }*/
            core = openFile(Uri.decode(uri.getEncodedPath()));
            SearchTaskResult.set(null);
            if (core != null && core.countPages() == 0)
            {
                core = null;
            }
        /*}*/
        if (core == null)
        {
            AlertDialog alert = mAlertBuilder.create();
            alert.setTitle(com.artifex.mupdfdemo.R.string.cannot_open_document);
            alert.setButton(AlertDialog.BUTTON_POSITIVE, getString(com.artifex.mupdfdemo.R.string.dismiss),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
                    });
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    getActivity().finish();
                }
            });
            alert.show();
            return;
        }
    }

    private MuPDFCore openFile(String path)
    {
        int lastSlashPos = path.lastIndexOf('/');
        mFileName = lastSlashPos == -1
                ? path
                : path.substring(lastSlashPos + 1);
        System.out.println("Trying to open " + path);
        try
        {
            core = new MuPDFCore(view.getContext(), path);
            // New file: drop the old outline data
            OutlineActivityData.set(null);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
        return core;
    }

    public void createAlertWaiter() {
        mAlertsActive = true;
        // All mupdf library calls are performed on asynchronous tasks to avoid stalling
        // the UI. Some calls can lead to javascript-invoked requests to display an
        // alert dialog and collect a reply from the user. The task has to be blocked
        // until the user's reply is received. This method creates an asynchronous task,
        // the purpose of which is to wait of these requests and produce the dialog
        // in response, while leaving the core blocked. When the dialog receives the
        // user's response, it is sent to the core via replyToAlert, unblocking it.
        // Another alert-waiting task is then created to pick up the next alert.
        if (mAlertTask != null) {
            mAlertTask.cancel(true);
            mAlertTask = null;
        }
        if (mAlertDialog != null) {
            mAlertDialog.cancel();
            mAlertDialog = null;
        }
        mAlertTask = new AsyncTask<Void,Void,MuPDFAlert>() {

            @Override
            protected MuPDFAlert doInBackground(Void... arg0) {
                if (!mAlertsActive)
                    return null;

                return core.waitForAlert();
            }

            @Override
            protected void onPostExecute(final MuPDFAlert result) {
                // core.waitForAlert may return null when shutting down
                if (result == null)
                    return;
                final MuPDFAlert.ButtonPressed pressed[] = new MuPDFAlert.ButtonPressed[3];
                for(int i = 0; i < 3; i++)
                    pressed[i] = MuPDFAlert.ButtonPressed.None;
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mAlertDialog = null;
                        if (mAlertsActive) {
                            int index = 0;
                            switch (which) {
                                case AlertDialog.BUTTON1: index=0; break;
                                case AlertDialog.BUTTON2: index=1; break;
                                case AlertDialog.BUTTON3: index=2; break;
                            }
                            result.buttonPressed = pressed[index];
                            // Send the user's response to the core, so that it can
                            // continue processing.
                            core.replyToAlert(result);
                            // Create another alert-waiter to pick up the next alert.
                            createAlertWaiter();
                        }
                    }
                };
                mAlertDialog = mAlertBuilder.create();
                mAlertDialog.setTitle(result.title);
                mAlertDialog.setMessage(result.message);
                switch (result.iconType)
                {
                    case Error:
                        break;
                    case Warning:
                        break;
                    case Question:
                        break;
                    case Status:
                        break;
                }
                switch (result.buttonGroupType)
                {
                    case OkCancel:
                        mAlertDialog.setButton(AlertDialog.BUTTON2, getString(com.artifex.mupdfdemo.R.string.cancel), listener);
                        pressed[1] = MuPDFAlert.ButtonPressed.Cancel;
                    case Ok:
                        mAlertDialog.setButton(AlertDialog.BUTTON1, getString(com.artifex.mupdfdemo.R.string.okay), listener);
                        pressed[0] = MuPDFAlert.ButtonPressed.Ok;
                        break;
                    case YesNoCancel:
                        mAlertDialog.setButton(AlertDialog.BUTTON3, getString(com.artifex.mupdfdemo.R.string.cancel), listener);
                        pressed[2] = MuPDFAlert.ButtonPressed.Cancel;
                    case YesNo:
                        mAlertDialog.setButton(AlertDialog.BUTTON1, getString(com.artifex.mupdfdemo.R.string.yes), listener);
                        pressed[0] = MuPDFAlert.ButtonPressed.Yes;
                        mAlertDialog.setButton(AlertDialog.BUTTON2, getString(com.artifex.mupdfdemo.R.string.no), listener);
                        pressed[1] = MuPDFAlert.ButtonPressed.No;
                        break;
                }
                mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        mAlertDialog = null;
                        if (mAlertsActive) {
                            result.buttonPressed = MuPDFAlert.ButtonPressed.None;
                            core.replyToAlert(result);
                            createAlertWaiter();
                        }
                    }
                });

                mAlertDialog.show();
            }
        };

        mAlertTask.executeOnExecutor(new ThreadPerTaskExecutor());
    }

    @Override
    public void performPickFor(FilePicker picker) {

        Intent intent = new Intent(getContext(), ChoosePDFActivity.class);
        intent.setAction(ChoosePDFActivity.PICK_KEY_FILE);
        int FILEPICK_REQUEST = 2;
        startActivityForResult(intent, FILEPICK_REQUEST);
    }

    class ThreadPerTaskExecutor implements Executor {
        public void execute(@NonNull Runnable r) {
            new Thread(r).start();
        }
    }

    public void destroyAlertWaiter() {
        mAlertsActive = false;
        if (mAlertDialog != null) {
            mAlertDialog.cancel();
            mAlertDialog = null;
        }
        if (mAlertTask != null) {
            mAlertTask.cancel(true);
            mAlertTask = null;
        }
    }

    class Scheme {
        public byte[] scheme;
        public String schemeDate;
        public String schemeName;

        public Scheme(byte[] scheme, String schemeDate, String schemeName) {
            this.scheme = scheme;
            this.schemeDate = schemeDate;
            this.schemeName = schemeName;
        }
    }
}

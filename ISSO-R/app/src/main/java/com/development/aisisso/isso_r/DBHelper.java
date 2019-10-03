package com.development.aisisso.isso_r;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "ISSO_DB", null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table I_ISSO ("
                + "C_ISSO integer primary key,"
                + "NAME text,"
                + "C_OTC_EXP integer,"
                + "N_OTC_EXP text,"
                + "FULLNAME text,"
                + "DORNAME text,"
                + "W_ISSO integer,"
                + "OBSTACLE text, "
                + "LENGTH REAL,"
                + "LATITUDE REAL,"
                + "LONGITUDE REAL" + ");");
        db.execSQL("create table I_REM_PLAN("
                + "C_ISSO integer,"
                + "C_REM integer,"
                + "N_REM text,"
                + "N1 integer, N2 integer, N3 integer, N4 integer, N5 integer, N6 integer, N7 integer,"
                + "N8 integer, N9 integer, N10 integer, N11 integer, N12 integer"
                + ");");
        db.execSQL("create table I_REM_PLAN_ITEMS("
                + "C_ISSO integer,"
                + "C_REM integer,"
                + "PERIOD DATE,"
                + "RATING integer,"
                + "COMMENTS text,"
                + "PHOTO_NAME text,"
                + "SYNC boolean,"
                + "primary key (C_ISSO, PERIOD, C_REM)"
                + ");");
        db.execSQL("create table I_REM_PLAN_EXEC("
                + "C_ISSO integer,"
                + "PERIOD DATE,"
                + "PERFORMER text,"
                + "ACTUAL_DATE DATE,"
                + "EDIT_DATE DATE,"
                + "OFFSET long,"
                + "LATITUDE_REM REAL,"
                + "LONGITUDE_REM REAL,"
                + "SYNC boolean,"
                + "primary key (C_ISSO, PERIOD)"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Tag", " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");
        db.beginTransaction();
        try {
            if (newVersion == 2) {
                db.execSQL("create temporary table I_ISSO_tmp ("
                        + "C_ISSO integer primary key,"
                        + "NAME text,"
                        + "C_OTC_EXP integer,"
                        + "N_OTC_EXP text,"
                        + "FULLNAME text,"
                        + "DORNAME text,"
                        + "W_ISSO integer,"
                        + "OBSTACLE text, "
                        + "LENGTH REAL,"
                        + "LATITUDE REAL,"
                        + "LONGITUDE REAL);");
                db.execSQL("insert into I_ISSO_tmp select * from I_ISSO;");
                db.execSQL("drop table I_ISSO;");
                db.execSQL("create table I_ISSO ("
                        + "C_ISSO integer primary key,"
                        + "NAME text,"
                        + "C_OTC_EXP integer,"
                        + "N_OTC_EXP text,"
                        + "FULLNAME text,"
                        + "DORNAME text,"
                        + "W_ISSO integer,"
                        + "OBSTACLE text, "
                        + "LENGTH REAL,"
                        + "LATITUDE REAL,"
                        + "LONGITUDE REAL" + ");");
                db.execSQL("insert into I_ISSO select * from I_ISSO_tmp;");
                db.execSQL("drop table I_ISSO_tmp;");
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
        SharedPreferences sp = context.getSharedPreferences(MainActivity.MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("newVersion", true);
        e.commit();
    }
}

class MarshalDouble implements Marshal
{


    public Object readInstance(XmlPullParser parser, String namespace, String name,
                               PropertyInfo expected) throws IOException, XmlPullParserException {

        return Double.parseDouble(parser.nextText());
    }


    public void register(SoapSerializationEnvelope cm) {
        cm.addMapping(cm.xsd, "double", Double.class, this);

    }


    public void writeInstance(XmlSerializer writer, Object obj) throws IOException {
        writer.text(obj.toString());
    }

}
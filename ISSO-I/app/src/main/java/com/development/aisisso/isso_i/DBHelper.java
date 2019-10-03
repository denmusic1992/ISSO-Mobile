package com.development.aisisso.isso_i;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "ISSO_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table I_ISSO ("
                + "C_ISSO bigint primary key,"
                + "NAME text,"
                + "C_TYPEISSO integer,"
                + "C_OTC_EXP integer,"
                + "N_OTC_EXP text,"
                + "FULLNAME text,"
                + "DORNAME text,"
                + "W_ISSO REAL,"
                + "OBSTACLE text, "
                + "LENGTH REAL,"
                + "LATITUDE REAL,"
                + "LONGITUDE REAL" + ");");
        db.execSQL("create table TABLE_NAMES ("
                + "C_GR_CONSTR integer primary key,"
                + "SYS_NAME text,"
                + "DESCRIPTION text,"
                + "PARENT_ID integer,"
                + "PARENT_VIEW integer" + ");");
        db.execSQL("create table TABLE_ATTRIBUTES ("
                + "ID integer primary key,"
                + "C_GR_CONSTR integer,"
                + "SYS_NAME text,"
                + "DATA_TYPE text,"
                + "DESCRIPTION text,"
                + "IS_BLOB boolean,"
                + "CATEGORY text,"
                + "VISIBLEINGRID integer" + ");");
        db.execSQL("create table TABLE_VALUES ("
                + "ATTRIBUTE_ID integer,"
                + "ISSO integer,"
                + "VALUE text" + ");");
        db.execSQL("create table TABLE_DELEGATES ("
                + "ISSO_TYPE integer,"
                + "C_GR_CONSTR integer" + ");");
        db.execSQL("create table UPLOAD_PHOTOS ("
                + "C_ISSO integer,"
                + "N integer,"
                + "COMMENTS text,"
                + "PHOTO blob,"
                + "PHOTO_DATE long" + ");");
        db.execSQL("create table UPLOAD_SCHEMES ("
                + "C_ISSO integer,"
                + "N integer,"
                + "COMMENTS text,"
                + "SCHEME blob,"
                + "THUMBNAIL blob,"
                + "SCHEME_DATE long" + ");");

        /*db.execSQL("create table I_DEFECT ("
                + "C_ISSO integer,"
                + "N_DEF integer,"
                + "C_DEFECT integer,"
                + "C_GR_CONSTR integer,"
                + "N_CONSTR integer,"
                + "B integer,"
                + "B1 integer,"
                + "D integer,"
                + "D1 integer,"
                + "R integer,"
                + "R1 integer,"
                + "G integer,"
                + "G1 integer,"
                + "DATE DATE,"
                + "DATEF DATE" + ");");
        db.execSQL("create table I_DEF_MOD ("
                + "C_ISSO integer,"
                + "N_DEF integer,"
                + "DATE DATE,"
                + "L_DEF text,"
                + "W_DEF text,"
                + "C_REM integer,"
                + "V_REM REAL" + ");");
        db.execSQL("create table I_DEF_DESCR ("
                + "C_ISSO integer,"
                + "N_DEF integer,"
                + "DATE DATE,"
                + "C_DEFPARAM integer,"
                + "VALUE REAL,"
                + "B integer,"
                + "B1 integer,"
                + "D integer,"
                + "D1 integer,"
                + "R integer,"
                + "R1 integer,"
                + "G integer,"
                + "G1 integer" + ");");
        db.execSQL("create table S_DEFECT ("
                + "C_DEFECT integer,"
                + "N_DEFECT text,"
                + "DEF_GRP integer,"
                + "W_DEF text" + ");");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Tag", " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}
package com.example.dheoclaveria.capturelecture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 * Created by Dheo Claveria on 9/30/2017.
 */

public class DBTools extends SQLiteOpenHelper {

    private Context context;
    private static int version = 1;
    private static String db_name = "db_capturelecture";

    public String tbl_name = "tbl_capturelecture";

    public String fld_id = "cl_id";
    public String fld_title = "cl_title";
    public String fld_subjectname = "cl_subjectname";
    public String fld_description = "cl_description";
    public String fld_keyword = "cl_keyword";
    public String fld_date = "cl_date";
    public String fld_time = "cl_time";
    public String fld_image = "cl_image";
//////////////////////////////////////////////////

    public String tbl_subjectname = "tbl_subjectname";
    public String sn_id = "sn_id";
    public String sn_title = "sn_title";
    public String sn_image = "sn_image";
    public String sn_color = "sn_color";


    SQLiteDatabase db;

    public DBTools(Context appContext) {

        super(appContext, db_name, null, version);
        this.context = appContext;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + tbl_name + " ( " + fld_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + fld_title + " TEXT, " + fld_subjectname + " TEXT, " + fld_description + " TEXT, "
                + fld_keyword + " TEXT, " + fld_date + " TEXT, " + fld_time + " TEXT, " + fld_image + " TEXT ) ";

        db.execSQL(query);

        String query1 = "CREATE TABLE " + tbl_subjectname+ " ( " + sn_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + sn_title + " TEXT, " +  sn_image + " TEXT, " + sn_color + " TEXT ) ";

        db.execSQL(query1);
//
//        query1 = "INSERT INTO " + tbl_subjectname + " ( " + sn_title + "," + sn_image + "," + sn_color +  " ) " +
//                           " VALUES('Welcome to Capture Lecture','books','default') ";
//        db.execSQL(query1);

        try {
                String rootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/CaptureLecture";
                File file=new File(rootPath,"SubjectName");
                if(!file.exists()){
                    file.mkdirs();
                }



        }catch (Exception ex){}
//
//        query = "INSERT INTO " + tbl_name + " ( " + fld_title + "," + flds_subjectname + "," + fld_description + "," + fld_keyword + "," + fld_date + "," + fld_time + "," + fld_image + " ) " +
//                            " VALUES('Welcome to Capture Lecture','Programming','Sample description','no keyword','10-02-2017','12:13','book') ";
//        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXIST " + tbl_name;
        db.execSQL(query);

        String query1 = "DROP TABLE IF EXIST " + tbl_subjectname;
        db.execSQL(query1);
        onCreate(db);

    }

    public void openConnection() {

        db = this.getWritableDatabase();
    }

    public void closeConnection() {

        this.close();
    }

    public Cursor executeQuery(String query) {
        openConnection();
        db = this.getReadableDatabase();
        return db.rawQuery(query, null);

    }

    public Cursor getAllData() {

        openConnection();
        //return db.query(tbl_name, new String[]{ fld_title}, null, null, null, null, null, null);
        return db.query(tbl_name, new String[]{fld_title, fld_subjectname, fld_description, fld_keyword, fld_date, fld_time,fld_image}
                , null, null, null, null, null, null);
    }

    public Cursor getsingleData(int id) {

        String query = "SELECT * FROM " + tbl_name + "WHERE " + tbl_name + " = " + id;

        return db.rawQuery(query, null);
    }

    public long insertitem(String title, String subjectname, String descripton, String keyword, String date, String time, String image) {

        openConnection();
        ContentValues values = new ContentValues();
        values.put(fld_title, title);
        values.put(fld_subjectname, subjectname);
        values.put(fld_description, descripton);
        values.put(fld_keyword, keyword);
        values.put(fld_date, date);
        values.put(fld_time, time);
        values.put(fld_image, image);

        long succes = db.insert(tbl_name, null, values);
        return succes;

    }

    public long insertsubject(String title, String image, String color) {

        openConnection();
        ContentValues values = new ContentValues();
        values.put(sn_title, title);
        values.put(sn_image, image);
        values.put(sn_color, color);

        long succes = db.insert(tbl_subjectname, null, values);
        return succes;

    }


    public long updateitem(int id, String title, String subjectname, String descripton, String keyword, String date, String time , String image) {
        openConnection();
        ContentValues values = new ContentValues();
        values.put(fld_id, id);
        values.put(fld_title, title);
        values.put(fld_subjectname, subjectname);
        values.put(fld_description, descripton);
        values.put(fld_keyword, keyword);
        values.put(fld_date, date);
        values.put(fld_time, time);
        values.put(fld_image, image);


        long success = db.update(tbl_name,values, fld_id + "=?", new String[]{id + ""});
        return success;

    }

    public long updatesubject(int id,String title, String image, String color) {
        openConnection();
        ContentValues values = new ContentValues();
        values.put(fld_id, id);
        values.put(sn_title, title);
        values.put(sn_image, image);
        values.put(sn_color, color);

        long success = db.update(tbl_subjectname,values, fld_id + "=?", new String[]{id + ""});
        return success;

    }


    public boolean deleteData(String tblname,int id) {

        String sub_id = null;
        if(tblname.equals(tbl_name)){sub_id = fld_id;}
        else{sub_id = sn_id;}

        String query = "DELETE FROM " + tblname + " WHERE " + sub_id + " = " + id;

        try {
            openConnection();
            db.execSQL(query);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


}

package com.example.dheoclaveria.prelim_exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dheo Claveria on 7/22/2017.
 */
//dheo claveria and noel atazar prelim examaniation
public class DBTools extends SQLiteOpenHelper {

    private Context context;
    private static int version = 1;
    private static String db_name = "db_todo";

    public String tbl_name = "tbl_todo";

    public String fld_id = "todo_id";
    public String fld_title = "todo_title";
    public String fld_details = "todo_details";
    public String fld_frmdate = "todo_frmdate";
    public String fld_frmtime = "todo_frmtime";
    public String fld_todate = "todo_todate";
    public String fld_totime = "todo_totime";
    public String fld_prioritylevel = "todo_prioritylevel";

    SQLiteDatabase db;

    public DBTools(Context appContext) {

        super(appContext, db_name, null, version);
        this.context = appContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + tbl_name + " ( " + fld_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "  + fld_title + " TEXT, " + fld_details + " TEXT, " + fld_frmdate + " TEXT, "
                        + fld_frmtime + " TEXT, "+ fld_todate + " TEXT, "+ fld_totime + " TEXT, "+ fld_prioritylevel + " INT ) ";

        db.execSQL(query);
////
//        query = "INSERT INTO " + tbl_name + " ( " + fld_title + "," + fld_details + "," + fld_frmdate + "," + fld_frmtime + "," + fld_todate + "," + fld_totime + "," + fld_prioritylevel + " ) " +
//                            " VALUES('Prelim Exam','matapos to  ng 31','asd','asd','asd','asd','2') ";
//        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXIST " + tbl_name;
        db.execSQL(query);
        onCreate(db);

    }

    public void openConnection() {

        db = this.getWritableDatabase();
    }

    public void closeConnection() {

        this.close();
    }

    public Cursor getAllData() {

        openConnection();
        //return db.query(tbl_name, new String[]{ fld_title}, null, null, null, null, null, null);
        return db.query(tbl_name, new String[]{ fld_title, fld_details, fld_frmdate, fld_frmtime, fld_todate, fld_totime , fld_prioritylevel}

                                                                            , null, null, null, null ,null , null);
    }
    public Cursor getsingleData(int id) {

        String query = "SELECT * FROM "+tbl_name+ "WHERE "+tbl_name+ " = "+ id;

        return db.rawQuery(query,null);
    }

    public long insertData(String title, String details , String frmdate, String frmtime, String todate, String totime, int prioritylevel) {

        openConnection();
        ContentValues values = new ContentValues();
        values.put(fld_title, title);
        values.put(fld_details, details);
        values.put(fld_frmdate, frmdate);
        values.put(fld_frmtime, frmtime);
        values.put(fld_todate, todate);
        values.put(fld_totime, totime);
        values.put(fld_prioritylevel, prioritylevel);

        long succes = db.insert(tbl_name, null, values);
        return succes;

    }

    public long updateData(int id, String title, String details, String frmdate, String frmtitme, String todate, String totime, int prioritylevel) {
        openConnection();
        ContentValues values = new ContentValues();
        values.put(fld_id, id);
        values.put(fld_title, title);
        values.put(fld_details, details);
        values.put(fld_frmdate, frmdate);
        values.put(fld_frmtime, frmtitme);
        values.put(fld_todate, todate);
        values.put(fld_totime, totime);
        values.put(fld_prioritylevel, prioritylevel);

        long succes = db.update(tbl_name, values,fld_id + "=?", new String[]{id + ""});
        return succes;

    }

    public boolean deleteData(int id) {

        String query = "DELETE FROM " + tbl_name + " WHERE " + fld_id + " = " + id;

        try {
            openConnection();
            db.execSQL(query);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public Cursor executeQuery(String query) {
        openConnection();
        db = this.getReadableDatabase();
        return db.rawQuery(query,null);

    }

}

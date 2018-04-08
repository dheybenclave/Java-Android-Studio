package com.example.dheoclaveria.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dheo Claveria on 7/22/2017.
 */


public class DBTools extends SQLiteOpenHelper {

    private Context context;
    private static int version = 1;
    public static String db_name = "db_user";
    public String tbl_name = "tbl_user";

    public String fld_id = "user_id";
    public String fld_name = "user_name";
    public String fld_password = "user_password";


        SQLiteDatabase db;

    public DBTools(Context appContext) {

        super(appContext, db_name, null, version);
        this.context = appContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + tbl_name + " ( " + fld_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + fld_name + " TEXT, " + fld_password + " TEXT ) ";

        db.execSQL(query);
        query = "INSERT INTO " + tbl_name + " ( " + fld_name + "," + fld_password + " ) " + " VALUES('Dheo Claveria','claveria') ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int odlVersion, int newVersion) {

        String query = "DROP TABLE IF EXIST " + tbl_name;
        db.execSQL(query);
        onCreate(db);

    }
    public void openConnection() {

        db = this.getWritableDatabase();
    }

    public void closeconnection() {

        this.close();
    }

    public Cursor getAlluser() {

        openConnection();
        return db.query(tbl_name, new String[]{fld_id, fld_name, fld_password}, null, null, null, null, null, null);

    }

    public Cursor getUser(int id) {

        String query = "SELECT * FROM tbl_user " + "WHERE user_id  " + id;

        return db.rawQuery(query,null);
    }

    public long insertData(String name, String password) {

        openConnection();
        ContentValues values = new ContentValues();
        values.put(fld_name, name);
        values.put(fld_password, password);

        long succes = db.insert(tbl_name, null, values);
        return succes;

    }


    public long updateData(int id, String name, String password) {
        openConnection();
        ContentValues values = new ContentValues();
        values.put(fld_id, id);
        values.put(fld_name, name);
        values.put(fld_password, password);

        long succes = db.update(tbl_name, values,fld_id + "=?", new String[]{id + ""});
        return succes;

    }
    public boolean deleteDate(int id) {

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

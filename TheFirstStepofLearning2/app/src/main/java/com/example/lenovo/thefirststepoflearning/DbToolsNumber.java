package com.example.lenovo.thefirststepoflearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dragonaire on 10/14/2017.
 */

public class DbToolsNumber extends SQLiteOpenHelper {
    private Context contex;
    private static int version =3;
    private static String dbName = "DBUsersNum";
    public String tblLists = "tblUsersNum";
    private String fldID = "UserID";
    private String fldUsername = "Username";
    private String fldScore = "Score";

    SQLiteDatabase db;

    public DbToolsNumber(Context appContext){
        super(appContext,dbName,null,version);
        this.contex = appContext;
    }

    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + tblLists + "( " + fldID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + fldUsername + " TEXT, "
                + fldScore + " TEXT ) ";

        db.execSQL(query);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS " + tblLists;
        db.execSQL(query);
        onCreate(db);
    }

    public void openConnection(){
        db = this.getWritableDatabase();
    }
    public void closeConnection(){
        this.close();
    }
    public Cursor getAllUsers(){
        openConnection();
        return db.query(tblLists, new String[]{fldID,fldUsername,fldScore}, null,null,null,null,null);
    }

    public long insertUser(String username, String score){
        openConnection();
        ContentValues values = new ContentValues();
        values.put(fldUsername, username);
        values.put(fldScore,score);

        long success = db.insert(tblLists, null, values);
        return success;
    }
    public long updateUser(int id, String username, String score){
        openConnection();
        ContentValues values = new ContentValues();
        values.put(fldID, id);
        values.put(fldUsername,username);
        values.put(fldScore, score);
        String id1 = String.valueOf(id);
        long success = db.update(tblLists, values, fldID + "=?" , new String[]{id1});
        return success;
    }

    public long deleteUser(String id)
    {
        openConnection();
        int i = db.delete(tblLists,"UserID=?", new String[]{id});
        return i;
    }
}

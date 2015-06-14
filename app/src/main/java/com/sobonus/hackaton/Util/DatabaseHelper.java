package com.sobonus.hackaton.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sobonus.hackaton.Model.RateModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhamad.kamal on 22/5/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String KEY_ROWID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_VALUE = "value";

    private static final String DATABASE_TABLE_SETTING = "setting";
    private static final String DATABASE_TABLE_FAVOURITE = "rememberme";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "soexchange.sqlite";
    public final static String DATABASE_PATH ="/data/data/com.sobonus.hackaton/databases/";
    public static final int DATABASE_VERSION = 1;
    //public static final int DATABASE_VERSION_old = 1;

    //Constructor
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    //Create a empty database on the system
    public void createDatabase() throws IOException
    {
        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            //Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist1 = checkDataBase();
        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }
    }

    //Check database already exist or not
    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }

    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException
    {
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    //delete database
    public void db_delete()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }

    //Open database
    public void openDatabase() throws SQLException
    {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public void onCreate(SQLiteDatabase db)
    {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }
    }

    public String getBaseCode(){
        String query = "SELECT * FROM setting where name='basecode'";
            SQLiteDatabase db = this.getWritableDatabase();
        Timestamp time;
        Cursor cursor = db.rawQuery(query, null);
        String basecode = null;
        try {
            if (cursor.moveToFirst()) {
                do {
                    // time= new Timestamp(cursor.getLong())
                     basecode = cursor.getString(2);
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
            db.close();
        }

      /*  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("SELECT * FROM ZPRAYER WHERE ZZONEDETAIL = ? AND ZPRAYERTIMEDATE= ?");
        stmt.bindString(1, "19");
        stmt.bindString(2, String.valueOf(startstamp.getNanos()));
        stmt.execute();*/


    return basecode;}

    public boolean updateBaseCode(String basecode)
    {
        ContentValues args = new ContentValues();
/*
        args.put(KEY_ROWID, rowId);
*/
        args.put(KEY_VALUE, basecode);
/*
        args.put(KEY_NAME, name);
        args.put(KEY_NOTIFICATION, address);*/
        int i =  myDataBase.update(DATABASE_TABLE_SETTING, args, KEY_ROWID + "=" + 1+"", null);

        return i > 0;
    }

    public List<String> getFavouriteList(){
        List<String>list = new ArrayList<>();
        String query = "SELECT * FROM "+DATABASE_TABLE_FAVOURITE;
        SQLiteDatabase db = this.getWritableDatabase();
        Timestamp time;
        Cursor cursor = db.rawQuery(query, null);
        String basecode = null;
        try {
            if (cursor.moveToFirst()) {
                do {
                    // time= new Timestamp(cursor.getLong())
                    basecode = cursor.getString(1);
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
            db.close();
        }

      /*  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("SELECT * FROM ZPRAYER WHERE ZZONEDETAIL = ? AND ZPRAYERTIMEDATE= ?");
        stmt.bindString(1, "19");
        stmt.bindString(2, String.valueOf(startstamp.getNanos()));
        stmt.execute();*/


        return list;}

    public void insertFavourite(String base) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", base);
        db.insert(DATABASE_TABLE_FAVOURITE, null, values);
        db.close(); }

}
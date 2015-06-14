package com.sobonus.hackaton.Util;

import android.content.Context;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by muhamad.kamal on 13/6/2015.
 */
public class Util {
    DatabaseHelper myDbHelper;
    public static DatabaseHelper executeDB(Context context){
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        try {
            myDbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            myDbHelper.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return myDbHelper;  }

}

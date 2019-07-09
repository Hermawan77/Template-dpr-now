package com.example.template_dpr_now;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 4;
    public static final String TABEL_INPUT ="input";

    public DataHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // TODO Auto-generated method stub
        String sql_input = "create table input(id integer primary key, name text null, email text null, phone text null," +
                "time text null, date text null, essai text null); ";
        Log.d("Data", "onCreate: "+ sql_input);
        db.execSQL(sql_input);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        db.execSQL("DROP TABLE IF EXISTS '" + TABEL_INPUT + "'");
        onCreate(db);
    }
}

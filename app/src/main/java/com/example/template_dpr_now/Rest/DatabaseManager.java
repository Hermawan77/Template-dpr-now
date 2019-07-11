package com.example.template_dpr_now.Rest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dpr";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pilihan";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_ESSAI = "essai";
    private static final String COLUMN_PILIHAN = "pilihan";

    public DatabaseManager(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase){
        String sql =
                "CREATE TABLE IF NOT EXISTS pilihan (\n"+
                        "   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n"+
                        "   name varchar(100) NOT NULL, \n"+
                        "   email varchar(100) NOT NULL, \n"+
                        "   phone double NOT NULL, \n"+
                        "   time time NOT NULL, \n"+
                        "   date date NOT NULL, \n"+
                        "   essai varchar(200) NOT NULL, \n"+
                        "   pilihan varchar(100) NOT NULL \n"+
                        ");";

        sqLiteDatabase.execSQL(sql);

    }

    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1){
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public boolean addpilihan(String name, String email, double phone, String date, String time, String essai, String pilihan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_ESSAI, essai);
        contentValues.put(COLUMN_PILIHAN, pilihan);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;

    }

    public Cursor getAllPilihan(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updatepilihan(int id, String name, String email, Double phone, String date, String time, String essai, String pilihan){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_ESSAI, essai);
        contentValues.put(COLUMN_PILIHAN, pilihan);
        return db.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1 ;
    }

    public boolean deleteEmployee(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }
}

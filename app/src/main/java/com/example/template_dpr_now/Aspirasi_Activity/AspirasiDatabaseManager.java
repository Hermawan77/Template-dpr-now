package com.example.template_dpr_now.Aspirasi_Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AspirasiDatabaseManager extends SQLiteOpenHelper {

    //deklarasi variable
    private static final String DATABASE_NAME = "dpr";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_NAME = "aspirasi";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_ESSAI = "essai";
    private static final String COLUMN_PILIHAN = "pilihan";
    private static final String COLUMN_CHECKBOXVAL = "checkboxval";
    private static final String COLUMN_RADIOTEXT = "radiotext";
    private static final String COLUMN_SEEKBAR = "seekbar";
    private static final String COLUMN_IMAGE = "image";

    //inisialisasi
    public AspirasiDatabaseManager(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    //pembuatan fungsi oncreate, untuk membuat database
    //disini kita dapat mendeklarasikan jenis variable dan jumlah karakter variable tersebut
    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase){
        String sql =
                "CREATE TABLE IF NOT EXISTS aspirasi (\n"+
                        "   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n"+
                        "   name varchar(100) NOT NULL, \n"+
                        "   email varchar(100) NOT NULL, \n"+
                        "   phone varchar (15) NOT NULL, \n"+
                        "   time time NOT NULL, \n"+
                        "   date date NOT NULL, \n"+
                        "   essai varchar(200) NOT NULL, \n"+
                        "   pilihan varchar(100) NOT NULL, \n"+
                        "   checkboxval varchar NOT NULL, \n"+
                        "   radiotext varchar NOT NULL, \n"+
                        "   seekbar varchar \n,"+
                        "   image BLOB \n"+
                        ");";

        sqLiteDatabase.execSQL(sql);

    }

    //fungsi saat database diperbaharui, maka database sebelumnya akan d drop dan digantikan dengan yang baru
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1){
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    //fungsi untuk memasukkan nilai dari inputan yang telah dimasukkan
    public boolean addpilihan(String name, String email, String phone, String date, String time, String essai, String pilihan, String checkboxval, String radiotext, String seekbar, byte[] image){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_ESSAI, essai);
        contentValues.put(COLUMN_PILIHAN, pilihan);
        contentValues.put(COLUMN_CHECKBOXVAL, checkboxval);
        contentValues.put(COLUMN_RADIOTEXT, radiotext);
        contentValues.put(COLUMN_SEEKBAR, seekbar);
        contentValues.put(COLUMN_IMAGE, image);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    //fungsi untuk menampilkan seluruh data inputan yang telah masuk
    public Cursor getAllPilihan(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    //fungsi untuk mengupdate data aspirasi yang sudah ada
    public boolean updateAspirasi(int id, String name, String email, String phone, String date, String time, String essai, String pilihan, String checkboxval, String radiotext, String seekbar, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_ESSAI, essai);
        contentValues.put(COLUMN_PILIHAN, pilihan);
        contentValues.put(COLUMN_CHECKBOXVAL, checkboxval);
        contentValues.put(COLUMN_RADIOTEXT, radiotext);
        contentValues.put(COLUMN_SEEKBAR, seekbar);
        contentValues.put(COLUMN_IMAGE, String.valueOf(image));
        return db.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 0 ;
    }

    //fungsi untuk menghapus data yang diinginkan
    public boolean deleteAspirasi(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 0;
    }

}

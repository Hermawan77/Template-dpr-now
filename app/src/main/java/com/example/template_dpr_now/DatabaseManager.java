package com.example.template_dpr_now;

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
                        "   phone varchar (15) NOT NULL, \n"+
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

    public boolean addpilihan(String name, String email, String phone, String date, String time, String essai, String pilihan){
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

    public boolean updatepilihan(int id, String name, String email, String phone, String date, String time, String essai, String pilihan){
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
//
//    public List<Aspirasii> findAll(){
//        List<Aspirasii> pilihans = null;
//        try{ SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + pilihans, null);
//            if (cursor.moveToFirst()){
//                pilihans = new ArrayList<>();
//                do {
//                    Aspirasi pilihans = new Aspirasii();
//                            pilihans.setid(cursor.getInt(0));
//                            cursor.getString(1);
//                            cursor.getString(2);
//                            cursor.getString(3);
//                            cursor.getString(4);
//                            cursor.getString(5);
//                            cursor.getString(6);
//                            cursor.getString(7);
//
//                    ));
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            pilihans = null;
//        }
//        return pilihans;
//    }
//
//    public List <Aspirasi> search (String  keyword) {
//        List<Aspirasi> pilihans = null;
//        try{ Cursor cursor = mDatabase.getAllPilihan();
//            if (cursor.moveToFirst()){
//                pilihans = new ArrayList<>();
//                do {
//                    pilihanList.add(new Aspirasii(
//                            cursor.getInt(0),
//                            cursor.getString(1),
//                            cursor.getString(2),
//                            cursor.getString(3),
//                            cursor.getString(4),
//                            cursor.getString(5),
//                            cursor.getString(6),
//                            cursor.getString(7)
//                    ));
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            pilihans = null;
//        }
//        return pilihans;
//    }

}

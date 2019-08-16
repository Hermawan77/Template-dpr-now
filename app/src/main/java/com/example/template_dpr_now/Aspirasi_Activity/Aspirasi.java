package com.example.template_dpr_now.Aspirasi_Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.template_dpr_now.R;

import java.util.ArrayList;
import java.util.List;

public class Aspirasi extends AppCompatActivity {

    //deklarasi variable
    List<Aspirasii> pilihanList;
    ListView listViewPilihans;

    //mendeklarasikan objek dari database
    AspirasiDatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aspirasi_layout);

        //bagian inisialisasi database
        mDatabase = new AspirasiDatabaseManager(this);

        //inisialisai list
        listViewPilihans = (ListView) findViewById(R.id.listviewaspirasi);
        pilihanList = new ArrayList<>();

        //metode ini kan menampilkan data yang ada dalam database
        loadFromDatabase();

    }

    private void loadFromDatabase() {

        //kita gunakan rawQuery(sql, selectionargs) untuk mengambil semua data inputan yang masuk
        Cursor cursor = mDatabase.getAllPilihan();
        //kondisi disini menjelaskan if (ketika) kursor mendapatkan data
        if (cursor.moveToFirst()) {
            //akan melakukan looping (mengulangi pencarian semua data yang ada)
            do {
                //akan memuat setiap catatan data kedalam list
                pilihanList.add(new Aspirasii(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getBlob(11)
                ));
            } while (cursor.moveToNext());
        }

        //membuat objek adapter
        AspirasiAdapter adapter = new AspirasiAdapter(this, R.layout.aspirasi_list_layout, pilihanList, mDatabase);

        //menambahkan adapter kedalam listview
        listViewPilihans.setAdapter(adapter);
    }
}

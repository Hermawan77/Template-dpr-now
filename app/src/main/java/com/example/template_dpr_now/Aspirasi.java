package com.example.template_dpr_now;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Aspirasi extends AppCompatActivity {

    List<Aspirasii> pilihanList;
    ListView listViewPilihans;

    //The databasemanager object
    DatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirasi);

        mDatabase = new DatabaseManager(this);

        listViewPilihans = (ListView) findViewById(R.id.listviewaspirasi);
        pilihanList = new ArrayList<>();

        //this method will display the employees in the list
        loadFromDatabase();

    }

    private void loadFromDatabase() {

        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursor = mDatabase.getAllPilihan();
        //if the cursor has some data
        if (cursor.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
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

        //creating the adapter object
        AspirasiAdapter adapter = new AspirasiAdapter(this, R.layout.list_layout_aspirasi, pilihanList, mDatabase);

        //adding the adapter to listview
        listViewPilihans.setAdapter(adapter);
    }
}

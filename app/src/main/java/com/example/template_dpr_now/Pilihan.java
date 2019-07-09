package com.example.template_dpr_now;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Pilihan extends AppCompatActivity {

    List<Pilihann> pilihanList;
    SQLiteDatabase db;
    ListView listViewPilihans;
    PilihanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan);

        listViewPilihans = (ListView) findViewById(R.id.listViewEmployees);
        pilihanList = new ArrayList<Pilihann>();

        //opening the database
        db = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        //this method will display the employees in the list
        showPilihansFromDatabase();
    }
    private void showPilihansFromDatabase() {

        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = db.rawQuery("SELECT * FROM employees", null);

        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                pilihanList.add(new Pilihann(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getString(4),
                        cursorEmployees.getString(5),
                        cursorEmployees.getString(6),
                        cursorEmployees.getDouble(7)
                ));
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();

        //creating the adapter object
        adapter = new PilihanAdapter(this, R.layout.list_layout_pilihan, pilihanList);

        //adding the adapter to listview
        listViewPilihans.setAdapter(adapter);
    }

}

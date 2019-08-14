package com.example.template_dpr_now.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.template_dpr_now.AspirasiAdapter;
import com.example.template_dpr_now.AspirasiDatabaseManager;
import com.example.template_dpr_now.AspirasiInput;
import com.example.template_dpr_now.Aspirasii;
import com.example.template_dpr_now.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentAspirasi extends Fragment {

    List<Aspirasii> pilihanList;
    ListView listViewPilihans;
    AspirasiDatabaseManager mDatabase;
    Button btnbuataspirasi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aspirasi, container, false);

        mDatabase = new AspirasiDatabaseManager(getActivity());
        listViewPilihans = view.findViewById(R.id.listviewaspirasi);
        pilihanList = new ArrayList<>();
        loadPilihansFromDatabase();
        btnbuataspirasi = view.findViewById(R.id.btnbuataspirasi);
        btnbuataspirasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AspirasiInput.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void loadPilihansFromDatabase() {
        Cursor cursor = mDatabase.getAllPilihan();
        if (cursor.moveToFirst()) {
            do {
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

        AspirasiAdapter adapter = new AspirasiAdapter(getActivity(), R.layout.aspirasi_list_layout, pilihanList, mDatabase);

        listViewPilihans.setAdapter(adapter);
    }
}

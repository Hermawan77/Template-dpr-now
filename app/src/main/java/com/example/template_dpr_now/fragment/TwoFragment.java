package com.example.template_dpr_now.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.template_dpr_now.DatabaseManager;
import com.example.template_dpr_now.PilihanAdapter;
import com.example.template_dpr_now.Pilihann;
import com.example.template_dpr_now.R;

import java.util.ArrayList;
import java.util.List;


public class TwoFragment extends Fragment {

    List<Pilihann> pilihanList;
    ListView listViewPilihans;
    DatabaseManager mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        mDatabase = new DatabaseManager(getActivity());
        listViewPilihans = view.findViewById(R.id.listviewpilihan);
        pilihanList = new ArrayList<>();

        loadPilihansFromDatabase();

        return view;
    }

    private void loadPilihansFromDatabase() {
        Cursor cursor = mDatabase.getAllPilihan();
        if (cursor.moveToFirst()) {
            do {
                pilihanList.add(new Pilihann(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }

        PilihanAdapter adapter = new PilihanAdapter(getActivity(), R.layout.list_layout_pilihan, pilihanList, mDatabase);

        listViewPilihans.setAdapter(adapter);
    }
}

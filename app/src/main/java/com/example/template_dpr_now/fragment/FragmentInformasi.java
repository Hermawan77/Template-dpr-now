package com.example.template_dpr_now.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.template_dpr_now.Agenda;
import com.example.template_dpr_now.Berita;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.SemuaAnggota;

public class FragmentInformasi extends Fragment {

    private Button keSemuaAnggota;
    private Button keBerita;
    private Button keAgenda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informasi, container, false);

        keBerita = view.findViewById(R.id.berita);
        keAgenda = view.findViewById(R.id.agenda);
        keSemuaAnggota = view.findViewById(R.id.semuaAnggota);

        keBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Berita.class);
                startActivity(intent);
            }
        });

        keAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Agenda.class);
                startActivity(intent);
            }
        });

        keSemuaAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SemuaAnggota.class);
                startActivity(intent);
            }
        });


        return view;
    }
}

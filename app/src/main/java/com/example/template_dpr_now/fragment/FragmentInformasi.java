package com.example.template_dpr_now.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.template_dpr_now.Agenda_Activity.Agenda;
import com.example.template_dpr_now.Berita_Activity.Berita;
import com.example.template_dpr_now.Pelayananmasyarakat_Activity.PelayananmasyakatActivity;
import com.example.template_dpr_now.Majalah_Activity.MajalahActivity;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Semuaanggota_Activity.SemuaAnggota;
import com.example.template_dpr_now.Undangundang_Activity.UndangUndangActivity;

public class FragmentInformasi extends Fragment {

    private Button keSemuaAnggota;
    private Button keBerita;
    private Button keAgenda;
    private Button keKunjungan;
    private Button keMajalah;
    private Button keUU;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informasi, container, false);

        keBerita = view.findViewById(R.id.berita);
        keAgenda = view.findViewById(R.id.agenda);
        keSemuaAnggota = view.findViewById(R.id.semuaAnggota);
        keKunjungan = view.findViewById(R.id.kunjungan);
        keMajalah = view.findViewById(R.id.daftarmajalah);
        keUU = view.findViewById(R.id.hasil);

        keBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Berita.class);
                startActivity(intent);
            }
        });

        keKunjungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PelayananmasyakatActivity.class);
                startActivity(i);
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

        keMajalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MajalahActivity.class);
                startActivity(i);
            }
        });

        keUU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UndangUndangActivity.class);
                startActivity(i);
            }
        });



        return view;
    }
}

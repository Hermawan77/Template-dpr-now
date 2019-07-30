package com.example.template_dpr_now;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.template_dpr_now.Agenda.EXTRA_DESKRIPSI;
import static com.example.template_dpr_now.Agenda.EXTRA_JAM;
import static com.example.template_dpr_now.Agenda.EXTRA_JUDUL;
import static com.example.template_dpr_now.Agenda.EXTRA_TANGGAL;

public class AgendaDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_detail);

        Intent intent = getIntent();
        String tanggal = intent.getStringExtra(EXTRA_TANGGAL);
        String jam = intent.getStringExtra(EXTRA_JAM);
        String judul = intent.getStringExtra(EXTRA_JUDUL);
        String deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI);

        TextView textViewTanggal = findViewById(R.id.tanggalberita_detail);
        TextView textViewJam = findViewById(R.id.kategori_detail);
        TextView textViewJudul = findViewById(R.id.judulberita_detail);
        TextView textViewDeskripsi = findViewById(R.id.isiberita_detail);

        textViewTanggal.setText(tanggal);
        textViewJam.setText(jam);
        textViewJudul.setText(judul);
        textViewDeskripsi.setText(deskripsi);
    }
}

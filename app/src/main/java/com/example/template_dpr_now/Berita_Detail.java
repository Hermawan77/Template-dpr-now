package com.example.template_dpr_now;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.template_dpr_now.Berita.EXTRA_ISI;
import static com.example.template_dpr_now.Berita.EXTRA_JUDUL;
import static com.example.template_dpr_now.Berita.EXTRA_KATEGORI;
import static com.example.template_dpr_now.Berita.EXTRA_TANGGAL;
import static com.example.template_dpr_now.Berita.EXTRA_URL;

public class Berita_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);

        Intent intent = getIntent();
        String tanggal = intent.getStringExtra(EXTRA_TANGGAL);
        String kategori = intent.getStringExtra(EXTRA_KATEGORI);
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String judul = intent.getStringExtra(EXTRA_JUDUL);
        String isi = intent.getStringExtra(EXTRA_ISI);

        TextView textViewTanggal = findViewById(R.id.tanggalberita_detail);
        TextView textViewKategori = findViewById(R.id.kategori_detail);
        ImageView imageView = findViewById(R.id.fotoberita_detail);
        TextView textViewJudul = findViewById(R.id.judulberita_detail);
        TextView textViewIsi = findViewById(R.id.isiberita_detail);

        textViewTanggal.setText(tanggal);
        textViewKategori.setText(kategori);
        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewJudul.setText(judul);
        textViewIsi.setText(isi);
    }
}

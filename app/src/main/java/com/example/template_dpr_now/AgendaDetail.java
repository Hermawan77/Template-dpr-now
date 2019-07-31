package com.example.template_dpr_now;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.template_dpr_now.Agenda.EXTRA_DESKRIPSI;
import static com.example.template_dpr_now.Agenda.EXTRA_JAM;
import static com.example.template_dpr_now.Agenda.EXTRA_JUDUL;
import static com.example.template_dpr_now.Agenda.EXTRA_TANGGAL;

public class AgendaDetail extends AppCompatActivity {

    ImageButton backagendadetail;
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

        backagendadetail = findViewById(R.id.backdetailagenda);
        backagendadetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaDetail.this, Agenda.class));
                finish();
            }
        });
    }
}

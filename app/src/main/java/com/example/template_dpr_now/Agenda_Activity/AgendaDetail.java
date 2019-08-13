package com.example.template_dpr_now.Agenda_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.template_dpr_now.R;

import static com.example.template_dpr_now.Agenda_Activity.Agenda.EXTRA_DESKRIPSI;
import static com.example.template_dpr_now.Agenda_Activity.Agenda.EXTRA_JAM;
import static com.example.template_dpr_now.Agenda_Activity.Agenda.EXTRA_JUDUL;
import static com.example.template_dpr_now.Agenda_Activity.Agenda.EXTRA_TANGGAL;

public class AgendaDetail extends AppCompatActivity {

    ImageButton backagendadetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_detail);

        //Menerima dats dari activity Agenda
        Intent intent = getIntent();
        String tanggal = intent.getStringExtra(EXTRA_TANGGAL);
        String jam = intent.getStringExtra(EXTRA_JAM);
        String judul = intent.getStringExtra(EXTRA_JUDUL);
        String deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI);

        TextView textViewTanggal = findViewById(R.id.agenda_tanggal);
        TextView textViewJam = findViewById(R.id.agenda_jam);
        TextView textViewJudul = findViewById(R.id.agenda_isi);
        TextView textViewDeskripsi = findViewById(R.id.agenda_deskripsi);

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

package com.example.template_dpr_now;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Pengaturan extends AppCompatActivity {

    Spinner spinner_font;
    Button t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pengaplikasian tema saat activity di-create
        Theme.createTheme(this);

        setContentView(R.layout.activity_pengaturan);

        t1 = findViewById(R.id.Theme1);
        t2 = findViewById(R.id.Theme2);
        t3 = findViewById(R.id.Theme3);

        spinner_font = findViewById(R.id.spinner_font);


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theme.setTheme(getApplication(), 0);
                //refresh activity
                recreate();
                Toast.makeText(Pengaturan.this, "Tema berhasil diubah", Toast.LENGTH_SHORT).show();
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theme.setTheme(getApplication(), 1);
                recreate();
                Toast.makeText(Pengaturan.this, "Tema berhasil diubah", Toast.LENGTH_SHORT).show();
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theme.setTheme(getApplicationContext(), 2);
                recreate();
                Toast.makeText(Pengaturan.this, "Tema berhasil diubah", Toast.LENGTH_SHORT).show();
            }
        });

        spinner_font.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        Theme.setTheme(getApplicationContext(), 10);
                        recreate();
                        Toast.makeText(Pengaturan.this, "Font berhasil diubah", Toast.LENGTH_SHORT).show();
                        spinner_font.setSelection(0);
                        break;
                    case 2:
                        Theme.setTheme(getApplicationContext(), 11);
                        recreate();
                        Toast.makeText(Pengaturan.this, "Font berhasil diubah", Toast.LENGTH_SHORT).show();
                        spinner_font.setSelection(0);
                        break;

                        default:
                            Toast.makeText(Pengaturan.this, "Font gagal diubah", Toast.LENGTH_SHORT).show();
                            spinner_font.setSelection(0);
                            break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

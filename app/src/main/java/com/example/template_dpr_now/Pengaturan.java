package com.example.template_dpr_now;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Pengaturan extends AppCompatActivity {

    Button t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Theme.createTheme(this);

        setContentView(R.layout.activity_pengaturan);

        t1 = findViewById(R.id.Theme1);
        t2 = findViewById(R.id.Theme2);
        t3 = findViewById(R.id.Theme3);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theme.setTheme(getApplication(), 0);
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
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

package com.example.template_dpr_now;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Pengaturan extends AppCompatActivity {

    Button t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Theme.createTheme(this);

        setContentView(R.layout.activity_pengaturan);

        t1 = findViewById(R.id.Theme1);
        t2 = findViewById(R.id.Theme2);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theme.setTheme(getApplication(), 0);
                recreate();
                Toast.makeText(Pengaturan.this, "Restart aplikasi untuk perubahan", Toast.LENGTH_SHORT).show();
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theme.setTheme(getApplication(), 1);
                recreate();
                Toast.makeText(Pengaturan.this, "Restart aplikasi untuk perubahan", Toast.LENGTH_SHORT).show();
            }
        });
    }



}

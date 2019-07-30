package com.example.template_dpr_now;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
    // Mendeklarasikan Variable
    private final int SPLASH_DISPLAY_LENGTH = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Menampilkan activity_splash.xml
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // Membuat fungsi splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ketika splash selesai maka akan pindah class ke Slide.java
                Intent mainIntent = new Intent(SplashActivity.this, Slide.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }

            // Durasi splash yaitu 2000 atau 2 detik
        }, SPLASH_DISPLAY_LENGTH);
    }
}

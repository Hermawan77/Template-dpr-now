package com.example.template_dpr_now;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "google_account";
    Button crudbtn, homebtn, off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        off = (Button) findViewById(R.id.logout);
        crudbtn= findViewById(R.id.crudbtn);
        homebtn = findViewById(R.id.homebtn);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MulaiActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

        crudbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Input.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.template_dpr_now;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Daftar extends AppCompatActivity implements View.OnClickListener  {

    // Mendeklarasikan Variable
    FirebaseAuth mAuth;
    EditText daftaremail, daftarpassword;
    Button daftar;
    TextView disini;

    // Menampilkan activity_daftar.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        // Memberikan nilai
        daftaremail = (EditText)findViewById(R.id.E_maildaftar);
        daftarpassword = (EditText) findViewById(R.id.passworddaftar);
        daftar = (Button) findViewById(R.id.daftarakun);
        disini = (TextView) findViewById(R.id.sudahpunya);
        mAuth = FirebaseAuth.getInstance();

        // Memberikan Handler agar ada fungsi saat di click
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Memberikan Handler agar ada fungsi saat di click
        disini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah dari class ini ke Email.java
                Intent i = new Intent(Daftar.this, Email.class);
                startActivity(i);
            }
        });

    }

    // Method untuk registrasi akun email
    public void registerUser(){
        // Mendeklarasikan Variable dan mengambil variable yang telah diisi user
        String email = daftaremail.getText().toString().trim();
        String password = daftarpassword.getText().toString().trim();

        // Mengecek apakah email sudah diisi atau belum
        if (email.isEmpty()) {
            daftaremail.setError("Email is required");
            daftaremail.requestFocus();
            return;
        }

        // Mengecek apakah email valid atau tidak
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            daftaremail.setError("Please enter a valid email");
            daftaremail.requestFocus();
            return;
        }

        // Mengecek apakah password sudah diisi atau belum
        if (password.isEmpty()) {
            daftarpassword.setError("Password is required");
            daftarpassword.requestFocus();
            return;
        }

        // Mengecek apakah password sudah memenuhi minimum huruf atau angka
        if (password.length() < 6) {
            daftarpassword.setError("Minimum lenght of password should be 6");
            daftarpassword.requestFocus();
            return;
        }

        // Membuat email dan password pada Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Jika berhasil maka akan muncul toast lalu pindah class
                if (task.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Register Succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Daftar.this, Email.class);
                    startActivity(i);
                }
                // Jika tidak maka akan muncul toast lalu muncul keterangan
                else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }


    @Override
    public void onClick(View view) {


    }

}

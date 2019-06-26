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

    FirebaseAuth mAuth;
    EditText daftaremail, daftarpassword;
    Button daftar;
    TextView disini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        daftaremail = (EditText)findViewById(R.id.E_maildaftar);
        daftarpassword = (EditText) findViewById(R.id.passworddaftar);
        daftar = (Button) findViewById(R.id.daftarakun);
        disini = (TextView) findViewById(R.id.sudahpunya);
        mAuth = FirebaseAuth.getInstance();

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        disini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Daftar.this, Email.class);
                startActivity(i);
            }
        });

    }

    public void registerUser(){
        String email = daftaremail.getText().toString().trim();
        String password = daftarpassword.getText().toString().trim();

        if (email.isEmpty()) {
            daftaremail.setError("Email is required");
            daftaremail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            daftaremail.setError("Please enter a valid email");
            daftaremail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            daftarpassword.setError("Password is required");
            daftarpassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            daftarpassword.setError("Minimum lenght of password should be 6");
            daftarpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Register Succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Daftar.this, Email.class);
                    startActivity(i);
                } else {

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

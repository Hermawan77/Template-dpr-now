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
import com.google.firebase.auth.FirebaseUser;

public class Email extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    TextView daftarakun;
    EditText email, pass;
    Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            FirebaseUser user =  firebaseAuth.getCurrentUser();
            Intent i = new Intent(Email.this, MainActivity.class);
            startActivity(i);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.E_mail);
        pass = (EditText) findViewById(R.id.passwordemail);
        login = (Button) findViewById(R.id.loginemail);
        daftarakun = (TextView) findViewById(R.id.daftardisini);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        daftarakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    private void userLogin() {
        String Email = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (Email.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            pass.setError("Minimum lenght of password should be 6");
            pass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(Email.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(Email.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Email.this, "Email atau Password salah", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void Register() {
        Intent intent = new Intent(this, Daftar.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}

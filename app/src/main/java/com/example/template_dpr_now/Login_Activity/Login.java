package com.example.template_dpr_now.Login_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Util.SharedPrefManager;
import com.example.template_dpr_now.fragment.LainnyaFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    // Mendeklarasikan Variable
    private static final String TAG = "Template_DPR_Now";
    private long backPressedTime;
    FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;
    private SignInButton googleSignInButton;
    SharedPrefManager sharedPrefManager;
    Button google;

    /**
     back press berfungsi ketika ingin keluar aplikasi, pengguna harus menekan tombol back sebanyak 2 kali
     dan akan memunculkan toast
     **/
            boolean twice;

    @Override
    public void onBackPressed() {
        if(twice == true){
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            System.exit(0);
        }

        twice = true;
        Toast.makeText(this, "Tekan Kembali untuk exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 3000);

    }



    // Menampilkan layott login_layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
        Mengecek Login melalui Firebase
         **/
        firebaseAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            FirebaseUser user =  firebaseAuth.getCurrentUser();
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(Login.this, "Selamat Datang Kembali", Toast.LENGTH_SHORT).show();
        }

        /**
          Code berikut berfungsi untuk mengecek session dari Login via API, Jika session true ( sudah login )
          maka langsung memulai MainActivity.
          **/
        sharedPrefManager = new SharedPrefManager(this);


        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(Login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_layout);

        // Memberi nilai
        googleSignInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);


        // Memberi Handler agar ada fungsi saat di click
        googleSignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case 101:
                    try {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    } catch (ApiException e) {
                        Log.w(TAG, "signInResult:failed code="+e.getStatusCode());
                    }
                    break;
            }
    }

    // Ketika login menggunakan akun google berhasil maka akan berpindah ke MainActivity.java
    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.GOOGLE_ACCOUNT, googleSignInAccount);

        intent.putExtra(LainnyaFragment.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);

    }



    //cek apakah sudah pernah login dan belum logout, jika sudah, ketika aplikasi dibuka maka akan langsung ke mainActivity
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null){
            Toast.makeText(this, "Selamat Datang Kembali",Toast.LENGTH_SHORT).show();
            onLoggedIn(alreadyloggedAccount);
        }
        else {
            Log.d(TAG, "Not Logged In");
        }
    }

    // Pindah ke class Login_email.java
    public void masukEmail(View view) {
        Intent i = new Intent(Login.this, Login_email.class);
        startActivity(i);
    }
    /**
     * Pindah ke Login via API
     **/
    public void masukAPI(View view) {
        Intent i = new Intent(Login.this, Login_API.class);
        startActivity(i);
    }

}

package com.example.template_dpr_now.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template_dpr_now.Login;
import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.Pengaturan;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Util.SharedPrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;
import static android.widget.Toast.*;
import static com.example.template_dpr_now.Util.SharedPrefManager.SP_SUDAH_LOGIN;

public class LainnyaFragment extends Fragment {

    // Deklarasi Variable
    public static final String GOOGLE_ACCOUNT = "google_account";
    private GoogleSignInClient googleSignInClient;
    private TextView profileName;
    private TextView profileEmail;
    private ImageView profileImage;
    private TextView pengaturan;
    private TextView info;
    private TextView signOut;
    SharedPrefManager sharedPrefManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lainnya, container, false);

        // Memberikan nilai
        profileName = view.findViewById(R.id.profile_text);
        profileEmail = view.findViewById(R.id.profile_email);
        profileImage = view.findViewById(R.id.profile_image);
        pengaturan = view.findViewById(R.id.pengaturan);
        info = view.findViewById(R.id.info);
        signOut = view.findViewById(R.id.signOut);
        setDataOnView();

        sharedPrefManager = new SharedPrefManager(getContext());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        // Memberikan Handler agar bisa di click
        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah dari class ini ke Pengaturan.java
                Intent intent = new Intent(getActivity(), Pengaturan.class);
                startActivity(intent);
            }
        });

        // Memberikan Handler agar bisa di click
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah dari class ini ke MainActivity.java
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        // Memberikan Handler agar bisa di click
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logout dari akun Firebase Authentication dan pindah ke Login.java
                FirebaseAuth.getInstance().signOut();
//             //Logout dari akun API dan pindah ke Login.java
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                Intent intent = new Intent(getActivity(), Login.class);
                getActivity().finish();
                startActivity(intent);

                // Logout dari akun google SignIn dan pindah ke Login.java
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getActivity(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                });

            }
        });

        return view;
    }

    // Sinkronisasi akun google agar profile di akun DPR Now! terisi otomatis sesuai dengan akun google
    private void setDataOnView(){

        GoogleSignInAccount googleSignInAccount = getActivity().getIntent().getParcelableExtra(GOOGLE_ACCOUNT);

        if (googleSignInAccount != null){
            Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
            profileName.setText(googleSignInAccount.getDisplayName());
            profileEmail.setText(googleSignInAccount.getEmail());
        }

        else {
            Log.d(TAG, "Debug Mode");
        }

    }
}

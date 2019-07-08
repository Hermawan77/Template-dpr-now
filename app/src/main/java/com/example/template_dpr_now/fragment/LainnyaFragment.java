package com.example.template_dpr_now.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template_dpr_now.Login;
import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;

public class LainnyaFragment extends Fragment {

    public static final String GOOGLE_ACCOUNT = "google_account";

    private GoogleSignInClient googleSignInClient;

    private TextView profileName;
    private TextView profileEmail;
    private ImageView profileImage;
    private TextView signOut;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lainnya, container, false);
        profileName = view.findViewById(R.id.profile_text);
        profileEmail = view.findViewById(R.id.profile_email);
        profileImage = view.findViewById(R.id.profile_image);
        signOut = view.findViewById(R.id.signOut);
        setDataOnView();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

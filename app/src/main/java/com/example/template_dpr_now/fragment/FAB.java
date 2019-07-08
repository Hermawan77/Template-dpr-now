package com.example.template_dpr_now.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.Model.PostPutDelPengaduan;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Rest.API_Client;
import com.example.template_dpr_now.Rest.API_Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class FAB extends Fragment {
    AutoCompleteTextView edit_nama, edit_email;
    EditText edit_nomor, edit_aduan;
    Button btsimpan;
    API_Interface mApiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fab, container, false);
        edit_nama = view.findViewById(R.id.autoCompleteTextView);
        edit_nomor = view.findViewById(R.id.Phone);
        edit_email = view.findViewById(R.id.Email);
        edit_aduan = view.findViewById(R.id.aduan);
        btsimpan = view.findViewById(R.id.btnsimpan);
        mApiInterface = API_Client.getClient().create(API_Interface.class);

         btsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelPengaduan> postKontakCall = mApiInterface.postKontak(edit_nama.getText().toString(), edit_email.getText().toString(), edit_nomor.getText().toString(),edit_aduan.getText().toString());
                postKontakCall.enqueue(new Callback<PostPutDelPengaduan>() {
                    @Override
                    public void onResponse(Call<PostPutDelPengaduan> call, Response<PostPutDelPengaduan> response) {
//                        MainActivity.ma.refresh();
//                        finish();

                        Log.d(TAG,"is sukses : "+response.isSuccessful());
                        Log.d(TAG,"pesan : "+response.message());
                        Log.d(TAG,"Bodi error : "+response.errorBody());
                        Log.d(TAG,"kasar : "+response.raw());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPengaduan> call, Throwable t) {
                        //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                       Log.d(TAG+call.request().url(),t.getMessage());
                    }
                });
            }
        });

//        btBack = view.findViewById(R.id.btBackGo);
//        btBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.ma.refresh();
//                finish();
//            }
//        });

        return  view;

    }
}
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.template_dpr_now.InputAspirasi;
import com.example.template_dpr_now.Model.PostPutDelPengaduan;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Rest.API_Client;
import com.example.template_dpr_now.Rest.API_Interface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class FAB extends Fragment {
    AutoCompleteTextView edit_nama, edit_email;
    EditText edit_nomor, edit_aduan;
    Button btsimpan, btsql;
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

        btsql = view.findViewById(R.id.btnsql);

        btsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InputAspirasi.class);
                startActivity(intent);
            }
        });

         btsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> hashMap=new HashMap<>();
                hashMap.put("nama",edit_nama.getText().toString());
                hashMap.put("email", edit_email.getText().toString());
                hashMap.put("no_telepon",edit_nomor.getText().toString());
                hashMap.put("isi_aduan",edit_aduan.getText().toString());

                Call<PostPutDelPengaduan> postPengaduanCall = mApiInterface.postPengaduan(hashMap);
                postPengaduanCall.enqueue(new Callback<PostPutDelPengaduan>() {
                    @Override
                    public void onResponse(Call<PostPutDelPengaduan> call, Response<PostPutDelPengaduan> response) {

                        Log.d(TAG,"Sukses ? : "+response.isSuccessful());
                        Log.d(TAG,"Body Error : "+response.errorBody());
                        Log.d(TAG,"Pesan : "+response.raw());

                    }

                    @Override
                    public void onFailure(Call<PostPutDelPengaduan> call, Throwable t) {
                        //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                       Log.d(TAG+call.request().url(),t.getMessage());
                    }
                });
            }
        });

        return  view;

    }
}
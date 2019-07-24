package com.example.template_dpr_now;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.template_dpr_now.Model.PostPutDelAkun;
import com.example.template_dpr_now.Rest.API_Client;
import com.example.template_dpr_now.Rest.API_Interface;
import com.example.template_dpr_now.Util.SharedPrefManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.support.constraint.Constraints.TAG;

public class Login_API extends AppCompatActivity {

    EditText edit_nama,edit_password;
    Button btsimpan;
    Context mContext;
    API_Interface api;
    SharedPrefManager sharedPrefManager;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_api);
        //getSupportActionBar().hide();

        edit_nama = findViewById(R.id.edemail);
        edit_password = findViewById(R.id.edpass);
        btsimpan = findViewById(R.id.btnlogin);
//        ButterKnife.bind(this);
        mContext = this;
        // meng-init yang ada di package apihelper
        api = API_Client.getClient().create(API_Interface.class);// meng-init yang ada di package REST
        sharedPrefManager = new SharedPrefManager(this);

        btsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }

        });

        // Code berikut berfungsi untuk mengecek session, Jika session true ( sudah login )
        // maka langsung memulai MainActivity.
        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(Login_API.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void requestLogin() {
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("nama",edit_nama.getText().toString());
        hashMap.put("password", edit_password.getText().toString());

        Call<PostPutDelAkun> postAkunCall = api.postAkunn(hashMap);
        postAkunCall.enqueue(new Callback<PostPutDelAkun>() {
            @Override
            public void onResponse(Call<PostPutDelAkun> call, Response<PostPutDelAkun> response) {
                if (response.isSuccessful()){
                    Log.d(TAG,"Sukses ? : "+response.isSuccessful());
                    Log.d(TAG,"Body Error : "+response.errorBody());
                    Log.d(TAG,"Pesan : "+response.raw());

                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().toString());
                        if (jsonRESULTS.getString("error").equals("false")){
                            // Jika login berhasil maka data nama yang ada di response API
                            // akan diparsing ke activity selanjutnya.
                            Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                            String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                            // Shared Pref ini berfungsi untuk menjadi trigger session login
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            startActivity(new Intent(mContext, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        } else {
                            // Jika login gagal
                            String error_message = jsonRESULTS.getString("error_msg");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG,"ada error?: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PostPutDelAkun> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());

            }
        });

    }
}

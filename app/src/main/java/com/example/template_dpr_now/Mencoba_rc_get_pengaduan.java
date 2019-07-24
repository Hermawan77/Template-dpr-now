package com.example.template_dpr_now;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.template_dpr_now.Adapter.PengaduanAdapter;
import com.example.template_dpr_now.Model.GetPengaduan;
import com.example.template_dpr_now.Model.Pengaduan;
import com.example.template_dpr_now.Rest.API_Client;
import com.example.template_dpr_now.Rest.API_Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mencoba_rc_get_pengaduan extends AppCompatActivity {
    Button btIns;
    private ListView listView;
    private PengaduanAdapter retroAdapter;

    API_Interface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static Mencoba_rc_get_pengaduan ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mencoba_rc_get_pengaduan);

        mRecyclerView = (RecyclerView) findViewById(R.id.rcmencoba);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = API_Client.getClient().create(API_Interface.class);
        ma=this;
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter( mAdapter );
        Call<GetPengaduan> PengaduanCall = mApiInterface.getPengaduan();
        PengaduanCall.enqueue(new Callback<GetPengaduan>() {
            @Override
            public void onResponse(Call<GetPengaduan> call, Response<GetPengaduan>
                    response) {
                List<Pengaduan> PengaduanList = response.body().getListDataPengaduan();
                Log.d("Retrofit Get", "Jumlah data Pengaduan: " +
                        String.valueOf(PengaduanList.size()));
                mAdapter = new PengaduanAdapter((ArrayList<Pengaduan>) PengaduanList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPengaduan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
        //refresh();


    public void refresh() {
        Call<GetPengaduan> PengaduanCall = mApiInterface.getPengaduan();
        PengaduanCall.enqueue(new Callback<GetPengaduan>() {
            @Override
            public void onResponse(Call<GetPengaduan> call, Response<GetPengaduan>
                    response) {
                List<Pengaduan> PengaduanList = response.body().getListDataPengaduan();
                Log.d("Retrofit Get", "Jumlah data Pengaduan: " +
                        String.valueOf(PengaduanList.size()));
                mAdapter = new PengaduanAdapter((ArrayList<Pengaduan>) PengaduanList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPengaduan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
package com.example.template_dpr_now.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.template_dpr_now.Pengaduan_Activity.PengaduanAdapter;
import com.example.template_dpr_now.Pengaduan_Activity.Pengaduan;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Rest.API_Client;
import com.example.template_dpr_now.Rest.API_Interface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPengaduan extends Fragment {
    API_Interface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PengaduanAdapter mPengaduan_Adapter;
    public static FragmentPengaduan ma;
    private ArrayList<KomisiItem> mPengaduan_Item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);
        mRecyclerView = view.findViewById(R.id.rcFragmentPengaduan);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = API_Client.getClient().create(API_Interface.class);
        mRecyclerView.setHasFixedSize(true);
        mPengaduan_Item = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //ma.getActivity();
        ma = this;
//
//        Type collectionType = new TypeToken<Collection<Pengaduan>>(){}.getType();
//        Collection<Pengaduan> enums = gson.fromJson(yourJson, collectionType);
        Call<List<Pengaduan>> mCall;
        mCall = mApiInterface.ambilPengaduan();
       // Call<Pengaduan> pengaduanCall = mApiInterface.ambilPengaduan();
       mCall.enqueue(new Callback<List<Pengaduan>>() {
            @Override
            public void onResponse(Call<List<Pengaduan>> call, Response<List<Pengaduan>>
                    response) {
                List<Pengaduan> pengaduanList = response.body();
                Log.d("Retrofit Get", response.toString());
//                Log.d("Retrofit Get", "Jumlah data Pengaduan: " +
//                        String.valueOf(pengaduanList.size()));
                mAdapter = new PengaduanAdapter(pengaduanList);
               // mPengaduan_Adapter = new PengaduanAdapter(getActivity(), mPengaduan_Item);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<Pengaduan>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

        //getActivity();
        //refresh();
        return view;
    }
//    public void refresh() {
//        Call<Pengaduan> PengaduanCall = mApiInterface.Pengaduan();
//        PengaduanCall.enqueue(new Callback<Pengaduan>() {
//            @Override
//            public void onResponse(Call<Pengaduan> call, Response<Pengaduan>
//                    response) {
//                List<Pengaduan> PengaduanList = response.body().getListDataPengaduan();
//                Log.d("Retrofit Get", "Jumlah data Pengaduan: " +
//                        String.valueOf(PengaduanList.size()));
//
//                mPengaduan_Adapter = new PengaduanAdapter(getActivity(), mPengaduan_Item);
//                //mAdapter = new PengaduanAdapter(PengaduanList);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<Pengaduan> call, Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
//    }

}

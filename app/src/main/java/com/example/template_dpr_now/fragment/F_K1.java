package com.example.template_dpr_now.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.example.template_dpr_now.Item_K1;
import com.example.template_dpr_now.K1_Adapter;
import com.example.template_dpr_now.R;

import java.util.ArrayList;

public class F_K1 extends Fragment {

    private RecyclerView mRecyclerView;
    private K1_Adapter mK1_Adapter;
    private ArrayList<Item_K1> mK1_List;
    private RequestQueue mRequestQueue;
    static String respon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_k1, container, false);

        mRecyclerView = view.findViewById(R.id.re_k1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mK1_List = new ArrayList<>();
        mK1_Adapter = new K1_Adapter(getActivity(), mK1_List);
        mRecyclerView.setAdapter(mK1_Adapter);

        mRequestQueue = Volley.newRequestQueue(getContext());

        testReq();
        return view;
        //parseJSON();

    }

    private void testReq() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="http://dpr.go.id/rest/?method=getAkd&menu=Sekretariat-Komisi-I&tipe=xml";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is: "+ response);
                        respon = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

        queue.add(stringRequest);
    }

    private void parseJSON() {
        String url = "http://dpr.go.id/rest/?method=getAkd&menu=Sekretariat-Komisi-I&tipe=json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("content");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);


                                String nama = content.getString("nama");
                                String foto = content.getString("foto");
                                String nip = content.getString("nip");
                                String jabatan = content.getString("jabatan");

                                foto = "http://dpr.go.id"+foto;

                                mK1_List.add(new Item_K1(foto, nama, nip, jabatan));
                            }

                            mK1_Adapter = new K1_Adapter(getActivity(), mK1_List);
                            mRecyclerView.setAdapter(mK1_Adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}

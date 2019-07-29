package com.example.template_dpr_now.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.XmlToJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class F_K2 extends Fragment {
    private RecyclerView mRecyclerview;
    private KomisiAdapter mF_K2_Adapter;
    private ArrayList<KomisiItem> mF_K2_Item;
    private RequestQueue mRequestQueue;

    private String BASE_URL = "http://dpr.go.id/rest/?method=getAkd&menu=Sekretariat-Komisi-II&tipe=xml";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_k2, container, false);

        mRecyclerview = view.findViewById(R.id.recycler_view_k2);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        mF_K2_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getContext());

        parseLink();

        return view;

    }

    private void parseLink() {
        //RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println("Respon = "+ response);

                        response = response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
                        response = response.substring(10);
                        response = response.substring(0, response.length()-11);

                        //System.out.println("Hasil = "+response);

                        XmlToJson xmlToJson = new XmlToJson.Builder(response).skipTag("/title").skipTag("/type").build();

                        //System.out.println("json = " + xmlToJson);

                        JSONObject jsonObject = xmlToJson.toJson();

                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("content");

                            for (int i= 0; i<jsonArray.length();i++){
                                JSONObject content = jsonArray.getJSONObject(i);

                                String foto = "http://dpr.go.id" + content.getString("foto");

                                System.out.println("link = " + foto);

                                String nama = content.getString("nama");
                                String nip = content.getString("nip");
                                String jabatan = content.getString("jabatan");

                                mF_K2_Item.add(new KomisiItem(foto, nama, nip, jabatan));
                            }

                            mF_K2_Adapter = new KomisiAdapter(getActivity(), mF_K2_Item);
                            mRecyclerview.setAdapter(mF_K2_Adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Gagal");
            }
        });

// Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);
    }

}

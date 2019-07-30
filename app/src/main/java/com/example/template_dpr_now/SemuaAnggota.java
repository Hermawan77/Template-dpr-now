package com.example.template_dpr_now;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.template_dpr_now.fragment.KomisiAdapter;
import com.example.template_dpr_now.fragment.KomisiItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SemuaAnggota extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private SemuaAnggotaAdapter mSemuaAnggota_Adapter;
    private ArrayList<SemuaAnggotaItem> mSemuaAnggota_Item;
    private RequestQueue mRequestQueue;

    private String BASE_URL = "http://www.dpr.go.id/rest/?method=getSemuaAnggota&tipe=xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semua_anggota);

        mRecyclerview = findViewById(R.id.recycler_view_semuaanggota);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mSemuaAnggota_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseLink();
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
                        response = response.substring(9, response.length()-81);

                        System.out.println("Hasil = "+response);

                        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();

                        //System.out.println("json = " + xmlToJson);

                        JSONObject jsonObject = xmlToJson.toJson();

                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("item");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);

                                String namaanggota = content.getString("nama");
                                String fraksi = content.getString("fraksi");
                                String dapil = content.getString("dapil");
                                String foto = "http://dpr.go.id" + content.getString("foto");

                                System.out.println("link = " + foto);

                                mSemuaAnggota_Item.add(new SemuaAnggotaItem(namaanggota, fraksi, dapil, foto));
                            }


                            mSemuaAnggota_Adapter = new SemuaAnggotaAdapter(SemuaAnggota.this, mSemuaAnggota_Item);
                            mRecyclerview.setAdapter(mSemuaAnggota_Adapter);

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

package com.example.template_dpr_now.Pelayananmasyarakat_Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.template_dpr_now.Library_XmlToJson;
import com.example.template_dpr_now.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PelayananmasyakatActivity extends AppCompatActivity {
    // Deklarasi Variabel
    private RecyclerView mRecyclerview;
    private PelayananmasyakatAdapter mKunjunganYanmas_Adapter;
    private ArrayList<PelayananmasyakatItem> mKunjungaYanmas_Item;
    private RequestQueue mRequestQueue;
    private String BASE_URL = "http://www.dpr.go.id/rest/?method=getAllAlbumYanmas&hal=n&tipe=xml";


    // Menampilkan pelayanan_masyarakat_layout.xmlml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pelayanan_masyarakat_layout);

        mRecyclerview = findViewById(R.id.recycler_view_kunjungan);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mKunjungaYanmas_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseLink();
    }


    // Proses parsing XML
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

                        System.out.println("Hasil = "+response);

                        // XML to JSON
                        Library_XmlToJson libraryXmlToJson = new Library_XmlToJson.Builder(response).build();

                        //System.out.println("json = " + libraryXmlToJson);

                        JSONObject jsonObject = libraryXmlToJson.toJson();

                        // GET <album>
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("album");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);

                                // GET <image>, <tanggal>, <judul> dari <album>
                                String tanggal = content.getString("tanggal");
                                String judul = content.getString("judul");
                                String image = "http://dpr.go.id" + content.getString("file_name");


                                System.out.println("link = " + image);

                                // Mengisi Item dengan hasil parsing berupa image, tanggal, judul
                                mKunjungaYanmas_Item.add(new PelayananmasyakatItem(image, tanggal, judul));

                            }

                            // Menampilkan hasil parsing ke activity
                            mKunjunganYanmas_Adapter = new PelayananmasyakatAdapter(PelayananmasyakatActivity.this, mKunjungaYanmas_Item);
                            mRecyclerview.setAdapter(mKunjunganYanmas_Adapter);

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

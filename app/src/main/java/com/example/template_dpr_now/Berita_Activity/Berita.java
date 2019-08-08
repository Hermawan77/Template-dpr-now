package com.example.template_dpr_now.Berita_Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;

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

public class Berita extends AppCompatActivity implements Berita_Adapter.OnItemClickListener{
    public static final String EXTRA_TANGGAL = "tanggal";
    public static final String EXTRA_KATEGORI = "kategori";
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_ISI = "isi";


    private RecyclerView mRecyclerview;
    private Berita_Adapter mBerita_Adapter;
    private ArrayList<Berita_Item> mBerita_Item;
    private RequestQueue mRequestQueue;
    Toolbar toolbarberita;


    private String BASE_URL = "http://dpr.go.id/rest/?method=get5BeritaFoto&tipe=xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        mRecyclerview = findViewById(R.id.recycler_view_berita);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mBerita_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        toolbarberita = findViewById(R.id.toolbarberita);

        parseLink();
    }

    private void parseLink() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println("Respon = "+ response);

                        response = response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
                        response = response.substring(10);
                        response = response.substring(0, response.length()-11);

                        XmlToJson xmlToJson = new XmlToJson.Builder(response).skipTag("/title").skipTag("/type").build();

                        JSONObject jsonObject = xmlToJson.toJson();

                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("berita");

                            for (int i= 0; i<jsonArray.length();i++){
                                JSONObject content = jsonArray.getJSONObject(i);

                                String tanggal = content.getString("tanggal");
                                String kategori = content.getString("kategori");
                                String foto =content.getString("gambar");
                                foto = foto.substring(1, foto.length()-1);
                                foto = "http://dpr.go.id" + foto;

                                String judul = content.getString("title");
                                String isi = content.getString("isi");

                                isi = Html.fromHtml(isi).toString().trim();

                                //isi = isi.replaceAll("\\<p(.+?)p\\>", "");
                                //isi = isi.replaceAll("\\<h4(.+?)h4\\>", "").trim();

                                System.out.println("isinya = " + isi);

                                mBerita_Item.add(new Berita_Item(tanggal, kategori, foto, judul, isi));
                            }

                            mBerita_Adapter = new Berita_Adapter(Berita.this, mBerita_Item);
                            mRecyclerview.setAdapter(mBerita_Adapter);
                            mBerita_Adapter.setOnItemClickListener(Berita.this);

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


    @Override
    public void onItemClick(int position) {
        Intent intentDetail = new Intent(this, Berita_Detail.class);
        Berita_Item clickedItem = mBerita_Item.get(position);

        intentDetail.putExtra(EXTRA_TANGGAL, clickedItem.getTanggal());
        intentDetail.putExtra(EXTRA_KATEGORI, clickedItem.getKategori());
        intentDetail.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        intentDetail.putExtra(EXTRA_JUDUL, clickedItem.getJudul());
        intentDetail.putExtra(EXTRA_ISI, clickedItem.getIsi());

        startActivity(intentDetail);
    }
}

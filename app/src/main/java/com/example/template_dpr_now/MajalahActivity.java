package com.example.template_dpr_now;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MajalahActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private MajalahAdapter mMajalah_Adapter;
    private ArrayList<MajalahItem> mMajalah_Item;
    private RequestQueue mRequestQueue;

    private String BASE_URL = "http://www.dpr.go.id/rest/?method=getAllMajalah&tahun=2015&tipe=xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majalah);

        mRecyclerview = findViewById(R.id.recycler_view_majalah);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mMajalah_Item = new ArrayList<>();

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
                        response = response.substring(0, response.length()-11);

                        System.out.println("Hasil = "+response);

                        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();

                        //System.out.println("json = " + xmlToJson);

                        JSONObject jsonObject = xmlToJson.toJson();

                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("majalah");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);

                                String edisi = content.getString("edisi");
                                String tahun = content.getString("tahun");
                                String file = "http://dpr.go.id" + content.getString("file");
                                String image = "http://dpr.go.id" + content.getString("gambar");


                                System.out.println("link = " + image);

                                mMajalah_Item.add(new MajalahItem(image, tahun, edisi, file));

                            }


                            mMajalah_Adapter = new MajalahAdapter(MajalahActivity.this, mMajalah_Item);
                            mRecyclerview.setAdapter(mMajalah_Adapter);

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

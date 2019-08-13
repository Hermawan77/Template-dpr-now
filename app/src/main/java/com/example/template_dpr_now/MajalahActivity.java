package com.example.template_dpr_now;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

public class MajalahActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Deklarasi Variabel
    private RecyclerView mRecyclerview;
    private MajalahAdapter mMajalah_Adapter;
    private ArrayList<MajalahItem> mMajalah_Item;
    private RequestQueue mRequestQueue;
    String text;


    // Menampilkan fragment activity_majalah.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majalah);

        mRecyclerview = findViewById(R.id.recycler_view_majalah);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mMajalah_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        //parseLink();

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tahun, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    // GET URL sesuai dengan tahun yang terpilih dari Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        String BASE_URL = "http://www.dpr.go.id/rest/?method=getAllMajalah&tahun=" + text + "&tipe=xml";
        parseLink(BASE_URL);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    // Proses parsing XML
    private void parseLink(String BASE_URL) {
        //RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println("Respon = "+ response);
                        mMajalah_Item.clear();

                        response = response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
                        response = response.substring(10);
                        response = response.substring(0, response.length()-11);

                        System.out.println("Hasil = "+response);

                        // XML to JSON
                        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();

                        //System.out.println("json = " + xmlToJson);

                        JSONObject jsonObject = xmlToJson.toJson();

                        // GET <majalah>
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("majalah");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);

                                // GET <edisi>, <tahun>, <file>, <gambar> dari <album>
                                String edisi = content.getString("edisi");
                                String tahun = content.getString("tahun");
                                String file = "http://dpr.go.id" + content.getString("file");
                                String image = "http://dpr.go.id" + content.getString("gambar");


                                System.out.println("link = " + image);

                                // Mengisi mKomisi_Item dengan hasil parsing berupa image, tahun, edisi, file
                                mMajalah_Item.add(new MajalahItem(image, tahun, edisi, file));

                            }

                            // Menampilkan hasil parsing ke activity
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

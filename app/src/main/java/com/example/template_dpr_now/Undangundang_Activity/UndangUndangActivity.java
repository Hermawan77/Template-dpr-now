package com.example.template_dpr_now.Undangundang_Activity;

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
import com.example.template_dpr_now.Library_XmlToJson;
import com.example.template_dpr_now.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UndangUndangActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    // Deklarasi Variabel
    private RecyclerView mRecyclerview;
    private UndangUndangAdapter mUndangUndang_Adapter;
    private ArrayList<UndangUndangItem> mUndangUndang_Item;
    private RequestQueue mRequestQueue;
    String text;


    // Menampilkan activity_undang_undang.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_undang_undang_layout);

        mRecyclerview = findViewById(R.id.recycler_view_uu);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mUndangUndang_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tahunuu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    // GET URL sesuai dengan tahun yang terpilih dari Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        String BASE_URL = "http://www.dpr.go.id/rest/?method=getAllUu&tahun=" + text + "&tipe=xml";
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
                        mUndangUndang_Item.clear();

                        response = response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
                        response = response.substring(10);
                        response = response.substring(0, response.length()-11);

                        System.out.println("Hasil = "+response);

                        // XML to JSON
                        Library_XmlToJson libraryXmlToJson = new Library_XmlToJson.Builder(response).build();

                        //System.out.println("json = " + libraryXmlToJson);

                        JSONObject jsonObject = libraryXmlToJson.toJson();

                        // GET <uu>
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("uu");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);

                                // GET <nomor>, <tentang>, <file> dari <uu>
                                String nomor = content.getString("nomor");
                                String tentang = content.getString("tentang");
                                String file = "http://dpr.go.id" + content.getString("file");

                                // Mengisi Item dengan hasil parsing berupa nomor, tentang, file
                                mUndangUndang_Item.add(new UndangUndangItem(nomor, tentang, file));
                            }

                            // Menampilkan hasil parsing ke activity
                            mUndangUndang_Adapter = new UndangUndangAdapter(UndangUndangActivity.this, mUndangUndang_Item);
                            mRecyclerview.setAdapter(mUndangUndang_Adapter);

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

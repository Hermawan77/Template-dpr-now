package com.example.template_dpr_now.Semuaanggota_Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

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

public class SemuaAnggota extends AppCompatActivity {
    // Deklarasi Variabel
    boolean count = false;
    private RecyclerView mRecyclerview;
    private SemuaAnggotaAdapter mSemuaAnggota_Adapter;
    private ArrayList<SemuaAnggotaItem> mSemuaAnggota_Item;
    private RequestQueue mRequestQueue;
    private String BASE_URL = "http://www.dpr.go.id/rest/?method=getSemuaAnggota&tipe=xml";


    // Menampilkan daftar_semua_anggota_layout.xmlt.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_semua_anggota_layout);

        mRecyclerview = findViewById(R.id.recycler_view_semuaanggota);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mSemuaAnggota_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        SearchView searchView = findViewById(R.id.searchanggota);
        search(searchView);
        parseLink();
    }


    // Proses parsing XML
    private void parseLink() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println("Respon = "+ response);

                        mSemuaAnggota_Item.clear();

                        response = response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
                        //response = response.substring(10);
                        response = response.substring(19, response.length()-81);

                        //System.out.println("Hasil = "+response);

                        // XML to JSON
                        Library_XmlToJson libraryXmlToJson = new Library_XmlToJson.Builder(response).build();

                        //System.out.println("json = " + libraryXmlToJson);

                        JSONObject jsonObject = libraryXmlToJson.toJson();

                        // GET <item>
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("item");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);

                                // GET <nama>, <fraksi>, <dapil>, <foto> dari <item>
                                String namaanggota = content.getString("nama");
                                String fraksi = content.getString("fraksi");
                                String dapil = content.getString("dapil");
                                String foto = "http://dpr.go.id" + content.getString("foto");
                                //String daftarakd = content.getString("daftar_akd");

                                // GET <daftar_akd>
                                String daftarakd = "";

                                JSONObject daftar_akd = content.getJSONObject("daftar_akd");
                                //System.out.println("list = " + daftar_akd);

                                String isi[] = new String[5];
                                for (int j=0; j<daftar_akd.length();j++){
                                    isi[j] = daftar_akd.getString("akd").replace("content","").replaceAll("[^a-zA-Z\\s\\,]","");
                                    //System.out.println("isi = " + isi[j]);
                                    isi[j] = isi[j].replace(",","\n");
                                    daftarakd = daftarakd + isi[j];
                                }

                                // Mengisi Item dengan hasil parsing berupa namaanggota, fraksi, dapil, foto, daftarakd
                                mSemuaAnggota_Item.add(new SemuaAnggotaItem(namaanggota, fraksi, dapil, foto, daftarakd));

                                //mSemuaAnggota_Adapter.notifyDataSetChanged();

                            }

                            // Menampilkan hasil parsing ke activity
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

        mRequestQueue.add(stringRequest);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_anggota, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }*/

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!count){
                    mSemuaAnggota_Adapter.getFilter().filter(s);
                    count = true;
                }
                else {
                    recreate();
                    count = false;
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //mSemuaAnggota_Adapter.getFilter().filter(s);
                return true;
            }
        });
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/
}
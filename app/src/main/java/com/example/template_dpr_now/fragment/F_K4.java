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
import com.example.template_dpr_now.Library_XmlToJson;
import com.example.template_dpr_now.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class F_K4 extends Fragment {
    // Deklarasi Variabel
    private RecyclerView mRecyclerview;
    private KomisiAdapter mF_K4_Adapter;
    private ArrayList<KomisiItem> mF_K4_Item;
    private RequestQueue mRequestQueue;
    private String BASE_URL = "http://dpr.go.id/rest/?method=getAkd&menu=Sekretariat-Komisi-IV&tipe=xml";


    // Menampilkan fragment komisi4_fragment.xmlfragment.xml
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.komisi4_fragment, container, false);

        mRecyclerview = view.findViewById(R.id.recycler_view_k4);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        mF_K4_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getContext());

        parseLink();

        return view;

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

                        //System.out.println("Hasil = "+response);

                        // XML to JSON
                        Library_XmlToJson libraryXmlToJson = new Library_XmlToJson.Builder(response).skipTag("/title").skipTag("/type").build();

                        //System.out.println("json = " + libraryXmlToJson);

                        JSONObject jsonObject = libraryXmlToJson.toJson();

                        // GET <content>
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("content");

                            for (int i= 0; i<jsonArray.length();i++){
                                JSONObject content = jsonArray.getJSONObject(i);

                                // GET <foto>, <nama>, <nip>, <jabatan> dari <content>
                                String foto = "http://dpr.go.id" + content.getString("foto");

                                System.out.println("link = " + foto);

                                String nama = content.getString("nama");
                                String nip = content.getString("nip");
                                String jabatan = content.getString("jabatan");

                                // Mengisi mKomisi_Item dengan hasil parsing berupa foto, nama, nip, jabatan
                                mF_K4_Item.add(new KomisiItem(foto, nama, nip, jabatan));
                            }

                            // Menampilkan hasil parsing ke fragment
                            mF_K4_Adapter = new KomisiAdapter(getActivity(), mF_K4_Item);
                            mRecyclerview.setAdapter(mF_K4_Adapter);

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
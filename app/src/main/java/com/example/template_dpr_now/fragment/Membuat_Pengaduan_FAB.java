/*
Class ini digunakan untuk memasukkan data ke database pengaduan, database pengaduan berjalan pada server lokal
 */

package com.example.template_dpr_now.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.template_dpr_now.Library_XmlToJson;
import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.Pengaduan_Activity.PostPutDelPengaduan;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Rest.API_Client;
import com.example.template_dpr_now.Rest.API_Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;

public class Membuat_Pengaduan_FAB extends Fragment {
    private static final int PICK_IMAGE_REQUEST=1;
    private RequestQueue mRequestQueue;
    List<String> responseList = new ArrayList<String>();
    private String BASE_URL = "http://www.dpr.go.id/rest/?method=getSemuaAnggota&tipe=xml";

    AutoCompleteTextView edit_nama, edit_email;
    EditText edit_nomor, edit_aduan;
    Button btsimpan, pdf, image;
    API_Interface mApiInterface;
    TextView txtpdf, txtimage;
    Uri pdfUri, imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fab, container, false);
        edit_nama = view.findViewById(R.id.autoCompleteTextView);
        edit_nomor = view.findViewById(R.id.Phone);
        edit_email = view.findViewById(R.id.Email);
        edit_aduan = view.findViewById(R.id.aduan);
        btsimpan = view.findViewById(R.id.btnsimpan);

        mApiInterface = API_Client.getClient().create(API_Interface.class); // meng-init yang ada di package REST

        //post data ke database lokal (REST API) menggunakan Retrofit
         btsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> hashMap=new HashMap<>();
                hashMap.put("nama",edit_nama.getText().toString());
                hashMap.put("email", edit_email.getText().toString());
                hashMap.put("no_telepon",edit_nomor.getText().toString());
                hashMap.put("isi_aduan",edit_aduan.getText().toString());

                Call<PostPutDelPengaduan> postPengaduanCall = mApiInterface.postPengaduan(hashMap);
                postPengaduanCall.enqueue(new Callback<PostPutDelPengaduan>() {
                    @Override
                    public void onResponse(Call<PostPutDelPengaduan> call, Response<PostPutDelPengaduan> response) {

                        Log.d(TAG,"Sukses ? : "+response.isSuccessful());
                        Log.d(TAG,"Body Error : "+response.errorBody());
                        Log.d(TAG,"Pesan : "+response.raw());
                    }
                    @Override
                    public void onFailure(Call<PostPutDelPengaduan> call, Throwable t) {

                       Log.d(TAG+call.request().url(),t.getMessage());
                    }
                });

                //membuat popup konfirmasi menggunakan alert dialog, R.style dapat diubah
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Data yang diisi sudah benar ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        ((Activity) getActivity()).overridePendingTransition(0, 0);

                    }
                });
                builder.setNegativeButton("No", null);
                builder.create().show();
            }
        });

        //autocomplete, mengambil nama dari JSON kemudian diletakan pada Arraylist
        mRequestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
                        response = response.substring(19, response.length()-81);
                        Library_XmlToJson libraryXmlToJson = new Library_XmlToJson.Builder(response).build();
                        JSONObject jsonObject = libraryXmlToJson.toJson();

                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("item");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject content = jsonArray.getJSONObject(i);
                                String namaanggota = content.getString("nama");
                                responseList.add(namaanggota);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Gagal");
            }
        });

        mRequestQueue.add(stringRequest);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.autocomplete_list_item, R.id.text_view_list_item, responseList);
        edit_nama.setAdapter(adapter);

        return  view;

    }

    //memilih PDF dan Image
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else if(requestCode==1&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectImage();
        }
    }

    private void selectPdf(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri=data.getData();
            txtpdf.setText("A file is selected : "+ data.getData().getLastPathSegment());
        }
        else if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            imageUri=data.getData();
            txtimage.setText("A file is selected : "+ data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(getContext(), "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }
}
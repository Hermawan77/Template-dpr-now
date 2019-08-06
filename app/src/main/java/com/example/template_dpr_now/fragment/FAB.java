package com.example.template_dpr_now.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template_dpr_now.InputAspirasi;
import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.Model.PostPutDelPengaduan;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.Rest.API_Client;
import com.example.template_dpr_now.Rest.API_Interface;
import com.example.template_dpr_now.Theme;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;

public class FAB extends Fragment {
    private static final int PICK_IMAGE_REQUEST=1;

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
        pdf = view.findViewById(R.id.pickpdf);
        image = view.findViewById(R.id.pickimage);
        txtpdf = view.findViewById(R.id.textpdf);
        txtimage = view.findViewById(R.id.textimage);

        mApiInterface = API_Client.getClient().create(API_Interface.class); // meng-init yang ada di package REST

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
                        //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                       Log.d(TAG+call.request().url(),t.getMessage());
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Widget_AppCompat_Button_ButtonBar_AlertDialog);
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

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectPdf();

                }
                else{
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectImage();

                }
                else{
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }

            }
        });

        return  view;

    }

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
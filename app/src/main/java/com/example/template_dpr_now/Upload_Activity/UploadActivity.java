package com.example.template_dpr_now.Upload_Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.template_dpr_now.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity {

    // Mendeklarasikan Variable
    EditText uploadname;
    Button uploadfile;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Menampilkan images_upload_layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_upload_layout);

        // Memberi nilai
        uploadname = (EditText) findViewById(R.id.txt_pdfName);
        uploadfile = (Button) findViewById(R.id.btn_upload);

        // Memberi nilai Firebase Storage dan Database lalu memberi nilai tempat untuk menyimpan data
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        // Memberi Handler agar berfungsi ketika tombol di tekan
        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDFFile();
            }
        });
    }

    // Method yang berfungsi menampilkan file berformat pdf dalam device pengguna
    public void selectPDFFile(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"),1);
    }

    // Method untuk load file pdf
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null &&data.getData()!=null){
            uploadPDFFile(data.getData());
        }
    }

    // Mengupload file pdf
    private void uploadPDFFile(Uri data){
        // Mendeklarasikan Variable
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading . . .");
        progressDialog.show();

        // Mendeklarasikan tempat upload pdf file dan memberikan nama file sesuai waktu saat ini
        StorageReference reference =  storageReference.child("uploads/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // Ketika berhasil maka akan mengambil nama file dan url untuk membuka file
                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while(!uri.isComplete());
                Uri url = uri.getResult();

                UploadPDF UploadPDF = new UploadPDF(uploadname.getText().toString(),url.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(UploadPDF);
                Toast.makeText(UploadActivity.this, "File Upload", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                // Menampilkan progress Dialog
                double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Upload : "+(int)progress+"%");
            }
        });
    }


    public void btn_lihat(View view) {

        // Berpindah ke UploadPDF_view_Files.java
        startActivity(new Intent(getApplicationContext(), UploadPDF_view_Files.class));
    }
}
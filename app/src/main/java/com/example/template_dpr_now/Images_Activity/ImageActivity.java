package com.example.template_dpr_now.Images_Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template_dpr_now.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    // Mendeklarasikan Variable
    private static final int PICK_IMAGE_REQUEST=1;
    private Button pilih, upload;
    private TextView lihat;
    private EditText judul;
    private ImageView foto;
    private ProgressBar proses;
    private Uri mImageUri;
    private StorageTask mUploadTask;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Memanggil image_upload_layout.xmlt.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_upload_layout);

        // Memberikan nilai
        pilih = (Button) findViewById(R.id.btn_image);
        upload = (Button) findViewById(R.id.uploadimage);
        lihat = (TextView) findViewById(R.id.viewimage);
        judul = (EditText) findViewById(R.id.edit_text);
        foto = (ImageView) findViewById(R.id.imageview);
        proses = (ProgressBar) findViewById(R.id.progressbar);

        // Mendeklarasikan path/tempat dimana data akan disimpan
        mStorageRef = FirebaseStorage.getInstance().getReference("images");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("images");

        // Memberikan Handler agar ada fungsi saat di click
        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // Memberikan Handler agar ada fungsi saat di click
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan Toast bahwa data sedang di upload
                if(mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(ImageActivity.this, "Upload In Progress", Toast.LENGTH_SHORT).show();
                }

                uploadFile();
            }
        });

        // Memberikan Handler agar ada fungsi saat di click
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });
    }

    // Method yang berfungsi menampilkan file berformat image dalam device pengguna
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Method untuk load foto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(foto);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    // Method untuk mengupload foto
    private void uploadFile(){
        // Jika foto telah dipilih
        if (mImageUri != null)
        {
            // Maka upload foto di mStorageRef yang telah diberi nilai untuk disimpan di folder "images"
            mStorageRef.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return mStorageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    // Jika berhasil maka simpan foto di Firebase Storage
                    if (task.isSuccessful())
                    {
                        Uri downloadUri = task.getResult();
                        // Mengambil text judul dan mengambil Url dan menyimpannya di Firebase Database
                        ImagesUpload upload = new ImagesUpload(judul.getText().toString().trim(),
                                downloadUri.toString());

                        mDatabaseRef.push().setValue(upload);
                        // Menampilkan Toast
                        Toast.makeText(ImageActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                    }
                    // Jika gagal maka akan memunculkan toast
                    else
                    {
                        Toast.makeText(ImageActivity.this, "Upload gagal " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // Pindah ke ImagesActivity.java
    private void openImagesActivity(){
        Intent intent = new Intent(ImageActivity.this, ImagesActivity.class);
        startActivity(intent);
    }
}

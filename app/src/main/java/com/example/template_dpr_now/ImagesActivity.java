package com.example.template_dpr_now;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    // Mendeklarasikan Variable
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> mUploads;
    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Menampilkan activity_images.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        // Memberikan nilai
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ImagesActivity.this));
        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ImagesActivity.this);
        mProgressCircle.setVisibility(View.INVISIBLE);

        // Memberi nilai untuk Firebase Storage dan Database
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("images");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {

            // Upload foto
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    ImageUpload upload = postSnapshot.getValue(ImageUpload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                    mAdapter.notifyDataSetChanged();
                    mProgressCircle.setVisibility(View.INVISIBLE);

            }

            // Gagal upload
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    // Memunculkan toast saat ditekan beberapa detik
    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Normal click at position " + position, Toast.LENGTH_SHORT).show();
    }

    // Memunculkan toast saat ditekan beberapa detik
    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Normal click at position " + position, Toast.LENGTH_SHORT).show();
    }

    // Menghapus item/foto
    @Override
    public void onDeleteClick(int position) {
        ImageUpload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ImagesActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Menghapusnya dari Firebase Databse
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}

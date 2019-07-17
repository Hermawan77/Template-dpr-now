package com.example.template_dpr_now;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class View_PDF_Files extends AppCompatActivity {

    // Mendeklarasikan Variable
    ListView ListFile;
    DatabaseReference databaseReference;
    List<UploadPDF> uploadPDFS;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // Menampilkan activity_viewpdf.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdf);

        // Mendeklarasikan Variable
        ListFile = (ListView) findViewById(R.id.myListView);
        uploadPDFS = new ArrayList<>();

        viewAllFiles();

        // Memberi Handler agar berfungsi saat di click
        ListFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mengambil urutan file di Storage
                UploadPDF uploadPDF = uploadPDFS.get(position);

                // Berpindah dengan membuka file
                Intent intent = new Intent();
                intent.setData(Uri.parse(uploadPDF.getUrl()));
                startActivity(intent);
            }
        });

    }

    private void viewAllFiles(){

        // Memberi nilai pada Firebase Database dan menyimpannya pada folder "uploads"
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        // Membuka seluruh file berformat pdf yang ada pada Firebase Storeage dalam bentuk ListView
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadPDF uploadPDF = postSnapshot.getValue(com.example.template_dpr_now.UploadPDF.class);
                    uploadPDFS.add(uploadPDF);
                }

                // Mengurutkan sesuai waktu upload
                String[] uploads = new String[uploadPDFS.size()];
                for(int i=0;i<uploads.length;i++){
                    uploads[i] = uploadPDFS.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){

                    @Override
                    public View getView(int position,  View convertView,  ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView myText = (TextView) view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);

                        return view;
                    }
                };


                ListFile.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

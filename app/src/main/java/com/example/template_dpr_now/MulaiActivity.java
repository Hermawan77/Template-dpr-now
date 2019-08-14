package com.example.template_dpr_now;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MulaiActivity extends AppCompatActivity {

    // Mendeklarasikan Variable
    Button menu1, menu2, menu3;
    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Menampilkan slide_images_layout.xmlt.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_images_layout);

        // Memberi nilai
        menu1 = findViewById(R.id.menu1);
        menu2 = findViewById(R.id.menu2);
        menu3 = findViewById(R.id.menu3);

        int images[] = {R.drawable.foto1, R.drawable.foto2, R.drawable.foto3};

        v_flipper =findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImages(image);
        }

//        menu1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MulaiActivity.this, MulaiActivity.class);
//                MainActivity.this.startActivity(intent);
//                AspirasiInput.this.finish();
//            }
//        });
//
//        menu2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MulaiActivity.this, MulaiActivity.class);
//                MainActivity.this.startActivity(intent);
//                AspirasiInput.this.finish();
//            }
//        });
//
//        menu3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MulaiActivity.this, MulaiActivity.class);
//                MainActivity.this.startActivity(intent);
//                AspirasiInput.this.finish();
//            }
//        });
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

}
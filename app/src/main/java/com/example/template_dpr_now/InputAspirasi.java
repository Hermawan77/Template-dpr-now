package com.example.template_dpr_now;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class InputAspirasi extends AppCompatActivity implements View.OnClickListener {

    private static final String CHANNEL_ID = ".notificationDemo.channelId";
    private static final int PICK_IMAGE_REQUEST=1;
    private static final String[] temp = new String[]{
            "Arif", "Aan", "Bambang", "Budi", "Babeh", "Cece"};
    EditText text3,text4,text5,text6, textpdf, textimage;
    AutoCompleteTextView text1, text2;
    TextView txtTime,txtDate, Lihat, selectedpdf, selectedimage;
    Button Save, pdf, image;
    Spinner spinner;
    DatabaseManager mDatabase;
    Uri pdfUri, imageUri;
    private int  mHour, mMinute, mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        text1 = (AutoCompleteTextView) findViewById(R.id.actv);
        text2 =(AutoCompleteTextView) findViewById(R.id.emailview);
        text3 = (EditText) findViewById(R.id.phoneview);
        text4 = (EditText) findViewById(R.id.Date);
        text5 = (EditText) findViewById(R.id.Time);
        text6 = (EditText) findViewById(R.id.essai);
        textpdf = (EditText) findViewById(R.id.edittextpdf);
        textimage = (EditText) findViewById(R.id.edittextgambar);

        txtTime = (EditText) findViewById(R.id.Time);
        txtTime.setOnClickListener(this);
        txtDate = (EditText) findViewById(R.id.Date);
        txtDate.setOnClickListener(this);

        String[] test = getResources().getStringArray(R.array.Test);

        pdf = (Button) findViewById(R.id.selectpdf);
        image = (Button) findViewById(R.id.selectimage);
        spinner = (Spinner) findViewById(R.id.spinner);
        Save = (Button) findViewById(R.id.simpan);
        Lihat = (TextView) findViewById(R.id.Viewpilihan);
        selectedpdf = (TextView) findViewById(R.id.notification1);
        selectedimage = (TextView) findViewById(R.id.notification2);

        AutoCompleteTextView editText = findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item, R.id.text_view_list_item, temp);
        editText.setAdapter(adapter);

        Save.setOnClickListener(this);
        Lihat.setOnClickListener(this);

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(InputAspirasi.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectPdf();

                }
                else{
                    ActivityCompat.requestPermissions(InputAspirasi.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(InputAspirasi.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectImage();

                }
                else{
                    ActivityCompat.requestPermissions(InputAspirasi.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }

            }
        });


        mDatabase = new DatabaseManager(this);
    }

    private void addpilihan(){
        String name = text1.getText().toString().trim();
        String email = text2.getText().toString().trim();
        String phone = text3.getText().toString().trim();
        String time = text4.getText().toString().trim();
        String date = text5.getText().toString().trim();
        String essai = text6.getText().toString().trim();
        String pilihan = spinner.getSelectedItem().toString().trim();

        if (name.isEmpty()){
            text1.setError("pengisian nama diperlukan");
            text1.requestFocus();
            return ;
        }

        if (email.isEmpty()){
            text2.setError("pengisian alamat email diperlukan");
            text2.requestFocus();
            return ;
        }

        if (phone.isEmpty()) {
            text3.setError("pengisian nomor handphone diperlukan");
            text3.requestFocus();
            return ;
        }

        if (time.isEmpty()){
            text4.setError("pengisian waktu diperlukan");
            text4.requestFocus();
            return ;
        }

        if (date.isEmpty()) {
            text5.setError("pengisian tanggal diperlukan");
            text5.requestFocus();
            return ;
        }

        if(essai.isEmpty()) {
            text6.setError("pengisian keterangan essai diperlukan");
            text6.requestFocus();
            return ;
        }

        if (mDatabase.addpilihan(name, email, phone, date, time, essai, pilihan)){
            //Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InputAspirasi.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(InputAspirasi.this, 0, intent, 0);

            Notification.Builder builder = new Notification.Builder(InputAspirasi.this);

            Notification notification = builder.setContentTitle("Notifikasi Baru")
                    .setContentText("Pegawai selesai ditambahkan")
                    .setTicker("Pesan baru")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setChannelId(CHANNEL_ID);
            }

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        CHANNEL_ID,
                        "NotificationDemo",
                        IMPORTANCE_DEFAULT
                );
                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(0, notification);
            startActivity(intent);
        }

        else
        Toast.makeText(this, "Could not add employee", Toast.LENGTH_SHORT).show();

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri=data.getData();
            selectedpdf.setText("A file is selected : "+ data.getData().getLastPathSegment());
        }
        else if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            imageUri=data.getData();
            selectedimage.setText("A file is selected : "+ data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(InputAspirasi.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick (View view){

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get (Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        switch (view.getId()) {
            case R.id.simpan:

                addpilihan();

                break;
            case R.id.Viewpilihan:

                startActivity(new Intent(this, Aspirasi.class));
                finish();

                break;

            case R.id.Date:

                final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int dayOfMonth, int month, int year) {
                                txtDate.setText(dayOfMonth +"-"+ month +"-"+ year);
                            }
                        }, mYear,mMonth,mDay);
                datePickerDialog.show();

                break;

            case R.id.Time:

                final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

                break;
        }
    }
}

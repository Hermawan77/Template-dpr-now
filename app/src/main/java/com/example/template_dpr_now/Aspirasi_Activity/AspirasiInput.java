package com.example.template_dpr_now.Aspirasi_Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class AspirasiInput extends AppCompatActivity implements View.OnClickListener {

    //deklarasi variable
    private static final String CHANNEL_ID = ".notificationDemo.channelId";
    private static final int PICK_IMAGE_REQUEST=1;
    private static final String[] temp = new String[]{
            "Annisa", "Gita", "Asmara", "Bahrul", "Faiz", "Dicky", "Hermawan", "Yulianto", "Pambudi"};
    EditText text3,text4,text5,text6;
    AutoCompleteTextView text1, text2;
    TextView txtTime,txtDate, Lihat, txtProgress;
    Button Save, btngambar, addfile;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton pria, wanita;
    AspirasiDatabaseManager mDatabase;
    CheckBox cb1, cb2, cb3, cb4;
    String checkboxtxt, Seekbar_txt;
    SeekBar seekBar;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
    private int  mHour, mMinute, mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aspirasi_input_layout);
        this.setTheme(R.style.DefaultTheme);

        //inisialisasi edit text
        text1 = (AutoCompleteTextView) findViewById(R.id.actv);
        text2 =(AutoCompleteTextView) findViewById(R.id.emailview);
        text3 = (EditText) findViewById(R.id.phoneview);
        text4 = (EditText) findViewById(R.id.Date);
        text5 = (EditText) findViewById(R.id.Time);
        text6 = (EditText) findViewById(R.id.essai);

        //inisialisasi time
        txtTime = (EditText) findViewById(R.id.Time);
        txtTime.setOnClickListener(this);

        //inisialisasi date
        txtDate = (EditText) findViewById(R.id.Date);
        txtDate.setOnClickListener(this);

        //inisialisasi spinner
        spinner = (Spinner) findViewById(R.id.spinner);


        //inisialisasi view image dan fungsi pick image untuk mengambil dan menyimpan serta menampilkan gambar terpilih
        imageView = (ImageView) findViewById(R.id.imageview);
        btngambar = (Button) findViewById(R.id.addgambar);
        btngambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        //fungsi permission untuk mengambil gambar dari device
                        AspirasiInput.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
//        String[] test = getResources().getStringArray(R.array.Test);

        //inisialisi dan fungsi untuk autocomplete statis
        AutoCompleteTextView editText = findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.autocomplete_list_item, R.id.text_view_list_item, temp);
        editText.setAdapter(adapter);

        //inisialisasi dan fungsi saat seekbar digerakaan untuk mendapatkan nilainya
        txtProgress = findViewById(R.id.textseekbar);
        seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Seekbar_txt = String.valueOf(progress);
                txtProgress.setText("" + Seekbar_txt + "%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ////inisialisasi tombol simpan
        Save = (Button) findViewById(R.id.simpan);
        Save.setOnClickListener(this);

        //inisialisasi tombol lihat daftar
        Lihat = (TextView) findViewById(R.id.Viewpilihan);
        Lihat.setOnClickListener(this);

        //inisialisasi databasemanager
        mDatabase = new AspirasiDatabaseManager(this);
    }

    private void addpilihan(){
        //pengabilan nilai dan memasukkan nilainya kedalam variable baru
        String name = text1.getText().toString().trim();
        String email = text2.getText().toString().trim();
        String phone = text3.getText().toString().trim();
        String time = text4.getText().toString().trim();
        String date = text5.getText().toString().trim();
        String essai = text6.getText().toString().trim();
        String pilihan = spinner.getSelectedItem().toString().trim();
        byte[] image = imageViewToByte(imageView);

        //fungsi untuk menampilkan date and time ke layar (dialog pop up)
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-YYYY");
        try {
            Date baru = dt.parse(date);
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            //System.out.println("Baru = " + dt1.format(date));
            date = dt1.format(baru);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //inisialisasi nilai checkbox
        cb1 = (CheckBox) findViewById(R.id.checkbox1);
        cb1.setOnClickListener(this);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);
        cb2.setOnClickListener(this);
        cb3 = (CheckBox) findViewById(R.id.checkbox3);
        cb3.setOnClickListener(this);
        cb4 = (CheckBox) findViewById(R.id.checkbox4);
        cb4.setOnClickListener(this);

        //deklarasi string baru untuk simpan nilai string checkbox
        String Checkboxval;

        //fungsi untuk memasukkan nilai checkbox tercentang kedalam sebuah array
        List<CheckBox> items = new ArrayList<CheckBox>();
        for (CheckBox item : items ){
            if (item.isChecked())
                checkboxtxt = item.getText().toString();
        }
        CheckBox[] nameString = new CheckBox[]{cb1, cb2, cb3, cb4};

        for (int i=0; i<=3; i++)
        {
            if (nameString[i].isChecked())
            {
                checkboxtxt = checkboxtxt + "," + nameString[i].getText().toString();
            }
        }
        checkboxtxt = checkboxtxt.replace("null,","");
        Checkboxval = checkboxtxt;


        //inisialisai radio button
        radioGroup = (RadioGroup) findViewById(R.id.rb);
        pria = (RadioButton) findViewById(R.id.pria);
        wanita = (RadioButton) findViewById(R.id.wanita);
        String radiotext;

        //fungsi untuk mendapatkan nilai dari radi button terpilih
        radioGroup.getCheckedRadioButtonId();
        int radioID = radioGroup.getCheckedRadioButtonId();
        if (radioID == pria.getId()){
            radiotext = pria.getText().toString();
        } else {
            radiotext = wanita.getText().toString();
        }

        //inisialisasi dan pengambilan nilai seekbar
        String seekbar;
        seekbar = txtProgress.getText().toString();

        //fungsi pengecekan apakah nilai edittext telah terisi atau belum, dan meminta pengisian jika kosong
        if (name.isEmpty()){
            text1.setError("pengisian nama diperlukan");
            text1.requestFocus();
            return ;
        }

        //fungsi pengecekan apakah nilai edittext telah terisi atau belum, dan meminta pengisian jika kosong
        if (email.isEmpty()){
            text2.setError("pengisian alamat email diperlukan");
            text2.requestFocus();
            return ;
        }

        //fungsi pengecekan apakah nilai edittext telah terisi atau belum, dan meminta pengisian jika kosong
        if (phone.isEmpty()) {
            text3.setError("pengisian nomor handphone diperlukan");
            text3.requestFocus();
            return ;
        }

        //fungsi pengecekan apakah nilai edittext telah terisi atau belum, dan meminta pengisian jika kosong
        if (time.isEmpty()){
            text4.setError("pengisian waktu diperlukan");
            text4.requestFocus();
            return ;
        }

        //fungsi pengecekan apakah nilai edittext telah terisi atau belum, dan meminta pengisian jika kosong
        if (date.isEmpty()) {
            text5.setError("pengisian tanggal diperlukan");
            text5.requestFocus();
            return ;
        }

        //fungsi pengecekan apakah nilai edittext telah terisi atau belum, dan meminta pengisian jika kosong
        if(essai.isEmpty()) {
            text6.setError("pengisian keterangan essai diperlukan");
            text6.requestFocus();
            return ;
        }

        //fungsi untuk memasukkan nilai inputan kedalam database
        if (mDatabase.addpilihan(name, email, phone, date, time, essai, pilihan, Checkboxval,  radiotext, seekbar, image )){

            Intent intent = new Intent(AspirasiInput.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(AspirasiInput.this, 0, intent, 0);

            Notification.Builder builder = new Notification.Builder(AspirasiInput.this);

            Notification notification = builder.setContentTitle("Notifikasi Baru")
                    .setContentText("Aspirasi selesai ditambahkan")
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
        Toast.makeText(this, "Tidak dapat menambahkan data", Toast.LENGTH_SHORT).show();

    }

    //fungsi untuk mengconvert gambar kedalam byte
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //fungsi untuk meminta akses kedalam galery device
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //fungsi cek akses galery apakah sudah diizinkan dan sesuai
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //fungsi ketika tombol dalm tampilan mendapat aksi klik
    @Override
    public void onClick (View view){

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get (Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        switch (view.getId()) {
            //saat tombol simpan diklik maka akan berjalan fungsi addpilihan()
            case R.id.simpan:

                addpilihan();

                break;
                //fungsi saat tombol view ilihan diklik akan menampilkan list halaman data inputan
            case R.id.Viewpilihan:

                startActivity(new Intent(this, Aspirasi.class));
                finish();

                break;
                //fungsi saat date di klik akan muncul dialog kalender
            case R.id.Date:

                final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int dayOfMonth, int month, int year) {
                                String tanggal = dayOfMonth + "-" + month + "-" + year;

                                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date date = dt.parse(tanggal);
                                    SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-YYYY");
                                    //System.out.println("Baru = " + dt1.format(date));
                                    tanggal = dt1.format(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                txtDate.setText(tanggal);
                            }
                        }, mYear,mMonth,mDay);
                datePickerDialog.show();

                break;
            //fungsi saat time di klik akan muncul dialog jam
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

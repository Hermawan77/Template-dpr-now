package com.example.template_dpr_now;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Currency;

public class Input extends AppCompatActivity implements View.OnClickListener {

    protected Cursor cursor;
    DataHelper dbHelper;
    EditText text3,text4,text5,text6;
    AutoCompleteTextView text1, text2;
    TextView txtTime,txtDate;
    Button Save;
    Spinner spinner;
    private int  mHour, mMinute, mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        dbHelper = new DataHelper(this);

        text1 = (AutoCompleteTextView) findViewById(R.id.namaview);
        text2 =(AutoCompleteTextView) findViewById(R.id.emailview);
        text3 = (EditText) findViewById(R.id.phoneview);
        text4 = (EditText) findViewById(R.id.Time);
        text5 = (EditText) findViewById(R.id.Date);
        text6 = (EditText) findViewById(R.id.essai);

        txtTime = (EditText) findViewById(R.id.Time);
        txtTime.setOnClickListener(this);
        txtDate = (EditText) findViewById(R.id.Date);
        txtDate.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        Save = (Button) findViewById(R.id.simpan);

        buatinput();
    }

    public void buatinput (){
        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View arg0){
                String key_name = text1.getText().toString().trim();
                String key_email = text2.getText().toString().trim();
                String key_phone = text3.getText().toString().trim();
                String key_time = text4.getText().toString().trim();
                String key_date = text5.getText().toString().trim();
                String key_essai = text6.getText().toString().trim();
                String key_spinner = spinner.getSelectedItem().toString();


                if (key_name.isEmpty()){
                    text1.setError("pengisian nama diperlukan");
                    text1.requestFocus();
                    return;
                }

                if (key_email.isEmpty()){
                    text2.setError("pengisian alamat email diperlukan");
                    text2.requestFocus();
                    return;
                }

                if (key_phone.isEmpty()) {
                    text3.setError("pengisian nomor handphone diperlukan");
                    text3.requestFocus();
                    return;
                }

                if (key_time.isEmpty()){
                    text4.setError("pengisian waktu diperlukan");
                    text4.requestFocus();
                    return;
                }

                if (key_date.isEmpty()) {
                    text5.setError("pengisian tanggal diperlukan");
                    text5.requestFocus();
                    return;
                }

                if(key_essai.isEmpty()) {
                    text6.setError("pengisian keterangan essai diperlukan");
                    text6.requestFocus();
                    return;
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into input(name, email, phone, time, date, essai) values ('"+
                        text1.getText().toString()+"','"+
                        text2.getText().toString()+"','"+
                        text3.getText().toString()+"','"+
                        text4.getText().toString()+"','"+
                        text5.getText().toString()+"','"+
                        text6.getText().toString()+"','"+
                        spinner.getSelectedItem().toString()+ "')");
                Toast.makeText(getApplicationContext(),"Sukses", Toast.LENGTH_LONG).show();
                startActivity (new Intent(Input.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onClick (View v){

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get (Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int dayOfMonth, int month, int year) {
                        txtDate.setText(dayOfMonth +"-"+ month +"-"+ year);
                    }
                }, mYear,mMonth,mDay);
        datePickerDialog.show();
    }
}

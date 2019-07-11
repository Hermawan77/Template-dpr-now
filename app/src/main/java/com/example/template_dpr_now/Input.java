package com.example.template_dpr_now;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.template_dpr_now.Rest.DatabaseManager;
import java.util.Calendar;

public class Input extends AppCompatActivity implements View.OnClickListener {

    EditText text3,text4,text5,text6;
    AutoCompleteTextView text1, text2;
    TextView txtTime,txtDate, Lihat;
    Button Save;
    Spinner spinner;
    DatabaseManager mDatabase;
    private int  mHour, mMinute, mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        text1 = (AutoCompleteTextView) findViewById(R.id.namaview);
        text2 =(AutoCompleteTextView) findViewById(R.id.emailview);
        text3 = (EditText) findViewById(R.id.phoneview);
        text4 = (EditText) findViewById(R.id.Date);
        text5 = (EditText) findViewById(R.id.Time);
        text6 = (EditText) findViewById(R.id.essai);

        txtTime = (EditText) findViewById(R.id.Time);
        txtTime.setOnClickListener(this);
        txtDate = (EditText) findViewById(R.id.Date);
        txtDate.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        Save = (Button) findViewById(R.id.simpan);
        Lihat = (TextView) findViewById(R.id.Viewpilihan);

        Save.setOnClickListener(this);
        Lihat.setOnClickListener(this);

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

        if (mDatabase.addpilihan(name, email, Double.parseDouble(phone), date, time, essai, pilihan))
        Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "Could not add employee", Toast.LENGTH_SHORT).show();

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

                startActivity(new Intent(this, MainActivity.class));
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

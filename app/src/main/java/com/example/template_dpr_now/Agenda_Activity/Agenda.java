package com.example.template_dpr_now.Agenda_Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.XmlToJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Agenda extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AgendaAdapter.OnItemClickListener {
    public static final String EXTRA_TANGGAL = "tanggal";
    public static final String EXTRA_JAM = "jam";
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_DESKRIPSI = "deskripsi";

    private RecyclerView mRecyclerview;
    private AgendaAdapter mAgenda_Adapter;
    private ArrayList<AgendaItem> mAgenda_Item;
    private RequestQueue mRequestQueue;
    private TextView status;
    private TextView sekarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        status = findViewById(R.id.status);
        sekarang = findViewById(R.id.sekarang);

        this.setTheme(R.style.DefaultTheme);

        findViewById(R.id.kalender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        mRecyclerview = findViewById(R.id.recycler_view_agenda);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mAgenda_Item = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

    }

    //Menampilkan kalender
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    //Ketika tanggal di-set
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy - MM - dd");
        String current = simpleDateFormat.format(c.getTime());

        display(current);

        //Menampilkan alert ketika kalender yang dipilih salah
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Tahun dan Bulan Salah");
        alert
                .setMessage("Masukkan Tahun dan Bulan dengan Benar")
                .setIcon(R.drawable.you_tube_logo)
                .setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alert.create();

        if (year > Integer.parseInt(current.substring(0,4))){
            alertDialog.show();
        }

        else {

            if (month + 1 > Integer.parseInt(current.substring(7,9))){
                alertDialog.show();
            }

            else {
                String tahun = Integer.toString(year);

                int a = 1 + month;

                String bulan = Integer.toString(a);

                if (bulan.length()==1){
                    bulan = "0" +  bulan;
                }

        status.setText("Agenda Bulan " + bulan + " Tahun " + tahun);

                String BASE_URL = "http://www.dpr.go.id/rest/?method=getAgendaPerBulan&tahun=" + tahun + "&bulan=" + bulan + "&tipe=xml";
                parseLink(BASE_URL);
            }

        }

    }

    private void display(String current) {
        sekarang.setText(current);
    }

    //Request menggunakan Volley
    private void parseLink(final String BASE_URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //clear data agar data berubah saat tanggal diganti
                        mAgenda_Item.clear();

                        response = response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
                        response = response.substring(10, response.length()-11);

                        //Parsing xml ke json
                        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
                        JSONObject jsonObject = xmlToJson.toJson();

                        try {
                            //get Array dengan nama "agenda"
                            JSONArray jsonArray = jsonObject.getJSONArray("agenda");

                            for (int i= 0; i<jsonArray.length();i++){
                                JSONObject agenda = jsonArray.getJSONObject(i);

                                //get isi "tanggal"
                                String tanggal = agenda.getString("tanggal");

                                //konversi "tanggal" dari yyyy-MM-dd ke dd-MM-yyyy
                                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date date = dt.parse(tanggal);
                                    SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
                                    System.out.println("Baru = " + dt1.format(date));
                                    tanggal = dt1.format(date);
                                    tanggal = tanggal.substring(0,2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                //get isi "jam"
                                String jam = agenda.getString("jam");
                                //get isi "judul"
                                String judul = agenda.getString("title");
                                //get isi "deskripsi"
                                String deskripsi = agenda.getString("deskripsi");

                                //data masuk
                                mAgenda_Item.add(new AgendaItem(tanggal, jam, judul, deskripsi));
                            }

                            //masuk ke adapter untuk ke recyclerview
                            mAgenda_Adapter = new AgendaAdapter(Agenda.this, mAgenda_Item);
                            mRecyclerview.setAdapter(mAgenda_Adapter);
                            //set ketika salah satu agenda ditekan
                            mAgenda_Adapter.setOnItemClickListener(Agenda.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            //Menampilkan error di logcat maupun toast ketika gagal mengambil data
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Gagal");
                Toast.makeText(getApplicationContext(), "Gagal Mengambil Data, Masalah Koneksi?", Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(stringRequest);
    }

    //Ketika salah satu agenda ditekan, meneruskan data "tanggal", "jam", "judul", dan "deskripsi" serta berpindah dari activity Agenda ke activity AgendaDetail
    @Override
    public void onItemClick(int position) {
        Intent intentDetail = new Intent(this, AgendaDetail.class);
        AgendaItem clickedItem = mAgenda_Item.get(position);

        intentDetail.putExtra(EXTRA_TANGGAL, clickedItem.getTanggal());
        intentDetail.putExtra(EXTRA_JAM, clickedItem.getJam());
        intentDetail.putExtra(EXTRA_JUDUL, clickedItem.getJudul());
        intentDetail.putExtra(EXTRA_DESKRIPSI, clickedItem.getDeskripsi());

        startActivity(intentDetail);
    }

}
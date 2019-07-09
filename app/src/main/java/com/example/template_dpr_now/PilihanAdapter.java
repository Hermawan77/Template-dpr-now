package com.example.template_dpr_now;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PilihanAdapter extends ArrayAdapter<Pilihann> {

    Context mCtx;
    int listLayoutRes;
    List<Pilihann> pilihannList;
    SQLiteDatabase db;
    public PilihanAdapter(Context mCtx, int listLayoutRes, List<Pilihann> employeeList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, employeeList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.pilihannList = employeeList;
        this.db = mDatabase;
    }

    public PilihanAdapter(Pilihan pilihan, int list_layout_pilihan, List<Pilihann> pilihanList) {
        super(pilihan, list_layout_pilihan, pilihanList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        final Pilihann pilihann = pilihannList.get(position);


        //getting views
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);
        TextView textViewPilihan = view.findViewById(R.id.textViewPilihan);
        TextView textViewEssai = view.findViewById(R.id.textViewEssai);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewTime = view.findViewById(R.id.textViewTime);

        //adding data to views
        textViewName.setText(pilihann.getName());
        textViewEmail.setText(pilihann.getEmail());
        textViewPhone.setText(String.valueOf(pilihann.getPhone()));
        textViewPilihan.setText(pilihann.getPilihan());
        textViewEssai.setText(pilihann.getEssai());
        textViewDate.setText(pilihann.getDate());
        textViewTime.setText(pilihann.getTime());

        //we will use these buttons later for update and delete operation
        Button buttonDelete = view.findViewById(R.id.buttonDelete);
        Button buttonEdit = view.findViewById(R.id.buttonEdit);

        //adding a clicklistener to button
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePilihann(pilihann);
            }
        });

        //the delete operation
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM employees WHERE id = ?";
                        db.execSQL(sql, new Integer[]{pilihann.getId()});
                        reloadEmployeesFromDatabase();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private void updatePilihann(final Pilihann pilihann){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_pilihan, null);
        builder.setView(view);

        final EditText editTextNama = view.findViewById(R.id.namaview);
        final EditText editTextEmail = view.findViewById(R.id.emailview);
        final EditText editTextPhone = view.findViewById(R.id.phoneview);
        final Spinner spinnerpilihan = view.findViewById(R.id.spinner);
        final EditText editTextEssai = view.findViewById(R.id.essai);
        final EditText editTextDate = view.findViewById(R.id.Date);
        final EditText editTextTime = view.findViewById(R.id.Time);

        editTextNama.setText(pilihann.getName());
        editTextEmail.setText(pilihann.getEmail());
        editTextPhone.setText(String.valueOf(pilihann.getPhone()));
        editTextEssai.setText(pilihann.getEssai());
        editTextDate.setText(pilihann.getDate());
        editTextTime.setText(pilihann.getName());

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editTextNama.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String pilihan = spinnerpilihan.getSelectedItem().toString();
                String essai = editTextEssai.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();

                if (nama.isEmpty()) {
                    editTextNama.setError("mohon diisi");
                    editTextNama.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    editTextEmail.setError("mohon diisi");
                    editTextEmail.requestFocus();
                    return;
                }

                if (phone.isEmpty()) {
                    editTextPhone.setError("mohon diisi");
                    editTextPhone.requestFocus();
                    return;
                }

                if (essai.isEmpty()) {
                    editTextEssai.setError("mohon diisi");
                    editTextEssai.requestFocus();
                    return;
                }

                if (date.isEmpty()) {
                    editTextDate.setError("mohon diisi");
                    editTextDate.requestFocus();
                    return;
                }

                if (time.isEmpty()) {
                    editTextTime.setError("mohon diisi");
                    editTextTime.requestFocus();
                    return;
                }

                String sql = "UPDATE employees \n" +
                        "SET nama = ?, \n" +
                        "email = ?, \n" +
                        "phone = ?, \n" +
                        "pilihan = ?, \n" +
                        "essai = ?, \n" +
                        "date = ?, \n" +
                        "time = ? \n" +
                        "WHERE id = ?;\n";

                db.execSQL(sql, new String[]{nama, email, phone, pilihan, essai, date, time, String.valueOf(pilihann.getId())});
                Toast.makeText(mCtx, "Employee Updated", Toast.LENGTH_SHORT).show();
                reloadEmployeesFromDatabase();

                dialog.dismiss();
            }
        });
    }

    private void reloadEmployeesFromDatabase() {
        Cursor cursorEmployees = db.rawQuery("SELECT * FROM employees", null);
        if (cursorEmployees.moveToFirst()) {
            pilihannList.clear();
            do {
                pilihannList.add(new Pilihann(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getString(4),
                        cursorEmployees.getString(5),
                        cursorEmployees.getString(6),
                        cursorEmployees.getDouble(7)
                ));
            } while (cursorEmployees.moveToNext());
        }
        cursorEmployees.close();
        notifyDataSetChanged();
    }
}

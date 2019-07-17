package com.example.template_dpr_now;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PilihanAdapter extends ArrayAdapter<Pilihann> {

    Context mCtx;
    int layoutRes;
    List<Pilihann> pilihannList;

    //the databasemanager object
    DatabaseManager mDatabase;

    public PilihanAdapter(Context mCtx, int listLayoutRes, List<Pilihann> pilihannList, DatabaseManager mDatabase) {
        super(mCtx, listLayoutRes, pilihannList);

        this.mCtx = mCtx;
        this.layoutRes = listLayoutRes;
        this.pilihannList = pilihannList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes, null);

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

        view.findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePilihan(pilihann);
            }
        });

        view.findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePilihann(pilihann);
            }
        });

        return view;
    }

    private void updatePilihann(final Pilihann pilihann){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_pilihan, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextName = view.findViewById(R.id.namaview);
        final EditText editTextEmail = view.findViewById(R.id.emailview);
        final EditText editTextPhone = view.findViewById(R.id.phoneview);
        final Spinner spinnerpilihan = view.findViewById(R.id.spinner);
        final EditText editTextEssai = view.findViewById(R.id.essai);
        final EditText editTextDate = view.findViewById(R.id.Date);
        final EditText editTextTime = view.findViewById(R.id.Time);

        editTextName.setText(pilihann.getName());
        editTextEmail.setText(pilihann.getEmail());
        editTextPhone.setText(String.valueOf(pilihann.getPhone()));
        editTextEssai.setText(pilihann.getEssai());
        editTextDate.setText(pilihann.getDate());
        editTextTime.setText(pilihann.getName());

        view.findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String pilihan = spinnerpilihan.getSelectedItem().toString();
                String essai = editTextEssai.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();

                if (name.isEmpty()) {
                    editTextName.setError("mohon diisi");
                    editTextName.requestFocus();
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

                //calling the update method from database manager instance
                if (mDatabase.updatepilihan(pilihann.getId(), name, email, Double.valueOf(phone), date, time, essai, pilihan)) {
                    Toast.makeText(mCtx, "Pilihan Updated", Toast.LENGTH_SHORT).show();
                    loadEmployeesFromDatabaseAgain();
                }
                alertDialog.dismiss();
            }
        });
    }
    private void deletePilihan(final Pilihann pilihann){
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //calling the delete method from the database manager instance
                if (mDatabase.deleteEmployee(pilihann.getId()))
                    loadEmployeesFromDatabaseAgain();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void loadEmployeesFromDatabaseAgain() {
        //calling the read method from database instance
        Cursor cursor = mDatabase.getAllPilihan();

        pilihannList.clear();
        if (cursor.moveToFirst()) {
            do {
                pilihannList.add(new Pilihann(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();
    }
}

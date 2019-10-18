package com.example.template_dpr_now.Aspirasi_Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template_dpr_now.R;

import java.util.List;

public class AspirasiAdapter extends ArrayAdapter<Aspirasii> {

    //mendeklarasikan variable
    Context mCtx;
    int layoutRes;
    List<Aspirasii> aspirasiiList;

    //mendeklarasikan objek dari database
    AspirasiDatabaseManager mDatabase;

    //inisialisasi fugsi konstruktor bernilai
    public AspirasiAdapter(Context mCtx, int listLayoutRes, List<Aspirasii> aspirasiiList, AspirasiDatabaseManager mDatabase) {
        super(mCtx, listLayoutRes, aspirasiiList);

        this.mCtx = mCtx;
        this.layoutRes = listLayoutRes;
        this.aspirasiiList = aspirasiiList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes, null);

        //inisialisasi list
        final Aspirasii aspirasii = aspirasiiList.get(position);

        //mendaptakan akses ke id tampilan
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);
        TextView textViewPilihan = view.findViewById(R.id.textViewPilihan);
        TextView textViewEssai = view.findViewById(R.id.textViewEssai);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewTime = view.findViewById(R.id.textViewTime);
        TextView textViewCheckbox = view.findViewById(R.id.textViewCheckbox);
        TextView textViewRadio = view.findViewById(R.id.textViewRadio);
        TextView textViewSeekbar = view.findViewById(R.id.textViewSeekbar);
        ImageView imageview = view.findViewById(R.id.gambarviewlist);


        //menambahkan data ke masing-masing tampilan
        textViewName.setText(aspirasii.getName());
        textViewEmail.setText(aspirasii.getEmail());
        textViewPhone.setText(aspirasii.getPhone());
        textViewPilihan.setText(aspirasii.getPilihan());
        textViewEssai.setText(aspirasii.getEssai());
        textViewDate.setText(aspirasii.getDate());
        textViewTime.setText(aspirasii.getTime());
        textViewCheckbox.setText(aspirasii.getCheckboxval());
        textViewRadio.setText(aspirasii.getRadiotext());
        textViewSeekbar.setText(aspirasii.getSeekbar());
        byte[] recordImage = aspirasii.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        imageview.setImageBitmap(bitmap);

        //fungsi saat tombol delet diklik
        view.findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAspirasi(aspirasii);
            }
        });

        return view;
    }

    private void updateAspirasi(final Aspirasii aspirasii){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.aspirasi_update_layout, null);
        builder.setView(view);

        //fungsi yang membuat tampilan dialog (karena bagian update di tampilkan dalam dialog)
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //mendaptakan akses ke id tampilan
        final EditText editTextName = view.findViewById(R.id.namaview);
        final EditText editTextEmail = view.findViewById(R.id.emailview);
        final EditText editTextPhone = view.findViewById(R.id.phoneview);
        final Spinner spinnerpilihan = view.findViewById(R.id.spinner);
        final EditText editTextEssai = view.findViewById(R.id.essai);
        final EditText editTextDate = view.findViewById(R.id.Date);
        final EditText editTextTime = view.findViewById(R.id.Time);
        final EditText editTextCheckbox = view.findViewById(R.id.checkboxview);
        final EditText editTextRadio = view.findViewById(R.id.radio1);
        final EditText editTextSeekbar = view.findViewById(R.id.seekbar1);
        final ImageView updateimage = view.findViewById(R.id.updategambar);

        //menambahkan data ke masing-masing tampilan
        editTextName.setText(aspirasii.getName());
        editTextEmail.setText(aspirasii.getEmail());
        editTextPhone.setText(aspirasii.getPhone());
        editTextEssai.setText(aspirasii.getEssai());
        editTextDate.setText(aspirasii.getDate());
        editTextTime.setText(aspirasii.getName());
        editTextCheckbox.setText(aspirasii.getCheckboxval());
        editTextRadio.setText(aspirasii.getRadiotext());
        editTextSeekbar.setText(aspirasii.getSeekbar());
        final byte[] recordImage = aspirasii.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        updateimage.setImageBitmap(bitmap);

        //untuk mendapatkan data yang ada pada tampilan dan menyimpannya dalam variable baru
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
                String checkbox = editTextCheckbox.getText().toString().trim();
                String radio = editTextRadio.getText().toString().trim();
                String seekbar = editTextSeekbar.getText().toString().trim();
                byte[] rImage = aspirasii.getImage();
                Bitmap bitmap = BitmapFactory.decodeByteArray(rImage, 0, rImage.length);
                updateimage.setImageBitmap(bitmap);


                //kondisi jika bagian kosong, diminta untuk mengisi
                if (name.isEmpty()) {
                    editTextName.setError("mohon diisi");
                    editTextName.requestFocus();
                    return;
                }

                //kondisi jika bagian kosong, diminta untuk mengisi
                if (email.isEmpty()) {
                    editTextEmail.setError("mohon diisi");
                    editTextEmail.requestFocus();
                    return;
                }

                //kondisi jika bagian kosong, diminta untuk mengisi
                if (phone.isEmpty()) {
                    editTextPhone.setError("mohon diisi");
                    editTextPhone.requestFocus();
                    return;
                }

                //kondisi jika bagian kosong, diminta untuk mengisi
                if (essai.isEmpty()) {
                    editTextEssai.setError("mohon diisi");
                    editTextEssai.requestFocus();
                    return;
                }

                //kondisi jika bagian kosong, diminta untuk mengisi
                if (date.isEmpty()) {
                    editTextDate.setError("mohon diisi");
                    editTextDate.requestFocus();
                    return;
                }

                //kondisi jika bagian kosong, diminta untuk mengisi
                if (time.isEmpty()) {
                    editTextTime.setError("mohon diisi");
                    editTextTime.requestFocus();
                    return;
                }

                //memanggil metode update dari database manager
                if (mDatabase.updateAspirasi(aspirasii.getId(), name, email, phone, date, time, essai, pilihan, checkbox, radio, seekbar, rImage)) {
                    Toast.makeText(mCtx, "Aspirasi Updated", Toast.LENGTH_SHORT).show();
                    loadEmployeesFromDatabaseAgain();
                }
                //saat selesai alaret dialog hilang (ditutup)
                alertDialog.dismiss();
            }
        });
    }
    private void deleteAspirasi(final Aspirasii aspirasii){
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Yakin menghapus aspirasi?");

        //fungsi untuk mengklik tulisan "iya"
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //memanggil metode menghapus dari databasemanager
                if (mDatabase.deleteAspirasi(aspirasii.getId()))
                    loadEmployeesFromDatabaseAgain();
            }
        });

        //fungsi saat tulisan "batalkan diklik
        builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //untuk membuat dialognya yang menampilkan opsi penghapusan
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void loadEmployeesFromDatabaseAgain() {
        //memanggil metode pembacaan data dari database manager
        Cursor cursor = mDatabase.getAllPilihan();

        aspirasiiList.clear();
        if (cursor.moveToFirst()) {
            do {
                aspirasiiList.add(new Aspirasii(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getBlob(11)
                ));
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();
    }
}

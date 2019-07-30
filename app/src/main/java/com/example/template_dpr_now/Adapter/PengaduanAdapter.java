package com.example.template_dpr_now.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.template_dpr_now.MainActivity;
import com.example.template_dpr_now.Model.Pengaduan;
import com.example.template_dpr_now.R;
import com.example.template_dpr_now.fragment.KomisiItem;

import java.util.ArrayList;
import java.util.List;

public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.MyViewHolder> implements ListAdapter {
    List<Pengaduan> mPengaduanList;
    ArrayList<Pengaduan> mdataModelArrayList;
    private Context mContext;
    private ArrayList<KomisiItem> mF_K1_List;

    public PengaduanAdapter(List<Pengaduan> PengaduanList) {
        mPengaduanList = PengaduanList;
//        mContext = context;
//        mF_K1_List = F_K1_List;
    }



    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_pengaduan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){

        //memasukkan data ke holder
        holder.mTextViewAduan.setText(mPengaduanList.get(position).getIsi_aduan());

        holder.mTextViewNama.setText(mPengaduanList.get(position).getNama());

        holder.mTextViewTanggal.setText(mPengaduanList.get(position).getTanggal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), MainActivity.class);

                mIntent.putExtra("Nama", mPengaduanList.get(position).getNama());

                mIntent.putExtra("Aduan", mPengaduanList.get(position).getIsi_aduan());
                mIntent.putExtra("Tanggal", mPengaduanList.get(position).getTanggal());

                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mPengaduanList.size();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewEmail, mTextViewNama, mTextViewNomor,mTextViewAduan,mTextViewTanggal;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvNama);

            mTextViewAduan = (TextView) itemView.findViewById(R.id.tvAduan);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.datetime);
        }
    }
}

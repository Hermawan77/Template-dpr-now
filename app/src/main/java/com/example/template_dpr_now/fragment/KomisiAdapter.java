package com.example.template_dpr_now.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.template_dpr_now.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KomisiAdapter extends RecyclerView.Adapter<KomisiAdapter.F_K1_ViewHolder> {
    private Context mContext;
    private ArrayList<KomisiItem> mF_K1_List;

    public KomisiAdapter(Context context, ArrayList<KomisiItem> F_K1_List) {
        mContext = context;
        mF_K1_List = F_K1_List;
    }

    @Override
    public F_K1_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.komisi_list, parent, false);
        return new F_K1_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(F_K1_ViewHolder holder, int position) {
        KomisiItem currentItem = mF_K1_List.get(position);

        String imageUrl = currentItem.getImageUrl();
        String nama = currentItem.getNama();
        String nip = currentItem.getNip();
        String jabatan = currentItem.getJabatan();

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.mTextViewNama.setText(nama);
        holder.mTextViewNip.setText(nip);
        holder.mTextViewJabatan.setText(jabatan);
        //Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mF_K1_List.size();
    }

    public class F_K1_ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewNama;
        public TextView mTextViewNip;
        public TextView mTextViewJabatan;

        public F_K1_ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.k1_image);
            mTextViewNama = itemView.findViewById(R.id.k1_nama);
            mTextViewNip = itemView.findViewById(R.id.k1_nip);
            mTextViewJabatan = itemView.findViewById(R.id.k1_jabatan);
        }
    }
}

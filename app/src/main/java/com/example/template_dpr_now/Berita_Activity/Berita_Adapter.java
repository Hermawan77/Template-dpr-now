package com.example.template_dpr_now.Berita_Activity;

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

class Berita_Adapter extends RecyclerView.Adapter<Berita_Adapter.BeritaViewHolder> {
    private Context mContext;
    private ArrayList<Berita_Item> mBeritaItem;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public Berita_Adapter (Context context, ArrayList<Berita_Item> berita_item){
        mContext = context;
        mBeritaItem = berita_item;
    }

    @Override
    public Berita_Adapter.BeritaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Berita_Adapter.BeritaViewHolder holder, int position) {
        Berita_Item currentItem = mBeritaItem.get(position);

        String tanggal = currentItem.getTanggal();
        String kategori = currentItem.getKategori();
        String imageUrl = currentItem.getImageUrl();
        String judul = currentItem.getJudul();
        String isi = currentItem.getIsi();

        holder.mTextViewTanggal.setText(tanggal);
        holder.mTextViewKategori.setText(kategori);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.mTextViewJudul.setText(judul);
        holder.mTextViewIsi.setText(isi);
    }

    @Override
    public int getItemCount() {
        return mBeritaItem.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewTanggal;
        public TextView mTextViewKategori;
        public ImageView mImageView;
        public TextView mTextViewJudul;
        public TextView mTextViewIsi;

        public BeritaViewHolder(View itemView) {
            super(itemView);
            mTextViewTanggal = itemView.findViewById(R.id.tanggalberita);
            mTextViewKategori = itemView.findViewById(R.id.kategori);
            mImageView = itemView.findViewById(R.id.fotoberita);
            mTextViewJudul = itemView.findViewById(R.id.judulberita);
            mTextViewIsi = itemView.findViewById(R.id.isiberita);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

package com.example.template_dpr_now.Pelayananmasyarakat_Activity;

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

public class PelayananmasyakatAdapter extends RecyclerView.Adapter<PelayananmasyakatAdapter.KunjunganYunmasActivity_ViewHolder> {
    private Context mContext;
    private ArrayList<PelayananmasyakatItem> Kunjungan_List;

    public PelayananmasyakatAdapter(Context context, ArrayList<PelayananmasyakatItem> KunjunganYanmas_List){
        mContext = context;
        Kunjungan_List = KunjunganYanmas_List;
    }


    @Override
    public PelayananmasyakatAdapter.KunjunganYunmasActivity_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.pelayanan_masyarakat_list, parent, false);
        return new PelayananmasyakatAdapter.KunjunganYunmasActivity_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PelayananmasyakatAdapter.KunjunganYunmasActivity_ViewHolder holder, int position) {
        PelayananmasyakatItem currentItem = Kunjungan_List.get(position);

        String imageUrl = currentItem.getImage();
        String tanggal = currentItem.getTanggal();
        String judul = currentItem.getJudul();

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.mTanggal.setText(tanggal);
        holder.mJudul.setText(judul);
        }

    @Override
    public int getItemCount() {
        return Kunjungan_List.size();
    }

    public class KunjunganYunmasActivity_ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTanggal;
        public TextView mJudul;

        public KunjunganYunmasActivity_ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.kunjungan_image);
            mTanggal = itemView.findViewById(R.id.kunjungan_tanggal);
            mJudul = itemView.findViewById(R.id.kunjungan_judul);
        }
    }
}

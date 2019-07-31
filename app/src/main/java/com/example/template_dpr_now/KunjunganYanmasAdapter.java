package com.example.template_dpr_now;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KunjunganYanmasAdapter extends RecyclerView.Adapter<KunjunganYanmasAdapter.KunjunganYunmasActivity_ViewHolder> {
    private Context mContext;
    private ArrayList<KunjunganYanmasItem> Kunjungan_List;

    public KunjunganYanmasAdapter(Context context, ArrayList<KunjunganYanmasItem> KunjunganYanmas_List){
        mContext = context;
        Kunjungan_List = KunjunganYanmas_List;
    }


    @Override
    public KunjunganYanmasAdapter.KunjunganYunmasActivity_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.kunjungan_yanmas_list, parent, false);
        return new KunjunganYanmasAdapter.KunjunganYunmasActivity_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KunjunganYanmasAdapter.KunjunganYunmasActivity_ViewHolder holder, int position) {
        KunjunganYanmasItem currentItem = Kunjungan_List.get(position);

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

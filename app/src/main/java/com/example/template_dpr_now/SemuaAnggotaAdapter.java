package com.example.template_dpr_now;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.template_dpr_now.fragment.KomisiAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SemuaAnggotaAdapter extends RecyclerView.Adapter<SemuaAnggotaAdapter.SemuaAnggota_ViewHolder> {
    private Context mContext;
    private ArrayList<SemuaAnggotaItem> Anggota_List;

    public SemuaAnggotaAdapter(Context context, ArrayList<SemuaAnggotaItem> SemuaAnggota_List){
        mContext = context;
        Anggota_List = SemuaAnggota_List;
    }

    @Override
    public SemuaAnggotaAdapter.SemuaAnggota_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.semua_anggota_list, parent, false);
        return new SemuaAnggota_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SemuaAnggotaAdapter.SemuaAnggota_ViewHolder holder, int i) {
        SemuaAnggotaItem currentItem = Anggota_List.get(i);

        String imageUrl = currentItem.getImageUrl();
        String nama = currentItem.getNamaAnggota();
        String fraksi = currentItem.getFraksi();
        String dapil = currentItem.getDapil();

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mViewImage);
        holder.mNamaAnggota.setText(nama);
        holder.mFraksi.setText(fraksi);
        holder.mDapil.setText(dapil);
    }

    @Override
    public int getItemCount() {
        return Anggota_List.size();
    }

    public class SemuaAnggota_ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mViewImage;
        public TextView mNamaAnggota;
        public TextView mFraksi;
        public TextView mDapil;

        public SemuaAnggota_ViewHolder(View itemView) {
            super(itemView);
            mViewImage = itemView.findViewById(R.id.anggota_image);
            mNamaAnggota = itemView.findViewById(R.id.anggota_nama);
            mFraksi = itemView.findViewById(R.id.anggota_fraksi);
            mDapil = itemView.findViewById(R.id.anggota_dapil);
        }
    }
}

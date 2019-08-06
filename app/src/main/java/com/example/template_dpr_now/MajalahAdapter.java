package com.example.template_dpr_now;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MajalahAdapter extends RecyclerView.Adapter<MajalahAdapter.MajalahActivity_ViewHolder>{
    private Context mContext;
    private ArrayList<MajalahItem> mMajalahItem;

    public MajalahAdapter(Context context, ArrayList<MajalahItem> Majalah_List){
        mContext = context;
        mMajalahItem = Majalah_List;
    }

    @Override
    public MajalahAdapter.MajalahActivity_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.majalah_list, parent, false);
        return new MajalahAdapter.MajalahActivity_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MajalahAdapter.MajalahActivity_ViewHolder holder, int i) {
        MajalahItem currentItem = mMajalahItem.get(i);

        String imageUrl = currentItem.getImage();
        String edisi = currentItem.getEdisi();
        String tahun = currentItem.getTahun();
        String file = currentItem.getFile();

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.mEdisi.setText(" ("+edisi+")");
        holder.mTahun.setText("Edisi : "+tahun);
        holder.mFile.setText(file);
    }

    @Override
    public int getItemCount() {
        return mMajalahItem.size();
    }

    public class MajalahActivity_ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mEdisi;
        public TextView mTahun;
        public TextView mFile;

        public MajalahActivity_ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.majalah_image);
            mEdisi = itemView.findViewById(R.id.majalah_edisi);
            mTahun = itemView.findViewById(R.id.majalah_tahun);
            mFile = itemView.findViewById(R.id.majalah_file);
        }
    }
}

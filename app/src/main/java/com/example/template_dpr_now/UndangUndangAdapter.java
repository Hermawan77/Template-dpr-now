package com.example.template_dpr_now;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class UndangUndangAdapter extends RecyclerView.Adapter<UndangUndangAdapter.UndangUndangActivity_ViewHolder> {
    private Context mContext;
    private ArrayList<UndangUndangItem> mUndanUndangItem;

    public UndangUndangAdapter(Context context, ArrayList<UndangUndangItem> UndangUndang_List){
        mContext = context;
        mUndanUndangItem = UndangUndang_List;
    }

    @Override
    public UndangUndangAdapter.UndangUndangActivity_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.undangundang_list, parent, false);
        return new UndangUndangAdapter.UndangUndangActivity_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UndangUndangAdapter.UndangUndangActivity_ViewHolder holder, int i) {
        UndangUndangItem currentItem = mUndanUndangItem.get(i);

        String nomor = currentItem.getNomor();
        String tentang = currentItem.getTentang();
        String doc = currentItem.getDoc();

        holder.mNomor.setText(nomor);
        holder.mTentang.setText(tentang);
        holder.mDoc.setText(doc);
    }

    @Override
    public int getItemCount() {
        return mUndanUndangItem.size();
    }

    public class UndangUndangActivity_ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNomor;
        public TextView mTentang;
        public TextView mDoc;

        public UndangUndangActivity_ViewHolder(View itemView) {
            super(itemView);
            mNomor = itemView.findViewById(R.id.nomoruu);
            mTentang = itemView.findViewById(R.id.tentanguu);
            mDoc = itemView.findViewById(R.id.docuu);
        }
    }

}

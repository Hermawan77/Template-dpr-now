 package com.example.template_dpr_now.Agenda_Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.template_dpr_now.R;

import java.util.ArrayList;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.Agenda_ViewHolder> {
    private Context mContext;
    private ArrayList<AgendaItem> mAgenda_List;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public AgendaAdapter(Context context, ArrayList<AgendaItem> Agenda_List){
        mContext = context;
        mAgenda_List = Agenda_List;
    }

    @Override
    public Agenda_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_agenda, parent, false);
        return new Agenda_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Agenda_ViewHolder holder, int position) {
        AgendaItem currentItem = mAgenda_List.get(position);

        String tanggal = currentItem.getTanggal();
        String jam = currentItem.getJam();
        String judul = currentItem.getJudul();
        String deskripsi = currentItem.getDeskripsi();

        holder.mTextViewTanggal.setText(tanggal);
        holder.mTextViewJam.setText(jam);
        holder.mTextViewJudul.setText(judul);
        holder.mTextViewDeskripsi.setText(deskripsi);
    }

    @Override
    public int getItemCount() {
        return mAgenda_List.size();
    }

    public class Agenda_ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewTanggal;
        public TextView mTextViewJam;
        public TextView mTextViewJudul;
        public TextView mTextViewDeskripsi;

        public Agenda_ViewHolder(View itemView) {
            super(itemView);
            mTextViewTanggal = itemView.findViewById(R.id.tanggal_agenda);
            mTextViewJam = itemView.findViewById(R.id.jam_agenda);
            mTextViewJudul = itemView.findViewById(R.id.judul_agenda);
            mTextViewDeskripsi = itemView.findViewById(R.id.deskripsi_agenda);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
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

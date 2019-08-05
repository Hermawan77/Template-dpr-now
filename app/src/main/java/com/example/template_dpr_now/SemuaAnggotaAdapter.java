package com.example.template_dpr_now;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SemuaAnggotaAdapter extends RecyclerView.Adapter<SemuaAnggotaAdapter.SemuaAnggota_ViewHolder> implements Filterable{
    private Context mContext;
    private ArrayList<SemuaAnggotaItem> Anggota_List;
    private ArrayList<SemuaAnggotaItem> Anggota_List_Filtered;

    public SemuaAnggotaAdapter(Context context, ArrayList<SemuaAnggotaItem> SemuaAnggota_List) {
        mContext = context;
        Anggota_List = SemuaAnggota_List;
        Anggota_List_Filtered = SemuaAnggota_List;
    }

    @Override
    public SemuaAnggotaAdapter.SemuaAnggota_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.semua_anggota_list, parent, false);
        return new SemuaAnggota_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SemuaAnggotaAdapter.SemuaAnggota_ViewHolder holder, int position) {
        SemuaAnggotaItem currentItem = Anggota_List.get(position);

        String imageUrl = currentItem.getImageUrl();
        String nama = currentItem.getNamaAnggota();
        String fraksi = currentItem.getFraksi();
        String dapil = currentItem.getDapil();
        String daftarAkd = currentItem.getDaftarAkd();

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mViewImage);
        holder.mNamaAnggota.setText(nama);
        holder.mFraksi.setText(fraksi);
        holder.mDapil.setText(dapil);
        holder.mDaftarAkd.setText(daftarAkd);
    }

    @Override
    public int getItemCount() {
        return Anggota_List_Filtered.size();
    }

    public class SemuaAnggota_ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mViewImage;
        public TextView mNamaAnggota;
        public TextView mFraksi;
        public TextView mDapil;
        public TextView mDaftarAkd;

        public SemuaAnggota_ViewHolder(View itemView) {
            super(itemView);
            mViewImage = itemView.findViewById(R.id.anggota_image);
            mNamaAnggota = itemView.findViewById(R.id.anggota_nama);
            mFraksi = itemView.findViewById(R.id.anggota_fraksi);
            mDapil = itemView.findViewById(R.id.anggota_dapil);
            mDaftarAkd = itemView.findViewById(R.id.anggota_daftarakd);
        }
    }


    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()){
                    Anggota_List_Filtered = Anggota_List;
                }

                else {
                    ArrayList<SemuaAnggotaItem> filteredList = new ArrayList<>();

                    for (SemuaAnggotaItem item : Anggota_List){
                        if (item.getNamaAnggota().toLowerCase().contains(charString)){
                            System.out.println("Isinya = " + charString + " : " + item.getNamaAnggota());
                            filteredList.add(item);
                        }
                    }

                    Anggota_List_Filtered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = Anggota_List_Filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Anggota_List = (ArrayList<SemuaAnggotaItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

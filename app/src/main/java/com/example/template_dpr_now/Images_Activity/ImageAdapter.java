package com.example.template_dpr_now.Images_Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.template_dpr_now.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    // Mendeklarasikan Variable
    private Context mContext;
    private List<ImagesUpload> mUploads;
    private OnItemClickListener mListener;

    // Memberi nilai
    public ImageAdapter(Context context, List<ImagesUpload> uploads){
        mContext = context;
        mUploads = uploads;
    }

    // Menampilkan slide_images_item.xmltem.xml
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.slide_images_item, parent, false);
        return new ImageViewHolder(v);
    }

    // Method untuk mnampilkan image sesuai urutan
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ImagesUpload uploadCurrent = mUploads.get(position);
        holder.textView.setText(uploadCurrent.getName());

        Picasso.get().load(uploadCurrent.getImageUrl())
                .fit()
                .centerInside()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    // Sebagai item clicker
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textView;
        public ImageView imageView;

        public ImageViewHolder(View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener!=null){
                int position = getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        // Menampilkan pilihan ketika item di tahan beberapa detik
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatEver = menu.add(Menu.NONE, 1,1,"Do What Ever");
            MenuItem delete = menu.add(Menu.NONE,2,2, "Delete");
            doWhatEver.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        // Clicker item
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListener!=null){
                int position = getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1 :
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2 :
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}

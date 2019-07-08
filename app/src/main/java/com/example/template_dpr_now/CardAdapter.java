package com.example.template_dpr_now;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ArrayList<Card> mCarddata;
    private Context mContext;

    public CardAdapter(Context context, ArrayList<Card> cardData){
        this.mCarddata = cardData;
        this.mContext = context;
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder holder, int position) {
        Card currentCard = mCarddata.get(position);
        holder.bindTo(currentCard);

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, holder.buttonViewOption);

                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.laporkan:
                                //Laporkan
                                break;
                            case R.id.sembunyikan:
                                //sembunyikan
                                break;
                                case R.id.default_activity_button:
                                    //default
                                    break;
                        }

                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCarddata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView buttonViewOption;

        ViewHolder( View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);

            itemView.setOnClickListener(this);
        }

        void bindTo(Card currentCard){
            mTitleText.setText(currentCard.getTitle());
            mInfoText.setText(currentCard.getInfo());
        }

        @Override
        public void onClick(View view) {
            Card currentCard = mCarddata.get(getAdapterPosition());

            Intent detailIntent = new Intent(mContext, MulaiActivity.class);
            detailIntent.putExtra("title", currentCard.getTitle());
            mContext.startActivity(detailIntent);
        }
    }
}

package com.example.template_dpr_now;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder( CardAdapter.ViewHolder holder, int position) {
        Card currentCard = mCarddata.get(position);
        holder.bindTo(currentCard);
    }

    @Override
    public int getItemCount() {
        return mCarddata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitileText;
        private TextView mInfoText;

        ViewHolder( View itemView) {
            super(itemView);

            mTitileText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);

            itemView.setOnClickListener(this);
        }

        void bindTo(Card currentCard){
            mTitileText.setText(currentCard.getTitle());
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

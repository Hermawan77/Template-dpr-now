package com.example.template_dpr_now.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.template_dpr_now.Card;
import com.example.template_dpr_now.CardAdapter;
import com.example.template_dpr_now.R;

import java.util.ArrayList;

public class FragmentPengaduan extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Card> mCardData;
    private CardAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_pengaduan);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCardData = new ArrayList<>();

        mAdapter = new CardAdapter(getActivity(), mCardData);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();


        return view;
    }

    private void initializeData() {
        String[] cardList = getResources().getStringArray(R.array.card_titles);
        String[] cardInfo = getResources().getStringArray(R.array.card_info);

        mCardData.clear();

        for (int i=0; i<cardList.length; i++){
            mCardData.add(new Card(cardList[i],cardInfo[i]));
        }

        mAdapter.notifyDataSetChanged();

    }

}

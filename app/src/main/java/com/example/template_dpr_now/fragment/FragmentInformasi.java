package com.example.template_dpr_now.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.template_dpr_now.Berita;
import com.example.template_dpr_now.R;

public class FragmentInformasi extends Fragment {

    private Button keBerita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informasi, container, false);

        keBerita = view.findViewById(R.id.berita);

        keBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Berita.class);
                startActivity(intent);
            }
        });

        return view;
    }
}

package com.example.template_dpr_now.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.template_dpr_now.R;

import java.util.ArrayList;
import java.util.List;

public class KomisiFragment extends Fragment {

    // Mendeklarasikan Variable
    private ViewPager mViewPager;
    private SectionsPageAdapter mSectionPageAdapter;

    // Menampilkan fragment_komisi.xml
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Memberi nilai
        View view = inflater.inflate(R.layout.fragment_komisi, container, false);
        mSectionPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        mViewPager = view.findViewById(R.id.container_komisi);
        setupViewPager (mViewPager);

        TabLayout tabLayout = view.findViewById(R.id.tabs_komisi);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    // Menampilkan tombol atau tulisan untuk pindah ke fragment lain
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new F_K1(), "Komisi I");
        adapter.addFragment(new F_K2(), "Komisi II");
        adapter.addFragment(new F_K3(), "Komisi III");
        adapter.addFragment(new F_K4(), "Komisi IV");
        adapter.addFragment(new F_K5(), "Komisi V");
        adapter.addFragment(new F_K6(), "Komisi VI");
        viewPager.setAdapter(adapter);
    }

    // Adapter
    public class SectionsPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public SectionsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}

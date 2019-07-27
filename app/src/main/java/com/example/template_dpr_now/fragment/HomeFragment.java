package com.example.template_dpr_now.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.template_dpr_now.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // Mendeklarasikan Variable
    private ViewPager mViewPager;
    private SectionsPageAdapter mSectionsPageAdapter;
    Toolbar toolbarhome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    // Memanggil layout fragment_home
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Memberikan nilai
        mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        toolbarhome = view.findViewById(R.id.toolbarmenu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarhome);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");
        toolbarhome.setLogo(R.drawable.ic_email);
        return view;

    }

    // Menampilkan tombol atau tulisan untuk pindah ke fragment lain berupa Tab View
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentPengaduan(), "PENGADUAN");
        adapter.addFragment(new FragmentAspirasi(), "ASPIRASI");
        adapter.addFragment(new FragmentInformasi(), "INFORMASI");
        viewPager.setAdapter(adapter);
    }

    // Adapter 3 fragment
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

package com.example.template_dpr_now;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.template_dpr_now.fragment.FAB;
import com.example.template_dpr_now.fragment.HomeFragment;
import com.example.template_dpr_now.fragment.KomisiFragment;
import com.example.template_dpr_now.fragment.LainnyaFragment;
import com.example.template_dpr_now.fragment.StreamingFragment;

public class MainActivity extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "google_account";
    //public static final String DATABASE_NAME = "db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Theme.createTheme(this);

        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_komisi:
                            selectedFragment = new KomisiFragment();
                            break;
                        case R.id.nav_add:
                            selectedFragment = new FAB();
                            break;
                        case R.id.nav_streaming:
                            selectedFragment = new StreamingFragment();
                            break;
                        case R.id.nav_lainnya:
                            selectedFragment = new LainnyaFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

}

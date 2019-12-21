package com.example.fyp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.fyp.ui.fragment.fragOne;
import com.example.fyp.ui.fragment.fragTwo;
import com.example.fyp.viewAdapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class display extends AppCompatActivity {

    private TabLayout tabs;
    private int[] tabIcons = {
            R.drawable.ic_air_conditioner_remote_control_free_icon_1,
            R.drawable.iconthermostat,
            R.drawable.ic_humidity_svgrepo_com,
            R.drawable.icon_co2,
            R.drawable.icon_pm10,
            R.drawable.iconpm25,
            R.drawable.icon_voc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons(){
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragOne(), "Control");
        adapter.addFragment(new fragTwo(), "Temp");

        viewPager.setAdapter(adapter);
    }
}
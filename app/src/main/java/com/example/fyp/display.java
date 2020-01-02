package com.example.fyp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.fyp.Storage.PrefKey;
import com.example.fyp.ui.fragment.fragCO2;
import com.example.fyp.ui.fragment.fragControl;
import com.example.fyp.ui.fragment.fragHumi;
import com.example.fyp.ui.fragment.fragPM10;
import com.example.fyp.ui.fragment.fragPM25;
import com.example.fyp.ui.fragment.fragTemp;
import com.example.fyp.ui.fragment.fragVOC;
import com.example.fyp.viewAdapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class display extends AppCompatActivity implements PrefKey {

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

    private String roomName;
    private Integer position;
    private String myString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

         roomName = getIntent().getStringExtra(BUNDLE_ROOM);
         position = getIntent().getIntExtra(BUNDLE_POSITION, 0);

//        Toast toast = Toast.makeText( display.this, position.toString(), Toast.LENGTH_SHORT);
//        toast.show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(roomName);
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
        tabs.getTabAt(2).setIcon(tabIcons[2]);
        tabs.getTabAt(3).setIcon(tabIcons[3]);
        tabs.getTabAt(4).setIcon(tabIcons[4]);
        tabs.getTabAt(5).setIcon(tabIcons[5]);
        tabs.getTabAt(6).setIcon(tabIcons[6]);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragControl(), "Control");
        adapter.addFragment(new fragTemp(), "Temp");
        adapter.addFragment(new fragHumi(), "Humi");
        adapter.addFragment(new fragCO2(), "CO2");
        adapter.addFragment(new fragPM10(), "PM10");
        adapter.addFragment(new fragPM25(), "PM2.5");
        adapter.addFragment(new fragVOC(), "VOC");

        viewPager.setAdapter(adapter);
    }

}
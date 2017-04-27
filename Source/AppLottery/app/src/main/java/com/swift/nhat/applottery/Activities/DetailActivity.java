package com.swift.nhat.applottery.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.swift.nhat.applottery.Adapters.PagerAdapter;
import com.swift.nhat.applottery.R;
import com.swift.nhat.applottery.Utils.DataExtral;

import java.util.ArrayList;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //getSupportActionBar().hide();

        setTheme(R.style.AppTheme1);
        Intent intent = getIntent();
        DataExtral data = intent.getParcelableExtra("data");
        Map<String, ArrayList<String>> data_map = data.getData_map();


        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager, data_map);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
    }
}

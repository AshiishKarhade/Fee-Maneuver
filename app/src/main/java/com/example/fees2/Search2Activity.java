package com.example.fees2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fees2.Fragments.general_fragment;
import com.example.fees2.Fragments.home_sections_pager_adapter;
import com.example.fees2.Fragments.obc_fragment;
import com.example.fees2.Fragments.oms_fragment;
import com.example.fees2.Fragments.sc_fragment;
import com.example.fees2.Fragments.st_fragment;
import com.google.android.material.tabs.TabLayout;

public class Search2Activity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_search2);

        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabs);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new general_fragment(), "General");
        adapter.addFragment(new oms_fragment(), "OMS");
        adapter.addFragment(new obc_fragment(), "OBC");
        adapter.addFragment(new sc_fragment(), "SC");
        adapter.addFragment(new st_fragment(), "ST");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

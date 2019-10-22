package com.example.fees2.Fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class home_sections_pager_adapter extends FragmentPagerAdapter {

    private static final String tag="fragment pager adapter";
    private final List<Fragment>mfragmentlist=new ArrayList<> ();

    public home_sections_pager_adapter(@NonNull FragmentManager fm) {
        super (fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentlist.size ();
    }
    public void addfragment(Fragment fragment){
        mfragmentlist.add (fragment);
    }
}

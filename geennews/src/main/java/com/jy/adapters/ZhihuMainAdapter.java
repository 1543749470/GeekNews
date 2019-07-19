package com.jy.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZhihuMainAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments ;


    public ZhihuMainAdapter(FragmentManager childFragmentManager, List<Fragment> fragments) {
        super(childFragmentManager);

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

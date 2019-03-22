package com.laotian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainfagmentAdapter  extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;
    public MainfagmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragmentList = list;
        this.fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

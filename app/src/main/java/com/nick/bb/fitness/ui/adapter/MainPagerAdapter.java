package com.nick.bb.fitness.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sharpay on 17-3-23.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    List<String> titleList;
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        this.titleList = new ArrayList<>();
        this.fragmentList = new ArrayList<>();
    }

    public void setList( List<Fragment> fragments,List<String> titleList){
        this.titleList.addAll(titleList);
        fragmentList.addAll(fragments);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

}
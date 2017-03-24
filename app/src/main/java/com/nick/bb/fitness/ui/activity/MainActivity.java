package com.nick.bb.fitness.ui.activity;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nick.bb.fitness.R;
import com.nick.bb.fitness.ui.adapter.MainPagerAdapter;
import com.nick.bb.fitness.ui.fragment.BeautyFragment;
import com.nick.bb.fitness.ui.fragment.GankFragment;
import com.nick.bb.fitness.ui.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.tab_container)
    ViewPager tabContainer;
    MainPagerAdapter pagerAdapter;
    List<Fragment> fragmentList;
    List<String>  titleList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        fragmentList = new ArrayList<Fragment>();
        Bundle bundle = new Bundle();
        GankFragment allFragment = new GankFragment();
        bundle.putString("type","all");
        allFragment.setArguments(bundle);
        fragmentList.add(allFragment);


        BeautyFragment beautyFragment = new BeautyFragment();
        fragmentList.add(beautyFragment);

        GankFragment androidFragment = new GankFragment();
        Bundle androidBundle = new Bundle();
        androidBundle.putString("type","Android");
        androidFragment.setArguments(androidBundle);
        fragmentList.add(androidFragment);

        GankFragment iosFragment = new GankFragment();
        Bundle iosBundle = new Bundle();
        iosBundle.putString("type","iOS");
        iosFragment.setArguments(iosBundle);
        fragmentList.add(iosFragment);


        GankFragment webFragment = new GankFragment();
        Bundle webBundle = new Bundle();
        webBundle .putString("type","前端");
        webFragment.setArguments(webBundle);
        fragmentList.add(webFragment);

        GankFragment restFragment = new GankFragment();
        Bundle restBundle = new Bundle();
        restBundle.putString("type","休息视频");
        restFragment.setArguments(restBundle);
        fragmentList.add(restFragment);

        titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("美女");
        titleList.add("Android");
        titleList.add("IOS");
        titleList.add("前端");
        titleList.add("休息视频");
        pagerAdapter.setList(fragmentList,titleList);
        pagerAdapter.notifyDataSetChanged();
    }


    void initView(){
        pagerAdapter = new MainPagerAdapter(this.getSupportFragmentManager());
        tabContainer.setAdapter(pagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(tabContainer);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(tabContainer) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.invalidate();

    }
}

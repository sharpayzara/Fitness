package com.nick.bb.fitness.ui.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by sharpay on 17-3-24.
 */

public abstract class BaseFragment extends Fragment{
    protected View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preView();
        view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this,view);
        initView();
        initData();
        handData();
        return view;
    }

    protected abstract int getLayoutResId();

    protected void preView(){}

    protected void initView(){}

    protected void initData(){}

    protected void handData(){}

}

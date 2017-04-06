package com.nick.bb.fitness.ui.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nick.bb.fitness.injector.HasComponent;

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

    protected abstract void initView();

    protected abstract void initData();

    protected void handData(){}

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}

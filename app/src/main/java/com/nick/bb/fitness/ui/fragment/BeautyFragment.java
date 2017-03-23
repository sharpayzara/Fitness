package com.nick.bb.fitness.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.R;
import com.nick.bb.fitness.api.entity.BeautyBean;
import com.nick.bb.fitness.injector.components.ApplicationComponent;
import com.nick.bb.fitness.injector.components.DaggerBeautyListComponent;
import com.nick.bb.fitness.injector.components.BeautyListComponent;
import com.nick.bb.fitness.injector.modules.ActivityModule;
import com.nick.bb.fitness.injector.modules.BeautyListModule;
import com.nick.bb.fitness.mvp.contract.BeautyListContract;
import com.nick.bb.fitness.ui.adapter.BeautyListRecyclerAdapter;
import com.nick.bb.fitness.ui.widget.LoadingLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sharpay on 17-3-23.
 */

public class BeautyFragment extends Fragment implements BeautyListContract.View {
    protected View view;
    @Inject
    BeautyListContract.Presenter presenter;
    BeautyListRecyclerAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView BeautyListRecycle;
    @BindView(R.id.loading_layout)
    LoadingLayout loadingLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_beauty, container, false);
        ButterKnife.bind(this, view);
        mAdapter = new BeautyListRecyclerAdapter(this.getActivity());
        BeautyListRecycle.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        BeautyListRecycle.setAdapter(mAdapter);
        injectDependences();
        presenter.attachView(this);
        loadBeautyList();
        return view;
    }

    private void injectDependences() {
        ApplicationComponent applicationComponent = ((AndroidApplication) this.getActivity().getApplication()).getApplicationComponent();
        BeautyListComponent BeautyListComponent = DaggerBeautyListComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this.getActivity()))
                .beautyListModule(new BeautyListModule())
                .build();
        BeautyListComponent.inject(this);
    }

    public void loadBeautyList() {
        presenter.loadBeautyList();
    }

    @Override
    public void showBeautyList(List<BeautyBean> BeautyBean) {
        mAdapter.setBeautyList(BeautyBean);
    }

    @Override
    public void showProgressBar() {
        loadingLayout.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        loadingLayout.hideProgressBar();
    }

    @Override
    public void showErrorView() {
        loadingLayout.showErrorView();
    }

    @Override
    public void showWifiView() {
        loadingLayout.showWifiView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
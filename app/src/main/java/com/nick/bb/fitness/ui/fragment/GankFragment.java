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
import com.nick.bb.fitness.api.entity.GankBean;
import com.nick.bb.fitness.injector.components.ApplicationComponent;
import com.nick.bb.fitness.injector.components.DaggerGankListComponent;
import com.nick.bb.fitness.injector.components.GankListComponent;
import com.nick.bb.fitness.injector.modules.ActivityModule;
import com.nick.bb.fitness.injector.modules.GankListModule;
import com.nick.bb.fitness.mvp.contract.GankListContract;
import com.nick.bb.fitness.ui.adapter.GankListRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sharpay on 17-3-23.
 */

public class GankFragment extends Fragment implements GankListContract.View {
    protected View view;
    @Inject
    GankListContract.Presenter presenter;
    GankListRecyclerAdapter mAdapter;
    @BindView(R.id.gank_list_recycle)
    RecyclerView gankListRecycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_gank, container, false);
        ButterKnife.bind(this, view);
        mAdapter = new GankListRecyclerAdapter(this.getActivity());
        gankListRecycle.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        gankListRecycle.setAdapter(mAdapter);
        injectDependences();
        presenter.attachView(this);
        loadGankList();
        return view;
    }

    private void injectDependences() {
        ApplicationComponent applicationComponent = ((AndroidApplication) this.getActivity().getApplication()).getApplicationComponent();
        GankListComponent gankListComponent = DaggerGankListComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this.getActivity()))
                .gankListModule(new GankListModule())
                .build();
        gankListComponent.inject(this);
    }

    public void loadGankList() {
        presenter.loadGankList();
    }

    @Override
    public void showGankList(List<GankBean> gankBean) {
        mAdapter.setGankList(gankBean);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

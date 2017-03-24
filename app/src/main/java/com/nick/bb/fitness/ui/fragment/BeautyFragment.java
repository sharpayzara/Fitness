package com.nick.bb.fitness.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.nick.bb.fitness.ui.listener.NoDoubleClickListener;
import com.nick.bb.fitness.ui.widget.LMRecyclerView;
import com.nick.bb.fitness.ui.widget.LoadingLayout;
import com.nick.bb.fitness.util.TipUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sharpay on 17-3-23.
 */

public class BeautyFragment extends Fragment implements BeautyListContract.View,LMRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    protected View view;
    @Inject
    BeautyListContract.Presenter presenter;
    BeautyListRecyclerAdapter mAdapter;
    @BindView(R.id.recycler_view)
    LMRecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.loading_layout)
    LoadingLayout loadingLayout;

    int page = 1;
    int size = 20;
    boolean canLoadMore = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_beauty, container, false);
        ButterKnife.bind(this, view);
        mAdapter = new BeautyListRecyclerAdapter(this.getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadMoreListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
        injectDependences();
        presenter.attachView(this);
        loadBeautyList();
        loadingLayout.setOnRetryClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                showProgressBar();
                presenter.loadBeautyList(page,size);
            }
        });
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
        presenter.loadBeautyList(page,size);
    }

    @Override
    public void showBeautyList(List<BeautyBean> BeautyBean) {
        mAdapter.setBeautyList(BeautyBean);
    }

    @Override
    public void showProgressBar() {
        if(loadingLayout != null) {
            loadingLayout.showProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        if(loadingLayout != null){
            loadingLayout.hideProgressBar();
        }
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showErrorView() {
        if(loadingLayout != null) {
            loadingLayout.showErrorView();
        }
    }

    @Override
    public void showWifiView() {
        if(loadingLayout != null) {
            loadingLayout.showWifiView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void loadMore() {
        if (canLoadMore) {
            if (!swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(true);
            }
            page++;
            presenter.loadBeautyList(page, size);
        } else {
            TipUtil.showSnackTip(recyclerView, "没有更多数据了!");
        }
    }

    @Override
    public void onRefresh() {
        canLoadMore = true;
        page = 1;
        mAdapter.setBeautyList(null);
        mAdapter.notifyDataSetChanged();
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        presenter.loadBeautyList(page,size);
    }
}
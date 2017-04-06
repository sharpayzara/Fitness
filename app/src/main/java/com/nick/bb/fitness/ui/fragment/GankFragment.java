package com.nick.bb.fitness.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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
import com.nick.bb.fitness.ui.fragment.base.BaseFragment;
import com.nick.bb.fitness.ui.listener.NoDoubleClickListener;
import com.nick.bb.fitness.ui.widget.LMRecyclerView;
import com.nick.bb.fitness.ui.widget.LoadingLayout;
import com.nick.bb.fitness.util.TipUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
/**
 * Created by sharpay on 17-3-23.
 */

public class GankFragment  extends BaseFragment implements GankListContract.View,LMRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    protected View view;
    @Inject
    GankListContract.Presenter presenter;
    GankListRecyclerAdapter mAdapter;
    @BindView(R.id.recycler_view)
    LMRecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.loading_layout)
    LoadingLayout loadingLayout;

    int page = 1;
    int size = 20;
    String type;
    boolean canLoadMore = true;

    @Override
    protected void preView() {
        type = getArguments().getString("type");
    }

    @Override
    protected void initView() {
        mAdapter = new GankListRecyclerAdapter(this.getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadMoreListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
        injectDependences();
        presenter.attachView(this);
        loadingLayout.setOnRetryClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                showProgressBar();
                presenter.loadGankList(page, size,type);
            }
        });
    }

    @Override
    protected void initData() {
        loadGankList();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gank;
    }

    private void injectDependences() {
       // this.getComponent(HomeComponent.class).inject(this);


        ApplicationComponent applicationComponent = ((AndroidApplication) this.getActivity().getApplication()).getApplicationComponent();
        GankListComponent gankListComponent= DaggerGankListComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this.getActivity()))
                .gankListModule(new GankListModule())
                .build();
        gankListComponent.inject(this);
    }

    public void loadGankList() {
        presenter.loadGankList(page, size,type);
    }

    @Override
    public void showGankList(List<GankBean> GankBean) {
        mAdapter.setGankList(GankBean);
    }

    @Override
    public void showProgressBar() {
        if (loadingLayout != null) {
            loadingLayout.showProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        if (loadingLayout != null) {
            loadingLayout.hideProgressBar();
        }
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showErrorView() {
        if (loadingLayout != null) {
            loadingLayout.showErrorView();
        }
    }

    @Override
    public void showWifiView() {
        if (loadingLayout != null) {
            loadingLayout.showWifiView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void loadMore() {
        if (canLoadMore) {
            page++;
            presenter.loadGankList(page, size,type);
        } else {
            TipUtil.showSnackTip(recyclerView, "没有更多数据了!");
        }
    }

    @Override
    public void onRefresh() {
        canLoadMore = true;
        page = 1;
        mAdapter.setGankList(null);
        mAdapter.notifyDataSetChanged();
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        presenter.loadGankList(page, size,type);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}

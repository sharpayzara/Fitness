package com.nick.bb.fitness.mvp.contract;

import com.nick.bb.fitness.api.entity.GankBean;
import com.nick.bb.fitness.mvp.presenter.base.BasePresenter;
import com.nick.bb.fitness.mvp.view.BaseView;

import java.util.List;

/**
 * Created by sharpay on 17-3-22.
 */

public interface GankListContract {
    interface View extends BaseView{
        void showGankList(List<GankBean> albumList);
        void showProgressBar();
        void hideProgressBar();
        void showErrorView();
        void showWifiView();
    }

    interface Presenter extends BasePresenter<View>{
        void loadGankList(int page,int size,String type);
    }
}

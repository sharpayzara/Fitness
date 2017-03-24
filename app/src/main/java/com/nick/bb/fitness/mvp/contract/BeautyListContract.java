package com.nick.bb.fitness.mvp.contract;

import com.nick.bb.fitness.api.entity.BeautyBean;
import com.nick.bb.fitness.api.entity.GankBean;
import com.nick.bb.fitness.mvp.presenter.base.BasePresenter;
import com.nick.bb.fitness.mvp.view.BaseView;

import java.util.List;

/**
 * Created by sharpay on 17-3-22.
 */

public interface BeautyListContract {
    interface View extends BaseView{
        void showBeautyList(List<BeautyBean> albumList);
        void showProgressBar();
        void hideProgressBar();
        void showErrorView();
        void showWifiView();
    }

    interface Presenter extends BasePresenter<View>{
        void loadBeautyList(int page,int size);
    }
}

package com.nick.bb.fitness.mvp.presenter.base;

import com.nick.bb.fitness.mvp.view.BaseView;

/**
 * Created by hefuyi on 2016/11/7.
 */

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void subscribe();

    void unsubscribe();
}

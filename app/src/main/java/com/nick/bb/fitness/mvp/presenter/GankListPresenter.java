package com.nick.bb.fitness.mvp.presenter;

import android.util.Log;

import com.nick.bb.fitness.api.entity.decor.GankList;
import com.nick.bb.fitness.mvp.contract.GankListContract;
import com.nick.bb.fitness.mvp.usercase.GetGankList;
import com.nick.bb.fitness.mvp.view.BaseView;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sharpay on 17-3-22.
 */

public class GankListPresenter implements GankListContract.Presenter {
    private GankListContract.View mView;
    private GetGankList mUsecase;
    private CompositeSubscription mCompositeSubscription;

    public GankListPresenter(GetGankList mUsecase) {
        this.mUsecase = mUsecase;
    }


    @Override
    public void attachView(GankListContract.View view) {
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void loadGankList() {
        mCompositeSubscription.clear();
        Subscription subscription = mUsecase.execute(new GetGankList.RequestValues())
                .getGankList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GankList>() {
                    @Override
                    public void call(GankList gankList) {
                        mView.showGankList(gankList.getResults());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        Log.d("dsfa","sdfasga"+throwable.toString());
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}

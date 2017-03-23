package com.nick.bb.fitness.mvp.presenter;

import android.util.Log;

import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.mvp.contract.BeautyListContract;
import com.nick.bb.fitness.mvp.usercase.GetBeautyList;
import com.nick.bb.fitness.util.NetworkUtil;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sharpay on 17-3-22.
 */

public class BeautyListPresenter implements BeautyListContract.Presenter {
    private BeautyListContract.View mView;
    private GetBeautyList mUsecase;
    private CompositeSubscription mCompositeSubscription;

    public BeautyListPresenter(GetBeautyList mUsecase) {
        this.mUsecase = mUsecase;
    }


    @Override
    public void attachView(BeautyListContract.View view) {
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
    public void loadBeautyList() {
        mCompositeSubscription.clear();
        mView.showProgressBar();
        Subscription subscription = mUsecase.execute(new GetBeautyList.RequestValues())
                .getBeautyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BeautyList>() {
                    @Override
                    public void call(BeautyList BeautyList) {
                        mView.hideProgressBar();
                        mView.showBeautyList(BeautyList.getResults());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.hideProgressBar();
                        if (!NetworkUtil.isConnectedByState(AndroidApplication.getContext())) {
                            mView.showWifiView();
                        }else {
                            mView.showErrorView();
                        }
                        Log.d("dsfa",throwable.toString());
                    }
                });
        mCompositeSubscription.add(subscription);
    }

}

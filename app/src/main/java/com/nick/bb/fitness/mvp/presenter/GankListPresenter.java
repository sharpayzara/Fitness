package com.nick.bb.fitness.mvp.presenter;

import android.util.Log;

import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.api.entity.decor.GankList;
import com.nick.bb.fitness.mvp.contract.GankListContract;
import com.nick.bb.fitness.mvp.usercase.GetGankList;
import com.nick.bb.fitness.util.NetworkUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
/**
 * Created by sharpay on 17-3-22.
 */

public class GankListPresenter implements GankListContract.Presenter {
    private GankListContract.View mView;
    private GetGankList mUsecase;

    @Inject
    public GankListPresenter(GetGankList mUsecase) {
        this.mUsecase = mUsecase;
    }


    @Override
    public void attachView(GankListContract.View view) {
        mView = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mUsecase.dispose();
    }

    @Override
    public void loadGankList(int page,int size,String type) {
        mView.showProgressBar();
        mUsecase.execute(new GankListObserver(),new GetGankList.Params(page,size,type));
    }
    private final class GankListObserver extends DisposableObserver<GankList> {

        @Override
        public void onNext(GankList gankList) {
            mView.showGankList(gankList.getResults());
        }

        @Override
        public void onError(Throwable throwable) {
            if (!NetworkUtil.isConnectedByState(AndroidApplication.getContext())) {
                mView.showWifiView();
            }else {
                mView.showErrorView();
            }
        }

        @Override
        public void onComplete() {
            mView.hideProgressBar();
        }
    }
}

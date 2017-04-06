package com.nick.bb.fitness.mvp.presenter;

import android.util.Log;

import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.api.entity.decor.BeautyList;
import com.nick.bb.fitness.mvp.contract.BeautyListContract;
import com.nick.bb.fitness.mvp.usercase.GetBeautyList;
import com.nick.bb.fitness.util.NetworkUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by sharpay on 17-3-22.
 */

public class BeautyListPresenter  implements BeautyListContract.Presenter {
    private BeautyListContract.View mView;
    private GetBeautyList mUsecase;

    @Inject
    public BeautyListPresenter(GetBeautyList mUsecase) {
        this.mUsecase = mUsecase;
    }


    @Override
    public void attachView(BeautyListContract.View view) {
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
    public void loadBeautyList(int page, int size) {
        mView.showProgressBar();
        mUsecase.execute(new BeautyListPresenter.BeautyListObserver(),new GetBeautyList.Params(page,size));
    }

    private final class BeautyListObserver extends DisposableObserver<BeautyList> {

        @Override
        public void onNext(BeautyList gankList) {
            mView.showBeautyList(gankList.getResults());
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

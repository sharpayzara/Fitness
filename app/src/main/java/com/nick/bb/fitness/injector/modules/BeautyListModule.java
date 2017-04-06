package com.nick.bb.fitness.injector.modules;

import com.nick.bb.fitness.mvp.contract.BeautyListContract;
import com.nick.bb.fitness.mvp.presenter.BeautyListPresenter;
import com.nick.bb.fitness.mvp.usercase.GetBeautyList;
import com.nick.bb.fitness.repository.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sharpay on 17-3-22.
 */
@Module
public class BeautyListModule {

    @Provides
    BeautyListContract.Presenter getBeautyListPresenter(BeautyListPresenter beautyListPresenter) {
        return beautyListPresenter;
    }
}

package com.nick.bb.fitness.injector.modules;

import com.nick.bb.fitness.api.entity.decor.GankList;
import com.nick.bb.fitness.mvp.contract.GankListContract;
import com.nick.bb.fitness.mvp.presenter.GankListPresenter;
import com.nick.bb.fitness.mvp.usercase.GetGankList;
import com.nick.bb.fitness.repository.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sharpay on 17-3-22.
 */
@Module
public class GankListModule {

    @Provides
    GankListContract.Presenter getGankListPresenter(GankListPresenter presenter) {
        return presenter;
    }
}

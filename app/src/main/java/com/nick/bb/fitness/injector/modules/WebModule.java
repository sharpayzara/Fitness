package com.nick.bb.fitness.injector.modules;

import android.app.Activity;
import android.content.Context;

import com.nick.bb.fitness.mvp.contract.WebContract;
import com.nick.bb.fitness.mvp.presenter.WebPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sharpay on 17-3-22.
 */
@Module
public class WebModule {

    @Provides
    WebContract.Presenter getWebPresenter(Context context) {
        return new WebPresenter(context);
    }
    
}

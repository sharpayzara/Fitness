package com.nick.bb.fitness.injector.modules;

import android.app.Application;

import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.injector.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sharpay on 17-3-22.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication mApplication;

    public ApplicationModule(AndroidApplication application) {
        this.mApplication = application;
    }

    @Provides
    @PerApplication
    public AndroidApplication provideAndroidApplication() {
        return mApplication;
    }

    @Provides
    @PerApplication
    public Application provideApplication() {
        return mApplication;
    }
}

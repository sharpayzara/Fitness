package com.nick.bb.fitness.injector.modules;

import android.app.Application;

import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.executor.JobExecutor;
import com.nick.bb.fitness.executor.PostExecutionThread;
import com.nick.bb.fitness.executor.ThreadExecutor;
import com.nick.bb.fitness.executor.UIThread;
import com.nick.bb.fitness.injector.scope.PerApplication;

import javax.inject.Singleton;

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
    AndroidApplication provideAndroidApplication() {
        return mApplication;
    }

    @Provides
    @PerApplication
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PerApplication
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @PerApplication
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}

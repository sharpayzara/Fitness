package com.nick.bb.fitness;

import android.app.Application;
import android.content.Context;

import com.jiongbull.jlog.JLog;
import com.nick.bb.fitness.injector.components.ApplicationComponent;
import com.nick.bb.fitness.injector.components.DaggerApplicationComponent;
import com.nick.bb.fitness.injector.modules.ApplicationModule;
import com.nick.bb.fitness.injector.modules.NetworkModule;

/**
 * Created by sharpay on 17-3-22.
 */

public class AndroidApplication extends Application{
    private static Context sContext;
    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        JLog.init(this)
                .setDebug(BuildConfig.DEBUG);
        sContext = getApplicationContext();
        setupInjector();
    }
    private void setupInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getContext() {
        return sContext;
    }
}

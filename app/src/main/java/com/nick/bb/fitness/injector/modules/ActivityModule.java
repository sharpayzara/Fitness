package com.nick.bb.fitness.injector.modules;

import android.app.Activity;
import android.content.Context;
import com.nick.bb.fitness.injector.scope.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sharpay on 17-3-22.
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Context provideContext(){
        return mActivity;
    }
}

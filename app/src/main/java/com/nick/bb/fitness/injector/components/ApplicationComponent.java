package com.nick.bb.fitness.injector.components;

import android.app.Application;

import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.executor.PostExecutionThread;
import com.nick.bb.fitness.executor.ThreadExecutor;
import com.nick.bb.fitness.injector.modules.ApplicationModule;
import com.nick.bb.fitness.injector.modules.NetworkModule;
import com.nick.bb.fitness.injector.scope.PerApplication;
import com.nick.bb.fitness.repository.Repository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sharpay on 17-3-22.
 */
@PerApplication
@Component(modules = {ApplicationModule.class,NetworkModule.class})
public interface ApplicationComponent {
    Application application();

    Repository repository();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}

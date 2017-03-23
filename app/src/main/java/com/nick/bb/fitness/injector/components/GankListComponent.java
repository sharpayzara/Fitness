package com.nick.bb.fitness.injector.components;

import com.nick.bb.fitness.injector.modules.ActivityModule;
import com.nick.bb.fitness.injector.modules.GankListModule;
import com.nick.bb.fitness.injector.scope.PerActivity;
import com.nick.bb.fitness.ui.activity.MainActivity;
import com.nick.bb.fitness.ui.fragment.GankFragment;

import dagger.Component;

/**
 * Created by sharpay on 17-3-22.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, GankListModule.class})
public interface GankListComponent {
    void inject(GankFragment fragment);
}

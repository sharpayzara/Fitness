package com.nick.bb.fitness.injector.components;

import com.nick.bb.fitness.injector.modules.ActivityModule;
import com.nick.bb.fitness.injector.modules.BeautyListModule;
import com.nick.bb.fitness.injector.modules.GankListModule;
import com.nick.bb.fitness.injector.scope.PerActivity;
import com.nick.bb.fitness.ui.fragment.BeautyFragment;
import com.nick.bb.fitness.ui.fragment.GankFragment;

import dagger.Component;

/**
 * Created by sharpay on 17-3-22.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, BeautyListModule.class})
public interface BeautyListComponent {
    void inject(BeautyFragment fragment);
}

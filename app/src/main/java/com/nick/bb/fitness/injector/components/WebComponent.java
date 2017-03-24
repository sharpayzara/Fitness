package com.nick.bb.fitness.injector.components;

import com.nick.bb.fitness.injector.modules.ActivityModule;
import com.nick.bb.fitness.injector.modules.GankListModule;
import com.nick.bb.fitness.injector.modules.WebModule;
import com.nick.bb.fitness.injector.scope.PerActivity;
import com.nick.bb.fitness.mvp.contract.WebContract;
import com.nick.bb.fitness.ui.activity.WebActivity;
import com.nick.bb.fitness.ui.fragment.GankFragment;

import dagger.Component;

/**
 * Created by sharpay on 17-3-22.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, WebModule.class})
public interface WebComponent {
    void inject(WebActivity activity);
}

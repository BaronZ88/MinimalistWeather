package com.baronzhang.android.weather.activity.component;

import com.baronzhang.android.weather.activity.MainActivity;
import com.baronzhang.android.weather.activity.module.HomePageModule;
import com.baronzhang.android.weather.util.ActivityScoped;
import com.baronzhang.android.weather.ApplicationComponent;

import dagger.Component;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/29
 */
@ActivityScoped
@Component(modules = HomePageModule.class, dependencies = ApplicationComponent.class)
public interface HomePageComponent {

    void inject(MainActivity mainActivity);
}

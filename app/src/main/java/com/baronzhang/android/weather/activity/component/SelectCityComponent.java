package com.baronzhang.android.weather.activity.component;

import com.baronzhang.android.weather.activity.SelectCityActivity;
import com.baronzhang.android.weather.activity.module.SelectCityModule;
import com.baronzhang.android.weather.util.ActivityScoped;

import dagger.Component;
import com.baronzhang.android.weather.ApplicationComponent;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@ActivityScoped
@Component(modules = SelectCityModule.class, dependencies = ApplicationComponent.class)
public interface SelectCityComponent {

    void inject(SelectCityActivity selectCityActivity);
}

package com.baronzhang.android.weather.activity.component;

import com.baronzhang.android.weather.activity.CityManagerActivity;

import dagger.Component;
import com.baronzhang.android.weather.ApplicationComponent;
import com.baronzhang.android.weather.activity.module.CityManagerModule;
import com.baronzhang.android.weather.util.ActivityScoped;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@ActivityScoped
@Component(modules = CityManagerModule.class, dependencies = ApplicationComponent.class)
public interface CityManagerComponent {

    void inject(CityManagerActivity cityManagerActivity);
}

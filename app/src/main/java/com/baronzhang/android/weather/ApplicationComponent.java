package com.baronzhang.android.weather;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author 张磊 (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    WeatherApplication getApplication();

    Context getContext();
}

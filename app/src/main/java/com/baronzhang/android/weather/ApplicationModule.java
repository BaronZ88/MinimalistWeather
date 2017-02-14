package com.baronzhang.android.weather;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {

        this.context = context;
    }

    @Provides
    @Singleton
    WeatherApplication provideApplication() {

        return (WeatherApplication) context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext() {

        return context;
    }
}

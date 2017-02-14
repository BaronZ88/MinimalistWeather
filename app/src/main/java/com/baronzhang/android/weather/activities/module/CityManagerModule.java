package com.baronzhang.android.weather.activities.module;

import com.baronzhang.android.weather.contracts.CityManagerContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@Module
public class CityManagerModule {

    private CityManagerContract.View view;

    public CityManagerModule(CityManagerContract.View view) {
        this.view = view;
    }

    @Provides
    CityManagerContract.View provideCityManagerContactView() {
        return view;
    }
}

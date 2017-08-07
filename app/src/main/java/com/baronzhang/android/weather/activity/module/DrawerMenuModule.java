package com.baronzhang.android.weather.activity.module;

import com.baronzhang.android.weather.contract.DrawerContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@Module
public class DrawerMenuModule {

    private DrawerContract.View view;

    public DrawerMenuModule(DrawerContract.View view) {
        this.view = view;
    }

    @Provides
    DrawerContract.View provideCityManagerContactView() {
        return view;
    }
}

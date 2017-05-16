package com.baronzhang.android.weather.activity.module;

import dagger.Module;
import dagger.Provides;

import com.baronzhang.android.weather.contract.SelectCityContract;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@Module
public class SelectCityModule {

    private SelectCityContract.View view;

    public SelectCityModule(SelectCityContract.View view) {
        this.view = view;
    }

    @Provides
    SelectCityContract.View provideSelectCityContractView() {
        return view;
    }
}

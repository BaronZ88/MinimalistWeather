package me.baron.weatherstyle.presenter.module;

import dagger.Module;
import dagger.Provides;
import me.baron.weatherstyle.contract.CityManagerContract;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Module
public class CityManagerPresentModule {

    private CityManagerContract.View view;

    public CityManagerPresentModule(CityManagerContract.View view) {
        this.view = view;
    }

    @Provides
    CityManagerContract.View provideCityManagerContactView() {
        return view;
    }
}

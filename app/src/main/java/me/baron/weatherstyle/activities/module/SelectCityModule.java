package me.baron.weatherstyle.activities.module;

import dagger.Module;
import dagger.Provides;
import me.baron.weatherstyle.contracts.SelectCityContract;

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

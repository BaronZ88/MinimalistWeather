package me.baron.weatherstyle.presenter.module;

import dagger.Module;
import dagger.Provides;
import me.baron.weatherstyle.contract.SelectCityContract;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Module
public class SelectCityPresenterModule {

    private SelectCityContract.View view;

    public SelectCityPresenterModule(SelectCityContract.View view) {
        this.view = view;
    }

    @Provides
    SelectCityContract.View provideSelectCityContractView() {
        return view;
    }
}

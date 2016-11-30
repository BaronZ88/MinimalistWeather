package me.baron.weatherstyle.presenter.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.baron.weatherstyle.contract.SelectCityContract;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Module
public class SelectCityPresenterModule {

    private Context context;
    private SelectCityContract.View view;

    public SelectCityPresenterModule(Context context, SelectCityContract.View view) {

        this.context = context;
        this.view = view;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    SelectCityContract.View provideSelectCityContractView() {
        return view;
    }
}

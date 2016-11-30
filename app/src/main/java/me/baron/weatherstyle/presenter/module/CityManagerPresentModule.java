package me.baron.weatherstyle.presenter.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.baron.weatherstyle.contract.CityManagerContract;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@Module
public class CityManagerPresentModule {

    private Context context;
    private CityManagerContract.View view;

    public CityManagerPresentModule(Context context, CityManagerContract.View view) {

        this.context = context;
        this.view = view;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    CityManagerContract.View provideCityManagerContactView() {

        return view;
    }
}

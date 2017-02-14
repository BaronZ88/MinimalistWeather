package com.baronzhang.android.weather.presenters.component;

import com.baronzhang.android.weather.presenters.SelectCityPresenter;

import javax.inject.Singleton;

import dagger.Component;
import com.baronzhang.android.weather.ApplicationModule;
import com.baronzhang.android.weather.presenters.CityManagerPresenter;
import com.baronzhang.android.weather.presenters.HomePagePresenter;

/**
 * @author 张磊 (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/12/2
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface PresenterComponent {

    void inject(HomePagePresenter presenter);

    void inject(SelectCityPresenter presenter);

    void inject(CityManagerPresenter presenter);
}
 
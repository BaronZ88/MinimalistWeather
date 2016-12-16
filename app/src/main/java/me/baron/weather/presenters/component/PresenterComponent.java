package me.baron.weather.presenters.component;

import javax.inject.Singleton;

import dagger.Component;
import me.baron.weather.ApplicationModule;
import me.baron.weather.presenters.CityManagerPresenter;
import me.baron.weather.presenters.HomePagePresenter;
import me.baron.weather.presenters.SelectCityPresenter;

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
 
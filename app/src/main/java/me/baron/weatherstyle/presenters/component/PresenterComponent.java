package me.baron.weatherstyle.presenters.component;

import javax.inject.Singleton;

import dagger.Component;
import me.baron.weatherstyle.ApplicationModule;
import me.baron.weatherstyle.presenters.CityManagerPresenter;
import me.baron.weatherstyle.presenters.HomePagePresenter;
import me.baron.weatherstyle.presenters.SelectCityPresenter;

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
 
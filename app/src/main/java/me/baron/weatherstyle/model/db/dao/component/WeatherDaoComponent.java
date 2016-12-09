package me.baron.weatherstyle.model.db.dao.component;

import dagger.Component;
import me.baron.weatherstyle.ApplicationComponent;
import me.baron.weatherstyle.presenter.CityManagerPresenter;
import me.baron.weatherstyle.presenter.HomePagePresenter;
import me.baron.weatherstyle.utils.ApplicationScoped;

/**
 * @author 张磊 (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/12/2
 */
@ApplicationScoped
@Component(dependencies = ApplicationComponent.class)
public interface WeatherDaoComponent {

    void inject(HomePagePresenter presenter);

    void inject(CityManagerPresenter presenter);
}
 
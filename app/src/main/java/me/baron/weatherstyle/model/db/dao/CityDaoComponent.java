package me.baron.weatherstyle.model.db.dao;

import dagger.Component;
import me.baron.weatherstyle.ApplicationComponent;
import me.baron.weatherstyle.activities.scope.ForApplication;
import me.baron.weatherstyle.presenter.SelectCityPresenter;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/12/2
 */
@ForApplication
@Component(dependencies = ApplicationComponent.class)
public interface CityDaoComponent {

    void inject(SelectCityPresenter selectCityPresenter);
}

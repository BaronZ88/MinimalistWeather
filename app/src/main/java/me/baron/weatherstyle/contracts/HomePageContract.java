package me.baron.weatherstyle.contracts;

import me.baron.library.presenter.BasePresenter;
import me.baron.library.view.BaseView;
import me.baron.weatherstyle.models.db.entities.adapter.WeatherAdapter;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public interface HomePageContract {

    interface View extends BaseView<Presenter> {

        void displayWeatherInformation(WeatherAdapter weather);
    }

    interface Presenter extends BasePresenter {

        void loadWeather(String cityId);
    }
}

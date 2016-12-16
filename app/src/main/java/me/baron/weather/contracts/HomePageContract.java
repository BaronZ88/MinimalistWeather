package me.baron.weather.contracts;

import me.baron.library.presenter.BasePresenter;
import me.baron.library.view.BaseView;
import me.baron.weather.models.db.entities.style.Weather;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public interface HomePageContract {

    interface View extends BaseView<Presenter> {

        void displayWeatherInformation(Weather weather);
    }

    interface Presenter extends BasePresenter {

        void loadWeather(String cityId);
    }
}

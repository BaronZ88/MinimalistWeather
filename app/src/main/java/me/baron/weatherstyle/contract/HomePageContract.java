package me.baron.weatherstyle.contract;

import me.baron.androidlibrary.presenter.BasePresenter;
import me.baron.androidlibrary.view.BaseView;
import me.baron.weatherstyle.models.adapter.WeatherAdapter;

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

package com.baronzhang.android.weather.contracts;

import com.baronzhang.android.weather.models.db.entities.style.Weather;
import com.baronzhang.android.library.presenter.BasePresenter;
import com.baronzhang.android.library.view.BaseView;

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

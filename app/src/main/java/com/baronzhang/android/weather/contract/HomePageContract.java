package com.baronzhang.android.weather.contract;

import com.baronzhang.android.weather.model.db.entities.minimalist.Weather;
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

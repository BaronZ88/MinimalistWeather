package com.baronzhang.android.weather.contracts;

import com.baronzhang.android.library.presenter.BasePresenter;
import com.baronzhang.android.library.view.BaseView;
import com.baronzhang.android.weather.models.db.entities.style.Weather;

import java.util.List;

import com.baronzhang.android.weather.presenters.CityManagerPresenter;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/4/16
 */
public interface CityManagerContract {

    interface View extends BaseView<CityManagerPresenter> {

        void displaySavedCities(List<Weather> weatherList);
    }

    interface Presenter extends BasePresenter {

        void loadSavedCities();

        void deleteCity(String cityId);
    }
}

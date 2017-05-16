package com.baronzhang.android.weather.contract;

import com.baronzhang.android.library.presenter.BasePresenter;
import com.baronzhang.android.library.view.BaseView;
import com.baronzhang.android.weather.model.db.entities.minimalist.Weather;
import com.baronzhang.android.weather.presenter.CityManagerPresenter;

import java.io.InvalidClassException;
import java.util.List;

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

        void saveCurrentCityToPreference(String cityId) throws InvalidClassException;
    }
}

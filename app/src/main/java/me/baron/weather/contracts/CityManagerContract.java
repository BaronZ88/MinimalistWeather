package me.baron.weather.contracts;

import java.util.List;

import me.baron.library.presenter.BasePresenter;
import me.baron.library.view.BaseView;
import me.baron.weather.models.db.entities.style.Weather;
import me.baron.weather.presenters.CityManagerPresenter;

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

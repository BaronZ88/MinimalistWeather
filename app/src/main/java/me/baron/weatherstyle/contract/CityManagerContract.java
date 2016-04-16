package me.baron.weatherstyle.contract;

import java.util.List;

import me.baron.androidlibrary.presenter.BasePresenter;
import me.baron.androidlibrary.view.BaseView;
import me.baron.weatherstyle.models.style.Weather;
import me.baron.weatherstyle.presenter.CityManagerPresenter;

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
    }
}

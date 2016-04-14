package me.baron.weatherstyle.contract;

import java.util.List;

import me.baron.androidlibrary.presenter.BasePresenter;
import me.baron.androidlibrary.view.BaseView;
import me.baron.weatherstyle.models.City;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public interface SelectCityContract {

    interface View extends BaseView<Presenter> {

        void displayCities(List<City> cities);
    }

    interface Presenter extends BasePresenter {

        void loadCities();
    }
}

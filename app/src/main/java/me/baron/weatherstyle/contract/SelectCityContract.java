package me.baron.weatherstyle.contract;

import java.util.List;

import me.baron.library.presenter.BasePresenter;
import me.baron.library.view.BaseView;
import me.baron.weatherstyle.model.db.models.City;

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

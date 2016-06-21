package me.baron.weatherstyle.presenter;

import android.content.Context;

import java.sql.SQLException;

import me.baron.weatherstyle.contract.CityManagerContract;
import me.baron.weatherstyle.database.dao.WeatherDao;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/4/16
 */
public class CityManagerPresenter implements CityManagerContract.Presenter {

    private Context context;
    private CityManagerContract.View view;
    private WeatherDao weatherDao;

    public CityManagerPresenter(Context context, CityManagerContract.View view) {

        this.context = context;
        this.view = view;
        this.weatherDao = new WeatherDao(context);
        view.setPresenter(this);
    }

    @Override
    public void start() {

        loadSavedCities();
    }

    @Override
    public void loadSavedCities() {

        try {
            Observable.just(new WeatherDao(context).queryAllSaveCity())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weathers -> {
                        view.displaySavedCities(weathers);
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCity(int cityId) {
        try {
            weatherDao.deleteById(cityId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

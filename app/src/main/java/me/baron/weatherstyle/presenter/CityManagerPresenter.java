package me.baron.weatherstyle.presenter;

import java.io.InvalidClassException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import me.baron.weatherstyle.WeatherApp;
import me.baron.weatherstyle.contract.CityManagerContract;
import me.baron.weatherstyle.model.db.dao.WeatherDao;
import me.baron.weatherstyle.model.db.dao.component.DaggerWeatherDaoComponent;
import me.baron.weatherstyle.model.db.models.style.Weather;
import me.baron.weatherstyle.model.preferences.Preferences;
import me.baron.weatherstyle.model.preferences.WeatherSettings;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/4/16
 */
public final class CityManagerPresenter implements CityManagerContract.Presenter {

    private CityManagerContract.View view;

    @Inject
    WeatherDao weatherDao;

    @Inject
    CityManagerPresenter(CityManagerContract.View view) {

        this.view = view;
        view.setPresenter(this);

        DaggerWeatherDaoComponent.builder()
                .applicationComponent(WeatherApp.getInstance().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void start() {

        loadSavedCities();
    }

    @Override
    public void loadSavedCities() {

        try {
            Observable.just(weatherDao.queryAllSaveCity())
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
    public void deleteCity(String cityId) {

        Observable.just(deleteCityFromDBAndReturnCurrentCityId(cityId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentCityId -> {
                    if (currentCityId == null)
                        return;
                    try {
                        Preferences.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, currentCityId);
                    } catch (InvalidClassException e) {
                        e.printStackTrace();
                    }
                });
    }

    private String deleteCityFromDBAndReturnCurrentCityId(String cityId) {
        String currentCityId = Preferences.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getId(), "");
        try {
            weatherDao.deleteById(cityId);
            if (cityId.equals(currentCityId)) {//说明删除的是当前选择的城市，所以需要重新设置默认城市
                List<Weather> weatherList = weatherDao.queryAllSaveCity();
                if (weatherList != null && weatherList.size() > 0) {
                    currentCityId = weatherList.get(0).getCityId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCityId;
    }

}

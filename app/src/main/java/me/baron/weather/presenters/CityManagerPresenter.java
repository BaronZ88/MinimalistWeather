package me.baron.weather.presenters;

import android.content.Context;

import java.io.InvalidClassException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import me.baron.weather.ApplicationModule;
import me.baron.weather.contracts.CityManagerContract;
import me.baron.weather.models.db.dao.WeatherDao;
import me.baron.weather.models.db.entities.style.Weather;
import me.baron.weather.models.preferences.Preferences;
import me.baron.weather.models.preferences.WeatherSettings;
import me.baron.weather.presenters.component.DaggerPresenterComponent;
import me.baron.weather.utils.ActivityScoped;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/4/16
 */
@ActivityScoped
public final class CityManagerPresenter implements CityManagerContract.Presenter {

    private CityManagerContract.View view;


    private CompositeSubscription subscriptions;

    @Inject
    WeatherDao weatherDao;

    @Inject
    CityManagerPresenter(Context context, CityManagerContract.View view) {

        this.view = view;
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void subscribe() {
        loadSavedCities();
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    @Override
    public void loadSavedCities() {

        try {
            Subscription subscription = Observable.just(weatherDao.queryAllSaveCity())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weathers -> {
                        view.displaySavedCities(weathers);
                    });
            subscriptions.add(subscription);
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

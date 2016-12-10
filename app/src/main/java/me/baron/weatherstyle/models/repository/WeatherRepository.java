package me.baron.weatherstyle.models.repository;

import java.sql.SQLException;

import me.baron.weatherstyle.models.db.dao.WeatherDao;
import me.baron.weatherstyle.models.db.entities.adapter.KnowWeatherAdapter;
import me.baron.weatherstyle.models.db.entities.adapter.MiWeatherAdapter;
import me.baron.weatherstyle.models.db.entities.adapter.WeatherAdapter;
import me.baron.weatherstyle.models.http.ApiClient;
import me.baron.weatherstyle.models.http.ApiConstants;
import rx.Observable;
import rx.exceptions.Exceptions;

import static me.baron.weatherstyle.models.http.ApiClient.configuration;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/12/10
 */
public class WeatherRepository {

    public static Observable<WeatherAdapter> getWeather(String cityId, WeatherDao weatherDao) {

        Observable<WeatherAdapter> observable = null;
        switch (configuration.getDataSourceType()) {
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_KNOW:
                observable = ApiClient.weatherService.getKnowWeather(cityId)
                        .map(KnowWeatherAdapter::new);
                break;
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_MI:
                observable = ApiClient.weatherService.getMiWeather(cityId)
                        .map(MiWeatherAdapter::new);
                break;
        }
        assert observable != null;
        observable.doOnNext(weatherAdapter -> {
            try {
                weatherDao.insertWeather(weatherAdapter.getWeather());
            } catch (SQLException e) {
                throw Exceptions.propagate(new Throwable(e.getMessage()));
            }
        });
        return observable;
    }
}

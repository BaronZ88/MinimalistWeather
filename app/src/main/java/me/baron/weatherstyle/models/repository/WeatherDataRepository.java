package me.baron.weatherstyle.models.repository;

import android.content.Context;
import android.text.TextUtils;

import java.sql.SQLException;

import me.baron.library.utils.NetworkUtils;
import me.baron.weatherstyle.models.db.dao.WeatherDao;
import me.baron.weatherstyle.models.db.entities.adapter.KnowWeatherAdapter;
import me.baron.weatherstyle.models.db.entities.adapter.MiWeatherAdapter;
import me.baron.weatherstyle.models.db.entities.style.Weather;
import me.baron.weatherstyle.models.http.ApiClient;
import me.baron.weatherstyle.models.http.ApiConstants;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.schedulers.Schedulers;

import static me.baron.weatherstyle.models.http.ApiClient.configuration;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/12/10
 */
public class WeatherDataRepository {

    public static Observable<Weather> getWeather(Context context, String cityId, WeatherDao weatherDao) {

        //从数据库获取天气数据
        Observable<Weather> observableForGetWeatherFromDB = Observable.create(new Observable.OnSubscribe<Weather>() {
            @Override
            public void call(Subscriber<? super Weather> subscriber) {
                try {
                    Weather weather = weatherDao.queryWeather(cityId);
                    subscriber.onNext(weather);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    throw Exceptions.propagate(e);
                }
            }
        });

        if (!NetworkUtils.isNetworkConnected(context)) {
            return observableForGetWeatherFromDB;
        }

        //从服务端获取天气数据
        Observable<Weather> observableForGetWeatherFromNetWork = null;
        switch (configuration.getDataSourceType()) {
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_KNOW:
                observableForGetWeatherFromNetWork = ApiClient.weatherService.getKnowWeather(cityId)
                        .map(knowWeather -> new KnowWeatherAdapter(knowWeather).getWeather());
                break;
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_MI:
                observableForGetWeatherFromNetWork = ApiClient.weatherService.getMiWeather(cityId)
                        .map(miWeather -> new MiWeatherAdapter(miWeather).getWeather());
                break;
        }
        assert observableForGetWeatherFromNetWork != null;
        observableForGetWeatherFromNetWork = observableForGetWeatherFromNetWork.doOnNext(weather -> Schedulers.io().createWorker().schedule(() -> {
            try {
                weatherDao.insertWeather(weather);
            } catch (SQLException e) {
                throw Exceptions.propagate(e);
            }
        }));

        return Observable.concat(observableForGetWeatherFromDB, observableForGetWeatherFromNetWork)
                .filter(weather -> weather != null && !TextUtils.isEmpty(weather.getCityId()))
                .takeUntil(weather -> System.currentTimeMillis() - Long.parseLong(weather.getRealTime().getTime()) <= 60 * 60 * 1000);
    }
}

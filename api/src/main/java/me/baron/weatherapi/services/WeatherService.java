package me.baron.weatherapi.services;

import me.baron.weatherapi.entities.MiWeather;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
public interface WeatherService {

    /**
     * http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101010100
     * @param cityId 城市ID
     * @return WeatherData
     */
    @GET("weather")
    Observable<MiWeather> getMiWeather(@Query("cityId") String cityId);

    @GET("users/{user}/repos")
    Observable<String> listRepos(@Path("user") String user);
}

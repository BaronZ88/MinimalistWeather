package com.baronzhang.android.weather.data.http.service;

import com.baronzhang.android.weather.data.http.entity.envicloud.EnvironmentCloudCityAirLive;
import com.baronzhang.android.weather.data.http.entity.envicloud.EnvironmentCloudForecast;
import com.baronzhang.android.weather.data.http.entity.envicloud.EnvironmentCloudWeatherLive;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2017/2/16
 */
public interface EnvironmentCloudWeatherService {

    /**
     * 获取指定城市的实时天气
     * <p>
     * API地址：http://service.envicloud.cn:8082/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
     *
     * @param cityId 城市id
     * @return Observable
     */
    @GET("/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    Observable<EnvironmentCloudWeatherLive> getWeatherLive(@Path("cityId") String cityId);

    /**
     * 获取指定城市7日天气预报
     * <p>
     * API地址：http://service.envicloud.cn:8082/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
     *
     * @param cityId 城市id
     * @return Observable
     */
    @GET("/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    Observable<EnvironmentCloudForecast> getWeatherForecast(@Path("cityId") String cityId);

    /**
     * 获取指定城市的实时空气质量
     * <p>
     * API地址：http://service.envicloud.cn:8082/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
     *
     * @param cityId 城市id
     * @return Observable
     */
    @GET("/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    Observable<EnvironmentCloudCityAirLive> getAirLive(@Path("cityId") String cityId);
}

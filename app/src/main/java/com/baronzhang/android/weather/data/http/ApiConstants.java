package com.baronzhang.android.weather.data.http;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/3/8
 */
public final class ApiConstants {

    static final String MI_WEATHER_API_HOST = "http://weatherapi.market.xiaomi.com/wtr-v2/";
    static final String KNOW_WEATHER_API_HOST = "http://knowweather.duapp.com/";
    static final String ENVIRONMENT_CLOUD_WEATHER_API_HOST = "http://service.envicloud.cn:8082/";

    public static final int WEATHER_DATA_SOURCE_TYPE_KNOW = 1;
    public static final int WEATHER_DATA_SOURCE_TYPE_MI = 2;
    public static final int WEATHER_DATA_SOURCE_TYPE_ENVIRONMENT_CLOUD = 3;
}

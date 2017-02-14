package com.baronzhang.android.weather.models.http;

import com.baronzhang.android.weather.models.http.configuration.ApiConfiguration;
import com.baronzhang.android.weather.models.http.converter.FastJsonConverterFactory;
import com.baronzhang.android.weather.models.http.services.WeatherService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import com.baronzhang.android.weather.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
public final class ApiClient {

    public static WeatherService weatherService;

    public static ApiConfiguration configuration;

    public static void init(ApiConfiguration apiConfiguration) {

        configuration = apiConfiguration;
        String weatherApiHost = "";
        switch (configuration.getDataSourceType()) {
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_KNOW:
                weatherApiHost = ApiConstants.KNOW_WEATHER_API_HOST;
                break;
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_MI:
                weatherApiHost = ApiConstants.MI_WEATHER_API_HOST;
                break;
        }
        weatherService = initWeatherService(weatherApiHost, WeatherService.class);
    }

    private static <T> T initWeatherService(String baseUrl, Class<T> clazz) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor).addNetworkInterceptor(new StethoInterceptor());
        }
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(clazz);
    }

}


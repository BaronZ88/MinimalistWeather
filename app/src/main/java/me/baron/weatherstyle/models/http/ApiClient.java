package me.baron.weatherstyle.models.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import me.baron.weatherstyle.models.http.configuration.ApiConfiguration;
import me.baron.weatherstyle.models.http.converter.FastJsonConverterFactory;
import me.baron.weatherstyle.models.http.services.WeatherService;
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

    public static void init(ApiConfiguration configuration) {

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

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(clazz);
    }

}


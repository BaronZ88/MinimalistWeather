package me.baron.weatherapi;

import me.baron.weatherapi.converter.FastJsonConverterFactory;
import me.baron.weatherapi.services.WeatherService;

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

    public static void init() {

        weatherService = initWeatherService(ApiConstants.MI_WEATHER_API_HOST, WeatherService.class);
    }

    private static <T> T initWeatherService(String baseUrl, Class<T> clazz) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
//                .addNetworkInterceptor(new HttpRequestInterceptor())
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


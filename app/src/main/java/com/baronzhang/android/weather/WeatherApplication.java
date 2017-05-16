package com.baronzhang.android.weather;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.design.BuildConfig;
import android.util.Log;

import com.baronzhang.android.weather.model.http.ApiClient;
import com.baronzhang.android.weather.model.http.ApiConstants;
import com.baronzhang.android.weather.model.http.configuration.ApiConfiguration;
import com.facebook.stetho.Stetho;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/4
 */
public class WeatherApplication extends Application {

    private static final String TAG = "WeatherApp";

    private ApplicationComponent applicationComponent;

    private static WeatherApplication weatherApplicationInstance;

    public static WeatherApplication getInstance() {

        return weatherApplicationInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate start");
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Stetho.initializeWithDefaults(this);

        weatherApplicationInstance = this;

        //初始化ApiClient
        ApiConfiguration apiConfiguration = ApiConfiguration.builder()
                .dataSourceType(ApiConstants.WEATHER_DATA_SOURCE_TYPE_KNOW)
                .build();
        ApiClient.init(apiConfiguration);
        Log.d(TAG, "onCreate end");
    }


    public ApplicationComponent getApplicationComponent() {

        return applicationComponent;
    }
}

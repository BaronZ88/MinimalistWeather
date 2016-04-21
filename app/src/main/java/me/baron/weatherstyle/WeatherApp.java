package me.baron.weatherstyle;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import me.baron.weatherapi.ApiClient;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/4
 */
public class WeatherApp extends Application {

    private static final String TAG = "WeatherApp";

    private static WeatherApp weatherAppInstance;

    public static WeatherApp getInstance(){

        return weatherAppInstance;
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
        weatherAppInstance = this;

        //init retrofit2
        ApiClient.init();
        Log.d(TAG, "onCreate end");
    }
}

package com.baronzhang.android.weather

import android.app.Application
import android.content.Context
import android.os.StrictMode
import android.util.Log

import com.baronzhang.android.weather.data.http.ApiClient
import com.baronzhang.android.weather.data.http.ApiConstants
import com.baronzhang.android.weather.data.http.configuration.ApiConfiguration

/**
 * @author baronzhang (微信公众号：BaronTalk)
 */
class WeatherApplication : Application() {

    companion object {

        private const val TAG = "WeatherApp"

        var instance: WeatherApplication? = null
            private set
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        Log.d(TAG, "attachBaseContext")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate start")
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build())
        }

        //初始化Stetho
        BuildConfig.STETHO.init(this.applicationContext)

        instance = this

        //初始化ApiClient
        val apiConfiguration = ApiConfiguration.builder()
                .dataSourceType(ApiConstants.WEATHER_DATA_SOURCE_TYPE_ENVIRONMENT_CLOUD)
                .build()
        ApiClient.init(apiConfiguration)
        Log.d(TAG, "onCreate end")
    }
}

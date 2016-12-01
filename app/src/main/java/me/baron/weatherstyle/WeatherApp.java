package me.baron.weatherstyle;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.design.BuildConfig;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;
import me.baron.weatherstyle.model.http.ApiClient;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/4
 */
public class WeatherApp extends Application {

    private static final String TAG = "WeatherApp";

    private AppComponent appComponent;

    private static WeatherApp weatherAppInstance;

    public static WeatherApp getInstance() {

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
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        Fabric.with(this, new Crashlytics());
        Stetho.initializeWithDefaults(this.getApplicationContext());

        weatherAppInstance = this;

        //init retrofit2
        ApiClient.init();
        Log.d(TAG, "onCreate end");
    }


    public AppComponent getAppComponent() {

        return appComponent;
    }
}

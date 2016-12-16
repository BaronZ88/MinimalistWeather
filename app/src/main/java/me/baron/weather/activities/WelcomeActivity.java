package me.baron.weather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.InvalidClassException;

import me.baron.library.activity.BaseActivity;
import me.baron.weather.models.preferences.Preferences;
import me.baron.weather.models.preferences.WeatherSettings;
import me.baron.weather.utils.CityDBUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class WelcomeActivity extends BaseActivity {

    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate start");

        Observable.just(initAppData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gotoMainPage());

        Log.d(TAG, "onCreate end");
    }

    private void gotoMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 初始化应用数据
     */
    private String initAppData() {
        Preferences.loadDefaults();
        //TODO 测试，待删除
        if (Preferences.getSharedPreferences().getBoolean(WeatherSettings.SETTINGS_FIRST_USE.getId(), false)) {
            try {
                Preferences.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, "101020100");
                Preferences.savePreference(WeatherSettings.SETTINGS_FIRST_USE, false);
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "importCityData start");
        CityDBUtil.importCityData();
        Log.d(TAG, "importCityData end");
        return null;
    }
}

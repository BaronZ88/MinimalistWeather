package me.baron.weatherstyle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.InvalidClassException;

import me.baron.library.activity.BaseActivity;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.preferences.Preferences;
import me.baron.weatherstyle.preferences.WeatherSettings;
import me.baron.weatherstyle.utils.CommonUtil;
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
        setContentView(R.layout.activity_welcome);
        Log.d(TAG, "onCreate start");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Observable.just(initAppData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gotoMainPage());

        Log.d(TAG, "onCreate end");
    }

    private void gotoMainPage() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
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
        CommonUtil.importCityData();
        Log.d(TAG, "importCityData end");
        return null;
    }
}

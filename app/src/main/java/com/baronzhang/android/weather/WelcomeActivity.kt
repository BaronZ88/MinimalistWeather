package com.baronzhang.android.weather

import android.content.Intent
import android.os.Bundle

import com.baronzhang.android.weather.base.BaseActivity
import com.baronzhang.android.library.util.system.StatusBarHelper
import com.baronzhang.android.weather.feature.home.MainActivity
import com.baronzhang.android.weather.data.db.CityDatabaseHelper
import com.baronzhang.android.weather.data.preference.PreferenceHelper
import com.baronzhang.android.weather.data.preference.WeatherSettings

import java.io.InvalidClassException

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author baronzhang (微信公众号：BaronTalk)
 */
class WelcomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarHelper.statusBarLightMode(this)

        Observable.just<String>(initAppData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gotoMainPage() }

    }

    private fun gotoMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // 修复 Android 9.0 下 Activity 跳转动画导致的启动页闪屏的问题
        overridePendingTransition(0, 0)
        finish()
    }

    /**
     * 初始化应用数据
     */
    private fun initAppData(): String? {
        PreferenceHelper.loadDefaults()
        //TODO 测试，待删除
        if (PreferenceHelper.getSharedPreferences().getBoolean(WeatherSettings.SETTINGS_FIRST_USE.id, false)) {
            try {
                PreferenceHelper.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, "101020100")
                PreferenceHelper.savePreference(WeatherSettings.SETTINGS_FIRST_USE, false)
            } catch (e: InvalidClassException) {
                e.printStackTrace()
            }

        }
        CityDatabaseHelper.importCityDB()
        return null
    }
}

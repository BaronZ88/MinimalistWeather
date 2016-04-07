package me.baron.weatherstyle.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import me.baron.androidlibrary.activity.BaseActivity;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.database.dao.CityDao;
import me.baron.weatherstyle.models.City;
import me.baron.weatherstyle.utils.CommonUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Observable.just(initAppData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> {
                    gotoMainPage();
                });
    }

    private void gotoMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 初始化应用数据
     */
    private List<City> initAppData() {

        CommonUtil.importCityData();
        return new CityDao(this).queryCityList();
    }
}

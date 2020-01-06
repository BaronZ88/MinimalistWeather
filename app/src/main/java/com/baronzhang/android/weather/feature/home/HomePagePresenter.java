package com.baronzhang.android.weather.feature.home;

import android.content.Context;
import android.widget.Toast;

import com.baronzhang.android.library.util.RxSchedulerUtils;
import com.baronzhang.android.weather.data.db.dao.WeatherDao;
import com.baronzhang.android.weather.data.preference.PreferenceHelper;
import com.baronzhang.android.weather.data.preference.WeatherSettings;
import com.baronzhang.android.weather.data.repository.WeatherDataRepository;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public final class HomePagePresenter implements HomePageContract.Presenter {

    private final Context context;
    private final HomePageContract.View weatherView;

    private CompositeSubscription subscriptions;

    private WeatherDao weatherDao;

    HomePagePresenter(Context context, HomePageContract.View view) {

        this.context = context;
        this.weatherView = view;
        this.subscriptions = new CompositeSubscription();
        weatherView.setPresenter(this);

        weatherDao = new WeatherDao(context);
    }

    @Override
    public void subscribe() {
        String cityId = PreferenceHelper.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getId(), "");
        loadWeather(cityId, false);
    }

    @Override
    public void loadWeather(String cityId, boolean refreshNow) {

        Subscription subscription = WeatherDataRepository.getWeather(context, cityId, weatherDao, refreshNow)
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .subscribe(weatherView::displayWeatherInformation, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                });
        subscriptions.add(subscription);
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }
}

package com.baronzhang.android.weather.presenters;

import android.content.Context;
import android.widget.Toast;

import com.baronzhang.android.weather.ApplicationModule;
import com.baronzhang.android.weather.contracts.HomePageContract;
import com.baronzhang.android.weather.models.db.dao.WeatherDao;
import com.baronzhang.android.weather.models.preferences.Preferences;
import com.baronzhang.android.weather.models.preferences.WeatherSettings;
import com.baronzhang.android.weather.models.repository.WeatherDataRepository;
import com.baronzhang.android.weather.presenters.component.DaggerPresenterComponent;
import com.baronzhang.android.weather.utils.ActivityScoped;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
@ActivityScoped
public final class HomePagePresenter implements HomePageContract.Presenter {

    private final Context context;
    private final HomePageContract.View weatherView;

    private CompositeSubscription subscriptions;

    @Inject
    WeatherDao weatherDao;

    @Inject
    HomePagePresenter(Context context, HomePageContract.View view) {

        this.context = context;
        this.weatherView = view;
        this.subscriptions = new CompositeSubscription();
        weatherView.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void subscribe() {
        String cityId = Preferences.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getId(), "");
        loadWeather(cityId);
    }

    @Override
    public void loadWeather(String cityId) {

        Subscription subscription = WeatherDataRepository.getWeather(context, cityId, weatherDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

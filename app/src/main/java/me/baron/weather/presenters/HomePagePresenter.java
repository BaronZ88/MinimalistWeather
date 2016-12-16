package me.baron.weather.presenters;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import me.baron.weather.ApplicationModule;
import me.baron.weather.contracts.HomePageContract;
import me.baron.weather.models.db.dao.WeatherDao;
import me.baron.weather.models.preferences.Preferences;
import me.baron.weather.models.preferences.WeatherSettings;
import me.baron.weather.models.repository.WeatherDataRepository;
import me.baron.weather.presenters.component.DaggerPresenterComponent;
import me.baron.weather.utils.ActivityScoped;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
@ActivityScoped
public final class HomePagePresenter implements HomePageContract.Presenter {

    private final Context context;
    private final HomePageContract.View weatherView;

    @Inject
    WeatherDao weatherDao;

    @Inject
    HomePagePresenter(Context context, HomePageContract.View view) {

        this.context = context;
        this.weatherView = view;
        weatherView.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void start() {
        String cityId = Preferences.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getId(), "");
        loadWeather(cityId);
    }

    @Override
    public void loadWeather(String cityId) {

        WeatherDataRepository.getWeather(context, cityId, weatherDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherView::displayWeatherInformation, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}

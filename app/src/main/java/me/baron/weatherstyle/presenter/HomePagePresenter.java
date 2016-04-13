package me.baron.weatherstyle.presenter;

import android.content.Context;
import android.widget.Toast;

import java.sql.SQLException;

import me.baron.weatherapi.ApiClient;
import me.baron.weatherstyle.database.dao.WeatherDao;
import me.baron.weatherstyle.contract.HomePageContract;
import me.baron.weatherstyle.models.adapter.MiWeatherAdapter;
import me.baron.weatherstyle.models.adapter.WeatherAdapter;
import me.baron.weatherstyle.preferences.Preferences;
import me.baron.weatherstyle.preferences.WeatherSettings;
import me.baron.weatherstyle.utils.NetworkUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class HomePagePresenter implements HomePageContract.Presenter {

    private final Context context;
    private final HomePageContract.View weatherView;

    public HomePagePresenter(Context context, HomePageContract.View view) {

        this.context = context;
        this.weatherView = view;
        weatherView.setPresenter(this);
    }

    @Override
    public void start() {
        String cityId = Preferences.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getId(), "");
        loadWeather(cityId);
    }

    @Override
    public void loadWeather(String cityId) {

        if (NetworkUtil.isNetworkConnected(context)) {
            ApiClient.weatherService.getMiWeather(cityId)
                    .map(miWeather -> {
                        WeatherAdapter weather = new MiWeatherAdapter(miWeather);
                        try {
                            new WeatherDao(context).insertWeather(weather.getWeather());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return weather;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weatherView::displayWeatherInformation);
        }
    }
}

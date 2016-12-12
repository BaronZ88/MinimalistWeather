package me.baron.weatherstyle.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.baron.library.fragment.BaseFragment;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.contracts.HomePageContract;
import me.baron.weatherstyle.models.db.entities.style.AQI;
import me.baron.weatherstyle.models.db.entities.style.Forecast;
import me.baron.weatherstyle.models.db.entities.style.Weather;
import me.baron.weatherstyle.views.adapters.ForecastAdapter;
import me.baron.widget.IndicatorView;

public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    //基本天气信息
    @BindView(R.id.cv_weather_information)
    CardView weatherInformationCardView;
    @BindView(R.id.tv_city_name)
    TextView cityNameTextView;
    @BindView(R.id.tv_weather_name)
    TextView weatherNameTextView;
    @BindView(R.id.tv_real_time)
    TextView realTimeTextView;

    //AQI
    @BindView(R.id.cv_aqi)
    CardView aqiCardView;
    @BindView(R.id.tv_aqi)
    TextView aqiTextView;
    @BindView(R.id.tv_quality)
    TextView qualityTextView;
    @BindView(R.id.indicator_view_aqi)
    IndicatorView aqiIndicatorView;
    @BindView(R.id.tv_advice)
    TextView adviceTextView;
    @BindView(R.id.tv_city_rank)
    TextView cityRankTextView;

    //预报
    @BindView(R.id.rv_forecast)
    RecyclerView forecastRecyclerView;


    private Unbinder unbinder;

    private Weather weather;
    private List<Forecast> forecasts;
    private ForecastAdapter forecastAdapter;

    private HomePageContract.Presenter presenter;

    public HomePageFragment() {

    }

    public static HomePageFragment newInstance() {

        return new HomePageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        forecastRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        forecasts = new ArrayList<>();
        forecastAdapter = new ForecastAdapter(forecasts);
        forecastRecyclerView.setItemAnimator(new DefaultItemAnimator());
        forecastRecyclerView.setAdapter(forecastAdapter);

        aqiIndicatorView.setIndicatorValueChangeListener((currentIndicatorValue, stateDescription, indicatorTextColor) -> {
            aqiTextView.setText(String.valueOf(currentIndicatorValue));
            if (TextUtils.isEmpty(weather.getAqi().getQuality())) {
                qualityTextView.setText(stateDescription);
            } else {
                qualityTextView.setText(weather.getAqi().getQuality());
            }
            aqiTextView.setTextColor(indicatorTextColor);
            qualityTextView.setTextColor(indicatorTextColor);
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void displayWeatherInformation(Weather weather) {

        this.weather = weather;
        cityNameTextView.setText(weather.getCityName());
        weatherNameTextView.setText(weather.getRealTime().getWeather());
        realTimeTextView.setText(weather.getRealTime().getTime());

        AQI aqi = weather.getAqi();
        aqiIndicatorView.setIndicatorValue(aqi.getAqi());
        adviceTextView.setText(aqi.getAdvice());
        cityRankTextView.setText(aqi.getCityRank());

        forecasts.addAll(weather.getForecasts());
        forecastAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

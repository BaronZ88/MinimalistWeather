package me.baron.weatherstyle.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import me.baron.weatherstyle.models.db.entities.adapter.WeatherAdapter;
import me.baron.weatherstyle.models.db.entities.style.AQI;
import me.baron.weatherstyle.models.db.entities.style.Forecast;
import me.baron.weatherstyle.views.adapters.ForecastAdapter;
import me.baron.widget.IndicatorView;

public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    //基本天气信息
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.tv_weather_name)
    TextView tvWeather;
    @BindView(R.id.tv_real_time)
    TextView tvRealTime;

    //AQI
    @BindView(R.id.tv_aqi)
    TextView tvAqi;
    @BindView(R.id.tv_quality)
    TextView tvQuality;
    @BindView(R.id.indicator_view_aqi)
    IndicatorView indicatorView;
    @BindView(R.id.tv_advice)
    TextView tvAdvice;
    @BindView(R.id.tv_city_rank)
    TextView tvCityRank;

    //预报
    @BindView(R.id.rv_forecast)
    RecyclerView forecastRecyclerView;

    private Unbinder unbinder;

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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void displayWeatherInformation(WeatherAdapter weather) {

        tvCityName.setText(weather.getCityName());
        tvWeather.setText(weather.getWeather().getRealTime().getWeather());
        tvRealTime.setText(weather.getRealTime().getTime());

        AQI aqi = weather.getAQI();
        tvAqi.setText(String.valueOf(aqi.getAqi()));
        tvQuality.setText(aqi.getQuality());
        indicatorView.setIndicatorValue(aqi.getAqi());
        tvAdvice.setText(aqi.getAdvice());
        tvCityRank.setText(aqi.getCityRank());

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

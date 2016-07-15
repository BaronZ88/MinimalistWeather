package me.baron.weatherstyle.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.baron.library.fragment.BaseFragment;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.adapters.ForecastAdapter;
import me.baron.weatherstyle.contract.HomePageContract;
import me.baron.weatherstyle.models.adapter.WeatherAdapter;
import me.baron.weatherstyle.models.style.Forecast;

public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    @Bind(R.id.tv_city_name)
    TextView tvCityName;
    @Bind(R.id.tv_weather_name)
    TextView tvWeather;
    @Bind(R.id.tv_real_time)
    TextView tvRealTime;
    @Bind(R.id.tv_aqi)
    TextView tvAqi;
    @Bind(R.id.rv_forecast)
    RecyclerView forecastRecyclerView;

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
        ButterKnife.bind(this, rootView);

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
        Log.d("WeatherStyle", "weather=" + weather.getForecasts());
        tvCityName.setText(weather.getCityName());
        tvWeather.setText(weather.getWeather().getRealTime().getWeather());
        tvRealTime.setText(weather.getRealTime().getTime());
        tvAqi.setText("空气污染指数：" + weather.getAQI().getAqi());

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
        ButterKnife.unbind(this);
    }
}

package me.baron.weatherstyle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.baron.library.fragment.BaseFragment;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.contract.HomePageContract;
import me.baron.weatherstyle.models.adapter.WeatherAdapter;


public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    @Bind(R.id.tv_city_name)
    TextView tvCityName;
    @Bind(R.id.tv_weather)
    TextView tvWeather;
    @Bind(R.id.tv_real_time)
    TextView tvRealTime;
    @Bind(R.id.tv_aqi)
    TextView tvAqi;
    private HomePageContract.Presenter presenter;

    public HomePageFragment() {

    }

    public static HomePageFragment newInstance() {

        return new HomePageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.bind(this, view);
        return view;
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
        tvAqi.setText("空气污染指数：" + weather.getAQI().getAqi());

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

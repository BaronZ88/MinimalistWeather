package me.baron.weatherstyle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.baron.androidlibrary.fragment.BaseFragment;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.contract.HomePageContract;
import me.baron.weatherstyle.models.adapter.WeatherAdapter;
import me.baron.weatherstyle.models.style.Weather;


public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    private HomePageContract.Presenter presenter;

    public HomePageFragment() {

    }

    public static HomePageFragment newInstance() {

        return new HomePageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void displayWeatherInformation(WeatherAdapter weather) {
        Toast.makeText(getActivity(), weather.getCityName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        this.presenter = presenter;
    }
}

package me.baron.weather.views.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.baron.library.fragment.BaseFragment;
import me.baron.library.utils.DateConvertUtils;
import me.baron.weather.R;
import me.baron.weather.contracts.HomePageContract;
import me.baron.weather.models.db.entities.style.AQI;
import me.baron.weather.models.db.entities.style.Forecast;
import me.baron.weather.models.db.entities.style.LifeIndex;
import me.baron.weather.models.db.entities.style.Weather;
import me.baron.weather.views.adapters.ForecastAdapter;
import me.baron.weather.views.adapters.LifeIndexAdapter;
import me.baron.widget.IndicatorView;

public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    //基本天气信息
    @BindView(R.id.cv_weather_information)
    CardView weatherInformationCardView;
    @BindView(R.id.temp_text_view)
    TextView tempTextView;
    @BindView(R.id.weather_text_view)
    TextView weatherNameTextView;
    @BindView(R.id.publish_time_text_view)
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
    @BindView(R.id.forecast_recycler_view)
    RecyclerView forecastRecyclerView;

    //生活指数
    @BindView(R.id.index_card_view)
    CardView indexCardView;
    @BindView(R.id.life_index_recycler_view)
    RecyclerView lifeIndexRecyclerView;

    private OnFragmentInteractionListener onFragmentInteractionListener;

    private Unbinder unbinder;

    private Weather weather;

    private List<Forecast> forecasts;
    private List<LifeIndex> lifeIndices;

    private ForecastAdapter forecastAdapter;
    private LifeIndexAdapter lifeIndexAdapter;

    private HomePageContract.Presenter presenter;

    public HomePageFragment() {

    }

    public static HomePageFragment newInstance() {

        return new HomePageFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            /**
             * 重写此方法解决ScrollView嵌套RecyclerView滑动卡顿的问题
             */
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        forecasts = new ArrayList<>();
        forecastAdapter = new ForecastAdapter(forecasts);
        forecastAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
        });
        forecastRecyclerView.setItemAnimator(new DefaultItemAnimator());
        forecastRecyclerView.setAdapter(forecastAdapter);

        lifeIndexRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4) {
            /**
             * 重写此方法解决ScrollView嵌套RecyclerView滑动卡顿的问题
             */
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        lifeIndices = new ArrayList<>();
        lifeIndexAdapter = new LifeIndexAdapter(getActivity(), lifeIndices);
        lifeIndexAdapter.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(HomePageFragment.this.getContext(), lifeIndices.get(i).getDetails(), Toast.LENGTH_LONG).show());
        lifeIndexRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lifeIndexRecyclerView.setAdapter(lifeIndexAdapter);

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
        assert presenter != null;
        presenter.subscribe();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayWeatherInformation(Weather weather) {

        this.weather = weather;
        onFragmentInteractionListener.updatePageTitle(weather.getCityName());
        tempTextView.setText(weather.getRealTime().getTemp());
        weatherNameTextView.setText(weather.getRealTime().getWeather());
        realTimeTextView.setText(getString(R.string.string_publish_time) + DateConvertUtils.timeStampToDate(weather.getRealTime().getTime(), DateConvertUtils.DATA_FORMAT_PATTEN_YYYY_MMMM_DD_HH_MM));

        AQI aqi = weather.getAqi();
        aqiIndicatorView.setIndicatorValue(aqi.getAqi());
        adviceTextView.setText(aqi.getAdvice());
        cityRankTextView.setText(aqi.getCityRank());

        forecasts.clear();
        forecasts.addAll(weather.getForecasts());
        forecastAdapter.notifyDataSetChanged();

        lifeIndices.clear();
        lifeIndices.addAll(weather.getLifeIndexes());
        lifeIndexAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void updatePageTitle(String title);
    }
}

package me.baron.weather.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.baron.library.fragment.BaseFragment;
import me.baron.weather.R;
import me.baron.weather.views.adapters.CityListAdapter;
import me.baron.weather.contracts.SelectCityContract;
import me.baron.weather.models.db.entities.City;
import me.baron.weather.models.preferences.Preferences;
import me.baron.weather.models.preferences.WeatherSettings;
import me.baron.weather.views.widget.DividerItemDecoration;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class SelectCityFragment extends BaseFragment implements SelectCityContract.View {

    public List<City> cities;
    public CityListAdapter cityListAdapter;

    @BindView(R.id.rv_city_list)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private SelectCityContract.Presenter presenter;

    public SelectCityFragment() {
    }

    public static SelectCityFragment newInstance() {
        return new SelectCityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_select_city, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        cities = new ArrayList<>();
        cityListAdapter = new CityListAdapter(cities);
        cityListAdapter.setOnItemClickListener((parent, view, position, id) -> {
            try {
                City selectedCity = cityListAdapter.mFilterData.get(position);
                Preferences.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, selectedCity.getCityId() + "");
                Toast.makeText(this.getActivity(), selectedCity.getCityName(), Toast.LENGTH_LONG).show();
                getActivity().finish();
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        });
        recyclerView.setAdapter(cityListAdapter);
        presenter.subscribe();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.unSubscribe();
    }

    @Override
    public void displayCities(List<City> cities) {
        this.cities.addAll(cities);
        cityListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(SelectCityContract.Presenter presenter) {
        this.presenter = presenter;
    }
}

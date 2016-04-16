package me.baron.weatherstyle.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.baron.androidlibrary.fragment.BaseFragment;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.adapters.CityListAdapter;
import me.baron.weatherstyle.contract.SelectCityContract;
import me.baron.weatherstyle.models.City;
import me.baron.weatherstyle.widget.DividerItemDecoration;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class SelectCityFragment extends BaseFragment implements SelectCityContract.View {

    public List<City> cities;
    public CityListAdapter cityListAdapter;

    @Bind(R.id.rv_city_list)
    RecyclerView recyclerView;

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
        ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        cities = new ArrayList<>();
        cityListAdapter = new CityListAdapter(cities);
        cityListAdapter.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(this.getActivity(), cities.get(position).getCityName(), Toast.LENGTH_LONG).show());
        recyclerView.setAdapter(cityListAdapter);

        this.presenter.start();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

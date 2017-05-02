package com.baronzhang.android.weather.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.baronzhang.android.library.fragment.BaseFragment;
import com.baronzhang.android.weather.R;
import com.baronzhang.android.weather.activity.SelectCityActivity;
import com.baronzhang.android.weather.contract.CityManagerContract;
import com.baronzhang.android.weather.model.db.entities.minimalist.Weather;
import com.baronzhang.android.weather.presenter.CityManagerPresenter;
import com.baronzhang.android.weather.view.adapter.CityManagerAdapter;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CityManagerFragment extends BaseFragment implements CityManagerContract.View {


    private static final String ARG_COLUMN_COUNT = "column-count";

    @BindView(R.id.rv_city_manager)
    RecyclerView cityManagerRecyclerView;

    private Unbinder unbinder;

    private int columnCount = 3;
    private List<Weather> weatherList;
    private CityManagerAdapter cityManagerAdapter;

    private CityManagerContract.Presenter presenter;

    public CityManagerFragment() {
    }

    public static CityManagerFragment newInstance(int columnCount) {
        CityManagerFragment fragment = new CityManagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_manager, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        Context context = rootView.getContext();
        if (columnCount <= 1) {
            cityManagerRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            cityManagerRecyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }
        cityManagerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        weatherList = new ArrayList<>();
        cityManagerAdapter = new CityManagerAdapter(weatherList);
        cityManagerAdapter.setOnItemClickListener(new CityManagerAdapter.OnCityManagerItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    presenter.saveCurrentCityToPreference(weatherList.get(position).getCityId() + "");
                    CityManagerFragment.this.getActivity().finish();
                } catch (InvalidClassException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDeleteClick(String cityId) {
                presenter.deleteCity(cityId);
            }
        });
        cityManagerRecyclerView.setAdapter(cityManagerAdapter);

        presenter.subscribe();

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.unSubscribe();
    }

    @Override
    public void displaySavedCities(List<Weather> weatherList) {
        this.weatherList.addAll(weatherList);
        cityManagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CityManagerPresenter presenter) {

        this.presenter = presenter;
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent intent = new Intent(getActivity(), SelectCityActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}

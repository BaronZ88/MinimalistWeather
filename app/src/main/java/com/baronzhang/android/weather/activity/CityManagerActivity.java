package com.baronzhang.android.weather.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.baronzhang.android.library.activity.BaseActivity;
import com.baronzhang.android.library.util.ActivityUtils;
import com.baronzhang.android.weather.R;
import com.baronzhang.android.weather.WeatherApplication;
import com.baronzhang.android.weather.activity.component.DaggerCityManagerComponent;
import com.baronzhang.android.weather.activity.module.CityManagerModule;
import com.baronzhang.android.weather.presenter.CityManagerPresenter;
import com.baronzhang.android.weather.view.fragment.CityManagerFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class CityManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    CityManagerPresenter cityManagerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        CityManagerFragment cityManagerFragment = CityManagerFragment.newInstance(3);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), cityManagerFragment, R.id.fragment_container);

        DaggerCityManagerComponent.builder()
                .applicationComponent(WeatherApplication.getInstance().getApplicationComponent())
                .cityManagerModule(new CityManagerModule(cityManagerFragment))
                .build().inject(this);
    }
}

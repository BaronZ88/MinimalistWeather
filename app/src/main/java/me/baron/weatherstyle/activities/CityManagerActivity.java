package me.baron.weatherstyle.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.baron.library.activity.BaseActivity;
import me.baron.library.utils.ActivityUtils;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.WeatherApp;
import me.baron.weatherstyle.activities.component.DaggerCityManagerComponent;
import me.baron.weatherstyle.presenter.CityManagerPresenter;
import me.baron.weatherstyle.activities.module.CityManagerModule;
import me.baron.weatherstyle.view.fragments.CityManagerFragment;

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
                .applicationComponent(WeatherApp.getInstance().getApplicationComponent())
                .cityManagerModule(new CityManagerModule(cityManagerFragment))
                .build().inject(this);
    }
}

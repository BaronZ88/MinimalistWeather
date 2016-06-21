package me.baron.weatherstyle.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.baron.library.activity.BaseActivity;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.fragments.CityManagerFragment;
import me.baron.weatherstyle.presenter.CityManagerPresenter;
import me.baron.weatherstyle.utils.ActivityUtils;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class CityManagerActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

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

        new CityManagerPresenter(this, cityManagerFragment);
    }
}

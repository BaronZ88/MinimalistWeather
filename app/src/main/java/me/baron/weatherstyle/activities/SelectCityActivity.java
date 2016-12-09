package me.baron.weatherstyle.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.baron.library.activity.BaseActivity;
import me.baron.library.utils.ActivityUtils;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.activities.component.DaggerSelectCityComponent;
import me.baron.weatherstyle.presenter.SelectCityPresenter;
import me.baron.weatherstyle.presenter.module.SelectCityPresenterModule;
import me.baron.weatherstyle.view.fragments.SelectCityFragment;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class SelectCityActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    SelectCityFragment selectCityFragment;

    @Inject
    SelectCityPresenter selectCityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        selectCityFragment = SelectCityFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), selectCityFragment, R.id.fragment_container);

        DaggerSelectCityComponent.builder()
                .selectCityPresenterModule(new SelectCityPresenterModule(selectCityFragment))
                .build().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_city, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
            RxSearchView.queryTextChanges(searchView)
                    .map(charSequence -> charSequence == null ? null : charSequence.toString().trim())
                    .throttleLast(100, TimeUnit.MILLISECONDS)
                    .debounce(100, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(searchText -> selectCityFragment.cityListAdapter.getFilter().filter(searchText));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
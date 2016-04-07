package me.baron.weatherstyle.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.annimon.stream.Stream;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.baron.androidlibrary.activity.BaseActivity;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.fragments.SelectCityFragment;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class SelectCityActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    SelectCityFragment selectCityFragment;

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
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, selectCityFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_city, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);//在菜单中找到对应控件的item
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        RxSearchView.queryTextChanges(searchView)
//                .filter(charSequence -> {
//                    if (TextUtils.isEmpty(charSequence)) {
//
//                    }
//                    return !TextUtils.isEmpty(charSequence);
//                })
                .map(charSequence -> {

                    Stream.of(selectCityFragment.cities)
                            .filter(city -> !(city.getCityName().contains(charSequence)
                                    || city.getCityNameEn().contains(charSequence)))
                            .forEach(city -> selectCityFragment.cities.remove(city));
                    return selectCityFragment.cities;
                })
                .throttleLast(300, TimeUnit.MILLISECONDS)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    selectCityFragment.cityListAdapter.notifyDataSetChanged();
                });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
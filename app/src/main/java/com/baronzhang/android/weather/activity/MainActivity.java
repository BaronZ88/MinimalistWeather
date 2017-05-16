package com.baronzhang.android.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.baronzhang.android.library.activity.BaseActivity;
import com.baronzhang.android.library.util.ActivityUtils;
import com.baronzhang.android.weather.R;
import com.baronzhang.android.weather.WeatherApplication;
import com.baronzhang.android.weather.activity.component.DaggerHomePageComponent;
import com.baronzhang.android.weather.activity.module.HomePageModule;
import com.baronzhang.android.weather.presenter.HomePagePresenter;
import com.baronzhang.android.weather.view.fragment.HomePageFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomePageFragment.OnFragmentInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Inject
    HomePagePresenter homePagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawerLayout != null;
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        HomePageFragment homePageFragment = HomePageFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homePageFragment, R.id.fragment_container);

        DaggerHomePageComponent.builder()
                .applicationComponent(WeatherApplication.getInstance().getApplicationComponent())
                .homePageModule(new HomePageModule(homePageFragment))
                .build().inject(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_select_city) {
            Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_city_manager) {
            Intent intent = new Intent(MainActivity.this, CityManagerActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void updatePageTitle(String title) {
        toolbar.setTitle(title);
    }
}

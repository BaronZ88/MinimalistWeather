package me.baron.weatherstyle.presenter;

import javax.inject.Inject;

import me.baron.weatherstyle.WeatherApp;
import me.baron.weatherstyle.contract.SelectCityContract;
import me.baron.weatherstyle.model.db.dao.CityDao;
import me.baron.weatherstyle.model.db.dao.component.DaggerCityDaoComponent;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public final class SelectCityPresenter implements SelectCityContract.Presenter {

    private final SelectCityContract.View cityListView;

    @Inject
    CityDao cityDao;

    @Inject
    SelectCityPresenter(SelectCityContract.View view) {

        this.cityListView = view;
        cityListView.setPresenter(this);

        DaggerCityDaoComponent.builder()
                .applicationComponent(WeatherApp.getInstance().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void start() {
        loadCities();
    }

    @Override
    public void loadCities() {
        Observable.just(cityDao.queryCityList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityListView::displayCities);
    }

}

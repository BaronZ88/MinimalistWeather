package me.baron.weatherstyle.presenter;

import android.content.Context;

import javax.inject.Inject;

import me.baron.weatherstyle.ApplicationModule;
import me.baron.weatherstyle.contract.SelectCityContract;
import me.baron.weatherstyle.model.db.dao.CityDao;
import me.baron.weatherstyle.presenter.component.DaggerPresenterComponent;
import me.baron.weatherstyle.utils.ActivityScoped;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
@ActivityScoped
public final class SelectCityPresenter implements SelectCityContract.Presenter {

    private final SelectCityContract.View cityListView;

    @Inject
    CityDao cityDao;

    @Inject
    SelectCityPresenter(Context context, SelectCityContract.View view) {

        this.cityListView = view;
        cityListView.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
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

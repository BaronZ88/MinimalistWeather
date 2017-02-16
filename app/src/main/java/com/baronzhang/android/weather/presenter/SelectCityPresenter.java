package com.baronzhang.android.weather.presenter;

import android.content.Context;

import com.baronzhang.android.weather.ApplicationModule;
import com.baronzhang.android.weather.contract.SelectCityContract;
import com.baronzhang.android.weather.model.db.dao.CityDao;
import com.baronzhang.android.weather.presenter.component.DaggerPresenterComponent;
import com.baronzhang.android.weather.util.ActivityScoped;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
@ActivityScoped
public final class SelectCityPresenter implements SelectCityContract.Presenter {

    private final SelectCityContract.View cityListView;

    private CompositeSubscription subscriptions;

    @Inject
    CityDao cityDao;

    @Inject
    SelectCityPresenter(Context context, SelectCityContract.View view) {

        this.cityListView = view;
        this.subscriptions = new CompositeSubscription();
        cityListView.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void loadCities() {
        Subscription subscription = Observable.just(cityDao.queryCityList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityListView::displayCities);
        subscriptions.add(subscription);
    }

    @Override
    public void subscribe() {
        loadCities();
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }
}

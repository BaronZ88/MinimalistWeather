package me.baron.weatherstyle.presenter;

import android.content.Context;

import javax.inject.Inject;

import me.baron.weatherstyle.contract.SelectCityContract;
import me.baron.weatherstyle.model.db.dao.CityDao;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public final class SelectCityPresenter implements SelectCityContract.Presenter {

    private final Context context;
    private final SelectCityContract.View cityListView;

    @Inject
    SelectCityPresenter(Context context, SelectCityContract.View view) {

        this.context = context;
        this.cityListView = view;
        cityListView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCities();
    }

    @Override
    public void loadCities() {
        Observable.just(new CityDao(context).queryCityList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityListView::displayCities);
    }

}

package me.baron.weatherstyle.presenters;

import android.content.Context;

import me.baron.weatherstyle.contract.SelectCityContract;
import me.baron.weatherstyle.database.dao.CityDao;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class SelectCityPresenter implements SelectCityContract.Presenter {

    private final Context context;
    private final SelectCityContract.View cityListView;


    public SelectCityPresenter(Context context, SelectCityContract.View view) {

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

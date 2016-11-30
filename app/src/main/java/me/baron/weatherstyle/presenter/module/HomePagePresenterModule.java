package me.baron.weatherstyle.presenter.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.baron.weatherstyle.contract.HomePageContract;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link me.baron.weatherstyle.presenter.HomePagePresenter}
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@Module
public class HomePagePresenterModule {

    private Context context;
    private HomePageContract.View view;

    public HomePagePresenterModule(Context context, HomePageContract.View view) {

        this.context = context;
        this.view = view;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    HomePageContract.View provideHomePageContractView() {
        return view;
    }
}

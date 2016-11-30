package me.baron.weatherstyle.module;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link me.baron.weatherstyle.presenters.HomePagePresenter}
 *
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 * 2016/11/30
 */

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.baron.weatherstyle.contract.HomePageContract;

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

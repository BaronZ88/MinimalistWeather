package me.baron.weatherstyle.activities.component;

import dagger.Component;
import me.baron.weatherstyle.activities.MainActivity;
import me.baron.weatherstyle.presenter.module.HomePagePresenterModule;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/29
 */
@Component(modules = HomePagePresenterModule.class)
public interface HomePageComponent {

    void inject(MainActivity mainActivity);
}

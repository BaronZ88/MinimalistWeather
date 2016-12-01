package me.baron.weatherstyle.activities.component;

import dagger.Component;
import me.baron.weatherstyle.AppComponent;
import me.baron.weatherstyle.activities.MainActivity;
import me.baron.weatherstyle.activities.scope.ActivityScope;
import me.baron.weatherstyle.presenter.module.HomePagePresenterModule;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/29
 */
@ActivityScope
@Component(modules = HomePagePresenterModule.class, dependencies = AppComponent.class)
public interface HomePageComponent {

    void inject(MainActivity mainActivity);
}

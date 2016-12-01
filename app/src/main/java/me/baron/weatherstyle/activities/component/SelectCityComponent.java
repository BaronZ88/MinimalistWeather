package me.baron.weatherstyle.activities.component;

import dagger.Component;
import me.baron.weatherstyle.AppComponent;
import me.baron.weatherstyle.activities.SelectCityActivity;
import me.baron.weatherstyle.activities.scope.ActivityScope;
import me.baron.weatherstyle.presenter.module.SelectCityPresenterModule;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@ActivityScope
@Component(modules = SelectCityPresenterModule.class, dependencies = AppComponent.class)
public interface SelectCityComponent {

    void inject(SelectCityActivity selectCityActivity);
}

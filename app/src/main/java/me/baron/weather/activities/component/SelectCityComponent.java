package me.baron.weather.activities.component;

import dagger.Component;
import me.baron.weather.ApplicationComponent;
import me.baron.weather.activities.SelectCityActivity;
import me.baron.weather.activities.module.SelectCityModule;
import me.baron.weather.utils.ActivityScoped;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@ActivityScoped
@Component(modules = SelectCityModule.class, dependencies = ApplicationComponent.class)
public interface SelectCityComponent {

    void inject(SelectCityActivity selectCityActivity);
}

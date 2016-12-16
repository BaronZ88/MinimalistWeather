package me.baron.weather.activities.component;

import dagger.Component;
import me.baron.weather.ApplicationComponent;
import me.baron.weather.activities.MainActivity;
import me.baron.weather.utils.ActivityScoped;
import me.baron.weather.activities.module.HomePageModule;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/29
 */
@ActivityScoped
@Component(modules = HomePageModule.class, dependencies = ApplicationComponent.class)
public interface HomePageComponent {

    void inject(MainActivity mainActivity);
}

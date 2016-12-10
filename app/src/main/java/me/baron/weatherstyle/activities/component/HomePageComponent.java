package me.baron.weatherstyle.activities.component;

import dagger.Component;
import me.baron.weatherstyle.ApplicationComponent;
import me.baron.weatherstyle.activities.MainActivity;
import me.baron.weatherstyle.utils.ActivityScoped;
import me.baron.weatherstyle.activities.module.HomePageModule;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/29
 */
@ActivityScoped
@Component(modules = HomePageModule.class, dependencies = ApplicationComponent.class)
public interface HomePageComponent {

    void inject(MainActivity mainActivity);
}

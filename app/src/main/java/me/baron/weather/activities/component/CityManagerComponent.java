package me.baron.weather.activities.component;

import dagger.Component;
import me.baron.weather.ApplicationComponent;
import me.baron.weather.activities.CityManagerActivity;
import me.baron.weather.activities.module.CityManagerModule;
import me.baron.weather.utils.ActivityScoped;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@ActivityScoped
@Component(modules = CityManagerModule.class, dependencies = ApplicationComponent.class)
public interface CityManagerComponent {

    void inject(CityManagerActivity cityManagerActivity);
}

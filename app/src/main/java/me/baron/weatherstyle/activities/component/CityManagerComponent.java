package me.baron.weatherstyle.activities.component;

import dagger.Component;
import me.baron.weatherstyle.ApplicationComponent;
import me.baron.weatherstyle.activities.CityManagerActivity;
import me.baron.weatherstyle.presenter.module.CityManagerPresentModule;
import me.baron.weatherstyle.utils.ApplicationScoped;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@ApplicationScoped
@Component(modules = CityManagerPresentModule.class, dependencies = ApplicationComponent.class)
public interface CityManagerComponent {

    void inject(CityManagerActivity cityManagerActivity);
}

package me.baron.weatherstyle.activities.component;

import dagger.Component;
import me.baron.weatherstyle.activities.CityManagerActivity;
import me.baron.weatherstyle.presenter.module.CityManagerPresentModule;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/11/30
 */
@Component(modules = CityManagerPresentModule.class)
public interface CityManagerComponent {

    void inject(CityManagerActivity cityManagerActivity);
}

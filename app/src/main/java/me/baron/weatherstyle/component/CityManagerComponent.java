package me.baron.weatherstyle.component;

import dagger.Component;
import me.baron.weatherstyle.activities.CityManagerActivity;
import me.baron.weatherstyle.module.CityManagerPresentModule;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Component(modules = CityManagerPresentModule.class)
public interface CityManagerComponent {

    void inject(CityManagerActivity cityManagerActivity);
}

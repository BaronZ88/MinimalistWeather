/*
 * Copyright (C) 2012 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.baron.weatherstyle.preferences;

/**
 * An interface that defines method to observe configuration changes.
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public interface ConfigurationListener {

    /**
     * Method invoked when a configuration value was changed.
     *
     * @param pref The preference changed
     * @param newValue The new value of the preference
     * @param applied If the configuration was applied (not need to be applied again)
     */
    void onConfigurationChanged(WeatherSettings pref, Object newValue, boolean applied);
}

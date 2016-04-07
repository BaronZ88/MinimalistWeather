package me.baron.weatherstyle.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import me.baron.weatherstyle.WeatherApp;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/6
 */
public final class Preferences {

    private static final String TAG = "Preferences";


    public static final String SETTINGS_FILENAME = "me.baron.weatherstyle";

    /**
     * The list of configuration listeners.
     */
    private static final List<ConfigurationListener> CONFIGURATION_LISTENERS =
            Collections.synchronizedList(new ArrayList<>());

    private Preferences() {
        super();
    }

    /**
     * 初始化应用程序默认配置（SharedPreferences）
     */
    public static void loadDefaults() {
        //设置SharedPreferences默认值
        try {
            Map<WeatherSettings, Object> defaultPrefs = new HashMap<>();
            WeatherSettings[] values = WeatherSettings.values();
            for (WeatherSettings value : values) {
                defaultPrefs.put(value, value.getDefaultValue());
            }
            savePreferences(defaultPrefs, false, true);
        } catch (Exception ex) {
            Log.e(TAG, "Save default settings fails", ex);
        }
    }

    /**
     * Method that adds a new configuration listener.
     *
     * @param listener The new configuration listener
     */
    public static void addConfigurationListener(ConfigurationListener listener) {
        CONFIGURATION_LISTENERS.add(listener);
    }

    /**
     * Method that removes the configuration listener.
     *
     * @param listener The configuration listener to be removed
     */
    public static void removeConfigurationListener(ConfigurationListener listener) {
        CONFIGURATION_LISTENERS.remove(listener);
    }

    /**
     * Method that returns the shared preferences of the application.
     *
     * @return SharedPreferences The shared preferences of the application
     */
    public static SharedPreferences getSharedPreferences() {
        return WeatherApp.getInstance().getSharedPreferences(
                SETTINGS_FILENAME, Context.MODE_PRIVATE);
    }

    /**
     * Method that saves a preference.
     *
     * @param pref    The preference identifier
     * @param value   The value of the preference
     * @param applied If the preference was applied
     * @throws InvalidClassException If the value of the preference is not of the
     *                               type of the preference
     */
    public static void savePreference(WeatherSettings pref, Object value, boolean applied)
            throws InvalidClassException {
        Map<WeatherSettings, Object> prefs =
                new HashMap<WeatherSettings, Object>();
        prefs.put(pref, value);
        savePreferences(prefs, applied);
    }

    /**
     * Method that saves the preferences passed as argument.
     *
     * @param prefs   The preferences to be saved
     * @param applied If the preference was applied
     * @throws InvalidClassException If the value of a preference is not of the
     *                               type of the preference
     */
    public static void savePreferences(Map<WeatherSettings, Object> prefs, boolean applied)
            throws InvalidClassException {
        savePreferences(prefs, false, applied);
    }

    /**
     * Method that saves the preferences passed as argument.
     *
     * @param prefs          The preferences to be saved
     * @param noSaveIfExists No saves if the preference if has a value
     * @param applied        If the preference was applied
     * @throws InvalidClassException If the value of a preference is not of the
     *                               type of the preference
     */
    private static void savePreferences(
            Map<WeatherSettings, Object> prefs, boolean noSaveIfExists, boolean applied)
            throws InvalidClassException {
        //Get the preferences editor
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();

        //Save all settings
        for (WeatherSettings pref : prefs.keySet()) {
            if (!noSaveIfExists && sp.contains(pref.getId())) {
                //The preference already has a value
                continue;
            }
            //Known and valid types
            Object value = prefs.get(pref);
            if (value instanceof Boolean && pref.getDefaultValue() instanceof Boolean) {
                editor.putBoolean(pref.getId(), (Boolean) value);
            } else if (value instanceof String && pref.getDefaultValue() instanceof String) {
                editor.putString(pref.getId(), (String) value);
            } else if (value instanceof Set && pref.getDefaultValue() instanceof Set) {
                editor.putStringSet(pref.getId(), (Set<String>) value);
            } else if (value instanceof Integer && pref.getDefaultValue() instanceof Integer) {
                editor.putInt(pref.getId(), (Integer) value);
            } else if (value instanceof Float && pref.getDefaultValue() instanceof Float) {
                editor.putFloat(pref.getId(), (Float) value);
            } else if (value instanceof Long && pref.getDefaultValue() instanceof Long) {
                editor.putLong(pref.getId(), (Long) value);
            } else {
                //The object is not of the appropriate type
                String msg = String.format(
                        "%s: %s",
                        pref.getId(),
                        value.getClass().getName());
                Log.e(TAG, String.format(
                        "Configuration error. InvalidClassException: %s",
                        msg));
                throw new InvalidClassException(msg);
            }
        }

        //Apply settings
        editor.apply();

        //Now its time to communicate the configuration change
        if (CONFIGURATION_LISTENERS != null && CONFIGURATION_LISTENERS.size() > 0) {
            for (WeatherSettings pref : prefs.keySet()) {
                Object value = prefs.get(pref);
                for (ConfigurationListener listener : CONFIGURATION_LISTENERS) {
                    listener.onConfigurationChanged(pref, value, applied);
                }
            }
        }
    }
}

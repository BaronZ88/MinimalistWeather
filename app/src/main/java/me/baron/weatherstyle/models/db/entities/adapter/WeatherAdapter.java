package me.baron.weatherstyle.models.db.entities.adapter;

import java.util.List;

import me.baron.weatherstyle.models.db.entities.style.AQI;
import me.baron.weatherstyle.models.db.entities.style.Forecast;
import me.baron.weatherstyle.models.db.entities.style.LifeIndex;
import me.baron.weatherstyle.models.db.entities.style.RealTime;
import me.baron.weatherstyle.models.db.entities.style.Weather;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
public abstract class WeatherAdapter {

    public abstract String getCityId();

    public abstract String getCityName();

    public abstract String getCityNameEn();

    public abstract RealTime getRealTime();

    public abstract List<Forecast> getForecasts();

    public abstract List<LifeIndex> getLifeIndexes();

    public abstract AQI getAQI();

    public Weather getWeather() {

        Weather weather = new Weather();
        weather.setCityId(getCityId());
        weather.setCityName(getCityName());
        weather.setCityNameEn(getCityNameEn());
        weather.setAqi(getAQI());
        weather.setForecasts(getForecasts());
        weather.setLifeIndexes(getLifeIndexes());
        weather.setRealTime(getRealTime());
        return weather;
    }
}

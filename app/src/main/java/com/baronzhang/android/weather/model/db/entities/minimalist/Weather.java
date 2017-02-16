package com.baronzhang.android.weather.model.db.entities.minimalist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
@DatabaseTable(tableName = "Weather")
public class Weather {

    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String CITY_NAME_FIELD_NAME = "cityName";
    public static final String CITY_NAME_EN_FIELD_NAME = "cityNameEn";
//    public static final String REAL_TIME_FIELD_NAME = "real_time_city_id";
//    public static final String FORECASTS_FIELD_NAME = "forecasts_city_id";
//    public static final String AQI_FIELD_NAME = "aqi_city_id";
//    public static final String LIFE_INDEXES_FIELD_NAME = "life_indexes_city_id";

    @DatabaseField(columnName = CITY_ID_FIELD_NAME, id = true)
    private String cityId;
    @DatabaseField(columnName = CITY_NAME_FIELD_NAME)
    private String cityName;
    @DatabaseField(columnName = CITY_NAME_EN_FIELD_NAME)
    private String cityNameEn;
//    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = REAL_TIME_FIELD_NAME)
    private WeatherLive weatherLive;
//    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = FORECASTS_FIELD_NAME)
    private List<WeatherForecast> weatherForecasts;
//    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = AQI_FIELD_NAME)
    private AirQualityLive airQualityLive;
//    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = LIFE_INDEXES_FIELD_NAME)
    private List<LifeIndex> lifeIndexes;

    public AirQualityLive getAirQualityLive() {
        return airQualityLive;
    }

    public void setAirQualityLive(AirQualityLive airQualityLive) {
        this.airQualityLive = airQualityLive;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public List<WeatherForecast> getWeatherForecasts() {
        return weatherForecasts;
    }

    public void setWeatherForecasts(List<WeatherForecast> weatherForecasts) {
        this.weatherForecasts = weatherForecasts;
    }

    public List<LifeIndex> getLifeIndexes() {
        return lifeIndexes;
    }

    public void setLifeIndexes(List<LifeIndex> lifeIndexes) {
        this.lifeIndexes = lifeIndexes;
    }

    public WeatherLive getWeatherLive() {
        return weatherLive;
    }

    public void setWeatherLive(WeatherLive weatherLive) {
        this.weatherLive = weatherLive;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "aqi=" + airQualityLive +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityNameEn='" + cityNameEn + '\'' +
                ", realTime=" + weatherLive +
                ", forecasts=" + weatherForecasts +
                ", lifeIndexes=" + lifeIndexes +
                '}';
    }
}

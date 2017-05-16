package com.baronzhang.android.weather.model.db.entities.minimalist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 天气预报
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
@DatabaseTable(tableName = "WeatherForecast")
public class WeatherForecast {

    public static final String ID_FIELD_NAME = "_id";
    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String WEATHER_FIELD_NAME = "weather";
    public static final String WEATHER_DAY_FIELD_NAME = "weatherDay";
    public static final String WEATHER_NIGHT_FIELD_NAME = "weatherNight";
    public static final String TEMP_MAX_FIELD_NAME = "tempMax";
    public static final String TEMP_MIN_FIELD_NAME = "tempMin";
    public static final String WIND_FIELD_NAME = "wind";
    public static final String DATA_FIELD_NAME = "data";
    public static final String WEEK_FIELD_NAME = "week";

    @DatabaseField(columnName = ID_FIELD_NAME, generatedId = true)
    private long id;//数据库自增长ID
    @DatabaseField(columnName = CITY_ID_FIELD_NAME)
    private String cityId;
    @DatabaseField(columnName = WEATHER_FIELD_NAME)
    private String weather;
    @DatabaseField(columnName = WEATHER_DAY_FIELD_NAME)
    private String weatherDay;
    @DatabaseField(columnName = WEATHER_NIGHT_FIELD_NAME)
    private String weatherNight;
    @DatabaseField(columnName = TEMP_MAX_FIELD_NAME)
    private int tempMax;
    @DatabaseField(columnName = TEMP_MIN_FIELD_NAME)
    private int tempMin;
    @DatabaseField(columnName = WIND_FIELD_NAME)
    private String wind;
    @DatabaseField(columnName = DATA_FIELD_NAME)
    private String data;
    @DatabaseField(columnName = WEEK_FIELD_NAME)
    private String week; //周一，周二，...

    public WeatherForecast() {
    }

    public WeatherForecast(String cityId, String weather, String weatherDay, String weatherNight,
                           int tempMax, int tempMin, String wind, String data, String week) {

        this.cityId = cityId;
        this.weather = weather;
        this.weatherDay = weatherDay;
        this.weatherNight = weatherNight;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.wind = wind;
        this.data = data;
        this.week = week;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDay() {
        return weatherDay;
    }

    public void setWeatherDay(String weatherDay) {
        this.weatherDay = weatherDay;
    }

    public String getWeatherNight() {
        return weatherNight;
    }

    public void setWeatherNight(String weatherNight) {
        this.weatherNight = weatherNight;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", weather='" + weather + '\'' +
                ", weatherDay='" + weatherDay + '\'' +
                ", weatherNight='" + weatherNight + '\'' +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", wind='" + wind + '\'' +
                ", data='" + data + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}

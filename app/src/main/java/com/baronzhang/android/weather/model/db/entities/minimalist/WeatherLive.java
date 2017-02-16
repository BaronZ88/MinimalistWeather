package com.baronzhang.android.weather.model.db.entities.minimalist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 天气实况
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
@DatabaseTable(tableName = "WeatherLive")
public class WeatherLive {

    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String WEATHER_FIELD_NAME = "weather";
    public static final String TEMP_FIELD_NAME = "temp";
    public static final String HUMIDITY_FIELD_NAME = "humidity";
    public static final String WIND_FIELD_NAME = "wind";
    public static final String WIND_SPEED_FIELD_NAME = "windSpeed";
    public static final String TIME_FIELD_NAME = "time";

    @DatabaseField(columnName = CITY_ID_FIELD_NAME, id = true)
    private String cityId;
    @DatabaseField(columnName = WEATHER_FIELD_NAME)
    private String weather;//天气情况
    @DatabaseField(columnName = TEMP_FIELD_NAME)
    private String temp;//温度
    @DatabaseField(columnName = HUMIDITY_FIELD_NAME)
    private String humidity;//湿度
    @DatabaseField(columnName = WIND_FIELD_NAME)
    private String wind;//风向
    @DatabaseField(columnName = WIND_SPEED_FIELD_NAME)
    private String windSpeed;//风速
    @DatabaseField(columnName = TIME_FIELD_NAME)
    private long time;//发布时间（时间戳）

    public WeatherLive() {
    }

    public WeatherLive(String cityId, String weather, String temp, String humidity, String wind, String windSpeed, long time) {

        this.cityId = cityId;
        this.weather = weather;
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
        this.windSpeed = windSpeed;
        this.time = time;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "RealTime{" +
                "cityId='" + cityId + '\'' +
                ", weather='" + weather + '\'' +
                ", temp='" + temp + '\'' +
                ", humidity='" + humidity + '\'' +
                ", wind='" + wind + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

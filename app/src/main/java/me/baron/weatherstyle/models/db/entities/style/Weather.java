package me.baron.weatherstyle.models.db.entities.style;

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
    private RealTime realTime;
//    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = FORECASTS_FIELD_NAME)
    private List<Forecast> forecasts;
//    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = AQI_FIELD_NAME)
    private AQI aqi;
//    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = LIFE_INDEXES_FIELD_NAME)
    private List<LifeIndex> lifeIndexes;

    public AQI getAqi() {
        return aqi;
    }

    public void setAqi(AQI aqi) {
        this.aqi = aqi;
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

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public List<LifeIndex> getLifeIndexes() {
        return lifeIndexes;
    }

    public void setLifeIndexes(List<LifeIndex> lifeIndexes) {
        this.lifeIndexes = lifeIndexes;
    }

    public RealTime getRealTime() {
        return realTime;
    }

    public void setRealTime(RealTime realTime) {
        this.realTime = realTime;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "aqi=" + aqi +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityNameEn='" + cityNameEn + '\'' +
                ", realTime=" + realTime +
                ", forecasts=" + forecasts +
                ", lifeIndexes=" + lifeIndexes +
                '}';
    }
}

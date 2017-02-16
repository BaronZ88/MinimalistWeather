package com.baronzhang.android.weather.model.db.entities.minimalist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 空气质量实况
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
@DatabaseTable(tableName = "AirQuality")
public class AirQualityLive {

    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String AQI_FIELD_NAME = "aqi";
    public static final String PM25_FIELD_NAME = "pm25";
    public static final String PM10_FIELD_NAME = "pm10";
    public static final String PUBLISH_TIME_FIELD_NAME = "publishTime";
    public static final String ADVICE_FIELD_NAME = "advice";
    public static final String CITY_RANK_FIELD_NAME = "cityRank";
    public static final String QUALITY_FIELD_NAME = "quality";

    @DatabaseField(columnName = CITY_ID_FIELD_NAME, id = true)
    private String cityId;
    @DatabaseField(columnName = AQI_FIELD_NAME)
    private int aqi;
    @DatabaseField(columnName = PM25_FIELD_NAME)
    private int pm25;
    @DatabaseField(columnName = PM10_FIELD_NAME)
    private int pm10;
    @DatabaseField(columnName = PUBLISH_TIME_FIELD_NAME)
    private String publishTime;
    @DatabaseField(columnName = ADVICE_FIELD_NAME)
    private String advice;
    @DatabaseField(columnName = CITY_RANK_FIELD_NAME)
    private String cityRank;
    @DatabaseField(columnName = QUALITY_FIELD_NAME)
    private String quality;

    public AirQualityLive() {
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getCityRank() {
        return cityRank;
    }

    public void setCityRank(String cityRank) {
        this.cityRank = cityRank;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}

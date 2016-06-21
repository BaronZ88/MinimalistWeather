package me.baron.weatherstyle.models.style;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
@DatabaseTable(tableName = "aqi")
public class AQI {

    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String AQI_FIELD_NAME = "aqi";
    public static final String PM25_FIELD_NAME = "pm25";
    public static final String PM10_FIELD_NAME = "pm10";
    public static final String PUBLISH_TIME_FIELD_NAME = "publishTime";
    public static final String SO2_FIELD_NAME = "so2";
    public static final String NO3_FIELD_NAME = "no3";
    public static final String SRC_FIELD_NAME = "src";

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
    @DatabaseField(columnName = SO2_FIELD_NAME)
    private int so2;
    @DatabaseField(columnName = NO3_FIELD_NAME)
    private int no3;
    @DatabaseField(columnName = SRC_FIELD_NAME)
    private String src;

    public AQI() {
    }

    public AQI(String cityId, int aqi, int pm25, int pm10, String publishTime, int so2, int no3, String src) {

        this.cityId = cityId;
        this.aqi = aqi;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.publishTime = publishTime;
        this.so2 = so2;
        this.no3 = no3;
        this.src = src;
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

    public int getNo3() {
        return no3;
    }

    public void setNo3(int no3) {
        this.no3 = no3;
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

    public int getSo2() {
        return so2;
    }

    public void setSo2(int so2) {
        this.so2 = so2;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "AQI{" +
                "cityId=" + cityId +
                ", aqi=" + aqi +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", publishTime='" + publishTime + '\'' +
                ", so2=" + so2 +
                ", no3=" + no3 +
                ", src='" + src + '\'' +
                '}';
    }
}

package com.baronzhang.android.weather.data.http.entity.mi;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 小米天气空气污染指数
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
public class MiAQI {

    @JSONField(name = "city")
    private String cityName;
    @JSONField(name = "city_id")
    private int cityId;
    @JSONField(name = "pub_time")
    private String publishTime;
    private int aqi;
    private int pm25;
    private int pm10;
    private int so2;
    private int no3;
    private String src;
    private String spot;

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "MiAQI{" +
                "cityName='" + cityName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", aqi=" + aqi +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", so2=" + so2 +
                ", no3=" + no3 +
                ", src='" + src + '\'' +
                ", spot='" + spot + '\'' +
                '}';
    }
}

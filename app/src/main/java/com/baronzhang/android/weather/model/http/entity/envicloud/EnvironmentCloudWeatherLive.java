package com.baronzhang.android.weather.model.http.entity.envicloud;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2017/2/16
 */
public class EnvironmentCloudWeatherLive {


    /**
     * airpressure : 1016.0
     * rain : 0.0
     * windpower : 微风
     * rcode : 200
     * feelst : 17.7
     * citycode : 101020100
     * rdesc : Success
     * winddirect : 西北风
     * temperature : 17.8
     * humidity : 50.0
     * windspeed : 0.9
     * updatetime : 2017-02-16 14:06
     * phenomena : 阵雨
     */

    private String airpressure;
    private String rain;
    private String windpower;
    private int rcode;
    private String feelst;
    private String citycode;
    private String rdesc;
    private String winddirect;
    private String temperature;
    private String humidity;
    private String windspeed;
    private String updatetime;
    private String phenomena;

    public String getAirpressure() {
        return airpressure;
    }

    public void setAirpressure(String airpressure) {
        this.airpressure = airpressure;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public String getFeelst() {
        return feelst;
    }

    public void setFeelst(String feelst) {
        this.feelst = feelst;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getPhenomena() {
        return phenomena;
    }

    public void setPhenomena(String phenomena) {
        this.phenomena = phenomena;
    }
}


package com.baronzhang.android.weather.data.http.entity.know;

import java.util.List;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/12/10
 */
public class KnowWeather {

    private String cityId;
    private BasicEntity basic;
    private AqiEntity aqi;
    private List<HoursForecastEntity> hoursForecast;
    private List<DailyForecastEntity> dailyForecast;
    private List<LifeIndexEntity> lifeIndex;
    private List<?> alarms;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public BasicEntity getBasic() {
        return basic;
    }

    public void setBasic(BasicEntity basic) {
        this.basic = basic;
    }

    public AqiEntity getAqi() {
        return aqi;
    }

    public void setAqi(AqiEntity aqi) {
        this.aqi = aqi;
    }

    public List<HoursForecastEntity> getHoursForecast() {
        return hoursForecast;
    }

    public void setHoursForecast(List<HoursForecastEntity> hoursForecast) {
        this.hoursForecast = hoursForecast;
    }

    public List<DailyForecastEntity> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<DailyForecastEntity> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public List<LifeIndexEntity> getLifeIndex() {
        return lifeIndex;
    }

    public void setLifeIndex(List<LifeIndexEntity> lifeIndex) {
        this.lifeIndex = lifeIndex;
    }

    public List<?> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<?> alarms) {
        this.alarms = alarms;
    }

    public static class BasicEntity {
        /**
         * city : 北京
         * province : 北京市
         * temp : 3°
         * time : 2016-12-10 17:00:00
         * weather : 晴
         * weatherIcon : /public
         * img : 0
         */

        private String city;
        private String province;
        private String temp;
        private String time;
        private String weather;
        private String weatherIcon;
        private String img;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeatherIcon() {
            return weatherIcon;
        }

        public void setWeatherIcon(String weatherIcon) {
            this.weatherIcon = weatherIcon;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class AqiEntity {
        /**
         * aqi : 65
         * cityRank : 空气质量超过全国68%的城市或地区
         * pm10 : 37
         * pm25 : 59
         * quality : 良
         * advice : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
         */

        private String aqi;
        private String cityRank;
        private String pm10;
        private String pm25;
        private String quality;
        private String advice;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCityRank() {
            return cityRank;
        }

        public void setCityRank(String cityRank) {
            this.cityRank = cityRank;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }
    }

    public static class HoursForecastEntity {
        /**
         * temp : -1°
         * time : 2016-12-10 19:00:00
         * weather : 晴
         * weatherIcon : /public
         * img : 0
         */

        private String temp;
        private String time;
        private String weather;
        private String weatherIcon;
        private String img;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeatherIcon() {
            return weatherIcon;
        }

        public void setWeatherIcon(String weatherIcon) {
            this.weatherIcon = weatherIcon;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class DailyForecastEntity {
        /**
         * date : 2016-12-10
         * temp_range : -4~5°
         * weather : 晴
         * week : 今天
         * weatherIcon : /public
         * img : 0
         */

        private String date;
        private String temp_range;
        private String weather;
        private String week;
        private String weatherIcon;
        private String img;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTemp_range() {
            return temp_range;
        }

        public void setTemp_range(String temp_range) {
            this.temp_range = temp_range;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeatherIcon() {
            return weatherIcon;
        }

        public void setWeatherIcon(String weatherIcon) {
            this.weatherIcon = weatherIcon;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class LifeIndexEntity {
        /**
         * name : 防晒
         * level : 较弱
         * content : 紫外线强度较弱，建议涂擦SPF在12-15之间，PA+的防晒护肤品。
         */

        private String name;
        private String level;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

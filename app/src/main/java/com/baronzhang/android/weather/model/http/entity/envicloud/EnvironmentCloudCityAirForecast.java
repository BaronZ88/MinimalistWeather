package com.baronzhang.android.weather.model.http.entity.envicloud;

import java.util.List;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2017/2/16
 */
public class EnvironmentCloudCityAirForecast {


    /**
     * citycode : 101020100
     * rdesc : Success
     * forecast : [{"no2_max":"490.57","o3_8h_max":"50.94","no2_min":"111.35","o3_min":"17.37","aqi_avg":"283","co_max":"2.00","date":"2017-01-12","o3_max":"57.51","co_min":".85","pm10_max":"248.13","so2_max":"310.84","so2_min":"126.30","pm25_min":"60.16","pm25_max":"227.47","o3_8h_min":"17.37","pm10_min":"68.05"},{"no2_max":"258.61","o3_8h_max":"64.53","no2_min":"53.62","o3_min":"26.21","aqi_avg":"139","co_max":"1.53","date":"2017-01-13","o3_max":"69.14","co_min":".50","pm10_max":"177.69","so2_max":"124.33","so2_min":"124.33","pm25_min":"32.78","pm25_max":"164.85","o3_8h_min":"25.80","pm10_min":"37.01"},{"no2_max":"154.79","o3_8h_max":"70.03","no2_min":"43.53","o3_min":"29.89","aqi_avg":"102","co_max":"1.15","date":"2017-01-14","o3_max":"76.01","co_min":".35","pm10_max":"82.00","so2_max":"96.88","so2_min":"88.43","pm25_min":"14.87","pm25_max":"74.91","o3_8h_min":"33.30","pm10_min":"17.52"},{"no2_max":"110.86","o3_8h_max":"78.12","no2_min":"35.90","o3_min":"55.97","aqi_avg":"87","co_max":".69","date":"2017-01-15","o3_max":"81.55","co_min":".33","pm10_max":"65.95","so2_max":"60.52","so2_min":"53.21","pm25_min":"15.55","pm25_max":"59.48","o3_8h_min":"57.29","pm10_min":"18.54"},{"no2_max":"173.07","o3_8h_max":"80.01","no2_min":"38.08","o3_min":"58.19","aqi_avg":"104","co_max":".97","date":"2017-01-16","o3_max":"82.76","co_min":".38","pm10_max":"64.34","so2_max":"102.89","so2_min":"51.16","pm25_min":"18.75","pm25_max":"56.38","o3_8h_min":"66.63","pm10_min":"21.86"}]
     * rcode : 200
     * cityname : 上海
     * publishdate : 2017-01-12
     */

    private String citycode;
    private String rdesc;
    private int rcode;
    private String cityname;
    private String publishdate;
    private List<ForecastEntity> forecast;

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

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public List<ForecastEntity> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastEntity> forecast) {
        this.forecast = forecast;
    }

    public static class ForecastEntity {
        /**
         * no2_max : 490.57
         * o3_8h_max : 50.94
         * no2_min : 111.35
         * o3_min : 17.37
         * aqi_avg : 283
         * co_max : 2.00
         * date : 2017-01-12
         * o3_max : 57.51
         * co_min : .85
         * pm10_max : 248.13
         * so2_max : 310.84
         * so2_min : 126.30
         * pm25_min : 60.16
         * pm25_max : 227.47
         * o3_8h_min : 17.37
         * pm10_min : 68.05
         */

        private String no2_max;
        private String o3_8h_max;
        private String no2_min;
        private String o3_min;
        private String aqi_avg;
        private String co_max;
        private String date;
        private String o3_max;
        private String co_min;
        private String pm10_max;
        private String so2_max;
        private String so2_min;
        private String pm25_min;
        private String pm25_max;
        private String o3_8h_min;
        private String pm10_min;

        public String getNo2_max() {
            return no2_max;
        }

        public void setNo2_max(String no2_max) {
            this.no2_max = no2_max;
        }

        public String getO3_8h_max() {
            return o3_8h_max;
        }

        public void setO3_8h_max(String o3_8h_max) {
            this.o3_8h_max = o3_8h_max;
        }

        public String getNo2_min() {
            return no2_min;
        }

        public void setNo2_min(String no2_min) {
            this.no2_min = no2_min;
        }

        public String getO3_min() {
            return o3_min;
        }

        public void setO3_min(String o3_min) {
            this.o3_min = o3_min;
        }

        public String getAqi_avg() {
            return aqi_avg;
        }

        public void setAqi_avg(String aqi_avg) {
            this.aqi_avg = aqi_avg;
        }

        public String getCo_max() {
            return co_max;
        }

        public void setCo_max(String co_max) {
            this.co_max = co_max;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getO3_max() {
            return o3_max;
        }

        public void setO3_max(String o3_max) {
            this.o3_max = o3_max;
        }

        public String getCo_min() {
            return co_min;
        }

        public void setCo_min(String co_min) {
            this.co_min = co_min;
        }

        public String getPm10_max() {
            return pm10_max;
        }

        public void setPm10_max(String pm10_max) {
            this.pm10_max = pm10_max;
        }

        public String getSo2_max() {
            return so2_max;
        }

        public void setSo2_max(String so2_max) {
            this.so2_max = so2_max;
        }

        public String getSo2_min() {
            return so2_min;
        }

        public void setSo2_min(String so2_min) {
            this.so2_min = so2_min;
        }

        public String getPm25_min() {
            return pm25_min;
        }

        public void setPm25_min(String pm25_min) {
            this.pm25_min = pm25_min;
        }

        public String getPm25_max() {
            return pm25_max;
        }

        public void setPm25_max(String pm25_max) {
            this.pm25_max = pm25_max;
        }

        public String getO3_8h_min() {
            return o3_8h_min;
        }

        public void setO3_8h_min(String o3_8h_min) {
            this.o3_8h_min = o3_8h_min;
        }

        public String getPm10_min() {
            return pm10_min;
        }

        public void setPm10_min(String pm10_min) {
            this.pm10_min = pm10_min;
        }
    }
}

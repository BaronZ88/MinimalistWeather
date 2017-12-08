package com.baronzhang.android.weather.data.db.entities.adapter;

import com.baronzhang.android.library.util.DateConvertUtils;
import com.baronzhang.android.weather.data.db.entities.minimalist.AirQualityLive;
import com.baronzhang.android.weather.data.db.entities.minimalist.LifeIndex;
import com.baronzhang.android.weather.data.db.entities.minimalist.WeatherForecast;
import com.baronzhang.android.weather.data.db.entities.minimalist.WeatherLive;
import com.baronzhang.android.weather.data.http.entity.know.KnowWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/12/10
 */
public class KnowWeatherAdapter extends WeatherAdapter {

    private KnowWeather knowWeather;

    public KnowWeatherAdapter(KnowWeather knowWeather) {
        this.knowWeather = knowWeather;
    }

    @Override
    public String getCityId() {
        return knowWeather.getCityId();
    }

    @Override
    public String getCityName() {
        return knowWeather.getBasic().getCity();
    }

    @Override
    public String getCityNameEn() {
        return null;
    }

    @Override
    public WeatherLive getWeatherLive() {

        WeatherLive weatherLive = new WeatherLive();
        weatherLive.setCityId(knowWeather.getCityId());
        weatherLive.setHumidity("");
        weatherLive.setTemp(knowWeather.getBasic().getTemp());
        weatherLive.setTime(DateConvertUtils.dateToTimeStamp(knowWeather.getBasic().getTime(),DateConvertUtils.DATA_FORMAT_PATTEN_YYYY_MM_DD_HH_MM_SS));
        weatherLive.setWeather(knowWeather.getBasic().getWeather());
        weatherLive.setWind("");
        weatherLive.setWindSpeed("");
        return weatherLive;
    }

    @Override
    public List<WeatherForecast> getWeatherForecasts() {

        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        List<KnowWeather.DailyForecastEntity> dailyForecasts = knowWeather.getDailyForecast();
        for (KnowWeather.DailyForecastEntity dailyForecastEntity : dailyForecasts) {
            WeatherForecast weatherForecast = new WeatherForecast();
            weatherForecast.setCityId(knowWeather.getCityId());
            weatherForecast.setWind("");
            weatherForecast.setWeather(dailyForecastEntity.getWeather());
            weatherForecast.setWeek(dailyForecastEntity.getWeek());
            weatherForecast.setDate(dailyForecastEntity.getDate());
            int[] temperature = splitTemperature(dailyForecastEntity.getTemp_range());
            if (temperature != null) {
                weatherForecast.setTempMax(temperature[1]);
                weatherForecast.setTempMin(temperature[0]);
            }
            weatherForecasts.add(weatherForecast);
        }
        return weatherForecasts;
    }

    @Override
    public List<LifeIndex> getLifeIndexes() {

        List<LifeIndex> indexList = new ArrayList<>();
        List<KnowWeather.LifeIndexEntity> lifeIndexEntityList = knowWeather.getLifeIndex();
        for (KnowWeather.LifeIndexEntity lifeIndexEntity : lifeIndexEntityList) {
            LifeIndex lifeIndex = new LifeIndex();
            lifeIndex.setCityId(knowWeather.getCityId());
            lifeIndex.setName(lifeIndexEntity.getName());
            lifeIndex.setIndex(lifeIndexEntity.getLevel());
            lifeIndex.setDetails(lifeIndexEntity.getContent());
            indexList.add(lifeIndex);
        }
        return indexList;
    }

    @Override
    public AirQualityLive getAirQualityLive() {

        KnowWeather.AqiEntity aqiEntity = knowWeather.getAqi();
        AirQualityLive airQualityLive = new AirQualityLive();
        airQualityLive.setCityId(knowWeather.getCityId());
        airQualityLive.setAqi(Integer.parseInt(aqiEntity.getAqi()));
        airQualityLive.setPm25(Integer.parseInt(aqiEntity.getPm25()));
        airQualityLive.setPm10(Integer.parseInt(aqiEntity.getPm10()));
        airQualityLive.setAdvice(aqiEntity.getAdvice());
        airQualityLive.setCityRank(aqiEntity.getCityRank());
        airQualityLive.setQuality(aqiEntity.getQuality());
        return airQualityLive;
    }

    /**
     * 拆分气温
     *
     * @param temperatureRange 如：-6~2°
     * @return {-6, 2}
     */
    private int[] splitTemperature(String temperatureRange) {
        if (temperatureRange != null && temperatureRange.contains("~")) {
            if (temperatureRange.contains("°")) {
                temperatureRange = temperatureRange.replaceAll("°", "");
            }
            String[] temps = temperatureRange.split("~");
            return new int[]{Integer.parseInt(temps[0]), Integer.parseInt(temps[1])};
        } else {
            return null;
        }
    }
}

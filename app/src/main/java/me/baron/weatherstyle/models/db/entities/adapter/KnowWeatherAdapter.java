package me.baron.weatherstyle.models.db.entities.adapter;

import java.util.ArrayList;
import java.util.List;

import me.baron.weatherstyle.models.db.entities.style.AQI;
import me.baron.weatherstyle.models.db.entities.style.Forecast;
import me.baron.weatherstyle.models.db.entities.style.LifeIndex;
import me.baron.weatherstyle.models.db.entities.style.RealTime;
import me.baron.weatherstyle.models.http.entities.know.KnowWeather;

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
    public RealTime getRealTime() {

        RealTime realTime = new RealTime();
        realTime.setCityId(knowWeather.getCityId());
        realTime.setHumidity("");
        realTime.setTemp(knowWeather.getBasic().getTemp());
        realTime.setTime(knowWeather.getBasic().getTime());
        realTime.setWeather(knowWeather.getBasic().getWeather());
        realTime.setWind("");
        realTime.setWindSpeed("");
        return realTime;
    }

    @Override
    public List<Forecast> getForecasts() {

        List<Forecast> forecasts = new ArrayList<>();
        List<KnowWeather.DailyForecastEntity> dailyForecasts = knowWeather.getDailyForecast();
        for (KnowWeather.DailyForecastEntity dailyForecastEntity : dailyForecasts) {
            Forecast forecast = new Forecast();
            forecast.setCityId(knowWeather.getCityId());
            forecast.setWind("");
            forecast.setWeather(dailyForecastEntity.getWeather());
            forecast.setWeek(dailyForecastEntity.getWeek());
            forecast.setData(dailyForecastEntity.getDate());
            forecasts.add(forecast);
        }
        return forecasts;
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
    public AQI getAQI() {

        KnowWeather.AqiEntity aqiEntity = knowWeather.getAqi();
        AQI aqi = new AQI();
        aqi.setCityId(knowWeather.getCityId());
        aqi.setAqi(Integer.parseInt(aqiEntity.getAqi()));
        aqi.setPm25(Integer.parseInt(aqiEntity.getPm25()));
        aqi.setPm10(Integer.parseInt(aqiEntity.getPm10()));
        aqi.setAdvice(aqiEntity.getAdvice());
        aqi.setCityRank(aqiEntity.getCityRank());
        return aqi;
    }
}

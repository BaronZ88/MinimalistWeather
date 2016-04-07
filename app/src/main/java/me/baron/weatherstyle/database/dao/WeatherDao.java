package me.baron.weatherstyle.database.dao;

import android.content.Context;

import me.baron.weatherstyle.database.WeatherDatabaseHelper;
import me.baron.weatherstyle.models.style.AQI;
import me.baron.weatherstyle.models.style.Forecast;
import me.baron.weatherstyle.models.style.LifeIndex;
import me.baron.weatherstyle.models.style.RealTime;
import me.baron.weatherstyle.models.style.Weather;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/3/14
 */
public class WeatherDao {

    private Context context;

    private Dao<AQI, Integer> apiDaoOperation;
    private Dao<Forecast, Long> forecastDaoOperation;
    private Dao<LifeIndex, Long> lifeIndexesDaoOperation;
    private Dao<RealTime, Integer> realTimeDaoOperation;
    private Dao<Weather, Integer> weatherDaoOperation;

    public WeatherDao(Context context) {

        this.context = context;
        this.apiDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(AQI.class);
        this.forecastDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(Forecast.class);
        this.lifeIndexesDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(LifeIndex.class);
        this.realTimeDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(RealTime.class);
        this.weatherDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(Weather.class);
    }

    public Weather queryWeather(int cityId) throws SQLException {

        final Weather[] weather = new Weather[1];
        TransactionManager.callInTransaction(WeatherDatabaseHelper.getInstance(context).getConnectionSource(), (Callable<Void>) () -> {
            weather[0] = weatherDaoOperation.queryForId(cityId);
            if (weather[0] != null) {
                weather[0].setAqi(apiDaoOperation.queryForId(cityId));
                weather[0].setForecasts(forecastDaoOperation.queryForEq(Forecast.CITY_ID_FIELD_NAME, cityId));
                weather[0].setLifeIndexes(lifeIndexesDaoOperation.queryForEq(Forecast.CITY_ID_FIELD_NAME, cityId));
                weather[0].setRealTime(realTimeDaoOperation.queryForId(cityId));
            }
            return null;
        });
        return weather[0];
    }

    public void insertWeather(Weather weather) throws SQLException {

        TransactionManager.callInTransaction(WeatherDatabaseHelper.getInstance(context).getConnectionSource(), (Callable<Void>) () -> {
            if (weatherDaoOperation.queryForId(weather.getCityId()) != null) {
                deleteWeather(weather.getCityId());
            }
            weatherDaoOperation.create(weather);
            apiDaoOperation.create(weather.getAqi());
            for (Forecast forecast : weather.getForecasts()) {
                forecastDaoOperation.create(forecast);
            }
            for (LifeIndex index : weather.getLifeIndexes()) {
                lifeIndexesDaoOperation.create(index);
            }
            realTimeDaoOperation.create(weather.getRealTime());
            return null;
        });
    }

    public void deleteWeather(int cityId) throws SQLException {

        weatherDaoOperation.deleteById(cityId);
    }

    /**
     * 查询数据库中的所有已添加的城市
     *
     * @return 结果集中只包括城市信息，天气数据不在其中
     * @throws SQLException
     */
    public List<Weather> queryAllSaveCity() throws SQLException {

        return weatherDaoOperation.queryForAll();
    }
}

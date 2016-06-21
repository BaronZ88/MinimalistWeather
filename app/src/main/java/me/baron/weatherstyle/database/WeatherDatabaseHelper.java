package me.baron.weatherstyle.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import me.baron.weatherstyle.models.style.AQI;
import me.baron.weatherstyle.models.style.Forecast;
import me.baron.weatherstyle.models.style.LifeIndex;
import me.baron.weatherstyle.models.style.RealTime;
import me.baron.weatherstyle.models.style.Weather;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/3/13
 */
public final class WeatherDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = "WeatherDatabaseHelper";

    private static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    private static volatile WeatherDatabaseHelper instance;

    public WeatherDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, AQI.class);
            TableUtils.createTable(connectionSource, Forecast.class);
            TableUtils.createTable(connectionSource, LifeIndex.class);
            TableUtils.createTable(connectionSource, RealTime.class);
            TableUtils.createTable(connectionSource, Weather.class);

            String weatherTrigger = "CREATE TRIGGER trigger_delete AFTER DELETE " +
                    "ON weather " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "DELETE FROM AQI WHERE cityId = OLD.cityId; " +
                    "DELETE FROM RealTime WHERE cityId = OLD.cityId; " +
                    "DELETE FROM Forecast WHERE cityId = OLD.cityId; " +
                    "DELETE FROM LifeIndex WHERE cityId = OLD.cityId; " +
                    "END;";
            database.execSQL(weatherTrigger);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    /**
     * 单例获取OpenHelper实例
     *
     * @param context application context
     * @return instance
     */
    public static WeatherDatabaseHelper getInstance(Context context) {

        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (WeatherDatabaseHelper.class) {
                if (instance == null) {
                    instance = new WeatherDatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void close() {
        super.close();
        DaoManager.clearCache();
    }

    public <D extends Dao<T, ?>, T> D getWeatherDao(Class<T> clazz) {
        try {
            return getDao(clazz);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}

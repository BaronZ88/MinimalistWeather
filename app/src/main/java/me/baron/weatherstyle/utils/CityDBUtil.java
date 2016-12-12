package me.baron.weatherstyle.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.baron.weatherstyle.AppConstants;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.WeatherApp;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/3/14
 */
public class CityDBUtil {

    /**
     * 导入城市数据库
     */
    public static void importCityData() {

        // 判断保持城市的数据库文件是否存在
        File file = new File(WeatherApp.getInstance().getDatabasePath(AppConstants.DB_NAME_CITY).getAbsolutePath());
        if (!file.exists()) {// 如果不存在，则导入数据库文件
            //数据库文件
            File dbFile = WeatherApp.getInstance().getDatabasePath(AppConstants.DB_NAME_CITY);
            try {
                if (!dbFile.getParentFile().exists()) {
                    dbFile.getParentFile().mkdir();
                }
                if (!dbFile.exists()) {
                    dbFile.createNewFile();
                }
                //加载欲导入的数据库
                InputStream is = WeatherApp.getInstance().getResources().openRawResource(R.raw.city);
                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                fos.write(buffer);
                is.close();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

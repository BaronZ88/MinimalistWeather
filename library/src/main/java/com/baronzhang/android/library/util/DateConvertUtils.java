package com.baronzhang.android.library.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 *         2016/12/12
 */
public final class DateConvertUtils {

    public static final String DATA_FORMAT_PATTEN_YYYY_MMMM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMAT_PATTEN_YYYY_MMMM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 将时间转换为时间戳
     *
     * @param data             待转换的日期
     * @param dataFormatPatten 待转换日期格式
     */
    public static long dateToTimeStamp(String data, String dataFormatPatten) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten);
        Date date = null;
        try {
            date = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.getTime();
    }

    /**
     * 将时间戳转换为日期
     *
     * @param time             待转换的时间戳
     * @param dataFormatPatten 转换出的日期格式
     */
    public static String timeStampToDate(long time, String dataFormatPatten) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPatten);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
}

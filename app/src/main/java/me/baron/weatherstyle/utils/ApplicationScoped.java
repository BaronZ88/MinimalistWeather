package me.baron.weatherstyle.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/12/1
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScoped {
}

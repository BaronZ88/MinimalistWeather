package com.baronzhang.android.weather.base

/**
 * view interface,所有View(此项目中的View主要是Fragment和自定义的ViewGroup)必须实现此接口
 *
 * @author baronzhang (微信公众号：BaronTalk)
 */
interface BaseView<T> {

    fun setPresenter(presenter: T)
}

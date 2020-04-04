package com.baronzhang.android.weather.base

import android.os.Bundle

/**
 * 用于实现 Fragment 懒加载，通常在 ViewPager 场景下使用，否者可以直接继承 BaseFragment
 *
 * @author baronzhang (公众号：BaronTalk)
 */
abstract class LazyLoadPageFragment : BaseFragment() {

    var isViewInitiated: Boolean = false
    var isVisibleToUser: Boolean = false
    var isDataInitiated: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareFetchData()
    }

    /**
     * setUserVisibleHint()在Fragment实例化时会先调用一次，并且默认值是false，当选中当前显示的Fragment时还会再调用一次。
     *
     * 此方法在fragment生命周期开始之前便被调用，运行在onAttach()之前。所以，它运行的时候，fragment都还没创建。
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareFetchData()
    }

    /**
     * 获取页面数据
     */
    abstract fun fetchData()

    /**
     * 是否需要强制刷新
     */
    fun isNeedForceUpdate(): Boolean {
        return false
    }

    private fun prepareFetchData(): Boolean {
        if (isViewInitiated && isVisibleToUser && (!isDataInitiated || isNeedForceUpdate())) {
            isDataInitiated = true
            fetchData()
            return true
        }
        return false
    }
}
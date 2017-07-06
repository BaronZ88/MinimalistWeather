package com.baronzhang.android.weather.view.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2017/7/6
 */
public class CannotScrollLinearLayoutManager extends LinearLayoutManager {

    public CannotScrollLinearLayoutManager(Context context) {
        super(context);
    }

    /**
     * 重写此方法解决ScrollView嵌套RecyclerView滑动卡顿的问题
     */
    @Override
    public boolean canScrollVertically() {
        return false;
    }
}

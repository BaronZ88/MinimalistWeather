package com.baronzhang.android.library.presenter;

/**
 * presenter interface,所有Presenter必须实现此接口
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public interface BasePresenter {

    void subscribe();

    void unSubscribe();
}

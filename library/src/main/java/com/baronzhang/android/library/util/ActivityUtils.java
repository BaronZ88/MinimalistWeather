package com.baronzhang.android.library.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/4/13
 */
public final class ActivityUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}

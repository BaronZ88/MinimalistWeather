package com.baronzhang.android.weather.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import android.view.MenuItem

/**
 * @author baronzhang (微信公众号：BaronTalk)
 */
open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        /*
         * 解决Vector兼容性问题
         *
         * First up, this functionality was originally released in 23.2.0,
         * but then we found some memory usage and Configuration updating
         * issues so we it removed in 23.3.0. In 23.4.0 (technically a fix
         * release) we’ve re-added the same functionality but behind a flag
         * which you need to manually enable.
         *
         * http://www.jianshu.com/p/e3614e7abc03
         */
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}

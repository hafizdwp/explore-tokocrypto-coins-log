package com.hafizdwp.explore_tokocrypto_coins_log.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.Preference
import com.hafizdwp.explore_tokocrypto_coins_log.util.log

/**
 * @author hafizdwp
 * 18/05/2021
 **/
abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutRes: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyNightMode()
        setContentView(layoutRes)
    }

    private fun applyNightMode() {
        val pref = Preference.getInstance()
        val isNightMode = pref.nightMode
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun <T> LiveData<T>.observe(action: (T?) -> Unit) {
        observe(this@BaseActivity, {
            action.invoke(it)
        })
    }
}
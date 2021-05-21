package com.hafizdwp.explore_tokocrypto_coins_log.data.local

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.hafizdwp.explore_tokocrypto_coins_log.app.MyApplication

/**
 * @author hafizdwp
 * 21/05/2021
 **/
object Preference {
    private val INSTANCE by lazy { PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()) }
    fun getInstance(): SharedPreferences = INSTANCE

    const val KEY_NIGHT_MODE = "key_night_mode"
}
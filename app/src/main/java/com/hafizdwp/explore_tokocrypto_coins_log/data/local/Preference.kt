package com.hafizdwp.explore_tokocrypto_coins_log.data.local

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.hafizdwp.explore_tokocrypto_coins_log.app.MyApplication

/**
 * @author hafizdwp
 * 21/05/2021
 **/
class Preference {

    companion object {
        private var instance: Preference? = null

        @JvmStatic
        fun getInstance(): Preference {
            return instance ?: synchronized(Preference::class.java) {
                instance ?: Preference().also {
                    instance = it
                }
            }
        }
    }

    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()) }

    private fun SharedPreferences.Editor.putDouble(key: String, double: Double) =
            putLong(key, java.lang.Double.doubleToRawLongBits(double))

    private fun SharedPreferences.getDouble(key: String, default: Double) =
            java.lang.Double.longBitsToDouble(getLong(key, java.lang.Double.doubleToRawLongBits(default)))

    /// ============================================================================================
    /// Keys

    private val KEY_CACHE_TIME = "key_cache_time"
    private val KEY_NIGHT_MODE = "key_night_mode"
    private val KEY_IDR_RATE = "key_idr_rate"


    /// ============================================================================================
    /// Variables

    var nightMode: Boolean
        get() = prefs.getBoolean(KEY_NIGHT_MODE, false)
        set(isNightMode) = prefs.edit { putBoolean(KEY_NIGHT_MODE, isNightMode) }

    var idrRate: Double
        get() = prefs.getDouble(KEY_IDR_RATE, 0.0)
        set(newIdrRate) = prefs.edit { putDouble(KEY_IDR_RATE, newIdrRate) }

    var lastCache: Long
        get() = prefs.getLong(KEY_CACHE_TIME, System.currentTimeMillis())
        set(newCacheTime) = prefs.edit { putLong(KEY_CACHE_TIME, newCacheTime) }
}
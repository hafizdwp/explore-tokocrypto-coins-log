package com.hafizdwp.explore_tokocrypto_coins_log.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.Preference
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class LocalDataSource(private val database: Database,
                      private val pref: SharedPreferences) {

    companion object {
        private var instance: LocalDataSource? = null

        @JvmStatic
        fun getInstance(database: Database,
                        pref: SharedPreferences): LocalDataSource {
            return instance ?: synchronized(LocalDataSource::class.java) {
                instance ?: LocalDataSource(database, pref).also {
                    instance = it
                }
            }
        }
    }

    suspend fun saveSymbols(list: List<Symbol>) {
        database.symbolDao().insert(list)
    }

    suspend fun getSymbols(): List<Symbol> {
        return database.symbolDao().getAll()
    }

    suspend fun deleteSymbols() {
        database.symbolDao().nuke()
    }

    suspend fun saveCoins(list: List<Coin>) {
        database.coinDao().insert(list)
    }

    suspend fun getCoins(): List<Coin> {
        return database.coinDao().getAll()
    }

    suspend fun deleteCoins() {
        database.coinDao().nuke()
    }

    fun getNightMode(): Boolean {
        return pref.getBoolean(Preference.KEY_NIGHT_MODE, false)
    }

    fun saveNightMode(isNightMode: Boolean) {
        pref.edit { putBoolean(Preference.KEY_NIGHT_MODE, isNightMode) }
    }
}
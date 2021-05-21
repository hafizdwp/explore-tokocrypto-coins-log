package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.local.Preference
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol
import com.hafizdwp.explore_tokocrypto_coins_log.util.log

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class LocalDataSource(private val database: Database,
                      private val pref: Preference) {

    companion object {
        private var instance: LocalDataSource? = null
        private const val CACHE_LIMIT = (10 * 1000).toLong()

        @JvmStatic
        fun getInstance(database: Database,
                        pref: Preference): LocalDataSource {
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
        deleteCoins()
        database.coinDao().insert(list)
    }

    suspend fun getCoins(): List<Coin> {
        return database.coinDao().getAll()
    }

    suspend fun deleteCoins() {
        database.coinDao().nuke()
    }

    fun getNightMode(): Boolean {
        return pref.nightMode
    }

    fun saveNightMode(isNightMode: Boolean) {
        pref.nightMode = isNightMode
    }

    fun getIdrRate(): Double {
        return pref.idrRate
    }

    fun saveIdrRate(idrRate: Double) {
        pref.idrRate = idrRate
    }

    fun saveLastCache(mill: Long) {
        pref.lastCache = mill
    }

    fun isCacheExpires(): Boolean {
        val now = System.currentTimeMillis()
        val cacheTime = pref.lastCache

        log("""
            isCacheExpires...
            cache limit: $CACHE_LIMIT
            calculation: ${now - cacheTime} therefor ${now - cacheTime > CACHE_LIMIT}
        """.trimIndent())
        return now - cacheTime > CACHE_LIMIT
    }
}
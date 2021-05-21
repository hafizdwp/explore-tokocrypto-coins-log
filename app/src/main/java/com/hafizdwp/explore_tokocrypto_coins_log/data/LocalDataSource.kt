package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.local.Preference
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.CacheMap
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol
import com.hafizdwp.explore_tokocrypto_coins_log.util.log
import com.hafizdwp.explore_tokocrypto_coins_log.util.logError

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

    suspend fun saveMapCache(tag: String) {
        var cacheMap = database.cacheMapDao().getByTag(tag)
        if (cacheMap != null) {
            cacheMap.isExpired = false
            database.cacheMapDao().update(cacheMap)
        } else {
            cacheMap = CacheMap(tag = tag, isExpired = false)
            database.cacheMapDao().insert(cacheMap)
        }
    }

    suspend fun isCacheExpires(tag: String): Boolean {
        val now = System.currentTimeMillis()
        val globalCache = pref.globalCache

        val isGlobalCacheExpired = globalCache == 0L || (now - globalCache > CACHE_LIMIT)
        log("isGlobalCacheExpired: $isGlobalCacheExpired")
        log("CACHE LIMIT: $CACHE_LIMIT")
        log("difference: ${now - globalCache}")
        return if (isGlobalCacheExpired) {
            logError("globalcache is expired. assigning new global cache, and refresh map caches")
            pref.globalCache = System.currentTimeMillis()

            val currentCacheMap = database.cacheMapDao().getAll()
            val expiredCacheMaps = currentCacheMap.map {
                CacheMap(tag = it.tag, isExpired = true)
            }

            database.cacheMapDao().nuke()
            database.cacheMapDao().insert(expiredCacheMaps)

            true
        } else {
            log("global cache is not expired. checking tags ...")
            val cacheByTag = database.cacheMapDao().getByTag(tag)
            log("is tag expired: $cacheByTag")

            cacheByTag?.isExpired == true
        }
    }
}
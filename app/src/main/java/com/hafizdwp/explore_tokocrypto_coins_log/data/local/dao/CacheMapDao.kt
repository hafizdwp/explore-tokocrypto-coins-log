package com.hafizdwp.explore_tokocrypto_coins_log.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseDao
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.CacheMap
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin

/**
 * @author hafizdwp
 * 21/05/2021
 **/

@Dao
abstract class CacheMapDao: BaseDao<CacheMap> {

    @Query("SELECT * FROM table_cache_map")
    abstract suspend fun getAll(): List<CacheMap>

    @Query("SELECT * FROM table_cache_map WHERE tag = :tag")
    abstract suspend fun getByTag(tag: String): CacheMap?

    @Query("DELETE FROM table_cache_map")
    abstract suspend fun nuke()
}
package com.hafizdwp.explore_tokocrypto_coins_log.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseDao
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin

/**
 * @author hafizdwp
 * 20/05/2021
 **/

@Dao
abstract class CoinDao : BaseDao<Coin> {

    @Query("SELECT * FROM table_coin")
    abstract suspend fun getAll(): List<Coin>

    @Query("DELETE FROM table_coin")
    abstract suspend fun nuke()
}
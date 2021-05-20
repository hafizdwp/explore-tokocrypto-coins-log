package com.hafizdwp.explore_tokocrypto_coins_log.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseDao
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol

/**
 * @author hafizdwp
 * 20/05/2021
 **/

@Dao
abstract class SymbolDao : BaseDao<Symbol> {

    @Query("SELECT * FROM table_symbol")
    abstract suspend fun getAll(): List<Symbol>
}
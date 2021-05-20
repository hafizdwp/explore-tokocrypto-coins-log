package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class LocalDataSource(private val database: Database) {

    suspend fun saveSymbols(list: List<Symbol>) {
        database.symbolDao().insert(list)
    }

    suspend fun getSymbols(): List<Symbol> {
        return database.symbolDao().getAll()
    }

    companion object {
        private var instance: LocalDataSource? = null

        @JvmStatic
        fun getInstance(database: Database): LocalDataSource {
            return instance ?: synchronized(LocalDataSource::class.java) {
                instance ?: LocalDataSource(database).also {
                    instance = it
                }
            }
        }
    }
}
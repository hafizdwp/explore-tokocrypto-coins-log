package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class Repository(private val remoteDataSource: RemoteDataSource,
                 private val localDataSource: LocalDataSource) {

    suspend fun getAllSymbols(): List<Symbol> {
        val remoteResponse = remoteDataSource.getAllSymbols()
        val filteredUsdtSymbols = RepositoryExt.filterUsdtSymbols(remoteResponse.data?.list)
        val convertedSymbols = RepositoryExt.convertToSymbolTables(filteredUsdtSymbols)

        localDataSource.deleteSymbols()
        localDataSource.saveSymbols(convertedSymbols)
        return localDataSource.getSymbols()
    }

    suspend fun getCoinsBySymbols(symbols: List<String>): List<Coin> {
        val remoteResponse = remoteDataSource.getCoinsBySymbols(symbols)
        val convertedCoins = RepositoryExt.convertToCoinTables(remoteResponse)

        localDataSource.deleteCoins()
        localDataSource.saveCoins(convertedCoins)
        return localDataSource.getCoins()
    }


    companion object {
        private var instance: Repository? = null

        @JvmStatic
        fun getInstance(rds: RemoteDataSource, lds: LocalDataSource): Repository {
            return instance ?: synchronized(Repository::class.java) {
                instance ?: Repository(rds, lds).also {
                    instance = it
                }
            }
        }
    }
}
package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.BaseListResponse
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.ExchangeResponse

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

        localDataSource.saveSymbols(convertedSymbols)
        return localDataSource.getSymbols()
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
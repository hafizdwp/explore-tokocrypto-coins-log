package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class Repository(private val remote: RemoteDataSource,
                 private val local: LocalDataSource) {

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

    suspend fun getIdrRate(refresh: Boolean): Double {
        val tag = ::getIdrRate.name
        if (local.getIdrRate() == 0.0
                || local.isCacheExpires(tag)
                || refresh) {
            getRemoteIdrRate(tag)
        }

        return local.getIdrRate()
    }

    private suspend fun getRemoteIdrRate(tag: String) {
        val rateResponse = remote.getExchangeRates()

        local.saveMapCache(tag)
        local.saveIdrRate(rateResponse.rates.IDR)
    }

//    suspend fun getAllSymbols(): List<Symbol> {
//        val remoteResponse = remote.getAllSymbols()
//        val filteredUsdtSymbols = RepositoryExt.filterUsdtSymbols(remoteResponse.data?.list)
//        val convertedSymbols = RepositoryExt.convertToSymbolTables(filteredUsdtSymbols)
//
//        local.deleteSymbols()
//        local.saveSymbols(convertedSymbols)
//        return local.getSymbols()
//    }

    suspend fun getCoinsBySymbols(symbols: List<String>, idrRate: Double, refresh: Boolean): List<Coin> {
        val tag = ::getCoinsBySymbols.name
        if (local.getCoins().isEmpty()
                || local.isCacheExpires(tag)
                || refresh) {
            getRemoteCoinsBySymbols(symbols, idrRate, tag)
        }

        return local.getCoins()
    }

    private suspend fun getRemoteCoinsBySymbols(symbols: List<String>, idrRate: Double, tag: String) {
        val coinResponses = remote.getCoinsBySymbols(symbols)
        val coinEntities = coinResponses.map {
            Coin(symbol = it.symbol,
                    name = it.name,
                    image = it.image,
                    current_price = it.current_price,
                    current_price_idr = it.current_price * idrRate)
        }

        local.saveMapCache(tag)
        local.saveCoins(coinEntities)
    }

    fun getNightMode(): Boolean {
        return local.getNightMode()
    }

    fun saveNightMode(isNightMode: Boolean) {
        local.saveNightMode(isNightMode)
    }
}
package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.api.CoingeckoApi
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.api.TokocryptoApi
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.BaseListResponse
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.CoinResponse
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.ExchangeResponse
import io.ktor.client.*

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class RemoteDataSource(api: HttpClient) {

    private val tokocryptoApi by lazy { TokocryptoApi.getInstance(api) }
    private val coingeckoApi by lazy { CoingeckoApi.getInstance(api) }

    suspend fun getAllSymbols(): BaseListResponse<ExchangeResponse> {
        return tokocryptoApi.getAllSymbols()
    }

    suspend fun getCoinsBySymbols(symbols: List<String>): List<CoinResponse> {
        return coingeckoApi.getCoinsBySymbols(symbols)
    }
}
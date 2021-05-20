package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.api

import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.CoinResponse
import com.hafizdwp.explore_tokocrypto_coins_log.ktor.Endpoint
import io.ktor.client.*
import io.ktor.client.request.*

/**
 * @author hafizdwp
 * 20/05/2021
 **/
class CoingeckoApi(private val api: HttpClient) {

    suspend fun getCoinsBySymbols(symbols: List<String>): List<CoinResponse> {
        return api.get(Endpoint.CG_COINS_MARKET) {
            parameter("vs_currency", "usd")
            parameter("ids", symbols.joinToString(","))
        }
    }


    companion object {
        private var instance: CoingeckoApi? = null

        @JvmStatic
        fun getInstance(api: HttpClient): CoingeckoApi {
            return instance ?: synchronized(CoingeckoApi::class.java) {
                instance ?: CoingeckoApi(api).also {
                    instance = it
                }
            }
        }
    }
}
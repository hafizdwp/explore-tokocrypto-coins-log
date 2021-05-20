package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.api

import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.ExchangeRatesResponse
import io.ktor.client.*
import io.ktor.client.request.*

/**
 * @author hafizdwp
 * 20/05/2021
 **/
class OpenExchangeApi(private val api: HttpClient) {

    suspend fun getExchangeRates(): ExchangeRatesResponse {
        return api.get("https://openexchangerates.org/api/latest.json?app_id=9dea138f43654081a266f40e3dd7d5bb")
    }

    companion object {
        private var instance: OpenExchangeApi? = null

        @JvmStatic
        fun getInstance(api: HttpClient): OpenExchangeApi {
            return instance ?: synchronized(OpenExchangeApi::class.java) {
                instance ?: OpenExchangeApi(api).also {
                    instance = it
                }
            }
        }
    }
}
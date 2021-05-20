package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.api

import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.BaseListResponse
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.ExchangeResponse
import com.hafizdwp.explore_tokocrypto_coins_log.ktor.Endpoint
import io.ktor.client.*
import io.ktor.client.request.*

/**
 * @author hafizdwp
 * 20/05/2021
 **/
class TokocryptoApi(private val api: HttpClient) {

    suspend fun getAllSymbols(): BaseListResponse<ExchangeResponse> {
        return api.get(Endpoint.TKO_ALL_SYMBOLS)
    }


    companion object {
        private var instance: TokocryptoApi? = null

        @JvmStatic
        fun getInstance(api: HttpClient): TokocryptoApi {
            return instance ?: synchronized(TokocryptoApi::class.java) {
                instance ?: TokocryptoApi(api).also {
                    instance = it
                }
            }
        }
    }
}
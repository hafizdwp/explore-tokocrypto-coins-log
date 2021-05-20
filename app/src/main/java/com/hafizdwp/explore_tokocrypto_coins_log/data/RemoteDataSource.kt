package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.BaseListResponse
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.ExchangeResponse
import com.hafizdwp.explore_tokocrypto_coins_log.ktor.Endpoint
import io.ktor.client.*
import io.ktor.client.request.*

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class RemoteDataSource(private val api: HttpClient) {

    suspend fun getAllSymbols(): BaseListResponse<ExchangeResponse> {
        return api.get(Endpoint.TKO_ALL_SYMBOLS)
    }
}
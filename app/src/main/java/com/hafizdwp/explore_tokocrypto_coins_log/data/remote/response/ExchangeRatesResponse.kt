package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response

import kotlinx.serialization.Serializable

/**
 * @author hafizdwp
 * 20/05/2021
 **/

@Serializable
data class ExchangeRatesResponse(
        var disclaimer: String,
        var license: String,
        var timestamp: Int,
        var base: String,
        var rates: Rates
) {

    @Serializable
    data class Rates(
            var IDR: Double
    )
}
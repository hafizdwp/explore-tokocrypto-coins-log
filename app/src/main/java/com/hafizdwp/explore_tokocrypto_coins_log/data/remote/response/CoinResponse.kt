package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response

import kotlinx.serialization.Serializable

/**
 * @author hafizdwp
 * 20/05/2021
 **/

@Serializable
data class CoinResponse(
    var id: String? = null,
    var symbol: String,
    var name: String,
    var image: String,
    var current_price: Double,
    var market_cap: Double? = null,
    var market_cap_rank: Int? = null,
    var fully_diluted_valuation: Double? = null,
    var total_volume: Double? = null,
    var high_24h: Double? = null,
    var low_24h: Double? = null,
    var price_change_24h: Double? = null,
    var price_change_percentage_24h: Double? = null,
    var market_cap_change_24h: Double? = null,
    var market_cap_change_percentage_24h: Double? = null,
    var circulating_supply: Double? = null,
    var total_supply: Double? = null,
    var max_supply: Double? = null,
    var ath: Double? = null,
    var ath_change_percentage: Double? = null,
    var ath_date: String? = null,
    var atl: Double? = null,
    var atl_change_percentage: Double? = null,
    var atl_date: String? = null,
    var last_updated: String? = null,
    var price_change_percentage_1h_in_currency: Double? = null
)
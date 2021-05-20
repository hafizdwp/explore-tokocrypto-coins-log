package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response

import kotlinx.serialization.Serializable

/**
 * @author hafizdwp
 * 20/05/2021
 **/
@Serializable
data class ExchangeResponse(
        var type: Int? = null,
        var symbol: String? = null,
        var baseAsset: String? = null,
        var basePrecision: Int? = null,
        var quoteAsset: String? = null,
        var quotePrecision: Int? = null,
        var filters: List<Filter?>? = null,
        var orderTypes: List<String?>? = null,
        var icebergEnable: Int? = null,
        var ocoEnable: Int? = null,
        var spotTradingEnable: Int? = null,
        var marginTradingEnable: Int? = null,
        var permissions: List<String?>? = null
) {

    @Serializable
    data class Filter(
            var filterType: String? = null,
            var minPrice: String? = null,
            var maxPrice: String? = null,
            var tickSize: String? = null,
            var applyToMarket: Boolean? = null,
            var multiplierUp: Int? = null,
            var multiplierDown: Double? = null,
            var avgPriceMins: String? = null,
            var minQty: String? = null,
            var maxQty: String? = null,
            var stepSize: String? = null,
            var minNotional: String? = null,
            var limit: String? = null,
            var maxNumAlgoOrders: String? = null
    )
}
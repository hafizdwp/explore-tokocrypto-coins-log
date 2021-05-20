package com.hafizdwp.explore_tokocrypto_coins_log.ktor

/**
 * @author hafizdwp
 * 20/05/2021
 **/
object Endpoint {
    const val BASE_URL = "api.github.com"
    const val GITHUB = "users/hafizdwp"


    private const val BASE_URL_TOKOCRYPTO = "https://www.tokocrypto.com/"
    const val TKO_ALL_SYMBOLS = "${BASE_URL_TOKOCRYPTO}open/v1/common/symbols"

    private const val BASE_URL_COINGECKO = "https://api.coingecko.com/api/v3/"
    const val CG_COINS_MARKET = "${BASE_URL_COINGECKO}coins/markets"
}
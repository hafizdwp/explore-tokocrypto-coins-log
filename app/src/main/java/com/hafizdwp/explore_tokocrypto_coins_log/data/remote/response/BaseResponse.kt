package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response

/**
 * @author hafizdwp
 * 20/05/2021
 **/
data class BaseResponse<T>(
        var code: Int,
        var msg: String,
        var data: T? = null
)
package com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response

import kotlinx.serialization.Serializable

/**
 * @author hafizdwp
 * 20/05/2021
 **/

@Serializable
data class BaseListResponse<T>(
        var code: Int,
        var msg: String,
        var data: NestedList<T>?
) {

    @Serializable
    data class NestedList<T>(
            var list: List<T>? = null
    )
}
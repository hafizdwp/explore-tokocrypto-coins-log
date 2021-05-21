package com.hafizdwp.explore_tokocrypto_coins_log.data.local.table

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author hafizdwp
 * 21/05/2021
 **/

@Entity(tableName = "table_cache_map")
data class CacheMap(
        @PrimaryKey
        var id: Int? = null,
        var tag: String,
        var isExpired: Boolean
)
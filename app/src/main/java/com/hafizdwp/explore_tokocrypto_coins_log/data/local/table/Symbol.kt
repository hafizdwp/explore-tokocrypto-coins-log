package com.hafizdwp.explore_tokocrypto_coins_log.data.local.table

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author hafizdwp
 * 20/05/2021
 **/

@Entity(tableName = "table_symbol")
data class Symbol(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var name: String
)
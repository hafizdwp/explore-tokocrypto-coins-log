package com.hafizdwp.explore_tokocrypto_coins_log.data

import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.ExchangeResponse
import com.hafizdwp.explore_tokocrypto_coins_log.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * @author hafizdwp
 * 20/05/2021
 **/
object RepositoryExt {

    suspend fun convertToSymbolTables(filteredUsdtSymbols: List<ExchangeResponse>): List<Symbol> {
        return withContext(Dispatchers.Default) {
            val filteredList = arrayListOf<Symbol>()
            filteredUsdtSymbols.forEach {
                val symbol = Symbol(name = it.baseAsset.toString().toLowerCase(Locale.ROOT))
                filteredList.add(symbol)
            }

            filteredList
        }
    }

    suspend fun filterUsdtSymbols(list: List<ExchangeResponse>?): List<ExchangeResponse> {
        return withContext(Dispatchers.Default) {
            val filteredList = arrayListOf<ExchangeResponse>()
            var index = 0
            list?.forEach {
                log("filtering... ${index++}")
                if (it.quoteAsset == "USDT")
                    filteredList.add(it)
            }

            filteredList
        }
    }
}
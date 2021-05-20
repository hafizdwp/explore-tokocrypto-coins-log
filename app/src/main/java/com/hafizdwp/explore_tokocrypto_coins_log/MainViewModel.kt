package com.hafizdwp.explore_tokocrypto_coins_log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseViewModel
import com.hafizdwp.explore_tokocrypto_coins_log.data.Repository
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author hafizdwp
 * 18/05/2021
 **/
class MainViewModel(private val repository: Repository) : BaseViewModel() {

    private val _coins = MutableLiveData<String>()
    val coins: LiveData<String>
        get() = _coins

    fun getAllCoins() = viewModelScope.launch {
        try {
            val symbols: List<Symbol> = asyncAwait { repository.getAllSymbols() }
            val stringSymbols = convertSymbolsToString(symbols)
            val coins: List<Coin> = asyncAwait { repository.getCoinsBySymbols(stringSymbols) }

            _coins.value = coins.toJson()

        } catch (e: Exception) {
            logError(e.toString())
        }
    }

    private suspend fun convertSymbolsToString(symbols: List<Symbol>): List<String> {
        return withContext(Dispatchers.Default) {
            val strings = arrayListOf<String>()
            symbols.forEach {
                strings.add(it.name)
            }

            strings
        }
    }
}
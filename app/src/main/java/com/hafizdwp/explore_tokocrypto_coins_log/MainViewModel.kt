package com.hafizdwp.explore_tokocrypto_coins_log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseViewModel
import com.hafizdwp.explore_tokocrypto_coins_log.data.Repository
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol
import com.hafizdwp.explore_tokocrypto_coins_log.data.remote.response.ExchangeRatesResponse
import com.hafizdwp.explore_tokocrypto_coins_log.util.logError
import kotlinx.coroutines.*

/**
 * @author hafizdwp
 * 18/05/2021
 **/
class MainViewModel(private val repository: Repository) : BaseViewModel() {

    val coins: LiveData<Pair<List<Coin>, Double>>
        get() = _coins
    val swipe: LiveData<Boolean>
        get() = _swipe

    private val _coins = MutableLiveData<Pair<List<Coin>, Double>>()
    private val _swipe = MutableLiveData<Boolean>()


    fun getAllCoins() = viewModelScope.launch {
        try {
            _swipe.value = true
//            val symbols: List<Symbol> = asyncAwait { repository.getAllSymbols() }
//            val stringSymbols = convertSymbolsToString(symbols)
//            val coins: List<Coin> = asyncAwait { repository.getCoinsBySymbols(stringSymbols) }


            // Coins
            val stringSymbols = convertSymbolsToString(listSymbol)
            val deferredCoins: Deferred<List<Coin>> = async { repository.getCoinsBySymbols(stringSymbols) }
            val deferredIdr: Deferred<ExchangeRatesResponse> = async { repository.getExchangeRates() }

            val sortedCoins = deferredCoins.await().sortedBy { it.symbol }
            val idr = deferredIdr.await().rates.IDR

            // Post value
            _coins.value = Pair(
                    sortedCoins,
                    idr
            )

        } catch (e: Exception) {
            logError(e.toString())
        } finally {
            _swipe.value = false
        }
    }

    private suspend fun convertSymbolsToString(symbols: List<Symbol>): List<String> {
        return withContext(Dispatchers.Default) {
            val strings = arrayListOf<String>()
            symbols.forEach {
                strings.add(it.id)
            }

            strings
        }
    }

    fun getNightMode(): Boolean = repository.getNightMode()

    fun switchNightMode() {
        repository.saveNightMode(!repository.getNightMode())
    }
}
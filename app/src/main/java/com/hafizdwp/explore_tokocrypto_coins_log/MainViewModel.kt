package com.hafizdwp.explore_tokocrypto_coins_log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseViewModel
import com.hafizdwp.explore_tokocrypto_coins_log.data.Repository
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.util.logError
import kotlinx.coroutines.launch

/**
 * @author hafizdwp
 * 18/05/2021
 **/
class MainViewModel(private val repository: Repository) : BaseViewModel() {

    val coins: LiveData<List<Coin>>
        get() = _coins
    val swipe: LiveData<Boolean>
        get() = _swipe

    private val _coins = MutableLiveData<List<Coin>>()
    private val _swipe = MutableLiveData<Boolean>()


    fun getAllCoins(refresh: Boolean = false) = viewModelScope.launch {
        try {
            _swipe.value = true

            val idrRate = withIO { repository.getIdrRate(refresh) }
            val coins = withIO { repository.getCoinsBySymbols(listSymbol, idrRate, refresh) }
            val sortedCoins = withDefault { coins.sortedBy { it.symbol } }

            _coins.value = sortedCoins

        } catch (e: Exception) {
            logError(e.toString())
        } finally {
            _swipe.value = false
        }
    }

    fun getNightMode(): Boolean = repository.getNightMode()

    fun switchNightMode() {
        repository.saveNightMode(!repository.getNightMode())
    }

    //            val symbols: List<Symbol> = asyncAwait { repository.getAllSymbols() }
//            val stringSymbols = convertSymbolsToString(symbols)
//            val coins: List<Coin> = asyncAwait { repository.getCoinsBySymbols(stringSymbols) }
}
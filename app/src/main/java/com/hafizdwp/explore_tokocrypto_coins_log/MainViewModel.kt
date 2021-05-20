package com.hafizdwp.explore_tokocrypto_coins_log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseViewModel
import com.hafizdwp.explore_tokocrypto_coins_log.data.Repository
import kotlinx.coroutines.launch

/**
 * @author hafizdwp
 * 18/05/2021
 **/
class MainViewModel(private val repository: Repository) : BaseViewModel() {

    private val _randomFact = MutableLiveData<String>()
    val randomFact: LiveData<String>
        get() = _randomFact

    fun getAllSymbols() = viewModelScope.launch {
        try {
            val response = asyncAwait { repository.getAllSymbols() }
            _randomFact.value = response.toJson()

        } catch (e: Exception) {
        }
    }
}
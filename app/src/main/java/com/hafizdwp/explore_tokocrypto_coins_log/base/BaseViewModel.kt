package com.hafizdwp.explore_tokocrypto_coins_log.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @author hafizdwp
 * 20/05/2021
 **/
abstract class BaseViewModel : ViewModel() {

    suspend fun <T> asyncAwait(context: CoroutineContext = Dispatchers.Default,
                               action: suspend () -> T): T {
        return withContext(context) { action() }
    }
}
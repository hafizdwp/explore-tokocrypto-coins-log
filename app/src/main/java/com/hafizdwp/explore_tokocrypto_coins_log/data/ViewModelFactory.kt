package com.hafizdwp.explore_tokocrypto_coins_log.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hafizdwp.explore_tokocrypto_coins_log.MainViewModel
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.Preference
import com.hafizdwp.explore_tokocrypto_coins_log.ktor.ktorHttpClient

/**
 * @author hafizdwp
 * 17/05/2021
 **/
class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application) =
                instance ?: synchronized(ViewModelFactory::class.java) {
                    instance ?: ViewModelFactory(provideRepository(application.applicationContext))
                            .also { instance = it }
                }

        private fun provideRepository(context: Context): Repository {
            val rds = RemoteDataSource(ktorHttpClient)

            val database = Database.getInstance(context)
            val pref = Preference.getInstance()
            val lds = LocalDataSource.getInstance(database, pref)

            return Repository.getInstance(rds, lds)
        }
    }
}
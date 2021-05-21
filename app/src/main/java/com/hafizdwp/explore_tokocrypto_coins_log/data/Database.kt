package com.hafizdwp.explore_tokocrypto_coins_log.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.dao.CacheMapDao
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.dao.CoinDao
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.dao.SymbolDao
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.CacheMap
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol

@androidx.room.Database(
        entities = [
            Symbol::class,
            Coin::class,
            CacheMap::class],
        version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun symbolDao(): SymbolDao
    abstract fun coinDao(): CoinDao
    abstract fun cacheMapDao(): CacheMapDao

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance(context: Context): Database {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "crypto.db"
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                this.instance = instance

                // return instance
                instance
            }
        }
    }
}
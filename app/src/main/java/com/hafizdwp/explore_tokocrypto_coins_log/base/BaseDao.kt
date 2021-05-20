package com.hafizdwp.explore_tokocrypto_coins_log.base

import androidx.room.*


/**
 * Created by mochadwi on 8/3/18.
 */
@Dao
interface BaseDao<T> {
    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     * @return The SQLite row id
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param objList the objects to be inserted.
     * @return The SQLite row ids
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(objList: List<T>): List<Long>

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    suspend fun update(obj: T): Int

    /**
     * Update an array of objects from the database.
     *
     * @param objList the object to be updated
     */
    @Update
    suspend fun update(objList: List<T>): Int

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    suspend fun delete(obj: T)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    suspend fun delete(obj: List<T>)

//    /**
//     * Upsert for object
//     *
//     * @param obj
//     */
//    @Transaction
//    fun upsert(obj: T): Int {
//        val id = insert(obj)
//        if (id == -1L) {
//            return update(obj)
//        }
//
//        return id.toInt()
//    }

    /**
     * Upsert for object list
     *
     * @param objList
     */
//    @Transaction
//    fun upsert(objList: List<T>) {
//        val insertResult = insert(objList)
//        val updateList = ArrayList<T>()
//
//        for (i in insertResult.indices) {
//            if (insertResult[i] == -1L) {
//                updateList[i] = objList[i]
//            }
//        }
//
//        if (!updateList.isEmpty()) {
//            update(updateList)
//        }
//    }

    /**
     * Delete old list and insert new list
     *
     * @param oldList
     */
//    @Transaction
//    fun delsert(oldList: List<T>, newList: List<T>) {
//        delete(oldList)
//        insert(newList)
//    }
}
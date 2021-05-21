package com.hafizdwp.explore_tokocrypto_coins_log.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.hafizdwp.explore_tokocrypto_coins_log.app.MyApplication
import com.hafizdwp.explore_tokocrypto_coins_log.data.ViewModelFactory


inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel() =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(MyApplication.getInstance())).get(T::class.java)

inline fun <reified VM : ViewModel> Fragment.obtainViewModel() =
        ViewModelProviders.of(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(VM::class.java)


val gson by lazy { GsonBuilder().setPrettyPrinting().create() }
//val gson by lazy { Gson() }

inline fun <reified T> makeType() = object : TypeToken<T>() {}.type

fun <T> T.toJson(): String = gson.toJson(this)

inline fun <reified T> String.fromJson(): T = gson.fromJson(this, makeType<T>())

/**
 * Log ext
 * */
fun log(msg: String,
        tag: String? = null) {
    Log.d(tag ?: "mytag", msg)
}

fun logError(msg: String,
             tag: String? = null) {
    Log.e(tag ?: "mytag", msg)
}

fun logWarning(msg: String,
               tag: String? = null) {
    Log.w(tag ?: "mytag", msg)
}

/**
 * Ez toast
 * */
var mToast: Toast? = null

fun AppCompatActivity.toast(msg: String?) {
    if (!msg.isNullOrBlank()) {
        mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        mToast?.show()
    }
}

/**
 * @param msg    message
 * @param length Toast.LENGTH_LONG or Toast.LENGTH_SHORT or int numbers
 * */
fun AppCompatActivity.toastSpammable(msg: String?,
                                     length: Int = Toast.LENGTH_LONG) {
    if (!msg.isNullOrBlank()) {
        if (mToast != null) mToast?.cancel()
        mToast = Toast.makeText(this, msg, length)
        mToast?.show()
    }
}

fun Context.toast(msg: String?, length: Int = Toast.LENGTH_LONG) {
    if (!msg.isNullOrBlank()) {
        mToast = Toast.makeText(this, msg, length)
        mToast?.show()
    }
}

fun Context.toastSpammable(msg: String?) {
    if (!msg.isNullOrBlank()) {
        if (mToast != null) mToast?.cancel()
        mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        mToast?.show()
    }
}

fun Fragment.toast(msg: String?) {
    (requireActivity() as? AppCompatActivity)?.toast(msg)
}

fun Fragment.toastSpammable(msg: String?,
                            length: Int = Toast.LENGTH_LONG) {
    (requireActivity() as? AppCompatActivity)?.toastSpammable(msg, length)
}

fun RecyclerView.defaultDivider(): DividerItemDecoration {
    return DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
}

//inline fun SharedPreferences.edit(action: SharedPreferences.() -> Unit) {
//    action(this).apply()
//}
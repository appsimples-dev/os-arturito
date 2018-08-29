package com.outsmart.os_arturito

import android.content.Context
import android.content.SharedPreferences
import com.outsmart.academicatlas.OSApplication

/**
 * Created by appsimples on 8/21/18.
 */
object SPManager {

    private const val FILE_NAME = "os_settings" // TODO change this to be custom

    private val sharedPreferences: SharedPreferences by lazy { initializeSharedPreferences() }

    private fun initializeSharedPreferences() = OSApplication.instance.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun write(key: String, value: String) {
        sharedPreferences.edit()?.putString(key, value)?.apply()
    }

    fun write(key: String, value: Boolean) {
        sharedPreferences.edit()?.putBoolean(key, value)?.apply()
    }

    fun read(key: String): String? {
        return sharedPreferences.getString(key, null)
    }


    fun delete(key: String) {
        sharedPreferences.edit()?.remove(key)?.apply()
    }

    fun clear() {
        sharedPreferences.edit()?.clear()?.apply()
    }
}
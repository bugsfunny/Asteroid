package com.goodayedi.asteroid.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPref {
    lateinit var mSharedPref: SharedPreferences

    fun init(context: Context) {
        mSharedPref = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
    }

    fun read(key: String, defValue: String): String? {
        return mSharedPref.getString(key, defValue)
    }

    fun write(key: String, value: String) {
        val prefsEditor = mSharedPref.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }
}




package com.example.UTAMU.SharePreforproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class Uerdetails(val context: Context) {
    private val PREFS_NAME = "loginprefs"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    fun save(KEY_NAME: String, text: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)

        editor!!.apply()
    }


    fun getValueString(KEY_NAME: String): String? =sharedPref.getString(KEY_NAME, null)

//    fun save(KEY_NAME: String, value: Int) {
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//
//        editor.putInt(KEY_NAME, value)
//
//        editor.commit()
//    }
//
//
//    fun getValueInt(KEY_NAME: String): Int = sharedPref.getInt(KEY_NAME, 0)
//
//    fun save(KEY_NAME: String, status: Boolean) {
//
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//
//        editor.putBoolean(KEY_NAME, status!!)
//
//        editor.commit()
//    }
//
//    fun getValueBoolien(KEY_NAME: String, defaultValue: Boolean): Boolean = sharedPref.getBoolean(KEY_NAME, defaultValue)

//    fun clearSharedPreference() {
//
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//
//        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
//
//        editor.clear()
//        editor.commit()
//    }
//
//    fun removeValue(KEY_NAME: String) {
//
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//
//        editor.remove(KEY_NAME)
//        editor.commit()
//    }
}


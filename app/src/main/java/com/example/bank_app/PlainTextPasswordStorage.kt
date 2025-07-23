package com.example.bank_app.util // Or your preferred package

import android.content.Context
import android.content.SharedPreferences

object PlainTextPasswordStorage {

    private const val PREFS_FILENAME = "demo_user_prefs"
    private const val KEY_PASSWORD = "user_password"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }

    fun savePassword(context: Context, password: String) {
        val prefs = getPrefs(context)
        with(prefs.edit()) {
            putString(KEY_PASSWORD, password)
            apply()
        }
    }

    fun getPassword(context: Context): String? {
        val prefs = getPrefs(context)
        return prefs.getString(KEY_PASSWORD, null)
    }

    fun isPasswordSet(context: Context): Boolean {
        val prefs = getPrefs(context)
        return prefs.contains(KEY_PASSWORD)
    }

    fun clearPassword(context: Context) {
        val prefs = getPrefs(context)
        with(prefs.edit()) {
            remove(KEY_PASSWORD)
            apply()
        }
    }
}


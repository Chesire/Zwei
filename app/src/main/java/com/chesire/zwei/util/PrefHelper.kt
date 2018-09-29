package com.chesire.zwei.util

import android.content.Context
import android.content.SharedPreferences
import com.chesire.zwei.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefHelper @Inject constructor(
    context: Context,
    private val sharedPreferences: SharedPreferences
) {
    private val bypassedWelcomeKey = context.getString(R.string.key_bypassed_welcome)
    private val bypassedRequestKey = context.getString(R.string.key_bypassed_request)

    var hasBypassedWelcome: Boolean
        get() = sharedPreferences.getBoolean(bypassedWelcomeKey, false)
        set(value) {
            sharedPreferences.edit { it.put(bypassedWelcomeKey to value) }
        }

    var hasBypassedRequest: Boolean
        get() = sharedPreferences.getBoolean(bypassedRequestKey, false)
        set(value) {
            sharedPreferences.edit { it.put(bypassedRequestKey to value) }
        }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        edit().apply { operation(this) }.apply()
    }

    private fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        val value = pair.second

        when (value) {
            is Boolean -> putBoolean(key, value)
            else -> error("Invalid type ${value.javaClass} attempted to be passed into SharedPreferences")
        }
    }
}
package com.chesire.zwei.util

import android.content.Context
import android.content.SharedPreferences
import com.chesire.zwei.R
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PrefHelper @Inject constructor(
    context: Context,
    private val sharedPreferences: SharedPreferences
) {
    private val bypassedWelcomeKey = context.getString(R.string.key_bypassed_welcome)
    private val bypassedRequestKey = context.getString(R.string.key_bypassed_request)
    private val acquiredCharacterKey = context.getString(R.string.key_acquired_character)
    private val allowAnalyticsKey = context.getString(R.string.key_allow_analytics)
    private val allowCrashReportingKey = context.getString(R.string.key_allow_crash_reporting)

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

    var hasAcquiredCharacter: Boolean
        get() = sharedPreferences.getBoolean(acquiredCharacterKey, false)
        set(value) {
            sharedPreferences.edit { it.put(acquiredCharacterKey to value) }
        }

    var allowAnalytics: Boolean
        get() = sharedPreferences.getBoolean(allowAnalyticsKey, false)
        set(value) {
            sharedPreferences.edit { it.put(allowAnalyticsKey to value) }
        }

    var allowCrashReporting: Boolean
        get() = sharedPreferences.getBoolean(allowCrashReportingKey, false)
        set(value) {
            sharedPreferences.edit { it.put(allowCrashReportingKey to value) }
        }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        edit().apply { operation(this) }.apply()
    }

    private fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val (key, value) = pair

        when (value) {
            is Boolean -> putBoolean(key, value)
            else -> error("Invalid type ${value.javaClass} attempted to be passed into SharedPreferences")
        }
    }
}

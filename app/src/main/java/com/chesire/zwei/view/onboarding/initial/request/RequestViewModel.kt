package com.chesire.zwei.view.onboarding.initial.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chesire.zwei.util.PrefHelper
import javax.inject.Inject

class RequestViewModel @Inject constructor(
    private val sharedPref: PrefHelper
) : ViewModel() {
    val analyticsEnabled = MutableLiveData<Boolean>(false)
    val crashReportingEnabled = MutableLiveData<Boolean>(false)

    fun saveAnalyticsChoices() {
        sharedPref.allowAnalytics = analyticsEnabled.value ?: false
        sharedPref.allowCrashReporting = crashReportingEnabled.value ?: false
    }
}

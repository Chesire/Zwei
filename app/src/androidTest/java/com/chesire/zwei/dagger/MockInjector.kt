package com.chesire.zwei.dagger

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.chesire.zwei.MockApplication
import com.chesire.zwei.dagger.components.DaggerMockComponent
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object MockInjector {
    fun init(mockApp: MockApplication) {
        DaggerMockComponent.builder()
            .application(mockApp)
            .build()
            .inject(mockApp)

        mockApp.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity?) {}

            override fun onActivityResumed(p0: Activity?) {}

            override fun onActivityStarted(p0: Activity?) {}

            override fun onActivityDestroyed(p0: Activity?) {}

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}

            override fun onActivityStopped(p0: Activity?) {}

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                handleActivity(p0)
            }
        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector || activity is Injectable) {
            AndroidInjection.inject(activity)
        }

        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                    }
                }, true
            )
        }
    }
}
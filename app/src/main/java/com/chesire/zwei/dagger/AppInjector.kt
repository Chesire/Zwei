package com.chesire.zwei.dagger

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.chesire.zwei.ZweiApplication
import com.chesire.zwei.dagger.components.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {
    fun init(zweiApp: ZweiApplication) {
        DaggerAppComponent.builder()
            .application(zweiApp)
            .build()
            .inject(zweiApp)

        zweiApp.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
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
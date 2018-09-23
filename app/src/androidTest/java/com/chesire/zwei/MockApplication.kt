package com.chesire.zwei

import android.app.Activity
import android.app.Application
import com.chesire.zwei.dagger.MockInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MockApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        MockInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}
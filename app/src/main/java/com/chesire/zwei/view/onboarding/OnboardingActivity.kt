package com.chesire.zwei.view.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.chesire.zwei.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.activityOnboardingFrame,
                WelcomeFragment.newInstance(),
                WelcomeFragment.tag
            )
            .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
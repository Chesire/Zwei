package com.chesire.zwei.view.onboarding

import android.os.Bundle
import com.chesire.zwei.R
import dagger.android.support.DaggerAppCompatActivity

class OnboardingActivity : DaggerAppCompatActivity() {
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
}
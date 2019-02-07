package com.chesire.zwei.view

import android.content.Intent
import android.os.Bundle
import com.chesire.zwei.util.PrefHelper
import com.chesire.zwei.view.onboarding.OnboardingActivity
import com.chesire.zwei.view.profile.ProfileActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PrimingActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (shouldDisplayOnboarding()) {
            startActivity(Intent(this, OnboardingActivity::class.java))
        } else {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        finish()
    }

    private fun shouldDisplayOnboarding() = !prefHelper.hasBypassedWelcome ||
            !prefHelper.hasBypassedRequest ||
            !prefHelper.hasAcquiredCharacter
}

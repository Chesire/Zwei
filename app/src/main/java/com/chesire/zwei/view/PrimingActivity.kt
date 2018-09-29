package com.chesire.zwei.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.chesire.zwei.dagger.Injectable
import com.chesire.zwei.util.PrefHelper
import com.chesire.zwei.view.onboarding.OnboardingActivity
import javax.inject.Inject

class PrimingActivity : Activity(), Injectable {
    @Inject
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (prefHelper.shouldDisplayOnboarding) {
            startActivity(Intent(this, OnboardingActivity::class.java))
        } else {
            // start the primary activity - for now we only have onboarding
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
        finish()
    }
}
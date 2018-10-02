package com.chesire.zwei.view

import android.content.Intent
import android.os.Bundle
import com.chesire.zwei.view.onboarding.OnboardingActivity
import dagger.android.support.DaggerAppCompatActivity

class PrimingActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Later will add a check if onboarding has been complete
        startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
    }
}
package com.chesire.zwei.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.chesire.zwei.view.onboarding.OnboardingActivity

class PrimingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Later will add a check if onboarding has been complete
        startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
    }
}
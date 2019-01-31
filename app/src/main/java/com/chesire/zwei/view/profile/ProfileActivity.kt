package com.chesire.zwei.view.profile

import android.os.Bundle
import com.chesire.zwei.R
import dagger.android.support.DaggerAppCompatActivity

class ProfileActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set initial view to onboarding for now, until the new layout is made
        setContentView(R.layout.activity_onboarding)
    }
}

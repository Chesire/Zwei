package com.chesire.zwei.view.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chesire.zwei.R
import com.chesire.zwei.util.PrefHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val (tag, fragment) = getInitialFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.activityOnboardingFrame,
                fragment,
                tag
            )
            .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    private fun getInitialFragment(): Pair<String, Fragment> {
        val fragment: Fragment
        val tag: String
        if (!prefHelper.hasBypassedWelcome) {
            fragment = WelcomeFragment.newInstance()
            tag = WelcomeFragment.tag
        } else if (!prefHelper.hasBypassedRequest) {
            fragment = RequestFragment.newInstance()
            tag = RequestFragment.tag
        } else {
            fragment = SelectWorldFragment.newInstance()
            tag = SelectWorldFragment.tag
        }

        return tag to fragment
    }
}
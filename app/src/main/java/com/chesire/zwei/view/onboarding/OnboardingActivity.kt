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

class OnboardingActivity : AppCompatActivity(), HasSupportFragmentInjector, OnboardingInteractor {
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

    override fun completeWelcome() {
        prefHelper.hasBypassedWelcome = true
        val (tag, fragment) = getFragmentDetails(RequestFragment.tag)
        loadFragment(tag, fragment)
    }

    override fun completeRequest() {
        prefHelper.hasBypassedRequest = true
        val (tag, fragment) = getFragmentDetails(EnterWorldFragment.tag)
        loadFragment(tag, fragment)
    }

    private fun getInitialFragment(): Pair<String, Fragment> {
        return if (!prefHelper.hasBypassedWelcome) {
            getFragmentDetails(WelcomeFragment.tag)
        } else if (!prefHelper.hasBypassedRequest) {
            getFragmentDetails(RequestFragment.tag)
        } else {
            getFragmentDetails(EnterWorldFragment.tag)
        }
    }

    private fun getFragmentDetails(tag: String): Pair<String, Fragment> {
        return when (tag) {
            WelcomeFragment.tag -> WelcomeFragment.tag to WelcomeFragment.newInstance()
            RequestFragment.tag -> RequestFragment.tag to RequestFragment.newInstance()
            EnterWorldFragment.tag -> EnterWorldFragment.tag to EnterWorldFragment.newInstance()
            else -> error("Unexpected tag $tag requested")
        }
    }

    private fun loadFragment(tag: String, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.activityOnboardingFrame,
                fragment,
                tag
            )
            .addToBackStack(null)
            .commit()
    }
}
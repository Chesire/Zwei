package com.chesire.zwei.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.chesire.zwei.R
import com.chesire.zwei.util.PrefHelper
import com.chesire.zwei.view.onboarding.initial.InitialInteractor
import com.chesire.zwei.view.onboarding.initial.RequestFragment
import com.chesire.zwei.view.onboarding.initial.WelcomeFragment
import com.chesire.zwei.view.onboarding.search.EnterCharacterFragment
import com.chesire.zwei.view.onboarding.search.EnterWorldFragment
import com.chesire.zwei.view.onboarding.search.SearchInteractor
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class OnboardingActivity : DaggerAppCompatActivity(), InitialInteractor, SearchInteractor {
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

    override fun completeEnterWorld() {
        val (tag, fragment) = getFragmentDetails(EnterCharacterFragment.tag)
        loadFragment(tag, fragment)
    }

    override fun completeEnterCharacter() {
        // Nothing yet
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
            EnterCharacterFragment.tag -> EnterCharacterFragment.tag to EnterCharacterFragment.newInstance()
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

package com.chesire.zwei.view.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.chesire.zwei.R
import com.chesire.zwei.util.PrefHelper
import com.chesire.zwei.view.onboarding.character.CharacterInteractor
import com.chesire.zwei.view.onboarding.character.isYourCharacter.IsYourCharacterFragment
import com.chesire.zwei.view.onboarding.character.loadingCharacter.LoadingCharacterFragment
import com.chesire.zwei.view.onboarding.character.selectCharacter.SelectCharacterFragment
import com.chesire.zwei.view.onboarding.initial.InitialInteractor
import com.chesire.zwei.view.onboarding.initial.request.RequestFragment
import com.chesire.zwei.view.onboarding.initial.welcome.WelcomeFragment
import com.chesire.zwei.view.onboarding.search.EnterCharacterFragment
import com.chesire.zwei.view.onboarding.search.EnterWorldFragment
import com.chesire.zwei.view.onboarding.search.SearchInteractor
import com.chesire.zwei.view.profile.ProfileActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

private const val FRAGMENT_KEY = "onboarding_fragment_key"

@Suppress("TooManyFunctions")
class OnboardingActivity
    : DaggerAppCompatActivity(),
    InitialInteractor,
    SearchInteractor,
    CharacterInteractor {

    private lateinit var currentFragment: Fragment

    @Inject
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val tag: String
        val fragment: Fragment
        val savedFragment: Fragment? = if (savedInstanceState != null) {
            supportFragmentManager.getFragment(savedInstanceState, FRAGMENT_KEY)
        } else {
            null
        }

        if (savedFragment == null) {
            val fragmentData = getInitialFragment()
            tag = fragmentData.first
            fragment = fragmentData.second
        } else {
            fragment = savedFragment
            tag = getTagForFragment(fragment)
        }

        currentFragment = fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityOnboardingFrame, fragment, tag)
            .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            currentFragment = supportFragmentManager.fragments.last()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            supportFragmentManager.putFragment(it, FRAGMENT_KEY, currentFragment)
        }
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
        val (tag, fragment) = getFragmentDetails(IsYourCharacterFragment.tag)
        loadFragment(tag, fragment)
    }

    override fun isYourCharacterIsIncorrect() {
        val (tag, fragment) = getFragmentDetails(SelectCharacterFragment.tag)
        loadFragment(tag, fragment)
    }

    override fun onCharacterChosen() {
        val (tag, fragment) = getFragmentDetails(LoadingCharacterFragment.tag)
        loadFragment(tag, fragment)
    }

    override fun completeLoadingCharacter() {
        prefHelper.hasAcquiredCharacter = true
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
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
            IsYourCharacterFragment.tag -> IsYourCharacterFragment.tag to IsYourCharacterFragment.newInstance()
            SelectCharacterFragment.tag -> SelectCharacterFragment.tag to SelectCharacterFragment.newInstance()
            LoadingCharacterFragment.tag -> LoadingCharacterFragment.tag to LoadingCharacterFragment.newInstance()
            else -> error("Unexpected tag $tag requested")
        }
    }

    private fun getTagForFragment(fragment: Fragment): String {
        return when (fragment) {
            is WelcomeFragment -> WelcomeFragment.tag
            is RequestFragment -> RequestFragment.tag
            is EnterWorldFragment -> EnterWorldFragment.tag
            is EnterCharacterFragment -> EnterCharacterFragment.tag
            is IsYourCharacterFragment -> IsYourCharacterFragment.tag
            is SelectCharacterFragment -> SelectCharacterFragment.tag
            is LoadingCharacterFragment -> LoadingCharacterFragment.tag
            else -> error("Unexpected fragment tag requested - $fragment")
        }
    }

    private fun loadFragment(tag: String, fragment: Fragment) {
        currentFragment = fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityOnboardingFrame, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}

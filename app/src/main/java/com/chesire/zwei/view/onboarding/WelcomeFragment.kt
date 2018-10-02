package com.chesire.zwei.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chesire.zwei.R
import com.chesire.zwei.xivapi.XIVApi
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_welcome.testButton
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

class WelcomeFragment : DaggerFragment() {
    @Inject
    lateinit var xivApi: XIVApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_welcome, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        testButton.setOnClickListener {
            launch {
                try {
                    val data = xivApi.searchForCharacter("Cheshire Cat", "Phoenix").await()

                    Timber.e("INCOMING DATA")
                    Timber.e("Name: ${data.data}.name")
                    Timber.e("Message: ${data.message}")
                    Timber.e("Found: ${data.data.count()} characters")
                } catch (ex: Exception) {
                    Timber.e(ex)
                }
            }
        }
    }

    companion object {
        const val tag = "WelcomeFragment"
        fun newInstance(): WelcomeFragment {
            return WelcomeFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
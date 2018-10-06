package com.chesire.zwei.view.onboarding.initial

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chesire.zwei.R
import kotlinx.android.synthetic.main.fragment_welcome.view.buttonNext

class WelcomeFragment : Fragment() {
    private lateinit var initialInteractor: InitialInteractor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false).apply {
            buttonNext.setOnClickListener {
                initialInteractor.completeWelcome()
            }
        }
    }

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        initialInteractor = context as InitialInteractor
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
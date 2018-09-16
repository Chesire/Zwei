package com.chesire.zwei.view.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chesire.zwei.R

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
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
package com.chesire.zwei.view.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chesire.zwei.R
import kotlinx.android.synthetic.main.fragment_welcome.view.buttonNext

class RequestFragment : Fragment() {
    private lateinit var onboardingInteractor: OnboardingInteractor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request, container, false).apply {
            buttonNext.setOnClickListener {
                onboardingInteractor.completeRequest()
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onboardingInteractor = context as OnboardingInteractor
    }

    companion object {
        const val tag = "RequestFragment"
        fun newInstance(): RequestFragment {
            return RequestFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
package com.chesire.zwei.view.onboarding.initial.request

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chesire.zwei.R
import com.chesire.zwei.view.onboarding.initial.InitialInteractor
import kotlinx.android.synthetic.main.fragment_request.buttonNext

class RequestFragment : Fragment() {
    private var initialInteractor: InitialInteractor? = null

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        initialInteractor = context as InitialInteractor
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_request, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener { initialInteractor?.completeRequest() }
    }

    companion object {
        const val tag = "RequestFragment"

        fun newInstance() = RequestFragment()
    }
}

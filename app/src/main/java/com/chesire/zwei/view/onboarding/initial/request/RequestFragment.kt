package com.chesire.zwei.view.onboarding.initial.request

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.chesire.zwei.R
import com.chesire.zwei.databinding.FragmentRequestBinding
import com.chesire.zwei.view.onboarding.initial.InitialInteractor
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RequestFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var initialInteractor: InitialInteractor? = null
    private val viewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(RequestViewModel::class.java)
    }

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        initialInteractor = context as InitialInteractor
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentRequestBinding>(
            inflater,
            R.layout.fragment_request,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            fragmentRequestNext.setOnClickListener {
                viewModel.saveAnalyticsChoices()
                initialInteractor?.completeRequest()
            }
        }.root
    }

    companion object {
        const val tag = "RequestFragment"

        fun newInstance() = RequestFragment()
    }
}

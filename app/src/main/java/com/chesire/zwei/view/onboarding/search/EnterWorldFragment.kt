package com.chesire.zwei.view.onboarding.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.chesire.zwei.BuildConfig
import com.chesire.zwei.R
import com.chesire.zwei.databinding.FragmentEnterWorldBinding
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_request.buttonNext
import javax.inject.Inject

class EnterWorldFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentEnterWorldBinding
    private var searchInteractor: SearchInteractor? = null
    private val viewModel: OnboardingViewModel by lazy {
        ViewModelProviders
            .of(requireActivity(), viewModelFactory)
            .get(OnboardingViewModel::class.java)
    }

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        searchInteractor = context as SearchInteractor
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentEnterWorldBinding>(
            inflater,
            R.layout.fragment_enter_world,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(viewLifecycleOwner)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener { searchInteractor?.completeEnterWorld() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.vm = viewModel

        if (BuildConfig.DEBUG) {
            viewModel.world.value = "Phoenix"
            viewModel.characterName.value = "Cheshire Cat"
        }
    }

    companion object {
        const val tag = "EnterWorldFragment"

        fun newInstance() = EnterWorldFragment()
    }
}

package com.chesire.zwei.view.onboarding.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.chesire.zwei.R
import com.chesire.zwei.databinding.FragmentEnterCharacterBinding
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import com.chesire.zwei.util.LiveDataStatus
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EnterCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentEnterCharacterBinding
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
        return DataBindingUtil.inflate<FragmentEnterCharacterBinding>(
            inflater,
            R.layout.fragment_enter_character,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(viewLifecycleOwner)
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.searchStatus.observe(
            viewLifecycleOwner,
            Observer {
                onSearchStatusChange(it)
            }
        )

        binding.vm = viewModel
    }

    private fun onSearchStatusChange(status: LiveDataStatus) {
        when (status) {
            LiveDataStatus.Loading -> {
                // display loading indicator
            }
            LiveDataStatus.Error -> {
                // Display appropriate error state
            }
            LiveDataStatus.Success -> searchInteractor?.completeEnterCharacter()
        }
    }

    companion object {
        const val tag = "EnterCharacterFragment"

        fun newInstance() = EnterCharacterFragment()
    }
}

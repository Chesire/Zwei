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
import com.chesire.zwei.databinding.FragmentEntercharacterBinding
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import com.chesire.zwei.xivapi.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EnterCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentEntercharacterBinding
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
        return DataBindingUtil.inflate<FragmentEntercharacterBinding>(
            inflater,
            R.layout.fragment_entercharacter,
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

    private fun onSearchStatusChange(status: Status) {
        when (status) {
            Status.Loading -> {
                // display loading indicator
            }
            Status.Error -> {
                // Display appropriate error state
            }
            Status.Success -> searchInteractor?.completeEnterCharacter()
        }
    }

    companion object {
        const val tag = "EnterCharacterFragment"

        fun newInstance() = EnterCharacterFragment()
    }
}

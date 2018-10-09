package com.chesire.zwei.view.onboarding.character

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
import com.chesire.zwei.databinding.FragmentLoadingcharacterBinding
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoadingCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var binding: FragmentLoadingcharacterBinding
    private lateinit var characterInteractor: CharacterInteractor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentLoadingcharacterBinding>(
            inflater,
            R.layout.fragment_loadingcharacter,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(this@LoadingCharacterFragment)
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders
            .of(activity!!, viewModelFactory)
            .get(OnboardingViewModel::class.java)
            .apply {
                getCharacterStatus.observe(
                    this@LoadingCharacterFragment,
                    Observer { onGetCharacterStatusChange(it) })
            }

        binding.vm = viewModel
        viewModel.getCharacter()
    }

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        characterInteractor = context as CharacterInteractor
    }

    private fun onGetCharacterStatusChange(status: GetCharacterStatus) {
        when (status) {
            GetCharacterStatus.Loading -> {
                // Display loading UI
            }
            GetCharacterStatus.GotCharacter -> characterInteractor.completeChooseCharacter()
            GetCharacterStatus.GotInfo -> {
                // Request successful, but character is being added to the api
            }
            GetCharacterStatus.Error -> {
                // Request failed
            }
        }
    }

    companion object {
        const val tag = "LoadingCharacterFragment"
        fun newInstance(): LoadingCharacterFragment {
            return LoadingCharacterFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
package com.chesire.zwei.view.onboarding.character.loadingCharacter

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
import com.chesire.zwei.databinding.FragmentLoadingCharacterBinding
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import com.chesire.zwei.view.onboarding.character.CharacterInteractor
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoadingCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var characterInteractor: CharacterInteractor? = null
    private val viewModel: OnboardingViewModel by lazy {
        ViewModelProviders
            .of(requireActivity(), viewModelFactory)
            .get(OnboardingViewModel::class.java)
    }

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        characterInteractor = context as CharacterInteractor
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentLoadingCharacterBinding>(
            inflater,
            R.layout.fragment_loading_character,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getCharacterStatus.observe(
            viewLifecycleOwner,
            Observer {
                onGetCharacterStatusChange(it)
            }
        )

        viewModel.getCharacter()
    }

    private fun onGetCharacterStatusChange(status: GetCharacterStatus) {
        when (status) {
            GetCharacterStatus.Loading -> {
                // Display loading UI
            }
            GetCharacterStatus.GotCharacter -> characterInteractor?.completeLoadingCharacter()
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

        fun newInstance() = LoadingCharacterFragment()
    }
}

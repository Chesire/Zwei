package com.chesire.zwei.view.onboarding.character.selectCharacter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.chesire.zwei.R
import com.chesire.zwei.databinding.FragmentSelectCharacterBinding
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import com.chesire.zwei.view.onboarding.character.CharacterInteractor
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SelectCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentSelectCharacterBinding
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
        return DataBindingUtil.inflate<FragmentSelectCharacterBinding>(
            inflater,
            R.layout.fragment_select_character,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(viewLifecycleOwner)
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //binding.vm = viewModel
    }

    companion object {
        const val tag = "SelectCharacterFragment"

        fun newInstance() = SelectCharacterFragment()
    }
}

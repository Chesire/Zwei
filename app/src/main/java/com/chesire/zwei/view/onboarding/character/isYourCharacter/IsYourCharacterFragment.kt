package com.chesire.zwei.view.onboarding.character.isYourCharacter

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
import com.chesire.zwei.databinding.FragmentChooseCharacterBinding
import com.chesire.zwei.view.GlideApp
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import com.chesire.zwei.view.onboarding.character.CharacterInteractor
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_choose_character.imageAvatar
import javax.inject.Inject

class IsYourCharacterFragment : DaggerFragment() {
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
        return DataBindingUtil.inflate<FragmentChooseCharacterBinding>(
            inflater,
            R.layout.fragment_choose_character,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            buttonYes.setOnClickListener { characterInteractor?.onCharacterChosen() }
            buttonNo.setOnClickListener { characterInteractor?.isYourCharacterIsIncorrect() }
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.currentCharacter.observe(
            viewLifecycleOwner,
            Observer { characterModel ->
                GlideApp.with(requireContext())
                    .load(characterModel.avatar)
                    .into(imageAvatar)
            }
        )
    }

    companion object {
        const val tag = "IsYourCharacterFragment"

        fun newInstance() = IsYourCharacterFragment()
    }
}

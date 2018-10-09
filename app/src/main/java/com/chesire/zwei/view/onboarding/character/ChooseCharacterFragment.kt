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
import com.chesire.zwei.databinding.FragmentChoosecharacterBinding
import com.chesire.zwei.view.GlideApp
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_choosecharacter.imageAvatar
import javax.inject.Inject

class ChooseCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var binding: FragmentChoosecharacterBinding
    private lateinit var characterInteractor: CharacterInteractor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentChoosecharacterBinding>(
            inflater,
            R.layout.fragment_choosecharacter,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(this@ChooseCharacterFragment)
            buttonYes.setOnClickListener { characterInteractor.completeChooseCharacter() }
            buttonNo.setOnClickListener {
                // load ui to select character
            }
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders
            .of(activity!!, viewModelFactory)
            .get(OnboardingViewModel::class.java)
            .apply {
                currentCharacter.observe(
                    this@ChooseCharacterFragment,
                    Observer {
                        GlideApp.with(requireContext())
                            .load(viewModel.currentCharacter.value!!.avatar)
                            .into(imageAvatar)
                    })
            }

        binding.vm = viewModel
    }

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        characterInteractor = context as CharacterInteractor
    }

    companion object {
        const val tag = "ChooseCharacterFragment"
        fun newInstance(): ChooseCharacterFragment {
            return ChooseCharacterFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
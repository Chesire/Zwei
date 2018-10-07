package com.chesire.zwei.view.onboarding.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.chesire.zwei.R
import com.chesire.zwei.databinding.FragmentGuesscharacterBinding
import com.chesire.zwei.view.GlideApp
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_guesscharacter.imageAvatar
import javax.inject.Inject

class GuessCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var binding: FragmentGuesscharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentGuesscharacterBinding>(
            inflater,
            R.layout.fragment_guesscharacter,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(this@GuessCharacterFragment)
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders
            .of(activity!!, viewModelFactory)
            .get(OnboardingViewModel::class.java)

        binding.vm = viewModel

        GlideApp.with(requireContext())
            .load(viewModel.currentCharacter.value!!.avatar)
            .into(imageAvatar)
    }

    companion object {
        const val tag = "GuessCharacterFragment"
        fun newInstance(): GuessCharacterFragment {
            return GuessCharacterFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
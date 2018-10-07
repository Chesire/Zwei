package com.chesire.zwei.view.onboarding.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
    private lateinit var searchInteractor: SearchInteractor

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
            buttonYes.setOnClickListener { searchInteractor.completeChooseCharacter() }
            buttonNo.setOnClickListener {
                // load selection of character
            }
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

    @Suppress("UnsafeCast")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        searchInteractor = context as SearchInteractor
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
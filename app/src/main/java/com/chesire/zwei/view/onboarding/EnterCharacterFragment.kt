package com.chesire.zwei.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.chesire.zwei.R
import com.chesire.zwei.dagger.Injectable
import com.chesire.zwei.databinding.FragmentEntercharacterBinding
import javax.inject.Inject

class EnterCharacterFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var binding: FragmentEntercharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentEntercharacterBinding>(
            inflater,
            R.layout.fragment_enterworld,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(this@EnterCharacterFragment)
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders
            .of(activity!!, viewModelFactory)
            .get(OnboardingViewModel::class.java)

        binding.vm = viewModel
    }

    companion object {
        const val tag = "EnterCharacterFragment"
        fun newInstance(): EnterCharacterFragment {
            return EnterCharacterFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
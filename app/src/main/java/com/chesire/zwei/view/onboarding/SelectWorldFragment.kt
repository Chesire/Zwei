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
import com.chesire.zwei.databinding.FragmentSelectworldBinding
import javax.inject.Inject

class SelectWorldFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var binding: FragmentSelectworldBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentSelectworldBinding>(
            inflater,
            R.layout.fragment_selectworld,
            container,
            false
        ).apply {
            binding = this
            setLifecycleOwner(this@SelectWorldFragment)
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
        const val tag = "SelectWorldFragment"
        fun newInstance(): SelectWorldFragment {
            return SelectWorldFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
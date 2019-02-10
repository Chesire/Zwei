package com.chesire.zwei.view.onboarding.character.selectCharacter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chesire.zwei.R
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import com.chesire.zwei.view.onboarding.character.CharacterInteractor
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_select_character.fragmentSelectCharacterCharacterList
import javax.inject.Inject

class SelectCharacterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var characterInteractor: CharacterInteractor? = null
    private lateinit var viewAdapter: SelectCharacterViewAdapter
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
    ): View? = inflater.inflate(R.layout.fragment_select_character, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewAdapter = SelectCharacterViewAdapter()

        fragmentSelectCharacterCharacterList.apply {
            adapter = viewAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }

        viewModel.foundCharacters.observe(
            viewLifecycleOwner,
            Observer { characters ->
                viewAdapter.setCharacters(characters)
            }
        )
    }

    companion object {
        const val tag = "SelectCharacterFragment"

        fun newInstance() = SelectCharacterFragment()
    }
}

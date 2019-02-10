package com.chesire.zwei.view.onboarding.character.selectCharacter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chesire.zwei.R
import com.chesire.zwei.view.onboarding.OnboardingViewModel
import com.chesire.zwei.view.onboarding.character.CharacterInteractor
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_select_character.fragmentSelectCharacterCharacterList
import kotlinx.android.synthetic.main.fragment_select_character.fragmentSelectCharacterConfirm
import javax.inject.Inject

class SelectCharacterFragment :
    DaggerFragment(),
    SelectCharacterViewAdapter.OnCharacterSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var characterInteractor: CharacterInteractor? = null
    private val viewAdapter = SelectCharacterViewAdapter(this)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSelectCharacterCharacterList.apply {
            adapter = viewAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }

        fragmentSelectCharacterConfirm.setOnClickListener { characterInteractor?.onCharacterChosen() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewAdapter.setCharacters(viewModel.foundCharacters.value ?: emptyList())
    }

    override fun characterSelected(characterModel: SearchCharacterModel) {
        fragmentSelectCharacterConfirm.isEnabled = true
        viewModel.currentCharacter.value = characterModel
    }

    companion object {
        const val tag = "SelectCharacterFragment"

        fun newInstance() = SelectCharacterFragment()
    }
}

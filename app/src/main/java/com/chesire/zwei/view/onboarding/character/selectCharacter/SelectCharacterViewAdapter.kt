package com.chesire.zwei.view.onboarding.character.selectCharacter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chesire.zwei.R
import com.chesire.zwei.view.GlideApp
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.android.synthetic.main.adapter_item_search_character.view.adapterItemSearchCharacterAvatar
import kotlinx.android.synthetic.main.adapter_item_search_character.view.adapterItemSearchCharacterName
import kotlinx.android.synthetic.main.adapter_item_search_character.view.adapterItemSearchCharacterServer

class SelectCharacterViewAdapter(
    private val listener: OnCharacterSelectedListener
) : RecyclerView.Adapter<SelectCharacterViewAdapter.ViewHolder>() {

    private val characters = mutableListOf<SearchCharacterModel>()

    fun setCharacters(newCharacters: List<SearchCharacterModel>) = characters.addAll(newCharacters)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_item_search_character,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = characters.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var characterModel: SearchCharacterModel

        fun bind(model: SearchCharacterModel) {
            characterModel = model

            view.setOnClickListener { listener.characterSelected(characterModel) }
            view.adapterItemSearchCharacterName.text = model.name
            view.adapterItemSearchCharacterServer.text = model.server

            GlideApp.with(view)
                .load(model.avatar)
                // .placeholder()
                // .error()
                .into(view.adapterItemSearchCharacterAvatar)
        }
    }

    interface OnCharacterSelectedListener {
        fun characterSelected(characterModel: SearchCharacterModel)
    }
}

package com.chesire.zwei.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chesire.zwei.R

class SelectWorldFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_selectworld, container, false)

    companion object {
        const val tag = "SelectWorldFragment"
        fun newInstance(): SelectWorldFragment {
            return SelectWorldFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
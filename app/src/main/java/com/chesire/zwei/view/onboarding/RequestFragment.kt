package com.chesire.zwei.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chesire.zwei.R
import com.chesire.zwei.dagger.Injectable

class RequestFragment : Fragment(), Injectable {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_request, container, false)

    companion object {
        const val tag = "RequestFragment"
        fun newInstance(): RequestFragment {
            return RequestFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
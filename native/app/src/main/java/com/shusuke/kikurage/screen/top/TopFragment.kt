package com.shusuke.kikurage.screen.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.shusuke.kikurage.app.AppRootActivity
import com.shusuke.kikurage.R

class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppRootActivity).setupToolbarTitle(R.string.fragment_top_title)
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Transition
        val loginButton = view.findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_topFragment_to_loginFragment)
        }
    }
}
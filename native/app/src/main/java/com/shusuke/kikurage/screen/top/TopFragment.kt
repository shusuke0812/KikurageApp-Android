package com.shusuke.kikurage.screen.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.shusuke.kikurage.R

class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        val toolbarTitleTextView = toolbar?.findViewById<TextView>(R.id.toolbar_title)
        toolbarTitleTextView?.setText(R.string.fragment_top_title)
    }
}
package com.formationandroid.projectmemokotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.formationandroid.projectmemokotlin.R
import com.formationandroid.projectmemokotlin.activities.DetailActivity
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(),
    View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null && view != null) {
            val name = requireArguments().getString(EXTRA_NAME)
            val description = requireArguments().getString(EXTRA_DESCRIPTION)
            MemoName.text = name
            MemoDescription.text = description
        }
    }

    override fun onClick(v: View) {
        val activity = activity
        if (activity is DetailActivity) {
            (activity as DetailActivity?)?.afficheNote(v)
        }
    }

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_DESCRIPTION = "description"
    }
}
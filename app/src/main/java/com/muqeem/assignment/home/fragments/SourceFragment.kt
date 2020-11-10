package com.muqeem.assignment.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinrnd.base.views.BaseFragment
import com.muqeem.assignment.R
import com.muqeem.assignment.home.viewmodels.NewsViewModel

class SourceFragment : BaseFragment() {

    private lateinit var homeViewModel: NewsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
       // val textView: TextView = root.findViewById(R.id.recyclerView)

        return root
    }
}
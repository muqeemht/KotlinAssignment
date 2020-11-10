package com.muqeem.assignment.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrnd.base.views.BaseFragment
import com.muqeem.assignment.R
import com.muqeem.assignment.home.adapters.SourceListAdapter
import com.muqeem.assignment.home.models.SourceModel
import com.muqeem.assignment.home.viewmodels.SourceViewModel

class SourceFragment : BaseFragment() {

    private lateinit var sourceAdapter: SourceListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView= root.findViewById(R.id.recyclerView)
        initAdapter()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: SourceViewModel by viewModels()
        setupNetworkListeners(viewModel)
        viewModel.onSourcesReceived().observe(viewLifecycleOwner, Observer<List<SourceModel>> { newsList->
            sourceAdapter.updateData(newsList)
        })
    }

    private fun initAdapter() {
        sourceAdapter = SourceListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = sourceAdapter
    }
}
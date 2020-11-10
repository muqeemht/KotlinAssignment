package com.muqeem.assignment.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrnd.base.views.BaseFragment
import com.muqeem.assignment.home.viewmodels.NewsViewModel
import com.muqeem.assignment.R
import com.muqeem.assignment.home.adapters.HeadlinesAdapter
import com.muqeem.assignment.home.adapters.NewsListAdapter
import com.muqeem.assignment.home.models.NewsModel

class HeadlinesFragment : BaseFragment() {
    private lateinit var newsListAdapter: HeadlinesAdapter
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
        val viewModel: NewsViewModel by viewModels()
        setupNetworkListeners(viewModel)
        viewModel.onHeadlinesReceived("bbc-news").observe(viewLifecycleOwner, Observer<PagedList<NewsModel>> { newsList->
            newsListAdapter.submitList(newsList)
        })
    }

    private fun initAdapter() {
        newsListAdapter = HeadlinesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = newsListAdapter
           }
}
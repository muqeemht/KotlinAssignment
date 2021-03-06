package com.muqeem.assignment.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
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
    val args: HeadlinesFragmentArgs by navArgs()

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
        viewModel.onHeadlinesReceived(args.sourceName).observe(viewLifecycleOwner, Observer<PagedList<NewsModel>> { newsList->
            newsListAdapter.submitList(newsList)
        })
    }

    private fun initAdapter() {
        newsListAdapter = HeadlinesAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = newsListAdapter
           }

    override fun onClick(v: View?) {
        super.onClick(v)
        Navigation.findNavController(view!!).popBackStack(R.id.nav_headlines, false)
        Navigation.findNavController(view!!).navigate(HeadlinesFragmentDirections.newsDetails().setNewsModel(newsListAdapter.getSelectedItem(v?.tag as Int)))
    }
}
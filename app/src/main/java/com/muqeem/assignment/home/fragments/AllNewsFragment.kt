package com.muqeem.assignment.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrnd.base.views.BaseFragment
import com.muqeem.assignment.home.viewmodels.NewsViewModel
import com.muqeem.assignment.R
import com.muqeem.assignment.home.adapters.NewsListAdapter
import com.muqeem.assignment.home.models.NewsModel

class AllNewsFragment : BaseFragment() {
    private lateinit var newsListAdapter: NewsListAdapter
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
        viewModel.onAllNewsReceived().observe(viewLifecycleOwner, Observer<PagedList<NewsModel>> { newsList->
            newsListAdapter.submitList(newsList)
        })
    }

    private fun initAdapter() {
        newsListAdapter = NewsListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = newsListAdapter
           }

    override fun onClick(v: View?) {
        super.onClick(v)

        Navigation.findNavController(view!!).popBackStack(R.id.nav_all_news, false)
        Navigation.findNavController(view!!).navigate(AllNewsFragmentDirections.newsDetails().setNewsModel(newsListAdapter.getSelectedItem(v?.tag as Int)))
    }

    fun NavController.navigateSafe(
        navDirections: NavDirections? = null
    ) {
        try {
            navDirections?.let {
                this.navigate(navDirections)
            }
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }
}
package com.muqeem.assignment.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinrnd.base.views.BaseFragment
import com.muqeem.assignment.home.viewmodels.NewsViewModel
import com.muqeem.assignment.R
import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.home.adapters.HeadlinesAdapter
import com.muqeem.assignment.home.adapters.NewsListAdapter
import com.muqeem.assignment.home.models.NewsData
import com.muqeem.assignment.home.models.NewsModel

class NewsDetailFragment : BaseFragment() {
    val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_news_detail, container, false)

        val newsTitle = root.findViewById(R.id.tv_title) as TextView
        val newsDesc = root.findViewById(R.id.tv_desc) as TextView
        val imageView = root.findViewById(R.id.image_view) as ImageView

        val news: NewsData? = args.newsModel
        if (news != null) {
            newsTitle.text = news.title
        }
        if (news != null) {
            newsDesc.text = news.desc
        }

        Glide.with(MyApplication.getAppContext())
            .load(news?.url)
            .into(imageView);
        return root
    }

}
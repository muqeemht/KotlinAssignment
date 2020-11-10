package com.muqeem.assignment.home.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muqeem.assignment.R
import com.muqeem.assignment.home.models.NewsModel


class NewsListAdapter : PagedListAdapter<NewsModel, NewsListAdapter.NewsViewHolder?>(NewsModel.diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsListAdapter.NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: NewsModel?) {
            if (news != null) {
                val newsTitle = itemView.findViewById(R.id.tv_title) as TextView
                val newsDesc = itemView.findViewById(R.id.tv_desc) as TextView
                newsTitle.text = news.title
                newsDesc.text = news.desc
            }
        }

    }
}
package com.muqeem.assignment.home.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muqeem.assignment.R
import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.home.models.NewsModel


class HeadlinesAdapter : PagedListAdapter<NewsModel, HeadlinesAdapter.NewsViewHolder?>(NewsModel.diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadlinesAdapter.NewsViewHolder {
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
                val imageView = itemView.findViewById(R.id.image_view) as ImageView
                newsTitle.text = news.title
                newsDesc.text = news.author

                Glide.with(MyApplication.getAppContext())
                    .load(news.urlToImage)
                    .into(imageView);

            }
        }

    }
}
package com.muqeem.assignment.home.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muqeem.assignment.R
import com.muqeem.assignment.home.models.SourceModel


class SourceListAdapter(private val mListener: View.OnClickListener) : RecyclerView.Adapter<SourceListAdapter.NewsViewHolder>() {
    private var sourcesList: List<SourceModel>? = null

    fun updateData(sourcesList: List<SourceModel>?){
        this.sourcesList = sourcesList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SourceListAdapter.NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.headlines_item, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.setTag(sourcesList?.get(position)?.source)
        holder.itemView.setOnClickListener(mListener)
        sourcesList?.get(position)?.let { holder.bind(it) }


    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: SourceModel) {
            if (news != null) {

                val newsTitle = itemView.findViewById(R.id.tv_title) as TextView
                newsTitle.text = news.name

            }
        }

    }

    override fun getItemCount(): Int {
        if(sourcesList != null){
            return sourcesList!!.size
        }
        return 0;
    }
}
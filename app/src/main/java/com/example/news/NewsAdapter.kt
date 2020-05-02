package com.example.news


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class NewsAdapter(
    private val context: Context,
    private val list: List<Article>
) : RecyclerView.Adapter<NewsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflatedView = LayoutInflater.from(parent.context)
        return NewsHolder(inflatedView, parent, context)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article = list.get(position)
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

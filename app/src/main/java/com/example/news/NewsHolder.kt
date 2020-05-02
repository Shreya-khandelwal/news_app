package com.example.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val context: Context
) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.news_list_item, parent, false)) {
    private var textSource: TextView? = null
    private var textTitle: TextView? = null
    private var textContent: TextView? = null
    private var image:ImageView?= null


    init {
        textSource = itemView.findViewById(R.id.tv_source)
        textTitle = itemView.findViewById(R.id.tv_title)
        textContent = itemView.findViewById(R.id.tv_content)
        image = itemView.findViewById(R.id.tv_image)
    }
    fun bind(article: Article) {
        textSource?.text = article.source.name
        textTitle?.text = article.title
        textContent?.text = article.content
        Glide.with(context).load(article.urlToImage).into(image)
    }
}


package com.example.news

import android.graphics.Color
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val url =  "http://newsapi.org/v2/top-headlines?country=in&apiKey=c8bc350bff114c9faa9c38a1d3163ab8"

    val newsList: ArrayList<Article> = ArrayList()
    var objAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        val actionBar = supportActionBar
        actionBar!!.title = "Google News"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rv_news_list.layoutManager = LinearLayoutManager(this)
        objAdapter = NewsAdapter(this, newsList)
        rv_news_list.adapter = objAdapter



        getData()

        itemsswipetorefresh.setColorSchemeColors(Color.BLUE)
        itemsswipetorefresh.setOnRefreshListener {
            newsList.clear()
            getData()
            objAdapter = NewsAdapter(this, newsList)
            rv_news_list.adapter = objAdapter
            itemsswipetorefresh.isRefreshing = false
        }
    }

        fun getData() {
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val jsonAdapter: JsonAdapter<Articles> = moshi.adapter<Articles>(
                        Articles::class.java
                    )

                    val articleRes: Articles? = jsonAdapter.fromJson(response)

                    articleRes?.let {
                        newsList.addAll(it.articles)
                        objAdapter?.notifyDataSetChanged()
                    }

                },
                Response.ErrorListener { })
            queue.add(stringRequest)
        }

}

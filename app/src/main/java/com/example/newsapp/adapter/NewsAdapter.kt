package com.example.newsapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article
import java.time.Instant


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {



    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Title: TextView = itemView.findViewById(R.id.tvTitle)
        val Description: TextView = itemView.findViewById(R.id.tvDescription)
        val Published: TextView = itemView.findViewById(R.id.tvPublishedAt)
        val Source: TextView = itemView.findViewById(R.id.tvSource)

    }

    // DiffUtil Async works in Background Thread
    // DiffUtil only  Updates those data which are different to Old List

    private val differCallBACK = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url

            // check same ID but Id use in Only in local database
            // we use url is also unique article

        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
            // check same Data
        }
    }

    // differ is a tool which find the differ in two list
    val differ = AsyncListDiffer(
        this,
        differCallBACK
    )  // Async is used to run in background not in main thread

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        // Create the ViewHolder and the store the Views and recycle

        val itemView =// LayoutInflater gives the object of Layout
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_article, parent, false)

//      // create the Views
        return ArticleViewHolder(itemView)
        //create the View Holder

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {


        // set the data in Views

        val article = differ.currentList[position]
        holder.itemView.apply {


            val imageView: ImageView = findViewById(R.id.ivArticleImage)
            Glide
                .with(this)
                .load(article.urlToImage)
                .into(imageView)

            holder.Source.text = article.source.name
            holder.Title.text = article.title
            holder.Description.text = article.description
            holder.Published.text = article.publishedAt


            setOnClickListener {
                // Check article if it is not equal to null then it pass the Current article
                onItemClickListener?.let { it(article) }
            }
        }

    }
    // we can click the single article and see the web page

    // Article ->   Current Article
    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}


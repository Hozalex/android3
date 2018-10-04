package com.ahozyainov.cloudshare.view.fragment.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.view.GlideApp
import com.ahozyainov.cloudshare.R.layout.image_recycler_search as imageRecyclerSearch

class SearchAdapter(private val items: List<String>,
                    private var onSearchImageClickListener: OnSearchImageClickListener)
    : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val searchViewHolder = SearchViewHolder(LayoutInflater.from(parent.context)
                .inflate(imageRecyclerSearch, parent, false) as ImageView)
        searchViewHolder.imageView.setOnClickListener {
            onSearchImageClickListener.onSearchImageClick(searchViewHolder.adapterPosition)
        }
        return searchViewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if (items[position].isNotEmpty()) {
            GlideApp.with(holder.imageView.context)
                    .load(items[position])
                    .placeholder(R.mipmap.example)
                    .centerCrop()
                    .into(holder.imageView)
        }
    }

    class SearchViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    interface OnSearchImageClickListener {
        fun onSearchImageClick(imagePosition: Int)
    }
}
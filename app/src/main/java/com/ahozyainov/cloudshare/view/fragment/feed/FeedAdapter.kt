package com.ahozyainov.cloudshare.view.fragment.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.view.GlideApp
import com.ahozyainov.cloudshare.R.layout.image_recycler_feed as imageRecyclerFeed

class FeedAdapter(private val items: List<String>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {

        return FeedViewHolder(LayoutInflater.from(parent.context)
                .inflate(imageRecyclerFeed, parent, false) as ImageView)
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        GlideApp.with(holder.imageView.context)
                .load(items[position])
                .placeholder(R.mipmap.example)
                .into(holder.imageView)
    }

    class FeedViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

}
package com.shazawdidi.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shazawdidi.pojoClasses.Articles
import com.shazawdidi.ui.NewsDetails
import com.shazawdidi.ui.R
import java.util.*


class NewsAdapter(private val mContext: Context, private val mList: ArrayList<Articles>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    // getting row list View to add the data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_news_list,
            parent,
            false
        )
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.author.text = ItemsViewModel.author
        holder.title.text = ItemsViewModel.title

        // show and load image
        Glide.with(mContext)
            .load(ItemsViewModel.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

            }).into(holder.imgUrl)


        // when user click to row Move data into anther activity
        holder.root.setOnClickListener {
            val intent = Intent(mContext, NewsDetails::class.java)
            intent.putExtra("urlToImage", ItemsViewModel.urlToImage)
            intent.putExtra("title", ItemsViewModel.title)
            intent.putExtra("description", ItemsViewModel.description)
            mContext.startActivity(intent)
        }
    }

    // return the size of listView
    override fun getItemCount(): Int {
        return mList.size
    }

    // view holder row
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imgUrl: ImageView = itemView.findViewById(R.id.imgUrl)
        val author: TextView = itemView.findViewById(R.id.author)
        val title: TextView = itemView.findViewById(R.id.title)
        val progressBar: ProgressBar = itemView.findViewById(R.id.loadProgress)
        val root: RelativeLayout = itemView.findViewById(R.id.root)
    }
}
